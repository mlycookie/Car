package edu.qau.car;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import edu.qau.car.bean.Cjlb;

public class CjlbActivity extends Activity {
	
	private boolean isLjy;
	private ListView cjlbList;
	private CjlbAdapter adapter;
	private List<Cjlb> list;
	private LayoutInflater inflater;
	private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
			"yyyy-MM-dd");
	private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm:ss");

	private static final HashMap<String, String> hpzl = new HashMap<String, String>();
	static {
		hpzl.put("01", "大型汽车");
		hpzl.put("02", "小型汽车");
		hpzl.put("03", "使馆汽车");
		hpzl.put("04", "领馆汽车");
		hpzl.put("05", "境外汽车");
		hpzl.put("06", "外籍汽车");
		hpzl.put("07", "普通摩托车");
		hpzl.put("08", "轻便摩托车");
		hpzl.put("09", "使馆摩托车");
		hpzl.put("10", "领馆摩托车");
		hpzl.put("11", "境外摩托车");
		hpzl.put("12", "外籍摩托车");
		hpzl.put("13", "低速车");
		hpzl.put("14", "拖拉机");
		hpzl.put("15", "挂车");
		hpzl.put("16", "教练汽车");
		hpzl.put("17", "教练摩托车");
		hpzl.put("18", "试验汽车");
		hpzl.put("19", "试验摩托车");
		hpzl.put("20", "临时入境汽车");
		hpzl.put("21", "临时入境摩托车");
		hpzl.put("22", "临时行驶车");
		hpzl.put("23", "警用汽车");
		hpzl.put("24", "警用摩托");
		hpzl.put("25", "原农机号牌");
		hpzl.put("26", "香港入出境车");
		hpzl.put("27", "澳门入出境车");
		hpzl.put("31", "武警号牌");
		hpzl.put("32", "军队号牌");
		hpzl.put("41", "无号牌");
		hpzl.put("42", "假号牌");
		hpzl.put("43", "挪用号牌");
		hpzl.put("51", "大型新能源汽车");
		hpzl.put("52", "小型新能源汽车");
		hpzl.put("99", "其他号牌");
	}

	public void back(View view) {
		finish();
	}

	public static void actionStart(final Context context, final String search) {
		new WsAsyncTask().execute(new WsCallback() {

			String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jyjgbh>%s</jyjgbh><jyxm>%s</jyxm><hphm>%s</hphm></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {
				User user = PreferencesUtils.getUser(context);
				String xml = String.format(foramt, user.getJCZBH(),
						PreferencesUtils.getJyxm(context), search);
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
				return "17F25";
			}

			@Override
			public void callback(JSONObject obj) {
				try {
					if (obj.getBoolean("ok")) {
						JSONArray array = obj.getJSONArray("Table");
						Intent intent = new Intent(context, CjlbActivity.class);
						intent.putExtra("array", array.toString());
						context.startActivity(intent);
					} else {
						Toast.makeText(context, "未查询到车辆信息", Toast.LENGTH_LONG)
								.show();
					}
				} catch (JSONException e) {
					Toast.makeText(context, "网络访问失败", Toast.LENGTH_LONG).show();
				}

			}
		});
	}

	public static void actionStart(final Context context, final int jylx) {
		new WsAsyncTask().execute(new WsCallback() {

			String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jyjgbh>%s</jyjgbh><jyrq>%s</jyrq><jylx>%d</jylx><jyxm>%s</jyxm></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {
				User user = PreferencesUtils.getUser(context);
				String xml = String.format(foramt, user.getJCZBH(),simpleDateFormat1.format(new Date()), jylx,
						PreferencesUtils.getJyxm(context));
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
				return "17F22";
			}

			@Override
			public void callback(JSONObject obj) {
				try {
					if (obj.getBoolean("ok")) {
						JSONArray array = obj.getJSONArray("Table");
						Intent intent = new Intent(context, CjlbActivity.class);
						intent.putExtra("array", array.toString());
						context.startActivity(intent);
					} else {
						Toast.makeText(context, "未查询到车辆信息", Toast.LENGTH_LONG)
								.show();
					}
				} catch (JSONException e) {
					Toast.makeText(context, "网络访问失败", Toast.LENGTH_LONG).show();
				}

			}
		});
	}

	private TextView userInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cjlb);

		userInfo = (TextView) findViewById(R.id.user_info);

		isLjy = PreferencesUtils.isLsy(this);
//		if(isLjy){
			userInfo.setText(PreferencesUtils.getUserInfo(this));
//		}
//		else{
//			userInfo.setText(PreferencesUtils.getUserInfo(this));
//		}

		String array = getIntent().getStringExtra("array");
		Log.w("car", array);
		init(array);

		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		adapter = new CjlbAdapter();
		cjlbList = (ListView) findViewById(R.id.cjlb_list);
		cjlbList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		cjlbList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {				
				User user = PreferencesUtils.getUser(CjlbActivity.this);
				if (user.getRYLB().equals("10")) {
					ClActivity.actionStart(CjlbActivity.this, list.get(position));
				}else if(user.getRYLB().equals("15")){
					Intent intent = new Intent(CjlbActivity.this, LsjyXmxzActivity.class);
					intent.putExtra("bean", list.get(position));
					startActivity(intent);
				}else{
					Intent intent = new Intent(CjlbActivity.this, JyxxActivity.class);
					intent.putExtra("bean", list.get(position));
					startActivity(intent);
				}
			}
		});

	}
	
	private void init(String array) {

		try {
			
			list = new ArrayList<Cjlb>();
			JSONArray jsonArray = new JSONArray(array);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Cjlb bean = new Cjlb();
				bean.setJylsh(jsonObject.getString("jylsh"));
				bean.setHphm(jsonObject.getString("hphm"));
				try {
					Date date = simpleDateFormat2.parse(jsonObject
							.getString("jyrq"));
					bean.setCcrq(simpleDateFormat1.format(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String hpzlTemp = jsonObject.getString("hpzl");
				if (hpzlTemp.length() == 1) {
					hpzlTemp = "0" + hpzlTemp;
				}
				bean.setHpzl(hpzl.get(hpzlTemp));
				bean.setHpzlNum(Integer.parseInt(jsonObject.getString("hpzl")));
				bean.setSyr("");
				bean.setZt(Integer.parseInt(jsonObject.getString("iswg")));
				bean.setIsdpdt(Integer.parseInt(jsonObject.getString("isdpdt")));
				bean.setIsdp(Integer.parseInt(jsonObject.getString("isdp")));

				try{
					bean.setIsls(Integer.parseInt(jsonObject.getString("isls")));
					bean.setIspdzc(Integer.parseInt(jsonObject.getString("ispdzc")));
				}catch (Exception e){
					e.printStackTrace();
				}

				String jycs = jsonObject.getString("jycs");
				if (null == jycs || "".equals(jycs)) {
					bean.setJycs("0");
				} else {
					bean.setJycs(jycs);
				}
				bean.setClsbdh(jsonObject.getString("clsbdh"));
				list.add(bean);

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private class CjlbAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			ViewHolder holder;
			if (arg1 == null) {
				arg1 = inflater.inflate(R.layout.cjlb_item, arg2, false);
				holder = new ViewHolder();
				holder.jylsh = (TextView) arg1.findViewById(R.id.jylsh);
				holder.hphm = (TextView) arg1.findViewById(R.id.hphm);
				holder.ccrq = (TextView) arg1.findViewById(R.id.ccrq);
				holder.hpzl = (TextView) arg1.findViewById(R.id.hpzl);
				holder.zt = (TextView) arg1.findViewById(R.id.zt);
				holder.syr = (TextView) arg1.findViewById(R.id.syr);
				holder.dpdt = (TextView) arg1.findViewById(R.id.dpdt);
				holder.dp = (TextView) arg1.findViewById(R.id.dp);
				holder.xczd = (TextView) arg1.findViewById(R.id.xczd);
				holder.pdzc = (TextView) arg1.findViewById(R.id.pdzc);
				arg1.setTag(holder);
			} else {
				holder = (ViewHolder) arg1.getTag();
			}

			holder.jylsh.setText(list.get(arg0).getJylsh());
			holder.hphm.setText(list.get(arg0).getHphm());
			holder.ccrq.setText(list.get(arg0).getCcrq());
			holder.hpzl.setText(list.get(arg0).getHpzl());
			holder.syr.setText(list.get(arg0).getSyr());
			if (list.get(arg0).getZt() == 0) {
				holder.zt.setBackgroundResource(R.drawable.wgbjc);
			} else if (list.get(arg0).getZt() == 1) {
				holder.zt.setBackgroundResource(R.drawable.wgdjc);
			} else if (list.get(arg0).getZt() == 2) {
				holder.zt.setBackgroundResource(R.drawable.wgyjc);
			} else if (list.get(arg0).getZt() == 3) {
				holder.zt.setBackgroundResource(R.drawable.wgfj);
			}

			if (list.get(arg0).getIsdpdt() == 0) {
				holder.dpdt.setBackgroundResource(R.drawable.dpdtbjc);
			} else if (list.get(arg0).getIsdpdt() == 1) {
				holder.dpdt.setBackgroundResource(R.drawable.dpdtdjc);
			} else if (list.get(arg0).getIsdpdt() == 2) {
				holder.dpdt.setBackgroundResource(R.drawable.dpdtyjc);
			} else if (list.get(arg0).getIsdpdt() == 3) {
				holder.dpdt.setBackgroundResource(R.drawable.dpdtfj);
			}

			if(isLjy){
				if (list.get(arg0).getIsdp() == 0) {
					holder.dp.setBackgroundResource(R.drawable.dpbjc);
				} else if (list.get(arg0).getIsdp() == 1) {
					holder.dp.setBackgroundResource(R.drawable.dpdjc);
				} else if (list.get(arg0).getIsdp() == 2) {
					holder.dp.setBackgroundResource(R.drawable.dpyjc);
				} else if (list.get(arg0).getIsdp() == 3) {
					holder.dp.setBackgroundResource(R.drawable.dpfj);
				}

				if (list.get(arg0).getIsls() == 0) {
					holder.xczd.setBackgroundResource(R.drawable.xczdbjc);
				} else if (list.get(arg0).getIsls() == 1) {
					holder.xczd.setBackgroundResource(R.drawable.xczddjc);
				} else if (list.get(arg0).getIsls() == 2) {
					holder.xczd.setBackgroundResource(R.drawable.xczdyjc);
				} else if (list.get(arg0).getIsls() == 3) {
					holder.xczd.setBackgroundResource(R.drawable.xczdfj);
				}

				if (list.get(arg0).getIspdzc() == 0) {
					holder.pdzc.setBackgroundResource(R.drawable.pdzcbjc);
				} else if (list.get(arg0).getIspdzc() == 1) {
					holder.pdzc.setBackgroundResource(R.drawable.pdzcdjc);
				} else if (list.get(arg0).getIspdzc() == 2) {
					holder.pdzc.setBackgroundResource(R.drawable.pdzcyjc);
				} else if (list.get(arg0).getIspdzc() == 3) {
					holder.pdzc.setBackgroundResource(R.drawable.pdzcfj);
				}
				
			}else{
				if (list.get(arg0).getIsdp() == 0) {
					holder.dp.setBackgroundResource(R.drawable.dpbjc1);
				} else if (list.get(arg0).getIsdp() == 1) {
					holder.dp.setBackgroundResource(R.drawable.dpdjc1);
				} else if (list.get(arg0).getIsdp() == 2) {
					holder.dp.setBackgroundResource(R.drawable.dpyjc1);
				} else if (list.get(arg0).getIsdp() == 3) {
					holder.dp.setBackgroundResource(R.drawable.dpfj1);
				}
				holder.dp.getLayoutParams().height=(dp2px(CjlbActivity.this,42));
			}
 
			return arg1;
		}
	}

	private class ViewHolder {

		TextView jylsh, hphm, ccrq, hpzl, zt, syr, dpdt, dp,xczd,pdzc;

	}

	private int dp2px(Context context , float dpValue){
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}
