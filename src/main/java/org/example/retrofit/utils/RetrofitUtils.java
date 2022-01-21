package org.example.retrofit.utils;

import lombok.experimental.UtilityClass;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


@UtilityClass
public class RetrofitUtils {

    public String baseUrl = "http://80.78.248.82:8189/market/api/v1/";

    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }
}
