package e2;

import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class j0 {
    public static final j0 d = new j0(new i0());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f14129e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f14130f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f14131g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14132a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f14133b;
    public final boolean c;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14129e = Integer.toString(1, 36);
        f14130f = Integer.toString(2, 36);
        f14131g = Integer.toString(3, 36);
    }

    public j0(i0 i0Var) {
        this.f14132a = i0Var.f14120a;
        this.f14133b = i0Var.f14121b;
        this.c = i0Var.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || j0.class != obj.getClass()) {
            return false;
        }
        j0 j0Var = (j0) obj;
        return this.f14132a == j0Var.f14132a && this.f14133b == j0Var.f14133b && this.c == j0Var.c;
    }

    public final int hashCode() {
        return ((((this.f14132a + 31) * 31) + (this.f14133b ? 1 : 0)) * 31) + (this.c ? 1 : 0);
    }
}
