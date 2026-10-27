package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LinearSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends o {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private j f1799d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private j f1800e;

    private float m(RecyclerView.o oVar, j jVar) {
        int iJ = oVar.J();
        if (iJ == 0) {
            return 1.0f;
        }
        View view = null;
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        View view2 = null;
        for (int i3 = 0; i3 < iJ; i3++) {
            View viewI = oVar.I(i3);
            int iH0 = oVar.h0(viewI);
            if (iH0 != -1) {
                if (iH0 < i2) {
                    view = viewI;
                    i2 = iH0;
                }
                if (iH0 > i) {
                    view2 = viewI;
                    i = iH0;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int iMax = Math.max(jVar.d(view), jVar.d(view2)) - Math.min(jVar.g(view), jVar.g(view2));
        if (iMax == 0) {
            return 1.0f;
        }
        return (iMax * 1.0f) / ((i - i2) + 1);
    }

    private int n(RecyclerView.o oVar, View view, j jVar) {
        return (jVar.g(view) + (jVar.e(view) / 2)) - (jVar.m() + (jVar.n() / 2));
    }

    private int o(RecyclerView.o oVar, j jVar, int i, int i2) {
        int[] iArrD = d(i, i2);
        float fM = m(oVar, jVar);
        if (fM <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(iArrD[0]) > Math.abs(iArrD[1]) ? iArrD[0] : iArrD[1]) / fM);
    }

    private View p(RecyclerView.o oVar, j jVar) {
        int iJ = oVar.J();
        View view = null;
        if (iJ == 0) {
            return null;
        }
        int iM = jVar.m() + (jVar.n() / 2);
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < iJ; i2++) {
            View viewI = oVar.I(i2);
            int iAbs = Math.abs((jVar.g(viewI) + (jVar.e(viewI) / 2)) - iM);
            if (iAbs < i) {
                view = viewI;
                i = iAbs;
            }
        }
        return view;
    }

    private j q(RecyclerView.o oVar) {
        j jVar = this.f1800e;
        if (jVar == null || jVar.f1802a != oVar) {
            this.f1800e = j.a(oVar);
        }
        return this.f1800e;
    }

    private j r(RecyclerView.o oVar) {
        j jVar = this.f1799d;
        if (jVar == null || jVar.f1802a != oVar) {
            this.f1799d = j.c(oVar);
        }
        return this.f1799d;
    }

    @Override // androidx.recyclerview.widget.o
    public int[] c(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.k()) {
            iArr[0] = n(oVar, view, q(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.l()) {
            iArr[1] = n(oVar, view, r(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.o
    public View h(RecyclerView.o oVar) {
        if (oVar.l()) {
            return p(oVar, r(oVar));
        }
        if (oVar.k()) {
            return p(oVar, q(oVar));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.o
    public int i(RecyclerView.o oVar, int i, int i2) {
        int iY;
        View viewH;
        int iH0;
        int i3;
        PointF pointFA;
        int iO;
        int iO2;
        if (!(oVar instanceof RecyclerView.y.b) || (iY = oVar.Y()) == 0 || (viewH = h(oVar)) == null || (iH0 = oVar.h0(viewH)) == -1 || (pointFA = ((RecyclerView.y.b) oVar).a(iY - 1)) == null) {
            return -1;
        }
        if (oVar.k()) {
            iO = o(oVar, q(oVar), i, 0);
            if (pointFA.x < 0.0f) {
                iO = -iO;
            }
        } else {
            iO = 0;
        }
        if (oVar.l()) {
            iO2 = o(oVar, r(oVar), 0, i2);
            if (pointFA.y < 0.0f) {
                iO2 = -iO2;
            }
        } else {
            iO2 = 0;
        }
        if (oVar.l()) {
            iO = iO2;
        }
        if (iO == 0) {
            return -1;
        }
        int i4 = iH0 + iO;
        int i5 = i4 >= 0 ? i4 : 0;
        return i5 >= iY ? i3 : i5;
    }
}
