package org.chromium.chrome.browser.multiwindow;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import org.chromium.chrome.R$dimen;

/* JADX INFO: loaded from: classes2.dex */
public class CloseConfirmationDialogView extends LinearLayout {
    public CloseConfirmationDialogView(Context context) {
        super(context);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        Resources resources = getResources();
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), resources.getDisplayMetrics().widthPixels - (resources.getDimensionPixelSize(R$dimen.confirmation_dialog_side_margin) * 2)), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i2);
    }

    public CloseConfirmationDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
