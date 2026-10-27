package z0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: z0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2752a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f24254a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f24255b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2752a)) {
            return false;
        }
        C2752a c2752a = (C2752a) obj;
        return this.f24254a == c2752a.f24254a && Float.compare(this.f24255b, c2752a.f24255b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f24255b) + (Long.hashCode(this.f24254a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DataPointAtTime(time=");
        sb.append(this.f24254a);
        sb.append(", dataPoint=");
        return AbstractC1347d.h(sb, this.f24255b, ')');
    }
}
