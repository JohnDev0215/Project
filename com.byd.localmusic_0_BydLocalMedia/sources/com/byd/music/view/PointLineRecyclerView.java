package com.byd.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class PointLineRecyclerView extends RecyclerView {
    private int childHeight;
    private int childWidth;
    private int customChildCount;
    private int groupHeight;
    private int groupWidth;
    private boolean isInit;
    private int minLine;
    private boolean needResetHeight;

    public PointLineRecyclerView(Context context) {
        this(context, null);
    }

    public PointLineRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PointLineRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.customChildCount = 0;
    }

    public void setMinLine(int i) {
        this.minLine = i;
    }

    public void setCustomChildCount(int i) {
        this.customChildCount = i;
    }

    public int getCustomChildCount() {
        return this.customChildCount;
    }

    public void setResetHeight(boolean z) {
        this.needResetHeight = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onMeasure(int i, int i2) {
        int itemHeight;
        int i3;
        super.onMeasure(i, i2);
        if (!(getLayoutManager() instanceof PointLineGridLayoutManager) || (itemHeight = ((PointLineGridLayoutManager) getLayoutManager()).getItemHeight()) == 0 || (i3 = this.minLine) == 0) {
            return;
        }
        this.childHeight = itemHeight;
        setMeasuredDimension(i, itemHeight * i3);
        if (this.needResetHeight) {
            invalidate();
            this.needResetHeight = false;
        }
    }

    public void setHeightToMin() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.minLine * this.childHeight;
        setLayoutParams(layoutParams);
    }

    public void setHeightToMax() {
        if (getLayoutManager() instanceof GridLayoutManager) {
            int iCeil = (int) Math.ceil(this.customChildCount / ((GridLayoutManager) getLayoutManager()).getSpanCount());
            setMeasuredDimension(getMeasuredWidth(), this.childHeight * iCeil);
            this.groupHeight = iCeil * this.childHeight;
        }
    }

    public int getGroupHeight() {
        return this.groupHeight;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }
}
