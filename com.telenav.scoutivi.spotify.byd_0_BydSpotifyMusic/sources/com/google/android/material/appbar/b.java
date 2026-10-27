package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import i0.c0;
import i0.u;
import java.util.List;

/* JADX INFO: compiled from: HeaderScrollingViewBehavior.java */
/* JADX INFO: loaded from: classes.dex */
abstract class b extends c<View> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Rect f4072d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Rect f4073e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4074f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4075g;

    public b() {
        this.f4072d = new Rect();
        this.f4073e = new Rect();
        this.f4074f = 0;
    }

    private static int N(int i7) {
        if (i7 == 0) {
            return 8388659;
        }
        return i7;
    }

    @Override // com.google.android.material.appbar.c
    protected void F(CoordinatorLayout coordinatorLayout, View view, int i7) {
        View viewH = H(coordinatorLayout.r(view));
        if (viewH == null) {
            super.F(coordinatorLayout, view, i7);
            this.f4074f = 0;
            return;
        }
        CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
        Rect rect = this.f4072d;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, viewH.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((coordinatorLayout.getHeight() + viewH.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        c0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && u.v(coordinatorLayout) && !u.v(view)) {
            rect.left += lastWindowInsets.g();
            rect.right -= lastWindowInsets.h();
        }
        Rect rect2 = this.f4073e;
        i0.e.a(N(fVar.f1926c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i7);
        int I = I(viewH);
        view.layout(rect2.left, rect2.top - I, rect2.right, rect2.bottom - I);
        this.f4074f = rect2.top - viewH.getBottom();
    }

    abstract View H(List<View> list);

    final int I(View view) {
        if (this.f4075g == 0) {
            return 0;
        }
        float fJ = J(view);
        int i7 = this.f4075g;
        return c0.a.b((int) (fJ * i7), 0, i7);
    }

    float J(View view) {
        return 1.0f;
    }

    public final int K() {
        return this.f4075g;
    }

    int L(View view) {
        return view.getMeasuredHeight();
    }

    final int M() {
        return this.f4074f;
    }

    public final void O(int i7) {
        this.f4075g = i7;
    }

    protected boolean P() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean m(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9, int i10) {
        View viewH;
        c0 lastWindowInsets;
        int i11 = view.getLayoutParams().height;
        if ((i11 != -1 && i11 != -2) || (viewH = H(coordinatorLayout.r(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i9);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (u.v(viewH) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.i() + lastWindowInsets.f();
        }
        int iL = size + L(viewH);
        int measuredHeight = viewH.getMeasuredHeight();
        if (P()) {
            view.setTranslationY(-measuredHeight);
        } else {
            iL -= measuredHeight;
        }
        coordinatorLayout.J(view, i7, i8, View.MeasureSpec.makeMeasureSpec(iL, i11 == -1 ? TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED : Integer.MIN_VALUE), i10);
        return true;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4072d = new Rect();
        this.f4073e = new Rect();
        this.f4074f = 0;
    }
}
