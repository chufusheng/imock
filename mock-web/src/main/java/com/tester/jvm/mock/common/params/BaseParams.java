package com.tester.jvm.mock.common.params;

import lombok.Getter;
import lombok.Setter;

/**
 * {@link BaseParams}
 * <p>
 *
 * @author fusheng.chu
 */
@Getter
@Setter
public class BaseParams implements java.io.Serializable {

    private Integer page = 1;

    private Integer size = 1000;

    private String appName;

    private String environment;


}
