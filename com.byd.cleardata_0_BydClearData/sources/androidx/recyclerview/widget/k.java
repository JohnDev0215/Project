package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final RecyclerView.o f1597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1598b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Rect f1599c;

    static class a extends k {
        a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.k
        public int d(View view) {
            return this.f1597a.p0(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1597a.o0(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1597a.n0(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int g(View view) {
            return this.f1597a.m0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int h() {
            return this.f1597a.G0();
        }

        @Override // androidx.recyclerview.widget.k
        public int i() {
            return this.f1597a.G0() - this.f1597a.b();
        }

        @Override // androidx.recyclerview.widget.k
        public int j() {
            return this.f1597a.b();
        }

        @Override // androidx.recyclerview.widget.k
        public int k() {
            return this.f1597a.H0();
        }

        @Override // androidx.recyclerview.widget.k
        public int l() {
            return this.f1597a.t0();
        }

        @Override // androidx.recyclerview.widget.k
        public int m() {
            return this.f1597a.l();
        }

        @Override // androidx.recyclerview.widget.k
        public int n() {
            return (this.f1597a.G0() - this.f1597a.l()) - this.f1597a.b();
        }

        @Override // androidx.recyclerview.widget.k
        public int p(View view) {
            this.f1597a.F0(view, true, this.f1599c);
            return this.f1599c.right;
        }

        @Override // androidx.recyclerview.widget.k
        public int q(View view) {
            this.f1597a.F0(view, true, this.f1599c);
            return this.f1599c.left;
        }

        @Override // androidx.recyclerview.widget.k
        public void r(int i) {
            this.f1597a.W0(i);
        }
    }

    static class b extends k {
        b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.k
        public int d(View view) {
            return this.f1597a.k0(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1597a.n0(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1597a.o0(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int g(View view) {
            return this.f1597a.q0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int h() {
            return this.f1597a.s0();
        }

        @Override // androidx.recyclerview.widget.k
        public int i() {
            return this.f1597a.s0() - this.f1597a.e();
        }

        @Override // androidx.recyclerview.widget.k
        public int j() {
            return this.f1597a.e();
        }

        @Override // androidx.recyclerview.widget.k
        public int k() {
            return this.f1597a.t0();
        }

        @Override // androidx.recyclerview.widget.k
        public int l() {
            return this.f1597a.H0();
        }

        @Override // androidx.recyclerview.widget.k
        public int m() {
            return this.f1597a.j();
        }

        @Override // androidx.recyclerview.widget.k
        public int n() {
            return (this.f1597a.s0() - this.f1597a.j()) - this.f1597a.e();
        }

        @Override // androidx.recyclerview.widget.k
        public int p(View view) {
            this.f1597a.F0(view, true, this.f1599c);
            return this.f1599c.bottom;
        }

        @Override // androidx.recyclerview.widget.k
        public int q(View view) {
            this.f1597a.F0(view, true, this.f1599c);
            return this.f1599c.top;
        }

        @Override // androidx.recyclerview.widget.k
        public void r(int i) {
            this.f1597a.X0(i);
        }
    }

    private k(RecyclerView.o oVar) {
        this.f1598b = Integer.MIN_VALUE;
        this.f1599c = new Rect();
        this.f1597a = oVar;
    }

    /* synthetic */ k(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static k a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static k b(RecyclerView.o oVar, int i) {
        if (i == 0) {
            return a(oVar);
        }
        if (i == 1) {
            return c(oVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static k c(RecyclerView.o oVar) {
        return new b(oVar);
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
        if (Integer.MIN_VALUE == this.f1598b) {
            return 0;
        }
        return n() - this.f1598b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i);

    public void s() {
        this.f1598b = n();
    }
}
