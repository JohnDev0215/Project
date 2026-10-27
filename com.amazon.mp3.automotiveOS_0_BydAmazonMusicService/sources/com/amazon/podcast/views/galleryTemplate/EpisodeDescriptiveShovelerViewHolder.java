package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class EpisodeDescriptiveShovelerViewHolder extends RecyclerView.ViewHolder {
    private final EpisodeDescriptiveShovelerView view;

    public EpisodeDescriptiveShovelerViewHolder(final EpisodeDescriptiveShovelerView itemView) {
        super(itemView);
        this.view = itemView;
    }

    public void bind(final EpisodeDescriptiveShovelerElement element) {
        this.view.bind(element, null, null);
    }

    public void handleOnViewed() {
        this.view.handleOnViewed();
    }
}
