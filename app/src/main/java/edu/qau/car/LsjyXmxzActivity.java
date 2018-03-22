package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 路试检验项目选择
 */
public class LsjyXmxzActivity extends Activity {

	private ImageButton fh;
	private ImageButton tc;
	private TextView userInfo;

	/**
	 * 行车制动	R1
	 * 坡度驻车	R2
	 * @param view
     */
	
	public void xczd(View view) {
		Intent i = new Intent(this,LsjyZjmActivity.class);
		i.putExtra("flag","R1");
		startActivity(i);
	}

	public void pdzc(View view) {
		Intent i = new Intent(this,LsjyZjmActivity.class);
		i.putExtra("flag","R2");
		startActivity(i);
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
		setContentView(R.layout.activity_lsjyxmxz);

		fh = (ImageButton) findViewById(R.id.fh);
		tc = (ImageButton) findViewById(R.id.tc);
		tc.setVisibility(View.VISIBLE);
		fh.setVisibility(View.GONE);
 
		userInfo = (TextView) findViewById(R.id.user_info);

		userInfo.setText("当前用户："+PreferencesUtils.getUserXm(this) + "    路试检验");

	}

}
