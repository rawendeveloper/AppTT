package com.example.ttgafsa.Utility;

import static android.os.Build.VERSION_CODES.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import com.example.ttgafsa.R;

public class ProgressBar {


    private final Dialog dialog;

    public ProgressBar(Context context) {
        dialog = new Dialog(context, com.example.ttgafsa.R.style.CustomDialog);
        dialog.setContentView(com.example.ttgafsa.R.layout.progress_bar);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
            dialog.dismiss();
    }




}
