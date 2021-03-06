package akirashino.honapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ItemActivity extends Activity {

	DBAdapter myDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		openDB();
		poplistview();
		
	}

	@SuppressWarnings("deprecation")
	private void poplistview() {
		Cursor c = myDB.getAllItem();

		// lifetime
		this.startManagingCursor(c);

		// setup mapping from cursor to view

		String[] fromName = new String[] { DBAdapter.KEY_NAME,
				DBAdapter.KEY_RESID };

		int[] toViewId = new int[] { R.id.name, R.id.icon };

		// adapter
		SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(this, // context
				R.layout.list_object, // row template
				c, fromName, toViewId);

		ListView list = (ListView) this.findViewById(R.id.itemlist);
		list.setAdapter(myAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Cursor c = myDB.getAllItem();
				Intent in = new Intent(ItemActivity.this, ItemObjectActivity.class);
				
			    in.putExtra("_id",arg2+1);
			    startActivity(in);
				
			}
		});
		
	}
	private void openDB() {
		myDB = new DBAdapter(this);
		myDB.open();
	}

	protected void onDestroy() {
		super.onDestroy();
		closeDB();
	}

	private void closeDB() {
		myDB.close();

	}
}
