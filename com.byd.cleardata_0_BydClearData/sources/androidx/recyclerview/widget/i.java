package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class i extends o {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private k f1595d;
    private k e;

    private float m(RecyclerView.o oVar, k kVar) {
        int iF0 = oVar.f0();
        if (iF0 == 0) {
            return 1.0f;
        }
        View view = null;
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        View view2 = null;
        for (int i3 = 0; i3 < iF0; i3++) {
            View viewE0 = oVar.e0(i3);
            int iZ0 = oVar.z0(viewE0);
            if (iZ0 != -1) {
                if (iZ0 < i2) {
                    view = viewE0;
                    i2 = iZ0;
                }
                if (iZ0 > i) {
                    view2 = viewE0;
                    i = iZ0;
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
        int iF0 = oVar.f0();
        View view = null;
        if (iF0 == 0) {
            return null;
        }
        int iM = kVar.m() + (kVar.n() / 2);
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < iF0; i2++) {
            View viewE0 = oVar.e0(i2);
            int iAbs = Math.abs((kVar.g(viewE0) + (kVar.e(viewE0) / 2)) - iM);
            if (iAbs < i) {
                view = viewE0;
                i = iAbs;
            }
        }
        return view;
    }

    private k q(RecyclerView.o oVar) {
        k kVar = this.e;
        if (kVar == null || kVar.f1597a != oVar) {
            this.e = k.a(oVar);
        }
        return this.e;
    }

    private k r(RecyclerView.o oVar) {
        k kVar = this.f1595d;
        if (kVar == null || kVar.f1597a != oVar) {
            this.f1595d = k.c(oVar);
        }
        return this.f1595d;
    }

    @Override // androidx.recyclerview.widget.o
    public int[] c(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.G()) {
            iArr[0] = n(oVar, view, q(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.H()) {
            iArr[1] = n(oVar, view, r(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.o
    public View h(RecyclerView.o oVar) {
        k kVarQ;
        if (oVar.H()) {
            kVarQ = r(oVar);
        } else {
            if (!oVar.G()) {
                return null;
            }
            kVarQ = q(oVar);
        }
        return p(oVar, kVarQ);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.o
    public int i(RecyclerView.o oVar, int i, int i2) {
        int iU0;
        View viewH;
        int iZ0;
        int i3;
        PointF pointFN;
        int iO;
        int iO2;
        if (!(oVar instanceof RecyclerView.z.b) || (iU0 = oVar.u0()) == 0 || (viewH = h(oVar)) == null || (iZ0 = oVar.z0(viewH)) == -1 || (pointFN = ((RecyclerView.z.b) oVar).n(iU0 - 1)) == null) {
            return -1;
        }
        if (oVar.G()) {
            iO = o(oVar, q(oVar), i, 0);
            if (pointFN.x < 0.0f) {
                iO = -iO;
            }
        } else {
            iO = 0;
        }
        if (oVar.H()) {
            iO2 = o(oVar, r(oVar), 0, i2);
            if (pointFN.y < 0.0f) {
                iO2 = -iO2;
            }
        } else {
            iO2 = 0;
        }
        if (oVar.H()) {
            iO = iO2;
        }
        if (iO == 0) {
            return -1;
        }
        int i4 = iZ0 + iO;
        int i5 = i4 >= 0 ? i4 : 0;
        return i5 >= iU0 ? i3 : i5;
    }
}
