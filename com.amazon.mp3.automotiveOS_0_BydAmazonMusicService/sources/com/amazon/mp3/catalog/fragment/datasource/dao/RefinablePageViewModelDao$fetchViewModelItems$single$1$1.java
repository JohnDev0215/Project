package com.amazon.mp3.catalog.fragment.datasource.dao;

import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.visualon.OSMPUtils.voOSType;
import io.reactivex.SingleEmitter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: RefinablePageViewModelDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "D", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$fetchViewModelItems$single$1$1", f = "RefinablePageViewModelDao.kt", i = {}, l = {voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_LOCATION}, m = "invokeSuspend", n = {}, s = {})
final class RefinablePageViewModelDao$fetchViewModelItems$single$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FetchType $fetchType;
    final /* synthetic */ SingleEmitter<PageGridViewModel> $it;
    final /* synthetic */ int $pageIndex;
    final /* synthetic */ int $pageSize;
    int label;
    final /* synthetic */ RefinablePageViewModelDao<R, D> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RefinablePageViewModelDao$fetchViewModelItems$single$1$1(RefinablePageViewModelDao<R, D> refinablePageViewModelDao, int i, int i2, FetchType fetchType, SingleEmitter<PageGridViewModel> singleEmitter, Continuation<? super RefinablePageViewModelDao$fetchViewModelItems$single$1$1> continuation) {
        super(2, continuation);
        this.this$0 = refinablePageViewModelDao;
        this.$pageSize = i;
        this.$pageIndex = i2;
        this.$fetchType = fetchType;
        this.$it = singleEmitter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RefinablePageViewModelDao$fetchViewModelItems$single$1$1(this.this$0, this.$pageSize, this.$pageIndex, this.$fetchType, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RefinablePageViewModelDao$fetchViewModelItems$single$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009f  */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            Method dump skipped, instruction units count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao$fetchViewModelItems$single$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
