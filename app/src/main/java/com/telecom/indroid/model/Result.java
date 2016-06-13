package com.telecom.indroid.model;

/**
 * Created by Administrator on 2016/6/7.
 */

import java.io.Serializable;

/**
 * 结果类
 *
 * @author 李斯滨
 *
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 8647919856613579944L;
    // 是否成功
    private boolean success;
    // 错误消息
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

