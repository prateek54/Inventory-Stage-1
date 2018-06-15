package dev.prateek.com.inventorys1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dev.prateek.com.inventorys1.data.ItemContract;
import dev.prateek.com.inventorys1.data.ItemDbHelper;
import dev.prateek.com.inventorys1.data.ItemContract.ItemEntry;

public class MainActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private ItemDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ItemDbHelper(this);

        Button addDummyDataButton = findViewById(R.id.add_button);
        addDummyDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long nrRow = insertItem();
                if (nrRow > 0)
                    queryDatabase();
                else
                    Toast.makeText(MainActivity.this, "Error inserting item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        queryDatabase();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void queryDatabase() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ItemContract.ItemEntry._ID,
                ItemEntry.COLUMN_ITEM_NAME,
                ItemEntry.COLUMN_ITEM_PRICE,
                ItemEntry.COLUMN_ITEM_QUANTITY,
                ItemEntry.COLUMN_ITEM_SUPPLIER,
                ItemEntry.COLUMN_ITEM_SUPPLIER_PHONE,
                ItemEntry.COLUMN_ITEM_SUPPLIER_EMAIL,
                ItemEntry.COLUMN_ITEM_BARCODE};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                ItemEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = findViewById(R.id.text);

        try {
            displayView.setText("The table contains " + cursor.getCount() + " books.\n\n");
            displayView.append(ItemEntry._ID + " - " +
                    ItemEntry.COLUMN_ITEM_NAME + " - " +
                    ItemEntry.COLUMN_ITEM_PRICE + " - " +
                    ItemEntry.COLUMN_ITEM_QUANTITY + " - " +
                    ItemEntry.COLUMN_ITEM_SUPPLIER + " - " +
                    ItemEntry.COLUMN_ITEM_SUPPLIER_PHONE + " - " +
                    ItemEntry.COLUMN_ITEM_SUPPLIER_EMAIL + " - " +
                    ItemEntry.COLUMN_ITEM_BARCODE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ItemEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_SUPPLIER);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_SUPPLIER_PHONE);
            int supplierEmailColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_SUPPLIER_EMAIL);
            int barcodeColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_BARCODE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                Float currentPrice = cursor.getFloat(priceColumnIndex);
                Float currentQuantity = cursor.getFloat(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);
                String currentSupplierEmail = cursor.getString(supplierEmailColumnIndex);
                String currentBarcode = cursor.getString(barcodeColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplier + " - " +
                        currentSupplierPhone + " - " +
                        currentSupplierEmail + " - " +
                        currentBarcode));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded item data into the database. For debugging purposes only.
     */
    private long insertItem() {
        // Gets the database in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        ContentValues values = new ContentValues();
        values.put(ItemEntry.COLUMN_ITEM_NAME, "Dummy Book");
        values.put(ItemEntry.COLUMN_ITEM_PRICE, 4.5);
        values.put(ItemEntry.COLUMN_ITEM_QUANTITY, 2);
        values.put(ItemEntry.COLUMN_ITEM_SUPPLIER, "Carrefour");
        values.put(ItemEntry.COLUMN_ITEM_SUPPLIER_PHONE, "0745555444");
        values.put(ItemEntry.COLUMN_ITEM_SUPPLIER_EMAIL, "carrefour@gmail.com");
        values.put(ItemEntry.COLUMN_ITEM_BARCODE, "798321654132");


        return db.insert(ItemEntry.TABLE_NAME, null, values);
    }
}
