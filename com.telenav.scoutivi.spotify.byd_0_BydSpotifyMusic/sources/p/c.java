package p;

import java.util.Arrays;

/* JADX INFO: compiled from: Easing.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static c f8705b = new c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String[] f8706c = {"standard", "accelerate", "decelerate", "linear"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f8707a = "identity";

    /* JADX INFO: compiled from: Easing.java */
    static class a extends c {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private static double f8708h = 0.01d;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private static double f8709i = 1.0E-4d;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        double f8710d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        double f8711e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        double f8712f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        double f8713g;

        a(String str) {
            this.f8707a = str;
            int iIndexOf = str.indexOf(40);
            int iIndexOf2 = str.indexOf(44, iIndexOf);
            this.f8710d = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
            int i7 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf(44, i7);
            this.f8711e = Double.parseDouble(str.substring(i7, iIndexOf3).trim());
            int i8 = iIndexOf3 + 1;
            int iIndexOf4 = str.indexOf(44, i8);
            this.f8712f = Double.parseDouble(str.substring(i8, iIndexOf4).trim());
            int i9 = iIndexOf4 + 1;
            this.f8713g = Double.parseDouble(str.substring(i9, str.indexOf(41, i9)).trim());
        }

        private double d(double d7) {
            double d8 = 1.0d - d7;
            double d9 = 3.0d * d8;
            return (this.f8710d * d8 * d9 * d7) + (this.f8712f * d9 * d7 * d7) + (d7 * d7 * d7);
        }

        private double e(double d7) {
            double d8 = 1.0d - d7;
            double d9 = 3.0d * d8;
            return (this.f8711e * d8 * d9 * d7) + (this.f8713g * d9 * d7 * d7) + (d7 * d7 * d7);
        }

        @Override // p.c
        public double a(double d7) {
            if (d7 <= 0.0d) {
                return 0.0d;
            }
            if (d7 >= 1.0d) {
                return 1.0d;
            }
            double d8 = 0.5d;
            double d9 = 0.5d;
            while (d8 > f8708h) {
                d8 *= 0.5d;
                d9 = d(d9) < d7 ? d9 + d8 : d9 - d8;
            }
            double d10 = d9 - d8;
            double d11 = d(d10);
            double d12 = d9 + d8;
            double d13 = d(d12);
            double dE = e(d10);
            return (((e(d12) - dE) * (d7 - d11)) / (d13 - d11)) + dE;
        }

        @Override // p.c
        public double b(double d7) {
            double d8 = 0.5d;
            double d9 = 0.5d;
            while (d8 > f8709i) {
                d8 *= 0.5d;
                d9 = d(d9) < d7 ? d9 + d8 : d9 - d8;
            }
            double d10 = d9 - d8;
            double d11 = d9 + d8;
            return (e(d11) - e(d10)) / (d(d11) - d(d10));
        }
    }

    public static c c(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new a(str);
        }
        if (str.startsWith("spline")) {
            return new l(str);
        }
        if (str.startsWith("Schlick")) {
            return new i(str);
        }
        switch (str) {
            case "accelerate":
                return new a("cubic(0.4, 0.05, 0.8, 0.7)");
            case "decelerate":
                return new a("cubic(0.0, 0.0, 0.2, 0.95)");
            case "anticipate":
                return new a("cubic(0.36, 0, 0.66, -0.56)");
            case "linear":
                return new a("cubic(1, 1, 0, 0)");
            case "overshoot":
                return new a("cubic(0.34, 1.56, 0.64, 1)");
            case "standard":
                return new a("cubic(0.4, 0.0, 0.2, 1)");
            default:
                System.err.println("transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(f8706c));
                return f8705b;
        }
    }

    public double a(double d7) {
        return d7;
    }

    public double b(double d7) {
        return 1.0d;
    }

    public String toString() {
        return this.f8707a;
    }
}
