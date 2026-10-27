package w;

import x.r;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f8659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f8660b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f8661c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f8662d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8663e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f8664f;

    public void a(float f8, float f9, int i8, int i9, float[] fArr) {
        float f10 = fArr[0];
        float f11 = fArr[1];
        float f12 = (f8 - 0.5f) * 2.0f;
        float f13 = (f9 - 0.5f) * 2.0f;
        float f14 = f10 + this.f8661c;
        float f15 = f11 + this.f8662d;
        float f16 = f14 + (this.f8659a * f12);
        float f17 = f15 + (this.f8660b * f13);
        float radians = (float) Math.toRadians(this.f8664f);
        float radians2 = (float) Math.toRadians(this.f8663e);
        double d8 = radians;
        double d9 = i9 * f13;
        float fSin = f16 + (((float) ((((double) ((-i8) * f12)) * Math.sin(d8)) - (Math.cos(d8) * d9))) * radians2);
        float fCos = f17 + (radians2 * ((float) ((((double) (i8 * f12)) * Math.cos(d8)) - (d9 * Math.sin(d8)))));
        fArr[0] = fSin;
        fArr[1] = fCos;
    }

    public void b() {
        this.f8663e = 0.0f;
        this.f8662d = 0.0f;
        this.f8661c = 0.0f;
        this.f8660b = 0.0f;
        this.f8659a = 0.0f;
    }

    public void c(x.g gVar, float f8) {
        if (gVar != null) {
            this.f8663e = gVar.b(f8);
        }
    }

    public void d(r rVar, float f8) {
        if (rVar != null) {
            this.f8663e = rVar.b(f8);
            this.f8664f = rVar.a(f8);
        }
    }

    public void e(x.g gVar, x.g gVar2, float f8) {
        if (gVar == null && gVar2 == null) {
            return;
        }
        if (gVar == null) {
            this.f8659a = gVar.b(f8);
        }
        if (gVar2 == null) {
            this.f8660b = gVar2.b(f8);
        }
    }

    public void f(r rVar, r rVar2, float f8) {
        if (rVar != null) {
            this.f8659a = rVar.b(f8);
        }
        if (rVar2 != null) {
            this.f8660b = rVar2.b(f8);
        }
    }

    public void g(x.g gVar, x.g gVar2, float f8) {
        if (gVar != null) {
            this.f8661c = gVar.b(f8);
        }
        if (gVar2 != null) {
            this.f8662d = gVar2.b(f8);
        }
    }

    public void h(r rVar, r rVar2, float f8) {
        if (rVar != null) {
            this.f8661c = rVar.b(f8);
        }
        if (rVar2 != null) {
            this.f8662d = rVar2.b(f8);
        }
    }
}
