package com.dmall.distributedidtest.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "dataSourceFirst")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource dataSourceFirst() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceSecond")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource dataSourceSecond() {
        return DataSourceBuilder.create().build();
    }
}
