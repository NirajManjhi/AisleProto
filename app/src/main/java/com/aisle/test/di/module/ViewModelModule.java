package com.aisle.test.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aisle.test.Base.ViewModelFactory;
import com.aisle.test.di.ViewModelKey;
import com.aisle.test.viewmodels.DiscoverViewModel;
import com.aisle.test.viewmodels.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(value = LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(value = DiscoverViewModel.class)
    abstract ViewModel bindDiscoverViewModel(DiscoverViewModel discoverViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
