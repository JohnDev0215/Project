package p2;

import B2.C0063c;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.PlaybackParams;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import d5.AbstractC1095q;
import d5.C1078G;
import d5.I;
import d5.d0;
import e2.C1195c;
import e2.C1196d;
import e2.C1208p;
import e2.L;
import e2.P;
import f2.C1254a;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import l3.C1764u;
import o2.C2026c;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static final Object f20776j0 = new Object();

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public static ScheduledExecutorService f20777k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public static int f20778l0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public s f20779A;
    public s B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public P f20780C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f20781D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public ByteBuffer f20782E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f20783F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public long f20784G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public long f20785H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public long f20786I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public long f20787J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f20788K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f20789L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f20790M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public long f20791N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public float f20792O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public ByteBuffer f20793P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public int f20794Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public ByteBuffer f20795R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f20796S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public boolean f20797T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public boolean f20798U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f20799V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public boolean f20800W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public int f20801X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public C1196d f20802Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public C1764u f20803Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f20804a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f20805a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h3.y f20806b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public long f20807b0;
    public final C2100o c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public long f20808c0;
    public final C2085B d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f20809d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d0 f20810e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f20811e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d0 f20812f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public Looper f20813f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C2099n f20814g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public long f20815g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayDeque f20816h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public long f20817h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f20818i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public Handler f20819i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f20820j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h3.y f20821k;
    public final A4.m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A4.m f20822m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w f20823n;
    public final m2.b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final w f20824p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public o2.l f20825q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C1764u f20826r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public r f20827s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public r f20828t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C1254a f20829u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public AudioTrack f20830v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C2087b f20831w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public C2090e f20832x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public h3.y f20833y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C1195c f20834z;

    public v(q qVar) {
        C2087b c2087bC;
        Context context = qVar.f20758a;
        this.f20804a = context;
        C1195c c1195c = C1195c.f14046g;
        this.f20834z = c1195c;
        if (context != null) {
            C2087b c2087b = C2087b.c;
            int i7 = AbstractC1343A.f15195a;
            c2087bC = C2087b.c(context, c1195c, null);
        } else {
            c2087bC = (C2087b) qVar.c;
        }
        this.f20831w = c2087bC;
        this.f20806b = (h3.y) qVar.d;
        int i10 = AbstractC1343A.f15195a;
        this.f20818i = false;
        this.f20820j = 0;
        this.f20823n = (w) qVar.f20760e;
        m2.b bVar = (m2.b) qVar.f20762g;
        bVar.getClass();
        this.o = bVar;
        this.f20814g = new C2099n(new C1764u(this, 12));
        C2100o c2100o = new C2100o();
        this.c = c2100o;
        C2085B c2085b = new C2085B();
        c2085b.f20691m = AbstractC1343A.f15198f;
        this.d = c2085b;
        f2.h hVar = new f2.h();
        C1078G c1078g = I.f13384m;
        Object[] objArr = {hVar, c2100o, c2085b};
        AbstractC1095q.d(3, objArr);
        this.f20810e = I.t(3, objArr);
        this.f20812f = I.B(new C2084A());
        this.f20792O = 1.0f;
        this.f20801X = 0;
        this.f20802Y = new C1196d();
        P p10 = P.d;
        this.B = new s(p10, 0L, 0L);
        this.f20780C = p10;
        this.f20781D = false;
        this.f20816h = new ArrayDeque();
        this.l = new A4.m();
        this.f20822m = new A4.m();
        this.f20824p = (w) qVar.f20761f;
    }

    public static boolean p(AudioTrack audioTrack) {
        return AbstractC1343A.f15195a >= 29 && audioTrack.isOffloadedPlayback();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(long r9) {
        /*
            r8 = this;
            boolean r0 = r8.x()
            h3.y r1 = r8.f20806b
            if (r0 != 0) goto L3f
            boolean r0 = r8.f20805a0
            if (r0 != 0) goto L39
            p2.r r0 = r8.f20828t
            int r2 = r0.c
            if (r2 != 0) goto L39
            e2.p r0 = r0.f20763a
            int r0 = r0.f14297E
            e2.P r0 = r8.f20780C
            r1.getClass()
            float r2 = r0.f13995a
            java.lang.Object r3 = r1.o
            f2.g r3 = (f2.g) r3
            float r4 = r3.c
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            r5 = 1
            if (r4 == 0) goto L2c
            r3.c = r2
            r3.f14580i = r5
        L2c:
            float r2 = r3.d
            float r4 = r0.f13996b
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L3b
            r3.d = r4
            r3.f14580i = r5
            goto L3b
        L39:
            e2.P r0 = e2.P.d
        L3b:
            r8.f20780C = r0
        L3d:
            r3 = r0
            goto L42
        L3f:
            e2.P r0 = e2.P.d
            goto L3d
        L42:
            boolean r0 = r8.f20805a0
            if (r0 != 0) goto L59
            p2.r r0 = r8.f20828t
            int r2 = r0.c
            if (r2 != 0) goto L59
            e2.p r0 = r0.f20763a
            int r0 = r0.f14297E
            boolean r0 = r8.f20781D
            java.lang.Object r1 = r1.f15500n
            p2.z r1 = (p2.z) r1
            r1.o = r0
            goto L5a
        L59:
            r0 = 0
        L5a:
            r8.f20781D = r0
            java.util.ArrayDeque r0 = r8.f20816h
            p2.s r1 = new p2.s
            r4 = 0
            long r4 = java.lang.Math.max(r4, r9)
            p2.r r9 = r8.f20828t
            long r6 = r8.k()
            int r9 = r9.f20765e
            long r6 = h2.AbstractC1343A.L(r6, r9)
            r2 = r1
            r2.<init>(r3, r4, r6)
            r0.add(r1)
            p2.r r9 = r8.f20828t
            f2.a r9 = r9.f20769i
            r8.f20829u = r9
            r9.b()
            l3.u r9 = r8.f20826r
            if (r9 == 0) goto L9c
            boolean r8 = r8.f20781D
            java.lang.Object r9 = r9.f18608m
            p2.x r9 = (p2.x) r9
            l8.g r9 = r9.f20838O0
            java.lang.Object r10 = r9.l
            android.os.Handler r10 = (android.os.Handler) r10
            if (r10 == 0) goto L9c
            p2.h r0 = new p2.h
            r0.<init>()
            r10.post(r0)
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.v.a(long):void");
    }

    public final AudioTrack b(C0063c c0063c, C1195c c1195c, int i7, C1208p c1208p) throws C2095j {
        try {
            AudioTrack audioTrackA = this.f20824p.a(c0063c, c1195c, i7);
            int state = audioTrackA.getState();
            if (state == 1) {
                return audioTrackA;
            }
            try {
                audioTrackA.release();
            } catch (Exception unused) {
            }
            throw new C2095j(state, c0063c.f552b, c0063c.c, c0063c.f551a, c1208p, c0063c.f553e, null);
        } catch (IllegalArgumentException | UnsupportedOperationException e10) {
            throw new C2095j(0, c0063c.f552b, c0063c.c, c0063c.f551a, c1208p, c0063c.f553e, e10);
        }
    }

    public final AudioTrack c(r rVar) throws C2095j {
        try {
            return b(rVar.a(), this.f20834z, this.f20801X, rVar.f20763a);
        } catch (C2095j e10) {
            C1764u c1764u = this.f20826r;
            if (c1764u != null) {
                c1764u.P(e10);
            }
            throw e10;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0197  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(e2.C1208p r27, int[] r28) throws p2.C2094i {
        /*
            Method dump skipped, instruction units count: 668
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.v.d(e2.p, int[]):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(long r13) throws p2.C2096k {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.v.e(long):void");
    }

    public final boolean f() throws C2096k {
        if (!this.f20829u.e()) {
            e(Long.MIN_VALUE);
            return this.f20795R == null;
        }
        C1254a c1254a = this.f20829u;
        if (c1254a.e() && !c1254a.d) {
            c1254a.d = true;
            ((f2.d) c1254a.f14546b.get(0)).e();
        }
        t(Long.MIN_VALUE);
        if (!this.f20829u.d()) {
            return false;
        }
        ByteBuffer byteBuffer = this.f20795R;
        return byteBuffer == null || !byteBuffer.hasRemaining();
    }

    public final void g() {
        h3.y yVar;
        if (o()) {
            this.f20784G = 0L;
            this.f20785H = 0L;
            this.f20786I = 0L;
            this.f20787J = 0L;
            this.f20811e0 = false;
            this.f20788K = 0;
            this.B = new s(this.f20780C, 0L, 0L);
            this.f20791N = 0L;
            this.f20779A = null;
            this.f20816h.clear();
            this.f20793P = null;
            this.f20794Q = 0;
            this.f20795R = null;
            this.f20797T = false;
            this.f20796S = false;
            this.f20798U = false;
            this.f20782E = null;
            this.f20783F = 0;
            this.d.o = 0L;
            C1254a c1254a = this.f20828t.f20769i;
            this.f20829u = c1254a;
            c1254a.b();
            AudioTrack audioTrack = this.f20814g.c;
            audioTrack.getClass();
            if (audioTrack.getPlayState() == 3) {
                this.f20830v.pause();
            }
            if (p(this.f20830v)) {
                h3.y yVar2 = this.f20821k;
                yVar2.getClass();
                this.f20830v.unregisterStreamEventCallback((u) yVar2.f15500n);
                ((Handler) yVar2.f15499m).removeCallbacksAndMessages(null);
            }
            C0063c c0063cA = this.f20828t.a();
            r rVar = this.f20827s;
            if (rVar != null) {
                this.f20828t = rVar;
                this.f20827s = null;
            }
            C2099n c2099n = this.f20814g;
            c2099n.d();
            c2099n.c = null;
            c2099n.f20735e = null;
            if (AbstractC1343A.f15195a >= 24 && (yVar = this.f20833y) != null) {
                t tVar = (t) yVar.o;
                tVar.getClass();
                ((AudioTrack) yVar.f15499m).removeOnRoutingChangedListener(tVar);
                yVar.o = null;
                this.f20833y = null;
            }
            AudioTrack audioTrack2 = this.f20830v;
            C1764u c1764u = this.f20826r;
            Handler handler = new Handler(Looper.myLooper());
            synchronized (f20776j0) {
                try {
                    if (f20777k0 == null) {
                        f20777k0 = Executors.newSingleThreadScheduledExecutor(new h2.z());
                    }
                    f20778l0++;
                    f20777k0.schedule(new io.sentry.android.replay.p(audioTrack2, c1764u, handler, c0063cA, 9), 20L, TimeUnit.MILLISECONDS);
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.f20830v = null;
        }
        A4.m mVar = this.f20822m;
        mVar.c = null;
        mVar.f426a = -9223372036854775807L;
        mVar.f427b = -9223372036854775807L;
        A4.m mVar2 = this.l;
        mVar2.c = null;
        mVar2.f426a = -9223372036854775807L;
        mVar2.f427b = -9223372036854775807L;
        this.f20815g0 = 0L;
        this.f20817h0 = 0L;
        Handler handler2 = this.f20819i0;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
    }

    public final C2091f h(C1208p c1208p) {
        int i7;
        boolean zBooleanValue;
        AudioManager audioManager;
        if (this.f20809d0) {
            return C2091f.d;
        }
        C1195c c1195c = this.f20834z;
        m2.b bVar = this.o;
        bVar.getClass();
        c1208p.getClass();
        c1195c.getClass();
        int i10 = AbstractC1343A.f15195a;
        if (i10 < 29 || (i7 = c1208p.f14296D) == -1) {
            return C2091f.d;
        }
        Boolean bool = (Boolean) bVar.f18945m;
        boolean z6 = false;
        if (bool != null) {
            zBooleanValue = bool.booleanValue();
        } else {
            Context context = (Context) bVar.l;
            if (context == null || (audioManager = (AudioManager) context.getSystemService("audio")) == null) {
                bVar.f18945m = Boolean.FALSE;
            } else {
                String parameters = audioManager.getParameters("offloadVariableRateSupported");
                bVar.f18945m = Boolean.valueOf(parameters != null && parameters.equals("offloadVariableRateSupported=1"));
            }
            zBooleanValue = ((Boolean) bVar.f18945m).booleanValue();
        }
        String str = c1208p.f14316n;
        str.getClass();
        int iB = L.b(str, c1208p.f14314k);
        if (iB == 0 || i10 < AbstractC1343A.n(iB)) {
            return C2091f.d;
        }
        int iP = AbstractC1343A.p(c1208p.f14295C);
        if (iP == 0) {
            return C2091f.d;
        }
        try {
            AudioFormat audioFormatO = AbstractC1343A.o(i7, iP, iB);
            if (i10 < 31) {
                if (!AudioManager.isOffloadedPlaybackSupported(audioFormatO, (AudioAttributes) c1195c.b().f13998a)) {
                    return C2091f.d;
                }
                V2.c cVar = new V2.c();
                cVar.f9334a = true;
                cVar.c = zBooleanValue;
                return cVar.a();
            }
            int playbackOffloadSupport = AudioManager.getPlaybackOffloadSupport(audioFormatO, (AudioAttributes) c1195c.b().f13998a);
            if (playbackOffloadSupport == 0) {
                return C2091f.d;
            }
            V2.c cVar2 = new V2.c();
            if (i10 > 32 && playbackOffloadSupport == 2) {
                z6 = true;
            }
            cVar2.f9334a = true;
            cVar2.f9335b = z6;
            cVar2.c = zBooleanValue;
            return cVar2.a();
        } catch (IllegalArgumentException unused) {
            return C2091f.d;
        }
    }

    public final int i(C1208p c1208p) {
        q();
        if (!"audio/raw".equals(c1208p.f14316n)) {
            return this.f20831w.d(this.f20834z, c1208p) != null ? 2 : 0;
        }
        int i7 = c1208p.f14297E;
        if (AbstractC1343A.D(i7)) {
            return i7 != 2 ? 1 : 2;
        }
        AbstractC1347d.p("Invalid PCM encoding: ", i7, "DefaultAudioSink");
        return 0;
    }

    public final long j() {
        r rVar = this.f20828t;
        return rVar.c == 0 ? this.f20784G / ((long) rVar.f20764b) : this.f20785H;
    }

    public final long k() {
        r rVar = this.f20828t;
        if (rVar.c != 0) {
            return this.f20787J;
        }
        long j3 = this.f20786I;
        long j10 = rVar.d;
        int i7 = AbstractC1343A.f15195a;
        return ((j3 + j10) - 1) / j10;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x010d, code lost:
    
        if (r10.b() == 0) goto L72;
     */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0279  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean l(java.nio.ByteBuffer r24, long r25, int r27) throws p2.C2096k, p2.C2095j {
        /*
            Method dump skipped, instruction units count: 1182
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.v.l(java.nio.ByteBuffer, long, int):boolean");
    }

    public final boolean m() {
        return o() && !(AbstractC1343A.f15195a >= 29 && this.f20830v.isOffloadedPlayback() && this.f20798U) && this.f20814g.c(k());
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x000c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean n() throws p2.C2095j {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.v.n():boolean");
    }

    public final boolean o() {
        return this.f20830v != null;
    }

    public final void q() {
        Context context;
        C2087b c2087bB;
        C2088c c2088c;
        if (this.f20832x != null || (context = this.f20804a) == null) {
            return;
        }
        this.f20813f0 = Looper.myLooper();
        C2090e c2090e = new C2090e(context, new C2026c(this, 3), this.f20834z, this.f20803Z);
        this.f20832x = c2090e;
        if (c2090e.f20708j) {
            c2087bB = c2090e.f20705g;
            c2087bB.getClass();
        } else {
            c2090e.f20708j = true;
            C2089d c2089d = c2090e.f20704f;
            if (c2089d != null) {
                c2089d.f20699a.registerContentObserver(c2089d.f20700b, false, c2089d);
            }
            int i7 = AbstractC1343A.f15195a;
            Handler handler = c2090e.c;
            Context context2 = c2090e.f20701a;
            if (i7 >= 23 && (c2088c = c2090e.d) != null) {
                AudioManager audioManager = (AudioManager) context2.getSystemService("audio");
                audioManager.getClass();
                audioManager.registerAudioDeviceCallback(c2088c, handler);
            }
            c2087bB = C2087b.b(context2, context2.registerReceiver(c2090e.f20703e, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"), null, handler), c2090e.f20707i, c2090e.f20706h);
            c2090e.f20705g = c2087bB;
        }
        this.f20831w = c2087bB;
    }

    public final void r() {
        this.f20799V = true;
        if (o()) {
            C2099n c2099n = this.f20814g;
            if (c2099n.f20752x != -9223372036854775807L) {
                c2099n.f20732I.getClass();
                c2099n.f20752x = AbstractC1343A.H(SystemClock.elapsedRealtime());
            }
            C2098m c2098m = c2099n.f20735e;
            c2098m.getClass();
            c2098m.a();
            this.f20830v.play();
        }
    }

    public final void s() {
        if (this.f20797T) {
            return;
        }
        this.f20797T = true;
        long jK = k();
        C2099n c2099n = this.f20814g;
        c2099n.f20754z = c2099n.b();
        c2099n.f20732I.getClass();
        c2099n.f20752x = AbstractC1343A.H(SystemClock.elapsedRealtime());
        c2099n.f20725A = jK;
        if (p(this.f20830v)) {
            this.f20798U = false;
        }
        this.f20830v.stop();
        this.f20783F = 0;
    }

    public final void t(long j3) throws C2096k {
        ByteBuffer byteBuffer;
        e(j3);
        if (this.f20795R != null) {
            return;
        }
        if (!this.f20829u.e()) {
            ByteBuffer byteBuffer2 = this.f20793P;
            if (byteBuffer2 != null) {
                w(byteBuffer2);
                e(j3);
                return;
            }
            return;
        }
        while (!this.f20829u.d()) {
            do {
                C1254a c1254a = this.f20829u;
                if (c1254a.e()) {
                    ByteBuffer byteBuffer3 = c1254a.c[c1254a.c()];
                    if (byteBuffer3.hasRemaining()) {
                        byteBuffer = byteBuffer3;
                    } else {
                        c1254a.f(f2.d.f14550a);
                        byteBuffer = c1254a.c[c1254a.c()];
                    }
                } else {
                    byteBuffer = f2.d.f14550a;
                }
                if (byteBuffer.hasRemaining()) {
                    w(byteBuffer);
                    e(j3);
                } else {
                    ByteBuffer byteBuffer4 = this.f20793P;
                    if (byteBuffer4 == null || !byteBuffer4.hasRemaining()) {
                        return;
                    }
                    C1254a c1254a2 = this.f20829u;
                    ByteBuffer byteBuffer5 = this.f20793P;
                    if (c1254a2.e() && !c1254a2.d) {
                        c1254a2.f(byteBuffer5);
                    }
                }
            } while (this.f20795R == null);
            return;
        }
    }

    public final void u() {
        g();
        C1078G c1078gY = this.f20810e.listIterator(0);
        while (c1078gY.hasNext()) {
            ((f2.d) c1078gY.next()).d();
        }
        C1078G c1078gY2 = this.f20812f.listIterator(0);
        while (c1078gY2.hasNext()) {
            ((f2.d) c1078gY2.next()).d();
        }
        C1254a c1254a = this.f20829u;
        if (c1254a != null) {
            int i7 = 0;
            while (true) {
                d0 d0Var = c1254a.f14545a;
                if (i7 >= d0Var.size()) {
                    break;
                }
                f2.d dVar = (f2.d) d0Var.get(i7);
                dVar.flush();
                dVar.d();
                i7++;
            }
            c1254a.c = new ByteBuffer[0];
            f2.b bVar = f2.b.f14547e;
            c1254a.d = false;
        }
        this.f20799V = false;
        this.f20809d0 = false;
    }

    public final void v() {
        if (o()) {
            try {
                this.f20830v.setPlaybackParams(new PlaybackParams().allowDefaults().setSpeed(this.f20780C.f13995a).setPitch(this.f20780C.f13996b).setAudioFallbackMode(2));
            } catch (IllegalArgumentException e10) {
                AbstractC1346c.A("DefaultAudioSink", "Failed to set playback params", e10);
            }
            P p10 = new P(this.f20830v.getPlaybackParams().getSpeed(), this.f20830v.getPlaybackParams().getPitch());
            this.f20780C = p10;
            C2099n c2099n = this.f20814g;
            c2099n.f20739i = p10.f13995a;
            C2098m c2098m = c2099n.f20735e;
            if (c2098m != null) {
                c2098m.a();
            }
            c2099n.d();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0055 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(java.nio.ByteBuffer r17) {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.v.w(java.nio.ByteBuffer):void");
    }

    public final boolean x() {
        r rVar = this.f20828t;
        return rVar != null && rVar.f20770j && AbstractC1343A.f15195a >= 23;
    }
}
