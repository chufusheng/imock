package com.tester.jvm.mock.service.convert;


import com.tester.jvm.mock.common.domain.RecordDetailBO;
import com.tester.jvm.mock.dal.model.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;



/**
 * {@link }
 * <p>
 *
 * @author fusheng.chu
 */
@Component("recordDetailConverter")
@Slf4j
public class RecordDetailConverter implements ModelConverter<Record, RecordDetailBO> {


    @Override
    public RecordDetailBO convert(Record source) {
        RecordDetailBO rdb = new RecordDetailBO();
        BeanUtils.copyProperties(source, rdb);
        return rdb;
    }

    @Override
    public Record reconvert(RecordDetailBO target) {
        return null;
    }
}
