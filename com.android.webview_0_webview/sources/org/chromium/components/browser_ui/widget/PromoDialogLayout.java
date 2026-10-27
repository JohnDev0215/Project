package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.AbstractC0371Um;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class PromoDialogLayout extends BoundedLinearLayout {
    public LinearLayout v;
    public ViewGroup w;
    public LinearLayout x;
    public TextView y;

    public PromoDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        this.v = (LinearLayout) findViewById(AbstractC0371Um.G1);
        this.w = (ViewGroup) findViewById(AbstractC0371Um.c3);
        this.x = (LinearLayout) findViewById(AbstractC0371Um.v3);
        this.y = (TextView) findViewById(AbstractC0371Um.L1);
        super.onFinishInflate();
    }

    @Override // org.chromium.components.browser_ui.widget.BoundedLinearLayout, android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getSize(i) > ((double) View.MeasureSpec.getSize(i2)) * 1.5d) {
            this.v.setOrientation(0);
        } else {
            this.v.setOrientation(1);
        }
        super.onMeasure(i, i2);
        throw null;
    }
}
