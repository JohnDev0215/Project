package n2;

/* JADX INFO: compiled from: RoundedCornerTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f4151a = -1.0f;

    @Override // n2.d
    public void a(o oVar, float f3, float f4, float f5) {
        oVar.o(0.0f, f5 * f4, 180.0f, 180.0f - f3);
        float f6 = f5 * 2.0f * f4;
        oVar.a(0.0f, 0.0f, f6, f6, 180.0f, f3);
    }
}
