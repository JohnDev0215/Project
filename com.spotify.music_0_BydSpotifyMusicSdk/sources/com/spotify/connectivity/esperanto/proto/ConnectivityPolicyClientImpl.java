package com.spotify.connectivity.esperanto.proto;

import com.spotify.esperanto.esperanto.ClientBase;
import com.spotify.esperanto.esperanto.Transport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import java.util.Base64;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class ConnectivityPolicyClientImpl extends ClientBase implements ConnectivityPolicyClient {
    private final Transport transport;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectivityPolicyClientImpl(Transport transport) {
        super(transport);
        v03.j(transport, "transport");
        this.transport = transport;
    }

    @Override // com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClient
    public Single<ForceOfflineResponse> setForceOffline(ForceOfflineRequest forceOfflineRequest) {
        v03.j(forceOfflineRequest, "request");
        Single map = callSingle("spotify.connectivity.esperanto.proto.ConnectivityPolicy", "setForceOffline", forceOfflineRequest).map(new o() { // from class: com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClientImpl.setForceOffline.1
            @Override // io.reactivex.rxjava3.functions.o
            public final ForceOfflineResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return ForceOfflineResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.esperanto.proto.ForceOfflineResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.conn…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClient
    public Single<PutRulesResponse> setRules(PutRulesRequest putRulesRequest) {
        v03.j(putRulesRequest, "request");
        Single map = callSingle("spotify.connectivity.esperanto.proto.ConnectivityPolicy", "setRules", putRulesRequest).map(new o() { // from class: com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClientImpl.setRules.1
            @Override // io.reactivex.rxjava3.functions.o
            public final PutRulesResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return PutRulesResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.esperanto.proto.PutRulesResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.conn…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClient
    public Observable<GetStateResponse> subState(SubStateRequest subStateRequest) {
        v03.j(subStateRequest, "request");
        Observable map = callStream("spotify.connectivity.esperanto.proto.ConnectivityPolicy", "subState", subStateRequest).map(new o() { // from class: com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClientImpl.subState.1
            @Override // io.reactivex.rxjava3.functions.o
            public final GetStateResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return GetStateResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.esperanto.proto.GetStateResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callStream(\"spotify.conn…     }\n                })");
        return map;
    }
}
