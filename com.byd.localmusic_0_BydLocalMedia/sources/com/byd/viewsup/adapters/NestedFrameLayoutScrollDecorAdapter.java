package com.byd.viewsup.adapters;

import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes5.dex */
public class NestedFrameLayoutScrollDecorAdapter implements IOverScrollDecoratorAdapter, IOverScroller, NestedFragLayoutAdapter {
    protected boolean isHorizontal;
    protected boolean mIsCanRebound = true;
    protected final FrameLayout mView;

    public NestedFrameLayoutScrollDecorAdapter(FrameLayout frameLayout, boolean z) {
        this.isHorizontal = true;
        this.mView = frameLayout;
        this.isHorizontal = z;
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
        if (this.isHorizontal) {
            return !this.mView.canScrollHorizontally(-1);
        }
        return !this.mView.canScrollVertically(-1);
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteEnd() {
        if (this.isHorizontal) {
            return !this.mView.canScrollHorizontally(1);
        }
        return !this.mView.canScrollVertically(1);
    }

    @Override // com.byd.viewsup.adapters.NestedFragLayoutAdapter
    public boolean isHorizontal() {
        return this.isHorizontal;
    }
}
