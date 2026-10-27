package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.LinkNavigatorElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
final class LinkNavigatorViewHolder extends RecyclerView.ViewHolder {
    private final LinkNavigatorView linkNavigatorView;

    public LinkNavigatorViewHolder(final LinkNavigatorView linkNavigatorView) {
        super(linkNavigatorView);
        this.linkNavigatorView = linkNavigatorView;
    }

    public void bind(final LinkNavigatorElement element) {
        this.linkNavigatorView.bind(element);
    }
}
