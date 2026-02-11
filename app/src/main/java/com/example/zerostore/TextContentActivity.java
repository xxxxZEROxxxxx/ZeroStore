package com.example.zerostore;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TextContentActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_CONTENT = "extra_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_content);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String content = getIntent().getStringExtra(EXTRA_CONTENT);

        TextView txtTitle = findViewById(R.id.txtPageTitle);
        TextView txtContent = findViewById(R.id.txtPageContent);
        ImageView btnBack = findViewById(R.id.btnBack);

        if (title != null) {
            txtTitle.setText(title);
        }

        if (content != null) {
            txtContent.setText(content);
        }

        btnBack.setOnClickListener(v -> finish());
    }

    public void onBackClicked(View view) {
        finish();
    }
}
