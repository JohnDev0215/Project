package android.net;

import android.net.SocketKeepalive;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
final class TcpSocketKeepalive extends SocketKeepalive {
    TcpSocketKeepalive(IConnectivityManager iConnectivityManager, Network network, ParcelFileDescriptor parcelFileDescriptor, Executor executor, SocketKeepalive.Callback callback) {
        super(iConnectivityManager, network, parcelFileDescriptor, executor, callback);
    }

    @Override // android.net.SocketKeepalive
    void startImpl(final int i) {
        this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$TcpSocketKeepalive$n5VR0uMuz82ZfynpSiXN0BdIDxY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startImpl$0$TcpSocketKeepalive(i);
            }
        });
    }

    public /* synthetic */ void lambda$startImpl$0$TcpSocketKeepalive(int i) {
        try {
            this.mService.startTcpKeepalive(this.mNetwork, this.mPfd.getFileDescriptor(), i, this.mCallback);
        } catch (RemoteException e) {
            Log.e("SocketKeepalive", "Error starting packet keepalive: ", e);
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.net.SocketKeepalive
    void stopImpl() {
        this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$TcpSocketKeepalive$BkAk5H9vsT_XVkEQmwqE0SsJgTc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$stopImpl$1$TcpSocketKeepalive();
            }
        });
    }

    public /* synthetic */ void lambda$stopImpl$1$TcpSocketKeepalive() {
        try {
            if (this.mSlot != null) {
                this.mService.stopKeepalive(this.mNetwork, this.mSlot.intValue());
            }
        } catch (RemoteException e) {
            Log.e("SocketKeepalive", "Error stopping packet keepalive: ", e);
            throw e.rethrowFromSystemServer();
        }
    }
}
