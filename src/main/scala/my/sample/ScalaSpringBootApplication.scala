package my.sample

import my.sample.config.SampleConfig

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

object ScalaSpringBootApplication extends App {

    override def main ( args : Array[String]) {

        val applicationContext : ApplicationContext = new SpringApplicationBuilder().sources(classOf[SampleConfig]).showBanner(true).run(args.toString).asInstanceOf[ApplicationContext];
        val taskExecutor : ThreadPoolTaskExecutor = applicationContext.getBean("taskExecutor").asInstanceOf[ThreadPoolTaskExecutor]
		
		for (i <- 1 to 100) {

            taskExecutor.execute(new Runnable() {

			    override def run() {
                    println("@@Start -> [%s] run ..." , Thread.currentThread().getName() )
                    try {
                        Thread.sleep(5000)
                    } catch {
                        case e: Exception => printf("ERROR : %s ", e.getMessage);
                    }
                    printf("@@Finish -> [%s] end ..." ,Thread.currentThread().getName())
                }
            })
        }
        
        var breaker = true

            while(breaker) {
                var count : Int = taskExecutor.getActiveCount()
                printf("Active Threads : %d" + count);
                try {
                    Thread.sleep(1000);
                } catch {
                    case e: Exception => printf("ERROR : %s ", e.getMessage);
                }
                if (count == 0) {
                    taskExecutor.shutdown();
                    breaker = false;
                }
            }
        }
}