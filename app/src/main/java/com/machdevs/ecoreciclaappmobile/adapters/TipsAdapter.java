package com.machdevs.ecoreciclaappmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.models.TipItem;
import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipViewHolder> {

    private List<TipItem> tipItems;
    private int[] gradients = {
            R.drawable.gradient_tip_1,
            R.drawable.gradient_tip_2,
            R.drawable.gradient_tip_3,
            R.drawable.gradient_tip_4,
            R.drawable.gradient_tip_5
    };

    public TipsAdapter(List<TipItem> tipItems) {
        this.tipItems = tipItems;
    }

    @NonNull
    @Override
    public TipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tips, parent, false);
        return new TipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipViewHolder holder, int position) {
        TipItem item = tipItems.get(position);
        holder.bind(item, gradients[position % gradients.length]);
    }

    @Override
    public int getItemCount() {
        return tipItems.size();
    }

    static class TipViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipTitle, tvTipContent;
        View itemView;

        TipViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTipTitle = itemView.findViewById(R.id.tvTipTitle);
            tvTipContent = itemView.findViewById(R.id.tvTipContent);
        }

        void bind(TipItem item, int gradientDrawable) {
            tvTipTitle.setText(item.getTitle());
            tvTipContent.setText(item.getContent());
            itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), gradientDrawable));
        }
    }
}