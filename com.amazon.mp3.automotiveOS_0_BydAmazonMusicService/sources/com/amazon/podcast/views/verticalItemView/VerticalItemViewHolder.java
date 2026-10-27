package com.amazon.podcast.views.verticalItemView;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public final class VerticalItemViewHolder extends RecyclerView.ViewHolder {
    private final VerticalItemView verticalItemView;

    public VerticalItemViewHolder(@NonNull final VerticalItemView verticalItemView) {
        super(verticalItemView);
        this.verticalItemView = verticalItemView;
    }

    public void bind(final VerticalItemElement element) {
        this.verticalItemView.bind(element);
    }
}
