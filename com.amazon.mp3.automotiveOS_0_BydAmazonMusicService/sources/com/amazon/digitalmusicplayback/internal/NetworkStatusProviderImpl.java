package com.amazon.digitalmusicplayback.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.NetworkReachabilityNotifier;
import com.amazon.digitalmusicplayback.NetworkStatusProvider;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkStatusProviderImpl extends NetworkStatusProvider {
    private static final String SOURCE_OTA = "OTA";
    private static final String SOURCE_UNKNOWN = "UNKNOWN";
    private static final String SOURCE_WIFI = "WIFI";
    private Context mContext;
    private LoggingFacade.Logger mLogger;
    private NetworkReachabilityNotifier mNotifier;
    private NetworkStatusReceiver mReceiver = null;

    @Override // com.amazon.digitalmusicplayback.NetworkStatusProvider
    public String getNetworkSource() {
        NetworkStatusReceiver networkStatusReceiver = this.mReceiver;
        return networkStatusReceiver == null ? SOURCE_UNKNOWN : networkStatusReceiver.mSource;
    }

    @Override // com.amazon.digitalmusicplayback.NetworkStatusProvider
    public boolean getNetworkReachability() {
        return this.mReceiver.mReachable;
    }

    @Override // com.amazon.digitalmusicplayback.NetworkStatusProvider
    public void initialize(NetworkReachabilityNotifier networkReachabilityNotifier) {
        this.mReceiver = new NetworkStatusReceiver(this.mContext);
        this.mNotifier = networkReachabilityNotifier;
    }

    @Override // com.amazon.digitalmusicplayback.NetworkStatusProvider
    public void shutdown() {
        this.mContext.unregisterReceiver(this.mReceiver);
        this.mReceiver = null;
    }

    public NetworkStatusProviderImpl(@NonNull Context context, LoggingFacade loggingFacade) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        this.mContext = context.getApplicationContext();
    }

    private final class NetworkStatusReceiver extends BroadcastReceiver {
        private boolean mReachable = false;
        private String mSource = NetworkStatusProviderImpl.SOURCE_UNKNOWN;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            refreshConnectivityStatus(context);
        }

        public NetworkStatusReceiver(@NonNull Context context) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(this, intentFilter);
        }

        private void refreshConnectivityStatus(@NonNull Context context) {
            boolean zIsConnected;
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            String str = NetworkStatusProviderImpl.SOURCE_UNKNOWN;
            if (activeNetworkInfo == null) {
                zIsConnected = false;
            } else {
                int type = activeNetworkInfo.getType();
                if (type == 1 || type == 9) {
                    str = NetworkStatusProviderImpl.SOURCE_WIFI;
                } else if (type == 0 || type == 4 || type == 6) {
                    str = "OTA";
                }
                zIsConnected = activeNetworkInfo.isConnected();
            }
            String str2 = this.mSource;
            if (str != str2) {
                NetworkStatusProviderImpl.this.mLogger.debug("updating native network source: " + str, new Object[0]);
                this.mSource = str;
            }
            if (zIsConnected != this.mReachable) {
                NetworkStatusProviderImpl.this.mLogger.debug("updating native network reachability: " + zIsConnected, new Object[0]);
                this.mReachable = zIsConnected;
                if (NetworkStatusProviderImpl.this.mNotifier != null) {
                    NetworkStatusProviderImpl.this.mNotifier.onNetworkReachabilityChanged(this.mReachable);
                }
            }
            if (str2 == this.mSource || NetworkStatusProviderImpl.this.mNotifier == null) {
                return;
            }
            NetworkStatusProviderImpl.this.mNotifier.onNetworkSourceChanged(this.mSource);
        }
    }
}
