package my.sample;

import my.sample.config.SampleConfig;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class SpringApplication {
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder()
				.sources(SampleConfig.class).showBanner(true).run(args);
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) app
				.getBean("taskExecutor");
		for (int i = 0; i < 10; i++) {

			taskExecutor.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println("@@Start -> [" + Thread.currentThread().getName()
							+ "] run ...");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("@@Finish -> [" + Thread.currentThread().getName()
							+ "] end ...");
				}
			});
		}

		for (;;) {
			int count = taskExecutor.getActiveCount();
			System.out.println("Active Threads : " + count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				taskExecutor.shutdown();
				break;
			}
		}
	}
}
