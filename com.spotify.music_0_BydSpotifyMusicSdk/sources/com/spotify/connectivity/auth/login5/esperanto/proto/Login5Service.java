package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired;
import com.spotify.esperanto.esperanto.ServiceBase;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class Login5Service implements ServiceBase {
    private final String name = "spotify.authentication.login5.impl.proto.Login5";

    public abstract Observable<EsAuthenticateResult.AuthenticateResult> authenticate(EsAuthenticateRequest.AuthenticateRequest authenticateRequest);

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
        if (v03.b(str2, "codeRequiredResend")) {
            EsCodeRequired.CodeRequiredResendRequest from = EsCodeRequired.CodeRequiredResendRequest.parseFrom(bArr);
            v03.i(from, "request_msg");
            Single map = codeRequiredResend(from).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.Login5Service.callSingle.1
                @Override // io.reactivex.rxjava3.functions.o
                public final byte[] apply(EsCodeRequired.CodeRequiredResendResult codeRequiredResendResult) {
                    v03.j(codeRequiredResendResult, "it");
                    return codeRequiredResendResult.toByteArray();
                }
            });
            v03.i(map, "codeRequiredResend(reque…it.toByteArray()\n      })");
            return map;
        }
        if (v03.b(str2, "codeRequiredProceed")) {
            EsCodeRequired.CodeRequiredProceedRequest from2 = EsCodeRequired.CodeRequiredProceedRequest.parseFrom(bArr);
            v03.i(from2, "request_msg");
            Single map2 = codeRequiredProceed(from2).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.Login5Service.callSingle.2
                @Override // io.reactivex.rxjava3.functions.o
                public final byte[] apply(EsCodeRequired.CodeRequiredProceedResult codeRequiredProceedResult) {
                    v03.j(codeRequiredProceedResult, "it");
                    return codeRequiredProceedResult.toByteArray();
                }
            });
            v03.i(map2, "codeRequiredProceed(requ…it.toByteArray()\n      })");
            return map2;
        }
        if (!v03.b(str2, "interactionRequiredProceed")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        EsInteractionRequired.InteractionRequiredProceedRequest from3 = EsInteractionRequired.InteractionRequiredProceedRequest.parseFrom(bArr);
        v03.i(from3, "request_msg");
        Single map3 = interactionRequiredProceed(from3).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.Login5Service.callSingle.3
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(EsInteractionRequired.InteractionRequiredProceedResult interactionRequiredProceedResult) {
                v03.j(interactionRequiredProceedResult, "it");
                return interactionRequiredProceedResult.toByteArray();
            }
        });
        v03.i(map3, "interactionRequiredProce…it.toByteArray()\n      })");
        return map3;
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
        if (!v03.b(str2, "authenticate")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        EsAuthenticateRequest.AuthenticateRequest from = EsAuthenticateRequest.AuthenticateRequest.parseFrom(bArr);
        v03.i(from, "request_msg");
        Observable map = authenticate(from).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.Login5Service.callStream.1
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(EsAuthenticateResult.AuthenticateResult authenticateResult) {
                v03.j(authenticateResult, "it");
                return authenticateResult.toByteArray();
            }
        });
        v03.i(map, "authenticate(request_msg…it.toByteArray()\n      })");
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

    public abstract Single<EsCodeRequired.CodeRequiredProceedResult> codeRequiredProceed(EsCodeRequired.CodeRequiredProceedRequest codeRequiredProceedRequest);

    public abstract Single<EsCodeRequired.CodeRequiredResendResult> codeRequiredResend(EsCodeRequired.CodeRequiredResendRequest codeRequiredResendRequest);

    @Override // com.spotify.esperanto.esperanto.ServiceBase
    public String getName() {
        return this.name;
    }

    public abstract Single<EsInteractionRequired.InteractionRequiredProceedResult> interactionRequiredProceed(EsInteractionRequired.InteractionRequiredProceedRequest interactionRequiredProceedRequest);
}
