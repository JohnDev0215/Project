package com.byd.viewsup.adapters;

import android.view.View;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes5.dex */
public class ScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, IOverScroller {
    protected boolean mIsCanRebound;
    private boolean mPureScroll;
    protected final ScrollView mView;

    public ScrollViewOverScrollDecorAdapter(ScrollView scrollView, boolean z) {
        this.mPureScroll = false;
        this.mIsCanRebound = true;
        this.mView = scrollView;
        this.mPureScroll = z;
    }

    public ScrollViewOverScrollDecorAdapter(ScrollView scrollView) {
        this.mPureScroll = false;
        this.mIsCanRebound = true;
        this.mView = scrollView;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public void setCanRebound(boolean z) {
        this.mIsCanRebound = z;
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public View getView() {
        return this.mView;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public boolean preConditionStart() {
        return this.mIsCanRebound;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public boolean preConditionEnd() {
        return this.mIsCanRebound;
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteStart() {
        if (this.mPureScroll) {
            return !this.mView.canScrollVertically(-1);
        }
        return false;
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteEnd() {
        if (this.mPureScroll) {
            return !this.mView.canScrollVertically(1);
        }
        return this.mView.getChildAt(0).getMeasuredHeight() > this.mView.getHeight() && !this.mView.canScrollVertically(1);
    }
}
