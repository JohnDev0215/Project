package p;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final double[] f8681a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    C0135a[] f8682b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f8683c = true;

    /* JADX INFO: renamed from: p.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArcCurveFit.java */
    private static class C0135a {

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        private static double[] f8684s = new double[91];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        double[] f8685a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        double f8686b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        double f8687c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        double f8688d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        double f8689e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        double f8690f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        double f8691g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        double f8692h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        double f8693i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        double f8694j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        double f8695k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        double f8696l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        double f8697m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        double f8698n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        double f8699o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        double f8700p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        boolean f8701q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        boolean f8702r;

        C0135a(int i7, double d7, double d8, double d9, double d10, double d11, double d12) {
            this.f8702r = false;
            this.f8701q = i7 == 1;
            this.f8687c = d7;
            this.f8688d = d8;
            this.f8693i = 1.0d / (d8 - d7);
            if (3 == i7) {
                this.f8702r = true;
            }
            double d13 = d11 - d9;
            double d14 = d12 - d10;
            if (!this.f8702r && Math.abs(d13) >= 0.001d && Math.abs(d14) >= 0.001d) {
                this.f8685a = new double[101];
                boolean z6 = this.f8701q;
                this.f8694j = d13 * ((double) (z6 ? -1 : 1));
                this.f8695k = d14 * ((double) (z6 ? 1 : -1));
                this.f8696l = z6 ? d11 : d9;
                this.f8697m = z6 ? d10 : d12;
                a(d9, d10, d11, d12);
                this.f8698n = this.f8686b * this.f8693i;
                return;
            }
            this.f8702r = true;
            this.f8689e = d9;
            this.f8690f = d11;
            this.f8691g = d10;
            this.f8692h = d12;
            double dHypot = Math.hypot(d14, d13);
            this.f8686b = dHypot;
            this.f8698n = dHypot * this.f8693i;
            double d15 = this.f8688d;
            double d16 = this.f8687c;
            this.f8696l = d13 / (d15 - d16);
            this.f8697m = d14 / (d15 - d16);
        }

        private void a(double d7, double d8, double d9, double d10) {
            double dHypot;
            double d11 = d9 - d7;
            double d12 = d8 - d10;
            int i7 = 0;
            double d13 = 0.0d;
            double d14 = 0.0d;
            double d15 = 0.0d;
            while (true) {
                if (i7 >= f8684s.length) {
                    break;
                }
                double d16 = d13;
                double radians = Math.toRadians((((double) i7) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d11;
                double dCos = Math.cos(radians) * d12;
                if (i7 > 0) {
                    dHypot = Math.hypot(dSin - d14, dCos - d15) + d16;
                    f8684s[i7] = dHypot;
                } else {
                    dHypot = d16;
                }
                i7++;
                d15 = dCos;
                d13 = dHypot;
                d14 = dSin;
            }
            double d17 = d13;
            this.f8686b = d17;
            int i8 = 0;
            while (true) {
                double[] dArr = f8684s;
                if (i8 >= dArr.length) {
                    break;
                }
                dArr[i8] = dArr[i8] / d17;
                i8++;
            }
            int i9 = 0;
            while (true) {
                if (i9 >= this.f8685a.length) {
                    return;
                }
                double length = ((double) i9) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(f8684s, length);
                if (iBinarySearch >= 0) {
                    this.f8685a[i9] = ((double) iBinarySearch) / ((double) (f8684s.length - 1));
                } else if (iBinarySearch == -1) {
                    this.f8685a[i9] = 0.0d;
                } else {
                    int i10 = -iBinarySearch;
                    int i11 = i10 - 2;
                    double[] dArr2 = f8684s;
                    this.f8685a[i9] = (((double) i11) + ((length - dArr2[i11]) / (dArr2[i10 - 1] - dArr2[i11]))) / ((double) (dArr2.length - 1));
                }
                i9++;
            }
        }

        double b() {
            double d7 = this.f8694j * this.f8700p;
            double dHypot = this.f8698n / Math.hypot(d7, (-this.f8695k) * this.f8699o);
            if (this.f8701q) {
                d7 = -d7;
            }
            return d7 * dHypot;
        }

        double c() {
            double d7 = this.f8694j * this.f8700p;
            double d8 = (-this.f8695k) * this.f8699o;
            double dHypot = this.f8698n / Math.hypot(d7, d8);
            return this.f8701q ? (-d8) * dHypot : d8 * dHypot;
        }

        public double d(double d7) {
            return this.f8696l;
        }

        public double e(double d7) {
            return this.f8697m;
        }

        public double f(double d7) {
            double d8 = (d7 - this.f8687c) * this.f8693i;
            double d9 = this.f8689e;
            return d9 + (d8 * (this.f8690f - d9));
        }

        public double g(double d7) {
            double d8 = (d7 - this.f8687c) * this.f8693i;
            double d9 = this.f8691g;
            return d9 + (d8 * (this.f8692h - d9));
        }

        double h() {
            return this.f8696l + (this.f8694j * this.f8699o);
        }

        double i() {
            return this.f8697m + (this.f8695k * this.f8700p);
        }

        double j(double d7) {
            if (d7 <= 0.0d) {
                return 0.0d;
            }
            if (d7 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f8685a;
            double length = d7 * ((double) (dArr.length - 1));
            int i7 = (int) length;
            return dArr[i7] + ((length - ((double) i7)) * (dArr[i7 + 1] - dArr[i7]));
        }

        void k(double d7) {
            double dJ = j((this.f8701q ? this.f8688d - d7 : d7 - this.f8687c) * this.f8693i) * 1.5707963267948966d;
            this.f8699o = Math.sin(dJ);
            this.f8700p = Math.cos(dJ);
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
            r0.f8683c = r2
            r0.f8681a = r1
            int r3 = r1.length
            int r3 = r3 - r2
            p.a$a[] r3 = new p.a.C0135a[r3]
            r0.f8682b = r3
            r3 = 0
            r5 = r2
            r6 = r5
            r4 = r3
        L16:
            p.a$a[] r7 = r0.f8682b
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
            p.a$a r22 = new p.a$a
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
        throw new UnsupportedOperationException("Method not decompiled: p.a.<init>(int[], double[], double[][]):void");
    }

    @Override // p.b
    public double c(double d7, int i7) {
        double d8;
        double dG;
        double dE;
        double dI;
        double dC;
        int i8 = 0;
        if (this.f8683c) {
            C0135a[] c0135aArr = this.f8682b;
            if (d7 < c0135aArr[0].f8687c) {
                double d9 = c0135aArr[0].f8687c;
                d8 = d7 - c0135aArr[0].f8687c;
                if (!c0135aArr[0].f8702r) {
                    c0135aArr[0].k(d9);
                    if (i7 == 0) {
                        dI = this.f8682b[0].h();
                        dC = this.f8682b[0].b();
                    } else {
                        dI = this.f8682b[0].i();
                        dC = this.f8682b[0].c();
                    }
                    return dI + (d8 * dC);
                }
                if (i7 == 0) {
                    dG = c0135aArr[0].f(d9);
                    dE = this.f8682b[0].d(d9);
                } else {
                    dG = c0135aArr[0].g(d9);
                    dE = this.f8682b[0].e(d9);
                }
            } else if (d7 > c0135aArr[c0135aArr.length - 1].f8688d) {
                double d10 = c0135aArr[c0135aArr.length - 1].f8688d;
                d8 = d7 - d10;
                int length = c0135aArr.length - 1;
                if (i7 == 0) {
                    dG = c0135aArr[length].f(d10);
                    dE = this.f8682b[length].d(d10);
                } else {
                    dG = c0135aArr[length].g(d10);
                    dE = this.f8682b[length].e(d10);
                }
            }
            return dG + (d8 * dE);
        }
        C0135a[] c0135aArr2 = this.f8682b;
        if (d7 < c0135aArr2[0].f8687c) {
            d7 = c0135aArr2[0].f8687c;
        } else if (d7 > c0135aArr2[c0135aArr2.length - 1].f8688d) {
            d7 = c0135aArr2[c0135aArr2.length - 1].f8688d;
        }
        while (true) {
            C0135a[] c0135aArr3 = this.f8682b;
            if (i8 >= c0135aArr3.length) {
                return Double.NaN;
            }
            if (d7 <= c0135aArr3[i8].f8688d) {
                if (c0135aArr3[i8].f8702r) {
                    return i7 == 0 ? c0135aArr3[i8].f(d7) : c0135aArr3[i8].g(d7);
                }
                c0135aArr3[i8].k(d7);
                return i7 == 0 ? this.f8682b[i8].h() : this.f8682b[i8].i();
            }
            i8++;
        }
    }

    @Override // p.b
    public void d(double d7, double[] dArr) {
        if (this.f8683c) {
            C0135a[] c0135aArr = this.f8682b;
            if (d7 < c0135aArr[0].f8687c) {
                double d8 = c0135aArr[0].f8687c;
                double d9 = d7 - c0135aArr[0].f8687c;
                if (c0135aArr[0].f8702r) {
                    dArr[0] = c0135aArr[0].f(d8) + (this.f8682b[0].d(d8) * d9);
                    dArr[1] = this.f8682b[0].g(d8) + (d9 * this.f8682b[0].e(d8));
                    return;
                } else {
                    c0135aArr[0].k(d8);
                    dArr[0] = this.f8682b[0].h() + (this.f8682b[0].b() * d9);
                    dArr[1] = this.f8682b[0].i() + (d9 * this.f8682b[0].c());
                    return;
                }
            }
            if (d7 > c0135aArr[c0135aArr.length - 1].f8688d) {
                double d10 = c0135aArr[c0135aArr.length - 1].f8688d;
                double d11 = d7 - d10;
                int length = c0135aArr.length - 1;
                if (c0135aArr[length].f8702r) {
                    dArr[0] = c0135aArr[length].f(d10) + (this.f8682b[length].d(d10) * d11);
                    dArr[1] = this.f8682b[length].g(d10) + (d11 * this.f8682b[length].e(d10));
                    return;
                } else {
                    c0135aArr[length].k(d7);
                    dArr[0] = this.f8682b[length].h() + (this.f8682b[length].b() * d11);
                    dArr[1] = this.f8682b[length].i() + (d11 * this.f8682b[length].c());
                    return;
                }
            }
        } else {
            C0135a[] c0135aArr2 = this.f8682b;
            if (d7 < c0135aArr2[0].f8687c) {
                d7 = c0135aArr2[0].f8687c;
            }
            if (d7 > c0135aArr2[c0135aArr2.length - 1].f8688d) {
                d7 = c0135aArr2[c0135aArr2.length - 1].f8688d;
            }
        }
        int i7 = 0;
        while (true) {
            C0135a[] c0135aArr3 = this.f8682b;
            if (i7 >= c0135aArr3.length) {
                return;
            }
            if (d7 <= c0135aArr3[i7].f8688d) {
                if (c0135aArr3[i7].f8702r) {
                    dArr[0] = c0135aArr3[i7].f(d7);
                    dArr[1] = this.f8682b[i7].g(d7);
                    return;
                } else {
                    c0135aArr3[i7].k(d7);
                    dArr[0] = this.f8682b[i7].h();
                    dArr[1] = this.f8682b[i7].i();
                    return;
                }
            }
            i7++;
        }
    }

    @Override // p.b
    public void e(double d7, float[] fArr) {
        if (this.f8683c) {
            C0135a[] c0135aArr = this.f8682b;
            if (d7 < c0135aArr[0].f8687c) {
                double d8 = c0135aArr[0].f8687c;
                double d9 = d7 - c0135aArr[0].f8687c;
                if (c0135aArr[0].f8702r) {
                    fArr[0] = (float) (c0135aArr[0].f(d8) + (this.f8682b[0].d(d8) * d9));
                    fArr[1] = (float) (this.f8682b[0].g(d8) + (d9 * this.f8682b[0].e(d8)));
                    return;
                } else {
                    c0135aArr[0].k(d8);
                    fArr[0] = (float) (this.f8682b[0].h() + (this.f8682b[0].b() * d9));
                    fArr[1] = (float) (this.f8682b[0].i() + (d9 * this.f8682b[0].c()));
                    return;
                }
            }
            if (d7 > c0135aArr[c0135aArr.length - 1].f8688d) {
                double d10 = c0135aArr[c0135aArr.length - 1].f8688d;
                double d11 = d7 - d10;
                int length = c0135aArr.length - 1;
                if (c0135aArr[length].f8702r) {
                    fArr[0] = (float) (c0135aArr[length].f(d10) + (this.f8682b[length].d(d10) * d11));
                    fArr[1] = (float) (this.f8682b[length].g(d10) + (d11 * this.f8682b[length].e(d10)));
                    return;
                } else {
                    c0135aArr[length].k(d7);
                    fArr[0] = (float) this.f8682b[length].h();
                    fArr[1] = (float) this.f8682b[length].i();
                    return;
                }
            }
        } else {
            C0135a[] c0135aArr2 = this.f8682b;
            if (d7 < c0135aArr2[0].f8687c) {
                d7 = c0135aArr2[0].f8687c;
            } else if (d7 > c0135aArr2[c0135aArr2.length - 1].f8688d) {
                d7 = c0135aArr2[c0135aArr2.length - 1].f8688d;
            }
        }
        int i7 = 0;
        while (true) {
            C0135a[] c0135aArr3 = this.f8682b;
            if (i7 >= c0135aArr3.length) {
                return;
            }
            if (d7 <= c0135aArr3[i7].f8688d) {
                if (c0135aArr3[i7].f8702r) {
                    fArr[0] = (float) c0135aArr3[i7].f(d7);
                    fArr[1] = (float) this.f8682b[i7].g(d7);
                    return;
                } else {
                    c0135aArr3[i7].k(d7);
                    fArr[0] = (float) this.f8682b[i7].h();
                    fArr[1] = (float) this.f8682b[i7].i();
                    return;
                }
            }
            i7++;
        }
    }

    @Override // p.b
    public void f(double d7, double[] dArr) {
        C0135a[] c0135aArr = this.f8682b;
        if (d7 < c0135aArr[0].f8687c) {
            d7 = c0135aArr[0].f8687c;
        } else if (d7 > c0135aArr[c0135aArr.length - 1].f8688d) {
            d7 = c0135aArr[c0135aArr.length - 1].f8688d;
        }
        int i7 = 0;
        while (true) {
            C0135a[] c0135aArr2 = this.f8682b;
            if (i7 >= c0135aArr2.length) {
                return;
            }
            if (d7 <= c0135aArr2[i7].f8688d) {
                if (c0135aArr2[i7].f8702r) {
                    dArr[0] = c0135aArr2[i7].d(d7);
                    dArr[1] = this.f8682b[i7].e(d7);
                    return;
                } else {
                    c0135aArr2[i7].k(d7);
                    dArr[0] = this.f8682b[i7].b();
                    dArr[1] = this.f8682b[i7].c();
                    return;
                }
            }
            i7++;
        }
    }

    @Override // p.b
    public double[] g() {
        return this.f8681a;
    }
}
