package x;

/* JADX INFO: renamed from: x.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2650p extends AbstractC2652s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f23598a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f23599b;

    public C2650p(float f10, float f11) {
        this.f23598a = f10;
        this.f23599b = f11;
    }

    @Override // x.AbstractC2652s
    public final float a(int i7) {
        if (i7 == 0) {
            return this.f23598a;
        }
        if (i7 != 1) {
            return 0.0f;
        }
        return this.f23599b;
    }

    @Override // x.AbstractC2652s
    public final int b() {
        return 2;
    }

    @Override // x.AbstractC2652s
    public final AbstractC2652s c() {
        return new C2650p(0.0f, 0.0f);
    }

    @Override // x.AbstractC2652s
    public final void d() {
        this.f23598a = 0.0f;
        this.f23599b = 0.0f;
    }

    @Override // x.AbstractC2652s
    public final void e(float f10, int i7) {
        if (i7 == 0) {
            this.f23598a = f10;
        } else {
            if (i7 != 1) {
                return;
            }
            this.f23599b = f10;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2650p) {
            C2650p c2650p = (C2650p) obj;
            if (c2650p.f23598a == this.f23598a && c2650p.f23599b == this.f23599b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.f23599b) + (Float.hashCode(this.f23598a) * 31);
    }

    public final String toString() {
        return "AnimationVector2D: v1 = " + this.f23598a + ", v2 = " + this.f23599b;
    }
}
