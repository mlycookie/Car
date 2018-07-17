package edu.qau.car;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class PreferencesUtils {

	private static Gson gson = new Gson();
	private static Map<String, String> rylb = new HashMap<>();
	private static Map<String, String> jyxm = new HashMap<>();
	static {
		rylb.put("10", "车辆外观");
		rylb.put("11", "底盘动态");
		rylb.put("12", "车辆底盘");
		rylb.put("15", "路试员");
		rylb.put("07","信息登录员");

		jyxm.put("10", "wgjcxm");
		jyxm.put("11", "dtdpjyxm");
		jyxm.put("12", "dpjyxm");
		
		
//		jyxm.put("13","xczdxm");//行车制动
//		jyxm.put("14","pdzcxm");//坡度驻车
		
		
	}

	public static String getUserJyxm(Context context) {
		User user = getUser(context);
		if (user != null) {
			return jyxm.get(user.getRYLB());
		}
		return null;
	}

	public static User getUser(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String json = sharedPreferences.getString("user", null);
		if (json != null) {
			return gson.fromJson(json, User.class);
		}
		return null;
	}

	public static String getUserInfo(Context context) {
		String format = "当前用户：%s    %s";
		return String.format(format, getUserXm(context), getUserRYLB(context));
	}

	public static String getUserLsyInfo(Context context) {
		String format = "当前用户：%s    %s";
		return String.format(format, getUserXm(context), getUserLsjyTypeStr(context));
	}

	public static String getUserRYLB(Context context) {
		User user = getUser(context);
		if (user != null) {
			return rylb.get(user.getRYLB());
		}
		return null;
	}

	public static String getJyxm(Context context) {
		User user = getUser(context);
		if (user != null) {
			String jyxm;
			if ("10".equals(user.getRYLB())) {
				jyxm = "F1";
			} else if ("11".equals(user.getRYLB())) {
				jyxm = "DC";
			} else if ("12".equals(user.getRYLB())) {
				jyxm = "C1";
			} else  if ("15".equals(user.getRYLB())){
				jyxm = getUserLsjyType(context);
			}else  {
				jyxm = "";
			}
			return jyxm;
		}
		return null;
	}

	public static String getUserXm(Context context) {
		User user = getUser(context);
		if (user != null) {
			return user.getXM();
		}
		return null;
	}

	public static String getUserLsjyType(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String type = sharedPreferences.getString("lsjy", null);
		return type;
	}

	private  static String getUserLsjyTypeStr(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String type = sharedPreferences.getString("lsjyStr", null);
		return type;
	}

	/**
	 * 是否是路试员角色
	 * @param context
	 * @return
     */
	public static boolean isLsy(Context context){
		User user = getUser(context);
		if(user!=null && "15".equals(user.getRYLB())){
			return true;
		}
		return false;
	}


}
