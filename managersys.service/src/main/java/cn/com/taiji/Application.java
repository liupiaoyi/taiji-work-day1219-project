package cn.com.taiji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	/**
	 * 
	 * {"page":1,"pageSize":8,"filter":{"filters":[{"field":"loginName","value":"sue"},{"field":"userName","value":"sd"}],"logic":"and"},"sort":[{"field":"loginName","dir":"asc"},{"field":"email","dir":"asc"}]}
	 * 
	 * 
	 * {"page":1,"pageSize":8,"filter":{"filters":[{"field":"loginName","value":"sue"},{"field":"userName","value":"sd"}],"logic":"and"}}
	 */
}
