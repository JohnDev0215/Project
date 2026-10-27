package defpackage;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public class p4 {
    public float a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;

    public void a(float f, float f2, int i, int i2, float[] fArr) {
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = (f - 0.5f) * 2.0f;
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = f3 + this.c;
        float f8 = f4 + this.d;
        float f9 = f7 + (this.a * f5);
        float f10 = f8 + (this.b * f6);
        float radians = (float) Math.toRadians(this.f);
        float radians2 = (float) Math.toRadians(this.e);
        double d = radians;
        double d2 = i2 * f6;
        float fSin = f9 + (((float) ((((double) ((-i) * f5)) * Math.sin(d)) - (Math.cos(d) * d2))) * radians2);
        float fCos = f10 + (radians2 * ((float) ((((double) (i * f5)) * Math.cos(d)) - (d2 * Math.sin(d)))));
        fArr[0] = fSin;
        fArr[1] = fCos;
    }

    public void b() {
        this.e = 0.0f;
        this.d = 0.0f;
        this.c = 0.0f;
        this.b = 0.0f;
        this.a = 0.0f;
    }

    public void c(w4 w4Var, float f) {
        if (w4Var != null) {
            this.e = w4Var.b(f);
        }
    }

    public void d(h5 h5Var, float f) {
        if (h5Var != null) {
            this.e = h5Var.b(f);
            this.f = h5Var.a(f);
        }
    }

    public void e(w4 w4Var, w4 w4Var2, float f) {
        if (w4Var == null && w4Var2 == null) {
            return;
        }
        if (w4Var == null) {
            this.a = w4Var.b(f);
        }
        if (w4Var2 == null) {
            this.b = w4Var2.b(f);
        }
    }

    public void f(h5 h5Var, h5 h5Var2, float f) {
        if (h5Var != null) {
            this.a = h5Var.b(f);
        }
        if (h5Var2 != null) {
            this.b = h5Var2.b(f);
        }
    }

    public void g(w4 w4Var, w4 w4Var2, float f) {
        if (w4Var != null) {
            this.c = w4Var.b(f);
        }
        if (w4Var2 != null) {
            this.d = w4Var2.b(f);
        }
    }

    public void h(h5 h5Var, h5 h5Var2, float f) {
        if (h5Var != null) {
            this.c = h5Var.b(f);
        }
        if (h5Var2 != null) {
            this.d = h5Var2.b(f);
        }
    }
}
