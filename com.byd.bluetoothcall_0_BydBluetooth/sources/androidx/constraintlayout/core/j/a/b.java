package androidx.constraintlayout.core.j.a;

/* JADX INFO: compiled from: CurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: compiled from: CurveFit.java */
    static class a extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        double f743a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        double[] f744b;

        a(double d2, double[] dArr) {
            this.f743a = d2;
            this.f744b = dArr;
        }

        @Override // androidx.constraintlayout.core.j.a.b
        public double c(double d2, int i) {
            return this.f744b[i];
        }

        @Override // androidx.constraintlayout.core.j.a.b
        public void d(double d2, double[] dArr) {
            double[] dArr2 = this.f744b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // androidx.constraintlayout.core.j.a.b
        public void e(double d2, float[] fArr) {
            int i = 0;
            while (true) {
                double[] dArr = this.f744b;
                if (i >= dArr.length) {
                    return;
                }
                fArr[i] = (float) dArr[i];
                i++;
            }
        }

        @Override // androidx.constraintlayout.core.j.a.b
        public double f(double d2, int i) {
            return 0.0d;
        }

        @Override // androidx.constraintlayout.core.j.a.b
        public void g(double d2, double[] dArr) {
            for (int i = 0; i < this.f744b.length; i++) {
                dArr[i] = 0.0d;
            }
        }

        @Override // androidx.constraintlayout.core.j.a.b
        public double[] h() {
            return new double[]{this.f743a};
        }
    }

    public static b a(int i, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i = 2;
        }
        return i != 0 ? i != 2 ? new f(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new g(dArr, dArr2);
    }

    public static b b(int[] iArr, double[] dArr, double[][] dArr2) {
        return new androidx.constraintlayout.core.j.a.a(iArr, dArr, dArr2);
    }

    public abstract double c(double d2, int i);

    public abstract void d(double d2, double[] dArr);

    public abstract void e(double d2, float[] fArr);

    public abstract double f(double d2, int i);

    public abstract void g(double d2, double[] dArr);

    public abstract double[] h();
}
