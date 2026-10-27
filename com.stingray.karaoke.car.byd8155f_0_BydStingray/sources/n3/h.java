package N3;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import h8.AbstractC1386c;
import java.lang.ref.WeakReference;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class h implements ComponentCallbacks2 {
    public final WeakReference l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Context f4584m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public J3.e f4585n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f4586p = true;

    public h(z3.i iVar) {
        this.l = new WeakReference(iVar);
    }

    public final synchronized void a() {
        J3.e eVar;
        try {
            z3.i iVar = (z3.i) this.l.get();
            if (iVar == null) {
                b();
            } else if (this.f4585n == null) {
                if (iVar.d.f4580b) {
                    Context context = iVar.f24398a;
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
                    if (connectivityManager == null || AbstractC1386c.c(context, "android.permission.ACCESS_NETWORK_STATE") != 0) {
                        eVar = new Y4.e(3);
                    } else {
                        try {
                            eVar = new P(connectivityManager, this);
                        } catch (Exception unused) {
                            eVar = new Y4.e(3);
                        }
                    }
                } else {
                    eVar = new Y4.e(3);
                }
                this.f4585n = eVar;
                this.f4586p = eVar.l();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void b() {
        try {
            if (this.o) {
                return;
            }
            this.o = true;
            Context context = this.f4584m;
            if (context != null) {
                context.unregisterComponentCallbacks(this);
            }
            J3.e eVar = this.f4585n;
            if (eVar != null) {
                eVar.shutdown();
            }
            this.l.clear();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.content.ComponentCallbacks
    public final synchronized void onConfigurationChanged(Configuration configuration) {
        if (((z3.i) this.l.get()) == null) {
            b();
        }
    }

    @Override // android.content.ComponentCallbacks
    public final synchronized void onLowMemory() {
        onTrimMemory(80);
    }

    @Override // android.content.ComponentCallbacks2
    public final synchronized void onTrimMemory(int i7) {
        z3.i iVar = (z3.i) this.l.get();
        if (iVar != null) {
            I3.c cVar = (I3.c) iVar.c.getValue();
            if (cVar != null) {
                cVar.f2934a.b(i7);
                C1.j jVar = cVar.f2935b;
                synchronized (jVar) {
                    if (i7 >= 10 && i7 != 20) {
                        jVar.h();
                    }
                }
            }
        } else {
            b();
        }
    }
}
