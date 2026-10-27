package D2;

import B2.E;
import B2.G;
import B2.L;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f1156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1157b;
    public final int c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1158e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1159f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1160g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1161h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1162i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1163j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f1164k;
    public long[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f1165m;

    public e(int i7, int i10, long j3, int i11, L l) {
        boolean z6 = true;
        if (i10 != 1 && i10 != 2) {
            z6 = false;
        }
        AbstractC1346c.c(z6);
        this.d = j3;
        this.f1158e = i11;
        this.f1156a = l;
        int i12 = (((i7 % 10) + 48) << 8) | ((i7 / 10) + 48);
        this.f1157b = (i10 == 2 ? 1667497984 : 1651965952) | i12;
        this.c = i10 == 2 ? i12 | 1650720768 : -1;
        this.f1164k = -1L;
        this.l = new long[512];
        this.f1165m = new int[512];
    }

    public final G a(int i7) {
        return new G(((this.d * ((long) 1)) / ((long) this.f1158e)) * ((long) this.f1165m[i7]), this.l[i7]);
    }

    public final E b(long j3) {
        if (this.f1163j == 0) {
            G g7 = new G(0L, this.f1164k);
            return new E(g7, g7);
        }
        int i7 = (int) (j3 / ((this.d * ((long) 1)) / ((long) this.f1158e)));
        int iD = AbstractC1343A.d(this.f1165m, i7, true, true);
        if (this.f1165m[iD] == i7) {
            G gA = a(iD);
            return new E(gA, gA);
        }
        G gA2 = a(iD);
        int i10 = iD + 1;
        return i10 < this.l.length ? new E(gA2, a(i10)) : new E(gA2, gA2);
    }
}
