package com.tester.jvm.mock.dal.dao;


import com.google.common.collect.Lists;
import com.tester.jvm.mock.common.params.ModuleInfoParams;
import com.tester.jvm.mock.dal.model.ModuleInfo;
import com.tester.jvm.mock.dal.repository.ModuleInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * {@link }
 * <p>
 *
 * @author fusheng.chu
 */
@Component("moduleInfoDao")
public class ModuleInfoDao {

    @Resource
    private ModuleInfoRepository moduleInfoRepository;

    public ModuleInfo findByAppName(String appName) {
        return moduleInfoRepository.findByAppName(appName);
    }

    public ModuleInfo findByAppNameAndEnvironment(String appName, String environment) {
        return moduleInfoRepository.findByAppNameAndEnvironment(appName, environment);
    }

    public Page<ModuleInfo> selectByParams(@NotNull final ModuleInfoParams params) {
        Pageable pageable = PageRequest.of(params.getPage() - 1, params.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        return moduleInfoRepository.findAll(
                (root, query, cb) -> {
                    List<Predicate> predicates = Lists.newArrayList();
                    if (params.getAppName() != null && !params.getAppName().isEmpty()) {
                        predicates.add(cb.equal(root.<String>get("appName"), params.getAppName()));
                    }
                    if (params.getIp() != null && !params.getIp().isEmpty()) {
                        predicates.add(cb.equal(root.<String>get("ip"), params.getIp()));
                    }
                    if (params.getEnvironment() != null && !params.getEnvironment().isEmpty()) {
                        predicates.add(cb.equal(root.<String>get("environment"), params.getEnvironment()));
                    }
                    return cb.and(predicates.toArray(new Predicate[0]));
                },
                pageable
        );
    }

    public ModuleInfo saveByAppNameAndIp(ModuleInfo params) {
        ModuleInfo mi = moduleInfoRepository.findByAppNameAndIp(params.getAppName(), params.getIp());
        if (mi != null) {
            params.setId(mi.getId());
        }
        return moduleInfoRepository.saveAndFlush(params);
    }

    public ModuleInfo save(ModuleInfo params) {
        ModuleInfo mi = moduleInfoRepository.findByAppNameAndEnvironment(params.getAppName(),params.getEnvironment());
        if (mi != null) {
            params.setId(mi.getId());
        }
        return moduleInfoRepository.saveAndFlush(params);
    }

    public ModuleInfo saveAndFlush(ModuleInfo params) {
        return moduleInfoRepository.saveAndFlush(params);
    }

    public ModuleInfo findByAppNameAndIp(String appName, String ip) {
        return moduleInfoRepository.findByAppNameAndIp(appName, ip);
    }
}
