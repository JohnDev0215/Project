package me.everything.android.ui.overscroll.adapters;

import android.view.View;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class ScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final ScrollView mView;

    public ScrollViewOverScrollDecorAdapter(ScrollView scrollView) {
        this.mView = scrollView;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mView;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return !this.mView.canScrollVertically(-1);
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return !this.mView.canScrollVertically(1);
    }
}
