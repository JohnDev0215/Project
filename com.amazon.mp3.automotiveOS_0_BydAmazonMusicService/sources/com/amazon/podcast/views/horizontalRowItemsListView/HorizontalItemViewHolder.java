package com.amazon.podcast.views.horizontalRowItemsListView;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class HorizontalItemViewHolder extends RecyclerView.ViewHolder {
    private final HorizontalRowItemView horizontalRowItemView;

    public HorizontalItemViewHolder(@NonNull final HorizontalRowItemView horizontalRowItemView) {
        super(horizontalRowItemView);
        this.horizontalRowItemView = horizontalRowItemView;
    }

    public void bind(final int position, final HorizontalItemElement element) {
        this.horizontalRowItemView.bind(position, element);
    }
}
