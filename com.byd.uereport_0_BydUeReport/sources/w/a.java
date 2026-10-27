package w;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double[] f8601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0135a[] f8602b;

    /* JADX INFO: renamed from: w.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArcCurveFit.java */
    public static class C0135a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static double[] f8603a = new double[91];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double[] f8604b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public double f8605c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double f8606d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f8607e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f8608f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public double f8609g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public double f8610h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public double f8611i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public double f8612j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public double f8613k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public double f8614l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public double f8615m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public double f8616n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public double f8617o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public double f8618p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public double f8619q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f8620r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public boolean f8621s;

        public C0135a(int i8, double d8, double d9, double d10, double d11, double d12, double d13) {
            this.f8621s = false;
            this.f8620r = i8 == 1;
            this.f8606d = d8;
            this.f8607e = d9;
            this.f8612j = 1.0d / (d9 - d8);
            if (3 == i8) {
                this.f8621s = true;
            }
            double d14 = d12 - d10;
            double d15 = d13 - d11;
            if (!this.f8621s && Math.abs(d14) >= 0.001d && Math.abs(d15) >= 0.001d) {
                this.f8604b = new double[101];
                boolean z7 = this.f8620r;
                this.f8613k = d14 * ((double) (z7 ? -1 : 1));
                this.f8614l = d15 * ((double) (z7 ? 1 : -1));
                this.f8615m = z7 ? d12 : d10;
                this.f8616n = z7 ? d11 : d13;
                a(d10, d11, d12, d13);
                this.f8617o = this.f8605c * this.f8612j;
                return;
            }
            this.f8621s = true;
            this.f8608f = d10;
            this.f8609g = d12;
            this.f8610h = d11;
            this.f8611i = d13;
            double dHypot = Math.hypot(d15, d14);
            this.f8605c = dHypot;
            this.f8617o = dHypot * this.f8612j;
            double d16 = this.f8607e;
            double d17 = this.f8606d;
            this.f8615m = d14 / (d16 - d17);
            this.f8616n = d15 / (d16 - d17);
        }

        public final void a(double d8, double d9, double d10, double d11) {
            double dHypot;
            double d12 = d10 - d8;
            double d13 = d9 - d11;
            int i8 = 0;
            double d14 = 0.0d;
            double d15 = 0.0d;
            double d16 = 0.0d;
            while (true) {
                if (i8 >= f8603a.length) {
                    break;
                }
                double d17 = d14;
                double radians = Math.toRadians((((double) i8) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d12;
                double dCos = Math.cos(radians) * d13;
                if (i8 > 0) {
                    dHypot = Math.hypot(dSin - d15, dCos - d16) + d17;
                    f8603a[i8] = dHypot;
                } else {
                    dHypot = d17;
                }
                i8++;
                d16 = dCos;
                d14 = dHypot;
                d15 = dSin;
            }
            double d18 = d14;
            this.f8605c = d18;
            int i9 = 0;
            while (true) {
                double[] dArr = f8603a;
                if (i9 >= dArr.length) {
                    break;
                }
                dArr[i9] = dArr[i9] / d18;
                i9++;
            }
            int i10 = 0;
            while (true) {
                if (i10 >= this.f8604b.length) {
                    return;
                }
                double length = ((double) i10) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(f8603a, length);
                if (iBinarySearch >= 0) {
                    this.f8604b[i10] = iBinarySearch / (f8603a.length - 1);
                } else if (iBinarySearch == -1) {
                    this.f8604b[i10] = 0.0d;
                } else {
                    int i11 = -iBinarySearch;
                    int i12 = i11 - 2;
                    double[] dArr2 = f8603a;
                    this.f8604b[i10] = (((double) i12) + ((length - dArr2[i12]) / (dArr2[i11 - 1] - dArr2[i12]))) / ((double) (dArr2.length - 1));
                }
                i10++;
            }
        }

        public double b() {
            double d8 = this.f8613k * this.f8619q;
            double dHypot = this.f8617o / Math.hypot(d8, (-this.f8614l) * this.f8618p);
            if (this.f8620r) {
                d8 = -d8;
            }
            return d8 * dHypot;
        }

        public double c() {
            double d8 = this.f8613k * this.f8619q;
            double d9 = (-this.f8614l) * this.f8618p;
            double dHypot = this.f8617o / Math.hypot(d8, d9);
            return this.f8620r ? (-d9) * dHypot : d9 * dHypot;
        }

        public double d(double d8) {
            return this.f8615m;
        }

        public double e(double d8) {
            return this.f8616n;
        }

        public double f(double d8) {
            double d9 = (d8 - this.f8606d) * this.f8612j;
            double d10 = this.f8608f;
            return d10 + (d9 * (this.f8609g - d10));
        }

        public double g(double d8) {
            double d9 = (d8 - this.f8606d) * this.f8612j;
            double d10 = this.f8610h;
            return d10 + (d9 * (this.f8611i - d10));
        }

        public double h() {
            return this.f8615m + (this.f8613k * this.f8618p);
        }

        public double i() {
            return this.f8616n + (this.f8614l * this.f8619q);
        }

        public double j(double d8) {
            if (d8 <= 0.0d) {
                return 0.0d;
            }
            if (d8 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f8604b;
            double length = d8 * ((double) (dArr.length - 1));
            int i8 = (int) length;
            return dArr[i8] + ((length - ((double) i8)) * (dArr[i8 + 1] - dArr[i8]));
        }

        public void k(double d8) {
            double dJ = j((this.f8620r ? this.f8607e - d8 : d8 - this.f8606d) * this.f8612j) * 1.5707963267948966d;
            this.f8618p = Math.sin(dJ);
            this.f8619q = Math.cos(dJ);
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
            r0.f8601a = r1
            int r2 = r1.length
            r3 = 1
            int r2 = r2 - r3
            w.a$a[] r2 = new w.a.C0135a[r2]
            r0.f8602b = r2
            r2 = 0
            r4 = r2
            r5 = r3
            r6 = r5
        L14:
            w.a$a[] r7 = r0.f8602b
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
            w.a$a r22 = new w.a$a
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
        throw new UnsupportedOperationException("Method not decompiled: w.a.<init>(int[], double[], double[][]):void");
    }

    @Override // w.b
    public double c(double d8, int i8) {
        C0135a[] c0135aArr = this.f8602b;
        int i9 = 0;
        if (d8 < c0135aArr[0].f8606d) {
            d8 = c0135aArr[0].f8606d;
        } else if (d8 > c0135aArr[c0135aArr.length - 1].f8607e) {
            d8 = c0135aArr[c0135aArr.length - 1].f8607e;
        }
        while (true) {
            C0135a[] c0135aArr2 = this.f8602b;
            if (i9 >= c0135aArr2.length) {
                return Double.NaN;
            }
            if (d8 <= c0135aArr2[i9].f8607e) {
                if (c0135aArr2[i9].f8621s) {
                    return i8 == 0 ? c0135aArr2[i9].f(d8) : c0135aArr2[i9].g(d8);
                }
                c0135aArr2[i9].k(d8);
                return i8 == 0 ? this.f8602b[i9].h() : this.f8602b[i9].i();
            }
            i9++;
        }
    }

    @Override // w.b
    public void d(double d8, double[] dArr) {
        C0135a[] c0135aArr = this.f8602b;
        if (d8 < c0135aArr[0].f8606d) {
            d8 = c0135aArr[0].f8606d;
        }
        if (d8 > c0135aArr[c0135aArr.length - 1].f8607e) {
            d8 = c0135aArr[c0135aArr.length - 1].f8607e;
        }
        int i8 = 0;
        while (true) {
            C0135a[] c0135aArr2 = this.f8602b;
            if (i8 >= c0135aArr2.length) {
                return;
            }
            if (d8 <= c0135aArr2[i8].f8607e) {
                if (c0135aArr2[i8].f8621s) {
                    dArr[0] = c0135aArr2[i8].f(d8);
                    dArr[1] = this.f8602b[i8].g(d8);
                    return;
                } else {
                    c0135aArr2[i8].k(d8);
                    dArr[0] = this.f8602b[i8].h();
                    dArr[1] = this.f8602b[i8].i();
                    return;
                }
            }
            i8++;
        }
    }

    @Override // w.b
    public void e(double d8, float[] fArr) {
        C0135a[] c0135aArr = this.f8602b;
        if (d8 < c0135aArr[0].f8606d) {
            d8 = c0135aArr[0].f8606d;
        } else if (d8 > c0135aArr[c0135aArr.length - 1].f8607e) {
            d8 = c0135aArr[c0135aArr.length - 1].f8607e;
        }
        int i8 = 0;
        while (true) {
            C0135a[] c0135aArr2 = this.f8602b;
            if (i8 >= c0135aArr2.length) {
                return;
            }
            if (d8 <= c0135aArr2[i8].f8607e) {
                if (c0135aArr2[i8].f8621s) {
                    fArr[0] = (float) c0135aArr2[i8].f(d8);
                    fArr[1] = (float) this.f8602b[i8].g(d8);
                    return;
                } else {
                    c0135aArr2[i8].k(d8);
                    fArr[0] = (float) this.f8602b[i8].h();
                    fArr[1] = (float) this.f8602b[i8].i();
                    return;
                }
            }
            i8++;
        }
    }

    @Override // w.b
    public double f(double d8, int i8) {
        C0135a[] c0135aArr = this.f8602b;
        int i9 = 0;
        if (d8 < c0135aArr[0].f8606d) {
            d8 = c0135aArr[0].f8606d;
        }
        if (d8 > c0135aArr[c0135aArr.length - 1].f8607e) {
            d8 = c0135aArr[c0135aArr.length - 1].f8607e;
        }
        while (true) {
            C0135a[] c0135aArr2 = this.f8602b;
            if (i9 >= c0135aArr2.length) {
                return Double.NaN;
            }
            if (d8 <= c0135aArr2[i9].f8607e) {
                if (c0135aArr2[i9].f8621s) {
                    return i8 == 0 ? c0135aArr2[i9].d(d8) : c0135aArr2[i9].e(d8);
                }
                c0135aArr2[i9].k(d8);
                return i8 == 0 ? this.f8602b[i9].b() : this.f8602b[i9].c();
            }
            i9++;
        }
    }

    @Override // w.b
    public void g(double d8, double[] dArr) {
        C0135a[] c0135aArr = this.f8602b;
        if (d8 < c0135aArr[0].f8606d) {
            d8 = c0135aArr[0].f8606d;
        } else if (d8 > c0135aArr[c0135aArr.length - 1].f8607e) {
            d8 = c0135aArr[c0135aArr.length - 1].f8607e;
        }
        int i8 = 0;
        while (true) {
            C0135a[] c0135aArr2 = this.f8602b;
            if (i8 >= c0135aArr2.length) {
                return;
            }
            if (d8 <= c0135aArr2[i8].f8607e) {
                if (c0135aArr2[i8].f8621s) {
                    dArr[0] = c0135aArr2[i8].d(d8);
                    dArr[1] = this.f8602b[i8].e(d8);
                    return;
                } else {
                    c0135aArr2[i8].k(d8);
                    dArr[0] = this.f8602b[i8].b();
                    dArr[1] = this.f8602b[i8].c();
                    return;
                }
            }
            i8++;
        }
    }

    @Override // w.b
    public double[] h() {
        return this.f8601a;
    }
}
