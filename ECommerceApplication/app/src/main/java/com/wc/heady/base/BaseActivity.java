package com.wc.heady.base;

import android.app.Dialog;


import com.wc.heady.utils.ProgressBarUtils;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {
    private Dialog dialog;

    public void showProgress() {
        dialog = ProgressBarUtils.showProgress(this);
    }

    public void hideProgress() {
        if (dialog != null) {
            ProgressBarUtils.hideProgress(dialog);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog = null;
    }
}
