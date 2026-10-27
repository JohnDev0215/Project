package com.amazon.mp3.net;

import android.content.Context;
import android.util.Log;
import com.amazon.hermes.AdpAuthMethod;
import com.amazon.hermes.AuthMethod;
import com.amazon.hermes.NoOpAuthMethod;
import com.amazon.hermes.OauthAuthMethod;
import com.amazon.hermes.RequestFacade;
import com.amazon.mp3.AmazonApplication;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.AdpInfo;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.ProfileProvider;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.perf.FirebasePerformance;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: RequestAuthorizer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B-\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000fH\u0002J\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000fJ\u0016\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000fH\u0002J\u001e\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016J\u001e\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0001R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/net/RequestAuthorizer;", "", "()V", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "shouldEnableOAuth", "", "authenticationProvider", "Lkotlin/Function0;", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "accessTokenProvider", "", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "adpInfoKV", "Lkotlin/Pair;", "authKV", "oAuthTokenKV", "signRequest", "Lcom/amazon/hermes/RequestFacade;", "requestFacade", "requestBody", "Lcom/amazon/mp3/net/HttpRequestBuilder;", "request", "urlAuthorizerMethod", "Lcom/amazon/hermes/AuthMethod;", ImagesContract.URL, "Ljava/net/URL;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RequestAuthorizer {
    private static final String TAG = RequestAuthorizer.class.getSimpleName();
    private final Function0<String> accessTokenProvider;
    private final Function0<AuthenticationProvider> authenticationProvider;
    private final boolean shouldEnableOAuth;

    /* JADX WARN: Multi-variable type inference failed */
    public RequestAuthorizer(boolean z, Function0<? extends AuthenticationProvider> authenticationProvider, Function0<String> accessTokenProvider) {
        Intrinsics.checkNotNullParameter(authenticationProvider, "authenticationProvider");
        Intrinsics.checkNotNullParameter(accessTokenProvider, "accessTokenProvider");
        this.shouldEnableOAuth = z;
        this.authenticationProvider = authenticationProvider;
        this.accessTokenProvider = accessTokenProvider;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public RequestAuthorizer() {
        Context context = AmazonApplication.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        this(context);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RequestAuthorizer(Context context) {
        this(AdpToOAuthMigrationEvaluator.isEnabled$default(AdpToOAuthMigrationEvaluator.INSTANCE.getInstance(context), false, 1, null), new Function0<AuthenticationProvider>() { // from class: com.amazon.mp3.net.RequestAuthorizer.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AuthenticationProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(AuthenticationProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof AuthenticationProvider)) {
                    interfaceProvider = null;
                }
                return (AuthenticationProvider) interfaceProvider;
            }
        }, new Function0<String>() { // from class: com.amazon.mp3.net.RequestAuthorizer.2
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ProfileProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof ProfileProvider)) {
                    interfaceProvider = null;
                }
                ProfileProvider profileProvider = (ProfileProvider) interfaceProvider;
                if (profileProvider == null) {
                    return null;
                }
                return profileProvider.getAccessTokenBlocking();
            }
        });
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final HttpRequestBuilder signRequest(HttpRequestBuilder request) {
        Outcome<AdpInfo> adpInfoBlocking;
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.shouldEnableOAuth) {
            Pair<String, String> pairOAuthTokenKV = oAuthTokenKV();
            if (pairOAuthTokenKV != null) {
                request.addHeader(pairOAuthTokenKV.getFirst(), pairOAuthTokenKV.getSecond());
            }
        } else {
            AuthenticationProvider authenticationProviderInvoke = this.authenticationProvider.invoke();
            if (authenticationProviderInvoke != null && (adpInfoBlocking = authenticationProviderInvoke.getAdpInfoBlocking()) != null && (adpInfoBlocking instanceof Outcome.Success) && RequestSigner.signRequest((AdpInfo) ((Outcome.Success) adpInfoBlocking).getValue(), request)) {
                return request;
            }
            Log.e(TAG, Intrinsics.stringPlus("Failed to fetch Adp Info for request signing ", request));
        }
        return request;
    }

    public final RequestFacade<?> signRequest(RequestFacade<?> requestFacade, String requestBody) {
        Outcome<AdpInfo> adpInfoBlocking;
        Intrinsics.checkNotNullParameter(requestFacade, "requestFacade");
        Intrinsics.checkNotNullParameter(requestBody, "requestBody");
        if (this.shouldEnableOAuth) {
            Pair<String, String> pairOAuthTokenKV = oAuthTokenKV();
            if (pairOAuthTokenKV != null) {
                requestFacade.addHeaders(pairOAuthTokenKV.getFirst(), pairOAuthTokenKV.getSecond());
            }
        } else {
            AuthenticationProvider authenticationProviderInvoke = this.authenticationProvider.invoke();
            if (authenticationProviderInvoke != null && (adpInfoBlocking = authenticationProviderInvoke.getAdpInfoBlocking()) != null && (adpInfoBlocking instanceof Outcome.Success)) {
                Outcome.Success success = (Outcome.Success) adpInfoBlocking;
                requestFacade.addHeaders(com.amazon.hermes.security.RequestSigner.signRequest(FirebasePerformance.HttpMethod.POST, requestFacade.getUrl(), requestBody, ((AdpInfo) success.getValue()).getPrivateKey(), ((AdpInfo) success.getValue()).getAdpToken()));
                return requestFacade;
            }
            Log.e(TAG, Intrinsics.stringPlus("Failed to sign request with ADP info ", requestFacade));
        }
        return requestFacade;
    }

    public final Pair<String, String> authKV() {
        return this.shouldEnableOAuth ? oAuthTokenKV() : adpInfoKV();
    }

    public final AuthMethod urlAuthorizerMethod(URL url, Object request) {
        Outcome<AdpInfo> adpInfoBlocking;
        if (this.shouldEnableOAuth) {
            String strInvoke = this.accessTokenProvider.invoke();
            if (strInvoke != null) {
                return new OauthAuthMethod(strInvoke);
            }
        } else {
            AuthenticationProvider authenticationProviderInvoke = this.authenticationProvider.invoke();
            if (authenticationProviderInvoke != null && (adpInfoBlocking = authenticationProviderInvoke.getAdpInfoBlocking()) != null) {
                if (url == null || request == null) {
                    Log.e(TAG, "URL and Service Request cannot be null");
                    return new NoOpAuthMethod();
                }
                if (adpInfoBlocking instanceof Outcome.Success) {
                    Outcome.Success success = (Outcome.Success) adpInfoBlocking;
                    return new AdpAuthMethod(((AdpInfo) success.getValue()).getAdpToken(), ((AdpInfo) success.getValue()).getPrivateKey(), url, request);
                }
            }
        }
        return new NoOpAuthMethod();
    }

    private final Pair<String, String> oAuthTokenKV() {
        String strInvoke = this.accessTokenProvider.invoke();
        if (strInvoke != null) {
            return new Pair<>("x-amz-access-token", strInvoke);
        }
        Log.e(TAG, "Failed to get OAuth token");
        return null;
    }

    private final Pair<String, String> adpInfoKV() {
        Outcome<AdpInfo> adpInfoBlocking;
        AuthenticationProvider authenticationProviderInvoke = this.authenticationProvider.invoke();
        if (authenticationProviderInvoke == null || (adpInfoBlocking = authenticationProviderInvoke.getAdpInfoBlocking()) == null || !(adpInfoBlocking instanceof Outcome.Success)) {
            return null;
        }
        return new Pair<>("x-adp-token", ((AdpInfo) ((Outcome.Success) adpInfoBlocking).getValue()).getAdpToken());
    }
}
