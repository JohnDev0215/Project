package com.spotify.connectivity.platformconnectiontype;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.connectiontype.ConnectionType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(23)
class ConnectivityListenerImpl extends ConnectivityListener {
    private final ConnectivityManager mConnectivityManager;
    private final ConnectivityUtil mConnectivityUtil;
    private final Context mContext;
    private boolean mListening;
    private final PowerManager mPowerManager;
    private final PlatformConnectionTypeProperties mProperties;
    private PhoneStateListener mTelephonyListener;
    private final TelephonyManager mTelephonyManager;
    private final BroadcastReceiver mIdleReceiver = new BroadcastReceiver() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectivityListenerImpl.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityListenerImpl.this.notifyObservers();
        }
    };
    private final Map<Network, ConnectionType> mNetworkToConnectionTypeMap = new HashMap();
    private final ConnectivityManager.NetworkCallback mNetworkCallback = new AnonymousClass2();

    /* JADX INFO: renamed from: com.spotify.connectivity.platformconnectiontype.ConnectivityListenerImpl$2, reason: invalid class name */
    public class AnonymousClass2 extends ConnectivityManager.NetworkCallback {
        public AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onCapabilitiesChangedInternal, reason: merged with bridge method [inline-methods] */
        public void lambda$onCapabilitiesChanged$1(Network network, NetworkCapabilities networkCapabilities) {
            synchronized (ConnectivityListenerImpl.this.mNetworkToConnectionTypeMap) {
                ConnectivityListenerImpl.this.mNetworkToConnectionTypeMap.put(network, ConnectivityListenerImpl.this.mConnectivityUtil.getConnectionType(networkCapabilities));
            }
            ConnectivityListenerImpl.this.notifyObservers();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(final Network network) {
            if (ConnectivityListenerImpl.this.mProperties.shouldUseSingleThread()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.spotify.connectivity.platformconnectiontype.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.c.lambda$onAvailable$0(network);
                    }
                });
            } else {
                lambda$onAvailable$0(network);
            }
        }

        /* JADX INFO: renamed from: onAvailableInternal, reason: merged with bridge method [inline-methods] */
        public void lambda$onAvailable$0(Network network) {
            synchronized (ConnectivityListenerImpl.this.mNetworkToConnectionTypeMap) {
                ConnectivityListenerImpl.this.mNetworkToConnectionTypeMap.put(network, ConnectionType.CONNECTION_TYPE_NONE);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(final Network network, final NetworkCapabilities networkCapabilities) {
            if (ConnectivityListenerImpl.this.mProperties.shouldUseSingleThread()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.spotify.connectivity.platformconnectiontype.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.c.lambda$onCapabilitiesChanged$1(network, networkCapabilities);
                    }
                });
            } else {
                lambda$onCapabilitiesChanged$1(network, networkCapabilities);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            synchronized (ConnectivityListenerImpl.this.mNetworkToConnectionTypeMap) {
                ConnectivityListenerImpl.this.mNetworkToConnectionTypeMap.remove(network);
            }
            ConnectivityListenerImpl.this.notifyObservers();
        }
    }

    public ConnectivityListenerImpl(Context context, ConnectivityUtil connectivityUtil, PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        context.getClass();
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mProperties = platformConnectionTypeProperties;
        this.mTelephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
        this.mConnectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        this.mPowerManager = (PowerManager) applicationContext.getSystemService("power");
        this.mConnectivityUtil = connectivityUtil;
    }

    private boolean isIdle() {
        return this.mPowerManager.isDeviceIdleMode() && !this.mPowerManager.isIgnoringBatteryOptimizations(this.mContext.getPackageName());
    }

    private boolean isRoaming() {
        return this.mTelephonyManager.isNetworkRoaming();
    }

    public NetworkRequest buildNetworkRequest() {
        NetworkRequest.Builder builderAddCapability = new NetworkRequest.Builder().addCapability(12).addCapability(13);
        if (!this.mProperties.netCapabilitiesValidatedDisregardedEnabled()) {
            builderAddCapability.addCapability(16);
        }
        return builderAddCapability.build();
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public void destroy() {
        PhoneStateListener phoneStateListener;
        if (this.mListening) {
            synchronized (this.mConnectivityObservers) {
                this.mConnectivityObservers.clear();
            }
            this.mContext.unregisterReceiver(this.mIdleReceiver);
            TelephonyManager telephonyManager = this.mTelephonyManager;
            if (telephonyManager != null && (phoneStateListener = this.mTelephonyListener) != null) {
                telephonyManager.listen(phoneStateListener, 0);
            }
            this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
            synchronized (this.mNetworkToConnectionTypeMap) {
                this.mNetworkToConnectionTypeMap.clear();
            }
            this.mListening = false;
        }
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public ConnectionType getConnectionType() {
        if (isIdle()) {
            Logger.e("Connectivity status: (idle)", new Object[0]);
        }
        ConnectionType connectionType = ConnectionType.CONNECTION_TYPE_NONE;
        synchronized (this.mNetworkToConnectionTypeMap) {
            try {
                if (!this.mNetworkToConnectionTypeMap.isEmpty()) {
                    connectionType = (ConnectionType) Collections.max(this.mNetworkToConnectionTypeMap.values());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return connectionType;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public void notifyObservers() {
        ConnectionType connectionType = getConnectionType();
        boolean zIsRoaming = isRoaming();
        synchronized (this.mConnectivityObservers) {
            try {
                Iterator<ConnectivityObserver> it = this.mConnectivityObservers.iterator();
                while (it.hasNext()) {
                    it.next().setConnectivityType(connectionType, zIsRoaming);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public void setup() {
        if (this.mConnectivityManager != null) {
            this.mConnectivityManager.registerNetworkCallback(buildNetworkRequest(), this.mNetworkCallback);
        }
        if (this.mTelephonyManager != null) {
            PhoneStateListener phoneStateListener = new PhoneStateListener() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectivityListenerImpl.3
                @Override // android.telephony.PhoneStateListener
                public void onDataConnectionStateChanged(int i, int i2) {
                    super.onDataConnectionStateChanged(i, i2);
                    ConnectivityListenerImpl.this.notifyObservers();
                }
            };
            this.mTelephonyListener = phoneStateListener;
            this.mTelephonyManager.listen(phoneStateListener, 64);
        }
        this.mContext.registerReceiver(this.mIdleReceiver, new IntentFilter("android.os.action.DEVICE_IDLE_MODE_CHANGED"));
        this.mListening = true;
    }
}
