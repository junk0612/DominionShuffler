package com.enixer.dominionshuffler.cardlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.content.Context;

public class CardListCtrl {
	List<Card> cardList = new ArrayList<Card>();

	public List<Card> getSelectedCards(Context context) {
		List<Card> allCardList = new CardBizLogic().getAllCards(context);
		Set<Integer> set = new HashSet<Integer>();
		Random rand = new Random(System.currentTimeMillis());
		while (cardList.size() < 10) {
			int s = allCardList.size();
			int r = rand.nextInt(s);
			if (set.add(r)) {
				cardList.add(allCardList.get(r));
			}
		}
		Collections.sort(cardList);
		return cardList;
	}

	public void createDatabase(Context context) {
		new CardBizLogic().createDatabase(context);
	}

}
