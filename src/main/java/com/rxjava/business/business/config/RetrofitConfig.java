package com.rxjava.business.business.config;

import com.rxjava.business.business.proxy.JsonPlaceHolderProxy;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@AllArgsConstructor
public class RetrofitConfig {

    private ExternalApiProperties externalApiProperties;

    @Bean
    public JsonPlaceHolderProxy authenticatedUserProxy() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(externalApiProperties.getJsonplaceholderUrl())
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(JsonPlaceHolderProxy.class);
    }
}
