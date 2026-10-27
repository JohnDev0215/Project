package k3;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.SparseBooleanArray;
import com.stingray.karaoke.car.player.PlayerService;
import e2.C1202j;
import e2.C1206n;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h5.C1373E;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public class I0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final long f17792A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public d5.I f17793C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final d5.d0 f17794D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final Bundle f17795E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f17796a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Uri f17797b;
    public final F0 c;
    public final E0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l3.P f17798e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final PlayerService f17799f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final m1 f17800g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Q0 f17801h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f17802i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final E1 f17803j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final A0 f17804k;
    public final Handler l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f17805m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final B0 f17806n;
    public final Handler o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f17807p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f17808q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final d5.d0 f17809r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public q1 f17810s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public u1 f17811t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final PendingIntent f17812u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public G0 f17813v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public e7.f f17814w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public C1679y0 f17815x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public S0 f17816y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f17817z;

    static {
        new C1(1);
    }

    public I0(A0 a02, PlayerService playerService, N5.b0 b0Var, PendingIntent pendingIntent, d5.d0 d0Var, d5.d0 d0Var2, d5.d0 d0Var3, l3.P p10, Bundle bundle, Bundle bundle2, io.sentry.internal.debugmeta.c cVar) {
        AbstractC1346c.t("MediaSessionImpl", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.0] [" + AbstractC1343A.f15197e + "]");
        this.f17804k = a02;
        this.f17799f = playerService;
        this.f17802i = "";
        this.f17812u = pendingIntent;
        this.f17793C = d0Var;
        this.f17794D = d0Var2;
        this.f17809r = d0Var3;
        this.f17798e = p10;
        this.f17795E = bundle2;
        this.f17805m = cVar;
        this.f17807p = true;
        this.f17808q = true;
        m1 m1Var = new m1(this);
        this.f17800g = m1Var;
        this.o = new Handler(Looper.getMainLooper());
        Looper looperT0 = ((e2.W) b0Var.f14069b).T0();
        Handler handler = new Handler(looperT0);
        this.l = handler;
        this.f17810s = q1.f18034F;
        this.c = new F0(this, looperT0);
        this.d = new E0(this, looperT0);
        Uri uriBuild = new Uri.Builder().scheme(I0.class.getName()).appendPath("").appendPath(String.valueOf(SystemClock.elapsedRealtime())).build();
        this.f17797b = uriBuild;
        Q0 q02 = new Q0(this, uriBuild, handler, bundle);
        this.f17801h = q02;
        this.f17803j = new E1(Process.myUid(), 1005000300, 4, playerService.getPackageName(), m1Var, bundle, (MediaSession.Token) ((l3.L) q02.f17847j.f18537m).c.f18534m);
        e2.S s6 = C1675w0.f18148f;
        z1 z1Var = C1675w0.f18147e;
        u1 u1Var = new u1(b0Var);
        u1Var.d = d0Var;
        u1Var.f18132e = d0Var2;
        u1Var.f18133f = z1Var;
        u1Var.f18134g = s6;
        u1Var.c = bundle2;
        this.f17811t = u1Var;
        AbstractC1343A.K(handler, new A2.m(this, 23, u1Var));
        this.f17792A = 3000L;
        this.f17806n = new B0(this, 2);
        AbstractC1343A.K(handler, new B0(this, 3));
    }

    public static boolean i(C1679y0 c1679y0) {
        return c1679y0 != null && c1679y0.f18161b == 0 && Objects.equals(c1679y0.b(), "com.android.systemui");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.view.KeyEvent r3, boolean r4) {
        /*
            r2 = this;
            k3.A0 r0 = r2.f17804k
            k3.I0 r0 = r0.f17714a
            k3.y0 r0 = r0.d()
            r0.getClass()
            int r3 = r3.getKeyCode()
            r1 = 85
            if (r3 == r1) goto L17
            r1 = 79
            if (r3 != r1) goto L1b
        L17:
            if (r4 == 0) goto L1b
            r3 = 87
        L1b:
            r4 = 126(0x7e, float:1.77E-43)
            if (r3 == r4) goto L72
            r4 = 127(0x7f, float:1.78E-43)
            if (r3 == r4) goto L6b
            r4 = 272(0x110, float:3.81E-43)
            if (r3 == r4) goto L63
            r4 = 273(0x111, float:3.83E-43)
            if (r3 == r4) goto L5b
            switch(r3) {
                case 85: goto L45;
                case 86: goto L3e;
                case 87: goto L63;
                case 88: goto L5b;
                case 89: goto L37;
                case 90: goto L30;
                default: goto L2e;
            }
        L2e:
            r2 = 0
            return r2
        L30:
            k3.c r3 = new k3.c
            r4 = 1
            r3.<init>(r2, r0, r4)
            goto L78
        L37:
            k3.c r3 = new k3.c
            r4 = 2
            r3.<init>(r2, r0, r4)
            goto L78
        L3e:
            k3.c r3 = new k3.c
            r4 = 3
            r3.<init>(r2, r0, r4)
            goto L78
        L45:
            k3.u1 r3 = r2.f17811t
            boolean r3 = r3.C()
            if (r3 == 0) goto L54
            k3.c r3 = new k3.c
            r4 = 4
            r3.<init>(r2, r0, r4)
            goto L78
        L54:
            k3.c r3 = new k3.c
            r4 = 5
            r3.<init>(r2, r0, r4)
            goto L78
        L5b:
            k3.c r3 = new k3.c
            r4 = 9
            r3.<init>(r2, r0, r4)
            goto L78
        L63:
            k3.c r3 = new k3.c
            r4 = 8
            r3.<init>(r2, r0, r4)
            goto L78
        L6b:
            k3.c r3 = new k3.c
            r4 = 7
            r3.<init>(r2, r0, r4)
            goto L78
        L72:
            k3.c r3 = new k3.c
            r4 = 6
            r3.<init>(r2, r0, r4)
        L78:
            android.os.Handler r4 = r2.l
            k3.C0 r1 = new k3.C0
            r1.<init>(r2, r3, r0)
            h2.AbstractC1343A.K(r4, r1)
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.I0.a(android.view.KeyEvent, boolean):boolean");
    }

    public final void b(C1679y0 c1679y0, H0 h02) {
        int iU;
        m1 m1Var = this.f17800g;
        try {
            E0.X xY = m1Var.f18014e.Y(c1679y0);
            if (xY != null) {
                iU = xY.u();
            } else {
                if (!(m1Var.f18014e.a0(c1679y0) || this.f17801h.f17842e.a0(c1679y0))) {
                    return;
                } else {
                    iU = 0;
                }
            }
            InterfaceC1677x0 interfaceC1677x0 = c1679y0.d;
            if (interfaceC1677x0 != null) {
                h02.c(interfaceC1677x0, iU);
            }
        } catch (DeadObjectException unused) {
            m1Var.f18014e.l0(c1679y0);
        } catch (RemoteException e10) {
            AbstractC1346c.A("MediaSessionImpl", "Exception in " + c1679y0.toString(), e10);
        }
    }

    public final void c(H0 h02) {
        d5.I iU = this.f17800g.f18014e.U();
        for (int i7 = 0; i7 < iU.size(); i7++) {
            b((C1679y0) iU.get(i7), h02);
        }
        try {
            h02.c(this.f17801h.f17845h, 0);
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    public final C1679y0 d() {
        d5.I iU = this.f17800g.f18014e.U();
        for (int i7 = 0; i7 < iU.size(); i7++) {
            C1679y0 c1679y0 = (C1679y0) iU.get(i7);
            if (g(c1679y0)) {
                return c1679y0;
            }
        }
        return null;
    }

    public final void e(e2.S s6) {
        this.c.a(false, false);
        c(new C1654l0(s6));
        try {
            i4.d dVar = this.f17801h.f17845h;
            C1202j c1202j = this.f17810s.f18083q;
            dVar.o();
        } catch (RemoteException e10) {
            AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
        }
    }

    public final void f(C1679y0 c1679y0, boolean z6) {
        if (n()) {
            boolean z10 = this.f17811t.R0(16) && this.f17811t.Q0() != null;
            boolean z11 = this.f17811t.R0(31) || this.f17811t.R0(20);
            C1679y0 c1679y0R = r(c1679y0);
            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
            AbstractC1346c.j(!false);
            sparseBooleanArray.append(1, true);
            AbstractC1346c.j(!false);
            e2.S s6 = new e2.S(new C1206n(sparseBooleanArray));
            if (z10 || !z11) {
                if (!z10) {
                    AbstractC1346c.z("MediaSessionImpl", "Play requested without current MediaItem, but playback resumption prevented by missing available commands");
                }
                AbstractC1343A.A(this.f17811t);
                if (z6) {
                    o(c1679y0R);
                    return;
                }
                return;
            }
            this.f17798e.getClass();
            UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();
            h5.u uVar = new h5.u();
            uVar.m(unsupportedOperationException);
            uVar.b(new h5.t(uVar, 0, new t4.e(this, c1679y0R, z6, s6)), new T0.C(this, 2));
        }
    }

    public final boolean g(C1679y0 c1679y0) {
        return Objects.equals(c1679y0.b(), this.f17799f.getPackageName()) && c1679y0.f18161b != 0 && c1679y0.a().getBoolean("androidx.media3.session.MediaNotificationManager", false);
    }

    public final boolean h() {
        boolean z6;
        synchronized (this.f17796a) {
            z6 = this.f17817z;
        }
        return z6;
    }

    public final h5.y j(C1679y0 c1679y0, d5.d0 d0Var) {
        return this.f17798e.x(this.f17804k, r(c1679y0), d0Var);
    }

    public final C1675w0 k(C1679y0 c1679y0) {
        int i7 = 0;
        if (this.B && i(c1679y0)) {
            z1 z1Var = C1675w0.f18147e;
            z1 z1Var2 = this.f17811t.f18133f;
            z1Var2.getClass();
            e2.S s6 = this.f17811t.f18134g;
            s6.getClass();
            d5.I i10 = this.f17811t.d;
            d5.I iW = i10 == null ? null : d5.I.w(i10);
            d5.I i11 = this.f17811t.f18132e;
            return new C1675w0(z1Var2, s6, iW, i11 != null ? d5.I.w(i11) : null);
        }
        this.f17798e.getClass();
        A0 a02 = this.f17804k;
        A7.m.f(a02, "session");
        z1 z1Var3 = C1675w0.f18147e;
        z1Var3.getClass();
        HashSet hashSet = new HashSet(z1Var3.f18173a);
        Bundle bundle = Bundle.EMPTY;
        hashSet.add(new y1("com.stingray.karaoke.ADD_TO_FAVORITES", bundle));
        hashSet.add(new y1("com.stingray.karaoke.REMOVE_FROM_FAVORITES", bundle));
        hashSet.add(new y1("com.stingray.karaoke.SET_IS_FAVORITE", bundle));
        hashSet.add(new y1("com.stingray.karaoke.SET_IS_NOT_FAVORITE", bundle));
        hashSet.add(new y1("com.stingray.karaoke.QUERY_IS_FAVORITE", bundle));
        z1 z1Var4 = new z1(hashSet);
        e2.S s10 = C1675w0.f18148f;
        C1675w0 c1675w0 = new C1675w0(z1Var4, s10, null, null);
        if (g(c1679y0)) {
            this.B = true;
            u1 u1Var = this.f17811t;
            I0 i02 = a02.f17714a;
            u1Var.d = i02.f17793C;
            u1Var.f18132e = i02.f17794D;
            boolean z6 = u1Var.f18134g.a(17) != s10.a(17);
            u1 u1Var2 = this.f17811t;
            u1Var2.f18133f = z1Var4;
            u1Var2.f18134g = s10;
            Q0 q02 = this.f17801h;
            if (z6) {
                AbstractC1343A.K(q02.f17843f.l, new L0(q02, u1Var2, i7));
            } else {
                q02.L(u1Var2);
            }
        }
        return c1675w0;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final h5.y l(C1679y0 c1679y0, y1 y1Var, Bundle bundle) {
        N5.Q q4;
        N5.Q q10;
        C1679y0 c1679y0R = r(c1679y0);
        l3.P p10 = this.f17798e;
        p10.getClass();
        A7.m.f(this.f17804k, "session");
        A7.m.f(c1679y0R, "controller");
        A7.m.f(bundle, "args");
        String str = y1Var.f18168b;
        int iHashCode = str.hashCode();
        PlayerService playerService = (PlayerService) p10.f18537m;
        switch (iHashCode) {
            case -1456713293:
                if (str.equals("com.stingray.karaoke.ADD_TO_FAVORITES") && (q4 = PlayerService.f13011x) != null) {
                    q4.a(true);
                }
                break;
            case 136430354:
                if (str.equals("com.stingray.karaoke.SET_IS_FAVORITE")) {
                    A0 a02 = playerService.f13019t;
                    if (a02 != null) {
                        d5.d0 d0VarB = d5.I.B((C1627b) p10.f18538n);
                        AbstractC1346c.h(d0VarB, "layout must not be null");
                        d5.I iW = d5.I.w(d0VarB);
                        I0 i02 = a02.f17714a;
                        i02.f17793C = iW;
                        i02.f17811t.d = iW;
                        i02.c(new G2.a(iW, 21));
                    }
                    playerService.f13018s = true;
                }
                break;
            case 145093662:
                if (str.equals("com.stingray.karaoke.SET_IS_NOT_FAVORITE")) {
                    A0 a03 = playerService.f13019t;
                    if (a03 != null) {
                        d5.d0 d0VarB2 = d5.I.B((C1627b) p10.o);
                        AbstractC1346c.h(d0VarB2, "layout must not be null");
                        d5.I iW2 = d5.I.w(d0VarB2);
                        I0 i03 = a03.f17714a;
                        i03.f17793C = iW2;
                        i03.f17811t.d = iW2;
                        i03.c(new G2.a(iW2, 21));
                    }
                    playerService.f13018s = false;
                }
                break;
            case 184447227:
                if (str.equals("com.stingray.karaoke.REMOVE_FROM_FAVORITES") && (q10 = PlayerService.f13011x) != null) {
                    q10.a(false);
                }
                break;
            case 662868728:
                if (str.equals("com.stingray.karaoke.QUERY_IS_FAVORITE")) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean("com.stingray.karaoke.QUERY_IS_FAVORITE_EXTRA", playerService.f13018s);
                    return l9.l.T(new C1(0, bundle2));
                }
                break;
        }
        return l9.l.T(new C1(0));
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean m(k3.C1679y0 r10, android.content.Intent r11) {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.I0.m(k3.y0, android.content.Intent):boolean");
    }

    public final boolean n() {
        int i7;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            C1373E c1373e = new C1373E();
            this.o.post(new A2.m(this, 22, c1373e));
            try {
                return ((Boolean) c1373e.get()).booleanValue();
            } catch (InterruptedException | ExecutionException e10) {
                throw new IllegalStateException(e10);
            }
        }
        e7.f fVar = this.f17814w;
        if (fVar == null || (i7 = AbstractC1343A.f15195a) < 31 || i7 >= 33) {
            return true;
        }
        PlayerService playerService = (PlayerService) fVar.f14480m;
        if (playerService.c().f18131j) {
            return true;
        }
        return playerService.j(this.f17804k, true);
    }

    public final void o(C1679y0 c1679y0) {
        r(c1679y0);
        this.f17798e.getClass();
    }

    public final C1373E p(C1679y0 c1679y0, d5.d0 d0Var, final int i7, final long j3) {
        return AbstractC1343A.S(this.f17798e.x(this.f17804k, r(c1679y0), d0Var), new h5.q() { // from class: k3.v0
            @Override // h5.q
            public final h5.y apply(Object obj) {
                return l9.l.T(new C1681z0((List) obj, i7, j3));
            }
        });
    }

    public final void q() {
        String str;
        int i7 = 0;
        StringBuilder sb = new StringBuilder("Release ");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" [AndroidXMedia3/1.5.0] [");
        sb.append(AbstractC1343A.f15197e);
        sb.append("] [");
        HashSet hashSet = e2.G.f13882a;
        synchronized (e2.G.class) {
            str = e2.G.f13883b;
        }
        sb.append(str);
        sb.append("]");
        AbstractC1346c.t("MediaSessionImpl", sb.toString());
        synchronized (this.f17796a) {
            try {
                if (this.f17817z) {
                    return;
                }
                this.f17817z = true;
                E0 e02 = this.d;
                T1.k kVar = (T1.k) e02.f17756b;
                if (kVar != null) {
                    e02.removeCallbacks(kVar);
                    e02.f17756b = null;
                }
                this.l.removeCallbacksAndMessages(null);
                try {
                    AbstractC1343A.K(this.l, new B0(this, i7));
                } catch (Exception e10) {
                    AbstractC1346c.A("MediaSessionImpl", "Exception thrown while closing", e10);
                }
                Q0 q02 = this.f17801h;
                q02.getClass();
                int i10 = AbstractC1343A.f15195a;
                I0 i02 = q02.f17843f;
                l3.P p10 = q02.f17847j;
                if (i10 < 31) {
                    ComponentName componentName = q02.l;
                    if (componentName == null) {
                        ((l3.L) p10.f18537m).f18523a.setMediaButtonReceiver(null);
                    } else {
                        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON", i02.f17797b);
                        intent.setComponent(componentName);
                        ((l3.L) p10.f18537m).f18523a.setMediaButtonReceiver(PendingIntent.getBroadcast(i02.f17799f, 0, intent, Q0.f17841q));
                    }
                }
                h2.r rVar = q02.f17848k;
                if (rVar != null) {
                    i02.f17799f.unregisterReceiver(rVar);
                }
                l3.L l = (l3.L) p10.f18537m;
                l.f18526f.kill();
                MediaSession mediaSession = l.f18523a;
                mediaSession.setCallback(null);
                l.f18524b.c.set(null);
                mediaSession.release();
                m1 m1Var = this.f17800g;
                Iterator it = m1Var.f18014e.U().iterator();
                while (it.hasNext()) {
                    InterfaceC1677x0 interfaceC1677x0 = ((C1679y0) it.next()).d;
                    if (interfaceC1677x0 != null) {
                        try {
                            interfaceC1677x0.a();
                        } catch (RemoteException unused) {
                        }
                    }
                }
                Iterator it2 = m1Var.f18015f.iterator();
                while (it2.hasNext()) {
                    InterfaceC1677x0 interfaceC1677x02 = ((C1679y0) it2.next()).d;
                    if (interfaceC1677x02 != null) {
                        try {
                            interfaceC1677x02.a();
                        } catch (RemoteException unused2) {
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final C1679y0 r(C1679y0 c1679y0) {
        if (!this.B || !i(c1679y0)) {
            return c1679y0;
        }
        C1679y0 c1679y0D = d();
        c1679y0D.getClass();
        return c1679y0D;
    }

    public final void s() {
        Handler handler = this.l;
        B0 b02 = this.f17806n;
        handler.removeCallbacks(b02);
        if (this.f17808q) {
            long j3 = this.f17792A;
            if (j3 > 0) {
                if (this.f17811t.O() || this.f17811t.d()) {
                    handler.postDelayed(b02, j3);
                }
            }
        }
    }

    public final void t() {
        if (Looper.myLooper() != this.l.getLooper()) {
            throw new IllegalStateException("Player callback method is called from a wrong thread. See javadoc of MediaSession for details.");
        }
    }
}
