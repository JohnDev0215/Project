package defpackage;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: compiled from: KeyCycleOscillator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d41 {
    public a a;
    public String b;
    public int c = 0;
    public String d = null;
    public int e = 0;
    public final ArrayList<b> f = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class a {
        public final gt1 a;
        public final float[] b;
        public final double[] c;
        public final float[] d;
        public final float[] e;
        public final float[] f;
        public l20 g;
        public double[] h;
        public double[] i;

        public a(int i, int i2, String str) {
            long j;
            char c;
            gt1 gt1Var = new gt1();
            this.a = gt1Var;
            gt1Var.e = i;
            if (str != null) {
                double[] dArr = new double[str.length() / 2];
                int iIndexOf = str.indexOf(40) + 1;
                int iIndexOf2 = str.indexOf(44, iIndexOf);
                char c2 = 0;
                int i3 = 0;
                while (iIndexOf2 != -1) {
                    dArr[i3] = Double.parseDouble(str.substring(iIndexOf, iIndexOf2).trim());
                    iIndexOf = iIndexOf2 + 1;
                    iIndexOf2 = str.indexOf(44, iIndexOf);
                    i3++;
                }
                dArr[i3] = Double.parseDouble(str.substring(iIndexOf, str.indexOf(41, iIndexOf)).trim());
                double[] dArrCopyOf = Arrays.copyOf(dArr, i3 + 1);
                int length = (dArrCopyOf.length * 3) - 2;
                int length2 = dArrCopyOf.length - 1;
                double d = 1.0d / ((double) length2);
                double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, 1);
                double[] dArr3 = new double[length];
                int i4 = 0;
                while (i4 < dArrCopyOf.length) {
                    double d2 = dArrCopyOf[i4];
                    int i5 = i4 + length2;
                    dArr2[i5][c2] = d2;
                    double d3 = ((double) i4) * d;
                    dArr3[i5] = d3;
                    if (i4 > 0) {
                        int i6 = (length2 * 2) + i4;
                        j = 4607182418800017408L;
                        c = 0;
                        dArr2[i6][0] = d2 + 1.0d;
                        dArr3[i6] = d3 + 1.0d;
                        int i7 = i4 - 1;
                        dArr2[i7][0] = (d2 - 1.0d) - d;
                        dArr3[i7] = (d3 - 1.0d) - d;
                    } else {
                        j = 4607182418800017408L;
                        c = 0;
                    }
                    i4++;
                    c2 = c;
                }
                gt1Var.d = new mi1(dArr3, dArr2);
            }
            this.b = new float[i2];
            this.c = new double[i2];
            this.d = new float[i2];
            this.e = new float[i2];
            this.f = new float[i2];
            float[] fArr = new float[i2];
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class b {
        public final int a;
        public final float b;
        public final float c;
        public final float d;
        public final float e;

        public b(int i, float f, float f2, float f3, float f4) {
            this.a = i;
            this.b = f4;
            this.c = f2;
            this.d = f;
            this.e = f3;
        }
    }

    public final float a(float f) {
        a aVar = this.a;
        l20 l20Var = aVar.g;
        if (l20Var != null) {
            l20Var.c(f, aVar.h);
        } else {
            double[] dArr = aVar.h;
            dArr[0] = aVar.e[0];
            dArr[1] = aVar.f[0];
            dArr[2] = aVar.b[0];
        }
        double[] dArr2 = aVar.h;
        return (float) ((aVar.a.c(f, dArr2[1]) * aVar.h[2]) + dArr2[0]);
    }

    public final float b(float f) {
        double dE;
        double d;
        double dSignum;
        a aVar = this.a;
        l20 l20Var = aVar.g;
        double d2 = 0.0d;
        if (l20Var != null) {
            double d3 = f;
            l20Var.f(d3, aVar.i);
            aVar.g.c(d3, aVar.h);
        } else {
            double[] dArr = aVar.i;
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
            dArr[2] = 0.0d;
        }
        double d4 = f;
        double d5 = aVar.h[1];
        gt1 gt1Var = aVar.a;
        double dC = gt1Var.c(d4, d5);
        double d6 = aVar.h[1];
        double d7 = aVar.i[1];
        double dB = gt1Var.b(d4) + d6;
        if (d4 <= 0.0d) {
            d4 = 1.0E-5d;
        } else if (d4 >= 1.0d) {
            d4 = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(gt1Var.b, d4);
        if (iBinarySearch <= 0) {
            if (iBinarySearch != 0) {
                int i = (-iBinarySearch) - 1;
                float[] fArr = gt1Var.a;
                float f2 = fArr[i];
                int i2 = i - 1;
                float f3 = fArr[i2];
                double d8 = f2 - f3;
                double[] dArr2 = gt1Var.b;
                double d9 = dArr2[i];
                double d10 = dArr2[i2];
                double d11 = d8 / (d9 - d10);
                d2 = (((double) f3) - (d11 * d10)) + (d4 * d11);
            } else {
                d2 = 0.0d;
            }
        }
        double d12 = d2 + d7;
        switch (gt1Var.e) {
            case 1:
                dE = 0.0d;
                break;
            case 2:
                d = d12 * 4.0d;
                dSignum = Math.signum((((dB * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                dE = d * dSignum;
                break;
            case 3:
                dE = d12 * 2.0d;
                break;
            case 4:
                dE = (-d12) * 2.0d;
                break;
            case 5:
                d = d12 * (-6.283185307179586d);
                dSignum = Math.sin(dB * 6.283185307179586d);
                dE = d * dSignum;
                break;
            case 6:
                dE = d12 * 4.0d * ((((dB * 4.0d) + 2.0d) % 4.0d) - 2.0d);
                break;
            case 7:
                dE = gt1Var.d.e(dB % 1.0d);
                break;
            default:
                d = d12 * 6.283185307179586d;
                dSignum = Math.cos(dB * 6.283185307179586d);
                dE = d * dSignum;
                break;
        }
        double[] dArr3 = aVar.i;
        return (float) ((dE * aVar.h[2]) + (dC * dArr3[2]) + dArr3[0]);
    }

    public final void d() {
        int i;
        ArrayList<b> arrayList = this.f;
        int size = arrayList.size();
        if (size == 0) {
            return;
        }
        Collections.sort(arrayList, new c41());
        double[] dArr = new double[size];
        char c = 0;
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, 3);
        this.a = new a(this.c, size, this.d);
        int i2 = 0;
        for (b bVar : arrayList) {
            float f = bVar.d;
            dArr[i2] = ((double) f) * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f2 = bVar.b;
            dArr3[c] = f2;
            float f3 = bVar.c;
            dArr3[1] = f3;
            float f4 = bVar.e;
            dArr3[2] = f4;
            a aVar = this.a;
            aVar.c[i2] = ((double) bVar.a) / 100.0d;
            aVar.d[i2] = f;
            aVar.e[i2] = f3;
            aVar.f[i2] = f4;
            aVar.b[i2] = f2;
            i2++;
            c = 0;
        }
        a aVar2 = this.a;
        double[] dArr4 = aVar2.c;
        double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, dArr4.length, 3);
        float[] fArr = aVar2.b;
        aVar2.h = new double[fArr.length + 2];
        aVar2.i = new double[fArr.length + 2];
        double d = dArr4[0];
        float[] fArr2 = aVar2.d;
        gt1 gt1Var = aVar2.a;
        if (d > 0.0d) {
            gt1Var.a(0.0d, fArr2[0]);
        }
        int length = dArr4.length - 1;
        if (dArr4[length] < 1.0d) {
            gt1Var.a(1.0d, fArr2[length]);
        }
        for (int i3 = 0; i3 < dArr5.length; i3++) {
            double[] dArr6 = dArr5[i3];
            dArr6[0] = aVar2.e[i3];
            dArr6[1] = aVar2.f[i3];
            dArr6[2] = fArr[i3];
            gt1Var.a(dArr4[i3], fArr2[i3]);
        }
        int i4 = 0;
        double d2 = 0.0d;
        while (true) {
            float[] fArr3 = gt1Var.a;
            if (i4 >= fArr3.length) {
                break;
            }
            d2 += (double) fArr3[i4];
            i4++;
        }
        int i5 = 1;
        double d3 = 0.0d;
        while (true) {
            float[] fArr4 = gt1Var.a;
            if (i5 >= fArr4.length) {
                break;
            }
            int i6 = i5 - 1;
            float f5 = (fArr4[i6] + fArr4[i5]) / 2.0f;
            double[] dArr7 = gt1Var.b;
            d3 = ((dArr7[i5] - dArr7[i6]) * ((double) f5)) + d3;
            i5++;
        }
        int i7 = 0;
        while (true) {
            float[] fArr5 = gt1Var.a;
            if (i7 >= fArr5.length) {
                break;
            }
            fArr5[i7] = fArr5[i7] * ((float) (d2 / d3));
            i7++;
        }
        gt1Var.c[0] = 0.0d;
        int i8 = 1;
        while (true) {
            float[] fArr6 = gt1Var.a;
            if (i8 >= fArr6.length) {
                break;
            }
            int i9 = i8 - 1;
            float f6 = (fArr6[i9] + fArr6[i8]) / 2.0f;
            double[] dArr8 = gt1Var.b;
            double d4 = dArr8[i8] - dArr8[i9];
            double[] dArr9 = gt1Var.c;
            dArr9[i8] = (d4 * ((double) f6)) + dArr9[i9];
            i8++;
        }
        if (dArr4.length > 1) {
            i = 0;
            aVar2.g = l20.a(0, dArr4, dArr5);
        } else {
            i = 0;
            aVar2.g = null;
        }
        l20.a(i, dArr, dArr2);
    }

    public final String toString() {
        String string = this.b;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (b bVar : this.f) {
            StringBuilder sbK = C0173a.k(string, "[");
            sbK.append(bVar.a);
            sbK.append(" , ");
            sbK.append(decimalFormat.format(bVar.b));
            sbK.append("] ");
            string = sbK.toString();
        }
        return string;
    }

    public void c(ox oxVar) {
    }
}
