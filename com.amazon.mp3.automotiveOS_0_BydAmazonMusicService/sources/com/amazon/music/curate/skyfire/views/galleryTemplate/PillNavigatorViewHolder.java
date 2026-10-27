package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
final class PillNavigatorViewHolder extends RecyclerView.ViewHolder {
    private final PillNavigatorView pillNavigatorView;

    public PillNavigatorViewHolder(final PillNavigatorView itemView) {
        super(itemView);
        this.pillNavigatorView = itemView;
    }

    public void bind(final PillNavigatorWidgetElement element) {
        this.pillNavigatorView.bind(element);
    }
}
