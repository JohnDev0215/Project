package w;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: compiled from: Easing.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f8624a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String[] f8625b = {"standard", "accelerate", "decelerate", "linear"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f8626c = "identity";

    /* JADX INFO: compiled from: Easing.java */
    public static class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static double f8627d = 0.01d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static double f8628e = 1.0E-4d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f8629f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public double f8630g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public double f8631h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public double f8632i;

        public a(String str) {
            this.f8626c = str;
            int iIndexOf = str.indexOf(40);
            int iIndexOf2 = str.indexOf(44, iIndexOf);
            this.f8629f = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
            int i8 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf(44, i8);
            this.f8630g = Double.parseDouble(str.substring(i8, iIndexOf3).trim());
            int i9 = iIndexOf3 + 1;
            int iIndexOf4 = str.indexOf(44, i9);
            this.f8631h = Double.parseDouble(str.substring(i9, iIndexOf4).trim());
            int i10 = iIndexOf4 + 1;
            this.f8632i = Double.parseDouble(str.substring(i10, str.indexOf(41, i10)).trim());
        }

        @Override // w.c
        public double a(double d8) {
            if (d8 <= 0.0d) {
                return 0.0d;
            }
            if (d8 >= 1.0d) {
                return 1.0d;
            }
            double d9 = 0.5d;
            double d10 = 0.5d;
            while (d9 > f8627d) {
                d9 *= 0.5d;
                d10 = d(d10) < d8 ? d10 + d9 : d10 - d9;
            }
            double d11 = d10 - d9;
            double d12 = d(d11);
            double d13 = d10 + d9;
            double d14 = d(d13);
            double dE = e(d11);
            return (((e(d13) - dE) * (d8 - d12)) / (d14 - d12)) + dE;
        }

        @Override // w.c
        public double b(double d8) {
            double d9 = 0.5d;
            double d10 = 0.5d;
            while (d9 > f8628e) {
                d9 *= 0.5d;
                d10 = d(d10) < d8 ? d10 + d9 : d10 - d9;
            }
            double d11 = d10 - d9;
            double d12 = d10 + d9;
            return (e(d12) - e(d11)) / (d(d12) - d(d11));
        }

        public final double d(double d8) {
            double d9 = 1.0d - d8;
            double d10 = 3.0d * d9;
            return (this.f8629f * d9 * d10 * d8) + (this.f8631h * d10 * d8 * d8) + (d8 * d8 * d8);
        }

        public final double e(double d8) {
            double d9 = 1.0d - d8;
            double d10 = 3.0d * d9;
            return (this.f8630g * d9 * d10 * d8) + (this.f8632i * d10 * d8 * d8) + (d8 * d8 * d8);
        }
    }

    public static c c(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new a(str);
        }
        switch (str) {
            case "accelerate":
                return new a("cubic(0.4, 0.05, 0.8, 0.7)");
            case "decelerate":
                return new a("cubic(0.0, 0.0, 0.2, 0.95)");
            case "linear":
                return new a("cubic(1, 1, 0, 0)");
            case "standard":
                return new a("cubic(0.4, 0.0, 0.2, 1)");
            default:
                Log.e("ConstraintSet", "transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(f8625b));
                return f8624a;
        }
    }

    public double a(double d8) {
        return d8;
    }

    public double b(double d8) {
        return 1.0d;
    }

    public String toString() {
        return this.f8626c;
    }
}
