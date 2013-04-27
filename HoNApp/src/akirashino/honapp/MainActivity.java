package akirashino.honapp;

import java.io.IOException;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TabHost;

/*
 * Steps to using the DB:
 * 1. [DONE] Instantiate the DB Adapter
 * 2. [DONE] Open the DB
 * 3. [DONE] use get, insert, delete, .. to change or call data.
 * 4. [DONE]Close the DB
 */

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	
	private TabHost myTabHost;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myTabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		DataBaseHelper myDbHelper;
		myDbHelper = new DataBaseHelper(this);

		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {
			
			throw new Error("Unable to create database");

		}

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		// Hero Tab
		intent = new Intent(this, HeroActivity.class);
		spec = myTabHost.newTabSpec("heroes").setIndicator("Heroes")
				.setContent(intent);
		myTabHost.addTab(spec);

		// Item Tab
		intent = new Intent(this, ItemActivity.class);
		spec = myTabHost.newTabSpec("items").setIndicator("Items")
				.setContent(intent);
		myTabHost.addTab(spec);

	}

}
