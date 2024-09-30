package com.example.ttgafsa.Utility;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ttgafsa.R;

public class NetworkChangeListener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (!Common.isConnectedToInternet(context)) {           //internet is not connected


            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet_dialog, null);
            builder.setView(layout_dialog);


            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ConstraintLayout btnRetry = layout_dialog.findViewById(R.id.retry_btn);
            LottieAnimationView errorLogo = layout_dialog.findViewById(R.id.errorLogo);




            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);




            //btn retry
            btnRetry.setOnClickListener(view -> {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        dialog.dismiss();
                        onReceive(context, intent);

                    }},600);
            });


            //show dialog
            dialog.show();

        }

    }
}
