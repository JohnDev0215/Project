package defpackage;

import android.app.Notification;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import org.chromium.chrome.browser.base.SplitCompatService;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: DownloadForegroundServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class zb0 extends SplitCompatService.a {
    public final a b = new a();

    /* JADX INFO: compiled from: DownloadForegroundServiceImpl.java */
    public class a extends Binder {
        public a() {
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final IBinder a() {
        return this.b;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void b() {
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void c() {
        bc0 bc0Var;
        gd0.a(1);
        for (String str : SharedPreferencesManager.getInstance().h("ForegroundServiceObservers")) {
            try {
                bc0Var = (bc0) Class.forName(str).newInstance();
            } catch (Throwable th) {
                fa1.l("DownloadFgServiceObs", "getObserverFromClassName(): %s", str, th);
                bc0Var = null;
            }
            if (bc0Var != null) {
                bc0Var.b();
            }
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void d() {
        gd0.a(3);
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final int e(Intent intent, int i, int i2) {
        if (intent != null) {
            return 1;
        }
        gd0.a(4);
        this.a.stopSelf();
        return 1;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void f() {
        bc0 bc0Var;
        gd0.a(2);
        for (String str : SharedPreferencesManager.getInstance().h("ForegroundServiceObservers")) {
            try {
                bc0Var = (bc0) Class.forName(str).newInstance();
            } catch (Throwable th) {
                fa1.l("DownloadFgServiceObs", "getObserverFromClassName(): %s", str, th);
                bc0Var = null;
            }
            if (bc0Var != null) {
                bc0Var.a();
            }
        }
    }

    public final void h(int i, Notification notification) {
        C0175b.s("startForegroundInternal id: ", i, "cr_DownloadFg");
        SplitCompatService splitCompatService = this.a;
        if (Build.VERSION.SDK_INT >= 31) {
            g6.a(splitCompatService, i, notification, 1);
        } else {
            splitCompatService.startForeground(i, notification, 1);
        }
    }

    public final void i(int i) {
        C0175b.s("stopForegroundInternal flags: ", i, "cr_DownloadFg");
        try {
            this.a.stopForeground(i);
        } catch (NullPointerException e) {
            Log.e("cr_ForegroundService", "Failed to stop foreground service, ", e);
        }
    }
}
