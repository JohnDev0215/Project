package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElement;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class JumpBackInVisualShovelerCompactViewHolder extends RecyclerView.ViewHolder {
    private final JumpBackInVisualShovelerCompactView jumpBackInShovelerCompactView;
    private final LifecycleOwner lifecycleOwner;

    public JumpBackInVisualShovelerCompactViewHolder(final LifecycleOwner lifecycleOwner, final JumpBackInVisualShovelerCompactView itemView) {
        super(itemView);
        this.lifecycleOwner = lifecycleOwner;
        this.jumpBackInShovelerCompactView = itemView;
    }

    public void bind(final JumpBackInElement element) {
        this.jumpBackInShovelerCompactView.bind(this.lifecycleOwner, element);
    }

    public void handleOnViewed() {
        this.jumpBackInShovelerCompactView.handleOnViewed();
    }
}
