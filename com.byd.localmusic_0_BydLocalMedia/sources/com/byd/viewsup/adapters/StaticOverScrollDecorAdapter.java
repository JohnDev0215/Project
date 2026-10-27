package com.byd.viewsup.adapters;

import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public class StaticOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, NestedFragLayoutAdapter {
    protected boolean canRebound;
    private boolean isHorizontal;
    protected final View mView;

    public StaticOverScrollDecorAdapter(View view) {
        this.canRebound = true;
        this.isHorizontal = false;
        this.mView = view;
    }

    public StaticOverScrollDecorAdapter(View view, boolean z) {
        this.canRebound = true;
        this.isHorizontal = false;
        this.mView = view;
        this.isHorizontal = z;
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public View getView() {
        return this.mView;
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteStart() {
        if (isHorizontal()) {
            return !this.mView.canScrollHorizontally(-1);
        }
        return !this.mView.canScrollVertically(-1);
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteEnd() {
        if (isHorizontal()) {
            return !this.mView.canScrollHorizontally(1);
        }
        return !this.mView.canScrollVertically(1);
    }

    @Override // com.byd.viewsup.adapters.NestedFragLayoutAdapter
    public boolean isHorizontal() {
        return this.isHorizontal;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public void setCanRebound(boolean z) {
        this.canRebound = z;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public boolean preConditionStart() {
        return this.canRebound;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public boolean preConditionEnd() {
        return this.canRebound;
    }
}
