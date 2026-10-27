package b.e.a.a;

import androidx.constraintlayout.motion.widget.r;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f2345a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f2346b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f2347c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f2348d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f2349e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f2350f;

    public void a(float f2, float f3, int i, int i2, float[] fArr) {
        float f4 = fArr[0];
        float f5 = fArr[1];
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = (f3 - 0.5f) * 2.0f;
        float f8 = f4 + this.f2347c;
        float f9 = f5 + this.f2348d;
        float f10 = f8 + (this.f2345a * f6);
        float f11 = f9 + (this.f2346b * f7);
        float radians = (float) Math.toRadians(this.f2350f);
        float radians2 = (float) Math.toRadians(this.f2349e);
        double d2 = radians;
        double d3 = i2 * f7;
        float fSin = f10 + (((float) ((((double) ((-i) * f6)) * Math.sin(d2)) - (Math.cos(d2) * d3))) * radians2);
        float fCos = f11 + (radians2 * ((float) ((((double) (i * f6)) * Math.cos(d2)) - (d3 * Math.sin(d2)))));
        fArr[0] = fSin;
        fArr[1] = fCos;
    }

    public void b() {
        this.f2349e = 0.0f;
        this.f2348d = 0.0f;
        this.f2347c = 0.0f;
        this.f2346b = 0.0f;
        this.f2345a = 0.0f;
    }

    public void c(androidx.constraintlayout.motion.widget.g gVar, float f2) {
        if (gVar != null) {
            this.f2349e = gVar.b(f2);
        }
    }

    public void d(r rVar, float f2) {
        if (rVar != null) {
            this.f2349e = rVar.b(f2);
            this.f2350f = rVar.a(f2);
        }
    }

    public void e(androidx.constraintlayout.motion.widget.g gVar, androidx.constraintlayout.motion.widget.g gVar2, float f2) {
        if (gVar == null && gVar2 == null) {
            return;
        }
        if (gVar == null) {
            this.f2345a = gVar.b(f2);
        }
        if (gVar2 == null) {
            this.f2346b = gVar2.b(f2);
        }
    }

    public void f(r rVar, r rVar2, float f2) {
        if (rVar != null) {
            this.f2345a = rVar.b(f2);
        }
        if (rVar2 != null) {
            this.f2346b = rVar2.b(f2);
        }
    }

    public void g(androidx.constraintlayout.motion.widget.g gVar, androidx.constraintlayout.motion.widget.g gVar2, float f2) {
        if (gVar != null) {
            this.f2347c = gVar.b(f2);
        }
        if (gVar2 != null) {
            this.f2348d = gVar2.b(f2);
        }
    }

    public void h(r rVar, r rVar2, float f2) {
        if (rVar != null) {
            this.f2347c = rVar.b(f2);
        }
        if (rVar2 != null) {
            this.f2348d = rVar2.b(f2);
        }
    }
}
