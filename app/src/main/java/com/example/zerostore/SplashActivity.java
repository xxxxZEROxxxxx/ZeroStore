package com.example.zerostore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            // Check if onboarding is completed
            SharedPreferences prefs = getSharedPreferences("ZeroStorePrefs", MODE_PRIVATE);
            boolean onboardingCompleted = prefs.getBoolean("onboarding_completed", false);

            Intent intent;
            if (onboardingCompleted) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, OnboardingActivity.class);
            }
            startActivity(intent);
            finish();
        }, 1500);
    }
}
