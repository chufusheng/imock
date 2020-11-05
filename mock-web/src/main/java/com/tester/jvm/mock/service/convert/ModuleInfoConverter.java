package com.tester.jvm.mock.service.convert;


import com.tester.jvm.mock.common.domain.ModuleInfoBO;
import com.tester.jvm.mock.common.domain.ModuleStatus;
import com.tester.jvm.mock.dal.model.ModuleInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


/**
 * {@link ModuleInfoConverter}
 * <p>
 *
 * @author fusheng.chu
 */
@Component("moduleInfoConverter")
public class ModuleInfoConverter implements ModelConverter<ModuleInfo, ModuleInfoBO> {

    @Override
    public ModuleInfoBO convert(ModuleInfo source) {
        ModuleInfoBO moduleInfo = new ModuleInfoBO();
        BeanUtils.copyProperties(source, moduleInfo);
        moduleInfo.setStatus(ModuleStatus.of(source.getStatus()));
        return moduleInfo;
    }

    @Override
    public ModuleInfo reconvert(ModuleInfoBO target) {
        ModuleInfo moduleInfo = new ModuleInfo();
        BeanUtils.copyProperties(target, moduleInfo);
        moduleInfo.setStatus(target.getStatus().name());
        return moduleInfo;
    }
}
