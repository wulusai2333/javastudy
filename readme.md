# Springboot

越是学习就越能意识到自己的无知

## git本地代码与远程仓库合并

先创建远程仓库,得到一个仓库地址,如:https://github.com/wulusai2333/javastudy.git

进入到本地项目根目录文件夹执行

```shell
#这里假设你的yum git已经安装完全
#本地项目根目录
git init
#git remote add本地仓库与远端建立连接. origin 远端仓库名字
git remote add origin https://github.com/wulusai2333/javastudy.git
#拉取远程仓库master分支代码
git pull origin master
#将变更合并
git add .
#提交代码
git commit -m 'first commit'
#将更改推送带远程仓库
git push
<<<<<<< HEAD
=======

#项目重建后与远程分支关联
git clone https://github.com/wulusai2333/javastudy.git
#创建本地dev分支与远程dev分支对应
git checkout -b origin/dev
git checkout master
#将dev分支代码合并到master
git merge dev
git push origin master

#查看所有分支
git branch -a
git push origin --delete 
>>>>>>> master
```



## Spring

以IOC(反转控制)和AOP(面向切面编程)为内核,提供了展现层SpringMVC和持久层SpringJDBC以及业务层事务管理的企业级应用技术

优势:

```
	IOC解耦
	AOP实现OOP难以实现的功能
	声明式事务支持
	方便测试
	方便各种框架的集成
	降低javaEE的API使用难度
	Java源码的学习范例
```

耦合

```
模块之间关联强度:
内容耦合:一个模块直接修改另一个模块的数据
外部耦合:模块直接访问全局变量
控制耦合:通过接口传递信号
标记耦合:A向B和C传递一个公共参数
数据耦合:模块间通过参数传递数据
非直接耦合:没有直接联系,通过主模块调用

内聚:
	好的内聚模块应当只做一件事

解耦:
	降低程序间的依赖关系,类的依赖,方法的依赖
	编译期不依赖,运行时依赖
	反射创建对象,避免new
	通过配置文件来获取要创建对象的全限定名

反射解耦示例:

```

## springbootApplication.yaml

```yaml
server:
  port: 8080 #服务端口号
spring:
  application: #应用配置
    name: test #服务名
  datasource: #数据库配置
    driver-class-name: com.mysql.cj.jdbc.Driver #驱动
    username: root #登录数据库的用户名和密码
    password: root
    url: jdbc:mysql://localhost:3306/javastudy?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
  main:
    allow-bean-definition-overriding: true #同名是否允许覆盖注册
logging: #日志
  level:
    root: debug #打印日志级别
  file: #日志文件配置
    path: 
    name: test.log


```



## 通用Mapper

它是基于mybatis

官方文档: https://github.com/abel533/Mapper/wiki

可以简单的建立程序与数据库的映射,结合Mybatis Geneator生成dao代码



## PageHelper

Mybits的分页插件 https://pagehelper.github.io/docs/howtouse/

拦截sql

## HikariCP

目前最快的数据库连接池 https://github.com/brettwooldridge/HikariCP

## alipay

支付api: https://docs.open.alipay.com/api_1/alipay.trade.page.pay

快速接入: https://opendocs.alipay.com/open/270/105899



支付请求信息

apiName : 官方文档中的method,通常使用jdk的不需要填

如下例子未省略的参数是必填项,有些sdk帮我填了,bizContent的全部信息都需要自己填

```json
{
  "apiName": "alipay.trade.page.pay",
  "apiNameFirstUpper": "AlipayTradePagePay",
  "apiNameFirstLower": "alipayTradePagePay",
  "bizContent": {
      				 "out_trade_no":"1584526448557",
                     "product_code":"FAST_INSTANT_TRADE_PAY",
                     "total_amount":"100",
                     "subject":"test2",
                     "body":"",
                     "time_expire":"",
                     "goods_detail":"",
                     "passback_params":"",
                     "extend_params":"",
                     "goods_type":"",
                     "timeout_express":"",
                     "promo_params":"",
                     "royalty_info":"",
                     "sub_merchant":"",
                     "merchant_order_no":"",
                     "enable_pay_channels":"",
                     "store_id":"",
                     "disable_pay_channels":"",
                     "qr_pay_mode":"",
                     "qrcode_width":"",
                     "settle_info":"",
                     "invoice_info":"",
                     "agreement_sign_params":"",
                     "integration_type":"",
                     "request_from_url":"",
                     "business_params":"",
                     "ext_user_info":""
                  }
}
```

## Dubbo



### Springboot与Dubbo的整合

centos7环境配置

```shell
#检查系统有无jdk
rpm -qa |grep java
rpm -qa |grep jdk
rpm -qa |grep gcj
#有就卸载
rpm -qa | grep java | xargs rpm -e --nodeps 
#安装1.8版本的openjdk
yum install java-1.8.0-openjdk* -y
java -version
#以上操作因为需要在linux执行mvn命令会直接编译代码
#如果没能解决参考 https://www.cnblogs.com/benjamin77/p/8460030.html

#安装maven https://maven.apache.org/download.cgi 找到合适的版本
wget https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
tar -zxvf apache-maven-3.6.3-bin.tar.gz
mv apache-maven-3.6.3 maven
#修改环境变量
vi /etc/profile
export MAVEN_HOME=/root/maven  #你的maven目录
export PATH=$PATH:$MAVEN_HOME/bin
source  /etc/profile
#修改maven配置文件 conf/setting.xml 添加阿里云仓库
<mirrors> 
   <mirror> 
     <id>alimaven</id> 
     <name>aliyun maven</name> 
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url> 
     <mirrorOf>central</mirrorOf> 
 </mirror>  
</mirrors>
#其他maven相关配置 https://www.cnblogs.com/hepengju/p/11610451.html

#安装zookeeper 在http://www.apache.org/dyn/closer.cgi/zookeeper
wget https://downloads.apache.org/zookeeper/stable/apache-zookeeper-3.5.7-bin.tar.gz
tar -zxvf apache-zookeeper-3.5.7-bin.tar.gz
mv apache-zookeeper-3.5.7 zookeeper
#修改配置文件
#注意zookeeper 3.5版本以后启动时会占用8080端口
#需要修改配置文件zoo.cfg
admin.serverPort=8888 #端口修改为任意空闲端口
#然后再 zookeeper/bin目录下
./zkServer.sh start #启动
./zkServer.sh restart #重启
```

 项目实例      https://github.com/tuguangquan/mybatis/wiki

不过此项目在我测试的时候有兼容性问题

```xml
将 version版本降为1.5.1.RELEASE解决
<!-- Spring Boot 启动父依赖 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.1.RELEASE</version>
    </parent>
```

linux环境下加入如下项,否则生成的jar不可执行

```xml
<!--mvn install 时生成可执行的jar包-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

linux下运行jar包失败

```shell
#jar包启动的几种方式
java -jar XXX.jar #当前ssh窗口运行
java -jar XXX.jar & #后台运行,ssh窗口关闭停止
nohup java -jar XXX.jar & #后台运行,ssh窗口关闭程序依然运行
nohup java -jar XXX.jar >temp.txt & #输出定向到此文件中
#jar进程没能关闭 查找占用端口的进程
netstat -lnp|grep 8080 #查看8080端口的进程pid
ps 进程pid #看下进程情况
kill -9 进程pid


```

windows下运行jar失败

```shell
netstat -aon #查看所有端口
#查看指定 端口占用
netstat -aon|findstr “端口”

#查看指定进程
tasklist|findstr “PID”

#结束进程
tasklist /f /t /im XXX.exe #有可能杀不掉或者误杀
taskkill /f /pid 3352 #根据pid杀掉进程
```

