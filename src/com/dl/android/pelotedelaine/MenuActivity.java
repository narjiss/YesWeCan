package com.dl.android.pelotedelaine;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.menu_picture:
			Intent intent = new Intent( MenuActivity.this, PictureActivity.class);
			startActivity(intent);
			finish();			
		case R.id.menu_play:
		case R.id.menu_quit:
			System.exit(RESULT_OK);

		}
		return true;
	}
	


}
