package Z7;

import g8.C1319i;
import g8.EnumC1318h;
import h2.AbstractC1347d;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1319i f10947a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Collection f10948b;
    public final boolean c;

    public n(C1319i c1319i, Collection collection, boolean z6) {
        A7.m.f(collection, "qualifierApplicabilityTypes");
        this.f10947a = c1319i;
        this.f10948b = collection;
        this.c = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return A7.m.a(this.f10947a, nVar.f10947a) && A7.m.a(this.f10948b, nVar.f10948b) && this.c == nVar.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    public final int hashCode() {
        int iHashCode = (this.f10948b.hashCode() + (this.f10947a.hashCode() * 31)) * 31;
        boolean z6 = this.c;
        ?? r22 = z6;
        if (z6) {
            r22 = 1;
        }
        return iHashCode + r22;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("JavaDefaultQualifiers(nullabilityQualifier=");
        sb.append(this.f10947a);
        sb.append(", qualifierApplicabilityTypes=");
        sb.append(this.f10948b);
        sb.append(", definitelyNotNull=");
        return AbstractC1347d.i(sb, this.c, ')');
    }

    public n(C1319i c1319i, Collection collection) {
        this(c1319i, collection, c1319i.f15009a == EnumC1318h.f15008n);
    }
}
