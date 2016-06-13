package com.telecom.indroid.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;



/**
 * @create:???????:2014-9-14,by:?????
 * 
 *                               ??????????????????APP??¡À???MEID??????C????
 *
 * @modify:??????:2015-3-19,?????:????? ???LTE???????CDMA????
 *
 */
public class PhoneInfo {

	/**
	 * ??????????MEID
	 */
	public static String getMeid(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String meid = "000000000000000";
		;
		if (manager.getDeviceId() != null) {
			meid = manager.getDeviceId();
		}
		return meid;
	}

	/**
	 * ????????¡À????
	 */
	public static String getVersionName(Context context) {
		String version;
		try {
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()???????????0?????????¡À????
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			version = packInfo.versionName;
		} catch (Exception e) {
			// TODO: handle exception
			version = "??¡À????";
		}
		return version;
	}

	/**
	 * ???????????????????????
	 *
	 * @return ???????????????????????????
	 */
//	public static CdmaConnectStatus getCdmaConnectStatus(Context context) {
//		CdmaConnectStatus cdmaConnectStatus = new CdmaConnectStatus();
//
//		TelephonyManager manager = (TelephonyManager) context
//				.getSystemService(Context.TELEPHONY_SERVICE);
//		int type = manager.getNetworkType();
//
//		// ?????????CDMA
//		if (type == TelephonyManager.NETWORK_TYPE_CDMA
//				|| type == TelephonyManager.NETWORK_TYPE_1xRTT
//				|| type == TelephonyManager.NETWORK_TYPE_EVDO_0
//				|| type == TelephonyManager.NETWORK_TYPE_EVDO_A
//				|| type == TelephonyManager.NETWORK_TYPE_EVDO_B
//				|| type == TelephonyManager.NETWORK_TYPE_EHRPD
//				|| type == TelephonyManager.NETWORK_TYPE_LTE) {
//			CdmaCellLocation cdma = (CdmaCellLocation) manager
//					.getCellLocation();
//
//
//
//
//			// ????MCC??MNC????????
//			String netWorkOperator = manager.getNetworkOperator();
//			String mcc = netWorkOperator.substring(0, 3);
//			String mnc = netWorkOperator
//					.substring(netWorkOperator.length() - 2);
//
//			if (!(mcc.equals(AppConfig.cdma_mcc) && (mnc
//					.equals(AppConfig.cdma_mnc)||mnc.equals(AppConfig.lte_mnc)))) {
//				cdmaConnectStatus.setMessage(context
//						.getString(R.string.cdma_notice_noinchinacdma));
//			} else {
//				Integer systemId = cdma.getSystemId();
//				Integer networkId = cdma.getNetworkId();
//				// ???????????
//				boolean inQZ = false;
//				if (systemId == AppConfig.cdma_systemid) {
//					for (int nid : AppConfig.cdma_networdids) {
//						if (networkId == nid) {
//							inQZ = true;
//							break;
//						}
//					}
//				}
//				if (!inQZ) {
//					cdmaConnectStatus.setMessage(context
//							.getString(R.string.cdma_notice_noinquanzhoucdma));
//				} else {
//					cdmaConnectStatus.setConnectCdma(true);
//					cdmaConnectStatus.setCellid(cdma.getBaseStationId());
//				}
//			}
//		} else {
//			cdmaConnectStatus.setConnectCdma(false);
//			cdmaConnectStatus.setMessage(context
//					.getString(R.string.cdma_notice_noincdma));
//			// cdmaConnectStatus.setCellid(22702);
//		}
//		return cdmaConnectStatus;
//	}
}
