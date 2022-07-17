package com.companyname.talimatmicroservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.companyname.feignclient") // feign gelen class icin bean tanımlamamis oldum
public class CustomBeanFactory {
    /*
    * @Bean 3rd parti class'larin nasıl object olusturacagini belirtmece
    * */
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
