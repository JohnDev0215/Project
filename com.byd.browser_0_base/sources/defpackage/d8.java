package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public final class d8 extends l20 {
    public final double[] a;
    public final a[] b;

    /* JADX INFO: compiled from: ArcCurveFit.java */
    public static class a {
        public static final double[] s = new double[91];
        public final double[] a;
        public double b;
        public final double c;
        public final double d;
        public final double e;
        public final double f;
        public final double g;
        public final double h;
        public final double i;
        public final double j;
        public final double k;
        public final double l;
        public final double m;
        public final double n;
        public double o;
        public double p;
        public final boolean q;
        public final boolean r;

        public a(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            double[] dArr;
            this.r = false;
            double d7 = d5 - d3;
            double d8 = d6 - d4;
            if (i == 1) {
                this.q = true;
            } else if (i == 4) {
                this.q = d8 > 0.0d;
            } else if (i != 5) {
                this.q = false;
            } else {
                this.q = d8 < 0.0d;
            }
            this.c = d;
            this.d = d2;
            double d9 = 1.0d / (d2 - d);
            this.i = d9;
            if (3 == i) {
                this.r = true;
            }
            if (this.r || Math.abs(d7) < 0.001d || Math.abs(d8) < 0.001d) {
                this.r = true;
                this.e = d3;
                this.f = d5;
                this.g = d4;
                this.h = d6;
                double dHypot = Math.hypot(d8, d7);
                this.b = dHypot;
                this.n = dHypot * d9;
                this.l = d7 / (d2 - d);
                this.m = d8 / (d2 - d);
                return;
            }
            this.a = new double[101];
            boolean z = this.q;
            this.j = ((double) (z ? -1 : 1)) * d7;
            this.k = d8 * ((double) (z ? 1 : -1));
            this.l = z ? d5 : d3;
            this.m = z ? d4 : d6;
            double d10 = d4 - d6;
            double dHypot2 = 0.0d;
            double d11 = 0.0d;
            double d12 = 0.0d;
            int i2 = 0;
            while (true) {
                dArr = s;
                if (i2 >= 91) {
                    break;
                }
                double d13 = d10;
                double radians = Math.toRadians((((double) i2) * 90.0d) / ((double) 90));
                double dSin = Math.sin(radians) * d7;
                double dCos = Math.cos(radians) * d13;
                if (i2 > 0) {
                    dHypot2 += Math.hypot(dSin - d11, dCos - d12);
                    dArr[i2] = dHypot2;
                }
                i2++;
                d12 = dCos;
                d11 = dSin;
                d10 = d13;
            }
            this.b = dHypot2;
            for (int i3 = 0; i3 < 91; i3++) {
                dArr[i3] = dArr[i3] / dHypot2;
            }
            int i4 = 0;
            while (true) {
                double[] dArr2 = this.a;
                if (i4 >= dArr2.length) {
                    this.n = this.b * this.i;
                    return;
                }
                double length = ((double) i4) / ((double) (dArr2.length - 1));
                int iBinarySearch = Arrays.binarySearch(dArr, length);
                if (iBinarySearch >= 0) {
                    dArr2[i4] = ((double) iBinarySearch) / ((double) 90);
                } else if (iBinarySearch == -1) {
                    dArr2[i4] = 0.0d;
                } else {
                    int i5 = -iBinarySearch;
                    int i6 = i5 - 2;
                    double d14 = dArr[i6];
                    dArr2[i4] = (((length - d14) / (dArr[i5 - 1] - d14)) + ((double) i6)) / ((double) 90);
                }
                i4++;
            }
        }

        public final double a() {
            double d = this.j * this.p;
            double dHypot = this.n / Math.hypot(d, (-this.k) * this.o);
            if (this.q) {
                d = -d;
            }
            return d * dHypot;
        }

        public final double b() {
            double d = this.j * this.p;
            double d2 = (-this.k) * this.o;
            double dHypot = this.n / Math.hypot(d, d2);
            return this.q ? (-d2) * dHypot : d2 * dHypot;
        }

        public final double c(double d) {
            double d2 = (d - this.c) * this.i;
            double d3 = this.f;
            double d4 = this.e;
            return ((d3 - d4) * d2) + d4;
        }

        public final double d(double d) {
            double d2 = (d - this.c) * this.i;
            double d3 = this.h;
            double d4 = this.g;
            return ((d3 - d4) * d2) + d4;
        }

        public final double e() {
            return (this.j * this.o) + this.l;
        }

        public final double f() {
            return (this.k * this.p) + this.m;
        }

        public final void g(double d) {
            double d2 = (this.q ? this.d - d : d - this.c) * this.i;
            double d3 = 0.0d;
            if (d2 > 0.0d) {
                d3 = 1.0d;
                if (d2 < 1.0d) {
                    double[] dArr = this.a;
                    double length = d2 * ((double) (dArr.length - 1));
                    int i = (int) length;
                    double d4 = dArr[i];
                    d3 = ((dArr[i + 1] - d4) * (length - ((double) i))) + d4;
                }
            }
            double d5 = d3 * 1.5707963267948966d;
            this.o = Math.sin(d5);
            this.p = Math.cos(d5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0033 A[PHI: r9
  0x0033: PHI (r9v1 int) = (r9v0 int), (r9v3 int), (r9v4 int) binds: [B:6:0x001c, B:12:0x0026, B:14:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public d8(int[] r25, double[] r26, double[][] r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = r26
            r24.<init>()
            r0.a = r1
            int r2 = r1.length
            r3 = 1
            int r2 = r2 - r3
            d8$a[] r2 = new d8.a[r2]
            r0.b = r2
            r2 = 0
            r4 = r2
            r5 = r3
            r6 = r5
        L14:
            d8$a[] r7 = r0.b
            int r8 = r7.length
            if (r4 >= r8) goto L53
            r8 = r25[r4]
            r9 = 3
            if (r8 == 0) goto L33
            if (r8 == r3) goto L30
            r10 = 2
            if (r8 == r10) goto L2e
            if (r8 == r9) goto L2c
            r9 = 4
            if (r8 == r9) goto L33
            r9 = 5
            if (r8 == r9) goto L33
            goto L34
        L2c:
            if (r5 != r3) goto L30
        L2e:
            r5 = r10
            goto L31
        L30:
            r5 = r3
        L31:
            r6 = r5
            goto L34
        L33:
            r6 = r9
        L34:
            d8$a r22 = new d8$a
            r10 = r1[r4]
            int r23 = r4 + 1
            r12 = r1[r23]
            r8 = r27[r4]
            r14 = r8[r2]
            r16 = r8[r3]
            r8 = r27[r23]
            r18 = r8[r2]
            r20 = r8[r3]
            r8 = r22
            r9 = r6
            r8.<init>(r9, r10, r12, r14, r16, r18, r20)
            r7[r4] = r22
            r4 = r23
            goto L14
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d8.<init>(int[], double[], double[][]):void");
    }

    @Override // defpackage.l20
    public final double b(double d) {
        a[] aVarArr = this.b;
        a aVar = aVarArr[0];
        double d2 = aVar.c;
        if (d < d2) {
            double d3 = d - d2;
            if (aVar.r) {
                return (d3 * aVarArr[0].l) + aVar.c(d2);
            }
            aVar.g(d2);
            return (aVarArr[0].a() * d3) + aVarArr[0].e();
        }
        if (d > aVarArr[aVarArr.length - 1].d) {
            double d4 = aVarArr[aVarArr.length - 1].d;
            double d5 = d - d4;
            int length = aVarArr.length - 1;
            return (d5 * aVarArr[length].l) + aVarArr[length].c(d4);
        }
        for (int i = 0; i < aVarArr.length; i++) {
            a aVar2 = aVarArr[i];
            if (d <= aVar2.d) {
                if (aVar2.r) {
                    return aVar2.c(d);
                }
                aVar2.g(d);
                return aVarArr[i].e();
            }
        }
        return Double.NaN;
    }

    @Override // defpackage.l20
    public final void c(double d, double[] dArr) {
        a[] aVarArr = this.b;
        a aVar = aVarArr[0];
        double d2 = aVar.c;
        if (d < d2) {
            double d3 = d - d2;
            if (aVar.r) {
                double dC = aVar.c(d2);
                a aVar2 = aVarArr[0];
                dArr[0] = (aVar2.l * d3) + dC;
                dArr[1] = (d3 * aVarArr[0].m) + aVar2.d(d2);
                return;
            }
            aVar.g(d2);
            dArr[0] = (aVarArr[0].a() * d3) + aVarArr[0].e();
            dArr[1] = (aVarArr[0].b() * d3) + aVarArr[0].f();
            return;
        }
        if (d <= aVarArr[aVarArr.length - 1].d) {
            for (int i = 0; i < aVarArr.length; i++) {
                a aVar3 = aVarArr[i];
                if (d <= aVar3.d) {
                    if (aVar3.r) {
                        dArr[0] = aVar3.c(d);
                        dArr[1] = aVarArr[i].d(d);
                        return;
                    } else {
                        aVar3.g(d);
                        dArr[0] = aVarArr[i].e();
                        dArr[1] = aVarArr[i].f();
                        return;
                    }
                }
            }
            return;
        }
        double d4 = aVarArr[aVarArr.length - 1].d;
        double d5 = d - d4;
        int length = aVarArr.length - 1;
        a aVar4 = aVarArr[length];
        if (aVar4.r) {
            double dC2 = aVar4.c(d4);
            a aVar5 = aVarArr[length];
            dArr[0] = (aVar5.l * d5) + dC2;
            dArr[1] = (d5 * aVarArr[length].m) + aVar5.d(d4);
            return;
        }
        aVar4.g(d);
        dArr[0] = (aVarArr[length].a() * d5) + aVarArr[length].e();
        dArr[1] = (aVarArr[length].b() * d5) + aVarArr[length].f();
    }

    @Override // defpackage.l20
    public final void d(double d, float[] fArr) {
        a[] aVarArr = this.b;
        a aVar = aVarArr[0];
        double d2 = aVar.c;
        if (d < d2) {
            double d3 = d - d2;
            if (aVar.r) {
                double dC = aVar.c(d2);
                a aVar2 = aVarArr[0];
                fArr[0] = (float) ((aVar2.l * d3) + dC);
                fArr[1] = (float) ((d3 * aVarArr[0].m) + aVar2.d(d2));
                return;
            }
            aVar.g(d2);
            fArr[0] = (float) ((aVarArr[0].a() * d3) + aVarArr[0].e());
            fArr[1] = (float) ((aVarArr[0].b() * d3) + aVarArr[0].f());
            return;
        }
        if (d <= aVarArr[aVarArr.length - 1].d) {
            for (int i = 0; i < aVarArr.length; i++) {
                a aVar3 = aVarArr[i];
                if (d <= aVar3.d) {
                    if (aVar3.r) {
                        fArr[0] = (float) aVar3.c(d);
                        fArr[1] = (float) aVarArr[i].d(d);
                        return;
                    } else {
                        aVar3.g(d);
                        fArr[0] = (float) aVarArr[i].e();
                        fArr[1] = (float) aVarArr[i].f();
                        return;
                    }
                }
            }
            return;
        }
        double d4 = aVarArr[aVarArr.length - 1].d;
        double d5 = d - d4;
        int length = aVarArr.length - 1;
        a aVar4 = aVarArr[length];
        if (!aVar4.r) {
            aVar4.g(d);
            fArr[0] = (float) aVarArr[length].e();
            fArr[1] = (float) aVarArr[length].f();
        } else {
            double dC2 = aVar4.c(d4);
            a aVar5 = aVarArr[length];
            fArr[0] = (float) ((aVar5.l * d5) + dC2);
            fArr[1] = (float) ((d5 * aVarArr[length].m) + aVar5.d(d4));
        }
    }

    @Override // defpackage.l20
    public final double e(double d) {
        a[] aVarArr = this.b;
        double d2 = aVarArr[0].c;
        if (d < d2) {
            d = d2;
        }
        if (d > aVarArr[aVarArr.length - 1].d) {
            d = aVarArr[aVarArr.length - 1].d;
        }
        for (int i = 0; i < aVarArr.length; i++) {
            a aVar = aVarArr[i];
            if (d <= aVar.d) {
                if (aVar.r) {
                    return aVar.l;
                }
                aVar.g(d);
                return aVarArr[i].a();
            }
        }
        return Double.NaN;
    }

    @Override // defpackage.l20
    public final void f(double d, double[] dArr) {
        a[] aVarArr = this.b;
        double d2 = aVarArr[0].c;
        if (d < d2) {
            d = d2;
        } else if (d > aVarArr[aVarArr.length - 1].d) {
            d = aVarArr[aVarArr.length - 1].d;
        }
        for (int i = 0; i < aVarArr.length; i++) {
            a aVar = aVarArr[i];
            if (d <= aVar.d) {
                if (aVar.r) {
                    dArr[0] = aVar.l;
                    dArr[1] = aVar.m;
                    return;
                } else {
                    aVar.g(d);
                    dArr[0] = aVarArr[i].a();
                    dArr[1] = aVarArr[i].b();
                    return;
                }
            }
        }
    }

    @Override // defpackage.l20
    public final double[] g() {
        return this.a;
    }
}
