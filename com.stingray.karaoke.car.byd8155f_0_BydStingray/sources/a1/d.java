package a1;

import a.AbstractC0904a;
import b1.InterfaceC0988a;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class d implements b {
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f11029m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC0988a f11030n;

    public d(float f10, float f11, InterfaceC0988a interfaceC0988a) {
        this.l = f10;
        this.f11029m = f11;
        this.f11030n = interfaceC0988a;
    }

    @Override // a1.b
    public final float K(long j3) {
        if (n.a(m.b(j3), 4294967296L)) {
            return this.f11030n.b(m.c(j3));
        }
        throw new IllegalStateException("Only Sp can convert to Px");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Float.compare(this.l, dVar.l) == 0 && Float.compare(this.f11029m, dVar.f11029m) == 0 && A7.m.a(this.f11030n, dVar.f11030n);
    }

    @Override // a1.b
    public final float getDensity() {
        return this.l;
    }

    public final int hashCode() {
        return this.f11030n.hashCode() + AbstractC1347d.b(Float.hashCode(this.l) * 31, 31, this.f11029m);
    }

    @Override // a1.b
    public final float p() {
        return this.f11029m;
    }

    public final String toString() {
        return "DensityWithConverter(density=" + this.l + ", fontScale=" + this.f11029m + ", converter=" + this.f11030n + ')';
    }

    @Override // a1.b
    public final long w(float f10) {
        return AbstractC0904a.O(this.f11030n.a(f10), 4294967296L);
    }
}
