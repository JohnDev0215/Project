package w;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23072a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f23073b;
    public final long c;

    public I(float f10, float f11, long j3) {
        this.f23072a = f10;
        this.f23073b = f11;
        this.c = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof I)) {
            return false;
        }
        I i7 = (I) obj;
        return Float.compare(this.f23072a, i7.f23072a) == 0 && Float.compare(this.f23073b, i7.f23073b) == 0 && this.c == i7.c;
    }

    public final int hashCode() {
        return Long.hashCode(this.c) + AbstractC1347d.b(Float.hashCode(this.f23072a) * 31, 31, this.f23073b);
    }

    public final String toString() {
        return "FlingInfo(initialVelocity=" + this.f23072a + ", distance=" + this.f23073b + ", duration=" + this.c + ')';
    }
}
