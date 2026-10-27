package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final RecyclerView.m f1218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1219b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Rect f1220c;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends k {
        a(RecyclerView.m mVar) {
            super(mVar, null);
        }

        @Override // androidx.recyclerview.widget.k
        public int d(View view) {
            return this.f1218a.S(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.n) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int e(View view) {
            RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
            return this.f1218a.R(view) + ((ViewGroup.MarginLayoutParams) nVar).leftMargin + ((ViewGroup.MarginLayoutParams) nVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int f(View view) {
            RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
            return this.f1218a.Q(view) + ((ViewGroup.MarginLayoutParams) nVar).topMargin + ((ViewGroup.MarginLayoutParams) nVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int g(View view) {
            return this.f1218a.P(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.n) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int h() {
            return this.f1218a.m0();
        }

        @Override // androidx.recyclerview.widget.k
        public int i() {
            return this.f1218a.m0() - this.f1218a.d0();
        }

        @Override // androidx.recyclerview.widget.k
        public int j() {
            return this.f1218a.d0();
        }

        @Override // androidx.recyclerview.widget.k
        public int k() {
            return this.f1218a.n0();
        }

        @Override // androidx.recyclerview.widget.k
        public int l() {
            return this.f1218a.W();
        }

        @Override // androidx.recyclerview.widget.k
        public int m() {
            return this.f1218a.c0();
        }

        @Override // androidx.recyclerview.widget.k
        public int n() {
            return (this.f1218a.m0() - this.f1218a.c0()) - this.f1218a.d0();
        }

        @Override // androidx.recyclerview.widget.k
        public int p(View view) {
            this.f1218a.l0(view, true, this.f1220c);
            return this.f1220c.right;
        }

        @Override // androidx.recyclerview.widget.k
        public int q(View view) {
            this.f1218a.l0(view, true, this.f1220c);
            return this.f1220c.left;
        }

        @Override // androidx.recyclerview.widget.k
        public void r(int i) {
            this.f1218a.A0(i);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends k {
        b(RecyclerView.m mVar) {
            super(mVar, null);
        }

        @Override // androidx.recyclerview.widget.k
        public int d(View view) {
            return this.f1218a.N(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.n) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int e(View view) {
            RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
            return this.f1218a.Q(view) + ((ViewGroup.MarginLayoutParams) nVar).topMargin + ((ViewGroup.MarginLayoutParams) nVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int f(View view) {
            RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
            return this.f1218a.R(view) + ((ViewGroup.MarginLayoutParams) nVar).leftMargin + ((ViewGroup.MarginLayoutParams) nVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int g(View view) {
            return this.f1218a.T(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.n) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int h() {
            return this.f1218a.V();
        }

        @Override // androidx.recyclerview.widget.k
        public int i() {
            return this.f1218a.V() - this.f1218a.b0();
        }

        @Override // androidx.recyclerview.widget.k
        public int j() {
            return this.f1218a.b0();
        }

        @Override // androidx.recyclerview.widget.k
        public int k() {
            return this.f1218a.W();
        }

        @Override // androidx.recyclerview.widget.k
        public int l() {
            return this.f1218a.n0();
        }

        @Override // androidx.recyclerview.widget.k
        public int m() {
            return this.f1218a.e0();
        }

        @Override // androidx.recyclerview.widget.k
        public int n() {
            return (this.f1218a.V() - this.f1218a.e0()) - this.f1218a.b0();
        }

        @Override // androidx.recyclerview.widget.k
        public int p(View view) {
            this.f1218a.l0(view, true, this.f1220c);
            return this.f1220c.bottom;
        }

        @Override // androidx.recyclerview.widget.k
        public int q(View view) {
            this.f1218a.l0(view, true, this.f1220c);
            return this.f1220c.top;
        }

        @Override // androidx.recyclerview.widget.k
        public void r(int i) {
            this.f1218a.B0(i);
        }
    }

    /* synthetic */ k(RecyclerView.m mVar, a aVar) {
        this(mVar);
    }

    public static k a(RecyclerView.m mVar) {
        return new a(mVar);
    }

    public static k b(RecyclerView.m mVar, int i) {
        if (i == 0) {
            return a(mVar);
        }
        if (i == 1) {
            return c(mVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static k c(RecyclerView.m mVar) {
        return new b(mVar);
    }

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f(View view);

    public abstract int g(View view);

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public abstract int m();

    public abstract int n();

    public int o() {
        if (Integer.MIN_VALUE == this.f1219b) {
            return 0;
        }
        return n() - this.f1219b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i);

    public void s() {
        this.f1219b = n();
    }

    private k(RecyclerView.m mVar) {
        this.f1219b = Integer.MIN_VALUE;
        this.f1220c = new Rect();
        this.f1218a = mVar;
    }
}
