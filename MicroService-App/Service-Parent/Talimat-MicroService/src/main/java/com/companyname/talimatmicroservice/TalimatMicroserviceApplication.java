package com.companyname.talimatmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TalimatMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalimatMicroserviceApplication.class, args);
	}

}
