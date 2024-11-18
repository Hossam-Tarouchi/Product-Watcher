package com.imedia24.productWatcher.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceAnalyzerJob {

    @Scheduled(cron = "0 0 0 * * 5", zone = "GMT")
    public void execute(){}
}
