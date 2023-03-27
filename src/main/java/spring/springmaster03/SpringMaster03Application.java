package spring.springmaster03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan // Automatic Servlet Registration
@SpringBootApplication
public class SpringMaster03Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMaster03Application.class, args);
	}

//	@Bean
//	InternalResourceViewResolver internalResourceViewResolver() {
//		return new InternalResourceViewResolver("/WEB-INF/vies/", ".jsp");
//	}
}
