package m0;

import h2.AbstractC1347d;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d f18881e = new d(0.0f, 0.0f, 0.0f, 0.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f18882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f18883b;
    public final float c;
    public final float d;

    public d(float f10, float f11, float f12, float f13) {
        this.f18882a = f10;
        this.f18883b = f11;
        this.c = f12;
        this.d = f13;
    }

    public final long a() {
        return h6.e.a((c() / 2.0f) + this.f18882a, (b() / 2.0f) + this.f18883b);
    }

    public final float b() {
        return this.d - this.f18883b;
    }

    public final float c() {
        return this.c - this.f18882a;
    }

    public final d d(d dVar) {
        return new d(Math.max(this.f18882a, dVar.f18882a), Math.max(this.f18883b, dVar.f18883b), Math.min(this.c, dVar.c), Math.min(this.d, dVar.d));
    }

    public final boolean e() {
        return this.f18882a >= this.c || this.f18883b >= this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Float.compare(this.f18882a, dVar.f18882a) == 0 && Float.compare(this.f18883b, dVar.f18883b) == 0 && Float.compare(this.c, dVar.c) == 0 && Float.compare(this.d, dVar.d) == 0;
    }

    public final boolean f(d dVar) {
        return this.c > dVar.f18882a && dVar.c > this.f18882a && this.d > dVar.f18883b && dVar.d > this.f18883b;
    }

    public final d g(float f10, float f11) {
        return new d(this.f18882a + f10, this.f18883b + f11, this.c + f10, this.d + f11);
    }

    public final d h(long j3) {
        return new d(c.d(j3) + this.f18882a, c.e(j3) + this.f18883b, c.d(j3) + this.c, c.e(j3) + this.d);
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f18882a) * 31, 31, this.f18883b), 31, this.c);
    }

    public final String toString() {
        return "Rect.fromLTRB(" + AbstractC1985B.v(this.f18882a) + ", " + AbstractC1985B.v(this.f18883b) + ", " + AbstractC1985B.v(this.c) + ", " + AbstractC1985B.v(this.d) + ')';
    }
}
