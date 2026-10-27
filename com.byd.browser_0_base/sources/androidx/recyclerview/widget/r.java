package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class r extends s {
    public r(RecyclerView.m mVar) {
        super(mVar);
    }

    @Override // androidx.recyclerview.widget.s
    public final int b(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return RecyclerView.m.y(view) + view.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }

    @Override // androidx.recyclerview.widget.s
    public final int c(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        Rect rect = ((RecyclerView.LayoutParams) view.getLayoutParams()).b;
        return view.getMeasuredHeight() + rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }

    @Override // androidx.recyclerview.widget.s
    public final int d(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        Rect rect = ((RecyclerView.LayoutParams) view.getLayoutParams()).b;
        return view.getMeasuredWidth() + rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    @Override // androidx.recyclerview.widget.s
    public final int e(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return (view.getTop() - RecyclerView.m.O(view)) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
    }

    @Override // androidx.recyclerview.widget.s
    public final int f() {
        return this.a.o;
    }

    @Override // androidx.recyclerview.widget.s
    public final int g() {
        RecyclerView.m mVar = this.a;
        return mVar.o - mVar.G();
    }

    @Override // androidx.recyclerview.widget.s
    public final int h() {
        return this.a.G();
    }

    @Override // androidx.recyclerview.widget.s
    public final int i() {
        return this.a.m;
    }

    @Override // androidx.recyclerview.widget.s
    public final int j() {
        return this.a.l;
    }

    @Override // androidx.recyclerview.widget.s
    public final int k() {
        return this.a.J();
    }

    @Override // androidx.recyclerview.widget.s
    public final int l() {
        RecyclerView.m mVar = this.a;
        return (mVar.o - mVar.J()) - mVar.G();
    }

    @Override // androidx.recyclerview.widget.s
    public final int n(View view) {
        RecyclerView.m mVar = this.a;
        Rect rect = this.c;
        mVar.P(view, rect);
        return rect.bottom;
    }

    @Override // androidx.recyclerview.widget.s
    public final int o(View view) {
        RecyclerView.m mVar = this.a;
        Rect rect = this.c;
        mVar.P(view, rect);
        return rect.top;
    }

    @Override // androidx.recyclerview.widget.s
    public final void p(int i) {
        this.a.U(i);
    }
}
