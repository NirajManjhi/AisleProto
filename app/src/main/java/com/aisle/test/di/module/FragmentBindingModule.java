package com.aisle.test.di.module;

import com.aisle.test.ui.home.DiscoverFragment;
import com.aisle.test.ui.login.LoginOtpFragment;
import com.aisle.test.ui.login.LoginPhoneFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract LoginPhoneFragment bindLoginPhoneFragment();

    @ContributesAndroidInjector
    abstract LoginOtpFragment bindLoginOtpFragment();

    @ContributesAndroidInjector
    abstract DiscoverFragment bindDiscoverFragment();
}
