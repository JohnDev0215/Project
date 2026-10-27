package p2;

import android.media.AudioTrack;
import h2.AbstractC1343A;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import l3.C1764u;

/* JADX INFO: renamed from: p2.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2099n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public long f20725A;
    public long B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f20726C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f20727D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public long f20728E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public long f20729F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f20730G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public long f20731H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public h2.v f20732I;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1764u f20733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long[] f20734b;
    public AudioTrack c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C2098m f20735e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f20736f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f20737g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f20738h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f20739i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f20740j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f20741k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Method f20742m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f20743n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f20744p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f20745q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f20746r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f20747s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f20748t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f20749u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f20750v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f20751w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f20752x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f20753y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f20754z;

    public C2099n(C1764u c1764u) {
        this.f20733a = c1764u;
        try {
            this.f20742m = AudioTrack.class.getMethod("getLatency", null);
        } catch (NoSuchMethodException unused) {
        }
        this.f20734b = new long[10];
        this.f20732I = h2.v.f15245a;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x023f A[Catch: Exception -> 0x0255, TRY_LEAVE, TryCatch #0 {Exception -> 0x0255, blocks: (B:93:0x0216, B:95:0x023f), top: B:141:0x0216 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long a(boolean r30) {
        /*
            Method dump skipped, instruction units count: 830
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.C2099n.a(boolean):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long b() {
        /*
            r11 = this;
            h2.v r0 = r11.f20732I
            r0.getClass()
            long r0 = android.os.SystemClock.elapsedRealtime()
            long r2 = r11.f20752x
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r3 = 2
            if (r2 == 0) goto L46
            android.media.AudioTrack r2 = r11.c
            r2.getClass()
            int r2 = r2.getPlayState()
            if (r2 != r3) goto L23
            long r0 = r11.f20754z
            return r0
        L23:
            long r0 = h2.AbstractC1343A.H(r0)
            long r2 = r11.f20752x
            long r0 = r0 - r2
            float r2 = r11.f20739i
            long r3 = h2.AbstractC1343A.t(r2, r0)
            int r0 = r11.f20736f
            long r5 = (long) r0
            r7 = 1000000(0xf4240, double:4.940656E-318)
            java.math.RoundingMode r9 = java.math.RoundingMode.UP
            long r0 = h2.AbstractC1343A.N(r3, r5, r7, r9)
            long r2 = r11.f20725A
            long r4 = r11.f20754z
            long r4 = r4 + r0
            long r0 = java.lang.Math.min(r2, r4)
            return r0
        L46:
            long r6 = r11.f20746r
            long r6 = r0 - r6
            r8 = 5
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 < 0) goto Lb7
            android.media.AudioTrack r2 = r11.c
            r2.getClass()
            int r6 = r2.getPlayState()
            r7 = 1
            if (r6 != r7) goto L5d
            goto Lb5
        L5d:
            int r2 = r2.getPlaybackHeadPosition()
            long r7 = (long) r2
            r9 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r7 = r7 & r9
            boolean r2 = r11.f20737g
            r9 = 0
            if (r2 == 0) goto L7b
            if (r6 != r3) goto L78
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 != 0) goto L78
            long r2 = r11.f20747s
            r11.f20749u = r2
        L78:
            long r2 = r11.f20749u
            long r7 = r7 + r2
        L7b:
            int r2 = h2.AbstractC1343A.f15195a
            r3 = 29
            if (r2 > r3) goto L99
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 != 0) goto L97
            long r2 = r11.f20747s
            int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r2 <= 0) goto L97
            r2 = 3
            if (r6 != r2) goto L97
            long r2 = r11.f20753y
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto Lb5
            r11.f20753y = r0
            goto Lb5
        L97:
            r11.f20753y = r4
        L99:
            long r2 = r11.f20747s
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 <= 0) goto Lb3
            boolean r4 = r11.f20730G
            if (r4 == 0) goto Lac
            long r4 = r11.f20731H
            long r4 = r4 + r2
            r11.f20731H = r4
            r2 = 0
            r11.f20730G = r2
            goto Lb3
        Lac:
            long r2 = r11.f20748t
            r4 = 1
            long r2 = r2 + r4
            r11.f20748t = r2
        Lb3:
            r11.f20747s = r7
        Lb5:
            r11.f20746r = r0
        Lb7:
            long r0 = r11.f20747s
            long r2 = r11.f20731H
            long r0 = r0 + r2
            long r2 = r11.f20748t
            r11 = 32
            long r2 = r2 << r11
            long r0 = r0 + r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.C2099n.b():long");
    }

    public final boolean c(long j3) {
        long jA = a(false);
        int i7 = this.f20736f;
        int i10 = AbstractC1343A.f15195a;
        if (j3 <= AbstractC1343A.N(jA, i7, 1000000L, RoundingMode.UP)) {
            if (!this.f20737g) {
                return false;
            }
            AudioTrack audioTrack = this.c;
            audioTrack.getClass();
            if (audioTrack.getPlayState() != 2 || b() != 0) {
                return false;
            }
        }
        return true;
    }

    public final void d() {
        this.f20741k = 0L;
        this.f20751w = 0;
        this.f20750v = 0;
        this.l = 0L;
        this.f20726C = 0L;
        this.f20729F = 0L;
        this.f20740j = false;
    }
}
