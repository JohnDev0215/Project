package com.spotify.connectivity.connectivitysdkpolicy;

import com.spotify.connectivity.ConnectionType;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectivityManager {
    void destroy();

    ConnectionType getConnectionType();

    boolean isNetworkAllowed();

    boolean isNetworkAllowedIfRoaming();

    boolean isRoaming();

    boolean isSyncAllowedOverCellular();

    boolean isSyncAllowedOverWifi();

    void setConnectionType(ConnectionType connectionType, boolean z);

    void setNetworkAllowed(boolean z);

    void setNetworkAllowedIfRoaming(boolean z);

    void setSyncAllowedOverCellular(boolean z);

    void setSyncAllowedOverWifi(boolean z);
}
