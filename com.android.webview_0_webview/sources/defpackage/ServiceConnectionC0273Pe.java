package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: renamed from: Pe, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ServiceConnectionC0273Pe implements ServiceConnection {
    public final int a;
    public final /* synthetic */ BaseGmsClient b;

    public ServiceConnectionC0273Pe(BaseGmsClient baseGmsClient, int i) {
        this.b = baseGmsClient;
        this.a = i;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.b.u();
            return;
        }
        synchronized (this.b.k) {
            BaseGmsClient baseGmsClient = this.b;
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            baseGmsClient.l = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0781ff)) ? new C0725ef(iBinder) : (InterfaceC0781ff) iInterfaceQueryLocalInterface;
        }
        this.b.p(0, this.a);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.b.k) {
            this.b.l = null;
        }
        Handler handler = this.b.i;
        handler.sendMessage(handler.obtainMessage(6, this.a, 1));
    }
}
