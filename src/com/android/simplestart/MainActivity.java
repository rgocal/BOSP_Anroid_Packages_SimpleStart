package com.android.simplestart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		next = (Button) findViewById(R.id.start);
		next.setOnClickListener(new OnClickListener() {
		
	public void onClick(View v) {
        Intent a = new Intent(MainActivity.this, Finish.class);
        startActivity(a);
    }
	});
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case R.id.settings:
    	  @SuppressWarnings("unused")
		final Context context = this;
    	  Intent settings_menu = new Intent(Settings.ACTION_SETTINGS);
			startActivity(settings_menu);
      }
      return false;
    }

}
