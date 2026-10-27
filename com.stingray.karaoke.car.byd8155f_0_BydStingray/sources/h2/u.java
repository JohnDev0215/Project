package h2;

/* JADX INFO: loaded from: classes.dex */
public final class u {
    public static final u c = new u(-1, -1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f15243a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f15244b;

    static {
        new u(0, 0);
    }

    public u(int i7, int i10) {
        AbstractC1346c.c((i7 == -1 || i7 >= 0) && (i10 == -1 || i10 >= 0));
        this.f15243a = i7;
        this.f15244b = i10;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return this.f15243a == uVar.f15243a && this.f15244b == uVar.f15244b;
    }

    public final int hashCode() {
        int i7 = this.f15243a;
        int i10 = i7 << 16;
        return this.f15244b ^ ((i7 >>> 16) | i10);
    }

    public final String toString() {
        return this.f15243a + "x" + this.f15244b;
    }
}
