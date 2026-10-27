package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p extends RecyclerView.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    RecyclerView f1476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Scroller f1477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView.t f1478c = new a();

    /* JADX INFO: compiled from: SnapHelper.java */
    class a extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1479a = false;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
            if (i == 0 && this.f1479a) {
                this.f1479a = false;
                p.this.l();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            if (i == 0 && i2 == 0) {
                return;
            }
            this.f1479a = true;
        }
    }

    /* JADX INFO: compiled from: SnapHelper.java */
    class b extends h {
        b(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.h, androidx.recyclerview.widget.RecyclerView.y
        protected void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            p pVar = p.this;
            RecyclerView recyclerView = pVar.f1476a;
            if (recyclerView == null) {
                return;
            }
            int[] iArrC = pVar.c(recyclerView.getLayoutManager(), view);
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
    }

    private void g() {
        this.f1476a.a1(this.f1478c);
        this.f1476a.setOnFlingListener(null);
    }

    private void j() throws IllegalStateException {
        if (this.f1476a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.f1476a.l(this.f1478c);
        this.f1476a.setOnFlingListener(this);
    }

    private boolean k(RecyclerView.o oVar, int i, int i2) {
        RecyclerView.y yVarE;
        int i3;
        if (!(oVar instanceof RecyclerView.y.b) || (yVarE = e(oVar)) == null || (i3 = i(oVar, i, i2)) == -1) {
            return false;
        }
        yVarE.p(i3);
        oVar.T1(yVarE);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i, int i2) {
        RecyclerView.o layoutManager = this.f1476a.getLayoutManager();
        if (layoutManager == null || this.f1476a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f1476a.getMinFlingVelocity();
        return (Math.abs(i2) > minFlingVelocity || Math.abs(i) > minFlingVelocity) && k(layoutManager, i, i2);
    }

    public void b(RecyclerView recyclerView) throws IllegalStateException {
        RecyclerView recyclerView2 = this.f1476a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            g();
        }
        this.f1476a = recyclerView;
        if (recyclerView != null) {
            j();
            this.f1477b = new Scroller(this.f1476a.getContext(), new DecelerateInterpolator());
            l();
        }
    }

    public abstract int[] c(RecyclerView.o oVar, View view);

    public int[] d(int i, int i2) {
        this.f1477b.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.f1477b.getFinalX(), this.f1477b.getFinalY()};
    }

    protected RecyclerView.y e(RecyclerView.o oVar) {
        return f(oVar);
    }

    @Deprecated
    protected h f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new b(this.f1476a.getContext());
        }
        return null;
    }

    public abstract View h(RecyclerView.o oVar);

    public abstract int i(RecyclerView.o oVar, int i, int i2);

    void l() {
        RecyclerView.o layoutManager;
        View viewH;
        RecyclerView recyclerView = this.f1476a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewH = h(layoutManager)) == null) {
            return;
        }
        int[] iArrC = c(layoutManager, viewH);
        if (iArrC[0] == 0 && iArrC[1] == 0) {
            return;
        }
        this.f1476a.n1(iArrC[0], iArrC[1]);
    }
}
