package x;

import h2.AbstractC1347d;
import w8.C2607b;

/* JADX INFO: renamed from: x.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2640i0 implements InterfaceC2615D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23544a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f23545b;
    public final Object c;

    public C2640i0(float f10, float f11, Object obj) {
        this.f23544a = f10;
        this.f23545b = f11;
        this.c = obj;
    }

    @Override // x.InterfaceC2647m
    public final F0 a(D0 d02) {
        Object obj = this.c;
        return new C2607b(this.f23544a, this.f23545b, obj == null ? null : (AbstractC2652s) d02.f23394a.b(obj));
    }

    @Override // x.InterfaceC2615D
    public final H0 e() {
        D0 d02 = E0.f23396a;
        Object obj = this.c;
        return new C2607b(this.f23544a, this.f23545b, obj == null ? null : (AbstractC2652s) o0.f23591u.b(obj));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C2640i0)) {
            return false;
        }
        C2640i0 c2640i0 = (C2640i0) obj;
        return c2640i0.f23544a == this.f23544a && c2640i0.f23545b == this.f23545b && A7.m.a(c2640i0.c, this.c);
    }

    public final int hashCode() {
        Object obj = this.c;
        return Float.hashCode(this.f23545b) + AbstractC1347d.b((obj != null ? obj.hashCode() : 0) * 31, 31, this.f23544a);
    }

    public /* synthetic */ C2640i0(Object obj, int i7) {
        this(1.0f, 1500.0f, (i7 & 4) != 0 ? null : obj);
    }
}
