package x;

/* JADX INFO: renamed from: x.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2644k0 implements F0 {
    public final F0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f23551m;

    public C2644k0(F0 f02, long j3) {
        this.l = f02;
        this.f23551m = j3;
    }

    @Override // x.F0
    public final boolean a() {
        return this.l.a();
    }

    @Override // x.F0
    public final long b(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return this.l.b(abstractC2652s, abstractC2652s2, abstractC2652s3) + this.f23551m;
    }

    @Override // x.F0
    public final AbstractC2652s c(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        long j10 = this.f23551m;
        return j3 < j10 ? abstractC2652s3 : this.l.c(j3 - j10, abstractC2652s, abstractC2652s2, abstractC2652s3);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C2644k0)) {
            return false;
        }
        C2644k0 c2644k0 = (C2644k0) obj;
        return c2644k0.f23551m == this.f23551m && A7.m.a(c2644k0.l, this.l);
    }

    public final int hashCode() {
        return Long.hashCode(this.f23551m) + (this.l.hashCode() * 31);
    }

    @Override // x.F0
    public final AbstractC2652s o(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        long j10 = this.f23551m;
        return j3 < j10 ? abstractC2652s : this.l.o(j3 - j10, abstractC2652s, abstractC2652s2, abstractC2652s3);
    }
}
