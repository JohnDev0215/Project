package t1;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;
import l0.b0;
import l0.t;

/* JADX INFO: compiled from: HeaderScrollingViewBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends c<View> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f5026d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f5027e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5028f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5029g;

    public b() {
        this.f5026d = new Rect();
        this.f5027e = new Rect();
        this.f5028f = 0;
    }

    public static int N(int i3) {
        if (i3 == 0) {
            return 8388659;
        }
        return i3;
    }

    @Override // t1.c
    public void F(CoordinatorLayout coordinatorLayout, View view, int i3) {
        View viewH = H(coordinatorLayout.v(view));
        if (viewH == null) {
            super.F(coordinatorLayout, view, i3);
            this.f5028f = 0;
            return;
        }
        CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
        Rect rect = this.f5026d;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, viewH.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((coordinatorLayout.getHeight() + viewH.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        b0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && t.w(coordinatorLayout) && !t.w(view)) {
            rect.left += lastWindowInsets.f();
            rect.right -= lastWindowInsets.g();
        }
        Rect rect2 = this.f5027e;
        l0.d.a(N(fVar.f1099c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i3);
        int I = I(viewH);
        view.layout(rect2.left, rect2.top - I, rect2.right, rect2.bottom - I);
        this.f5028f = rect2.top - viewH.getBottom();
    }

    public abstract View H(List<View> list);

    public final int I(View view) {
        if (this.f5029g == 0) {
            return 0;
        }
        float fJ = J(view);
        int i3 = this.f5029g;
        return g0.a.b((int) (fJ * i3), 0, i3);
    }

    public abstract float J(View view);

    public final int K() {
        return this.f5029g;
    }

    public int L(View view) {
        return view.getMeasuredHeight();
    }

    public final int M() {
        return this.f5028f;
    }

    public final void O(int i3) {
        this.f5029g = i3;
    }

    public boolean P() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean m(CoordinatorLayout coordinatorLayout, View view, int i3, int i4, int i5, int i6) {
        View viewH;
        b0 lastWindowInsets;
        int i7 = view.getLayoutParams().height;
        if ((i7 != -1 && i7 != -2) || (viewH = H(coordinatorLayout.v(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i5);
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
        coordinatorLayout.N(view, i3, i4, View.MeasureSpec.makeMeasureSpec(iL, i7 == -1 ? TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED : Integer.MIN_VALUE), i6);
        return true;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5026d = new Rect();
        this.f5027e = new Rect();
        this.f5028f = 0;
    }
}
