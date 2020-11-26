package com.tester.jvm.mock.dal.repository;


import com.tester.jvm.mock.common.exception.BizException;
import com.tester.jvm.mock.dal.model.ModuleConfig;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link ModuleConfigRepository}
 * <p>
 *
 * @author fusheng.chu
 */
@Repository
@Transactional(rollbackFor = {RuntimeException.class, Error.class, BizException.class})
public interface ModuleConfigRepository extends JpaRepository<ModuleConfig, Long>, JpaSpecificationExecutor<ModuleConfig> {

    ModuleConfig findByAppNameAndEnvironment(String appName, String environment);

    @Modifying
    @Query("update ModuleConfig  set isUsable=?2 where  id=?1")
    int updateStateById(long id, int isUsable);
}
