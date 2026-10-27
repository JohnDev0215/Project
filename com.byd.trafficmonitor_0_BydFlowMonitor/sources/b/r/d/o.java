package b.r.d;

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
    public RecyclerView f3125a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Scroller f3126b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RecyclerView.t f3127c = new a();

    /* JADX INFO: compiled from: SnapHelper.java */
    public class a extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3128a = false;

        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i2) {
            super.a(recyclerView, i2);
            if (i2 == 0 && this.f3128a) {
                this.f3128a = false;
                o.this.l();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i2, int i3) {
            if (i2 == 0 && i3 == 0) {
                return;
            }
            this.f3128a = true;
        }
    }

    /* JADX INFO: compiled from: SnapHelper.java */
    public class b extends g {
        public b(Context context) {
            super(context);
        }

        @Override // b.r.d.g, androidx.recyclerview.widget.RecyclerView.y
        public void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            o oVar = o.this;
            RecyclerView recyclerView = oVar.f3125a;
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

        @Override // b.r.d.g
        public float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i2, int i3) {
        RecyclerView.o layoutManager = this.f3125a.getLayoutManager();
        if (layoutManager == null || this.f3125a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f3125a.getMinFlingVelocity();
        return (Math.abs(i3) > minFlingVelocity || Math.abs(i2) > minFlingVelocity) && k(layoutManager, i2, i3);
    }

    public void b(RecyclerView recyclerView) throws IllegalStateException {
        RecyclerView recyclerView2 = this.f3125a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            g();
        }
        this.f3125a = recyclerView;
        if (recyclerView != null) {
            j();
            this.f3126b = new Scroller(this.f3125a.getContext(), new DecelerateInterpolator());
            l();
        }
    }

    public abstract int[] c(RecyclerView.o oVar, View view);

    public int[] d(int i2, int i3) {
        this.f3126b.fling(0, 0, i2, i3, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.f3126b.getFinalX(), this.f3126b.getFinalY()};
    }

    public RecyclerView.y e(RecyclerView.o oVar) {
        return f(oVar);
    }

    @Deprecated
    public g f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new b(this.f3125a.getContext());
        }
        return null;
    }

    public final void g() {
        this.f3125a.Z0(this.f3127c);
        this.f3125a.setOnFlingListener(null);
    }

    public abstract View h(RecyclerView.o oVar);

    public abstract int i(RecyclerView.o oVar, int i2, int i3);

    public final void j() throws IllegalStateException {
        if (this.f3125a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.f3125a.l(this.f3127c);
        this.f3125a.setOnFlingListener(this);
    }

    public final boolean k(RecyclerView.o oVar, int i2, int i3) {
        RecyclerView.y yVarE;
        int i4;
        if (!(oVar instanceof RecyclerView.y.b) || (yVarE = e(oVar)) == null || (i4 = i(oVar, i2, i3)) == -1) {
            return false;
        }
        yVarE.p(i4);
        oVar.J1(yVarE);
        return true;
    }

    public void l() {
        RecyclerView.o layoutManager;
        View viewH;
        RecyclerView recyclerView = this.f3125a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewH = h(layoutManager)) == null) {
            return;
        }
        int[] iArrC = c(layoutManager, viewH);
        if (iArrC[0] == 0 && iArrC[1] == 0) {
            return;
        }
        this.f3125a.m1(iArrC[0], iArrC[1]);
    }
}
