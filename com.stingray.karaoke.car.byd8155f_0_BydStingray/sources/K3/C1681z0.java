package k3;

import h2.AbstractC1343A;
import java.util.List;

/* JADX INFO: renamed from: k3.z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1681z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d5.I f18170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18171b;
    public final long c;

    public C1681z0(List list, int i7, long j3) {
        this.f18170a = d5.I.w(list);
        this.f18171b = i7;
        this.c = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1681z0)) {
            return false;
        }
        C1681z0 c1681z0 = (C1681z0) obj;
        if (this.f18170a.equals(c1681z0.f18170a)) {
            Integer numValueOf = Integer.valueOf(this.f18171b);
            Integer numValueOf2 = Integer.valueOf(c1681z0.f18171b);
            int i7 = AbstractC1343A.f15195a;
            if (numValueOf.equals(numValueOf2) && Long.valueOf(this.c).equals(Long.valueOf(c1681z0.c))) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return C7.a.P(this.c) + (((this.f18170a.hashCode() * 31) + this.f18171b) * 31);
    }
}
