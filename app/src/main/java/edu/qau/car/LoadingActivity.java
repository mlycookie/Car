package edu.qau.car;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LoadingActivity extends Activity {

	public static Activity my = null;

	public static void startAction(Context context) {
		Intent intent = new Intent(context, LoadingActivity.class);
		context.startActivity(intent);
	}

	public static void endAction() {

		my.finish();
		my = null;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

		my = this;
	}

	@Override
	public void onBackPressed() {

	}
}
