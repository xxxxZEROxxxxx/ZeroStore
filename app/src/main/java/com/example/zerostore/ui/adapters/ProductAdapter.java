package com.example.zerostore.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zerostore.R;
import com.example.zerostore.data.model.Product;
import com.example.zerostore.data.local.DataProvider;
import com.example.zerostore.ui.products.ProductDetailsActivity;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> products;
    private List<Product> productsFull;
    private Context context;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        if (products != null) {
            this.products = new ArrayList<>(products);
            this.productsFull = new ArrayList<>(products);
        } else {
            this.products = new ArrayList<>();
            this.productsFull = new ArrayList<>();
        }
    }

    public void updateList(List<Product> newProducts) {
        if (newProducts != null) {
            this.products = new ArrayList<>(newProducts);
            this.productsFull = new ArrayList<>(newProducts);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);

        String emoji = getCategoryEmoji(product.getCategoryId());
        holder.tvEmoji.setText(emoji);

        holder.tvName.setText(product.getNameAr());
        holder.tvPrice.setText(product.getPrice() + " " + product.getCurrency());
        holder.tvBadge.setText(product.getDeliveryTime());

        holder.btnDetails.setOnClickListener(v -> openDetails(product));
        holder.itemView.setOnClickListener(v -> openDetails(product));
    }

    private void openDetails(Product product) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra("productId", product.getId());
        context.startActivity(intent);
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

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void filter(String query) {
        products.clear();
        if (query == null || query.trim().isEmpty()) {
            products.addAll(productsFull);
        } else {
            String lowerQuery = query.toLowerCase();
            for (Product p : productsFull) {
                if (p.getNameAr().toLowerCase().contains(lowerQuery)) {
                    products.add(p);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmoji, tvName, tvPrice, tvBadge, btnDetails;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmoji = itemView.findViewById(R.id.tvProductEmoji);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            tvBadge = itemView.findViewById(R.id.tvProductBadge);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
}
