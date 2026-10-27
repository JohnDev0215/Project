package t;

import u.r;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f4984a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f4985b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f4986c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4987d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4988e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4989f;

    public void a(float f3, float f4, int i3, int i4, float[] fArr) {
        float f5 = fArr[0];
        float f6 = fArr[1];
        float f7 = (f3 - 0.5f) * 2.0f;
        float f8 = (f4 - 0.5f) * 2.0f;
        float f9 = f5 + this.f4986c;
        float f10 = f6 + this.f4987d;
        float f11 = f9 + (this.f4984a * f7);
        float f12 = f10 + (this.f4985b * f8);
        float radians = (float) Math.toRadians(this.f4989f);
        float radians2 = (float) Math.toRadians(this.f4988e);
        double d3 = radians;
        double d4 = i4 * f8;
        float fSin = f11 + (((float) ((((double) ((-i3) * f7)) * Math.sin(d3)) - (Math.cos(d3) * d4))) * radians2);
        float fCos = f12 + (radians2 * ((float) ((((double) (i3 * f7)) * Math.cos(d3)) - (d4 * Math.sin(d3)))));
        fArr[0] = fSin;
        fArr[1] = fCos;
    }

    public void b() {
        this.f4988e = 0.0f;
        this.f4987d = 0.0f;
        this.f4986c = 0.0f;
        this.f4985b = 0.0f;
        this.f4984a = 0.0f;
    }

    public void c(u.g gVar, float f3) {
        if (gVar != null) {
            this.f4988e = gVar.b(f3);
        }
    }

    public void d(r rVar, float f3) {
        if (rVar != null) {
            this.f4988e = rVar.b(f3);
            this.f4989f = rVar.a(f3);
        }
    }

    public void e(u.g gVar, u.g gVar2, float f3) {
        if (gVar == null && gVar2 == null) {
            return;
        }
        if (gVar == null) {
            this.f4984a = gVar.b(f3);
        }
        if (gVar2 == null) {
            this.f4985b = gVar2.b(f3);
        }
    }

    public void f(r rVar, r rVar2, float f3) {
        if (rVar != null) {
            this.f4984a = rVar.b(f3);
        }
        if (rVar2 != null) {
            this.f4985b = rVar2.b(f3);
        }
    }

    public void g(u.g gVar, u.g gVar2, float f3) {
        if (gVar != null) {
            this.f4986c = gVar.b(f3);
        }
        if (gVar2 != null) {
            this.f4987d = gVar2.b(f3);
        }
    }

    public void h(r rVar, r rVar2, float f3) {
        if (rVar != null) {
            this.f4986c = rVar.b(f3);
        }
        if (rVar2 != null) {
            this.f4987d = rVar2.b(f3);
        }
    }
}
