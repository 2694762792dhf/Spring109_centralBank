package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
/*配置bean*/
/*@MapperScan扫描动态代理*/
@MapperScan(basePackages = "com.dao")
/*获取数据库属性文件的相关的值：url、useName等*/
@PropertySource("classpath:jdbc.properties")

public class SessionFactoryConfig {
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.initialSize}")
    private int initialSize;


    /*使用Java类配置dbcp2数据源*/
    @Bean
    public BasicDataSource getBds(){
        BasicDataSource bds=new BasicDataSource();
        bds.setDriverClassName(driver);
        bds.setUrl(url);
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setInitialSize(initialSize);
        return bds;
    }

    /*使用Java类配置Druid数据源*/
    @Bean
    public DruidDataSource getDds(){
        DruidDataSource dds=new DruidDataSource();
        dds.setDriverClassName(driver);
        dds.setUrl(url);
        dds.setUsername(username);
        dds.setPassword(password);
//        dds.setInitialSize(initialSize);
        return dds;
    }

    @Autowired
    @Qualifier("getDds")
    private DataSource dataSource;


/*spring整合mybatis*/
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        PathMatchingResourcePatternResolver pmrpr=new PathMatchingResourcePatternResolver();
        /*将xml文件的路径解析为类型为Resource的资源对象：*/
        ssfb.setMapperLocations(pmrpr.getResources("mappers/**"));

        /*mybatis的相关配置：日志、驼峰等*/
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        ssfb.setConfiguration(configuration);
        return ssfb;
    }
}
