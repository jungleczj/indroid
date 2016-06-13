package com.telecom.indroid.storage;

import java.util.ArrayList;

import android.app.Application;

/**
 * 全局变量，用于存储登录用户,登录成功返回随机数
 * 
 * @create:创建时间:2014-12-6,by:李斯滨
 */
public class AppInfo extends Application {
	// 登录号码
	private String telno;
	// 随机数
	private int random;
	// 通知方式
	private ArrayList<String> notice = new ArrayList<String>();
	// 通知方式
	private ArrayList<String> status = new ArrayList<String>();
	// 查询次数
	private ArrayList<String> times = new ArrayList<String>();
	// 查询周期
	private ArrayList<String> cycle = new ArrayList<String>();
	// CDMA位置查询状态
	private ArrayList<String> cdmaLocStatus = new ArrayList<String>();

		
	// 退出是重启所有全局变量
	public void resetAll() {
		telno = "";
		random = 0;
		notice.clear();
		status.clear();
		times.clear();
		cycle.clear();
		cdmaLocStatus.clear();
	}
	// 用户账号
	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	// 随机数
	public int getRandom() {
		return random;
	}

	public void setRandom(int random) {
		this.random = random;
	}



	public ArrayList<String> getStatus() {
		return status;
	}

	public void setStatus(ArrayList<String> status) {
		this.status = status;
	}

	public ArrayList<String> getNotice() {
		return notice;
	}

	public void setNotice(ArrayList<String> notice) {
		this.notice = notice;
	}

	public ArrayList<String> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<String> times) {
		this.times = times;
	}

	public ArrayList<String> getCycle() {
		return cycle;
	}

	public void setCycle(ArrayList<String> cycle) {
		this.cycle = cycle;
	}

	public ArrayList<String> getCdmaLocStatus() {
		return cdmaLocStatus;
	}

	public void setCdmaLocStatus(ArrayList<String> cdmaLocStatus) {
		this.cdmaLocStatus = cdmaLocStatus;
	}
}
