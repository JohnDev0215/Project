package t;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: compiled from: Easing.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f4949a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String[] f4950b = {"standard", "accelerate", "decelerate", "linear"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4951c = "identity";

    /* JADX INFO: compiled from: Easing.java */
    public static class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static double f4952d = 0.01d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static double f4953e = 1.0E-4d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f4954f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public double f4955g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public double f4956h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public double f4957i;

        public a(String str) {
            this.f4951c = str;
            int iIndexOf = str.indexOf(40);
            int iIndexOf2 = str.indexOf(44, iIndexOf);
            this.f4954f = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
            int i3 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf(44, i3);
            this.f4955g = Double.parseDouble(str.substring(i3, iIndexOf3).trim());
            int i4 = iIndexOf3 + 1;
            int iIndexOf4 = str.indexOf(44, i4);
            this.f4956h = Double.parseDouble(str.substring(i4, iIndexOf4).trim());
            int i5 = iIndexOf4 + 1;
            this.f4957i = Double.parseDouble(str.substring(i5, str.indexOf(41, i5)).trim());
        }

        @Override // t.c
        public double a(double d3) {
            if (d3 <= 0.0d) {
                return 0.0d;
            }
            if (d3 >= 1.0d) {
                return 1.0d;
            }
            double d4 = 0.5d;
            double d5 = 0.5d;
            while (d4 > f4952d) {
                d4 *= 0.5d;
                d5 = d(d5) < d3 ? d5 + d4 : d5 - d4;
            }
            double d6 = d5 - d4;
            double d7 = d(d6);
            double d8 = d5 + d4;
            double d9 = d(d8);
            double dE = e(d6);
            return (((e(d8) - dE) * (d3 - d7)) / (d9 - d7)) + dE;
        }

        @Override // t.c
        public double b(double d3) {
            double d4 = 0.5d;
            double d5 = 0.5d;
            while (d4 > f4953e) {
                d4 *= 0.5d;
                d5 = d(d5) < d3 ? d5 + d4 : d5 - d4;
            }
            double d6 = d5 - d4;
            double d7 = d5 + d4;
            return (e(d7) - e(d6)) / (d(d7) - d(d6));
        }

        public final double d(double d3) {
            double d4 = 1.0d - d3;
            double d5 = 3.0d * d4;
            return (this.f4954f * d4 * d5 * d3) + (this.f4956h * d5 * d3 * d3) + (d3 * d3 * d3);
        }

        public final double e(double d3) {
            double d4 = 1.0d - d3;
            double d5 = 3.0d * d4;
            return (this.f4955g * d4 * d5 * d3) + (this.f4957i * d5 * d3 * d3) + (d3 * d3 * d3);
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
                Log.e("ConstraintSet", "transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(f4950b));
                return f4949a;
        }
    }

    public double a(double d3) {
        return d3;
    }

    public double b(double d3) {
        return 1.0d;
    }

    public String toString() {
        return this.f4951c;
    }
}
