package edu.qau.car;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.angmarch.views.NiceSpinner;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedList;

public class DlActivity extends Activity implements WsCallback {

    private String dlXml = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><loginid>%s</loginid><loginpwd>%s</loginpwd><rylb>%d</rylb></vehispara></root>";
    private EditText loginid;
    private EditText loginpwd;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

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
        String xml = String.format(dlXml, loginid.getText().toString(),
                loginpwd.getText().toString(), rylb[gw.getSelectedIndex()]);
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
        return "17F21";
    }

    @Override
    public void callback(JSONObject obj) {
        Log.w("dl", obj.toString());
        try {
            if (obj != null && obj.getBoolean("ok")) {
                JSONObject userJson = obj.getJSONArray("Table")
                        .getJSONObject(0);
                userJson.remove("RYLB");
                userJson.accumulate("RYLB", rylb[gw.getSelectedIndex()]);
                Log.w("car", userJson.toString());
                editor.putString("user", userJson.toString());
                editor.commit();

                if(7 == rylb[gw.getSelectedIndex()]){
                    Intent intent  = new Intent(this, XxlrActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent  = new Intent(this, ZjmActivity.class);
                    startActivity(intent);
                }

                
//                switch ( rylb[gw.getSelectedIndex()]){
//                    case 15:
//                        intent = new Intent(this, LsjyXmxzActivity.class);
//                        startActivity(intent);
//                        break;
//                    default:
//                        intent = new Intent(this, ZjmActivity.class);
//                        startActivity(intent);
//                        break;
//                }
                
            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_LONG).show();
            }
  
         } catch (Exception e) {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_LONG).show();
        } finally {
            loginpwd.setText("");
        }

    }

    public void dl(View view) {
        editor.putString("loginid", loginid.getText().toString());

        new WsAsyncTask().execute(this);

    }

    private NiceSpinner gw;
    private LinkedList<String> gwList = new LinkedList<String>(Arrays.asList(
            "车辆外观检验员", "底盘动态检验员", "车辆底盘检验员", "路试员","信息登录员"));
    private int[] rylb = new int[]{10, 11, 12, 15, 7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dl);

        gw = (NiceSpinner) findViewById(R.id.gw);
        gw.setTextColor(Color.BLUE);
        gw.attachDataSource(gwList);

        loginid = (EditText) findViewById(R.id.dl_loginid);
        loginpwd = (EditText) findViewById(R.id.dl_loginpwd);
        findViewById(R.id.dl_bt_set_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DlActivity.this, SetUrlActivity.class);
                startActivity(intent);
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        loginid.setText(sharedPreferences.getString("loginid", ""));
    }

}
