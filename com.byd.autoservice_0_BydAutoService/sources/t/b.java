package t;

/* JADX INFO: compiled from: CurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: compiled from: CurveFit.java */
    public static class a extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double f4947a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double[] f4948b;

        public a(double d3, double[] dArr) {
            this.f4947a = d3;
            this.f4948b = dArr;
        }

        @Override // t.b
        public double c(double d3, int i3) {
            return this.f4948b[i3];
        }

        @Override // t.b
        public void d(double d3, double[] dArr) {
            double[] dArr2 = this.f4948b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // t.b
        public void e(double d3, float[] fArr) {
            int i3 = 0;
            while (true) {
                double[] dArr = this.f4948b;
                if (i3 >= dArr.length) {
                    return;
                }
                fArr[i3] = (float) dArr[i3];
                i3++;
            }
        }

        @Override // t.b
        public double f(double d3, int i3) {
            return 0.0d;
        }

        @Override // t.b
        public void g(double d3, double[] dArr) {
            for (int i3 = 0; i3 < this.f4948b.length; i3++) {
                dArr[i3] = 0.0d;
            }
        }

        @Override // t.b
        public double[] h() {
            return new double[]{this.f4947a};
        }
    }

    public static b a(int i3, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i3 = 2;
        }
        return i3 != 0 ? i3 != 2 ? new d(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new e(dArr, dArr2);
    }

    public static b b(int[] iArr, double[] dArr, double[][] dArr2) {
        return new t.a(iArr, dArr, dArr2);
    }

    public abstract double c(double d3, int i3);

    public abstract void d(double d3, double[] dArr);

    public abstract void e(double d3, float[] fArr);

    public abstract double f(double d3, int i3);

    public abstract void g(double d3, double[] dArr);

    public abstract double[] h();
}
