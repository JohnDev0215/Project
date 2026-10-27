package z2;

import E5.z;
import e2.q0;
import w8.C2607b;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2607b f24368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f24369b;
    public final s9.d c = new s9.d();
    public final A2.g d = new A2.g(6, (byte) 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final A2.g f24370e = new A2.g(6, (byte) 0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final z f24371f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public q0 f24372g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public q0 f24373h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f24374i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f24375j;

    public s(C2607b c2607b, o oVar) {
        this.f24368a = c2607b;
        this.f24369b = oVar;
        z zVar = new z(7, (byte) 0);
        int iHighestOneBit = Integer.bitCount(16) != 1 ? Integer.highestOneBit(15) << 1 : 16;
        zVar.f1841b = 0;
        zVar.c = 0;
        zVar.f1842e = new long[iHighestOneBit];
        zVar.d = iHighestOneBit - 1;
        this.f24371f = zVar;
        this.f24373h = q0.d;
        this.f24375j = -9223372036854775807L;
    }
}
