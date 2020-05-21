package exam;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepNextConditionalJobConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	@Autowired
	public StepNextConditionalJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}
	@Bean
	public Job stepNextConditionalJob(){
		return jobBuilderFactory.get("stepNextConditionalJob")
				.start(conditionalJobStep1())
					.on("FAILED")
					.to(conditionalJobStep3())
					.on("*")
					.end()
				.from(conditionalJobStep1())
					.on("*")
					.to(conditionalJobStep2())
					.next(conditionalJobStep3())
					.on("*")
					.end()
				.end()
				.build();
				
	}
	private Step conditionalJobStep3() {
		// TODO Auto-generated method stub
		return null;
	}
	private Step conditionalJobStep2() {
		// TODO Auto-generated method stub
		return null;
	}
	private Step conditionalJobStep1() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
