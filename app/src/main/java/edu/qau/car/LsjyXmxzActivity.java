package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.qau.car.bean.Cjlb;

/**
 * 路试检验项目选择
 */
public class LsjyXmxzActivity extends Activity {

	private ImageButton fh;
	private ImageButton tc;
	private TextView userInfo;
	private Cjlb cjlb;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm:ss");
	private static final int CONFIRM = 1002;

	SharedPreferences.Editor editor ;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CONFIRM && resultCode == Activity.RESULT_OK) {
			lsjyIntf();
		}
	}
	
	/**
	 * 路试过程开始
	 * @param view
	 */
	public void lsgcks(View view) {
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jycs>%s</jycs><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jyxm>R</jyxm><kssj>%s</kssj></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {
				 

				User user = PreferencesUtils.getUser(LsjyXmxzActivity.this);
				String jycs = String.valueOf(Integer.parseInt(cjlb.getJycs()));
				String hpzl = cjlb.getHpzlNum() + "";
				if (hpzl.length() == 1) {
					hpzl = "0" + hpzl;
				}
				String xml = String.format(foramt, cjlb.getJylsh(),
						user.getJCZBH(), jycs, hpzl, cjlb.getHphm(),
						cjlb.getClsbdh(), simpleDateFormat.format(new Date()));

				return xml;
			}

			@Override
			public String getUri() {
				return null;
			}

			@Override
			public String getNamespace() {
				return null;
			}

			@Override
			public String getMethodName() {
				return "writeObjectOut";
			}

			@Override
			public String getJkxlh() {
				return "00000";
			}

			@Override
			public String getJkid() {
				return "17F55";
			}

			@Override
			public void callback(String obj) {
				Log.w("car", obj);
				String code = XmlUtils.getValue(obj, "code");
				String message = XmlUtils.getValue(obj, "message");
				 
				Toast.makeText(LsjyXmxzActivity.this, message,
						Toast.LENGTH_LONG).show();
			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}
	
	/**
	 * 路试过程结束
	 * @param view
	 */
	public void lsgcjs(View view) {

		Intent intent = new Intent(LsjyXmxzActivity.this,
				ConfirmActivity.class);
		intent.putExtra("message",
				" 是否结束本车辆的路试检验？");
		startActivityForResult(intent, CONFIRM);
		
	}

	public void lsjyIntf(){
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jycs>%s</jycs><jyxm>R</jyxm><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jssj>%s</jssj></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {

				String hpzl = cjlb.getHpzlNum() + "";
				if (hpzl.length() == 1) {
					hpzl = "0" + hpzl;
				}

				User user = PreferencesUtils.getUser(LsjyXmxzActivity.this);
				String xml = String.format(foramt, cjlb.getJylsh(),
						user.getJCZBH(), cjlb.getJycs(), hpzl,
						cjlb.getHphm(), cjlb.getClsbdh(),
						simpleDateFormat.format(new Date()));
				Log.w("car", xml);
				return xml;
			}

			@Override
			public String getUri() {
				return null;
			}

			@Override
			public String getNamespace() {
				return null;
			}

			@Override
			public String getMethodName() {
				return "writeObjectOut";
			}

			@Override
			public String getJkxlh() {
				return "00000";
			}

			@Override
			public String getJkid() {
				return "17F58";
			}

			@Override
			public void callback(String obj) {

				String code = XmlUtils.getValue(obj, "code");
				String message = XmlUtils.getValue(obj, "message");
				if ("1".equals(code)) {
					Intent i = new Intent(LsjyXmxzActivity.this,ZjmActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					finish();
				} else {
				}
				Toast.makeText(LsjyXmxzActivity.this, message, Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}	
	
	/**
	 * 行车制动	R1
	 * 坡度驻车	R2
	 * @param view
	 */
	public void xczd(View view) {

		editor .putString("lsjy","R1");
		editor.putString("lsjyStr","行车制动");
		editor.commit();
		
		Intent intent = new Intent(LsjyXmxzActivity.this, JyxxActivity.class);
		intent.putExtra("bean", cjlb);
		startActivity(intent);
	}

	public void pdzc(View view) {
 
		editor .putString("lsjy","R2");
		editor.putString("lsjyStr","坡道驻车");
		editor.commit();
		
		Intent intent = new Intent(LsjyXmxzActivity.this, JyxxActivity.class);
		intent.putExtra("bean", cjlb);
		startActivity(intent);
	}
	
	public void back(View view) {
		//finish();
		Intent intent = new Intent(this,ZjmActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lsjyxmxz);

		fh = (ImageButton) findViewById(R.id.fh);
		tc = (ImageButton) findViewById(R.id.tc);

		cjlb = getIntent().getParcelableExtra("bean");
		
		userInfo = (TextView) findViewById(R.id.user_info);

		userInfo.setText(PreferencesUtils.getUserInfo(this));

		TextView jylsh = (TextView) findViewById(R.id.jylsh);
		TextView hmpz = (TextView) findViewById(R.id.hphm);
		jylsh.setText(cjlb.getJylsh());
		hmpz.setText(cjlb.getHphm());

		SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
		editor =   sharedPreferences.edit();
		
	}

}
