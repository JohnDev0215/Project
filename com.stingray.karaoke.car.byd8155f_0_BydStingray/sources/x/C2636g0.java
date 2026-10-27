package x;

/* JADX INFO: renamed from: x.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2636g0 implements InterfaceC2659z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f23536a;

    public C2636g0(int i7) {
        this.f23536a = i7;
    }

    @Override // x.InterfaceC2615D
    public final /* bridge */ /* synthetic */ H0 e() {
        return a(E0.f23396a);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C2636g0) && ((C2636g0) obj).f23536a == this.f23536a;
    }

    public final int hashCode() {
        return this.f23536a;
    }

    @Override // x.InterfaceC2647m
    public final G0 a(D0 d02) {
        return new L8.p(this.f23536a);
    }
}
