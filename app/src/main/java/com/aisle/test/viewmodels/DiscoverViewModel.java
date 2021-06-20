package com.aisle.test.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aisle.test.R;
import com.aisle.test.models.ProfilesBaseResponse;
import com.aisle.test.network.ApiRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DiscoverViewModel extends ViewModel {

    private final ApiRepository repository;
    private CompositeDisposable disposable;

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Integer> errorMessage = new MutableLiveData<>();
    private MutableLiveData<ProfilesBaseResponse> profileData = new MutableLiveData<>();

    @Inject
    public DiscoverViewModel(ApiRepository repository) {
        this.repository = repository;
        this.disposable = new CompositeDisposable();
        fetchProfiles();
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<Integer> getErrorMessageResourceId() {
        return errorMessage;
    }

    public LiveData<ProfilesBaseResponse> getProfiles() {
        return profileData;
    }

    private void fetchProfiles() {
        Disposable d = repository.getProfiles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d1 -> loading.setValue(true))
                .doOnError(d1 -> loading.setValue(false))
                .doOnSuccess(d1 -> loading.setValue(false))
                .subscribeWith(new DisposableSingleObserver<ProfilesBaseResponse>() {
                    @Override
                    public void onSuccess(ProfilesBaseResponse profilesBaseResponse) {
                        profileData.setValue(profilesBaseResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorMessage.setValue(R.string.err_something_wrong);
                    }
                });
        disposable.add(d);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
