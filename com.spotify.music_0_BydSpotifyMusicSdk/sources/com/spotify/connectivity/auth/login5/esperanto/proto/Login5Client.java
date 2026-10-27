package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface Login5Client {
    Observable<EsAuthenticateResult.AuthenticateResult> authenticate(EsAuthenticateRequest.AuthenticateRequest authenticateRequest);

    Single<EsCodeRequired.CodeRequiredProceedResult> codeRequiredProceed(EsCodeRequired.CodeRequiredProceedRequest codeRequiredProceedRequest);

    Single<EsCodeRequired.CodeRequiredResendResult> codeRequiredResend(EsCodeRequired.CodeRequiredResendRequest codeRequiredResendRequest);

    Single<EsInteractionRequired.InteractionRequiredProceedResult> interactionRequiredProceed(EsInteractionRequired.InteractionRequiredProceedRequest interactionRequiredProceedRequest);
}
