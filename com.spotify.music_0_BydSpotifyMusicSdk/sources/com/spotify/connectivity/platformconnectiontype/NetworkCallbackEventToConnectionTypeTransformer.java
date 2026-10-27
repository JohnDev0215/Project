package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import com.spotify.connectivity.connectiontype.ConnectionType;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.o;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkCallbackEventToConnectionTypeTransformer implements ObservableTransformer<NetworkCallbackEvent, ConnectionType> {
    private final ConnectivityUtil connectivityUtil;
    private final Context context;
    private ConnectionType currentConnectionType;

    public NetworkCallbackEventToConnectionTypeTransformer(Context context, ConnectivityUtil connectivityUtil) {
        v03.j(context, "context");
        v03.j(connectivityUtil, "connectivityUtil");
        this.context = context;
        this.connectivityUtil = connectivityUtil;
        this.currentConnectionType = ConnectionType.CONNECTION_TYPE_NONE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConnectionType toConnectionType(NetworkCallbackEvent networkCallbackEvent) {
        if (!(networkCallbackEvent instanceof NetworkAvailable)) {
            if (networkCallbackEvent instanceof NetworkCapabilitiesChanged) {
                ConnectionType connectionType = this.connectivityUtil.getConnectionType(((NetworkCapabilitiesChanged) networkCallbackEvent).getCapabilities());
                v03.i(connectionType, "connectivityUtil.getConn…nType(event.capabilities)");
                this.currentConnectionType = connectionType;
            } else if (networkCallbackEvent instanceof NetworkLost) {
                this.currentConnectionType = ConnectionType.CONNECTION_TYPE_NONE;
            }
        }
        return this.currentConnectionType;
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<ConnectionType> apply(Observable<NetworkCallbackEvent> observable) {
        v03.j(observable, "upstream");
        ObservableSource map = observable.map(new o() { // from class: com.spotify.connectivity.platformconnectiontype.NetworkCallbackEventToConnectionTypeTransformer.apply.1
            @Override // io.reactivex.rxjava3.functions.o
            public final ConnectionType apply(NetworkCallbackEvent networkCallbackEvent) {
                v03.j(networkCallbackEvent, "p0");
                return NetworkCallbackEventToConnectionTypeTransformer.this.toConnectionType(networkCallbackEvent);
            }
        });
        v03.i(map, "upstream.map(::toConnectionType)");
        return map;
    }

    public final ConnectivityUtil getConnectivityUtil() {
        return this.connectivityUtil;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ConnectionType getCurrentConnectionType() {
        return this.currentConnectionType;
    }
}
