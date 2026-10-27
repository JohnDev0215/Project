package G7;

/* JADX INFO: loaded from: classes.dex */
public final class g extends e {
    public static final g o = new g(1, 0);

    public g(long j3, long j10) {
        super(j3, j10, 1L);
    }

    @Override // G7.e
    public final boolean equals(Object obj) {
        if (obj instanceof g) {
            if (!isEmpty() || !((g) obj).isEmpty()) {
                g gVar = (g) obj;
                if (this.l == gVar.l) {
                    if (this.f2716m == gVar.f2716m) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // G7.e
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j3 = this.l;
        long j10 = ((long) 31) * (j3 ^ (j3 >>> 32));
        long j11 = this.f2716m;
        return (int) (j10 + (j11 ^ (j11 >>> 32)));
    }

    @Override // G7.e
    public final boolean isEmpty() {
        return this.l > this.f2716m;
    }

    @Override // G7.e
    public final String toString() {
        return this.l + ".." + this.f2716m;
    }
}
