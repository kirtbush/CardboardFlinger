package com.kutmastak.cardboardflinger;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CardboardFlingerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardboard_flinger);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cardboard_flinger, menu);
		return true;
	}

}
