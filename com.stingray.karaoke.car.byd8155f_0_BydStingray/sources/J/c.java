package J;

import m0.f;

/* JADX INFO: loaded from: classes.dex */
public final class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3038a;

    public c(float f10) {
        this.f3038a = f10;
        if (f10 < 0.0f || f10 > 100.0f) {
            throw new IllegalArgumentException("The percent should be in the range of [0, 100]");
        }
    }

    @Override // J.a
    public final float a(long j3, a1.b bVar) {
        return (this.f3038a / 100.0f) * f.c(j3);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c) && Float.compare(this.f3038a, ((c) obj).f3038a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3038a);
    }

    public final String toString() {
        return "CornerSize(size = " + this.f3038a + "%)";
    }
}
