package jp.enixer.dominionshuffler.daos;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class CardDao {
    private InputStream inputStream;
    private Scanner scanner;
    private DominionShufflerDatabaseHelper helper;
    private SQLiteDatabase db;
    private final File mDatabasePath;

    public CardDao(Context context) {
        super();
        AssetManager assetManager = context.getAssets();
        try {
            inputStream = assetManager.open("DominionCards.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner = new Scanner(inputStream);
        helper = new DominionShufflerDatabaseHelper(context);
        mDatabasePath = context.getDatabasePath(helper.getDBName());
    }

    public void createDatabase(Context context) {
        db = helper.getWritableDatabase();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] contents = data.split(",");
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("_id", Integer.parseInt(contents[0]));
            contentvalues.put("name", contents[1]);
            contentvalues.put("version", contents[2]);
            contentvalues.put("cost", Integer.parseInt(contents[3]));
            contentvalues.put("potion", contents[4]);
            contentvalues.put("classification", contents[5]);
            contentvalues.put("treasure", Integer.parseInt(contents[6]));
            contentvalues.put("vp", Integer.parseInt(contents[7]));
            contentvalues.put("card", Integer.parseInt(contents[8]));
            contentvalues.put("action", Integer.parseInt(contents[9]));
            contentvalues.put("buy", Integer.parseInt(contents[10]));
            contentvalues.put("coin", Integer.parseInt(contents[11]));
            contentvalues.put("vptoken", Integer.parseInt(contents[12]));
            contentvalues.put("description", contents[13]);
            db.insert("cardlist", null, contentvalues);
        }
        db.close();
    }

    public Cursor getCardList() {
        db = helper.getReadableDatabase();
        return db.query(helper.getTableName(), null, null, null, null, null,
                null);
    }

    public boolean checkDataBaseExists() {
        String dbPath = mDatabasePath.getAbsolutePath();

        SQLiteDatabase checkDb = null;
        try {
            checkDb = SQLiteDatabase.openDatabase(dbPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // データベースはまだ存在していない
        }

        if (checkDb == null) {
            // データベースはまだ存在していない
            return false;
        }

        int oldVersion = checkDb.getVersion();
        int newVersion = helper.getDBVersion();

        if (oldVersion == newVersion) {
            // データベースは存在していて最新
            checkDb.close();
            return true;
        }

        // データベースが存在していて最新ではないので削除
        File f = new File(dbPath);
        f.delete();
        return false;
    }

    static class DominionShufflerDatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "cardlist.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "cardlist";

        public DominionShufflerDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + getTableName()
                    + "(_id integer primary key" + ", name text not null"
                    + ", version text not null" + ", cost integer not null"
                    + ", potion text not null"
                    + ", classification text not null"
                    + ", treasure integer not null" + ", vp integer not null"
                    + ", card integer not null" + ", action integer not null"
                    + ", buy integer not null" + ", coin integer not null"
                    + ", vptoken integer not null"
                    + ", description text not null" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public String getTableName() {
            return TABLE_NAME;
        }

        public String getDBName() {
            return DATABASE_NAME;
        }

        public int getDBVersion() {
            return DATABASE_VERSION;
        }

    }

}
