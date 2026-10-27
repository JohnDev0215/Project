package org.chromium.chrome.browser.history_clusters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import defpackage.lb0;
import defpackage.mo;
import defpackage.ya1;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$style;

/* JADX INFO: loaded from: classes.dex */
class HistoryClustersRelatedSearchesChipLayout extends FrameLayout {
    public lb0 e;
    public mo f;
    public final ya1 g;
    public Callback<String> h;

    public HistoryClustersRelatedSearchesChipLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new ya1();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        mo moVar = new mo(getContext(), this.g);
        this.f = moVar;
        moVar.a.g(new mo.a(getResources().getDimensionPixelSize(R$dimen.related_search_chip_list_chip_spacing), getResources().getDimensionPixelSize(R$dimen.related_search_chip_list_side_padding)));
        addView(this.f.a);
        lb0 lb0Var = new lb0(getContext(), R$style.HorizontalDivider);
        this.e = lb0Var;
        lb0Var.a(this, generateDefaultLayoutParams());
    }
}
