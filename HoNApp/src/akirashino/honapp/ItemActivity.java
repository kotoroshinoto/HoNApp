package akirashino.honapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemActivity extends Activity {

	DBAdapter myDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("Items");
		setContentView(textView);
//		setContentView(R.layout.activity_main);
		// int resourceId = this.getResources().getIdentifier("testimage",
		// "drawable", "akirashino.honapp");

		// openDB();
	}
	// @Override
	// protected void onDestroy() {
	// super.onDestroy();
	// closeDB();
	// }
	//
	//
	// private void openDB() {
	// myDb = new DBAdapter(this);
	// myDb.open();
	// }
	// private void closeDB() {
	// myDb.close();
	// }
	//
	// public void onClick_DisplayRecords(View v) {
	//
	// Cursor cursor = myDb.getAllRowsItem();
	// displayRecordSetItem(cursor);
	// }
	//
	// // Display an entire recordset to the screen.
	// private void displayRecordSetItem(Cursor cursor) {
	// String message = "";
	// // populate the message from the cursor
	//
	// // Reset cursor to start, checking to see if there's data:
	// if (cursor.moveToFirst()) {
	// do {
	// // Process the data:
	// int id = cursor.getInt(DBAdapter.COL_ROWID);
	// String name = cursor.getString(DBAdapter.COL_NAME);
	// String desc = cursor.getString(DBAdapter.COL_DESC);
	// String price = cursor.getString(DBAdapter.COL_PRICE);
	// String image = cursor.getString(DBAdapter.COL_IMAGE);
	//
	// // Append data to the message:
	// message += "name=" + name
	// + desc
	// +" Price=" + price
	// + image
	// +"\n";
	// } while(cursor.moveToNext());
	// }
	//
	// // Close the cursor to avoid a resource leak.
	// cursor.close();
	//
	// }
}
