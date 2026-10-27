package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.VisualShovelerElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
final class VisualShovelerViewHolder extends RecyclerView.ViewHolder {
    private final VisualShovelerView visualShovelerView;

    public VisualShovelerViewHolder(final VisualShovelerView itemView) {
        super(itemView);
        this.visualShovelerView = itemView;
    }

    public void bind(final VisualShovelerElement element) {
        this.visualShovelerView.bind(element);
    }
}
