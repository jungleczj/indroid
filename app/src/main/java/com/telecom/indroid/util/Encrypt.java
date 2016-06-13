package com.telecom.indroid.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @create:???????:2014-9-14,by:?????
 *
 * ????
 *
 */
public class Encrypt {
	/**
	 * ????MD5?
	 * @param val
	 * @return
	 */
	public static String getMD5(String val) {
		String md5String = val;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(val.getBytes());
			byte[] hash = digest.digest();
			StringBuilder sb = new StringBuilder(hash.length * 2);
			for (byte b : hash) {
				if ((b & 0xff) < 0x10)
					sb.append("0");
				sb.append(Integer.toHexString(b & 0xff));
			}
			md5String = sb.toString().toUpperCase();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5String;
	}
}
