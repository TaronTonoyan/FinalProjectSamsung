package com.samsung.finalprojectsamsungtt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.samsung.finalprojectsamsungtt.models.Account;
import com.samsung.finalprojectsamsungtt.models.Order;
import com.samsung.finalprojectsamsungtt.models.Product;

import java.util.ArrayList;

public class DBShop {

    private static final String DATABASE_NAME = "shop.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ACCOUNTS = "tableLogin";
    private static final String TABLE_PRODUCTS = "tableProduct";
    private static final String TABLE_ORDERS = "tableOrder";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_IS_ADMIN = "Is_Admin";
    private static final String COLUMN_ADDRESS = "Address";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PRICE = "Price";
    private static final String COLUMN_CATEGORY = "Category";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_IMAGE = "Image";
    private static final String COLUMN_OWNER = "Owner";
    private static final String COLUMN_PRODUCT = "Product";
    private static final String COLUMN_IS_WISHLIST = "Is_Wishlist";
    private static final String COLUMN_QUANTITY = "Quantity";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_EMAIL = 1;
    private static final int NUM_COLUMN_PASSWORD = 2;
    private static final int NUM_COLUMN_IS_ADMIN = 3;
    private static final int NUM_COLUMN_ADDRESS = 4;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_PRICE = 2;
    private static final int NUM_COLUMN_CATEGORY = 3;
    private static final int NUM_COLUMN_DESCRIPTION = 4;
    private static final int NUM_COLUMN_IMAGE = 5;
    private static final int NUM_COLUMN_OWNER = 1;
    private static final int NUM_COLUMN_PRODUCT = 2;
    private static final int NUM_COLUMN_IS_WISHLIST = 3;
    private static final int NUM_COLUMN_QUANTITY = 4;

    private final SQLiteDatabase db;

    public DBShop(Context context) {
        OpenHelper openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
    }

    public long insertAcc(String email, String password, int isAdmin, String address) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_IS_ADMIN, isAdmin);
        cv.put(COLUMN_ADDRESS, address);
        return db.insert(TABLE_ACCOUNTS, null, cv);
    }

    public long insertProduct(String name, double price, String category, String description, String image) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_IMAGE, image);
        return db.insert(TABLE_PRODUCTS, null, cv);
    }

    public long insertOrder(long owner, long product, int isWishlist, int quantity) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_OWNER, owner);
        cv.put(COLUMN_PRODUCT, product);
        cv.put(COLUMN_IS_WISHLIST, isWishlist);
        cv.put(COLUMN_QUANTITY, quantity);
        return db.insert(TABLE_ORDERS, null, cv);
    }

    public int updateAcc(Account ld) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, ld.getEmail());
        cv.put(COLUMN_PASSWORD, ld.getPassword());
        cv.put(COLUMN_IS_ADMIN, ld.getIsAdmin());
        cv.put(COLUMN_ADDRESS, ld.getAddress());

        return db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + " = ?", new String[] {String.valueOf(ld.getId())});
    }

    public int updateProduct(Product ld) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, ld.getName());
        cv.put(COLUMN_PRICE, ld.getPrice());
        cv.put(COLUMN_CATEGORY, ld.getCategory());
        cv.put(COLUMN_DESCRIPTION, ld.getDescription());
        cv.put(COLUMN_IMAGE, ld.getImage());

        return db.update(TABLE_PRODUCTS, cv, COLUMN_ID + " = ?", new String[] {String.valueOf(ld.getId())});
    }

    public int updateOrder(Order ld) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_OWNER, ld.getOwner());
        cv.put(COLUMN_PRODUCT, ld.getProduct());
        cv.put(COLUMN_IS_WISHLIST, ld.getIsWishlist());
        cv.put(COLUMN_QUANTITY, ld.getQuantity());

        return db.update(TABLE_ORDERS, cv, COLUMN_ID + " = ?", new String[] {String.valueOf(ld.getId())});
    }

    public void deleteAcc(long id) {
        db.delete(TABLE_ACCOUNTS, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public void deleteProduct(long id) {
        db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public void deleteOrder(long id) {
        db.delete(TABLE_ORDERS, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public void deleteAllAccounts() {
        db.delete(TABLE_ACCOUNTS, null, null);
    }

    public void deleteAllProducts() {
        db.delete(TABLE_PRODUCTS, null, null);
    }

    public void deleteAllOrders() {
        db.delete(TABLE_ORDERS, null, null);
    }

    public Account selectAcc(long id) {
        Cursor cursor = db.query(TABLE_ACCOUNTS, null, COLUMN_ID + " = ?", new String[] {String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        String email = cursor.getString(NUM_COLUMN_EMAIL);
        String password = cursor.getString(NUM_COLUMN_PASSWORD);
        int isAdmin = cursor.getInt(NUM_COLUMN_IS_ADMIN);
        String address = cursor.getString(NUM_COLUMN_ADDRESS);
        return new Account(id, email, password, isAdmin, address);
    }

    public Product selectProduct(long id) {
        Cursor cursor = db.query(TABLE_PRODUCTS, null, COLUMN_ID + " = ?", new String[] {String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        String name = cursor.getString(NUM_COLUMN_NAME);
        float price = cursor.getFloat(NUM_COLUMN_PRICE);
        String category = cursor.getString(NUM_COLUMN_CATEGORY);
        String description = cursor.getString(NUM_COLUMN_DESCRIPTION);
        String image = cursor.getString(NUM_COLUMN_IMAGE);
        return new Product(id, name, price, category, description, image);
    }

    public Order selectOrder(long id) {
        Cursor cursor = db.query(TABLE_ORDERS, null, COLUMN_ID + " = ?", new String[] {String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        long owner = cursor.getLong(NUM_COLUMN_OWNER);
        long product = cursor.getLong(NUM_COLUMN_PRODUCT);
        int isWishlist = cursor.getInt(NUM_COLUMN_IS_WISHLIST);
        int quantity = cursor.getInt(NUM_COLUMN_QUANTITY);
        return new Order(id, owner, product, isWishlist, quantity);
    }

    public ArrayList<Account> selectAllAccounts() {
        Cursor cursor = db.query(TABLE_ACCOUNTS, null, null, null, null, null, null);

        ArrayList<Account> arr = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(NUM_COLUMN_ID);
                String email = cursor.getString(NUM_COLUMN_EMAIL);
                String password = cursor.getString(NUM_COLUMN_PASSWORD);
                int isAdmin = cursor.getInt(NUM_COLUMN_IS_ADMIN);
                String address = cursor.getString(NUM_COLUMN_ADDRESS);
                arr.add(new Account(id, email, password, isAdmin, address));
            } while (cursor.moveToNext());
        }
        return arr;
    }

    public ArrayList<Product> selectAllProducts() {
        Cursor cursor = db.query(TABLE_PRODUCTS, null, null, null, null, null, null);

        ArrayList<Product> arr = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(NUM_COLUMN_ID);
                String name = cursor.getString(NUM_COLUMN_NAME);
                float price = cursor.getFloat(NUM_COLUMN_PRICE);
                String category = cursor.getString(NUM_COLUMN_CATEGORY);
                String description = cursor.getString(NUM_COLUMN_DESCRIPTION);
                String image = cursor.getString(NUM_COLUMN_IMAGE);
                arr.add(new Product(id, name, price, category, description, image));
            } while (cursor.moveToNext());
        }
        return arr;
    }

    public ArrayList<Order> selectAllOrders() {
        Cursor cursor = db.query(TABLE_ORDERS, null, null, null, null, null, null);

        ArrayList<Order> arr = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(NUM_COLUMN_ID);
                long owner = cursor.getLong(NUM_COLUMN_OWNER);
                long product = cursor.getLong(NUM_COLUMN_PRODUCT);
                int isWishlist = cursor.getInt(NUM_COLUMN_IS_WISHLIST);
                int quantity = cursor.getInt(NUM_COLUMN_QUANTITY);
                arr.add(new Order(id, owner, product, isWishlist, quantity));
            } while (cursor.moveToNext());
        }
        return arr;
    }

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String queryAcc = "CREATE TABLE " + TABLE_ACCOUNTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_IS_ADMIN + " INTEGER, " +
                    COLUMN_ADDRESS + " TEXT);";
            String queryProduct = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PRICE + " REAL, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_IMAGE + " TEXT);";
            String queryOrder = "CREATE TABLE " + TABLE_ORDERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_OWNER + " INTEGER, " +
                    COLUMN_PRODUCT + " INTEGER, " +
                    COLUMN_IS_WISHLIST + " INTEGER, " +
                    COLUMN_QUANTITY + " INTEGER);";
            db.execSQL(queryAcc);
            db.execSQL(queryProduct);
            db.execSQL(queryOrder);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
            onCreate(db);
        }
    }

}
