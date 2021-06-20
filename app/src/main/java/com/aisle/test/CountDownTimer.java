package com.aisle.test;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class CountDownTimer {

    private TimeUnit timeUnit;
    private int startValue;
    private Disposable disposable;

    public CountDownTimer(TimeUnit timeUnit, int startValue) {
        this.timeUnit = timeUnit;
        this.startValue = startValue;
    }

    public abstract void onTick(int tickValue);

    public abstract void onFinish();

    public void start() {
        disposable = Observable.zip(Observable.range(0, startValue),
                Observable.interval(1, timeUnit), (integer, aLong) -> startValue - integer)
                .doOnComplete(this::onFinish)
                .doOnError(throwable -> Log.e("", throwable.getMessage()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTick);
    }

    public void cancel() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
