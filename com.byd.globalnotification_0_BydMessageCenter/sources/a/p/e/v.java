package a.p.e;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class v extends w {
    public v(RecyclerView.m mVar) {
        super(mVar, null);
    }

    @Override // a.p.e.w
    public int b(View view) {
        return this.f1264a.C(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.n) view.getLayoutParams())).bottomMargin;
    }

    @Override // a.p.e.w
    public int c(View view) {
        RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
        return this.f1264a.E(view) + ((ViewGroup.MarginLayoutParams) nVar).topMargin + ((ViewGroup.MarginLayoutParams) nVar).bottomMargin;
    }

    @Override // a.p.e.w
    public int d(View view) {
        RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
        return this.f1264a.F(view) + ((ViewGroup.MarginLayoutParams) nVar).leftMargin + ((ViewGroup.MarginLayoutParams) nVar).rightMargin;
    }

    @Override // a.p.e.w
    public int e(View view) {
        return this.f1264a.H(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.n) view.getLayoutParams())).topMargin;
    }

    @Override // a.p.e.w
    public int f() {
        return this.f1264a.r;
    }

    @Override // a.p.e.w
    public int g() {
        RecyclerView.m mVar = this.f1264a;
        return mVar.r - mVar.N();
    }

    @Override // a.p.e.w
    public int h() {
        return this.f1264a.N();
    }

    @Override // a.p.e.w
    public int i() {
        return this.f1264a.p;
    }

    @Override // a.p.e.w
    public int j() {
        return this.f1264a.o;
    }

    @Override // a.p.e.w
    public int k() {
        return this.f1264a.Q();
    }

    @Override // a.p.e.w
    public int l() {
        RecyclerView.m mVar = this.f1264a;
        return (mVar.r - mVar.Q()) - this.f1264a.N();
    }

    @Override // a.p.e.w
    public int n(View view) {
        this.f1264a.V(view, true, this.f1266c);
        return this.f1266c.bottom;
    }

    @Override // a.p.e.w
    public int o(View view) {
        this.f1264a.V(view, true, this.f1266c);
        return this.f1266c.top;
    }

    @Override // a.p.e.w
    public void p(int i) {
        this.f1264a.c0(i);
    }
}
