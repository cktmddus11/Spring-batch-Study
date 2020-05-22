package exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepNextConditionalJobConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	private static Logger log = LoggerFactory.getLogger(StepNextConditionalJobConfiguration.class);
	
	@Autowired
	public StepNextConditionalJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}
	@Bean
	public Job stepNextConditionalJob(){
		return jobBuilderFactory.get("stepNextConditionalJob")
				.start(conditionalJobStep1())
					.on("FAILED") // 캐치할 ExitStatus
					.to(conditionalJobStep3())
					.on("*")
					.end() // flowbuilder 반환
				.from(conditionalJobStep1()) // step1의 추가 이벤트
					.on("*")
					.to(conditionalJobStep2())
					.next(conditionalJobStep3())
					.on("*")
					.end()
				.end() // flowbuilder 종료
				.build();
				
	}
	private Step conditionalJobStep3() {
		return stepBuilderFactory.get("conditionalJobStep3")
				.tasklet((contribute, chukContext) -> {
					log.info("=====step3=======");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	private Step conditionalJobStep2() {
		return stepBuilderFactory.get("conditionalJobStep2")
				.tasklet((contribute, chukContext) -> {
					log.info("=====step2=======");
					return RepeatStatus.FINISHED;
				})
				.build();
	}

	private Step conditionalJobStep1() {
		return stepBuilderFactory.get("conditionalJobStep1")
				.tasklet((contribute, chukContext) -> {
					log.info("=====step1=======");
					//contribute.setExitStatus(ExitStatus.FAILED); // exitStatus failed로 지정
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	
	
	
	
	
}
