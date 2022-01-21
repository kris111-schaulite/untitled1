package org.example.retrofitTest;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.example.retrofit.DTO.Product;
import org.example.retrofit.restapi.ProductService;
import org.example.retrofit.utils.RetrofitUtils;
import org.junit.jupiter.api.*;
import retrofit2.Response;

public class GetProductByIdTest {
        static ProductService productService;
        private Product product;
        Faker faker;

        private static Integer expectedId;
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
                expectedId = resp.body().getId();
        }


        @org.junit.jupiter.api.Test
        @DisplayName("Получить продукт по id")
        @SneakyThrows
        void getProductById() {
                //act
                Response<Product> response = productService.getProduct(expectedId).execute();
                //assert
                Assertions.assertTrue(response.isSuccessful(), "Появляется код ошибки - " + response.code());
                Assertions.assertEquals(expectedId, response.body().getId(), "Неверный id");
                Assertions.assertEquals(expectedTitle, response.body().getTitle(), "Неверный title продукта");
                Assertions.assertEquals(expectedPrice, response.body().getPrice(), "Неверный price продукта");
        }

        @Test
        @DisplayName("Вввод некорректного id")
        @SneakyThrows
        void getProductInvalidId() {
                Response<Product> response = productService.getProduct(34).execute();
                int expectedCodeError = 404;
                Assertions.assertFalse(response.isSuccessful(), "появляется код - " + response.code());
                Assertions.assertEquals(expectedCodeError, response.code(), "Код ошибки не совпадает");
        }

        @SneakyThrows
        @AfterAll
        static void tearDown() {
                Response<ResponseBody> response = productService.deleteProduct(Math.toIntExact(expectedId)).execute();
                Assertions.assertTrue(response.isSuccessful());
        }
}





