package b2;

/* JADX INFO: compiled from: ScaleXY.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f2068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2069b;

    public d(float f8, float f9) {
        this.f2068a = f8;
        this.f2069b = f9;
    }

    public boolean a(float f8, float f9) {
        return this.f2068a == f8 && this.f2069b == f9;
    }

    public float b() {
        return this.f2068a;
    }

    public float c() {
        return this.f2069b;
    }

    public void d(float f8, float f9) {
        this.f2068a = f8;
        this.f2069b = f9;
    }

    public String toString() {
        return b() + "x" + c();
    }

    public d() {
        this(1.0f, 1.0f);
    }
}
