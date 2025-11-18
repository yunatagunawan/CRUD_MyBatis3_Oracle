package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.example.demo.mapper") // <-- scan your mapper package
@EnableConfigurationProperties(OracleDataSourceProperties.class)
public class MyBatisConfig {

    @Bean
    @Profile("dev")
    public DataSource devDataSource(OracleDataSourceProperties properties) {
        return createHikariDataSource(properties);
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource(OracleDataSourceProperties properties) {
        return createHikariDataSource(properties);
    }

    private DataSource createHikariDataSource(OracleDataSourceProperties props) {
        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(props.getUrl());
        ds.setUsername(props.getUsername());
        ds.setPassword(props.getPassword());
        ds.setDriverClassName(props.getDriverClassName());

        // Hikari settings
        ds.setMaximumPoolSize(props.getHikari().getMaximumPoolSize());
        ds.setMinimumIdle(props.getHikari().getMinimumIdle());
        ds.setConnectionTimeout(props.getHikari().getConnectionTimeout());
        ds.setIdleTimeout(props.getHikari().getIdleTimeout());

        return ds;
    }

    @Bean("batchSqlSessionTemplate")
    public SqlSessionTemplate batchSqlSessionTemplate(SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory, ExecutorType.BATCH);
    }

    /**
     * Configure MyBatis SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // Set MyBatis config file
        factoryBean.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml")
        );

        // Set mapper XML location
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/xml/*.xml")
        );

        // Optional: Type aliases package
        factoryBean.setTypeAliasesPackage("com.example.demo.entity");

        return factoryBean.getObject();
    }

    /**
     * Optional: MapperScannerConfigurer
     * This is an alternative to @MapperScan
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.example.demo.mapper");
        return configurer;
    }
}
