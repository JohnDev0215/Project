package p;

/* JADX INFO: compiled from: CurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: compiled from: CurveFit.java */
    static class a extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        double f8703a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        double[] f8704b;

        a(double d7, double[] dArr) {
            this.f8703a = d7;
            this.f8704b = dArr;
        }

        @Override // p.b
        public double c(double d7, int i7) {
            return this.f8704b[i7];
        }

        @Override // p.b
        public void d(double d7, double[] dArr) {
            double[] dArr2 = this.f8704b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // p.b
        public void e(double d7, float[] fArr) {
            int i7 = 0;
            while (true) {
                double[] dArr = this.f8704b;
                if (i7 >= dArr.length) {
                    return;
                }
                fArr[i7] = (float) dArr[i7];
                i7++;
            }
        }

        @Override // p.b
        public void f(double d7, double[] dArr) {
            for (int i7 = 0; i7 < this.f8704b.length; i7++) {
                dArr[i7] = 0.0d;
            }
        }

        @Override // p.b
        public double[] g() {
            return new double[]{this.f8703a};
        }
    }

    public static b a(int i7, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i7 = 2;
        }
        return i7 != 0 ? i7 != 2 ? new f(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new g(dArr, dArr2);
    }

    public static b b(int[] iArr, double[] dArr, double[][] dArr2) {
        return new p.a(iArr, dArr, dArr2);
    }

    public abstract double c(double d7, int i7);

    public abstract void d(double d7, double[] dArr);

    public abstract void e(double d7, float[] fArr);

    public abstract void f(double d7, double[] dArr);

    public abstract double[] g();
}
