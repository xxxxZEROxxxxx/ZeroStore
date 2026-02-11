package com.example.zerostore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Product> products;
    private ArrayList<Product> productsFull; // for search filter
    private Context context;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = new ArrayList<>(products);
        this.productsFull = new ArrayList<>(products);
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

        // Set emoji icon based on category
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
        if (query == null || query.isEmpty()) {
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
