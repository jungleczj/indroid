package com.telecom.indroid.model;

/**
 * �û���Ϣ
 * 
 * @author ��˹��
 * 
 */
public class User {
	// �û���
	private String userName = null;
	// ��������
	private String passWord = null;
	// �Ƿ��¼����
	private boolean logPassword = false;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isLogPassword() {
		return logPassword;
	}

	public void setLogPassword(boolean logPassword) {
		this.logPassword = logPassword;
	}

}
