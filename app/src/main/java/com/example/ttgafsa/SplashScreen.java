package com.example.ttgafsa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ttgafsa.Helpers.LocalStorage;
import com.example.ttgafsa.Utility.ProgressBar;
import com.example.ttgafsa.Utility.StatusBarColorUtil;

public class SplashScreen extends AppCompatActivity {

    private static final long SPLASH_TIMEOUT = 3200; // Adjust to the total duration of all animations
    private ImageView splashImage;
    private TextView animatedText;
    ProgressBar progressBar;

    @Override
    public void onBackPressed() {
        // Prevent back press on the splash screen
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarColorUtil.setStatusBarColor(this, R.color.white);
        setContentView(R.layout.splash_screen);

        // Bind views
        splashImage = findViewById(R.id.splash_image);
        animatedText = findViewById(R.id.animated_text);
        progressBar = new ProgressBar(this);

        // Load animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeIn2 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        Animation translateUp = AnimationUtils.loadAnimation(this, R.anim.translate_up);

        // Set fillAfter to true to retain final position
        fadeIn.setFillAfter(true);
        fadeIn2.setFillAfter(true);
        zoomOut.setFillAfter(true);
        translateUp.setFillAfter(true);

        // Combine fade-in and zoom-out animations
        AnimationSet fadeInAndZoomOut = new AnimationSet(true);
        fadeInAndZoomOut.addAnimation(fadeIn);
        fadeInAndZoomOut.addAnimation(zoomOut);

        // Start fade-in and zoom-out animation
        splashImage.setVisibility(ImageView.VISIBLE);
        splashImage.startAnimation(fadeInAndZoomOut);

        fadeInAndZoomOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start translation after fade-in and zoom-out are complete
                splashImage.startAnimation(translateUp);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        translateUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Keep the image visible after translation and start text animation
                animatedText.startAnimation(fadeIn2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fadeIn2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Show progress bar after the text fade-in animation ends
                progressBar.show();

                // Delay for some time, then redirect the user
                new Handler().postDelayed(() -> {
                    // Check if the user is logged in
                    if (LocalStorage.isUserLoggedIn(getApplicationContext())) {
                        startActivity(new Intent(SplashScreen.this, Dashboard.class));
                    } else {
                        startActivity(new Intent(SplashScreen.this, Login.class));
                    }

                    // Dismiss the progress bar and finish the splash screen activity
                    progressBar.dismiss();
                    finish();
                }, SPLASH_TIMEOUT);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    } // onCreate ends
}
