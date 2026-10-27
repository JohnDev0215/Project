package a.p.e;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class u extends w {
    public u(RecyclerView.m mVar) {
        super(mVar, null);
    }

    @Override // a.p.e.w
    public int b(View view) {
        return this.f1264a.G(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.n) view.getLayoutParams())).rightMargin;
    }

    @Override // a.p.e.w
    public int c(View view) {
        RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
        return this.f1264a.F(view) + ((ViewGroup.MarginLayoutParams) nVar).leftMargin + ((ViewGroup.MarginLayoutParams) nVar).rightMargin;
    }

    @Override // a.p.e.w
    public int d(View view) {
        RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
        return this.f1264a.E(view) + ((ViewGroup.MarginLayoutParams) nVar).topMargin + ((ViewGroup.MarginLayoutParams) nVar).bottomMargin;
    }

    @Override // a.p.e.w
    public int e(View view) {
        return this.f1264a.D(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.n) view.getLayoutParams())).leftMargin;
    }

    @Override // a.p.e.w
    public int f() {
        return this.f1264a.q;
    }

    @Override // a.p.e.w
    public int g() {
        RecyclerView.m mVar = this.f1264a;
        return mVar.q - mVar.P();
    }

    @Override // a.p.e.w
    public int h() {
        return this.f1264a.P();
    }

    @Override // a.p.e.w
    public int i() {
        return this.f1264a.o;
    }

    @Override // a.p.e.w
    public int j() {
        return this.f1264a.p;
    }

    @Override // a.p.e.w
    public int k() {
        return this.f1264a.O();
    }

    @Override // a.p.e.w
    public int l() {
        RecyclerView.m mVar = this.f1264a;
        return (mVar.q - mVar.O()) - this.f1264a.P();
    }

    @Override // a.p.e.w
    public int n(View view) {
        this.f1264a.V(view, true, this.f1266c);
        return this.f1266c.right;
    }

    @Override // a.p.e.w
    public int o(View view) {
        this.f1264a.V(view, true, this.f1266c);
        return this.f1266c.left;
    }

    @Override // a.p.e.w
    public void p(int i) {
        this.f1264a.b0(i);
    }
}
