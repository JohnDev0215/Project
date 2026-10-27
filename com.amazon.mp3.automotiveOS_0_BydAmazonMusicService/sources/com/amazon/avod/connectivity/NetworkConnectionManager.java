package com.amazon.avod.connectivity;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import androidx.core.net.ConnectivityManagerCompat;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkConnectionManager {
    private static final DetailedNetworkInfo DEFAULT_NETWORK_INFO = new DetailedNetworkInfo(NetworkType.NO_CONNECTION, MobileNetworkClass.NONE, NetworkState.NO_ACCESS);
    private volatile CachedNetworkInfo mCachedNetworkInfo;
    private final NetworkConnectionManagerConfig mConfig;
    private ConnectivityManager mConnectivityManager;
    private final ExecutorService mEventExecutor;
    private Handler mHandler;
    private final InitializationLatch mInitializationLatch;

    @GuardedBy("mListeners")
    private final Set<ConnectivityChangeListener> mListeners;
    private long mNetworkInfoTtlMillis;
    private final Object mNetworkStateRefreshLock;
    private volatile DetailedNetworkInfo mQAOverride;
    private final ExecutorService mRefreshExecutor;
    private final Runnable mRefreshRunnable;
    private TelephonyManager mTelephonyManager;
    private WifiManager mWifiManager;

    private static class SingletonHolder {
        private static volatile NetworkConnectionManager sInstance = new NetworkConnectionManager();

        private SingletonHolder() {
        }
    }

    public static NetworkConnectionManager getInstance() {
        return SingletonHolder.sInstance;
    }

    NetworkConnectionManager() {
        this(NetworkConnectionManagerConfig.getInstance(), ExecutorBuilder.newBuilder(NetworkConnectionManager.class.getSimpleName() + "-Refresh", new String[0]).withFixedThreadPoolSize(1).allowCoreThreadExpiry().build(), ExecutorBuilder.newBuilder(NetworkConnectionManager.class.getSimpleName() + "-Event", new String[0]).withFixedThreadPoolSize(1).allowCoreThreadExpiry().build());
    }

    NetworkConnectionManager(@Nonnull NetworkConnectionManagerConfig networkConnectionManagerConfig, @Nonnull ExecutorService executorService, @Nonnull ExecutorService executorService2) {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mListeners = new HashSet();
        this.mNetworkStateRefreshLock = new Object();
        this.mCachedNetworkInfo = new CachedNetworkInfo(DEFAULT_NETWORK_INFO);
        this.mQAOverride = null;
        this.mRefreshRunnable = new Runnable() { // from class: com.amazon.avod.connectivity.NetworkConnectionManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0();
            }
        };
        this.mConfig = (NetworkConnectionManagerConfig) Preconditions.checkNotNull(networkConnectionManagerConfig, "config");
        this.mRefreshExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "refreshExecutor");
        this.mEventExecutor = (ExecutorService) Preconditions.checkNotNull(executorService2, "eventExecutor");
    }

    public void initialize(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        Context applicationContext = context.getApplicationContext();
        this.mConnectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        this.mTelephonyManager = (TelephonyManager) applicationContext.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        this.mWifiManager = (WifiManager) applicationContext.getSystemService("wifi");
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mNetworkInfoTtlMillis = this.mConfig.getNetworkInfoTtlMillis();
        this.mInitializationLatch.complete();
        new ConnectivityChangeReceiver().register(applicationContext);
        refreshNetworkInfoAsync();
    }

    public void refreshNetworkInfoAsync() {
        this.mInitializationLatch.checkInitialized();
        this.mRefreshExecutor.execute(this.mRefreshRunnable);
    }

    @Nonnull
    @Deprecated
    public DetailedNetworkInfo getNetworkInfo() {
        return getCachedNetworkInfo();
    }

    @Nonnull
    public DetailedNetworkInfo getCachedNetworkInfo() {
        CachedNetworkInfo cachedNetworkInfo = this.mCachedNetworkInfo;
        if (cachedNetworkInfo.millisSinceUpdate() > this.mNetworkInfoTtlMillis && !cachedNetworkInfo.updateRequested.getAndSet(true)) {
            this.mRefreshExecutor.execute(this.mRefreshRunnable);
        }
        return cachedNetworkInfo.info;
    }

    @Nonnull
    public DetailedNetworkInfo getNetworkInfoSync() {
        this.mInitializationLatch.checkInitialized();
        return lambda$new$0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nonnull
    /* JADX INFO: renamed from: refreshFromConnectivityManager, reason: merged with bridge method [inline-methods] */
    public DetailedNetworkInfo lambda$new$0() {
        CachedNetworkInfo cachedNetworkInfo = new CachedNetworkInfo(resolveDetailedNetworkInfo(this.mConnectivityManager.getActiveNetworkInfo(), this.mQAOverride));
        synchronized (this.mNetworkStateRefreshLock) {
            CachedNetworkInfo cachedNetworkInfo2 = this.mCachedNetworkInfo;
            this.mCachedNetworkInfo = cachedNetworkInfo;
            if (!cachedNetworkInfo2.info.equals(cachedNetworkInfo.info)) {
                notifyListeners(cachedNetworkInfo2.info, cachedNetworkInfo.info);
            }
        }
        return cachedNetworkInfo.info;
    }

    @Nonnull
    private static DetailedNetworkInfo resolveDetailedNetworkInfo(@Nullable NetworkInfo networkInfo, @Nullable DetailedNetworkInfo detailedNetworkInfo) {
        if (detailedNetworkInfo != null) {
            return detailedNetworkInfo;
        }
        if (networkInfo != null) {
            return DetailedNetworkInfo.createFromNetworkInfo(networkInfo);
        }
        return DEFAULT_NETWORK_INFO;
    }

    @Nonnull
    @Deprecated
    public NetworkType getNetworkType() {
        return getCachedNetworkInfo().getNetworkType();
    }

    @Deprecated
    public boolean hasDataConnection() {
        return getCachedNetworkInfo().hasFullNetworkAccess();
    }

    @Deprecated
    public boolean hasWANConnection() {
        return getCachedNetworkInfo().hasWanConnection();
    }

    public boolean canDeviceSupportWAN() {
        if (this.mConfig.hasWANSupportBeenDetected()) {
            return true;
        }
        boolean zCheckForWANSupport = checkForWANSupport();
        if (zCheckForWANSupport) {
            this.mConfig.onWANSupportDetected();
        }
        return zCheckForWANSupport;
    }

    private boolean checkForWANSupport() {
        int simState = this.mTelephonyManager.getSimState();
        DLog.devf("SIM card state: %s", Integer.valueOf(simState));
        if (simState == 5 || simState == 4 || simState == 3 || simState == 2) {
            return true;
        }
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(0);
        DLog.devf("SIM card network info: %s", networkInfo);
        return networkInfo != null || getNetworkInfo().getNetworkType() == NetworkType.WAN;
    }

    public int getSignalStrength() {
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return -1;
        }
        return WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 5);
    }

    public int getSignalStrengthRssi() {
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if (connectionInfo == null || connectionInfo.getNetworkId() == -1) {
            return -1;
        }
        return connectionInfo.getRssi();
    }

    public void registerListener(@Nonnull ConnectionChangeListener connectionChangeListener) {
        Preconditions.checkNotNull(connectionChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.mListeners) {
            connectionChangeListener.initialize(this.mEventExecutor);
            if (!this.mListeners.add(connectionChangeListener)) {
                DLog.warnf("Couldn't re-register listener: %s", connectionChangeListener.getClass().getSimpleName());
            }
        }
    }

    public void registerListener(@Nonnull ConnectivityChangeListener connectivityChangeListener) {
        Preconditions.checkNotNull(connectivityChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.mListeners) {
            if (!this.mListeners.add(connectivityChangeListener)) {
                DLog.warnf("Couldn't re-register listener: %s", connectivityChangeListener.getClass().getSimpleName());
            }
        }
    }

    public void unregisterListener(@Nonnull ConnectivityChangeListener connectivityChangeListener) {
        Preconditions.checkNotNull(connectivityChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.mListeners) {
            this.mListeners.remove(connectivityChangeListener);
        }
    }

    private void notifyListeners(@Nonnull DetailedNetworkInfo detailedNetworkInfo, @Nonnull DetailedNetworkInfo detailedNetworkInfo2) {
        DLog.logf("Network Change: %s --> %s", detailedNetworkInfo, detailedNetworkInfo2);
        this.mHandler.post(new NotifyListenersRunnable(detailedNetworkInfo, detailedNetworkInfo2));
    }

    private class NotifyListenersRunnable implements Runnable {
        private final DetailedNetworkInfo mFrom;
        private final DetailedNetworkInfo mTo;

        private NotifyListenersRunnable(DetailedNetworkInfo detailedNetworkInfo, DetailedNetworkInfo detailedNetworkInfo2) {
            this.mFrom = detailedNetworkInfo;
            this.mTo = detailedNetworkInfo2;
        }

        @Override // java.lang.Runnable
        public void run() {
            notifyListenersFromMainThread(this.mFrom, this.mTo);
        }

        private void notifyListenersFromMainThread(DetailedNetworkInfo detailedNetworkInfo, DetailedNetworkInfo detailedNetworkInfo2) {
            synchronized (NetworkConnectionManager.this.mListeners) {
                Iterator it = NetworkConnectionManager.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((ConnectivityChangeListener) it.next()).onConnectionChange(detailedNetworkInfo, detailedNetworkInfo2);
                }
            }
        }
    }

    private class ConnectivityChangeReceiver extends BroadcastReceiver {
        private ConnectivityChangeReceiver() {
        }

        public void register(Context context) {
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                DLog.logf("Received CONNECTIVITY_ACTION intent. Refreshing network info.");
                NetworkConnectionManager.this.refreshNetworkInfoAsync();
            }
        }
    }

    public String getConnectionType() {
        this.mInitializationLatch.checkInitialized();
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        return activeNetworkInfo.getType() == 0 ? String.format(Locale.US, "%s-%s", activeNetworkInfo.getTypeName(), getMobileNetworkClass()) : (activeNetworkInfo.getType() == 1 && ConnectivityManagerCompat.isActiveNetworkMetered(this.mConnectivityManager)) ? String.format(Locale.US, "%s-%s", activeNetworkInfo.getTypeName(), "Metered") : activeNetworkInfo.getTypeName();
    }

    private String getMobileNetworkClass() {
        return MobileNetworkClass.getClassByTelephonyManager(this.mTelephonyManager).getShortName();
    }

    private static class CachedNetworkInfo {

        @Nonnull
        final DetailedNetworkInfo info;
        final long lastUpdateInRealtimeMillis;

        @Nonnull
        final AtomicBoolean updateRequested;

        private CachedNetworkInfo(@Nonnull DetailedNetworkInfo detailedNetworkInfo) {
            this.updateRequested = new AtomicBoolean(false);
            this.info = detailedNetworkInfo;
            this.lastUpdateInRealtimeMillis = SystemClock.elapsedRealtime();
        }

        long millisSinceUpdate() {
            return SystemClock.elapsedRealtime() - this.lastUpdateInRealtimeMillis;
        }
    }

    static class NetworkConnectionManagerConfig extends ConfigBase {
        private final ConfigurationValue<Boolean> mHasWANSupportBeenDetected;
        private final ConfigurationValue<Long> mNetworkInfoTtlMillis;

        private static class SingletonHolder {
            public static final NetworkConnectionManagerConfig INSTANCE = new NetworkConnectionManagerConfig();

            private SingletonHolder() {
            }
        }

        public static NetworkConnectionManagerConfig getInstance() {
            return SingletonHolder.INSTANCE;
        }

        NetworkConnectionManagerConfig() {
            super("NetworkConnectionManagerConfig");
            this.mHasWANSupportBeenDetected = newBooleanConfigValue("hasWANSupportBeenDetected", false, ConfigType.PERSISTENT);
            this.mNetworkInfoTtlMillis = newLongConfigValue("networkConnectionManager_networkInfoTtlMillis", TimeUnit.SECONDS.toMillis(5L), ConfigType.SERVER);
        }

        public long getNetworkInfoTtlMillis() {
            return this.mNetworkInfoTtlMillis.getValue().longValue();
        }

        public boolean hasWANSupportBeenDetected() {
            return this.mHasWANSupportBeenDetected.getValue().booleanValue();
        }

        public void onWANSupportDetected() {
            this.mHasWANSupportBeenDetected.updateValue(Boolean.TRUE);
        }
    }
}
