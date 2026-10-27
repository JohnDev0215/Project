package com.amazon.podcast.views.deeplinkThumbnailNavigator;

import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class DeeplinkThumbnailNavigatorItemViewHolder extends RecyclerView.ViewHolder {
    private final DeeplinkThumbnailNavigatorItemView view;

    public DeeplinkThumbnailNavigatorItemViewHolder(final DeeplinkThumbnailNavigatorItemView itemView) {
        super(itemView);
        this.view = itemView;
    }

    public void bind(final ThumbnailNavigatorItemElement element) {
        this.view.bind(element);
    }
}
