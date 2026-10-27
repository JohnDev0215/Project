package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PagerSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private m f3167d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private m f3168e;

    /* JADX INFO: compiled from: PagerSnapHelper.java */
    class a extends j {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.j, androidx.recyclerview.widget.RecyclerView.y
        protected void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            n nVar = n.this;
            int[] iArrC = nVar.c(nVar.f3175a.getLayoutManager(), view);
            int i7 = iArrC[0];
            int i8 = iArrC[1];
            int iW = w(Math.max(Math.abs(i7), Math.abs(i8)));
            if (iW > 0) {
                aVar.d(i7, i8, iW, this.f3156j);
            }
        }

        @Override // androidx.recyclerview.widget.j
        protected float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // androidx.recyclerview.widget.j
        protected int x(int i7) {
            return Math.min(100, super.x(i7));
        }
    }

    private int l(RecyclerView.o oVar, View view, m mVar) {
        return (mVar.g(view) + (mVar.e(view) / 2)) - (mVar.m() + (mVar.n() / 2));
    }

    private View m(RecyclerView.o oVar, m mVar) {
        int iJ = oVar.J();
        View view = null;
        if (iJ == 0) {
            return null;
        }
        int iM = mVar.m() + (mVar.n() / 2);
        int i7 = Integer.MAX_VALUE;
        for (int i8 = 0; i8 < iJ; i8++) {
            View viewI = oVar.I(i8);
            int iAbs = Math.abs((mVar.g(viewI) + (mVar.e(viewI) / 2)) - iM);
            if (iAbs < i7) {
                view = viewI;
                i7 = iAbs;
            }
        }
        return view;
    }

    private m n(RecyclerView.o oVar) {
        m mVar = this.f3168e;
        if (mVar == null || mVar.f3164a != oVar) {
            this.f3168e = m.a(oVar);
        }
        return this.f3168e;
    }

    private m o(RecyclerView.o oVar) {
        if (oVar.l()) {
            return p(oVar);
        }
        if (oVar.k()) {
            return n(oVar);
        }
        return null;
    }

    private m p(RecyclerView.o oVar) {
        m mVar = this.f3167d;
        if (mVar == null || mVar.f3164a != oVar) {
            this.f3167d = m.c(oVar);
        }
        return this.f3167d;
    }

    private boolean q(RecyclerView.o oVar, int i7, int i8) {
        return oVar.k() ? i7 > 0 : i8 > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean r(RecyclerView.o oVar) {
        PointF pointFA;
        int iY = oVar.Y();
        if (!(oVar instanceof RecyclerView.y.b) || (pointFA = ((RecyclerView.y.b) oVar).a(iY - 1)) == null) {
            return false;
        }
        return pointFA.x < 0.0f || pointFA.y < 0.0f;
    }

    @Override // androidx.recyclerview.widget.r
    public int[] c(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.k()) {
            iArr[0] = l(oVar, view, n(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.l()) {
            iArr[1] = l(oVar, view, p(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.r
    protected j e(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new a(this.f3175a.getContext());
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.r
    public View g(RecyclerView.o oVar) {
        if (oVar.l()) {
            return m(oVar, p(oVar));
        }
        if (oVar.k()) {
            return m(oVar, n(oVar));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.r
    public int h(RecyclerView.o oVar, int i7, int i8) {
        m mVarO;
        int iY = oVar.Y();
        if (iY == 0 || (mVarO = o(oVar)) == null) {
            return -1;
        }
        int i9 = Integer.MIN_VALUE;
        int i10 = Integer.MAX_VALUE;
        int iJ = oVar.J();
        View view = null;
        View view2 = null;
        for (int i11 = 0; i11 < iJ; i11++) {
            View viewI = oVar.I(i11);
            if (viewI != null) {
                int iL = l(oVar, viewI, mVarO);
                if (iL <= 0 && iL > i9) {
                    view2 = viewI;
                    i9 = iL;
                }
                if (iL >= 0 && iL < i10) {
                    view = viewI;
                    i10 = iL;
                }
            }
        }
        boolean zQ = q(oVar, i7, i8);
        if (zQ && view != null) {
            return oVar.h0(view);
        }
        if (!zQ && view2 != null) {
            return oVar.h0(view2);
        }
        if (zQ) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int iH0 = oVar.h0(view) + (r(oVar) == zQ ? -1 : 1);
        if (iH0 < 0 || iH0 >= iY) {
            return -1;
        }
        return iH0;
    }
}
