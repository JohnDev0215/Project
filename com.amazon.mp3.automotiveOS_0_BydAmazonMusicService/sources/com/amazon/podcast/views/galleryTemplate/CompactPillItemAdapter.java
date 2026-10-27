package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElement;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class CompactPillItemAdapter extends RecyclerView.Adapter<CompactPillItemViewHolder> {
    private List<CompactPillItemElement> items;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;

    CompactPillItemAdapter() {
    }

    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    public void setMethodsDispatcher(final MethodsDispatcher methodsDispatcher) {
        this.methodsDispatcher = methodsDispatcher;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public CompactPillItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int position) {
        return new CompactPillItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_compact_pill_item, viewGroup, false), this.ownerId, this.methodsDispatcher);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final CompactPillItemViewHolder viewHolder, final int position) {
        viewHolder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CompactPillItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<CompactPillItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
