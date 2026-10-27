package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public abstract class o extends RecyclerView.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    RecyclerView f1602a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Scroller f1603b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView.t f1604c = new a();

    class a extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1605a = false;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
            if (i == 0 && this.f1605a) {
                this.f1605a = false;
                o.this.l();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            if (i == 0 && i2 == 0) {
                return;
            }
            this.f1605a = true;
        }
    }

    class b extends h {
        b(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.h, androidx.recyclerview.widget.RecyclerView.z
        protected void o(View view, RecyclerView.a0 a0Var, RecyclerView.z.a aVar) {
            o oVar = o.this;
            RecyclerView recyclerView = oVar.f1602a;
            if (recyclerView == null) {
                return;
            }
            int[] iArrC = oVar.c(recyclerView.getLayoutManager(), view);
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
        this.f1602a.Z0(this.f1604c);
        this.f1602a.setOnFlingListener(null);
    }

    private void j() {
        if (this.f1602a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.f1602a.k(this.f1604c);
        this.f1602a.setOnFlingListener(this);
    }

    private boolean k(RecyclerView.o oVar, int i, int i2) {
        RecyclerView.z zVarE;
        int i3;
        if (!(oVar instanceof RecyclerView.z.b) || (zVarE = e(oVar)) == null || (i3 = i(oVar, i, i2)) == -1) {
            return false;
        }
        zVarE.p(i3);
        oVar.f2(zVarE);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i, int i2) {
        RecyclerView.o layoutManager = this.f1602a.getLayoutManager();
        if (layoutManager == null || this.f1602a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f1602a.getMinFlingVelocity();
        return (Math.abs(i2) > minFlingVelocity || Math.abs(i) > minFlingVelocity) && k(layoutManager, i, i2);
    }

    public void b(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f1602a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            g();
        }
        this.f1602a = recyclerView;
        if (recyclerView != null) {
            j();
            this.f1603b = new Scroller(this.f1602a.getContext(), new DecelerateInterpolator());
            l();
        }
    }

    public abstract int[] c(RecyclerView.o oVar, View view);

    public int[] d(int i, int i2) {
        this.f1603b.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.f1603b.getFinalX(), this.f1603b.getFinalY()};
    }

    protected RecyclerView.z e(RecyclerView.o oVar) {
        return f(oVar);
    }

    @Deprecated
    protected h f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.z.b) {
            return new b(this.f1602a.getContext());
        }
        return null;
    }

    public abstract View h(RecyclerView.o oVar);

    public abstract int i(RecyclerView.o oVar, int i, int i2);

    void l() {
        RecyclerView.o layoutManager;
        View viewH;
        RecyclerView recyclerView = this.f1602a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewH = h(layoutManager)) == null) {
            return;
        }
        int[] iArrC = c(layoutManager, viewH);
        if (iArrC[0] == 0 && iArrC[1] == 0) {
            return;
        }
        this.f1602a.m1(iArrC[0], iArrC[1]);
    }
}
