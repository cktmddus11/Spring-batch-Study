package com.example.demo.job;

import java.sql.Timestamp;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class LcmgrTestJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("=======job수행 ============");
		System.out.println(new Timestamp(System.currentTimeMillis()));
		
	}

}
