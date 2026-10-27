package w2;

import d5.AbstractC1102y;
import e2.C1208p;
import n2.AbstractC1905e;

/* JADX INFO: renamed from: w2.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2593g implements Comparable {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f23199m;

    public C2593g(C1208p c1208p, int i7) {
        this.l = (c1208p.f14308e & 1) != 0;
        this.f23199m = AbstractC1905e.o(i7, false);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        C2593g c2593g = (C2593g) obj;
        return AbstractC1102y.f13464a.c(this.f23199m, c2593g.f23199m).c(this.l, c2593g.l).e();
    }
}
