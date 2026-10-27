package com.byd.profile.view;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;

/* JADX INFO: loaded from: classes2.dex */
public class ScrollLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled;

    public ScrollLinearLayoutManager(Context context) {
        super(context);
        this.isScrollEnabled = true;
    }

    public ScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.isScrollEnabled = true;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.isScrollEnabled && super.canScrollVertically();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.isScrollEnabled && super.canScrollHorizontally();
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.isScrollEnabled = scrollEnabled;
    }
}
