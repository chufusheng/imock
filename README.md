## imock

###### imock 服务是基于阿里测试中间件[jvm-sandbox](https://github.com/alibaba/jvm-sandbox)开发的一款mock服务,感兴趣可以去了解一下，imock 与其他mock 方式不一样， 是Java方法级别的mock，操作就是监听指定方法，返回指定的mock内容

#### 项目介绍
imock 包含mock-module、mock-web ,mock-module就是jvm-sandbox的模块，需要安装到目标应用服务器，mock-web 为mock服务后台，imock是前后端分离，还有一个前端react 项目  [imcok-web](https://github.com/chufusheng/imock-web)


#### Git    
- https://github.com/chufusheng/imock

#### 使用
- 第一步：数据初始化  https://github.com/chufusheng/imock/tree/main/bin/sql 
- 第二步：启动mock服务管理后台  mock-web 
- 第三步：启动前端项目   imock-web
- 第四步：安装mock-module（默认会安装jvm-sandbox）
    1. 本地安装 到项目下的bin目录执行 
       sh package.sh    //打包模块
       sh install-local.sh  //安装模块
    2. 远程安装到目标应用  curl -s https://kunchu.oss-cn-beijing.aliyuncs.com/install-troublemaker.sh |sh //少用远程，里面的模块包有可能没有更新或者获取不到  
- 第五步：配置目标应用
    1. 通过配置文件配置  进入根目录下的隐藏目录  ~/.sandbox-module/cfg  修改mock.properties  
    2. 启动目标应用时带上 java 环境变量-Dmock.host=http://127.0.0.1:8003 -Dapp.name=test -Dapp.env=test
```

# mock.properties 
# 心跳上报配置  当环境变量没有配置的时候使用 该配置

# mock 服务的地址和端口
mock.host=http://127.0.0.1:8003
# 标识目标应用的名称
app.name=test
# 标识目标应用的环境
app.env=test

```
         





- 第六步：启动  
    1. 通过jps查看 目标应用的java 进程  比如  1234
    2. 到根目录 ~/sandbox/bin  执行 ./sandbox.sh -p 1234

如果出现以下日志则安装成功
```
                    NAMESPACE : default
                      VERSION : 1.2.1
                         MODE : ATTACH
                  SERVER_ADDR : 0.0.0.0
                  SERVER_PORT : 58441
               UNSAFE_SUPPORT : ENABLE
                 SANDBOX_HOME : /Users/pro9q/sandbox/bin/..
            SYSTEM_MODULE_LIB : /Users/pro9q/sandbox/bin/../module
              USER_MODULE_LIB : /Users/pro9q/sandbox/sandbox-module;~/.sandbox-module;
          SYSTEM_PROVIDER_LIB : /Users/pro9q/sandbox/bin/../provider
           EVENT_POOL_SUPPORT : DISABLE
```



![image](https://kunchu.oss-cn-beijing.aliyuncs.com/image/first.png)


![image](https://kunchu.oss-cn-beijing.aliyuncs.com/image/create.png)