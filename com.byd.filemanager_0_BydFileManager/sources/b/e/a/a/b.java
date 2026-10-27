package b.e.a.a;

/* JADX INFO: compiled from: CurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: compiled from: CurveFit.java */
    static class a extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        double f1778a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        double[] f1779b;

        a(double d2, double[] dArr) {
            this.f1778a = d2;
            this.f1779b = dArr;
        }

        @Override // b.e.a.a.b
        public double c(double d2, int i) {
            return this.f1779b[i];
        }

        @Override // b.e.a.a.b
        public void d(double d2, double[] dArr) {
            double[] dArr2 = this.f1779b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // b.e.a.a.b
        public void e(double d2, float[] fArr) {
            int i = 0;
            while (true) {
                double[] dArr = this.f1779b;
                if (i >= dArr.length) {
                    return;
                }
                fArr[i] = (float) dArr[i];
                i++;
            }
        }

        @Override // b.e.a.a.b
        public double f(double d2, int i) {
            return 0.0d;
        }

        @Override // b.e.a.a.b
        public void g(double d2, double[] dArr) {
            for (int i = 0; i < this.f1779b.length; i++) {
                dArr[i] = 0.0d;
            }
        }

        @Override // b.e.a.a.b
        public double[] h() {
            return new double[]{this.f1778a};
        }
    }

    public static b a(int i, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i = 2;
        }
        return i != 0 ? i != 2 ? new d(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new e(dArr, dArr2);
    }

    public static b b(int[] iArr, double[] dArr, double[][] dArr2) {
        return new b.e.a.a.a(iArr, dArr, dArr2);
    }

    public abstract double c(double d2, int i);

    public abstract void d(double d2, double[] dArr);

    public abstract void e(double d2, float[] fArr);

    public abstract double f(double d2, int i);

    public abstract void g(double d2, double[] dArr);

    public abstract double[] h();
}
