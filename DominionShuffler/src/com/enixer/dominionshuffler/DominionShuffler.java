package com.enixer.dominionshuffler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class DominionShuffler extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dominion_shuffler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.dominion_shuffler, menu);
		return true;
	}

}
