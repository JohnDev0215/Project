package p;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyCycleOscillator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private p.b f8715a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f8716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f8717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f8718d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f8719e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8720f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ArrayList<c> f8721g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            return Integer.compare(cVar.f8739a, cVar2.f8739a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f8723a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        h f8724b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f8725c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f8726d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f8727e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        float[] f8728f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        double[] f8729g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        float[] f8730h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        float[] f8731i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        float[] f8732j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        float[] f8733k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        int f8734l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        p.b f8735m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        double[] f8736n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        double[] f8737o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        float f8738p;

        b(int i7, String str, int i8, int i9) {
            h hVar = new h();
            this.f8724b = hVar;
            this.f8725c = 0;
            this.f8726d = 1;
            this.f8727e = 2;
            this.f8734l = i7;
            this.f8723a = i8;
            hVar.e(i7, str);
            this.f8728f = new float[i9];
            this.f8729g = new double[i9];
            this.f8730h = new float[i9];
            this.f8731i = new float[i9];
            this.f8732j = new float[i9];
            this.f8733k = new float[i9];
        }

        public double a(float f7) {
            p.b bVar = this.f8735m;
            if (bVar != null) {
                bVar.d(f7, this.f8736n);
            } else {
                double[] dArr = this.f8736n;
                dArr[0] = this.f8731i[0];
                dArr[1] = this.f8732j[0];
                dArr[2] = this.f8728f[0];
            }
            double[] dArr2 = this.f8736n;
            return dArr2[0] + (this.f8724b.c(f7, dArr2[1]) * this.f8736n[2]);
        }

        public void b(int i7, int i8, float f7, float f8, float f9, float f10) {
            this.f8729g[i7] = ((double) i8) / 100.0d;
            this.f8730h[i7] = f7;
            this.f8731i[i7] = f8;
            this.f8732j[i7] = f9;
            this.f8728f[i7] = f10;
        }

        public void c(float f7) {
            this.f8738p = f7;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.f8729g.length, 3);
            float[] fArr = this.f8728f;
            this.f8736n = new double[fArr.length + 2];
            this.f8737o = new double[fArr.length + 2];
            if (this.f8729g[0] > 0.0d) {
                this.f8724b.a(0.0d, this.f8730h[0]);
            }
            double[] dArr2 = this.f8729g;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f8724b.a(1.0d, this.f8730h[length]);
            }
            for (int i7 = 0; i7 < dArr.length; i7++) {
                dArr[i7][0] = this.f8731i[i7];
                dArr[i7][1] = this.f8732j[i7];
                dArr[i7][2] = this.f8728f[i7];
                this.f8724b.a(this.f8729g[i7], this.f8730h[i7]);
            }
            this.f8724b.d();
            double[] dArr3 = this.f8729g;
            if (dArr3.length > 1) {
                this.f8735m = p.b.a(0, dArr3, dArr);
            } else {
                this.f8735m = null;
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f8739a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f8740b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f8741c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f8742d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f8743e;

        public c(int i7, float f7, float f8, float f9, float f10) {
            this.f8739a = i7;
            this.f8740b = f10;
            this.f8741c = f8;
            this.f8742d = f7;
            this.f8743e = f9;
        }
    }

    public float a(float f7) {
        return (float) this.f8716b.a(f7);
    }

    protected void b(Object obj) {
    }

    public void c(int i7, int i8, String str, int i9, float f7, float f8, float f9, float f10) {
        this.f8721g.add(new c(i7, f7, f8, f9, f10));
        if (i9 != -1) {
            this.f8720f = i9;
        }
        this.f8718d = i8;
        this.f8719e = str;
    }

    public void d(int i7, int i8, String str, int i9, float f7, float f8, float f9, float f10, Object obj) {
        this.f8721g.add(new c(i7, f7, f8, f9, f10));
        if (i9 != -1) {
            this.f8720f = i9;
        }
        this.f8718d = i8;
        b(obj);
        this.f8719e = str;
    }

    public void e(String str) {
        this.f8717c = str;
    }

    public void f(float f7) {
        int size = this.f8721g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f8721g, new a());
        double[] dArr = new double[size];
        char c7 = 0;
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 3);
        this.f8716b = new b(this.f8718d, this.f8719e, this.f8720f, size);
        int i7 = 0;
        for (c cVar : this.f8721g) {
            float f8 = cVar.f8742d;
            dArr[i7] = ((double) f8) * 0.01d;
            double[] dArr3 = dArr2[i7];
            float f9 = cVar.f8740b;
            dArr3[c7] = f9;
            double[] dArr4 = dArr2[i7];
            float f10 = cVar.f8741c;
            dArr4[1] = f10;
            double[] dArr5 = dArr2[i7];
            float f11 = cVar.f8743e;
            dArr5[2] = f11;
            this.f8716b.b(i7, cVar.f8739a, f8, f10, f11, f9);
            i7++;
            c7 = 0;
        }
        this.f8716b.c(f7);
        this.f8715a = p.b.a(0, dArr, dArr2);
    }

    public boolean g() {
        return this.f8720f == 1;
    }

    public String toString() {
        String str = this.f8717c;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<c> it = this.f8721g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().f8739a + " , " + decimalFormat.format(r3.f8740b) + "] ";
        }
        return str;
    }
}
