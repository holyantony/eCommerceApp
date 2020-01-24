package com.wc.heady.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;


import com.wc.heady.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = {NetworkModule.class,ViewModelFactoryModule.class,RoomModule.class})
public class AppModule {


    @Singleton
    @Provides
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    static Resources provideResource(Context context) {
        return context.getResources();
    }


}



