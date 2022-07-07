package com.companyname.hesapmicroservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanFactory {
    /*
    * @Bean 3rd parti class'larin nasıl object olusturacagini belirtmece
    * */
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
