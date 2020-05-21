package com.example.batchprocessing;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/*@Configuration("BatchConfiguration")
@EnableBatchProcessing*/
public class BatchConfiguration extends DefaultBatchConfigurer {
	@Autowired
	DataSource dataSource;
	
//	@Override
//	protected JobRepository createJobRepository() throws Exception {
//	    JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//	    factory.setDataSource(dataSource);
//	    factory.setTransactionManager(new DataSourceTransactionManager());
//	    factory.setIsolationLevelForCreate("ISOLATION_REPEATABLE_READ");
//	    return factory.getObject();
//	}

}
