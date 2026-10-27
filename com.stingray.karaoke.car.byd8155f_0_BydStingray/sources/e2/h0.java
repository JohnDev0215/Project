package e2;

import h2.AbstractC1343A;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class h0 {
    public static final String c;
    public static final String d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0 f14118a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d5.I f14119b;

    static {
        int i7 = AbstractC1343A.f15195a;
        c = Integer.toString(0, 36);
        d = Integer.toString(1, 36);
    }

    public h0(g0 g0Var, List list) {
        if (!list.isEmpty() && (((Integer) Collections.min(list)).intValue() < 0 || ((Integer) Collections.max(list)).intValue() >= g0Var.f14104a)) {
            throw new IndexOutOfBoundsException();
        }
        this.f14118a = g0Var;
        this.f14119b = d5.I.w(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h0.class != obj.getClass()) {
            return false;
        }
        h0 h0Var = (h0) obj;
        return this.f14118a.equals(h0Var.f14118a) && this.f14119b.equals(h0Var.f14119b);
    }

    public final int hashCode() {
        return (this.f14119b.hashCode() * 31) + this.f14118a.hashCode();
    }
}
