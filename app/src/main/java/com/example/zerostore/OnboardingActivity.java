package com.example.zerostore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button btnNext, btnSkip;
    private ArrayList<OnboardingItem> onboardingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        setupOnboardingItems();

        OnboardingAdapter adapter = new OnboardingAdapter(onboardingItems);
        viewPager.setAdapter(adapter);

        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() + 1 < adapter.getItemCount()) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                finishOnboarding();
            }
        });

        btnSkip.setOnClickListener(v -> finishOnboarding());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == adapter.getItemCount() - 1) {
                    btnNext.setText("ابدأ الآن");
                } else {
                    btnNext.setText("التالي");
                }
            }
        });
    }

    private void setupOnboardingItems() {
        onboardingItems = new ArrayList<>();
        onboardingItems.add(new OnboardingItem(
                "أهلاً بك في Zero Store",
                "وجهتك الأولى للمنتجات الرقمية والخدمات المميزة.",
                R.drawable.onboarding_image_1));
        onboardingItems.add(new OnboardingItem(
                "خدمات متنوعة",
                "من شحن الألعاب إلى الاشتراكات وخدمات التصميم، كل ما تحتاجه في مكان واحد.",
                R.drawable.onboarding_image_2));
        onboardingItems.add(new OnboardingItem(
                "أسعار منافسة",
                "نوفر لك أفضل الأسعار مع ضمان الجودة وسرعة التنفيذ.",
                R.drawable.onboarding_image_3));
    }

    private void finishOnboarding() {
        SharedPreferences prefs = getSharedPreferences("ZeroStorePrefs", MODE_PRIVATE);
        prefs.edit().putBoolean("onboarding_completed", true).apply();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    // Inner classes for Item and Adapter
    static class OnboardingItem {
        String title, description;
        int imageResId;

        OnboardingItem(String title, String description, int imageResId) {
            this.title = title;
            this.description = description;
            this.imageResId = imageResId;
        }
    }

    class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

        private ArrayList<OnboardingItem> items;

        OnboardingAdapter(ArrayList<OnboardingItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OnboardingViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboarding, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class OnboardingViewHolder extends RecyclerView.ViewHolder {
            TextView textTitle, textDescription;
            ImageView imageOnboarding;

            OnboardingViewHolder(@NonNull View itemView) {
                super(itemView);
                textTitle = itemView.findViewById(R.id.tvTitle);
                textDescription = itemView.findViewById(R.id.tvDescription);
                imageOnboarding = itemView.findViewById(R.id.imgSlide);
            }

            void bind(OnboardingItem item) {
                textTitle.setText(item.title);
                textDescription.setText(item.description);
                imageOnboarding.setImageResource(item.imageResId);
            }
        }
    }
}
