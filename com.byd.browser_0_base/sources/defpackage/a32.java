package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.IPostMessageService;
import androidx.browser.customtabs.CustomTabsSessionToken;

/* JADX INFO: compiled from: PostMessageServiceConnection.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a32 implements w22, ServiceConnection {
    public final Object e = new Object();
    public final ICustomTabsCallback f;
    public IPostMessageService g;
    public String h;
    public boolean i;

    public a32(CustomTabsSessionToken customTabsSessionToken) {
        ICustomTabsCallback iCustomTabsCallback = customTabsSessionToken.a;
        IBinder iBinderAsBinder = iCustomTabsCallback == null ? null : iCustomTabsCallback.asBinder();
        if (iBinderAsBinder == null) {
            throw new IllegalArgumentException("Provided session must have binder.");
        }
        this.f = ICustomTabsCallback.Stub.asInterface(iBinderAsBinder);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IPostMessageService iPostMessageServiceAsInterface = IPostMessageService.Stub.asInterface(iBinder);
        this.g = iPostMessageServiceAsInterface;
        if (!this.i || iPostMessageServiceAsInterface == null) {
            return;
        }
        synchronized (this.e) {
            try {
                this.g.onMessageChannelReady(this.f, null);
            } catch (RemoteException unused) {
            } finally {
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.g = null;
    }
}
