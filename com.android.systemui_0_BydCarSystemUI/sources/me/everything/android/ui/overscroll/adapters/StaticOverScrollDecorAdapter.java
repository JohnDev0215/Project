package me.everything.android.ui.overscroll.adapters;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class StaticOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {
    protected final View mView;

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return true;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return true;
    }

    public StaticOverScrollDecorAdapter(View view) {
        this.mView = view;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mView;
    }
}
