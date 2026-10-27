package b.e.a.a;

/* JADX INFO: compiled from: CurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: compiled from: CurveFit.java */
    static class a extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        double f2313a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        double[] f2314b;

        a(double d2, double[] dArr) {
            this.f2313a = d2;
            this.f2314b = dArr;
        }

        @Override // b.e.a.a.b
        public double c(double d2, int i2) {
            return this.f2314b[i2];
        }

        @Override // b.e.a.a.b
        public void d(double d2, double[] dArr) {
            double[] dArr2 = this.f2314b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // b.e.a.a.b
        public void e(double d2, float[] fArr) {
            int i2 = 0;
            while (true) {
                double[] dArr = this.f2314b;
                if (i2 >= dArr.length) {
                    return;
                }
                fArr[i2] = (float) dArr[i2];
                i2++;
            }
        }

        @Override // b.e.a.a.b
        public double f(double d2, int i2) {
            return 0.0d;
        }

        @Override // b.e.a.a.b
        public void g(double d2, double[] dArr) {
            for (int i2 = 0; i2 < this.f2314b.length; i2++) {
                dArr[i2] = 0.0d;
            }
        }

        @Override // b.e.a.a.b
        public double[] h() {
            return new double[]{this.f2313a};
        }
    }

    public static b a(int i2, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i2 = 2;
        }
        return i2 != 0 ? i2 != 2 ? new d(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new e(dArr, dArr2);
    }

    public static b b(int[] iArr, double[] dArr, double[][] dArr2) {
        return new b.e.a.a.a(iArr, dArr, dArr2);
    }

    public abstract double c(double d2, int i2);

    public abstract void d(double d2, double[] dArr);

    public abstract void e(double d2, float[] fArr);

    public abstract double f(double d2, int i2);

    public abstract void g(double d2, double[] dArr);

    public abstract double[] h();
}
