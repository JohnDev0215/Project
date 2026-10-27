package b.r.d;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView.o f3115a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3116b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f3117c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class a extends j {
        public a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // b.r.d.j
        public int d(View view) {
            return this.f3115a.T(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // b.r.d.j
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f3115a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // b.r.d.j
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f3115a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // b.r.d.j
        public int g(View view) {
            return this.f3115a.Q(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // b.r.d.j
        public int h() {
            return this.f3115a.o0();
        }

        @Override // b.r.d.j
        public int i() {
            return this.f3115a.o0() - this.f3115a.f0();
        }

        @Override // b.r.d.j
        public int j() {
            return this.f3115a.f0();
        }

        @Override // b.r.d.j
        public int k() {
            return this.f3115a.p0();
        }

        @Override // b.r.d.j
        public int l() {
            return this.f3115a.X();
        }

        @Override // b.r.d.j
        public int m() {
            return this.f3115a.e0();
        }

        @Override // b.r.d.j
        public int n() {
            return (this.f3115a.o0() - this.f3115a.e0()) - this.f3115a.f0();
        }

        @Override // b.r.d.j
        public int p(View view) {
            this.f3115a.n0(view, true, this.f3117c);
            return this.f3117c.right;
        }

        @Override // b.r.d.j
        public int q(View view) {
            this.f3115a.n0(view, true, this.f3117c);
            return this.f3117c.left;
        }

        @Override // b.r.d.j
        public void r(int i2) {
            this.f3115a.C0(i2);
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class b extends j {
        public b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // b.r.d.j
        public int d(View view) {
            return this.f3115a.O(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // b.r.d.j
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f3115a.R(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // b.r.d.j
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f3115a.S(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // b.r.d.j
        public int g(View view) {
            return this.f3115a.U(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // b.r.d.j
        public int h() {
            return this.f3115a.W();
        }

        @Override // b.r.d.j
        public int i() {
            return this.f3115a.W() - this.f3115a.d0();
        }

        @Override // b.r.d.j
        public int j() {
            return this.f3115a.d0();
        }

        @Override // b.r.d.j
        public int k() {
            return this.f3115a.X();
        }

        @Override // b.r.d.j
        public int l() {
            return this.f3115a.p0();
        }

        @Override // b.r.d.j
        public int m() {
            return this.f3115a.g0();
        }

        @Override // b.r.d.j
        public int n() {
            return (this.f3115a.W() - this.f3115a.g0()) - this.f3115a.d0();
        }

        @Override // b.r.d.j
        public int p(View view) {
            this.f3115a.n0(view, true, this.f3117c);
            return this.f3117c.bottom;
        }

        @Override // b.r.d.j
        public int q(View view) {
            this.f3115a.n0(view, true, this.f3117c);
            return this.f3117c.top;
        }

        @Override // b.r.d.j
        public void r(int i2) {
            this.f3115a.D0(i2);
        }
    }

    public /* synthetic */ j(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static j a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static j b(RecyclerView.o oVar, int i2) {
        if (i2 == 0) {
            return a(oVar);
        }
        if (i2 == 1) {
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
        if (Integer.MIN_VALUE == this.f3116b) {
            return 0;
        }
        return n() - this.f3116b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i2);

    public void s() {
        this.f3116b = n();
    }

    public j(RecyclerView.o oVar) {
        this.f3116b = Integer.MIN_VALUE;
        this.f3117c = new Rect();
        this.f3115a = oVar;
    }
}
