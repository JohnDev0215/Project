package org.chromium.chrome.browser.toolbar.bottom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import defpackage.cc3;
import defpackage.oj2;
import defpackage.pn3;
import defpackage.tx;
import defpackage.zp1;
import org.chromium.chrome.R$dimen;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ScrollingBottomViewResourceFrameLayout extends ViewResourceFrameLayout {
    public final Rect h;
    public final int i;
    public Object j;
    public Object k;
    public tx l;

    public class a extends pn3 {
        public a(View view) {
            super(view, false);
        }

        @Override // defpackage.fm
        public final void d(Canvas canvas, Rect rect) {
            oj2.U(2, 3, "Android.Toolbar.BitmapCapture");
            ScrollingBottomViewResourceFrameLayout scrollingBottomViewResourceFrameLayout = ScrollingBottomViewResourceFrameLayout.this;
            scrollingBottomViewResourceFrameLayout.h.set(rect);
            if (scrollingBottomViewResourceFrameLayout.h.intersect(0, 0, scrollingBottomViewResourceFrameLayout.getWidth(), scrollingBottomViewResourceFrameLayout.i)) {
                canvas.save();
                canvas.clipRect(scrollingBottomViewResourceFrameLayout.h);
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                canvas.restore();
            }
            scrollingBottomViewResourceFrameLayout.k = scrollingBottomViewResourceFrameLayout.j;
        }

        @Override // defpackage.pn3
        public final boolean h() {
            if (cc3.a()) {
                return false;
            }
            if (!cc3.b()) {
                return super.h();
            }
            if (!super.h()) {
                return false;
            }
            ScrollingBottomViewResourceFrameLayout scrollingBottomViewResourceFrameLayout = ScrollingBottomViewResourceFrameLayout.this;
            tx txVar = scrollingBottomViewResourceFrameLayout.l;
            if (txVar == null || !txVar.a()) {
                Object obj = scrollingBottomViewResourceFrameLayout.j;
                return (obj == null || obj.equals(scrollingBottomViewResourceFrameLayout.k)) ? false : true;
            }
            tx txVar2 = scrollingBottomViewResourceFrameLayout.l;
            txVar2.f.p(txVar2);
            return false;
        }
    }

    public ScrollingBottomViewResourceFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new Rect();
        this.i = getResources().getDimensionPixelOffset(R$dimen.toolbar_shadow_height);
    }

    public int getTopShadowHeight() {
        return this.i;
    }

    @Override // org.chromium.components.browser_ui.widget.ViewResourceFrameLayout
    public final pn3 j() {
        return new a(this);
    }

    public void setConstraintsSupplier(zp1<Integer> zp1Var) {
        this.l = new tx(getResourceAdapter(), zp1Var, Looper.getMainLooper());
    }
}
