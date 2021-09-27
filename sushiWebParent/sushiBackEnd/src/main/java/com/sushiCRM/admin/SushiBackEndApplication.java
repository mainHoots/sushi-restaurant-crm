package com.sushiCRM.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.sushiCRM.common.entity", "com.sushiCRM.admin.user"})
public class SushiBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SushiBackEndApplication.class, args);
	}

}
