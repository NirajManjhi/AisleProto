package com.aisle.test.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aisle.test.R;
import com.aisle.test.models.LoginOtpRequest;
import com.aisle.test.models.LoginOtpResponse;
import com.aisle.test.models.LoginPhoneRequest;
import com.aisle.test.models.LoginPhoneResponse;
import com.aisle.test.network.ApiRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private ApiRepository repository;
    private CompositeDisposable disposable;

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Integer> errorMessage = new MutableLiveData<>();

    @Inject
    public LoginViewModel(ApiRepository repository) {
        this.repository = repository;
        this.disposable = new CompositeDisposable();
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<Integer> getErrorMessageResourceId() {
        return errorMessage;
    }

    public LiveData<LoginPhoneResponse> submitPhone(String phone) {
        MutableLiveData<LoginPhoneResponse> phoneResponseLiveData = new MutableLiveData<>();
        if (phone.length() != 10) {
            errorMessage.setValue(R.string.err_phone_invalid);
        } else {
            Disposable d = repository.getOtp(new LoginPhoneRequest(phone))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(d1 -> loading.setValue(true))
                    .doOnError(d1 -> loading.setValue(false))
                    .doOnSuccess(d1 -> loading.setValue(false))
                    .subscribeWith(new DisposableSingleObserver<LoginPhoneResponse>() {
                        @Override
                        public void onSuccess(LoginPhoneResponse loginPhoneResponse) {
                            phoneResponseLiveData.setValue(loginPhoneResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            errorMessage.setValue(R.string.err_something_wrong);
                        }
                    });
            disposable.add(d);
        }
        return phoneResponseLiveData;
    }

    public LiveData<LoginOtpResponse> verifyOtp(String phone, String otp) {
        MutableLiveData<LoginOtpResponse> otpResponseLiveData = new MutableLiveData<>();
        if (phone.length() != 10) {
            errorMessage.setValue(R.string.err_phone_invalid);
        } else if(otp == null || otp.length() < 4) {
            errorMessage.setValue(R.string.err_otp_invalid);
        } else {
            Disposable d = repository.verifyOtp(new LoginOtpRequest(phone, otp))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(d1 -> loading.setValue(true))
                    .doOnError(d1 -> loading.setValue(false))
                    .doOnSuccess(d1 -> loading.setValue(false))
                    .subscribeWith(new DisposableSingleObserver<LoginOtpResponse>() {
                        @Override
                        public void onSuccess(LoginOtpResponse loginOtpResponse) {
                            if (loginOtpResponse != null && loginOtpResponse.getToken() != null && !loginOtpResponse.getToken().isEmpty()) {
                                repository.setToken(loginOtpResponse.getToken());
                                otpResponseLiveData.setValue(loginOtpResponse);
                            } else {
                                errorMessage.setValue(R.string.err_otp_verification_failed);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            errorMessage.setValue(R.string.err_something_wrong);
                        }
                    });
            disposable.add(d);
        }
        return otpResponseLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    public String getToken() {
        return repository.getToken();
    }
}
