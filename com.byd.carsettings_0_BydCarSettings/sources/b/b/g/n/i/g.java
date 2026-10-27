package b.b.g.n.i;

import b.b.g.n.q.m;

/* JADX INFO: compiled from: SocSettingPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends b.b.m.e.g<b.b.g.q.f.a.h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public m f1041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.n.q.e f1042b;

    public int c() {
        return this.f1042b.o() == 2 ? 1 : 0;
    }

    public int d() {
        return this.f1041a.h1();
    }

    public int[] e() {
        return this.f1041a.i1();
    }

    public void f() {
        this.f1041a = m.p0();
        this.f1042b = b.b.g.n.q.e.j();
    }

    public void g(int i) {
        this.f1042b.J(i == 0 ? 1 : 2);
    }

    public void h(int i, int i2) {
        this.f1041a.p3(i, i2);
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        this.f1041a = null;
        this.f1042b = null;
    }
}
