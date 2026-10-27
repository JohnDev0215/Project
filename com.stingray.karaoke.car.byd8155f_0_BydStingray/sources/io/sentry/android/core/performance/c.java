package io.sentry.android.core.performance;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import io.sentry.android.core.C1440p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ e f16273m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Application f16274n;

    public /* synthetic */ c(e eVar, Application application, int i7) {
        this.l = i7;
        this.f16273m = eVar;
        this.f16274n = application;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                e eVar = this.f16273m;
                eVar.getClass();
                new Handler(Looper.getMainLooper()).post(new c(eVar, this.f16274n, 1));
                break;
            default:
                e eVar2 = this.f16273m;
                Application application = this.f16274n;
                if (eVar2.f16284u == null) {
                    eVar2.f16277m = false;
                    C1440p c1440p = eVar2.f16282s;
                    if (c1440p != null && c1440p.isRunning()) {
                        eVar2.f16282s.close();
                        eVar2.f16282s = null;
                    }
                }
                application.unregisterActivityLifecycleCallbacks(e.f16276y);
                break;
        }
    }
}
