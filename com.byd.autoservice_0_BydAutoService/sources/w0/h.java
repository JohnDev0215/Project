package w0;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LinearSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends o {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f5648d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f5649e;

    @Override // w0.o
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

    @Override // w0.o
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
    @Override // w0.o
    public int i(RecyclerView.o oVar, int i3, int i4) {
        int iY;
        View viewH;
        int iH0;
        int i5;
        PointF pointFA;
        int iO;
        int iO2;
        if (!(oVar instanceof RecyclerView.y.b) || (iY = oVar.Y()) == 0 || (viewH = h(oVar)) == null || (iH0 = oVar.h0(viewH)) == -1 || (pointFA = ((RecyclerView.y.b) oVar).a(iY - 1)) == null) {
            return -1;
        }
        if (oVar.k()) {
            iO = o(oVar, q(oVar), i3, 0);
            if (pointFA.x < 0.0f) {
                iO = -iO;
            }
        } else {
            iO = 0;
        }
        if (oVar.l()) {
            iO2 = o(oVar, r(oVar), 0, i4);
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
        int i6 = iH0 + iO;
        int i7 = i6 >= 0 ? i6 : 0;
        return i7 >= iY ? i5 : i7;
    }

    public final float m(RecyclerView.o oVar, j jVar) {
        int iJ = oVar.J();
        if (iJ == 0) {
            return 1.0f;
        }
        View view = null;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        View view2 = null;
        for (int i5 = 0; i5 < iJ; i5++) {
            View viewI = oVar.I(i5);
            int iH0 = oVar.h0(viewI);
            if (iH0 != -1) {
                if (iH0 < i4) {
                    view = viewI;
                    i4 = iH0;
                }
                if (iH0 > i3) {
                    view2 = viewI;
                    i3 = iH0;
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
        return (iMax * 1.0f) / ((i3 - i4) + 1);
    }

    public final int n(RecyclerView.o oVar, View view, j jVar) {
        return (jVar.g(view) + (jVar.e(view) / 2)) - (jVar.m() + (jVar.n() / 2));
    }

    public final int o(RecyclerView.o oVar, j jVar, int i3, int i4) {
        int[] iArrD = d(i3, i4);
        float fM = m(oVar, jVar);
        if (fM <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(iArrD[0]) > Math.abs(iArrD[1]) ? iArrD[0] : iArrD[1]) / fM);
    }

    public final View p(RecyclerView.o oVar, j jVar) {
        int iJ = oVar.J();
        View view = null;
        if (iJ == 0) {
            return null;
        }
        int iM = jVar.m() + (jVar.n() / 2);
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < iJ; i4++) {
            View viewI = oVar.I(i4);
            int iAbs = Math.abs((jVar.g(viewI) + (jVar.e(viewI) / 2)) - iM);
            if (iAbs < i3) {
                view = viewI;
                i3 = iAbs;
            }
        }
        return view;
    }

    public final j q(RecyclerView.o oVar) {
        j jVar = this.f5649e;
        if (jVar == null || jVar.f5651a != oVar) {
            this.f5649e = j.a(oVar);
        }
        return this.f5649e;
    }

    public final j r(RecyclerView.o oVar) {
        j jVar = this.f5648d;
        if (jVar == null || jVar.f5651a != oVar) {
            this.f5648d = j.c(oVar);
        }
        return this.f5648d;
    }
}
