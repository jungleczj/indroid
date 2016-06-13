package com.telecom.indroid.webservice;

import java.util.Iterator;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import com.telecom.indroid.config.AppConfig;
import com.telecom.indroid.model.LoginResult;
import com.telecom.indroid.model.QueryResult;
import com.telecom.indroid.model.Result;

/**
 * WebServices统一接口
 *
 * WebServices相关配置放在AppConfig
 *
 * @author 李斯滨
 *
 * @modify:修改时间:2014-10-15,修改人:李斯滨
 *
 *                                 增加查询通知QueryNotice和ConfirmNotic两个接口
 */
public class WebService {
    /**
     * 登录V2
     *
     * 发送用户名、密码到服务器上验证登录，如果登录成功则返回一个数字
     *
     * @param userName
     *            用户名，格式为手机号码
     * @param passWord
     *            用户密码
     * @return 登录结果
     * @param meid
     *            终端MEID
     * @return
     */
    public static LoginResult Login(String userName, String passWord,
                                    String version, String meid, String terType, String androidVersion) {
        Log.v(AppConfig.TAG, "密码的MD5" + passWord);
        String methodName = "LoginV4";
        Bundle para = new Bundle();
        para.putString("userName", userName);
        para.putString("passWord", passWord);
        para.putString("version", version);
        para.putString("meid", meid);
        para.putString("terType", terType);
        para.putString("androidVersion", androidVersion);

        Object result = getResultSoapObject(AppConfig.webServiceURI,methodName, para,AppConfig.SHORT_TIME_OUT);
        LoginResult loginResult = null;
        if (result != null) {
            String jsonData = result.toString();
            Gson gson = new Gson();
            loginResult = gson.fromJson(jsonData, LoginResult.class);
        } else {
            loginResult = new LoginResult();
            loginResult.setSuccess(false);
            loginResult.setMessage("连接服务器超时,请稍后重试");
        }
        return loginResult;
    }

    /**
     * 登录Oss
   //  * @param userName
   //  * @param passWord
     * @param version
     * @param meid
     * @param terType
     * @param androidVersion
     * @return
     */
    public static LoginResult LoginOss(String staffCode, String staffPwd,String phoneNo,
                                       String version, String meid, String terType, String androidVersion) {
        String methodName = "LoginOss";
        Bundle para = new Bundle();
        para.putString("staffCode", staffCode);
        para.putString("staffPwd", staffPwd);
        para.putString("phoneNo", phoneNo);
        para.putString("version", version);
        para.putString("meid", meid);
        para.putString("terType", terType);
        para.putString("androidVersion", androidVersion);

        Object result = getResultSoapObject(methodName, para);
        LoginResult loginResult = null;
        if (result != null) {
            String jsonData = result.toString();
            Gson gson = new Gson();
            loginResult = gson.fromJson(jsonData, LoginResult.class);
        } else {
            loginResult = new LoginResult();
            loginResult.setSuccess(false);
            loginResult.setMessage("连接服务器超时,请稍后重试");
        }
        return loginResult;
    }

    /**
     * 查询操作V2
     *
    // * @param telno
     *            登录账号
    // * @param random
     *            随机数
     * @param queryType
     *            查询类型
     * @param queryObjectID
     *            查询对象
     * @return
     */
    public static QueryResult query(String area,String queryType, String queryObjectID) {
        // 定义方法
        String methodName = "Query";
        // 添加参数
        Bundle para = new Bundle();
        para.putString("area", area);
        para.putString("queryType", queryType);
        para.putString("queryObjectID", queryObjectID);

        // 从服务器获取返回结果
        Object result = getResultSoapObject(methodName, para);
        // 解析数据
        QueryResult queryResult = null;
        if (result != null) {
            String jsonData = result.toString();
            Gson gson = new Gson();
            queryResult = gson.fromJson(jsonData, QueryResult.class);
        } else {
            queryResult = new QueryResult();
            queryResult.setSuccess(false);
            queryResult.setMessage("连接服务器超时,请稍后重试");
        }
        return queryResult;
    }

    private static Object getResultSoapObject(String methodName, Bundle para) {
        return getResultSoapObject(AppConfig.webServiceURI,methodName, para, AppConfig.SHORT_TIME_OUT);
    }

    // Query另一版本
//    public static QueryResult query(Context context, String queryType,
//                                    String queryObjectID) {
//        AppInfo appInfo = (AppInfo) context.getApplicationContext();
//        return query(appInfo.getTelno(), appInfo.getRandom(), queryType,
//                queryObjectID);
//
//    }

    /**
     * 提交意见反馈
     *
     * @param telno
     * @param random
     * @param category
     * @param subject
     * @param content
     * @return
     */
//    public static boolean submitSuggest(String telno, int random,
//                                        String category, String subject, String content) {
//        String methodName = "SumbitSuggest";
//        Bundle para = new Bundle();
//        para.putString("telno", telno);
//        para.putInt("random", random);
//        para.putString("category", category);
//        para.putString("subject", subject);
//        para.putString("content", content);
//
//        Object result = getResultSoapObject(methodName, para,
//                AppConfig.LONG_TIME_OUT);
//        boolean success = false;
//        if (result != null)
//            success = Boolean.parseBoolean(result.toString());
//        return success;
//    }

    /**
     * @create:创建时间:2014-10-19,by:李斯滨 1、增加查询通知和确认通知的接口
     */
    /**
     * 查询通知
     *
     * @param telno
     * @param random
     * @param maxid
     * @param minid
     * @param action
     * @return
     */
//    public static QueryResult queryNotice(String telno, int random, int maxid,
//                                          int minid, String action) {
//        // 定义方法
//        String methodName = "QueryNotice";
//
//        // 添加参数
//        Bundle para = new Bundle();
//        para.putString("telno", telno);
//        para.putInt("random", random);
//        para.putInt("maxid", maxid);
//        para.putInt("minid", minid);
//        para.putString("action", action);
//
//        // 从服务器获取返回结果
//        Object result = getResultSoapObject(methodName, para,
//                AppConfig.LONG_TIME_OUT);
//        // 解析数据
//        QueryResult queryResult = null;
//        if (result != null) {
//            String jsonData = result.toString();
//            Gson gson = new Gson();
//            queryResult = gson.fromJson(jsonData, QueryResult.class);
//        } else {
//            queryResult = new QueryResult();
//            queryResult.setSuccess(false);
//            queryResult.setMessage("连接服务器超时,请稍后重试");
//        }
//        return queryResult;
//    }

    // QueryNotice另一版本
//    public static QueryResult queryNotice(Context context, int maxid,
//                                          int minid, String action) {
//        AppInfo appInfo = (AppInfo) context.getApplicationContext();
//        return queryNotice(appInfo.getTelno(), appInfo.getRandom(), maxid,
//                minid, action);
//
//    }

    /**
     * 确认通知
     *
     * @param telno
     * @param random
     * @param id
     * @param action
     * @return
     */
//    public static Result confirmNotice(String telno, int random, int id,
//                                       String action) {
//        String methodName = "ConfirmNotice";
//        Bundle para = new Bundle();
//        para.putString("telno", telno);
//        para.putInt("random", random);
//        para.putInt("id", id);
//        para.putString("action", action);
//
//        Object result = getResultSoapObject(methodName, para,
//                AppConfig.LONG_TIME_OUT);
//        // 解析数据
//        Result queryResult = null;
//        if (result != null) {
//            String jsonData = result.toString();
//            Gson gson = new Gson();
//            queryResult = gson.fromJson(jsonData, Result.class);
//        } else {
//            queryResult = new Result();
//            queryResult.setSuccess(false);
//            queryResult.setMessage("连接服务器超时,请稍后重试");
//        }
//        return queryResult;
//    }

    // 确认通知另一版本
//    public static Result confirmNotice(Context context, int id, String action) {
//        AppInfo appInfo = (AppInfo) context.getApplicationContext();
//        return confirmNotice(appInfo.getTelno(), appInfo.getRandom(), id,
//                action);
//    }

    /**
     * 提交OLT分光器资料核查
     *
     * @param telno
     * @param random
     * @param id
     * @param divport
     * @param equip
     * @return
     */
//    public static Result sumbitOltDivCheck(String telno, int random, String id,
//                                           String divName, int divport, Equip equip, int onlineOnuNum) {
//        String methodName = "SumbitOltDivCheck";
//        Bundle para = new Bundle();
//        para.putString("telno", telno);
//        para.putInt("random", random);
//        para.putString("id", id);
//        para.putString("divName", divName);
//        para.putInt("divport", divport);
//        Gson equipGson = new Gson();
//        equipGson.toJson(equip);
//        para.putString("equipJson", equipGson.toJson(equip));
//        para.putInt("onlineOnuNum", onlineOnuNum);
//
//        Object result = getResultSoapObject(methodName, para,
//                AppConfig.LONG_TIME_OUT);
//        // 解析数据
//        Result queryResult = null;
//        if (result != null) {
//            String jsonData = result.toString();
//            Gson gson = new Gson();
//            queryResult = gson.fromJson(jsonData, Result.class);
//        } else {
//            queryResult = new Result();
//            queryResult.setSuccess(false);
//            queryResult.setMessage("连接服务器超时,请稍后重试");
//        }
//        return queryResult;
//    }
//
////    public static Result sumbitOltDivCheck(Context context, String id,
////                                           String divName, int divport, Equip equip, int onlineOnuNum) {
////        AppInfo appInfo = (AppInfo) context.getApplicationContext();
////        return sumbitOltDivCheck(appInfo.getTelno(), appInfo.getRandom(), id,
////                divName, divport, equip, onlineOnuNum);
////    }

    /**
     * @modify:修改时间:2014-10-19,修改人:李斯滨 1、实现服务器的双备份
     */
//    /**
//     * 和WEB服务器通信的实现,超时默认为短时间
//     *
//     * @param methodName
//     *            webservice调用函数名称
//     * @param para
//     *            webservice调用函数的参数列表
//     * @return null表示和服务器通信失败
//     *
//     */
//    private static Object getResultSoapObject(String methodName, Bundle para) {
//        return getResultSoapObject(methodName, para, AppConfig.SHORT_TIME_OUT);
//    }

//    /**
//     * 和WEB服务器通信的实现,实现服务器双备份
//     *
//     * @param methodName
//     *            同上
//     * @param para
//     *            同上
//     * @param timeout
//     *            超时时间
//     * @return null表示和服务器通信失败
//     *
//     */
//    private static Object getResultSoapObject(String methodName, Bundle para,
//                                              int timeout) {
//        Object result = getResultSoapObject(AppConfig.webServiceURI,
//                methodName, para, timeout);
//        if (result == null)
//            result = getResultSoapObject(AppConfig.webServiceURIBackup,
//                    methodName, para, timeout);
//        return result;
//    }

    /**
     * 和WEB服务器通信的实现
     *
     * @param webServiceUri
     *            服务器地址
     * @param methodName
     *            同上
     * @param para
     *            同上
     * @param timeout
     *            同上
     * @return null表示和服务器通信失败
     */
    private static Object getResultSoapObject(String webServiceUri,
                                              String methodName, Bundle para, int timeout) {
        SoapObject request = new SoapObject(AppConfig.webServiceNS, methodName);
        Log.v(AppConfig.TAG, "methodName:" + methodName);
        // 把参数加入加入request
        for (Iterator<String> iterator = para.keySet().iterator(); iterator
                .hasNext();) {
            String key = iterator.next();
            Object value = para.get(key);
            request.addProperty(key, value);
            Log.v(AppConfig.TAG, "Key:" + key + ",Value:" + value);
        }

        // 开始和服务器通信
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);
        envelope.bodyOut = request;
        envelope.dotNet = true;

        HttpTransportSE ht = new HttpTransportSE(webServiceUri, timeout);
        // HttpTransportSE ht = new HttpTransportSE(AppConfig.host,
        // AppConfig.port, timeout);
        // _FakeX509TrustManager.allowAllSSL();
        ht.debug = true;
        Object result = null;
        try {
            ht.call(AppConfig.webServiceNS+ methodName, envelope);
            if (envelope.getResponse() != null) {
                result = ((SoapObject) envelope.bodyIn).getProperty(0);
                Log.v(AppConfig.TAG, result.toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
//            Log.v(AppConfig.TAG, e.getLocalizedMessage());
//            e.printStackTrace();
        }
        return result;
    }
}
