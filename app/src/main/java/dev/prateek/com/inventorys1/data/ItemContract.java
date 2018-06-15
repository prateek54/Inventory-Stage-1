package dev.prateek.com.inventorys1.data;

import android.provider.BaseColumns;


public final class ItemContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ItemContract() {}
    /**
     * Inner class that defines constant values for the items database table.
     * Each entry in the table represents a single item.
     */
    public static final class ItemEntry implements BaseColumns {

        /** Name of database table for items */
        public final static String TABLE_NAME = "items";

        /**
         * Unique ID number for the item (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the item.
         *
         * Type: TEXT
         */
        public final static String COLUMN_ITEM_NAME ="name";

        /**
         * price about the item.
         *
         * Type: FLOAT
         */
        public final static String COLUMN_ITEM_PRICE = "price";


        /**
         * Quantity of the item.
         *
         * Type: FLOAT
         */
        public final static String COLUMN_ITEM_QUANTITY = "quantity";

        /**
         * Supplier of the item.
         *
         * Type: TEXT
         */
        public final static String COLUMN_ITEM_SUPPLIER = "supplier";

        /**
         * Phone number of the supplier of the item.
         *
         * Type: TEXT
         */
        public final static String COLUMN_ITEM_SUPPLIER_PHONE = "phone";

        /**
         * Supplier email of the item.
         *
         * Type: TEXT
         */
        public final static String COLUMN_ITEM_SUPPLIER_EMAIL = "email";

        /**
         * Barcode of the item.
         *
         * Type: TEXT
         */
        public final static String COLUMN_ITEM_BARCODE = "barcode";

    }
}
