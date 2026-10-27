package e2;

import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class q0 {
    public static final q0 d = new q0(0, 0, 1.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f14329e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f14330f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f14331g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14332a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14333b;
    public final float c;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14329e = Integer.toString(0, 36);
        f14330f = Integer.toString(1, 36);
        f14331g = Integer.toString(3, 36);
    }

    public q0(int i7, int i10, float f10) {
        this.f14332a = i7;
        this.f14333b = i10;
        this.c = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q0)) {
            return false;
        }
        q0 q0Var = (q0) obj;
        return this.f14332a == q0Var.f14332a && this.f14333b == q0Var.f14333b && this.c == q0Var.c;
    }

    public final int hashCode() {
        return Float.floatToRawIntBits(this.c) + ((((217 + this.f14332a) * 31) + this.f14333b) * 31);
    }
}
