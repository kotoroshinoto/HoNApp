package akirashino.honapp;

import java.lang.reflect.Field;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAdapter{

	// ///////////////////////////////////////////////////////////////////
	// Constants & Data
	// ///////////////////////////////////////////////////////////////////
	private static final String TAG = "DBAdapter";

	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	public static final String KEY_NAME = "name";
	public static final String KEY_HERO = "hero";
	public static final String KEY_RESID = "resid";
	public static final String PRICE = "price";
	public static final String IMAGE = "image";
	public static final String DESC = "desc";
	public static final String COST = "cost";
	public static final String PLACE = "place";
	
	
	public static final int COL_IMAGE = 1;
	public static final int COL_NAME = 2;
	public static final int COL_DESC = 3;
	public static final int COL_PRICE = 4;
	public static final int COL_HERO = 5;
	public static final int COL_RESID = 6;
	public static final int COL_COST = 7;
	public static final int COL_PLACE = 9;
	
	

	public static final String[] HERO_KEYS = new String[] { KEY_ROWID,
			KEY_NAME, IMAGE, KEY_RESID};
	public static final String[] IMAGE_KEYS = new String[] { KEY_ROWID, IMAGE};
	public static final String[] ABILITY_KEYS = new String[] { KEY_ROWID,
			KEY_NAME, KEY_HERO, DESC, IMAGE, KEY_RESID, COST, PLACE};
	public static final String[] ITEM_KEYS = new String[] { KEY_ROWID, KEY_NAME, PRICE, DESC, IMAGE, KEY_RESID, };

	public static final String DATABASE_NAME = "HonData";
	public static final String DATABASE_TABLE_HERO = "hero";
	public static final String DATABASE_TABLE_ITEM = "item";
	public static final String DATABASE_TABLE_ABILITY = "ability";
	public static final int DATABASE_VERSION = 2;


	private final Context context;

	private DataBaseHelper myDbHelper;

	private SQLiteDatabase db;

	// ///////////////////////////////////////////////////////////////////
	// Public methods:
	// ///////////////////////////////////////////////////////////////////

	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDbHelper = new DataBaseHelper(context);
	}

	// Open the database connection.
	public DBAdapter open() {
		db = myDbHelper.getWritableDatabase();
		//set hero images
		this.setHeroImageIDs();
		//set ability images
		this.setAbilityImageIDs();
		//set item images
		this.setItemImageIDs();
		return this;
	}

	// Close the database connection.
	public void close() {
		myDbHelper.close();
	}

	public Cursor getAllHero() {
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE_HERO, HERO_KEYS, where, null,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public Cursor getAllHeroImage() {
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE_HERO, IMAGE_KEYS, where, null,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public Cursor getAllAbility() {
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE_ABILITY, ABILITY_KEYS, where,
				null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public Cursor getAllItem() {
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE_ITEM, ITEM_KEYS, where, null,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRowHero(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = db.query(true, DATABASE_TABLE_HERO, HERO_KEYS, where, null,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public Cursor getRowAbility(String hero) {
		String where = KEY_HERO + "= \"" +hero+"\"";
		Cursor c = db.query(true, DATABASE_TABLE_ABILITY, ABILITY_KEYS, where,
				null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public Cursor getRowItem(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = db.query(true, DATABASE_TABLE_ITEM, ITEM_KEYS, where, null,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public void setHeroImageIDs(){
		Cursor c = this.getAllHero();
		if (c != null){
			while(!c.isAfterLast()){
				try {
				    Class res = R.drawable.class;
				    String imgname=c.getString(c.getColumnIndex("image"));
				    Field field = res.getField(imgname);
				    int drawableId = field.getInt(null);
				    db.execSQL("update hero set " +
				    		"resid=\""+drawableId+"\" where " +
				    				"image= \""+imgname+"\"");
				}
				catch (Exception e) {
				    Log.e("MyTag", "Failure to get drawable id.", e);
				}
				c.moveToNext();
			}
		} 
		
	}
	public void setAbilityImageIDs(){
		Cursor c = this.getAllAbility();
		if (c != null){
			while(!c.isAfterLast()){
				try {
				    Class res = R.drawable.class;
				    String imgname=c.getString(c.getColumnIndex("image"));
				    Field field = res.getField(imgname);
				    int drawableId = field.getInt(null);
				    db.execSQL("update ability" +
				    		" set resid=\""+drawableId+"\" where " +
				    				"image= \""+imgname+"\"");
				}
				catch (Exception e) {
				    Log.e("MyTag", "Failure to get drawable id.", e);
				}
				c.moveToNext();
			}
		} 
		
	}
	public void setItemImageIDs(){
		Cursor c = this.getAllItem();
		if (c != null){
			while(!c.isAfterLast()){
				try {
				    Class res = R.drawable.class;
				    String imgname=c.getString(c.getColumnIndex("image"));
				    Field field = res.getField(imgname);
				    int drawableId = field.getInt(null);
				    db.execSQL("update item set " +
				    		"resid=\""+drawableId+"\" where " +
				    				"image= \""+imgname+"\"");
				}
				catch (Exception e) {
				    Log.e("MyTag", "Failure to get drawable id.", e);
				}
				c.moveToNext();
			}
		} 
		
	}

}