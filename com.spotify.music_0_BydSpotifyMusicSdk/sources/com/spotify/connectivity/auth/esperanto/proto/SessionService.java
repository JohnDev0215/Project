package com.spotify.connectivity.auth.esperanto.proto;

import com.google.protobuf.Empty;
import com.spotify.connectivity.auth.esperanto.proto.EsSession;
import com.spotify.esperanto.esperanto.ServiceBase;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class SessionService implements ServiceBase {
    private final String name = "spotify.connectivity.auth.esperanto.proto.Session";

    public abstract Observable<EsSession.APPermanentErrorResult> apPermanentError(Empty empty);

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
        if (v03.b(str2, "sendEndSongs")) {
            Empty emptyG = Empty.g(bArr);
            v03.i(emptyG, "request_msg");
            Single map = sendEndSongs(emptyG).map(new o() { // from class: com.spotify.connectivity.auth.esperanto.proto.SessionService.callSingle.1
                @Override // io.reactivex.rxjava3.functions.o
                public final byte[] apply(EsSession.SendEndSongsResult sendEndSongsResult) {
                    v03.j(sendEndSongsResult, "it");
                    return sendEndSongsResult.toByteArray();
                }
            });
            v03.i(map, "sendEndSongs(request_msg…it.toByteArray()\n      })");
            return map;
        }
        if (!v03.b(str2, "writeProductStateToLegacyStorage")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        EsSession.ProductStateMap from = EsSession.ProductStateMap.parseFrom(bArr);
        v03.i(from, "request_msg");
        Single map2 = writeProductStateToLegacyStorage(from).map(new o() { // from class: com.spotify.connectivity.auth.esperanto.proto.SessionService.callSingle.2
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(Empty empty) {
                v03.j(empty, "it");
                return empty.toByteArray();
            }
        });
        v03.i(map2, "writeProductStateToLegac…it.toByteArray()\n      })");
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
        if (v03.b(str2, "willLogoutAndForgetCurrentUser")) {
            Empty emptyG = Empty.g(bArr);
            v03.i(emptyG, "request_msg");
            Observable map = willLogoutAndForgetCurrentUser(emptyG).map(new o() { // from class: com.spotify.connectivity.auth.esperanto.proto.SessionService.callStream.1
                @Override // io.reactivex.rxjava3.functions.o
                public final byte[] apply(Empty empty) {
                    v03.j(empty, "it");
                    return empty.toByteArray();
                }
            });
            v03.i(map, "willLogoutAndForgetCurre…it.toByteArray()\n      })");
            return map;
        }
        if (!v03.b(str2, "apPermanentError")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        Empty emptyG2 = Empty.g(bArr);
        v03.i(emptyG2, "request_msg");
        Observable map2 = apPermanentError(emptyG2).map(new o() { // from class: com.spotify.connectivity.auth.esperanto.proto.SessionService.callStream.2
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(EsSession.APPermanentErrorResult aPPermanentErrorResult) {
                v03.j(aPPermanentErrorResult, "it");
                return aPPermanentErrorResult.toByteArray();
            }
        });
        v03.i(map2, "apPermanentError(request…it.toByteArray()\n      })");
        return map2;
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

    public abstract Single<EsSession.SendEndSongsResult> sendEndSongs(Empty empty);

    public abstract Observable<Empty> willLogoutAndForgetCurrentUser(Empty empty);

    public abstract Single<Empty> writeProductStateToLegacyStorage(EsSession.ProductStateMap productStateMap);
}
