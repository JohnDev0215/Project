package androidx.constraintlayout.core.j.a;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: compiled from: StepCurve.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    g f794d;

    l(String str) {
        this.f747c = str;
        double[] dArr = new double[str.length() / 2];
        int iIndexOf = str.indexOf(40) + 1;
        int iIndexOf2 = str.indexOf(44, iIndexOf);
        int i = 0;
        while (iIndexOf2 != -1) {
            dArr[i] = Double.parseDouble(str.substring(iIndexOf, iIndexOf2).trim());
            iIndexOf = iIndexOf2 + 1;
            iIndexOf2 = str.indexOf(44, iIndexOf);
            i++;
        }
        dArr[i] = Double.parseDouble(str.substring(iIndexOf, str.indexOf(41, iIndexOf)).trim());
        this.f794d = d(Arrays.copyOf(dArr, i + 1));
    }

    private static g d(double[] dArr) {
        int length = (dArr.length * 3) - 2;
        int length2 = dArr.length - 1;
        double d2 = 1.0d / ((double) length2);
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, length, 1);
        double[] dArr3 = new double[length];
        for (int i = 0; i < dArr.length; i++) {
            double d3 = dArr[i];
            int i2 = i + length2;
            dArr2[i2][0] = d3;
            double d4 = ((double) i) * d2;
            dArr3[i2] = d4;
            if (i > 0) {
                int i3 = (length2 * 2) + i;
                dArr2[i3][0] = d3 + 1.0d;
                dArr3[i3] = d4 + 1.0d;
                int i4 = i - 1;
                dArr2[i4][0] = (d3 - 1.0d) - d2;
                dArr3[i4] = (d4 - 1.0d) - d2;
            }
        }
        g gVar = new g(dArr3, dArr2);
        System.out.println(" 0 " + gVar.c(0.0d, 0));
        System.out.println(" 1 " + gVar.c(1.0d, 0));
        return gVar;
    }

    @Override // androidx.constraintlayout.core.j.a.c
    public double a(double d2) {
        return this.f794d.c(d2, 0);
    }

    @Override // androidx.constraintlayout.core.j.a.c
    public double b(double d2) {
        return this.f794d.f(d2, 0);
    }
}
