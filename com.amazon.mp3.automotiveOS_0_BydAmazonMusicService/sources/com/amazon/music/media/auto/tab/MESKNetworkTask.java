package com.amazon.music.media.auto.tab;

import CoreInterface.v1_0.Response;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.media.auto.provider.network.EndpointConfig;
import com.amazon.music.media.auto.provider.network.MESKRequestProvider;
import com.amazon.music.media.auto.util.Mappers;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: MESKNetworkTask.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/media/auto/tab/MESKNetworkTask;", "", "meskRequestProvider", "Lcom/amazon/music/media/auto/provider/network/MESKRequestProvider;", "okHttpClient", "Lokhttp3/OkHttpClient;", "tabType", "Lcom/amazon/music/media/auto/tab/TabType;", "(Lcom/amazon/music/media/auto/provider/network/MESKRequestProvider;Lokhttp3/OkHttpClient;Lcom/amazon/music/media/auto/tab/TabType;)V", "isFetchInProgress", "", ImagesContract.URL, "", "execute", "LCoreInterface/v1_0/Response;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isClientErrorCode", "code", "", "isServerErrorCode", "parseResponse", "response", "Lokhttp3/Response;", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MESKNetworkTask {
    private static final String TAG = MESKNetworkTask.class.getSimpleName();
    private boolean isFetchInProgress;
    private final MESKRequestProvider meskRequestProvider;
    private final OkHttpClient okHttpClient;
    private final TabType tabType;
    private final String url;

    /* JADX INFO: compiled from: MESKNetworkTask.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TabType.values().length];
            iArr[TabType.HOME.ordinal()] = 1;
            iArr[TabType.LIBRARY.ordinal()] = 2;
            iArr[TabType.PODCAST.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.tab.MESKNetworkTask$execute$1, reason: invalid class name */
    /* JADX INFO: compiled from: MESKNetworkTask.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.media.auto.tab.MESKNetworkTask", f = "MESKNetworkTask.kt", i = {0, 0, 0}, l = {91}, m = "execute", n = {"this", "generatedRequestId", "maxRetries"}, s = {"L$0", "L$1", "I$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MESKNetworkTask.this.execute(this);
        }
    }

    private final boolean isClientErrorCode(int code) {
        return 400 <= code && code < 500;
    }

    private final boolean isServerErrorCode(int code) {
        return 500 <= code && code < 600;
    }

    public MESKNetworkTask(MESKRequestProvider meskRequestProvider, OkHttpClient okHttpClient, TabType tabType) {
        String homeTabEndpoint;
        Intrinsics.checkNotNullParameter(meskRequestProvider, "meskRequestProvider");
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Intrinsics.checkNotNullParameter(tabType, "tabType");
        this.meskRequestProvider = meskRequestProvider;
        this.okHttpClient = okHttpClient;
        this.tabType = tabType;
        int i = WhenMappings.$EnumSwitchMapping$0[tabType.ordinal()];
        if (i == 1) {
            EndpointConfig endpointConfig = EndpointConfig.INSTANCE;
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            ConfigurationProvider configurationProvider = (ConfigurationProvider) (interfaceProvider instanceof ConfigurationProvider ? interfaceProvider : null);
            homeTabEndpoint = endpointConfig.getHomeTabEndpoint(configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null);
        } else if (i == 2) {
            EndpointConfig endpointConfig2 = EndpointConfig.INSTANCE;
            AtomicMap<String, InterfaceProvider> providerInstances2 = Providers.INSTANCE.getProviderInstances();
            String originalClassName2 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
            Intrinsics.checkNotNull(originalClassName2);
            InterfaceProvider interfaceProvider2 = providerInstances2.get(originalClassName2);
            ConfigurationProvider configurationProvider2 = (ConfigurationProvider) (interfaceProvider2 instanceof ConfigurationProvider ? interfaceProvider2 : null);
            homeTabEndpoint = endpointConfig2.getLibraryEndpoint(configurationProvider2 instanceof Configuration ? (Configuration) configurationProvider2 : null);
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            EndpointConfig endpointConfig3 = EndpointConfig.INSTANCE;
            AtomicMap<String, InterfaceProvider> providerInstances3 = Providers.INSTANCE.getProviderInstances();
            String originalClassName3 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
            Intrinsics.checkNotNull(originalClassName3);
            InterfaceProvider interfaceProvider3 = providerInstances3.get(originalClassName3);
            ConfigurationProvider configurationProvider3 = (ConfigurationProvider) (interfaceProvider3 instanceof ConfigurationProvider ? interfaceProvider3 : null);
            homeTabEndpoint = endpointConfig3.getPodcastEndpoint(configurationProvider3 instanceof Configuration ? (Configuration) configurationProvider3 : null);
        }
        this.url = homeTabEndpoint;
    }

    public /* synthetic */ MESKNetworkTask(MESKRequestProvider mESKRequestProvider, OkHttpClient okHttpClient, TabType tabType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mESKRequestProvider, (i & 2) != 0 ? UnifiedOkHttpClient.newClient() : okHttpClient, tabType);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object execute(kotlin.coroutines.Continuation<? super CoreInterface.v1_0.Response> r17) {
        /*
            Method dump skipped, instruction units count: 595
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.auto.tab.MESKNetworkTask.execute(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.tab.MESKNetworkTask$execute$2, reason: invalid class name */
    /* JADX INFO: compiled from: MESKNetworkTask.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.tab.MESKNetworkTask$execute$2", f = "MESKNetworkTask.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws InterruptedException {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Thread.sleep(1000L);
            return Unit.INSTANCE;
        }
    }

    private final Response parseResponse(okhttp3.Response response) throws JsonProcessingException {
        String strString;
        ResponseBody responseBodyBody = response.body();
        String str = "";
        if (responseBodyBody != null && (strString = responseBodyBody.string()) != null) {
            str = strString;
        }
        Object value = Mappers.INSTANCE.mapper().readValue(str, (Class<Object>) Response.class);
        Intrinsics.checkNotNullExpressionValue(value, "Mappers.mapper().readVal…1_0.Response::class.java)");
        return (Response) value;
    }
}
