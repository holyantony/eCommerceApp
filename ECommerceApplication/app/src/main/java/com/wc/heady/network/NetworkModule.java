package com.wc.heady.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wc.heady.utils.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    static Gson provideGSON(){
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(OkHttpClient okHttpClient,Gson gson){
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build();
    }

    @Provides
    @Singleton
    static OkHttpClient provideHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        /*if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }*/

       return builder.build();
    }

    @Provides
    @Singleton
    static APIServices providesNetworkService(Retrofit retrofit) {
        return retrofit.create(APIServices.class);
    }
}
