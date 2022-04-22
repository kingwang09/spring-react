package io.labs.springreact.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableTransactionManagement
public class RDBConfig {
    @Bean(name="dataSource")
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") final DataSource masterDataSource
            , @Qualifier("slaveDataSource") final DataSource slaveDataSource) {

        final AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            /** <a href="http://egloos.zum.com/kwon37xi/v/5364167">conf</a> */
            @Override
            protected Object determineCurrentLookupKey() {
                final String dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master";
                String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
                log.trace("current transaction : {}", transactionName);
                log.trace("current dataSource type : {}", dataSourceType);
                return dataSourceType;
            }
        };

        final Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", masterDataSource);
        dataSourceMap.put("slave", slaveDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        return routingDataSource;
    }

    @Primary
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.master.datasource")
    public DataSource masterDataSource(final DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.slave.datasource")
    public DataSource slaveDataSource(final DataSourceProperties slaveDataSourceProperties) {
        return slaveDataSourceProperties.initializeDataSourceBuilder().build();
    }

//    @Primary
//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.master.datasource")
//    public DataSource masterDataSource(final DataSourceProperties dataSourceProperties) {
//        return dataSourceProperties.initializeDataSourceBuilder().build();
//    }
}
