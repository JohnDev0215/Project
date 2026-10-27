package com.amazon.music.platform.aom.hints;

import com.amazon.music.platform.aom.Outcome;
import com.amazon.music.platform.aom.hints.dae.DAE;
import com.amazon.music.platform.aom.hints.provider.RemoteAlexaHintsProvider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AlexaHintsRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/music/platform/aom/Outcome;", "Lcom/amazon/music/platform/aom/hints/AlexaHints;", "<anonymous>"}, k = 3, mv = {1, 7, 1})
@DebugMetadata(c = "com.amazon.music.platform.aom.hints.AlexaHintsRepository$getTemplatedHints$futureHints$1", f = "AlexaHintsRepository.kt", i = {}, l = {48}, m = "invokeSuspend", n = {}, s = {})
final class AlexaHintsRepository$getTemplatedHints$futureHints$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Outcome<? extends AlexaHints>>, Object> {
    final /* synthetic */ DAE $dae;
    int label;
    final /* synthetic */ AlexaHintsRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AlexaHintsRepository$getTemplatedHints$futureHints$1(AlexaHintsRepository alexaHintsRepository, DAE dae, Continuation<? super AlexaHintsRepository$getTemplatedHints$futureHints$1> continuation) {
        super(2, continuation);
        this.this$0 = alexaHintsRepository;
        this.$dae = dae;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AlexaHintsRepository$getTemplatedHints$futureHints$1(this.this$0, this.$dae, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public /* bridge */ /* synthetic */ Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Outcome<? extends AlexaHints>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Outcome<AlexaHints>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Outcome<AlexaHints>> continuation) {
        return ((AlexaHintsRepository$getTemplatedHints$futureHints$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RemoteAlexaHintsProvider remoteAlexaHintsProvider = this.this$0.alexaHintsProvider;
            DAE dae = this.$dae;
            this.label = 1;
            obj = remoteAlexaHintsProvider.getAlexaHints(dae, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
