package w;

/* JADX INFO: compiled from: CurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: compiled from: CurveFit.java */
    public static class a extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double f8622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double[] f8623b;

        public a(double d8, double[] dArr) {
            this.f8622a = d8;
            this.f8623b = dArr;
        }

        @Override // w.b
        public double c(double d8, int i8) {
            return this.f8623b[i8];
        }

        @Override // w.b
        public void d(double d8, double[] dArr) {
            double[] dArr2 = this.f8623b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // w.b
        public void e(double d8, float[] fArr) {
            int i8 = 0;
            while (true) {
                double[] dArr = this.f8623b;
                if (i8 >= dArr.length) {
                    return;
                }
                fArr[i8] = (float) dArr[i8];
                i8++;
            }
        }

        @Override // w.b
        public double f(double d8, int i8) {
            return 0.0d;
        }

        @Override // w.b
        public void g(double d8, double[] dArr) {
            for (int i8 = 0; i8 < this.f8623b.length; i8++) {
                dArr[i8] = 0.0d;
            }
        }

        @Override // w.b
        public double[] h() {
            return new double[]{this.f8622a};
        }
    }

    public static b a(int i8, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i8 = 2;
        }
        return i8 != 0 ? i8 != 2 ? new d(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new e(dArr, dArr2);
    }

    public static b b(int[] iArr, double[] dArr, double[][] dArr2) {
        return new w.a(iArr, dArr, dArr2);
    }

    public abstract double c(double d8, int i8);

    public abstract void d(double d8, double[] dArr);

    public abstract void e(double d8, float[] fArr);

    public abstract double f(double d8, int i8);

    public abstract void g(double d8, double[] dArr);

    public abstract double[] h();
}
