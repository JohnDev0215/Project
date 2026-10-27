package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.PillItemElementInterface.v1_0.PillItemElement;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class PillItemAdapter extends RecyclerView.Adapter<PillItemViewHolder> {
    private List<PillItemElement> items;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;

    public PillItemAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public PillItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int position) {
        return new PillItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_pill_item, viewGroup, false), this.ownerId, this.methodsDispatcher);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final PillItemViewHolder viewHolder, final int position) {
        viewHolder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<PillItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<PillItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
