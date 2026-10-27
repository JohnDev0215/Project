package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.WidgetElementsInterface.v1_0.FollowsElement;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class FollowsVisualShovelerCompactViewHolder extends RecyclerView.ViewHolder {
    private final LifecycleOwner lifecycleOwner;
    private final FollowsVisualShovelerCompactView visualShovelerCompactView;

    public FollowsVisualShovelerCompactViewHolder(final LifecycleOwner lifecycleOwner, final FollowsVisualShovelerCompactView itemView) {
        super(itemView);
        this.lifecycleOwner = lifecycleOwner;
        this.visualShovelerCompactView = itemView;
    }

    public void bind(final FollowsElement element) {
        this.visualShovelerCompactView.bind(this.lifecycleOwner, element);
    }

    public void handleOnViewed() {
        this.visualShovelerCompactView.handleOnViewed();
    }
}
