package b.o.a;

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
    public RecyclerView f1409a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Scroller f1410b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RecyclerView.t f1411c = new a();

    /* JADX INFO: compiled from: SnapHelper.java */
    public class a extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1412a = false;

        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
            if (i == 0 && this.f1412a) {
                this.f1412a = false;
                o.this.l();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            if (i == 0 && i2 == 0) {
                return;
            }
            this.f1412a = true;
        }
    }

    /* JADX INFO: compiled from: SnapHelper.java */
    public class b extends g {
        public b(Context context) {
            super(context);
        }

        @Override // b.o.a.g, androidx.recyclerview.widget.RecyclerView.y
        public void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            o oVar = o.this;
            RecyclerView recyclerView = oVar.f1409a;
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

        @Override // b.o.a.g
        public float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i, int i2) {
        RecyclerView.o layoutManager = this.f1409a.getLayoutManager();
        if (layoutManager == null || this.f1409a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f1409a.getMinFlingVelocity();
        return (Math.abs(i2) > minFlingVelocity || Math.abs(i) > minFlingVelocity) && k(layoutManager, i, i2);
    }

    public void b(RecyclerView recyclerView) throws IllegalStateException {
        RecyclerView recyclerView2 = this.f1409a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            g();
        }
        this.f1409a = recyclerView;
        if (recyclerView != null) {
            j();
            this.f1410b = new Scroller(this.f1409a.getContext(), new DecelerateInterpolator());
            l();
        }
    }

    public abstract int[] c(RecyclerView.o oVar, View view);

    public int[] d(int i, int i2) {
        this.f1410b.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.f1410b.getFinalX(), this.f1410b.getFinalY()};
    }

    public RecyclerView.y e(RecyclerView.o oVar) {
        return f(oVar);
    }

    @Deprecated
    public g f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new b(this.f1409a.getContext());
        }
        return null;
    }

    public final void g() {
        this.f1409a.removeOnScrollListener(this.f1411c);
        this.f1409a.setOnFlingListener(null);
    }

    public abstract View h(RecyclerView.o oVar);

    public abstract int i(RecyclerView.o oVar, int i, int i2);

    public final void j() throws IllegalStateException {
        if (this.f1409a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.f1409a.addOnScrollListener(this.f1411c);
        this.f1409a.setOnFlingListener(this);
    }

    public final boolean k(RecyclerView.o oVar, int i, int i2) {
        RecyclerView.y yVarE;
        int i3;
        if (!(oVar instanceof RecyclerView.y.b) || (yVarE = e(oVar)) == null || (i3 = i(oVar, i, i2)) == -1) {
            return false;
        }
        yVarE.p(i3);
        oVar.J1(yVarE);
        return true;
    }

    public void l() {
        RecyclerView.o layoutManager;
        View viewH;
        RecyclerView recyclerView = this.f1409a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewH = h(layoutManager)) == null) {
            return;
        }
        int[] iArrC = c(layoutManager, viewH);
        if (iArrC[0] == 0 && iArrC[1] == 0) {
            return;
        }
        this.f1409a.smoothScrollBy(iArrC[0], iArrC[1]);
    }
}
