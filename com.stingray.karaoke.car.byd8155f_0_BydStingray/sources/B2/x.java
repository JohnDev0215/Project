package B2;

import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import e2.C1207o;
import e2.C1208p;
import h2.AbstractC1343A;
import io.sentry.C1489n1;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f603a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f604b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f606f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f607g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f608h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f609i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f610j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C1489n1 f611k;
    public final e2.K l;

    public x(int i7, byte[] bArr) {
        N n10 = new N(bArr.length, bArr);
        n10.r(i7 * 8);
        this.f603a = n10.i(16);
        this.f604b = n10.i(16);
        this.c = n10.i(24);
        this.d = n10.i(24);
        int i10 = n10.i(20);
        this.f605e = i10;
        this.f606f = d(i10);
        this.f607g = n10.i(3) + 1;
        int i11 = n10.i(5) + 1;
        this.f608h = i11;
        this.f609i = a(i11);
        this.f610j = n10.k(36);
        this.f611k = null;
        this.l = null;
    }

    public static int a(int i7) {
        if (i7 == 8) {
            return 1;
        }
        if (i7 == 12) {
            return 2;
        }
        if (i7 == 16) {
            return 4;
        }
        if (i7 != 20) {
            return i7 != 24 ? -1 : 6;
        }
        return 5;
    }

    public static int d(int i7) {
        switch (i7) {
            case BYDAutoEngineDevice.ENGINE_SPEED_MAX /* 8000 */:
                return 4;
            case 16000:
                return 5;
            case 22050:
                return 6;
            case 24000:
                return 7;
            case 32000:
                return 8;
            case 44100:
                return 9;
            case 48000:
                return 10;
            case 88200:
                return 1;
            case 96000:
                return 11;
            case 176400:
                return 2;
            case 192000:
                return 3;
            default:
                return -1;
        }
    }

    public final long b() {
        long j3 = this.f610j;
        if (j3 == 0) {
            return -9223372036854775807L;
        }
        return (j3 * 1000000) / ((long) this.f605e);
    }

    public final C1208p c(byte[] bArr, e2.K k10) {
        bArr[4] = -128;
        int i7 = this.d;
        if (i7 <= 0) {
            i7 = -1;
        }
        e2.K k11 = this.l;
        if (k11 != null) {
            k10 = k11.d(k10);
        }
        C1207o c1207o = new C1207o();
        c1207o.f14246m = e2.L.l("audio/flac");
        c1207o.f14247n = i7;
        c1207o.B = this.f607g;
        c1207o.f14228C = this.f605e;
        c1207o.f14229D = AbstractC1343A.u(this.f608h);
        c1207o.f14248p = Collections.singletonList(bArr);
        c1207o.f14245k = k10;
        return new C1208p(c1207o);
    }

    public x(int i7, int i10, int i11, int i12, int i13, int i14, int i15, long j3, C1489n1 c1489n1, e2.K k10) {
        this.f603a = i7;
        this.f604b = i10;
        this.c = i11;
        this.d = i12;
        this.f605e = i13;
        this.f606f = d(i13);
        this.f607g = i14;
        this.f608h = i15;
        this.f609i = a(i15);
        this.f610j = j3;
        this.f611k = c1489n1;
        this.l = k10;
    }
}
