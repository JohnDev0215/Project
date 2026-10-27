package com.amazon.mp3.catalog.fragment.datasource.repository;

import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.RefinableDataSource;
import com.amazon.mp3.library.refinement.filter.Filter;
import com.amazon.mp3.library.refinement.sort.Sort;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinablePageDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u0002H\u0001\u0012\u0002\b\u00030\u00042\u00020\u0005B\u0019\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0002\u0010\bJ3\u0010\t\u001a\u00020\n\"\u0018\b\u0002\u0010\u000b*\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010\f2\u0006\u0010\r\u001a\u0002H\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\nJ\u0011\u0010\u0010\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u0004\u0018\u00018\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J3\u0010\u0013\u001a\u00020\n\"\u0018\b\u0002\u0010\u000b*\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010\f2\u0006\u0010\r\u001a\u0002H\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u0014\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J/\u0010\u0015\u001a\u00020\n\"\u0014\b\u0002\u0010\u0016*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00172\u0006\u0010\u0018\u001a\u0002H\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "R", "Lcom/amazon/mp3/library/refinement/Refinable;", "D", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "modelDao", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/RefinablePageViewModelDao;", "(Lcom/amazon/mp3/catalog/fragment/datasource/dao/RefinablePageViewModelDao;)V", "addFilter", "", "F", "Lcom/amazon/mp3/library/refinement/filter/Filter;", PageUriUtils.FILTER_QUERY_PARAM, "(Lcom/amazon/mp3/library/refinement/filter/Filter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearListeners", "clearSort", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDataSource", "removeFilter", "reset", "setSort", "S", "Lcom/amazon/mp3/library/refinement/sort/Sort;", "sort", "(Lcom/amazon/mp3/library/refinement/sort/Sort;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldUseOfflineSource", "useOfflineSource", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinablePageDataRepository<R extends Refinable, D extends RefinableDataSource<R, ?>> extends PageDataRepository {

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository$getDataSource$1, reason: invalid class name */
    /* JADX INFO: compiled from: RefinablePageDataRepository.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository", f = "RefinablePageDataRepository.kt", i = {}, l = {60}, m = "getDataSource", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageDataRepository<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RefinablePageDataRepository<R, D> refinablePageDataRepository, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = refinablePageDataRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.getDataSource(this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository$setSort$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinablePageDataRepository.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository", f = "RefinablePageDataRepository.kt", i = {0, 0}, l = {39, 40}, m = "setSort", n = {"this", "sort"}, s = {"L$0", "L$1"})
    static final class C03241<S extends Sort<R, D>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinablePageDataRepository<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03241(RefinablePageDataRepository<R, D> refinablePageDataRepository, Continuation<? super C03241> continuation) {
            super(continuation);
            this.this$0 = refinablePageDataRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.setSort(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefinablePageDataRepository(RefinablePageViewModelDao<R, D> modelDao) {
        super(modelDao);
        Intrinsics.checkNotNullParameter(modelDao, "modelDao");
    }

    public final <F extends Filter<R, ?, D>> Object addFilter(F f, Continuation<? super Unit> continuation) {
        PageViewModelDao modelDao = getModelDao();
        RefinablePageViewModelDao refinablePageViewModelDao = modelDao instanceof RefinablePageViewModelDao ? (RefinablePageViewModelDao) modelDao : null;
        if (refinablePageViewModelDao != null) {
            Object objAddFilter = refinablePageViewModelDao.addFilter(f, continuation);
            return objAddFilter == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAddFilter : Unit.INSTANCE;
        }
        if (IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    public final <F extends Filter<R, ?, D>> Object removeFilter(F f, Continuation<? super Unit> continuation) {
        PageViewModelDao modelDao = getModelDao();
        RefinablePageViewModelDao refinablePageViewModelDao = modelDao instanceof RefinablePageViewModelDao ? (RefinablePageViewModelDao) modelDao : null;
        if (refinablePageViewModelDao != null) {
            Object objRemoveFilter = refinablePageViewModelDao.removeFilter(f, continuation);
            return objRemoveFilter == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemoveFilter : Unit.INSTANCE;
        }
        if (IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <S extends com.amazon.mp3.library.refinement.sort.Sort<R, D>> java.lang.Object setSort(S r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository.C03241
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository$setSort$1 r0 = (com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository.C03241) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository$setSort$1 r0 = new com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository$setSort$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r7)
            goto L70
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            java.lang.Object r6 = r0.L$1
            com.amazon.mp3.library.refinement.sort.Sort r6 = (com.amazon.mp3.library.refinement.sort.Sort) r6
            java.lang.Object r2 = r0.L$0
            com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository r2 = (com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L51
        L40:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.clearSort(r0)
            if (r7 != r1) goto L50
            return r1
        L50:
            r2 = r5
        L51:
            com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao r7 = r2.getModelDao()
            boolean r2 = r7 instanceof com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
            r4 = 0
            if (r2 == 0) goto L5d
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao r7 = (com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao) r7
            goto L5e
        L5d:
            r7 = r4
        L5e:
            if (r7 != 0) goto L63
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L63:
            r0.L$0 = r4
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r6 = r7.addSort(r6, r0)
            if (r6 != r1) goto L70
            return r1
        L70:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository.setSort(com.amazon.mp3.library.refinement.sort.Sort, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object clearSort(Continuation<? super Unit> continuation) {
        PageViewModelDao modelDao = getModelDao();
        RefinablePageViewModelDao refinablePageViewModelDao = modelDao instanceof RefinablePageViewModelDao ? (RefinablePageViewModelDao) modelDao : null;
        if (refinablePageViewModelDao != null) {
            Object objClearSort = refinablePageViewModelDao.clearSort(continuation);
            return objClearSort == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objClearSort : Unit.INSTANCE;
        }
        if (IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    public final Object reset(Continuation<? super Unit> continuation) {
        PageViewModelDao modelDao = getModelDao();
        RefinablePageViewModelDao refinablePageViewModelDao = modelDao instanceof RefinablePageViewModelDao ? (RefinablePageViewModelDao) modelDao : null;
        if (refinablePageViewModelDao != null) {
            refinablePageViewModelDao.setUseOnlineSource(true);
        }
        PageViewModelDao modelDao2 = getModelDao();
        RefinablePageViewModelDao refinablePageViewModelDao2 = modelDao2 instanceof RefinablePageViewModelDao ? (RefinablePageViewModelDao) modelDao2 : null;
        if (refinablePageViewModelDao2 != null) {
            Object objReset = refinablePageViewModelDao2.reset(continuation);
            return objReset == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objReset : Unit.INSTANCE;
        }
        if (IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getDataSource(kotlin.coroutines.Continuation<? super D> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository$getDataSource$1 r0 = (com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository$getDataSource$1 r0 = new com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository$getDataSource$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L54
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao r6 = r5.getModelDao()
            boolean r2 = r6 instanceof com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
            if (r2 == 0) goto L40
            com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao r6 = (com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao) r6
            goto L41
        L40:
            r6 = r4
        L41:
            if (r6 != 0) goto L44
            goto L57
        L44:
            kotlinx.coroutines.Deferred r6 = r6.getDataSource()
            if (r6 != 0) goto L4b
            goto L57
        L4b:
            r0.label = r3
            java.lang.Object r6 = r6.await(r0)
            if (r6 != r1) goto L54
            return r1
        L54:
            r4 = r6
            com.amazon.mp3.library.refinement.datasource.RefinableDataSource r4 = (com.amazon.mp3.library.refinement.datasource.RefinableDataSource) r4
        L57:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository.getDataSource(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void shouldUseOfflineSource(boolean useOfflineSource) {
        PageViewModelDao modelDao = getModelDao();
        RefinablePageViewModelDao refinablePageViewModelDao = modelDao instanceof RefinablePageViewModelDao ? (RefinablePageViewModelDao) modelDao : null;
        if (refinablePageViewModelDao == null) {
            return;
        }
        refinablePageViewModelDao.setUseOnlineSource(!useOfflineSource);
    }

    public final void clearListeners() {
        PageViewModelDao modelDao = getModelDao();
        RefinablePageViewModelDao refinablePageViewModelDao = modelDao instanceof RefinablePageViewModelDao ? (RefinablePageViewModelDao) modelDao : null;
        if (refinablePageViewModelDao == null) {
            return;
        }
        refinablePageViewModelDao.clearListeners();
    }
}
