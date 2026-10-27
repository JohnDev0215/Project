package org.chromium.chrome.browser.omnibox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.LinearLayout;
import defpackage.pr1;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class LocationBarPhone extends LocationBarLayout {
    public View r;
    public View s;

    public LocationBarPhone(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        boolean z = false;
        if (view == this.r) {
            LinearLayout linearLayout = this.p;
            if (linearLayout.getVisibility() == 0) {
                canvas.save();
                if (this.r.getLeft() < linearLayout.getLeft()) {
                    canvas.clipRect(0, 0, (int) linearLayout.getX(), getBottom());
                } else {
                    canvas.clipRect(linearLayout.getX() + linearLayout.getWidth(), 0.0f, getWidth(), getBottom());
                }
                z = true;
            }
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        if (z) {
            canvas.restore();
        }
        return zDrawChild;
    }

    @Override // org.chromium.chrome.browser.omnibox.LocationBarLayout, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.r = findViewById(R$id.url_bar);
        this.s = findViewById(R$id.location_bar_status);
        Rect rect = new Rect();
        LinearLayout linearLayout = this.p;
        linearLayout.getHitRect(rect);
        rect.left -= 15;
        this.q.a.add(new TouchDelegate(rect, linearLayout));
        if (pr1.e(getContext())) {
            setPaddingRelative(getContext().getResources().getDimensionPixelSize(R$dimen.location_bar_start_padding_modern), getPaddingTop(), getPaddingEnd(), getPaddingBottom());
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent traceEventH0 = TraceEvent.H0("LocationBarPhone.onLayout", null);
        try {
            super.onLayout(z, i, i2, i3, i4);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // org.chromium.chrome.browser.omnibox.LocationBarLayout, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        TraceEvent traceEventH0 = TraceEvent.H0("LocationBarPhone.onMeasure", null);
        try {
            super.onMeasure(i, i2);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
