package com.tester.jvm.mock.common.params;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * {@link ModuleConfigParams}
 * <p>
 *
 * @author fusheng.chu
 */
@Getter
@Setter
public class ModuleConfigParams extends BaseParams {

    private long id;

    private String appName;

    private String environment;

    private String ruleConfig;

    private String mockClass;

    private String mockMethod;

    private Boolean isThrows;

    private String returnObj;

}
