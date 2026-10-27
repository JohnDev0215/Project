package io.sentry.android.core;

import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import j4.ThreadFactoryC1542c;
import java.util.ArrayList;
import java.util.Iterator;
import l3.C1758n;
import l3.C1759o;
import l3.C1764u;
import l3.InterfaceC1750f;
import q.MenuC2151h;
import r.C2247f;
import r.C2250i;
import r.u0;

/* JADX INFO: loaded from: classes.dex */
public final class M implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f16071m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16072n;

    public /* synthetic */ M(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f16072n = obj;
        this.f16071m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C1764u c1764u;
        u0 u0Var;
        C2247f c2247f;
        switch (this.l) {
            case 0:
                if (((NetworkBreadcrumbsIntegration) this.f16072n).f16080p) {
                    return;
                }
                synchronized (((NetworkBreadcrumbsIntegration) this.f16072n).o) {
                    try {
                        ((NetworkBreadcrumbsIntegration) this.f16072n).f16082r = new O(((NetworkBreadcrumbsIntegration) this.f16072n).f16078m, ((F1) this.f16071m).getDateProvider());
                        NetworkBreadcrumbsIntegration networkBreadcrumbsIntegration = (NetworkBreadcrumbsIntegration) this.f16072n;
                        if (B8.t.k0(networkBreadcrumbsIntegration.l, networkBreadcrumbsIntegration.f16079n, networkBreadcrumbsIntegration.f16078m, networkBreadcrumbsIntegration.f16082r)) {
                            ((NetworkBreadcrumbsIntegration) this.f16072n).f16079n.k(EnumC1495p1.DEBUG, "NetworkBreadcrumbsIntegration installed.", new Object[0]);
                            n7.z.c("NetworkBreadcrumbs");
                        } else {
                            ((NetworkBreadcrumbsIntegration) this.f16072n).f16079n.k(EnumC1495p1.DEBUG, "NetworkBreadcrumbsIntegration not installed.", new Object[0]);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            case 1:
                ThreadFactoryC1542c threadFactoryC1542c = (ThreadFactoryC1542c) this.f16072n;
                if (threadFactoryC1542c.d) {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                }
                try {
                    ((Runnable) this.f16071m).run();
                    return;
                } catch (Throwable th2) {
                    threadFactoryC1542c.c.getClass();
                    if (Log.isLoggable("GlideExecutor", 6)) {
                        h6.n.i("GlideExecutor", "Request threw uncaught throwable", th2);
                        return;
                    }
                    return;
                }
            case 2:
                C1759o c1759o = (C1759o) this.f16072n;
                ArrayList arrayList = (ArrayList) c1759o.f18585a;
                boolean zIsEmpty = arrayList.isEmpty();
                l3.O o = (l3.O) this.f16071m;
                if (!zIsEmpty) {
                    InterfaceC1750f interfaceC1750fA = o.a();
                    if (interfaceC1750fA != null) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            ((Bundle) it.next()).putBinder("extra_session_binder", interfaceC1750fA.asBinder());
                        }
                    }
                    arrayList.clear();
                }
                C1758n c1758n = (C1758n) c1759o.f18586b;
                c1758n.getClass();
                MediaSession.Token token = (MediaSession.Token) o.f18534m;
                token.getClass();
                c1758n.setSessionToken(token);
                return;
            default:
                C2250i c2250i = (C2250i) this.f16072n;
                MenuC2151h menuC2151h = c2250i.f21484n;
                if (menuC2151h != null && (c1764u = menuC2151h.f21051e) != null && (u0Var = ((ActionMenuView) c1764u.f18608m).f11335E) != null) {
                    Toolbar toolbar = u0Var.f21563a;
                    C2250i c2250i2 = toolbar.l.f11334D;
                    if (c2250i2 == null || (c2247f = c2250i2.f21478C) == null || !c2247f.b()) {
                        Iterator it2 = toolbar.f11367R.f2338a.iterator();
                        if (it2.hasNext()) {
                            ((X1.i) it2.next()).getClass();
                            throw null;
                        }
                    }
                }
                ActionMenuView actionMenuView = c2250i.f21487r;
                if (actionMenuView != null && actionMenuView.getWindowToken() != null) {
                    C2247f c2247f2 = (C2247f) this.f16071m;
                    if (c2247f2.b()) {
                        c2250i.f21478C = c2247f2;
                    } else if (c2247f2.f21091e != null) {
                        c2247f2.d(0, 0, false, false);
                        c2250i.f21478C = c2247f2;
                    }
                }
                c2250i.f21480E = null;
                return;
        }
    }
}
