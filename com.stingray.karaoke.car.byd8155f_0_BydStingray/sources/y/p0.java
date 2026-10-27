package y;

import h2.AbstractC1347d;
import n0.AbstractC1861K;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f23985a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D.L f23986b;

    public p0() {
        long jD = AbstractC1861K.d(4284900966L);
        D.L lA = androidx.compose.foundation.layout.a.a(0.0f, 3);
        this.f23985a = jD;
        this.f23986b = lA;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!p0.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.foundation.OverscrollConfiguration");
        p0 p0Var = (p0) obj;
        return C1891t.c(this.f23985a, p0Var.f23985a) && A7.m.a(this.f23986b, p0Var.f23986b);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return this.f23986b.hashCode() + (Long.hashCode(this.f23985a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("OverscrollConfiguration(glowColor=");
        AbstractC1347d.n(this.f23985a, ", drawPadding=", sb);
        sb.append(this.f23986b);
        sb.append(')');
        return sb.toString();
    }
}
