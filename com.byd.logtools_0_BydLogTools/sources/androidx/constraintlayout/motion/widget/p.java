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
    static String[] f850b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b.e.a.a.c f851c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f852d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f853e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f854f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f855g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float f856h;
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
        this.f852d = 0;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = c.f789a;
        this.n = new LinkedHashMap<>();
        this.o = 0;
        this.p = new double[18];
        this.q = new double[18];
    }

    private boolean c(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    public void a(b.a aVar) {
        this.f851c = b.e.a.a.c.c(aVar.f1141c.f1156d);
        b.c cVar = aVar.f1141c;
        this.m = cVar.f1157e;
        this.k = cVar.f1160h;
        this.f852d = cVar.f1158f;
        this.l = aVar.f1140b.f1165e;
        for (String str : aVar.f1144f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f1144f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.n.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(p pVar) {
        return Float.compare(this.f854f, pVar.f854f);
    }

    void d(p pVar, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | c(this.f854f, pVar.f854f);
        zArr[1] = zArr[1] | c(this.f855g, pVar.f855g) | z;
        zArr[2] = z | c(this.f856h, pVar.f856h) | zArr[2];
        zArr[3] = zArr[3] | c(this.i, pVar.i);
        zArr[4] = c(this.j, pVar.j) | zArr[4];
    }

    void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.f854f, this.f855g, this.f856h, this.i, this.j, this.k};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    void f(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f2 = this.f855g;
        float f3 = this.f856h;
        float f4 = this.i;
        float f5 = this.j;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f6 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f2 = f6;
            } else if (i3 == 2) {
                f3 = f6;
            } else if (i3 == 3) {
                f4 = f6;
            } else if (i3 == 4) {
                f5 = f6;
            }
        }
        fArr[i] = f2 + (f4 / 2.0f) + 0.0f;
        fArr[i + 1] = f3 + (f5 / 2.0f) + 0.0f;
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
        float f2 = this.f855g;
        float f3 = this.f856h;
        float f4 = this.i;
        float f5 = this.j;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f6 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f2 = f6;
            } else if (i3 == 2) {
                f3 = f6;
            } else if (i3 == 3) {
                f4 = f6;
            } else if (i3 == 4) {
                f5 = f6;
            }
        }
        float f7 = f4 + f2;
        float f8 = f5 + f3;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i4 = i + 1;
        fArr[i] = f2 + 0.0f;
        int i5 = i4 + 1;
        fArr[i4] = f3 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f7 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f3 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f7 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f8 + 0.0f;
        fArr[i9] = f2 + 0.0f;
        fArr[i9 + 1] = f8 + 0.0f;
    }

    boolean j(String str) {
        return this.n.containsKey(str);
    }

    void k(i iVar, p pVar, p pVar2) {
        float f2 = iVar.f790b / 100.0f;
        this.f853e = f2;
        this.f852d = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.i;
        float f6 = pVar.i;
        float f7 = pVar2.j;
        float f8 = pVar.j;
        this.f854f = this.f853e;
        float f9 = pVar.f855g;
        float f10 = pVar.f856h;
        float f11 = (pVar2.f855g + (f5 / 2.0f)) - ((f6 / 2.0f) + f9);
        float f12 = (pVar2.f856h + (f7 / 2.0f)) - (f10 + (f8 / 2.0f));
        float f13 = ((f5 - f6) * f3) / 2.0f;
        this.f855g = (int) ((f9 + (f11 * f2)) - f13);
        float f14 = ((f7 - f8) * f4) / 2.0f;
        this.f856h = (int) ((f10 + (f12 * f2)) - f14);
        this.i = (int) (f6 + r9);
        this.j = (int) (f8 + r12);
        float f15 = Float.isNaN(iVar.m) ? f2 : iVar.m;
        float f16 = Float.isNaN(iVar.p) ? 0.0f : iVar.p;
        if (!Float.isNaN(iVar.n)) {
            f2 = iVar.n;
        }
        float f17 = Float.isNaN(iVar.o) ? 0.0f : iVar.o;
        this.o = 2;
        this.f855g = (int) (((pVar.f855g + (f15 * f11)) + (f17 * f12)) - f13);
        this.f856h = (int) (((pVar.f856h + (f11 * f16)) + (f12 * f2)) - f14);
        this.f851c = b.e.a.a.c.c(iVar.f826h);
        this.m = iVar.i;
    }

    void l(i iVar, p pVar, p pVar2) {
        float f2 = iVar.f790b / 100.0f;
        this.f853e = f2;
        this.f852d = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.i - pVar.i;
        float f6 = pVar2.j - pVar.j;
        this.f854f = this.f853e;
        if (!Float.isNaN(iVar.m)) {
            f2 = iVar.m;
        }
        float f7 = pVar.f855g;
        float f8 = pVar.i;
        float f9 = pVar.f856h;
        float f10 = pVar.j;
        float f11 = (pVar2.f855g + (pVar2.i / 2.0f)) - ((f8 / 2.0f) + f7);
        float f12 = (pVar2.f856h + (pVar2.j / 2.0f)) - ((f10 / 2.0f) + f9);
        float f13 = f11 * f2;
        float f14 = (f5 * f3) / 2.0f;
        this.f855g = (int) ((f7 + f13) - f14);
        float f15 = f2 * f12;
        float f16 = (f6 * f4) / 2.0f;
        this.f856h = (int) ((f9 + f15) - f16);
        this.i = (int) (f8 + r7);
        this.j = (int) (f10 + r8);
        float f17 = Float.isNaN(iVar.n) ? 0.0f : iVar.n;
        this.o = 1;
        float f18 = (int) ((pVar.f855g + f13) - f14);
        this.f855g = f18;
        float f19 = (int) ((pVar.f856h + f15) - f16);
        this.f856h = f19;
        this.f855g = f18 + ((-f12) * f17);
        this.f856h = f19 + (f11 * f17);
        this.f851c = b.e.a.a.c.c(iVar.f826h);
        this.m = iVar.i;
    }

    void m(int i, int i2, i iVar, p pVar, p pVar2) {
        float f2 = iVar.f790b / 100.0f;
        this.f853e = f2;
        this.f852d = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.i;
        float f6 = pVar.i;
        float f7 = pVar2.j;
        float f8 = pVar.j;
        this.f854f = this.f853e;
        float f9 = pVar.f855g;
        float f10 = pVar.f856h;
        float f11 = pVar2.f855g + (f5 / 2.0f);
        float f12 = pVar2.f856h + (f7 / 2.0f);
        float f13 = (f5 - f6) * f3;
        this.f855g = (int) ((f9 + ((f11 - ((f6 / 2.0f) + f9)) * f2)) - (f13 / 2.0f));
        float f14 = (f7 - f8) * f4;
        this.f856h = (int) ((f10 + ((f12 - (f10 + (f8 / 2.0f))) * f2)) - (f14 / 2.0f));
        this.i = (int) (f6 + f13);
        this.j = (int) (f8 + f14);
        this.o = 3;
        if (!Float.isNaN(iVar.m)) {
            this.f855g = (int) (iVar.m * ((int) (i - this.i)));
        }
        if (!Float.isNaN(iVar.n)) {
            this.f856h = (int) (iVar.n * ((int) (i2 - this.j)));
        }
        this.f851c = b.e.a.a.c.c(iVar.f826h);
        this.m = iVar.i;
    }

    void n(float f2, float f3, float f4, float f5) {
        this.f855g = f2;
        this.f856h = f3;
        this.i = f4;
        this.j = f5;
    }

    void o(float f2, float f3, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f8 = (float) dArr[i];
            double d2 = dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f4 = f8;
            } else if (i2 == 2) {
                f6 = f8;
            } else if (i2 == 3) {
                f5 = f8;
            } else if (i2 == 4) {
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
        float f3 = this.f855g;
        float f4 = this.f856h;
        float f5 = this.i;
        float f6 = this.j;
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
        float f7 = Float.NaN;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        while (true) {
            double[] dArr4 = this.p;
            if (i3 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i3]) && (dArr3 == null || dArr3[i3] == 0.0d)) {
                f2 = f3;
                z = z2;
            } else {
                double d2 = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.p[i3])) {
                    d2 = this.p[i3] + d2;
                }
                f2 = f3;
                float f12 = (float) d2;
                float f13 = (float) this.q[i3];
                z = true;
                if (i3 == 1) {
                    f8 = f13;
                    f3 = f12;
                } else if (i3 == 2) {
                    f4 = f12;
                    f10 = f13;
                } else if (i3 == 3) {
                    f5 = f12;
                    f9 = f13;
                } else if (i3 == 4) {
                    f6 = f12;
                    f11 = f13;
                } else if (i3 == 5) {
                    f3 = f2;
                    f7 = f12;
                }
                i3++;
                z2 = z;
            }
            f3 = f2;
            i3++;
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
        int i4 = (int) f15;
        float f16 = f4 + 0.5f;
        int i5 = (int) f16;
        int i6 = (int) (f15 + f5);
        int i7 = (int) (f16 + f6);
        int i8 = i6 - i4;
        int i9 = i7 - i5;
        if ((i8 == view.getMeasuredWidth() && i9 == view.getMeasuredHeight()) ? false : z3) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i8, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
        view.layout(i4, i5, i6, i7);
    }

    public p(int i, int i2, i iVar, p pVar, p pVar2) {
        this.f852d = 0;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = c.f789a;
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
