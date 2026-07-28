package com.example.zerostore.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zerostore.data.model.Category;
import com.example.zerostore.data.model.Favorite;
import com.example.zerostore.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Info
    public static final String DATABASE_NAME = "zerostore_database";
    public static final int DATABASE_VERSION = 3;

    private SQLiteDatabase db;
    private static DatabaseHelper instance;

    // Singleton
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Product.CREATE_TABLE);
        db.execSQL(Category.CREATE_TABLE);
        db.execSQL(Favorite.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Product.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Category.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Favorite.TABLE);
        // Also drop Room's master table just to clean up
        db.execSQL("DROP TABLE IF EXISTS room_master_table");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // =====================================================
    // Products CRUD
    // =====================================================

    public boolean insertProduct(Product product) {
        ContentValues cv = new ContentValues();
        cv.put(Product.COL_ID, product.getId());
        cv.put(Product.COL_CATEGORY_ID, product.getCategoryId());
        cv.put(Product.COL_NAME_AR, product.getNameAr());
        cv.put(Product.COL_PRICE, product.getPrice());
        cv.put(Product.COL_CURRENCY, product.getCurrency());
        cv.put(Product.COL_DURATION, product.getDuration());
        cv.put(Product.COL_DELIVERY_TIME, product.getDeliveryTime());
        cv.put(Product.COL_WARRANTY, product.getWarranty());
        cv.put(Product.COL_REQUIREMENTS, product.getRequirements());
        cv.put(Product.COL_DESCRIPTION_AR, product.getDescriptionAr());
        cv.put(Product.COL_NOTES_AR, product.getNotesAr());
        cv.put(Product.COL_IS_TOP, product.isTop() ? 1 : 0);
        cv.put(Product.COL_IS_OFFER, product.isOffer() ? 1 : 0);

        long count = db.insertWithOnConflict(Product.TABLE, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        return count > 0;
    }

    public void insertProducts(List<Product> products) {
        db.beginTransaction();
        try {
            for (Product product : products) {
                insertProduct(product);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM " + Product.TABLE;
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                Product p = cursorToProduct(c);
                list.add(p);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public ArrayList<Product> getProductsByCategory(int categoryId) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM " + Product.TABLE
                + " WHERE " + Product.COL_CATEGORY_ID + " = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(categoryId)});

        if (c.moveToFirst()) {
            do {
                list.add(cursorToProduct(c));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public ArrayList<Product> getTopProducts() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM " + Product.TABLE
                + " WHERE " + Product.COL_IS_TOP + " = 1 LIMIT 5";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                list.add(cursorToProduct(c));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public ArrayList<Product> getOfferProducts() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM " + Product.TABLE
                + " WHERE " + Product.COL_IS_OFFER + " = 1";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                list.add(cursorToProduct(c));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM " + Product.TABLE
                + " WHERE " + Product.COL_ID + " = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(productId)});

        Product product = null;
        if (c.moveToFirst()) {
            product = cursorToProduct(c);
        }
        c.close();
        return product;
    }

    public void deleteAllProducts() {
        db.delete(Product.TABLE, null, null);
    }

    // Helper: convert Cursor row to Product object
    private Product cursorToProduct(Cursor c) {
        Product p = new Product();
        p.setId(c.getInt(c.getColumnIndexOrThrow(Product.COL_ID)));
        p.setCategoryId(c.getInt(c.getColumnIndexOrThrow(Product.COL_CATEGORY_ID)));
        p.setNameAr(c.getString(c.getColumnIndexOrThrow(Product.COL_NAME_AR)));
        p.setPrice(c.getString(c.getColumnIndexOrThrow(Product.COL_PRICE)));
        p.setCurrency(c.getString(c.getColumnIndexOrThrow(Product.COL_CURRENCY)));
        p.setDuration(c.getString(c.getColumnIndexOrThrow(Product.COL_DURATION)));
        p.setDeliveryTime(c.getString(c.getColumnIndexOrThrow(Product.COL_DELIVERY_TIME)));
        p.setWarranty(c.getString(c.getColumnIndexOrThrow(Product.COL_WARRANTY)));
        p.setRequirements(c.getString(c.getColumnIndexOrThrow(Product.COL_REQUIREMENTS)));
        p.setDescriptionAr(c.getString(c.getColumnIndexOrThrow(Product.COL_DESCRIPTION_AR)));
        p.setNotesAr(c.getString(c.getColumnIndexOrThrow(Product.COL_NOTES_AR)));
        p.setTop(c.getInt(c.getColumnIndexOrThrow(Product.COL_IS_TOP)) == 1);
        p.setOffer(c.getInt(c.getColumnIndexOrThrow(Product.COL_IS_OFFER)) == 1);
        return p;
    }

    // =====================================================
    // Categories CRUD
    // =====================================================

    public boolean insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(Category.COL_ID, category.getId());
        cv.put(Category.COL_NAME_AR, category.getNameAr());
        cv.put(Category.COL_ICON, category.getIcon());

        long count = db.insertWithOnConflict(Category.TABLE, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        return count > 0;
    }

    public void insertCategories(List<Category> categories) {
        db.beginTransaction();
        try {
            for (Category cat : categories) {
                insertCategory(cat);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM " + Category.TABLE;
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                Category cat = new Category();
                cat.setId(c.getInt(c.getColumnIndexOrThrow(Category.COL_ID)));
                cat.setNameAr(c.getString(c.getColumnIndexOrThrow(Category.COL_NAME_AR)));
                cat.setIcon(c.getString(c.getColumnIndexOrThrow(Category.COL_ICON)));
                list.add(cat);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public void deleteAllCategories() {
        db.delete(Category.TABLE, null, null);
    }

    // =====================================================
    // Favorites CRUD
    // =====================================================

    public boolean insertFavorite(int productId) {
        ContentValues cv = new ContentValues();
        cv.put(Favorite.COL_PRODUCT_ID, productId);

        long count = db.insertWithOnConflict(Favorite.TABLE, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        return count > 0;
    }

    public void deleteFavorite(int productId) {
        db.delete(Favorite.TABLE,
                Favorite.COL_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(productId)});
    }

    public boolean isFavorite(int productId) {
        String sql = "SELECT 1 FROM " + Favorite.TABLE
                + " WHERE " + Favorite.COL_PRODUCT_ID + " = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(productId)});
        boolean exists = c.moveToFirst();
        c.close();
        return exists;
    }

    public ArrayList<Product> getFavoriteProducts() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT " + Product.TABLE + ".* FROM " + Product.TABLE
                + " INNER JOIN " + Favorite.TABLE
                + " ON " + Product.TABLE + "." + Product.COL_ID
                + " = " + Favorite.TABLE + "." + Favorite.COL_PRODUCT_ID;
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                list.add(cursorToProduct(c));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    // Close Database (as taught in lecture part 4)
    public void closeDB() {
        SQLiteDatabase db = getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
