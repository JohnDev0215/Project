package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final RecyclerView.o f1467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Rect f1469c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    static class a extends k {
        a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.k
        public int d(View view) {
            return this.f1467a.d0(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1467a.c0(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1467a.b0(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int g(View view) {
            return this.f1467a.a0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int h() {
            return this.f1467a.u0();
        }

        @Override // androidx.recyclerview.widget.k
        public int i() {
            return this.f1467a.u0() - this.f1467a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.k
        public int j() {
            return this.f1467a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.k
        public int k() {
            return this.f1467a.v0();
        }

        @Override // androidx.recyclerview.widget.k
        public int l() {
            return this.f1467a.h0();
        }

        @Override // androidx.recyclerview.widget.k
        public int m() {
            return this.f1467a.getPaddingLeft();
        }

        @Override // androidx.recyclerview.widget.k
        public int n() {
            return (this.f1467a.u0() - this.f1467a.getPaddingLeft()) - this.f1467a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.k
        public int p(View view) {
            this.f1467a.t0(view, true, this.f1469c);
            return this.f1469c.right;
        }

        @Override // androidx.recyclerview.widget.k
        public int q(View view) {
            this.f1467a.t0(view, true, this.f1469c);
            return this.f1469c.left;
        }

        @Override // androidx.recyclerview.widget.k
        public void r(int i) {
            this.f1467a.K0(i);
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    static class b extends k {
        b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // androidx.recyclerview.widget.k
        public int d(View view) {
            return this.f1467a.Y(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1467a.b0(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1467a.c0(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int g(View view) {
            return this.f1467a.e0(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.k
        public int h() {
            return this.f1467a.g0();
        }

        @Override // androidx.recyclerview.widget.k
        public int i() {
            return this.f1467a.g0() - this.f1467a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.k
        public int j() {
            return this.f1467a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.k
        public int k() {
            return this.f1467a.h0();
        }

        @Override // androidx.recyclerview.widget.k
        public int l() {
            return this.f1467a.v0();
        }

        @Override // androidx.recyclerview.widget.k
        public int m() {
            return this.f1467a.getPaddingTop();
        }

        @Override // androidx.recyclerview.widget.k
        public int n() {
            return (this.f1467a.g0() - this.f1467a.getPaddingTop()) - this.f1467a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.k
        public int p(View view) {
            this.f1467a.t0(view, true, this.f1469c);
            return this.f1469c.bottom;
        }

        @Override // androidx.recyclerview.widget.k
        public int q(View view) {
            this.f1467a.t0(view, true, this.f1469c);
            return this.f1469c.top;
        }

        @Override // androidx.recyclerview.widget.k
        public void r(int i) {
            this.f1467a.L0(i);
        }
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
        if (Integer.MIN_VALUE == this.f1468b) {
            return 0;
        }
        return n() - this.f1468b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i);

    public void s() {
        this.f1468b = n();
    }

    private k(RecyclerView.o oVar) {
        this.f1468b = Integer.MIN_VALUE;
        this.f1469c = new Rect();
        this.f1467a = oVar;
    }
}
