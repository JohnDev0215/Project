package com.android.systemui.qs.tileimpl;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public interface QSTile {
    void click(boolean isSelected);

    View createTileView(ViewGroup parent);

    boolean disableDrag();

    String getTileId();

    int getTileImageRes();

    String getTileLabel();

    boolean isAvailable();

    void longClick();

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    void release();

    void updateState(boolean isVisible);
}
