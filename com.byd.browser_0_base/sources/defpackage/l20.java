package defpackage;

/* JADX INFO: compiled from: CurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l20 {

    /* JADX INFO: compiled from: CurveFit.java */
    public static class a extends l20 {
        public final double a;
        public final double[] b;

        public a(double d, double[] dArr) {
            this.a = d;
            this.b = dArr;
        }

        @Override // defpackage.l20
        public final double b(double d) {
            return this.b[0];
        }

        @Override // defpackage.l20
        public final void c(double d, double[] dArr) {
            double[] dArr2 = this.b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // defpackage.l20
        public final void d(double d, float[] fArr) {
            int i = 0;
            while (true) {
                double[] dArr = this.b;
                if (i >= dArr.length) {
                    return;
                }
                fArr[i] = (float) dArr[i];
                i++;
            }
        }

        @Override // defpackage.l20
        public final double e(double d) {
            return 0.0d;
        }

        @Override // defpackage.l20
        public final void f(double d, double[] dArr) {
            for (int i = 0; i < this.b.length; i++) {
                dArr[i] = 0.0d;
            }
        }

        @Override // defpackage.l20
        public final double[] g() {
            return new double[]{this.a};
        }
    }

    public static l20 a(int i, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i = 2;
        }
        return i != 0 ? i != 2 ? new h71(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new mi1(dArr, dArr2);
    }

    public abstract double b(double d);

    public abstract void c(double d, double[] dArr);

    public abstract void d(double d, float[] fArr);

    public abstract double e(double d);

    public abstract void f(double d, double[] dArr);

    public abstract double[] g();
}
