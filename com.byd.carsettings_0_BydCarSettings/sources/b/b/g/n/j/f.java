package b.b.g.n.j;

import b.b.g.o.c;

/* JADX INFO: compiled from: DiracSoundFieldPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends b.b.m.e.g<b.b.g.q.f.b.j> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1082a = c.d.a();

    public int[] c() {
        int[] iArrG = this.f1082a.g();
        iArrG[0] = iArrG[0] + 7;
        iArrG[1] = iArrG[1] + 7;
        return iArrG;
    }

    public int[] d() {
        this.f1082a.z();
        return c();
    }

    public int e(int i, int i2) {
        return this.f1082a.L(i - 7, i2 - 7);
    }
}
