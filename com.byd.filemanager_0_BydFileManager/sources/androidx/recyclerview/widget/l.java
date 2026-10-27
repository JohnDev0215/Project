package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PagerSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private k f1470d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private k f1471e;

    /* JADX INFO: compiled from: PagerSnapHelper.java */
    class a extends h {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.h, androidx.recyclerview.widget.RecyclerView.y
        protected void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            l lVar = l.this;
            int[] iArrC = lVar.c(lVar.f1476a.getLayoutManager(), view);
            int i = iArrC[0];
            int i2 = iArrC[1];
            int iW = w(Math.max(Math.abs(i), Math.abs(i2)));
            if (iW > 0) {
                aVar.d(i, i2, iW, this.j);
            }
        }

        @Override // androidx.recyclerview.widget.h
        protected float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // androidx.recyclerview.widget.h
        protected int x(int i) {
            return Math.min(100, super.x(i));
        }
    }

    private int m(RecyclerView.o oVar, View view, k kVar) {
        return (kVar.g(view) + (kVar.e(view) / 2)) - (kVar.m() + (kVar.n() / 2));
    }

    private View n(RecyclerView.o oVar, k kVar) {
        int iT = oVar.T();
        View view = null;
        if (iT == 0) {
            return null;
        }
        int iM = kVar.m() + (kVar.n() / 2);
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < iT; i2++) {
            View viewS = oVar.S(i2);
            int iAbs = Math.abs((kVar.g(viewS) + (kVar.e(viewS) / 2)) - iM);
            if (iAbs < i) {
                view = viewS;
                i = iAbs;
            }
        }
        return view;
    }

    private k o(RecyclerView.o oVar) {
        k kVar = this.f1471e;
        if (kVar == null || kVar.f1467a != oVar) {
            this.f1471e = k.a(oVar);
        }
        return this.f1471e;
    }

    private k p(RecyclerView.o oVar) {
        if (oVar.v()) {
            return q(oVar);
        }
        if (oVar.u()) {
            return o(oVar);
        }
        return null;
    }

    private k q(RecyclerView.o oVar) {
        k kVar = this.f1470d;
        if (kVar == null || kVar.f1467a != oVar) {
            this.f1470d = k.c(oVar);
        }
        return this.f1470d;
    }

    private boolean r(RecyclerView.o oVar, int i, int i2) {
        return oVar.u() ? i > 0 : i2 > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean s(RecyclerView.o oVar) {
        PointF pointFE;
        int iI0 = oVar.i0();
        if (!(oVar instanceof RecyclerView.y.b) || (pointFE = ((RecyclerView.y.b) oVar).e(iI0 - 1)) == null) {
            return false;
        }
        return pointFE.x < 0.0f || pointFE.y < 0.0f;
    }

    @Override // androidx.recyclerview.widget.p
    public int[] c(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.u()) {
            iArr[0] = m(oVar, view, o(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.v()) {
            iArr[1] = m(oVar, view, q(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.p
    protected h f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new a(this.f1476a.getContext());
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.p
    public View h(RecyclerView.o oVar) {
        if (oVar.v()) {
            return n(oVar, q(oVar));
        }
        if (oVar.u()) {
            return n(oVar, o(oVar));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.p
    public int i(RecyclerView.o oVar, int i, int i2) {
        k kVarP;
        int iI0 = oVar.i0();
        if (iI0 == 0 || (kVarP = p(oVar)) == null) {
            return -1;
        }
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        int iT = oVar.T();
        View view = null;
        View view2 = null;
        for (int i5 = 0; i5 < iT; i5++) {
            View viewS = oVar.S(i5);
            if (viewS != null) {
                int iM = m(oVar, viewS, kVarP);
                if (iM <= 0 && iM > i3) {
                    view2 = viewS;
                    i3 = iM;
                }
                if (iM >= 0 && iM < i4) {
                    view = viewS;
                    i4 = iM;
                }
            }
        }
        boolean zR = r(oVar, i, i2);
        if (zR && view != null) {
            return oVar.n0(view);
        }
        if (!zR && view2 != null) {
            return oVar.n0(view2);
        }
        if (zR) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int iN0 = oVar.n0(view) + (s(oVar) == zR ? -1 : 1);
        if (iN0 < 0 || iN0 >= iI0) {
            return -1;
        }
        return iN0;
    }
}
