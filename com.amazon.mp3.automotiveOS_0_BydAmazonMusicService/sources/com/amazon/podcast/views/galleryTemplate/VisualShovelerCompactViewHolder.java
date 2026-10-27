package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class VisualShovelerCompactViewHolder extends RecyclerView.ViewHolder {
    private final VisualShovelerCompactView visualShovelerCompactView;

    public VisualShovelerCompactViewHolder(final VisualShovelerCompactView itemView) {
        super(itemView);
        this.visualShovelerCompactView = itemView;
    }

    public void bind(final VisualShovelerCompactElement element) {
        this.visualShovelerCompactView.bind(element);
    }

    public void handleOnViewed() {
        this.visualShovelerCompactView.handleOnViewed();
    }
}
