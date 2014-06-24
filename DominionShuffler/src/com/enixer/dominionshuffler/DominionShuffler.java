package com.enixer.dominionshuffler;

import java.util.ArrayList;
import java.util.List;

import com.enixer.dominionshuffler.cardlist.Card;
import com.enixer.dominionshuffler.cardlist.CardListCtrl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class DominionShuffler extends Activity {
	private List<Card> list;
	private List<String> addList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dominion_shuffler);
		createDatabase(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.dominion_shuffler, menu);
		return true;
	}

	public void createDatabase(Context context) {
		new CardListCtrl().createDatabase(context);
	}

	public void onShuffleButtonClick(View v) {
		list = new CardListCtrl().getSelectedCards(this);
		CardListAdapter adapter = new CardListAdapter(this, 0, list);
		addList = new ArrayList<String>();
		ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, addList);
		addList.add("ポーション");
		ListView lv = (ListView) findViewById(R.id.cardList);
		lv.setAdapter(adapter);
		GridView gv = (GridView) findViewById(R.id.addition);
		gv.setAdapter(aAdapter);

	}

	private class CardListAdapter extends ArrayAdapter<Card> {
		private LayoutInflater inflater;

		public CardListAdapter(Context context, int resource, List<Card> objects) {
			super(context, resource, objects);
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Card card = (Card) getItem(position);
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.card_list_item, null);
			}

			TextView numberTextView = (TextView) convertView
					.findViewById(R.id.numberText);
			numberTextView.setText(String.format("%03d", card.getId()));
			TextView costTextView = (TextView) convertView
					.findViewById(R.id.costText);
			costTextView.setText(Integer.toString(card.getCost()));
			if (card.isPotion()) {
				costTextView.setTextColor(Color.RED);
			} else {
				costTextView.setTextColor(Color.BLACK);
			}
			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.nameText);
			nameTextView.setText(card.getName());
			TextView versionTextView = (TextView) convertView
					.findViewById(R.id.versionText);
			versionTextView.setText(card.getVersion());

			return convertView;
		}
	}

}
