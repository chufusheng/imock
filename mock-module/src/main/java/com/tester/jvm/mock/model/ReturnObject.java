package com.tester.jvm.mock.model;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: fusheng.chu
 * @create: 2020-11-19 16:45
 **/

public class ReturnObject {

    private String[] classNames;

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    private String  returnData;

    public String[] getClassNames() {
        return classNames;
    }

    public void setClassNames(String[] classNames) {
        this.classNames = classNames;
    }



}
