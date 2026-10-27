package F0;

import android.graphics.Matrix;
import d5.C1078G;
import java.io.Serializable;
import n0.AbstractC1861K;
import n0.C1854D;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class E0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2014a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2015b;
    public boolean c;
    public Serializable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f2016e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f2017f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Serializable f2018g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f2019h;

    /* JADX WARN: Multi-variable type inference failed */
    public E0(InterfaceC2785n interfaceC2785n) {
        this.d = (A7.o) interfaceC2785n;
        this.f2014a = true;
        this.f2015b = true;
        this.c = true;
    }

    public float[] a(Object obj) {
        float[] fArrA = (float[]) this.f2019h;
        if (fArrA == null) {
            fArrA = C1854D.a();
            this.f2019h = fArrA;
        }
        if (this.f2015b) {
            this.c = T.s(b(obj), fArrA);
            this.f2015b = false;
        }
        if (this.c) {
            return fArrA;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [float[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r2v1, types: [A7.o, z7.n] */
    public float[] b(Object obj) {
        float[] fArr = (float[]) this.f2018g;
        float[] fArr2 = fArr;
        if (fArr == null) {
            ?? A5 = C1854D.a();
            this.f2018g = A5;
            fArr2 = A5;
        }
        if (!this.f2014a) {
            return fArr2;
        }
        Matrix matrix = (Matrix) this.f2016e;
        if (matrix == null) {
            matrix = new Matrix();
            this.f2016e = matrix;
        }
        ((A7.o) this.d).invoke(obj, matrix);
        Matrix matrix2 = (Matrix) this.f2017f;
        if (matrix2 == null || !matrix.equals(matrix2)) {
            AbstractC1861K.y(matrix, fArr2);
            this.f2016e = matrix2;
            this.f2017f = matrix;
        }
        this.f2014a = false;
        return fArr2;
    }

    public void c() {
        this.f2014a = true;
        this.f2015b = true;
    }

    public E0() {
        this.f2017f = d5.i0.f13428r;
        this.f2015b = true;
        C1078G c1078g = d5.I.f13384m;
        this.f2018g = d5.d0.f13415p;
    }
}
