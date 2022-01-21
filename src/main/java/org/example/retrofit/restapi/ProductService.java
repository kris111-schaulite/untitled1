package org.example.retrofit.restapi;

import okhttp3.ResponseBody;
import org.example.retrofit.DTO.Product;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Locale;

public interface ProductService {

//    @GET("categories/{id}")
//    Call<ResponseBody> getProduct(@Path("id") int id);
//
//    @GET("categories/{id}")
//    Call<GetCategoryResponse> getCategory(@Path("id") int id);
    @GET("categories/{id}")
    Call<Locale.Category> getCategory(@Path("id") Long id);

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Integer id);

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") Integer id);

    @PUT("products")
    Call<Product> putProduct(@Body Product putProductRequest);

}

