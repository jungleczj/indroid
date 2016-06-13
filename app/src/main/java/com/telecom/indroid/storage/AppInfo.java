package com.telecom.indroid.storage;

import java.util.ArrayList;

import android.app.Application;

/**
 * ȫ�ֱ��������ڴ洢��¼�û�,��¼�ɹ����������
 * 
 * @create:����ʱ��:2014-12-6,by:��˹��
 */
public class AppInfo extends Application {
	// ��¼����
	private String telno;
	// �����
	private int random;
	// ֪ͨ��ʽ
	private ArrayList<String> notice = new ArrayList<String>();
	// ֪ͨ��ʽ
	private ArrayList<String> status = new ArrayList<String>();
	// ��ѯ����
	private ArrayList<String> times = new ArrayList<String>();
	// ��ѯ����
	private ArrayList<String> cycle = new ArrayList<String>();
	// CDMAλ�ò�ѯ״̬
	private ArrayList<String> cdmaLocStatus = new ArrayList<String>();

		
	// �˳�����������ȫ�ֱ���
	public void resetAll() {
		telno = "";
		random = 0;
		notice.clear();
		status.clear();
		times.clear();
		cycle.clear();
		cdmaLocStatus.clear();
	}
	// �û��˺�
	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	// �����
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
