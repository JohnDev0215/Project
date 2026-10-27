package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class i4 extends j4 {
    public final double[] a;
    public a[] b;

    /* JADX INFO: compiled from: ArcCurveFit.java */
    public static class a {
        public static double[] a = new double[91];
        public double[] b;
        public double c;
        public double d;
        public double e;
        public double f;
        public double g;
        public double h;
        public double i;
        public double j;
        public double k;
        public double l;
        public double m;
        public double n;
        public double o;
        public double p;
        public double q;
        public boolean r;
        public boolean s;

        public a(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            this.s = false;
            this.r = i == 1;
            this.d = d;
            this.e = d2;
            this.j = 1.0d / (d2 - d);
            if (3 == i) {
                this.s = true;
            }
            double d7 = d5 - d3;
            double d8 = d6 - d4;
            if (!this.s && Math.abs(d7) >= 0.001d && Math.abs(d8) >= 0.001d) {
                this.b = new double[101];
                boolean z = this.r;
                this.k = d7 * ((double) (z ? -1 : 1));
                this.l = d8 * ((double) (z ? 1 : -1));
                this.m = z ? d5 : d3;
                this.n = z ? d4 : d6;
                a(d3, d4, d5, d6);
                this.o = this.c * this.j;
                return;
            }
            this.s = true;
            this.f = d3;
            this.g = d5;
            this.h = d4;
            this.i = d6;
            double dHypot = Math.hypot(d8, d7);
            this.c = dHypot;
            this.o = dHypot * this.j;
            double d9 = this.e;
            double d10 = this.d;
            this.m = d7 / (d9 - d10);
            this.n = d8 / (d9 - d10);
        }

        public final void a(double d, double d2, double d3, double d4) {
            double dHypot;
            double d5 = d3 - d;
            double d6 = d2 - d4;
            int i = 0;
            double d7 = 0.0d;
            double d8 = 0.0d;
            double d9 = 0.0d;
            while (true) {
                if (i >= a.length) {
                    break;
                }
                double d10 = d7;
                double radians = Math.toRadians((((double) i) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d5;
                double dCos = Math.cos(radians) * d6;
                if (i > 0) {
                    dHypot = Math.hypot(dSin - d8, dCos - d9) + d10;
                    a[i] = dHypot;
                } else {
                    dHypot = d10;
                }
                i++;
                d9 = dCos;
                d7 = dHypot;
                d8 = dSin;
            }
            double d11 = d7;
            this.c = d11;
            int i2 = 0;
            while (true) {
                double[] dArr = a;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = dArr[i2] / d11;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.b.length) {
                    return;
                }
                double length = ((double) i3) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(a, length);
                if (iBinarySearch >= 0) {
                    this.b[i3] = iBinarySearch / (a.length - 1);
                } else if (iBinarySearch == -1) {
                    this.b[i3] = 0.0d;
                } else {
                    int i4 = -iBinarySearch;
                    int i5 = i4 - 2;
                    double[] dArr2 = a;
                    this.b[i3] = (((double) i5) + ((length - dArr2[i5]) / (dArr2[i4 - 1] - dArr2[i5]))) / ((double) (dArr2.length - 1));
                }
                i3++;
            }
        }

        public double b() {
            double d = this.k * this.q;
            double dHypot = this.o / Math.hypot(d, (-this.l) * this.p);
            if (this.r) {
                d = -d;
            }
            return d * dHypot;
        }

        public double c() {
            double d = this.k * this.q;
            double d2 = (-this.l) * this.p;
            double dHypot = this.o / Math.hypot(d, d2);
            return this.r ? (-d2) * dHypot : d2 * dHypot;
        }

        public double d(double d) {
            return this.m;
        }

        public double e(double d) {
            return this.n;
        }

        public double f(double d) {
            double d2 = (d - this.d) * this.j;
            double d3 = this.f;
            return d3 + (d2 * (this.g - d3));
        }

        public double g(double d) {
            double d2 = (d - this.d) * this.j;
            double d3 = this.h;
            return d3 + (d2 * (this.i - d3));
        }

        public double h() {
            return this.m + (this.k * this.p);
        }

        public double i() {
            return this.n + (this.l * this.q);
        }

        public double j(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.b;
            double length = d * ((double) (dArr.length - 1));
            int i = (int) length;
            return dArr[i] + ((length - ((double) i)) * (dArr[i + 1] - dArr[i]));
        }

        public void k(double d) {
            double dJ = j((this.r ? this.e - d : d - this.d) * this.j) * 1.5707963267948966d;
            this.p = Math.sin(dJ);
            this.q = Math.cos(dJ);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public i4(int[] r25, double[] r26, double[][] r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = r26
            r24.<init>()
            r0.a = r1
            int r2 = r1.length
            r3 = 1
            int r2 = r2 - r3
            i4$a[] r2 = new i4.a[r2]
            r0.b = r2
            r2 = 0
            r4 = r2
            r5 = r3
            r6 = r5
        L14:
            i4$a[] r7 = r0.b
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
            i4$a r22 = new i4$a
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.i4.<init>(int[], double[], double[][]):void");
    }

    @Override // defpackage.j4
    public double c(double d, int i) {
        a[] aVarArr = this.b;
        int i2 = 0;
        if (d < aVarArr[0].d) {
            d = aVarArr[0].d;
        } else if (d > aVarArr[aVarArr.length - 1].e) {
            d = aVarArr[aVarArr.length - 1].e;
        }
        while (true) {
            a[] aVarArr2 = this.b;
            if (i2 >= aVarArr2.length) {
                return Double.NaN;
            }
            if (d <= aVarArr2[i2].e) {
                if (aVarArr2[i2].s) {
                    return i == 0 ? aVarArr2[i2].f(d) : aVarArr2[i2].g(d);
                }
                aVarArr2[i2].k(d);
                return i == 0 ? this.b[i2].h() : this.b[i2].i();
            }
            i2++;
        }
    }

    @Override // defpackage.j4
    public void d(double d, double[] dArr) {
        a[] aVarArr = this.b;
        if (d < aVarArr[0].d) {
            d = aVarArr[0].d;
        }
        if (d > aVarArr[aVarArr.length - 1].e) {
            d = aVarArr[aVarArr.length - 1].e;
        }
        int i = 0;
        while (true) {
            a[] aVarArr2 = this.b;
            if (i >= aVarArr2.length) {
                return;
            }
            if (d <= aVarArr2[i].e) {
                if (aVarArr2[i].s) {
                    dArr[0] = aVarArr2[i].f(d);
                    dArr[1] = this.b[i].g(d);
                    return;
                } else {
                    aVarArr2[i].k(d);
                    dArr[0] = this.b[i].h();
                    dArr[1] = this.b[i].i();
                    return;
                }
            }
            i++;
        }
    }

    @Override // defpackage.j4
    public void e(double d, float[] fArr) {
        a[] aVarArr = this.b;
        if (d < aVarArr[0].d) {
            d = aVarArr[0].d;
        } else if (d > aVarArr[aVarArr.length - 1].e) {
            d = aVarArr[aVarArr.length - 1].e;
        }
        int i = 0;
        while (true) {
            a[] aVarArr2 = this.b;
            if (i >= aVarArr2.length) {
                return;
            }
            if (d <= aVarArr2[i].e) {
                if (aVarArr2[i].s) {
                    fArr[0] = (float) aVarArr2[i].f(d);
                    fArr[1] = (float) this.b[i].g(d);
                    return;
                } else {
                    aVarArr2[i].k(d);
                    fArr[0] = (float) this.b[i].h();
                    fArr[1] = (float) this.b[i].i();
                    return;
                }
            }
            i++;
        }
    }

    @Override // defpackage.j4
    public double f(double d, int i) {
        a[] aVarArr = this.b;
        int i2 = 0;
        if (d < aVarArr[0].d) {
            d = aVarArr[0].d;
        }
        if (d > aVarArr[aVarArr.length - 1].e) {
            d = aVarArr[aVarArr.length - 1].e;
        }
        while (true) {
            a[] aVarArr2 = this.b;
            if (i2 >= aVarArr2.length) {
                return Double.NaN;
            }
            if (d <= aVarArr2[i2].e) {
                if (aVarArr2[i2].s) {
                    return i == 0 ? aVarArr2[i2].d(d) : aVarArr2[i2].e(d);
                }
                aVarArr2[i2].k(d);
                return i == 0 ? this.b[i2].b() : this.b[i2].c();
            }
            i2++;
        }
    }

    @Override // defpackage.j4
    public void g(double d, double[] dArr) {
        a[] aVarArr = this.b;
        if (d < aVarArr[0].d) {
            d = aVarArr[0].d;
        } else if (d > aVarArr[aVarArr.length - 1].e) {
            d = aVarArr[aVarArr.length - 1].e;
        }
        int i = 0;
        while (true) {
            a[] aVarArr2 = this.b;
            if (i >= aVarArr2.length) {
                return;
            }
            if (d <= aVarArr2[i].e) {
                if (aVarArr2[i].s) {
                    dArr[0] = aVarArr2[i].d(d);
                    dArr[1] = this.b[i].e(d);
                    return;
                } else {
                    aVarArr2[i].k(d);
                    dArr[0] = this.b[i].b();
                    dArr[1] = this.b[i].c();
                    return;
                }
            }
            i++;
        }
    }

    @Override // defpackage.j4
    public double[] h() {
        return this.a;
    }
}
