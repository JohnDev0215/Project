package g8;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: g8.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1319i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final EnumC1318h f15009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15010b;

    public C1319i(EnumC1318h enumC1318h) {
        this.f15009a = enumC1318h;
        this.f15010b = false;
    }

    public static C1319i a(C1319i c1319i, EnumC1318h enumC1318h, boolean z6, int i7) {
        if ((i7 & 1) != 0) {
            enumC1318h = c1319i.f15009a;
        }
        if ((i7 & 2) != 0) {
            z6 = c1319i.f15010b;
        }
        c1319i.getClass();
        A7.m.f(enumC1318h, "qualifier");
        return new C1319i(enumC1318h, z6);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1319i)) {
            return false;
        }
        C1319i c1319i = (C1319i) obj;
        return this.f15009a == c1319i.f15009a && this.f15010b == c1319i.f15010b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    public final int hashCode() {
        int iHashCode = this.f15009a.hashCode() * 31;
        boolean z6 = this.f15010b;
        ?? r12 = z6;
        if (z6) {
            r12 = 1;
        }
        return iHashCode + r12;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NullabilityQualifierWithMigrationStatus(qualifier=");
        sb.append(this.f15009a);
        sb.append(", isForWarningOnly=");
        return AbstractC1347d.i(sb, this.f15010b, ')');
    }

    public C1319i(EnumC1318h enumC1318h, boolean z6) {
        this.f15009a = enumC1318h;
        this.f15010b = z6;
    }
}
