package com.tester.jvm.mock.dal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * {@link ModuleConfig}
 * <p>
 *
 * @author fusheng.chu
 */
@Entity
@Table(name = "module_config")
@Getter
@Setter
public class ModuleConfig implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "app_name")
    private String appName;

    private String environment;

    @Column(name = "mock_class")
    private String mockClass;

    @Column(name = "mock_method")
    private String mockMethod;

    @Column(name = "return_obj")
    private String returnObj;

    @Column(name = "rule_config")
    private String ruleConfig;

    @Column(name = "is_throws")
    private Boolean isThrows;

    @Column(name = "is_usable")
    private Integer isUsable;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
