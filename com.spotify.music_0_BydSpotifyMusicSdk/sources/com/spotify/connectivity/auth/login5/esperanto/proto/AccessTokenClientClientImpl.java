package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient;
import com.spotify.esperanto.esperanto.ClientBase;
import com.spotify.esperanto.esperanto.Transport;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import java.util.Base64;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class AccessTokenClientClientImpl extends ClientBase implements AccessTokenClientClient {
    private final Transport transport;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessTokenClientClientImpl(Transport transport) {
        super(transport);
        v03.j(transport, "transport");
        this.transport = transport;
    }

    @Override // com.spotify.connectivity.auth.login5.esperanto.proto.AccessTokenClientClient
    public Single<EsAccessTokenClient.AccessTokenResponse> getToken(EsAccessTokenClient.GetTokenRequest getTokenRequest) {
        v03.j(getTokenRequest, "request");
        Single map = callSingle("spotify.authentication.login5.impl.proto.AccessTokenClient", "getToken", getTokenRequest).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.AccessTokenClientClientImpl.getToken.1
            @Override // io.reactivex.rxjava3.functions.o
            public final EsAccessTokenClient.AccessTokenResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return EsAccessTokenClient.AccessTokenResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.auth…     }\n                })");
        return map;
    }
}
