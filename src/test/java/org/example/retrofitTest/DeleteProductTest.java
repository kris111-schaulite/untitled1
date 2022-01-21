package org.example.retrofitTest;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.example.retrofit.DTO.Product;
import org.example.retrofit.restapi.ProductService;
import org.example.retrofit.utils.RetrofitUtils;
import org.junit.jupiter.api.*;
import retrofit2.Response;

@DisplayName("Удаление продукта")
public class DeleteProductTest {
    static ProductService productService;
    private Product product;
    private Faker faker;
    private Integer expectedId;
    private String expectedTitle;
    private Integer expectedPrice;
    private String expectedCategoryTitle = "Food";

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils
                .getRetrofit()
                .create(ProductService.class);
    }

    @SneakyThrows
    @BeforeEach
    void setUp() {
        faker = new Faker();
        expectedTitle = faker.food().ingredient();
        expectedPrice = (int) (Math.random() * 1000);
        product = new Product()
                .withTitle(expectedTitle)
                .withPrice(expectedPrice)
                .withCategoryTitle(expectedCategoryTitle);
        productService.createProduct(product).execute();
        Response<Product> resp = productService.createProduct(product).execute();
        assert resp.body() != null;
        expectedId = resp.body().getId();
    }

    @SneakyThrows
    @Test
    @DisplayName("Удаление продукта по id")
    void deleteProductById() {
        Response<ResponseBody> response = productService.deleteProduct(expectedId).execute();
        Assertions.assertTrue(response.isSuccessful());
    }
}
