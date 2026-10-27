package k5;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;
import o0.b0;
import o0.t;

/* JADX INFO: compiled from: HeaderScrollingViewBehavior.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends c<View> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f5887d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f5888e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5889f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5890g;

    public b() {
        this.f5887d = new Rect();
        this.f5888e = new Rect();
        this.f5889f = 0;
    }

    public static int N(int i8) {
        if (i8 == 0) {
            return 8388659;
        }
        return i8;
    }

    @Override // k5.c
    public void F(CoordinatorLayout coordinatorLayout, View view, int i8) {
        View viewH = H(coordinatorLayout.v(view));
        if (viewH == null) {
            super.F(coordinatorLayout, view, i8);
            this.f5889f = 0;
            return;
        }
        CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
        Rect rect = this.f5887d;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, viewH.getBottom() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, ((coordinatorLayout.getHeight() + viewH.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        b0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && t.x(coordinatorLayout) && !t.x(view)) {
            rect.left += lastWindowInsets.f();
            rect.right -= lastWindowInsets.g();
        }
        Rect rect2 = this.f5888e;
        o0.d.a(N(fVar.f1188c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i8);
        int I = I(viewH);
        view.layout(rect2.left, rect2.top - I, rect2.right, rect2.bottom - I);
        this.f5889f = rect2.top - viewH.getBottom();
    }

    public abstract View H(List<View> list);

    public final int I(View view) {
        if (this.f5890g == 0) {
            return 0;
        }
        float fJ = J(view);
        int i8 = this.f5890g;
        return j0.a.b((int) (fJ * i8), 0, i8);
    }

    public abstract float J(View view);

    public final int K() {
        return this.f5890g;
    }

    public int L(View view) {
        return view.getMeasuredHeight();
    }

    public final int M() {
        return this.f5889f;
    }

    public final void O(int i8) {
        this.f5890g = i8;
    }

    public boolean P() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean m(CoordinatorLayout coordinatorLayout, View view, int i8, int i9, int i10, int i11) {
        View viewH;
        b0 lastWindowInsets;
        int i12 = view.getLayoutParams().height;
        if ((i12 != -1 && i12 != -2) || (viewH = H(coordinatorLayout.v(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i10);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (t.x(viewH) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.h() + lastWindowInsets.e();
        }
        int iL = size + L(viewH);
        int measuredHeight = viewH.getMeasuredHeight();
        if (P()) {
            view.setTranslationY(-measuredHeight);
        } else {
            iL -= measuredHeight;
        }
        coordinatorLayout.N(view, i8, i9, View.MeasureSpec.makeMeasureSpec(iL, i12 == -1 ? TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED : Integer.MIN_VALUE), i11);
        return true;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5887d = new Rect();
        this.f5888e = new Rect();
        this.f5889f = 0;
    }
}
