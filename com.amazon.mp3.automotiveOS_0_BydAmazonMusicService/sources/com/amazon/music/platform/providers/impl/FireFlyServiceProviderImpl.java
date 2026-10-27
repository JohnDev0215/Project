package com.amazon.music.platform.providers.impl;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazonaws.http.HttpHeader;
import com.apollographql.apollo3.ApolloClient;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: FireFlyServiceProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0001!B5\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u001f\u0010 J\u0013\u0010\u0004\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00032\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007H\u0014J\u0013\u0010\u000b\u001a\u00020\u0003H\u0094@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005R\u0017\u0010\f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lcom/amazon/music/platform/providers/impl/FireFlyServiceProviderImpl;", "Lcom/amazon/music/platform/providers/impl/GraphQLServiceProviderImpl;", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "", "getAuthToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authorizationToken", "", "httpHeaders", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "getApolloClient", "getAccessToken", "serviceEndpoint", "Ljava/lang/String;", "getServiceEndpoint", "()Ljava/lang/String;", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "authProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "getAuthProvider", "()Lcom/amazon/music/platform/providers/AuthenticationProvider;", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "getCustomerMetadataProvider", "()Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/providers/AuthenticationProvider;Lcom/amazon/music/platform/providers/DeviceInfoProvider;Lcom/amazon/music/platform/providers/CustomerMetadataProvider;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class FireFlyServiceProviderImpl extends GraphQLServiceProviderImpl implements FireFlyServiceProvider {
    private final AuthenticationProvider authProvider;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final String serviceEndpoint;

    /* JADX INFO: renamed from: com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl$getAccessToken$1, reason: invalid class name */
    /* JADX INFO: compiled from: FireFlyServiceProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl", f = "FireFlyServiceProviderImpl.kt", i = {0}, l = {72}, m = "getAccessToken", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FireFlyServiceProviderImpl.this.getAccessToken(this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl$getAuthToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FireFlyServiceProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl", f = "FireFlyServiceProviderImpl.kt", i = {}, l = {59}, m = "getAuthToken", n = {}, s = {})
    static final class C04431 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C04431(Continuation<? super C04431> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FireFlyServiceProviderImpl.this.getAuthToken(this);
        }
    }

    public FireFlyServiceProviderImpl() {
        this(null, null, null, null, 15, null);
    }

    public /* synthetic */ FireFlyServiceProviderImpl(String str, AuthenticationProvider authenticationProvider, DeviceInfoProvider deviceInfoProvider, CustomerMetadataProvider customerMetadataProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "https://gql.music.amazon.dev/" : str, (i & 2) != 0 ? (AuthenticationProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(AuthenticationProvider.class)) : authenticationProvider, (i & 4) != 0 ? (DeviceInfoProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(DeviceInfoProvider.class)) : deviceInfoProvider, (i & 8) != 0 ? (CustomerMetadataProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(CustomerMetadataProvider.class)) : customerMetadataProvider);
    }

    @Override // com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl
    public AuthenticationProvider getAuthProvider() {
        return this.authProvider;
    }

    @Override // com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl
    public DeviceInfoProvider getDeviceInfoProvider() {
        return this.deviceInfoProvider;
    }

    @Override // com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl
    public CustomerMetadataProvider getCustomerMetadataProvider() {
        return this.customerMetadataProvider;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FireFlyServiceProviderImpl(String serviceEndpoint, AuthenticationProvider authenticationProvider, DeviceInfoProvider deviceInfoProvider, CustomerMetadataProvider customerMetadataProvider) {
        super("https://gql.music.amazon.dev/", authenticationProvider, deviceInfoProvider, customerMetadataProvider, null, 16, null);
        Intrinsics.checkNotNullParameter(serviceEndpoint, "serviceEndpoint");
        this.serviceEndpoint = serviceEndpoint;
        this.authProvider = authenticationProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.customerMetadataProvider = customerMetadataProvider;
    }

    @Override // com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl
    protected ApolloClient.Builder getApolloClient(String authorizationToken, Map<String, String> httpHeaders) {
        Intrinsics.checkNotNullParameter(authorizationToken, "authorizationToken");
        return super.getApolloClient(authorizationToken, httpHeaders).addHttpHeader(HttpHeader.AUTHORIZATION, authorizationToken).addHttpHeader("x-api-key", PlatformProviderUtilKt.getFireFlyApiKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getAuthToken(kotlin.coroutines.Continuation<? super java.lang.String> r5) throws java.lang.Exception {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl.C04431
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl$getAuthToken$1 r0 = (com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl.C04431) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl$getAuthToken$1 r0 = new com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl$getAuthToken$1
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
            java.lang.Exception r5 = new java.lang.Exception
            r5.<init>(r0)
            throw r5
        L66:
            java.lang.Exception r5 = new java.lang.Exception
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl.getAuthToken(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object getAccessToken(kotlin.coroutines.Continuation<? super java.lang.String> r6) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl.getAccessToken(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
