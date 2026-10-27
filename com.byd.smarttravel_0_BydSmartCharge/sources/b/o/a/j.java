package b.o.a;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView.o f1403a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1404b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f1405c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class a extends j {
        public a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // b.o.a.j
        public int d(View view) {
            return this.f1403a.T(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // b.o.a.j
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1403a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // b.o.a.j
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1403a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // b.o.a.j
        public int g(View view) {
            return this.f1403a.Q(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // b.o.a.j
        public int h() {
            return this.f1403a.o0();
        }

        @Override // b.o.a.j
        public int i() {
            return this.f1403a.o0() - this.f1403a.f0();
        }

        @Override // b.o.a.j
        public int j() {
            return this.f1403a.f0();
        }

        @Override // b.o.a.j
        public int k() {
            return this.f1403a.p0();
        }

        @Override // b.o.a.j
        public int l() {
            return this.f1403a.X();
        }

        @Override // b.o.a.j
        public int m() {
            return this.f1403a.e0();
        }

        @Override // b.o.a.j
        public int n() {
            return (this.f1403a.o0() - this.f1403a.e0()) - this.f1403a.f0();
        }

        @Override // b.o.a.j
        public int p(View view) {
            this.f1403a.n0(view, true, this.f1405c);
            return this.f1405c.right;
        }

        @Override // b.o.a.j
        public int q(View view) {
            this.f1403a.n0(view, true, this.f1405c);
            return this.f1405c.left;
        }

        @Override // b.o.a.j
        public void r(int i) {
            this.f1403a.C0(i);
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class b extends j {
        public b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // b.o.a.j
        public int d(View view) {
            return this.f1403a.O(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // b.o.a.j
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1403a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // b.o.a.j
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f1403a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // b.o.a.j
        public int g(View view) {
            return this.f1403a.U(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // b.o.a.j
        public int h() {
            return this.f1403a.W();
        }

        @Override // b.o.a.j
        public int i() {
            return this.f1403a.W() - this.f1403a.d0();
        }

        @Override // b.o.a.j
        public int j() {
            return this.f1403a.d0();
        }

        @Override // b.o.a.j
        public int k() {
            return this.f1403a.X();
        }

        @Override // b.o.a.j
        public int l() {
            return this.f1403a.p0();
        }

        @Override // b.o.a.j
        public int m() {
            return this.f1403a.g0();
        }

        @Override // b.o.a.j
        public int n() {
            return (this.f1403a.W() - this.f1403a.g0()) - this.f1403a.d0();
        }

        @Override // b.o.a.j
        public int p(View view) {
            this.f1403a.n0(view, true, this.f1405c);
            return this.f1405c.bottom;
        }

        @Override // b.o.a.j
        public int q(View view) {
            this.f1403a.n0(view, true, this.f1405c);
            return this.f1405c.top;
        }

        @Override // b.o.a.j
        public void r(int i) {
            this.f1403a.D0(i);
        }
    }

    public /* synthetic */ j(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static j a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static j b(RecyclerView.o oVar, int i) {
        if (i == 0) {
            return a(oVar);
        }
        if (i == 1) {
            return c(oVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static j c(RecyclerView.o oVar) {
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
        if (Integer.MIN_VALUE == this.f1404b) {
            return 0;
        }
        return n() - this.f1404b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i);

    public void s() {
        this.f1404b = n();
    }

    public j(RecyclerView.o oVar) {
        this.f1404b = Integer.MIN_VALUE;
        this.f1405c = new Rect();
        this.f1403a = oVar;
    }
}
