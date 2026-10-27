package A2;

import B8.t;
import android.content.Context;
import android.net.ConnectivityManager;
import com.stingray.karaoke.car.player.PlayerService;
import h3.C1350a;
import io.sentry.EnumC1495p1;
import io.sentry.K;
import io.sentry.android.core.B;
import io.sentry.android.core.C1425a;
import io.sentry.android.core.NetworkBreadcrumbsIntegration;
import io.sentry.android.core.O;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f344m;

    public /* synthetic */ l(C1425a c1425a, C1350a c1350a) {
        this.l = 15;
        this.f344m = c1425a;
    }

    private final void a() {
        NetworkBreadcrumbsIntegration networkBreadcrumbsIntegration = (NetworkBreadcrumbsIntegration) this.f344m;
        synchronized (networkBreadcrumbsIntegration.o) {
            try {
                if (networkBreadcrumbsIntegration.f16082r != null) {
                    Context context = networkBreadcrumbsIntegration.l;
                    K k10 = networkBreadcrumbsIntegration.f16079n;
                    B b10 = networkBreadcrumbsIntegration.f16078m;
                    O o = networkBreadcrumbsIntegration.f16082r;
                    b10.getClass();
                    ConnectivityManager connectivityManagerV = t.V(context, k10);
                    if (connectivityManagerV != null) {
                        try {
                            connectivityManagerV.unregisterNetworkCallback(o);
                        } catch (Throwable th) {
                            k10.s(EnumC1495p1.WARNING, "unregisterNetworkCallback failed", th);
                        }
                    }
                    networkBreadcrumbsIntegration.f16079n.k(EnumC1495p1.DEBUG, "NetworkBreadcrumbsIntegration removed.", new Object[0]);
                }
                networkBreadcrumbsIntegration.f16082r = null;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    private final void b() {
        synchronized (((PlayerService) this.f344m).l) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x03a3  */
    /* JADX WARN: Type inference failed for: r1v20, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r1v32, types: [java.lang.Object, m7.f] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 2554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: A2.l.run():void");
    }

    public /* synthetic */ l(Object obj, int i7) {
        this.l = i7;
        this.f344m = obj;
    }
}
