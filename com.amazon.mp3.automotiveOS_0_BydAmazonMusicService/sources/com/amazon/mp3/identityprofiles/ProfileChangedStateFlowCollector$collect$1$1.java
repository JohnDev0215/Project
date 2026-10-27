package com.amazon.mp3.identityprofiles;

import android.content.Context;
import com.amazon.mp3.featuregate.PCAEvaluator;
import com.amazon.mp3.library.service.sync.SyncService;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: ProfileChangedStateFlowCollector.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.identityprofiles.ProfileChangedStateFlowCollector$collect$1$1", f = "ProfileChangedStateFlowCollector.kt", i = {}, l = {24}, m = "invokeSuspend", n = {}, s = {})
final class ProfileChangedStateFlowCollector$collect$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;
    final /* synthetic */ ProfileChangedStateFlowCollector this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ProfileChangedStateFlowCollector$collect$1$1(ProfileChangedStateFlowCollector profileChangedStateFlowCollector, Context context, Continuation<? super ProfileChangedStateFlowCollector$collect$1$1> continuation) {
        super(2, continuation);
        this.this$0 = profileChangedStateFlowCollector;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileChangedStateFlowCollector$collect$1$1(this.this$0, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ProfileChangedStateFlowCollector$collect$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow stateFlow = this.this$0.profileChangedStateFlow;
            if (stateFlow != null) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$context);
                this.label = 1;
                if (stateFlow.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                return Unit.INSTANCE;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.identityprofiles.ProfileChangedStateFlowCollector$collect$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProfileChangedStateFlowCollector.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "musicProfile", "", "emit", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        final /* synthetic */ Context $context;

        AnonymousClass1(Context context) {
            this.$context = context;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            return emit((String) obj, (Continuation<? super Unit>) continuation);
        }

        public final Object emit(String str, Continuation<? super Unit> continuation) {
            if (PCAEvaluator.isEnabled$default(new PCAEvaluator(this.$context), false, 1, null)) {
                SyncService.startSync(this.$context, 13987);
            }
            return Unit.INSTANCE;
        }
    }
}
