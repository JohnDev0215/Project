package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import defpackage.om3;
import defpackage.z92;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ViewRectProvider.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nn3 extends z92 implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {
    public final View i;
    public ViewTreeObserver l;
    public boolean m;
    public final int[] g = {-1, -1};
    public final Rect h = new Rect();
    public int j = -1;
    public int k = -1;

    public nn3(View view) {
        this.i = view;
    }

    @Override // defpackage.z92
    public final void a(z92.a aVar) {
        View view = this.i;
        view.addOnAttachStateChangeListener(this);
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        this.l = viewTreeObserver;
        viewTreeObserver.addOnGlobalLayoutListener(this);
        this.l.addOnPreDrawListener(this);
        c(false);
        this.f = aVar;
    }

    @Override // defpackage.z92
    public final void b() {
        this.i.removeOnAttachStateChangeListener(this);
        ViewTreeObserver viewTreeObserver = this.l;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            this.l.removeOnGlobalLayoutListener(this);
            this.l.removeOnPreDrawListener(this);
        }
        this.l = null;
        this.f = null;
    }

    public final void c(boolean z) {
        int[] iArr = this.g;
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = this.j;
        int i4 = this.k;
        View view = this.i;
        view.getLocationInWindow(iArr);
        iArr[0] = Math.max(iArr[0], 0);
        iArr[1] = Math.max(iArr[1], 0);
        this.j = view.getWidth();
        int height = view.getHeight();
        this.k = height;
        if (!z && iArr[0] == i && iArr[1] == i2 && this.j == i3 && height == i4) {
            return;
        }
        int i5 = iArr[0];
        Rect rect = this.e;
        rect.left = i5;
        rect.top = iArr[1];
        rect.right = view.getWidth() + i5;
        int height2 = view.getHeight() + rect.top;
        rect.bottom = height2;
        int i6 = rect.left;
        Rect rect2 = this.h;
        rect.left = i6 + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom = height2 - rect2.bottom;
        if (!this.m) {
            boolean z2 = view.getLayoutDirection() == 1;
            int i7 = rect.left;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            rect.left = i7 + (z2 ? om3.e.d(view) : om3.e.e(view));
            rect.right -= z2 ? om3.e.e(view) : om3.e.d(view);
            rect.top = view.getPaddingTop() + rect.top;
            rect.bottom -= view.getPaddingBottom();
        }
        rect.right = Math.max(rect.left, rect.right);
        rect.bottom = Math.max(rect.top, rect.bottom);
        rect.right = Math.min(rect.right, view.getRootView().getWidth());
        rect.bottom = Math.min(rect.bottom, view.getRootView().getHeight());
        z92.a aVar = this.f;
        if (aVar != null) {
            ((p4) aVar).g();
        }
    }

    public final void d(boolean z) {
        if (z == this.m) {
            return;
        }
        this.m = z;
        c(true);
    }

    public final void e(Rect rect) {
        Rect rect2 = this.h;
        if (rect.equals(rect2)) {
            return;
        }
        rect2.set(rect);
        c(true);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        z92.a aVar;
        if (this.i.isShown() || (aVar = this.f) == null) {
            return;
        }
        ((p4) aVar).a();
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        if (this.i.isShown()) {
            c(false);
            return true;
        }
        z92.a aVar = this.f;
        if (aVar == null) {
            return true;
        }
        ((p4) aVar).a();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        z92.a aVar = this.f;
        if (aVar != null) {
            ((p4) aVar).a();
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
