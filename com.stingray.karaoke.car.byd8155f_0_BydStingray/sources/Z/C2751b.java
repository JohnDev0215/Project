package z;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: renamed from: z.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2751b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f24226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f24227b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f24228e;

    public C2751b(long j3, long j10, long j11, long j12, long j13) {
        this.f24226a = j3;
        this.f24227b = j10;
        this.c = j11;
        this.d = j12;
        this.f24228e = j13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C2751b)) {
            return false;
        }
        C2751b c2751b = (C2751b) obj;
        return C1891t.c(this.f24226a, c2751b.f24226a) && C1891t.c(this.f24227b, c2751b.f24227b) && C1891t.c(this.c, c2751b.c) && C1891t.c(this.d, c2751b.d) && C1891t.c(this.f24228e, c2751b.f24228e);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.f24228e) + AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(Long.hashCode(this.f24226a) * 31, 31, this.f24227b), 31, this.c), 31, this.d);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ContextMenuColors(backgroundColor=");
        AbstractC1347d.n(this.f24226a, ", textColor=", sb);
        AbstractC1347d.n(this.f24227b, ", iconColor=", sb);
        AbstractC1347d.n(this.c, ", disabledTextColor=", sb);
        AbstractC1347d.n(this.d, ", disabledIconColor=", sb);
        sb.append((Object) C1891t.i(this.f24228e));
        sb.append(')');
        return sb.toString();
    }
}
