package com.rs.retailstore.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration  //báo ở đây có những config để hệ thống quét
public class DataSourceConfig {

    //Kết nối với CSDL
    @Bean //thùng chứa data
    public DataSource dataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/retail_store_youtube");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("123456");
        return dataSourceBuilder.build();
    }
}
