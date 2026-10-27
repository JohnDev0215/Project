package com.spotify.connectivity.platformconnectiontype;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.connectiontype.SpotifyConnectivityManager;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.f;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(24)
public final class SpotifyConnectivityManagerImpl implements SpotifyConnectivityManager {
    private final Context applicationContext;
    private final ConnectivityManager connectivityManager;
    private final Observable<ConnectionType> connectivityObservable;
    private final NetworkCallbackEventToConnectionTypeTransformer networkCallbackEventToConnectionTypeTransformer;

    public SpotifyConnectivityManagerImpl(Context context, ConnectivityUtil connectivityUtil, final Scheduler scheduler) {
        v03.j(context, "context");
        v03.j(connectivityUtil, "connectivityUtil");
        v03.j(scheduler, "debounceScheduler");
        Context applicationContext = context.getApplicationContext();
        v03.i(applicationContext, "checkNotNull(context).applicationContext");
        this.applicationContext = applicationContext;
        Object systemService = applicationContext.getSystemService("connectivity");
        v03.g(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.connectivityManager = (ConnectivityManager) systemService;
        NetworkCallbackEventToConnectionTypeTransformer networkCallbackEventToConnectionTypeTransformer = new NetworkCallbackEventToConnectionTypeTransformer(context, connectivityUtil);
        this.networkCallbackEventToConnectionTypeTransformer = networkCallbackEventToConnectionTypeTransformer;
        io.reactivex.rxjava3.observables.a aVarReplay = Observable.create(new ObservableOnSubscribe() { // from class: com.spotify.connectivity.platformconnectiontype.SpotifyConnectivityManagerImpl$connectivityObservable$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v1, types: [android.net.ConnectivityManager$NetworkCallback, com.spotify.connectivity.platformconnectiontype.SpotifyConnectivityManagerImpl$connectivityObservable$1$callback$1] */
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(final ObservableEmitter<NetworkCallbackEvent> observableEmitter) {
                v03.j(observableEmitter, "emitter");
                final ?? r0 = new ConnectivityManager.NetworkCallback() { // from class: com.spotify.connectivity.platformconnectiontype.SpotifyConnectivityManagerImpl$connectivityObservable$1$callback$1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onAvailable(Network network) {
                        v03.j(network, "network");
                        observableEmitter.onNext(new NetworkAvailable(network));
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                        v03.j(network, "network");
                        v03.j(networkCapabilities, "networkCapabilities");
                        observableEmitter.onNext(new NetworkCapabilitiesChanged(network, networkCapabilities));
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLost(Network network) {
                        v03.j(network, "network");
                        observableEmitter.onNext(new NetworkLost(network));
                    }
                };
                this.this$0.connectivityManager.registerDefaultNetworkCallback(r0);
                final SpotifyConnectivityManagerImpl spotifyConnectivityManagerImpl = this.this$0;
                observableEmitter.setCancellable(new f() { // from class: com.spotify.connectivity.platformconnectiontype.SpotifyConnectivityManagerImpl$connectivityObservable$1.1
                    @Override // io.reactivex.rxjava3.functions.f
                    public final void cancel() {
                        spotifyConnectivityManagerImpl.connectivityManager.unregisterNetworkCallback(r0);
                    }
                });
            }
        }).compose(networkCallbackEventToConnectionTypeTransformer).startWithItem(ConnectionType.CONNECTION_TYPE_NONE).distinctUntilChanged().debounce(new o() { // from class: com.spotify.connectivity.platformconnectiontype.SpotifyConnectivityManagerImpl$connectivityObservable$2
            @Override // io.reactivex.rxjava3.functions.o
            public final ObservableSource<ConnectionType> apply(ConnectionType connectionType) {
                Objects.requireNonNull(connectionType, "defaultValue is null");
                io.reactivex.rxjava3.subjects.b bVar = new io.reactivex.rxjava3.subjects.b(connectionType);
                return connectionType == ConnectionType.CONNECTION_TYPE_NONE ? bVar.delay(1L, TimeUnit.SECONDS, scheduler) : bVar;
            }
        }).replay(1);
        aVarReplay.getClass();
        this.connectivityObservable = new m3(aVarReplay);
    }

    @Override // com.spotify.connectivity.connectiontype.SpotifyConnectivityManager
    public ConnectionType getConnectionType() {
        ConnectionType currentConnectionType = this.networkCallbackEventToConnectionTypeTransformer.getCurrentConnectionType();
        if (currentConnectionType != ConnectionType.CONNECTION_TYPE_NONE) {
            return currentConnectionType;
        }
        ConnectionType connectionTypeBlockingFirst = this.connectivityObservable.blockingFirst();
        v03.i(connectionTypeBlockingFirst, "connectivityObservable.blockingFirst()");
        return connectionTypeBlockingFirst;
    }

    @Override // com.spotify.connectivity.connectiontype.SpotifyConnectivityManager
    public Observable<ConnectionType> getConnectionTypeObservable() {
        Observable<ConnectionType> observable = this.connectivityObservable;
        v03.i(observable, "connectivityObservable");
        return observable;
    }
}
