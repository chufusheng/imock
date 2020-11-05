package com.tester.jvm.mock.dal.dao;

import com.google.common.collect.Lists;
import com.tester.jvm.mock.common.params.RecordParams;
import com.tester.jvm.mock.dal.model.Record;
import com.tester.jvm.mock.dal.repository.RecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecordDao}
 * <p>
 *
 * @author fusheng.chu
 */
@Component("recordDao")
public class RecordDao {

    @Resource
    private RecordRepository recordRepository;

    public Record insert(Record record) {
        return recordRepository.save(record);
    }

    public Record selectByAppNameAndTraceId(String appName, String traceId) {
        return recordRepository.findByAppNameAndTraceId(appName, traceId);
    }

    public Page<Record> selectByAppNameOrTraceId(@NotNull final RecordParams params) {

        Pageable pageable = PageRequest.of(params.getPage() - 1, params.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        return recordRepository.findAll(
                (root, query, cb) -> {
                    List<Predicate> predicates = Lists.newArrayList();
                    if (params.getAppName() != null && !params.getAppName().isEmpty()) {
                        predicates.add(cb.equal(root.<String>get("appName"), params.getAppName()));
                    }
                    if (params.getTraceId() != null && !params.getTraceId().isEmpty()) {
                        predicates.add(cb.equal(root.<String>get("traceId"), params.getTraceId()));
                    }
                    return cb.and(predicates.toArray(new Predicate[0]));
                },
                pageable
        );
    }
}
