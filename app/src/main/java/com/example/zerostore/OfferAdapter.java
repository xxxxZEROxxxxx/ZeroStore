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

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private ArrayList<String[]> offers; // [title, subtitle, action_type]
    private Context context;

    public OfferAdapter(Context context, ArrayList<String[]> offers) {
        this.context = context;
        this.offers = offers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] offer = offers.get(position);
        holder.tvTitle.setText(offer[0]);
        holder.tvSubtitle.setText(offer[1]);

        holder.btnAction.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductListActivity.class);
            if (offer[2].equals("offers")) {
                intent.putExtra("showOffers", true);
                intent.putExtra("categoryName", "العروض");
            } else {
                int categoryId = Integer.parseInt(offer[2]);
                intent.putExtra("categoryId", categoryId);
                intent.putExtra("categoryName", DataProvider.getCategoryName(categoryId));
            }
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSubtitle, btnAction;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvOfferTitle);
            tvSubtitle = itemView.findViewById(R.id.tvOfferSubtitle);
            btnAction = itemView.findViewById(R.id.btnOfferAction);
        }
    }
}
