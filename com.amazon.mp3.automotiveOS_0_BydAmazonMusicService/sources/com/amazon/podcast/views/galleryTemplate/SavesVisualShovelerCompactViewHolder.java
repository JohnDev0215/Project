package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.WidgetElementsInterface.v1_0.SavesElement;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class SavesVisualShovelerCompactViewHolder extends RecyclerView.ViewHolder {
    private final LifecycleOwner lifecycleOwner;
    private final SavesVisualShovelerCompactView visualShovelerCompactView;

    public SavesVisualShovelerCompactViewHolder(final LifecycleOwner lifecycleOwner, final SavesVisualShovelerCompactView itemView) {
        super(itemView);
        this.lifecycleOwner = lifecycleOwner;
        this.visualShovelerCompactView = itemView;
    }

    public void bind(final SavesElement element) {
        this.visualShovelerCompactView.bind(this.lifecycleOwner, element);
    }

    public void handleOnViewed() {
        this.visualShovelerCompactView.handleOnViewed();
    }
}
