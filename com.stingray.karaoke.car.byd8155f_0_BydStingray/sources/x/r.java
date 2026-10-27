package x;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class r extends AbstractC2652s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f23611a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f23612b;
    public float c;
    public float d;

    public r(float f10, float f11, float f12, float f13) {
        this.f23611a = f10;
        this.f23612b = f11;
        this.c = f12;
        this.d = f13;
    }

    @Override // x.AbstractC2652s
    public final float a(int i7) {
        if (i7 == 0) {
            return this.f23611a;
        }
        if (i7 == 1) {
            return this.f23612b;
        }
        if (i7 == 2) {
            return this.c;
        }
        if (i7 != 3) {
            return 0.0f;
        }
        return this.d;
    }

    @Override // x.AbstractC2652s
    public final int b() {
        return 4;
    }

    @Override // x.AbstractC2652s
    public final AbstractC2652s c() {
        return new r(0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override // x.AbstractC2652s
    public final void d() {
        this.f23611a = 0.0f;
        this.f23612b = 0.0f;
        this.c = 0.0f;
        this.d = 0.0f;
    }

    @Override // x.AbstractC2652s
    public final void e(float f10, int i7) {
        if (i7 == 0) {
            this.f23611a = f10;
            return;
        }
        if (i7 == 1) {
            this.f23612b = f10;
        } else if (i7 == 2) {
            this.c = f10;
        } else {
            if (i7 != 3) {
                return;
            }
            this.d = f10;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof r) {
            r rVar = (r) obj;
            if (rVar.f23611a == this.f23611a && rVar.f23612b == this.f23612b && rVar.c == this.c && rVar.d == this.d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f23611a) * 31, 31, this.f23612b), 31, this.c);
    }

    public final String toString() {
        return "AnimationVector4D: v1 = " + this.f23611a + ", v2 = " + this.f23612b + ", v3 = " + this.c + ", v4 = " + this.d;
    }
}
