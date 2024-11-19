package com.imedia24.productWatcher.util.formatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class DateFormatter {
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("dd-MM-yyyy");
    };
}
