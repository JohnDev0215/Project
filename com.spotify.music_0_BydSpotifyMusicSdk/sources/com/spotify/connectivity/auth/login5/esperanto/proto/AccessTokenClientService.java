package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient;
import com.spotify.esperanto.esperanto.ServiceBase;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class AccessTokenClientService implements ServiceBase {
    private final String name = "spotify.authentication.login5.impl.proto.AccessTokenClient";

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
        if (!v03.b(str2, "getToken")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        EsAccessTokenClient.GetTokenRequest from = EsAccessTokenClient.GetTokenRequest.parseFrom(bArr);
        v03.i(from, "request_msg");
        Single map = getToken(from).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.AccessTokenClientService.callSingle.1
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(EsAccessTokenClient.AccessTokenResponse accessTokenResponse) {
                v03.j(accessTokenResponse, "it");
                return accessTokenResponse.toByteArray();
            }
        });
        v03.i(map, "getToken(request_msg).ma…it.toByteArray()\n      })");
        return map;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Observable<byte[]> callStream(String str, String str2, byte[] bArr) {
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

    public abstract Single<EsAccessTokenClient.AccessTokenResponse> getToken(EsAccessTokenClient.GetTokenRequest getTokenRequest);
}
