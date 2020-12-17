package com.tester.jvm.mock.dal.repository;


import com.tester.jvm.mock.common.exception.BizException;
import com.tester.jvm.mock.dal.model.ModuleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link ModuleInfoRepository}
 * <p>
 *
 * @author fusheng.chu
 */
@Repository
@Transactional(rollbackFor = {RuntimeException.class, Error.class, BizException.class})
public interface ModuleInfoRepository extends JpaRepository<ModuleInfo, Long>, JpaSpecificationExecutor<ModuleInfo> {

    /**
     * 根据appName查找在线模块
     *
     * @param appName 应用名
     * @return
     */
    ModuleInfo findByAppName(String appName);

    ModuleInfo findByAppNameAndEnvironment(String appName, String environment);


    ModuleInfo findByAppNameAndIp(String appName, String ip);


    @Modifying
    @Query("select DISTINCT appName from  ModuleConfig")
    List<String> getAppNameList();

    @Modifying
    @Query("select  DISTINCT environment from  ModuleConfig where appName=?1")
    List<String> getAppEnvByAppNameList(String appName);
}
