package com.spotify.connectivity.platformconnectiontype;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.spotify.connectivity.connectiontype.ConnectionType;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import p.lc;

/* JADX INFO: loaded from: classes.dex */
class ConnectivityListenerImplLegacy extends ConnectivityListener {
    private static final Set<ConnectivityListenerImplLegacy> LISTENERS = new CopyOnWriteArraySet();
    private static ConnectionType sOverrideConnectionType;
    private static boolean sOverrideRoaming;
    private final ConnectivityManager mConnectivityManager;
    private final BroadcastReceiver mConnectivityReceiver = new BroadcastReceiver() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectivityListenerImplLegacy.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityListenerImplLegacy.this.notifyObservers();
        }
    };
    private final ConnectivityUtil mConnectivityUtil;
    private final Context mContext;
    private boolean mListening;
    private PhoneStateListener mTelephonyListener;
    private final TelephonyManager mTelephonyManager;

    public ConnectivityListenerImplLegacy(Context context, ConnectivityUtil connectivityUtil) {
        context.getClass();
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mTelephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
        this.mConnectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        this.mConnectivityUtil = connectivityUtil;
    }

    private synchronized void hangUp() {
        PhoneStateListener phoneStateListener;
        if (this.mListening) {
            LISTENERS.remove(this);
            this.mConnectivityObservers.clear();
            try {
                this.mContext.unregisterReceiver(this.mConnectivityReceiver);
            } catch (IllegalArgumentException e) {
                lc.e("Should have been registered", e);
            }
            TelephonyManager telephonyManager = this.mTelephonyManager;
            if (telephonyManager != null && (phoneStateListener = this.mTelephonyListener) != null) {
                telephonyManager.listen(phoneStateListener, 0);
            }
            this.mListening = false;
        }
    }

    private boolean isRoaming() {
        return sOverrideConnectionType != null ? sOverrideRoaming : this.mTelephonyManager.isNetworkRoaming();
    }

    private static void onDataConnectionStateChanged(ConnectionType connectionType, boolean z) {
        Iterator<ConnectivityListenerImplLegacy> it = LISTENERS.iterator();
        while (it.hasNext()) {
            it.next().setConnectionType(connectionType, z);
        }
    }

    private void setConnectionType(ConnectionType connectionType, boolean z) {
        synchronized (this.mConnectivityObservers) {
            try {
                Iterator<ConnectivityObserver> it = this.mConnectivityObservers.iterator();
                while (it.hasNext()) {
                    it.next().setConnectivityType(connectionType, z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void setOverrideConnectionState(ConnectionType connectionType, boolean z) {
        sOverrideConnectionType = connectionType;
        sOverrideRoaming = z;
        if (connectionType != null) {
            onDataConnectionStateChanged(connectionType, z);
            return;
        }
        Iterator<ConnectivityListenerImplLegacy> it = LISTENERS.iterator();
        while (it.hasNext()) {
            it.next().notifyObservers();
        }
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public void destroy() {
        hangUp();
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public ConnectionType getConnectionType() {
        ConnectionType connectionType = sOverrideConnectionType;
        return connectionType != null ? connectionType : this.mConnectivityUtil.getConnectionType(this.mConnectivityManager, this.mTelephonyManager);
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
        if (this.mListening) {
            return;
        }
        this.mContext.registerReceiver(this.mConnectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        if (this.mTelephonyManager != null) {
            PhoneStateListener phoneStateListener = new PhoneStateListener() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectivityListenerImplLegacy.2
                @Override // android.telephony.PhoneStateListener
                public void onDataConnectionStateChanged(int i, int i2) {
                    super.onDataConnectionStateChanged(i, i2);
                    ConnectivityListenerImplLegacy.this.notifyObservers();
                }
            };
            this.mTelephonyListener = phoneStateListener;
            this.mTelephonyManager.listen(phoneStateListener, 64);
        }
        this.mListening = true;
        LISTENERS.add(this);
    }
}
