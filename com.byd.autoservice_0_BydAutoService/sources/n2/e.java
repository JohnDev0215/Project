package n2;

/* JADX INFO: compiled from: CutCornerTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f4097a = -1.0f;

    @Override // n2.d
    public void a(o oVar, float f3, float f4, float f5) {
        oVar.o(0.0f, f5 * f4, 180.0f, 180.0f - f3);
        double d3 = f5;
        double d4 = f4;
        oVar.m((float) (Math.sin(Math.toRadians(f3)) * d3 * d4), (float) (Math.sin(Math.toRadians(90.0f - f3)) * d3 * d4));
    }
}
