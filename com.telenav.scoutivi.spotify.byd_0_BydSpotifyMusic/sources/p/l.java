package p;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: compiled from: StepCurve.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    g f8780d;

    l(String str) {
        this.f8707a = str;
        double[] dArr = new double[str.length() / 2];
        int iIndexOf = str.indexOf(40) + 1;
        int iIndexOf2 = str.indexOf(44, iIndexOf);
        int i7 = 0;
        while (iIndexOf2 != -1) {
            dArr[i7] = Double.parseDouble(str.substring(iIndexOf, iIndexOf2).trim());
            iIndexOf = iIndexOf2 + 1;
            iIndexOf2 = str.indexOf(44, iIndexOf);
            i7++;
        }
        dArr[i7] = Double.parseDouble(str.substring(iIndexOf, str.indexOf(41, iIndexOf)).trim());
        this.f8780d = d(Arrays.copyOf(dArr, i7 + 1));
    }

    private static g d(double[] dArr) {
        int length = (dArr.length * 3) - 2;
        int length2 = dArr.length - 1;
        double d7 = 1.0d / ((double) length2);
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, length, 1);
        double[] dArr3 = new double[length];
        for (int i7 = 0; i7 < dArr.length; i7++) {
            double d8 = dArr[i7];
            int i8 = i7 + length2;
            dArr2[i8][0] = d8;
            double d9 = ((double) i7) * d7;
            dArr3[i8] = d9;
            if (i7 > 0) {
                int i9 = (length2 * 2) + i7;
                dArr2[i9][0] = d8 + 1.0d;
                dArr3[i9] = d9 + 1.0d;
                int i10 = i7 - 1;
                dArr2[i10][0] = (d8 - 1.0d) - d7;
                dArr3[i10] = (d9 - 1.0d) - d7;
            }
        }
        g gVar = new g(dArr3, dArr2);
        System.out.println(" 0 " + gVar.c(0.0d, 0));
        System.out.println(" 1 " + gVar.c(1.0d, 0));
        return gVar;
    }

    @Override // p.c
    public double a(double d7) {
        return this.f8780d.c(d7, 0);
    }

    @Override // p.c
    public double b(double d7) {
        return this.f8780d.k(d7, 0);
    }
}
