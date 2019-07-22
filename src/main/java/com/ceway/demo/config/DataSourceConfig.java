package com.ceway.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {
	

	@Bean(name = "readTestDb")
    @ConfigurationProperties(prefix = "spring.read-test-db.datasource") 
    public DataSource readTestDb() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "writeTestDb")
    @ConfigurationProperties(prefix = "spring.write-test-db.datasource")
    public DataSource writeTestDb() {
        return DataSourceBuilder.create().build();
    }
    

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(readTestDb());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put("readTestDb", readTestDb());
        dsMap.put("writeTestDb", writeTestDb());
 
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }
 
    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}