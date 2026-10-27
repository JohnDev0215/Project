package x;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: x.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2651q extends AbstractC2652s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f23607a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f23608b;
    public float c;

    public C2651q(float f10, float f11, float f12) {
        this.f23607a = f10;
        this.f23608b = f11;
        this.c = f12;
    }

    @Override // x.AbstractC2652s
    public final float a(int i7) {
        if (i7 == 0) {
            return this.f23607a;
        }
        if (i7 == 1) {
            return this.f23608b;
        }
        if (i7 != 2) {
            return 0.0f;
        }
        return this.c;
    }

    @Override // x.AbstractC2652s
    public final int b() {
        return 3;
    }

    @Override // x.AbstractC2652s
    public final AbstractC2652s c() {
        return new C2651q(0.0f, 0.0f, 0.0f);
    }

    @Override // x.AbstractC2652s
    public final void d() {
        this.f23607a = 0.0f;
        this.f23608b = 0.0f;
        this.c = 0.0f;
    }

    @Override // x.AbstractC2652s
    public final void e(float f10, int i7) {
        if (i7 == 0) {
            this.f23607a = f10;
        } else if (i7 == 1) {
            this.f23608b = f10;
        } else {
            if (i7 != 2) {
                return;
            }
            this.c = f10;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2651q) {
            C2651q c2651q = (C2651q) obj;
            if (c2651q.f23607a == this.f23607a && c2651q.f23608b == this.f23608b && c2651q.c == this.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.c) + AbstractC1347d.b(Float.hashCode(this.f23607a) * 31, 31, this.f23608b);
    }

    public final String toString() {
        return "AnimationVector3D: v1 = " + this.f23607a + ", v2 = " + this.f23608b + ", v3 = " + this.c;
    }
}
