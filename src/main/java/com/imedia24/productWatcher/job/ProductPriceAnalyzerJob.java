package com.imedia24.productWatcher.job;

import com.imedia24.productWatcher.dao.model.Price;
import com.imedia24.productWatcher.dao.model.Product;
import com.imedia24.productWatcher.dao.model.ProductAction;
import com.imedia24.productWatcher.service.implementation.ProductService;
import com.imedia24.productWatcher.util.enums.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductPriceAnalyzerJob {

    private final ProductService productService;
    private final SimpleDateFormat simpleDateFormat;

    @Autowired
    public ProductPriceAnalyzerJob(ProductService productService, SimpleDateFormat simpleDateFormat) {
        this.productService = productService;
        this.simpleDateFormat = simpleDateFormat;
    }

    @Scheduled(cron = "0 0 0 * * 5", zone = "GMT")
    public void execute(){

        List<Product> products = productService.findProductsWithPricesFromLastThreeMonths();
        products.forEach(this::analyzeProfitForProduct);

    }

    public void analyzeProfitForProduct(Product product) {
        List<Price> productPrices = product.getPrices();
        if (!productPrices.isEmpty()) {
            List<Price> prices = new ArrayList<>(productPrices);

            Collections.sort(prices);

            List<ProductAction> actions = new ArrayList<>();

            for (int i = 0; i < prices.size(); i+=2) {
                if (prices.get(i).getPrice() < prices.get(i+1).getPrice()) {
                    ProductAction actionBuy = ProductAction.builder()
                            .sku(product.getSku())
                            .type(Action.BUY)
                            .date(simpleDateFormat.format(prices.get(i).getDate()))
                            .build();

                    ProductAction actionSell = ProductAction.builder()
                            .sku(product.getSku())
                            .type(Action.SELL)
                            .date(simpleDateFormat.format(prices.get(i+1).getDate()))
                            .build();
                    actions.addAll(List.of(actionBuy, actionSell));
                }
            }
            // TODO: Publish to RabbitMQ
            System.err.println(actions);
        }
    }
}
