package com.example.batchprocessing;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration("BatchConfiguration")
public class BatchConfiguration implements BatchConfigurer {
	private static final Log logger = LogFactory.getLog(DefaultBatchConfigurer.class);
	
	private DataSource dataSource;
	private PlatformTransactionManager transactionManager;
	private JobRepository jobRepository;
	private JobLauncher jobLauncher;
	private JobExplorer jobExplorer;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		if(this.dataSource == null) {
			this.dataSource = dataSource;
		}

		if(this.transactionManager == null) {
			this.transactionManager = new DataSourceTransactionManager(this.dataSource);
		}
	}
	@PostConstruct
	public void initialize() {
		try {
			if(dataSource == null) {
				logger.warn("No datasource was provided...using a Map based JobRepository");

				if(this.transactionManager == null) {
					this.transactionManager = new ResourcelessTransactionManager();
				}

				MapJobRepositoryFactoryBean jobRepositoryFactory = new MapJobRepositoryFactoryBean(this.transactionManager);
				jobRepositoryFactory.afterPropertiesSet();
				this.jobRepository = jobRepositoryFactory.getObject();

				MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(jobRepositoryFactory);
				jobExplorerFactory.afterPropertiesSet();
				this.jobExplorer = jobExplorerFactory.getObject();
			} else {
				this.jobRepository = getJobRepository();
				this.jobExplorer = getJobExplorer();
			}

			this.jobLauncher = getJobLauncher();
		} catch (Exception e) {
			throw new BatchConfigurationException(e);
		}
	}
	
	@Override
	public JobRepository getJobRepository() throws Exception {
	    JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
	    factory.setDataSource(dataSource);
	    factory.setTransactionManager(new DataSourceTransactionManager(dataSource));
	    factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
	    factory.setTablePrefix("BATCH_");
	    factory.setMaxVarCharLength(1000);
	    return factory.getObject();
	}

	@Override
	public PlatformTransactionManager getTransactionManager() throws Exception {
		return transactionManager;
	}

	@Override
	public JobLauncher getJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	@Override
	public JobExplorer getJobExplorer() throws Exception {
		JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
		jobExplorerFactoryBean.setDataSource(this.dataSource);
		jobExplorerFactoryBean.afterPropertiesSet();
		return jobExplorerFactoryBean.getObject();
	}

}
