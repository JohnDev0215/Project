package com.spotify.connectivity.platformconnectiontype;

import com.google.protobuf.i;
import com.spotify.connectivity.connectiontype.ConnectivityManager;
import com.spotify.connectivity.connectiontype.OfflineState;
import com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClient;
import com.spotify.connectivity.esperanto.proto.ForceOfflineRequest;
import com.spotify.connectivity.esperanto.proto.ForceOfflineResponse;
import com.spotify.connectivity.esperanto.proto.GetStateResponse;
import com.spotify.connectivity.esperanto.proto.PutRulesRequest;
import com.spotify.connectivity.esperanto.proto.PutRulesResponse;
import com.spotify.connectivity.esperanto.proto.SubStateRequest;
import com.spotify.connectivity.productstate.RxProductState;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.operators.completable.d;
import io.reactivex.rxjava3.internal.operators.completable.e;
import java.util.Objects;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityManagerEsperanto implements ConnectivityManager {
    private final ConnectivityPolicyClient client;

    public ConnectivityManagerEsperanto(ConnectivityPolicyClient connectivityPolicyClient) {
        v03.j(connectivityPolicyClient, "client");
        this.client = connectivityPolicyClient;
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectivityManager
    public Completable forceOffline(boolean z) {
        ForceOfflineRequest forceOfflineRequest = (ForceOfflineRequest) ForceOfflineRequest.newBuilder().setForceOffline(z).build();
        ConnectivityPolicyClient connectivityPolicyClient = this.client;
        v03.i(forceOfflineRequest, "request");
        Completable completableFlatMapCompletable = connectivityPolicyClient.setForceOffline(forceOfflineRequest).flatMapCompletable(new o() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectivityManagerEsperanto.forceOffline.1

            /* JADX INFO: renamed from: com.spotify.connectivity.platformconnectiontype.ConnectivityManagerEsperanto$forceOffline$1$WhenMappings */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ForceOfflineResponse.StatusCode.values().length];
                    try {
                        iArr[ForceOfflineResponse.StatusCode.SUCCESS.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[ForceOfflineResponse.StatusCode.NOT_FOUND.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // io.reactivex.rxjava3.functions.o
            public final CompletableSource apply(ForceOfflineResponse forceOfflineResponse) {
                ForceOfflineResponse.StatusCode statusCode = forceOfflineResponse.getStatusCode();
                int i = statusCode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[statusCode.ordinal()];
                if (i == 1) {
                    return d.c;
                }
                int i2 = 0;
                return i != 2 ? new e(i2, new RuntimeException(forceOfflineResponse.getErrorMessage())) : new e(i2, new IllegalStateException(forceOfflineResponse.getErrorMessage()));
            }
        });
        v03.i(completableFlatMapCompletable, "single.flatMapCompletabl…)\n            }\n        }");
        return completableFlatMapCompletable;
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectivityManager
    public Completable setAllowSyncOverCellular(boolean z) {
        ConnectivityPolicyClient connectivityPolicyClient = this.client;
        i iVarBuild = PutRulesRequest.newBuilder().setAllowSyncOverCellular(z).build();
        v03.i(iVarBuild, "newBuilder().setAllowSyn…erCellular(allow).build()");
        Single<PutRulesResponse> rules = connectivityPolicyClient.setRules((PutRulesRequest) iVarBuild);
        Objects.requireNonNull(rules, "single is null");
        return new e(5, rules);
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectivityManager
    public Observable<OfflineState> state() {
        SubStateRequest subStateRequest = (SubStateRequest) SubStateRequest.newBuilder().build();
        ConnectivityPolicyClient connectivityPolicyClient = this.client;
        v03.i(subStateRequest, "request");
        Observable map = connectivityPolicyClient.subState(subStateRequest).map(new o() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectivityManagerEsperanto.state.1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // io.reactivex.rxjava3.functions.o
            public final OfflineState apply(GetStateResponse getStateResponse) {
                if (getStateResponse.getStatusCode() != GetStateResponse.StatusCode.SUCCESS) {
                    throw new IllegalStateException(getStateResponse.getErrorMessage());
                }
                String status = getStateResponse.getStatus();
                if (status != null) {
                    switch (status.hashCode()) {
                        case -1548612125:
                            if (status.equals(RxProductState.Keys.KEY_OFFLINE)) {
                                return new OfflineStateImpl(OfflineState.State.OFFLINE);
                            }
                            break;
                        case -1012222381:
                            if (status.equals("online")) {
                                return new OfflineStateImpl(OfflineState.State.ONLINE);
                            }
                            break;
                        case -424567171:
                            if (status.equals("forced_offline")) {
                                return new OfflineStateImpl(OfflineState.State.FORCED_OFFLINE);
                            }
                            break;
                        case -48584405:
                            if (status.equals("reconnecting")) {
                                return new OfflineStateImpl(OfflineState.State.RECONNECTING);
                            }
                            break;
                    }
                }
                throw new IllegalStateException(getStateResponse.getStatus());
            }
        });
        v03.i(map, "client.subState(request)…)\n            }\n        }");
        return map;
    }
}
