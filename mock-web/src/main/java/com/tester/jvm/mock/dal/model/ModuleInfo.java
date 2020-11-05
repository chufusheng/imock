package com.tester.jvm.mock.dal.model;

import com.tester.jvm.mock.common.domain.ModuleStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * {@link ModuleInfo}
 * <p>
 * 在线模块信息
 *
 * @author fusheng.chu
 */
@Entity
@Table(name = "module_info")
@Getter
@Setter
public class ModuleInfo implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "app_name")
    private String appName;

    private String environment;

    private String ip;

    private String port;

    private String version;

    private String  status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
