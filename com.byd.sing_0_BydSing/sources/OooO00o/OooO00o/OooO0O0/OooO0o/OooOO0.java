package OooO00o.OooO00o.OooO0o0.OooO0o;

import android.media.AudioManager;
import java.util.Objects;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooOO0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final OooOO0 f490a = new OooOO0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final kotlin.a f491b = d.b(OooO00o.f492OooO00o);

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<AudioManager> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f492OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final AudioManager invoke() {
            Object systemService = OooO00o.OooO00o.OooO0O0.a.f39a.a().getSystemService("audio");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
            return (AudioManager) systemService;
        }
    }

    public final double a(double d2) {
        double[] dArr = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.k;
        double[] dArr2 = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.l;
        if ((d2 <= 1.0d ? d2 : 1.0d) < 0.0d) {
            d2 = 0.0d;
        }
        int i = 1;
        while (d2 > dArr[i]) {
            i++;
        }
        int i2 = i - 1;
        double d3 = dArr[i2];
        double d4 = dArr[i];
        double d5 = dArr2[i2];
        double d6 = (((d2 - d3) / (d4 - d3)) * (dArr2[i] - d5)) + d5;
        OooO00o.OooO00o.OooO0O0.b.f41a.b("VolumeCalculate", h.i("bgmSysVolCurve vol = ", Double.valueOf(d6)));
        return d6;
    }

    public final int b(int i) {
        double[] dArr = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.i;
        double[] dArr2 = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.j;
        double d2 = i <= 100 ? (((double) i) * 1.0d) / (((double) 100) * 1.0d) : 1.0d;
        int i2 = 1;
        while (d2 > dArr[i2]) {
            i2++;
        }
        int i3 = i2 - 1;
        double d3 = dArr[i3];
        double d4 = dArr[i2];
        double d5 = dArr2[i3];
        double d6 = ((((d2 - d3) / (d4 - d3)) * (dArr2[i2] - d5)) + d5) * ((double) 100);
        if (d6 > 100.0d) {
            d6 = 100.0d;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.b("VolumeCalculate", h.i("micvolcurve , vol = ", Double.valueOf(d6)));
        return (int) d6;
    }

    public final int c(int i, double d2, double d3, double d4, double d5) {
        double d6 = (((((double) i) - d2) * (d5 - d4)) / (d3 - d2)) + d4;
        return d6 > d5 ? (int) d5 : d6 < d4 ? (int) d4 : (int) d6;
    }

    public final int d(int i, int i2) {
        return (int) (((i * 1.0f) * i2) / 100.0f);
    }

    public final AudioManager e() {
        return (AudioManager) f491b.getValue();
    }

    public final int f() {
        int iJ = j();
        double[] dArr = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.e;
        int[] iArr = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f;
        if (iArr == null || dArr == null) {
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.d("VolumeCalculate", "getCurrentCurveMax maybe not success init config!");
            bVar.d("VolumeCalculate", "volumeCurveMax:" + iArr + " volumeCurveSystem:" + dArr);
            return 256;
        }
        int i = 0;
        if (!(iArr.length == 0)) {
            if (!(dArr.length == 0)) {
                if (i() == 0) {
                    return iArr[0];
                }
                double dI = ((double) i()) / ((double) iJ);
                int length = dArr.length - 1;
                if (1 <= length) {
                    while (true) {
                        int i2 = length - 1;
                        if (dI <= dArr[length] && dI > dArr[length - 1]) {
                            i = length;
                            break;
                        }
                        if (1 > i2) {
                            break;
                        }
                        length = i2;
                    }
                }
                if (i >= 1 && i <= iArr.length) {
                    return iArr[i - 1];
                }
                OooO00o.OooO00o.OooO0O0.b.f41a.d("VolumeCalculate", "volumeCurveMax size " + iArr.length + ", index is " + i + ", maybe cause ArrayIndexOutOfBoundsException!!!");
                return 256;
            }
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.d("VolumeCalculate", "volumeCurveMax or volumeCurveSystem is empty!!!");
        return 256;
    }

    public final int g(int i) {
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        int iF = f();
        double[] dArr = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.f468d;
        if (dArr == null) {
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.d("VolumeCalculate", "getVolume maybe not success init config!");
            bVar.d("VolumeCalculate", h.i("volumeCurveUI:", dArr));
            return 128;
        }
        double d2 = 100;
        double length = 1.0d / ((double) (dArr.length - 1));
        double d3 = 0.0d;
        while (d3 < ((double) i) / d2) {
            d3 += length;
            i2++;
        }
        double d4 = (d3 - length) * d2;
        double d5 = d2 * d3;
        double d6 = iF;
        return c(i, d4, d5, dArr[i2 - 1] * d6, d6 * dArr[i2]);
    }

    public final int h(int i, int i2) {
        double[] dArr = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.g;
        double[] dArr2 = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.a.h;
        double d2 = i2 <= 100 ? (((double) i2) * 1.0d) / (((double) 100) * 1.0d) : 1.0d;
        int i3 = 1;
        while (d2 > dArr[i3]) {
            i3++;
        }
        int i4 = i3 - 1;
        double d3 = dArr[i4];
        double d4 = dArr[i3];
        double d5 = dArr2[i4];
        double d6 = ((((d2 - d3) / (d4 - d3)) * (dArr2[i3] - d5)) + d5) * ((double) i);
        OooO00o.OooO00o.OooO0O0.b.f41a.b("VolumeCalculate", h.i("sysVolCurve vol = ", Double.valueOf(d6)));
        return (int) d6;
    }

    public final int i() {
        AudioManager audioManagerE = e();
        if (audioManagerE == null) {
            return 0;
        }
        return audioManagerE.getStreamVolume(3);
    }

    public final int j() {
        AudioManager audioManagerE = e();
        if (audioManagerE == null) {
            return 0;
        }
        return audioManagerE.getStreamMaxVolume(3);
    }

    public final int k() {
        int i = i();
        int iJ = j();
        if (iJ == 0 || i > iJ) {
            return 100;
        }
        float f = (i * 100.0f) / iJ;
        OooO00o.OooO00o.OooO0O0.b.f41a.b("VolumeCalculate", h.i("getSysVol now = ", Float.valueOf(f)));
        return (int) f;
    }
}
