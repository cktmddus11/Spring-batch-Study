package com.example.batchprocessing;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

@DependsOn("BatchConfiguration")
public class JobExecutor implements Job{

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobLocator jobLocator;
	
	private static final Logger log = LoggerFactory.getLogger(JobExecutor.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getMergedJobDataMap();
		String jobName = (String) jobDataMap.get("job");
		JobParameters  jobParameters;
		
		try {
			jobParameters = new JobParametersBuilder().toJobParameters();
			jobLauncher.run(jobLocator.getJob(jobName), jobParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
