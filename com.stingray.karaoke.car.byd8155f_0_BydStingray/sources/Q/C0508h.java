package Q;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: Q.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0508h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5900a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5901b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f5902e;

    public C0508h(float f10, float f11, float f12, float f13, float f14) {
        this.f5900a = f10;
        this.f5901b = f11;
        this.c = f12;
        this.d = f13;
        this.f5902e = f14;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C0508h)) {
            return false;
        }
        C0508h c0508h = (C0508h) obj;
        return a1.e.a(this.f5900a, c0508h.f5900a) && a1.e.a(this.f5901b, c0508h.f5901b) && a1.e.a(this.c, c0508h.c) && a1.e.a(this.d, c0508h.d) && a1.e.a(this.f5902e, c0508h.f5902e);
    }

    public final int hashCode() {
        return Float.hashCode(this.f5902e) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f5900a) * 31, 31, this.f5901b), 31, this.c), 31, this.d);
    }
}
