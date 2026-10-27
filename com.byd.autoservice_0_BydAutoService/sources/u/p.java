package u;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.Arrays;
import java.util.LinkedHashMap;
import y.b;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
public class p implements Comparable<p> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String[] f5227b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t.c f5228c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5229d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f5230e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f5231f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f5232g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f5233h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f5234i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f5235j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5236k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f5237l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5238m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public LinkedHashMap<String, ConstraintAttribute> f5239n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5240o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public double[] f5241p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public double[] f5242q;

    public p() {
        this.f5229d = 0;
        this.f5236k = Float.NaN;
        this.f5237l = Float.NaN;
        this.f5238m = c.f5050a;
        this.f5239n = new LinkedHashMap<>();
        this.f5240o = 0;
        this.f5241p = new double[18];
        this.f5242q = new double[18];
    }

    public void a(b.a aVar) {
        this.f5228c = t.c.c(aVar.f5768c.f5812d);
        b.c cVar = aVar.f5768c;
        this.f5238m = cVar.f5813e;
        this.f5236k = cVar.f5816h;
        this.f5229d = cVar.f5814f;
        this.f5237l = aVar.f5767b.f5821e;
        for (String str : aVar.f5771f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f5771f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.f5239n.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(p pVar) {
        return Float.compare(this.f5231f, pVar.f5231f);
    }

    public final boolean c(float f3, float f4) {
        return (Float.isNaN(f3) || Float.isNaN(f4)) ? Float.isNaN(f3) != Float.isNaN(f4) : Math.abs(f3 - f4) > 1.0E-6f;
    }

    public void d(p pVar, boolean[] zArr, String[] strArr, boolean z3) {
        zArr[0] = zArr[0] | c(this.f5231f, pVar.f5231f);
        zArr[1] = zArr[1] | c(this.f5232g, pVar.f5232g) | z3;
        zArr[2] = z3 | c(this.f5233h, pVar.f5233h) | zArr[2];
        zArr[3] = zArr[3] | c(this.f5234i, pVar.f5234i);
        zArr[4] = c(this.f5235j, pVar.f5235j) | zArr[4];
    }

    public void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.f5231f, this.f5232g, this.f5233h, this.f5234i, this.f5235j, this.f5236k};
        int i3 = 0;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            if (iArr[i4] < 6) {
                dArr[i3] = fArr[iArr[i4]];
                i3++;
            }
        }
    }

    public void f(int[] iArr, double[] dArr, float[] fArr, int i3) {
        float f3 = this.f5232g;
        float f4 = this.f5233h;
        float f5 = this.f5234i;
        float f6 = this.f5235j;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            float f7 = (float) dArr[i4];
            int i5 = iArr[i4];
            if (i5 == 1) {
                f3 = f7;
            } else if (i5 == 2) {
                f4 = f7;
            } else if (i5 == 3) {
                f5 = f7;
            } else if (i5 == 4) {
                f6 = f7;
            }
        }
        fArr[i3] = f3 + (f5 / 2.0f) + 0.0f;
        fArr[i3 + 1] = f4 + (f6 / 2.0f) + 0.0f;
    }

    public int g(String str, double[] dArr, int i3) {
        ConstraintAttribute constraintAttribute = this.f5239n.get(str);
        if (constraintAttribute.f() == 1) {
            dArr[i3] = constraintAttribute.d();
            return 1;
        }
        int iF = constraintAttribute.f();
        constraintAttribute.e(new float[iF]);
        int i4 = 0;
        while (i4 < iF) {
            dArr[i3] = r1[i4];
            i4++;
            i3++;
        }
        return iF;
    }

    public int h(String str) {
        return this.f5239n.get(str).f();
    }

    public void i(int[] iArr, double[] dArr, float[] fArr, int i3) {
        float f3 = this.f5232g;
        float f4 = this.f5233h;
        float f5 = this.f5234i;
        float f6 = this.f5235j;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            float f7 = (float) dArr[i4];
            int i5 = iArr[i4];
            if (i5 == 1) {
                f3 = f7;
            } else if (i5 == 2) {
                f4 = f7;
            } else if (i5 == 3) {
                f5 = f7;
            } else if (i5 == 4) {
                f6 = f7;
            }
        }
        float f8 = f5 + f3;
        float f9 = f6 + f4;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i6 = i3 + 1;
        fArr[i3] = f3 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f4 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f8 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f4 + 0.0f;
        int i10 = i9 + 1;
        fArr[i9] = f8 + 0.0f;
        int i11 = i10 + 1;
        fArr[i10] = f9 + 0.0f;
        fArr[i11] = f3 + 0.0f;
        fArr[i11 + 1] = f9 + 0.0f;
    }

    public boolean j(String str) {
        return this.f5239n.containsKey(str);
    }

    public void k(i iVar, p pVar, p pVar2) {
        float f3 = iVar.f5051b / 100.0f;
        this.f5230e = f3;
        this.f5229d = iVar.f5125j;
        float f4 = Float.isNaN(iVar.f5126k) ? f3 : iVar.f5126k;
        float f5 = Float.isNaN(iVar.f5127l) ? f3 : iVar.f5127l;
        float f6 = pVar2.f5234i;
        float f7 = pVar.f5234i;
        float f8 = pVar2.f5235j;
        float f9 = pVar.f5235j;
        this.f5231f = this.f5230e;
        float f10 = pVar.f5232g;
        float f11 = pVar.f5233h;
        float f12 = (pVar2.f5232g + (f6 / 2.0f)) - ((f7 / 2.0f) + f10);
        float f13 = (pVar2.f5233h + (f8 / 2.0f)) - (f11 + (f9 / 2.0f));
        float f14 = ((f6 - f7) * f4) / 2.0f;
        this.f5232g = (int) ((f10 + (f12 * f3)) - f14);
        float f15 = ((f8 - f9) * f5) / 2.0f;
        this.f5233h = (int) ((f11 + (f13 * f3)) - f15);
        this.f5234i = (int) (f7 + r9);
        this.f5235j = (int) (f9 + r12);
        float f16 = Float.isNaN(iVar.f5128m) ? f3 : iVar.f5128m;
        float f17 = Float.isNaN(iVar.f5131p) ? 0.0f : iVar.f5131p;
        if (!Float.isNaN(iVar.f5129n)) {
            f3 = iVar.f5129n;
        }
        float f18 = Float.isNaN(iVar.f5130o) ? 0.0f : iVar.f5130o;
        this.f5240o = 2;
        this.f5232g = (int) (((pVar.f5232g + (f16 * f12)) + (f18 * f13)) - f14);
        this.f5233h = (int) (((pVar.f5233h + (f12 * f17)) + (f13 * f3)) - f15);
        this.f5228c = t.c.c(iVar.f5123h);
        this.f5238m = iVar.f5124i;
    }

    public void l(i iVar, p pVar, p pVar2) {
        float f3 = iVar.f5051b / 100.0f;
        this.f5230e = f3;
        this.f5229d = iVar.f5125j;
        float f4 = Float.isNaN(iVar.f5126k) ? f3 : iVar.f5126k;
        float f5 = Float.isNaN(iVar.f5127l) ? f3 : iVar.f5127l;
        float f6 = pVar2.f5234i - pVar.f5234i;
        float f7 = pVar2.f5235j - pVar.f5235j;
        this.f5231f = this.f5230e;
        if (!Float.isNaN(iVar.f5128m)) {
            f3 = iVar.f5128m;
        }
        float f8 = pVar.f5232g;
        float f9 = pVar.f5234i;
        float f10 = pVar.f5233h;
        float f11 = pVar.f5235j;
        float f12 = (pVar2.f5232g + (pVar2.f5234i / 2.0f)) - ((f9 / 2.0f) + f8);
        float f13 = (pVar2.f5233h + (pVar2.f5235j / 2.0f)) - ((f11 / 2.0f) + f10);
        float f14 = f12 * f3;
        float f15 = (f6 * f4) / 2.0f;
        this.f5232g = (int) ((f8 + f14) - f15);
        float f16 = f3 * f13;
        float f17 = (f7 * f5) / 2.0f;
        this.f5233h = (int) ((f10 + f16) - f17);
        this.f5234i = (int) (f9 + r7);
        this.f5235j = (int) (f11 + r8);
        float f18 = Float.isNaN(iVar.f5129n) ? 0.0f : iVar.f5129n;
        this.f5240o = 1;
        float f19 = (int) ((pVar.f5232g + f14) - f15);
        this.f5232g = f19;
        float f20 = (int) ((pVar.f5233h + f16) - f17);
        this.f5233h = f20;
        this.f5232g = f19 + ((-f13) * f18);
        this.f5233h = f20 + (f12 * f18);
        this.f5228c = t.c.c(iVar.f5123h);
        this.f5238m = iVar.f5124i;
    }

    public void m(int i3, int i4, i iVar, p pVar, p pVar2) {
        float f3 = iVar.f5051b / 100.0f;
        this.f5230e = f3;
        this.f5229d = iVar.f5125j;
        float f4 = Float.isNaN(iVar.f5126k) ? f3 : iVar.f5126k;
        float f5 = Float.isNaN(iVar.f5127l) ? f3 : iVar.f5127l;
        float f6 = pVar2.f5234i;
        float f7 = pVar.f5234i;
        float f8 = pVar2.f5235j;
        float f9 = pVar.f5235j;
        this.f5231f = this.f5230e;
        float f10 = pVar.f5232g;
        float f11 = pVar.f5233h;
        float f12 = pVar2.f5232g + (f6 / 2.0f);
        float f13 = pVar2.f5233h + (f8 / 2.0f);
        float f14 = (f6 - f7) * f4;
        this.f5232g = (int) ((f10 + ((f12 - ((f7 / 2.0f) + f10)) * f3)) - (f14 / 2.0f));
        float f15 = (f8 - f9) * f5;
        this.f5233h = (int) ((f11 + ((f13 - (f11 + (f9 / 2.0f))) * f3)) - (f15 / 2.0f));
        this.f5234i = (int) (f7 + f14);
        this.f5235j = (int) (f9 + f15);
        this.f5240o = 3;
        if (!Float.isNaN(iVar.f5128m)) {
            this.f5232g = (int) (iVar.f5128m * ((int) (i3 - this.f5234i)));
        }
        if (!Float.isNaN(iVar.f5129n)) {
            this.f5233h = (int) (iVar.f5129n * ((int) (i4 - this.f5235j)));
        }
        this.f5228c = t.c.c(iVar.f5123h);
        this.f5238m = iVar.f5124i;
    }

    public void n(float f3, float f4, float f5, float f6) {
        this.f5232g = f3;
        this.f5233h = f4;
        this.f5234i = f5;
        this.f5235j = f6;
    }

    public void o(float f3, float f4, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f9 = (float) dArr[i3];
            double d3 = dArr2[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f5 = f9;
            } else if (i4 == 2) {
                f7 = f9;
            } else if (i4 == 3) {
                f6 = f9;
            } else if (i4 == 4) {
                f8 = f9;
            }
        }
        float f10 = f5 - ((0.0f * f6) / 2.0f);
        float f11 = f7 - ((0.0f * f8) / 2.0f);
        fArr[0] = (f10 * (1.0f - f3)) + (((f6 * 1.0f) + f10) * f3) + 0.0f;
        fArr[1] = (f11 * (1.0f - f4)) + (((f8 * 1.0f) + f11) * f4) + 0.0f;
    }

    public void p(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f3;
        boolean z3;
        float f4 = this.f5232g;
        float f5 = this.f5233h;
        float f6 = this.f5234i;
        float f7 = this.f5235j;
        boolean z4 = true;
        if (iArr.length != 0 && this.f5241p.length <= iArr[iArr.length - 1]) {
            int i3 = iArr[iArr.length - 1] + 1;
            this.f5241p = new double[i3];
            this.f5242q = new double[i3];
        }
        Arrays.fill(this.f5241p, Double.NaN);
        for (int i4 = 0; i4 < iArr.length; i4++) {
            this.f5241p[iArr[i4]] = dArr[i4];
            this.f5242q[iArr[i4]] = dArr2[i4];
        }
        int i5 = 0;
        float f8 = Float.NaN;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        while (true) {
            double[] dArr4 = this.f5241p;
            if (i5 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i5]) && (dArr3 == null || dArr3[i5] == 0.0d)) {
                f3 = f4;
                z3 = z4;
            } else {
                double d3 = dArr3 != null ? dArr3[i5] : 0.0d;
                if (!Double.isNaN(this.f5241p[i5])) {
                    d3 = this.f5241p[i5] + d3;
                }
                f3 = f4;
                float f13 = (float) d3;
                float f14 = (float) this.f5242q[i5];
                z3 = true;
                if (i5 == 1) {
                    f9 = f14;
                    f4 = f13;
                } else if (i5 == 2) {
                    f5 = f13;
                    f11 = f14;
                } else if (i5 == 3) {
                    f6 = f13;
                    f10 = f14;
                } else if (i5 == 4) {
                    f7 = f13;
                    f12 = f14;
                } else if (i5 == 5) {
                    f4 = f3;
                    f8 = f13;
                }
                i5++;
                z4 = z3;
            }
            f4 = f3;
            i5++;
            z4 = z3;
        }
        float f15 = f4;
        boolean z5 = z4;
        if (!Float.isNaN(f8)) {
            view.setRotation((float) (((double) (Float.isNaN(Float.NaN) ? 0.0f : Float.NaN)) + ((double) f8) + Math.toDegrees(Math.atan2(f11 + (f12 / 2.0f), f9 + (f10 / 2.0f)))));
        } else if (!Float.isNaN(Float.NaN)) {
            view.setRotation(Float.NaN);
        }
        float f16 = f15 + 0.5f;
        int i6 = (int) f16;
        float f17 = f5 + 0.5f;
        int i7 = (int) f17;
        int i8 = (int) (f16 + f6);
        int i9 = (int) (f17 + f7);
        int i10 = i8 - i6;
        int i11 = i9 - i7;
        if ((i10 == view.getMeasuredWidth() && i11 == view.getMeasuredHeight()) ? false : z5) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i11, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
        view.layout(i6, i7, i8, i9);
    }

    public p(int i3, int i4, i iVar, p pVar, p pVar2) {
        this.f5229d = 0;
        this.f5236k = Float.NaN;
        this.f5237l = Float.NaN;
        this.f5238m = c.f5050a;
        this.f5239n = new LinkedHashMap<>();
        this.f5240o = 0;
        this.f5241p = new double[18];
        this.f5242q = new double[18];
        int i5 = iVar.f5132q;
        if (i5 == 1) {
            l(iVar, pVar, pVar2);
        } else if (i5 != 2) {
            k(iVar, pVar, pVar2);
        } else {
            m(i3, i4, iVar, pVar, pVar2);
        }
    }
}
