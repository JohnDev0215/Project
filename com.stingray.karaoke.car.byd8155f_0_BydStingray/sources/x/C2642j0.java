package x;

/* JADX INFO: renamed from: x.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2642j0 implements InterfaceC2647m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2615D f23547a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f23548b;

    public C2642j0(InterfaceC2615D interfaceC2615D, long j3) {
        this.f23547a = interfaceC2615D;
        this.f23548b = j3;
    }

    @Override // x.InterfaceC2647m
    public final F0 a(D0 d02) {
        return new C2644k0(this.f23547a.a(d02), this.f23548b);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C2642j0)) {
            return false;
        }
        C2642j0 c2642j0 = (C2642j0) obj;
        return c2642j0.f23548b == this.f23548b && A7.m.a(c2642j0.f23547a, this.f23547a);
    }

    public final int hashCode() {
        return Long.hashCode(this.f23548b) + (this.f23547a.hashCode() * 31);
    }
}
