package p;

/* JADX INFO: loaded from: classes.dex */
public final class q52 extends l13 {
    @Override // p.l13
    public final void e(float f, float f2, yc2 yc2Var) {
        yc2Var.d(f2 * f, 180.0f, 90.0f);
        float f3 = f2 * 2.0f * f;
        uc2 uc2Var = new uc2(0.0f, 0.0f, f3, f3);
        uc2Var.f = 180.0f;
        uc2Var.g = 90.0f;
        yc2Var.g.add(uc2Var);
        sc2 sc2Var = new sc2(uc2Var);
        yc2Var.a(180.0f);
        yc2Var.h.add(sc2Var);
        yc2Var.e = 270.0f;
        float f4 = (0.0f + f3) * 0.5f;
        float f5 = (f3 - 0.0f) / 2.0f;
        double d = 270.0f;
        yc2Var.c = (((float) Math.cos(Math.toRadians(d))) * f5) + f4;
        yc2Var.d = (f5 * ((float) Math.sin(Math.toRadians(d)))) + f4;
    }
}
