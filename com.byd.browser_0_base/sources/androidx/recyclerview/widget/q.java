package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends s {
    public q(RecyclerView.m mVar) {
        super(mVar);
    }

    @Override // androidx.recyclerview.widget.s
    public final int b(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return RecyclerView.m.M(view) + view.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    @Override // androidx.recyclerview.widget.s
    public final int c(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        Rect rect = ((RecyclerView.LayoutParams) view.getLayoutParams()).b;
        return view.getMeasuredWidth() + rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    @Override // androidx.recyclerview.widget.s
    public final int d(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        Rect rect = ((RecyclerView.LayoutParams) view.getLayoutParams()).b;
        return view.getMeasuredHeight() + rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }

    @Override // androidx.recyclerview.widget.s
    public final int e(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return (view.getLeft() - RecyclerView.m.F(view)) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
    }

    @Override // androidx.recyclerview.widget.s
    public final int f() {
        return this.a.n;
    }

    @Override // androidx.recyclerview.widget.s
    public final int g() {
        RecyclerView.m mVar = this.a;
        return mVar.n - mVar.I();
    }

    @Override // androidx.recyclerview.widget.s
    public final int h() {
        return this.a.I();
    }

    @Override // androidx.recyclerview.widget.s
    public final int i() {
        return this.a.l;
    }

    @Override // androidx.recyclerview.widget.s
    public final int j() {
        return this.a.m;
    }

    @Override // androidx.recyclerview.widget.s
    public final int k() {
        return this.a.H();
    }

    @Override // androidx.recyclerview.widget.s
    public final int l() {
        RecyclerView.m mVar = this.a;
        return (mVar.n - mVar.H()) - mVar.I();
    }

    @Override // androidx.recyclerview.widget.s
    public final int n(View view) {
        RecyclerView.m mVar = this.a;
        Rect rect = this.c;
        mVar.P(view, rect);
        return rect.right;
    }

    @Override // androidx.recyclerview.widget.s
    public final int o(View view) {
        RecyclerView.m mVar = this.a;
        Rect rect = this.c;
        mVar.P(view, rect);
        return rect.left;
    }

    @Override // androidx.recyclerview.widget.s
    public final void p(int i) {
        this.a.T(i);
    }
}
