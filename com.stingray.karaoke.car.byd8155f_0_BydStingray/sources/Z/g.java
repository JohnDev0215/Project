package z;

import e1.z;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class g implements z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f24236a;

    public g(long j3) {
        this.f24236a = j3;
    }

    @Override // e1.z
    public final long a(a1.i iVar, long j3, a1.k kVar, long j10) {
        int i7 = iVar.f11035a;
        long j11 = this.f24236a;
        return AbstractC2673a.d(h6.n.d(i7 + ((int) (j11 >> 32)), (int) (j10 >> 32), (int) (j3 >> 32), kVar == a1.k.l), h6.n.d(iVar.f11036b + ((int) (j11 & 4294967295L)), (int) (j10 & 4294967295L), (int) (j3 & 4294967295L), true));
    }
}
