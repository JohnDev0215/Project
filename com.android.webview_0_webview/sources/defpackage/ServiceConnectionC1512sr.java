package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: renamed from: sr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ServiceConnectionC1512sr implements ServiceConnection {
    public final /* synthetic */ C1567tr a;

    public ServiceConnectionC1512sr(C1567tr c1567tr, AbstractC1348pr abstractC1348pr) {
        this.a = c1567tr;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C1567tr c1567tr;
        new Intent().setClassName(this.a.r0.getPackageName(), "org.chromium.android_webview.services.DeveloperUiService");
        try {
            try {
                AbstractBinderC0357Tq.c(iBinder).q(this.a.p0);
                c1567tr = this.a;
            } catch (RemoteException e) {
                Ts.a("WebViewDevTools", "Failed to send flag overrides to service", e);
                c1567tr = this.a;
            }
            c1567tr.r0.unbindService(this);
        } catch (Throwable th) {
            this.a.r0.unbindService(this);
            throw th;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
