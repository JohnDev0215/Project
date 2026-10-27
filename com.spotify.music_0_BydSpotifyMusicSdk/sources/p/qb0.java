package p;

/* JADX INFO: loaded from: classes.dex */
public final class qb0 {

    @qb2("s")
    private long a;

    @qb2("r")
    private long b;

    public qb0(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    public final long a() {
        return this.b;
    }

    public final long b() {
        return this.a;
    }

    public final void c(qb0 qb0Var) {
        v03.j(qb0Var, "other");
        this.a += qb0Var.a;
        this.b += qb0Var.b;
    }

    public final void d(long j) {
        this.b = j;
    }

    public final void e(long j) {
        this.a = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof qb0)) {
            return false;
        }
        qb0 qb0Var = (qb0) obj;
        return this.a == qb0Var.a && this.b == qb0Var.b;
    }

    public final int hashCode() {
        return Long.hashCode(this.b) + (Long.hashCode(this.a) * 31);
    }

    public final String toString() {
        return "DroppedCounts(totalCount=" + this.a + ", reportedCount=" + this.b + ')';
    }
}
