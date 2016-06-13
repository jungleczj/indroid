package com.telecom.indroid.model;

/**
 * Created by Administrator on 2016/6/7.
 */

import java.util.Hashtable;
import java.io.Serializable;
import java.util.ArrayList;
import com.telecom.indroid.model.Result;

/**
 * 登录结果类
 *
 * @author 李斯滨
 * @modify:修改时间:2014-10-15,修改人:李斯滨 增加未读消息数
 */
public class LoginResult extends Result implements Serializable {
    private static final long serialVersionUID = 6458370458000226398L;
    // 随机数
    private int random = 0;
    // 设备类型列表
    private ArrayList<String> equipTypes = new ArrayList<String>();
    // 查询类型列表
    private ArrayList<String> queryTypes = new ArrayList<String>();
    // 版本信息
    //private VersionInfo versionInfo = new VersionInfo();
    // 考核提示信息
   // private CheckHint checkHint = new CheckHint();
    // 未读通知数
    private int notReadNotice = 0;
    // 配置参数
    private Hashtable<String, String> configPara = new Hashtable<String, String>();
    // 查询范围
    private String queryArea = "";

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public ArrayList<String> getEquipTypes() {
        return equipTypes;
    }

    public void setEquipTypes(ArrayList<String> equipTypes) {
        this.equipTypes = equipTypes;
    }

//    public VersionInfo getVersionInfo() {
//        return versionInfo;
//    }
//
//    public void setVersionInfo(VersionInfo versionInfo) {
//        this.versionInfo = versionInfo;
//    }

    public ArrayList<String> getQueryTypes() {
        return queryTypes;
    }

    public void setQueryTypes(ArrayList<String> queryTypes) {
        this.queryTypes = queryTypes;
    }

//    public CheckHint getCheckHint() {
//        return checkHint;
//    }

//    public void setCheckHint(CheckHint checkHint) {
//        this.checkHint = checkHint;
//    }

    public int getNotReadNotice() {
        return notReadNotice;
    }

    public void setNotReadNotice(int notReadNotice) {
        this.notReadNotice = notReadNotice;
    }

    public Hashtable<String, String> getConfigPara() {
        return configPara;
    }

    public void setConfigPara(Hashtable<String, String> configPara) {
        this.configPara = configPara;
    }

    public String getQueryArea() {
        return queryArea;
    }

    public void setQueryArea(String queryArea) {
        this.queryArea = queryArea;
    }
}
