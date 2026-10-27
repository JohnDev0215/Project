package k3;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.media.MediaMetadata;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import com.stingray.karaoke.car.player.PlayerService;
import d5.C1078G;
import e2.C1212u;
import e2.C1214w;
import e2.C1216y;
import e2.C1217z;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.C1458d0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import l3.AbstractC1757m;
import l3.C1740E;
import l3.C1741F;

/* JADX INFO: loaded from: classes.dex */
public final class Q0 extends AbstractC1757m {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int f17841q;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final B8.t f17842e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final I0 f17843f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final l3.U f17844g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final i4.d f17845h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final B5.H f17846i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final l3.P f17847j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final h2.r f17848k;
    public final ComponentName l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public s1 f17849m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile long f17850n;
    public i4.d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17851p;

    static {
        f17841q = AbstractC1343A.f15195a >= 31 ? 33554432 : 0;
    }

    public Q0(I0 i02, Uri uri, Handler handler, Bundle bundle) {
        ComponentName componentName;
        ComponentName componentNameI;
        PendingIntent foregroundService;
        this.f17843f = i02;
        PlayerService playerService = i02.f17799f;
        this.f17844g = l3.U.a(playerService);
        i4.d dVar = new i4.d();
        dVar.f15752p = this;
        dVar.f15750m = e2.I.f13914K;
        dVar.f15751n = "";
        dVar.l = -9223372036854775807L;
        this.f17845h = dVar;
        B8.t tVar = new B8.t(i02);
        this.f17842e = tVar;
        this.f17850n = 300000L;
        this.f17846i = new B5.H(i02.l.getLooper(), tVar, 15);
        PackageManager packageManager = playerService.getPackageManager();
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(playerService.getPackageName());
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        boolean z6 = true;
        if (listQueryBroadcastReceivers.size() == 1) {
            ActivityInfo activityInfo = listQueryBroadcastReceivers.get(0).activityInfo;
            componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
        } else {
            if (!listQueryBroadcastReceivers.isEmpty()) {
                throw new IllegalStateException("Expected 1 broadcast receiver that handles android.intent.action.MEDIA_BUTTON, found " + listQueryBroadcastReceivers.size());
            }
            componentName = null;
        }
        this.l = componentName;
        if (componentName == null || AbstractC1343A.f15195a < 31) {
            componentNameI = I(playerService, "androidx.media3.session.MediaLibraryService");
            componentNameI = componentNameI == null ? I(playerService, "androidx.media3.session.MediaSessionService") : componentNameI;
            if (componentNameI == null || componentNameI.equals(componentName)) {
                z6 = false;
            }
        } else {
            z6 = false;
            componentNameI = componentName;
        }
        Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON", uri);
        if (componentNameI == null) {
            h2.r rVar = new h2.r(this, 1);
            this.f17848k = rVar;
            IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_BUTTON");
            intentFilter.addDataScheme(uri.getScheme());
            if (AbstractC1343A.f15195a < 33) {
                playerService.registerReceiver(rVar, intentFilter);
            } else {
                playerService.registerReceiver(rVar, intentFilter, 4);
            }
            intent2.setPackage(playerService.getPackageName());
            foregroundService = PendingIntent.getBroadcast(playerService, 0, intent2, f17841q);
            componentNameI = new ComponentName(playerService, (Class<?>) PlayerService.class);
        } else {
            intent2.setComponent(componentNameI);
            foregroundService = z6 ? AbstractC1343A.f15195a >= 26 ? PendingIntent.getForegroundService(playerService, 0, intent2, f17841q) : PendingIntent.getService(playerService, 0, intent2, f17841q) : PendingIntent.getBroadcast(playerService, 0, intent2, f17841q);
            this.f17848k = null;
        }
        String strJoin = TextUtils.join(".", new String[]{"androidx.media3.session.id", i02.f17802i});
        int i7 = AbstractC1343A.f15195a;
        l3.P p10 = new l3.P(playerService, strJoin, i7 < 31 ? componentNameI : null, i7 < 31 ? foregroundService : null, bundle);
        this.f17847j = p10;
        if (i7 >= 31 && componentName != null) {
            try {
                MediaSession mediaSession = ((l3.L) p10.f18537m).f18523a;
                mediaSession.getClass();
                mediaSession.setMediaButtonBroadcastReceiver(componentName);
            } catch (IllegalArgumentException e10) {
                if (!Build.MANUFACTURER.equals("motorola")) {
                    throw e10;
                }
                AbstractC1346c.q("MediaSessionLegacyStub", "caught IllegalArgumentException on a motorola device when attempting to set the media button broadcast receiver. See https://github.com/androidx/media/issues/1730 for details.", e10);
            }
        }
        PendingIntent pendingIntent = i02.f17812u;
        if (pendingIntent != null) {
            ((l3.L) this.f17847j.f18537m).f18523a.setSessionActivity(pendingIntent);
        }
        ((l3.L) this.f17847j.f18537m).d(this, handler);
    }

    public static void C(l3.P p10, C1741F c1741f) {
        l3.L l = (l3.L) p10.f18537m;
        l.f18529i = c1741f;
        MediaMetadata mediaMetadata = c1741f.f18521m;
        if (mediaMetadata == null) {
            Parcel parcelObtain = Parcel.obtain();
            try {
                c1741f.writeToParcel(parcelObtain, 0);
                parcelObtain.setDataPosition(0);
                MediaMetadata mediaMetadata2 = (MediaMetadata) MediaMetadata.CREATOR.createFromParcel(parcelObtain);
                c1741f.f18521m = mediaMetadata2;
                parcelObtain.recycle();
                mediaMetadata = mediaMetadata2;
            } catch (Throwable th) {
                parcelObtain.recycle();
                throw th;
            }
        }
        l.f18523a.setMetadata(mediaMetadata);
    }

    public static void D(Q0 q02, u1 u1Var) {
        q02.getClass();
        int i7 = u1Var.R0(20) ? 4 : 0;
        if (q02.f17851p != i7) {
            q02.f17851p = i7;
            ((l3.L) q02.f17847j.f18537m).f18523a.setFlags(i7 | 3);
        }
    }

    public static void E(ArrayList arrayList, l3.P p10) {
        if (arrayList != null) {
            p10.getClass();
            HashSet hashSet = new HashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                l3.M m10 = (l3.M) it.next();
                if (m10 == null) {
                    throw new IllegalArgumentException("queue shouldn't have null items");
                }
                long j3 = m10.f18532m;
                if (hashSet.contains(Long.valueOf(j3))) {
                    h6.n.i("MediaSessionCompat", M5.b.f("Found duplicate queue id: ", j3), new IllegalArgumentException("id of each queue item should be unique"));
                }
                hashSet.add(Long.valueOf(j3));
            }
        }
        l3.L l = (l3.L) p10.f18537m;
        l.f18528h = arrayList;
        MediaSession mediaSession = l.f18523a;
        if (arrayList == null) {
            mediaSession.setQueue(null);
            return;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            l3.M m11 = (l3.M) it2.next();
            MediaSession.QueueItem queueItem = m11.f18533n;
            if (queueItem == null) {
                MediaSession.QueueItem queueItem2 = new MediaSession.QueueItem(m11.l.d(), m11.f18532m);
                m11.f18533n = queueItem2;
                queueItem = queueItem2;
            }
            arrayList2.add(queueItem);
        }
        mediaSession.setQueue(arrayList2);
    }

    public static e2.F F(String str, Uri uri, String str2, Bundle bundle) {
        C1212u c1212u = new C1212u();
        C1078G c1078g = d5.I.f13384m;
        d5.d0 d0Var = d5.d0.f13415p;
        Collections.emptyList();
        d5.d0 d0Var2 = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        e2.B b10 = e2.B.d;
        if (str == null) {
            str = "";
        }
        String str3 = str;
        l3.P p10 = new l3.P(22, false);
        p10.f18537m = uri;
        p10.f18538n = str2;
        p10.o = bundle;
        return new e2.F(str3, new C1214w(c1212u), null, new C1217z(c1216y), e2.I.f13914K, new e2.B(p10));
    }

    public static ComponentName I(PlayerService playerService, String str) {
        PackageManager packageManager = playerService.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(playerService.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            return null;
        }
        ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
        return new ComponentName(serviceInfo.packageName, serviceInfo.name);
    }

    @Override // l3.AbstractC1757m
    public final void A() {
        G(3, new J0(this, 6), this.f17847j.r(), true);
    }

    public final void G(final int i7, final P0 p02, final l3.T t10, final boolean z6) {
        I0 i02 = this.f17843f;
        if (i02.h()) {
            return;
        }
        if (t10 != null) {
            AbstractC1343A.K(i02.l, new Runnable() { // from class: k3.N0
                @Override // java.lang.Runnable
                public final void run() {
                    Q0 q02 = this.l;
                    I0 i03 = q02.f17843f;
                    if (i03.h()) {
                        return;
                    }
                    boolean zIsActive = ((l3.L) q02.f17847j.f18537m).f18523a.isActive();
                    int i10 = i7;
                    l3.T t11 = t10;
                    if (!zIsActive) {
                        StringBuilder sbJ = M5.b.j("Ignore incoming player command before initialization. command=", i10, ", pid=");
                        sbJ.append(t11.f18543a.f18540b);
                        AbstractC1346c.z("MediaSessionLegacyStub", sbJ.toString());
                        return;
                    }
                    C1679y0 c1679y0K = q02.K(t11);
                    if (!q02.f17842e.b0(c1679y0K, i10)) {
                        if (i10 != 1 || i03.f17811t.C()) {
                            return;
                        }
                        AbstractC1346c.z("MediaSessionLegacyStub", "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
                        return;
                    }
                    i03.r(c1679y0K);
                    i03.f17798e.getClass();
                    P0 p03 = p02;
                    i03.f17815x = c1679y0K;
                    try {
                        p03.d(c1679y0K);
                    } catch (RemoteException e10) {
                        AbstractC1346c.A("MediaSessionLegacyStub", "Exception in " + c1679y0K, e10);
                    }
                    i03.f17815x = null;
                    if (z6) {
                        new SparseBooleanArray().append(i10, true);
                        i03.o(c1679y0K);
                    }
                }
            });
            return;
        }
        AbstractC1346c.n("MediaSessionLegacyStub", "RemoteUserInfo is null, ignoring command=" + i7);
    }

    public final void H(y1 y1Var, int i7, P0 p02, l3.T t10) {
        if (t10 != null) {
            AbstractC1343A.K(this.f17843f.l, new RunnableC1640f0(this, y1Var, i7, t10, p02));
            return;
        }
        StringBuilder sb = new StringBuilder("RemoteUserInfo is null, ignoring command=");
        Object objValueOf = y1Var;
        if (y1Var == null) {
            objValueOf = Integer.valueOf(i7);
        }
        sb.append(objValueOf);
        AbstractC1346c.n("MediaSessionLegacyStub", sb.toString());
    }

    public final void J(e2.F f10, boolean z6) {
        G(31, new C1619M(this, f10, z6), this.f17847j.r(), false);
    }

    public final C1679y0 K(l3.T t10) {
        C1679y0 c1679y0X = this.f17842e.X(t10);
        if (c1679y0X == null) {
            c1679y0X = new C1679y0(t10, 0, 0, this.f17844g.b(t10), new O0(t10), Bundle.EMPTY);
            C1675w0 c1675w0K = this.f17843f.k(c1679y0X);
            this.f17842e.v(t10, c1679y0X, c1675w0K.f18149a, c1675w0K.f18150b);
        }
        B5.H h10 = this.f17846i;
        long j3 = this.f17850n;
        h10.removeMessages(1001, c1679y0X);
        h10.sendMessageDelayed(h10.obtainMessage(1001, c1679y0X), j3);
        return c1679y0X;
    }

    public final void L(u1 u1Var) {
        AbstractC1343A.K(this.f17843f.l, new L0(this, u1Var, 1));
    }

    @Override // l3.AbstractC1757m
    public final void a(C1740E c1740e) {
        if (c1740e != null) {
            G(20, new V(this, c1740e, -1), this.f17847j.r(), false);
        }
    }

    @Override // l3.AbstractC1757m
    public final void b(C1740E c1740e, int i7) {
        if (c1740e != null) {
            if (i7 == -1 || i7 >= 0) {
                G(20, new V(this, c1740e, i7), this.f17847j.r(), false);
            }
        }
    }

    @Override // l3.AbstractC1757m
    public final void c(String str, Bundle bundle, ResultReceiver resultReceiver) {
        AbstractC1346c.k(str);
        if (str.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST")) {
            return;
        }
        if (str.equals("androidx.media3.session.SESSION_COMMAND_REQUEST_SESSION3_TOKEN") && resultReceiver != null) {
            resultReceiver.send(0, this.f17843f.f17803j.b());
        } else {
            y1 y1Var = new y1(str, Bundle.EMPTY);
            H(y1Var, 0, new io.sentry.Q0(this, y1Var, bundle, resultReceiver), this.f17847j.r());
        }
    }

    @Override // l3.AbstractC1757m
    public final void d(String str, Bundle bundle) {
        y1 y1Var = new y1(str, Bundle.EMPTY);
        H(y1Var, 0, new io.sentry.Y0(this, y1Var, bundle, 4), this.f17847j.r());
    }

    @Override // l3.AbstractC1757m
    public final void e() {
        G(12, new J0(this, 0), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final boolean f(Intent intent) {
        return this.f17843f.m(new C1679y0(this.f17847j.r(), 0, 0, false, null, Bundle.EMPTY), intent);
    }

    @Override // l3.AbstractC1757m
    public final void g() {
        G(1, new J0(this, 11), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final void h() {
        G(1, new J0(this, 10), this.f17847j.r(), false);
    }

    @Override // l3.AbstractC1757m
    public final void i(String str, Bundle bundle) {
        J(F(str, null, null, bundle), true);
    }

    @Override // l3.AbstractC1757m
    public final void j(String str, Bundle bundle) {
        J(F(null, null, str, bundle), true);
    }

    @Override // l3.AbstractC1757m
    public final void k(Uri uri, Bundle bundle) {
        J(F(null, uri, null, bundle), true);
    }

    @Override // l3.AbstractC1757m
    public final void l() {
        G(2, new J0(this, 5), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final void m(String str, Bundle bundle) {
        J(F(str, null, null, bundle), false);
    }

    @Override // l3.AbstractC1757m
    public final void n(String str, Bundle bundle) {
        J(F(null, null, str, bundle), false);
    }

    @Override // l3.AbstractC1757m
    public final void o(Uri uri, Bundle bundle) {
        J(F(null, uri, null, bundle), false);
    }

    @Override // l3.AbstractC1757m
    public final void p(C1740E c1740e) {
        if (c1740e == null) {
            return;
        }
        G(20, new C1458d0(this, 17, c1740e), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final void q() {
        G(11, new J0(this, 4), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final void r(long j3) {
        G(5, new K0(this, j3, 1), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final void s(float f10) {
        if (f10 <= 0.0f) {
            return;
        }
        G(13, new C1608B(f10, 2, this), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final void t(l3.Y y10) {
        u(y10);
    }

    @Override // l3.AbstractC1757m
    public final void u(l3.Y y10) {
        e2.X xO = AbstractC1663q.o(y10);
        if (xO != null) {
            H(null, 40010, new J0(this, xO), this.f17847j.r());
            return;
        }
        AbstractC1346c.z("MediaSessionLegacyStub", "Ignoring invalid RatingCompat " + y10);
    }

    @Override // l3.AbstractC1757m
    public final void v(int i7) {
        G(15, new M0(this, i7, 0), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final void w(int i7) {
        G(14, new M0(this, i7, 1), this.f17847j.r(), true);
    }

    @Override // l3.AbstractC1757m
    public final void x() {
        boolean zR0 = this.f17843f.f17811t.R0(9);
        l3.P p10 = this.f17847j;
        if (zR0) {
            G(9, new J0(this, 8), p10.r(), true);
        } else {
            G(8, new J0(this, 9), p10.r(), true);
        }
    }

    @Override // l3.AbstractC1757m
    public final void y() {
        boolean zR0 = this.f17843f.f17811t.R0(7);
        l3.P p10 = this.f17847j;
        if (zR0) {
            G(7, new J0(this, 2), p10.r(), true);
        } else {
            G(6, new J0(this, 3), p10.r(), true);
        }
    }

    @Override // l3.AbstractC1757m
    public final void z(long j3) {
        if (j3 < 0) {
            return;
        }
        G(10, new K0(this, j3, 0), this.f17847j.r(), true);
    }
}
