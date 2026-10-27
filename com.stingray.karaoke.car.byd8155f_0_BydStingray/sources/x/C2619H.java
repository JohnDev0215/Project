package x;

/* JADX INFO: renamed from: x.H, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2619H implements InterfaceC2647m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2659z f23408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23409b;
    public final long c;

    public C2619H(InterfaceC2659z interfaceC2659z, int i7, long j3) {
        this.f23408a = interfaceC2659z;
        this.f23409b = i7;
        this.c = j3;
    }

    @Override // x.InterfaceC2647m
    public final F0 a(D0 d02) {
        return new I0(this.f23408a.a(d02), this.f23409b, this.c);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C2619H)) {
            return false;
        }
        C2619H c2619h = (C2619H) obj;
        if (c2619h.f23408a.equals(this.f23408a) && c2619h.f23409b == this.f23409b) {
            return c2619h.c == this.c;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.c) + ((AbstractC2641j.c(this.f23409b) + (this.f23408a.hashCode() * 31)) * 31);
    }
}
