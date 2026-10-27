package defpackage;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import defpackage.q6;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
public class f5 implements Comparable<f5> {
    public static String[] b = {"position", "x", "y", "width", "height", "pathRotate"};
    public k4 c;
    public int d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public float l;
    public int m;
    public LinkedHashMap<String, ConstraintAttribute> n;
    public int o;
    public double[] p;
    public double[] q;

    public f5() {
        this.d = 0;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = s4.a;
        this.n = new LinkedHashMap<>();
        this.o = 0;
        this.p = new double[18];
        this.q = new double[18];
    }

    public void a(q6.a aVar) {
        this.c = k4.c(aVar.c.d);
        q6.c cVar = aVar.c;
        this.m = cVar.e;
        this.k = cVar.h;
        this.d = cVar.f;
        this.l = aVar.b.e;
        for (String str : aVar.f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.n.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(f5 f5Var) {
        return Float.compare(this.f, f5Var.f);
    }

    public final boolean c(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void d(f5 f5Var, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | c(this.f, f5Var.f);
        zArr[1] = zArr[1] | c(this.g, f5Var.g) | z;
        zArr[2] = z | c(this.h, f5Var.h) | zArr[2];
        zArr[3] = zArr[3] | c(this.i, f5Var.i);
        zArr[4] = c(this.j, f5Var.j) | zArr[4];
    }

    public void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.f, this.g, this.h, this.i, this.j, this.k};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    public void f(int[] iArr, double[] dArr, float[] fArr, int i) {
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

    public int g(String str, double[] dArr, int i) {
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

    public int h(String str) {
        return this.n.get(str).f();
    }

    public void i(int[] iArr, double[] dArr, float[] fArr, int i) {
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

    public boolean j(String str) {
        return this.n.containsKey(str);
    }

    public void k(y4 y4Var, f5 f5Var, f5 f5Var2) {
        float f = y4Var.b / 100.0f;
        this.e = f;
        this.d = y4Var.j;
        float f2 = Float.isNaN(y4Var.k) ? f : y4Var.k;
        float f3 = Float.isNaN(y4Var.l) ? f : y4Var.l;
        float f4 = f5Var2.i;
        float f5 = f5Var.i;
        float f6 = f5Var2.j;
        float f7 = f5Var.j;
        this.f = this.e;
        float f8 = f5Var.g;
        float f9 = f5Var.h;
        float f10 = (f5Var2.g + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (f5Var2.h + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = ((f4 - f5) * f2) / 2.0f;
        this.g = (int) ((f8 + (f10 * f)) - f12);
        float f13 = ((f6 - f7) * f3) / 2.0f;
        this.h = (int) ((f9 + (f11 * f)) - f13);
        this.i = (int) (f5 + r9);
        this.j = (int) (f7 + r12);
        float f14 = Float.isNaN(y4Var.m) ? f : y4Var.m;
        float f15 = Float.isNaN(y4Var.p) ? 0.0f : y4Var.p;
        if (!Float.isNaN(y4Var.n)) {
            f = y4Var.n;
        }
        float f16 = Float.isNaN(y4Var.o) ? 0.0f : y4Var.o;
        this.o = 2;
        this.g = (int) (((f5Var.g + (f14 * f10)) + (f16 * f11)) - f12);
        this.h = (int) (((f5Var.h + (f10 * f15)) + (f11 * f)) - f13);
        this.c = k4.c(y4Var.h);
        this.m = y4Var.i;
    }

    public void l(y4 y4Var, f5 f5Var, f5 f5Var2) {
        float f = y4Var.b / 100.0f;
        this.e = f;
        this.d = y4Var.j;
        float f2 = Float.isNaN(y4Var.k) ? f : y4Var.k;
        float f3 = Float.isNaN(y4Var.l) ? f : y4Var.l;
        float f4 = f5Var2.i - f5Var.i;
        float f5 = f5Var2.j - f5Var.j;
        this.f = this.e;
        if (!Float.isNaN(y4Var.m)) {
            f = y4Var.m;
        }
        float f6 = f5Var.g;
        float f7 = f5Var.i;
        float f8 = f5Var.h;
        float f9 = f5Var.j;
        float f10 = (f5Var2.g + (f5Var2.i / 2.0f)) - ((f7 / 2.0f) + f6);
        float f11 = (f5Var2.h + (f5Var2.j / 2.0f)) - ((f9 / 2.0f) + f8);
        float f12 = f10 * f;
        float f13 = (f4 * f2) / 2.0f;
        this.g = (int) ((f6 + f12) - f13);
        float f14 = f * f11;
        float f15 = (f5 * f3) / 2.0f;
        this.h = (int) ((f8 + f14) - f15);
        this.i = (int) (f7 + r7);
        this.j = (int) (f9 + r8);
        float f16 = Float.isNaN(y4Var.n) ? 0.0f : y4Var.n;
        this.o = 1;
        float f17 = (int) ((f5Var.g + f12) - f13);
        this.g = f17;
        float f18 = (int) ((f5Var.h + f14) - f15);
        this.h = f18;
        this.g = f17 + ((-f11) * f16);
        this.h = f18 + (f10 * f16);
        this.c = k4.c(y4Var.h);
        this.m = y4Var.i;
    }

    public void m(int i, int i2, y4 y4Var, f5 f5Var, f5 f5Var2) {
        float f = y4Var.b / 100.0f;
        this.e = f;
        this.d = y4Var.j;
        float f2 = Float.isNaN(y4Var.k) ? f : y4Var.k;
        float f3 = Float.isNaN(y4Var.l) ? f : y4Var.l;
        float f4 = f5Var2.i;
        float f5 = f5Var.i;
        float f6 = f5Var2.j;
        float f7 = f5Var.j;
        this.f = this.e;
        float f8 = f5Var.g;
        float f9 = f5Var.h;
        float f10 = f5Var2.g + (f4 / 2.0f);
        float f11 = f5Var2.h + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.g = (int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f));
        float f13 = (f6 - f7) * f3;
        this.h = (int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f));
        this.i = (int) (f5 + f12);
        this.j = (int) (f7 + f13);
        this.o = 3;
        if (!Float.isNaN(y4Var.m)) {
            this.g = (int) (y4Var.m * ((int) (i - this.i)));
        }
        if (!Float.isNaN(y4Var.n)) {
            this.h = (int) (y4Var.n * ((int) (i2 - this.j)));
        }
        this.c = k4.c(y4Var.h);
        this.m = y4Var.i;
    }

    public void n(float f, float f2, float f3, float f4) {
        this.g = f;
        this.h = f2;
        this.i = f3;
        this.j = f4;
    }

    public void o(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f7 = (float) dArr[i];
            double d = dArr2[i];
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

    public void p(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
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
                double d = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.p[i3])) {
                    d = this.p[i3] + d;
                }
                f = f2;
                float f11 = (float) d;
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

    public f5(int i, int i2, y4 y4Var, f5 f5Var, f5 f5Var2) {
        this.d = 0;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = s4.a;
        this.n = new LinkedHashMap<>();
        this.o = 0;
        this.p = new double[18];
        this.q = new double[18];
        int i3 = y4Var.q;
        if (i3 == 1) {
            l(y4Var, f5Var, f5Var2);
        } else if (i3 != 2) {
            k(y4Var, f5Var, f5Var2);
        } else {
            m(i, i2, y4Var, f5Var, f5Var2);
        }
    }
}
