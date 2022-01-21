package org.example.retrofitTest;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.example.retrofit.DTO.Product;
import org.example.retrofit.restapi.ProductService;
import org.example.retrofit.utils.RetrofitUtils;
import org.junit.jupiter.api.*;
import retrofit2.Response;

@DisplayName("Создание продукта")
public class CreateProduct {
    static ProductService productService;
    private Product product;
    private Integer id;
    Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils
                .getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int)(Math.random()* 10000));
    }

    @SneakyThrows
    @Test
    @DisplayName("Создание продукта в категории Food")
    void createProductInFoodCategoryTest() {
        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        Assertions.assertTrue(response.isSuccessful(), "Появляется код ошибки - " + response.code());
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        Assertions.assertTrue(response.isSuccessful());
    }
}
