package g0;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f14677a;

    public f(float f10) {
        this.f14677a = f10;
    }

    public final int a(int i7, int i10) {
        return Math.round((1 + this.f14677a) * ((i10 - i7) / 2.0f));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof f) && Float.compare(this.f14677a, ((f) obj).f14677a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f14677a);
    }

    public final String toString() {
        return AbstractC1347d.h(new StringBuilder("Vertical(bias="), this.f14677a, ')');
    }
}
