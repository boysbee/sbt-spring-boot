package my.sample.config;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SampleConfig {

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setQueueCapacity(100);
		taskExecutor.setThreadFactory(Executors.defaultThreadFactory());
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor
				.setRejectedExecutionHandler(new RejectedExecutionHandler() {

					public void rejectedExecution(Runnable r,
							ThreadPoolExecutor t) {

						t.getQueue().add(r);
					}
				});
		return taskExecutor;
	}
}
