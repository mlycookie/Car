package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ZjmActivity extends Activity {

	private ImageButton fh;
	private ImageButton tc;
	private TextView userInfo;
	private EditText search;

	public void cjlb(View view) {
		CjlbActivity.actionStart(this, 0);
	}

	public void fjcl(View view) {
		CjlbActivity.actionStart(this, 1);
	}

	public void hgcl(View view) {
		CjlbActivity.actionStart(this, 2);
	}

	public void search(View view) {
		CjlbActivity.actionStart(this, search.getText().toString());
	}

	public void back(View view) {
		//finish();
		Intent intent = new Intent(this,DlActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zjm);

		fh = (ImageButton) findViewById(R.id.fh);
		tc = (ImageButton) findViewById(R.id.tc);
		tc.setVisibility(View.VISIBLE);
		fh.setVisibility(View.GONE);
		search = (EditText) findViewById(R.id.search);

		userInfo = (TextView) findViewById(R.id.user_info);

		userInfo.setText(PreferencesUtils.getUserInfo(this));

	}

}
