package androidx.constraintlayout.core.j.a;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final double[] f735a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    C0015a[] f736b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f737c = true;

    /* JADX INFO: renamed from: androidx.constraintlayout.core.j.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArcCurveFit.java */
    private static class C0015a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static double[] f738a = new double[91];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        double[] f739b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        double f740c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        double f741d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        double f742e;
        double f;
        double g;
        double h;
        double i;
        double j;
        double k;
        double l;
        double m;
        double n;
        double o;
        double p;
        double q;
        boolean r;
        boolean s;

        C0015a(int i, double d2, double d3, double d4, double d5, double d6, double d7) {
            this.s = false;
            this.r = i == 1;
            this.f741d = d2;
            this.f742e = d3;
            this.j = 1.0d / (d3 - d2);
            if (3 == i) {
                this.s = true;
            }
            double d8 = d6 - d4;
            double d9 = d7 - d5;
            if (!this.s && Math.abs(d8) >= 0.001d && Math.abs(d9) >= 0.001d) {
                this.f739b = new double[101];
                boolean z = this.r;
                this.k = d8 * ((double) (z ? -1 : 1));
                this.l = d9 * ((double) (z ? 1 : -1));
                this.m = z ? d6 : d4;
                this.n = z ? d5 : d7;
                a(d4, d5, d6, d7);
                this.o = this.f740c * this.j;
                return;
            }
            this.s = true;
            this.f = d4;
            this.g = d6;
            this.h = d5;
            this.i = d7;
            double dHypot = Math.hypot(d9, d8);
            this.f740c = dHypot;
            this.o = dHypot * this.j;
            double d10 = this.f742e;
            double d11 = this.f741d;
            this.m = d8 / (d10 - d11);
            this.n = d9 / (d10 - d11);
        }

        private void a(double d2, double d3, double d4, double d5) {
            double dHypot;
            double d6 = d4 - d2;
            double d7 = d3 - d5;
            int i = 0;
            double d8 = 0.0d;
            double d9 = 0.0d;
            double d10 = 0.0d;
            while (true) {
                if (i >= f738a.length) {
                    break;
                }
                double d11 = d8;
                double radians = Math.toRadians((((double) i) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d6;
                double dCos = Math.cos(radians) * d7;
                if (i > 0) {
                    dHypot = Math.hypot(dSin - d9, dCos - d10) + d11;
                    f738a[i] = dHypot;
                } else {
                    dHypot = d11;
                }
                i++;
                d10 = dCos;
                d8 = dHypot;
                d9 = dSin;
            }
            double d12 = d8;
            this.f740c = d12;
            int i2 = 0;
            while (true) {
                double[] dArr = f738a;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = dArr[i2] / d12;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.f739b.length) {
                    return;
                }
                double length = ((double) i3) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(f738a, length);
                if (iBinarySearch >= 0) {
                    this.f739b[i3] = ((double) iBinarySearch) / ((double) (f738a.length - 1));
                } else if (iBinarySearch == -1) {
                    this.f739b[i3] = 0.0d;
                } else {
                    int i4 = -iBinarySearch;
                    int i5 = i4 - 2;
                    double[] dArr2 = f738a;
                    this.f739b[i3] = (((double) i5) + ((length - dArr2[i5]) / (dArr2[i4 - 1] - dArr2[i5]))) / ((double) (dArr2.length - 1));
                }
                i3++;
            }
        }

        double b() {
            double d2 = this.k * this.q;
            double dHypot = this.o / Math.hypot(d2, (-this.l) * this.p);
            if (this.r) {
                d2 = -d2;
            }
            return d2 * dHypot;
        }

        double c() {
            double d2 = this.k * this.q;
            double d3 = (-this.l) * this.p;
            double dHypot = this.o / Math.hypot(d2, d3);
            return this.r ? (-d3) * dHypot : d3 * dHypot;
        }

        public double d(double d2) {
            return this.m;
        }

        public double e(double d2) {
            return this.n;
        }

        public double f(double d2) {
            double d3 = (d2 - this.f741d) * this.j;
            double d4 = this.f;
            return d4 + (d3 * (this.g - d4));
        }

        public double g(double d2) {
            double d3 = (d2 - this.f741d) * this.j;
            double d4 = this.h;
            return d4 + (d3 * (this.i - d4));
        }

        double h() {
            return this.m + (this.k * this.p);
        }

        double i() {
            return this.n + (this.l * this.q);
        }

        double j(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f739b;
            double length = d2 * ((double) (dArr.length - 1));
            int i = (int) length;
            return dArr[i] + ((length - ((double) i)) * (dArr[i + 1] - dArr[i]));
        }

        void k(double d2) {
            double dJ = j((this.r ? this.f742e - d2 : d2 - this.f741d) * this.j) * 1.5707963267948966d;
            this.p = Math.sin(dJ);
            this.q = Math.cos(dJ);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public a(int[] r25, double[] r26, double[][] r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = r26
            r24.<init>()
            r2 = 1
            r0.f737c = r2
            r0.f735a = r1
            int r3 = r1.length
            int r3 = r3 - r2
            androidx.constraintlayout.core.j.a.a$a[] r3 = new androidx.constraintlayout.core.j.a.a.C0015a[r3]
            r0.f736b = r3
            r3 = 0
            r5 = r2
            r6 = r5
            r4 = r3
        L16:
            androidx.constraintlayout.core.j.a.a$a[] r7 = r0.f736b
            int r8 = r7.length
            if (r4 >= r8) goto L53
            r8 = r25[r4]
            r9 = 3
            r10 = 2
            if (r8 == 0) goto L2f
            if (r8 == r2) goto L2c
            if (r8 == r10) goto L2a
            if (r8 == r9) goto L28
            goto L30
        L28:
            if (r5 != r2) goto L2c
        L2a:
            r5 = r10
            goto L2d
        L2c:
            r5 = r2
        L2d:
            r6 = r5
            goto L30
        L2f:
            r6 = r9
        L30:
            androidx.constraintlayout.core.j.a.a$a r22 = new androidx.constraintlayout.core.j.a.a$a
            r10 = r1[r4]
            int r23 = r4 + 1
            r12 = r1[r23]
            r8 = r27[r4]
            r14 = r8[r3]
            r8 = r27[r4]
            r16 = r8[r2]
            r8 = r27[r23]
            r18 = r8[r3]
            r8 = r27[r23]
            r20 = r8[r2]
            r8 = r22
            r9 = r6
            r8.<init>(r9, r10, r12, r14, r16, r18, r20)
            r7[r4] = r22
            r4 = r23
            goto L16
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.j.a.a.<init>(int[], double[], double[][]):void");
    }

    @Override // androidx.constraintlayout.core.j.a.b
    public double c(double d2, int i) {
        double d3;
        double dG;
        double dE;
        double dI;
        double dC;
        int i2 = 0;
        if (this.f737c) {
            C0015a[] c0015aArr = this.f736b;
            if (d2 < c0015aArr[0].f741d) {
                double d4 = c0015aArr[0].f741d;
                d3 = d2 - c0015aArr[0].f741d;
                if (!c0015aArr[0].s) {
                    c0015aArr[0].k(d4);
                    if (i == 0) {
                        dI = this.f736b[0].h();
                        dC = this.f736b[0].b();
                    } else {
                        dI = this.f736b[0].i();
                        dC = this.f736b[0].c();
                    }
                    return dI + (d3 * dC);
                }
                if (i == 0) {
                    dG = c0015aArr[0].f(d4);
                    dE = this.f736b[0].d(d4);
                } else {
                    dG = c0015aArr[0].g(d4);
                    dE = this.f736b[0].e(d4);
                }
            } else if (d2 > c0015aArr[c0015aArr.length - 1].f742e) {
                double d5 = c0015aArr[c0015aArr.length - 1].f742e;
                d3 = d2 - d5;
                int length = c0015aArr.length - 1;
                if (i == 0) {
                    dG = c0015aArr[length].f(d5);
                    dE = this.f736b[length].d(d5);
                } else {
                    dG = c0015aArr[length].g(d5);
                    dE = this.f736b[length].e(d5);
                }
            }
            return dG + (d3 * dE);
        }
        C0015a[] c0015aArr2 = this.f736b;
        if (d2 < c0015aArr2[0].f741d) {
            d2 = c0015aArr2[0].f741d;
        } else if (d2 > c0015aArr2[c0015aArr2.length - 1].f742e) {
            d2 = c0015aArr2[c0015aArr2.length - 1].f742e;
        }
        while (true) {
            C0015a[] c0015aArr3 = this.f736b;
            if (i2 >= c0015aArr3.length) {
                return Double.NaN;
            }
            if (d2 <= c0015aArr3[i2].f742e) {
                if (c0015aArr3[i2].s) {
                    return i == 0 ? c0015aArr3[i2].f(d2) : c0015aArr3[i2].g(d2);
                }
                c0015aArr3[i2].k(d2);
                return i == 0 ? this.f736b[i2].h() : this.f736b[i2].i();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.j.a.b
    public void d(double d2, double[] dArr) {
        if (this.f737c) {
            C0015a[] c0015aArr = this.f736b;
            if (d2 < c0015aArr[0].f741d) {
                double d3 = c0015aArr[0].f741d;
                double d4 = d2 - c0015aArr[0].f741d;
                if (c0015aArr[0].s) {
                    dArr[0] = c0015aArr[0].f(d3) + (this.f736b[0].d(d3) * d4);
                    dArr[1] = this.f736b[0].g(d3) + (d4 * this.f736b[0].e(d3));
                    return;
                } else {
                    c0015aArr[0].k(d3);
                    dArr[0] = this.f736b[0].h() + (this.f736b[0].b() * d4);
                    dArr[1] = this.f736b[0].i() + (d4 * this.f736b[0].c());
                    return;
                }
            }
            if (d2 > c0015aArr[c0015aArr.length - 1].f742e) {
                double d5 = c0015aArr[c0015aArr.length - 1].f742e;
                double d6 = d2 - d5;
                int length = c0015aArr.length - 1;
                if (c0015aArr[length].s) {
                    dArr[0] = c0015aArr[length].f(d5) + (this.f736b[length].d(d5) * d6);
                    dArr[1] = this.f736b[length].g(d5) + (d6 * this.f736b[length].e(d5));
                    return;
                } else {
                    c0015aArr[length].k(d2);
                    dArr[0] = this.f736b[length].h() + (this.f736b[length].b() * d6);
                    dArr[1] = this.f736b[length].i() + (d6 * this.f736b[length].c());
                    return;
                }
            }
        } else {
            C0015a[] c0015aArr2 = this.f736b;
            if (d2 < c0015aArr2[0].f741d) {
                d2 = c0015aArr2[0].f741d;
            }
            if (d2 > c0015aArr2[c0015aArr2.length - 1].f742e) {
                d2 = c0015aArr2[c0015aArr2.length - 1].f742e;
            }
        }
        int i = 0;
        while (true) {
            C0015a[] c0015aArr3 = this.f736b;
            if (i >= c0015aArr3.length) {
                return;
            }
            if (d2 <= c0015aArr3[i].f742e) {
                if (c0015aArr3[i].s) {
                    dArr[0] = c0015aArr3[i].f(d2);
                    dArr[1] = this.f736b[i].g(d2);
                    return;
                } else {
                    c0015aArr3[i].k(d2);
                    dArr[0] = this.f736b[i].h();
                    dArr[1] = this.f736b[i].i();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.j.a.b
    public void e(double d2, float[] fArr) {
        if (this.f737c) {
            C0015a[] c0015aArr = this.f736b;
            if (d2 < c0015aArr[0].f741d) {
                double d3 = c0015aArr[0].f741d;
                double d4 = d2 - c0015aArr[0].f741d;
                if (c0015aArr[0].s) {
                    fArr[0] = (float) (c0015aArr[0].f(d3) + (this.f736b[0].d(d3) * d4));
                    fArr[1] = (float) (this.f736b[0].g(d3) + (d4 * this.f736b[0].e(d3)));
                    return;
                } else {
                    c0015aArr[0].k(d3);
                    fArr[0] = (float) (this.f736b[0].h() + (this.f736b[0].b() * d4));
                    fArr[1] = (float) (this.f736b[0].i() + (d4 * this.f736b[0].c()));
                    return;
                }
            }
            if (d2 > c0015aArr[c0015aArr.length - 1].f742e) {
                double d5 = c0015aArr[c0015aArr.length - 1].f742e;
                double d6 = d2 - d5;
                int length = c0015aArr.length - 1;
                if (c0015aArr[length].s) {
                    fArr[0] = (float) (c0015aArr[length].f(d5) + (this.f736b[length].d(d5) * d6));
                    fArr[1] = (float) (this.f736b[length].g(d5) + (d6 * this.f736b[length].e(d5)));
                    return;
                } else {
                    c0015aArr[length].k(d2);
                    fArr[0] = (float) this.f736b[length].h();
                    fArr[1] = (float) this.f736b[length].i();
                    return;
                }
            }
        } else {
            C0015a[] c0015aArr2 = this.f736b;
            if (d2 < c0015aArr2[0].f741d) {
                d2 = c0015aArr2[0].f741d;
            } else if (d2 > c0015aArr2[c0015aArr2.length - 1].f742e) {
                d2 = c0015aArr2[c0015aArr2.length - 1].f742e;
            }
        }
        int i = 0;
        while (true) {
            C0015a[] c0015aArr3 = this.f736b;
            if (i >= c0015aArr3.length) {
                return;
            }
            if (d2 <= c0015aArr3[i].f742e) {
                if (c0015aArr3[i].s) {
                    fArr[0] = (float) c0015aArr3[i].f(d2);
                    fArr[1] = (float) this.f736b[i].g(d2);
                    return;
                } else {
                    c0015aArr3[i].k(d2);
                    fArr[0] = (float) this.f736b[i].h();
                    fArr[1] = (float) this.f736b[i].i();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.j.a.b
    public double f(double d2, int i) {
        C0015a[] c0015aArr = this.f736b;
        int i2 = 0;
        if (d2 < c0015aArr[0].f741d) {
            d2 = c0015aArr[0].f741d;
        }
        if (d2 > c0015aArr[c0015aArr.length - 1].f742e) {
            d2 = c0015aArr[c0015aArr.length - 1].f742e;
        }
        while (true) {
            C0015a[] c0015aArr2 = this.f736b;
            if (i2 >= c0015aArr2.length) {
                return Double.NaN;
            }
            if (d2 <= c0015aArr2[i2].f742e) {
                if (c0015aArr2[i2].s) {
                    return i == 0 ? c0015aArr2[i2].d(d2) : c0015aArr2[i2].e(d2);
                }
                c0015aArr2[i2].k(d2);
                return i == 0 ? this.f736b[i2].b() : this.f736b[i2].c();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.j.a.b
    public void g(double d2, double[] dArr) {
        C0015a[] c0015aArr = this.f736b;
        if (d2 < c0015aArr[0].f741d) {
            d2 = c0015aArr[0].f741d;
        } else if (d2 > c0015aArr[c0015aArr.length - 1].f742e) {
            d2 = c0015aArr[c0015aArr.length - 1].f742e;
        }
        int i = 0;
        while (true) {
            C0015a[] c0015aArr2 = this.f736b;
            if (i >= c0015aArr2.length) {
                return;
            }
            if (d2 <= c0015aArr2[i].f742e) {
                if (c0015aArr2[i].s) {
                    dArr[0] = c0015aArr2[i].d(d2);
                    dArr[1] = this.f736b[i].e(d2);
                    return;
                } else {
                    c0015aArr2[i].k(d2);
                    dArr[0] = this.f736b[i].b();
                    dArr[1] = this.f736b[i].c();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.j.a.b
    public double[] h() {
        return this.f735a;
    }
}
