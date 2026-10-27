package com.spotify.connectivity.esperanto.proto;

import com.spotify.esperanto.esperanto.ServiceBase;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConnectivityPolicyService implements ServiceBase {
    private final String name = "spotify.connectivity.esperanto.proto.ConnectivityPolicy";

    @Override // com.spotify.esperanto.esperanto.Transport
    public Single<byte[]> callSingle(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (!v03.b(str, getName())) {
            StringBuilder sbP = u4.p("Attempted to access mismatched [", str, "], but this service is [");
            sbP.append(getName());
            sbP.append(']');
            throw new RuntimeException(sbP.toString());
        }
        if (v03.b(str2, "setRules")) {
            PutRulesRequest from = PutRulesRequest.parseFrom(bArr);
            v03.i(from, "request_msg");
            Single map = setRules(from).map(new o() { // from class: com.spotify.connectivity.esperanto.proto.ConnectivityPolicyService.callSingle.1
                @Override // io.reactivex.rxjava3.functions.o
                public final byte[] apply(PutRulesResponse putRulesResponse) {
                    v03.j(putRulesResponse, "it");
                    return putRulesResponse.toByteArray();
                }
            });
            v03.i(map, "setRules(request_msg).ma…it.toByteArray()\n      })");
            return map;
        }
        if (!v03.b(str2, "setForceOffline")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        ForceOfflineRequest from2 = ForceOfflineRequest.parseFrom(bArr);
        v03.i(from2, "request_msg");
        Single map2 = setForceOffline(from2).map(new o() { // from class: com.spotify.connectivity.esperanto.proto.ConnectivityPolicyService.callSingle.2
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(ForceOfflineResponse forceOfflineResponse) {
                v03.j(forceOfflineResponse, "it");
                return forceOfflineResponse.toByteArray();
            }
        });
        v03.i(map2, "setForceOffline(request_…it.toByteArray()\n      })");
        return map2;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Observable<byte[]> callStream(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (!v03.b(str, getName())) {
            StringBuilder sbP = u4.p("Attempted to access mismatched [", str, "], but this service is [");
            sbP.append(getName());
            sbP.append(']');
            throw new RuntimeException(sbP.toString());
        }
        if (!v03.b(str2, "subState")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        SubStateRequest from = SubStateRequest.parseFrom(bArr);
        v03.i(from, "request_msg");
        Observable map = subState(from).map(new o() { // from class: com.spotify.connectivity.esperanto.proto.ConnectivityPolicyService.callStream.1
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(GetStateResponse getStateResponse) {
                v03.j(getStateResponse, "it");
                return getStateResponse.toByteArray();
            }
        });
        v03.i(map, "subState(request_msg).ma…it.toByteArray()\n      })");
        return map;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public byte[] callSync(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (v03.b(str, getName())) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        StringBuilder sbP = u4.p("Attempted to access mismatched [", str, "], but this service is [");
        sbP.append(getName());
        sbP.append(']');
        throw new RuntimeException(sbP.toString());
    }

    @Override // com.spotify.esperanto.esperanto.ServiceBase
    public String getName() {
        return this.name;
    }

    public abstract Single<ForceOfflineResponse> setForceOffline(ForceOfflineRequest forceOfflineRequest);

    public abstract Single<PutRulesResponse> setRules(PutRulesRequest putRulesRequest);

    public abstract Observable<GetStateResponse> subState(SubStateRequest subStateRequest);
}
