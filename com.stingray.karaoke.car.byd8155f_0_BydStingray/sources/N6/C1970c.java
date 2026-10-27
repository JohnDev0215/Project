package n6;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: n6.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1970c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f20055a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f20056b;
    public final int c;

    public C1970c(int i7, long j3, long j10) {
        this.f20055a = j3;
        this.f20056b = j10;
        this.c = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1970c)) {
            return false;
        }
        C1970c c1970c = (C1970c) obj;
        return this.f20055a == c1970c.f20055a && this.f20056b == c1970c.f20056b && this.c == c1970c.c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.c) + AbstractC1347d.c(Long.hashCode(this.f20055a) * 31, 31, this.f20056b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Encouragement(startTick=");
        sb.append(this.f20055a);
        sb.append(", endTick=");
        sb.append(this.f20056b);
        sb.append(", fromMultiplier=");
        return M5.b.h(sb, this.c, ")");
    }
}
