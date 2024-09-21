package net.study.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() throws ClassNotFoundException {
        HikariDataSource dataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();

        return dataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(getMybatisConfig());
        sqlSessionFactoryBean.setTypeAliasesPackage("net.study.domain.**.vo");

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("mybatis/**/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private org.apache.ibatis.session.Configuration getMybatisConfig() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setUseColumnLabel(true);
        configuration.setDefaultStatementTimeout(10000);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        configuration.setLogPrefix("[SQL]");


        return configuration;
    }
}
