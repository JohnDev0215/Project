package e2;

import d5.C1078G;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class n0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n0 f14225b;
    public static final String c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d5.I f14226a;

    static {
        C1078G c1078g = d5.I.f13384m;
        f14225b = new n0(d5.d0.f13415p);
        int i7 = AbstractC1343A.f15195a;
        c = Integer.toString(0, 36);
    }

    public n0(d5.I i7) {
        this.f14226a = d5.I.w(i7);
    }

    public final d5.I a() {
        return this.f14226a;
    }

    public final boolean b(int i7) {
        int i10 = 0;
        while (true) {
            d5.I i11 = this.f14226a;
            if (i10 >= i11.size()) {
                return false;
            }
            m0 m0Var = (m0) i11.get(i10);
            boolean[] zArr = m0Var.f14223e;
            int length = zArr.length;
            boolean z6 = false;
            int i12 = 0;
            while (true) {
                if (i12 >= length) {
                    break;
                }
                if (zArr[i12]) {
                    z6 = true;
                    break;
                }
                i12++;
            }
            if (z6 && m0Var.a() == i7) {
                return true;
            }
            i10++;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n0.class != obj.getClass()) {
            return false;
        }
        return this.f14226a.equals(((n0) obj).f14226a);
    }

    public final int hashCode() {
        return this.f14226a.hashCode();
    }
}
