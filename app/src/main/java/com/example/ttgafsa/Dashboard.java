package com.example.ttgafsa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ttgafsa.Helpers.LocalStorage;
import com.example.ttgafsa.Utility.StatusBarColorUtil;

public class Dashboard extends AppCompatActivity {

    TextView Logout_btn;

    ConstraintLayout Ajout_btn,Collect_btn,Recherche_btn;



    @Override
    public void onBackPressed() {
        // Prevent back press on the splash screen
        finishAffinity();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarColorUtil.setStatusBarColor(this, R.color.white);
        setContentView(R.layout.dashboard);

        bindingViews();


        Logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // In your logout method or button click listener
                LocalStorage.logOutUser(Dashboard.this);

                Toast.makeText(Dashboard.this, "Logging out..", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Dashboard.this, Login.class));
                        finish();
                    }
                },1500);



            }
        });



        Ajout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Dashboard.this, AjoutSites.class));

            }
        });



        Collect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, CollectSites.class));

            }
        });



        Recherche_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, RechercheSites.class));

            }
        });

    } //onCreate




    private void bindingViews() {
        Logout_btn = findViewById(R.id.Logout_btn);
        Ajout_btn = findViewById(R.id.Ajout_btn);
        Collect_btn = findViewById(R.id.Collect_btn);
        Recherche_btn = findViewById(R.id.Recherche_btn);
    }


}