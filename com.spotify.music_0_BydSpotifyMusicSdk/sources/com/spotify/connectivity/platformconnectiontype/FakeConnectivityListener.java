package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.productstate.RxProductState;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class FakeConnectivityListener extends ConnectivityListener {
    private final AtomicReference<ConnectionType> mCurrentType;
    private final Iterator<ConnectionType> mCurrentTypeIterator;

    public FakeConnectivityListener(List<? extends ConnectionType> list) {
        v03.j(list, "expectedTypes");
        Iterator it = list.iterator();
        this.mCurrentTypeIterator = it;
        this.mCurrentType = list.isEmpty() ^ true ? new AtomicReference<>(it.next()) : new AtomicReference<>(ConnectionType.CONNECTION_TYPE_WLAN);
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public void destroy() {
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public ConnectionType getConnectionType() {
        ConnectionType connectionType = this.mCurrentType.get();
        if (this.mCurrentTypeIterator.hasNext()) {
            this.mCurrentType.set(this.mCurrentTypeIterator.next());
        }
        v03.i(connectionType, RxProductState.Keys.KEY_TYPE);
        return connectionType;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public void notifyObservers() {
        List<ConnectivityObserver> list = this.mConnectivityObservers;
        v03.i(list, "mConnectivityObservers");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((ConnectivityObserver) it.next()).setConnectivityType(this.mCurrentType.get(), false);
        }
        if (this.mCurrentTypeIterator.hasNext()) {
            this.mCurrentType.set(this.mCurrentTypeIterator.next());
        }
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityListener
    public void setup() {
    }
}
