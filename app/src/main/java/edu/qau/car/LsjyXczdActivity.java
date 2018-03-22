package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.qau.car.bean.Cjlb;

public class LsjyXczdActivity extends Activity {

    private TextView userInfo;
    private boolean isSaveData = false;
    
    private RadioGroup xczdwdx;
    private RadioGroup xclszdpd;
    private EditText xczdcsd;
    private EditText xckzmfdd;
    private EditText xckzzdjl;
    private EditText xczdxtsj;
    
    private Cjlb cjlb;

    private final String JYXM = "R1";
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
            "yyyy/MM/dd hh:mm:ss");

    private final String KSZPZL = "0341";
    private final String JSZPZL = "0343";
    
    private String xczdwdxChecked = "1"  ;
    private String xclszdpdChecked = "1" ;
    private static final int CONFIRM = 1002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsjy_xczd);

        xckzmfdd = (EditText) findViewById(R.id.xckzmfdd);
        xczdcsd = (EditText) findViewById(R.id.xczdcsd);
        xckzzdjl = (EditText) findViewById(R.id.xckzzdjl);
        xczdxtsj = (EditText) findViewById(R.id.xczdxtsj);

        xckzmfdd.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(1)});
        xczdxtsj.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        
        userInfo = (TextView) findViewById(R.id.user_info);
        userInfo.setText(PreferencesUtils.getUserLsyInfo(this));
        cjlb = getIntent().getParcelableExtra("bean");

        xczdwdx = (RadioGroup) findViewById(R.id.xczdwdx);
        xczdwdx.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(xczdwdx.getCheckedRadioButtonId());
                xczdwdxChecked = rb.getTag().toString();
            }
        });

        xclszdpd = (RadioGroup) findViewById(R.id.xclszdpd);
        xclszdpd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(xclszdpd.getCheckedRadioButtonId());
                xclszdpdChecked = rb.getTag().toString();
            }
        });
        
    }

    public String getEditText(EditText editText){
        return editText.getText().toString();
    }
    
    
    public void back(View view) {
        finish();
    }

    public void jsjy(View view) {
        if(isSaveData){
            Intent intent = new Intent(LsjyXczdActivity.this,
                    ConfirmActivity.class);
            intent.putExtra("message",
                    " 是否结束本车辆的校验？");
            startActivityForResult(intent, CONFIRM);
        }else{
            Toast.makeText(LsjyXczdActivity.this,"请先提交数据",Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONFIRM && resultCode == Activity.RESULT_OK) {
            jsjyIntf();
        }
    }
    
    public void ckzp(View view){
        PzActivity.actionStart(LsjyXczdActivity.this, cjlb,"");
    }
    
    public void tj(View view) {
        
        if(validate()){
            tjIntf();
        }
        
     }
    
    public boolean validate(){

//        if(TextUtils.isEmpty(xczdcsd.getText().toString())){
//            Toast.makeText(LsjyXczdActivity.this,"请输入行车制动初速度",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if(TextUtils.isEmpty(xckzmfdd.getText().toString())){
//            Toast.makeText(LsjyXczdActivity.this,"请输入行车制动MFDD",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if(TextUtils.isEmpty(xckzzdjl.getText().toString())){
//            Toast.makeText(LsjyXczdActivity.this,"请输入行车空载制动距离",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if(TextUtils.isEmpty(xczdxtsj.getText().toString())){
//            Toast.makeText(LsjyXczdActivity.this,"请输入行车制动协调时间",Toast.LENGTH_SHORT).show();
//            return false;
//        }
// 
//        if(TextUtils.isEmpty(xczdwdxChecked)){
//            Toast.makeText(LsjyXczdActivity.this,"请选择行车制动稳定性",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if(TextUtils.isEmpty(xclszdpdChecked)){
//            Toast.makeText(LsjyXczdActivity.this,"请选择行车路试制动判定",Toast.LENGTH_SHORT).show();
//            return false;
//        }
        
        return true;
    }
    
     
     
     
    public void jykspz(View view) {
        jykspzIntf();
    }
    
    public void jyjspz(View view) {
        jyjspzIntf();
    }
    
    
    public void jykspzIntf(){
        new XmlAsyncTask().execute(new XmlCallback() {
            String foramt = "<?xml version='1.0' encoding='GBK'?><root><vehispara><jylsh>%s</jylsh><jcxdh>%s</jcxdh><fx>0</fx><jyjgbh>%s</jyjgbh><jycs>%s</jycs><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jyxm>%s</jyxm><zpzl>%s</zpzl></vehispara></root>";
            @Override
            public String getXml() {
                String hpzl = cjlb.getHpzlNum() + "";
                if (hpzl.length() == 1) {
                    hpzl = "0" + hpzl;
                }
                User user = PreferencesUtils.getUser(LsjyXczdActivity.this);
                String xml = String.format(foramt, cjlb.getJylsh(),
                        cjlb.getXzcdh(), user.getJCZBH(),   cjlb.getJycs(),hpzl,cjlb.getHphm(),cjlb.getClsbdh(), JYXM,KSZPZL
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
                Toast.makeText(LsjyXczdActivity.this, message, Toast.LENGTH_LONG)
                        .show();
            }
            
        });
    }

    public void jyjspzIntf(){
        new XmlAsyncTask().execute(new XmlCallback() {

            String foramt = "<?xml version='1.0' encoding='GBK'?><root><vehispara><jylsh>%s</jylsh><jcxdh>%s</jcxdh><fx>1</fx><jyjgbh>%s</jyjgbh><jycs>%s</jycs><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh><jyxm>%s</jyxm><zpzl>%s</zpzl></vehispara></root>";
            
            @Override
            public String getXml() {

                String hpzl = cjlb.getHpzlNum() + "";
                if (hpzl.length() == 1) {
                    hpzl = "0" + hpzl;
                }

                User user = PreferencesUtils.getUser(LsjyXczdActivity.this);
                String xml = String.format(foramt, cjlb.getJylsh(),
                        cjlb.getXzcdh(), user.getJCZBH(),   cjlb.getJycs(),hpzl,cjlb.getHphm(),cjlb.getClsbdh(), JYXM,JSZPZL
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
                Toast.makeText(LsjyXczdActivity.this, message, Toast.LENGTH_LONG)
                        .show();
            }
 
        });
    }

    public void tjIntf(){
        new XmlAsyncTask().execute(new XmlCallback() {

            String foramt = "<?xml version='1.0' encoding='GBK'?><root><vehispara>" +
                    "<jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs>" +
                    "<jyxm>%s</jyxm><hpzl>%s</hpzl><hphm>%s</hphm><clsbdh>%s</clsbdh>" +
                    "<lsy>%s</lsy>" +
                    "<lsjg>%s</lsjg>" +
                    "<zdcsd>%s</zdcsd>" +
                    "<zdxtsj>%s</zdxtsj>" +
                    "<zdwdx>%s</zdwdx>" +
                    "<xckzzdjl>%s</xckzzdjl>" +
                    "<xckzmfdd>%s</xckzmfdd>" +
                    "<lszdpd>%s</lszdpd>"+
                    "</vehispara></root>";
            @Override
            public String getXml() {
                String hpzl = cjlb.getHpzlNum() + "";
                if (hpzl.length() == 1) {
                    hpzl = "0" + hpzl;
                }
                User user = PreferencesUtils.getUser(LsjyXczdActivity.this);
                String xml = String.format(foramt, cjlb.getJylsh(), user.getJCZBH(),
                        cjlb.getXzcdh(),   cjlb.getJycs(),JYXM,hpzl,cjlb.getHphm(),cjlb.getClsbdh(),user.getXM() ,xclszdpdChecked,
                        getEditText(xczdcsd),getEditText(xczdxtsj),xczdwdxChecked,getEditText(xckzzdjl),getEditText(xckzmfdd),xclszdpdChecked
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
                Toast.makeText(LsjyXczdActivity.this, message, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public String getPara() {
                return "WriteXmlDoc";
            }
        });
    }

    public void jsjyIntf(){
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
                
                User user = PreferencesUtils.getUser(LsjyXczdActivity.this);
                String xml = String.format(foramt, cjlb.getJylsh(),
                        user.getJCZBH(),  cjlb.getXzcdh(), cjlb.getJycs(),JYXM, hpzl,
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
                    Intent i = new Intent(LsjyXczdActivity.this,LsjyZjmActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("flag","R1");
                    startActivity(i);
                    finish();
                } else {
                }
                Toast.makeText(LsjyXczdActivity.this, message, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public String getPara() {
                return "WriteXmlDoc";
            }
        });
    }
    
}
