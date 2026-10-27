package com.amazon.mp3.prime.browse;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.brush.RxUiV3Page;
import com.amazon.mp3.util.Log;
import com.amazon.music.page.api.AuthenticationProvider;
import com.amazon.music.page.api.MusicLandingPageApiGatewayClient;
import com.android.volley.Cache;
import com.android.volley.CacheStateManager;
import com.android.volley.toolbox.Volley;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: BrowsePageFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.prime.browse.BrowsePageFactory$Companion$createRxUiV3Page$1", f = "BrowsePageFactory.kt", i = {}, l = {33}, m = "invokeSuspend", n = {}, s = {})
final class BrowsePageFactory$Companion$createRxUiV3Page$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ CompletableFuture<RxUiV3Page> $pageRequestFuture;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BrowsePageFactory$Companion$createRxUiV3Page$1(Context context, CompletableFuture<RxUiV3Page> completableFuture, Continuation<? super BrowsePageFactory$Companion$createRxUiV3Page$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$pageRequestFuture = completableFuture;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowsePageFactory$Companion$createRxUiV3Page$1(this.$context, this.$pageRequestFuture, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowsePageFactory$Companion$createRxUiV3Page$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            BrowsePageFactory$Companion$createRxUiV3Page$1$accessToken$1 browsePageFactory$Companion$createRxUiV3Page$1$accessToken$1 = new BrowsePageFactory$Companion$createRxUiV3Page$1$accessToken$1(null);
            this.label = 1;
            obj = BuildersKt.withContext(io2, browsePageFactory$Companion$createRxUiV3Page$1$accessToken$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        final String str = (String) obj;
        final Context context = this.$context;
        AuthenticationProvider authenticationProvider = new AuthenticationProvider(context, str) { // from class: com.amazon.mp3.prime.browse.BrowsePageFactory$Companion$createRxUiV3Page$1$authenticationProvider$1
            final /* synthetic */ String $accessToken;
            final /* synthetic */ Context $context;
            private final AccountCredentialStorage accountCredentialStorage;
            private final AccountDetailUtil accountDetailUtil;

            {
                this.$context = context;
                this.$accessToken = str;
                this.accountCredentialStorage = AccountCredentialStorage.get(context);
                this.accountDetailUtil = AccountDetailUtil.get(context);
            }

            @Override // com.amazon.music.page.api.AuthenticationProvider
            /* JADX INFO: renamed from: getToken, reason: from getter */
            public String get$accessToken() {
                return this.$accessToken;
            }

            @Override // com.amazon.music.page.api.AuthenticationProvider
            public String getDeviceType() {
                String deviceType = this.accountCredentialStorage.getDeviceType();
                Intrinsics.checkNotNullExpressionValue(deviceType, "accountCredentialStorage.deviceType");
                return deviceType;
            }

            @Override // com.amazon.music.page.api.AuthenticationProvider
            public String getDeviceId() {
                String deviceId = this.accountCredentialStorage.getDeviceId();
                Intrinsics.checkNotNullExpressionValue(deviceId, "accountCredentialStorage.deviceId");
                return deviceId;
            }

            @Override // com.amazon.music.page.api.AuthenticationProvider
            public String getCustomerId() {
                String customerId = this.accountDetailUtil.getCustomerId();
                Intrinsics.checkNotNullExpressionValue(customerId, "accountDetailUtil.customerId");
                return customerId;
            }
        };
        try {
            Cache cache = Volley.newRequestQueue(this.$context).getCache();
            this.$pageRequestFuture.complete(new RxUiV3Page(new MusicLandingPageApiGatewayClient(authenticationProvider, cache, new CacheStateManager(cache)), this.$context));
        } catch (Exception e) {
            Log.error("Unable to get Page response for MusicLandingPageApiGatewayClient {}", e.toString());
            this.$pageRequestFuture.complete(null);
        }
        return Unit.INSTANCE;
    }
}
