package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LinearSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private k f1464d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private k f1465e;

    private float m(RecyclerView.o oVar, k kVar) {
        int iT = oVar.T();
        if (iT == 0) {
            return 1.0f;
        }
        View view = null;
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        View view2 = null;
        for (int i3 = 0; i3 < iT; i3++) {
            View viewS = oVar.S(i3);
            int iN0 = oVar.n0(viewS);
            if (iN0 != -1) {
                if (iN0 < i2) {
                    view = viewS;
                    i2 = iN0;
                }
                if (iN0 > i) {
                    view2 = viewS;
                    i = iN0;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int iMax = Math.max(kVar.d(view), kVar.d(view2)) - Math.min(kVar.g(view), kVar.g(view2));
        if (iMax == 0) {
            return 1.0f;
        }
        return (iMax * 1.0f) / ((i - i2) + 1);
    }

    private int n(RecyclerView.o oVar, View view, k kVar) {
        return (kVar.g(view) + (kVar.e(view) / 2)) - (kVar.m() + (kVar.n() / 2));
    }

    private int o(RecyclerView.o oVar, k kVar, int i, int i2) {
        int[] iArrD = d(i, i2);
        float fM = m(oVar, kVar);
        if (fM <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(iArrD[0]) > Math.abs(iArrD[1]) ? iArrD[0] : iArrD[1]) / fM);
    }

    private View p(RecyclerView.o oVar, k kVar) {
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

    private k q(RecyclerView.o oVar) {
        k kVar = this.f1465e;
        if (kVar == null || kVar.f1467a != oVar) {
            this.f1465e = k.a(oVar);
        }
        return this.f1465e;
    }

    private k r(RecyclerView.o oVar) {
        k kVar = this.f1464d;
        if (kVar == null || kVar.f1467a != oVar) {
            this.f1464d = k.c(oVar);
        }
        return this.f1464d;
    }

    @Override // androidx.recyclerview.widget.p
    public int[] c(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.u()) {
            iArr[0] = n(oVar, view, q(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.v()) {
            iArr[1] = n(oVar, view, r(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.p
    public View h(RecyclerView.o oVar) {
        if (oVar.v()) {
            return p(oVar, r(oVar));
        }
        if (oVar.u()) {
            return p(oVar, q(oVar));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.p
    public int i(RecyclerView.o oVar, int i, int i2) {
        int iI0;
        View viewH;
        int iN0;
        int i3;
        PointF pointFE;
        int iO;
        int iO2;
        if (!(oVar instanceof RecyclerView.y.b) || (iI0 = oVar.i0()) == 0 || (viewH = h(oVar)) == null || (iN0 = oVar.n0(viewH)) == -1 || (pointFE = ((RecyclerView.y.b) oVar).e(iI0 - 1)) == null) {
            return -1;
        }
        if (oVar.u()) {
            iO = o(oVar, q(oVar), i, 0);
            if (pointFE.x < 0.0f) {
                iO = -iO;
            }
        } else {
            iO = 0;
        }
        if (oVar.v()) {
            iO2 = o(oVar, r(oVar), 0, i2);
            if (pointFE.y < 0.0f) {
                iO2 = -iO2;
            }
        } else {
            iO2 = 0;
        }
        if (oVar.v()) {
            iO = iO2;
        }
        if (iO == 0) {
            return -1;
        }
        int i4 = iN0 + iO;
        int i5 = i4 >= 0 ? i4 : 0;
        return i5 >= iI0 ? i3 : i5;
    }
}
