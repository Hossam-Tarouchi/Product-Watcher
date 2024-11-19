package com.imedia24.productWatcher;

import com.imedia24.productWatcher.dao.model.Price;
import com.imedia24.productWatcher.dao.model.Product;
import com.imedia24.productWatcher.service.interfaces.IProductService;
import com.imedia24.productWatcher.util.constant.ResponseMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ProductWatcherApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class ProductControllerUnitTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setSku(123L);
        product.setName("Test Product");
        product.setPrices(new ArrayList<>(List.of(
                Price.builder()
                        .id(1L)
                        .sku(123L)
                        .date(new Date())
                        .build()
        )));
    }

    @Test
    void testGetProductFound() throws Exception {
        Long sku = 123L;

        when(productService.getProduct(any(Long.class))).thenReturn(product);
        mockMvc.perform(get("/api/v1/product/{sku}", sku))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value(ResponseMessage.EMPTY_MESSAGE))
                .andExpect(jsonPath("$.data.sku").value(product.getSku()))
                .andExpect(jsonPath("$.data.name").value(product.getName()))
                .andExpect(jsonPath("$.data.prices[0].id").value(1L))
                .andExpect(jsonPath("$.data.prices[0].price").value(0.0))
                .andExpect(jsonPath("$.data.prices[0].date").value("19/11/2024"))
                .andExpect(jsonPath("$.data.prices[0].sku").value(123L));
    }

    @Test
    void testGetProductNotFound() throws Exception {
        Long sku = 123L;
        when(productService.getProduct(any(Long.class))).thenReturn(null);

        mockMvc.perform(get("/api/v1/product/{sku}", sku))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(ResponseMessage.PRODUCT_NOT_FOUND));
    }

    @Test
    void testCreateProductSuccess() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sku\": 123, \"name\": \"Test Product\", \"price\": 99.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value(ResponseMessage.PRODUCT_CREATION_SUCCESS))
                .andExpect(jsonPath("$.data.sku").value(product.getSku()))
                .andExpect(jsonPath("$.data.name").value(product.getName()))
                .andExpect(jsonPath("$.data.prices[0].id").value(1L))
                .andExpect(jsonPath("$.data.prices[0].price").value(0.0))
                .andExpect(jsonPath("$.data.prices[0].date").value("19/11/2024"))
                .andExpect(jsonPath("$.data.prices[0].sku").value(123L));
    }

    @Test
    void testCreateProductFailure() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(null);

        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sku\": 123, \"name\": \"Test Product\", \"price\": 99.99}"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(ResponseMessage.PRODUCT_CREATION_ERROR));
    }

    @Test
    void testCreateProductInternalServerError() throws Exception {
        when(productService.createProduct(any(Product.class))).thenThrow(new RuntimeException("Unexpected Error"));

        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sku\": 123, \"name\": \"Test Product\", \"price\": 99.99}"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Unexpected Error"));
    }
}
