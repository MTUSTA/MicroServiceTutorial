package com.companyname.hesapmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HesapMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HesapMicroserviceApplication.class, args);
	}

}
