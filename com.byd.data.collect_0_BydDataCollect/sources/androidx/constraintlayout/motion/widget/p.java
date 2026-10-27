package androidx.constraintlayout.motion.widget;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import androidx.constraintlayout.widget.a;
import androidx.constraintlayout.widget.c;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
class p implements Comparable<p> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String[] f901b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b.e.a.a.c f902c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f903d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f904e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f905f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f906g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float f907h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float f908i;
    float j;
    float k;
    float l;
    int m;
    LinkedHashMap<String, androidx.constraintlayout.widget.a> n;
    int o;
    double[] p;
    double[] q;

    public p() {
        this.f903d = 0;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = c.f832a;
        this.n = new LinkedHashMap<>();
        this.o = 0;
        this.p = new double[18];
        this.q = new double[18];
    }

    private boolean c(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    public void a(c.a aVar) {
        this.f902c = b.e.a.a.c.c(aVar.f1072c.f1088d);
        c.C0012c c0012c = aVar.f1072c;
        this.m = c0012c.f1089e;
        this.k = c0012c.f1092h;
        this.f903d = c0012c.f1090f;
        this.l = aVar.f1071b.f1097e;
        for (String str : aVar.f1075f.keySet()) {
            androidx.constraintlayout.widget.a aVar2 = aVar.f1075f.get(str);
            if (aVar2.c() != a.b.STRING_TYPE) {
                this.n.put(str, aVar2);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(p pVar) {
        return Float.compare(this.f905f, pVar.f905f);
    }

    void d(p pVar, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | c(this.f905f, pVar.f905f);
        zArr[1] = zArr[1] | c(this.f906g, pVar.f906g) | z;
        zArr[2] = z | c(this.f907h, pVar.f907h) | zArr[2];
        zArr[3] = zArr[3] | c(this.f908i, pVar.f908i);
        zArr[4] = c(this.j, pVar.j) | zArr[4];
    }

    void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.f905f, this.f906g, this.f907h, this.f908i, this.j, this.k};
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3] < 6) {
                dArr[i2] = fArr[iArr[i3]];
                i2++;
            }
        }
    }

    void f(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.f906g;
        float f3 = this.f907h;
        float f4 = this.f908i;
        float f5 = this.j;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f6 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f6;
            } else if (i4 == 2) {
                f3 = f6;
            } else if (i4 == 3) {
                f4 = f6;
            } else if (i4 == 4) {
                f5 = f6;
            }
        }
        fArr[i2] = f2 + (f4 / 2.0f) + 0.0f;
        fArr[i2 + 1] = f3 + (f5 / 2.0f) + 0.0f;
    }

    int g(String str, double[] dArr, int i2) {
        androidx.constraintlayout.widget.a aVar = this.n.get(str);
        if (aVar.f() == 1) {
            dArr[i2] = aVar.d();
            return 1;
        }
        int iF = aVar.f();
        aVar.e(new float[iF]);
        int i3 = 0;
        while (i3 < iF) {
            dArr[i2] = r1[i3];
            i3++;
            i2++;
        }
        return iF;
    }

    int h(String str) {
        return this.n.get(str).f();
    }

    void i(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.f906g;
        float f3 = this.f907h;
        float f4 = this.f908i;
        float f5 = this.j;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f6 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f6;
            } else if (i4 == 2) {
                f3 = f6;
            } else if (i4 == 3) {
                f4 = f6;
            } else if (i4 == 4) {
                f5 = f6;
            }
        }
        float f7 = f4 + f2;
        float f8 = f5 + f3;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i5 = i2 + 1;
        fArr[i2] = f2 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f3 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f7 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f3 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f7 + 0.0f;
        int i10 = i9 + 1;
        fArr[i9] = f8 + 0.0f;
        fArr[i10] = f2 + 0.0f;
        fArr[i10 + 1] = f8 + 0.0f;
    }

    boolean j(String str) {
        return this.n.containsKey(str);
    }

    void k(i iVar, p pVar, p pVar2) {
        float f2 = iVar.f833b / 100.0f;
        this.f904e = f2;
        this.f903d = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.f908i;
        float f6 = pVar.f908i;
        float f7 = pVar2.j;
        float f8 = pVar.j;
        this.f905f = this.f904e;
        float f9 = pVar.f906g;
        float f10 = pVar.f907h;
        float f11 = (pVar2.f906g + (f5 / 2.0f)) - ((f6 / 2.0f) + f9);
        float f12 = (pVar2.f907h + (f7 / 2.0f)) - (f10 + (f8 / 2.0f));
        float f13 = ((f5 - f6) * f3) / 2.0f;
        this.f906g = (int) ((f9 + (f11 * f2)) - f13);
        float f14 = ((f7 - f8) * f4) / 2.0f;
        this.f907h = (int) ((f10 + (f12 * f2)) - f14);
        this.f908i = (int) (f6 + r9);
        this.j = (int) (f8 + r12);
        float f15 = Float.isNaN(iVar.m) ? f2 : iVar.m;
        float f16 = Float.isNaN(iVar.p) ? 0.0f : iVar.p;
        if (!Float.isNaN(iVar.n)) {
            f2 = iVar.n;
        }
        float f17 = Float.isNaN(iVar.o) ? 0.0f : iVar.o;
        this.o = 2;
        this.f906g = (int) (((pVar.f906g + (f15 * f11)) + (f17 * f12)) - f13);
        this.f907h = (int) (((pVar.f907h + (f11 * f16)) + (f12 * f2)) - f14);
        this.f902c = b.e.a.a.c.c(iVar.f872h);
        this.m = iVar.f873i;
    }

    void l(i iVar, p pVar, p pVar2) {
        float f2 = iVar.f833b / 100.0f;
        this.f904e = f2;
        this.f903d = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.f908i - pVar.f908i;
        float f6 = pVar2.j - pVar.j;
        this.f905f = this.f904e;
        if (!Float.isNaN(iVar.m)) {
            f2 = iVar.m;
        }
        float f7 = pVar.f906g;
        float f8 = pVar.f908i;
        float f9 = pVar.f907h;
        float f10 = pVar.j;
        float f11 = (pVar2.f906g + (pVar2.f908i / 2.0f)) - ((f8 / 2.0f) + f7);
        float f12 = (pVar2.f907h + (pVar2.j / 2.0f)) - ((f10 / 2.0f) + f9);
        float f13 = f11 * f2;
        float f14 = (f5 * f3) / 2.0f;
        this.f906g = (int) ((f7 + f13) - f14);
        float f15 = f2 * f12;
        float f16 = (f6 * f4) / 2.0f;
        this.f907h = (int) ((f9 + f15) - f16);
        this.f908i = (int) (f8 + r7);
        this.j = (int) (f10 + r8);
        float f17 = Float.isNaN(iVar.n) ? 0.0f : iVar.n;
        this.o = 1;
        float f18 = (int) ((pVar.f906g + f13) - f14);
        this.f906g = f18;
        float f19 = (int) ((pVar.f907h + f15) - f16);
        this.f907h = f19;
        this.f906g = f18 + ((-f12) * f17);
        this.f907h = f19 + (f11 * f17);
        this.f902c = b.e.a.a.c.c(iVar.f872h);
        this.m = iVar.f873i;
    }

    void m(int i2, int i3, i iVar, p pVar, p pVar2) {
        float f2 = iVar.f833b / 100.0f;
        this.f904e = f2;
        this.f903d = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.f908i;
        float f6 = pVar.f908i;
        float f7 = pVar2.j;
        float f8 = pVar.j;
        this.f905f = this.f904e;
        float f9 = pVar.f906g;
        float f10 = pVar.f907h;
        float f11 = pVar2.f906g + (f5 / 2.0f);
        float f12 = pVar2.f907h + (f7 / 2.0f);
        float f13 = (f5 - f6) * f3;
        this.f906g = (int) ((f9 + ((f11 - ((f6 / 2.0f) + f9)) * f2)) - (f13 / 2.0f));
        float f14 = (f7 - f8) * f4;
        this.f907h = (int) ((f10 + ((f12 - (f10 + (f8 / 2.0f))) * f2)) - (f14 / 2.0f));
        this.f908i = (int) (f6 + f13);
        this.j = (int) (f8 + f14);
        this.o = 3;
        if (!Float.isNaN(iVar.m)) {
            this.f906g = (int) (iVar.m * ((int) (i2 - this.f908i)));
        }
        if (!Float.isNaN(iVar.n)) {
            this.f907h = (int) (iVar.n * ((int) (i3 - this.j)));
        }
        this.f902c = b.e.a.a.c.c(iVar.f872h);
        this.m = iVar.f873i;
    }

    void n(float f2, float f3, float f4, float f5) {
        this.f906g = f2;
        this.f907h = f3;
        this.f908i = f4;
        this.j = f5;
    }

    void o(float f2, float f3, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f8 = (float) dArr[i2];
            double d2 = dArr2[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f4 = f8;
            } else if (i3 == 2) {
                f6 = f8;
            } else if (i3 == 3) {
                f5 = f8;
            } else if (i3 == 4) {
                f7 = f8;
            }
        }
        float f9 = f4 - ((0.0f * f5) / 2.0f);
        float f10 = f6 - ((0.0f * f7) / 2.0f);
        fArr[0] = (f9 * (1.0f - f2)) + (((f5 * 1.0f) + f9) * f2) + 0.0f;
        fArr[1] = (f10 * (1.0f - f3)) + (((f7 * 1.0f) + f10) * f3) + 0.0f;
    }

    void p(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f2;
        boolean z;
        float f3 = this.f906g;
        float f4 = this.f907h;
        float f5 = this.f908i;
        float f6 = this.j;
        boolean z2 = true;
        if (iArr.length != 0 && this.p.length <= iArr[iArr.length - 1]) {
            int i2 = iArr[iArr.length - 1] + 1;
            this.p = new double[i2];
            this.q = new double[i2];
        }
        Arrays.fill(this.p, Double.NaN);
        for (int i3 = 0; i3 < iArr.length; i3++) {
            this.p[iArr[i3]] = dArr[i3];
            this.q[iArr[i3]] = dArr2[i3];
        }
        int i4 = 0;
        float f7 = Float.NaN;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        while (true) {
            double[] dArr4 = this.p;
            if (i4 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i4]) && (dArr3 == null || dArr3[i4] == 0.0d)) {
                f2 = f3;
                z = z2;
            } else {
                double d2 = dArr3 != null ? dArr3[i4] : 0.0d;
                if (!Double.isNaN(this.p[i4])) {
                    d2 = this.p[i4] + d2;
                }
                f2 = f3;
                float f12 = (float) d2;
                float f13 = (float) this.q[i4];
                z = true;
                if (i4 == 1) {
                    f8 = f13;
                    f3 = f12;
                } else if (i4 == 2) {
                    f4 = f12;
                    f10 = f13;
                } else if (i4 == 3) {
                    f5 = f12;
                    f9 = f13;
                } else if (i4 == 4) {
                    f6 = f12;
                    f11 = f13;
                } else if (i4 == 5) {
                    f3 = f2;
                    f7 = f12;
                }
                i4++;
                z2 = z;
            }
            f3 = f2;
            i4++;
            z2 = z;
        }
        float f14 = f3;
        boolean z3 = z2;
        if (!Float.isNaN(f7)) {
            view.setRotation((float) (((double) (Float.isNaN(Float.NaN) ? 0.0f : Float.NaN)) + ((double) f7) + Math.toDegrees(Math.atan2(f10 + (f11 / 2.0f), f8 + (f9 / 2.0f)))));
        } else if (!Float.isNaN(Float.NaN)) {
            view.setRotation(Float.NaN);
        }
        float f15 = f14 + 0.5f;
        int i5 = (int) f15;
        float f16 = f4 + 0.5f;
        int i6 = (int) f16;
        int i7 = (int) (f15 + f5);
        int i8 = (int) (f16 + f6);
        int i9 = i7 - i5;
        int i10 = i8 - i6;
        if ((i9 == view.getMeasuredWidth() && i10 == view.getMeasuredHeight()) ? false : z3) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
        view.layout(i5, i6, i7, i8);
    }

    public p(int i2, int i3, i iVar, p pVar, p pVar2) {
        this.f903d = 0;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = c.f832a;
        this.n = new LinkedHashMap<>();
        this.o = 0;
        this.p = new double[18];
        this.q = new double[18];
        int i4 = iVar.q;
        if (i4 == 1) {
            l(iVar, pVar, pVar2);
        } else if (i4 != 2) {
            k(iVar, pVar, pVar2);
        } else {
            m(i2, i3, iVar, pVar, pVar2);
        }
    }
}
