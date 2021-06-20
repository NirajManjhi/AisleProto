package com.aisle.test.di.components;

import android.app.Application;

import com.aisle.test.Base.BaseApplication;
import com.aisle.test.di.module.ActivityBindingModule;
import com.aisle.test.di.module.ContextModule;
import com.aisle.test.di.module.FragmentBindingModule;
import com.aisle.test.di.module.NetworkModule;
import com.aisle.test.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {ContextModule.class,
        NetworkModule.class,
        AndroidSupportInjectionModule.class,
        FragmentBindingModule.class,
        ActivityBindingModule.class,
        ViewModelModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
