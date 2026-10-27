package c.b.a.a.b;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.g.j.b0;
import b.g.j.t;
import java.util.List;

/* JADX INFO: compiled from: HeaderScrollingViewBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends c<View> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f1674d;
    public final Rect e;
    public int f;
    public int g;

    public b() {
        this.f1674d = new Rect();
        this.e = new Rect();
        this.f = 0;
    }

    public static int N(int i) {
        if (i == 0) {
            return 8388659;
        }
        return i;
    }

    @Override // c.b.a.a.b.c
    public void F(CoordinatorLayout coordinatorLayout, View view, int i) {
        View viewH = H(coordinatorLayout.v(view));
        if (viewH == null) {
            super.F(coordinatorLayout, view, i);
            this.f = 0;
            return;
        }
        CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
        Rect rect = this.f1674d;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, viewH.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((coordinatorLayout.getHeight() + viewH.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        b0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && t.w(coordinatorLayout) && !t.w(view)) {
            rect.left += lastWindowInsets.f();
            rect.right -= lastWindowInsets.g();
        }
        Rect rect2 = this.e;
        b.g.j.d.a(N(fVar.f300c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
        int I = I(viewH);
        view.layout(rect2.left, rect2.top - I, rect2.right, rect2.bottom - I);
        this.f = rect2.top - viewH.getBottom();
    }

    public abstract View H(List<View> list);

    public final int I(View view) {
        if (this.g == 0) {
            return 0;
        }
        float fJ = J(view);
        int i = this.g;
        return b.g.e.a.b((int) (fJ * i), 0, i);
    }

    public abstract float J(View view);

    public final int K() {
        return this.g;
    }

    public int L(View view) {
        return view.getMeasuredHeight();
    }

    public final int M() {
        return this.f;
    }

    public final void O(int i) {
        this.g = i;
    }

    public boolean P() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean m(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        View viewH;
        b0 lastWindowInsets;
        int i5 = view.getLayoutParams().height;
        if ((i5 != -1 && i5 != -2) || (viewH = H(coordinatorLayout.v(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
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
        coordinatorLayout.N(view, i, i2, View.MeasureSpec.makeMeasureSpec(iL, i5 == -1 ? TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED : Integer.MIN_VALUE), i4);
        return true;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1674d = new Rect();
        this.e = new Rect();
        this.f = 0;
    }
}
