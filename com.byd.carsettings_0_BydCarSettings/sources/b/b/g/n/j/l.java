package b.b.g.n.j;

import b.b.g.o.c;
import b.b.g.q.f.b.q;

/* JADX INFO: compiled from: HarmanSoundFieldPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class l extends b.b.m.e.g<q> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1095a = c.d.a();

    public int[] c() {
        int[] iArrQ = this.f1095a.q();
        iArrQ[0] = iArrQ[0] + 10;
        iArrQ[1] = iArrQ[1] + 10;
        return iArrQ;
    }

    public int[] d() {
        this.f1095a.F();
        return c();
    }

    public int e(int i, int i2) {
        return this.f1095a.V(i - 10, i2 - 10);
    }
}
