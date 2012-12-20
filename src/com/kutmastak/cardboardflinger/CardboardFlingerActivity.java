package com.kutmastak.cardboardflinger;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.kutmastak.cardboardflinger.CardboardSurfaceView;

public class CardboardFlingerActivity extends Activity {

	private static final String MAIN_CARDBOARD_PREFS = "CardboardPreferences";
	private static final String CARDBOARD_COUNT = "CardboardCount";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardboard_flinger);
		
		//retrieve and reuse the number of cardboard people
		SharedPreferences settings = getApplicationContext().getSharedPreferences(MAIN_CARDBOARD_PREFS, 0);
		int count = settings.getInt(CARDBOARD_COUNT, 0);
		Log.d("MainActivity", "Initial CardboardCount "+Integer.toString(count));
		
		CardboardSurfaceView myDrawingView = new CardboardSurfaceView(getBaseContext());
		RelativeLayout myLayout = (RelativeLayout)findViewById(R.id.main_layout);
		myLayout.addView(myDrawingView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cardboard_flinger, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) {
			case R.id.menu_settings:
				Log.d("MenuItems", "You Selected add a cardboard person! Adding a new person to the view!");
				SharedPreferences settings = getApplicationContext().getSharedPreferences(MAIN_CARDBOARD_PREFS, 0);
				int count = settings.getInt(CARDBOARD_COUNT, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putInt(CARDBOARD_COUNT, ++count);
				editor.commit();
				Log.d("MenuItems", "Added the "+Integer.toString(count)+" Person!");
				return true;
			default:
				Log.d("MenuItems", "You Selected something questionable!");
				return super.onOptionsItemSelected(item);
				
		}
	}
}
