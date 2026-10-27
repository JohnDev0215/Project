package b.e.a.a;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
class a extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final double[] f1771a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    C0030a[] f1772b;

    /* JADX INFO: renamed from: b.e.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArcCurveFit.java */
    private static class C0030a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static double[] f1773a = new double[91];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        double[] f1774b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        double f1775c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        double f1776d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        double f1777e;
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

        C0030a(int i, double d2, double d3, double d4, double d5, double d6, double d7) {
            this.s = false;
            this.r = i == 1;
            this.f1776d = d2;
            this.f1777e = d3;
            this.j = 1.0d / (d3 - d2);
            if (3 == i) {
                this.s = true;
            }
            double d8 = d6 - d4;
            double d9 = d7 - d5;
            if (!this.s && Math.abs(d8) >= 0.001d && Math.abs(d9) >= 0.001d) {
                this.f1774b = new double[101];
                boolean z = this.r;
                this.k = d8 * ((double) (z ? -1 : 1));
                this.l = d9 * ((double) (z ? 1 : -1));
                this.m = z ? d6 : d4;
                this.n = z ? d5 : d7;
                a(d4, d5, d6, d7);
                this.o = this.f1775c * this.j;
                return;
            }
            this.s = true;
            this.f = d4;
            this.g = d6;
            this.h = d5;
            this.i = d7;
            double dHypot = Math.hypot(d9, d8);
            this.f1775c = dHypot;
            this.o = dHypot * this.j;
            double d10 = this.f1777e;
            double d11 = this.f1776d;
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
                if (i >= f1773a.length) {
                    break;
                }
                double d11 = d8;
                double radians = Math.toRadians((((double) i) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d6;
                double dCos = Math.cos(radians) * d7;
                if (i > 0) {
                    dHypot = Math.hypot(dSin - d9, dCos - d10) + d11;
                    f1773a[i] = dHypot;
                } else {
                    dHypot = d11;
                }
                i++;
                d10 = dCos;
                d8 = dHypot;
                d9 = dSin;
            }
            double d12 = d8;
            this.f1775c = d12;
            int i2 = 0;
            while (true) {
                double[] dArr = f1773a;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = dArr[i2] / d12;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.f1774b.length) {
                    return;
                }
                double length = ((double) i3) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(f1773a, length);
                if (iBinarySearch >= 0) {
                    this.f1774b[i3] = iBinarySearch / (f1773a.length - 1);
                } else if (iBinarySearch == -1) {
                    this.f1774b[i3] = 0.0d;
                } else {
                    int i4 = -iBinarySearch;
                    int i5 = i4 - 2;
                    double[] dArr2 = f1773a;
                    this.f1774b[i3] = (((double) i5) + ((length - dArr2[i5]) / (dArr2[i4 - 1] - dArr2[i5]))) / ((double) (dArr2.length - 1));
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
            double d3 = (d2 - this.f1776d) * this.j;
            double d4 = this.f;
            return d4 + (d3 * (this.g - d4));
        }

        public double g(double d2) {
            double d3 = (d2 - this.f1776d) * this.j;
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
            double[] dArr = this.f1774b;
            double length = d2 * ((double) (dArr.length - 1));
            int i = (int) length;
            return dArr[i] + ((length - ((double) i)) * (dArr[i + 1] - dArr[i]));
        }

        void k(double d2) {
            double dJ = j((this.r ? this.f1777e - d2 : d2 - this.f1776d) * this.j) * 1.5707963267948966d;
            this.p = Math.sin(dJ);
            this.q = Math.cos(dJ);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
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
            r0.f1771a = r1
            int r2 = r1.length
            r3 = 1
            int r2 = r2 - r3
            b.e.a.a.a$a[] r2 = new b.e.a.a.a.C0030a[r2]
            r0.f1772b = r2
            r2 = 0
            r4 = r2
            r5 = r3
            r6 = r5
        L14:
            b.e.a.a.a$a[] r7 = r0.f1772b
            int r8 = r7.length
            if (r4 >= r8) goto L51
            r8 = r25[r4]
            r9 = 3
            r10 = 2
            if (r8 == 0) goto L2d
            if (r8 == r3) goto L2a
            if (r8 == r10) goto L28
            if (r8 == r9) goto L26
            goto L2e
        L26:
            if (r5 != r3) goto L2a
        L28:
            r5 = r10
            goto L2b
        L2a:
            r5 = r3
        L2b:
            r6 = r5
            goto L2e
        L2d:
            r6 = r9
        L2e:
            b.e.a.a.a$a r22 = new b.e.a.a.a$a
            r10 = r1[r4]
            int r23 = r4 + 1
            r12 = r1[r23]
            r8 = r27[r4]
            r14 = r8[r2]
            r8 = r27[r4]
            r16 = r8[r3]
            r8 = r27[r23]
            r18 = r8[r2]
            r8 = r27[r23]
            r20 = r8[r3]
            r8 = r22
            r9 = r6
            r8.<init>(r9, r10, r12, r14, r16, r18, r20)
            r7[r4] = r22
            r4 = r23
            goto L14
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.a.a.a.<init>(int[], double[], double[][]):void");
    }

    @Override // b.e.a.a.b
    public double c(double d2, int i) {
        C0030a[] c0030aArr = this.f1772b;
        int i2 = 0;
        if (d2 < c0030aArr[0].f1776d) {
            d2 = c0030aArr[0].f1776d;
        } else if (d2 > c0030aArr[c0030aArr.length - 1].f1777e) {
            d2 = c0030aArr[c0030aArr.length - 1].f1777e;
        }
        while (true) {
            C0030a[] c0030aArr2 = this.f1772b;
            if (i2 >= c0030aArr2.length) {
                return Double.NaN;
            }
            if (d2 <= c0030aArr2[i2].f1777e) {
                if (c0030aArr2[i2].s) {
                    return i == 0 ? c0030aArr2[i2].f(d2) : c0030aArr2[i2].g(d2);
                }
                c0030aArr2[i2].k(d2);
                return i == 0 ? this.f1772b[i2].h() : this.f1772b[i2].i();
            }
            i2++;
        }
    }

    @Override // b.e.a.a.b
    public void d(double d2, double[] dArr) {
        C0030a[] c0030aArr = this.f1772b;
        if (d2 < c0030aArr[0].f1776d) {
            d2 = c0030aArr[0].f1776d;
        }
        if (d2 > c0030aArr[c0030aArr.length - 1].f1777e) {
            d2 = c0030aArr[c0030aArr.length - 1].f1777e;
        }
        int i = 0;
        while (true) {
            C0030a[] c0030aArr2 = this.f1772b;
            if (i >= c0030aArr2.length) {
                return;
            }
            if (d2 <= c0030aArr2[i].f1777e) {
                if (c0030aArr2[i].s) {
                    dArr[0] = c0030aArr2[i].f(d2);
                    dArr[1] = this.f1772b[i].g(d2);
                    return;
                } else {
                    c0030aArr2[i].k(d2);
                    dArr[0] = this.f1772b[i].h();
                    dArr[1] = this.f1772b[i].i();
                    return;
                }
            }
            i++;
        }
    }

    @Override // b.e.a.a.b
    public void e(double d2, float[] fArr) {
        C0030a[] c0030aArr = this.f1772b;
        if (d2 < c0030aArr[0].f1776d) {
            d2 = c0030aArr[0].f1776d;
        } else if (d2 > c0030aArr[c0030aArr.length - 1].f1777e) {
            d2 = c0030aArr[c0030aArr.length - 1].f1777e;
        }
        int i = 0;
        while (true) {
            C0030a[] c0030aArr2 = this.f1772b;
            if (i >= c0030aArr2.length) {
                return;
            }
            if (d2 <= c0030aArr2[i].f1777e) {
                if (c0030aArr2[i].s) {
                    fArr[0] = (float) c0030aArr2[i].f(d2);
                    fArr[1] = (float) this.f1772b[i].g(d2);
                    return;
                } else {
                    c0030aArr2[i].k(d2);
                    fArr[0] = (float) this.f1772b[i].h();
                    fArr[1] = (float) this.f1772b[i].i();
                    return;
                }
            }
            i++;
        }
    }

    @Override // b.e.a.a.b
    public double f(double d2, int i) {
        C0030a[] c0030aArr = this.f1772b;
        int i2 = 0;
        if (d2 < c0030aArr[0].f1776d) {
            d2 = c0030aArr[0].f1776d;
        }
        if (d2 > c0030aArr[c0030aArr.length - 1].f1777e) {
            d2 = c0030aArr[c0030aArr.length - 1].f1777e;
        }
        while (true) {
            C0030a[] c0030aArr2 = this.f1772b;
            if (i2 >= c0030aArr2.length) {
                return Double.NaN;
            }
            if (d2 <= c0030aArr2[i2].f1777e) {
                if (c0030aArr2[i2].s) {
                    return i == 0 ? c0030aArr2[i2].d(d2) : c0030aArr2[i2].e(d2);
                }
                c0030aArr2[i2].k(d2);
                return i == 0 ? this.f1772b[i2].b() : this.f1772b[i2].c();
            }
            i2++;
        }
    }

    @Override // b.e.a.a.b
    public void g(double d2, double[] dArr) {
        C0030a[] c0030aArr = this.f1772b;
        if (d2 < c0030aArr[0].f1776d) {
            d2 = c0030aArr[0].f1776d;
        } else if (d2 > c0030aArr[c0030aArr.length - 1].f1777e) {
            d2 = c0030aArr[c0030aArr.length - 1].f1777e;
        }
        int i = 0;
        while (true) {
            C0030a[] c0030aArr2 = this.f1772b;
            if (i >= c0030aArr2.length) {
                return;
            }
            if (d2 <= c0030aArr2[i].f1777e) {
                if (c0030aArr2[i].s) {
                    dArr[0] = c0030aArr2[i].d(d2);
                    dArr[1] = this.f1772b[i].e(d2);
                    return;
                } else {
                    c0030aArr2[i].k(d2);
                    dArr[0] = this.f1772b[i].b();
                    dArr[1] = this.f1772b[i].c();
                    return;
                }
            }
            i++;
        }
    }

    @Override // b.e.a.a.b
    public double[] h() {
        return this.f1771a;
    }
}
