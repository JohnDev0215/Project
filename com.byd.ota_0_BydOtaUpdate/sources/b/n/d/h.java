package b.n.d;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView.o f2101a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2102b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f2103c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class a extends h {
        public a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // b.n.d.h
        public int d(View view) {
            return this.f2101a.S(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // b.n.d.h
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f2101a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // b.n.d.h
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f2101a.Q(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // b.n.d.h
        public int g(View view) {
            return this.f2101a.P(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // b.n.d.h
        public int h() {
            return this.f2101a.m0();
        }

        @Override // b.n.d.h
        public int i() {
            return this.f2101a.m0() - this.f2101a.d0();
        }

        @Override // b.n.d.h
        public int j() {
            return this.f2101a.d0();
        }

        @Override // b.n.d.h
        public int k() {
            return this.f2101a.n0();
        }

        @Override // b.n.d.h
        public int l() {
            return this.f2101a.W();
        }

        @Override // b.n.d.h
        public int m() {
            return this.f2101a.c0();
        }

        @Override // b.n.d.h
        public int n() {
            return (this.f2101a.m0() - this.f2101a.c0()) - this.f2101a.d0();
        }

        @Override // b.n.d.h
        public int p(View view) {
            this.f2101a.l0(view, true, this.f2103c);
            return this.f2103c.right;
        }

        @Override // b.n.d.h
        public int q(View view) {
            this.f2101a.l0(view, true, this.f2103c);
            return this.f2103c.left;
        }

        @Override // b.n.d.h
        public void r(int i2) {
            this.f2101a.A0(i2);
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class b extends h {
        public b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // b.n.d.h
        public int d(View view) {
            return this.f2101a.N(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // b.n.d.h
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f2101a.Q(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // b.n.d.h
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f2101a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // b.n.d.h
        public int g(View view) {
            return this.f2101a.T(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // b.n.d.h
        public int h() {
            return this.f2101a.V();
        }

        @Override // b.n.d.h
        public int i() {
            return this.f2101a.V() - this.f2101a.b0();
        }

        @Override // b.n.d.h
        public int j() {
            return this.f2101a.b0();
        }

        @Override // b.n.d.h
        public int k() {
            return this.f2101a.W();
        }

        @Override // b.n.d.h
        public int l() {
            return this.f2101a.n0();
        }

        @Override // b.n.d.h
        public int m() {
            return this.f2101a.e0();
        }

        @Override // b.n.d.h
        public int n() {
            return (this.f2101a.V() - this.f2101a.e0()) - this.f2101a.b0();
        }

        @Override // b.n.d.h
        public int p(View view) {
            this.f2101a.l0(view, true, this.f2103c);
            return this.f2103c.bottom;
        }

        @Override // b.n.d.h
        public int q(View view) {
            this.f2101a.l0(view, true, this.f2103c);
            return this.f2103c.top;
        }

        @Override // b.n.d.h
        public void r(int i2) {
            this.f2101a.B0(i2);
        }
    }

    public /* synthetic */ h(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static h a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static h b(RecyclerView.o oVar, int i2) {
        if (i2 == 0) {
            return a(oVar);
        }
        if (i2 == 1) {
            return c(oVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static h c(RecyclerView.o oVar) {
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
        if (Integer.MIN_VALUE == this.f2102b) {
            return 0;
        }
        return n() - this.f2102b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i2);

    public void s() {
        this.f2102b = n();
    }

    public h(RecyclerView.o oVar) {
        this.f2102b = Integer.MIN_VALUE;
        this.f2103c = new Rect();
        this.f2101a = oVar;
    }
}
