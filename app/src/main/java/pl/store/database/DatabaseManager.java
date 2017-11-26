package pl.store.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pl.store.model.Product;

/**
 * Created by ktrojanowski on 26.11.2017.
 */

public class DatabaseManager {

    //columns
    public static final String ID = "id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_DESC = "product_desc";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_CATEGORY = "product_category";

    private final String DATABASE_NAME = "GeekMarketDB";
    private final String DATABASE_TABLE = "GeekMarketTable";
    private final int DATABASE_VERSION = 1;

    private DBHelper dbHelper;
    private final Context dbContext;
    private SQLiteDatabase db;

    public DatabaseManager (Context context) {
        this.dbContext = context;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            String sqlCode = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s REAL NOT NULL, %s TEXT NOT NULL)",
                    DATABASE_TABLE, ID, PRODUCT_NAME, PRODUCT_DESC, PRODUCT_PRICE, PRODUCT_CATEGORY);

            db.execSQL(sqlCode);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);

        }
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DBHelper(dbContext);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long createEntry(Product product) {
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME, product.getProductName());
        cv.put(PRODUCT_DESC, product.getProductDesc());
        cv.put(PRODUCT_PRICE, product.getProductPrice());
        cv.put(PRODUCT_CATEGORY, product.getProductCategory());

        return db.insert(DATABASE_TABLE, null, cv);

    }

    public String getData() {
        String [] columns = new String [] {ID, PRODUCT_NAME, PRODUCT_DESC, PRODUCT_PRICE, PRODUCT_CATEGORY};

        Cursor c = db.query(DATABASE_TABLE, columns, null, null, null, null,null);

        String result = "";

        int iRowID = c.getColumnIndex(ID);
        int iName = c.getColumnIndex(PRODUCT_NAME);
        int iDesc = c.getColumnIndex(PRODUCT_DESC);
        int iPrice = c.getColumnIndex(PRODUCT_PRICE);
        int iCategory = c.getColumnIndex(PRODUCT_CATEGORY);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            result = result + String.format("\n ID: %s NAME: %s DESC: %s PRICE: %s CATEGORY: %s", c.getString(iRowID), c.getString(iName), c.getString(iDesc), c.getString(iPrice), c.getString(iCategory));
        }

        c.close();

        return result;


    }
}
