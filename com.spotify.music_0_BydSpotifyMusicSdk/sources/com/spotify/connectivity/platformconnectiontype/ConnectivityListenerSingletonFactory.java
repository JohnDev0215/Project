package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityListenerSingletonFactory {
    private static volatile ConnectivityListener sConnectivityListener;

    private ConnectivityListenerSingletonFactory() {
    }

    public static ConnectivityListener createConnectivityListener(Context context, ConnectivityUtil connectivityUtil, PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        if (sConnectivityListener == null) {
            synchronized (ConnectivityListener.class) {
                try {
                    if (sConnectivityListener == null) {
                        sConnectivityListener = new ConnectivityListenerImpl(context, connectivityUtil, platformConnectionTypeProperties);
                    }
                } finally {
                }
            }
        }
        return sConnectivityListener;
    }
}
