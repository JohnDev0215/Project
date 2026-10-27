package p;

/* JADX INFO: loaded from: classes.dex */
public final class nj0 {
    public long a;
    public long b;

    public /* synthetic */ nj0() {
        this(0L, 0L);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof nj0)) {
            return false;
        }
        nj0 nj0Var = (nj0) obj;
        return this.a == nj0Var.a && this.b == nj0Var.b;
    }

    public final int hashCode() {
        return Long.hashCode(this.b) + (Long.hashCode(this.a) * 31);
    }

    public final String toString() {
        return "InternalErrorCounts(totalCount=" + this.a + ", reportedCount=" + this.b + ')';
    }

    public nj0(long j, long j2) {
        this.a = j;
        this.b = j2;
    }
}
