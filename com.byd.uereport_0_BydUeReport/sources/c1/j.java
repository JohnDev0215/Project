package c1;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView.o f2271a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2272b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f2273c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class a extends j {
        public a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // c1.j
        public int d(View view) {
            return this.f2271a.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).rightMargin;
        }

        @Override // c1.j
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f2271a.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // c1.j
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f2271a.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // c1.j
        public int g(View view) {
            return this.f2271a.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).leftMargin;
        }

        @Override // c1.j
        public int h() {
            return this.f2271a.getWidth();
        }

        @Override // c1.j
        public int i() {
            return this.f2271a.getWidth() - this.f2271a.getPaddingRight();
        }

        @Override // c1.j
        public int j() {
            return this.f2271a.getPaddingRight();
        }

        @Override // c1.j
        public int k() {
            return this.f2271a.getWidthMode();
        }

        @Override // c1.j
        public int l() {
            return this.f2271a.getHeightMode();
        }

        @Override // c1.j
        public int m() {
            return this.f2271a.getPaddingLeft();
        }

        @Override // c1.j
        public int n() {
            return (this.f2271a.getWidth() - this.f2271a.getPaddingLeft()) - this.f2271a.getPaddingRight();
        }

        @Override // c1.j
        public int p(View view) {
            this.f2271a.getTransformedBoundingBox(view, true, this.f2273c);
            return this.f2273c.right;
        }

        @Override // c1.j
        public int q(View view) {
            this.f2271a.getTransformedBoundingBox(view, true, this.f2273c);
            return this.f2273c.left;
        }

        @Override // c1.j
        public void r(int i8) {
            this.f2271a.offsetChildrenHorizontal(i8);
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class b extends j {
        public b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // c1.j
        public int d(View view) {
            return this.f2271a.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).bottomMargin;
        }

        @Override // c1.j
        public int e(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f2271a.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }

        @Override // c1.j
        public int f(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return this.f2271a.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }

        @Override // c1.j
        public int g(View view) {
            return this.f2271a.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.p) view.getLayoutParams())).topMargin;
        }

        @Override // c1.j
        public int h() {
            return this.f2271a.getHeight();
        }

        @Override // c1.j
        public int i() {
            return this.f2271a.getHeight() - this.f2271a.getPaddingBottom();
        }

        @Override // c1.j
        public int j() {
            return this.f2271a.getPaddingBottom();
        }

        @Override // c1.j
        public int k() {
            return this.f2271a.getHeightMode();
        }

        @Override // c1.j
        public int l() {
            return this.f2271a.getWidthMode();
        }

        @Override // c1.j
        public int m() {
            return this.f2271a.getPaddingTop();
        }

        @Override // c1.j
        public int n() {
            return (this.f2271a.getHeight() - this.f2271a.getPaddingTop()) - this.f2271a.getPaddingBottom();
        }

        @Override // c1.j
        public int p(View view) {
            this.f2271a.getTransformedBoundingBox(view, true, this.f2273c);
            return this.f2273c.bottom;
        }

        @Override // c1.j
        public int q(View view) {
            this.f2271a.getTransformedBoundingBox(view, true, this.f2273c);
            return this.f2273c.top;
        }

        @Override // c1.j
        public void r(int i8) {
            this.f2271a.offsetChildrenVertical(i8);
        }
    }

    public /* synthetic */ j(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static j a(RecyclerView.o oVar) {
        return new a(oVar);
    }

    public static j b(RecyclerView.o oVar, int i8) {
        if (i8 == 0) {
            return a(oVar);
        }
        if (i8 == 1) {
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
        if (Integer.MIN_VALUE == this.f2272b) {
            return 0;
        }
        return n() - this.f2272b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i8);

    public void s() {
        this.f2272b = n();
    }

    public j(RecyclerView.o oVar) {
        this.f2272b = Integer.MIN_VALUE;
        this.f2273c = new Rect();
        this.f2271a = oVar;
    }
}
