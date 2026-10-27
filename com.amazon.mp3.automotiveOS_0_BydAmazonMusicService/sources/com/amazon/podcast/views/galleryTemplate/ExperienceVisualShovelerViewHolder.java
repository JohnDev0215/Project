package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class ExperienceVisualShovelerViewHolder extends RecyclerView.ViewHolder {
    private final ExperienceVisualShovelerView experienceVisualShovelerView;

    public ExperienceVisualShovelerViewHolder(final ExperienceVisualShovelerView itemView) {
        super(itemView);
        this.experienceVisualShovelerView = itemView;
    }

    public void bind(final ExperienceVisualShovelerElement element) {
        this.experienceVisualShovelerView.bind(element);
    }

    public void handleOnViewed() {
        this.experienceVisualShovelerView.handleOnViewed();
    }
}
