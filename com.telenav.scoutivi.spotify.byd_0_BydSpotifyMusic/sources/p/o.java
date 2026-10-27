package p;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

/* JADX INFO: compiled from: TimeCycleSplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    protected static float f8796k = 6.2831855f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected b f8797a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f8801e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected String f8802f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected long f8805i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int f8798b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int[] f8799c = new int[10];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected float[][] f8800d = (float[][]) Array.newInstance((Class<?>) float.class, 10, 3);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected float[] f8803g = new float[3];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected boolean f8804h = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected float f8806j = Float.NaN;

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    protected static class a {
        static void a(int[] iArr, float[][] fArr, int i7, int i8) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i8;
            iArr2[1] = i7;
            int i9 = 2;
            while (i9 > 0) {
                int i10 = i9 - 1;
                int i11 = iArr2[i10];
                i9 = i10 - 1;
                int i12 = iArr2[i9];
                if (i11 < i12) {
                    int iB = b(iArr, fArr, i11, i12);
                    int i13 = i9 + 1;
                    iArr2[i9] = iB - 1;
                    int i14 = i13 + 1;
                    iArr2[i13] = i11;
                    int i15 = i14 + 1;
                    iArr2[i14] = i12;
                    i9 = i15 + 1;
                    iArr2[i15] = iB + 1;
                }
            }
        }

        private static int b(int[] iArr, float[][] fArr, int i7, int i8) {
            int i9 = iArr[i8];
            int i10 = i7;
            while (i7 < i8) {
                if (iArr[i7] <= i9) {
                    c(iArr, fArr, i10, i7);
                    i10++;
                }
                i7++;
            }
            c(iArr, fArr, i10, i8);
            return i10;
        }

        private static void c(int[] iArr, float[][] fArr, int i7, int i8) {
            int i9 = iArr[i7];
            iArr[i7] = iArr[i8];
            iArr[i8] = i9;
            float[] fArr2 = fArr[i7];
            fArr[i7] = fArr[i8];
            fArr[i8] = fArr2;
        }
    }

    protected float a(float f7) {
        float fAbs;
        switch (this.f8798b) {
            case 1:
                return Math.signum(f7 * f8796k);
            case 2:
                fAbs = Math.abs(f7);
                break;
            case 3:
                return (((f7 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                fAbs = ((f7 * 2.0f) + 1.0f) % 2.0f;
                break;
            case 5:
                return (float) Math.cos(f7 * f8796k);
            case 6:
                float fAbs2 = 1.0f - Math.abs(((f7 * 4.0f) % 4.0f) - 2.0f);
                fAbs = fAbs2 * fAbs2;
                break;
            default:
                return (float) Math.sin(f7 * f8796k);
        }
        return 1.0f - fAbs;
    }

    public void b(int i7, float f7, float f8, int i8, float f9) {
        int[] iArr = this.f8799c;
        int i9 = this.f8801e;
        iArr[i9] = i7;
        float[][] fArr = this.f8800d;
        fArr[i9][0] = f7;
        fArr[i9][1] = f8;
        fArr[i9][2] = f9;
        this.f8798b = Math.max(this.f8798b, i8);
        this.f8801e++;
    }

    protected void c(long j7) {
        this.f8805i = j7;
    }

    public void d(String str) {
        this.f8802f = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(int r12) {
        /*
            r11 = this;
            int r0 = r11.f8801e
            if (r0 != 0) goto L1d
            java.io.PrintStream r12 = java.lang.System.err
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Error no points added to "
            r0.append(r1)
            java.lang.String r1 = r11.f8802f
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r12.println(r0)
            return
        L1d:
            int[] r1 = r11.f8799c
            float[][] r2 = r11.f8800d
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            p.o.a.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r4
        L29:
            int[] r2 = r11.f8799c
            int r5 = r2.length
            if (r0 >= r5) goto L3b
            r5 = r2[r0]
            int r6 = r0 + (-1)
            r2 = r2[r6]
            if (r5 == r2) goto L38
            int r1 = r1 + 1
        L38:
            int r0 = r0 + 1
            goto L29
        L3b:
            if (r1 != 0) goto L3e
            r1 = r3
        L3e:
            double[] r0 = new double[r1]
            r2 = 3
            r5 = 2
            int[] r6 = new int[r5]
            r6[r3] = r2
            r6[r4] = r1
            java.lang.Class<double> r1 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r6)
            double[][] r1 = (double[][]) r1
            r2 = r4
            r6 = r2
        L52:
            int r7 = r11.f8801e
            if (r2 >= r7) goto L92
            if (r2 <= 0) goto L63
            int[] r7 = r11.f8799c
            r8 = r7[r2]
            int r9 = r2 + (-1)
            r7 = r7[r9]
            if (r8 != r7) goto L63
            goto L8f
        L63:
            int[] r7 = r11.f8799c
            r7 = r7[r2]
            double r7 = (double) r7
            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r7 = r7 * r9
            r0[r6] = r7
            r7 = r1[r6]
            float[][] r8 = r11.f8800d
            r9 = r8[r2]
            r9 = r9[r4]
            double r9 = (double) r9
            r7[r4] = r9
            r7 = r1[r6]
            r9 = r8[r2]
            r9 = r9[r3]
            double r9 = (double) r9
            r7[r3] = r9
            r7 = r1[r6]
            r8 = r8[r2]
            r8 = r8[r5]
            double r8 = (double) r8
            r7[r5] = r8
            int r6 = r6 + 1
        L8f:
            int r2 = r2 + 1
            goto L52
        L92:
            p.b r12 = p.b.a(r12, r0, r1)
            r11.f8797a = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.o.e(int):void");
    }

    public String toString() {
        String str = this.f8802f;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i7 = 0; i7 < this.f8801e; i7++) {
            str = str + "[" + this.f8799c[i7] + " , " + decimalFormat.format(this.f8800d[i7]) + "] ";
        }
        return str;
    }
}
