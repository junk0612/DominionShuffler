package com.enixer.dominionshuffler;

import java.util.List;

import com.enixer.dominionshuffler.cardlist.Card;
import com.enixer.dominionshuffler.cardlist.CardListCtrl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class DominionShuffler extends Activity {
	private List<Card> list;
	private List<String> addList;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dominion_shuffler);
		createDatabase(this);
		lv = (ListView) findViewById(R.id.cardList);
	}

	@Override
	protected void onResume() {
		super.onResume();
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				Card card = (Card) listView.getItemAtPosition(position);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						DominionShuffler.this);
				StringBuilder text = new StringBuilder();
				String nl = System.getProperty("line.separator");
				text.append(card.getClassification()).append(nl);
				if (card.getTreasure() >= 0)
					text.append(nl).append(card.getTreasure()).append(" コイン");
				if (card.getVp() >= 0)
					text.append(nl).append(card.getVp()).append(" 勝利点");
				if (card.getCard() >= 0)
					text.append(nl).append("+").append(card.getCard())
							.append(" カードを引く");
				if (card.getAction() >= 0)
					text.append(nl).append("+").append(card.getAction())
							.append(" アクション");
				if (card.getBuy() >= 0)
					text.append(nl).append("+").append(card.getBuy())
							.append(" カードを購入");
				if (card.getCoin() >= 0)
					text.append(nl).append("+").append(card.getCoin())
							.append(" コイン");
				if (card.getVpToken() >= 0)
					text.append(nl).append("+").append(card.getVpToken())
							.append(" 勝利点トークン");
				if (!(card.getTreasure() < 0 && card.getVp() < 0
						&& card.getCard() < 0 && card.getAction() < 0
						&& card.getBuy() < 0 && card.getCoin() < 0 && card
						.getVpToken() < 0)
						&& !card.getDescription().equals("none"))
					text.append(nl);
				if (!card.getDescription().equals("none")) {
					String[] descriptions = card.getDescription().split("<br>");
					for (int i = 0; i < descriptions.length; i++)
						text.append(nl).append(descriptions[i]);
				}
				builder.setTitle(card.getName())
						.setMessage(text)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();
			}
		});

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
		CardListCtrl ctrl = new CardListCtrl();
		list = ctrl.getSelectedCards(this);
		CardListAdapter adapter = new CardListAdapter(this, 0, list);
		addList = ctrl.getAdditionalCards();
		AddCardListAdapter aAdapter = new AddCardListAdapter(this, 0, addList);
		lv.setAdapter(adapter);
		if (addList.size() == 0)
			findViewById(R.id.additionTitle).setVisibility(View.GONE);
		else
			findViewById(R.id.additionTitle).setVisibility(View.VISIBLE);
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
			TextView potionView = (TextView) convertView
					.findViewById(R.id.potion);
			if (card.isPotion()) {
				potionView.setTextColor(Color.CYAN);
			} else {
				potionView.setTextColor(Color.YELLOW);
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

	private class AddCardListAdapter extends ArrayAdapter<String> {
		private LayoutInflater inflater;

		public AddCardListAdapter(Context context, int resource,
				List<String> objects) {
			super(context, resource, objects);
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String str = (String) getItem(position);
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.add_card_list_item,
						null);
			}

			TextView numberTextView = (TextView) convertView
					.findViewById(R.id.text_view_1);
			numberTextView.setText(str);

			return convertView;
		}
	}

}
