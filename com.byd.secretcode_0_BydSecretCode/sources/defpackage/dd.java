package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class dd {
    public final RecyclerView.o a;
    public int b;
    public final Rect c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class a extends dd {
        public a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // defpackage.dd
        public int d(View view) {
            return this.a.T(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // defpackage.dd
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // defpackage.dd
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // defpackage.dd
        public int g(View view) {
            return this.a.Q(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // defpackage.dd
        public int h() {
            return this.a.o0();
        }

        @Override // defpackage.dd
        public int i() {
            return this.a.o0() - this.a.f0();
        }

        @Override // defpackage.dd
        public int j() {
            return this.a.f0();
        }

        @Override // defpackage.dd
        public int k() {
            return this.a.p0();
        }

        @Override // defpackage.dd
        public int l() {
            return this.a.X();
        }

        @Override // defpackage.dd
        public int m() {
            return this.a.e0();
        }

        @Override // defpackage.dd
        public int n() {
            return (this.a.o0() - this.a.e0()) - this.a.f0();
        }

        @Override // defpackage.dd
        public int p(View view) {
            this.a.n0(view, true, this.c);
            return this.c.right;
        }

        @Override // defpackage.dd
        public int q(View view) {
            this.a.n0(view, true, this.c);
            return this.c.left;
        }

        @Override // defpackage.dd
        public void r(int i) {
            this.a.C0(i);
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class b extends dd {
        public b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // defpackage.dd
        public int d(View view) {
            return this.a.O(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // defpackage.dd
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // defpackage.dd
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // defpackage.dd
        public int g(View view) {
            return this.a.U(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // defpackage.dd
        public int h() {
            return this.a.W();
        }

        @Override // defpackage.dd
        public int i() {
            return this.a.W() - this.a.d0();
        }

        @Override // defpackage.dd
        public int j() {
            return this.a.d0();
        }

        @Override // defpackage.dd
        public int k() {
            return this.a.X();
        }

        @Override // defpackage.dd
        public int l() {
            return this.a.p0();
        }

        @Override // defpackage.dd
        public int m() {
            return this.a.g0();
        }

        @Override // defpackage.dd
        public int n() {
            return (this.a.W() - this.a.g0()) - this.a.d0();
        }

        @Override // defpackage.dd
        public int p(View view) {
            this.a.n0(view, true, this.c);
            return this.c.bottom;
        }

        @Override // defpackage.dd
        public int q(View view) {
            this.a.n0(view, true, this.c);
            return this.c.top;
        }

        @Override // defpackage.dd
        public void r(int i) {
            this.a.D0(i);
        }
    }

    public /* synthetic */ dd(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static dd a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static dd b(RecyclerView.o oVar, int i) {
        if (i == 0) {
            return a(oVar);
        }
        if (i == 1) {
            return c(oVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static dd c(RecyclerView.o oVar) {
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
        if (Integer.MIN_VALUE == this.b) {
            return 0;
        }
        return n() - this.b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i);

    public void s() {
        this.b = n();
    }

    public dd(RecyclerView.o oVar) {
        this.b = Integer.MIN_VALUE;
        this.c = new Rect();
        this.a = oVar;
    }
}
