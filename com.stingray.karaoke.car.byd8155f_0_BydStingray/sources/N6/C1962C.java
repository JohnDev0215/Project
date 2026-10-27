package n6;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: renamed from: n6.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1962C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f19985a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f19986b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f19987e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f19988f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f19989g;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1962C)) {
            return false;
        }
        C1962C c1962c = (C1962C) obj;
        return Float.compare(this.f19985a, c1962c.f19985a) == 0 && Float.compare(this.f19986b, c1962c.f19986b) == 0 && Float.compare(this.c, c1962c.c) == 0 && Float.compare(this.d, c1962c.d) == 0 && C1891t.c(this.f19987e, c1962c.f19987e) && Float.compare(this.f19988f, c1962c.f19988f) == 0 && Float.compare(this.f19989g, c1962c.f19989g) == 0 && Float.compare(100.0f, 100.0f) == 0;
    }

    public final int hashCode() {
        int iB = AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f19985a) * 31, 31, this.f19986b), 31, this.c), 31, this.d);
        int i7 = C1891t.l;
        return Float.hashCode(100.0f) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.c(iB, 31, this.f19987e), 31, this.f19988f), 31, this.f19989g);
    }

    public final String toString() {
        return "Particle(x=" + this.f19985a + ", y=" + this.f19986b + ", velocityX=" + this.c + ", velocityY=" + this.d + ", color=" + C1891t.i(this.f19987e) + ", size=" + this.f19988f + ", lifespan=" + this.f19989g + ", initialLifespan=100.0)";
    }
}
