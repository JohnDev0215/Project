package me.everything.android.ui.overscroll.adapters;

import android.view.View;
import android.widget.HorizontalScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class HorizontalScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final HorizontalScrollView mView;

    public HorizontalScrollViewOverScrollDecorAdapter(HorizontalScrollView horizontalScrollView) {
        this.mView = horizontalScrollView;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mView;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return !this.mView.canScrollHorizontally(-1);
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return !this.mView.canScrollHorizontally(1);
    }
}
