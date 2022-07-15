package com.example.building.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.Properties;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.building.common.utils.BuildingUtil;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Configures connection to Database.
 *
 * @author nghia.phan
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.example.building"},
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
@Profile({"local", "dev", "prod", "junit"})
public class DatasourceConfiguration {

    @Autowired
    private Environment env;

    /**
     * Creates a bean for primary datasource properties.
     *
     * @return A bean for primary datasource properties.
     */
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Creates a bean for the connection to the primary Database.
     *
     * @return A bean for the connection to the primary Database.
     */
    @Bean(name = "primaryDataSource")
    @Primary
    public HikariDataSource primaryDataSource() {
        return primaryDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    /**
     * Creates a primary datasource JDBC Template for the core DB connection.
     *
     * @return A primary datasource JDBC Template for the core DB connection..
     */
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * Additional JPA Properties
     *
     * @return the additional properties
     */
    private Properties additionalProperties() {
        Properties properties = new Properties();
        if (BuildingUtil.isNotBlank(env.getProperty("spring.jpa.properties.hibernate.dialect")))
            properties.setProperty(AvailableSettings.DIALECT, env.getProperty("spring.jpa.properties.hibernate.dialect"));
        if (BuildingUtil.isNotBlank(env.getProperty("spring.jpa.hibernate.ddl-auto")))
            properties.setProperty(AvailableSettings.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        if (BuildingUtil.isNotBlank(env.getProperty("spring.jpa.show-sql")))
            properties.setProperty(AvailableSettings.SHOW_SQL, env.getProperty("spring.jpa.show-sql"));
        return properties;
    }

    /**
     * Creates a bean for the primary datasource entity manager.
     *
     * @return A bean for the primary datasource entity manager.
     */
    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("primaryDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = builder
                .dataSource(dataSource)
                .packages("com.example.building.entity")
                .persistenceUnit("primaryEntityManagerFactory")
                .build();
        managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        managerFactoryBean.setJpaProperties(additionalProperties());
        return managerFactoryBean;

    }

    /**
     * Creates a bean for the primary database transaction manager.
     *
     * @return A bean for the primary database transaction manager.
     */
    @Primary
    @Bean(name = "primaryTransactionManager")
    public JpaTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

//    /**
//     * Init the schema and data to H2. If the oracle database the script will be ignored.
//     *
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") DataSource dataSource) {
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(new ClassPathResource("queries/schema.sql"));
//        populator.addScript(new ClassPathResource("queries/data.sql"));
//        populator.setSqlScriptEncoding(StandardCharsets.UTF_8.name());
//        initializer.setDatabasePopulator(populator);
//        initializer.setEnabled(true);
//        initializer.afterPropertiesSet();
//        return initializer;
//    }

}

