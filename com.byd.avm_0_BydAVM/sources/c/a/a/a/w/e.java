package c.a.a.a.w;

/* JADX INFO: compiled from: CutCornerTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f2065a = -1.0f;

    @Override // c.a.a.a.w.d
    public void a(o oVar, float f, float f2, float f3) {
        oVar.o(0.0f, f3 * f2, 180.0f, 180.0f - f);
        double d2 = f3;
        double d3 = f2;
        oVar.m((float) (Math.sin(Math.toRadians(f)) * d2 * d3), (float) (Math.sin(Math.toRadians(90.0f - f)) * d2 * d3));
    }
}
