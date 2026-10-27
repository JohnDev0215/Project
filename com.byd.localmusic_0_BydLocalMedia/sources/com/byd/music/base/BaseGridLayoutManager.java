package com.byd.music.base;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;

/* JADX INFO: loaded from: classes5.dex */
public class BaseGridLayoutManager extends GridLayoutManager {
    private boolean isCanScroll;

    public BaseGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isCanScroll = true;
    }

    public BaseGridLayoutManager(Context context, int i) {
        super(context, i);
        this.isCanScroll = true;
    }

    public BaseGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
        this.isCanScroll = true;
    }

    public void setIsCanScroll(boolean z) {
        this.isCanScroll = z;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.isCanScroll && super.canScrollVertically();
    }
}
