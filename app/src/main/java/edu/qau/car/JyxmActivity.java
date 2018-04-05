package edu.qau.car;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.angmarch.views.NiceSpinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import edu.qau.car.bean.Cjlb;
import edu.qau.car.bean.Jyxm;
import edu.qau.car.bean.Wgjyxm;

public class JyxmActivity extends Activity implements OnClickListener {

    private ImageButton zzs1;
    private ImageButton zzs2;
    private ImageButton zzs3;
    private ImageButton zzs4;
    private ImageButton zzs5;
    private ImageButton zzs6;
    private ImageButton qdxs1;
    private ImageButton qdxs2;
    private ImageButton qdxs3;
    private ImageButton qdxs4;
    private ImageButton qdxs5;
    private ImageButton zczs1;
    private ImageButton zczs2;
    private ImageButton zczs3;
    private ImageButton zczs4;
    private ImageButton zczs5;
    private ImageButton zczs6;
    private ImageButton zczw1;
    private ImageButton zczw2;
    private ImageButton zczw3;
    private ImageButton zczw4;
    private ImageButton zczw5;

    private int zzs;
    private int qdxs;
    private int zczs;
    private int zczw;
    private EditText mEt_qdxs;
    private EditText mEt_zczw;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zzs1:
                clear(zzs1, zzs2, zzs3, zzs4, zzs5, zzs6);
                zzs1.setImageResource(R.drawable.yi_p);
                zzs = 1;
                break;
            case R.id.zzs2:
                clear(zzs1, zzs2, zzs3, zzs4, zzs5, zzs6);
                zzs2.setImageResource(R.drawable.er_p);
                zzs = 2;
                break;
            case R.id.zzs3:
                clear(zzs1, zzs2, zzs3, zzs4, zzs5, zzs6);
                zzs3.setImageResource(R.drawable.san_p);
                zzs = 3;
                break;
            case R.id.zzs4:
                clear(zzs1, zzs2, zzs3, zzs4, zzs5, zzs6);
                zzs4.setImageResource(R.drawable.si_p);
                zzs = 4;
                break;
            case R.id.zzs5:
                clear(zzs1, zzs2, zzs3, zzs4, zzs5, zzs6);
                zzs5.setImageResource(R.drawable.wu_p);
                zzs = 5;
                break;
            case R.id.zzs6:
                clear(zzs1, zzs2, zzs3, zzs4, zzs5, zzs6);
                zzs6.setImageResource(R.drawable.liu_p);
                zzs = 6;
                break;

//            case R.id.zczw1:
//                clear(zczw1, zczw2, zczw3, zczw4, zczw5);
//                zczw1.setImageResource(R.drawable.yi_p);
//                zczw = 1;
//                break;
//            case R.id.zczw2:
//                clear(zczw1, zczw2, zczw3, zczw4, zczw5);
//                zczw2.setImageResource(R.drawable.er_p);
//                zczw = 2;
//                break;
//            case R.id.zczw3:
//                clear(zczw1, zczw2, zczw3, zczw4, zczw5);
//                zczw3.setImageResource(R.drawable.san_p);
//                zczw = 3;
//                break;
//            case R.id.zczw4:
//                clear(zczw1, zczw2, zczw3, zczw4, zczw5);
//                zczw4.setImageResource(R.drawable.si_p);
//                zczw = 4;
//                break;
//            case R.id.zczw5:
//                clear(zczw1, zczw2, zczw3, zczw4, zczw5);
//                zczw5.setImageResource(R.drawable.wu_p);
//                zczw = 5;
//                break;
            case R.id.zczs1:
                clear(zczs1, zczs2, zczs3, zczs4, zczs5, zczs6);
                zczs1.setImageResource(R.drawable.yi_p);
                zczs = 1;
                break;
            case R.id.zczs2:
                clear(zczs1, zczs2, zczs3, zczs4, zczs5, zczs6);
                zczs2.setImageResource(R.drawable.er_p);
                zczs = 2;
                break;
            case R.id.zczs3:
                clear(zczs1, zczs2, zczs3, zczs4, zczs5, zczs6);
                zczs3.setImageResource(R.drawable.san_p);
                zczs = 3;
                break;
            case R.id.zczs4:
                clear(zczs1, zczs2, zczs3, zczs4, zczs5, zczs6);
                zczs4.setImageResource(R.drawable.si_p);
                zczs = 4;
                break;
            case R.id.zczs5:
                clear(zczs1, zczs2, zczs3, zczs4, zczs5, zczs6);
                zczs5.setImageResource(R.drawable.wu_p);
                zczs = 5;
                break;
            case R.id.zczs6:
                clear(zczs1, zczs2, zczs3, zczs4, zczs5, zczs6);
                zczs6.setImageResource(R.drawable.liu_p);
                zczs = 6;
                break;
//            case R.id.qdxs1:
//                clear(qdxs1, qdxs2, qdxs3, qdxs4, qdxs5);
//                qdxs1.setImageResource(R.drawable.yi_p);
//                qdxs = 1;
//                break;
//            case R.id.qdxs2:
//                clear(qdxs1, qdxs2, qdxs3, qdxs4, qdxs5);
//                qdxs2.setImageResource(R.drawable.er_p);
//                qdxs = 2;
//                break;
//            case R.id.qdxs3:
//                clear(qdxs1, qdxs2, qdxs3, qdxs4, qdxs5);
//                qdxs3.setImageResource(R.drawable.san_p);
//                qdxs = 3;
//                break;
//            case R.id.qdxs4:
//                clear(qdxs1, qdxs2, qdxs3, qdxs4, qdxs5);
//                qdxs4.setImageResource(R.drawable.si_p);
//                qdxs = 4;
//                break;
//            case R.id.qdxs5:
//                clear(qdxs1, qdxs2, qdxs3, qdxs4, qdxs5);
//                qdxs5.setImageResource(R.drawable.wu_p);
//                qdxs = 5;
//                break;
            default:
                break;
        }
    }

    private NiceSpinner zdly;
    private LinkedList<Jyxm> zdlyList = new LinkedList<Jyxm>();

    private NiceSpinner qzdz;
    private LinkedList<Jyxm> qzdzList = new LinkedList<Jyxm>();

    private NiceSpinner qzsl;
    private LinkedList<String> qzslList = new LinkedList<String>(Arrays.asList(
            "1", "2", "3", "4", "5", "6","--"));

    private NiceSpinner syxz;
    private LinkedList<Jyxm> syxzList = new LinkedList<Jyxm>();

    private Type type = new TypeToken<LinkedList<Jyxm>>() {
    }.getType();
    private Gson gson = new Gson();

    public void clear(ImageButton yi, ImageButton er, ImageButton san,
                      ImageButton si, ImageButton wu) {
        wu.setImageResource(R.drawable.wu_n);
        si.setImageResource(R.drawable.si_n);
        san.setImageResource(R.drawable.san_n);
        er.setImageResource(R.drawable.er_n);
        yi.setImageResource(R.drawable.yi_n);
    }

    public void clear(ImageButton yi, ImageButton er, ImageButton san,
                      ImageButton si, ImageButton wu, ImageButton liu) {
        clear(yi, er, san, si, wu);
        liu.setImageResource(R.drawable.liu_n);

    }

    public void next(View view) {

        new XmlAsyncTask().execute(new XmlCallback() {

            String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><hpzl>%s</hpzl><hphm>%s</hphm><wgjcjyy>%s</wgjcjyy><wgjcjyysfzh>%s</wgjcjyysfzh><lcbds>%s</lcbds><zzs>%s</zzs><qdxs>%s</qdxs><zczs>%s</zczs><zczw>%s</zczw><zdly>%s</zdly><qzdz>%s</qzdz><qzsl>%s</qzsl><syxz>%s</syxz><ygddtz>%s</ygddtz><dlxj>%s</dlxj><mz>%s</mz><szxz>%s</szxz></vehispara></root>";

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

                User user = PreferencesUtils.getUser(JyxmActivity.this);

                String zdly_dmz = zdlyList.get(zdly.getSelectedIndex()).getDMSM1();
                if (zdly_dmz.contains("-")) zdly_dmz = "";
                else zdly_dmz = zdlyList.get(zdly.getSelectedIndex()).getDMZ();

                String qzdz_dmz = qzdzList.get(qzdz.getSelectedIndex()).getDMSM1();
                if (qzdz_dmz.contains("-")) qzdz_dmz = "";
                else qzdz_dmz = qzdzList.get(qzdz.getSelectedIndex()).getDMZ();

                String qzsl_dmz = qzslList.get(qzsl.getSelectedIndex());
                if (qzsl_dmz.contains("-")) qzsl_dmz = "";

                String syxz_dmz = syxzList.get(syxz.getSelectedIndex()).getDMSM1();
                if (syxz_dmz.contains("-")) syxz_dmz= "";
                else syxz_dmz = syxzList.get(syxz.getSelectedIndex()).getDMZ();

                String xml = String.format(foramt,
                        cjlb.getJylsh(),
                        user.getJCZBH(), hpzl, cjlb.getHphm(),
                        user.getXM(),
                        user.getSFZMHM(),
                        lcbds.getText().toString(),
                        zzs, qdxs, zczs, zczw,
                        zdly_dmz,
                        qzdz_dmz,
                        qzsl_dmz,
                        syxz_dmz,
                        ygddtz.isChecked() ? 1 : 0, dlxj.isChecked() ? 1 : 0,
                        mz.isChecked() ? 1 : 0, szxz.isChecked() ? 1 : 0);
                Log.w("getXml--car", xml);
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
                return "17F26";
            }

            @Override
            public void callback(String obj) {

                String code = XmlUtils.getValue(obj, "code");
                if ("1".equals(code)) {
                    Log.w("callback--car", flag + "");
                    if (flag == 0) {
                        PzActivity.actionStart(JyxmActivity.this, cjlb);
                    } else {
                        TjjgActivity.actionStart(JyxmActivity.this, cjlb);
                    }
                } else {
                    String message = XmlUtils.getValue(obj, "message");
                    Toast.makeText(JyxmActivity.this, message,
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public String getPara() {
                return "WriteXmlDoc";
            }
        });
    }

    public static void actionStart(final Context context, final Cjlb cjlb,
                                   final int flag) {
        new WsAsyncTask().execute(new WsCallback() {

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
            public String getXml() {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(context);
                String format = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jyjgbh>%s</jyjgbh><sfzh>%s</sfzh><loginid>%s</loginid></vehispara></root>";
                User user = PreferencesUtils.getUser(context);
                String xml = String.format(format, user.getJCZBH(),
                        user.getSFZMHM(),
                        sharedPreferences.getString("loginid", ""));
                Log.w("car", xml);

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
                return "17F24";
            }

            @Override
            public void callback(JSONObject obj) {
                try {
                    if (obj != null && obj.getBoolean("ok")) {
                        JSONArray zdly = obj.getJSONArray("Table");
                        JSONArray qzdz = obj.getJSONArray("Table1");
                        JSONArray syxz = obj.getJSONArray("Table2");

                        Intent intent = new Intent(context, JyxmActivity.class);
                        intent.putExtra("bean", cjlb);
                        intent.putExtra("zdly", zdly.toString());
                        intent.putExtra("qzdz", qzdz.toString());
                        intent.putExtra("syxz", syxz.toString());
                        intent.putExtra("flag", flag);


                        getInfo(context,cjlb,intent);

//                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "访问网络失败", Toast.LENGTH_LONG)
                                .show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "访问网络失败", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public static void getInfo(final Context context, final Cjlb cjlb , final Intent intent) {
        new WsAsyncTask().execute(new WsCallback() {

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
            public String getXml() {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(context);
                String format = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jyxm>%s</jyxm></vehispara></root>";
                User user = PreferencesUtils.getUser(context);
                String xml = String.format(format, cjlb.getJylsh(),
                        user.getJCZBH(),
                        PreferencesUtils.getJyxm(context));
                Log.w("car", xml);

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
                return "17F28";
            }

            @Override
            public void callback(JSONObject obj) {
                try {
                    if (obj != null && obj.getBoolean("ok")) {
                        JSONArray info = obj.getJSONArray("Table");
                        if(info!=null && info.length() > 0 ){
                            intent.putExtra("info", info.get(0).toString());
                            context.startActivity(intent);
                        }
                    } else {
                        Toast.makeText(context, "访问网络失败", Toast.LENGTH_LONG)
                                .show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "访问网络失败", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void back(View view) {
        finish();
    }

    private TextView userInfo;
    private Cjlb cjlb;
    private EditText lcbds;
    private CheckBox ygddtz;
    private CheckBox dlxj;
    private CheckBox mz;
    private CheckBox szxz;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jyxm);

        lcbds = (EditText) findViewById(R.id.lcbds);
        ygddtz = (CheckBox) findViewById(R.id.ygddtz);
        dlxj = (CheckBox) findViewById(R.id.dlxj);
        mz = (CheckBox) findViewById(R.id.mz);
        szxz = (CheckBox) findViewById(R.id.szxz);

        cjlb = getIntent().getParcelableExtra("bean");
        flag = getIntent().getIntExtra("flag", 0);

        userInfo = (TextView) findViewById(R.id.user_info);
        userInfo.setText(PreferencesUtils.getUserInfo(this));

        Jyxm jyxm = new Jyxm();
        jyxm.setDMSM1("--");
        zdlyList = gson.fromJson(getIntent().getStringExtra("zdly"), type);
        zdlyList.add(jyxm);
        qzdzList = gson.fromJson(getIntent().getStringExtra("qzdz"), type);
        qzdzList.add(jyxm);
        syxzList = gson.fromJson(getIntent().getStringExtra("syxz"), type);
        syxzList.add(jyxm);

        zdly = (NiceSpinner) findViewById(R.id.zdly);
        zdly.setTextColor(Color.BLUE);
        zdly.attachDataSource(zdlyList);
        for (int i = 0; i < zdlyList.size(); i++) {
            if (zdlyList.get(i).getDMSM1().equals("液压制动")) {
                zdly.setSelectedIndex(i);
                break;
            }
        }

        qzdz = (NiceSpinner) findViewById(R.id.qzdz);
        qzdz.setTextColor(Color.BLUE);
        qzdz.attachDataSource(qzdzList);
        for (int i = 0; i < qzdzList.size(); i++) {
            if (qzdzList.get(i).getDMSM1().equals("二灯远近光")) {
                qzdz.setSelectedIndex(i);
                break;
            }

        }

        qzsl = (NiceSpinner) findViewById(R.id.qzsl);
        qzsl.setTextColor(Color.BLUE);
        qzsl.attachDataSource(qzslList);

        syxz = (NiceSpinner) findViewById(R.id.syxz);
        syxz.setTextColor(Color.BLUE);
        syxz.attachDataSource(syxzList);
        for (int i = 0; i < syxzList.size(); i++) {
            if (syxzList.get(i).getDMSM1().equals("非营运")) {
                syxz.setSelectedIndex(i);
                break;
            }
        }

        Wgjyxm info =  gson.fromJson(getIntent().getStringExtra("info"), Wgjyxm.class);

        zzs1 = (ImageButton) findViewById(R.id.zzs1);
        zzs1.setOnClickListener(this);
        zzs2 = (ImageButton) findViewById(R.id.zzs2);
        zzs2.setOnClickListener(this);
        zzs3 = (ImageButton) findViewById(R.id.zzs3);
        zzs3.setOnClickListener(this);
        zzs4 = (ImageButton) findViewById(R.id.zzs4);
        zzs4.setOnClickListener(this);
        zzs5 = (ImageButton) findViewById(R.id.zzs5);
        zzs5.setOnClickListener(this);
        zzs6 = (ImageButton) findViewById(R.id.zzs6);
        zzs6.setOnClickListener(this);

//		qdxs1 = (ImageButton) findViewById(R.id.qdxs1);
//		qdxs1.setOnClickListener(this);
//		qdxs2 = (ImageButton) findViewById(R.id.qdxs2);
//		qdxs2.setOnClickListener(this);
//		qdxs3 = (ImageButton) findViewById(R.id.qdxs3);
//		qdxs3.setOnClickListener(this);
//		qdxs4 = (ImageButton) findViewById(R.id.qdxs4);
//		qdxs4.setOnClickListener(this);
//		qdxs5 = (ImageButton) findViewById(R.id.qdxs5);
//		qdxs5.setOnClickListener(this);

        mEt_qdxs = (EditText) findViewById(R.id.qdxs_et);
        mEt_qdxs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()))
                    qdxs = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        zczs1 = (ImageButton) findViewById(R.id.zczs1);
        zczs1.setOnClickListener(this);
        zczs2 = (ImageButton) findViewById(R.id.zczs2);
        zczs2.setOnClickListener(this);
        zczs3 = (ImageButton) findViewById(R.id.zczs3);
        zczs3.setOnClickListener(this);
        zczs4 = (ImageButton) findViewById(R.id.zczs4);
        zczs4.setOnClickListener(this);
        zczs5 = (ImageButton) findViewById(R.id.zczs5);
        zczs5.setOnClickListener(this);
        zczs6 = (ImageButton) findViewById(R.id.zczs6);
        zczs6.setOnClickListener(this);

//		zczw1 = (ImageButton) findViewById(R.id.zczw1);
//		zczw1.setOnClickListener(this);
//		zczw2 = (ImageButton) findViewById(R.id.zczw2);
//		zczw2.setOnClickListener(this);
//		zczw3 = (ImageButton) findViewById(R.id.zczw3);
//		zczw3.setOnClickListener(this);
//		zczw4 = (ImageButton) findViewById(R.id.zczw4);
//		zczw4.setOnClickListener(this);
//		zczw5 = (ImageButton) findViewById(R.id.zczw5);
//		zczw5.setOnClickListener(this);

        mEt_zczw = (EditText) findViewById(R.id.zczw_et);
        mEt_zczw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()))
                    zczw = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(info!=null){
            lcbds.setText(info.getLcbds());
            switch (info.getZzs()){
                case "1" :
                    zzs1.setImageResource(R.drawable.yi_p);
                    break;
                case "2":
                    zzs2.setImageResource(R.drawable.er_p);
                    break;
                case "3":
                    zzs3.setImageResource(R.drawable.san_p);
                    break;
                case "4":
                    zzs4.setImageResource(R.drawable.si_p);
                    break;
                case "5":
                    zzs5.setImageResource(R.drawable.wu_p);
                    break;
                case "6":
                    zzs6.setImageResource(R.drawable.liu_p);
                    break;

            }

            mEt_qdxs.setText(info.getQdxs());

            switch (info.getZczs()){
                case "1" :
                    zczs1.setImageResource(R.drawable.yi_p);
                    break;
                case "2":
                    zczs2.setImageResource(R.drawable.er_p);
                    break;
                case "3":
                    zczs3.setImageResource(R.drawable.san_p);
                    break;
                case "4":
                    zczs4.setImageResource(R.drawable.si_p);
                    break;
                case "5":
                    zczs5.setImageResource(R.drawable.wu_p);
                    break;
                case "6":
                    zczs6.setImageResource(R.drawable.liu_p);
                    break;

            }

            mEt_zczw.setText(info.getZczw());

            for (int i = 0; i < zdlyList.size(); i++) {
                if (zdlyList.get(i).getDMZ().equals(info.getZdly())) {
                    zdly.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < qzdzList.size(); i++) {
                if (qzdzList.get(i).getDMZ().equals(info.getQzdz())) {
                    qzdz.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < qzslList.size(); i++) {
                if (qzslList.get(i).equals(info.getQzsl())) {
                    qzsl.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < syxzList.size(); i++) {
                if (syxzList.get(i).getDMZ().equals(info.getSyxz())) {
                    syxz.setSelectedIndex(i);
                    break;
                }
            }

            if("1".equals(info.getYgddtz())){
                ygddtz.setChecked(true);
            }

            if("1".equals(info.getDlxj())){
                dlxj.setChecked(true);
            }

            if("1".equals(info.getMz())){
                mz.setChecked(true);
            }

            if("1".equals(info.getSzxz())){
                szxz.setChecked(true);
            }


        }
    }

}
