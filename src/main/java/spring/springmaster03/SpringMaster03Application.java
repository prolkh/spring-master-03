package spring.springmaster03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // Automatic Servlet Registration
@SpringBootApplication
public class SpringMaster03Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMaster03Application.class, args);
	}

}
