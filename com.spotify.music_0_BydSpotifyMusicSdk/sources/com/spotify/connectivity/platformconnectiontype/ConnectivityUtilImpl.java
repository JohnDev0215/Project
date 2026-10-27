package com.spotify.connectivity.platformconnectiontype;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.telephony.TelephonyManager;
import com.spotify.connectivity.connectiontype.ConnectionType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityUtilImpl implements ConnectivityUtil {
    private final PlatformConnectionTypeProperties mProperties;

    public ConnectivityUtilImpl(PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        this.mProperties = platformConnectionTypeProperties;
    }

    @TargetApi(23)
    private List<NetworkCapabilities> getAllInternetCapabilites(ConnectivityManager connectivityManager) {
        ArrayList arrayList = new ArrayList();
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            if (hasUnrestrictedInternetCapabilities(networkCapabilities)) {
                arrayList.add(networkCapabilities);
            }
        }
        return arrayList;
    }

    @TargetApi(23)
    private boolean hasUnrestrictedInternetCapabilities(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(13)) {
            return (this.mProperties.netCapabilitiesValidatedDisregardedEnabled() && networkCapabilities.hasTransport(0)) || networkCapabilities.hasCapability(16);
        }
        return false;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    public ConnectionType getConnectionType(Context context) {
        return getConnectionType((ConnectivityManager) context.getSystemService("connectivity"));
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    public ConnectionType getConnectionType(ConnectivityManager connectivityManager, TelephonyManager telephonyManager) {
        return getConnectionType(connectivityManager);
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    @TargetApi(23)
    public ConnectionType getConnectionType(Context context, Network network) {
        return getConnectionType(((ConnectivityManager) context.getSystemService("connectivity")).getNetworkCapabilities(network));
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    public ConnectionType getConnectionType(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return ConnectionType.CONNECTION_TYPE_NONE;
        }
        List<NetworkCapabilities> allInternetCapabilites = getAllInternetCapabilites(connectivityManager);
        if (!allInternetCapabilites.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator<NetworkCapabilities> it = allInternetCapabilites.iterator();
            while (it.hasNext()) {
                arrayList.add(getConnectionType(it.next()));
            }
            Collections.sort(arrayList, Collections.reverseOrder());
            return (ConnectionType) arrayList.get(0);
        }
        return ConnectionType.CONNECTION_TYPE_NONE;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityUtil
    @TargetApi(23)
    public ConnectionType getConnectionType(NetworkCapabilities networkCapabilities) {
        if (!hasUnrestrictedInternetCapabilities(networkCapabilities)) {
            return ConnectionType.CONNECTION_TYPE_NONE;
        }
        if (networkCapabilities.hasTransport(3)) {
            return ConnectionType.CONNECTION_TYPE_ETHERNET;
        }
        if (networkCapabilities.hasTransport(1)) {
            return ConnectionType.CONNECTION_TYPE_WLAN;
        }
        if (networkCapabilities.hasTransport(2)) {
            if (networkCapabilities.hasCapability(11)) {
                return ConnectionType.CONNECTION_TYPE_WLAN;
            }
            return ConnectionType.CONNECTION_TYPE_3G;
        }
        if (networkCapabilities.hasTransport(0)) {
            int linkDownstreamBandwidthKbps = networkCapabilities.getLinkDownstreamBandwidthKbps();
            if (linkDownstreamBandwidthKbps > 10000) {
                return ConnectionType.CONNECTION_TYPE_4G;
            }
            if (linkDownstreamBandwidthKbps > 100) {
                return ConnectionType.CONNECTION_TYPE_3G;
            }
            if (linkDownstreamBandwidthKbps > 50) {
                return ConnectionType.CONNECTION_TYPE_EDGE;
            }
            return ConnectionType.CONNECTION_TYPE_GPRS;
        }
        networkCapabilities.toString();
        return ConnectionType.CONNECTION_TYPE_3G;
    }
}
