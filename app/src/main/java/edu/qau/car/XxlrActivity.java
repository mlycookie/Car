package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;

import edu.qau.car.bean.Jyxm;

public class XxlrActivity extends Activity {

    TextView tvTitle;
    ImageView fhIv;
    ImageView tcIv;

    NiceSpinner spinnerHpzl;
    NiceSpinner spinnerClzt;

    private LinkedList<String> hpzlList = new LinkedList<String>(Arrays.asList("大型汽车","小型车辆"));
    private LinkedList<String> clztList = new LinkedList<String>(Arrays.asList("已解锁","锁定"));

    EditText etHphm;
    EditText etClsbdh;
    EditText etSyr;
    EditText etBz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xxlr);

        tvTitle = (TextView) findViewById(R.id.user_info);
        tvTitle.setText("信息录入");
        fhIv = (ImageView) findViewById(R.id.fh);
        tcIv = (ImageView) findViewById(R.id.tc);
        fhIv.setVisibility(View.GONE);
        tcIv.setVisibility(View.VISIBLE);

        etHphm = (EditText) findViewById(R.id.et_hphm);
        etClsbdh = (EditText) findViewById(R.id.et_clsbdh);
        etSyr = (EditText) findViewById(R.id.et_syr);
        etBz = (EditText) findViewById(R.id.et_bz);

        spinnerClzt = (NiceSpinner) findViewById(R.id.spinner_clzt);
        spinnerClzt.setTextColor(Color.BLUE);
        spinnerClzt.attachDataSource(clztList);
        spinnerHpzl = (NiceSpinner) findViewById(R.id.spinner_hpzl);
        spinnerHpzl.setTextColor(Color.BLUE);
        spinnerHpzl.attachDataSource(hpzlList);


    }

    public void back(View view){
        Intent intent = new Intent(this,DlActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    public boolean validate(){

        if(TextUtils.isEmpty(etHphm.getText().toString())){
            Toast.makeText(XxlrActivity.this,"请输入号牌号码",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(etSyr.getText().toString())){
            Toast.makeText(XxlrActivity.this,"请输入所有人",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void  next(View view){

        if(!validate()){
            return;
        }

        new XmlAsyncTask().execute(new XmlCallback() {

            String format = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><hphm>%s</hphm><hpzl>%s</hpzl><clsbdh>%s</clsbdh><syr>%s</syr><zt>%s</zt><remark>%s</remark></vehispara></root>";

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
            public String getXml() {

                String hpzl = "0" + (spinnerHpzl.getSelectedIndex()+ 1 );
                int zt = spinnerClzt.getSelectedIndex();
                String xml = String.format(format,etHphm.getText().toString(),hpzl,etClsbdh.getText().toString(),etSyr.getText().toString(),zt,etBz.getText().toString());

                return xml;
            }

            @Override
            public String getXtlb() {
                return "17";
            }

            @Override
            public String getJkxlh() {
                return "00000";
            }

            @Override
            public String getJkid() {
                return "17F29";
            }

            @Override
            public String getPara() {
                return "WriteXmlDoc";
            }

            @Override
            public void callback(String obj) {
                String code = XmlUtils.getValue(obj,"code");
//                if("1".equals(code)){
//
//                }else{
                    String message = XmlUtils.getValue(obj, "message");
                    Toast.makeText(XxlrActivity.this, message,
                            Toast.LENGTH_LONG).show();
//                }
            }
        });
    }


}
