package com.byd.music.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;

/* JADX INFO: loaded from: classes5.dex */
public class NoScrollLinearLayoutManager extends LinearLayoutManager {
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return false;
    }

    public NoScrollLinearLayoutManager(Context context) {
        super(context);
        setOrientation(0);
    }

    public NoScrollLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        setOrientation(0);
    }

    public NoScrollLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setOrientation(0);
    }
}
