package com.amazon.music.platform.providers.impl.mediagatewayservice;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.mediagateway.RecommendForAssistant$MediaContent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.LatencyTrackingWrapper;
import com.amazon.music.platform.providers.LoggingProvider;
import com.amazon.music.platform.providers.MediaGatewayServiceProvider;
import com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl;
import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.Error;
import com.apollographql.apollo3.exception.ApolloHttpException;
import com.apollographql.apollo3.exception.ApolloNetworkException;
import com.apollographql.apollo3.exception.ApolloWebSocketClosedException;
import com.apollographql.apollo3.exception.JsonDataException;
import com.apollographql.apollo3.exception.JsonEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: MediaGatewayServiceProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 42\u00020\u00012\u00020\u0002:\u00014Bu\u0012\u0006\u0010)\u001a\u00020\u0004\u0012\u0006\u0010\u001a\u001a\u00020\u0004\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010,\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\u000e\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040 \u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010.\u0012\u0006\u0010$\u001a\u00020#\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100¢\u0006\u0004\b2\u00103J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u001e\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\r\u001a\u00020\fH\u0002J&\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00042\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0010H\u0014J\u0013\u0010\u0014\u001a\u00020\u0004H\u0094@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0017\u001a\u00020\u0016H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001c\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u0004\u0018\u00010&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, d2 = {"Lcom/amazon/music/platform/providers/impl/mediagatewayservice/MediaGatewayServiceProviderImpl;", "Lcom/amazon/music/platform/providers/impl/GraphQLServiceProviderImpl;", "Lcom/amazon/music/platform/providers/MediaGatewayServiceProvider;", "", "", "requiredHeaders", "Lcom/apollographql/apollo3/api/Error;", "error", "Lcom/amazon/music/platform/Outcome;", "", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContent;", "gatewayError", "", "recommendationResult", "failure", "authorizationToken", "", "httpHeaders", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "getApolloClient", "getAccessToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery;", "queryData", "recommend", "(Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "platformOS", "Ljava/lang/String;", "platformSDKVersion", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "Lkotlin/Function0;", "playbackInstanceId", "Lkotlin/jvm/functions/Function0;", "Lcom/amazon/music/platform/providers/LoggingProvider;", "loggingProvider", "Lcom/amazon/music/platform/providers/LoggingProvider;", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "serviceEndpoint", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "authProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/LatencyTrackingWrapper;", "latencyTrackingWrapper", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/providers/AuthenticationProvider;Lcom/amazon/music/platform/providers/DeviceInfoProvider;Lcom/amazon/music/platform/providers/BuildInfoProvider;Lkotlin/jvm/functions/Function0;Lcom/amazon/music/platform/providers/CustomerMetadataProvider;Lcom/amazon/music/platform/providers/LoggingProvider;Lcom/amazon/music/platform/providers/LatencyTrackingWrapper;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class MediaGatewayServiceProviderImpl extends GraphQLServiceProviderImpl implements MediaGatewayServiceProvider {
    private static final String TAG;
    private final BuildInfoProvider buildInfoProvider;
    private final FeatureGateProvider featureGateProvider;
    private final LoggingProvider loggingProvider;
    private final String platformOS;
    private final String platformSDKVersion;
    private final Function0<String> playbackInstanceId;

    /* JADX INFO: renamed from: com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl$getAccessToken$1, reason: invalid class name */
    /* JADX INFO: compiled from: MediaGatewayServiceProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl", f = "MediaGatewayServiceProviderImpl.kt", i = {}, l = {72}, m = "getAccessToken", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaGatewayServiceProviderImpl.this.getAccessToken(this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl$recommend$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MediaGatewayServiceProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl", f = "MediaGatewayServiceProviderImpl.kt", i = {0}, l = {94}, m = "recommend", n = {"this"}, s = {"L$0"})
    static final class C04451 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04451(Continuation<? super C04451> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaGatewayServiceProviderImpl.this.recommend(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaGatewayServiceProviderImpl(String serviceEndpoint, String platformOS, String str, AuthenticationProvider authenticationProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, Function0<String> playbackInstanceId, CustomerMetadataProvider customerMetadataProvider, LoggingProvider loggingProvider, LatencyTrackingWrapper latencyTrackingWrapper) {
        super(serviceEndpoint, authenticationProvider, deviceInfoProvider, customerMetadataProvider, latencyTrackingWrapper);
        Intrinsics.checkNotNullParameter(serviceEndpoint, "serviceEndpoint");
        Intrinsics.checkNotNullParameter(platformOS, "platformOS");
        Intrinsics.checkNotNullParameter(playbackInstanceId, "playbackInstanceId");
        Intrinsics.checkNotNullParameter(loggingProvider, "loggingProvider");
        this.platformOS = platformOS;
        this.platformSDKVersion = str;
        this.buildInfoProvider = buildInfoProvider;
        this.playbackInstanceId = playbackInstanceId;
        this.loggingProvider = loggingProvider;
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        this.featureGateProvider = (FeatureGateProvider) (interfaceProvider instanceof FeatureGateProvider ? interfaceProvider : null);
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        String originalClassName = ProvidersKt.getOriginalClassName(companion);
        if (originalClassName == null) {
            originalClassName = "MediaGatewayServiceProviderImpl";
        }
        TAG = originalClassName;
    }

    @Override // com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl
    protected ApolloClient.Builder getApolloClient(String authorizationToken, Map<String, String> httpHeaders) {
        Intrinsics.checkNotNullParameter(authorizationToken, "authorizationToken");
        return super.getApolloClient(authorizationToken, httpHeaders).addHttpHeader("x-amz-access-token", authorizationToken);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object getAccessToken(kotlin.coroutines.Continuation<? super java.lang.String> r5) throws com.amazon.music.platform.providers.impl.mediagatewayservice.AuthTokenFetchingException {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl$getAccessToken$1 r0 = (com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl$getAccessToken$1 r0 = new com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl$getAccessToken$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L43
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            com.amazon.music.platform.providers.AuthenticationProvider r5 = r4.getAuthProvider()
            if (r5 == 0) goto L46
            r0.label = r3
            java.lang.Object r5 = r5.getOAuthInfo(r0)
            if (r5 != r1) goto L43
            return r1
        L43:
            com.amazon.music.platform.Outcome r5 = (com.amazon.music.platform.Outcome) r5
            goto L47
        L46:
            r5 = 0
        L47:
            java.lang.String r0 = "Missing authentication credentials."
            if (r5 == 0) goto L66
            boolean r1 = r5 instanceof com.amazon.music.platform.Outcome.Failure
            if (r1 != 0) goto L66
            boolean r1 = r5 instanceof com.amazon.music.platform.Outcome.Success
            if (r1 == 0) goto L60
            com.amazon.music.platform.Outcome$Success r5 = (com.amazon.music.platform.Outcome.Success) r5
            java.lang.Object r5 = r5.getValue()
            com.amazon.music.platform.data.OAuthInfo r5 = (com.amazon.music.platform.data.OAuthInfo) r5
            java.lang.String r5 = r5.getAccessToken()
            return r5
        L60:
            com.amazon.music.platform.providers.impl.mediagatewayservice.AuthTokenFetchingException r5 = new com.amazon.music.platform.providers.impl.mediagatewayservice.AuthTokenFetchingException
            r5.<init>(r0)
            throw r5
        L66:
            com.amazon.music.platform.providers.impl.mediagatewayservice.AuthTokenFetchingException r5 = new com.amazon.music.platform.providers.impl.mediagatewayservice.AuthTokenFetchingException
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl.getAccessToken(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.amazon.music.platform.providers.MediaGatewayServiceProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object recommend(com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery r8, kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<? extends java.util.List<? extends com.amazon.music.platform.data.mediagateway.RecommendForAssistant$MediaContent>>> r9) {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.providers.impl.mediagatewayservice.MediaGatewayServiceProviderImpl.recommend(com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Map<String, String> requiredHeaders() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        StringBuilder sb = new StringBuilder();
        sb.append(this.playbackInstanceId.invoke());
        sb.append(',');
        BuildInfoProvider buildInfoProvider = this.buildInfoProvider;
        sb.append(buildInfoProvider != null ? buildInfoProvider.getAppVersion() : null);
        sb.append(',');
        sb.append(this.platformOS);
        sb.append(';');
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        sb.append(deviceInfoProvider != null ? deviceInfoProvider.getOsVersion() : null);
        String string = sb.toString();
        if (this.platformSDKVersion != null) {
            string = string + ';' + this.platformSDKVersion;
        }
        linkedHashMap.put("x-amzn-app-correlation", string);
        return linkedHashMap;
    }

    private final Outcome<List<RecommendForAssistant$MediaContent>> gatewayError(Error error) {
        if (error != null) {
            return new Outcome.Failure(new MediaGatewayServiceProviderException(ThirdPartyVoiceError.gatewayServiceError.getErrorCode(), "Error: " + error.getMessage() + ", extension: " + error.getExtensions()));
        }
        return new Outcome.Failure(new MediaGatewayServiceProviderException(ThirdPartyVoiceError.gatewayEmptyResponse.getErrorCode(), "Undefined content for recommendation"));
    }

    private final Outcome<List<RecommendForAssistant$MediaContent>> failure(Throwable recommendationResult) {
        MediaGatewayServiceProviderException mediaGatewayServiceProviderException;
        if (recommendationResult instanceof ApolloNetworkException) {
            String errorCode = ThirdPartyVoiceError.networkError.getErrorCode();
            Throwable cause = recommendationResult.getCause();
            mediaGatewayServiceProviderException = new MediaGatewayServiceProviderException(errorCode, cause != null ? cause.getMessage() : null);
        } else if (recommendationResult instanceof ApolloHttpException) {
            String errorCode2 = ThirdPartyVoiceError.httpError.getErrorCode();
            Throwable cause2 = recommendationResult.getCause();
            mediaGatewayServiceProviderException = new MediaGatewayServiceProviderException(errorCode2, cause2 != null ? cause2.getMessage() : null);
        } else if (recommendationResult instanceof ApolloWebSocketClosedException) {
            String errorCode3 = ThirdPartyVoiceError.socketClosedError.getErrorCode();
            Throwable cause3 = recommendationResult.getCause();
            mediaGatewayServiceProviderException = new MediaGatewayServiceProviderException(errorCode3, cause3 != null ? cause3.getMessage() : null);
        } else if (recommendationResult instanceof JsonEncodingException) {
            String errorCode4 = ThirdPartyVoiceError.jsonEncodingError.getErrorCode();
            Throwable cause4 = recommendationResult.getCause();
            mediaGatewayServiceProviderException = new MediaGatewayServiceProviderException(errorCode4, cause4 != null ? cause4.getMessage() : null);
        } else if (recommendationResult instanceof JsonDataException) {
            String errorCode5 = ThirdPartyVoiceError.jsonDataError.getErrorCode();
            Throwable cause5 = recommendationResult.getCause();
            mediaGatewayServiceProviderException = new MediaGatewayServiceProviderException(errorCode5, cause5 != null ? cause5.getMessage() : null);
        } else if (recommendationResult instanceof AuthTokenFetchingException) {
            String errorCode6 = ThirdPartyVoiceError.authTokenFailure.getErrorCode();
            Throwable cause6 = recommendationResult.getCause();
            mediaGatewayServiceProviderException = new MediaGatewayServiceProviderException(errorCode6, cause6 != null ? cause6.getMessage() : null);
        } else {
            String errorCode7 = ThirdPartyVoiceError.genericError.getErrorCode();
            Throwable cause7 = recommendationResult.getCause();
            mediaGatewayServiceProviderException = new MediaGatewayServiceProviderException(errorCode7, cause7 != null ? cause7.getMessage() : null);
        }
        this.loggingProvider.logDebug(TAG, "recommendation request failure: " + recommendationResult);
        return new Outcome.Failure(mediaGatewayServiceProviderException);
    }
}
