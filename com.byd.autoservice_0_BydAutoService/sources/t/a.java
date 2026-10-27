package t;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double[] f4926a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0060a[] f4927b;

    /* JADX INFO: renamed from: t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArcCurveFit.java */
    public static class C0060a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static double[] f4928a = new double[91];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double[] f4929b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public double f4930c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double f4931d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f4932e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f4933f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public double f4934g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public double f4935h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public double f4936i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public double f4937j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public double f4938k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public double f4939l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public double f4940m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public double f4941n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public double f4942o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public double f4943p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public double f4944q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f4945r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public boolean f4946s;

        public C0060a(int i3, double d3, double d4, double d5, double d6, double d7, double d8) {
            this.f4946s = false;
            this.f4945r = i3 == 1;
            this.f4931d = d3;
            this.f4932e = d4;
            this.f4937j = 1.0d / (d4 - d3);
            if (3 == i3) {
                this.f4946s = true;
            }
            double d9 = d7 - d5;
            double d10 = d8 - d6;
            if (!this.f4946s && Math.abs(d9) >= 0.001d && Math.abs(d10) >= 0.001d) {
                this.f4929b = new double[101];
                boolean z3 = this.f4945r;
                this.f4938k = d9 * ((double) (z3 ? -1 : 1));
                this.f4939l = d10 * ((double) (z3 ? 1 : -1));
                this.f4940m = z3 ? d7 : d5;
                this.f4941n = z3 ? d6 : d8;
                a(d5, d6, d7, d8);
                this.f4942o = this.f4930c * this.f4937j;
                return;
            }
            this.f4946s = true;
            this.f4933f = d5;
            this.f4934g = d7;
            this.f4935h = d6;
            this.f4936i = d8;
            double dHypot = Math.hypot(d10, d9);
            this.f4930c = dHypot;
            this.f4942o = dHypot * this.f4937j;
            double d11 = this.f4932e;
            double d12 = this.f4931d;
            this.f4940m = d9 / (d11 - d12);
            this.f4941n = d10 / (d11 - d12);
        }

        public final void a(double d3, double d4, double d5, double d6) {
            double dHypot;
            double d7 = d5 - d3;
            double d8 = d4 - d6;
            int i3 = 0;
            double d9 = 0.0d;
            double d10 = 0.0d;
            double d11 = 0.0d;
            while (true) {
                if (i3 >= f4928a.length) {
                    break;
                }
                double d12 = d9;
                double radians = Math.toRadians((((double) i3) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d7;
                double dCos = Math.cos(radians) * d8;
                if (i3 > 0) {
                    dHypot = Math.hypot(dSin - d10, dCos - d11) + d12;
                    f4928a[i3] = dHypot;
                } else {
                    dHypot = d12;
                }
                i3++;
                d11 = dCos;
                d9 = dHypot;
                d10 = dSin;
            }
            double d13 = d9;
            this.f4930c = d13;
            int i4 = 0;
            while (true) {
                double[] dArr = f4928a;
                if (i4 >= dArr.length) {
                    break;
                }
                dArr[i4] = dArr[i4] / d13;
                i4++;
            }
            int i5 = 0;
            while (true) {
                if (i5 >= this.f4929b.length) {
                    return;
                }
                double length = ((double) i5) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(f4928a, length);
                if (iBinarySearch >= 0) {
                    this.f4929b[i5] = iBinarySearch / (f4928a.length - 1);
                } else if (iBinarySearch == -1) {
                    this.f4929b[i5] = 0.0d;
                } else {
                    int i6 = -iBinarySearch;
                    int i7 = i6 - 2;
                    double[] dArr2 = f4928a;
                    this.f4929b[i5] = (((double) i7) + ((length - dArr2[i7]) / (dArr2[i6 - 1] - dArr2[i7]))) / ((double) (dArr2.length - 1));
                }
                i5++;
            }
        }

        public double b() {
            double d3 = this.f4938k * this.f4944q;
            double dHypot = this.f4942o / Math.hypot(d3, (-this.f4939l) * this.f4943p);
            if (this.f4945r) {
                d3 = -d3;
            }
            return d3 * dHypot;
        }

        public double c() {
            double d3 = this.f4938k * this.f4944q;
            double d4 = (-this.f4939l) * this.f4943p;
            double dHypot = this.f4942o / Math.hypot(d3, d4);
            return this.f4945r ? (-d4) * dHypot : d4 * dHypot;
        }

        public double d(double d3) {
            return this.f4940m;
        }

        public double e(double d3) {
            return this.f4941n;
        }

        public double f(double d3) {
            double d4 = (d3 - this.f4931d) * this.f4937j;
            double d5 = this.f4933f;
            return d5 + (d4 * (this.f4934g - d5));
        }

        public double g(double d3) {
            double d4 = (d3 - this.f4931d) * this.f4937j;
            double d5 = this.f4935h;
            return d5 + (d4 * (this.f4936i - d5));
        }

        public double h() {
            return this.f4940m + (this.f4938k * this.f4943p);
        }

        public double i() {
            return this.f4941n + (this.f4939l * this.f4944q);
        }

        public double j(double d3) {
            if (d3 <= 0.0d) {
                return 0.0d;
            }
            if (d3 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f4929b;
            double length = d3 * ((double) (dArr.length - 1));
            int i3 = (int) length;
            return dArr[i3] + ((length - ((double) i3)) * (dArr[i3 + 1] - dArr[i3]));
        }

        public void k(double d3) {
            double dJ = j((this.f4945r ? this.f4932e - d3 : d3 - this.f4931d) * this.f4937j) * 1.5707963267948966d;
            this.f4943p = Math.sin(dJ);
            this.f4944q = Math.cos(dJ);
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
            r0.f4926a = r1
            int r2 = r1.length
            r3 = 1
            int r2 = r2 - r3
            t.a$a[] r2 = new t.a.C0060a[r2]
            r0.f4927b = r2
            r2 = 0
            r4 = r2
            r5 = r3
            r6 = r5
        L14:
            t.a$a[] r7 = r0.f4927b
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
            t.a$a r22 = new t.a$a
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
        throw new UnsupportedOperationException("Method not decompiled: t.a.<init>(int[], double[], double[][]):void");
    }

    @Override // t.b
    public double c(double d3, int i3) {
        C0060a[] c0060aArr = this.f4927b;
        int i4 = 0;
        if (d3 < c0060aArr[0].f4931d) {
            d3 = c0060aArr[0].f4931d;
        } else if (d3 > c0060aArr[c0060aArr.length - 1].f4932e) {
            d3 = c0060aArr[c0060aArr.length - 1].f4932e;
        }
        while (true) {
            C0060a[] c0060aArr2 = this.f4927b;
            if (i4 >= c0060aArr2.length) {
                return Double.NaN;
            }
            if (d3 <= c0060aArr2[i4].f4932e) {
                if (c0060aArr2[i4].f4946s) {
                    return i3 == 0 ? c0060aArr2[i4].f(d3) : c0060aArr2[i4].g(d3);
                }
                c0060aArr2[i4].k(d3);
                return i3 == 0 ? this.f4927b[i4].h() : this.f4927b[i4].i();
            }
            i4++;
        }
    }

    @Override // t.b
    public void d(double d3, double[] dArr) {
        C0060a[] c0060aArr = this.f4927b;
        if (d3 < c0060aArr[0].f4931d) {
            d3 = c0060aArr[0].f4931d;
        }
        if (d3 > c0060aArr[c0060aArr.length - 1].f4932e) {
            d3 = c0060aArr[c0060aArr.length - 1].f4932e;
        }
        int i3 = 0;
        while (true) {
            C0060a[] c0060aArr2 = this.f4927b;
            if (i3 >= c0060aArr2.length) {
                return;
            }
            if (d3 <= c0060aArr2[i3].f4932e) {
                if (c0060aArr2[i3].f4946s) {
                    dArr[0] = c0060aArr2[i3].f(d3);
                    dArr[1] = this.f4927b[i3].g(d3);
                    return;
                } else {
                    c0060aArr2[i3].k(d3);
                    dArr[0] = this.f4927b[i3].h();
                    dArr[1] = this.f4927b[i3].i();
                    return;
                }
            }
            i3++;
        }
    }

    @Override // t.b
    public void e(double d3, float[] fArr) {
        C0060a[] c0060aArr = this.f4927b;
        if (d3 < c0060aArr[0].f4931d) {
            d3 = c0060aArr[0].f4931d;
        } else if (d3 > c0060aArr[c0060aArr.length - 1].f4932e) {
            d3 = c0060aArr[c0060aArr.length - 1].f4932e;
        }
        int i3 = 0;
        while (true) {
            C0060a[] c0060aArr2 = this.f4927b;
            if (i3 >= c0060aArr2.length) {
                return;
            }
            if (d3 <= c0060aArr2[i3].f4932e) {
                if (c0060aArr2[i3].f4946s) {
                    fArr[0] = (float) c0060aArr2[i3].f(d3);
                    fArr[1] = (float) this.f4927b[i3].g(d3);
                    return;
                } else {
                    c0060aArr2[i3].k(d3);
                    fArr[0] = (float) this.f4927b[i3].h();
                    fArr[1] = (float) this.f4927b[i3].i();
                    return;
                }
            }
            i3++;
        }
    }

    @Override // t.b
    public double f(double d3, int i3) {
        C0060a[] c0060aArr = this.f4927b;
        int i4 = 0;
        if (d3 < c0060aArr[0].f4931d) {
            d3 = c0060aArr[0].f4931d;
        }
        if (d3 > c0060aArr[c0060aArr.length - 1].f4932e) {
            d3 = c0060aArr[c0060aArr.length - 1].f4932e;
        }
        while (true) {
            C0060a[] c0060aArr2 = this.f4927b;
            if (i4 >= c0060aArr2.length) {
                return Double.NaN;
            }
            if (d3 <= c0060aArr2[i4].f4932e) {
                if (c0060aArr2[i4].f4946s) {
                    return i3 == 0 ? c0060aArr2[i4].d(d3) : c0060aArr2[i4].e(d3);
                }
                c0060aArr2[i4].k(d3);
                return i3 == 0 ? this.f4927b[i4].b() : this.f4927b[i4].c();
            }
            i4++;
        }
    }

    @Override // t.b
    public void g(double d3, double[] dArr) {
        C0060a[] c0060aArr = this.f4927b;
        if (d3 < c0060aArr[0].f4931d) {
            d3 = c0060aArr[0].f4931d;
        } else if (d3 > c0060aArr[c0060aArr.length - 1].f4932e) {
            d3 = c0060aArr[c0060aArr.length - 1].f4932e;
        }
        int i3 = 0;
        while (true) {
            C0060a[] c0060aArr2 = this.f4927b;
            if (i3 >= c0060aArr2.length) {
                return;
            }
            if (d3 <= c0060aArr2[i3].f4932e) {
                if (c0060aArr2[i3].f4946s) {
                    dArr[0] = c0060aArr2[i3].d(d3);
                    dArr[1] = this.f4927b[i3].e(d3);
                    return;
                } else {
                    c0060aArr2[i3].k(d3);
                    dArr[0] = this.f4927b[i3].b();
                    dArr[1] = this.f4927b[i3].c();
                    return;
                }
            }
            i3++;
        }
    }

    @Override // t.b
    public double[] h() {
        return this.f4926a;
    }
}
