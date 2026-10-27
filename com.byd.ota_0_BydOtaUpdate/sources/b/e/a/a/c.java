package b.e.a.a;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: compiled from: Easing.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f1197a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String[] f1198b = {"standard", "accelerate", "decelerate", "linear"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1199c = "identity";

    /* JADX INFO: compiled from: Easing.java */
    public static class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static double f1200d = 0.01d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static double f1201e = 1.0E-4d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f1202f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public double f1203g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public double f1204h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public double f1205i;

        public a(String str) {
            this.f1199c = str;
            int iIndexOf = str.indexOf(40);
            int iIndexOf2 = str.indexOf(44, iIndexOf);
            this.f1202f = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
            int i2 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf(44, i2);
            this.f1203g = Double.parseDouble(str.substring(i2, iIndexOf3).trim());
            int i3 = iIndexOf3 + 1;
            int iIndexOf4 = str.indexOf(44, i3);
            this.f1204h = Double.parseDouble(str.substring(i3, iIndexOf4).trim());
            int i4 = iIndexOf4 + 1;
            this.f1205i = Double.parseDouble(str.substring(i4, str.indexOf(41, i4)).trim());
        }

        @Override // b.e.a.a.c
        public double a(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double d3 = 0.5d;
            double d4 = 0.5d;
            while (d3 > f1200d) {
                d3 *= 0.5d;
                d4 = d(d4) < d2 ? d4 + d3 : d4 - d3;
            }
            double d5 = d4 - d3;
            double d6 = d(d5);
            double d7 = d4 + d3;
            double d8 = d(d7);
            double dE = e(d5);
            return (((e(d7) - dE) * (d2 - d6)) / (d8 - d6)) + dE;
        }

        @Override // b.e.a.a.c
        public double b(double d2) {
            double d3 = 0.5d;
            double d4 = 0.5d;
            while (d3 > f1201e) {
                d3 *= 0.5d;
                d4 = d(d4) < d2 ? d4 + d3 : d4 - d3;
            }
            double d5 = d4 - d3;
            double d6 = d4 + d3;
            return (e(d6) - e(d5)) / (d(d6) - d(d5));
        }

        public final double d(double d2) {
            double d3 = 1.0d - d2;
            double d4 = 3.0d * d3;
            return (this.f1202f * d3 * d4 * d2) + (this.f1204h * d4 * d2 * d2) + (d2 * d2 * d2);
        }

        public final double e(double d2) {
            double d3 = 1.0d - d2;
            double d4 = 3.0d * d3;
            return (this.f1203g * d3 * d4 * d2) + (this.f1205i * d4 * d2 * d2) + (d2 * d2 * d2);
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
                Log.e("ConstraintSet", "transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(f1198b));
                return f1197a;
        }
    }

    public double a(double d2) {
        return d2;
    }

    public double b(double d2) {
        return 1.0d;
    }

    public String toString() {
        return this.f1199c;
    }
}
