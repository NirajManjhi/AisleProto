package com.aisle.test.di.module;

import com.aisle.test.ui.login.LoginActivity;
import com.aisle.test.ui.home.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
