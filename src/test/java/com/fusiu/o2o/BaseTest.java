package com.fusiu.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//配置 spring 和 junit 整合， junit 启动时加载 springIoC 容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉 junit spring 配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BaseTest {
}
