package defpackage;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PagerSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class ed extends id {
    public dd d;
    public dd e;

    /* JADX INFO: compiled from: PagerSnapHelper.java */
    public class a extends ad {
        public a(Context context) {
            super(context);
        }

        @Override // defpackage.ad, androidx.recyclerview.widget.RecyclerView.y
        public void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            ed edVar = ed.this;
            int[] iArrC = edVar.c(edVar.a.getLayoutManager(), view);
            int i = iArrC[0];
            int i2 = iArrC[1];
            int iW = w(Math.max(Math.abs(i), Math.abs(i2)));
            if (iW > 0) {
                aVar.d(i, i2, iW, this.j);
            }
        }

        @Override // defpackage.ad
        public float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // defpackage.ad
        public int x(int i) {
            return Math.min(100, super.x(i));
        }
    }

    @Override // defpackage.id
    public int[] c(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.k()) {
            iArr[0] = m(oVar, view, o(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.l()) {
            iArr[1] = m(oVar, view, q(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // defpackage.id
    public ad f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new a(this.a.getContext());
        }
        return null;
    }

    @Override // defpackage.id
    public View h(RecyclerView.o oVar) {
        if (oVar.l()) {
            return n(oVar, q(oVar));
        }
        if (oVar.k()) {
            return n(oVar, o(oVar));
        }
        return null;
    }

    @Override // defpackage.id
    public int i(RecyclerView.o oVar, int i, int i2) {
        dd ddVarP;
        int iY = oVar.Y();
        if (iY == 0 || (ddVarP = p(oVar)) == null) {
            return -1;
        }
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        int iJ = oVar.J();
        View view = null;
        View view2 = null;
        for (int i5 = 0; i5 < iJ; i5++) {
            View viewI = oVar.I(i5);
            if (viewI != null) {
                int iM = m(oVar, viewI, ddVarP);
                if (iM <= 0 && iM > i3) {
                    view2 = viewI;
                    i3 = iM;
                }
                if (iM >= 0 && iM < i4) {
                    view = viewI;
                    i4 = iM;
                }
            }
        }
        boolean zR = r(oVar, i, i2);
        if (zR && view != null) {
            return oVar.h0(view);
        }
        if (!zR && view2 != null) {
            return oVar.h0(view2);
        }
        if (zR) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int iH0 = oVar.h0(view) + (s(oVar) == zR ? -1 : 1);
        if (iH0 < 0 || iH0 >= iY) {
            return -1;
        }
        return iH0;
    }

    public final int m(RecyclerView.o oVar, View view, dd ddVar) {
        return (ddVar.g(view) + (ddVar.e(view) / 2)) - (ddVar.m() + (ddVar.n() / 2));
    }

    public final View n(RecyclerView.o oVar, dd ddVar) {
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

    public final dd o(RecyclerView.o oVar) {
        dd ddVar = this.e;
        if (ddVar == null || ddVar.a != oVar) {
            this.e = dd.a(oVar);
        }
        return this.e;
    }

    public final dd p(RecyclerView.o oVar) {
        if (oVar.l()) {
            return q(oVar);
        }
        if (oVar.k()) {
            return o(oVar);
        }
        return null;
    }

    public final dd q(RecyclerView.o oVar) {
        dd ddVar = this.d;
        if (ddVar == null || ddVar.a != oVar) {
            this.d = dd.c(oVar);
        }
        return this.d;
    }

    public final boolean r(RecyclerView.o oVar, int i, int i2) {
        return oVar.k() ? i > 0 : i2 > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean s(RecyclerView.o oVar) {
        PointF pointFA;
        int iY = oVar.Y();
        if (!(oVar instanceof RecyclerView.y.b) || (pointFA = ((RecyclerView.y.b) oVar).a(iY - 1)) == null) {
            return false;
        }
        return pointFA.x < 0.0f || pointFA.y < 0.0f;
    }
}
