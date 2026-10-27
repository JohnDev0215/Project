package x;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b0.b;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
public class p implements Comparable<p> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String[] f8939d = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public w.c f8940e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8941f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f8942g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f8943h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f8944i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f8945j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8946k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f8947l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f8948m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f8949n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f8950o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public LinkedHashMap<String, ConstraintAttribute> f8951p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f8952q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public double[] f8953r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public double[] f8954s;

    public p() {
        this.f8941f = 0;
        this.f8948m = Float.NaN;
        this.f8949n = Float.NaN;
        this.f8950o = c.f8764a;
        this.f8951p = new LinkedHashMap<>();
        this.f8952q = 0;
        this.f8953r = new double[18];
        this.f8954s = new double[18];
    }

    public void a(b.a aVar) {
        this.f8940e = w.c.c(aVar.f1944c.f1988d);
        b.c cVar = aVar.f1944c;
        this.f8950o = cVar.f1989e;
        this.f8948m = cVar.f1992h;
        this.f8941f = cVar.f1990f;
        this.f8949n = aVar.f1943b.f1997e;
        for (String str : aVar.f1947f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f1947f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.f8951p.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(p pVar) {
        return Float.compare(this.f8943h, pVar.f8943h);
    }

    public final boolean c(float f8, float f9) {
        return (Float.isNaN(f8) || Float.isNaN(f9)) ? Float.isNaN(f8) != Float.isNaN(f9) : Math.abs(f8 - f9) > 1.0E-6f;
    }

    public void d(p pVar, boolean[] zArr, String[] strArr, boolean z7) {
        zArr[0] = zArr[0] | c(this.f8943h, pVar.f8943h);
        zArr[1] = zArr[1] | c(this.f8944i, pVar.f8944i) | z7;
        zArr[2] = z7 | c(this.f8945j, pVar.f8945j) | zArr[2];
        zArr[3] = zArr[3] | c(this.f8946k, pVar.f8946k);
        zArr[4] = c(this.f8947l, pVar.f8947l) | zArr[4];
    }

    public void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.f8943h, this.f8944i, this.f8945j, this.f8946k, this.f8947l, this.f8948m};
        int i8 = 0;
        for (int i9 = 0; i9 < iArr.length; i9++) {
            if (iArr[i9] < 6) {
                dArr[i8] = fArr[iArr[i9]];
                i8++;
            }
        }
    }

    public void f(int[] iArr, double[] dArr, float[] fArr, int i8) {
        float f8 = this.f8944i;
        float f9 = this.f8945j;
        float f10 = this.f8946k;
        float f11 = this.f8947l;
        for (int i9 = 0; i9 < iArr.length; i9++) {
            float f12 = (float) dArr[i9];
            int i10 = iArr[i9];
            if (i10 == 1) {
                f8 = f12;
            } else if (i10 == 2) {
                f9 = f12;
            } else if (i10 == 3) {
                f10 = f12;
            } else if (i10 == 4) {
                f11 = f12;
            }
        }
        fArr[i8] = f8 + (f10 / 2.0f) + 0.0f;
        fArr[i8 + 1] = f9 + (f11 / 2.0f) + 0.0f;
    }

    public int g(String str, double[] dArr, int i8) {
        ConstraintAttribute constraintAttribute = this.f8951p.get(str);
        if (constraintAttribute.f() == 1) {
            dArr[i8] = constraintAttribute.d();
            return 1;
        }
        int iF = constraintAttribute.f();
        constraintAttribute.e(new float[iF]);
        int i9 = 0;
        while (i9 < iF) {
            dArr[i8] = r1[i9];
            i9++;
            i8++;
        }
        return iF;
    }

    public int h(String str) {
        return this.f8951p.get(str).f();
    }

    public void i(int[] iArr, double[] dArr, float[] fArr, int i8) {
        float f8 = this.f8944i;
        float f9 = this.f8945j;
        float f10 = this.f8946k;
        float f11 = this.f8947l;
        for (int i9 = 0; i9 < iArr.length; i9++) {
            float f12 = (float) dArr[i9];
            int i10 = iArr[i9];
            if (i10 == 1) {
                f8 = f12;
            } else if (i10 == 2) {
                f9 = f12;
            } else if (i10 == 3) {
                f10 = f12;
            } else if (i10 == 4) {
                f11 = f12;
            }
        }
        float f13 = f10 + f8;
        float f14 = f11 + f9;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i11 = i8 + 1;
        fArr[i8] = f8 + 0.0f;
        int i12 = i11 + 1;
        fArr[i11] = f9 + 0.0f;
        int i13 = i12 + 1;
        fArr[i12] = f13 + 0.0f;
        int i14 = i13 + 1;
        fArr[i13] = f9 + 0.0f;
        int i15 = i14 + 1;
        fArr[i14] = f13 + 0.0f;
        int i16 = i15 + 1;
        fArr[i15] = f14 + 0.0f;
        fArr[i16] = f8 + 0.0f;
        fArr[i16 + 1] = f14 + 0.0f;
    }

    public boolean j(String str) {
        return this.f8951p.containsKey(str);
    }

    public void k(i iVar, p pVar, p pVar2) {
        float f8 = iVar.f8765b / 100.0f;
        this.f8942g = f8;
        this.f8941f = iVar.f8839j;
        float f9 = Float.isNaN(iVar.f8840k) ? f8 : iVar.f8840k;
        float f10 = Float.isNaN(iVar.f8841l) ? f8 : iVar.f8841l;
        float f11 = pVar2.f8946k;
        float f12 = pVar.f8946k;
        float f13 = pVar2.f8947l;
        float f14 = pVar.f8947l;
        this.f8943h = this.f8942g;
        float f15 = pVar.f8944i;
        float f16 = pVar.f8945j;
        float f17 = (pVar2.f8944i + (f11 / 2.0f)) - ((f12 / 2.0f) + f15);
        float f18 = (pVar2.f8945j + (f13 / 2.0f)) - (f16 + (f14 / 2.0f));
        float f19 = ((f11 - f12) * f9) / 2.0f;
        this.f8944i = (int) ((f15 + (f17 * f8)) - f19);
        float f20 = ((f13 - f14) * f10) / 2.0f;
        this.f8945j = (int) ((f16 + (f18 * f8)) - f20);
        this.f8946k = (int) (f12 + r9);
        this.f8947l = (int) (f14 + r12);
        float f21 = Float.isNaN(iVar.f8842m) ? f8 : iVar.f8842m;
        float f22 = Float.isNaN(iVar.f8845p) ? 0.0f : iVar.f8845p;
        if (!Float.isNaN(iVar.f8843n)) {
            f8 = iVar.f8843n;
        }
        float f23 = Float.isNaN(iVar.f8844o) ? 0.0f : iVar.f8844o;
        this.f8952q = 2;
        this.f8944i = (int) (((pVar.f8944i + (f21 * f17)) + (f23 * f18)) - f19);
        this.f8945j = (int) (((pVar.f8945j + (f17 * f22)) + (f18 * f8)) - f20);
        this.f8940e = w.c.c(iVar.f8837h);
        this.f8950o = iVar.f8838i;
    }

    public void l(i iVar, p pVar, p pVar2) {
        float f8 = iVar.f8765b / 100.0f;
        this.f8942g = f8;
        this.f8941f = iVar.f8839j;
        float f9 = Float.isNaN(iVar.f8840k) ? f8 : iVar.f8840k;
        float f10 = Float.isNaN(iVar.f8841l) ? f8 : iVar.f8841l;
        float f11 = pVar2.f8946k - pVar.f8946k;
        float f12 = pVar2.f8947l - pVar.f8947l;
        this.f8943h = this.f8942g;
        if (!Float.isNaN(iVar.f8842m)) {
            f8 = iVar.f8842m;
        }
        float f13 = pVar.f8944i;
        float f14 = pVar.f8946k;
        float f15 = pVar.f8945j;
        float f16 = pVar.f8947l;
        float f17 = (pVar2.f8944i + (pVar2.f8946k / 2.0f)) - ((f14 / 2.0f) + f13);
        float f18 = (pVar2.f8945j + (pVar2.f8947l / 2.0f)) - ((f16 / 2.0f) + f15);
        float f19 = f17 * f8;
        float f20 = (f11 * f9) / 2.0f;
        this.f8944i = (int) ((f13 + f19) - f20);
        float f21 = f8 * f18;
        float f22 = (f12 * f10) / 2.0f;
        this.f8945j = (int) ((f15 + f21) - f22);
        this.f8946k = (int) (f14 + r7);
        this.f8947l = (int) (f16 + r8);
        float f23 = Float.isNaN(iVar.f8843n) ? 0.0f : iVar.f8843n;
        this.f8952q = 1;
        float f24 = (int) ((pVar.f8944i + f19) - f20);
        this.f8944i = f24;
        float f25 = (int) ((pVar.f8945j + f21) - f22);
        this.f8945j = f25;
        this.f8944i = f24 + ((-f18) * f23);
        this.f8945j = f25 + (f17 * f23);
        this.f8940e = w.c.c(iVar.f8837h);
        this.f8950o = iVar.f8838i;
    }

    public void m(int i8, int i9, i iVar, p pVar, p pVar2) {
        float f8 = iVar.f8765b / 100.0f;
        this.f8942g = f8;
        this.f8941f = iVar.f8839j;
        float f9 = Float.isNaN(iVar.f8840k) ? f8 : iVar.f8840k;
        float f10 = Float.isNaN(iVar.f8841l) ? f8 : iVar.f8841l;
        float f11 = pVar2.f8946k;
        float f12 = pVar.f8946k;
        float f13 = pVar2.f8947l;
        float f14 = pVar.f8947l;
        this.f8943h = this.f8942g;
        float f15 = pVar.f8944i;
        float f16 = pVar.f8945j;
        float f17 = pVar2.f8944i + (f11 / 2.0f);
        float f18 = pVar2.f8945j + (f13 / 2.0f);
        float f19 = (f11 - f12) * f9;
        this.f8944i = (int) ((f15 + ((f17 - ((f12 / 2.0f) + f15)) * f8)) - (f19 / 2.0f));
        float f20 = (f13 - f14) * f10;
        this.f8945j = (int) ((f16 + ((f18 - (f16 + (f14 / 2.0f))) * f8)) - (f20 / 2.0f));
        this.f8946k = (int) (f12 + f19);
        this.f8947l = (int) (f14 + f20);
        this.f8952q = 3;
        if (!Float.isNaN(iVar.f8842m)) {
            this.f8944i = (int) (iVar.f8842m * ((int) (i8 - this.f8946k)));
        }
        if (!Float.isNaN(iVar.f8843n)) {
            this.f8945j = (int) (iVar.f8843n * ((int) (i9 - this.f8947l)));
        }
        this.f8940e = w.c.c(iVar.f8837h);
        this.f8950o = iVar.f8838i;
    }

    public void n(float f8, float f9, float f10, float f11) {
        this.f8944i = f8;
        this.f8945j = f9;
        this.f8946k = f10;
        this.f8947l = f11;
    }

    public void o(float f8, float f9, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            float f14 = (float) dArr[i8];
            double d8 = dArr2[i8];
            int i9 = iArr[i8];
            if (i9 == 1) {
                f10 = f14;
            } else if (i9 == 2) {
                f12 = f14;
            } else if (i9 == 3) {
                f11 = f14;
            } else if (i9 == 4) {
                f13 = f14;
            }
        }
        float f15 = f10 - ((0.0f * f11) / 2.0f);
        float f16 = f12 - ((0.0f * f13) / 2.0f);
        fArr[0] = (f15 * (1.0f - f8)) + (((f11 * 1.0f) + f15) * f8) + 0.0f;
        fArr[1] = (f16 * (1.0f - f9)) + (((f13 * 1.0f) + f16) * f9) + 0.0f;
    }

    public void p(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f8;
        boolean z7;
        float f9 = this.f8944i;
        float f10 = this.f8945j;
        float f11 = this.f8946k;
        float f12 = this.f8947l;
        boolean z8 = true;
        if (iArr.length != 0 && this.f8953r.length <= iArr[iArr.length - 1]) {
            int i8 = iArr[iArr.length - 1] + 1;
            this.f8953r = new double[i8];
            this.f8954s = new double[i8];
        }
        Arrays.fill(this.f8953r, Double.NaN);
        for (int i9 = 0; i9 < iArr.length; i9++) {
            this.f8953r[iArr[i9]] = dArr[i9];
            this.f8954s[iArr[i9]] = dArr2[i9];
        }
        int i10 = 0;
        float f13 = Float.NaN;
        float f14 = 0.0f;
        float f15 = 0.0f;
        float f16 = 0.0f;
        float f17 = 0.0f;
        while (true) {
            double[] dArr4 = this.f8953r;
            if (i10 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i10]) && (dArr3 == null || dArr3[i10] == 0.0d)) {
                f8 = f9;
                z7 = z8;
            } else {
                double d8 = dArr3 != null ? dArr3[i10] : 0.0d;
                if (!Double.isNaN(this.f8953r[i10])) {
                    d8 = this.f8953r[i10] + d8;
                }
                f8 = f9;
                float f18 = (float) d8;
                float f19 = (float) this.f8954s[i10];
                z7 = true;
                if (i10 == 1) {
                    f14 = f19;
                    f9 = f18;
                } else if (i10 == 2) {
                    f10 = f18;
                    f16 = f19;
                } else if (i10 == 3) {
                    f11 = f18;
                    f15 = f19;
                } else if (i10 == 4) {
                    f12 = f18;
                    f17 = f19;
                } else if (i10 == 5) {
                    f9 = f8;
                    f13 = f18;
                }
                i10++;
                z8 = z7;
            }
            f9 = f8;
            i10++;
            z8 = z7;
        }
        float f20 = f9;
        boolean z9 = z8;
        if (!Float.isNaN(f13)) {
            view.setRotation((float) (((double) (Float.isNaN(Float.NaN) ? 0.0f : Float.NaN)) + ((double) f13) + Math.toDegrees(Math.atan2(f16 + (f17 / 2.0f), f14 + (f15 / 2.0f)))));
        } else if (!Float.isNaN(Float.NaN)) {
            view.setRotation(Float.NaN);
        }
        float f21 = f20 + 0.5f;
        int i11 = (int) f21;
        float f22 = f10 + 0.5f;
        int i12 = (int) f22;
        int i13 = (int) (f21 + f11);
        int i14 = (int) (f22 + f12);
        int i15 = i13 - i11;
        int i16 = i14 - i12;
        if ((i15 == view.getMeasuredWidth() && i16 == view.getMeasuredHeight()) ? false : z9) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i15, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i16, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
        view.layout(i11, i12, i13, i14);
    }

    public p(int i8, int i9, i iVar, p pVar, p pVar2) {
        this.f8941f = 0;
        this.f8948m = Float.NaN;
        this.f8949n = Float.NaN;
        this.f8950o = c.f8764a;
        this.f8951p = new LinkedHashMap<>();
        this.f8952q = 0;
        this.f8953r = new double[18];
        this.f8954s = new double[18];
        int i10 = iVar.f8846q;
        if (i10 == 1) {
            l(iVar, pVar, pVar2);
        } else if (i10 != 2) {
            k(iVar, pVar, pVar2);
        } else {
            m(i8, i9, iVar, pVar, pVar2);
        }
    }
}
