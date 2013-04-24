package akirashino.honapp;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

/*
 * 1. Instantiate the DBAdapter
 * 2. Open DB
 * 3. Use DB commands such as select
 * 4. Close DB
 */

public class HeroActivity extends Activity {
	DBAdapter myDB;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		ListView listView = (ListView) findViewById(R.id.listView1);
		setContentView(R.layout.activity_hero);
		openDB();
		poplistview();
	}

	@SuppressWarnings("deprecation")
	private void poplistview() {
		Cursor c = myDB.getAllHero();
//		Cursor c2 = myDB.getAllHeroImage();
		// lifetime
		this.startManagingCursor(c);		
//		this.startManagingCursor(c2);
		// setup mapping from cursor to view
		
		String[] fromName = new String[]{DBAdapter.KEY_NAME,DBAdapter.KEY_RESID};
//		String[] imgName = new String[]{};
		int[] toViewId = new int[]{R.id.name,R.id.icon};
//		int[] imgViewId = new int[]{};
		
		// adapter
		SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(
				this, //context
				R.layout.list_object, // row template
				c,
				fromName,
				toViewId
				);
//		SimpleCursorAdapter imgAdapter = new SimpleCursorAdapter(
//				this, //context
//				R.layout.list_object, // row template
//				c2,
//				imgName,
//				imgViewId
//				);
		// Set Adapter
		ListView list = (ListView) this.findViewById(R.id.listView1);
		list.setAdapter(myAdapter);
		
		
		
		ImageView image = (ImageView) this.findViewById(R.id.icon);
//		Resources res=this.getResources();
//		int id=R.drawable.chronos;
//		
//		Drawable drw=null;
//		try{
//			drw=res.getDrawable(id);
//		}catch(Resources.NotFoundException e){
//			Log.e("HeroActivity","Unable To Get Drawable for id: "+id);
//		}
//		if(drw != null){image.setImageDrawable(drw);}
//		try {
//		    Class res = R.drawable.class;
//		    Field field = res.getField("chronos");
//		    int drawableId = field.getInt(null);
//		    image.setImageResource(drawableId);
//		}
//		catch (Exception e) {
//		    Log.e("MyTag", "Failure to get drawable id.", e);
//		}
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