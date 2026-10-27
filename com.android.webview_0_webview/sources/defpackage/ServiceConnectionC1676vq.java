package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: renamed from: vq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ServiceConnectionC1676vq implements ServiceConnection {
    public ParcelFileDescriptor a;
    public long b;
    public final /* synthetic */ C1731wq c;

    public ServiceConnectionC1676vq(C1731wq c1731wq, ParcelFileDescriptor parcelFileDescriptor, long j) {
        this.c = c1731wq;
        this.a = parcelFileDescriptor;
        this.b = j;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            try {
                if (this.a.getFd() >= 0) {
                    AbstractBinderC0411Wq.c(iBinder).s(this.a, this.b, this.c.b);
                }
            } catch (RemoteException e) {
                Ts.a("VariationsSeedLoader", "Faild requesting seed", e);
            }
        } finally {
            Is.a.unbindService(this);
            AbstractC0626cr.a(this.a);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
