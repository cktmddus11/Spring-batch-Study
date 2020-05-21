package exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepNextJobConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	@Autowired
	public StepNextJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}
	private Logger log = LoggerFactory.getLogger(StepNextJobConfiguration.class);
	
	@Bean 
	public Job stepNextJob(){
		return jobBuilderFactory.get("stepNextJob")
				.start(step1())
				.next(step2())
				.next(step3())
				.build();
	}
	
	@Bean 
	public Step step1(){
		return stepBuilderFactory.get("step1")
				.tasklet((contribution, chuckContext) -> {
						log.info(">>>>step1>>>>>>>>");
						return RepeatStatus.FINISHED;
				})
				.build();
	}
	@Bean 
	public Step step2(){
		return stepBuilderFactory.get("step2")
				.tasklet((contribution, chuckContext) -> {
						log.info(">>>>step2>>>>>>>>");
						return RepeatStatus.FINISHED;
				})
				.build();
	}
	@Bean 
	public Step step3(){
		return stepBuilderFactory.get("step3")
				.tasklet((contribution, chuckContext) -> {
						log.info(">>>>step3>>>>>>>>");
						return RepeatStatus.FINISHED;
				})
				.build();
	}
	
}
