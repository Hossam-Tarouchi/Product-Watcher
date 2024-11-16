package com.imedia24.productWatcher.controller;

import com.imedia24.productWatcher.dao.model.CustomHttpResponse;
import com.imedia24.productWatcher.dao.model.Product;
import com.imedia24.productWatcher.service.interfaces.IProductService;
import com.imedia24.productWatcher.util.constant.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<CustomHttpResponse> getProduct(@PathVariable Long sku) {
        Product product = productService.getProduct(sku);
        if (product == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomHttpResponse.builder()
                            .success(false)
                            .message(ResponseMessage.PRODUCT_NOT_FOUND)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                CustomHttpResponse.builder()
                        .success(true)
                        .message(ResponseMessage.EMPTY_MESSAGE)
                        .data(product)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomHttpResponse> createProduct(@RequestBody Product product) {
        try{
            Product createdProduct = productService.createProduct(product);
            if (createdProduct == null){
                return ResponseEntity.status(HttpStatus.OK).body(
                        CustomHttpResponse.builder()
                                .success(false)
                                .message(ResponseMessage.PRODUCT_CREATION_ERROR)
                                .build()
                );
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    CustomHttpResponse.builder()
                            .success(true)
                            .message(ResponseMessage.PRODUCT_CREATION_SUCCESS)
                            .data(createdProduct)
                            .build()
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(
                    CustomHttpResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
            );
        }

    }
}
