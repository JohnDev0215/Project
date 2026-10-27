package n2;

import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class b0 {
    public static final b0 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19625a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f19626b;

    static {
        b0 b0Var = new b0(0L, 0L);
        new b0(Long.MAX_VALUE, Long.MAX_VALUE);
        new b0(Long.MAX_VALUE, 0L);
        new b0(0L, Long.MAX_VALUE);
        c = b0Var;
    }

    public b0(long j3, long j10) {
        AbstractC1346c.c(j3 >= 0);
        AbstractC1346c.c(j10 >= 0);
        this.f19625a = j3;
        this.f19626b = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b0.class != obj.getClass()) {
            return false;
        }
        b0 b0Var = (b0) obj;
        return this.f19625a == b0Var.f19625a && this.f19626b == b0Var.f19626b;
    }

    public final int hashCode() {
        return (((int) this.f19625a) * 31) + ((int) this.f19626b);
    }
}
