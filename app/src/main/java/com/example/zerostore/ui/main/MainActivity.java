package com.example.zerostore.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zerostore.R;
import com.example.zerostore.data.local.DataProvider;
import com.example.zerostore.ui.adapters.BannerAdapter;
import com.example.zerostore.ui.adapters.CategoryAdapter;
import com.example.zerostore.ui.adapters.OfferAdapter;
import com.example.zerostore.ui.adapters.ProductAdapter;
import com.example.zerostore.ui.common.TextContentActivity;
import com.example.zerostore.ui.favorites.FavoritesActivity;
import com.example.zerostore.ui.search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View homeSection, categoriesSection, offersSection, supportSection;
    private BottomNavigationView bottomNav;
    
    private MainViewModel mainViewModel;
    private ProductAdapter topAdapter;
    private CategoryAdapter categoryAdapterHome;
    private CategoryAdapter categoryAdapterAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        homeSection = findViewById(R.id.homeSection);
        categoriesSection = findViewById(R.id.categoriesSection);
        offersSection = findViewById(R.id.offersSection);
        supportSection = findViewById(R.id.supportSection);

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

        setupHome();
        setupCategories();
        setupOffers();
        setupSupport();

        showSection(homeSection);
        
        observeViewModel();
    }

    private void observeViewModel() {
        mainViewModel.getTopProducts().observe(this, products -> {
            if (products != null && topAdapter != null) {
                topAdapter.updateList(products);
            }
        });
        
        mainViewModel.getCategories().observe(this, categories -> {
            if (categories != null) {
                if (categoryAdapterHome != null) categoryAdapterHome.updateList(categories);
                if (categoryAdapterAll != null) categoryAdapterAll.updateList(categories);
            }
        });
    }

    private void showSection(View section) {
        homeSection.setVisibility(View.GONE);
        categoriesSection.setVisibility(View.GONE);
        offersSection.setVisibility(View.GONE);
        supportSection.setVisibility(View.GONE);
        section.setVisibility(View.VISIBLE);
    }

    private void setupHome() {
        findViewById(R.id.btnSearch).setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        findViewById(R.id.btnFavorites).setOnClickListener(v -> startActivity(new Intent(this, FavoritesActivity.class)));

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
        topAdapter = new ProductAdapter(this, new ArrayList<>());
        rvTopProducts.setLayoutManager(new LinearLayoutManager(this));
        rvTopProducts.setNestedScrollingEnabled(false);
        rvTopProducts.setAdapter(topAdapter);

        // Categories
        RecyclerView rvHomeCategories = findViewById(R.id.rvHomeCategories);
        categoryAdapterHome = new CategoryAdapter(this, new ArrayList<>());
        rvHomeCategories.setLayoutManager(new GridLayoutManager(this, 3));
        rvHomeCategories.setNestedScrollingEnabled(false);
        rvHomeCategories.setAdapter(categoryAdapterHome);
    }

    private void setupCategories() {
        RecyclerView rvCategories = findViewById(R.id.rvCategories);
        categoryAdapterAll = new CategoryAdapter(this, new ArrayList<>());
        rvCategories.setLayoutManager(new GridLayoutManager(this, 2));
        rvCategories.setAdapter(categoryAdapterAll);
    }

    private void setupOffers() {
        RecyclerView rvOffers = findViewById(R.id.rvOffers);
        ArrayList<String[]> offers = new ArrayList<>();
        offers.add(new String[] { "🔥 عروض الذكاء الاصطناعي", "ChatGPT Plus بـ24₪ | Gemini Pro سنة بـ40₪ | Super Grok بـ60₪", String.valueOf(DataProvider.CAT_AI) });
        offers.add(new String[] { "📡 شرائح إلكترونية eSIM", "Wecom 500GB بـ53₪ | Cellcom 400GB بـ65₪ | 5G متاح!", String.valueOf(DataProvider.CAT_ESIM) });
        offers.add(new String[] { "📞 حزم جوال بأسعار مميزة", "150 دقيقة بـ14₪ | 600 دقيقة بـ30₪ | إنترنت واتساب بـ5₪", String.valueOf(DataProvider.CAT_MOBILE) });
        offers.add(new String[] { "🎮 شحن ألعاب", "شدات PUBG من 16₪ | Free Fire Diamonds بأسعار لا تُفوّت!", String.valueOf(DataProvider.CAT_GAMING) });
        offers.add(new String[] { "🧩 سوشال ميديا", "Telegram Premium 3 أشهر بـ55₪ | Snapchat+ بـ45₪ | TikTok Coins", String.valueOf(DataProvider.CAT_SOFTWARE) });
        offers.add(new String[] { "🎬 بث ومشاهدة", "شاهد VIP شهر بـ12₪ فقط! | Netflix | Disney+", String.valueOf(DataProvider.CAT_STREAMING) });
        offers.add(new String[] { "💼 تصميم ومونتاج", "CapCut Pro بـ15₪ | Adobe بـ25₪ | Canva Pro بـ15₪", String.valueOf(DataProvider.CAT_DESIGN) });

        OfferAdapter offerAdapter = new OfferAdapter(this, offers);
        rvOffers.setLayoutManager(new LinearLayoutManager(this));
        rvOffers.setAdapter(offerAdapter);
    }

    private void setupSupport() {
        findViewById(R.id.btnSupportWhatsapp).setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/972567482488")));
            } catch (Exception e) {
                Toast.makeText(this, "واتساب غير مثبت", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnSupportTelegram).setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/zero19")));
            } catch (Exception e) {
                Toast.makeText(this, "تيليجرام غير مثبت", Toast.LENGTH_SHORT).show();
            }
        });

        setupFaqItem(R.id.faqQ1, R.id.faqA1);
        setupFaqItem(R.id.faqQ2, R.id.faqA2);
        setupFaqItem(R.id.faqQ3, R.id.faqA3);
        setupFaqItem(R.id.faqQ4, R.id.faqA4);

        setupPolicyLink(R.id.btnAbout, getString(R.string.about_title), getString(R.string.about_text));
        setupPolicyLink(R.id.btnPrivacy, getString(R.string.policy_privacy_title), getString(R.string.policy_privacy_text));
        setupPolicyLink(R.id.btnTerms, getString(R.string.policy_terms_title), getString(R.string.policy_terms_text));
        setupPolicyLink(R.id.btnRefund, getString(R.string.policy_refund_title), getString(R.string.policy_refund_text));
    }

    private void setupFaqItem(int questionId, int answerId) {
        TextView question = findViewById(questionId);
        TextView answer = findViewById(answerId);
        question.setOnClickListener(v -> answer.setVisibility(answer.getVisibility() == View.GONE ? View.VISIBLE : View.GONE));
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
