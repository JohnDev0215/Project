package me.everything.android.ui.overscroll.adapters;

import android.view.View;
import android.widget.AbsListView;

/* JADX INFO: loaded from: classes2.dex */
public class AbsListViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final AbsListView mView;

    public AbsListViewOverScrollDecorAdapter(AbsListView absListView) {
        this.mView = absListView;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mView;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return this.mView.getChildCount() > 0 && !canScrollListUp();
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
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
}
