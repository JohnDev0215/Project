package com.android.wifitrackerlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkKey;
import android.net.NetworkRequest;
import android.net.NetworkScoreManager;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import java.time.Clock;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes6.dex */
public class BaseWifiTracker implements LifecycleObserver {
    private static boolean sVerboseLogging;
    protected final ConnectivityManager mConnectivityManager;
    protected final Context mContext;
    protected boolean mIsCellDefaultRoute;
    protected boolean mIsWifiDefaultRoute;
    protected boolean mIsWifiValidated;
    private final BaseWifiTrackerCallback mListener;
    protected final Handler mMainHandler;
    protected final long mMaxScanAgeMillis;
    protected final NetworkScoreManager mNetworkScoreManager;
    protected final long mScanIntervalMillis;
    protected final ScanResultUpdater mScanResultUpdater;
    private final Scanner mScanner;
    private final String mTag;
    protected final WifiManager mWifiManager;
    protected final WifiNetworkScoreCache mWifiNetworkScoreCache;
    protected final Handler mWorkerHandler;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.wifitrackerlib.BaseWifiTracker.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BaseWifiTracker.isVerboseLoggingEnabled()) {
                Log.v(BaseWifiTracker.this.mTag, "Received broadcast: " + action);
            }
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                if (BaseWifiTracker.this.mWifiManager.getWifiState() == 3) {
                    BaseWifiTracker.this.mScanner.start();
                } else {
                    BaseWifiTracker.this.mScanner.stop();
                }
                BaseWifiTracker.this.notifyOnWifiStateChanged();
                BaseWifiTracker.this.handleWifiStateChangedAction();
                return;
            }
            if ("android.net.wifi.SCAN_RESULTS".equals(action)) {
                NetworkScoreManager networkScoreManager = BaseWifiTracker.this.mNetworkScoreManager;
                Stream<R> map = BaseWifiTracker.this.mWifiManager.getScanResults().stream().map(new Function() { // from class: com.android.wifitrackerlib.-$$Lambda$F7I5Dkjpf4Rkj99qB_bsUx3MJiA
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return NetworkKey.createFromScanResult((ScanResult) obj);
                    }
                });
                final Set set = BaseWifiTracker.this.mRequestedScoreKeys;
                Objects.requireNonNull(set);
                networkScoreManager.requestScores((Collection) map.filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$odOcE4rV8hguohUouyGm-OXJmSc
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return set.add((NetworkKey) obj);
                    }
                }).collect(Collectors.toList()));
                BaseWifiTracker.this.handleScanResultsAvailableAction(intent);
                return;
            }
            if ("android.net.wifi.CONFIGURED_NETWORKS_CHANGE".equals(action)) {
                BaseWifiTracker.this.handleConfiguredNetworksChangedAction(intent);
            } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                BaseWifiTracker.this.handleNetworkStateChangedAction(intent);
            } else if ("android.net.wifi.RSSI_CHANGED".equals(action)) {
                BaseWifiTracker.this.handleRssiChangedAction();
            }
        }
    };
    private final Set<NetworkKey> mRequestedScoreKeys = new HashSet();
    private final NetworkRequest mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build();
    private final ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.wifitrackerlib.BaseWifiTracker.2
        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties lp) {
            BaseWifiTracker.this.handleLinkPropertiesChanged(lp);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            boolean oldWifiValidated = BaseWifiTracker.this.mIsWifiValidated;
            BaseWifiTracker.this.mIsWifiValidated = networkCapabilities.hasCapability(16);
            if (BaseWifiTracker.isVerboseLoggingEnabled() && BaseWifiTracker.this.mIsWifiValidated != oldWifiValidated) {
                Log.v(BaseWifiTracker.this.mTag, "Is Wifi validated: " + BaseWifiTracker.this.mIsWifiValidated);
            }
            BaseWifiTracker.this.handleNetworkCapabilitiesChanged(networkCapabilities);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            BaseWifiTracker.this.mIsWifiValidated = false;
        }
    };
    private final ConnectivityManager.NetworkCallback mDefaultNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.wifitrackerlib.BaseWifiTracker.3
        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            boolean oldWifiDefault = BaseWifiTracker.this.mIsWifiDefaultRoute;
            boolean oldCellDefault = BaseWifiTracker.this.mIsCellDefaultRoute;
            BaseWifiTracker.this.mIsWifiDefaultRoute = networkCapabilities.hasTransport(1);
            BaseWifiTracker.this.mIsCellDefaultRoute = networkCapabilities.hasTransport(0);
            if (BaseWifiTracker.this.mIsWifiDefaultRoute != oldWifiDefault || BaseWifiTracker.this.mIsCellDefaultRoute != oldCellDefault) {
                if (BaseWifiTracker.isVerboseLoggingEnabled()) {
                    Log.v(BaseWifiTracker.this.mTag, "Wifi is the default route: " + BaseWifiTracker.this.mIsWifiDefaultRoute);
                    Log.v(BaseWifiTracker.this.mTag, "Cell is the default route: " + BaseWifiTracker.this.mIsCellDefaultRoute);
                }
                BaseWifiTracker.this.handleDefaultRouteChanged();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            BaseWifiTracker.this.mIsWifiDefaultRoute = false;
            BaseWifiTracker.this.mIsCellDefaultRoute = false;
            if (BaseWifiTracker.isVerboseLoggingEnabled()) {
                Log.v(BaseWifiTracker.this.mTag, "Wifi is the default route: false");
                Log.v(BaseWifiTracker.this.mTag, "Cell is the default route: false");
            }
            BaseWifiTracker.this.handleDefaultRouteChanged();
        }
    };

    protected interface BaseWifiTrackerCallback {
        void onWifiStateChanged();
    }

    public static boolean isVerboseLoggingEnabled() {
        return sVerboseLogging;
    }

    BaseWifiTracker(Lifecycle lifecycle, Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, Handler mainHandler, Handler workerHandler, Clock clock, long maxScanAgeMillis, long scanIntervalMillis, BaseWifiTrackerCallback listener, String tag) {
        lifecycle.addObserver(this);
        this.mContext = context;
        this.mWifiManager = wifiManager;
        this.mConnectivityManager = connectivityManager;
        this.mNetworkScoreManager = networkScoreManager;
        this.mMainHandler = mainHandler;
        this.mWorkerHandler = workerHandler;
        this.mMaxScanAgeMillis = maxScanAgeMillis;
        this.mScanIntervalMillis = scanIntervalMillis;
        this.mListener = listener;
        this.mTag = tag;
        this.mScanResultUpdater = new ScanResultUpdater(clock, maxScanAgeMillis + scanIntervalMillis);
        this.mWifiNetworkScoreCache = new WifiNetworkScoreCache(this.mContext, new WifiNetworkScoreCache.CacheListener(this.mWorkerHandler) { // from class: com.android.wifitrackerlib.BaseWifiTracker.4
            public void networkCacheUpdated(List<ScoredNetwork> networks) {
                BaseWifiTracker.this.handleNetworkScoreCacheUpdated();
            }
        });
        this.mScanner = new Scanner(workerHandler.getLooper());
        sVerboseLogging = this.mWifiManager.isVerboseLoggingEnabled();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.SCAN_RESULTS");
        filter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        filter.addAction("android.net.wifi.RSSI_CHANGED");
        this.mContext.registerReceiver(this.mBroadcastReceiver, filter, null, this.mWorkerHandler);
        this.mConnectivityManager.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback, this.mWorkerHandler);
        this.mConnectivityManager.registerDefaultNetworkCallback(this.mDefaultNetworkCallback, this.mWorkerHandler);
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        NetworkCapabilities defaultNetworkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (defaultNetworkCapabilities != null) {
            this.mIsWifiDefaultRoute = defaultNetworkCapabilities.hasTransport(1);
            this.mIsCellDefaultRoute = defaultNetworkCapabilities.hasTransport(0);
        } else {
            this.mIsWifiDefaultRoute = false;
            this.mIsCellDefaultRoute = false;
        }
        if (isVerboseLoggingEnabled()) {
            Log.v(this.mTag, "Wifi is the default route: " + this.mIsWifiDefaultRoute);
            Log.v(this.mTag, "Cell is the default route: " + this.mIsCellDefaultRoute);
        }
        this.mNetworkScoreManager.registerNetworkScoreCache(1, this.mWifiNetworkScoreCache, 2);
        if (this.mWifiManager.getWifiState() == 3) {
            Handler handler = this.mWorkerHandler;
            final Scanner scanner = this.mScanner;
            Objects.requireNonNull(scanner);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$BaseWifiTracker$pw9dhfMm5LbxL1gY1jP_JJmuRkU
                @Override // java.lang.Runnable
                public final void run() {
                    scanner.start();
                }
            });
        } else {
            Handler handler2 = this.mWorkerHandler;
            final Scanner scanner2 = this.mScanner;
            Objects.requireNonNull(scanner2);
            handler2.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$BaseWifiTracker$KvY90710q6wRJD152kDBMW9ndFs
                @Override // java.lang.Runnable
                public final void run() {
                    scanner2.stop();
                }
            });
        }
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$S9fuCAjG-YC38JCa05_AkNB8B-E
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.handleOnStart();
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Handler handler = this.mWorkerHandler;
        final Scanner scanner = this.mScanner;
        Objects.requireNonNull(scanner);
        handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$BaseWifiTracker$QlWe0ki0RbTEAz4j0GPGXOVBvC0
            @Override // java.lang.Runnable
            public final void run() {
                scanner.stop();
            }
        });
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
        this.mConnectivityManager.unregisterNetworkCallback(this.mDefaultNetworkCallback);
        this.mNetworkScoreManager.unregisterNetworkScoreCache(1, this.mWifiNetworkScoreCache);
        Handler handler2 = this.mWorkerHandler;
        final Set<NetworkKey> set = this.mRequestedScoreKeys;
        Objects.requireNonNull(set);
        handler2.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$gWMviBWa7YejsjM1KbUOIv9j1JA
            @Override // java.lang.Runnable
            public final void run() {
                set.clear();
            }
        });
    }

    public int getWifiState() {
        return this.mWifiManager.getWifiState();
    }

    protected void handleOnStart() {
    }

    protected void handleWifiStateChangedAction() {
    }

    protected void handleScanResultsAvailableAction(Intent intent) {
    }

    protected void handleConfiguredNetworksChangedAction(Intent intent) {
    }

    protected void handleNetworkStateChangedAction(Intent intent) {
    }

    protected void handleRssiChangedAction() {
    }

    protected void handleLinkPropertiesChanged(LinkProperties linkProperties) {
    }

    protected void handleNetworkCapabilitiesChanged(NetworkCapabilities capabilities) {
    }

    protected void handleDefaultRouteChanged() {
    }

    protected void handleNetworkScoreCacheUpdated() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    class Scanner extends Handler {
        private static final int SCAN_RETRY_TIMES = 3;
        private int mRetry;

        private Scanner(Looper looper) {
            super(looper);
            this.mRetry = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void start() {
            if (BaseWifiTracker.isVerboseLoggingEnabled()) {
                Log.v(BaseWifiTracker.this.mTag, "Scanner start");
            }
            postScan();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stop() {
            if (BaseWifiTracker.isVerboseLoggingEnabled()) {
                Log.v(BaseWifiTracker.this.mTag, "Scanner stop");
            }
            this.mRetry = 0;
            removeCallbacksAndMessages(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void postScan() {
            if (BaseWifiTracker.this.mWifiManager.startScan()) {
                this.mRetry = 0;
            } else {
                int i = this.mRetry + 1;
                this.mRetry = i;
                if (i >= 3) {
                    if (BaseWifiTracker.isVerboseLoggingEnabled()) {
                        Log.v(BaseWifiTracker.this.mTag, "Scanner failed to start scan " + this.mRetry + " times!");
                    }
                    this.mRetry = 0;
                    return;
                }
            }
            postDelayed(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$BaseWifiTracker$Scanner$Lob1PHu6bdjiK_7H86IDLNF_WiM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.postScan();
                }
            }, BaseWifiTracker.this.mScanIntervalMillis);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyOnWifiStateChanged() {
        final BaseWifiTrackerCallback baseWifiTrackerCallback = this.mListener;
        if (baseWifiTrackerCallback != null) {
            Handler handler = this.mMainHandler;
            Objects.requireNonNull(baseWifiTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$MIul75RWhCdcC435EGCLupy9Spc
                @Override // java.lang.Runnable
                public final void run() {
                    baseWifiTrackerCallback.onWifiStateChanged();
                }
            });
        }
    }
}
