package com.android.car.settings.wifi.details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.android.car.settings.common.Logger;
import com.android.settingslib.wifi.AccessPoint;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class WifiInfoProvider implements DefaultLifecycleObserver {
    private static final Logger LOG = new Logger((Class<?>) WifiInfoProvider.class);
    private final AccessPoint mAccessPoint;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private final IntentFilter mFilter;
    private LinkProperties mLinkProperties;
    private final Network mNetwork;
    private NetworkCapabilities mNetworkCapabilities;
    private NetworkInfo mNetworkInfo;
    private WifiConfiguration mWifiConfig;
    private WifiInfo mWifiInfo;
    private final WifiManager mWifiManager;
    private final Set<Listener> mListeners = new HashSet();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.wifi.details.WifiInfoProvider.1
        /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                java.lang.String r0 = r7.getAction()
                int r1 = r0.hashCode()
                r2 = -385684331(0xffffffffe902ec95, float:-9.892349E24)
                r3 = 2
                r4 = 1
                if (r1 == r2) goto L2e
                r2 = -343630553(0xffffffffeb849d27, float:-3.2064068E26)
                if (r1 == r2) goto L24
                r2 = 1625920338(0x60e99352, float:1.3464709E20)
                if (r1 == r2) goto L1a
            L19:
                goto L38
            L1a:
                java.lang.String r1 = "android.net.wifi.CONFIGURED_NETWORKS_CHANGE"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L19
                r0 = 0
                goto L39
            L24:
                java.lang.String r1 = "android.net.wifi.STATE_CHANGE"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L19
                r0 = r4
                goto L39
            L2e:
                java.lang.String r1 = "android.net.wifi.RSSI_CHANGED"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L19
                r0 = r3
                goto L39
            L38:
                r0 = -1
            L39:
                if (r0 == 0) goto L74
                if (r0 == r4) goto L40
                if (r0 == r3) goto L40
                goto Lb2
            L40:
                com.android.car.settings.common.Logger r0 = com.android.car.settings.wifi.details.WifiInfoProvider.access$000()
                java.lang.String r1 = "wifi changed."
                r0.d(r1)
                com.android.car.settings.wifi.details.WifiInfoProvider r0 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                com.android.car.settings.wifi.details.WifiInfoProvider.access$100(r0)
                com.android.car.settings.wifi.details.WifiInfoProvider r0 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                java.util.Collection r0 = com.android.car.settings.wifi.details.WifiInfoProvider.access$200(r0)
                java.util.Iterator r0 = r0.iterator()
            L58:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto Lb2
                java.lang.Object r1 = r0.next()
                com.android.car.settings.wifi.details.WifiInfoProvider$Listener r1 = (com.android.car.settings.wifi.details.WifiInfoProvider.Listener) r1
                com.android.car.settings.wifi.details.WifiInfoProvider r2 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                android.net.NetworkInfo r2 = com.android.car.settings.wifi.details.WifiInfoProvider.access$400(r2)
                com.android.car.settings.wifi.details.WifiInfoProvider r3 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                android.net.wifi.WifiInfo r3 = com.android.car.settings.wifi.details.WifiInfoProvider.access$500(r3)
                r1.onWifiChanged(r2, r3)
                goto L58
            L74:
                com.android.car.settings.common.Logger r0 = com.android.car.settings.wifi.details.WifiInfoProvider.access$000()
                java.lang.String r1 = "Wifi Config changed."
                r0.d(r1)
                r5.updateMatchingWifiConfig()
                com.android.car.settings.wifi.details.WifiInfoProvider r0 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                com.android.car.settings.wifi.details.WifiInfoProvider.access$100(r0)
                com.android.car.settings.wifi.details.WifiInfoProvider r0 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                java.util.Collection r0 = com.android.car.settings.wifi.details.WifiInfoProvider.access$200(r0)
                java.util.Iterator r0 = r0.iterator()
            L8f:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto Lb1
                java.lang.Object r1 = r0.next()
                com.android.car.settings.wifi.details.WifiInfoProvider$Listener r1 = (com.android.car.settings.wifi.details.WifiInfoProvider.Listener) r1
                com.android.car.settings.wifi.details.WifiInfoProvider r2 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                android.net.wifi.WifiConfiguration r2 = com.android.car.settings.wifi.details.WifiInfoProvider.access$300(r2)
                com.android.car.settings.wifi.details.WifiInfoProvider r3 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                android.net.NetworkInfo r3 = com.android.car.settings.wifi.details.WifiInfoProvider.access$400(r3)
                com.android.car.settings.wifi.details.WifiInfoProvider r4 = com.android.car.settings.wifi.details.WifiInfoProvider.this
                android.net.wifi.WifiInfo r4 = com.android.car.settings.wifi.details.WifiInfoProvider.access$500(r4)
                r1.onWifiConfigurationChanged(r2, r3, r4)
                goto L8f
            Lb1:
            Lb2:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.car.settings.wifi.details.WifiInfoProvider.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
        }

        private void updateMatchingWifiConfig() {
            for (WifiConfiguration wifiConfiguration : WifiInfoProvider.this.mWifiManager.getPrivilegedConfiguredNetworks()) {
                if (WifiInfoProvider.this.mAccessPoint.matches(wifiConfiguration)) {
                    WifiInfoProvider.this.mWifiConfig = wifiConfiguration;
                    return;
                }
            }
        }
    };
    private final NetworkRequest mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build();
    private final ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.car.settings.wifi.details.WifiInfoProvider.2
        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties lp) {
            if (network.equals(WifiInfoProvider.this.mNetwork) && !lp.equals(WifiInfoProvider.this.mLinkProperties)) {
                WifiInfoProvider.this.mLinkProperties = lp;
                for (Listener listener : WifiInfoProvider.this.getListenersCopy()) {
                    listener.onLinkPropertiesChanged(WifiInfoProvider.this.mNetwork, WifiInfoProvider.this.mLinkProperties);
                }
            }
        }

        private boolean hasCapabilityChanged(NetworkCapabilities nc, int cap) {
            return WifiInfoProvider.this.mNetworkCapabilities == null || WifiInfoProvider.this.mNetworkCapabilities.hasCapability(cap) != nc.hasCapability(cap);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities nc) {
            if (network.equals(WifiInfoProvider.this.mNetwork) && !nc.equals(WifiInfoProvider.this.mNetworkCapabilities)) {
                if (hasCapabilityChanged(nc, 16) || hasCapabilityChanged(nc, 17)) {
                    WifiInfoProvider.this.mAccessPoint.update(WifiInfoProvider.this.mWifiConfig, WifiInfoProvider.this.mWifiInfo, WifiInfoProvider.this.mNetworkInfo);
                }
                WifiInfoProvider.this.mNetworkCapabilities = nc;
                for (Listener listener : WifiInfoProvider.this.getListenersCopy()) {
                    listener.onCapabilitiesChanged(WifiInfoProvider.this.mNetwork, WifiInfoProvider.this.mNetworkCapabilities);
                }
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            if (network.equals(WifiInfoProvider.this.mNetwork)) {
                for (Listener listener : WifiInfoProvider.this.getListenersCopy()) {
                    listener.onLost(WifiInfoProvider.this.mNetwork);
                }
            }
        }
    };
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    interface Listener {
        void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities);

        void onLinkPropertiesChanged(Network network, LinkProperties linkProperties);

        void onLost(Network network);

        void onWifiChanged(NetworkInfo networkInfo, WifiInfo wifiInfo);

        void onWifiConfigurationChanged(WifiConfiguration wifiConfiguration, NetworkInfo networkInfo, WifiInfo wifiInfo);
    }

    WifiInfoProvider(Context context, AccessPoint accessPoint) {
        this.mContext = context;
        this.mAccessPoint = accessPoint;
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getSystemService(ConnectivityManager.class);
        this.mWifiManager = (WifiManager) this.mContext.getSystemService(WifiManager.class);
        this.mWifiConfig = this.mAccessPoint.getConfig();
        Network currentNetwork = this.mWifiManager.getCurrentNetwork();
        this.mNetwork = currentNetwork;
        this.mLinkProperties = this.mConnectivityManager.getLinkProperties(currentNetwork);
        this.mNetworkCapabilities = this.mConnectivityManager.getNetworkCapabilities(this.mNetwork);
        updateInfo();
        IntentFilter intentFilter = new IntentFilter();
        this.mFilter = intentFilter;
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.mFilter.addAction("android.net.wifi.RSSI_CHANGED");
        this.mFilter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    public void clearListeners() {
        this.mListeners.clear();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStart(LifecycleOwner owner) {
        LOG.d("onStart");
        this.mContext.registerReceiver(this.mReceiver, this.mFilter);
        this.mConnectivityManager.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback, this.mHandler);
        for (Listener listener : getListenersCopy()) {
            listener.onWifiChanged(this.mNetworkInfo, this.mWifiInfo);
        }
        LOG.d("Done onStart");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        LOG.d("onStop");
        this.mContext.unregisterReceiver(this.mReceiver);
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
        LOG.d("done onStop");
    }

    public NetworkInfo getNetworkInfo() {
        return this.mNetworkInfo;
    }

    public WifiInfo getWifiInfo() {
        return this.mWifiInfo;
    }

    public Network getNetwork() {
        return this.mNetwork;
    }

    public NetworkCapabilities getNetworkCapabilities() {
        return this.mNetworkCapabilities;
    }

    public WifiConfiguration getNetworkConfiguration() {
        return this.mWifiConfig;
    }

    public LinkProperties getLinkProperties() {
        return this.mLinkProperties;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Collection<Listener> getListenersCopy() {
        return Collections.unmodifiableCollection(this.mListeners);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInfo() {
        this.mNetworkInfo = this.mConnectivityManager.getNetworkInfo(this.mNetwork);
        this.mWifiInfo = this.mWifiManager.getConnectionInfo();
    }
}
