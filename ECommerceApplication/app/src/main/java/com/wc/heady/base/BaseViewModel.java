package com.wc.heady.base;

import androidx.lifecycle.ViewModel;


import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
