package S0;

import x3.AbstractC2673a;

/* JADX INFO: renamed from: S0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0691c implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7824a;

    public C0691c(int i7) {
        this.f7824a = i7;
    }

    @Override // S0.F
    public final A a(A a10) {
        int i7 = this.f7824a;
        return (i7 == 0 || i7 == Integer.MAX_VALUE) ? a10 : new A(AbstractC2673a.v(a10.l + i7, 1, 1000));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C0691c) && this.f7824a == ((C0691c) obj).f7824a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f7824a);
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("AndroidFontResolveInterceptor(fontWeightAdjustment="), this.f7824a, ')');
    }
}
