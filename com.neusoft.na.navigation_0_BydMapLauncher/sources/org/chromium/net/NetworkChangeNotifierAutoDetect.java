package org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.io.IOException;
import java.util.Arrays;
import org.chromium.base.ThreadUtils;
import org.chromium.base.metrics.RecordHistogram;

/* JADX INFO: loaded from: classes3.dex */
public class NetworkChangeNotifierAutoDetect extends BroadcastReceiver {
    private static final String TAG = "NetworkChangeNotifierAutoDetect";
    private static final int UNKNOWN_LINK_SPEED = -1;
    private int mConnectionType;
    private ConnectivityManagerDelegate mConnectivityManagerDelegate;
    private final Context mContext;
    private final NetworkConnectivityIntentFilter mIntentFilter;
    private int mMaxBandwidthConnectionType;
    private double mMaxBandwidthMbps;
    private final MyNetworkCallback mNetworkCallback;
    private final NetworkRequest mNetworkRequest;
    private final Observer mObserver;
    private boolean mRegistered;
    private final RegistrationPolicy mRegistrationPolicy;
    private WifiManagerDelegate mWifiManagerDelegate;
    private String mWifiSSID;

    public interface Observer {
        void onConnectionTypeChanged(int i);

        void onMaxBandwidthChanged(double d);

        void onNetworkConnect(int i, int i2);

        void onNetworkDisconnect(int i);

        void onNetworkSoonToDisconnect(int i);

        void purgeActiveNetworkList(int[] iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class NetworkState {
        private final boolean mConnected;
        private final int mSubtype;
        private final int mType;

        public NetworkState(boolean z, int i, int i2) {
            this.mConnected = z;
            this.mType = i;
            this.mSubtype = i2;
        }

        public boolean isConnected() {
            return this.mConnected;
        }

        public int getNetworkType() {
            return this.mType;
        }

        public int getNetworkSubType() {
            return this.mSubtype;
        }
    }

    static class ConnectivityManagerDelegate {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ConnectivityManager mConnectivityManager;

        ConnectivityManagerDelegate(Context context) {
            this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }

        ConnectivityManagerDelegate() {
            this.mConnectivityManager = null;
        }

        NetworkState getNetworkState() {
            return getNetworkState(this.mConnectivityManager.getActiveNetworkInfo());
        }

        private NetworkInfo getNetworkInfo(Network network) {
            try {
                NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(network);
                RecordHistogram.recordBooleanHistogram("NCN.getNetInfo1stSuccess", true);
                return networkInfo;
            } catch (NullPointerException unused) {
                RecordHistogram.recordBooleanHistogram("NCN.getNetInfo1stSuccess", false);
                try {
                    NetworkInfo networkInfo2 = this.mConnectivityManager.getNetworkInfo(network);
                    RecordHistogram.recordBooleanHistogram("NCN.getNetInfo2ndSuccess", true);
                    return networkInfo2;
                } catch (NullPointerException e) {
                    RecordHistogram.recordBooleanHistogram("NCN.getNetInfo2ndSuccess", false);
                    throw e;
                }
            }
        }

        NetworkState getNetworkState(Network network) {
            NetworkInfo networkInfo = getNetworkInfo(network);
            if (networkInfo != null && networkInfo.getType() == 17) {
                return getNetworkState();
            }
            return getNetworkState(networkInfo);
        }

        NetworkState getNetworkState(NetworkInfo networkInfo) {
            if (networkInfo == null || !networkInfo.isConnected()) {
                return new NetworkState(false, -1, -1);
            }
            return new NetworkState(true, networkInfo.getType(), networkInfo.getSubtype());
        }

        protected Network[] getAllNetworksUnfiltered() {
            return this.mConnectivityManager.getAllNetworks();
        }

        protected boolean vpnAccessible(Network network) {
            try {
                network.getSocketFactory().createSocket().close();
                return true;
            } catch (IOException unused) {
                return false;
            }
        }

        protected NetworkCapabilities getNetworkCapabilities(Network network) {
            return this.mConnectivityManager.getNetworkCapabilities(network);
        }

        void registerNetworkCallback(NetworkRequest networkRequest, ConnectivityManager.NetworkCallback networkCallback) {
            this.mConnectivityManager.registerNetworkCallback(networkRequest, networkCallback);
        }

        void unregisterNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
            this.mConnectivityManager.unregisterNetworkCallback(networkCallback);
        }

        int getDefaultNetId() {
            NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
            int iNetworkToNetId = -1;
            if (activeNetworkInfo == null) {
                return -1;
            }
            for (Network network : NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(this, null)) {
                NetworkInfo networkInfo = getNetworkInfo(network);
                if (networkInfo != null && (networkInfo.getType() == activeNetworkInfo.getType() || networkInfo.getType() == 17)) {
                    iNetworkToNetId = NetworkChangeNotifierAutoDetect.networkToNetId(network);
                }
            }
            return iNetworkToNetId;
        }
    }

    static class WifiManagerDelegate {
        private final Context mContext;
        private final boolean mHasWifiPermission;
        private final WifiManager mWifiManager;

        WifiManagerDelegate(Context context) {
            this.mContext = context;
            boolean z = context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) == 0;
            this.mHasWifiPermission = z;
            this.mWifiManager = z ? (WifiManager) context.getSystemService("wifi") : null;
        }

        WifiManagerDelegate() {
            this.mContext = null;
            this.mWifiManager = null;
            this.mHasWifiPermission = false;
        }

        String getWifiSSID() {
            WifiInfo wifiInfo;
            String ssid;
            Intent intentRegisterReceiver = this.mContext.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            return (intentRegisterReceiver == null || (wifiInfo = (WifiInfo) intentRegisterReceiver.getParcelableExtra("wifiInfo")) == null || (ssid = wifiInfo.getSSID()) == null) ? "" : ssid;
        }

        private WifiInfo getWifiInfo() {
            try {
                WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
                RecordHistogram.recordBooleanHistogram("NCN.getWifiInfo1stSuccess", true);
                return connectionInfo;
            } catch (NullPointerException unused) {
                RecordHistogram.recordBooleanHistogram("NCN.getWifiInfo1stSuccess", false);
                try {
                    WifiInfo connectionInfo2 = this.mWifiManager.getConnectionInfo();
                    RecordHistogram.recordBooleanHistogram("NCN.getWifiInfo2ndSuccess", true);
                    return connectionInfo2;
                } catch (NullPointerException e) {
                    RecordHistogram.recordBooleanHistogram("NCN.getWifiInfo2ndSuccess", false);
                    throw e;
                }
            }
        }

        int getLinkSpeedInMbps() {
            WifiInfo wifiInfo;
            if (!this.mHasWifiPermission || this.mWifiManager == null || (wifiInfo = getWifiInfo()) == null) {
                return -1;
            }
            return wifiInfo.getLinkSpeed();
        }

        boolean getHasWifiPermission() {
            return this.mHasWifiPermission;
        }
    }

    private class MyNetworkCallback extends ConnectivityManager.NetworkCallback {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Network mVpnInPlace;

        private MyNetworkCallback() {
            this.mVpnInPlace = null;
        }

        void initializeVpnInPlace() {
            NetworkCapabilities networkCapabilities;
            Network[] allNetworksFiltered = NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate, null);
            this.mVpnInPlace = null;
            if (allNetworksFiltered.length == 1 && (networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(allNetworksFiltered[0])) != null && networkCapabilities.hasTransport(4)) {
                this.mVpnInPlace = allNetworksFiltered[0];
            }
        }

        private boolean ignoreNetworkDueToVpn(Network network) {
            Network network2 = this.mVpnInPlace;
            return (network2 == null || network2.equals(network)) ? false : true;
        }

        private boolean ignoreConnectedInaccessibleVpn(Network network, NetworkCapabilities networkCapabilities) {
            if (networkCapabilities == null) {
                networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(network);
            }
            return networkCapabilities == null || (networkCapabilities.hasTransport(4) && !NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.vpnAccessible(network));
        }

        private boolean ignoreConnectedNetwork(Network network, NetworkCapabilities networkCapabilities) {
            return ignoreNetworkDueToVpn(network) || ignoreConnectedInaccessibleVpn(network, networkCapabilities);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            NetworkCapabilities networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(network);
            if (ignoreConnectedNetwork(network, networkCapabilities)) {
                return;
            }
            final boolean zHasTransport = networkCapabilities.hasTransport(4);
            if (zHasTransport) {
                this.mVpnInPlace = network;
            }
            final int iNetworkToNetId = NetworkChangeNotifierAutoDetect.networkToNetId(network);
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            final int currentConnectionType = networkChangeNotifierAutoDetect.getCurrentConnectionType(networkChangeNotifierAutoDetect.mConnectivityManagerDelegate.getNetworkState(network));
            ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkConnect(iNetworkToNetId, currentConnectionType);
                    if (zHasTransport) {
                        NetworkChangeNotifierAutoDetect.this.mObserver.onConnectionTypeChanged(currentConnectionType);
                        NetworkChangeNotifierAutoDetect.this.mObserver.purgeActiveNetworkList(new int[]{iNetworkToNetId});
                    }
                }
            });
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (ignoreConnectedNetwork(network, networkCapabilities)) {
                return;
            }
            final int iNetworkToNetId = NetworkChangeNotifierAutoDetect.networkToNetId(network);
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            final int currentConnectionType = networkChangeNotifierAutoDetect.getCurrentConnectionType(networkChangeNotifierAutoDetect.mConnectivityManagerDelegate.getNetworkState(network));
            ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkConnect(iNetworkToNetId, currentConnectionType);
                }
            });
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int i) {
            if (ignoreConnectedNetwork(network, null)) {
                return;
            }
            final int iNetworkToNetId = NetworkChangeNotifierAutoDetect.networkToNetId(network);
            ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.3
                @Override // java.lang.Runnable
                public void run() {
                    NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkSoonToDisconnect(iNetworkToNetId);
                }
            });
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(final Network network) {
            if (ignoreNetworkDueToVpn(network)) {
                return;
            }
            ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.4
                @Override // java.lang.Runnable
                public void run() {
                    NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkDisconnect(NetworkChangeNotifierAutoDetect.networkToNetId(network));
                }
            });
            if (this.mVpnInPlace != null) {
                this.mVpnInPlace = null;
                for (Network network2 : NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate, network)) {
                    onAvailable(network2);
                }
                NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
                final int currentConnectionType = networkChangeNotifierAutoDetect.getCurrentConnectionType(networkChangeNotifierAutoDetect.mConnectivityManagerDelegate.getNetworkState());
                ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.5
                    @Override // java.lang.Runnable
                    public void run() {
                        NetworkChangeNotifierAutoDetect.this.mObserver.onConnectionTypeChanged(currentConnectionType);
                    }
                });
            }
        }
    }

    public static abstract class RegistrationPolicy {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private NetworkChangeNotifierAutoDetect mNotifier;

        protected abstract void destroy();

        protected final void register() {
            this.mNotifier.register();
        }

        protected final void unregister() {
            this.mNotifier.unregister();
        }

        protected void init(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
            this.mNotifier = networkChangeNotifierAutoDetect;
        }
    }

    public NetworkChangeNotifierAutoDetect(Observer observer, Context context, RegistrationPolicy registrationPolicy) {
        ThreadUtils.assertOnUiThread();
        this.mObserver = observer;
        this.mContext = context.getApplicationContext();
        this.mConnectivityManagerDelegate = new ConnectivityManagerDelegate(context);
        this.mWifiManagerDelegate = new WifiManagerDelegate(context);
        this.mNetworkCallback = new MyNetworkCallback();
        this.mNetworkRequest = new NetworkRequest.Builder().addCapability(12).removeCapability(15).build();
        NetworkState networkState = this.mConnectivityManagerDelegate.getNetworkState();
        this.mConnectionType = getCurrentConnectionType(networkState);
        this.mWifiSSID = getCurrentWifiSSID(networkState);
        this.mMaxBandwidthMbps = getCurrentMaxBandwidthInMbps(networkState);
        this.mMaxBandwidthConnectionType = this.mConnectionType;
        this.mIntentFilter = new NetworkConnectivityIntentFilter(this.mWifiManagerDelegate.getHasWifiPermission());
        this.mRegistrationPolicy = registrationPolicy;
        registrationPolicy.init(this);
    }

    void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate connectivityManagerDelegate) {
        this.mConnectivityManagerDelegate = connectivityManagerDelegate;
    }

    void setWifiManagerDelegateForTests(WifiManagerDelegate wifiManagerDelegate) {
        this.mWifiManagerDelegate = wifiManagerDelegate;
    }

    RegistrationPolicy getRegistrationPolicy() {
        return this.mRegistrationPolicy;
    }

    boolean isReceiverRegisteredForTesting() {
        return this.mRegistered;
    }

    public void destroy() {
        this.mRegistrationPolicy.destroy();
        unregister();
    }

    public void register() {
        if (this.mRegistered) {
            return;
        }
        NetworkState currentNetworkState = getCurrentNetworkState();
        connectionTypeChanged(currentNetworkState);
        maxBandwidthChanged(currentNetworkState);
        this.mContext.registerReceiver(this, this.mIntentFilter);
        this.mRegistered = true;
        MyNetworkCallback myNetworkCallback = this.mNetworkCallback;
        if (myNetworkCallback != null) {
            myNetworkCallback.initializeVpnInPlace();
            this.mConnectivityManagerDelegate.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback);
            Network[] allNetworksFiltered = getAllNetworksFiltered(this.mConnectivityManagerDelegate, null);
            int[] iArr = new int[allNetworksFiltered.length];
            for (int i = 0; i < allNetworksFiltered.length; i++) {
                iArr[i] = networkToNetId(allNetworksFiltered[i]);
            }
            this.mObserver.purgeActiveNetworkList(iArr);
        }
    }

    public void unregister() {
        if (this.mRegistered) {
            this.mContext.unregisterReceiver(this);
            this.mRegistered = false;
            MyNetworkCallback myNetworkCallback = this.mNetworkCallback;
            if (myNetworkCallback != null) {
                this.mConnectivityManagerDelegate.unregisterNetworkCallback(myNetworkCallback);
            }
        }
    }

    public NetworkState getCurrentNetworkState() {
        return this.mConnectivityManagerDelegate.getNetworkState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Network[] getAllNetworksFiltered(ConnectivityManagerDelegate connectivityManagerDelegate, Network network) {
        NetworkCapabilities networkCapabilities;
        Network[] allNetworksUnfiltered = connectivityManagerDelegate.getAllNetworksUnfiltered();
        int i = 0;
        for (Network network2 : allNetworksUnfiltered) {
            if (!network2.equals(network) && (networkCapabilities = connectivityManagerDelegate.getNetworkCapabilities(network2)) != null && networkCapabilities.hasCapability(12)) {
                if (networkCapabilities.hasTransport(4)) {
                    if (connectivityManagerDelegate.vpnAccessible(network2)) {
                        return new Network[]{network2};
                    }
                } else {
                    allNetworksUnfiltered[i] = network2;
                    i++;
                }
            }
        }
        return (Network[]) Arrays.copyOf(allNetworksUnfiltered, i);
    }

    public int[] getNetworksAndTypes() {
        Network[] allNetworksFiltered = getAllNetworksFiltered(this.mConnectivityManagerDelegate, null);
        int[] iArr = new int[allNetworksFiltered.length * 2];
        int i = 0;
        for (Network network : allNetworksFiltered) {
            int i2 = i + 1;
            iArr[i] = networkToNetId(network);
            i = i2 + 1;
            iArr[i2] = getCurrentConnectionType(this.mConnectivityManagerDelegate.getNetworkState(network));
        }
        return iArr;
    }

    public int getDefaultNetId() {
        return this.mConnectivityManagerDelegate.getDefaultNetId();
    }

    public int getCurrentConnectionType(NetworkState networkState) {
        if (!networkState.isConnected()) {
            return 6;
        }
        int networkType = networkState.getNetworkType();
        if (networkType == 0) {
            switch (networkState.getNetworkSubType()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return 3;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return 4;
                case 13:
                    return 5;
                default:
                    return 0;
            }
        }
        if (networkType == 1) {
            return 2;
        }
        if (networkType == 6) {
            return 5;
        }
        if (networkType != 7) {
            return networkType != 9 ? 0 : 1;
        }
        return 7;
    }

    public int getCurrentConnectionSubtype(NetworkState networkState) {
        if (!networkState.isConnected()) {
            return 1;
        }
        if (networkState.getNetworkType() != 0) {
            return 0;
        }
        switch (networkState.getNetworkSubType()) {
        }
        return 0;
    }

    public double getCurrentMaxBandwidthInMbps(NetworkState networkState) {
        int linkSpeedInMbps;
        return (getCurrentConnectionType(networkState) != 2 || (linkSpeedInMbps = this.mWifiManagerDelegate.getLinkSpeedInMbps()) == -1) ? NetworkChangeNotifier.getMaxBandwidthForConnectionSubtype(getCurrentConnectionSubtype(networkState)) : linkSpeedInMbps;
    }

    private String getCurrentWifiSSID(NetworkState networkState) {
        return getCurrentConnectionType(networkState) != 2 ? "" : this.mWifiManagerDelegate.getWifiSSID();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkState currentNetworkState = getCurrentNetworkState();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            connectionTypeChanged(currentNetworkState);
            maxBandwidthChanged(currentNetworkState);
        } else if ("android.net.wifi.RSSI_CHANGED".equals(intent.getAction())) {
            maxBandwidthChanged(currentNetworkState);
        }
    }

    private void connectionTypeChanged(NetworkState networkState) {
        int currentConnectionType = getCurrentConnectionType(networkState);
        String currentWifiSSID = getCurrentWifiSSID(networkState);
        if (currentConnectionType == this.mConnectionType && currentWifiSSID.equals(this.mWifiSSID)) {
            return;
        }
        this.mConnectionType = currentConnectionType;
        this.mWifiSSID = currentWifiSSID;
        Log.d(TAG, "Network connectivity changed, type is: " + this.mConnectionType);
        this.mObserver.onConnectionTypeChanged(currentConnectionType);
    }

    private void maxBandwidthChanged(NetworkState networkState) {
        double currentMaxBandwidthInMbps = getCurrentMaxBandwidthInMbps(networkState);
        if (currentMaxBandwidthInMbps == this.mMaxBandwidthMbps && this.mConnectionType == this.mMaxBandwidthConnectionType) {
            return;
        }
        this.mMaxBandwidthMbps = currentMaxBandwidthInMbps;
        this.mMaxBandwidthConnectionType = this.mConnectionType;
        this.mObserver.onMaxBandwidthChanged(currentMaxBandwidthInMbps);
    }

    private static class NetworkConnectivityIntentFilter extends IntentFilter {
        NetworkConnectivityIntentFilter(boolean z) {
            addAction("android.net.conn.CONNECTIVITY_CHANGE");
            if (z) {
                addAction("android.net.wifi.RSSI_CHANGED");
            }
        }
    }

    static int networkToNetId(Network network) {
        return Integer.parseInt(network.toString());
    }
}
