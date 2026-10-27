package com.byd.music.utils.binding;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class CenterLinearManagerLayout extends LinearLayoutManager {
    private CenterSmoothScroller smoothScroller;

    public CenterLinearManagerLayout(Context context) {
        super(context);
        this.smoothScroller = new CenterSmoothScroller(context);
    }

    public CenterLinearManagerLayout(Context context, int i, boolean z) {
        super(context, i, z);
        this.smoothScroller = new CenterSmoothScroller(context);
    }

    public CenterLinearManagerLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.smoothScroller = new CenterSmoothScroller(context);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        this.smoothScroller.setTargetPosition(i);
        startSmoothScroll(this.smoothScroller);
    }

    private static class CenterSmoothScroller extends LinearSmoothScroller {
        public CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            return (i3 + ((i4 - i3) / 2)) - (i + ((i2 - i) / 2));
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 200.0f / displayMetrics.densityDpi;
        }
    }
}
