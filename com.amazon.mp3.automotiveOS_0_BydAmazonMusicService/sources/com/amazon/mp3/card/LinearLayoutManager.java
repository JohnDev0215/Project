package com.amazon.mp3.card;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes3.dex */
public class LinearLayoutManager extends AbstractCardLayoutManager {
    @Override // com.amazon.mp3.card.AbstractCardLayoutManager, com.amazon.mp3.card.LayoutManager
    public /* bridge */ /* synthetic */ void addView(final View itemView) {
        super.addView(itemView);
    }

    @Override // com.amazon.mp3.card.AbstractCardLayoutManager, com.amazon.mp3.card.LayoutManager
    public /* bridge */ /* synthetic */ void clearAll() {
        super.clearAll();
    }

    public LinearLayoutManager(ViewGroup parent, int contentIdx) {
        super(parent, contentIdx);
    }

    @Override // com.amazon.mp3.card.AbstractCardLayoutManager
    protected ViewGroup createContentContainer() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        return linearLayout;
    }
}
