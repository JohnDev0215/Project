package org.chromium.chrome.browser.contextmenu;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import defpackage.a8;
import org.chromium.chrome.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class ContextMenuListView extends ListView {
    public final boolean e;

    public ContextMenuListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = a8.k0(context);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3 = getResources().getDisplayMetrics().widthPixels;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.context_menu_max_width);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.context_menu_lateral_margin);
        View view = (View) getParent();
        int paddingLeft = view.getPaddingLeft();
        int iMin = Math.min(dimensionPixelSize, view.getMeasuredWidth());
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.e ? iMin - (paddingLeft * 2) : Math.min(iMin, i3 - (dimensionPixelSize2 * 2)) - (paddingLeft * 2), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i2);
    }
}
