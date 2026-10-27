package e2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class m0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f14217f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f14218g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f14219h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14220i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14221a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g0 f14222b;
    public final boolean c;
    public final int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean[] f14223e;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14217f = Integer.toString(0, 36);
        f14218g = Integer.toString(1, 36);
        f14219h = Integer.toString(3, 36);
        f14220i = Integer.toString(4, 36);
    }

    public m0(g0 g0Var, boolean z6, int[] iArr, boolean[] zArr) {
        int i7 = g0Var.f14104a;
        this.f14221a = i7;
        boolean z10 = false;
        AbstractC1346c.c(i7 == iArr.length && i7 == zArr.length);
        this.f14222b = g0Var;
        if (z6 && i7 > 1) {
            z10 = true;
        }
        this.c = z10;
        this.d = (int[]) iArr.clone();
        this.f14223e = (boolean[]) zArr.clone();
    }

    public final int a() {
        return this.f14222b.c;
    }

    public final boolean b(int i7) {
        return this.d[i7] == 4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m0.class != obj.getClass()) {
            return false;
        }
        m0 m0Var = (m0) obj;
        return this.c == m0Var.c && this.f14222b.equals(m0Var.f14222b) && Arrays.equals(this.d, m0Var.d) && Arrays.equals(this.f14223e, m0Var.f14223e);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f14223e) + ((Arrays.hashCode(this.d) + (((this.f14222b.hashCode() * 31) + (this.c ? 1 : 0)) * 31)) * 31);
    }
}
