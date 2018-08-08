package com.xlw.springbootdatajpa;

import com.xlw.springbootdatajpa.pojo.A;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MyConfig {

    @Bean
    @Primary
    public A aaa() {
        return new A();
    }

    @Bean
    public A aa() {
        return new A();
    }
}
