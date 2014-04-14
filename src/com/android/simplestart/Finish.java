package com.android.simplestart;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Finish extends Activity {
	
	Button finish;
	private static final String GOOGLE_SETUPWIZARD_PACKAGE = "com.google.android.setupwizard";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		
		finish = (Button) findViewById(R.id.finish);
	    finish.setOnClickListener(new OnClickListener() {
		
		public void onClick(View view) {
			Settings.Global.putInt(getContentResolver(), Settings.Global.DEVICE_PROVISIONED, 1);
	        Intent intent = new Intent("android.intent.action.MAIN");
	        intent.addCategory("android.intent.category.HOME");
	        disableSetupWizards(intent);
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.getFlags());
	        startActivity(intent);
	        finish();
		}
	    });
	}

	private void disableSetupWizards(Intent intent) {
		final PackageManager pm = getPackageManager();
        final List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo info : resolveInfos) {
            if (GOOGLE_SETUPWIZARD_PACKAGE.equals(info.activityInfo.packageName)) {
                final ComponentName componentName = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
                pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            }
        }
        pm.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
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