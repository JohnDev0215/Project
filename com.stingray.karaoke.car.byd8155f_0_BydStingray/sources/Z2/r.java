package z2;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.Surface;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2756b f24355a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f24356b;
    public final q c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Surface f24357e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f24358f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f24359g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f24360h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f24361i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f24362j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f24363k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f24364m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f24365n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f24366p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f24367q;

    public r(Context context) {
        DisplayManager displayManager;
        C2756b c2756b = new C2756b();
        c2756b.f24270a = new C2755a();
        c2756b.f24271b = new C2755a();
        c2756b.d = -9223372036854775807L;
        this.f24355a = c2756b;
        p pVar = (context == null || (displayManager = (DisplayManager) context.getSystemService("display")) == null) ? null : new p(this, displayManager);
        this.f24356b = pVar;
        this.c = pVar != null ? q.f24352p : null;
        this.f24363k = -9223372036854775807L;
        this.l = -9223372036854775807L;
        this.f24358f = -1.0f;
        this.f24361i = 1.0f;
        this.f24362j = 0;
    }

    public static void a(r rVar, Display display) {
        rVar.getClass();
        if (display != null) {
            long refreshRate = (long) (1.0E9d / ((double) display.getRefreshRate()));
            rVar.f24363k = refreshRate;
            rVar.l = (refreshRate * 80) / 100;
        } else {
            AbstractC1346c.z("VideoFrameReleaseHelper", "Unable to query display refresh rate");
            rVar.f24363k = -9223372036854775807L;
            rVar.l = -9223372036854775807L;
        }
    }

    public final void b() {
        Surface surface;
        if (AbstractC1343A.f15195a < 30 || (surface = this.f24357e) == null || this.f24362j == Integer.MIN_VALUE || this.f24360h == 0.0f) {
            return;
        }
        this.f24360h = 0.0f;
        try {
            surface.setFrameRate(0.0f, 0);
        } catch (IllegalStateException e10) {
            AbstractC1346c.q("VideoFrameReleaseHelper", "Failed to call Surface.setFrameRate", e10);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            r9 = this;
            int r0 = h2.AbstractC1343A.f15195a
            r1 = 30
            if (r0 < r1) goto L8d
            android.view.Surface r0 = r9.f24357e
            if (r0 != 0) goto Lc
            goto L8d
        Lc:
            z2.b r0 = r9.f24355a
            z2.a r2 = r0.f24270a
            boolean r2 = r2.a()
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r2 == 0) goto L39
            z2.a r2 = r0.f24270a
            boolean r2 = r2.a()
            if (r2 == 0) goto L37
            z2.a r2 = r0.f24270a
            long r4 = r2.f24266e
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L2b
            goto L2e
        L2b:
            long r6 = r2.f24267f
            long r6 = r6 / r4
        L2e:
            double r4 = (double) r6
            r6 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
            double r6 = r6 / r4
            float r2 = (float) r6
            goto L3b
        L37:
            r2 = r3
            goto L3b
        L39:
            float r2 = r9.f24358f
        L3b:
            float r4 = r9.f24359g
            int r5 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r5 != 0) goto L42
            return
        L42:
            int r5 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r5 == 0) goto L80
            int r3 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r3 == 0) goto L80
            z2.a r1 = r0.f24270a
            boolean r1 = r1.a()
            if (r1 == 0) goto L71
            z2.a r1 = r0.f24270a
            boolean r1 = r1.a()
            if (r1 == 0) goto L5f
            z2.a r0 = r0.f24270a
            long r0 = r0.f24267f
            goto L64
        L5f:
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L64:
            r3 = 5000000000(0x12a05f200, double:2.470328229E-314)
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 < 0) goto L71
            r0 = 1017370378(0x3ca3d70a, float:0.02)
            goto L73
        L71:
            r0 = 1065353216(0x3f800000, float:1.0)
        L73:
            float r1 = r9.f24359g
            float r1 = r2 - r1
            float r1 = java.lang.Math.abs(r1)
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 < 0) goto L8d
            goto L87
        L80:
            if (r5 == 0) goto L83
            goto L87
        L83:
            int r0 = r0.f24272e
            if (r0 < r1) goto L8d
        L87:
            r9.f24359g = r2
            r0 = 0
            r9.d(r0)
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z2.r.c():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(boolean r5) {
        /*
            r4 = this;
            int r0 = h2.AbstractC1343A.f15195a
            r1 = 30
            if (r0 < r1) goto L41
            android.view.Surface r0 = r4.f24357e
            if (r0 == 0) goto L41
            int r1 = r4.f24362j
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 != r2) goto L11
            goto L41
        L11:
            boolean r1 = r4.d
            r2 = 0
            if (r1 == 0) goto L22
            float r1 = r4.f24359g
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L22
            float r3 = r4.f24361i
            float r1 = r1 * r3
            goto L23
        L22:
            r1 = r2
        L23:
            if (r5 != 0) goto L2c
            float r5 = r4.f24360h
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 != 0) goto L2c
            return
        L2c:
            r4.f24360h = r1
            int r4 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r4 != 0) goto L34
            r4 = 0
            goto L35
        L34:
            r4 = 1
        L35:
            io.sentry.android.replay.t.d(r0, r1, r4)     // Catch: java.lang.IllegalStateException -> L39
            goto L41
        L39:
            r4 = move-exception
            java.lang.String r5 = "VideoFrameReleaseHelper"
            java.lang.String r0 = "Failed to call Surface.setFrameRate"
            h2.AbstractC1346c.q(r5, r0, r4)
        L41:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z2.r.d(boolean):void");
    }
}
