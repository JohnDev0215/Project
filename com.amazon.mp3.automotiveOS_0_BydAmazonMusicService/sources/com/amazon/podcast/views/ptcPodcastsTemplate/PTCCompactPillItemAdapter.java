package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.CompactPillItemElement;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.R$layout;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class PTCCompactPillItemAdapter extends RecyclerView.Adapter<PTCCompactPillItemViewHolder> {
    private List<CompactPillItemElement> items;

    PTCCompactPillItemAdapter() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public PTCCompactPillItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PTCCompactPillItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_compact_pill_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull PTCCompactPillItemViewHolder ptcCompactPillItemViewHolder, int i) {
        ptcCompactPillItemViewHolder.bind(this.items.get(i));
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
