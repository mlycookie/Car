package edu.qau.car;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import edu.qau.car.bean.Cjlb;

public class PzActivity extends Activity {

    private File file;
    private Uri uri;
    private static final int TAKE_PHOTO = 1;
    private static final int CONFIRM = 2;
    private static final int MY_PERMISSIONS_REQUEST = 5;
    
    public static final int SNAP_VELOCITY = 200;
    private int screenWidth;
    private View content1;
    private View content2;
    private View content3;
    private LinearLayout.LayoutParams content1Params;
    private LinearLayout.LayoutParams content3Params;
    private int leftEdge;
    private int rightEdge = 0;
    private VelocityTracker mVelocityTracker;
    private float xDown;
    private float xMove;
    private boolean isContent2Visible = false;
    private float xUp;
    private static boolean isShowPhoto;
    
    private LinearLayout bt1;
    private LinearLayout pz1;

    private LinearLayout bt2;
    private LinearLayout pz2;

    private LinearLayout bt3;
    private LinearLayout pz3;

    private PzItem item;

    private Cjlb cjlb;
    private ViewPager mViewpager;
    private ArrayList<View> mPageList;
    private ImageButton next;
    
    public void back(View view) {
        finish();
    }

    public void tj(View view) {
        /*
         * if (bundle.size() == list.size()) { Intent intent = new Intent(this,
		 * ConfirmActivity.class); intent.putExtra("message", "拍照完成了吗？");
		 * startActivityForResult(intent, CONFIRM); } else {
		 * Toast.makeText(this, "请长传全部图片", Toast.LENGTH_LONG).show(); }
		 */

        Intent intent = new Intent(this, ConfirmActivity.class);
        intent.putExtra("message", "拍照完成了吗？");
        startActivityForResult(intent, CONFIRM);

    }

    private ArrayList<Holder> list = new ArrayList<Holder>();
    private static HashMap<String, String> map = new HashMap<String, String>();

    public static class Holder {
        int n;
        int p;
        String name;

        public Holder(String name, int n, int p) {
            this.n = n;
            this.p = p;
            this.name = name;
        }
    }

    public static ArrayList<Holder> xm = new ArrayList<PzActivity.Holder>();

    static {
        xm.add(new Holder("0111", R.drawable.d0111_n, R.drawable.d0111_p));
        xm.add(new Holder("0112", R.drawable.d0112_n, R.drawable.d0112_p));
        xm.add(new Holder("0113", R.drawable.d0113_n, R.drawable.d0113_p));
        xm.add(new Holder("0115", R.drawable.d0115_n, R.drawable.d0115_p));
        xm.add(new Holder("0116", R.drawable.d0116_n, R.drawable.d0116_p));
        xm.add(new Holder("0117", R.drawable.d0117_n, R.drawable.d0117_p));
        xm.add(new Holder("0118", R.drawable.d0118_n, R.drawable.d0118_p));
        xm.add(new Holder("0119", R.drawable.d0119_n, R.drawable.d0119_p));
        xm.add(new Holder("0120", R.drawable.d0120_n, R.drawable.d0120_p));
        xm.add(new Holder("0121", R.drawable.d0121_n, R.drawable.d0121_p));
        xm.add(new Holder("0126", R.drawable.d0126_n, R.drawable.d0126_p));
        xm.add(new Holder("0127", R.drawable.d0127_n, R.drawable.d0127_p));
        xm.add(new Holder("0128", R.drawable.d0128_n, R.drawable.d0128_p));
        xm.add(new Holder("0130", R.drawable.d0130_n, R.drawable.d0130_p));
        xm.add(new Holder("0132", R.drawable.d0132_n, R.drawable.d0132_p));
        xm.add(new Holder("0133", R.drawable.d0133_n, R.drawable.d0133_p));
        xm.add(new Holder("0134", R.drawable.d0134_n, R.drawable.d0134_p));
        xm.add(new Holder("0135", R.drawable.d0135_n, R.drawable.d0135_p));
        xm.add(new Holder("0136", R.drawable.d0136_n, R.drawable.d0136_p));
        xm.add(new Holder("0138", R.drawable.d0138_n, R.drawable.d0138_p));
        xm.add(new Holder("0139", R.drawable.d0139_n, R.drawable.d0139_p));
        xm.add(new Holder("0140", R.drawable.d0140_n, R.drawable.d0140_p));
        xm.add(new Holder("0141", R.drawable.d0141_n, R.drawable.d0141_p));
        xm.add(new Holder("0154", R.drawable.d0154_n, R.drawable.d0154_p));
        xm.add(new Holder("0155", R.drawable.d0155_n, R.drawable.d0155_p));
        xm.add(new Holder("0156", R.drawable.d0156_n, R.drawable.d0156_p));
        xm.add(new Holder("0157", R.drawable.d0157_n, R.drawable.d0157_p));
        xm.add(new Holder("0158", R.drawable.d0158_n, R.drawable.d0158_p));
        xm.add(new Holder("0159", R.drawable.d0159_n, R.drawable.d0159_p));
        xm.add(new Holder("0160", R.drawable.d0160_n, R.drawable.d0160_p));
        xm.add(new Holder("0161", R.drawable.d0161_n, R.drawable.d0161_p));
        xm.add(new Holder("0163", R.drawable.d0163_n, R.drawable.d0163_p));
        xm.add(new Holder("0171", R.drawable.d0171_n, R.drawable.d0171_p));
        xm.add(new Holder("0172", R.drawable.d0172_n, R.drawable.d0172_p));
        xm.add(new Holder("0173", R.drawable.d0173_n, R.drawable.d0173_p));
        xm.add(new Holder("0174", R.drawable.d0174_n, R.drawable.d0174_p));
        xm.add(new Holder("0175", R.drawable.d0175_n, R.drawable.d0175_p));
        xm.add(new Holder("0176", R.drawable.d0176_n, R.drawable.d0176_p));

        xm.add(new Holder("0323", R.drawable.d0323_n, R.drawable.d0323_p));
        xm.add(new Holder("0344", R.drawable.d0344_n, R.drawable.d0344_p));
        xm.add(new Holder("0342", R.drawable.d0342_n, R.drawable.d0342_p));

        xm.add(new Holder("0341", R.drawable.d0341_n, R.drawable.d0341_p));
        xm.add(new Holder("0343", R.drawable.d0343_n, R.drawable.d0343_p));
        xm.add(new Holder("0345", R.drawable.d0345_n, R.drawable.d0345_p));

    }


    /**
     * 启动本页面
     *
     * @param context
     * @param cjlb
     */
    public static void actionStart(final Context context, final Cjlb cjlb ,final String pdzcZcpd) {
        final Intent intent = new Intent(context, PzActivity.class);
        intent.putExtra("bean", cjlb);
        final User user = PreferencesUtils.getUser(context);
        final ArrayList<String> list = new ArrayList<String>();
        
        isShowPhoto = true;

        new WsAsyncTask().execute(new WsCallback() {

            String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><zcpd>%s</zcpd><jycs>%s</jycs><jyxm>%s</jyxm></vehispara></root>";

            @Override
            public String getXtlb() {
                return "17";
            }

            @Override
            public String getXml() {
                String jycs = String.valueOf(Integer.parseInt(cjlb.getJycs()));
                String xml = String.format(foramt, cjlb.getJylsh(),
                        user.getJCZBH(), pdzcZcpd, jycs,  PreferencesUtils.getJyxm(context));
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
                return "17F27";
            }

            @Override
            public void callback(JSONObject obj) {
                Log.w("car", obj.toString());
                try {
                    if("R1".equals(PreferencesUtils.getUserLsjyType(context))){
                        list.add("0341");
                        list.add("0343");
                    } else{
                        list.add("0345");
                    }
                    if (obj.getBoolean("ok")) {
                        map.clear();
                        JSONArray array = obj.getJSONArray("Table");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject json = array.getJSONObject(i);
                            list.add(json.getString("zpzl"));
                            map.put(json.getString("zpzl"), json.getString("zplj"));
                        }
                    } else {
                        
                    }
                    intent.putExtra("list", list);
                    context.startActivity(intent);
                } catch (JSONException e) {
                    Toast.makeText(context, "网络访问失败", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
 
    }
    
    /**
     * 启动本页面
     *
     * @param context
     * @param cjlb
     */
    public static void actionStart(final Context context, final Cjlb cjlb) {
    

        final Intent intent = new Intent(context, PzActivity.class);
        intent.putExtra("bean", cjlb);
        User user = PreferencesUtils.getUser(context);
        final ArrayList<String> list = new ArrayList<String>();
        if ("12".equals(user.getRYLB())) {
            list.add("0323");
            intent.putExtra("list", list);
            context.startActivity(intent);
        } else if ("11".equals(user.getRYLB())) {
            list.add("0344");
            list.add("0342");
            intent.putExtra("list", list);
            context.startActivity(intent);
        } else if ("15".equals(user.getRYLB())) {
            if("R1".equals(PreferencesUtils.getUserLsjyType(context))){
                list.add("0341");
                list.add("0343");
            } else{
                list.add("0345");
            }
            intent.putExtra("list", list);
            context.startActivity(intent);
        } else if ("10".equals(user.getRYLB())) {
            new WsAsyncTask().execute(new WsCallback() {

                String foramt = "<?xml version='1.0' encoding='GBK'?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jycs>%s</jycs><xmlb>wgjyzp</xmlb></vehispara></root>";

                @Override
                public String getXtlb() {
                    return "17";
                }

                @Override
                public String getXml() {
                    User user = PreferencesUtils.getUser(context);
                    String jycs = String.valueOf(Integer.parseInt(cjlb
                            .getJycs()));
                    String xml = String.format(foramt, cjlb.getJylsh(),
                            user.getJCZBH(), jycs);
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
                        Log.w("car", obj.toString());
                        if (obj.getBoolean("ok")) {
                            map.clear();
                            JSONArray array = obj.getJSONArray("Table");
                            Log.w("car", array.toString());
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject json = array.getJSONObject(i);
                                list.add(json.getString("dmz"));
                                map.put(json.getString("dmz"), json.getString("zplj"));
                            }
                            intent.putExtra("list", list);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, "获取数据失败", Toast.LENGTH_LONG)
                                    .show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(context, "网络访问失败", Toast.LENGTH_LONG)
                                .show();
                    }

                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pz);

        cjlb = getIntent().getParcelableExtra("bean");

        next = (ImageButton) findViewById(R.id.next);
        mViewpager = (ViewPager) findViewById(R.id.pz_viewpager);
        //page
        LayoutInflater inflater = LayoutInflater.from(this);
        View page1 = inflater.inflate(R.layout.item_pz_layout, null, false);
        View page2 = inflater.inflate(R.layout.item_pz_layout, null, false);
        View page3 = inflater.inflate(R.layout.item_pz_layout, null, false);
        mPageList = new ArrayList<>();
        mPageList.add(page1);
        mPageList.add(page2);
        mPageList.add(page3);


        bt1 = (LinearLayout) page1.findViewById(R.id.bt);
        pz1 = (LinearLayout) page1.findViewById(R.id.pz);
        bt2 = (LinearLayout) page2.findViewById(R.id.bt);
        pz2 = (LinearLayout) page2.findViewById(R.id.pz);
        bt3 = (LinearLayout) page3.findViewById(R.id.bt);
        pz3 = (LinearLayout) page3.findViewById(R.id.pz);

        ArrayList<String> nums = getIntent().getStringArrayListExtra("list");
        for (String string : nums) {
            for (Holder holder : xm) {
                if (string.equals(holder.name)) {
                    list.add(holder);
                    continue;
                }
            }
        }


        //第一页
        for (int i = 0; i < 6 && i < list.size(); i++) {
            if (i != 5) {
                new PzItem(this, bt1, 2, pz1, list.get(i));
            } else {
                new PzItem(this, bt1, 3, pz1, list.get(i));
            }
        }
        for (int i = list.size(); i < 6; i++) {
            if (i != 5) {
                new BlankItem(this, bt1, 2, pz1);
            } else {
                new BlankItem(this, bt1, 3, pz1);
            }
        }

        //第二页
        if (list.size() > 6) {
            for (int i = 6; i < 12 && i < list.size(); i++) {
                if (i != 11) {
                    new PzItem(this, bt2, 2, pz2, list.get(i));
                } else {
                    new PzItem(this, bt2, 3, pz2, list.get(i));
                }
            }
            for (int i = list.size(); i < 12; i++) {
                if (i != 11) {
                    new BlankItem(this, bt2, 2, pz2);
                } else {
                    new BlankItem(this, bt2, 3, pz2);
                }
            }
        }

        //第三页
        if (list.size() > 12 && list.size() < 18) {
            for (int i = 12; i < 18 && i < list.size(); i++) {
                if (i != 17) {
                    new PzItem(this, bt3, 2, pz3, list.get(i));
                } else {
                    new PzItem(this, bt3, 3, pz3, list.get(i));
                }
            }
            for (int i = list.size(); i < 18; i++) {
                if (i != 17) {
                    new BlankItem(this, bt3, 2, pz3);
                } else {
                    new BlankItem(this, bt3, 3, pz3);
                }
            }
        }

        mViewpager.setAdapter(new MyAdapter());

		/*
         * new PzItem(this, R.drawable.d0111_n, R.drawable.d0111_p, bt1, 2, pz1,
		 * "zqf"); new PzItem(this, R.drawable.d0112_n, R.drawable.d0112_p, bt1,
		 * 2, pz1, "yhf"); new PzItem(this, R.drawable.d0113_n,
		 * R.drawable.d0113_p, bt1, 2, pz1, "clsb"); new PzItem(this,
		 * R.drawable.d0119_n, R.drawable.d0119_p, bt1, 2, pz1, "fdjh"); new
		 * PzItem(this, R.drawable.d0115_n, R.drawable.d0115_p, bt1, 2, pz1,
		 * "cxnb"); new PzItem(this, R.drawable.d0158_n, R.drawable.d0158_p,
		 * bt1, 3, pz1, "clzhf");
		 * 
		 * new PzItem(this, R.drawable.d0111_n, R.drawable.d0111_p, bt2, 2, pz2,
		 * "mhq"); new BlankItem(this, bt2, 2, pz2); new BlankItem(this, bt2, 2,
		 * pz2); new BlankItem(this, bt2, 2, pz2); new BlankItem(this, bt2, 2,
		 * pz2); new BlankItem(this, bt2, 3, pz2);
		 */

//        initValues();
        
        if(isShowPhoto){
            next.setVisibility(View.GONE);
//            getPhoto();
        }
        
    }


    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mPageList.get(position));
            return mPageList.get(position);
        }
    }


    private Bundle bundle = new Bundle();

    public void ckzp(View view) {
        if (!bundle.isEmpty()) {
            Intent intent = new Intent(this, CkzpActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    public void takephoto(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);

        }else {
            createFile();
        }
    }
    
    public void pic(PzItem item) {

        this.item = item;

        takephoto();

    }
    
    public void createFile(){
        File dcim = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File camera = new File(dcim, "camera");
        if (!camera.exists()) {
            camera.mkdir();
        }
        file = new File(camera, SystemClock.elapsedRealtime() + ".jpg");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
            "yyyy/MM/dd hh:mm:ss");

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS_REQUEST){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                createFile();
            } else
            {
                Toast.makeText(PzActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == TAKE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                LoadingActivity.startAction(PzActivity.this);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap temp = ImageUtils.decodeSampledBitmap(
                        file.getAbsolutePath(), 1024, 768);
                ImageUtils.compressBitmap(temp, baos);
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(baos.toByteArray());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    temp.recycle();
                    baos.reset();
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    baos = null;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                new XmlAsyncTask().execute(new XmlCallback() {

                    String foramt = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara><jylsh>%s</jylsh><jyjgbh>%s</jyjgbh><jcxdh>%s</jcxdh><jycs>%s</jycs><hphm>%s</hphm><hpzl>%s</hpzl><clsbdh>%s</clsbdh><zp>%s</zp><pssj>%s</pssj><jyxm>%s</jyxm><zpzl>%s</zpzl></vehispara></root>";

                    @Override
                    public String getXtlb() {
                        return "17";
                    }

                    @Override
                    public String getXml() {
                        FileInputStream fis = null;
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();

                        try {
                            fis = new FileInputStream(file);
                            int a = -1;
                            while ((a = fis.read()) != -1) {
                                baos.write(a);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        String jycs = String.valueOf(Integer.parseInt(cjlb
                                .getJycs()));
                        String hpzl = cjlb.getHpzlNum() + "";
                        if (hpzl.length() == 1) {
                            hpzl = "0" + hpzl;
                        }

                        User user = PreferencesUtils.getUser(PzActivity.this);
                        String xml = String.format(foramt, cjlb.getJylsh(),
                                user.getJCZBH(), cjlb.getXzcdh(), jycs, cjlb
                                        .getHphm(), hpzl, cjlb.getClsbdh(),
                                Base64.encodeToString(baos.toByteArray(),
                                        Base64.DEFAULT), simpleDateFormat2
                                        .format(new Date()), PreferencesUtils.getJyxm(PzActivity.this),
                                item.getName());

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
                        return "17F63";
                    }

                    @Override
                    public void callback(String obj) {
                        Log.w("car", obj);
                        LoadingActivity.endAction();
                        String code = XmlUtils.getValue(obj, "code");
                        if ("1".equals(code)) {
                            String message = XmlUtils.getValue(obj, "message");
                            // url = url.replaceAll("\\", "/");
                            // Log.w("car", url);
                            item.pzwc(AppURL.BASE_URL + message);
                        } else {
                            String message = XmlUtils.getValue(obj, "message");
                            Toast.makeText(PzActivity.this, message,
                                    Toast.LENGTH_LONG).show();
                            item.pzsb();
                        }
                    }

                    @Override
                    public String getPara() {
                        return "WriteXmlDoc";
                    }
                });
                // item.pzwc();
            }
        } else if (requestCode == CONFIRM) {
            if (resultCode == Activity.RESULT_OK) {
                TjjgActivity.actionStart(this, cjlb);
            }
        } 
    }
    
    public class BlankItem {
        private int pzWh;

        public BlankItem(Context context, LinearLayout btLayout, int weight,
                         LinearLayout pzLayout) {
            pzWh = context.getResources()
                    .getDimensionPixelOffset(R.dimen.pz_wh);

            TextView bt = new TextView(context);
            LinearLayout.LayoutParams btLayoutParams = new LinearLayout.LayoutParams(
                    pzWh, pzWh);
            btLayout.addView(bt, btLayoutParams);

            TextView btTv = new TextView(context);
            LinearLayout.LayoutParams btTvLayoutParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, weight);
            btLayout.addView(btTv, btTvLayoutParams);

            TextView pz = new TextView(context);
            LinearLayout.LayoutParams pzLayoutParams = new LinearLayout.LayoutParams(
                    pzWh, pzWh);
            pzLayout.addView(pz, pzLayoutParams);

            TextView pzTv = new TextView(context);
            LinearLayout.LayoutParams pzTvLayoutParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, weight);
            pzLayout.addView(pzTv, pzTvLayoutParams);

        }

    }

    public class PzItem implements View.OnClickListener {

        private ImageView bt;
        private TextView btTv;
        private int pzWh;
        private int pzF;

        private FrameLayout pzFrame;
        private ImageView pz;
        private ImageView sc;
        private TextView f;
        private TextView pzTv;
        private Context context;
        private int btSrc;
        private int btP;
        private String name;

        public String getName() {
            return name;
        }

        public PzItem(Context context, LinearLayout btLayout, int weight,
                      LinearLayout pzLayout, Holder holder) {
            this(context, holder.n, holder.p, btLayout, weight, pzLayout,
                    holder.name);

        }

        public PzItem(Context context, int btSrc, int btP,
                      LinearLayout btLayout, int weight, LinearLayout pzLayout,
                      String name) {
            this.name = name;
            this.btSrc = btSrc;
            this.btP = btP;
            this.context = context;
            pzWh = context.getResources()
                    .getDimensionPixelOffset(R.dimen.pz_wh);
            pzF = context.getResources().getDimensionPixelOffset(R.dimen.pz_f);
            bt = new ImageView(context);
            LinearLayout.LayoutParams btLayoutParams = new LinearLayout.LayoutParams(
                    pzWh, pzWh);
            bt.setScaleType(ScaleType.FIT_XY);
            bt.setImageResource(btSrc);
            btLayout.addView(bt, btLayoutParams);

            btTv = new TextView(context);
            LinearLayout.LayoutParams btTvLayoutParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, weight);
            btLayout.addView(btTv, btTvLayoutParams);

            pzFrame = new FrameLayout(context);
            LinearLayout.LayoutParams pzFrameLayoutParams = new LinearLayout.LayoutParams(
                    pzWh, pzWh);
            pzLayout.addView(pzFrame, pzFrameLayoutParams);

            pz = new ImageView(context);
            FrameLayout.LayoutParams pzLayoutParams = new FrameLayout.LayoutParams(
                    pzWh, pzWh);
            pz.setScaleType(ScaleType.FIT_XY);
            pz.setImageResource(R.drawable.wsc);
            pzFrame.addView(pz, pzLayoutParams);

            sc = new ImageView(context);
            FrameLayout.LayoutParams scLayoutParams = new FrameLayout.LayoutParams(
                    pzWh, pzWh);
            sc.setScaleType(ScaleType.FIT_XY);
            sc.setImageResource(R.drawable.pzsc);
            sc.setVisibility(View.GONE);
            sc.setOnClickListener(this);
            pzFrame.addView(sc, scLayoutParams);


            f = new TextView(context);
            FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(
                    pzF, pzF);
            f.setVisibility(View.GONE);
            f.setOnClickListener(this);
            pzFrame.addView(f, fLayoutParams);
            pzTv = new TextView(context);
            LinearLayout.LayoutParams pzTvLayoutParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, weight);
            pzLayout.addView(pzTv, pzTvLayoutParams);

            pz.setOnClickListener(this);

            if (!map.isEmpty()) {
                String zplj = map.get(name);
                if (zplj.length() > 20) {
                    pzwc(AppURL.BASE_URL + zplj);
                }
            }
        }

        public void pzwc(String url) {
            Picasso.with(context).load(url).placeholder(R.drawable.wsc)
                    .error(R.drawable.no_photo)
                    .resizeDimen(R.dimen.pz_wh, R.dimen.pz_wh).centerCrop()
                    .into(pz);
            add(url);
        }

        public void pzsb() {
            Picasso.with(context).load(R.drawable.scsb)
                    .placeholder(R.drawable.scsb).error(R.drawable.scsb)
                    .resizeDimen(R.dimen.pz_wh, R.dimen.pz_wh).centerCrop()
                    .into(pz);
        }

        private void add(String url) {

            bt.setImageResource(btP);
            f.setVisibility(View.VISIBLE);
            sc.setVisibility(View.VISIBLE);
            pz.setOnClickListener(null);
            bundle.putString(name, url);
        }

        @Override
        public void onClick(View v) {
            if (pz == v) {
                pic(this);
            } else if (sc == v) {
                sc.setVisibility(View.GONE);
                f.setVisibility(View.GONE);
                Picasso.with(context).load(R.drawable.wsc)
                        .placeholder(R.drawable.wsc).error(R.drawable.scsb)
                        .resizeDimen(R.dimen.pz_wh, R.dimen.pz_wh).centerCrop()
                        .into(pz);
                pz.setOnClickListener(this);
                bt.setImageResource(btSrc);
                bundle.remove(name);
            } else if (f == v) {
                Intent zqfIntent = new Intent(context, CkActivity.class);
                Log.w("car", name + " " + bundle.getString(name));
                zqfIntent.putExtra("tp", bundle.getString(name));
                startActivity(zqfIntent);
            }

        }

    }

}
