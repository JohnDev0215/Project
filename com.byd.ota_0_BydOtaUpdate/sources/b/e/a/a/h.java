package b.e.a.a;

import b.e.a.b.r;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1227a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f1228b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1229c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1230d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1231e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f1232f;

    public void a(float f2, float f3, int i2, int i3, float[] fArr) {
        float f4 = fArr[0];
        float f5 = fArr[1];
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = (f3 - 0.5f) * 2.0f;
        float f8 = f4 + this.f1229c;
        float f9 = f5 + this.f1230d;
        float f10 = f8 + (this.f1227a * f6);
        float f11 = f9 + (this.f1228b * f7);
        float radians = (float) Math.toRadians(this.f1232f);
        float radians2 = (float) Math.toRadians(this.f1231e);
        double d2 = radians;
        double d3 = i3 * f7;
        float fSin = f10 + (((float) ((((double) ((-i2) * f6)) * Math.sin(d2)) - (Math.cos(d2) * d3))) * radians2);
        float fCos = f11 + (radians2 * ((float) ((((double) (i2 * f6)) * Math.cos(d2)) - (d3 * Math.sin(d2)))));
        fArr[0] = fSin;
        fArr[1] = fCos;
    }

    public void b() {
        this.f1231e = 0.0f;
        this.f1230d = 0.0f;
        this.f1229c = 0.0f;
        this.f1228b = 0.0f;
        this.f1227a = 0.0f;
    }

    public void c(b.e.a.b.g gVar, float f2) {
        if (gVar != null) {
            this.f1231e = gVar.b(f2);
        }
    }

    public void d(r rVar, float f2) {
        if (rVar != null) {
            this.f1231e = rVar.b(f2);
            this.f1232f = rVar.a(f2);
        }
    }

    public void e(b.e.a.b.g gVar, b.e.a.b.g gVar2, float f2) {
        if (gVar == null && gVar2 == null) {
            return;
        }
        if (gVar == null) {
            this.f1227a = gVar.b(f2);
        }
        if (gVar2 == null) {
            this.f1228b = gVar2.b(f2);
        }
    }

    public void f(r rVar, r rVar2, float f2) {
        if (rVar != null) {
            this.f1227a = rVar.b(f2);
        }
        if (rVar2 != null) {
            this.f1228b = rVar2.b(f2);
        }
    }

    public void g(b.e.a.b.g gVar, b.e.a.b.g gVar2, float f2) {
        if (gVar != null) {
            this.f1229c = gVar.b(f2);
        }
        if (gVar2 != null) {
            this.f1230d = gVar2.b(f2);
        }
    }

    public void h(r rVar, r rVar2, float f2) {
        if (rVar != null) {
            this.f1229c = rVar.b(f2);
        }
        if (rVar2 != null) {
            this.f1230d = rVar2.b(f2);
        }
    }
}
