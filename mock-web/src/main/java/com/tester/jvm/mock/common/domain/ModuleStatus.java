package com.tester.jvm.mock.common.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ModuleStatus}
 * <p>
 *
 * @author fusheng.chu
 */
@Getter
public enum ModuleStatus {
    /**
     * 已激活
     */
    ACTIVE("已激活"),
    FROZEN("已冻结"),
    OFFLINE("离线"),

    ;

    private static final Map<String, ModuleStatus> CACHED = new HashMap<>(2);

    static {
        for (ModuleStatus status : values()) {
            CACHED.put(status.name(), status);
        }
    }

    private String desc;

    ModuleStatus(String desc) {
        this.desc = desc;
    }

    public static ModuleStatus of(String status) {
        return CACHED.get(status);
    }
}
