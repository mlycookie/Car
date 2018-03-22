package edu.qau.car;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2018/2/1.
 ****************************************/

public class SetUrlActivity extends Activity {

    private EditText mEtUrl;
    private RadioGroup radiogroup;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_url);
        findViewById(R.id.set_url_tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEtUrl = (EditText) findViewById(R.id.seturl_et);
        mEtUrl.setText(AppURL.BASE_URL);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SetUrlActivity.this);

        //初始化radiogroup
        String second = sharedPreferences.getString(AppURL.SECOND_URL_KEY, AppURL.SECOND_PATH_1);
        if (second.contains("ref")) {
            radiogroup.check(R.id.path_2);
        } else {
            radiogroup.check(R.id.path_1);
        }

        findViewById(R.id.set_url_bt_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mEtUrl.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(SetUrlActivity.this, "地址不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    AppURL.BASE_URL = url;
                    sharedPreferences.edit().putString(AppURL.BASE_URL_KEY, url).apply();
                    Toast.makeText(SetUrlActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.path_1) {
                    sharedPreferences.edit().putString(AppURL.SECOND_URL_KEY, AppURL.SECOND_PATH_1).apply();
                    AppURL.SECOND_URL = AppURL.SECOND_PATH_1;
                } else if (checkedId == R.id.path_2) {
                    sharedPreferences.edit().putString(AppURL.SECOND_URL_KEY, AppURL.SECOND_PATH_2).apply();
                    AppURL.SECOND_URL = AppURL.SECOND_PATH_2;
                }
                Toast.makeText(SetUrlActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
