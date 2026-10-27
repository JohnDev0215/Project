package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class EpisodeRowItemViewHolder extends RecyclerView.ViewHolder {
    private final EpisodeRowItemView episodeRowItemView;

    public EpisodeRowItemViewHolder(@NonNull final EpisodeRowItemView episodeRowItemView) {
        super(episodeRowItemView);
        this.episodeRowItemView = episodeRowItemView;
    }

    public void bind(final EpisodeRowItemElement element) {
        this.episodeRowItemView.bind(element);
    }
}
