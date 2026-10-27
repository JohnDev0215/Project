package androidx.constraintlayout.core.j.a;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f807c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f808d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f809e;
    float f;

    public void a(float f, float f2, int i, int i2, float[] fArr) {
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = (f - 0.5f) * 2.0f;
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = f3 + this.f807c;
        float f8 = f4 + this.f808d;
        float f9 = f7 + (this.f805a * f5);
        float f10 = f8 + (this.f806b * f6);
        float radians = (float) Math.toRadians(this.f);
        float radians2 = (float) Math.toRadians(this.f809e);
        double d2 = radians;
        double d3 = i2 * f6;
        float fSin = f9 + (((float) ((((double) ((-i) * f5)) * Math.sin(d2)) - (Math.cos(d2) * d3))) * radians2);
        float fCos = f10 + (radians2 * ((float) ((((double) (i * f5)) * Math.cos(d2)) - (d3 * Math.sin(d2)))));
        fArr[0] = fSin;
        fArr[1] = fCos;
    }

    public void b() {
        this.f809e = 0.0f;
        this.f808d = 0.0f;
        this.f807c = 0.0f;
        this.f806b = 0.0f;
        this.f805a = 0.0f;
    }

    public void c(e eVar, float f) {
        if (eVar != null) {
            this.f809e = eVar.b(f);
        }
    }

    public void d(j jVar, float f) {
        if (jVar != null) {
            this.f809e = jVar.b(f);
            this.f = jVar.a(f);
        }
    }

    public void e(e eVar, e eVar2, float f) {
        if (eVar != null) {
            this.f805a = eVar.b(f);
        }
        if (eVar2 != null) {
            this.f806b = eVar2.b(f);
        }
    }

    public void f(j jVar, j jVar2, float f) {
        if (jVar != null) {
            this.f805a = jVar.b(f);
        }
        if (jVar2 != null) {
            this.f806b = jVar2.b(f);
        }
    }

    public void g(e eVar, e eVar2, float f) {
        if (eVar != null) {
            this.f807c = eVar.b(f);
        }
        if (eVar2 != null) {
            this.f808d = eVar2.b(f);
        }
    }

    public void h(j jVar, j jVar2, float f) {
        if (jVar != null) {
            this.f807c = jVar.b(f);
        }
        if (jVar2 != null) {
            this.f808d = jVar2.b(f);
        }
    }
}
