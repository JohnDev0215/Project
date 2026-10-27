package com.google.android.material.appbar;

import a.g.k.b0;
import a.g.k.t;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class b extends c<View> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Rect f2966d;
    final Rect e;
    private int f;
    private int g;

    public b() {
        this.f2966d = new Rect();
        this.e = new Rect();
        this.f = 0;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2966d = new Rect();
        this.e = new Rect();
        this.f = 0;
    }

    private static int N(int i) {
        if (i == 0) {
            return 8388659;
        }
        return i;
    }

    @Override // com.google.android.material.appbar.c
    protected void F(CoordinatorLayout coordinatorLayout, View view, int i) {
        int bottom;
        View viewH = H(coordinatorLayout.r(view));
        if (viewH != null) {
            CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
            Rect rect = this.f2966d;
            rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, viewH.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((coordinatorLayout.getHeight() + viewH.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
            b0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
            if (lastWindowInsets != null && t.v(coordinatorLayout) && !t.v(view)) {
                rect.left += lastWindowInsets.f();
                rect.right -= lastWindowInsets.g();
            }
            Rect rect2 = this.e;
            a.g.k.d.a(N(fVar.f1156c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
            int I = I(viewH);
            view.layout(rect2.left, rect2.top - I, rect2.right, rect2.bottom - I);
            bottom = rect2.top - viewH.getBottom();
        } else {
            super.F(coordinatorLayout, view, i);
            bottom = 0;
        }
        this.f = bottom;
    }

    abstract View H(List<View> list);

    final int I(View view) {
        if (this.g == 0) {
            return 0;
        }
        float fJ = J(view);
        int i = this.g;
        return a.g.f.a.b((int) (fJ * i), 0, i);
    }

    abstract float J(View view);

    public final int K() {
        return this.g;
    }

    int L(View view) {
        return view.getMeasuredHeight();
    }

    final int M() {
        return this.f;
    }

    public final void O(int i) {
        this.g = i;
    }

    protected boolean P() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean m(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        View viewH;
        b0 lastWindowInsets;
        int i5 = view.getLayoutParams().height;
        if ((i5 != -1 && i5 != -2) || (viewH = H(coordinatorLayout.r(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (t.v(viewH) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.h() + lastWindowInsets.e();
        }
        int iL = size + L(viewH);
        int measuredHeight = viewH.getMeasuredHeight();
        if (P()) {
            view.setTranslationY(-measuredHeight);
        } else {
            iL -= measuredHeight;
        }
        coordinatorLayout.J(view, i, i2, View.MeasureSpec.makeMeasureSpec(iL, i5 == -1 ? 1073741824 : Integer.MIN_VALUE), i4);
        return true;
    }
}
