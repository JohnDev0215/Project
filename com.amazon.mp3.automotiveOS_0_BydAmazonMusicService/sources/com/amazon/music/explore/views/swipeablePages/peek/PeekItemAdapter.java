package com.amazon.music.explore.views.swipeablePages.peek;

import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageLabelElement;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.explore.R;
import com.squareup.picasso.Picasso;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PeekItemAdapter extends RecyclerView.Adapter<PeekItemViewHolder> {
    private final Context context;
    private final List<SwipeablePageLabelElement> itemList;
    private int selectedIndex = 0;

    public PeekItemAdapter(List<SwipeablePageLabelElement> list, Context context) {
        this.itemList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public PeekItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PeekItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.peek_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull PeekItemViewHolder peekItemViewHolder, int i) {
        SwipeablePageLabelElement swipeablePageLabelElement = this.itemList.get(i);
        Picasso.get().load(swipeablePageLabelElement.icon()).into(peekItemViewHolder.image);
        peekItemViewHolder.text.setText(swipeablePageLabelElement.title());
        if (i == this.selectedIndex) {
            peekItemViewHolder.image.setVisibility(0);
            peekItemViewHolder.text.setTextColor(ContextCompat.getColor(this.context, R.color.primary));
        } else {
            peekItemViewHolder.image.setVisibility(8);
            peekItemViewHolder.text.setTextColor(ContextCompat.getColor(this.context, R.color.primary_glass_3));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    public void updateSelectedItem(int i) {
        if (i == this.selectedIndex || i > getItemCount() - 1 || i < 0) {
            return;
        }
        int i2 = this.selectedIndex;
        this.selectedIndex = i;
        notifyItemChanged(i2);
        notifyItemChanged(this.selectedIndex);
    }
}
