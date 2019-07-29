package com.wjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.wjw.pubnub.*")
@ServletComponentScan
public class PubnubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubnubApplication.class, args);
	}

}
