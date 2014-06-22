package com.enixer.dominionshuffler.cardlist;

import java.io.InputStream;
import java.util.Scanner;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CardDao {
	private AssetManager assetManager;
	private InputStream inputStream;
	private Scanner scanner;
	private DominionShufflerDatabaseHelper helper;
	private SQLiteDatabase db;

	public CardDao(Context context) {
		super();
		assetManager = context.getAssets();
		try {
			inputStream = assetManager.open("DominionCards.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner = new Scanner(inputStream);
		helper = new DominionShufflerDatabaseHelper(context);
		createDatabase(context);
	}

	private void createDatabase(Context context) {
		db = helper.getWritableDatabase();
		while (scanner.hasNext()) {
			String data = scanner.next();
			String[] contents = data.split(",");
			ContentValues contentvalues = new ContentValues();
			contentvalues.put("_id", Integer.parseInt(contents[0]));
			contentvalues.put("cost", Integer.parseInt(contents[1]));
			contentvalues.put("potion", contents[2]);
			contentvalues.put("name", contents[3]);
			contentvalues.put("version", contents[4]);
			db.insert("cardlist", null, contentvalues);
		}
	}

	public Cursor getCardList() {
		db = helper.getReadableDatabase();
		return db.query(DominionShufflerDatabaseHelper.getTableName(), null,
				null, null, null, null, null);
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
			db.execSQL("create table "
					+ getTableName()
					+ "(_id integer primary key, cost integer not null, potion text not null, name text not null, version text not null);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

		public static String getTableName() {
			return TABLE_NAME;
		}

	}

}
