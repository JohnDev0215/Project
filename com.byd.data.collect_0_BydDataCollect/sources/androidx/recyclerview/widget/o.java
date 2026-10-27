package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o extends RecyclerView.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    RecyclerView f2013a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Scroller f2014b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView.t f2015c = new a();

    /* JADX INFO: compiled from: SnapHelper.java */
    class a extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f2016a = false;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i2) {
            super.a(recyclerView, i2);
            if (i2 == 0 && this.f2016a) {
                this.f2016a = false;
                o.this.l();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i2, int i3) {
            if (i2 == 0 && i3 == 0) {
                return;
            }
            this.f2016a = true;
        }
    }

    /* JADX INFO: compiled from: SnapHelper.java */
    class b extends g {
        b(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.g, androidx.recyclerview.widget.RecyclerView.y
        protected void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            o oVar = o.this;
            RecyclerView recyclerView = oVar.f2013a;
            if (recyclerView == null) {
                return;
            }
            int[] iArrC = oVar.c(recyclerView.getLayoutManager(), view);
            int i2 = iArrC[0];
            int i3 = iArrC[1];
            int iW = w(Math.max(Math.abs(i2), Math.abs(i3)));
            if (iW > 0) {
                aVar.d(i2, i3, iW, this.j);
            }
        }

        @Override // androidx.recyclerview.widget.g
        protected float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }

    private void g() {
        this.f2013a.Y0(this.f2015c);
        this.f2013a.setOnFlingListener(null);
    }

    private void j() {
        if (this.f2013a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.f2013a.l(this.f2015c);
        this.f2013a.setOnFlingListener(this);
    }

    private boolean k(RecyclerView.o oVar, int i2, int i3) {
        RecyclerView.y yVarE;
        int i4;
        if (!(oVar instanceof RecyclerView.y.b) || (yVarE = e(oVar)) == null || (i4 = i(oVar, i2, i3)) == -1) {
            return false;
        }
        yVarE.p(i4);
        oVar.J1(yVarE);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i2, int i3) {
        RecyclerView.o layoutManager = this.f2013a.getLayoutManager();
        if (layoutManager == null || this.f2013a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f2013a.getMinFlingVelocity();
        return (Math.abs(i3) > minFlingVelocity || Math.abs(i2) > minFlingVelocity) && k(layoutManager, i2, i3);
    }

    public void b(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f2013a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            g();
        }
        this.f2013a = recyclerView;
        if (recyclerView != null) {
            j();
            this.f2014b = new Scroller(this.f2013a.getContext(), new DecelerateInterpolator());
            l();
        }
    }

    public abstract int[] c(RecyclerView.o oVar, View view);

    public int[] d(int i2, int i3) {
        this.f2014b.fling(0, 0, i2, i3, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.f2014b.getFinalX(), this.f2014b.getFinalY()};
    }

    protected RecyclerView.y e(RecyclerView.o oVar) {
        return f(oVar);
    }

    @Deprecated
    protected g f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new b(this.f2013a.getContext());
        }
        return null;
    }

    public abstract View h(RecyclerView.o oVar);

    public abstract int i(RecyclerView.o oVar, int i2, int i3);

    void l() {
        RecyclerView.o layoutManager;
        View viewH;
        RecyclerView recyclerView = this.f2013a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewH = h(layoutManager)) == null) {
            return;
        }
        int[] iArrC = c(layoutManager, viewH);
        if (iArrC[0] == 0 && iArrC[1] == 0) {
            return;
        }
        this.f2013a.l1(iArrC[0], iArrC[1]);
    }
}
