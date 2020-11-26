package com.tester.jvm.mock.service.impl;


import com.tester.jvm.mock.common.domain.*;
import com.tester.jvm.mock.common.params.ModuleInfoParams;
import com.tester.jvm.mock.dal.dao.ModuleInfoDao;
import com.tester.jvm.mock.dal.model.ModuleInfo;
import com.tester.jvm.mock.service.ModuleInfoService;
import com.tester.jvm.mock.service.convert.ModuleInfoConverter;
import com.tester.jvm.mock.util.HttpUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link ModuleInfoServiceImpl}
 * <p>
 *
 * @author fusheng.chu
 */
@Service("ModuleInfoService")
public class ModuleInfoServiceImpl implements ModuleInfoService {

    private static String activeURI = "http://%s:%s/sandbox/default/module/http/sandbox-module-mgr/frozen?ids=mock";

    private static String frozenURI = "http://%s:%s/sandbox/default/module/http/sandbox-module-mgr/frozen?ids=mock";

    private static String reloadURI = "http://%s:%s/sandbox/default/module/http/mock/reload";


    private static String installBash = "sh %s/sandbox/bin/sandbox.sh -p %s -P 8820";

    @Resource
    private ModuleInfoDao moduleInfoDao;

    @Resource
    private ModuleInfoConverter moduleInfoConverter;

    @Override
    public PageResult<ModuleInfoBO> query(ModuleInfoParams params) {
        Page<ModuleInfo> page = moduleInfoDao.selectByParams(params);
        PageResult<ModuleInfoBO> result = new PageResult<>();
        if (page.hasContent()) {
            result.setSuccess(true);
            result.setPageIndex(params.getPage());
            result.setCount(page.getTotalElements());
            result.setPageSize(params.getSize());
            result.setTotalPage(page.getTotalPages());
            result.setData(page.getContent().stream().map(moduleInfoConverter::convert).collect(Collectors.toList()));
        }
        return result;
    }

    @Override
    public MockResult<ModuleInfoBO> query(String appName) {
        ModuleInfo byAppName = moduleInfoDao.findByAppName(appName);

        return ResultHelper.success(moduleInfoConverter.convert(byAppName));
    }

    @Override
    public MockResult<ModuleInfoBO> query(String appName, String ip) {
        ModuleInfo moduleInfo = moduleInfoDao.findByAppNameAndIp(appName, ip);
        if (moduleInfo == null) {
            return MockResult.builder().message("data not exist").build();
        }
        return ResultHelper.success(moduleInfoConverter.convert(moduleInfo));
    }

    @Override
    public MockResult<ModuleInfoBO> report(ModuleInfoBO params) {
        ModuleInfo moduleInfo = moduleInfoConverter.reconvert(params);
        moduleInfoDao.save(moduleInfo);
        return ResultHelper.success(moduleInfoConverter.convert(moduleInfo));
    }

    @Override
    public MockResult<ModuleInfoBO> active(ModuleInfoParams params) {
        return execute(activeURI, params, ModuleStatus.ACTIVE);
    }

    @Override
    public MockResult<ModuleInfoBO> frozen(ModuleInfoParams params) {
        return execute(frozenURI, params, ModuleStatus.FROZEN);
    }



    @Override
    public MockResult<String> reload(ModuleInfoParams params) {
        ModuleInfo moduleInfo = moduleInfoDao.findByAppNameAndEnvironment(params.getAppName(), params.getEnvironment());
        if (moduleInfo == null) {
            return ResultHelper.fail("data not exist");
        }
        HttpUtil.Resp resp = HttpUtil.doGet(String.format(reloadURI, moduleInfo.getIp(), moduleInfo.getPort()));
        return ResultHelper.fs(resp.isSuccess());
    }

    @Override
    public MockResult<String> install(ModuleInfoParams params) {

        // this is a fake local implement; must be overwrite in product usage;
        String runtimeBeanName = ManagementFactory.getRuntimeMXBean().getName();
        String pid = runtimeBeanName.split("@")[0];
        BufferedReader input = null;
        BufferedReader error = null;
        PrintWriter output = null;
        try {
            // /Users/tom/sandbox/bin/sandbox.sh
            String[] path = StringUtils.split(System.getProperty("user.dir"), File.separator);
            String userDir = File.separator + path[0] + File.separator + path[1];
            Process process = Runtime.getRuntime().exec(String.format(installBash, userDir, pid));
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            output = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
                builder.append(line).append("\n");
            }
            while ((line = error.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return ResultHelper.success("operate success", builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    // ignore
                }
            }
            if (output != null) {
                output.close();
            }
            if (error != null) {
                try {
                    error.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return ResultHelper.fail();
    }


    private MockResult<ModuleInfoBO> execute(String uri, ModuleInfoParams params, ModuleStatus finishStatus) {
        ModuleInfo moduleInfo = moduleInfoDao.findByAppNameAndIp(params.getAppName(), params.getIp());
        if (moduleInfo == null) {
            return ResultHelper.fail("data not exist");
        }
        HttpUtil.Resp resp = HttpUtil.doGet(String.format(uri, moduleInfo.getIp(), moduleInfo.getPort()));
        if (!resp.isSuccess()) {
            return ResultHelper.fail(resp.getMessage());
        }
        moduleInfo.setStatus(finishStatus.name());
        moduleInfoDao.saveAndFlush(moduleInfo);
        return ResultHelper.success(moduleInfoConverter.convert(moduleInfo));
    }
}
