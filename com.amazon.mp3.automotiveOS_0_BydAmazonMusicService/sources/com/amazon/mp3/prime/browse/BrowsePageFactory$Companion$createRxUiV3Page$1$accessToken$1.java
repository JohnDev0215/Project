package com.amazon.mp3.prime.browse;

import com.amazon.music.platform.providers.ProfileProvider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BrowsePageFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.prime.browse.BrowsePageFactory$Companion$createRxUiV3Page$1$accessToken$1", f = "BrowsePageFactory.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
final class BrowsePageFactory$Companion$createRxUiV3Page$1$accessToken$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    int label;

    BrowsePageFactory$Companion$createRxUiV3Page$1$accessToken$1(Continuation<? super BrowsePageFactory$Companion$createRxUiV3Page$1$accessToken$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowsePageFactory$Companion$createRxUiV3Page$1$accessToken$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((BrowsePageFactory$Companion$createRxUiV3Page$1$accessToken$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProfileProvider profileProvider = BrowsePageFactory.profileProvider;
            if (profileProvider == null) {
                return null;
            }
            this.label = 1;
            obj = profileProvider.getAccessToken(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return (String) obj;
    }
}
