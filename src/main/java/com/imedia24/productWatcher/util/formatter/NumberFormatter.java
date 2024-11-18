package com.imedia24.productWatcher.util.formatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;

@Configuration
public class NumberFormatter {
    @Bean
    public DecimalFormat formatNumber() {
        return new DecimalFormat("#.##");
    }
}
