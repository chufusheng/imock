package com.tester.jvm.mock.dal.dao;


import com.google.common.collect.Lists;
import com.tester.jvm.mock.common.params.ModuleConfigParams;
import com.tester.jvm.mock.dal.model.ModuleConfig;
import com.tester.jvm.mock.dal.repository.ModuleConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component("moduleConfigDao")
public class ModuleConfigDao {

    @Autowired
    private ModuleConfigRepository moduleConfigRepository;

    public Page<ModuleConfig> selectByParams(@NotNull final ModuleConfigParams params) {
        Pageable pageable = PageRequest.of(params.getPage() - 1, params.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        return moduleConfigRepository.findAll(
                (root, query, cb) -> {
                    List<Predicate> predicates = Lists.newArrayList();
                    if (params.getAppName() != null && !params.getAppName().isEmpty()) {
                        predicates.add(cb.equal(root.<String>get("appName"), params.getAppName()));
                    }
                    if (params.getEnvironment() != null && !params.getEnvironment().isEmpty()) {
                        predicates.add(cb.equal(root.<String>get("environment"), params.getEnvironment()));
                    }
                    if (params.getIsUsable() != null) {
                        predicates.add(cb.equal(root.<String>get("isUsable"), params.getIsUsable() ? 1 : 0));
                    }
                    return cb.and(predicates.toArray(new Predicate[0]));
                },
                pageable
        );
    }

    public ModuleConfig query(ModuleConfigParams params) {
        return moduleConfigRepository.findByAppNameAndEnvironment(params.getAppName(), params.getEnvironment());
    }

    public int stopAndOpen(long id, int isUsable) {
       return moduleConfigRepository.updateStateById(id, isUsable);
    }

    public ModuleConfig saveOrUpdate(ModuleConfig moduleConfig) {
        return moduleConfigRepository.saveAndFlush(moduleConfig);
    }

    public ModuleConfig query() {
        return moduleConfigRepository.findByAppNameAndEnvironment("1", "1");
    }
}
