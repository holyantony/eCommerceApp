package com.wc.heady.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.wc.heady.R;

import java.util.Objects;

public class ProgressBarUtils {

    public static Dialog showProgress(Context context) {
        Dialog progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.custom_progress_dialog);
        Objects.requireNonNull(progressDialog.getWindow())
               .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);

        progressDialog.show();
        return progressDialog;
    }

    public static boolean hideProgress(Dialog progressDialog) {
        boolean isProgressDismiss;
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            isProgressDismiss = true;
        } else {
            isProgressDismiss = false;
        }
        return isProgressDismiss;
    }
}
