package c.b.a.a.n;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.h.m.b0;
import b.h.m.t;
import java.util.List;

/* JADX INFO: compiled from: HeaderScrollingViewBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends c<View> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f4042d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f4043e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4044f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4045g;

    public b() {
        this.f4042d = new Rect();
        this.f4043e = new Rect();
        this.f4044f = 0;
    }

    public static int N(int i2) {
        if (i2 == 0) {
            return 8388659;
        }
        return i2;
    }

    @Override // c.b.a.a.n.c
    public void F(CoordinatorLayout coordinatorLayout, View view, int i2) {
        View viewH = H(coordinatorLayout.v(view));
        if (viewH == null) {
            super.F(coordinatorLayout, view, i2);
            this.f4044f = 0;
            return;
        }
        CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
        Rect rect = this.f4042d;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, viewH.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((coordinatorLayout.getHeight() + viewH.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        b0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && t.w(coordinatorLayout) && !t.w(view)) {
            rect.left += lastWindowInsets.f();
            rect.right -= lastWindowInsets.g();
        }
        Rect rect2 = this.f4043e;
        b.h.m.d.a(N(fVar.f752c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i2);
        int I = I(viewH);
        view.layout(rect2.left, rect2.top - I, rect2.right, rect2.bottom - I);
        this.f4044f = rect2.top - viewH.getBottom();
    }

    public abstract View H(List<View> list);

    public final int I(View view) {
        if (this.f4045g == 0) {
            return 0;
        }
        float fJ = J(view);
        int i2 = this.f4045g;
        return b.h.h.a.b((int) (fJ * i2), 0, i2);
    }

    public abstract float J(View view);

    public final int K() {
        return this.f4045g;
    }

    public int L(View view) {
        return view.getMeasuredHeight();
    }

    public final int M() {
        return this.f4044f;
    }

    public final void O(int i2) {
        this.f4045g = i2;
    }

    public boolean P() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean m(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
        View viewH;
        b0 lastWindowInsets;
        int i6 = view.getLayoutParams().height;
        if ((i6 != -1 && i6 != -2) || (viewH = H(coordinatorLayout.v(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i4);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (t.w(viewH) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.h() + lastWindowInsets.e();
        }
        int iL = size + L(viewH);
        int measuredHeight = viewH.getMeasuredHeight();
        if (P()) {
            view.setTranslationY(-measuredHeight);
        } else {
            iL -= measuredHeight;
        }
        coordinatorLayout.N(view, i2, i3, View.MeasureSpec.makeMeasureSpec(iL, i6 == -1 ? TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED : Integer.MIN_VALUE), i5);
        return true;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4042d = new Rect();
        this.f4043e = new Rect();
        this.f4044f = 0;
    }
}
