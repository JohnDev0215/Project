package com.amazon.mp3.card;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractCardLayoutManager implements LayoutManager {
    protected ViewGroup mContainer = createContentContainer();
    protected final Activity mContext;

    protected abstract ViewGroup createContentContainer();

    public AbstractCardLayoutManager(ViewGroup parent, int contentIdx) {
        this.mContext = (Activity) parent.getContext();
        parent.addView(this.mContainer, contentIdx, new ViewGroup.LayoutParams(-1, -1));
    }

    @Override // com.amazon.mp3.card.LayoutManager
    public void addView(final View itemView) {
        this.mContainer.addView(itemView);
    }

    @Override // com.amazon.mp3.card.LayoutManager
    public void clearAll() {
        this.mContainer.removeAllViews();
    }
}
