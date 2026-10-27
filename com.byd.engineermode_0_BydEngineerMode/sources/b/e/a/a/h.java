package b.e.a.a;

import androidx.constraintlayout.motion.widget.r;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f1666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f1667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f1668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f1669d;
    float e;
    float f;

    public void a(float f, float f2, int i, int i2, float[] fArr) {
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = (f - 0.5f) * 2.0f;
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = f3 + this.f1668c;
        float f8 = f4 + this.f1669d;
        float f9 = f7 + (this.f1666a * f5);
        float f10 = f8 + (this.f1667b * f6);
        float radians = (float) Math.toRadians(this.f);
        float radians2 = (float) Math.toRadians(this.e);
        double d2 = radians;
        double d3 = i2 * f6;
        float fSin = f9 + (((float) ((((double) ((-i) * f5)) * Math.sin(d2)) - (Math.cos(d2) * d3))) * radians2);
        float fCos = f10 + (radians2 * ((float) ((((double) (i * f5)) * Math.cos(d2)) - (d3 * Math.sin(d2)))));
        fArr[0] = fSin;
        fArr[1] = fCos;
    }

    public void b() {
        this.e = 0.0f;
        this.f1669d = 0.0f;
        this.f1668c = 0.0f;
        this.f1667b = 0.0f;
        this.f1666a = 0.0f;
    }

    public void c(androidx.constraintlayout.motion.widget.g gVar, float f) {
        if (gVar != null) {
            this.e = gVar.b(f);
        }
    }

    public void d(r rVar, float f) {
        if (rVar != null) {
            this.e = rVar.b(f);
            this.f = rVar.a(f);
        }
    }

    public void e(androidx.constraintlayout.motion.widget.g gVar, androidx.constraintlayout.motion.widget.g gVar2, float f) {
        if (gVar == null && gVar2 == null) {
            return;
        }
        if (gVar == null) {
            this.f1666a = gVar.b(f);
        }
        if (gVar2 == null) {
            this.f1667b = gVar2.b(f);
        }
    }

    public void f(r rVar, r rVar2, float f) {
        if (rVar != null) {
            this.f1666a = rVar.b(f);
        }
        if (rVar2 != null) {
            this.f1667b = rVar2.b(f);
        }
    }

    public void g(androidx.constraintlayout.motion.widget.g gVar, androidx.constraintlayout.motion.widget.g gVar2, float f) {
        if (gVar != null) {
            this.f1668c = gVar.b(f);
        }
        if (gVar2 != null) {
            this.f1669d = gVar2.b(f);
        }
    }

    public void h(r rVar, r rVar2, float f) {
        if (rVar != null) {
            this.f1668c = rVar.b(f);
        }
        if (rVar2 != null) {
            this.f1669d = rVar2.b(f);
        }
    }
}
