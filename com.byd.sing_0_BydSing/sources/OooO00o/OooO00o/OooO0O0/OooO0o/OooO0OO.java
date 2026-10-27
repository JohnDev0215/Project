package OooO00o.OooO00o.OooO0o0.OooO0o;

import kotlin.collections.f;
import kotlin.d;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0OO {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final OooO0OO f477a = new OooO0OO();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final double[] f478b;

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<double[]> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f479OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final double[] invoke() {
            return new double[]{4.2d, 4.08d, 4.0d, 3.93d, 3.87d, 3.82d, 3.79d, 3.77d, 3.73d, 3.7d, 3.68d, 3.5d, 2.5d};
        }
    }

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<int[]> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0O0 f480OooO00o = new OooO0O0();

        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final int[] invoke() {
            return new int[]{100, 90, 80, 70, 60, 50, 40, 30, 30, 15, 10, 5, 0};
        }
    }

    static {
        d.b(OooO00o.f479OooO00o);
        d.b(OooO0O0.f480OooO00o);
        f478b = new double[]{3.419d, 3.43d, 3.441d, 3.451d, 3.461d, 3.47d, 3.479d, 3.487d, 3.494d, 3.501d, 3.508d, 3.514d, 3.52d, 3.526d, 3.531d, 3.537d, 3.542d, 3.547d, 3.552d, 3.556d, 3.56d, 3.565d, 3.569d, 3.573d, 3.578d, 3.582d, 3.586d, 3.59d, 3.594d, 3.598d, 3.603d, 3.607d, 3.612d, 3.616d, 3.621d, 3.626d, 3.63d, 3.636d, 3.642d, 3.647d, 3.653d, 3.66d, 3.666d, 3.672d, 3.679d, 3.685d, 3.691d, 3.697d, 3.704d, 3.71d, 3.715d, 3.72d, 3.726d, 3.73d, 3.735d, 3.74d, 3.744d, 3.748d, 3.752d, 3.757d, 3.762d, 3.767d, 3.774d, 3.781d, 3.788d, 3.794d, 3.802d, 3.808d, 3.816d, 3.823d, 3.832d, 3.839d, 3.847d, 3.854d, 3.863d, 3.87d, 3.879d, 3.887d, 3.895d, 3.903d, 3.913d, 3.922d, 3.931d, 3.94d, 3.948d, 3.957d, 3.967d, 3.976d, 3.988d, 3.999d, 4.01d, 4.022d, 4.033d, 4.046d, 4.061d, 4.076d, 4.092d, 4.11d, 4.127d, 4.145d};
    }

    public final int a(double d2) {
        int i = 0;
        if (3.4d <= d2 && d2 <= 4.2d) {
            double[] dArr = f478b;
            if (d2 < f.c(dArr)) {
                return 1;
            }
            if (d2 > f.f(dArr)) {
                return 100;
            }
            int length = dArr.length - 1;
            if (length >= 0) {
                while (true) {
                    int i2 = i + 1;
                    if (d2 <= f478b[i]) {
                        return i;
                    }
                    if (i2 > length) {
                        break;
                    }
                    i = i2;
                }
            }
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.b("MicElectricity", "battery not in 3.4..4.2, return invalid value (-1)");
        return -1;
    }
}
