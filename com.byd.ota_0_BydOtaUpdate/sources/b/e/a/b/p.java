package b.e.a.b;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b.e.c.b;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
public class p implements Comparable<p> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String[] f1303c = {"position", "x", "y", "width", "height", "pathRotate"};
    public float E3;
    public float F3;
    public float G3;
    public float H3;
    public float I3;
    public float J3;
    public int K3;
    public LinkedHashMap<String, ConstraintAttribute> L3;
    public int M3;
    public double[] N3;
    public double[] O3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.e.a.a.c f1304d;
    public int s;
    public float x;
    public float y;

    public p() {
        this.s = 0;
        this.I3 = Float.NaN;
        this.J3 = Float.NaN;
        this.K3 = c.f1240a;
        this.L3 = new LinkedHashMap<>();
        this.M3 = 0;
        this.N3 = new double[18];
        this.O3 = new double[18];
    }

    public void a(b.a aVar) {
        this.f1304d = b.e.a.a.c.c(aVar.f1478c.f1494d);
        b.c cVar = aVar.f1478c;
        this.K3 = cVar.f1495e;
        this.I3 = cVar.f1498h;
        this.s = cVar.f1496f;
        this.J3 = aVar.f1477b.f1503e;
        for (String str : aVar.f1481f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f1481f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.L3.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(p pVar) {
        return Float.compare(this.y, pVar.y);
    }

    public final boolean c(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    public void d(p pVar, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | c(this.y, pVar.y);
        zArr[1] = zArr[1] | c(this.E3, pVar.E3) | z;
        zArr[2] = z | c(this.F3, pVar.F3) | zArr[2];
        zArr[3] = zArr[3] | c(this.G3, pVar.G3);
        zArr[4] = c(this.H3, pVar.H3) | zArr[4];
    }

    public void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.y, this.E3, this.F3, this.G3, this.H3, this.I3};
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3] < 6) {
                dArr[i2] = fArr[iArr[i3]];
                i2++;
            }
        }
    }

    public void f(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.E3;
        float f3 = this.F3;
        float f4 = this.G3;
        float f5 = this.H3;
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

    public int g(String str, double[] dArr, int i2) {
        ConstraintAttribute constraintAttribute = this.L3.get(str);
        if (constraintAttribute.f() == 1) {
            dArr[i2] = constraintAttribute.d();
            return 1;
        }
        int iF = constraintAttribute.f();
        constraintAttribute.e(new float[iF]);
        int i3 = 0;
        while (i3 < iF) {
            dArr[i2] = r1[i3];
            i3++;
            i2++;
        }
        return iF;
    }

    public int h(String str) {
        return this.L3.get(str).f();
    }

    public void i(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.E3;
        float f3 = this.F3;
        float f4 = this.G3;
        float f5 = this.H3;
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

    public boolean j(String str) {
        return this.L3.containsKey(str);
    }

    public void k(i iVar, p pVar, p pVar2) {
        float f2 = iVar.f1241b / 100.0f;
        this.x = f2;
        this.s = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.G3;
        float f6 = pVar.G3;
        float f7 = pVar2.H3;
        float f8 = pVar.H3;
        this.y = this.x;
        float f9 = pVar.E3;
        float f10 = pVar.F3;
        float f11 = (pVar2.E3 + (f5 / 2.0f)) - ((f6 / 2.0f) + f9);
        float f12 = (pVar2.F3 + (f7 / 2.0f)) - (f10 + (f8 / 2.0f));
        float f13 = ((f5 - f6) * f3) / 2.0f;
        this.E3 = (int) ((f9 + (f11 * f2)) - f13);
        float f14 = ((f7 - f8) * f4) / 2.0f;
        this.F3 = (int) ((f10 + (f12 * f2)) - f14);
        this.G3 = (int) (f6 + r9);
        this.H3 = (int) (f8 + r12);
        float f15 = Float.isNaN(iVar.m) ? f2 : iVar.m;
        float f16 = Float.isNaN(iVar.p) ? 0.0f : iVar.p;
        if (!Float.isNaN(iVar.n)) {
            f2 = iVar.n;
        }
        float f17 = Float.isNaN(iVar.o) ? 0.0f : iVar.o;
        this.M3 = 2;
        this.E3 = (int) (((pVar.E3 + (f15 * f11)) + (f17 * f12)) - f13);
        this.F3 = (int) (((pVar.F3 + (f11 * f16)) + (f12 * f2)) - f14);
        this.f1304d = b.e.a.a.c.c(iVar.f1280h);
        this.K3 = iVar.f1281i;
    }

    public void l(i iVar, p pVar, p pVar2) {
        float f2 = iVar.f1241b / 100.0f;
        this.x = f2;
        this.s = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.G3 - pVar.G3;
        float f6 = pVar2.H3 - pVar.H3;
        this.y = this.x;
        if (!Float.isNaN(iVar.m)) {
            f2 = iVar.m;
        }
        float f7 = pVar.E3;
        float f8 = pVar.G3;
        float f9 = pVar.F3;
        float f10 = pVar.H3;
        float f11 = (pVar2.E3 + (pVar2.G3 / 2.0f)) - ((f8 / 2.0f) + f7);
        float f12 = (pVar2.F3 + (pVar2.H3 / 2.0f)) - ((f10 / 2.0f) + f9);
        float f13 = f11 * f2;
        float f14 = (f5 * f3) / 2.0f;
        this.E3 = (int) ((f7 + f13) - f14);
        float f15 = f2 * f12;
        float f16 = (f6 * f4) / 2.0f;
        this.F3 = (int) ((f9 + f15) - f16);
        this.G3 = (int) (f8 + r7);
        this.H3 = (int) (f10 + r8);
        float f17 = Float.isNaN(iVar.n) ? 0.0f : iVar.n;
        this.M3 = 1;
        float f18 = (int) ((pVar.E3 + f13) - f14);
        this.E3 = f18;
        float f19 = (int) ((pVar.F3 + f15) - f16);
        this.F3 = f19;
        this.E3 = f18 + ((-f12) * f17);
        this.F3 = f19 + (f11 * f17);
        this.f1304d = b.e.a.a.c.c(iVar.f1280h);
        this.K3 = iVar.f1281i;
    }

    public void m(int i2, int i3, i iVar, p pVar, p pVar2) {
        float f2 = iVar.f1241b / 100.0f;
        this.x = f2;
        this.s = iVar.j;
        float f3 = Float.isNaN(iVar.k) ? f2 : iVar.k;
        float f4 = Float.isNaN(iVar.l) ? f2 : iVar.l;
        float f5 = pVar2.G3;
        float f6 = pVar.G3;
        float f7 = pVar2.H3;
        float f8 = pVar.H3;
        this.y = this.x;
        float f9 = pVar.E3;
        float f10 = pVar.F3;
        float f11 = pVar2.E3 + (f5 / 2.0f);
        float f12 = pVar2.F3 + (f7 / 2.0f);
        float f13 = (f5 - f6) * f3;
        this.E3 = (int) ((f9 + ((f11 - ((f6 / 2.0f) + f9)) * f2)) - (f13 / 2.0f));
        float f14 = (f7 - f8) * f4;
        this.F3 = (int) ((f10 + ((f12 - (f10 + (f8 / 2.0f))) * f2)) - (f14 / 2.0f));
        this.G3 = (int) (f6 + f13);
        this.H3 = (int) (f8 + f14);
        this.M3 = 3;
        if (!Float.isNaN(iVar.m)) {
            this.E3 = (int) (iVar.m * ((int) (i2 - this.G3)));
        }
        if (!Float.isNaN(iVar.n)) {
            this.F3 = (int) (iVar.n * ((int) (i3 - this.H3)));
        }
        this.f1304d = b.e.a.a.c.c(iVar.f1280h);
        this.K3 = iVar.f1281i;
    }

    public void n(float f2, float f3, float f4, float f5) {
        this.E3 = f2;
        this.F3 = f3;
        this.G3 = f4;
        this.H3 = f5;
    }

    public void o(float f2, float f3, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
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

    public void p(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f2;
        boolean z;
        float f3 = this.E3;
        float f4 = this.F3;
        float f5 = this.G3;
        float f6 = this.H3;
        boolean z2 = true;
        if (iArr.length != 0 && this.N3.length <= iArr[iArr.length - 1]) {
            int i2 = iArr[iArr.length - 1] + 1;
            this.N3 = new double[i2];
            this.O3 = new double[i2];
        }
        Arrays.fill(this.N3, Double.NaN);
        for (int i3 = 0; i3 < iArr.length; i3++) {
            this.N3[iArr[i3]] = dArr[i3];
            this.O3[iArr[i3]] = dArr2[i3];
        }
        int i4 = 0;
        float f7 = Float.NaN;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        while (true) {
            double[] dArr4 = this.N3;
            if (i4 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i4]) && (dArr3 == null || dArr3[i4] == 0.0d)) {
                f2 = f3;
                z = z2;
            } else {
                double d2 = dArr3 != null ? dArr3[i4] : 0.0d;
                if (!Double.isNaN(this.N3[i4])) {
                    d2 = this.N3[i4] + d2;
                }
                f2 = f3;
                float f12 = (float) d2;
                float f13 = (float) this.O3[i4];
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
        this.s = 0;
        this.I3 = Float.NaN;
        this.J3 = Float.NaN;
        this.K3 = c.f1240a;
        this.L3 = new LinkedHashMap<>();
        this.M3 = 0;
        this.N3 = new double[18];
        this.O3 = new double[18];
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
