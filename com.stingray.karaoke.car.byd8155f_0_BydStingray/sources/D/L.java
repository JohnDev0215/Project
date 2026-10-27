package D;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1039b;
    public final float c;
    public final float d;

    public L(float f10, float f11, float f12, float f13) {
        this.f1038a = f10;
        this.f1039b = f11;
        this.c = f12;
        this.d = f13;
        if (f10 < 0.0f) {
            throw new IllegalArgumentException("Start padding must be non-negative");
        }
        if (f11 < 0.0f) {
            throw new IllegalArgumentException("Top padding must be non-negative");
        }
        if (f12 < 0.0f) {
            throw new IllegalArgumentException("End padding must be non-negative");
        }
        if (f13 < 0.0f) {
            throw new IllegalArgumentException("Bottom padding must be non-negative");
        }
    }

    public final float a() {
        return this.d;
    }

    public final float b(a1.k kVar) {
        return kVar == a1.k.l ? this.f1038a : this.c;
    }

    public final float c(a1.k kVar) {
        return kVar == a1.k.l ? this.c : this.f1038a;
    }

    public final float d() {
        return this.f1039b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof L)) {
            return false;
        }
        L l = (L) obj;
        return a1.e.a(this.f1038a, l.f1038a) && a1.e.a(this.f1039b, l.f1039b) && a1.e.a(this.c, l.c) && a1.e.a(this.d, l.d);
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f1038a) * 31, 31, this.f1039b), 31, this.c);
    }

    public final String toString() {
        return "PaddingValues(start=" + ((Object) a1.e.b(this.f1038a)) + ", top=" + ((Object) a1.e.b(this.f1039b)) + ", end=" + ((Object) a1.e.b(this.c)) + ", bottom=" + ((Object) a1.e.b(this.d)) + ')';
    }
}
