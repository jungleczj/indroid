package com.telecom.indroid.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.telecom.indroid.model.User;


/**
 * �洢���ڵ��û����� �����û�/����
 * 
 * @create:����ʱ��:2014-12-6,by:��˹��
 */
public class UserStorage {
	/**
	 * ��ȡ�û�����
	 * 
	 * @param context
	 * @return
	 */
	public static User getUser(Context context) {
		User user = new User();
		SharedPreferences preferences = context.getSharedPreferences(
				"userInfo", Context.MODE_PRIVATE);
		String userName = preferences.getString("userName", "");
		String passWord = preferences.getString("passWord", "");
		boolean logPassword = preferences.getBoolean("logPassword", false);
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setLogPassword(logPassword);
		return user;
	}

	/**
	 * �洢�û�����
	 * 
	 * @param context
	 * @param user
	 */
	public static void saveUser(Context context, User user) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"userInfo", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("userName", user.getUserName());
		if (user.isLogPassword()) {
			editor.putString("passWord", user.getPassWord());
		} else {
			editor.putString("passWord", "");
		}
		editor.putBoolean("logPassword", user.isLogPassword());
		editor.commit();
	}

	/**
	 * �洢�����û�����
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveStringStorage(Context context, String key,
			String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"userInfo", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * ��ȡ�����û�����
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getStringStorage(Context context, String key) {
		SharedPreferences preferences = context.getSharedPreferences(
				"userInfo", Context.MODE_PRIVATE);
		String value = preferences.getString(key, "");
		return value;
	}
}
