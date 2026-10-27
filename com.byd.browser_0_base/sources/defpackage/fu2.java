package defpackage;

/* JADX INFO: compiled from: StopLogic.java */
/* JADX INFO: loaded from: classes.dex */
public final class fu2 extends dj1 {
    public final gu2 a;
    public er2 b;
    public eu2 c;

    public fu2() {
        gu2 gu2Var = new gu2();
        this.a = gu2Var;
        this.c = gu2Var;
    }

    @Override // defpackage.dj1
    public final float a() {
        return this.c.b();
    }

    public final void b(float f, float f2, float f3, float f4, float f5, float f6) {
        gu2 gu2Var = this.a;
        this.c = gu2Var;
        gu2Var.l = f;
        boolean z = f > f2;
        gu2Var.k = z;
        if (z) {
            gu2Var.d(-f3, f - f2, f5, f6, f4);
        } else {
            gu2Var.d(f3, f2 - f, f5, f6, f4);
        }
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return this.c.getInterpolation(f);
    }
}
