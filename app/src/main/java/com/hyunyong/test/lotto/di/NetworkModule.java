package com.hyunyong.test.lotto.di;

import com.hyunyong.test.lotto.data.Webservice;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {AppModule.class})
class NetworkModule {
    @Singleton
    @Provides
    static GsonConverterFactory provideGSONConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkhttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory factory) {
        return new Retrofit.Builder()
                .baseUrl("https://www.dhlottery.co.kr/")
                .client(client)
                .addConverterFactory(factory)
                .build();
    }

    @Singleton
    @Provides
    static Webservice provideWebService(Retrofit retrofit) {
        return retrofit.create(Webservice.class);
    }
}