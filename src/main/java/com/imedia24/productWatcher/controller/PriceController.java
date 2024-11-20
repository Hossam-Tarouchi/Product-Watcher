package com.imedia24.productWatcher.controller;

import com.imedia24.productWatcher.dao.model.CustomHttpResponse;
import com.imedia24.productWatcher.dao.model.Price;
import com.imedia24.productWatcher.service.interfaces.IPriceService;
import com.imedia24.productWatcher.util.constant.ResponseMessage;
import com.imedia24.productWatcher.util.exception.ProductDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    private final IPriceService priceService;

    @Autowired
    public PriceController(IPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<CustomHttpResponse> getPaginatedPrices(@RequestParam("page") int page){
        if (page < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    CustomHttpResponse.builder()
                            .success(false)
                            .message(ResponseMessage.PRICE_PAGE_VALUE_NOT_ACCEPTABLE)
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                CustomHttpResponse.builder()
                        .success(true)
                        .message(ResponseMessage.EMPTY_MESSAGE)
                        .data(priceService.getPaginatedPrices(page))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomHttpResponse> createPrice(@RequestBody Price price){
        try{
            Price createdPrice = priceService.createPrice(price);
            if(createdPrice == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        CustomHttpResponse.builder()
                                .success(false)
                                .message(ResponseMessage.PRICE_CREATION_ERROR)
                                .build()
                );
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    CustomHttpResponse.builder()
                            .success(true)
                            .message(ResponseMessage.PRICE_CREATION_SUCCESS)
                            .data(createdPrice)
                            .build()
            );
        } catch (ProductDoesntExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomHttpResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    CustomHttpResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
