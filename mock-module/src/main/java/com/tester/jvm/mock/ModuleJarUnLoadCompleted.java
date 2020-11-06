package com.tester.jvm.mock;

import com.alibaba.jvm.sandbox.api.spi.ModuleJarUnLoadSpi;
import com.tester.jvm.mock.util.LogbackUtils;
import org.kohsuke.MetaInfServices;

/**
 * {@link }
 * <p>
 *
 * @author fusheng.chu
 */
@MetaInfServices(ModuleJarUnLoadSpi.class)
public class ModuleJarUnLoadCompleted implements ModuleJarUnLoadSpi {

    @Override
    public void onJarUnLoadCompleted() {
        try {
            LogbackUtils.destroy();
        } catch (Throwable e) {
            // ignore
        }
    }
}
