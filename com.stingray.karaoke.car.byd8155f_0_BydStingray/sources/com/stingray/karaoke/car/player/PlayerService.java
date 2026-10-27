package com.stingray.karaoke.car.player;

import A2.m;
import C1.j;
import K.L0;
import N5.C0452h;
import N5.Q;
import N5.b0;
import N5.c0;
import T1.k;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.session.MediaSessionManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.stingray.drivemodedetector.R;
import d5.C1078G;
import d5.I;
import d5.d0;
import e2.C1195c;
import e2.W;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.s;
import h5.y;
import i0.AbstractC1397a;
import io.sentry.android.replay.p;
import io.sentry.internal.debugmeta.c;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import k2.i;
import k3.A0;
import k3.C1624a;
import k3.C1627b;
import k3.C1671u0;
import k3.C1672v;
import k3.I0;
import k3.InterfaceC1651k;
import k3.InterfaceC1670u;
import k3.R0;
import k3.RunnableC1664q0;
import k3.S0;
import k3.y1;
import kotlin.Metadata;
import l3.L;
import l3.O;
import l3.P;
import l9.l;
import n2.C1914n;
import n2.C1915o;
import n2.C1923x;
import t4.e;
import u.C2465e;
import u2.V;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000:\u0002\u0003\u0004B\u0007¢\u0006\u0004\b\u0001\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/stingray/karaoke/car/player/PlayerService;", "<init>", "()V", "N5/Q", "C8/d", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"UnsafeOptInUsageError"})
public final class PlayerService extends Service {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static C1923x f13008u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static V f13009v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static C0452h f13010w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static Q f13011x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static C0452h f13012y;
    public final Object l = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Handler f13013m = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C2465e f13014n = new C2465e(0);
    public R0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C1671u0 f13015p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public s f13016q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public j f13017r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f13018s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public A0 f13019t;

    public final void a(A0 a02) {
        A0 a03;
        AbstractC1346c.h(a02, "session must not be null");
        boolean z6 = true;
        AbstractC1346c.b("session is already released", !a02.f17714a.h());
        synchronized (this.l) {
            a03 = (A0) this.f13014n.get(a02.f17714a.f17802i);
            if (a03 != null && a03 != a02) {
                z6 = false;
            }
            AbstractC1346c.b("Session ID should be unique", z6);
            this.f13014n.put(a02.f17714a.f17802i, a02);
        }
        if (a03 == null) {
            AbstractC1343A.K(this.f13013m, new k(this, c(), a02, 11));
        }
    }

    public final j b() {
        j jVar;
        synchronized (this.l) {
            try {
                if (this.f13017r == null) {
                    this.f13017r = new j(this);
                }
                jVar = this.f13017r;
            } catch (Throwable th) {
                throw th;
            }
        }
        return jVar;
    }

    public final C1671u0 c() {
        C1671u0 c1671u0;
        synchronized (this.l) {
            try {
                if (this.f13015p == null) {
                    if (this.f13016q == null) {
                        e eVar = new e(getApplicationContext(), 4);
                        AbstractC1346c.j(!eVar.f22462m);
                        s sVar = new s();
                        Context context = (Context) eVar.f22463n;
                        sVar.f15238b = context;
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                        AbstractC1346c.k(notificationManager);
                        sVar.c = notificationManager;
                        sVar.f15237a = R.drawable.media3_notification_small_icon;
                        eVar.f22462m = true;
                        this.f13016q = sVar;
                    }
                    this.f13015p = new C1671u0(this, this.f13016q, b());
                }
                c1671u0 = this.f13015p;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c1671u0;
    }

    public final boolean d(A0 a02) {
        boolean zContainsKey;
        synchronized (this.l) {
            zContainsKey = this.f13014n.containsKey(a02.f17714a.f17802i);
        }
        return zContainsKey;
    }

    public final void e() {
        super.onCreate();
        synchronized (this.l) {
            this.o = new R0(this);
        }
    }

    public final void f() {
        super.onDestroy();
        synchronized (this.l) {
            try {
                R0 r02 = this.o;
                if (r02 != null) {
                    r02.c.clear();
                    r02.d.removeCallbacksAndMessages(null);
                    Iterator it = r02.f17853f.iterator();
                    while (it.hasNext()) {
                        try {
                            ((InterfaceC1651k) it.next()).a();
                        } catch (RemoteException unused) {
                        }
                    }
                    this.o = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final int g(Intent intent, int i7, int i10) {
        A0 a02;
        A0 a03;
        if (intent == null) {
            return 1;
        }
        j jVarB = b();
        Uri data = intent.getData();
        if (data != null) {
            synchronized (A0.f17713b) {
                try {
                    Iterator it = A0.c.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            a03 = null;
                            break;
                        }
                        a03 = (A0) it.next();
                        Uri uri = a03.f17714a.f17797b;
                        int i11 = AbstractC1343A.f15195a;
                        if (Objects.equals(uri, data)) {
                        }
                    }
                } finally {
                }
            }
            a02 = a03;
        } else {
            a02 = null;
        }
        jVarB.getClass();
        if ("android.intent.action.MEDIA_BUTTON".equals(intent.getAction())) {
            if (a02 == null) {
                if (TextUtils.isEmpty("android.media.session.MediaController")) {
                    throw new IllegalArgumentException("packageName should be nonempty");
                }
                new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", -1, -1);
                Bundle bundle = Bundle.EMPTY;
                a02 = this.f13019t;
                if (a02 == null) {
                    return 1;
                }
                a(a02);
            }
            I0 i02 = a02.f17714a;
            i02.l.post(new m(i02, 25, intent));
        } else if (a02 != null && "androidx.media3.session.CUSTOM_NOTIFICATION_ACTION".equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Object obj = extras != null ? extras.get("androidx.media3.session.EXTRAS_KEY_CUSTOM_NOTIFICATION_ACTION") : null;
            String str = obj instanceof String ? (String) obj : null;
            if (str == null) {
                return 1;
            }
            Bundle extras2 = intent.getExtras();
            Object obj2 = extras2 != null ? extras2.get("androidx.media3.session.EXTRAS_KEY_CUSTOM_NOTIFICATION_ACTION_EXTRAS") : null;
            Bundle bundle2 = obj2 instanceof Bundle ? (Bundle) obj2 : Bundle.EMPTY;
            C1671u0 c1671u0C = c();
            C1672v c1672vA = c1671u0C.a(a02);
            if (c1672vA != null) {
                AbstractC1343A.K(new Handler(a02.b().T0()), new p(c1671u0C, a02, str, bundle2, c1672vA));
            }
        }
        return 1;
    }

    public final void h(Intent intent) {
        if (c().f18131j) {
            return;
        }
        stopSelf();
    }

    public final void i(A0 a02, boolean z6) {
        C1672v c1672vA;
        C1672v c1672v;
        I iR0;
        A7.m.f(a02, "session");
        C1671u0 c1671u0C = c();
        if (!c1671u0C.f18124a.d(a02) || (c1672vA = c1671u0C.a(a02)) == null || c1672vA.t0().p() || c1672vA.e() == 1) {
            c1671u0C.b(true);
            return;
        }
        int i7 = c1671u0C.f18129h + 1;
        c1671u0C.f18129h = i7;
        y yVar = (y) c1671u0C.f18128g.get(a02);
        if (yVar == null || !yVar.isDone()) {
            c1672v = null;
        } else {
            try {
                c1672v = (C1672v) l.N(yVar);
            } catch (ExecutionException unused) {
                c1672v = null;
            }
        }
        if (c1672v != null) {
            c1672v.Z0();
            InterfaceC1670u interfaceC1670u = c1672v.c;
            if (interfaceC1670u.v()) {
                iR0 = interfaceC1670u.R0();
            } else {
                C1078G c1078g = I.f13384m;
                iR0 = d0.f13415p;
            }
        } else {
            C1078G c1078g2 = I.f13384m;
            iR0 = d0.f13415p;
        }
        AbstractC1343A.K(new Handler(a02.b().T0()), new RunnableC1664q0(c1671u0C, a02, iR0, new k3.V(c1671u0C, i7, a02, 2), z6, 0));
    }

    public final boolean j(A0 a02, boolean z6) {
        try {
            i(a02, c().c(a02, z6));
            return true;
        } catch (IllegalStateException e10) {
            if (AbstractC1343A.f15195a < 31 || !AbstractC1397a.x(e10)) {
                throw e10;
            }
            AbstractC1346c.q("MSessionService", "Failed to start foreground", e10);
            this.f13013m.post(new A2.l(this, 23));
            return false;
        }
    }

    public final void k(A0 a02) {
        AbstractC1346c.h(a02, "session must not be null");
        synchronized (this.l) {
            AbstractC1346c.b("session not found", this.f13014n.containsKey(a02.f17714a.f17802i));
            this.f13014n.remove(a02.f17714a.f17802i);
        }
        AbstractC1343A.K(this.f13013m, new m(c(), 26, a02));
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        String action;
        R0 r02;
        S0 s02;
        if (intent == null || (action = intent.getAction()) == null) {
            return null;
        }
        if (action.equals("androidx.media3.session.MediaSessionService")) {
            synchronized (this.l) {
                r02 = this.o;
                AbstractC1346c.k(r02);
            }
            return r02;
        }
        if (!action.equals("android.media.browse.MediaBrowserService")) {
            return null;
        }
        if (TextUtils.isEmpty("android.media.session.MediaController")) {
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", -1, -1);
        Bundle bundle = Bundle.EMPTY;
        A0 a02 = this.f13019t;
        if (a02 == null) {
            return null;
        }
        a(a02);
        I0 i02 = a02.f17714a;
        synchronized (i02.f17796a) {
            try {
                if (i02.f17816y == null) {
                    O o = ((L) i02.f17804k.f17714a.f17801h.f17847j.f18537m).c;
                    S0 s03 = new S0(i02);
                    s03.b(o);
                    i02.f17816y = s03;
                }
                s02 = i02.f17816y;
            } catch (Throwable th) {
                throw th;
            }
        }
        return s02.onBind(new Intent("android.media.browse.MediaBrowserService"));
    }

    @Override // android.app.Service
    public final void onCreate() {
        Intent launchIntentForPackage;
        int i7 = 0;
        e();
        V v10 = new V(new c(new L0(5), 8, new c0(2, null)));
        v10.d = new Y4.e(5);
        f13009v = v10;
        C1195c c1195c = new C1195c(2, 0, 1, 1, 0);
        C1915o c1915o = new C1915o(this);
        V v11 = f13009v;
        if (v11 == null) {
            A7.m.j("mediaSourceFactory");
            throw null;
        }
        AbstractC1346c.j(!c1915o.f19715t);
        c1915o.d = new C1914n(v11, i7);
        AbstractC1346c.j(!c1915o.f19715t);
        c1915o.f19706i = c1195c;
        C1923x c1923xA = c1915o.a();
        f13008u = c1923xA;
        b0 b0Var = new b0(this, c1923xA);
        PackageManager packageManager = getPackageManager();
        PendingIntent activity = (packageManager == null || (launchIntentForPackage = packageManager.getLaunchIntentForPackage(getPackageName())) == null) ? null : PendingIntent.getActivity(this, 0, launchIntentForPackage, 67108864);
        C1624a c1624a = new C1624a(0);
        c1624a.f17880f = getString(R.string.Favorite_AddToFavorites);
        c1624a.d = R.drawable.ic_like_notification;
        c1624a.e(new y1("com.stingray.karaoke.ADD_TO_FAVORITES", new Bundle()));
        C1627b c1627bA = c1624a.a();
        C1624a c1624a2 = new C1624a(0);
        c1624a2.f17880f = getString(R.string.Favorite_RemoveFromFavorites);
        c1624a2.d = R.drawable.ic_liked_notification;
        c1624a2.e(new y1("com.stingray.karaoke.REMOVE_FROM_FAVORITES", new Bundle()));
        C1627b c1627bA2 = c1624a2.a();
        AbstractC1346c.c(c1923xA.U0());
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        C1078G c1078g = I.f13384m;
        d0 d0Var = d0.f13415p;
        if (activity == null) {
            activity = null;
        } else if (AbstractC1343A.f15195a >= 31) {
            AbstractC1346c.c(activity.isActivity());
        }
        this.f13019t = new A0(this, b0Var, activity, d0Var, d0Var, d0Var, new P(this, c1627bA2, c1627bA, 7), bundle, bundle2, new c(new i(this)));
    }

    @Override // android.app.Service
    public final void onDestroy() {
        A0 a02 = this.f13019t;
        if (a02 != null) {
            a02.b().D();
            a02.b().a();
            try {
                synchronized (A0.f17713b) {
                    A0.c.remove(a02.f17714a.f17802i);
                }
                a02.f17714a.q();
            } catch (Exception unused) {
            }
        }
        stopForeground(1);
        stopSelf();
        f();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i7, int i10) {
        if (A7.m.a(intent != null ? intent.getAction() : null, "android.intent.action.MEDIA_BUTTON")) {
            C1923x c1923x = f13008u;
            if (c1923x == null) {
                A7.m.j("player");
                throw null;
            }
            if (!c1923x.O()) {
                stopSelf();
                return 2;
            }
        }
        g(intent, i7, i10);
        return 1;
    }

    @Override // android.app.Service
    public final void onTaskRemoved(Intent intent) {
        A0 a02 = this.f13019t;
        W wB = a02 != null ? a02.b() : null;
        A7.m.c(wB);
        if (wB.C()) {
            wB.b();
        }
        stopSelf();
        h(intent);
    }
}
