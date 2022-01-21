package org.example.retrofitTest;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.example.retrofit.DTO.Product;
import org.example.retrofit.restapi.ProductService;
import org.example.retrofit.utils.RetrofitUtils;
import org.junit.jupiter.api.*;
import retrofit2.Response;


@DisplayName("Изменение продукта")
public class PutProductTest {
    static ProductService productService;
    Faker faker = new Faker();
    private Product product;
    private String expectedTitle;
    private Integer expectedPrice = (int) (Math.random() * 1000);
    private Long expectedId = 2L;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils
                .getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        expectedTitle = faker.food().ingredient();
        product = new Product()
                .withId(Math.toIntExact(expectedId))
                .withTitle(expectedTitle)
                .withPrice(expectedPrice)
                .withCategoryTitle("Food");
    }

    @SneakyThrows
    @Test
    @DisplayName("Изменение")
    void putProduct() {
        Response<Product> response = productService.putProduct(product).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(expectedId, response.body().getId(), "Неверный id");
        Assertions.assertEquals(expectedTitle, response.body().getTitle(), "Неверный title продукта");
        Assertions.assertEquals(expectedPrice, response.body().getPrice(), "Неверный price продукта");
    }


}
