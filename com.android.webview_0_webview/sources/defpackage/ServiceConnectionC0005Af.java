package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: Af, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ServiceConnectionC0005Af implements ServiceConnection {
    public final Map a = new HashMap();
    public int b = 2;
    public boolean c;
    public IBinder d;
    public final C0417Xe e;
    public ComponentName f;
    public final /* synthetic */ C0023Bf g;

    public ServiceConnectionC0005Af(C0023Bf c0023Bf, C0417Xe c0417Xe) {
        this.g = c0023Bf;
        this.e = c0417Xe;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r7) {
        /*
            r6 = this;
            r7 = 3
            r6.b = r7
            Bf r7 = r6.g
            If r0 = r7.f
            android.content.Context r7 = r7.d
            Xe r1 = r6.e
            android.content.Intent r1 = r1.a(r7)
            Xe r2 = r6.e
            int r2 = r2.d
            java.util.Objects.requireNonNull(r0)
            android.content.ComponentName r0 = r1.getComponent()
            r3 = 0
            r4 = 1
            if (r0 != 0) goto L1f
            goto L3f
        L1f:
            java.lang.String r0 = r0.getPackageName()
            java.lang.String r5 = "com.google.android.gms"
            r5.equals(r0)
            Pf r5 = defpackage.C0292Qf.a(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3f
            android.content.Context r5 = r5.a     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3f
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3f
            android.content.pm.ApplicationInfo r0 = r5.getApplicationInfo(r0, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3f
            int r0 = r0.flags     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L3f
            r5 = 2097152(0x200000, float:2.938736E-39)
            r0 = r0 & r5
            if (r0 == 0) goto L3f
            r0 = 1
            goto L40
        L3f:
            r0 = 0
        L40:
            if (r0 == 0) goto L4a
            java.lang.String r7 = "ConnectionTracker"
            java.lang.String r0 = "Attempted to bind to a service in a STOPPED package."
            android.util.Log.w(r7, r0)
            goto L4e
        L4a:
            boolean r3 = r7.bindService(r1, r6, r2)
        L4e:
            r6.c = r3
            if (r3 == 0) goto L66
            Bf r7 = r6.g
            android.os.Handler r7 = r7.e
            Xe r0 = r6.e
            android.os.Message r7 = r7.obtainMessage(r4, r0)
            Bf r0 = r6.g
            android.os.Handler r1 = r0.e
            long r2 = r0.h
            r1.sendMessageDelayed(r7, r2)
            return
        L66:
            r7 = 2
            r6.b = r7
            Bf r7 = r6.g     // Catch: java.lang.IllegalArgumentException -> L77
            If r7 = r7.f     // Catch: java.lang.IllegalArgumentException -> L77
            Bf r0 = r6.g     // Catch: java.lang.IllegalArgumentException -> L77
            android.content.Context r0 = r0.d     // Catch: java.lang.IllegalArgumentException -> L77
            java.util.Objects.requireNonNull(r7)     // Catch: java.lang.IllegalArgumentException -> L77
            r0.unbindService(r6)     // Catch: java.lang.IllegalArgumentException -> L77
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ServiceConnectionC0005Af.a(java.lang.String):void");
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.g.c) {
            this.g.e.removeMessages(1, this.e);
            this.d = iBinder;
            this.f = componentName;
            Iterator it = this.a.values().iterator();
            while (it.hasNext()) {
                ((ServiceConnection) it.next()).onServiceConnected(componentName, iBinder);
            }
            this.b = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.g.c) {
            this.g.e.removeMessages(1, this.e);
            this.d = null;
            this.f = componentName;
            Iterator it = this.a.values().iterator();
            while (it.hasNext()) {
                ((ServiceConnection) it.next()).onServiceDisconnected(componentName);
            }
            this.b = 2;
        }
    }
}
