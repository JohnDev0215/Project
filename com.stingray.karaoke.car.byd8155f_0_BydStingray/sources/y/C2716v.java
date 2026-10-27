package y;

import n0.C1868S;

/* JADX INFO: renamed from: y.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2716v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f24013a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1868S f24014b;

    public C2716v(float f10, C1868S c1868s) {
        this.f24013a = f10;
        this.f24014b = c1868s;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2716v)) {
            return false;
        }
        C2716v c2716v = (C2716v) obj;
        return a1.e.a(this.f24013a, c2716v.f24013a) && this.f24014b.equals(c2716v.f24014b);
    }

    public final int hashCode() {
        return this.f24014b.hashCode() + (Float.hashCode(this.f24013a) * 31);
    }

    public final String toString() {
        return "BorderStroke(width=" + ((Object) a1.e.b(this.f24013a)) + ", brush=" + this.f24014b + ')';
    }
}
