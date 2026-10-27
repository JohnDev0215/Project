package defpackage;

import androidx.constraintlayout.widget.a;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
public final class ej1 implements Comparable<ej1> {
    public static final String[] v = {"position", "x", "y", "width", "height", "pathRotate"};
    public hf0 e;
    public int f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public float l;
    public float m;
    public int n;
    public int o;
    public float p;
    public bj1 q;
    public final LinkedHashMap<String, ox> r;
    public int s;
    public double[] t;
    public double[] u;

    public ej1() {
        this.f = 0;
        this.m = Float.NaN;
        this.n = -1;
        this.o = -1;
        this.p = Float.NaN;
        this.q = null;
        this.r = new LinkedHashMap<>();
        this.s = 0;
        this.t = new double[18];
        this.u = new double[18];
    }

    public static boolean e(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public static void h(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f7 = (float) dArr[i];
            double d = dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f4 = f7;
            } else if (i2 == 2) {
                f6 = f7;
            } else if (i2 == 3) {
                f3 = f7;
            } else if (i2 == 4) {
                f5 = f7;
            }
        }
        float f8 = f4 - ((0.0f * f3) / 2.0f);
        float f9 = f6 - ((0.0f * f5) / 2.0f);
        fArr[0] = (((f3 * 1.0f) + f8) * f) + ((1.0f - f) * f8) + 0.0f;
        fArr[1] = (((f5 * 1.0f) + f9) * f2) + ((1.0f - f2) * f9) + 0.0f;
    }

    public final void a(a.C0004a c0004a) {
        this.e = hf0.c(c0004a.d.d);
        a.c cVar = c0004a.d;
        this.n = cVar.e;
        this.o = cVar.b;
        this.m = cVar.h;
        this.f = cVar.f;
        float f = c0004a.c.e;
        this.p = c0004a.e.C;
        for (String str : c0004a.g.keySet()) {
            ox oxVar = c0004a.g.get(str);
            if (oxVar != null) {
                int iM = gq2.m(oxVar.c);
                if ((iM == 4 || iM == 5 || iM == 7) ? false : true) {
                    this.r.put(str, oxVar);
                }
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(ej1 ej1Var) {
        return Float.compare(this.h, ej1Var.h);
    }

    public final void f(double d, int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.i;
        float fCos = this.j;
        float f2 = this.k;
        float f3 = this.l;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f4 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f4;
            } else if (i3 == 2) {
                fCos = f4;
            } else if (i3 == 3) {
                f2 = f4;
            } else if (i3 == 4) {
                f3 = f4;
            }
        }
        bj1 bj1Var = this.q;
        if (bj1Var != null) {
            float[] fArr2 = new float[2];
            bj1Var.c(d, fArr2, new float[2]);
            float f5 = fArr2[0];
            float f6 = fArr2[1];
            double d2 = f;
            double d3 = fCos;
            double dSin = Math.sin(d3) * d2;
            fCos = (float) ((((double) f6) - (Math.cos(d3) * d2)) - ((double) (f3 / 2.0f)));
            f = (float) ((dSin + ((double) f5)) - ((double) (f2 / 2.0f)));
        }
        fArr[i] = (f2 / 2.0f) + f + 0.0f;
        fArr[i + 1] = (f3 / 2.0f) + fCos + 0.0f;
    }

    public final void g(float f, float f2, float f3, float f4) {
        this.i = f;
        this.j = f2;
        this.k = f3;
        this.l = f4;
    }

    public final void i(bj1 bj1Var, ej1 ej1Var) {
        double d = (((this.k / 2.0f) + this.i) - ej1Var.i) - (ej1Var.k / 2.0f);
        double d2 = (((this.l / 2.0f) + this.j) - ej1Var.j) - (ej1Var.l / 2.0f);
        this.q = bj1Var;
        this.i = (float) Math.hypot(d2, d);
        if (Float.isNaN(this.p)) {
            this.j = (float) (Math.atan2(d2, d) + 1.5707963267948966d);
        } else {
            this.j = (float) Math.toRadians(this.p);
        }
    }

    public ej1(int i, int i2, g41 g41Var, ej1 ej1Var, ej1 ej1Var2) {
        float f;
        int i3;
        float f2;
        int i4;
        float fMin;
        float f3;
        this.f = 0;
        this.m = Float.NaN;
        this.n = -1;
        this.o = -1;
        this.p = Float.NaN;
        this.q = null;
        this.r = new LinkedHashMap<>();
        this.s = 0;
        this.t = new double[18];
        this.u = new double[18];
        if (ej1Var.o != -1) {
            float f4 = g41Var.a / 100.0f;
            this.g = f4;
            this.f = g41Var.h;
            this.s = g41Var.o;
            float f5 = Float.isNaN(g41Var.i) ? f4 : g41Var.i;
            float f6 = Float.isNaN(g41Var.j) ? f4 : g41Var.j;
            float f7 = ej1Var2.k;
            float f8 = ej1Var.k;
            float f9 = ej1Var2.l;
            float f10 = ej1Var.l;
            this.h = this.g;
            this.k = (int) (((f7 - f8) * f5) + f8);
            this.l = (int) (((f9 - f10) * f6) + f10);
            if (g41Var.o != 2) {
                float f11 = Float.isNaN(g41Var.k) ? f4 : g41Var.k;
                float f12 = ej1Var2.i;
                float f13 = ej1Var.i;
                this.i = C0173a.f(f12, f13, f11, f13);
                f4 = Float.isNaN(g41Var.l) ? f4 : g41Var.l;
                float f14 = ej1Var2.j;
                float f15 = ej1Var.j;
                this.j = C0173a.f(f14, f15, f4, f15);
            } else {
                if (Float.isNaN(g41Var.k)) {
                    float f16 = ej1Var2.i;
                    float f17 = ej1Var.i;
                    fMin = C0173a.f(f16, f17, f4, f17);
                } else {
                    fMin = g41Var.k * Math.min(f6, f5);
                }
                this.i = fMin;
                if (Float.isNaN(g41Var.l)) {
                    float f18 = ej1Var2.j;
                    float f19 = ej1Var.j;
                    f3 = C0173a.f(f18, f19, f4, f19);
                } else {
                    f3 = g41Var.l;
                }
                this.j = f3;
            }
            this.o = ej1Var.o;
            this.e = hf0.c(g41Var.f);
            this.n = g41Var.g;
            return;
        }
        int i5 = g41Var.o;
        if (i5 == 1) {
            float f20 = g41Var.a / 100.0f;
            this.g = f20;
            this.f = g41Var.h;
            float f21 = Float.isNaN(g41Var.i) ? f20 : g41Var.i;
            float f22 = Float.isNaN(g41Var.j) ? f20 : g41Var.j;
            float f23 = ej1Var2.k - ej1Var.k;
            float f24 = ej1Var2.l - ej1Var.l;
            this.h = this.g;
            f20 = Float.isNaN(g41Var.k) ? f20 : g41Var.k;
            float f25 = ej1Var.i;
            float f26 = ej1Var.k;
            float f27 = ej1Var.j;
            float f28 = ej1Var.l;
            float f29 = ((ej1Var2.k / 2.0f) + ej1Var2.i) - ((f26 / 2.0f) + f25);
            float f30 = ((ej1Var2.l / 2.0f) + ej1Var2.j) - ((f28 / 2.0f) + f27);
            float f31 = f29 * f20;
            float f32 = (f23 * f21) / 2.0f;
            this.i = (int) ((f25 + f31) - f32);
            float f33 = f20 * f30;
            float f34 = (f24 * f22) / 2.0f;
            this.j = (int) ((f27 + f33) - f34);
            this.k = (int) (f26 + r6);
            this.l = (int) (f28 + r7);
            float f35 = Float.isNaN(g41Var.l) ? 0.0f : g41Var.l;
            this.s = 1;
            float f36 = (int) ((ej1Var.i + f31) - f32);
            float f37 = (int) ((ej1Var.j + f33) - f34);
            this.i = f36 + ((-f30) * f35);
            this.j = f37 + (f29 * f35);
            this.o = this.o;
            this.e = hf0.c(g41Var.f);
            this.n = g41Var.g;
            return;
        }
        if (i5 == 2) {
            float f38 = g41Var.a / 100.0f;
            this.g = f38;
            this.f = g41Var.h;
            float f39 = Float.isNaN(g41Var.i) ? f38 : g41Var.i;
            float f40 = Float.isNaN(g41Var.j) ? f38 : g41Var.j;
            float f41 = ej1Var2.k;
            float f42 = f41 - ej1Var.k;
            float f43 = ej1Var2.l;
            float f44 = f43 - ej1Var.l;
            this.h = this.g;
            float f45 = ej1Var.i;
            float f46 = ej1Var.j;
            float f47 = (f41 / 2.0f) + ej1Var2.i;
            float f48 = (f43 / 2.0f) + ej1Var2.j;
            float f49 = f42 * f39;
            this.i = (int) ((((f47 - ((r7 / 2.0f) + f45)) * f38) + f45) - (f49 / 2.0f));
            float f50 = f44 * f40;
            this.j = (int) ((((f48 - ((r12 / 2.0f) + f46)) * f38) + f46) - (f50 / 2.0f));
            this.k = (int) (r7 + f49);
            this.l = (int) (r12 + f50);
            this.s = 2;
            if (!Float.isNaN(g41Var.k)) {
                this.i = (int) (g41Var.k * (i - ((int) this.k)));
            }
            if (!Float.isNaN(g41Var.l)) {
                this.j = (int) (g41Var.l * (i2 - ((int) this.l)));
            }
            this.o = this.o;
            this.e = hf0.c(g41Var.f);
            this.n = g41Var.g;
            return;
        }
        if (i5 != 3) {
            float f51 = g41Var.a / 100.0f;
            this.g = f51;
            this.f = g41Var.h;
            float f52 = Float.isNaN(g41Var.i) ? f51 : g41Var.i;
            float f53 = Float.isNaN(g41Var.j) ? f51 : g41Var.j;
            float f54 = ej1Var2.k;
            float f55 = ej1Var.k;
            float f56 = f54 - f55;
            float f57 = ej1Var2.l;
            float f58 = ej1Var.l;
            float f59 = f57 - f58;
            this.h = this.g;
            float f60 = ej1Var.i;
            float f61 = ej1Var.j;
            float f62 = ((f54 / 2.0f) + ej1Var2.i) - ((f55 / 2.0f) + f60);
            float f63 = ((f57 / 2.0f) + ej1Var2.j) - ((f58 / 2.0f) + f61);
            float f64 = (f56 * f52) / 2.0f;
            this.i = (int) (((f62 * f51) + f60) - f64);
            float f65 = (f63 * f51) + f61;
            float f66 = (f59 * f53) / 2.0f;
            this.j = (int) (f65 - f66);
            this.k = (int) (f55 + r10);
            this.l = (int) (f58 + r13);
            float f67 = Float.isNaN(g41Var.k) ? f51 : g41Var.k;
            float f68 = Float.isNaN(g41Var.n) ? 0.0f : g41Var.n;
            f51 = Float.isNaN(g41Var.l) ? f51 : g41Var.l;
            if (Float.isNaN(g41Var.m)) {
                i4 = 0;
                f2 = 0.0f;
            } else {
                f2 = g41Var.m;
                i4 = 0;
            }
            this.s = i4;
            this.i = (int) (((f2 * f63) + ((f67 * f62) + ej1Var.i)) - f64);
            this.j = (int) (((f63 * f51) + ((f62 * f68) + ej1Var.j)) - f66);
            this.e = hf0.c(g41Var.f);
            this.n = g41Var.g;
            return;
        }
        float f69 = g41Var.a / 100.0f;
        this.g = f69;
        this.f = g41Var.h;
        float f70 = Float.isNaN(g41Var.i) ? f69 : g41Var.i;
        float f71 = Float.isNaN(g41Var.j) ? f69 : g41Var.j;
        float f72 = ej1Var2.k;
        float f73 = ej1Var.k;
        float f74 = f72 - f73;
        float f75 = ej1Var2.l;
        float f76 = ej1Var.l;
        float f77 = f75 - f76;
        this.h = this.g;
        float f78 = (f73 / 2.0f) + ej1Var.i;
        float f79 = ej1Var.j;
        float f80 = (f76 / 2.0f) + f79;
        float f81 = (f72 / 2.0f) + ej1Var2.i;
        float f82 = (f75 / 2.0f) + ej1Var2.j;
        if (f78 <= f81) {
            f78 = f81;
            f81 = f78;
        }
        if (f80 <= f82) {
            f80 = f82;
            f82 = f80;
        }
        float f83 = f78 - f81;
        float f84 = (f74 * f70) / 2.0f;
        this.i = (int) (((f83 * f69) + r14) - f84);
        float f85 = ((f80 - f82) * f69) + f79;
        float f86 = (f77 * f71) / 2.0f;
        this.j = (int) (f85 - f86);
        this.k = (int) (f73 + r9);
        this.l = (int) (f76 + r13);
        float f87 = Float.isNaN(g41Var.k) ? f69 : g41Var.k;
        float f88 = Float.isNaN(g41Var.n) ? 0.0f : g41Var.n;
        f69 = Float.isNaN(g41Var.l) ? f69 : g41Var.l;
        if (Float.isNaN(g41Var.m)) {
            i3 = 0;
            f = 0.0f;
        } else {
            f = g41Var.m;
            i3 = 0;
        }
        this.s = i3;
        this.i = (int) (((f * r16) + ((f87 * f83) + ej1Var.i)) - f84);
        this.j = (int) (((r16 * f69) + ((f83 * f88) + ej1Var.j)) - f86);
        this.e = hf0.c(g41Var.f);
        this.n = g41Var.g;
    }
}
