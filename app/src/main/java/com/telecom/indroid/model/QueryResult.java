package com.telecom.indroid.model;

/**
 * Created by Administrator on 2016/6/7.
 */

import com.google.gson.Gson;


        import java.io.Serializable;
        import java.lang.reflect.Type;
import java.security.acl.Group;
import java.util.ArrayList;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

/**
 * 查询结果类
 *
 * @author 李斯滨
 *
 * @modify:修改时间:2014-10-15,修改人:李斯滨
 *
 *                                 1、增加getNoticesFromJsonResult方法
 *
 */
public class QueryResult extends Result implements Serializable {
    private static final long serialVersionUID = 1L;
    // 返回的結果,JSON格式
    private String jsonResult;

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    // JSON转换为List<Equip>
    public ArrayList<Equip> getEquipsFromJsonResult() {
        Type listType = new TypeToken<ArrayList<Equip>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<Equip> equips = gson.fromJson(jsonResult, listType);
        return equips;
    }

    // JSON转换为List<String>
    public ArrayList<String> getListStringsFromJsonResult() {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<String> runningInfos = gson.fromJson(jsonResult, listType);
        return runningInfos;
    }

    /**
     * 吧JSON转换为List<Notice>
     *
     * @return
     */
//    public ArrayList<Notice> getNoticesFromJsonResult() {
//        Type listType = new TypeToken<ArrayList<Notice>>() {
//        }.getType();
//        Gson gson = new Gson();
//        ArrayList<Notice> notices = gson.fromJson(jsonResult, listType);
//        return notices;
//    }

    /**
     * 把JSON转换会工参AntennaPara
     *
     * @return AntennaPara
     */
//    public AntennaPara getAntennaParaFromJsonResult() {
//        Type listType = new TypeToken<AntennaPara>() {
//        }.getType();
//        Gson gson = new Gson();
//        AntennaPara para = gson.fromJson(jsonResult, listType);
//        return para;
//    }

    // JSON转换为List<Task>
//    public ArrayList<Task> getTasksFromJsonResult() {
//        Type listType = new TypeToken<ArrayList<Task>>() {
//        }.getType();
//        Gson gson = new Gson();
//        ArrayList<Task> tasks = gson.fromJson(jsonResult, listType);
//        return tasks;
//    }

    // JSON转换为List<TaskAddition>
//    public ArrayList<TaskAddition> getTaskAdditionsFromJsonResult() {
//        Type listType = new TypeToken<ArrayList<TaskAddition>>() {
//        }.getType();
//        Gson gson = new Gson();
//        ArrayList<TaskAddition> taskAdditions = gson.fromJson(jsonResult,
//                listType);
//        return taskAdditions;
//    }

    // JSON转换为List<Group>
    public ArrayList<Group> getGroupsFromJsonResult() {
        Type listType = new TypeToken<ArrayList<Group>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<Group> groups = gson.fromJson(jsonResult, listType);
        return groups;
    }

    // JSON转换为List<GroupUser>
//    public ArrayList<GroupUser> getGroupUsersFromJsonResult() {
//        Type listType = new TypeToken<ArrayList<GroupUser>>() {
//        }.getType();
//        Gson gson = new Gson();
//        ArrayList<GroupUser> groupUsers = gson.fromJson(jsonResult, listType);
//        return groupUsers;
//    }
}

