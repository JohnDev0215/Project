package com.amazon.music.platform.providers.impl;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.CustomerMetadata;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.GraphQLServiceProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.LatencyTrackingWrapper;
import com.amazon.music.platform.providers.LoggingProvider;
import com.amazon.music.platform.providers.LoggingProviderKt;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Query;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: GraphQLServiceProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 32\u00020\u0001:\u00013B?\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*¢\u0006\u0004\b1\u00102J\b\u0010\u0003\u001a\u00020\u0002H\u0002J(\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00022\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005H\u0014J7\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0\r\"\b\b\u0000\u0010\n*\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010JM\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0\r\"\b\b\u0000\u0010\n*\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0011J7\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0\r\"\b\b\u0000\u0010\n*\u00020\t2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015JM\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0\r\"\b\b\u0000\u0010\n*\u00020\t2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0002H¤@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010!\u001a\u0004\u0018\u00010 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001c\u0010&\u001a\u0004\u0018\u00010%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0016\u0010+\u001a\u0004\u0018\u00010*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u00100\u001a\u0004\u0018\u00010-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"Lcom/amazon/music/platform/providers/impl/GraphQLServiceProviderImpl;", "Lcom/amazon/music/platform/providers/GraphQLServiceProvider;", "", "generateRequestId", "authorizationToken", "", "httpHeaders", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "getApolloClient", "", "D", "Lcom/apollographql/apollo3/api/Query;", "queryData", "Lcom/amazon/music/platform/Outcome;", "Lcom/apollographql/apollo3/api/ApolloResponse;", SearchIntents.EXTRA_QUERY, "(Lcom/apollographql/apollo3/api/Query;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/apollographql/apollo3/api/Query;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/apollographql/apollo3/api/Mutation;", "mutationData", "mutation", "(Lcom/apollographql/apollo3/api/Mutation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/apollographql/apollo3/api/Mutation;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccessToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serviceEndpoint", "Ljava/lang/String;", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "authProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "getAuthProvider", "()Lcom/amazon/music/platform/providers/AuthenticationProvider;", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "getCustomerMetadataProvider", "()Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "Lcom/amazon/music/platform/providers/LatencyTrackingWrapper;", "latencyTrackingWrapper", "Lcom/amazon/music/platform/providers/LatencyTrackingWrapper;", "Lcom/amazon/music/platform/providers/LoggingProvider;", "getLoggingProvider", "()Lcom/amazon/music/platform/providers/LoggingProvider;", "loggingProvider", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/providers/AuthenticationProvider;Lcom/amazon/music/platform/providers/DeviceInfoProvider;Lcom/amazon/music/platform/providers/CustomerMetadataProvider;Lcom/amazon/music/platform/providers/LatencyTrackingWrapper;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public abstract class GraphQLServiceProviderImpl implements GraphQLServiceProvider {
    private final AuthenticationProvider authProvider;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final LatencyTrackingWrapper latencyTrackingWrapper;
    private final String serviceEndpoint;

    /* JADX INFO: renamed from: com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl$mutation$2, reason: invalid class name */
    /* JADX INFO: compiled from: GraphQLServiceProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl", f = "GraphQLServiceProviderImpl.kt", i = {0, 0, 0}, l = {114, 115}, m = "mutation$suspendImpl", n = {"$this", "mutationData", "httpHeaders"}, s = {"L$0", "L$1", "L$2"})
    static final class AnonymousClass2<D> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GraphQLServiceProviderImpl.mutation$suspendImpl(GraphQLServiceProviderImpl.this, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl$query$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GraphQLServiceProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl", f = "GraphQLServiceProviderImpl.kt", i = {0, 0, 0, 1}, l = {84, 94}, m = "query$suspendImpl", n = {"$this", "queryData", "httpHeaders", "$this"}, s = {"L$0", "L$1", "L$2", "L$0"})
    static final class C04442<D> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04442(Continuation<? super C04442> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GraphQLServiceProviderImpl.query$suspendImpl(GraphQLServiceProviderImpl.this, null, null, this);
        }
    }

    protected abstract Object getAccessToken(Continuation<? super String> continuation);

    public <D> Object mutation(Mutation<D> mutation, Map<String, String> map, Continuation<? super Outcome<ApolloResponse<D>>> continuation) {
        return mutation$suspendImpl(this, mutation, map, continuation);
    }

    @Override // com.amazon.music.platform.providers.GraphQLServiceProvider
    public <D> Object mutation(Mutation<D> mutation, Continuation<? super Outcome<ApolloResponse<D>>> continuation) {
        return mutation$suspendImpl(this, mutation, continuation);
    }

    @Override // com.amazon.music.platform.providers.GraphQLServiceProvider
    public <D> Object query(Query<D> query, Map<String, String> map, Continuation<? super Outcome<ApolloResponse<D>>> continuation) {
        return query$suspendImpl(this, query, map, continuation);
    }

    @Override // com.amazon.music.platform.providers.GraphQLServiceProvider
    public <D> Object query(Query<D> query, Continuation<? super Outcome<ApolloResponse<D>>> continuation) {
        return query$suspendImpl(this, query, continuation);
    }

    public GraphQLServiceProviderImpl(String serviceEndpoint, AuthenticationProvider authenticationProvider, DeviceInfoProvider deviceInfoProvider, CustomerMetadataProvider customerMetadataProvider, LatencyTrackingWrapper latencyTrackingWrapper) {
        Intrinsics.checkNotNullParameter(serviceEndpoint, "serviceEndpoint");
        this.serviceEndpoint = serviceEndpoint;
        this.authProvider = authenticationProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        this.latencyTrackingWrapper = latencyTrackingWrapper;
    }

    public /* synthetic */ GraphQLServiceProviderImpl(String str, AuthenticationProvider authenticationProvider, DeviceInfoProvider deviceInfoProvider, CustomerMetadataProvider customerMetadataProvider, LatencyTrackingWrapper latencyTrackingWrapper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? (AuthenticationProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(AuthenticationProvider.class)) : authenticationProvider, (i & 4) != 0 ? (DeviceInfoProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(DeviceInfoProvider.class)) : deviceInfoProvider, (i & 8) != 0 ? (CustomerMetadataProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(CustomerMetadataProvider.class)) : customerMetadataProvider, (i & 16) != 0 ? null : latencyTrackingWrapper);
    }

    public AuthenticationProvider getAuthProvider() {
        return this.authProvider;
    }

    public DeviceInfoProvider getDeviceInfoProvider() {
        return this.deviceInfoProvider;
    }

    public CustomerMetadataProvider getCustomerMetadataProvider() {
        return this.customerMetadataProvider;
    }

    private final LoggingProvider getLoggingProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(LoggingProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof LoggingProvider)) {
            interfaceProvider = null;
        }
        return (LoggingProvider) interfaceProvider;
    }

    protected ApolloClient.Builder getApolloClient(String authorizationToken, Map<String, String> httpHeaders) {
        String strJoinToString$default;
        CustomerMetadata customerMetadata;
        List<String> locales;
        List<String> locales2;
        Intrinsics.checkNotNullParameter(authorizationToken, "authorizationToken");
        ApolloClient.Builder builderAddHttpHeader = new ApolloClient.Builder().serverUrl(this.serviceEndpoint).addHttpHeader(HttpHeader.CONTENT_TYPE, "application/json");
        DeviceInfoProvider deviceInfoProvider = getDeviceInfoProvider();
        String str = (deviceInfoProvider == null || (locales2 = deviceInfoProvider.getLocales()) == null) ? null : (String) CollectionsKt___CollectionsKt.first((List) locales2);
        if (str != null) {
            builderAddHttpHeader.addHttpHeader("content-language", str);
        }
        DeviceInfoProvider deviceInfoProvider2 = getDeviceInfoProvider();
        if (deviceInfoProvider2 == null || (locales = deviceInfoProvider2.getLocales()) == null) {
            strJoinToString$default = null;
        } else {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(locales, 10));
            Iterator<T> it = locales.iterator();
            while (it.hasNext()) {
                arrayList.add(StringsKt__StringsJVMKt.replace$default((String) it.next(), Attributes.PREDEFINED_ATTRIBUTE_PREFIX, "-", false, 4, (Object) null));
            }
            strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, ";", null, null, 0, null, null, 62, null);
        }
        if (strJoinToString$default != null) {
            builderAddHttpHeader.addHttpHeader("Accept-Language", strJoinToString$default);
        }
        CustomerMetadataProvider customerMetadataProvider = getCustomerMetadataProvider();
        String sessionId = (customerMetadataProvider == null || (customerMetadata = customerMetadataProvider.getCustomerMetadata()) == null) ? null : customerMetadata.getSessionId();
        if (sessionId != null) {
            builderAddHttpHeader.addHttpHeader("x-amzn-session-id", sessionId);
        }
        DeviceInfoProvider deviceInfoProvider3 = getDeviceInfoProvider();
        String userAgent = deviceInfoProvider3 != null ? deviceInfoProvider3.getUserAgent() : null;
        if (userAgent != null) {
            builderAddHttpHeader.addHttpHeader("user-agent", userAgent);
        }
        if (httpHeaders != null) {
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                builderAddHttpHeader.addHttpHeader(entry.getKey(), entry.getValue());
            }
        }
        LoggingProvider loggingProvider = getLoggingProvider();
        if (loggingProvider != null) {
            loggingProvider.logDebug(LoggingProviderKt.getTag(this), "headers = " + httpHeaders);
        }
        return builderAddHttpHeader;
    }

    static /* synthetic */ Object query$suspendImpl(GraphQLServiceProviderImpl graphQLServiceProviderImpl, Query query, Continuation continuation) {
        return graphQLServiceProviderImpl.query(query, null, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00c6 A[Catch: all -> 0x00cf, Exception -> 0x00d6, TryCatch #2 {Exception -> 0x00d6, all -> 0x00cf, blocks: (B:13:0x002c, B:39:0x00c0, B:41:0x00c6, B:42:0x00c9, B:18:0x0047, B:27:0x0064, B:29:0x0070, B:30:0x0088, B:32:0x0095, B:33:0x0099, B:35:0x00ad, B:36:0x00b0, B:21:0x004e, B:23:0x0052, B:24:0x0055), top: B:50:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object query$suspendImpl(com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl r8, com.apollographql.apollo3.api.Query r9, java.util.Map r10, kotlin.coroutines.Continuation r11) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl.query$suspendImpl(com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl, com.apollographql.apollo3.api.Query, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String generateRequestId() {
        UUID uuidRandomUUID = UUID.randomUUID();
        Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
        String string = uuidRandomUUID.toString();
        Intrinsics.checkNotNullExpressionValue(string, "uuid4().toString()");
        return string;
    }

    static /* synthetic */ Object mutation$suspendImpl(GraphQLServiceProviderImpl graphQLServiceProviderImpl, Mutation mutation, Continuation continuation) {
        return graphQLServiceProviderImpl.mutation(mutation, null, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object mutation$suspendImpl(com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl r5, com.apollographql.apollo3.api.Mutation r6, java.util.Map r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl$mutation$2 r0 = (com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl$mutation$2 r0 = new com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl$mutation$2
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L48
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Exception -> L2c
            goto L78
        L2c:
            r5 = move-exception
            goto L80
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            java.lang.Object r5 = r0.L$2
            r7 = r5
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r5 = r0.L$1
            r6 = r5
            com.apollographql.apollo3.api.Mutation r6 = (com.apollographql.apollo3.api.Mutation) r6
            java.lang.Object r5 = r0.L$0
            com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl r5 = (com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Exception -> L2c
            goto L5a
        L48:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r5     // Catch: java.lang.Exception -> L2c
            r0.L$1 = r6     // Catch: java.lang.Exception -> L2c
            r0.L$2 = r7     // Catch: java.lang.Exception -> L2c
            r0.label = r4     // Catch: java.lang.Exception -> L2c
            java.lang.Object r8 = r5.getAccessToken(r0)     // Catch: java.lang.Exception -> L2c
            if (r8 != r1) goto L5a
            return r1
        L5a:
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Exception -> L2c
            com.apollographql.apollo3.ApolloClient$Builder r5 = r5.getApolloClient(r8, r7)     // Catch: java.lang.Exception -> L2c
            com.apollographql.apollo3.ApolloClient r5 = r5.build()     // Catch: java.lang.Exception -> L2c
            com.apollographql.apollo3.ApolloCall r5 = r5.mutation(r6)     // Catch: java.lang.Exception -> L2c
            r6 = 0
            r0.L$0 = r6     // Catch: java.lang.Exception -> L2c
            r0.L$1 = r6     // Catch: java.lang.Exception -> L2c
            r0.L$2 = r6     // Catch: java.lang.Exception -> L2c
            r0.label = r3     // Catch: java.lang.Exception -> L2c
            java.lang.Object r8 = r5.execute(r0)     // Catch: java.lang.Exception -> L2c
            if (r8 != r1) goto L78
            return r1
        L78:
            com.apollographql.apollo3.api.ApolloResponse r8 = (com.apollographql.apollo3.api.ApolloResponse) r8     // Catch: java.lang.Exception -> L2c
            com.amazon.music.platform.Outcome$Success r5 = new com.amazon.music.platform.Outcome$Success     // Catch: java.lang.Exception -> L2c
            r5.<init>(r8)     // Catch: java.lang.Exception -> L2c
            goto L86
        L80:
            com.amazon.music.platform.Outcome$Failure r6 = new com.amazon.music.platform.Outcome$Failure
            r6.<init>(r5)
            r5 = r6
        L86:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl.mutation$suspendImpl(com.amazon.music.platform.providers.impl.GraphQLServiceProviderImpl, com.apollographql.apollo3.api.Mutation, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
