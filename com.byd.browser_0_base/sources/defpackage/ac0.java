package defpackage;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import defpackage.zb0;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.download.DownloadNotificationServiceObserver;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: DownloadForegroundServiceManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class ac0 {
    public boolean c;
    public boolean e;
    public boolean f;
    public zb0 g;
    public final Handler a = new Handler();
    public final a b = new a();
    public int d = -1;
    public final HashMap h = new HashMap();
    public final b i = new b();

    /* JADX INFO: compiled from: DownloadForegroundServiceManager.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Log.w("cr_DownloadFg", "Checking if delayed stopAndUnbindService needs to be resolved.");
            ac0 ac0Var = ac0.this;
            ac0Var.c = false;
            ac0Var.b(false);
            ac0Var.a.removeCallbacks(ac0Var.b);
            Log.w("cr_DownloadFg", "Done checking if delayed stopAndUnbindService needs to be resolved.");
        }
    }

    /* JADX INFO: compiled from: DownloadForegroundServiceManager.java */
    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.w("cr_DownloadFg", "onServiceConnected");
            if (!(iBinder instanceof zb0.a)) {
                Log.w("cr_DownloadFg", "Not from DownloadNotificationService, do not connect. Component name: " + componentName);
                return;
            }
            zb0 zb0Var = zb0.this;
            ac0 ac0Var = ac0.this;
            ac0Var.g = zb0Var;
            Object obj = ThreadUtils.a;
            Set<String> setH = SharedPreferencesManager.getInstance().h("ForegroundServiceObservers");
            String name = DownloadNotificationServiceObserver.class.getName();
            if (!setH.contains(name)) {
                HashSet hashSet = new HashSet(setH);
                hashSet.add(name);
                SharedPreferencesManager.getInstance().p("ForegroundServiceObservers", hashSet);
            }
            ac0Var.b(true);
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            Log.w("cr_DownloadFg", "onServiceDisconnected");
            ac0.this.g = null;
        }
    }

    /* JADX INFO: compiled from: DownloadForegroundServiceManager.java */
    public static class c {
        public final int a;
        public final Notification b = null;
        public final int c = 3;
        public final Context d;

        public c(int i, Context context) {
            this.a = i;
            this.d = context;
        }
    }

    public final void a() {
        Iterator it = this.h.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!(((c) entry.getValue()).c == 0) && ((c) entry.getValue()).a != this.d) {
                it.remove();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x019c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(boolean r12) {
        /*
            Method dump skipped, instruction units count: 419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ac0.b(boolean):void");
    }

    public final void c(c cVar) {
        StringBuilder sb = new StringBuilder("startOrUpdateForegroundService id: ");
        int i = cVar.a;
        sb.append(i);
        sb.append(", startForeground() Called: ");
        sb.append(this.f);
        Log.w("cr_DownloadFg", sb.toString());
        Notification notificationE = cVar.b;
        if (notificationE == null && !this.f) {
            notificationE = n3.D("downloads", new o8(0, i, (String) null)).e();
        }
        if (this.g == null || i == -1 || notificationE == null) {
            return;
        }
        c cVar2 = (c) this.h.get(Integer.valueOf(this.d));
        Notification notification = cVar2 != null ? cVar2.b : null;
        boolean z = cVar2 != null && cVar2.c == 3;
        zb0 zb0Var = this.g;
        int i2 = this.d;
        zb0Var.getClass();
        Log.w("cr_DownloadFg", "startOrUpdateForegroundService new: " + i + ", old: " + i2 + ", kill old: " + z);
        if (i2 == -1 && notification == null) {
            zb0Var.h(i, notificationE);
        } else {
            zb0Var.i(z ? 1 : 2);
            zb0Var.h(i, notificationE);
        }
        if (i2 == -1) {
            List<String> list = gd0.a;
            if (org.chromium.base.library_loader.a.l.d()) {
                oj2.U(0, 3, "Android.DownloadManager.ForegroundServiceLifecycle");
            }
        } else if (i2 != i) {
            List<String> list2 = gd0.a;
            if (org.chromium.base.library_loader.a.l.d()) {
                oj2.U(1, 3, "Android.DownloadManager.ForegroundServiceLifecycle");
            }
        }
        this.f = true;
        this.d = i;
    }
}
