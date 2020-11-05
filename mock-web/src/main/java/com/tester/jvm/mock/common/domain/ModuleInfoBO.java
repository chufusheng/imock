package com.tester.jvm.mock.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * {@link ModuleInfoBO}
 * <p>
 * 在线模块信息
 *
 * @author fusheng.chu
 */
@Getter
@Setter
public class ModuleInfoBO extends BaseBO {

    private Long id;

    private String appName;

    private String environment;

    private String ip;

    private String port;

    private String version;

    private ModuleStatus status;

    private Date createTime;

    private Date updateTime;



    @Override
    public String toString() {
        return super.toString();
    }
}
