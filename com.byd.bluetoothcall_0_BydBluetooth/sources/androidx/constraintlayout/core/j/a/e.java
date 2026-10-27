package androidx.constraintlayout.core.j.a;

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
    private androidx.constraintlayout.core.j.a.b f751a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f752b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f753c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f754d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f755e = null;
    public int f = 0;
    ArrayList<c> g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            return Integer.compare(cVar.f762a, cVar2.f762a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f757a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        h f758b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f759c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f760d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f761e;
        float[] f;
        double[] g;
        float[] h;
        float[] i;
        float[] j;
        float[] k;
        int l;
        androidx.constraintlayout.core.j.a.b m;
        double[] n;
        double[] o;
        float p;

        b(int i, String str, int i2, int i3) {
            h hVar = new h();
            this.f758b = hVar;
            this.f759c = 0;
            this.f760d = 1;
            this.f761e = 2;
            this.l = i;
            this.f757a = i2;
            hVar.g(i, str);
            this.f = new float[i3];
            this.g = new double[i3];
            this.h = new float[i3];
            this.i = new float[i3];
            this.j = new float[i3];
            this.k = new float[i3];
        }

        public double a(float f) {
            androidx.constraintlayout.core.j.a.b bVar = this.m;
            if (bVar != null) {
                double d2 = f;
                bVar.g(d2, this.o);
                this.m.d(d2, this.n);
            } else {
                double[] dArr = this.o;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                dArr[2] = 0.0d;
            }
            double d3 = f;
            double dE = this.f758b.e(d3, this.n[1]);
            double d4 = this.f758b.d(d3, this.n[1], this.o[1]);
            double[] dArr2 = this.o;
            return dArr2[0] + (dE * dArr2[2]) + (d4 * this.n[2]);
        }

        public double b(float f) {
            androidx.constraintlayout.core.j.a.b bVar = this.m;
            if (bVar != null) {
                bVar.d(f, this.n);
            } else {
                double[] dArr = this.n;
                dArr[0] = this.i[0];
                dArr[1] = this.j[0];
                dArr[2] = this.f[0];
            }
            double[] dArr2 = this.n;
            return dArr2[0] + (this.f758b.e(f, dArr2[1]) * this.n[2]);
        }

        public void c(int i, int i2, float f, float f2, float f3, float f4) {
            this.g[i] = ((double) i2) / 100.0d;
            this.h[i] = f;
            this.i[i] = f2;
            this.j[i] = f3;
            this.f[i] = f4;
        }

        public void d(float f) {
            this.p = f;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.g.length, 3);
            float[] fArr = this.f;
            this.n = new double[fArr.length + 2];
            this.o = new double[fArr.length + 2];
            if (this.g[0] > 0.0d) {
                this.f758b.a(0.0d, this.h[0]);
            }
            double[] dArr2 = this.g;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f758b.a(1.0d, this.h[length]);
            }
            for (int i = 0; i < dArr.length; i++) {
                dArr[i][0] = this.i[i];
                dArr[i][1] = this.j[i];
                dArr[i][2] = this.f[i];
                this.f758b.a(this.g[i], this.h[i]);
            }
            this.f758b.f();
            double[] dArr3 = this.g;
            if (dArr3.length > 1) {
                this.m = androidx.constraintlayout.core.j.a.b.a(0, dArr3, dArr);
            } else {
                this.m = null;
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f762a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f763b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f764c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f765d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f766e;

        public c(int i, float f, float f2, float f3, float f4) {
            this.f762a = i;
            this.f763b = f4;
            this.f764c = f2;
            this.f765d = f;
            this.f766e = f3;
        }
    }

    public float a(float f) {
        return (float) this.f752b.b(f);
    }

    public float b(float f) {
        return (float) this.f752b.a(f);
    }

    protected void c(Object obj) {
    }

    public void d(int i, int i2, String str, int i3, float f, float f2, float f3, float f4) {
        this.g.add(new c(i, f, f2, f3, f4));
        if (i3 != -1) {
            this.f = i3;
        }
        this.f754d = i2;
        this.f755e = str;
    }

    public void e(int i, int i2, String str, int i3, float f, float f2, float f3, float f4, Object obj) {
        this.g.add(new c(i, f, f2, f3, f4));
        if (i3 != -1) {
            this.f = i3;
        }
        this.f754d = i2;
        c(obj);
        this.f755e = str;
    }

    public void f(String str) {
        this.f753c = str;
    }

    public void g(float f) {
        int size = this.g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.g, new a());
        double[] dArr = new double[size];
        char c2 = 0;
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 3);
        this.f752b = new b(this.f754d, this.f755e, this.f, size);
        int i = 0;
        for (c cVar : this.g) {
            float f2 = cVar.f765d;
            dArr[i] = ((double) f2) * 0.01d;
            double[] dArr3 = dArr2[i];
            float f3 = cVar.f763b;
            dArr3[c2] = f3;
            double[] dArr4 = dArr2[i];
            float f4 = cVar.f764c;
            dArr4[1] = f4;
            double[] dArr5 = dArr2[i];
            float f5 = cVar.f766e;
            dArr5[2] = f5;
            this.f752b.c(i, cVar.f762a, f2, f4, f5, f3);
            i++;
            c2 = 0;
        }
        this.f752b.d(f);
        this.f751a = androidx.constraintlayout.core.j.a.b.a(0, dArr, dArr2);
    }

    public boolean h() {
        return this.f == 1;
    }

    public String toString() {
        String str = this.f753c;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<c> it = this.g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().f762a + " , " + decimalFormat.format(r3.f763b) + "] ";
        }
        return str;
    }
}
