package com.byd.viewsup.adapters;

import android.view.View;
import android.widget.AbsListView;

/* JADX INFO: loaded from: classes5.dex */
public class AbsListViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    private boolean mCanRebound = true;
    protected final AbsListView mView;

    public AbsListViewOverScrollDecorAdapter(AbsListView absListView) {
        this.mView = absListView;
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public View getView() {
        return this.mView;
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteStart() {
        return this.mView.getChildCount() > 0 && !canScrollListUp();
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteEnd() {
        return this.mView.getChildCount() > 0 && !canScrollListDown();
    }

    public boolean canScrollListUp() {
        return this.mView.getFirstVisiblePosition() > 0 || this.mView.getChildAt(0).getTop() < this.mView.getListPaddingTop();
    }

    public boolean canScrollListDown() {
        int childCount = this.mView.getChildCount();
        return this.mView.getFirstVisiblePosition() + childCount < this.mView.getCount() || this.mView.getChildAt(childCount - 1).getBottom() > this.mView.getHeight() - this.mView.getListPaddingBottom();
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public void setCanRebound(boolean z) {
        this.mCanRebound = z;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public boolean preConditionStart() {
        return this.mCanRebound;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public boolean preConditionEnd() {
        return this.mCanRebound;
    }
}
