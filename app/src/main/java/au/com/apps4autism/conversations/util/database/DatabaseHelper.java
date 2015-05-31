package au.com.apps4autism.conversations.util.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DatabaseManager.DB_PATH + DatabaseManager.DB_NAME, null, DatabaseManager.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseManager.CREATE_TABLE_USER);
        db.execSQL(DatabaseManager.CREATE_TABLE_THEME);
        db.execSQL(DatabaseManager.CREATE_TABLE_STATEMENT);
        db.execSQL(DatabaseManager.CREATE_TABLE_INTERACTION);
        db.execSQL(DatabaseManager.CREATE_TABLE_USERPROGRESS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }
}