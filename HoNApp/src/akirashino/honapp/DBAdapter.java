package akirashino.honapp;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//TODO: Change the package to match your project.

//TO USE:
//Change the package (at top) to match your project.
//Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	// ///////////////////////////////////////////////////////////////////
	// Constants & Data
	// ///////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";

	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	public static final String KEY_NAME = "name";
	public static final String KEY_HERO = "hero";
	public static final String KEY_RESID = "resid";
	public static final String PRICE = "price";
	public static final String IMAGE = "image";
	public static final String DESC = "desc";

	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_IMAGE = 1;
	public static final int COL_NAME = 2;
	public static final int COL_DESC = 3;
	public static final int COL_PRICE = 4;
	public static final int COL_HERO = 5;
	public static final int COL_RESID = 6;
	

	public static final String[] HERO_KEYS = new String[] { KEY_ROWID,
			KEY_NAME, IMAGE};
	public static final String[] IMAGE_KEYS = new String[] { KEY_ROWID, IMAGE};
	public static final String[] ABILITY_KEYS = new String[] { KEY_ROWID,
			KEY_NAME, KEY_HERO };
	public static final String[] ITEM_KEYS = new String[] { KEY_ROWID, KEY_NAME };

	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "HonData";
	public static final String DATABASE_TABLE_HERO = "hero";
	public static final String DATABASE_TABLE_ITEM = "item";
	public static final String DATABASE_TABLE_ABILITY = "ability";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 2;

	// private static final String DATABASE_CREATE_SQL =
	// "create table " + DATABASE_TABLE
	// + " (" + KEY_ROWID + " integer primary key autoincrement, "

	/*
	 * CHANGE 2:
	 */
	// TODO: Place your fields here!
	// + KEY_{...} + " {type} not null"
	// - Key is the column name you created above.
	// - {type} is one of: text, integer, real, blob
	// (http://www.sqlite.org/datatype3.html)
	// - "not null" means it is a required field (must be given a value).
	// NOTE: All must be comma separated (end of line!) Last one must have NO
	// comma!!
	// + KEY_NAME + " text not null, "
	// + KEY_STUDENTNUM + " integer not null, "
	// + KEY_FAVCOLOUR + " string not null"

	// Rest of creation:
	// + ");";

	// Context of application who uses us.
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
		this.setHeroImageIDs();
		return this;
	}

	// Close the database connection.
	public void close() {
		myDbHelper.close();
	}

	// Add a new set of values to the database.

	// Insert it into the database.
	// return db.insert(DATABASE_TABLE, null, initialValues);

	// Delete a row from the database, by rowId (primary key)

	// Return all data in the database.
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

	public Cursor getAllRowsAbility() {
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE_ABILITY, ABILITY_KEYS, where,
				null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public Cursor getAllRowsItem() {
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

	public Cursor getRowAbility(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
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
		Cursor c = this.getAllHeroImage();
//		ArrayList<String> heroes=new ArrayList<String>();
		if (c != null){
			while(!c.isAfterLast()){
				try {
				    Class res = R.drawable.class;
				    String imgname=c.getString(c.getColumnIndex("image"));
				    Field field = res.getField(imgname);
				    int drawableId = field.getInt(null);
				    ContentValues vals = new ContentValues();
				    vals.put("resid", drawableId);
				    
				    db.update("hero", vals, "image="+imgname, null);
				}
				catch (Exception e) {
				    Log.e("MyTag", "Failure to get drawable id.", e);
				}
				c.moveToNext();
			}
		} //log an error
		
	}
	// Change an existing row to be equal to new data.

	// Insert it into the database.
	// return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	// }

	// ///////////////////////////////////////////////////////////////////
	// Private Helper Classes:
	// ///////////////////////////////////////////////////////////////////

	/**
	 * Private class which handles database creation and upgrading. Used to
	 * handle low-level database access.
	 */
	// private static class DatabaseHelper extends SQLiteOpenHelper
	// {
	//
	//
	// DatabaseHelper(Context context) {
	// super(context, DATABASE_NAME, null, DATABASE_VERSION);
	// }
	//
	// @Override
	// public void onCreate(SQLiteDatabase _db) {
	// _db.execSQL(DATABASE_CREATE_SQL);
	// }
	//
	// @Override
	// public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion)
	// {
	// Log.w(TAG, "Upgrading application's database from version " + oldVersion
	// + " to " + newVersion + ", which will destroy all old data!");
	//
	// // Destroy old database:
	// _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
	//
	// // Recreate new database:
	// onCreate(_db);
	// }
	// }
}