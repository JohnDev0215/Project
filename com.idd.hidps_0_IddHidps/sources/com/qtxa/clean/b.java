package com.qtxa.clean;

import a.a.a.c;
import android.content.Context;
import android.os.Handler;
import android.os.RemoteCallbackList;
import com.qtxa.clean.sysmonitor.SysMonitor;
import com.qtxa.clean.sysmonitor.d;
import com.qtxa.qtxadefend.w;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class b extends c.a implements com.qtxa.clean.a {
    public static String g = "idd-metrics_service";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private RemoteCallbackList<a.a.a.b> f19a = new RemoteCallbackList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f20b;
    private com.qtxa.clean.e.c c;
    private SysMonitor d;
    private ExecutorService e;
    private com.qtxa.clean.d.a f;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f.j(com.qtxa.clean.d.a.j);
        }
    }

    /* JADX INFO: renamed from: com.qtxa.clean.b$b, reason: collision with other inner class name */
    class RunnableC0005b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f22a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f23b;

        RunnableC0005b(int i, int i2) {
            this.f22a = i;
            this.f23b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = b.this.f19a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    if ((this.f22a & 1) != 0) {
                        w.e("SysClean", iBeginBroadcast + " DiskClean update CleanStatus: " + this.f23b);
                        ((a.a.a.b) b.this.f19a.getBroadcastItem(i)).j(this.f23b);
                    } else if ((this.f22a & 2) != 0) {
                        w.e("SysClean", iBeginBroadcast + " MemoryClean update CleanStatus: " + this.f23b);
                        ((a.a.a.b) b.this.f19a.getBroadcastItem(i)).p(this.f23b);
                    } else if ((this.f22a & 4) != 0) {
                        w.e("SysClean", iBeginBroadcast + " system monitor CleanStatus: " + this.f23b);
                        ((a.a.a.b) b.this.f19a.getBroadcastItem(i)).q(this.f23b);
                    }
                } catch (Exception unused) {
                }
            }
            b.this.f19a.finishBroadcast();
        }
    }

    public b(Context context) {
        this.c = null;
        this.d = null;
        this.e = null;
        d.b("QtSysMonitor", "SysCleanerImpl");
        this.f20b = context;
        context.getPackageManager();
        com.qtxa.clean.d.a aVarG = com.qtxa.clean.d.a.g(this.f20b);
        this.f = aVarG;
        aVarG.b(this);
        com.qtxa.clean.e.c cVarI = com.qtxa.clean.e.c.i(this.f20b);
        this.c = cVarI;
        cVarI.e(this);
        SysMonitor sysMonitorH = SysMonitor.h(this.f20b);
        this.d = sysMonitorH;
        sysMonitorH.f(this);
        this.e = Executors.newSingleThreadExecutor();
    }

    private void y(int i, int i2) {
        this.e.execute(new RunnableC0005b(i, i2));
    }

    @Override // a.a.a.c
    public double[] a() {
        return this.d.o();
    }

    @Override // a.a.a.c
    public void b(a.a.a.b bVar) {
        if (bVar != null) {
            this.f19a.register(bVar);
        }
    }

    @Override // a.a.a.c
    public double c(boolean z) {
        return this.d.k(z);
    }

    @Override // com.qtxa.clean.a
    public void f(int i) {
        y(1, i);
    }

    @Override // a.a.a.c
    public void k() {
        w.e("SysClean", "System clean  mMonitor.startGetMonitorData.");
    }

    @Override // a.a.a.c
    public void n(int i) {
        Handler handlerM = (i & 2) != 0 ? this.c.m(4096, i) : null;
        if ((i & 8) != 0 || (i & 16) != 0 || (i & 1) == 0 || handlerM == null) {
            return;
        }
        handlerM.postDelayed(new a(), 500L);
    }

    @Override // a.a.a.c
    public double[] o() {
        return this.d.r();
    }

    @Override // a.a.a.c
    public void s(a.a.a.b bVar) {
        if (bVar != null) {
            this.f19a.unregister(bVar);
        }
    }
}
