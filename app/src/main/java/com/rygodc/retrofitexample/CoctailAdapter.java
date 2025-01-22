package com.rygodc.retrofitexample;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CoctailAdapter extends RecyclerView.Adapter<CoctailAdapter.CoctailViewHolder> {

    private List<Drinks.Coctail> coctailList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Drinks.Coctail cocktail);
    }

    public CoctailAdapter(List<Drinks.Coctail> coctailList, OnItemClickListener listener) {
        this.coctailList = coctailList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CoctailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coctail_cardview, parent, false);
        return new CoctailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoctailViewHolder holder, int position) {
        Drinks.Coctail coctail = coctailList.get(position);
        holder.bind(coctail, listener);
    }

    @Override
    public int getItemCount() {
        return coctailList.size();
    }

    public static class CoctailViewHolder extends RecyclerView.ViewHolder {
        private ImageView coctailImage;
        private TextView coctailName;

        public CoctailViewHolder(@NonNull View itemView) {
            super(itemView);
            coctailImage = itemView.findViewById(R.id.coctailImage);
            coctailName = itemView.findViewById(R.id.coctailName);
        }

        public void bind(final Drinks.Coctail coctail, final OnItemClickListener listener) {
            coctailName.setText(coctail.coctailName);
            Glide.with(itemView.getContext()).load(coctail.coctailImageUrl).into(coctailImage);

            itemView.setScaleX(0.95f);
            itemView.setScaleY(0.95f);
            itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), CoctailDetailActivity.class);
                intent.putExtra("coctailName", coctail.coctailName);
                intent.putExtra("coctailImageUrl", coctail.coctailImageUrl);
                intent.putExtra("coctailId", coctail.coctailIid);
                itemView.getContext().startActivity(intent);
            });
        }


    }
}
