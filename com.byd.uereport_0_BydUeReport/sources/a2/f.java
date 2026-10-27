package a2;

/* JADX INFO: compiled from: MeanCalculator.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f67a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f68b;

    public void a(float f8) {
        float f9 = this.f67a + f8;
        this.f67a = f9;
        int i8 = this.f68b + 1;
        this.f68b = i8;
        if (i8 == Integer.MAX_VALUE) {
            this.f67a = f9 / 2.0f;
            this.f68b = i8 / 2;
        }
    }
}
