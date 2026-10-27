package k3;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.stingray.karaoke.car.player.PlayerService;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import v1.C2539f;
import v1.ServiceConnectionC2542i;

/* JADX INFO: renamed from: k3.u0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1671u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PlayerService f18124a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h2.s f18125b;
    public final C1.j c;
    public final v1.j d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Intent f18127f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f18129h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e7.f f18130i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ExecutorC1662p0 f18126e = new ExecutorC1662p0(new Handler(Looper.getMainLooper()), 0);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f18128g = new HashMap();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f18131j = false;

    public C1671u0(PlayerService playerService, h2.s sVar, C1.j jVar) {
        this.f18124a = playerService;
        this.f18125b = sVar;
        this.c = jVar;
        this.d = new v1.j(playerService);
        this.f18127f = new Intent(playerService, playerService.getClass());
    }

    public final C1672v a(A0 a02) {
        h5.y yVar = (h5.y) this.f18128g.get(a02);
        if (yVar == null || !yVar.isDone()) {
            return null;
        }
        try {
            return (C1672v) l9.l.N(yVar);
        } catch (ExecutionException e10) {
            throw new IllegalStateException(e10);
        }
    }

    public final void b(boolean z6) {
        ArrayList arrayList;
        PlayerService playerService = this.f18124a;
        synchronized (playerService.l) {
            arrayList = new ArrayList(playerService.f13014n.values());
        }
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            if (c((A0) arrayList.get(i7), false)) {
                return;
            }
        }
        int i10 = AbstractC1343A.f15195a;
        PlayerService playerService2 = this.f18124a;
        if (i10 >= 24) {
            playerService2.stopForeground(z6 ? 1 : 2);
        } else {
            playerService2.stopForeground(z6);
        }
        this.f18131j = false;
        if (!z6 || this.f18130i == null) {
            return;
        }
        this.d.f22977b.cancel(null, 1001);
        this.f18129h++;
        this.f18130i = null;
    }

    public final boolean c(A0 a02, boolean z6) {
        C1672v c1672vA = a(a02);
        return c1672vA != null && (c1672vA.C() || z6) && (c1672vA.e() == 3 || c1672vA.e() == 2);
    }

    public final void d(A0 a02, e7.f fVar, boolean z6) {
        ((Notification) fVar.f14480m).extras.putParcelable("android.mediaSession", (MediaSession.Token) ((l3.L) a02.f17714a.f17801h.f17847j.f18537m).c.f18534m);
        this.f18130i = fVar;
        Notification notification = (Notification) fVar.f14480m;
        if (z6) {
            Intent intent = this.f18127f;
            PlayerService playerService = this.f18124a;
            playerService.startForegroundService(intent);
            if (AbstractC1343A.f15195a >= 29) {
                try {
                    playerService.startForeground(1001, notification, 2);
                } catch (RuntimeException e10) {
                    AbstractC1346c.p("Util", "The service must be declared with a foregroundServiceType that includes mediaPlayback");
                    throw e10;
                }
            } else {
                playerService.startForeground(1001, notification);
            }
            this.f18131j = true;
            return;
        }
        v1.j jVar = this.d;
        jVar.getClass();
        Bundle bundle = notification.extras;
        NotificationManager notificationManager = jVar.f22977b;
        if (bundle == null || !bundle.getBoolean("android.support.useSideChannel")) {
            notificationManager.notify(null, 1001, notification);
        } else {
            C2539f c2539f = new C2539f(jVar.f22976a.getPackageName(), notification);
            synchronized (v1.j.f22974f) {
                try {
                    if (v1.j.f22975g == null) {
                        v1.j.f22975g = new ServiceConnectionC2542i(jVar.f22976a.getApplicationContext());
                    }
                    v1.j.f22975g.f22971m.obtainMessage(0, c2539f).sendToTarget();
                } catch (Throwable th) {
                    throw th;
                }
            }
            notificationManager.cancel(null, 1001);
        }
        b(false);
    }
}
