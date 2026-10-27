package w0;

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
    public RecyclerView f5662a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Scroller f5663b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RecyclerView.t f5664c = new a();

    /* JADX INFO: compiled from: SnapHelper.java */
    public class a extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5665a = false;

        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i3) {
            super.a(recyclerView, i3);
            if (i3 == 0 && this.f5665a) {
                this.f5665a = false;
                o.this.l();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i3, int i4) {
            if (i3 == 0 && i4 == 0) {
                return;
            }
            this.f5665a = true;
        }
    }

    /* JADX INFO: compiled from: SnapHelper.java */
    public class b extends g {
        public b(Context context) {
            super(context);
        }

        @Override // w0.g, androidx.recyclerview.widget.RecyclerView.y
        public void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            o oVar = o.this;
            RecyclerView recyclerView = oVar.f5662a;
            if (recyclerView == null) {
                return;
            }
            int[] iArrC = oVar.c(recyclerView.getLayoutManager(), view);
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
    }

    @Override // androidx.recyclerview.widget.RecyclerView.r
    public boolean a(int i3, int i4) {
        RecyclerView.o layoutManager = this.f5662a.getLayoutManager();
        if (layoutManager == null || this.f5662a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.f5662a.getMinFlingVelocity();
        return (Math.abs(i4) > minFlingVelocity || Math.abs(i3) > minFlingVelocity) && k(layoutManager, i3, i4);
    }

    public void b(RecyclerView recyclerView) throws IllegalStateException {
        RecyclerView recyclerView2 = this.f5662a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            g();
        }
        this.f5662a = recyclerView;
        if (recyclerView != null) {
            j();
            this.f5663b = new Scroller(this.f5662a.getContext(), new DecelerateInterpolator());
            l();
        }
    }

    public abstract int[] c(RecyclerView.o oVar, View view);

    public int[] d(int i3, int i4) {
        this.f5663b.fling(0, 0, i3, i4, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.f5663b.getFinalX(), this.f5663b.getFinalY()};
    }

    public RecyclerView.y e(RecyclerView.o oVar) {
        return f(oVar);
    }

    @Deprecated
    public g f(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new b(this.f5662a.getContext());
        }
        return null;
    }

    public final void g() {
        this.f5662a.Y0(this.f5664c);
        this.f5662a.setOnFlingListener(null);
    }

    public abstract View h(RecyclerView.o oVar);

    public abstract int i(RecyclerView.o oVar, int i3, int i4);

    public final void j() throws IllegalStateException {
        if (this.f5662a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.f5662a.l(this.f5664c);
        this.f5662a.setOnFlingListener(this);
    }

    public final boolean k(RecyclerView.o oVar, int i3, int i4) {
        RecyclerView.y yVarE;
        int i5;
        if (!(oVar instanceof RecyclerView.y.b) || (yVarE = e(oVar)) == null || (i5 = i(oVar, i3, i4)) == -1) {
            return false;
        }
        yVarE.p(i5);
        oVar.J1(yVarE);
        return true;
    }

    public void l() {
        RecyclerView.o layoutManager;
        View viewH;
        RecyclerView recyclerView = this.f5662a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewH = h(layoutManager)) == null) {
            return;
        }
        int[] iArrC = c(layoutManager, viewH);
        if (iArrC[0] == 0 && iArrC[1] == 0) {
            return;
        }
        this.f5662a.l1(iArrC[0], iArrC[1]);
    }
}
