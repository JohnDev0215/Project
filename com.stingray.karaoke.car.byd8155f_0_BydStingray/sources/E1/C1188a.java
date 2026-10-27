package e1;

import H7.H;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: e1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1188a implements z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0.c f13762a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f13763b;

    public C1188a(g0.c cVar, long j3) {
        this.f13762a = cVar;
        this.f13763b = j3;
    }

    @Override // e1.z
    public final long a(a1.i iVar, long j3, a1.k kVar, long j10) {
        int i7 = iVar.c;
        int i10 = iVar.f11035a;
        int i11 = iVar.d;
        int i12 = iVar.f11036b;
        long jA = this.f13762a.a(0L, H.c(i7 - i10, i11 - i12), kVar);
        long jA2 = this.f13762a.a(0L, j10, kVar);
        long j11 = (((long) (-((int) (jA2 & 4294967295L)))) & 4294967295L) | (((long) (-((int) (jA2 >> 32)))) << 32);
        long j12 = this.f13763b;
        return a1.h.d(a1.h.d(a1.h.d(AbstractC2673a.d(i10, i12), jA), j11), AbstractC2673a.d(((int) (j12 >> 32)) * (kVar == a1.k.l ? 1 : -1), (int) (j12 & 4294967295L)));
    }
}
