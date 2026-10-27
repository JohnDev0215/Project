package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class FadingEdgeScrollView extends ScrollView {
    public final Paint e;
    public final int f;
    public final int g;
    public int h;
    public int i;

    public FadingEdgeScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new Paint();
        this.h = 1;
        this.i = 1;
        this.f = getContext().getColor(R$color.toolbar_shadow_color);
        this.g = getResources().getDimensionPixelSize(R$dimen.divider_height);
    }

    public final void a(Canvas canvas, int i, float f, int i2) {
        if (i2 == 0) {
            return;
        }
        float fMax = i2 == 1 ? Math.max(0.0f, Math.min(1.0f, f)) : 1.0f;
        if (fMax <= 0.0f) {
            return;
        }
        int i3 = this.f;
        Paint paint = this.e;
        paint.setColor(Color.argb((int) (Color.alpha(i3) * fMax), (int) (Color.red(i3) * fMax), (int) (Color.green(i3) * fMax), (int) (Color.blue(i3) * fMax)));
        int scrollX = getScrollX();
        int right = getRight() + scrollX;
        int i4 = this.g;
        if (i == 1) {
            canvas.drawRect(scrollX, r1 - i4, right, (getBottom() + getScrollY()) - getTop(), paint);
        } else if (i == 0) {
            canvas.drawRect(scrollX, getScrollY(), right, r10 + i4, paint);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        setVerticalFadingEdgeEnabled(true);
        float topFadingEdgeStrength = getTopFadingEdgeStrength();
        float bottomFadingEdgeStrength = getBottomFadingEdgeStrength();
        setVerticalFadingEdgeEnabled(false);
        a(canvas, 0, topFadingEdgeStrength, this.h);
        a(canvas, 1, bottomFadingEdgeStrength, this.i);
    }
}
