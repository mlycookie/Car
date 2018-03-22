package edu.qau.car;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LsjyZjmActivity extends Activity {

	private ImageButton fh;
//	private ImageButton tc;
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
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zjm);

		fh = (ImageButton) findViewById(R.id.fh);
		search = (EditText) findViewById(R.id.search);
		userInfo = (TextView) findViewById(R.id.user_info);

		String flag= getIntent().getStringExtra("flag");
		String flagStr ="";
		switch(flag){
			case "R1" :
				flagStr = "行车制动";
				break;
			case "R2":
				flagStr = "坡道驻车";
				break;
		}
		
		SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor =   sharedPreferences.edit();
		editor .putString("lsjy",flag);
		editor.putString("lsjyStr",flagStr);
		editor.commit();
		 
		userInfo.setText( PreferencesUtils.getUserLsyInfo(this) );
		
	}

}
