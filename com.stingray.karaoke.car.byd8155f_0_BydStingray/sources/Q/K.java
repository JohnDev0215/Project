package Q;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5568a = C1891t.f19452k;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final P.h f5569b = null;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof K)) {
            return false;
        }
        K k10 = (K) obj;
        return C1891t.c(this.f5568a, k10.f5568a) && A7.m.a(this.f5569b, k10.f5569b);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        int iHashCode = Long.hashCode(this.f5568a) * 31;
        P.h hVar = this.f5569b;
        return iHashCode + (hVar != null ? hVar.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RippleConfiguration(color=");
        AbstractC1347d.n(this.f5568a, ", rippleAlpha=", sb);
        sb.append(this.f5569b);
        sb.append(')');
        return sb.toString();
    }
}
