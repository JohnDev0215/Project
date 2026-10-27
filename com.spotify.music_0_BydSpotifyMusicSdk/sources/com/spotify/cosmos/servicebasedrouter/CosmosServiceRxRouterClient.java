package com.spotify.cosmos.servicebasedrouter;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import p.ra;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class CosmosServiceRxRouterClient extends RxRouterClient {
    private final ra appLifecycleServiceAdapter;
    private final CosmosServiceRxRouterClient$serviceConnection$1 serviceConnection;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.spotify.cosmos.servicebasedrouter.CosmosServiceRxRouterClient$serviceConnection$1] */
    public CosmosServiceRxRouterClient(ra raVar) {
        v03.j(raVar, "appLifecycleServiceAdapter");
        this.serviceConnection = new ServiceConnection() { // from class: com.spotify.cosmos.servicebasedrouter.CosmosServiceRxRouterClient$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                this.this$0.notifyOnConnected(new RemoteNativeRxRouter((RemoteNativeRouter) iBinder));
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                this.this$0.notifyOnDisconnected();
            }
        };
    }

    @Override // com.spotify.cosmos.servicebasedrouter.RxRouterClient
    public void connect() {
        super.connect();
        throw null;
    }

    @Override // com.spotify.cosmos.servicebasedrouter.RxRouterClient
    public void disconnect() {
        super.disconnect();
        throw null;
    }
}
