package com.example.zerostore.ui.common;
import com.example.zerostore.R;
import com.example.zerostore.data.model.*;
import com.example.zerostore.data.local.*;
import com.example.zerostore.ui.adapters.*;
import com.example.zerostore.ui.main.*;
import com.example.zerostore.ui.products.*;
import com.example.zerostore.ui.search.*;
import com.example.zerostore.ui.favorites.*;
import com.example.zerostore.ui.common.*;

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

