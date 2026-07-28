package com.example.zerostore.ui.products;

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
import androidx.lifecycle.ViewModelProvider;
import com.example.zerostore.R;
import com.example.zerostore.data.local.DataProvider;
import com.example.zerostore.data.model.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product product;
    private String orderMessage;
    private ProductViewModel productViewModel;
    private boolean isProductFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        int productId = getIntent().getIntExtra("productId", -1);
        
        productViewModel.getProductById(productId).observe(this, p -> {
            if (p != null) {
                this.product = p;
                buildOrderMessage();
                populateUI();
                setupButtons();
                setupFavoriteButton();
            } else {
                Toast.makeText(this, "المنتج غير موجود", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setupFavoriteButton() {
        ImageView btnFavorite = findViewById(R.id.btnFavorite);
        
        productViewModel.isFavorite(product.getId()).observe(this, isFav -> {
            isProductFavorite = (isFav != null && isFav);
            updateFavoriteIcon(btnFavorite);
        });

        btnFavorite.setOnClickListener(v -> {
            if (isProductFavorite) {
                productViewModel.removeFavorite(product.getId());
                Toast.makeText(this, "تم الحذف من المفضلة", Toast.LENGTH_SHORT).show();
            } else {
                productViewModel.addFavorite(product.getId());
                Toast.makeText(this, "تم الإضافة للمفضلة", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFavoriteIcon(ImageView view) {
        if (isProductFavorite) {
            view.setImageResource(R.drawable.ic_heart_filled);
        } else {
            view.setImageResource(R.drawable.ic_heart_outline);
        }
    }

    private void populateUI() {
        TextView tvIcon = findViewById(R.id.tvProductIcon);
        tvIcon.setText(getCategoryEmoji(product.getCategoryId()));

        TextView tvName = findViewById(R.id.tvProductName);
        tvName.setText(product.getNameAr());

        TextView tvPrice = findViewById(R.id.tvProductPrice);
        tvPrice.setText(product.getPrice() + " " + product.getCurrency());

        TextView chipDuration = findViewById(R.id.chipDuration);
        chipDuration.setText("⏳ " + product.getDuration());

        TextView chipDelivery = findViewById(R.id.chipDelivery);
        chipDelivery.setText("⚡ " + product.getDeliveryTime());

        TextView chipWarranty = findViewById(R.id.chipWarranty);
        chipWarranty.setText("🛡️ " + product.getWarranty());

        TextView tvDesc = findViewById(R.id.tvProductDesc);
        tvDesc.setText(product.getDescriptionAr());

        TextView tvReqs = findViewById(R.id.tvProductReqs);
        String reqs = product.getRequirements();
        tvReqs.setText(reqs != null && !reqs.isEmpty() ? reqs : getString(R.string.product_not_specified));

        LinearLayout notesLayout = findViewById(R.id.notesLayout);
        TextView tvNotes = findViewById(R.id.tvProductNotes);
        String notes = product.getNotesAr();
        if (notes != null && !notes.isEmpty()) {
            notesLayout.setVisibility(View.VISIBLE);
            tvNotes.setText(notes);
        } else {
            notesLayout.setVisibility(View.GONE);
        }

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
        TextView btnWhatsapp = findViewById(R.id.btnBuyWhatsapp);
        btnWhatsapp.setOnClickListener(v -> openWhatsApp());

        TextView btnTelegram = findViewById(R.id.btnBuyTelegram);
        btnTelegram.setOnClickListener(v -> openTelegram());

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
            copyToClipboard(orderMessage);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/zero19"));
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
                .setPositiveButton(getString(R.string.btn_copy), (dialog, which) -> copyOrderMessage())
                .setNegativeButton(getString(R.string.btn_cancel), null)
                .show();
    }

    private String getCategoryEmoji(int categoryId) {
        switch (categoryId) {
            case DataProvider.CAT_AI: return "🤖";
            case DataProvider.CAT_GAMING: return "🎮";
            case DataProvider.CAT_STREAMING: return "🎬";
            case DataProvider.CAT_DESIGN: return "🎨";
            case DataProvider.CAT_GIFT_CARDS: return "🎁";
            case DataProvider.CAT_SOFTWARE: return "📱";
            case DataProvider.CAT_ESIM: return "📡";
            case DataProvider.CAT_MOBILE: return "📞";
            default: return "📦";
        }
    }
}
