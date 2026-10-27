package android.ext.services.watchdog;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.service.watchdog.ExplicitHealthCheckService;

/* JADX INFO: loaded from: classes.dex */
final class NetworkChecker extends ConnectivityManager.NetworkCallback implements ExplicitHealthChecker {
    private boolean mIsPending;
    private final Object mLock = new Object();
    private final String mPackageName;
    private final ExplicitHealthCheckService mService;

    NetworkChecker(ExplicitHealthCheckService explicitHealthCheckService, String str) {
        this.mService = explicitHealthCheckService;
        this.mPackageName = str;
    }

    @Override // android.ext.services.watchdog.ExplicitHealthChecker
    public void request() {
        synchronized (this.mLock) {
            if (this.mIsPending) {
                return;
            }
            ((ConnectivityManager) this.mService.getSystemService(ConnectivityManager.class)).registerNetworkCallback(new NetworkRequest.Builder().build(), this);
            this.mIsPending = true;
        }
    }

    @Override // android.ext.services.watchdog.ExplicitHealthChecker
    public void cancel() {
        synchronized (this.mLock) {
            if (this.mIsPending) {
                ((ConnectivityManager) this.mService.getSystemService(ConnectivityManager.class)).unregisterNetworkCallback(this);
                this.mIsPending = false;
            }
        }
    }

    @Override // android.ext.services.watchdog.ExplicitHealthChecker
    public boolean isPending() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mIsPending;
        }
        return z;
    }

    @Override // android.ext.services.watchdog.ExplicitHealthChecker
    public String getSupportedPackageName() {
        return this.mPackageName;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        synchronized (this.mLock) {
            if (this.mIsPending && networkCapabilities.hasCapability(16)) {
                this.mService.notifyHealthCheckPassed(this.mPackageName);
                cancel();
            }
        }
    }
}
