package com.android.internal.app;

import android.content.Context;
import android.util.AttributeSet;
import com.android.internal.widget.GridLayoutManager;
import com.android.internal.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class ChooserGridLayoutManager extends GridLayoutManager {
    private boolean mVerticalScrollEnabled;

    public ChooserGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mVerticalScrollEnabled = true;
    }

    public ChooserGridLayoutManager(Context context, int i) {
        super(context, i);
        this.mVerticalScrollEnabled = true;
    }

    public ChooserGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
        this.mVerticalScrollEnabled = true;
    }

    @Override // com.android.internal.widget.GridLayoutManager, com.android.internal.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.getRowCountForAccessibility(recycler, state) - 1;
    }

    void setVerticalScrollEnabled(boolean z) {
        this.mVerticalScrollEnabled = z;
    }

    @Override // com.android.internal.widget.LinearLayoutManager, com.android.internal.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mVerticalScrollEnabled && super.canScrollVertically();
    }
}
