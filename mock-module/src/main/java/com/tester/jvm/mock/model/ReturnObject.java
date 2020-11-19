package com.tester.jvm.mock.model;


import com.alibaba.fastjson.JSON;

/**
 * @author: fusheng.chu
 * @create: 2020-11-19 16:45
 **/

public class ReturnObject {

    private String[] classNames;
    private JSON returnData;

    public String[] getClassNames() {
        return classNames;
    }

    public void setClassNames(String[] classNames) {
        this.classNames = classNames;
    }

    public JSON getReturnData() {
        return returnData;
    }

    public void setReturnData(JSON returnData) {
        this.returnData = returnData;
    }


}
