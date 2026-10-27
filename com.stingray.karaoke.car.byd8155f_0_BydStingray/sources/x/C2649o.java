package x;

/* JADX INFO: renamed from: x.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2649o extends AbstractC2652s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f23577a;

    public C2649o(float f10) {
        this.f23577a = f10;
    }

    @Override // x.AbstractC2652s
    public final float a(int i7) {
        if (i7 == 0) {
            return this.f23577a;
        }
        return 0.0f;
    }

    @Override // x.AbstractC2652s
    public final int b() {
        return 1;
    }

    @Override // x.AbstractC2652s
    public final AbstractC2652s c() {
        return new C2649o(0.0f);
    }

    @Override // x.AbstractC2652s
    public final void d() {
        this.f23577a = 0.0f;
    }

    @Override // x.AbstractC2652s
    public final void e(float f10, int i7) {
        if (i7 == 0) {
            this.f23577a = f10;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C2649o) && ((C2649o) obj).f23577a == this.f23577a;
    }

    public final int hashCode() {
        return Float.hashCode(this.f23577a);
    }

    public final String toString() {
        return "AnimationVector1D: value = " + this.f23577a;
    }
}
