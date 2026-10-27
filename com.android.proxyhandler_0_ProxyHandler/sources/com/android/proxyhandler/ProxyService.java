package com.android.proxyhandler;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.net.IProxyCallback;
import com.android.net.IProxyPortListener;

/* JADX INFO: loaded from: classes.dex */
public class ProxyService extends Service {
    private static ProxyServer server;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (server == null) {
            ProxyServer proxyServer = new ProxyServer();
            server = proxyServer;
            proxyServer.startServer();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        ProxyServer proxyServer = server;
        if (proxyServer != null) {
            proxyServer.stopServer();
            server = null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new IProxyCallback.Stub(this) { // from class: com.android.proxyhandler.ProxyService.1
            public void getProxyPort(IBinder iBinder) throws RemoteException {
                IProxyPortListener iProxyPortListenerAsInterface;
                if (ProxyService.server == null || (iProxyPortListenerAsInterface = IProxyPortListener.Stub.asInterface(iBinder)) == null) {
                    return;
                }
                ProxyService.server.setCallback(iProxyPortListenerAsInterface);
            }
        };
    }
}
