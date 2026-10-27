package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
public final class PromoDialogLayout extends BoundedLinearLayout {
    public LinearLayout j;
    public ViewGroup k;
    public LinearLayout l;
    public TextView m;

    public PromoDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        this.j = (LinearLayout) findViewById(R$id.full_promo_content);
        this.k = (ViewGroup) findViewById(R$id.promo_container);
        this.l = (LinearLayout) findViewById(R$id.scrollable_promo_content);
        this.m = (TextView) findViewById(R$id.header);
        super.onFinishInflate();
    }

    @Override // org.chromium.components.browser_ui.widget.BoundedLinearLayout, android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getSize(i) > ((double) View.MeasureSpec.getSize(i2)) * 1.5d) {
            this.j.setOrientation(0);
        } else {
            this.j.setOrientation(1);
        }
        super.onMeasure(i, i2);
        throw null;
    }
}
