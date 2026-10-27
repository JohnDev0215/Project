package androidx.recyclerview.widget;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class r extends RecyclerView.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    RecyclerView f3175a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Scroller f3176b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView.t f3177c = new a();

    /* JADX INFO: compiled from: SnapHelper.java */
    class a extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f3178a = false;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i7) {
            super.a(recyclerView, i7);
            if (i7 == 0 && this.f3178a) {
                this.f3178a = false;
                r.this.k();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i7, int i8) {
            if (i7 == 0 && i8 == 0) {
                return;
            }
            this.f3178a = true;
        }
    }

    private void f() {
        this.f3175a.a1(this.f3177c);
        this.f3175a.setOnFlingListener(null);
    }

    private void i() {
        if (this.f3175a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.f3175a.l(this.f3177c);
        this.f3175a.setOnFlingListener(this);
    }

    private boolean j(RecyclerView.o oVar, int i7, int i8) {
        RecyclerView.y yVarD;
        int iH;
        if (!(oVar instanceof RecyclerView.y.b) || (yVarD = d(oVar)) == null || (iH = h(oVar, i7, i8)) == -1) {
            return false;
        }
        yVarD.p(iH);
        oVar.J1(yVarD);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i7, int i8) {
        RecyclerView.o layoutManager = this.f3175a.getLayoutManager();
        if (layoutManager == null || this.f3175a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f3175a.getMinFlingVelocity();
        return (Math.abs(i8) > minFlingVelocity || Math.abs(i7) > minFlingVelocity) && j(layoutManager, i7, i8);
    }

    public void b(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f3175a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            f();
        }
        this.f3175a = recyclerView;
        if (recyclerView != null) {
            i();
            this.f3176b = new Scroller(this.f3175a.getContext(), new DecelerateInterpolator());
            k();
        }
    }

    public abstract int[] c(RecyclerView.o oVar, View view);

    protected RecyclerView.y d(RecyclerView.o oVar) {
        return e(oVar);
    }

    @Deprecated
    protected abstract j e(RecyclerView.o oVar);

    public abstract View g(RecyclerView.o oVar);

    public abstract int h(RecyclerView.o oVar, int i7, int i8);

    void k() {
        RecyclerView.o layoutManager;
        View viewG;
        RecyclerView recyclerView = this.f3175a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewG = g(layoutManager)) == null) {
            return;
        }
        int[] iArrC = c(layoutManager, viewG);
        if (iArrC[0] == 0 && iArrC[1] == 0) {
            return;
        }
        this.f3175a.n1(iArrC[0], iArrC[1]);
    }
}
