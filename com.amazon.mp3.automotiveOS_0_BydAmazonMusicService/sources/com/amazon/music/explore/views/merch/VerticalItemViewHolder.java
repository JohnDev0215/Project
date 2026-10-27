package com.amazon.music.explore.views.merch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.views.VerticalTileView;

/* JADX INFO: loaded from: classes4.dex */
public class VerticalItemViewHolder extends RecyclerView.ViewHolder {
    private final VerticalTileView verticalTileView;

    public VerticalItemViewHolder(VerticalTileView verticalTileView) {
        super(verticalTileView);
        this.verticalTileView = verticalTileView;
    }

    @NonNull
    public VerticalTileView getView() {
        return this.verticalTileView;
    }
}
