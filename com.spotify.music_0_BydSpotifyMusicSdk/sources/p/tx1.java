package p;

/* JADX INFO: loaded from: classes.dex */
public final class tx1 {
    public static final u11 g = new u11();
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;

    public /* synthetic */ tx1() {
        this(false, false, false, false, false, false);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof tx1)) {
            return false;
        }
        tx1 tx1Var = (tx1) obj;
        return this.a == tx1Var.a && this.b == tx1Var.b && this.c == tx1Var.c && this.d == tx1Var.d && this.e == tx1Var.e && this.f == tx1Var.f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9 */
    public final int hashCode() {
        boolean z = this.a;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = r1 * 31;
        boolean z2 = this.b;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (i + r2) * 31;
        boolean z3 = this.c;
        ?? r22 = z3;
        if (z3) {
            r22 = 1;
        }
        int i3 = (i2 + r22) * 31;
        boolean z4 = this.d;
        ?? r23 = z4;
        if (z4) {
            r23 = 1;
        }
        int i4 = (i3 + r23) * 31;
        boolean z5 = this.e;
        ?? r24 = z5;
        if (z5) {
            r24 = 1;
        }
        int i5 = (i4 + r24) * 31;
        boolean z6 = this.f;
        return i5 + (z6 ? 1 : z6);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ProductState(hasPodcasts=");
        sb.append(this.a);
        sb.append(", isDeprecatedClient=");
        sb.append(this.b);
        sb.append(", isOnDemand=");
        sb.append(this.c);
        sb.append(", isPremium=");
        sb.append(this.d);
        sb.append(", autoplay=");
        sb.append(this.e);
        sb.append(", isPickAndShuffleEnabled=");
        return u4.n(sb, this.f, ')');
    }

    public tx1(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
        this.e = z5;
        this.f = z6;
    }
}
