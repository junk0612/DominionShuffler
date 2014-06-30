package com.enixer.dominionshuffler.cardlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.content.Context;

public class CardListCtrl {
	private List<Card> cardList = new ArrayList<Card>();
	private List<String> addList = new ArrayList<String>();

	public List<Card> getSelectedCards(Context context) {
		List<Card> allCardList = new CardBizLogic().getAllCards(context);
		Set<Integer> set = new HashSet<Integer>();
		Random rand = new Random(System.currentTimeMillis());
		while (cardList.size() < 10) {
			int s = allCardList.size();
			int r = rand.nextInt(s);
			if (set.add(r)) {
				cardList.add(allCardList.get(r));
				checkAddList(allCardList.get(r));
			}
		}
		Collections.sort(cardList);
		return cardList;
	}

	public void createDatabase(Context context) {
		new CardBizLogic().createDatabase(context);
	}

	public List<String> getAdditionalCards() {
		return addList;
	}

	public void checkAddList(Card card) {
		String name = card.getName();
		if (!addList.contains("コイントークン")
				&& (name.equals("海賊船") || name.equals("商人ギルド")
						|| name.equals("肉屋") || name.equals("パン屋")
						|| name.equals("広場") || name.equals("蝋燭職人")))
			addList.add("コイントークン");
		if (name.equals("原住民の村"))
			addList.add("原住民の村マット");
		if (name.equals("島"))
			addList.add("島マット");
		if (name.equals("抑留"))
			addList.add("抑留トークン");
		if (name.equals("交易路")) {
			addList.add("交易路マット");
			addList.add("交易路トークン");
		}
		if (!addList.contains("勝利点トークン")
				&& (name.equals("司教") || name.equals("ならず者") || card
						.getVpToken() > 0))
			addList.add("勝利点トークン");
		if (name.equals("馬上槍試合"))
			addList.add("褒賞カード");
		if (name.equals("魔女娘"))
			addList.add("災いカード");
		if (!addList.contains("ポーション") && card.isPotion())
			addList.add("ポーション");
		if (name.equals("隠遁者"))
			addList.add("狂人");
		if (!addList.contains("廃墟")
				&& (name.equals("狂信者") || name.equals("死の荷車") || name
						.equals("襲撃者")))
			addList.add("廃墟");
		if (!addList.contains("略奪品")
				&& (name.equals("襲撃者") || name.equals("略奪")))
			addList.add("略奪品");
		if (name.equals("浮浪児"))
			addList.add("傭兵");

		Collections.sort(addList);
	}
}
