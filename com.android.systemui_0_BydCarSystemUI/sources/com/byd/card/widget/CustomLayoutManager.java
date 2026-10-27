package com.byd.card.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class CustomLayoutManager extends LinearLayoutManager {
    private int mAllHeight;
    private int mScrollTrackHeight;
    private int mScrollTrackTopMargin;
    private int mShowHeight;

    public CustomLayoutManager(Context context) {
        super(context);
        this.mShowHeight = 1;
        this.mAllHeight = 1;
        this.mScrollTrackHeight = 1;
        this.mScrollTrackTopMargin = 1;
    }

    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.mShowHeight = 1;
        this.mAllHeight = 1;
        this.mScrollTrackHeight = 1;
        this.mScrollTrackTopMargin = 1;
    }

    public CustomLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mShowHeight = 1;
        this.mAllHeight = 1;
        this.mScrollTrackHeight = 1;
        this.mScrollTrackTopMargin = 1;
    }

    public void setHeight(int showHeight, int allHeight, int scrollTrackHeight, int scrollTrackTopMargin) {
        this.mShowHeight = showHeight;
        this.mAllHeight = allHeight;
        this.mScrollTrackHeight = scrollTrackHeight;
        this.mScrollTrackTopMargin = scrollTrackTopMargin;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return (int) (((((double) super.computeVerticalScrollOffset(state)) * 1.0d) * ((double) this.mScrollTrackHeight)) / ((double) this.mShowHeight));
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        double d = (((double) this.mShowHeight) * 1.0d) / ((double) this.mAllHeight);
        return (int) (((((double) this.mScrollTrackHeight) * d) + ((double) this.mScrollTrackTopMargin)) / d);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return super.computeVerticalScrollRange(state);
    }
}
