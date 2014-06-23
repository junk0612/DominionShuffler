package com.enixer.dominionshuffler.cardlist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

public class CardBizLogic {
	public List<Card> getAllCards(Context context) {
		List<Card> list = new ArrayList<Card>();
		Cursor cursor = new CardDao(context).getCardList();
		cursor.moveToFirst();
		while (cursor.moveToNext()) {
			int num = cursor.getInt(0);
			int cost = cursor.getInt(1);
			String potion = cursor.getString(2);
			String name = cursor.getString(3);
			String version = cursor.getString(4);
			list.add(new Card(num, cost, potion.equals("T"), name, version));
		}
		return list;
	}

	public void createDatabase(Context context) {
		CardDao dao = new CardDao(context);
		if (!dao.checkDataBaseExists()) {
			dao.createDatabase(context);
		}
	}
}
