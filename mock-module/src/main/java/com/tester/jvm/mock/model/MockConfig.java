package com.tester.jvm.mock.model;


public class MockConfig {

    private String ruleConfig;

    private String mockClass;

    private String mockMethod;

    private Boolean isThrows;

    private String returnObj;


    public Boolean getIsThrows() {
        return isThrows;
    }

    public void setIsThrows(Boolean thow) {
        isThrows = thow;
    }

    public String getRuleConfig() {
        return ruleConfig;
    }

    public void setRuleConfig(String ruleConfig) {
        this.ruleConfig = ruleConfig;
    }

    public String getMockClass() {
        return mockClass;
    }

    public void setMockClass(String mockClass) {
        this.mockClass = mockClass;
    }

    public String getMockMethod() {
        return mockMethod;
    }

    public void setMockMethod(String mockMothod) {
        this.mockMethod = mockMothod;
    }

    public String getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(String returnObj) {
        this.returnObj = returnObj;
    }


}
