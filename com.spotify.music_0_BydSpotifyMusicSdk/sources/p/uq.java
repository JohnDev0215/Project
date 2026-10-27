package p;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class uq extends xn2 {
    public boolean a = false;
    public final /* synthetic */ ViewGroup b;

    public uq(ViewGroup viewGroup) {
        this.b = viewGroup;
    }

    @Override // p.vn2
    public final void a(wn2 wn2Var) {
        if (!this.a) {
            ci1.D(this.b, false);
        }
        wn2Var.z(this);
    }

    @Override // p.xn2, p.vn2
    public final void c() {
        ci1.D(this.b, false);
    }

    @Override // p.xn2, p.vn2
    public final void d() {
        ci1.D(this.b, true);
    }

    @Override // p.xn2, p.vn2
    public final void e() {
        ci1.D(this.b, false);
        this.a = true;
    }
}
