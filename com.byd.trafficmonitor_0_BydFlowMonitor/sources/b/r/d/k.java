package b.r.d;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PagerSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends o {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f3118d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f3119e;

    /* JADX INFO: compiled from: PagerSnapHelper.java */
    public class a extends g {
        public a(Context context) {
            super(context);
        }

        @Override // b.r.d.g, androidx.recyclerview.widget.RecyclerView.y
        public void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            k kVar = k.this;
            int[] iArrC = kVar.c(kVar.f3125a.getLayoutManager(), view);
            int i2 = iArrC[0];
            int i3 = iArrC[1];
            int iW = w(Math.max(Math.abs(i2), Math.abs(i3)));
            if (iW > 0) {
                aVar.d(i2, i3, iW, this.j);
            }
        }

        @Override // b.r.d.g
        public float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // b.r.d.g
        public int x(int i2) {
            return Math.min(100, super.x(i2));
        }
    }

    @Override // b.r.d.o
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

    @Override // b.r.d.o
    public g f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new a(this.f3125a.getContext());
        }
        return null;
    }

    @Override // b.r.d.o
    public View h(RecyclerView.o oVar) {
        if (oVar.l()) {
            return n(oVar, q(oVar));
        }
        if (oVar.k()) {
            return n(oVar, o(oVar));
        }
        return null;
    }

    @Override // b.r.d.o
    public int i(RecyclerView.o oVar, int i2, int i3) {
        j jVarP;
        int iY = oVar.Y();
        if (iY == 0 || (jVarP = p(oVar)) == null) {
            return -1;
        }
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MAX_VALUE;
        int iJ = oVar.J();
        View view = null;
        View view2 = null;
        for (int i6 = 0; i6 < iJ; i6++) {
            View viewI = oVar.I(i6);
            if (viewI != null) {
                int iM = m(oVar, viewI, jVarP);
                if (iM <= 0 && iM > i4) {
                    view2 = viewI;
                    i4 = iM;
                }
                if (iM >= 0 && iM < i5) {
                    view = viewI;
                    i5 = iM;
                }
            }
        }
        boolean zR = r(oVar, i2, i3);
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
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < iJ; i3++) {
            View viewI = oVar.I(i3);
            int iAbs = Math.abs((jVar.g(viewI) + (jVar.e(viewI) / 2)) - iM);
            if (iAbs < i2) {
                view = viewI;
                i2 = iAbs;
            }
        }
        return view;
    }

    public final j o(RecyclerView.o oVar) {
        j jVar = this.f3119e;
        if (jVar == null || jVar.f3115a != oVar) {
            this.f3119e = j.a(oVar);
        }
        return this.f3119e;
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
        j jVar = this.f3118d;
        if (jVar == null || jVar.f3115a != oVar) {
            this.f3118d = j.c(oVar);
        }
        return this.f3118d;
    }

    public final boolean r(RecyclerView.o oVar, int i2, int i3) {
        return oVar.k() ? i2 > 0 : i3 > 0;
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
