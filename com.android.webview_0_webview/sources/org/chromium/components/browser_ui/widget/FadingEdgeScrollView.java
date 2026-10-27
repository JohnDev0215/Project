package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ScrollView;
import defpackage.AbstractC0281Pm;
import defpackage.AbstractC0299Qm;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FadingEdgeScrollView extends ScrollView {
    public final Paint s;
    public final int t;
    public final int u;
    public int v;
    public int w;

    public FadingEdgeScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = new Paint();
        this.v = 1;
        this.w = 1;
        this.t = getResources().getColor(AbstractC0281Pm.S5);
        this.u = getResources().getDimensionPixelSize(AbstractC0299Qm.x2);
    }

    public final void a(Canvas canvas, int i, float f, int i2) {
        if (i2 == 0) {
            return;
        }
        float fMax = i2 == 1 ? Math.max(0.0f, Math.min(1.0f, f)) : 1.0f;
        if (fMax <= 0.0f) {
            return;
        }
        this.s.setColor(Color.argb((int) (Color.alpha(this.t) * fMax), (int) (Color.red(this.t) * fMax), (int) (Color.green(this.t) * fMax), (int) (Color.blue(this.t) * fMax)));
        int scrollX = getScrollX();
        int right = getRight() + scrollX;
        if (i == 1) {
            canvas.drawRect(scrollX, r0 - this.u, right, (getBottom() + getScrollY()) - getTop(), this.s);
        } else if (i == 0) {
            canvas.drawRect(scrollX, getScrollY(), right, r9 + this.u, this.s);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        setVerticalFadingEdgeEnabled(true);
        float topFadingEdgeStrength = getTopFadingEdgeStrength();
        float bottomFadingEdgeStrength = getBottomFadingEdgeStrength();
        setVerticalFadingEdgeEnabled(false);
        a(canvas, 0, topFadingEdgeStrength, this.v);
        a(canvas, 1, bottomFadingEdgeStrength, this.w);
    }
}
