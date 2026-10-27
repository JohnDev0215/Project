package e6;

/* JADX INFO: compiled from: CutCornerTreatment.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f4651a = -1.0f;

    @Override // e6.d
    public void a(o oVar, float f8, float f9, float f10) {
        oVar.o(0.0f, f10 * f9, 180.0f, 180.0f - f8);
        double d8 = f10;
        double d9 = f9;
        oVar.m((float) (Math.sin(Math.toRadians(f8)) * d8 * d9), (float) (Math.sin(Math.toRadians(90.0f - f8)) * d8 * d9));
    }
}
