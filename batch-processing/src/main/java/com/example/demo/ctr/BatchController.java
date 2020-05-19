package com.example.demo.ctr;

import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.job.TrReReqJob;

@Controller
public class BatchController {
	@Autowired
	private Scheduler scheduler;
	
	@PostConstruct
	public void start(){
		JobDetail aggreReqJobDetail = buildJobDetail(TrReReqJob.class, "testJob", "test", new HashMap<>()); 
		
		try {    //                    																초 분 시 일 월  
			scheduler.scheduleJob(aggreReqJobDetail, buildJobTrigger("0 * * * * ?"));
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Trigger buildJobTrigger(String scheduleExp) {
		return TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
	}

	public JobDetail buildJobDetail(Class<TrReReqJob> job, String name, String group, Map params) {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.putAll(params);
		return newJob(job).withIdentity(name, group).usingJobData(jobDataMap).build();
	}
}
