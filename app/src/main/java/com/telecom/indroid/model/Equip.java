package com.telecom.indroid.model;

import java.io.Serializable;
/**
 * Created by Administrator on 2016/6/7.
 */

/**
 * 设备类
 *
 * @author 李斯滨
 *
 */
public class Equip implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id = "";
    private String name = "";
    private String type = "";
    private String port = "";
    private String info = "";
    private String attr = "";

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "Equip{" +
                "id='" + id + '\'' +
                '}';
    }
}

