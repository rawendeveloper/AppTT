package com.example.ttgafsa;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ttgafsa.Utility.StatusBarColorUtil;

public class CollectSites extends AppCompatActivity {

    ImageView returnButton;


    @Override
    public void onBackPressed() {
        // Prevent back press on the splash screen
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarColorUtil.setStatusBarColor(this, R.color.white);

        EdgeToEdge.enable(this);
        setContentView(R.layout.collect_sites);

        bindingViews();






        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    } //onCreate






    private void bindingViews() {
        returnButton = findViewById(R.id.return_row);
    }







}