package com.tester.jvm.mock.common.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * {@link ModuleConfigBO}
 * <p>
 *
 * @author fusheng.chu
 */
@Getter
@Setter
public class ModuleConfigBO extends BaseBO {

    private Long id;

    private String appName;

    private String environment;

    private String ruleConfig;

    private String mockClass;

    private String mockMethod;

    private String returnObj;

    private Boolean isThrows;

    private Date createTime;

    private Date updateTime;



    @Override
    public String toString() {
        return super.toString();
    }
}
