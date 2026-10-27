package com.amazon.music.share.menu;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SharingFragmentV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.music.share.menu.SharingFragmentV2$onCreateView$1$onGlobalLayout$1", f = "SharingFragmentV2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class SharingFragmentV2$onCreateView$1$onGlobalLayout$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SharingFragmentV2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SharingFragmentV2$onCreateView$1$onGlobalLayout$1(SharingFragmentV2 sharingFragmentV2, Continuation<? super SharingFragmentV2$onCreateView$1$onGlobalLayout$1> continuation) {
        super(2, continuation);
        this.this$0 = sharingFragmentV2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SharingFragmentV2$onCreateView$1$onGlobalLayout$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SharingFragmentV2$onCreateView$1$onGlobalLayout$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.showCollapsedSheet();
        return Unit.INSTANCE;
    }
}
