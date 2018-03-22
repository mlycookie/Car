package edu.qau.car;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.qau.car.bean.Cjlb;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ClActivity extends Activity implements OnClickListener {
	private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm:ss");

	private ImageButton zzs1;
	private ImageButton zzs2;
	private ImageButton zzs3;
	private ImageButton zzs4;

	private EditText wlkc1, wlkc2, wlkc3, wlkk1, wlkk2, wlkk3, wlkg1, wlkg2,
			wlkg3, gcc1, gcc2, gcc3, gck1, gck2, gck3, gcg1, gcg2, gcg3;

	int zzs = 0;

	private void clear() {
		wlkc1.setText("");
		wlkc2.setText("");
		wlkc3.setText("");
		wlkk1.setText("");
		wlkk2.setText("");
		wlkk3.setText("");
		wlkg1.setText("");
		wlkg2.setText("");
		wlkg3.setText("");
		gcc1.setText("");
		gcc2.setText("");
		gcc3.setText("");
		gck1.setText("");
		gck2.setText("");
		gck3.setText("");
		gcg1.setText("");
		gcg2.setText("");
		gcg3.setText("");
	}

	public void scsj(View view) {
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version='1.0' encoding='GBK' ?><root><vehispara><jylsh>%s</jylsh></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {

				String xml = String.format(foramt, cjlb.getJylsh());
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
				return "17F05";
			}

			@Override
			public void callback(String obj) {

				String code = XmlUtils.getValue(obj, "code");
				String message = XmlUtils.getValue(obj, "message");
				if ("1".equals(code)) {
				} else {
				}
				Toast.makeText(ClActivity.this, message, Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}

	public void clear(ImageButton yi, ImageButton er, ImageButton san,
			ImageButton si) {
		si.setImageResource(R.drawable.si_n);
		san.setImageResource(R.drawable.san_n);
		er.setImageResource(R.drawable.er_n);
		yi.setImageResource(R.drawable.yi_n);
	}

	public void cz(View view) {
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version='1.0' encoding='GBK' ?><root><vehispara><teststa>4</teststa></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {

				Log.w("car", foramt);
				return foramt;
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
				return "17F04";
			}

			@Override
			public void callback(String obj) {

				String code = XmlUtils.getValue(obj, "code");
				String message = XmlUtils.getValue(obj, "message");
				if ("1".equals(code)) {
					clear();
				} else {
				}
				Toast.makeText(ClActivity.this, message, Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}

	public void cljg(View view) {
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version='1.0' encoding='GBK' ?><root><vehispara><jylsh>%s</jylsh></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {

				String xml = String.format(foramt, cjlb.getJylsh());
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
				return null;
			}

			@Override
			public String getJkxlh() {
				return "00000";
			}

			@Override
			public String getJkid() {
				return "17F02";
			}

			@Override
			public void callback(String obj) {
				Log.w("cljg", obj);

				String code = XmlUtils.getValue(obj, "code");
				String message = XmlUtils.getValue(obj, "message");
				if ("1".equals(code)) {
					wlkc1.setText(XmlUtils.getValue(obj, "cwkc"));
					wlkk1.setText(XmlUtils.getValue(obj, "cwkk"));
					wlkg1.setText(XmlUtils.getValue(obj, "cwkg"));

					wlkc2.setText(XmlUtils.getValue(obj, "wkcbz"));
					wlkk2.setText(XmlUtils.getValue(obj, "wkkbz"));
					wlkg2.setText(XmlUtils.getValue(obj, "wkgbz"));

					wlkc3.setText(XmlUtils.getValue(obj, "wkcpd"));
					wlkk3.setText(XmlUtils.getValue(obj, "wkkpd"));
					wlkg3.setText(XmlUtils.getValue(obj, "wkgpd"));

				} else {
				}
				Toast.makeText(ClActivity.this, code + " " + message,
						Toast.LENGTH_LONG).show();

			}

			@Override
			public String getPara() {
				return null;
			}
		});
	}

	public void kscl(View view) {
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version='1.0' encoding='GBK' ?><root><vehispara id='0'><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><clsbdh>%s</clsbdh></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {

				User user = PreferencesUtils.getUser(ClActivity.this);
				String xml = String.format(foramt, cjlb.getJylsh(),
						user.getJCZBH(), cjlb.getClsbdh());
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
				return "17F03";
			}

			@Override
			public void callback(String obj) {

				String code = XmlUtils.getValue(obj, "code");
				String message = XmlUtils.getValue(obj, "message");
				if ("1".equals(code)) {
				} else {
				}
				Toast.makeText(ClActivity.this, message, Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}

	public void jsxm(View view) {
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs><jyxm>%s</jyxm><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jssj>%s</jssj></vehispara></root>";

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

				User user = PreferencesUtils.getUser(ClActivity.this);
				String xml = String.format(foramt, cjlb.getJylsh(),
						user.getJCZBH(), zzs, cjlb.getJycs(), "M1", hpzl,
						cjlb.getHphm(), cjlb.getClsbdh(),
						simpleDateFormat2.format(new Date()));
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
				} else {
				}
				Toast.makeText(ClActivity.this, message, Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}

	public void xmks(View view) {
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs><jyxm>%s</jyxm><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><kssj>%s</kssj></vehispara></root>";

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

				User user = PreferencesUtils.getUser(ClActivity.this);
				String xml = String.format(foramt, cjlb.getJylsh(),
						user.getJCZBH(), zzs, cjlb.getJycs(), "M1", hpzl,
						cjlb.getHphm(), cjlb.getClsbdh(),
						simpleDateFormat2.format(new Date()));
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
				return "17F55";
			}

			@Override
			public void callback(String obj) {

				String code = XmlUtils.getValue(obj, "code");
				String message = XmlUtils.getValue(obj, "message");
				if ("1".equals(code)) {
				} else {
				}
				Toast.makeText(ClActivity.this, message, Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}

	private Cjlb cjlb;

	public static void actionStart(final Context context, final Cjlb cjlb) {
		Intent intent = new Intent(context, ClActivity.class);
		intent.putExtra("bean", cjlb);
		context.startActivity(intent);

	}

	public void back(View view) {
		finish();
	}

	public void next(View view) {
		Intent intent = new Intent(this, JyxxActivity.class);
		intent.putExtra("bean", cjlb);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cl);

		cjlb = getIntent().getParcelableExtra("bean");

		zzs1 = (ImageButton) findViewById(R.id.zzs1);
		zzs1.setOnClickListener(this);
		zzs2 = (ImageButton) findViewById(R.id.zzs2);
		zzs2.setOnClickListener(this);
		zzs3 = (ImageButton) findViewById(R.id.zzs3);
		zzs3.setOnClickListener(this);
		zzs4 = (ImageButton) findViewById(R.id.zzs4);
		zzs4.setOnClickListener(this);

		wlkc1 = (EditText) findViewById(R.id.wkjc1);
		wlkc2 = (EditText) findViewById(R.id.wkjc2);
		wlkc3 = (EditText) findViewById(R.id.wkjc3);

		wlkk1 = (EditText) findViewById(R.id.wkjk1);
		wlkk2 = (EditText) findViewById(R.id.wkjk2);
		wlkk3 = (EditText) findViewById(R.id.wkjk3);

		wlkg1 = (EditText) findViewById(R.id.wkjg1);
		wlkg2 = (EditText) findViewById(R.id.wkjg2);
		wlkg3 = (EditText) findViewById(R.id.wkjg3);

		gcc1 = (EditText) findViewById(R.id.gcc1);
		gcc2 = (EditText) findViewById(R.id.gcc2);
		gcc3 = (EditText) findViewById(R.id.gcc3);

		gck1 = (EditText) findViewById(R.id.gck1);
		gck2 = (EditText) findViewById(R.id.gck2);
		gck3 = (EditText) findViewById(R.id.gck3);

		gcg1 = (EditText) findViewById(R.id.gcg1);
		gcg2 = (EditText) findViewById(R.id.gcg2);
		gcg3 = (EditText) findViewById(R.id.gcg3);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zzs1:
			clear(zzs1, zzs2, zzs3, zzs4);
			zzs1.setImageResource(R.drawable.yi_p);
			zzs = 1;
			break;
		case R.id.zzs2:
			clear(zzs1, zzs2, zzs3, zzs4);
			zzs2.setImageResource(R.drawable.er_p);
			zzs = 2;
			break;
		case R.id.zzs3:
			clear(zzs1, zzs2, zzs3, zzs4);
			zzs3.setImageResource(R.drawable.san_p);
			zzs = 3;
			break;
		case R.id.zzs4:
			clear(zzs1, zzs2, zzs3, zzs4);
			zzs4.setImageResource(R.drawable.si_p);
			zzs = 4;
			break;
		}
	}

}
