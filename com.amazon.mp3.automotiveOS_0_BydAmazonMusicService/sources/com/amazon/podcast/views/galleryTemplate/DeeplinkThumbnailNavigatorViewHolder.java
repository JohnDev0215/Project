package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class DeeplinkThumbnailNavigatorViewHolder extends RecyclerView.ViewHolder {
    private final DeeplinkThumbnailNavigatorView view;

    public DeeplinkThumbnailNavigatorViewHolder(final DeeplinkThumbnailNavigatorView itemView) {
        super(itemView);
        this.view = itemView;
    }

    public void bind(final ThumbnailNavigatorWidgetElement element) {
        this.view.bind(element);
    }

    public void handleOnViewed() {
        this.view.handleOnViewed();
    }
}
