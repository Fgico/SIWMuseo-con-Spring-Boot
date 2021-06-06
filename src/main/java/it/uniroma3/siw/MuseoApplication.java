package it.uniroma3.siw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"it.uniroma3.siw.model"})
public class MuseoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuseoApplication.class, args);
	}

}
