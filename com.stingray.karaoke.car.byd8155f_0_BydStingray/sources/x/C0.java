package x;

/* JADX INFO: loaded from: classes.dex */
public final class C0 implements InterfaceC2659z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f23392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23393b;
    public final InterfaceC2612A c;

    public C0(int i7, InterfaceC2612A interfaceC2612A, int i10) {
        this(i7, 0, (i10 & 4) != 0 ? AbstractC2614C.f23390a : interfaceC2612A);
    }

    @Override // x.InterfaceC2647m
    public final F0 a(D0 d02) {
        return new K.L0(this.f23392a, this.f23393b, this.c);
    }

    @Override // x.InterfaceC2615D
    public final H0 e() {
        D0 d02 = E0.f23396a;
        return new K.L0(this.f23392a, this.f23393b, this.c);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0)) {
            return false;
        }
        C0 c02 = (C0) obj;
        return c02.f23392a == this.f23392a && c02.f23393b == this.f23393b && A7.m.a(c02.c, this.c);
    }

    public final int hashCode() {
        return ((this.c.hashCode() + (this.f23392a * 31)) * 31) + this.f23393b;
    }

    @Override // x.InterfaceC2659z, x.InterfaceC2647m
    public final G0 a(D0 d02) {
        return new K.L0(this.f23392a, this.f23393b, this.c);
    }

    public C0(int i7, int i10, InterfaceC2612A interfaceC2612A) {
        this.f23392a = i7;
        this.f23393b = i10;
        this.c = interfaceC2612A;
    }
}
