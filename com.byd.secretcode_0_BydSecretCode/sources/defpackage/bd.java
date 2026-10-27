package defpackage;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LinearSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class bd extends id {
    public dd d;
    public dd e;

    @Override // defpackage.id
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

    @Override // defpackage.id
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
    @Override // defpackage.id
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

    public final float m(RecyclerView.o oVar, dd ddVar) {
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
        int iMax = Math.max(ddVar.d(view), ddVar.d(view2)) - Math.min(ddVar.g(view), ddVar.g(view2));
        if (iMax == 0) {
            return 1.0f;
        }
        return (iMax * 1.0f) / ((i - i2) + 1);
    }

    public final int n(RecyclerView.o oVar, View view, dd ddVar) {
        return (ddVar.g(view) + (ddVar.e(view) / 2)) - (ddVar.m() + (ddVar.n() / 2));
    }

    public final int o(RecyclerView.o oVar, dd ddVar, int i, int i2) {
        int[] iArrD = d(i, i2);
        float fM = m(oVar, ddVar);
        if (fM <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(iArrD[0]) > Math.abs(iArrD[1]) ? iArrD[0] : iArrD[1]) / fM);
    }

    public final View p(RecyclerView.o oVar, dd ddVar) {
        int iJ = oVar.J();
        View view = null;
        if (iJ == 0) {
            return null;
        }
        int iM = ddVar.m() + (ddVar.n() / 2);
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < iJ; i2++) {
            View viewI = oVar.I(i2);
            int iAbs = Math.abs((ddVar.g(viewI) + (ddVar.e(viewI) / 2)) - iM);
            if (iAbs < i) {
                view = viewI;
                i = iAbs;
            }
        }
        return view;
    }

    public final dd q(RecyclerView.o oVar) {
        dd ddVar = this.e;
        if (ddVar == null || ddVar.a != oVar) {
            this.e = dd.a(oVar);
        }
        return this.e;
    }

    public final dd r(RecyclerView.o oVar) {
        dd ddVar = this.d;
        if (ddVar == null || ddVar.a != oVar) {
            this.d = dd.c(oVar);
        }
        return this.d;
    }
}
