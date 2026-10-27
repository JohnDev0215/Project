package o0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: o0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2007c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20178a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f20179b;
    public final int c;

    public AbstractC2007c(String str, long j3, int i7) {
        this.f20178a = str;
        this.f20179b = j3;
        this.c = i7;
        if (str.length() == 0) {
            throw new IllegalArgumentException("The name of a color space cannot be null and must contain at least 1 character");
        }
        if (i7 < -1 || i7 > 63) {
            throw new IllegalArgumentException("The id must be between -1 and 63");
        }
    }

    public abstract float a(int i7);

    public abstract float b(int i7);

    public boolean c() {
        return false;
    }

    public abstract long d(float f10, float f11, float f12);

    public abstract float e(float f10, float f11, float f12);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractC2007c abstractC2007c = (AbstractC2007c) obj;
        if (this.c == abstractC2007c.c && A7.m.a(this.f20178a, abstractC2007c.f20178a)) {
            return AbstractC2006b.a(this.f20179b, abstractC2007c.f20179b);
        }
        return false;
    }

    public abstract long f(float f10, float f11, float f12, float f13, AbstractC2007c abstractC2007c);

    public int hashCode() {
        int iHashCode = this.f20178a.hashCode() * 31;
        int i7 = AbstractC2006b.f20177e;
        return AbstractC1347d.c(iHashCode, 31, this.f20179b) + this.c;
    }

    public final String toString() {
        return this.f20178a + " (id=" + this.c + ", model=" + ((Object) AbstractC2006b.b(this.f20179b)) + ')';
    }
}
