package com.example.zerostore;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product product;
    private String orderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Back button
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Get product
        int productId = getIntent().getIntExtra("productId", -1);
        product = DataProvider.getProductById(productId);

        if (product == null) {
            finish();
            return;
        }

        // Build order message
        buildOrderMessage();

        // Populate UI
        populateUI();

        // Setup buttons
        setupButtons();

        // Setup Favorite
        setupFavoriteButton();
    }

    private void setupFavoriteButton() {
        ImageView btnFavorite = findViewById(R.id.btnFavorite);
        updateFavoriteIcon(btnFavorite);

        btnFavorite.setOnClickListener(v -> {
            boolean isFav = FavoritesManager.getInstance(this).isFavorite(product.getId());
            if (isFav) {
                FavoritesManager.getInstance(this).removeFavorite(product.getId());
                Toast.makeText(this, "تم الحذف من المفضلة", Toast.LENGTH_SHORT).show();
            } else {
                FavoritesManager.getInstance(this).addFavorite(product.getId());
                Toast.makeText(this, "تم الإضافة للمفضلة", Toast.LENGTH_SHORT).show();
            }
            updateFavoriteIcon(btnFavorite);
        });
    }

    private void updateFavoriteIcon(ImageView view) {
        boolean isFav = FavoritesManager.getInstance(this).isFavorite(product.getId());
        if (isFav) {
            view.setImageResource(R.drawable.ic_heart_filled);
        } else {
            view.setImageResource(R.drawable.ic_heart_outline);
        }
    }

    private void populateUI() {
        // Category emoji as icon
        TextView tvIcon = findViewById(R.id.tvProductIcon);
        tvIcon.setText(getCategoryEmoji(product.getCategoryId()));

        // Product Name
        TextView tvName = findViewById(R.id.tvProductName);
        tvName.setText(product.getNameAr());

        // Price
        TextView tvPrice = findViewById(R.id.tvProductPrice);
        tvPrice.setText(product.getPrice() + " " + product.getCurrency());

        // Chips
        TextView chipDuration = findViewById(R.id.chipDuration);
        chipDuration.setText("⏳ " + product.getDuration());

        TextView chipDelivery = findViewById(R.id.chipDelivery);
        chipDelivery.setText("⚡ " + product.getDeliveryTime());

        TextView chipWarranty = findViewById(R.id.chipWarranty);
        chipWarranty.setText("🛡️ " + product.getWarranty());

        // Description
        TextView tvDesc = findViewById(R.id.tvProductDesc);
        tvDesc.setText(product.getDescriptionAr());

        // Requirements
        TextView tvReqs = findViewById(R.id.tvProductReqs);
        String reqs = product.getRequirements();
        tvReqs.setText(reqs != null && !reqs.isEmpty() ? reqs : getString(R.string.product_not_specified));

        // Notes
        LinearLayout notesLayout = findViewById(R.id.notesLayout);
        TextView tvNotes = findViewById(R.id.tvProductNotes);
        String notes = product.getNotesAr();
        if (notes != null && !notes.isEmpty()) {
            notesLayout.setVisibility(View.VISIBLE);
            tvNotes.setText(notes);
        } else {
            notesLayout.setVisibility(View.GONE);
        }

        // Toolbar title
        TextView tvToolbar = findViewById(R.id.tvDetailToolbar);
        tvToolbar.setText(product.getNameAr());
    }

    private void buildOrderMessage() {
        String categoryName = DataProvider.getCategoryName(product.getCategoryId());
        String requirements = product.getRequirements();
        if (requirements == null || requirements.isEmpty()) {
            requirements = getString(R.string.product_not_specified);
        }

        orderMessage = getString(R.string.order_message_template,
                product.getNameAr(),
                categoryName,
                product.getPrice(),
                product.getCurrency(),
                product.getDuration(),
                product.getDeliveryTime(),
                product.getWarranty(),
                requirements);
    }

    private void setupButtons() {
        // WhatsApp Button
        TextView btnWhatsapp = findViewById(R.id.btnBuyWhatsapp);
        btnWhatsapp.setOnClickListener(v -> openWhatsApp());

        // Telegram Button
        TextView btnTelegram = findViewById(R.id.btnBuyTelegram);
        btnTelegram.setOnClickListener(v -> openTelegram());

        // Copy Order Button
        TextView btnCopy = findViewById(R.id.btnCopyOrder);
        btnCopy.setOnClickListener(v -> copyOrderMessage());
    }

    private void openWhatsApp() {
        try {
            String encodedMessage = Uri.encode(orderMessage);
            String url = "https://wa.me/972567482488?text=" + encodedMessage;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            showAppNotInstalledDialog();
        }
    }

    private void openTelegram() {
        try {
            // Copy message first, then open Telegram
            copyToClipboard(orderMessage);
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://t.me/zero19"));
            startActivity(intent);
            Toast.makeText(this, getString(R.string.order_copied), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            showAppNotInstalledDialog();
        }
    }

    private void copyOrderMessage() {
        copyToClipboard(orderMessage);
        Toast.makeText(this, getString(R.string.order_copied), Toast.LENGTH_SHORT).show();
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("order_message", text);
        clipboard.setPrimaryClip(clip);
    }

    private void showAppNotInstalledDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_not_installed_title))
                .setMessage(getString(R.string.app_not_installed_message))
                .setPositiveButton(getString(R.string.btn_copy), (dialog, which) -> {
                    copyOrderMessage();
                })
                .setNegativeButton(getString(R.string.btn_cancel), null)
                .show();
    }

    private String getCategoryEmoji(int categoryId) {
        switch (categoryId) {
            case DataProvider.CAT_AI:
                return "🤖";
            case DataProvider.CAT_GAMING:
                return "🎮";
            case DataProvider.CAT_STREAMING:
                return "🎬";
            case DataProvider.CAT_DESIGN:
                return "🎨";
            case DataProvider.CAT_GIFT_CARDS:
                return "🎁";
            case DataProvider.CAT_SOFTWARE:
                return "📱";
            case DataProvider.CAT_ESIM:
                return "📡";
            case DataProvider.CAT_MOBILE:
                return "📞";
            default:
                return "📦";
        }
    }
}
