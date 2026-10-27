package g0;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f14676a;

    public e(float f10) {
        this.f14676a = f10;
    }

    public final int a(int i7, int i10, a1.k kVar) {
        float f10 = (i10 - i7) / 2.0f;
        a1.k kVar2 = a1.k.l;
        float f11 = this.f14676a;
        if (kVar != kVar2) {
            f11 *= -1;
        }
        return Math.round((1 + f11) * f10);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof e) && Float.compare(this.f14676a, ((e) obj).f14676a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f14676a);
    }

    public final String toString() {
        return AbstractC1347d.h(new StringBuilder("Horizontal(bias="), this.f14676a, ')');
    }
}
