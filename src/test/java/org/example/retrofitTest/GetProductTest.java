package org.example.retrofitTest;

import lombok.SneakyThrows;
import org.example.retrofit.DTO.Product;
import org.example.retrofit.restapi.ProductService;
import org.example.retrofit.utils.RetrofitUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@DisplayName("Вызов полного списка продуктов")
    public class GetProductTest {
    static ProductService productService;
    private Object Product;


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils
                .getRetrofit()
                .create(ProductService.class);
    }


    @SneakyThrows
    @Test
    @DisplayName("Список продуктов")
    void getProducts() {
        Response<List<org.example.retrofit.DTO.Product>> response = productService.getProducts().execute();
        Assertions.assertFalse(response.isSuccessful(), "Появляется код ошибки - " + response.code());
    }
}
