package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.qau.car.bean.Cjlb;

public class JyxxActivity extends Activity implements View.OnClickListener {

	private TextView jylsh;
	private TextView hphm;
	private TextView ccrq;
	private TextView hpzl;
	private TextView syr;
	private ImageView cd1;
	private ImageView cd2;
	private ImageView cd3;
	private ImageView cd4;
	private final Integer N = 1;
	private final Integer P = 2;
	private LinearLayout cdh;
//	private LinearLayout zcpd;

	private static final int CONFIRM = 1;

	private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm:ss");

	private int xzcdh = 0;

	private TextView userInfo;
	private boolean isLjy;
	public void qd(View view) {
		TjjgActivity.actionStart(this, cjlb);
	}

	public void ckzp(View view) {
		PzActivity.actionStart(this, cjlb);

	}

	public void back(View view) {
		finish();
	}

	public void jyxm(View view) {
		User user = PreferencesUtils.getUser(this);
		if ("10".equals(user.getRYLB())) {
			JyxmActivity.actionStart(this, cjlb, 1);
		}else if(isLjy){
			
		} else{
			TjjgActivity.actionStart(this, cjlb);
		}
	}

	public void jsjy(View view) {
		if(isLjy){
			Intent intent = new Intent(this, LsjyXmxzActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}else{
			Intent intent = new Intent(this, ZjmActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
	}
	
	public boolean isPdzc(){
		if(isLjy) {
			String type = PreferencesUtils.getUserLsjyType(this);
			if ("R2".equals(type)) {
				return true;
			}
		}
		return false;
	}
	
	public void ksjy(View view) {

		if ("10".equals(user.getRYLB()) && xzcdh == 0) {
			Toast.makeText(this, "请选择车道号", Toast.LENGTH_LONG).show();
			return;
		} else if ("12".equals(user.getRYLB()) && xzcdh == 0) {
			Toast.makeText(this, "请选择检测线号", Toast.LENGTH_LONG).show();
			return;
		}else if(isLjy){
			String type = PreferencesUtils.getUserLsjyType(this);
			if("R2".equals(type)&& xzcdh == 0){
				Toast.makeText(this, "请选择驻车坡度", Toast.LENGTH_LONG).show();
				return;
			}else if("R2".equals(type)&& xzcdh > 2 ){
				Toast.makeText(this, "请先正确选择当前检验的驻车坡度！", Toast.LENGTH_LONG).show();
				return;
			}
		}
 		startTask();
	}
	
	private  void startTask(){
		new XmlAsyncTask().execute(new XmlCallback() {

			String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jyxm>%s</jyxm><kssj>%s</kssj></vehispara></root>";

			@Override
			public String getXtlb() {
				return "17";
			}

			@Override
			public String getXml() {
				String jyxm;

				if ("10".equals(user.getRYLB())) {
					jyxm = "F1";
				} else if ("11".equals(user.getRYLB())) {
					jyxm = "DC";
				} else if ("12".equals(user.getRYLB())) {
					jyxm = "C1";
				}else  if ("15".equals(user.getRYLB())){
					jyxm = PreferencesUtils.getUserLsjyType(JyxxActivity.this);
				} else {
					jyxm = "";
				}
				cjlb.setJyxm(jyxm);
				cjlb.setXzcdh(xzcdh);

				User user = PreferencesUtils.getUser(JyxxActivity.this);
				String jycs = String.valueOf(Integer.parseInt(cjlb.getJycs()));
				String hpzl = cjlb.getHpzlNum() + "";
				if (hpzl.length() == 1) {
					hpzl = "0" + hpzl;
				}
				String xml = String.format(foramt, cjlb.getJylsh(),
						user.getJCZBH(), xzcdh, jycs, hpzl, cjlb.getHphm(),
						cjlb.getClsbdh(), jyxm,
						simpleDateFormat2.format(new Date()));

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
				if ("1".equals(code)) {

					if ("10".equals(user.getRYLB())) {
						Intent intent = new Intent(JyxxActivity.this,
								ConfirmActivity.class);
						intent.putExtra("message",
								"您确认要在" + xzcdh + "号车道开始检验号牌为" + cjlb.getHphm()
										+ "的" + cjlb.getHpzl() + "吗？");
						startActivityForResult(intent, CONFIRM);
					} else if ("12".equals(user.getRYLB())) {
						Intent intent = new Intent(JyxxActivity.this,
								ConfirmActivity.class);
						intent.putExtra(
								"message",
								"您确认要在" + xzcdh + "号检测线开始检验号牌为"
										+ cjlb.getHphm() + "的" + cjlb.getHpzl()
										+ "吗？");
						startActivityForResult(intent, CONFIRM);
					}else if ("15".equals(user.getRYLB() ) && "R1".equals(PreferencesUtils.getUserLsjyType(JyxxActivity.this))) {
						Intent intent = new Intent(JyxxActivity.this,	LsjyXczdActivity.class);
						intent.putExtra("bean", cjlb);
						startActivity (intent );
					}else if ("15".equals(user.getRYLB() ) && "R2".equals(PreferencesUtils.getUserLsjyType(JyxxActivity.this))) {
						Intent intent = new Intent(JyxxActivity.this,
								ConfirmActivity.class);
						intent.putExtra(
								"message",
								"您确认要在" + (xzcdh==1?"15%" : xzcdh == 2 ? "20%" : "") + "驻车坡度开始检验号牌为"
										+ cjlb.getHphm() + "的" + cjlb.getHpzl()
										+ "吗？");
						startActivityForResult(intent, CONFIRM);
					} else {
						User user = PreferencesUtils.getUser(JyxxActivity.this);
						if (user.getRYLB().equals("10")) {
							JyxmActivity.actionStart(JyxxActivity.this, cjlb, 0);
						} else {
							PzActivity.actionStart(JyxxActivity.this, cjlb);
						}
					}

				} else {
					String message = XmlUtils.getValue(obj, "message");
					Toast.makeText(JyxxActivity.this, message,
							Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public String getPara() {
				return "WriteXmlDoc";
			}
		});
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CONFIRM) {

			if (resultCode == Activity.RESULT_OK) {
				User user = PreferencesUtils.getUser(this);
				if (user.getRYLB().equals("10")) {
					JyxmActivity.actionStart(this, cjlb, 0);
				}else if ("15".equals(user.getRYLB() ) && "R2".equals(PreferencesUtils.getUserLsjyType(JyxxActivity.this))) {
					Intent intent = new Intent(JyxxActivity.this,LsjyPdzcActivity.class);
					intent.putExtra("bean", cjlb);
					intent.putExtra("zcpd",xzcdh==1?"1":xzcdh==2?"0":"");
					startActivity (intent );
				}else{
					PzActivity.actionStart(this, cjlb);
				}
			}
		}
	}

	private User user;
	private Cjlb cjlb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jyxx);

		userInfo = (TextView) findViewById(R.id.user_info);

		isLjy = PreferencesUtils.isLsy(this);
		if(isLjy){
			userInfo.setText(PreferencesUtils.getUserLsyInfo(this));
		}else{
			userInfo.setText(PreferencesUtils.getUserInfo(this));
		}
 
		cdh = (LinearLayout) findViewById(R.id.cdh);
		cd1 = (ImageView) findViewById(R.id.cd1);
		cd2 = (ImageView) findViewById(R.id.cd2);
		cd3 = (ImageView) findViewById(R.id.cd3);
		cd4 = (ImageView) findViewById(R.id.cd4);
		
		user = PreferencesUtils.getUser(this);
		if ("12".equals(user.getRYLB())) {
			cdh.setBackgroundResource(R.drawable.xzcdh2);
		}else if("15".equals(user.getRYLB())){
			String type = PreferencesUtils.getUserLsjyType(this);
			if("R1".equals(type)){
 				cdh.setVisibility(View.GONE);
			}else if("R2".equals(type)){
				cdh.setBackgroundResource(R.drawable.xzzcpd);
				clear();
				//默认选中第一个
				cd1.setImageResource(R.drawable.zcpd1_p);
				cd1.setTag(P);
				xzcdh = 1;
			}
		}

		jylsh = (TextView) findViewById(R.id.jylsh);
		hphm = (TextView) findViewById(R.id.hphm);
		ccrq = (TextView) findViewById(R.id.ccrq);
		hpzl = (TextView) findViewById(R.id.hpzl);
		syr = (TextView) findViewById(R.id.syr);

		cjlb = getIntent().getParcelableExtra("bean");
		jylsh.setText(cjlb.getJylsh());
		hphm.setText(cjlb.getHphm());
		ccrq.setText(cjlb.getCcrq());
		hpzl.setText(cjlb.getHpzl());
		syr.setText(cjlb.getSyr());

	

		cd1.setOnClickListener(this);
		cd2.setOnClickListener(this);
		cd3.setOnClickListener(this);
		cd4.setOnClickListener(this);

		cd1.setTag(N);
		cd2.setTag(N);
		cd3.setTag(N);
		cd4.setTag(N);

		if(isPdzc()){
			if (cjlb.getXzcdh() == 1) {
				cd1.setImageResource(R.drawable.zcpd1_p);
				cd1.setTag(P);
				xzcdh = 1;
			} else if (cjlb.getXzcdh() == 2) {
				cd2.setImageResource(R.drawable.zcpd2_p);
				cd2.setTag(P);
				xzcdh = 2;
			} else if (cjlb.getXzcdh() == 3) {
				cd3.setImageResource(R.drawable.zcpd3_p);
				cd3.setTag(P);
				xzcdh = 3;
			} else if (cjlb.getXzcdh() == 4) {
				cd4.setImageResource(R.drawable.zcpd4_p);
				cd4.setTag(P);
				xzcdh = 4;
			}
		}else{
			if (cjlb.getXzcdh() == 1) {
				cd1.setImageResource(R.drawable.cd1_p);
				cd1.setTag(P);
				xzcdh = 1;
			} else if (cjlb.getXzcdh() == 2) {
				cd2.setImageResource(R.drawable.cd2_p);
				cd2.setTag(P);
				xzcdh = 2;
			} else if (cjlb.getXzcdh() == 3) {
				cd3.setImageResource(R.drawable.cd3_p);
				cd3.setTag(P);
				xzcdh = 3;
			} else if (cjlb.getXzcdh() == 4) {
				cd4.setImageResource(R.drawable.cd4_p);
				cd4.setTag(P);
				xzcdh = 4;
			}
		}
		
	}

	public void clear() {
		xzcdh = 0;
		if(isPdzc()){
			cd1.setImageResource(R.drawable.zcpd1_n);
			cd1.setTag(N);
			cd2.setImageResource(R.drawable.zcpd2_n);
			cd2.setTag(N);
			cd3.setImageResource(R.drawable.zcpd3_n);
			cd3.setTag(N);
			cd4.setImageResource(R.drawable.zcpd4_n);
			cd4.setTag(N);
		}else{
			cd1.setImageResource(R.drawable.cd1_n);
			cd1.setTag(N);
			cd2.setImageResource(R.drawable.cd2_n);
			cd2.setTag(N);
			cd3.setImageResource(R.drawable.cd3_n);
			cd3.setTag(N);
			cd4.setImageResource(R.drawable.cd4_n);
			cd4.setTag(N);
		}
		
		
	}

	@Override
	public void onClick(View v) {

		if(isPdzc()){
			switch (v.getId()) {
				case R.id.cd1:
					if (cd1.getTag().equals(N)) {
						clear();
						cd1.setImageResource(R.drawable.zcpd1_p);
						cd1.setTag(P);
						xzcdh = 1;
					} else {
						clear();
					}
					break;
				case R.id.cd2:
					if (cd2.getTag().equals(N)) {
						clear();
						cd2.setImageResource(R.drawable.zcpd2_p);
						cd2.setTag(P);
						xzcdh = 2;
					} else {
						clear();
					}
					break;
				case R.id.cd3:
					if (cd3.getTag().equals(N)) {
						clear();
						cd3.setImageResource(R.drawable.zcpd3_p);
						cd3.setTag(P);
						xzcdh = 3;
					} else {
						clear();
					}
					break;
				case R.id.cd4:
					if (cd4.getTag().equals(N)) {
						clear();
						cd4.setImageResource(R.drawable.zcpd4_p);
						cd4.setTag(P);
						xzcdh = 4;
					} else {
						clear();
					}
					break;
				default:
					break;
			}
		}else{
			switch (v.getId()) {
				case R.id.cd1:
					if (cd1.getTag().equals(N)) {
						clear();
						cd1.setImageResource(R.drawable.cd1_p);
						cd1.setTag(P);
						xzcdh = 1;
					} else {
						clear();
					}
					break;
				case R.id.cd2:
					if (cd2.getTag().equals(N)) {
						clear();
						cd2.setImageResource(R.drawable.cd2_p);
						cd2.setTag(P);
						xzcdh = 2;
					} else {
						clear();
					}
					break;
				case R.id.cd3:
					if (cd3.getTag().equals(N)) {
						clear();
						cd3.setImageResource(R.drawable.cd3_p);
						cd3.setTag(P);
						xzcdh = 3;
					} else {
						clear();
					}
					break;
				case R.id.cd4:
					if (cd4.getTag().equals(N)) {
						clear();
						cd4.setImageResource(R.drawable.cd4_p);
						cd4.setTag(P);
						xzcdh = 4;
					} else {
						clear();
					}
					break;
				default:
					break;
			}
		}
	}

}
