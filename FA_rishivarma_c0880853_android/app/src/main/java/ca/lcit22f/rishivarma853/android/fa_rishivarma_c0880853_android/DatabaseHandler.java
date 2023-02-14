package ca.lcit22f.rishivarma853.android.fa_rishivarma_c0880853_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products_manager";
    private static final String TABLE_PRODUCTS = "products";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_DESCRIPTION = "description";
    private static final String PRODUCT_PRICE = "price";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME + " TEXT,"
                + PRODUCT_DESCRIPTION + " TEXT," + PRODUCT_PRICE + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new product
    void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName()); // Product Name
        values.put(PRODUCT_DESCRIPTION, product.getDescription()); // Product Phone
        values.put(PRODUCT_PRICE, product.getPrice());

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single product
    Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{PRODUCT_ID,
                        PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE}, PRODUCT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Product product = new Product(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        // return product
        return product;
    }

    // code to get all products in a list view
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setID(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setPrice(Integer.parseInt(cursor.getString(3)));
                // Adding product to list
                productList.add(product);
            } while (cursor.moveToNext());
        }
        // return product list
        return productList;
    }

    // code to update the single product
    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_DESCRIPTION, product.getDescription());
        values.put(PRODUCT_PRICE, product.getPrice());

        // updating row
        return db.update(TABLE_PRODUCTS, values, PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getID())});
    }

    // Deleting single product
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getID())});
        db.close();
    }

    // Getting products Count
    public int getProductsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

}