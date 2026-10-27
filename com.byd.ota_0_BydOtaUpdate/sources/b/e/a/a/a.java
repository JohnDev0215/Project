package b.e.a.a;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double[] f1184a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0014a[] f1185b;

    /* JADX INFO: renamed from: b.e.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArcCurveFit.java */
    public static class C0014a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static double[] f1186a = new double[91];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double[] f1187b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public double f1188c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double f1189d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f1190e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f1191f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public double f1192g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public double f1193h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public double f1194i;
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

        public C0014a(int i2, double d2, double d3, double d4, double d5, double d6, double d7) {
            this.s = false;
            this.r = i2 == 1;
            this.f1189d = d2;
            this.f1190e = d3;
            this.j = 1.0d / (d3 - d2);
            if (3 == i2) {
                this.s = true;
            }
            double d8 = d6 - d4;
            double d9 = d7 - d5;
            if (!this.s && Math.abs(d8) >= 0.001d && Math.abs(d9) >= 0.001d) {
                this.f1187b = new double[101];
                boolean z = this.r;
                this.k = d8 * ((double) (z ? -1 : 1));
                this.l = d9 * ((double) (z ? 1 : -1));
                this.m = z ? d6 : d4;
                this.n = z ? d5 : d7;
                a(d4, d5, d6, d7);
                this.o = this.f1188c * this.j;
                return;
            }
            this.s = true;
            this.f1191f = d4;
            this.f1192g = d6;
            this.f1193h = d5;
            this.f1194i = d7;
            double dHypot = Math.hypot(d9, d8);
            this.f1188c = dHypot;
            this.o = dHypot * this.j;
            double d10 = this.f1190e;
            double d11 = this.f1189d;
            this.m = d8 / (d10 - d11);
            this.n = d9 / (d10 - d11);
        }

        public final void a(double d2, double d3, double d4, double d5) {
            double dHypot;
            double d6 = d4 - d2;
            double d7 = d3 - d5;
            int i2 = 0;
            double d8 = 0.0d;
            double d9 = 0.0d;
            double d10 = 0.0d;
            while (true) {
                if (i2 >= f1186a.length) {
                    break;
                }
                double d11 = d8;
                double radians = Math.toRadians((((double) i2) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d6;
                double dCos = Math.cos(radians) * d7;
                if (i2 > 0) {
                    dHypot = Math.hypot(dSin - d9, dCos - d10) + d11;
                    f1186a[i2] = dHypot;
                } else {
                    dHypot = d11;
                }
                i2++;
                d10 = dCos;
                d8 = dHypot;
                d9 = dSin;
            }
            double d12 = d8;
            this.f1188c = d12;
            int i3 = 0;
            while (true) {
                double[] dArr = f1186a;
                if (i3 >= dArr.length) {
                    break;
                }
                dArr[i3] = dArr[i3] / d12;
                i3++;
            }
            int i4 = 0;
            while (true) {
                if (i4 >= this.f1187b.length) {
                    return;
                }
                double length = ((double) i4) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(f1186a, length);
                if (iBinarySearch >= 0) {
                    this.f1187b[i4] = iBinarySearch / (f1186a.length - 1);
                } else if (iBinarySearch == -1) {
                    this.f1187b[i4] = 0.0d;
                } else {
                    int i5 = -iBinarySearch;
                    int i6 = i5 - 2;
                    double[] dArr2 = f1186a;
                    this.f1187b[i4] = (((double) i6) + ((length - dArr2[i6]) / (dArr2[i5 - 1] - dArr2[i6]))) / ((double) (dArr2.length - 1));
                }
                i4++;
            }
        }

        public double b() {
            double d2 = this.k * this.q;
            double dHypot = this.o / Math.hypot(d2, (-this.l) * this.p);
            if (this.r) {
                d2 = -d2;
            }
            return d2 * dHypot;
        }

        public double c() {
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
            double d3 = (d2 - this.f1189d) * this.j;
            double d4 = this.f1191f;
            return d4 + (d3 * (this.f1192g - d4));
        }

        public double g(double d2) {
            double d3 = (d2 - this.f1189d) * this.j;
            double d4 = this.f1193h;
            return d4 + (d3 * (this.f1194i - d4));
        }

        public double h() {
            return this.m + (this.k * this.p);
        }

        public double i() {
            return this.n + (this.l * this.q);
        }

        public double j(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f1187b;
            double length = d2 * ((double) (dArr.length - 1));
            int i2 = (int) length;
            return dArr[i2] + ((length - ((double) i2)) * (dArr[i2 + 1] - dArr[i2]));
        }

        public void k(double d2) {
            double dJ = j((this.r ? this.f1190e - d2 : d2 - this.f1189d) * this.j) * 1.5707963267948966d;
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
            r0.f1184a = r1
            int r2 = r1.length
            r3 = 1
            int r2 = r2 - r3
            b.e.a.a.a$a[] r2 = new b.e.a.a.a.C0014a[r2]
            r0.f1185b = r2
            r2 = 0
            r4 = r2
            r5 = r3
            r6 = r5
        L14:
            b.e.a.a.a$a[] r7 = r0.f1185b
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
    public double c(double d2, int i2) {
        C0014a[] c0014aArr = this.f1185b;
        int i3 = 0;
        if (d2 < c0014aArr[0].f1189d) {
            d2 = c0014aArr[0].f1189d;
        } else if (d2 > c0014aArr[c0014aArr.length - 1].f1190e) {
            d2 = c0014aArr[c0014aArr.length - 1].f1190e;
        }
        while (true) {
            C0014a[] c0014aArr2 = this.f1185b;
            if (i3 >= c0014aArr2.length) {
                return Double.NaN;
            }
            if (d2 <= c0014aArr2[i3].f1190e) {
                if (c0014aArr2[i3].s) {
                    return i2 == 0 ? c0014aArr2[i3].f(d2) : c0014aArr2[i3].g(d2);
                }
                c0014aArr2[i3].k(d2);
                return i2 == 0 ? this.f1185b[i3].h() : this.f1185b[i3].i();
            }
            i3++;
        }
    }

    @Override // b.e.a.a.b
    public void d(double d2, double[] dArr) {
        C0014a[] c0014aArr = this.f1185b;
        if (d2 < c0014aArr[0].f1189d) {
            d2 = c0014aArr[0].f1189d;
        }
        if (d2 > c0014aArr[c0014aArr.length - 1].f1190e) {
            d2 = c0014aArr[c0014aArr.length - 1].f1190e;
        }
        int i2 = 0;
        while (true) {
            C0014a[] c0014aArr2 = this.f1185b;
            if (i2 >= c0014aArr2.length) {
                return;
            }
            if (d2 <= c0014aArr2[i2].f1190e) {
                if (c0014aArr2[i2].s) {
                    dArr[0] = c0014aArr2[i2].f(d2);
                    dArr[1] = this.f1185b[i2].g(d2);
                    return;
                } else {
                    c0014aArr2[i2].k(d2);
                    dArr[0] = this.f1185b[i2].h();
                    dArr[1] = this.f1185b[i2].i();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // b.e.a.a.b
    public void e(double d2, float[] fArr) {
        C0014a[] c0014aArr = this.f1185b;
        if (d2 < c0014aArr[0].f1189d) {
            d2 = c0014aArr[0].f1189d;
        } else if (d2 > c0014aArr[c0014aArr.length - 1].f1190e) {
            d2 = c0014aArr[c0014aArr.length - 1].f1190e;
        }
        int i2 = 0;
        while (true) {
            C0014a[] c0014aArr2 = this.f1185b;
            if (i2 >= c0014aArr2.length) {
                return;
            }
            if (d2 <= c0014aArr2[i2].f1190e) {
                if (c0014aArr2[i2].s) {
                    fArr[0] = (float) c0014aArr2[i2].f(d2);
                    fArr[1] = (float) this.f1185b[i2].g(d2);
                    return;
                } else {
                    c0014aArr2[i2].k(d2);
                    fArr[0] = (float) this.f1185b[i2].h();
                    fArr[1] = (float) this.f1185b[i2].i();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // b.e.a.a.b
    public double f(double d2, int i2) {
        C0014a[] c0014aArr = this.f1185b;
        int i3 = 0;
        if (d2 < c0014aArr[0].f1189d) {
            d2 = c0014aArr[0].f1189d;
        }
        if (d2 > c0014aArr[c0014aArr.length - 1].f1190e) {
            d2 = c0014aArr[c0014aArr.length - 1].f1190e;
        }
        while (true) {
            C0014a[] c0014aArr2 = this.f1185b;
            if (i3 >= c0014aArr2.length) {
                return Double.NaN;
            }
            if (d2 <= c0014aArr2[i3].f1190e) {
                if (c0014aArr2[i3].s) {
                    return i2 == 0 ? c0014aArr2[i3].d(d2) : c0014aArr2[i3].e(d2);
                }
                c0014aArr2[i3].k(d2);
                return i2 == 0 ? this.f1185b[i3].b() : this.f1185b[i3].c();
            }
            i3++;
        }
    }

    @Override // b.e.a.a.b
    public void g(double d2, double[] dArr) {
        C0014a[] c0014aArr = this.f1185b;
        if (d2 < c0014aArr[0].f1189d) {
            d2 = c0014aArr[0].f1189d;
        } else if (d2 > c0014aArr[c0014aArr.length - 1].f1190e) {
            d2 = c0014aArr[c0014aArr.length - 1].f1190e;
        }
        int i2 = 0;
        while (true) {
            C0014a[] c0014aArr2 = this.f1185b;
            if (i2 >= c0014aArr2.length) {
                return;
            }
            if (d2 <= c0014aArr2[i2].f1190e) {
                if (c0014aArr2[i2].s) {
                    dArr[0] = c0014aArr2[i2].d(d2);
                    dArr[1] = this.f1185b[i2].e(d2);
                    return;
                } else {
                    c0014aArr2[i2].k(d2);
                    dArr[0] = this.f1185b[i2].b();
                    dArr[1] = this.f1185b[i2].c();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // b.e.a.a.b
    public double[] h() {
        return this.f1184a;
    }
}
