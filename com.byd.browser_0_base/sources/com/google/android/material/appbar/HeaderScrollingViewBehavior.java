package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.in3;
import defpackage.n3;
import defpackage.om3;
import defpackage.pt3;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    public final Rect c;
    public final Rect d;
    public int e;
    public int f;

    public HeaderScrollingViewBehavior() {
        this.c = new Rect();
        this.d = new Rect();
        this.e = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean i(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        AppBarLayout appBarLayoutU;
        pt3 lastWindowInsets;
        int i4 = view.getLayoutParams().height;
        if ((i4 != -1 && i4 != -2) || (appBarLayoutU = u(coordinatorLayout.p(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (size > 0) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.d.a(appBarLayoutU) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
                size += lastWindowInsets.b() + lastWindowInsets.e();
            }
        } else {
            size = coordinatorLayout.getHeight();
        }
        coordinatorLayout.x(view, i, i2, View.MeasureSpec.makeMeasureSpec((size + w(appBarLayoutU)) - appBarLayoutU.getMeasuredHeight(), i4 == -1 ? TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED : Integer.MIN_VALUE));
        return true;
    }

    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    public final void t(CoordinatorLayout coordinatorLayout, View view, int i) {
        AppBarLayout appBarLayoutU = u(coordinatorLayout.p(view));
        int iW = 0;
        if (appBarLayoutU == null) {
            coordinatorLayout.w(view, i);
            this.e = 0;
            return;
        }
        CoordinatorLayout.e eVar = (CoordinatorLayout.e) view.getLayoutParams();
        int paddingLeft = coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
        int bottom = appBarLayoutU.getBottom() + ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        int width = (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
        int bottom2 = ((appBarLayoutU.getBottom() + coordinatorLayout.getHeight()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
        Rect rect = this.c;
        rect.set(paddingLeft, bottom, width, bottom2);
        pt3 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.d.a(coordinatorLayout) && !om3.d.a(view)) {
                rect.left = lastWindowInsets.c() + rect.left;
                rect.right -= lastWindowInsets.d();
            }
        }
        Rect rect2 = this.d;
        int i2 = eVar.c;
        if (i2 == 0) {
            i2 = 8388659;
        }
        Gravity.apply(i2, view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
        if (this.f != 0) {
            float fV = v(appBarLayoutU);
            int i3 = this.f;
            iW = n3.w((int) (fV * i3), 0, i3);
        }
        view.layout(rect2.left, rect2.top - iW, rect2.right, rect2.bottom - iW);
        this.e = rect2.top - appBarLayoutU.getBottom();
    }

    public abstract AppBarLayout u(List list);

    public float v(View view) {
        return 1.0f;
    }

    public int w(View view) {
        return view.getMeasuredHeight();
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new Rect();
        this.d = new Rect();
        this.e = 0;
    }
}
