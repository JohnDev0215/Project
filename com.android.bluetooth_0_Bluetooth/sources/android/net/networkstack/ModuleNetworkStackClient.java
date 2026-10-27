package android.net.networkstack;

import android.content.Context;
import android.net.INetworkStackConnector;
import android.net.NetworkStack;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class ModuleNetworkStackClient extends NetworkStackClientBase {
    private static final String TAG = ModuleNetworkStackClient.class.getSimpleName();
    private static ModuleNetworkStackClient sInstance;

    private ModuleNetworkStackClient() {
    }

    public static synchronized ModuleNetworkStackClient getInstance(Context packageContext) {
        if (Build.VERSION.SDK_INT < 29 || (Build.VERSION.SDK_INT == 29 && "REL".equals(Build.VERSION.CODENAME))) {
            throw new UnsupportedOperationException("ModuleNetworkStackClient is not supported on API " + Build.VERSION.SDK_INT);
        }
        if (sInstance == null) {
            ModuleNetworkStackClient moduleNetworkStackClient = new ModuleNetworkStackClient();
            sInstance = moduleNetworkStackClient;
            moduleNetworkStackClient.startPolling();
        }
        return sInstance;
    }

    protected static synchronized void resetInstanceForTest() {
        sInstance = null;
    }

    private void startPolling() {
        IBinder nss = NetworkStack.getService();
        if (nss != null) {
            onNetworkStackConnected(INetworkStackConnector.Stub.asInterface(nss));
        } else {
            new Thread(new PollingRunner()).start();
        }
    }

    private class PollingRunner implements Runnable {
        private PollingRunner() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                IBinder nss = NetworkStack.getService();
                if (nss == null) {
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        Log.e(ModuleNetworkStackClient.TAG, "Interrupted while waiting for NetworkStack connector", e);
                    }
                } else {
                    ModuleNetworkStackClient.this.onNetworkStackConnected(INetworkStackConnector.Stub.asInterface(nss));
                    return;
                }
            }
        }
    }
}
