package com.example.demo.job;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Component
public class TrReReqJob extends QuartzJobBean  implements InterruptableJob {
	private static final Logger log = LoggerFactory.getLogger(TrReReqJob.class);
	@Override
	public void interrupt() throws UnableToInterruptJobException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("1");
		
	}

}
