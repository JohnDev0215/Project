package w0;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView.o f5651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5652b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f5653c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class a extends j {
        public a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // w0.j
        public int d(View view) {
            return this.f5651a.T(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // w0.j
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f5651a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // w0.j
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f5651a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // w0.j
        public int g(View view) {
            return this.f5651a.Q(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // w0.j
        public int h() {
            return this.f5651a.o0();
        }

        @Override // w0.j
        public int i() {
            return this.f5651a.o0() - this.f5651a.f0();
        }

        @Override // w0.j
        public int j() {
            return this.f5651a.f0();
        }

        @Override // w0.j
        public int k() {
            return this.f5651a.p0();
        }

        @Override // w0.j
        public int l() {
            return this.f5651a.X();
        }

        @Override // w0.j
        public int m() {
            return this.f5651a.e0();
        }

        @Override // w0.j
        public int n() {
            return (this.f5651a.o0() - this.f5651a.e0()) - this.f5651a.f0();
        }

        @Override // w0.j
        public int p(View view) {
            this.f5651a.n0(view, true, this.f5653c);
            return this.f5653c.right;
        }

        @Override // w0.j
        public int q(View view) {
            this.f5651a.n0(view, true, this.f5653c);
            return this.f5653c.left;
        }

        @Override // w0.j
        public void r(int i3) {
            this.f5651a.C0(i3);
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class b extends j {
        public b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // w0.j
        public int d(View view) {
            return this.f5651a.O(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // w0.j
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f5651a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // w0.j
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f5651a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // w0.j
        public int g(View view) {
            return this.f5651a.U(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // w0.j
        public int h() {
            return this.f5651a.W();
        }

        @Override // w0.j
        public int i() {
            return this.f5651a.W() - this.f5651a.d0();
        }

        @Override // w0.j
        public int j() {
            return this.f5651a.d0();
        }

        @Override // w0.j
        public int k() {
            return this.f5651a.X();
        }

        @Override // w0.j
        public int l() {
            return this.f5651a.p0();
        }

        @Override // w0.j
        public int m() {
            return this.f5651a.g0();
        }

        @Override // w0.j
        public int n() {
            return (this.f5651a.W() - this.f5651a.g0()) - this.f5651a.d0();
        }

        @Override // w0.j
        public int p(View view) {
            this.f5651a.n0(view, true, this.f5653c);
            return this.f5653c.bottom;
        }

        @Override // w0.j
        public int q(View view) {
            this.f5651a.n0(view, true, this.f5653c);
            return this.f5653c.top;
        }

        @Override // w0.j
        public void r(int i3) {
            this.f5651a.D0(i3);
        }
    }

    public /* synthetic */ j(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static j a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static j b(RecyclerView.o oVar, int i3) {
        if (i3 == 0) {
            return a(oVar);
        }
        if (i3 == 1) {
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
        if (Integer.MIN_VALUE == this.f5652b) {
            return 0;
        }
        return n() - this.f5652b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i3);

    public void s() {
        this.f5652b = n();
    }

    public j(RecyclerView.o oVar) {
        this.f5652b = Integer.MIN_VALUE;
        this.f5653c = new Rect();
        this.f5651a = oVar;
    }
}
