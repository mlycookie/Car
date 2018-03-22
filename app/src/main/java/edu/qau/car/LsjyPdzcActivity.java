package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.qau.car.bean.Cjlb;

public class LsjyPdzcActivity extends Activity {

    private TextView userInfo;
    private boolean isSaveData;

    private RadioGroup zczdpdGroup; 
    Chronometer timer;

    private Cjlb cjlb;
    int miss=0;
    private String zcpd ;
    private String zczdpdChecked = "1"; //默认合格
    
    private final String JYXM = "R2";
    private final String JYZPZL = "0345";
    private static final int CONFIRM = 1001;
    
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
            "yyyy/MM/dd hh:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsjy_pdzc);
        userInfo = (TextView) findViewById(R.id.user_info);
        userInfo.setText(PreferencesUtils.getUserLsyInfo(this));
        timer = (Chronometer) findViewById(R.id.timer);

        cjlb = getIntent().getParcelableExtra("bean");
        zcpd = getIntent().getStringExtra("zcpd");

        zczdpdGroup = (RadioGroup) findViewById(R.id.zczdpd_group);
        zczdpdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(zczdpdGroup.getCheckedRadioButtonId());
                zczdpdChecked = rb.getTag().toString();
            }
        });
    }

    public void back(View view) {
        finish();
    }
    
    public void ksjs(View view){
        timer.setFormat("%s");//设置显示的格式  
        timer.setBase(SystemClock.elapsedRealtime());//跳过已经记录的时间
        
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long s = SystemClock.elapsedRealtime() - chronometer.getBase();
                if (s > 60 * 1000 * 2)
                {
                    timer.setTextColor(Color.parseColor("#FF0000"));
                }
                timer.setText(FormatMiss(miss));
                miss ++;
            }
        });
        
        timer.start();
    }

    public String FormatMiss(int miss){
        String  mm = (miss % 3600)/60>9 ?(miss % 3600)/60+"":"0"+(miss % 3600)/60;
        String ss = (miss % 3600) % 60>9 ?(miss % 3600) % 60+"":"0"+(miss % 3600) % 60;
        return mm+"分:"+ss+"秒";
    }

    public void tzjs(View view){
        timer.stop();
        miss = 0;
        timer.setText(FormatMiss(miss));
        timer.setTextColor(Color.parseColor("#FFFFFF"));
    }
    
    public void jypz(View view){
        //zcpd  0-20%，1-15%
        new XmlAsyncTask().execute(new XmlCallback() {
            String foramt = "<?xml version='1.0' encoding='GBK'?><root><vehispara><jylsh>%s</jylsh><jcxdh>%s</jcxdh><fx>0</fx><zcpd>1</zcpd><jyjgbh>%s</jyjgbh><jycs>%s</jycs><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jyxm>%s</jyxm><zpzl>%s</zpzl></vehispara></root>";
            @Override
            public String getXml() {
                String hpzl = cjlb.getHpzlNum() + "";
                if (hpzl.length() == 1) {
                    hpzl = "0" + hpzl;
                }
                User user = PreferencesUtils.getUser(LsjyPdzcActivity.this);
                String xml = String.format(foramt, cjlb.getJylsh(),
                        cjlb.getXzcdh(),user.getJCZBH(), cjlb.getJycs(),hpzl,cjlb.getHphm(),cjlb.getClsbdh(), JYXM,JYZPZL
                );
                Log.w("car", xml);
                return xml;
            }
            @Override
            public String getXtlb() {
                return "17";
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
                return "17F73";
            }

            @Override
            public String getPara() {
                return "WriteXmlDoc";
            }

            @Override
            public void callback(String obj) {
                String code = XmlUtils.getValue(obj, "code");
                String message = XmlUtils.getValue(obj, "message");
                if ("1".equals(code)) {

                }
                Toast.makeText(LsjyPdzcActivity.this, message, Toast.LENGTH_LONG)
                        .show();
            }

        });
        
    }

    public void ckzp(View view){
        PzActivity.actionStart(LsjyPdzcActivity.this, cjlb ,zcpd);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONFIRM && resultCode == Activity.RESULT_OK) {
            jsjyIntf();
        }
    }
    
    public void jsjy(View view) {
        if(isSaveData){

            Intent intent = new Intent(LsjyPdzcActivity.this,
                    ConfirmActivity.class);
            intent.putExtra("message",
                    " 是否结束本车辆的校验？");
            startActivityForResult(intent, CONFIRM);
            
        }else{
            Toast.makeText(LsjyPdzcActivity.this,"请先提交数据",Toast.LENGTH_SHORT).show();
        }

    }

    public void jsjyIntf(){
        new XmlAsyncTask().execute(new XmlCallback() {

            String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs><jyxm>%s</jyxm><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jssj>%s</jssj><zcpd>%s</zcpd></vehispara></root>";

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

                User user = PreferencesUtils.getUser(LsjyPdzcActivity.this);
                String xml = String.format(foramt, cjlb.getJylsh(),
                        user.getJCZBH(),  cjlb.getXzcdh(), cjlb.getJycs(),JYXM, hpzl,
                        cjlb.getHphm(), cjlb.getClsbdh(),
                        simpleDateFormat2.format(new Date())
                        ,zcpd
                );
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
                    Intent i = new Intent(LsjyPdzcActivity.this,LsjyZjmActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("flag","R2");
                    startActivity(i);
                    finish();
                } else {
                }
                Toast.makeText(LsjyPdzcActivity.this, message, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public String getPara() {
                return "WriteXmlDoc";
            }
        });
    }
    
    public void tj(View view) {
        new XmlAsyncTask().execute(new XmlCallback() {

            String foramt = "<?xml version='1.0' encoding='GBK'?><root><vehispara>" +
                    "<jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs>" +
                    "<jyxm>%s</jyxm><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh>" +
                    "<lsy>%s</lsy>" +
                    "<zcpd>%s</zcpd>" +
                    "<lszczdpd>%s</lszczdpd>" +
                    "<lsjg>%s</lsjg>" +
                    "</vehispara></root>";
            @Override
            public String getXml() {
                String hpzl = cjlb.getHpzlNum() + "";
                if (hpzl.length() == 1) {
                    hpzl = "0" + hpzl;
                }
                User user = PreferencesUtils.getUser(LsjyPdzcActivity.this);
                String xml = String.format(foramt, cjlb.getJylsh(), user.getJCZBH(),
                        cjlb.getXzcdh(),   cjlb.getJycs(),JYXM,hpzl,cjlb.getHphm(),cjlb.getClsbdh(),user.getXM() ,zcpd, zczdpdChecked, zczdpdChecked
                );
                Log.w("car", xml);

                return xml;
            }

            @Override
            public String getXtlb() {
                return "17";
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
                return "17F54";
            }

            @Override
            public void callback(String obj) {

                String code = XmlUtils.getValue(obj, "code");
                String message = XmlUtils.getValue(obj, "message");
                if ("1".equals(code)) {
                    isSaveData = true;
                } else {
                }
                Toast.makeText(LsjyPdzcActivity.this, message, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public String getPara() {
                return "WriteXmlDoc";
            }
        });
    }
    
    
    
    
}
