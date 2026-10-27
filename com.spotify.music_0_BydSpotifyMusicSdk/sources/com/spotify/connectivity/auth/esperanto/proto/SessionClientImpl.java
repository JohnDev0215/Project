package com.spotify.connectivity.auth.esperanto.proto;

import com.google.protobuf.Empty;
import com.spotify.connectivity.auth.esperanto.proto.EsSession;
import com.spotify.esperanto.esperanto.ClientBase;
import com.spotify.esperanto.esperanto.Transport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import java.util.Base64;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class SessionClientImpl extends ClientBase implements SessionClient {
    private final Transport transport;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionClientImpl(Transport transport) {
        super(transport);
        v03.j(transport, "transport");
        this.transport = transport;
    }

    @Override // com.spotify.connectivity.auth.esperanto.proto.SessionClient
    public Observable<EsSession.APPermanentErrorResult> apPermanentError(Empty empty) {
        v03.j(empty, "request");
        Observable map = callStream("spotify.connectivity.auth.esperanto.proto.Session", "apPermanentError", empty).map(new o() { // from class: com.spotify.connectivity.auth.esperanto.proto.SessionClientImpl.apPermanentError.1
            @Override // io.reactivex.rxjava3.functions.o
            public final EsSession.APPermanentErrorResult apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return EsSession.APPermanentErrorResult.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResult: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callStream(\"spotify.conn…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.auth.esperanto.proto.SessionClient
    public Single<EsSession.SendEndSongsResult> sendEndSongs(Empty empty) {
        v03.j(empty, "request");
        Single map = callSingle("spotify.connectivity.auth.esperanto.proto.Session", "sendEndSongs", empty).map(new o() { // from class: com.spotify.connectivity.auth.esperanto.proto.SessionClientImpl.sendEndSongs.1
            @Override // io.reactivex.rxjava3.functions.o
            public final EsSession.SendEndSongsResult apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return EsSession.SendEndSongsResult.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResult: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.conn…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.auth.esperanto.proto.SessionClient
    public Observable<Empty> willLogoutAndForgetCurrentUser(Empty empty) {
        v03.j(empty, "request");
        Observable map = callStream("spotify.connectivity.auth.esperanto.proto.Session", "willLogoutAndForgetCurrentUser", empty).map(new o() { // from class: com.spotify.connectivity.auth.esperanto.proto.SessionClientImpl.willLogoutAndForgetCurrentUser.1
            @Override // io.reactivex.rxjava3.functions.o
            public final Empty apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return Empty.g(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.google.protobuf.Empty: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callStream(\"spotify.conn…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.auth.esperanto.proto.SessionClient
    public Single<Empty> writeProductStateToLegacyStorage(EsSession.ProductStateMap productStateMap) {
        v03.j(productStateMap, "request");
        Single map = callSingle("spotify.connectivity.auth.esperanto.proto.Session", "writeProductStateToLegacyStorage", productStateMap).map(new o() { // from class: com.spotify.connectivity.auth.esperanto.proto.SessionClientImpl.writeProductStateToLegacyStorage.1
            @Override // io.reactivex.rxjava3.functions.o
            public final Empty apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return Empty.g(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.google.protobuf.Empty: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.conn…     }\n                })");
        return map;
    }
}
