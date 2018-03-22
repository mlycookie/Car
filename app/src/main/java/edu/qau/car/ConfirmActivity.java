package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ConfirmActivity extends Activity {

	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm);

		text = (TextView) findViewById(R.id.text);
		Intent intent = getIntent();
		String data = intent.getStringExtra("message");
		text.setText(data);
	}

	public void close(View view) {
		setResult(Activity.RESULT_CANCELED);
		finish();
	}

	public void confirm(View view) {
		setResult(Activity.RESULT_OK);
		finish();
	}

	@Override
	public void onBackPressed() {

	}

}
