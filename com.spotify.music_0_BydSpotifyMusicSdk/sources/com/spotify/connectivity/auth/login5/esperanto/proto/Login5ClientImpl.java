package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired;
import com.spotify.esperanto.esperanto.ClientBase;
import com.spotify.esperanto.esperanto.Transport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import java.util.Base64;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class Login5ClientImpl extends ClientBase implements Login5Client {
    private final Transport transport;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Login5ClientImpl(Transport transport) {
        super(transport);
        v03.j(transport, "transport");
        this.transport = transport;
    }

    @Override // com.spotify.connectivity.auth.login5.esperanto.proto.Login5Client
    public Observable<EsAuthenticateResult.AuthenticateResult> authenticate(EsAuthenticateRequest.AuthenticateRequest authenticateRequest) {
        v03.j(authenticateRequest, "request");
        Observable map = callStream("spotify.authentication.login5.impl.proto.Login5", "authenticate", authenticateRequest).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.Login5ClientImpl.authenticate.1
            @Override // io.reactivex.rxjava3.functions.o
            public final EsAuthenticateResult.AuthenticateResult apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return EsAuthenticateResult.AuthenticateResult.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResult: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callStream(\"spotify.auth…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.auth.login5.esperanto.proto.Login5Client
    public Single<EsCodeRequired.CodeRequiredProceedResult> codeRequiredProceed(EsCodeRequired.CodeRequiredProceedRequest codeRequiredProceedRequest) {
        v03.j(codeRequiredProceedRequest, "request");
        Single map = callSingle("spotify.authentication.login5.impl.proto.Login5", "codeRequiredProceed", codeRequiredProceedRequest).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.Login5ClientImpl.codeRequiredProceed.1
            @Override // io.reactivex.rxjava3.functions.o
            public final EsCodeRequired.CodeRequiredProceedResult apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return EsCodeRequired.CodeRequiredProceedResult.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResult: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.auth…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.auth.login5.esperanto.proto.Login5Client
    public Single<EsCodeRequired.CodeRequiredResendResult> codeRequiredResend(EsCodeRequired.CodeRequiredResendRequest codeRequiredResendRequest) {
        v03.j(codeRequiredResendRequest, "request");
        Single map = callSingle("spotify.authentication.login5.impl.proto.Login5", "codeRequiredResend", codeRequiredResendRequest).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.Login5ClientImpl.codeRequiredResend.1
            @Override // io.reactivex.rxjava3.functions.o
            public final EsCodeRequired.CodeRequiredResendResult apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return EsCodeRequired.CodeRequiredResendResult.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResult: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.auth…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.auth.login5.esperanto.proto.Login5Client
    public Single<EsInteractionRequired.InteractionRequiredProceedResult> interactionRequiredProceed(EsInteractionRequired.InteractionRequiredProceedRequest interactionRequiredProceedRequest) {
        v03.j(interactionRequiredProceedRequest, "request");
        Single map = callSingle("spotify.authentication.login5.impl.proto.Login5", "interactionRequiredProceed", interactionRequiredProceedRequest).map(new o() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.Login5ClientImpl.interactionRequiredProceed.1
            @Override // io.reactivex.rxjava3.functions.o
            public final EsInteractionRequired.InteractionRequiredProceedResult apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return EsInteractionRequired.InteractionRequiredProceedResult.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResult: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.auth…     }\n                })");
        return map;
    }
}
