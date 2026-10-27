package androidx.constraintlayout.motion.widget;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import androidx.constraintlayout.widget.d;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
class p implements Comparable<p> {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    static String[] f1502y = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    p.c f1503e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float f1505h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float f1506i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    float f1507j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    float f1508k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    float f1509l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    float f1510m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    int f1513p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    int f1514q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    float f1515r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    m f1516s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    LinkedHashMap<String, androidx.constraintlayout.widget.a> f1517t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    int f1518u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    int f1519v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    double[] f1520w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    double[] f1521x;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f1504g = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    float f1511n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    float f1512o = Float.NaN;

    public p() {
        int i7 = d.f1349f;
        this.f1513p = i7;
        this.f1514q = i7;
        this.f1515r = Float.NaN;
        this.f1516s = null;
        this.f1517t = new LinkedHashMap<>();
        this.f1518u = 0;
        this.f1520w = new double[18];
        this.f1521x = new double[18];
    }

    private boolean c(float f7, float f8) {
        return (Float.isNaN(f7) || Float.isNaN(f8)) ? Float.isNaN(f7) != Float.isNaN(f8) : Math.abs(f7 - f8) > 1.0E-6f;
    }

    public void a(d.a aVar) {
        this.f1503e = p.c.c(aVar.f1778d.f1843d);
        d.c cVar = aVar.f1778d;
        this.f1513p = cVar.f1844e;
        this.f1514q = cVar.f1841b;
        this.f1511n = cVar.f1848i;
        this.f1504g = cVar.f1845f;
        this.f1519v = cVar.f1842c;
        this.f1512o = aVar.f1777c.f1858e;
        this.f1515r = aVar.f1779e.D;
        for (String str : aVar.f1781g.keySet()) {
            androidx.constraintlayout.widget.a aVar2 = aVar.f1781g.get(str);
            if (aVar2 != null && aVar2.g()) {
                this.f1517t.put(str, aVar2);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(p pVar) {
        return Float.compare(this.f1506i, pVar.f1506i);
    }

    void d(p pVar, boolean[] zArr, String[] strArr, boolean z6) {
        boolean zC = c(this.f1507j, pVar.f1507j);
        boolean zC2 = c(this.f1508k, pVar.f1508k);
        zArr[0] = zArr[0] | c(this.f1506i, pVar.f1506i);
        boolean z7 = zC | zC2 | z6;
        zArr[1] = zArr[1] | z7;
        zArr[2] = z7 | zArr[2];
        zArr[3] = zArr[3] | c(this.f1509l, pVar.f1509l);
        zArr[4] = c(this.f1510m, pVar.f1510m) | zArr[4];
    }

    void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.f1506i, this.f1507j, this.f1508k, this.f1509l, this.f1510m, this.f1511n};
        int i7 = 0;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            if (iArr[i8] < 6) {
                dArr[i7] = fArr[iArr[i8]];
                i7++;
            }
        }
    }

    void f(double d7, int[] iArr, double[] dArr, float[] fArr, int i7) {
        float fSin = this.f1507j;
        float fCos = this.f1508k;
        float f7 = this.f1509l;
        float f8 = this.f1510m;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            float f9 = (float) dArr[i8];
            int i9 = iArr[i8];
            if (i9 == 1) {
                fSin = f9;
            } else if (i9 == 2) {
                fCos = f9;
            } else if (i9 == 3) {
                f7 = f9;
            } else if (i9 == 4) {
                f8 = f9;
            }
        }
        m mVar = this.f1516s;
        if (mVar != null) {
            float[] fArr2 = new float[2];
            mVar.i(d7, fArr2, new float[2]);
            float f10 = fArr2[0];
            float f11 = fArr2[1];
            double d8 = f10;
            double d9 = fSin;
            double d10 = fCos;
            fSin = (float) ((d8 + (Math.sin(d10) * d9)) - ((double) (f7 / 2.0f)));
            fCos = (float) ((((double) f11) - (d9 * Math.cos(d10))) - ((double) (f8 / 2.0f)));
        }
        fArr[i7] = fSin + (f7 / 2.0f) + 0.0f;
        fArr[i7 + 1] = fCos + (f8 / 2.0f) + 0.0f;
    }

    void g(double d7, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f7;
        float f8 = this.f1507j;
        float f9 = this.f1508k;
        float f10 = this.f1509l;
        float f11 = this.f1510m;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        for (int i7 = 0; i7 < iArr.length; i7++) {
            float f16 = (float) dArr[i7];
            float f17 = (float) dArr2[i7];
            int i8 = iArr[i7];
            if (i8 == 1) {
                f8 = f16;
                f12 = f17;
            } else if (i8 == 2) {
                f9 = f16;
                f14 = f17;
            } else if (i8 == 3) {
                f10 = f16;
                f13 = f17;
            } else if (i8 == 4) {
                f11 = f16;
                f15 = f17;
            }
        }
        float f18 = 2.0f;
        float f19 = (f13 / 2.0f) + f12;
        float fCos = (f15 / 2.0f) + f14;
        m mVar = this.f1516s;
        if (mVar != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            mVar.i(d7, fArr3, fArr4);
            float f20 = fArr3[0];
            float f21 = fArr3[1];
            float f22 = fArr4[0];
            float f23 = fArr4[1];
            double d8 = f8;
            double d9 = f9;
            f7 = f10;
            float fSin = (float) ((((double) f20) + (Math.sin(d9) * d8)) - ((double) (f10 / 2.0f)));
            float fCos2 = (float) ((((double) f21) - (d8 * Math.cos(d9))) - ((double) (f11 / 2.0f)));
            double d10 = f12;
            double d11 = f14;
            float fSin2 = (float) (((double) f22) + (Math.sin(d9) * d10) + (Math.cos(d9) * d11));
            fCos = (float) ((((double) f23) - (d10 * Math.cos(d9))) + (Math.sin(d9) * d11));
            f19 = fSin2;
            f8 = fSin;
            f9 = fCos2;
            f18 = 2.0f;
        } else {
            f7 = f10;
        }
        fArr[0] = f8 + (f7 / f18) + 0.0f;
        fArr[1] = f9 + (f11 / f18) + 0.0f;
        fArr2[0] = f19;
        fArr2[1] = fCos;
    }

    int h(String str, double[] dArr, int i7) {
        androidx.constraintlayout.widget.a aVar = this.f1517t.get(str);
        int i8 = 0;
        if (aVar == null) {
            return 0;
        }
        if (aVar.h() == 1) {
            dArr[i7] = aVar.e();
            return 1;
        }
        int iH = aVar.h();
        aVar.f(new float[iH]);
        while (i8 < iH) {
            dArr[i7] = r2[i8];
            i8++;
            i7++;
        }
        return iH;
    }

    int i(String str) {
        androidx.constraintlayout.widget.a aVar = this.f1517t.get(str);
        if (aVar == null) {
            return 0;
        }
        return aVar.h();
    }

    void j(int[] iArr, double[] dArr, float[] fArr, int i7) {
        float f7 = this.f1507j;
        float fCos = this.f1508k;
        float f8 = this.f1509l;
        float f9 = this.f1510m;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            float f10 = (float) dArr[i8];
            int i9 = iArr[i8];
            if (i9 == 1) {
                f7 = f10;
            } else if (i9 == 2) {
                fCos = f10;
            } else if (i9 == 3) {
                f8 = f10;
            } else if (i9 == 4) {
                f9 = f10;
            }
        }
        m mVar = this.f1516s;
        if (mVar != null) {
            float fJ = mVar.j();
            float fK = this.f1516s.k();
            double d7 = f7;
            double d8 = fCos;
            float fSin = (float) ((((double) fJ) + (Math.sin(d8) * d7)) - ((double) (f8 / 2.0f)));
            fCos = (float) ((((double) fK) - (d7 * Math.cos(d8))) - ((double) (f9 / 2.0f)));
            f7 = fSin;
        }
        float f11 = f8 + f7;
        float f12 = f9 + fCos;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i10 = i7 + 1;
        fArr[i7] = f7 + 0.0f;
        int i11 = i10 + 1;
        fArr[i10] = fCos + 0.0f;
        int i12 = i11 + 1;
        fArr[i11] = f11 + 0.0f;
        int i13 = i12 + 1;
        fArr[i12] = fCos + 0.0f;
        int i14 = i13 + 1;
        fArr[i13] = f11 + 0.0f;
        int i15 = i14 + 1;
        fArr[i14] = f12 + 0.0f;
        fArr[i15] = f7 + 0.0f;
        fArr[i15 + 1] = f12 + 0.0f;
    }

    boolean k(String str) {
        return this.f1517t.containsKey(str);
    }

    void l(h hVar, p pVar, p pVar2) {
        float f7 = hVar.f1350a / 100.0f;
        this.f1505h = f7;
        this.f1504g = hVar.f1398j;
        float f8 = Float.isNaN(hVar.f1399k) ? f7 : hVar.f1399k;
        float f9 = Float.isNaN(hVar.f1400l) ? f7 : hVar.f1400l;
        float f10 = pVar2.f1509l;
        float f11 = pVar.f1509l;
        float f12 = pVar2.f1510m;
        float f13 = pVar.f1510m;
        this.f1506i = this.f1505h;
        float f14 = pVar.f1507j;
        float f15 = pVar.f1508k;
        float f16 = (pVar2.f1507j + (f10 / 2.0f)) - ((f11 / 2.0f) + f14);
        float f17 = (pVar2.f1508k + (f12 / 2.0f)) - (f15 + (f13 / 2.0f));
        float f18 = ((f10 - f11) * f8) / 2.0f;
        this.f1507j = (int) ((f14 + (f16 * f7)) - f18);
        float f19 = ((f12 - f13) * f9) / 2.0f;
        this.f1508k = (int) ((f15 + (f17 * f7)) - f19);
        this.f1509l = (int) (f11 + r9);
        this.f1510m = (int) (f13 + r12);
        float f20 = Float.isNaN(hVar.f1401m) ? f7 : hVar.f1401m;
        float f21 = Float.isNaN(hVar.f1404p) ? 0.0f : hVar.f1404p;
        if (!Float.isNaN(hVar.f1402n)) {
            f7 = hVar.f1402n;
        }
        float f22 = Float.isNaN(hVar.f1403o) ? 0.0f : hVar.f1403o;
        this.f1518u = 0;
        this.f1507j = (int) (((pVar.f1507j + (f20 * f16)) + (f22 * f17)) - f18);
        this.f1508k = (int) (((pVar.f1508k + (f16 * f21)) + (f17 * f7)) - f19);
        this.f1503e = p.c.c(hVar.f1396h);
        this.f1513p = hVar.f1397i;
    }

    void m(h hVar, p pVar, p pVar2) {
        float f7 = hVar.f1350a / 100.0f;
        this.f1505h = f7;
        this.f1504g = hVar.f1398j;
        float f8 = Float.isNaN(hVar.f1399k) ? f7 : hVar.f1399k;
        float f9 = Float.isNaN(hVar.f1400l) ? f7 : hVar.f1400l;
        float f10 = pVar2.f1509l - pVar.f1509l;
        float f11 = pVar2.f1510m - pVar.f1510m;
        this.f1506i = this.f1505h;
        if (!Float.isNaN(hVar.f1401m)) {
            f7 = hVar.f1401m;
        }
        float f12 = pVar.f1507j;
        float f13 = pVar.f1509l;
        float f14 = pVar.f1508k;
        float f15 = pVar.f1510m;
        float f16 = (pVar2.f1507j + (pVar2.f1509l / 2.0f)) - ((f13 / 2.0f) + f12);
        float f17 = (pVar2.f1508k + (pVar2.f1510m / 2.0f)) - ((f15 / 2.0f) + f14);
        float f18 = f16 * f7;
        float f19 = (f10 * f8) / 2.0f;
        this.f1507j = (int) ((f12 + f18) - f19);
        float f20 = f7 * f17;
        float f21 = (f11 * f9) / 2.0f;
        this.f1508k = (int) ((f14 + f20) - f21);
        this.f1509l = (int) (f13 + r7);
        this.f1510m = (int) (f15 + r8);
        float f22 = Float.isNaN(hVar.f1402n) ? 0.0f : hVar.f1402n;
        this.f1518u = 1;
        float f23 = (int) ((pVar.f1507j + f18) - f19);
        float f24 = (int) ((pVar.f1508k + f20) - f21);
        this.f1507j = f23 + ((-f17) * f22);
        this.f1508k = f24 + (f16 * f22);
        this.f1514q = this.f1514q;
        this.f1503e = p.c.c(hVar.f1396h);
        this.f1513p = hVar.f1397i;
    }

    void n(int i7, int i8, h hVar, p pVar, p pVar2) {
        float fMin;
        float f7;
        float f8 = hVar.f1350a / 100.0f;
        this.f1505h = f8;
        this.f1504g = hVar.f1398j;
        this.f1518u = hVar.f1405q;
        float f9 = Float.isNaN(hVar.f1399k) ? f8 : hVar.f1399k;
        float f10 = Float.isNaN(hVar.f1400l) ? f8 : hVar.f1400l;
        float f11 = pVar2.f1509l;
        float f12 = pVar.f1509l;
        float f13 = pVar2.f1510m;
        float f14 = pVar.f1510m;
        this.f1506i = this.f1505h;
        this.f1509l = (int) (f12 + ((f11 - f12) * f9));
        this.f1510m = (int) (f14 + ((f13 - f14) * f10));
        int i9 = hVar.f1405q;
        if (i9 == 1) {
            float f15 = Float.isNaN(hVar.f1401m) ? f8 : hVar.f1401m;
            float f16 = pVar2.f1507j;
            float f17 = pVar.f1507j;
            this.f1507j = (f15 * (f16 - f17)) + f17;
            if (!Float.isNaN(hVar.f1402n)) {
                f8 = hVar.f1402n;
            }
            float f18 = pVar2.f1508k;
            float f19 = pVar.f1508k;
            this.f1508k = (f8 * (f18 - f19)) + f19;
        } else if (i9 != 2) {
            float f20 = Float.isNaN(hVar.f1401m) ? f8 : hVar.f1401m;
            float f21 = pVar2.f1507j;
            float f22 = pVar.f1507j;
            this.f1507j = (f20 * (f21 - f22)) + f22;
            if (!Float.isNaN(hVar.f1402n)) {
                f8 = hVar.f1402n;
            }
            float f23 = pVar2.f1508k;
            float f24 = pVar.f1508k;
            this.f1508k = (f8 * (f23 - f24)) + f24;
        } else {
            if (Float.isNaN(hVar.f1401m)) {
                float f25 = pVar2.f1507j;
                float f26 = pVar.f1507j;
                fMin = ((f25 - f26) * f8) + f26;
            } else {
                fMin = Math.min(f10, f9) * hVar.f1401m;
            }
            this.f1507j = fMin;
            if (Float.isNaN(hVar.f1402n)) {
                float f27 = pVar2.f1508k;
                float f28 = pVar.f1508k;
                f7 = (f8 * (f27 - f28)) + f28;
            } else {
                f7 = hVar.f1402n;
            }
            this.f1508k = f7;
        }
        this.f1514q = pVar.f1514q;
        this.f1503e = p.c.c(hVar.f1396h);
        this.f1513p = hVar.f1397i;
    }

    void o(int i7, int i8, h hVar, p pVar, p pVar2) {
        float f7 = hVar.f1350a / 100.0f;
        this.f1505h = f7;
        this.f1504g = hVar.f1398j;
        float f8 = Float.isNaN(hVar.f1399k) ? f7 : hVar.f1399k;
        float f9 = Float.isNaN(hVar.f1400l) ? f7 : hVar.f1400l;
        float f10 = pVar2.f1509l;
        float f11 = pVar.f1509l;
        float f12 = pVar2.f1510m;
        float f13 = pVar.f1510m;
        this.f1506i = this.f1505h;
        float f14 = pVar.f1507j;
        float f15 = pVar.f1508k;
        float f16 = pVar2.f1507j + (f10 / 2.0f);
        float f17 = pVar2.f1508k + (f12 / 2.0f);
        float f18 = (f10 - f11) * f8;
        this.f1507j = (int) ((f14 + ((f16 - ((f11 / 2.0f) + f14)) * f7)) - (f18 / 2.0f));
        float f19 = (f12 - f13) * f9;
        this.f1508k = (int) ((f15 + ((f17 - (f15 + (f13 / 2.0f))) * f7)) - (f19 / 2.0f));
        this.f1509l = (int) (f11 + f18);
        this.f1510m = (int) (f13 + f19);
        this.f1518u = 2;
        if (!Float.isNaN(hVar.f1401m)) {
            this.f1507j = (int) (hVar.f1401m * ((int) (i7 - this.f1509l)));
        }
        if (!Float.isNaN(hVar.f1402n)) {
            this.f1508k = (int) (hVar.f1402n * ((int) (i8 - this.f1510m)));
        }
        this.f1514q = this.f1514q;
        this.f1503e = p.c.c(hVar.f1396h);
        this.f1513p = hVar.f1397i;
    }

    void p(float f7, float f8, float f9, float f10) {
        this.f1507j = f7;
        this.f1508k = f8;
        this.f1509l = f9;
        this.f1510m = f10;
    }

    void q(float f7, float f8, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        for (int i7 = 0; i7 < iArr.length; i7++) {
            float f13 = (float) dArr[i7];
            double d7 = dArr2[i7];
            int i8 = iArr[i7];
            if (i8 == 1) {
                f9 = f13;
            } else if (i8 == 2) {
                f11 = f13;
            } else if (i8 == 3) {
                f10 = f13;
            } else if (i8 == 4) {
                f12 = f13;
            }
        }
        float f14 = f9 - ((0.0f * f10) / 2.0f);
        float f15 = f11 - ((0.0f * f12) / 2.0f);
        fArr[0] = (f14 * (1.0f - f7)) + (((f10 * 1.0f) + f14) * f7) + 0.0f;
        fArr[1] = (f15 * (1.0f - f8)) + (((f12 * 1.0f) + f15) * f8) + 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void r(float f7, View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3, boolean z6) {
        float f8;
        float f9;
        boolean z7;
        boolean z8;
        float f10;
        float f11 = this.f1507j;
        float f12 = this.f1508k;
        float f13 = this.f1509l;
        float f14 = this.f1510m;
        if (iArr.length != 0 && this.f1520w.length <= iArr[iArr.length - 1]) {
            int i7 = iArr[iArr.length - 1] + 1;
            this.f1520w = new double[i7];
            this.f1521x = new double[i7];
        }
        Arrays.fill(this.f1520w, Double.NaN);
        for (int i8 = 0; i8 < iArr.length; i8++) {
            this.f1520w[iArr[i8]] = dArr[i8];
            this.f1521x[iArr[i8]] = dArr2[i8];
        }
        float f15 = Float.NaN;
        int i9 = 0;
        float f16 = 0.0f;
        float f17 = 0.0f;
        float f18 = 0.0f;
        float f19 = 0.0f;
        while (true) {
            double[] dArr4 = this.f1520w;
            if (i9 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i9]) && (dArr3 == null || dArr3[i9] == 0.0d)) {
                f10 = f15;
            } else {
                double d7 = dArr3 != null ? dArr3[i9] : 0.0d;
                if (!Double.isNaN(this.f1520w[i9])) {
                    d7 = this.f1520w[i9] + d7;
                }
                f10 = f15;
                float f20 = (float) d7;
                float f21 = (float) this.f1521x[i9];
                if (i9 == 1) {
                    f15 = f10;
                    f11 = f20;
                    f16 = f21;
                } else if (i9 == 2) {
                    f15 = f10;
                    f12 = f20;
                    f17 = f21;
                } else if (i9 == 3) {
                    f15 = f10;
                    f13 = f20;
                    f18 = f21;
                } else if (i9 == 4) {
                    f15 = f10;
                    f14 = f20;
                    f19 = f21;
                } else if (i9 == 5) {
                    f15 = f20;
                }
                i9++;
            }
            f15 = f10;
            i9++;
        }
        float f22 = f15;
        m mVar = this.f1516s;
        if (mVar != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            mVar.i(f7, fArr, fArr2);
            float f23 = fArr[0];
            float f24 = fArr[1];
            float f25 = fArr2[0];
            float f26 = fArr2[1];
            double d8 = f11;
            double d9 = f12;
            float fSin = (float) ((((double) f23) + (Math.sin(d9) * d8)) - ((double) (f13 / 2.0f)));
            float fCos = (float) ((((double) f24) - (Math.cos(d9) * d8)) - ((double) (f14 / 2.0f)));
            double d10 = f16;
            f8 = f13;
            f9 = f14;
            double d11 = f17;
            float fSin2 = (float) (((double) f25) + (Math.sin(d9) * d10) + (Math.cos(d9) * d8 * d11));
            float fCos2 = (float) ((((double) f26) - (d10 * Math.cos(d9))) + (d8 * Math.sin(d9) * d11));
            if (dArr2.length >= 2) {
                z7 = false;
                dArr2[0] = fSin2;
                z8 = true;
                dArr2[1] = fCos2;
            } else {
                z7 = false;
                z8 = true;
            }
            if (!Float.isNaN(f22)) {
                view.setRotation((float) (((double) f22) + Math.toDegrees(Math.atan2(fCos2, fSin2))));
            }
            f11 = fSin;
            f12 = fCos;
        } else {
            f8 = f13;
            f9 = f14;
            z7 = false;
            z8 = true;
            if (!Float.isNaN(f22)) {
                view.setRotation((float) (((double) 0.0f) + ((double) f22) + Math.toDegrees(Math.atan2(f17 + (f19 / 2.0f), f16 + (f18 / 2.0f)))));
            }
        }
        if (view instanceof c) {
            ((c) view).a(f11, f12, f11 + f8, f12 + f9);
            return;
        }
        float f27 = f11 + 0.5f;
        int i10 = (int) f27;
        float f28 = f12 + 0.5f;
        int i11 = (int) f28;
        int i12 = (int) (f27 + f8);
        int i13 = (int) (f28 + f9);
        int i14 = i12 - i10;
        int i15 = i13 - i11;
        if (((i14 == view.getMeasuredWidth() && i15 == view.getMeasuredHeight()) ? z7 : z8) || z6) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i14, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i15, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
        view.layout(i10, i11, i12, i13);
    }

    public void s(m mVar, p pVar) {
        double d7 = ((this.f1507j + (this.f1509l / 2.0f)) - pVar.f1507j) - (pVar.f1509l / 2.0f);
        double d8 = ((this.f1508k + (this.f1510m / 2.0f)) - pVar.f1508k) - (pVar.f1510m / 2.0f);
        this.f1516s = mVar;
        this.f1507j = (float) Math.hypot(d8, d7);
        if (Float.isNaN(this.f1515r)) {
            this.f1508k = (float) (Math.atan2(d8, d7) + 1.5707963267948966d);
        } else {
            this.f1508k = (float) Math.toRadians(this.f1515r);
        }
    }

    public p(int i7, int i8, h hVar, p pVar, p pVar2) {
        int i9 = d.f1349f;
        this.f1513p = i9;
        this.f1514q = i9;
        this.f1515r = Float.NaN;
        this.f1516s = null;
        this.f1517t = new LinkedHashMap<>();
        this.f1518u = 0;
        this.f1520w = new double[18];
        this.f1521x = new double[18];
        if (pVar.f1514q != d.f1349f) {
            n(i7, i8, hVar, pVar, pVar2);
            return;
        }
        int i10 = hVar.f1405q;
        if (i10 == 1) {
            m(hVar, pVar, pVar2);
        } else if (i10 != 2) {
            l(hVar, pVar, pVar2);
        } else {
            o(i7, i8, hVar, pVar, pVar2);
        }
    }
}
