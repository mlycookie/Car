package edu.qau.car;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.qau.car.bean.Cjlb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TjjgActivity extends Activity {

	private ImageButton qbhg;

	public static void actionStart(final Context context, final Cjlb cjlb) {
		new WsAsyncTask().execute(new WsCallback() {

			String foramt = "<?xml version='1.0' encoding='GBK'?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><xmlb>%s</xmlb></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {
				User user = PreferencesUtils.getUser(context);
				String xml = String.format(foramt, cjlb.getJylsh(),
						user.getJCZBH(), PreferencesUtils.getUserJyxm(context));
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
				return "17F23";
			}

			@Override
			public void callback(JSONObject obj) {
				try {
					if (obj.getBoolean("ok")) {
						JSONArray array = obj.getJSONArray("Table");
						Intent intent = new Intent(context, TjjgActivity.class);
						intent.putExtra("bean", cjlb);
						intent.putExtra("array", array.toString());
						context.startActivity(intent);
					} else {
						Toast.makeText(context, "网络访问失败", Toast.LENGTH_LONG)
								.show();
					}
				} catch (JSONException e) {
					Toast.makeText(context, "网络访问失败", Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	public void back(View view) {
		finish();
	}

	private class TjjgItem implements OnClickListener {
		private String dmz;
		private String jy;
		private String dmsm4;

		private TextView bt;
		private ImageButton wj;
		private ImageButton hg;
		private ImageButton bhg;

		public String getDmsm4() {
			return dmsm4;
		}

		public String getDmz() {
			return dmz;
		}

		public String getJy() {
			return jy;
		}

		public TjjgItem(LinearLayout layout, String bt, String dmz, String dmsm4) {
			this.dmz = dmz;
			this.dmsm4 = dmsm4;
			jy = "0";
			View view = inflater.inflate(R.layout.tjjg_item, layout, false);
			this.bt = (TextView) view.findViewById(R.id.bt);
			this.bt.setText(bt);
			wj = (ImageButton) view.findViewById(R.id.wj);
			hg = (ImageButton) view.findViewById(R.id.hg);
			bhg = (ImageButton) view.findViewById(R.id.bhg);
			wj.setOnClickListener(this);
			hg.setOnClickListener(this);
			bhg.setOnClickListener(this);
			layout.addView(view);

		}

		@Override
		public void onClick(View v) {
			clear(wj, hg, bhg);
			if (v == wj) {
				this.jy = "0";
				wj.setImageResource(R.drawable.wj_p);
			} else if (v == hg) {
				this.jy = "1";
				hg.setImageResource(R.drawable.hg_p);
			} else if (v == bhg) {
				this.jy = "2";
				bhg.setImageResource(R.drawable.bhg_p);
			}
		}

		public void hg() {
			clear(wj, hg, bhg);
			this.jy = "1";
			hg.setImageResource(R.drawable.hg_p);
		}

		private void clear(ImageButton wj, ImageButton hg, ImageButton bhg) {
			this.jy = "0";
			wj.setImageResource(R.drawable.wj_n);
			hg.setImageResource(R.drawable.hg_n);
			bhg.setImageResource(R.drawable.bhg_n);
		}

	}

	private LayoutInflater inflater;
	private LinearLayout layout;
	private ArrayList<TjjgItem> list = new ArrayList<TjjgItem>();
	private TextView userInfo;
	private static final int CONFIRM = 1;

	public void tj(View view) {

		User user = PreferencesUtils.getUser(this);

		String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs><jyxm>%s</jyxm><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh>%s%s</vehispara></root>";

		String jyxm = null;
		String wj = "";
		if ("10".equals(user.getRYLB())) {
			jyxm = "F1";
			wj = "<wgjcjyy>" + user.getXM() + "</wgjcjyy><wgjcjyysfzh>"
					+ user.getSFZMHM() + "</wgjcjyysfzh>";
		} else if ("11".equals(user.getRYLB())) {
			jyxm = "DC";
			wj = "<dpdtjyy>" + user.getXM() + "</dpdtjyy><dpdtjyysfzh>"
					+ user.getSFZMHM() + "</dpdtjyysfzh>";
		} else if ("12".equals(user.getRYLB())) {
			jyxm = "C1";
			wj = "<dpjcjyy>" + user.getXM() + "</dpjcjyy><dpjyysfzh>"
					+ user.getSFZMHM() + "</dpjyysfzh>";
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (TjjgItem item : list) {
			stringBuffer.append("<").append(item.getDmsm4()).append(">")
					.append(item.getJy()).append("</").append(item.getDmsm4())
					.append(">");
		}

		String jycs = String.valueOf(Integer.parseInt(cjlb.getJycs()));
		String hpzl = cjlb.getHpzlNum() + "";
		if (hpzl.length() == 1) {
			hpzl = "0" + hpzl;
		}
		final String xml = String.format(foramt, cjlb.getJylsh(),
				user.getJCZBH(), cjlb.getXzcdh(), jycs, jyxm, hpzl,
				cjlb.getHphm(), cjlb.getClsbdh(), stringBuffer.toString(), wj);
		Log.w("car", xml);
		new XmlAsyncTask().execute(new XmlCallback() {

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {
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
				return "17F80";
			}

			@Override
			public void callback(String obj) {
				Log.w("car", obj);
				String code = XmlUtils.getValue(obj, "code");
				if ("1".equals(code)) {
					Toast.makeText(TjjgActivity.this, "数据保存成功",
							Toast.LENGTH_LONG).show();
					qbhg.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(TjjgActivity.this,
									ConfirmActivity.class);
							intent.putExtra("message", "是否结束本车辆的检验？");
							startActivityForResult(intent, CONFIRM);
						}
					});
				} else {
					String message = XmlUtils.getValue(obj, "message");
					Toast.makeText(TjjgActivity.this, message,
							Toast.LENGTH_LONG).show();

				}

			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}

	private Cjlb cjlb;

	private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm:ss");

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == CONFIRM) {
			if (resultCode == Activity.RESULT_OK) {
				new XmlAsyncTask().execute(new XmlCallback() {

					String format = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs><jyxm>%s</jyxm><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jssj>%s</jssj></vehispara></root>";

					@Override
					public String getXtlb() {
						return "17";
					}

					@Override
					public String getXml() {
						User user = PreferencesUtils.getUser(TjjgActivity.this);
						String jyxm = null;

						if ("10".equals(user.getRYLB())) {
							jyxm = "F1";
						} else if ("11".equals(user.getRYLB())) {
							jyxm = "DC";
						} else if ("12".equals(user.getRYLB())) {
							jyxm = "C1";
						}
						String jycs = String.valueOf(Integer.parseInt(cjlb
								.getJycs()));
						String hpzl = cjlb.getHpzlNum() + "";
						if (hpzl.length() == 1) {
							hpzl = "0" + hpzl;
						}
						String xml = String.format(format, cjlb.getJylsh(),
								user.getJCZBH(), cjlb.getXzcdh(), jycs, jyxm,
								hpzl, cjlb.getHphm(), cjlb.getClsbdh(),
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
						Log.w("car", obj);
						String code = XmlUtils.getValue(obj, "code");
						if ("1".equals(code)) {
							Intent intent = new Intent(TjjgActivity.this,
									ZjmActivity.class);
							//intent.putExtra("bean", cjlb);
							intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);
							//finish();
						} else {
							String message = XmlUtils.getValue(obj, "message");
							Toast.makeText(TjjgActivity.this, message,
									Toast.LENGTH_LONG).show();

						}

					}

					@Override
					public String getPara() {
						return "WriteXmlDoc";
					}
				});
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tjjg);

		userInfo = (TextView) findViewById(R.id.user_info);
		userInfo.setText(PreferencesUtils.getUserInfo(this));

		cjlb = getIntent().getParcelableExtra("bean");

		layout = (LinearLayout) findViewById(R.id.layout);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		qbhg = (ImageButton) findViewById(R.id.qbhg);

		String array = getIntent().getStringExtra("array");
		Log.w("car", array);
		try {
			JSONArray jsonArray = new JSONArray(array);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				TjjgItem item = new TjjgItem(layout, json.getString("dmsm1"),
						json.getString("dmz"), json.getString("dmsm4"));
				list.add(item);
				item.hg();

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
