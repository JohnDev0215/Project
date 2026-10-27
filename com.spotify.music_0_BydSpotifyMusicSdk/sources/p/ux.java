package p;

import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.platformconnectiontype.ConnectivityMonitorImpl;
import com.spotify.connectivity.platformconnectiontype.ConnectivityObserver;
import com.spotify.connectivity.platformconnectiontype.InternetConnectivityObservableImpl;
import io.reactivex.rxjava3.core.ObservableEmitter;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ux implements ConnectivityObserver {
    public final /* synthetic */ int a;
    public final /* synthetic */ ObservableEmitter b;

    public /* synthetic */ ux(ObservableEmitter observableEmitter, int i) {
        this.a = i;
        this.b = observableEmitter;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.ConnectivityObserver
    public final void setConnectivityType(ConnectionType connectionType, boolean z) {
        int i = this.a;
        ObservableEmitter observableEmitter = this.b;
        switch (i) {
            case 0:
                ConnectivityMonitorImpl.lambda$getConnectionTypes$0(observableEmitter, connectionType, z);
                break;
            default:
                InternetConnectivityObservableImpl.lambda$createConnectionType$1(observableEmitter, connectionType, z);
                break;
        }
    }
}
