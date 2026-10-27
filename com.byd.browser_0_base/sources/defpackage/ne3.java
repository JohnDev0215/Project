package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.base.Callback;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: compiled from: TouchToFillViewBase.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ne3 implements ii {
    public final ji e;
    public final RelativeLayout f;
    public final RecyclerView g;
    public Callback<Integer> h;
    public final a i = new a();

    /* JADX INFO: compiled from: TouchToFillViewBase.java */
    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void c(int i) {
            ne3 ne3Var = ne3.this;
            ne3Var.h.onResult(Integer.valueOf(i));
            ne3Var.e.e(ne3Var.i);
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            if (i != 0) {
                return;
            }
            ne3 ne3Var = ne3.this;
            ne3Var.h.onResult(0);
            ne3Var.e.e(ne3Var.i);
        }
    }

    /* JADX INFO: compiled from: TouchToFillViewBase.java */
    public class b extends LinearLayoutManager {
        public b() {
            super(1);
        }
    }

    public ne3(ji jiVar, RelativeLayout relativeLayout) {
        this.e = jiVar;
        this.f = relativeLayout;
        RecyclerView recyclerViewX = x();
        this.g = recyclerViewX;
        recyclerViewX.getContext();
        recyclerViewX.setLayoutManager(new b());
        relativeLayout.setLayoutDirection(LocalizationUtils.isLayoutRtl() ? 1 : 0);
    }

    public static int w(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            i = (z ? 0 : marginLayoutParams.bottomMargin) + marginLayoutParams.topMargin;
        }
        int measuredHeight = view.getMeasuredHeight();
        if (z) {
            measuredHeight /= 2;
        }
        return i + measuredHeight;
    }

    public abstract int A();

    public final void B() {
        RecyclerView recyclerView = this.g;
        RelativeLayout relativeLayout = this.f;
        relativeLayout.measure(View.MeasureSpec.makeMeasureSpec(relativeLayout.getContext().getResources().getDisplayMetrics().widthPixels - (z() * 2), Integer.MIN_VALUE), 0);
        recyclerView.measure(View.MeasureSpec.makeMeasureSpec(relativeLayout.getContext().getResources().getDisplayMetrics().widthPixels - (z() * 2), Integer.MIN_VALUE), 0);
    }

    public abstract int b();

    public abstract int c();

    @Override // defpackage.ii
    public final View d() {
        return this.f;
    }

    @Override // defpackage.ii
    public final void destroy() {
        this.e.e(this.i);
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final boolean f() {
        return false;
    }

    @Override // defpackage.ii
    public final View g() {
        return null;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final float n() {
        if (qo.f().d()) {
            return -2.0f;
        }
        int iY = 0;
        if (this.g.getAdapter() != null) {
            iY = y(true) + w(v(), false);
        }
        ji jiVar = this.e;
        return Math.min(iY, jiVar.b()) / jiVar.b();
    }

    @Override // defpackage.ii
    public final boolean q() {
        return qo.f().d();
    }

    @Override // defpackage.ii
    public final float s() {
        RecyclerView.e adapter = this.g.getAdapter();
        int iY = 0;
        ji jiVar = this.e;
        if (adapter != null) {
            int iY2 = y(false) + w(v(), false);
            if (iY2 <= jiVar.b()) {
                iY = iY2;
            } else {
                B();
                ao3.e(this.f, "TouchToFillView.getMaximumSheetHeightPx");
                iY = y(false) + w(v(), false);
            }
        }
        return Math.min(iY, jiVar.b()) / jiVar.b();
    }

    @Override // defpackage.ii
    public final boolean t() {
        return false;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return false;
    }

    public abstract View v();

    public abstract RecyclerView x();

    public final int y(boolean z) {
        int iC;
        int i = 0;
        int iW = 0;
        int i2 = 0;
        while (true) {
            RecyclerView recyclerView = this.g;
            if (i >= recyclerView.getChildCount()) {
                return iW;
            }
            View childAt = recyclerView.getChildAt(i);
            if (recyclerView.getAdapter().m(RecyclerView.I(childAt)) == A()) {
                i2++;
            } else if (z) {
                if (recyclerView.getAdapter().m(RecyclerView.I(childAt)) == b()) {
                    iC = c();
                    break;
                }
            }
            if (z && i2 > 3) {
                iC = w(childAt, true);
                break;
            }
            iW += w(childAt, false);
            i++;
        }
        return iC + iW;
    }

    public abstract int z();
}
