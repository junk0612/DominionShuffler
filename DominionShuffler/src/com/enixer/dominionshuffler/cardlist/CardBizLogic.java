package com.enixer.dominionshuffler.cardlist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

public class CardBizLogic {
	public List<Card> getAllCards(Context context) {
		List<Card> list = new ArrayList<Card>();
		CardDao dao = new CardDao(context);
		Cursor cursor = dao.getCardList();
		cursor.moveToFirst();
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			String version = cursor.getString(2);
			int cost = cursor.getInt(3);
			String potion = cursor.getString(4);
			String classification = cursor.getString(5);
			int treasure = cursor.getInt(6);
			int vp = cursor.getInt(7);
			int card = cursor.getInt(8);
			int action = cursor.getInt(9);
			int buy = cursor.getInt(10);
			int coin = cursor.getInt(11);
			int vpToken = cursor.getInt(12);
			String description = cursor.getString(13);
			list.add(new Card(id, name, version, cost, potion.equals("T"),
					classification, treasure, vp, card, action, buy, coin,
					vpToken, description));
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
