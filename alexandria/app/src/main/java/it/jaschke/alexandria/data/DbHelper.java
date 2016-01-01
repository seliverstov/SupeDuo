package it.jaschke.alexandria.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by saj on 22/12/14.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "alexandria.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_BOOK_TABLE = "CREATE TABLE " + AlexandriaContract.BookEntry.TABLE_NAME + " ("+
                AlexandriaContract.BookEntry._ID + " INTEGER PRIMARY KEY," +
                AlexandriaContract.BookEntry.TITLE + " TEXT NOT NULL," +
                AlexandriaContract.BookEntry.SUBTITLE + " TEXT ," +
                AlexandriaContract.BookEntry.DESC + " TEXT ," +
                AlexandriaContract.BookEntry.IMAGE_URL + " TEXT, "+
                AlexandriaContract.BookEntry.CREATED_AT + " INTEGER," +
                AlexandriaContract.BookEntry.IS_NEW + " INTEGER," +
                "UNIQUE ("+ AlexandriaContract.BookEntry._ID +") ON CONFLICT IGNORE)";

        final String SQL_CREATE_AUTHOR_TABLE = "CREATE TABLE " + AlexandriaContract.AuthorEntry.TABLE_NAME + " ("+
                AlexandriaContract.AuthorEntry._ID + " INTEGER," +
                AlexandriaContract.AuthorEntry.AUTHOR + " TEXT," +
                " FOREIGN KEY (" + AlexandriaContract.AuthorEntry._ID + ") REFERENCES " +
                AlexandriaContract.BookEntry.TABLE_NAME + " (" + AlexandriaContract.BookEntry._ID + "))";

        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + AlexandriaContract.CategoryEntry.TABLE_NAME + " ("+
                AlexandriaContract.CategoryEntry._ID + " INTEGER," +
                AlexandriaContract.CategoryEntry.CATEGORY + " TEXT," +
                " FOREIGN KEY (" + AlexandriaContract.CategoryEntry._ID + ") REFERENCES " +
                AlexandriaContract.BookEntry.TABLE_NAME + " (" + AlexandriaContract.BookEntry._ID + "))";


        Log.d("sql-statments",SQL_CREATE_BOOK_TABLE);
        Log.d("sql-statments",SQL_CREATE_AUTHOR_TABLE);
        Log.d("sql-statments",SQL_CREATE_CATEGORY_TABLE);

        db.execSQL(SQL_CREATE_BOOK_TABLE);
        db.execSQL(SQL_CREATE_AUTHOR_TABLE);
        db.execSQL(SQL_CREATE_CATEGORY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion==1 && newVersion==2){
            db.execSQL("ALTER TABLE "+AlexandriaContract.BookEntry.TABLE_NAME+" ADD COLUMN "+AlexandriaContract.BookEntry.CREATED_AT+" INTEGER");
            db.execSQL("ALTER TABLE "+AlexandriaContract.BookEntry.TABLE_NAME+" ADD COLUMN "+AlexandriaContract.BookEntry.IS_NEW +" INTEGER");
        }
    }
}
