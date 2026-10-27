package e3;

/* JADX INFO: compiled from: RoundedCornerTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f6226a = -1.0f;

    @Override // e3.d
    public void a(m mVar, float f7, float f8, float f9) {
        mVar.o(0.0f, f9 * f8, 180.0f, 180.0f - f7);
        float f10 = f9 * 2.0f * f8;
        mVar.a(0.0f, 0.0f, f10, f10, 180.0f, f7);
    }
}
