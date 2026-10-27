package n0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: n0.O, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1865O {
    public static final C1865O d = new C1865O(0L, 0.0f, 7);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19402a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f19403b;
    public final float c;

    public C1865O(long j3, long j10, float f10) {
        this.f19402a = j3;
        this.f19403b = j10;
        this.c = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1865O)) {
            return false;
        }
        C1865O c1865o = (C1865O) obj;
        return C1891t.c(this.f19402a, c1865o.f19402a) && m0.c.b(this.f19403b, c1865o.f19403b) && this.c == c1865o.c;
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Float.hashCode(this.c) + AbstractC1347d.c(Long.hashCode(this.f19402a) * 31, 31, this.f19403b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Shadow(color=");
        AbstractC1347d.n(this.f19402a, ", offset=", sb);
        sb.append((Object) m0.c.j(this.f19403b));
        sb.append(", blurRadius=");
        return AbstractC1347d.h(sb, this.c, ')');
    }

    public /* synthetic */ C1865O(long j3, float f10, int i7) {
        this((i7 & 1) != 0 ? AbstractC1861K.d(4278190080L) : j3, 0L, (i7 & 4) != 0 ? 0.0f : f10);
    }
}
