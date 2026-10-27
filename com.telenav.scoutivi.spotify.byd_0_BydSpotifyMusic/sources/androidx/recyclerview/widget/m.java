package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final RecyclerView.o f3164a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3165b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Rect f3166c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    static class a extends m {
        a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.m
        public int d(View view) {
            return this.f3164a.T(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f3164a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f3164a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int g(View view) {
            return this.f3164a.Q(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int h() {
            return this.f3164a.o0();
        }

        @Override // androidx.recyclerview.widget.m
        public int i() {
            return this.f3164a.o0() - this.f3164a.f0();
        }

        @Override // androidx.recyclerview.widget.m
        public int j() {
            return this.f3164a.f0();
        }

        @Override // androidx.recyclerview.widget.m
        public int k() {
            return this.f3164a.p0();
        }

        @Override // androidx.recyclerview.widget.m
        public int l() {
            return this.f3164a.X();
        }

        @Override // androidx.recyclerview.widget.m
        public int m() {
            return this.f3164a.e0();
        }

        @Override // androidx.recyclerview.widget.m
        public int n() {
            return (this.f3164a.o0() - this.f3164a.e0()) - this.f3164a.f0();
        }

        @Override // androidx.recyclerview.widget.m
        public int p(View view) {
            this.f3164a.n0(view, true, this.f3166c);
            return this.f3166c.right;
        }

        @Override // androidx.recyclerview.widget.m
        public int q(View view) {
            this.f3164a.n0(view, true, this.f3166c);
            return this.f3166c.left;
        }

        @Override // androidx.recyclerview.widget.m
        public void r(int i7) {
            this.f3164a.C0(i7);
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    static class b extends m {
        b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.m
        public int d(View view) {
            return this.f3164a.O(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f3164a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f3164a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int g(View view) {
            return this.f3164a.U(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.m
        public int h() {
            return this.f3164a.W();
        }

        @Override // androidx.recyclerview.widget.m
        public int i() {
            return this.f3164a.W() - this.f3164a.d0();
        }

        @Override // androidx.recyclerview.widget.m
        public int j() {
            return this.f3164a.d0();
        }

        @Override // androidx.recyclerview.widget.m
        public int k() {
            return this.f3164a.X();
        }

        @Override // androidx.recyclerview.widget.m
        public int l() {
            return this.f3164a.p0();
        }

        @Override // androidx.recyclerview.widget.m
        public int m() {
            return this.f3164a.g0();
        }

        @Override // androidx.recyclerview.widget.m
        public int n() {
            return (this.f3164a.W() - this.f3164a.g0()) - this.f3164a.d0();
        }

        @Override // androidx.recyclerview.widget.m
        public int p(View view) {
            this.f3164a.n0(view, true, this.f3166c);
            return this.f3166c.bottom;
        }

        @Override // androidx.recyclerview.widget.m
        public int q(View view) {
            this.f3164a.n0(view, true, this.f3166c);
            return this.f3166c.top;
        }

        @Override // androidx.recyclerview.widget.m
        public void r(int i7) {
            this.f3164a.D0(i7);
        }
    }

    /* synthetic */ m(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static m a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static m b(RecyclerView.o oVar, int i7) {
        if (i7 == 0) {
            return a(oVar);
        }
        if (i7 == 1) {
            return c(oVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static m c(RecyclerView.o oVar) {
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
        if (Integer.MIN_VALUE == this.f3165b) {
            return 0;
        }
        return n() - this.f3165b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i7);

    public void s() {
        this.f3165b = n();
    }

    private m(RecyclerView.o oVar) {
        this.f3165b = Integer.MIN_VALUE;
        this.f3166c = new Rect();
        this.f3164a = oVar;
    }
}
