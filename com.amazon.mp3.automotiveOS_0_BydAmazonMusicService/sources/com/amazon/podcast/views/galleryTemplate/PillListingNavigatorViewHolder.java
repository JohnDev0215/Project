package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class PillListingNavigatorViewHolder extends RecyclerView.ViewHolder {
    private final PillListingNavigatorView pillListingNavigatorView;

    public PillListingNavigatorViewHolder(final PillListingNavigatorView itemView) {
        super(itemView);
        this.pillListingNavigatorView = itemView;
    }

    public void bind(final PillListingNavigatorElement element) {
        this.pillListingNavigatorView.bind(element);
    }

    public void handleOnViewed() {
        this.pillListingNavigatorView.handleOnViewed();
    }
}
