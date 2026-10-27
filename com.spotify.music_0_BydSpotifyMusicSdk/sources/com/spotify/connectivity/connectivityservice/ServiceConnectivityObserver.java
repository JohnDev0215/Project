package com.spotify.connectivity.connectivityservice;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.spotify.connectivity.auth.LoginController;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceConnectivityObserver {
    private final ConnectivityManager connectivityManager;
    private final Context context;
    private boolean destroyed;
    private final LoginController loginController;
    private ConnectionType previousConnectionType;
    private final TelephonyManager telephonyManager;

    public ServiceConnectivityObserver(LoginController loginController, ConnectivityManager connectivityManager, Context context) {
        v03.j(loginController, "loginController");
        v03.j(connectivityManager, "connectivityManager");
        v03.j(context, "context");
        this.loginController = loginController;
        this.connectivityManager = connectivityManager;
        this.context = context;
        Object systemService = context.getSystemService("phone");
        v03.g(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
        this.telephonyManager = (TelephonyManager) systemService;
        this.previousConnectionType = ConnectionType.CONNECTION_TYPE_UNKNOWN;
    }

    private final com.spotify.connectivity.ConnectionType mapConnectionType(ConnectionType connectionType) {
        com.spotify.connectivity.ConnectionType connectionTypeConnectionTypeOfValue = com.spotify.connectivity.ConnectionType.Companion.connectionTypeOfValue(connectionType.getNativeConstant());
        return connectionTypeConnectionTypeOfValue == null ? com.spotify.connectivity.ConnectionType.UNKNOWN : connectionTypeConnectionTypeOfValue;
    }

    public final synchronized void destroy() {
        this.destroyed = true;
    }

    public final Context getContext() {
        return this.context;
    }

    public final synchronized void setConnectivityType(ConnectionType connectionType) {
        try {
            v03.j(connectionType, "newType");
            if (this.destroyed) {
                return;
            }
            this.connectivityManager.setConnectionType(mapConnectionType(connectionType), this.telephonyManager.isNetworkRoaming());
            ConnectionType connectionType2 = this.previousConnectionType;
            if (connectionType == connectionType2) {
                return;
            }
            this.previousConnectionType = connectionType;
            if (connectionType.isOffline()) {
                return;
            }
            this.loginController.tryReconnectNow((connectionType2.isOffline() || connectionType2.isCellularNetwork() == connectionType.isCellularNetwork()) ? false : true);
        } catch (Throwable th) {
            throw th;
        }
    }
}
