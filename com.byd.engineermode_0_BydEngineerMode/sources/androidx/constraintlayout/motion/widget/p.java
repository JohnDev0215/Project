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
    static String[] f579b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b.e.a.a.c f580c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f581d;
    float e;
    float f;
    float g;
    float h;
    float i;
    float j;
    float k;
    float l;
    int m;
    LinkedHashMap<String, ConstraintAttribute> n;
    int o;
    double[] p;
    double[] q;

    public p() {
        this.f581d = 0;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = c.f547a;
        this.n = new LinkedHashMap<>();
        this.o = 0;
        this.p = new double[18];
        this.q = new double[18];
    }

    private boolean c(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void a(b.a aVar) {
        this.f580c = b.e.a.a.c.c(aVar.f748c.f757d);
        b.c cVar = aVar.f748c;
        this.m = cVar.e;
        this.k = cVar.h;
        this.f581d = cVar.f;
        this.l = aVar.f747b.e;
        for (String str : aVar.f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.n.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(p pVar) {
        return Float.compare(this.f, pVar.f);
    }

    void d(p pVar, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | c(this.f, pVar.f);
        zArr[1] = zArr[1] | c(this.g, pVar.g) | z;
        zArr[2] = z | c(this.h, pVar.h) | zArr[2];
        zArr[3] = zArr[3] | c(this.i, pVar.i);
        zArr[4] = c(this.j, pVar.j) | zArr[4];
    }

    void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.f, this.g, this.h, this.i, this.j, this.k};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    void f(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.g;
        float f2 = this.h;
        float f3 = this.i;
        float f4 = this.j;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        fArr[i] = f + (f3 / 2.0f) + 0.0f;
        fArr[i + 1] = f2 + (f4 / 2.0f) + 0.0f;
    }

    int g(String str, double[] dArr, int i) {
        ConstraintAttribute constraintAttribute = this.n.get(str);
        if (constraintAttribute.f() == 1) {
            dArr[i] = constraintAttribute.d();
            return 1;
        }
        int iF = constraintAttribute.f();
        constraintAttribute.e(new float[iF]);
        int i2 = 0;
        while (i2 < iF) {
            dArr[i] = r1[i2];
            i2++;
            i++;
        }
        return iF;
    }

    int h(String str) {
        return this.n.get(str).f();
    }

    void i(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.g;
        float f2 = this.h;
        float f3 = this.i;
        float f4 = this.j;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        float f6 = f3 + f;
        float f7 = f4 + f2;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i4 = i + 1;
        fArr[i] = f + 0.0f;
        int i5 = i4 + 1;
        fArr[i4] = f2 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f6 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f2 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f6 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f7 + 0.0f;
        fArr[i9] = f + 0.0f;
        fArr[i9 + 1] = f7 + 0.0f;
    }

    boolean j(String str) {
        return this.n.containsKey(str);
    }

    void k(i iVar, p pVar, p pVar2) {
        float f = iVar.f548b / 100.0f;
        this.e = f;
        this.f581d = iVar.j;
        float f2 = Float.isNaN(iVar.k) ? f : iVar.k;
        float f3 = Float.isNaN(iVar.l) ? f : iVar.l;
        float f4 = pVar2.i;
        float f5 = pVar.i;
        float f6 = pVar2.j;
        float f7 = pVar.j;
        this.f = this.e;
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
        this.o = 2;
        this.g = (int) (((pVar.g + (f14 * f10)) + (f16 * f11)) - f12);
        this.h = (int) (((pVar.h + (f10 * f15)) + (f11 * f)) - f13);
        this.f580c = b.e.a.a.c.c(iVar.h);
        this.m = iVar.i;
    }

    void l(i iVar, p pVar, p pVar2) {
        float f = iVar.f548b / 100.0f;
        this.e = f;
        this.f581d = iVar.j;
        float f2 = Float.isNaN(iVar.k) ? f : iVar.k;
        float f3 = Float.isNaN(iVar.l) ? f : iVar.l;
        float f4 = pVar2.i - pVar.i;
        float f5 = pVar2.j - pVar.j;
        this.f = this.e;
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
        this.o = 1;
        float f17 = (int) ((pVar.g + f12) - f13);
        this.g = f17;
        float f18 = (int) ((pVar.h + f14) - f15);
        this.h = f18;
        this.g = f17 + ((-f11) * f16);
        this.h = f18 + (f10 * f16);
        this.f580c = b.e.a.a.c.c(iVar.h);
        this.m = iVar.i;
    }

    void m(int i, int i2, i iVar, p pVar, p pVar2) {
        float f = iVar.f548b / 100.0f;
        this.e = f;
        this.f581d = iVar.j;
        float f2 = Float.isNaN(iVar.k) ? f : iVar.k;
        float f3 = Float.isNaN(iVar.l) ? f : iVar.l;
        float f4 = pVar2.i;
        float f5 = pVar.i;
        float f6 = pVar2.j;
        float f7 = pVar.j;
        this.f = this.e;
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
        this.o = 3;
        if (!Float.isNaN(iVar.m)) {
            this.g = (int) (iVar.m * ((int) (i - this.i)));
        }
        if (!Float.isNaN(iVar.n)) {
            this.h = (int) (iVar.n * ((int) (i2 - this.j)));
        }
        this.f580c = b.e.a.a.c.c(iVar.h);
        this.m = iVar.i;
    }

    void n(float f, float f2, float f3, float f4) {
        this.g = f;
        this.h = f2;
        this.i = f3;
        this.j = f4;
    }

    void o(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
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

    void p(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f;
        boolean z;
        float f2 = this.g;
        float f3 = this.h;
        float f4 = this.i;
        float f5 = this.j;
        boolean z2 = true;
        if (iArr.length != 0 && this.p.length <= iArr[iArr.length - 1]) {
            int i = iArr[iArr.length - 1] + 1;
            this.p = new double[i];
            this.q = new double[i];
        }
        Arrays.fill(this.p, Double.NaN);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.p[iArr[i2]] = dArr[i2];
            this.q[iArr[i2]] = dArr2[i2];
        }
        int i3 = 0;
        float f6 = Float.NaN;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        while (true) {
            double[] dArr4 = this.p;
            if (i3 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i3]) && (dArr3 == null || dArr3[i3] == 0.0d)) {
                f = f2;
                z = z2;
            } else {
                double d2 = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.p[i3])) {
                    d2 = this.p[i3] + d2;
                }
                f = f2;
                float f11 = (float) d2;
                float f12 = (float) this.q[i3];
                z = true;
                if (i3 == 1) {
                    f7 = f12;
                    f2 = f11;
                } else if (i3 == 2) {
                    f3 = f11;
                    f9 = f12;
                } else if (i3 == 3) {
                    f4 = f11;
                    f8 = f12;
                } else if (i3 == 4) {
                    f5 = f11;
                    f10 = f12;
                } else if (i3 == 5) {
                    f2 = f;
                    f6 = f11;
                }
                i3++;
                z2 = z;
            }
            f2 = f;
            i3++;
            z2 = z;
        }
        float f13 = f2;
        boolean z3 = z2;
        if (!Float.isNaN(f6)) {
            view.setRotation((float) (((double) (Float.isNaN(Float.NaN) ? 0.0f : Float.NaN)) + ((double) f6) + Math.toDegrees(Math.atan2(f9 + (f10 / 2.0f), f7 + (f8 / 2.0f)))));
        } else if (!Float.isNaN(Float.NaN)) {
            view.setRotation(Float.NaN);
        }
        float f14 = f13 + 0.5f;
        int i4 = (int) f14;
        float f15 = f3 + 0.5f;
        int i5 = (int) f15;
        int i6 = (int) (f14 + f4);
        int i7 = (int) (f15 + f5);
        int i8 = i6 - i4;
        int i9 = i7 - i5;
        if ((i8 == view.getMeasuredWidth() && i9 == view.getMeasuredHeight()) ? false : z3) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i8, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
        view.layout(i4, i5, i6, i7);
    }

    public p(int i, int i2, i iVar, p pVar, p pVar2) {
        this.f581d = 0;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = c.f547a;
        this.n = new LinkedHashMap<>();
        this.o = 0;
        this.p = new double[18];
        this.q = new double[18];
        int i3 = iVar.q;
        if (i3 == 1) {
            l(iVar, pVar, pVar2);
        } else if (i3 != 2) {
            k(iVar, pVar, pVar2);
        } else {
            m(i, i2, iVar, pVar, pVar2);
        }
    }
}
