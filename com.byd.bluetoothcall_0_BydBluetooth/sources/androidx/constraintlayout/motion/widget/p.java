package androidx.constraintlayout.motion.widget;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.b;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
class p implements Comparable<p> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String[] f947b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    androidx.constraintlayout.core.j.a.c f948c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f950e;
    float f;
    float g;
    float h;
    float i;
    float j;
    int m;
    int n;
    float o;
    n p;
    LinkedHashMap<String, ConstraintAttribute> q;
    int r;
    int s;
    double[] t;
    double[] u;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f949d = 0;
    float k = Float.NaN;
    float l = Float.NaN;

    public p() {
        int i = e.f925a;
        this.m = i;
        this.n = i;
        this.o = Float.NaN;
        this.p = null;
        this.q = new LinkedHashMap<>();
        this.r = 0;
        this.t = new double[18];
        this.u = new double[18];
    }

    private boolean e(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void b(b.a aVar) {
        this.f948c = androidx.constraintlayout.core.j.a.c.c(aVar.f1078d.f1094e);
        b.c cVar = aVar.f1078d;
        this.m = cVar.f;
        this.n = cVar.f1092c;
        this.k = cVar.j;
        this.f949d = cVar.g;
        this.s = cVar.f1093d;
        this.l = aVar.f1077c.f1099e;
        this.o = aVar.f1079e.E;
        for (String str : aVar.g.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.g.get(str);
            if (constraintAttribute != null && constraintAttribute.g()) {
                this.q.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(p pVar) {
        return Float.compare(this.f, pVar.f);
    }

    void g(p pVar, boolean[] zArr, String[] strArr, boolean z) {
        boolean zE = e(this.g, pVar.g);
        boolean zE2 = e(this.h, pVar.h);
        zArr[0] = zArr[0] | e(this.f, pVar.f);
        boolean z2 = zE | zE2 | z;
        zArr[1] = zArr[1] | z2;
        zArr[2] = z2 | zArr[2];
        zArr[3] = zArr[3] | e(this.i, pVar.i);
        zArr[4] = e(this.j, pVar.j) | zArr[4];
    }

    void h(double[] dArr, int[] iArr) {
        float[] fArr = {this.f, this.g, this.h, this.i, this.j, this.k};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    void i(double d2, int[] iArr, double[] dArr, float[] fArr, int i) {
        float fSin = this.g;
        float fCos = this.h;
        float f = this.i;
        float f2 = this.j;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f3 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                fSin = f3;
            } else if (i3 == 2) {
                fCos = f3;
            } else if (i3 == 3) {
                f = f3;
            } else if (i3 == 4) {
                f2 = f3;
            }
        }
        n nVar = this.p;
        if (nVar != null) {
            float[] fArr2 = new float[2];
            nVar.i(d2, fArr2, new float[2]);
            float f4 = fArr2[0];
            float f5 = fArr2[1];
            double d3 = f4;
            double d4 = fSin;
            double d5 = fCos;
            fSin = (float) ((d3 + (Math.sin(d5) * d4)) - ((double) (f / 2.0f)));
            fCos = (float) ((((double) f5) - (d4 * Math.cos(d5))) - ((double) (f2 / 2.0f)));
        }
        fArr[i] = fSin + (f / 2.0f) + 0.0f;
        fArr[i + 1] = fCos + (f2 / 2.0f) + 0.0f;
    }

    void j(double d2, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f;
        float f2 = this.g;
        float f3 = this.h;
        float f4 = this.i;
        float f5 = this.j;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f10 = (float) dArr[i];
            float f11 = (float) dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f2 = f10;
                f6 = f11;
            } else if (i2 == 2) {
                f3 = f10;
                f8 = f11;
            } else if (i2 == 3) {
                f4 = f10;
                f7 = f11;
            } else if (i2 == 4) {
                f5 = f10;
                f9 = f11;
            }
        }
        float f12 = 2.0f;
        float f13 = (f7 / 2.0f) + f6;
        float fCos = (f9 / 2.0f) + f8;
        n nVar = this.p;
        if (nVar != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            nVar.i(d2, fArr3, fArr4);
            float f14 = fArr3[0];
            float f15 = fArr3[1];
            float f16 = fArr4[0];
            float f17 = fArr4[1];
            double d3 = f2;
            double d4 = f3;
            f = f4;
            float fSin = (float) ((((double) f14) + (Math.sin(d4) * d3)) - ((double) (f4 / 2.0f)));
            float fCos2 = (float) ((((double) f15) - (d3 * Math.cos(d4))) - ((double) (f5 / 2.0f)));
            double d5 = f6;
            double d6 = f8;
            float fSin2 = (float) (((double) f16) + (Math.sin(d4) * d5) + (Math.cos(d4) * d6));
            fCos = (float) ((((double) f17) - (d5 * Math.cos(d4))) + (Math.sin(d4) * d6));
            f13 = fSin2;
            f2 = fSin;
            f3 = fCos2;
            f12 = 2.0f;
        } else {
            f = f4;
        }
        fArr[0] = f2 + (f / f12) + 0.0f;
        fArr[1] = f3 + (f5 / f12) + 0.0f;
        fArr2[0] = f13;
        fArr2[1] = fCos;
    }

    int k(String str, double[] dArr, int i) {
        ConstraintAttribute constraintAttribute = this.q.get(str);
        int i2 = 0;
        if (constraintAttribute == null) {
            return 0;
        }
        if (constraintAttribute.h() == 1) {
            dArr[i] = constraintAttribute.e();
            return 1;
        }
        int iH = constraintAttribute.h();
        constraintAttribute.f(new float[iH]);
        while (i2 < iH) {
            dArr[i] = r2[i2];
            i2++;
            i++;
        }
        return iH;
    }

    int l(String str) {
        ConstraintAttribute constraintAttribute = this.q.get(str);
        if (constraintAttribute == null) {
            return 0;
        }
        return constraintAttribute.h();
    }

    void m(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.g;
        float fCos = this.h;
        float f2 = this.i;
        float f3 = this.j;
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
        n nVar = this.p;
        if (nVar != null) {
            float fJ = nVar.j();
            float fK = this.p.k();
            double d2 = f;
            double d3 = fCos;
            float fSin = (float) ((((double) fJ) + (Math.sin(d3) * d2)) - ((double) (f2 / 2.0f)));
            fCos = (float) ((((double) fK) - (d2 * Math.cos(d3))) - ((double) (f3 / 2.0f)));
            f = fSin;
        }
        float f5 = f2 + f;
        float f6 = f3 + fCos;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i4 = i + 1;
        fArr[i] = f + 0.0f;
        int i5 = i4 + 1;
        fArr[i4] = fCos + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f5 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = fCos + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f5 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f6 + 0.0f;
        fArr[i9] = f + 0.0f;
        fArr[i9 + 1] = f6 + 0.0f;
    }

    boolean n(String str) {
        return this.q.containsKey(str);
    }

    void o(i iVar, p pVar, p pVar2) {
        float f = iVar.f926b / 100.0f;
        this.f950e = f;
        this.f949d = iVar.j;
        float f2 = Float.isNaN(iVar.k) ? f : iVar.k;
        float f3 = Float.isNaN(iVar.l) ? f : iVar.l;
        float f4 = pVar2.i;
        float f5 = pVar.i;
        float f6 = pVar2.j;
        float f7 = pVar.j;
        this.f = this.f950e;
        float f8 = pVar.g;
        float f9 = pVar.h;
        float f10 = (pVar2.g + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (pVar2.h + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = ((f4 - f5) * f2) / 2.0f;
        this.g = (int) ((f8 + (f10 * f)) - f12);
        float f13 = ((f6 - f7) * f3) / 2.0f;
        this.h = (int) ((f9 + (f11 * f)) - f13);
        this.i = (int) (f5 + r9);
        this.j = (int) (f7 + r12);
        float f14 = Float.isNaN(iVar.m) ? f : iVar.m;
        float f15 = Float.isNaN(iVar.p) ? 0.0f : iVar.p;
        if (!Float.isNaN(iVar.n)) {
            f = iVar.n;
        }
        float f16 = Float.isNaN(iVar.o) ? 0.0f : iVar.o;
        this.r = 0;
        this.g = (int) (((pVar.g + (f14 * f10)) + (f16 * f11)) - f12);
        this.h = (int) (((pVar.h + (f10 * f15)) + (f11 * f)) - f13);
        this.f948c = androidx.constraintlayout.core.j.a.c.c(iVar.h);
        this.m = iVar.i;
    }

    void p(i iVar, p pVar, p pVar2) {
        float f = iVar.f926b / 100.0f;
        this.f950e = f;
        this.f949d = iVar.j;
        float f2 = Float.isNaN(iVar.k) ? f : iVar.k;
        float f3 = Float.isNaN(iVar.l) ? f : iVar.l;
        float f4 = pVar2.i - pVar.i;
        float f5 = pVar2.j - pVar.j;
        this.f = this.f950e;
        if (!Float.isNaN(iVar.m)) {
            f = iVar.m;
        }
        float f6 = pVar.g;
        float f7 = pVar.i;
        float f8 = pVar.h;
        float f9 = pVar.j;
        float f10 = (pVar2.g + (pVar2.i / 2.0f)) - ((f7 / 2.0f) + f6);
        float f11 = (pVar2.h + (pVar2.j / 2.0f)) - ((f9 / 2.0f) + f8);
        float f12 = f10 * f;
        float f13 = (f4 * f2) / 2.0f;
        this.g = (int) ((f6 + f12) - f13);
        float f14 = f * f11;
        float f15 = (f5 * f3) / 2.0f;
        this.h = (int) ((f8 + f14) - f15);
        this.i = (int) (f7 + r7);
        this.j = (int) (f9 + r8);
        float f16 = Float.isNaN(iVar.n) ? 0.0f : iVar.n;
        this.r = 1;
        float f17 = (int) ((pVar.g + f12) - f13);
        this.g = f17;
        float f18 = (int) ((pVar.h + f14) - f15);
        this.h = f18;
        this.g = f17 + ((-f11) * f16);
        this.h = f18 + (f10 * f16);
        this.n = this.n;
        this.f948c = androidx.constraintlayout.core.j.a.c.c(iVar.h);
        this.m = iVar.i;
    }

    void q(int i, int i2, i iVar, p pVar, p pVar2) {
        float fMin;
        float f;
        float f2 = iVar.f926b / 100.0f;
        this.f950e = f2;
        this.f949d = iVar.j;
        this.r = iVar.q;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.i;
        float f6 = pVar.i;
        float f7 = pVar2.j;
        float f8 = pVar.j;
        this.f = this.f950e;
        this.i = (int) (f6 + ((f5 - f6) * f3));
        this.j = (int) (f8 + ((f7 - f8) * f4));
        int i3 = iVar.q;
        if (i3 == 1) {
            float f9 = Float.isNaN(iVar.m) ? f2 : iVar.m;
            float f10 = pVar2.g;
            float f11 = pVar.g;
            this.g = (f9 * (f10 - f11)) + f11;
            if (!Float.isNaN(iVar.n)) {
                f2 = iVar.n;
            }
            float f12 = pVar2.h;
            float f13 = pVar.h;
            this.h = (f2 * (f12 - f13)) + f13;
        } else if (i3 != 2) {
            float f14 = Float.isNaN(iVar.m) ? f2 : iVar.m;
            float f15 = pVar2.g;
            float f16 = pVar.g;
            this.g = (f14 * (f15 - f16)) + f16;
            if (!Float.isNaN(iVar.n)) {
                f2 = iVar.n;
            }
            float f17 = pVar2.h;
            float f18 = pVar.h;
            this.h = (f2 * (f17 - f18)) + f18;
        } else {
            if (Float.isNaN(iVar.m)) {
                float f19 = pVar2.g;
                float f20 = pVar.g;
                fMin = ((f19 - f20) * f2) + f20;
            } else {
                fMin = Math.min(f4, f3) * iVar.m;
            }
            this.g = fMin;
            if (Float.isNaN(iVar.n)) {
                float f21 = pVar2.h;
                float f22 = pVar.h;
                f = (f2 * (f21 - f22)) + f22;
            } else {
                f = iVar.n;
            }
            this.h = f;
        }
        this.n = pVar.n;
        this.f948c = androidx.constraintlayout.core.j.a.c.c(iVar.h);
        this.m = iVar.i;
    }

    void r(int i, int i2, i iVar, p pVar, p pVar2) {
        float f = iVar.f926b / 100.0f;
        this.f950e = f;
        this.f949d = iVar.j;
        float f2 = Float.isNaN(iVar.k) ? f : iVar.k;
        float f3 = Float.isNaN(iVar.l) ? f : iVar.l;
        float f4 = pVar2.i;
        float f5 = pVar.i;
        float f6 = pVar2.j;
        float f7 = pVar.j;
        this.f = this.f950e;
        float f8 = pVar.g;
        float f9 = pVar.h;
        float f10 = pVar2.g + (f4 / 2.0f);
        float f11 = pVar2.h + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.g = (int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f));
        float f13 = (f6 - f7) * f3;
        this.h = (int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f));
        this.i = (int) (f5 + f12);
        this.j = (int) (f7 + f13);
        this.r = 2;
        if (!Float.isNaN(iVar.m)) {
            this.g = (int) (iVar.m * ((int) (i - this.i)));
        }
        if (!Float.isNaN(iVar.n)) {
            this.h = (int) (iVar.n * ((int) (i2 - this.j)));
        }
        this.n = this.n;
        this.f948c = androidx.constraintlayout.core.j.a.c.c(iVar.h);
        this.m = iVar.i;
    }

    void s(float f, float f2, float f3, float f4) {
        this.g = f;
        this.h = f2;
        this.i = f3;
        this.j = f4;
    }

    void t(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f7 = (float) dArr[i];
            double d2 = dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f3 = f7;
            } else if (i2 == 2) {
                f5 = f7;
            } else if (i2 == 3) {
                f4 = f7;
            } else if (i2 == 4) {
                f6 = f7;
            }
        }
        float f8 = f3 - ((0.0f * f4) / 2.0f);
        float f9 = f5 - ((0.0f * f6) / 2.0f);
        fArr[0] = (f8 * (1.0f - f)) + (((f4 * 1.0f) + f8) * f) + 0.0f;
        fArr[1] = (f9 * (1.0f - f2)) + (((f6 * 1.0f) + f9) * f2) + 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void u(float f, View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3, boolean z) {
        float f2;
        boolean z2;
        boolean z3;
        float f3;
        float f4 = this.g;
        float f5 = this.h;
        float f6 = this.i;
        float f7 = this.j;
        if (iArr.length != 0 && this.t.length <= iArr[iArr.length - 1]) {
            int i = iArr[iArr.length - 1] + 1;
            this.t = new double[i];
            this.u = new double[i];
        }
        Arrays.fill(this.t, Double.NaN);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.t[iArr[i2]] = dArr[i2];
            this.u[iArr[i2]] = dArr2[i2];
        }
        float f8 = Float.NaN;
        int i3 = 0;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        while (true) {
            double[] dArr4 = this.t;
            if (i3 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i3]) && (dArr3 == null || dArr3[i3] == 0.0d)) {
                f3 = f8;
            } else {
                double d2 = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.t[i3])) {
                    d2 = this.t[i3] + d2;
                }
                f3 = f8;
                float f13 = (float) d2;
                float f14 = (float) this.u[i3];
                if (i3 == 1) {
                    f8 = f3;
                    f9 = f14;
                    f4 = f13;
                } else if (i3 == 2) {
                    f8 = f3;
                    f10 = f14;
                    f5 = f13;
                } else if (i3 == 3) {
                    f8 = f3;
                    f11 = f14;
                    f6 = f13;
                } else if (i3 == 4) {
                    f8 = f3;
                    f12 = f14;
                    f7 = f13;
                } else if (i3 == 5) {
                    f8 = f13;
                }
                i3++;
            }
            f8 = f3;
            i3++;
        }
        float f15 = f8;
        n nVar = this.p;
        if (nVar != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            nVar.i(f, fArr, fArr2);
            float f16 = fArr[0];
            float f17 = fArr[1];
            float f18 = fArr2[0];
            float f19 = fArr2[1];
            double d3 = f4;
            double d4 = f5;
            float fSin = (float) ((((double) f16) + (Math.sin(d4) * d3)) - ((double) (f6 / 2.0f)));
            f2 = f7;
            float fCos = (float) ((((double) f17) - (Math.cos(d4) * d3)) - ((double) (f7 / 2.0f)));
            double d5 = f9;
            double d6 = f10;
            float fSin2 = (float) (((double) f18) + (Math.sin(d4) * d5) + (Math.cos(d4) * d3 * d6));
            float fCos2 = (float) ((((double) f19) - (d5 * Math.cos(d4))) + (d3 * Math.sin(d4) * d6));
            if (dArr2.length >= 2) {
                z2 = false;
                dArr2[0] = fSin2;
                z3 = true;
                dArr2[1] = fCos2;
            } else {
                z2 = false;
                z3 = true;
            }
            if (!Float.isNaN(f15)) {
                view.setRotation((float) (((double) f15) + Math.toDegrees(Math.atan2(fCos2, fSin2))));
            }
            f4 = fSin;
            f5 = fCos;
        } else {
            f2 = f7;
            z2 = false;
            z3 = true;
            if (!Float.isNaN(f15)) {
                view.setRotation((float) (((double) 0.0f) + ((double) f15) + Math.toDegrees(Math.atan2(f10 + (f12 / 2.0f), f9 + (f11 / 2.0f)))));
            }
        }
        if (view instanceof d) {
            ((d) view).a(f4, f5, f6 + f4, f5 + f2);
            return;
        }
        float f20 = f4 + 0.5f;
        int i4 = (int) f20;
        float f21 = f5 + 0.5f;
        int i5 = (int) f21;
        int i6 = (int) (f20 + f6);
        int i7 = (int) (f21 + f2);
        int i8 = i6 - i4;
        int i9 = i7 - i5;
        if (i8 != view.getMeasuredWidth() || i9 != view.getMeasuredHeight()) {
            z2 = z3;
        }
        if (z2 || z) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i8, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
        view.layout(i4, i5, i6, i7);
    }

    public void v(n nVar, p pVar) {
        double d2 = ((this.g + (this.i / 2.0f)) - pVar.g) - (pVar.i / 2.0f);
        double d3 = ((this.h + (this.j / 2.0f)) - pVar.h) - (pVar.j / 2.0f);
        this.p = nVar;
        this.g = (float) Math.hypot(d3, d2);
        if (Float.isNaN(this.o)) {
            this.h = (float) (Math.atan2(d3, d2) + 1.5707963267948966d);
        } else {
            this.h = (float) Math.toRadians(this.o);
        }
    }

    public p(int i, int i2, i iVar, p pVar, p pVar2) {
        int i3 = e.f925a;
        this.m = i3;
        this.n = i3;
        this.o = Float.NaN;
        this.p = null;
        this.q = new LinkedHashMap<>();
        this.r = 0;
        this.t = new double[18];
        this.u = new double[18];
        if (pVar.n != e.f925a) {
            q(i, i2, iVar, pVar, pVar2);
            return;
        }
        int i4 = iVar.q;
        if (i4 == 1) {
            p(iVar, pVar, pVar2);
        } else if (i4 != 2) {
            o(iVar, pVar, pVar2);
        } else {
            r(i, i2, iVar, pVar, pVar2);
        }
    }
}
