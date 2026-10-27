package e6;

/* JADX INFO: compiled from: RoundedCornerTreatment.java */
/* JADX INFO: loaded from: classes2.dex */
public class l extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f4704a = -1.0f;

    @Override // e6.d
    public void a(o oVar, float f8, float f9, float f10) {
        oVar.o(0.0f, f10 * f9, 180.0f, 180.0f - f8);
        float f11 = f10 * 2.0f * f9;
        oVar.a(0.0f, 0.0f, f11, f11, 180.0f, f8);
    }
}
