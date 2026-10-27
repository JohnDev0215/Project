package com.amazon.mp3.catalog.fragment.viewmodel;

import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.library.refinement.RefinementType;
import com.amazon.mp3.library.refinement.filter.Filter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: RefinableViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "D", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$enableFilters$4$1$1$1", f = "RefinableViewModel.kt", i = {}, l = {65}, m = "invokeSuspend", n = {}, s = {})
final class RefinableViewModel$enableFilters$4$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Filter<R, ?, D> $filter;
    int label;
    final /* synthetic */ RefinableViewModel<R, D> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RefinableViewModel$enableFilters$4$1$1$1(RefinableViewModel<R, D> refinableViewModel, Filter<R, ?, D> filter, Continuation<? super RefinableViewModel$enableFilters$4$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = refinableViewModel;
        this.$filter = filter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RefinableViewModel$enableFilters$4$1$1$1(this.this$0, this.$filter, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RefinableViewModel$enableFilters$4$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RefinablePageDataRepository refinablePageDataRepository = (RefinablePageDataRepository) this.this$0.getPageDataRepository();
            RefinementType refinementType = this.$filter;
            this.label = 1;
            if (refinablePageDataRepository.addFilter(refinementType, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
