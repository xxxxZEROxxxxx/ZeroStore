package com.example.zerostore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View homeSection, categoriesSection, offersSection, supportSection;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find sections
        homeSection = findViewById(R.id.homeSection);
        categoriesSection = findViewById(R.id.categoriesSection);
        offersSection = findViewById(R.id.offersSection);
        supportSection = findViewById(R.id.supportSection);

        // Bottom Navigation
        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                showSection(homeSection);
            } else if (id == R.id.nav_categories) {
                showSection(categoriesSection);
            } else if (id == R.id.nav_offers) {
                showSection(offersSection);
            } else if (id == R.id.nav_support) {
                showSection(supportSection);
            }
            return true;
        });

        // Setup all sections
        setupHome();
        setupCategories();
        setupOffers();
        setupSupport();

        // Default to Home
        showSection(homeSection);
    }

    private void showSection(View section) {
        homeSection.setVisibility(View.GONE);
        categoriesSection.setVisibility(View.GONE);
        offersSection.setVisibility(View.GONE);
        supportSection.setVisibility(View.GONE);
        section.setVisibility(View.VISIBLE);
    }

    // ======================== HOME ========================
    private void setupHome() {
        // Search button
        findViewById(R.id.btnSearch).setOnClickListener(v -> {
            startActivity(new Intent(this, SearchActivity.class));
        });

        // Favorites button
        findViewById(R.id.btnFavorites).setOnClickListener(v -> {
            startActivity(new Intent(this, FavoritesActivity.class));
        });

        // Search button was replaced by the Logo in the header xml

        // Banners
        RecyclerView rvBanners = findViewById(R.id.rvBanners);
        ArrayList<String[]> bannerData = new ArrayList<>();
        bannerData.add(new String[] { getString(R.string.banner_1_title), getString(R.string.banner_1_subtitle) });
        bannerData.add(new String[] { getString(R.string.banner_2_title), getString(R.string.banner_2_subtitle) });
        bannerData.add(new String[] { getString(R.string.banner_3_title), getString(R.string.banner_3_subtitle) });
        BannerAdapter bannerAdapter = new BannerAdapter(this, bannerData);
        rvBanners.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvBanners.setAdapter(bannerAdapter);

        // Top Products
        RecyclerView rvTopProducts = findViewById(R.id.rvTopProducts);
        ArrayList<Product> topProducts = DataProvider.getTopProducts();
        ProductAdapter topAdapter = new ProductAdapter(this, topProducts);
        rvTopProducts.setLayoutManager(new LinearLayoutManager(this));
        rvTopProducts.setNestedScrollingEnabled(false);
        rvTopProducts.setAdapter(topAdapter);

        // Categories
        RecyclerView rvHomeCategories = findViewById(R.id.rvHomeCategories);
        ArrayList<Category> categories = DataProvider.getCategories();
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        rvHomeCategories.setLayoutManager(new GridLayoutManager(this, 3));
        rvHomeCategories.setNestedScrollingEnabled(false);
        rvHomeCategories.setAdapter(categoryAdapter);
    }

    // ======================== CATEGORIES ========================
    private void setupCategories() {
        RecyclerView rvCategories = findViewById(R.id.rvCategories);
        ArrayList<Category> categories = DataProvider.getCategories();
        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        rvCategories.setLayoutManager(new GridLayoutManager(this, 2));
        rvCategories.setAdapter(adapter);
    }

    // ======================== OFFERS ========================
    private void setupOffers() {
        RecyclerView rvOffers = findViewById(R.id.rvOffers);
        ArrayList<String[]> offers = new ArrayList<>();
        // [title, subtitle, action_type (categoryId or "offers")]
        offers.add(new String[] { "🔥 عروض الذكاء الاصطناعي",
                "ChatGPT Plus بـ24₪ | Gemini Pro سنة بـ40₪ | Super Grok بـ60₪", String.valueOf(DataProvider.CAT_AI) });
        offers.add(new String[] { "📡 شرائح إلكترونية eSIM", "Wecom 500GB بـ53₪ | Cellcom 400GB بـ65₪ | 5G متاح!",
                String.valueOf(DataProvider.CAT_ESIM) });
        offers.add(new String[] { "📞 حزم جوال بأسعار مميزة", "150 دقيقة بـ14₪ | 600 دقيقة بـ30₪ | إنترنت واتساب بـ5₪",
                String.valueOf(DataProvider.CAT_MOBILE) });
        offers.add(new String[] { "🎮 شحن ألعاب", "شدات PUBG من 16₪ | Free Fire Diamonds بأسعار لا تُفوّت!",
                String.valueOf(DataProvider.CAT_GAMING) });
        offers.add(new String[] { "🧩 سوشال ميديا", "Telegram Premium 3 أشهر بـ55₪ | Snapchat+ بـ45₪ | TikTok Coins",
                String.valueOf(DataProvider.CAT_SOFTWARE) });
        offers.add(new String[] { "🎬 بث ومشاهدة", "شاهد VIP شهر بـ12₪ فقط! | Netflix | Disney+",
                String.valueOf(DataProvider.CAT_STREAMING) });
        offers.add(new String[] { "💼 تصميم ومونتاج", "CapCut Pro بـ15₪ | Adobe بـ25₪ | Canva Pro بـ15₪",
                String.valueOf(DataProvider.CAT_DESIGN) });

        OfferAdapter offerAdapter = new OfferAdapter(this, offers);
        rvOffers.setLayoutManager(new LinearLayoutManager(this));
        rvOffers.setAdapter(offerAdapter);
    }

    // ======================== SUPPORT ========================
    private void setupSupport() {
        // WhatsApp Support
        TextView btnWhatsapp = findViewById(R.id.btnSupportWhatsapp);
        btnWhatsapp.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://wa.me/972567482488"));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "واتساب غير مثبت", Toast.LENGTH_SHORT).show();
            }
        });

        // Telegram Support
        TextView btnTelegram = findViewById(R.id.btnSupportTelegram);
        btnTelegram.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://t.me/zero19"));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "تيليجرام غير مثبت", Toast.LENGTH_SHORT).show();
            }
        });

        // FAQ Expandable
        setupFaqItem(R.id.faqQ1, R.id.faqA1);
        setupFaqItem(R.id.faqQ2, R.id.faqA2);
        setupFaqItem(R.id.faqQ3, R.id.faqA3);
        setupFaqItem(R.id.faqQ4, R.id.faqA4);

        // Policies & About
        setupPolicyLink(R.id.btnAbout, getString(R.string.about_title), getString(R.string.about_text));
        setupPolicyLink(R.id.btnPrivacy, getString(R.string.policy_privacy_title),
                getString(R.string.policy_privacy_text));
        setupPolicyLink(R.id.btnTerms, getString(R.string.policy_terms_title), getString(R.string.policy_terms_text));
        setupPolicyLink(R.id.btnRefund, getString(R.string.policy_refund_title),
                getString(R.string.policy_refund_text));
    }

    private void setupFaqItem(int questionId, int answerId) {
        TextView question = findViewById(questionId);
        TextView answer = findViewById(answerId);
        question.setOnClickListener(v -> {
            if (answer.getVisibility() == View.GONE) {
                answer.setVisibility(View.VISIBLE);
            } else {
                answer.setVisibility(View.GONE);
            }
        });
    }

    private void setupPolicyLink(int viewId, String title, String content) {
        findViewById(viewId).setOnClickListener(v -> {
            Intent intent = new Intent(this, TextContentActivity.class);
            intent.putExtra(TextContentActivity.EXTRA_TITLE, title);
            intent.putExtra(TextContentActivity.EXTRA_CONTENT, content);
            startActivity(intent);
        });
    }
}