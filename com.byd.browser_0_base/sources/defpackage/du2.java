package defpackage;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: compiled from: StepCurve.java */
/* JADX INFO: loaded from: classes.dex */
public final class du2 extends hf0 {
    public final mi1 d;

    public du2(String str) {
        this.a = str;
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
        double[] dArrCopyOf = Arrays.copyOf(dArr, i + 1);
        int length = (dArrCopyOf.length * 3) - 2;
        int length2 = dArrCopyOf.length - 1;
        double d = 1.0d / ((double) length2);
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, 1);
        double[] dArr3 = new double[length];
        for (int i2 = 0; i2 < dArrCopyOf.length; i2++) {
            double d2 = dArrCopyOf[i2];
            int i3 = i2 + length2;
            dArr2[i3][0] = d2;
            double d3 = ((double) i2) * d;
            dArr3[i3] = d3;
            if (i2 > 0) {
                int i4 = (length2 * 2) + i2;
                dArr2[i4][0] = d2 + 1.0d;
                dArr3[i4] = d3 + 1.0d;
                int i5 = i2 - 1;
                dArr2[i5][0] = (d2 - 1.0d) - d;
                dArr3[i5] = (d3 - 1.0d) - d;
            }
        }
        mi1 mi1Var = new mi1(dArr3, dArr2);
        System.out.println(" 0 " + mi1Var.b(0.0d));
        System.out.println(" 1 " + mi1Var.b(1.0d));
        this.d = mi1Var;
    }

    @Override // defpackage.hf0
    public final double a(double d) {
        return this.d.b(d);
    }

    @Override // defpackage.hf0
    public final double b(double d) {
        return this.d.e(d);
    }
}
