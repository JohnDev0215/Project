package com.byd.viewsup.adapters;

import android.view.View;
import android.widget.HorizontalScrollView;

/* JADX INFO: loaded from: classes5.dex */
public class HorizontalScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, IOverScroller, NestedFragLayoutAdapter {
    protected boolean mIsCanRebound = true;
    protected final HorizontalScrollView mView;

    @Override // com.byd.viewsup.adapters.NestedFragLayoutAdapter
    public boolean isHorizontal() {
        return true;
    }

    public HorizontalScrollViewOverScrollDecorAdapter(HorizontalScrollView horizontalScrollView) {
        this.mView = horizontalScrollView;
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public View getView() {
        return this.mView;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public void setCanRebound(boolean z) {
        this.mIsCanRebound = z;
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
        return !this.mView.canScrollHorizontally(-1);
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteEnd() {
        return !this.mView.canScrollHorizontally(1);
    }
}
