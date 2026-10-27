package com.amazon.music.explore.views.swipeablePages.pages;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeablePageViewHolder extends RecyclerView.ViewHolder {
    private final SwipeablePageView view;

    public SwipeablePageViewHolder(View view) {
        super(view);
        this.view = (SwipeablePageView) view;
    }

    public SwipeablePageView getView() {
        return this.view;
    }
}
