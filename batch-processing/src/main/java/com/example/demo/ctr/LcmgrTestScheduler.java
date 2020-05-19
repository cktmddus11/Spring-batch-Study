package com.example.demo.ctr;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.job.LcmgrTestJob;
@Component
public class LcmgrTestScheduler {
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
	
	@PostConstruct
	public void start() throws SchedulerException{
		schedulerFactory = new StdSchedulerFactory();
		scheduler = schedulerFactory.getScheduler();
		scheduler.start(); // 스케줄러시작
		// withIdentity - job 고유명
		JobDetail job = JobBuilder.newJob(LcmgrTestJob.class).withIdentity("testJob").build();
		Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(CronScheduleBuilder.cronSchedule("1 * * * * ?")).build();
//      startAt과 endAt을 사용해 job 스케쥴의 시작, 종료 시간도 지정할 수 있다.
//      Trigger trigger = TriggerBuilder.newTrigger().startAt(startDateTime).endAt(EndDateTime)
//              .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * * *")).build();
		scheduler.scheduleJob(job, trigger);
	}
	
}
