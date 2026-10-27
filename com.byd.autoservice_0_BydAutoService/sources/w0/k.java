package w0;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PagerSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends o {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f5654d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f5655e;

    /* JADX INFO: compiled from: PagerSnapHelper.java */
    public class a extends g {
        public a(Context context) {
            super(context);
        }

        @Override // w0.g, androidx.recyclerview.widget.RecyclerView.y
        public void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            k kVar = k.this;
            int[] iArrC = kVar.c(kVar.f5662a.getLayoutManager(), view);
            int i3 = iArrC[0];
            int i4 = iArrC[1];
            int iW = w(Math.max(Math.abs(i3), Math.abs(i4)));
            if (iW > 0) {
                aVar.d(i3, i4, iW, this.f5641j);
            }
        }

        @Override // w0.g
        public float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // w0.g
        public int x(int i3) {
            return Math.min(100, super.x(i3));
        }
    }

    @Override // w0.o
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

    @Override // w0.o
    public g f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new a(this.f5662a.getContext());
        }
        return null;
    }

    @Override // w0.o
    public View h(RecyclerView.o oVar) {
        if (oVar.l()) {
            return n(oVar, q(oVar));
        }
        if (oVar.k()) {
            return n(oVar, o(oVar));
        }
        return null;
    }

    @Override // w0.o
    public int i(RecyclerView.o oVar, int i3, int i4) {
        j jVarP;
        int iY = oVar.Y();
        if (iY == 0 || (jVarP = p(oVar)) == null) {
            return -1;
        }
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MAX_VALUE;
        int iJ = oVar.J();
        View view = null;
        View view2 = null;
        for (int i7 = 0; i7 < iJ; i7++) {
            View viewI = oVar.I(i7);
            if (viewI != null) {
                int iM = m(oVar, viewI, jVarP);
                if (iM <= 0 && iM > i5) {
                    view2 = viewI;
                    i5 = iM;
                }
                if (iM >= 0 && iM < i6) {
                    view = viewI;
                    i6 = iM;
                }
            }
        }
        boolean zR = r(oVar, i3, i4);
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

    public final int m(RecyclerView.o oVar, View view, j jVar) {
        return (jVar.g(view) + (jVar.e(view) / 2)) - (jVar.m() + (jVar.n() / 2));
    }

    public final View n(RecyclerView.o oVar, j jVar) {
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

    public final j o(RecyclerView.o oVar) {
        j jVar = this.f5655e;
        if (jVar == null || jVar.f5651a != oVar) {
            this.f5655e = j.a(oVar);
        }
        return this.f5655e;
    }

    public final j p(RecyclerView.o oVar) {
        if (oVar.l()) {
            return q(oVar);
        }
        if (oVar.k()) {
            return o(oVar);
        }
        return null;
    }

    public final j q(RecyclerView.o oVar) {
        j jVar = this.f5654d;
        if (jVar == null || jVar.f5651a != oVar) {
            this.f5654d = j.c(oVar);
        }
        return this.f5654d;
    }

    public final boolean r(RecyclerView.o oVar, int i3, int i4) {
        return oVar.k() ? i3 > 0 : i4 > 0;
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
