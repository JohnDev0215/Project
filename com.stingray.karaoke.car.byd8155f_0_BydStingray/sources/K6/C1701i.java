package k6;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: k6.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1701i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f18268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f18269b;
    public final float c;

    public C1701i(float f10, int i7) {
        f10 = (i7 & 2) != 0 ? 1.25f : f10;
        this.f18268a = 0.75f;
        this.f18269b = f10;
        this.c = 2.5f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1701i)) {
            return false;
        }
        C1701i c1701i = (C1701i) obj;
        return Float.compare(this.f18268a, c1701i.f18268a) == 0 && Float.compare(this.f18269b, c1701i.f18269b) == 0 && Float.compare(this.c, c1701i.c) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.c) + AbstractC1347d.b(Float.hashCode(this.f18268a) * 31, 31, this.f18269b);
    }

    public final String toString() {
        return "DisplayAspectRatioThreshold(narrow=" + this.f18268a + ", wide=" + this.f18269b + ", ultraWide=" + this.c + ")";
    }
}
