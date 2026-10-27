package org.chromium.chrome.browser.search_resumption;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import defpackage.qg2;
import defpackage.rg2;

/* JADX INFO: loaded from: classes2.dex */
public class SearchResumptionTileContainerView extends LinearLayout {
    public boolean e;

    public SearchResumptionTileContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final SearchResumptionTileView a() {
        return (SearchResumptionTileView) LayoutInflater.from(getContext()).inflate(R.layout.search_resumption_module_tile_layout, (ViewGroup) this, false);
    }

    public final void b(boolean z, boolean z2) {
        if (this.e == z) {
            return;
        }
        this.e = z;
        if (!z) {
            rg2 rg2Var = new rg2(this, getMeasuredHeight());
            rg2Var.setDuration(z2 ? 200L : 0L);
            startAnimation(rg2Var);
            return;
        }
        measure(View.MeasureSpec.makeMeasureSpec(getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = getMeasuredHeight();
        getLayoutParams().height = 1;
        setVisibility(0);
        qg2 qg2Var = new qg2(this, measuredHeight);
        qg2Var.setDuration(z2 ? 200L : 0L);
        startAnimation(qg2Var);
    }

    public final boolean c() {
        return this.e;
    }
}
