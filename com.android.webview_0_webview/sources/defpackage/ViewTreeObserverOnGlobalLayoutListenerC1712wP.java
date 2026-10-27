package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: renamed from: wP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewTreeObserverOnGlobalLayoutListenerC1712wP extends AbstractC1328pP implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {
    public final int[] u = new int[2];
    public final Rect v = new Rect();
    public final View w;
    public ViewTreeObserver x;
    public boolean y;

    public ViewTreeObserverOnGlobalLayoutListenerC1712wP(View view) {
        this.w = view;
        int[] iArr = this.u;
        iArr[0] = -1;
        iArr[1] = -1;
    }

    public final void b() {
        int[] iArr = this.u;
        int i = iArr[0];
        int i2 = iArr[1];
        this.w.getLocationInWindow(iArr);
        int[] iArr2 = this.u;
        iArr2[0] = Math.max(iArr2[0], 0);
        int[] iArr3 = this.u;
        iArr3[1] = Math.max(iArr3[1], 0);
        int[] iArr4 = this.u;
        if (iArr4[0] == i && iArr4[1] == i2) {
            return;
        }
        Rect rect = this.s;
        int[] iArr5 = this.u;
        int i3 = iArr5[0];
        rect.left = i3;
        rect.top = iArr5[1];
        rect.right = this.w.getWidth() + i3;
        Rect rect2 = this.s;
        rect2.bottom = this.w.getHeight() + rect2.top;
        Rect rect3 = this.s;
        int i4 = rect3.left;
        Rect rect4 = this.v;
        rect3.left = i4 + rect4.left;
        rect3.top += rect4.top;
        rect3.right -= rect4.right;
        rect3.bottom -= rect4.bottom;
        if (!this.y) {
            boolean z = this.w.getLayoutDirection() == 1;
            Rect rect5 = this.s;
            int i5 = rect5.left;
            View view = this.w;
            rect5.left = i5 + (z ? AbstractC1251o3.n(view) : AbstractC1251o3.o(view));
            this.s.right -= z ? AbstractC1251o3.o(this.w) : AbstractC1251o3.n(this.w);
            Rect rect6 = this.s;
            rect6.top = this.w.getPaddingTop() + rect6.top;
            this.s.bottom -= this.w.getPaddingBottom();
        }
        Rect rect7 = this.s;
        rect7.right = Math.max(rect7.left, rect7.right);
        Rect rect8 = this.s;
        rect8.bottom = Math.max(rect8.top, rect8.bottom);
        Rect rect9 = this.s;
        rect9.right = Math.min(rect9.right, this.w.getRootView().getWidth());
        Rect rect10 = this.s;
        rect10.bottom = Math.min(rect10.bottom, this.w.getRootView().getHeight());
        InterfaceC1273oP interfaceC1273oP = this.t;
        if (interfaceC1273oP != null) {
            ((ViewOnTouchListenerC1218nP) interfaceC1273oP).b();
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (this.w.isShown()) {
            return;
        }
        a();
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        if (this.w.isShown()) {
            b();
            return true;
        }
        a();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        a();
    }
}
