package ca.georgebrown.comp3074.assigment_part2;

import android.content.Context;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.provider.BaseColumns;

public class ItemDbHelper extends SQLiteOpenHelper
        implements BaseColumns {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "items.db";

    public static final String CREATE_TABLE = "CREATE TABLE "+
            ItemContract.ItemEntry.TABLE_NAME + " ( "+
            _ID + " INTEGER PRIMARY KEY, " +
            ItemContract.ItemEntry.NAME + " TEXT )";
    public static final String DROP_TABLE = "drop table if exists "+ ItemContract.ItemEntry.TABLE_NAME;

    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d("Database Operations","Table created..");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
        Log.d("DATABASE", "Database dropped");


    }

    public void putItemInfo( String name, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemContract.ItemEntry.NAME,name);

        database.insert(ItemContract.ItemEntry.TABLE_NAME,null,contentValues);
        Log.d("Database Operations","one row inserted..");
    }

    public Cursor readItems(SQLiteDatabase database){

        String[] projections = {_ID, ItemContract.ItemEntry.NAME};

        Cursor cursor = database.query(ItemContract.ItemEntry.TABLE_NAME,
                projections,
                null,
                null,
                null,
                null,
                null);
        return  cursor;
    }

    public void deleteItem(int id, SQLiteDatabase database){
        String selection = _ID+" = "+id;
        //calling the method called delete on sqliteobject
        database.delete(ItemContract.ItemEntry.TABLE_NAME, selection, null);
    }



}
