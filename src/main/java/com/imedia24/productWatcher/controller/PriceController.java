package com.imedia24.productWatcher.controller;

import com.imedia24.productWatcher.dao.model.CustomHttpResponse;
import com.imedia24.productWatcher.dao.model.Price;
import com.imedia24.productWatcher.service.interfaces.IPriceService;
import com.imedia24.productWatcher.util.constant.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    private final IPriceService priceService;

    @Autowired
    public PriceController(IPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<CustomHttpResponse> getPaginatedPrices(){
        return ResponseEntity.status(HttpStatus.OK).body(
                CustomHttpResponse.builder()
                        .success(true)
                        .message(ResponseMessage.EMPTY_MESSAGE)
                        .data(priceService.getPaginatedPrices())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomHttpResponse> createPrice(@RequestBody Price price){
        Price createdPrice = priceService.createPrice(price);
        try{
            if(createdPrice == null){
                return ResponseEntity.status(HttpStatus.OK).body(
                        CustomHttpResponse.builder()
                                .success(false)
                                .message(ResponseMessage.PRICE_CREATION_ERROR)
                                .build()
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    CustomHttpResponse.builder()
                            .success(true)
                            .message(ResponseMessage.PRICE_CREATION_SUCCESS)
                            .data(createdPrice)
                            .build()
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(
                    CustomHttpResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
