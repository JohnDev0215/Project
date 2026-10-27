package com.airbnb.lottie.v;

import android.car.Car;
import android.graphics.Color;
import androidx.annotation.IntRange;
import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l implements j0<com.airbnb.lottie.t.k.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f941a;

    public l(int i) {
        this.f941a = i;
    }

    private void b(com.airbnb.lottie.t.k.c cVar, List<Float> list) {
        int i = this.f941a * 4;
        if (list.size() <= i) {
            return;
        }
        int size = (list.size() - i) / 2;
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        int i2 = 0;
        while (i < list.size()) {
            if (i % 2 == 0) {
                dArr[i2] = list.get(i).floatValue();
            } else {
                dArr2[i2] = list.get(i).floatValue();
                i2++;
            }
            i++;
        }
        for (int i3 = 0; i3 < cVar.c(); i3++) {
            int i4 = cVar.a()[i3];
            cVar.a()[i3] = Color.argb(c(cVar.b()[i3], dArr, dArr2), Color.red(i4), Color.green(i4), Color.blue(i4));
        }
    }

    @IntRange(from = Car.CAR_WAIT_TIMEOUT_DO_NOT_WAIT, to = 255)
    private int c(double d, double[] dArr, double[] dArr2) {
        double dI;
        int i = 1;
        while (true) {
            if (i >= dArr.length) {
                dI = dArr2[dArr2.length - 1];
                break;
            }
            int i2 = i - 1;
            double d2 = dArr[i2];
            double d3 = dArr[i];
            if (dArr[i] >= d) {
                dI = com.airbnb.lottie.w.g.i(dArr2[i2], dArr2[i], (d - d2) / (d3 - d2));
                break;
            }
            i++;
        }
        return (int) (dI * 255.0d);
    }

    @Override // com.airbnb.lottie.v.j0
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.t.k.c a(com.airbnb.lottie.v.k0.c cVar, float f) {
        ArrayList arrayList = new ArrayList();
        boolean z = cVar.w() == c.b.BEGIN_ARRAY;
        if (z) {
            cVar.k();
        }
        while (cVar.p()) {
            arrayList.add(Float.valueOf((float) cVar.r()));
        }
        if (z) {
            cVar.m();
        }
        if (this.f941a == -1) {
            this.f941a = arrayList.size() / 4;
        }
        int i = this.f941a;
        float[] fArr = new float[i];
        int[] iArr = new int[i];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.f941a * 4; i4++) {
            int i5 = i4 / 4;
            double dFloatValue = arrayList.get(i4).floatValue();
            int i6 = i4 % 4;
            if (i6 == 0) {
                fArr[i5] = (float) dFloatValue;
            } else if (i6 == 1) {
                i2 = (int) (dFloatValue * 255.0d);
            } else if (i6 == 2) {
                i3 = (int) (dFloatValue * 255.0d);
            } else if (i6 == 3) {
                iArr[i5] = Color.argb(255, i2, i3, (int) (dFloatValue * 255.0d));
            }
        }
        com.airbnb.lottie.t.k.c cVar2 = new com.airbnb.lottie.t.k.c(fArr, iArr);
        b(cVar2, arrayList);
        return cVar2;
    }
}
