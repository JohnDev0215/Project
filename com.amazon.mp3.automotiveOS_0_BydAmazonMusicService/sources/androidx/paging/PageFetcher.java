package androidx.paging;

import androidx.annotation.VisibleForTesting;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: PageFetcher.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0002&'B\\\u0012(\u0010\u0004\u001a$\b\u0001\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\fø\u0001\u0000¢\u0006\u0002\u0010\rJ3\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00072\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u0019H\u0002J\u0006\u0010\u001e\u001a\u00020\u0019JB\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010 0\u000f*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010!2\u0006\u0010\"\u001a\u00020#2\u0014\u0010$\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010%H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\b\u001a\u0004\u0018\u00018\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R5\u0010\u0004\u001a$\b\u0001\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0016X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"Landroidx/paging/PageFetcher;", "Key", "", "Value", "pagingSourceFactory", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Landroidx/paging/PagingSource;", "initialKey", "config", "Landroidx/paging/PagingConfig;", "remoteMediator", "Landroidx/paging/RemoteMediator;", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Landroidx/paging/PagingConfig;Landroidx/paging/RemoteMediator;)V", "flow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "Ljava/lang/Object;", "Lkotlin/jvm/functions/Function1;", "refreshEvents", "Landroidx/paging/ConflatedEventBus;", "", "retryEvents", "", "generateNewPagingSource", "previousPagingSource", "(Landroidx/paging/PagingSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invalidate", "refresh", "injectRemoteEvents", "Landroidx/paging/PageEvent;", "Landroidx/paging/PageFetcherSnapshot;", "job", "Lkotlinx/coroutines/Job;", "accessor", "Landroidx/paging/RemoteMediatorAccessor;", "GenerationInfo", "PagerUiReceiver", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PageFetcher<Key, Value> {
    private final PagingConfig config;
    private final Flow<PagingData<Value>> flow;
    private final Key initialKey;
    private final Function1<Continuation<? super PagingSource<Key, Value>>, Object> pagingSourceFactory;
    private final ConflatedEventBus<Boolean> refreshEvents;
    private final ConflatedEventBus<Unit> retryEvents;

    /* JADX INFO: renamed from: androidx.paging.PageFetcher$generateNewPagingSource$1, reason: invalid class name */
    /* JADX INFO: compiled from: PageFetcher.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.PageFetcher", f = "PageFetcher.kt", i = {0, 0}, l = {188}, m = "generateNewPagingSource", n = {"this", "previousPagingSource"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PageFetcher<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PageFetcher<Key, Value> pageFetcher, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = pageFetcher;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.generateNewPagingSource(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PageFetcher(Function1<? super Continuation<? super PagingSource<Key, Value>>, ? extends Object> pagingSourceFactory, Key key, PagingConfig config, RemoteMediator<Key, Value> remoteMediator) {
        Intrinsics.checkNotNullParameter(pagingSourceFactory, "pagingSourceFactory");
        Intrinsics.checkNotNullParameter(config, "config");
        this.pagingSourceFactory = pagingSourceFactory;
        this.initialKey = key;
        this.config = config;
        this.refreshEvents = new ConflatedEventBus<>(null, 1, null);
        this.retryEvents = new ConflatedEventBus<>(null, 1, null);
        this.flow = SimpleChannelFlowKt.simpleChannelFlow(new PageFetcher$flow$1(remoteMediator, this, null));
    }

    public /* synthetic */ PageFetcher(Function1 function1, Object obj, PagingConfig pagingConfig, RemoteMediator remoteMediator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, obj, pagingConfig, (i & 8) != 0 ? null : remoteMediator);
    }

    public final Flow<PagingData<Value>> getFlow() {
        return this.flow;
    }

    public final void refresh() {
        this.refreshEvents.send(Boolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidate() {
        this.refreshEvents.send(Boolean.FALSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Flow<PageEvent<Value>> injectRemoteEvents(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Job job, RemoteMediatorAccessor<Key, Value> remoteMediatorAccessor) {
        if (remoteMediatorAccessor == null) {
            return pageFetcherSnapshot.getPageEventFlow();
        }
        return CancelableChannelFlowKt.cancelableChannelFlow(job, new C02281(remoteMediatorAccessor, pageFetcherSnapshot, new MutableLoadStateCollection(), null));
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcher$injectRemoteEvents$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PageFetcher.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00040\u0003H\u008a@"}, d2 = {"", "Key", "Value", "Landroidx/paging/SimpleProducerScope;", "Landroidx/paging/PageEvent;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PageFetcher$injectRemoteEvents$1", f = "PageFetcher.kt", i = {}, l = {233}, m = "invokeSuspend", n = {}, s = {})
    static final class C02281 extends SuspendLambda implements Function2<SimpleProducerScope<PageEvent<Value>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ RemoteMediatorAccessor<Key, Value> $accessor;
        final /* synthetic */ MutableLoadStateCollection $sourceStates;
        final /* synthetic */ PageFetcherSnapshot<Key, Value> $this_injectRemoteEvents;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02281(RemoteMediatorAccessor<Key, Value> remoteMediatorAccessor, PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, MutableLoadStateCollection mutableLoadStateCollection, Continuation<? super C02281> continuation) {
            super(2, continuation);
            this.$accessor = remoteMediatorAccessor;
            this.$this_injectRemoteEvents = pageFetcherSnapshot;
            this.$sourceStates = mutableLoadStateCollection;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02281 c02281 = new C02281(this.$accessor, this.$this_injectRemoteEvents, this.$sourceStates, continuation);
            c02281.L$0 = obj;
            return c02281;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(SimpleProducerScope<PageEvent<Value>> simpleProducerScope, Continuation<? super Unit> continuation) {
            return ((C02281) create(simpleProducerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final SimpleProducerScope simpleProducerScope = (SimpleProducerScope) this.L$0;
                Flow flowSimpleChannelFlow = SimpleChannelFlowKt.simpleChannelFlow(new PageFetcher$injectRemoteEvents$1$invokeSuspend$$inlined$combineWithoutBatching$1(this.$accessor.getState(), this.$this_injectRemoteEvents.getPageEventFlow(), null, this.$sourceStates));
                FlowCollector<PageEvent<Value>> flowCollector = new FlowCollector<PageEvent<Value>>() { // from class: androidx.paging.PageFetcher$injectRemoteEvents$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(PageEvent<Value> pageEvent, Continuation<? super Unit> continuation) {
                        Object objSend = simpleProducerScope.send(pageEvent, continuation);
                        return objSend == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flowSimpleChannelFlow.collect(flowCollector, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object generateNewPagingSource(androidx.paging.PagingSource<Key, Value> r5, kotlin.coroutines.Continuation<? super androidx.paging.PagingSource<Key, Value>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.paging.PageFetcher.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.paging.PageFetcher$generateNewPagingSource$1 r0 = (androidx.paging.PageFetcher.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.PageFetcher$generateNewPagingSource$1 r0 = new androidx.paging.PageFetcher$generateNewPagingSource$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            androidx.paging.PagingSource r5 = (androidx.paging.PagingSource) r5
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcher r0 = (androidx.paging.PageFetcher) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4c
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super androidx.paging.PagingSource<Key, Value>>, java.lang.Object> r6 = r4.pagingSourceFactory
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.invoke(r0)
            if (r6 != r1) goto L4b
            return r1
        L4b:
            r0 = r4
        L4c:
            androidx.paging.PagingSource r6 = (androidx.paging.PagingSource) r6
            boolean r1 = r6 instanceof androidx.paging.LegacyPagingSource
            if (r1 == 0) goto L5c
            r1 = r6
            androidx.paging.LegacyPagingSource r1 = (androidx.paging.LegacyPagingSource) r1
            androidx.paging.PagingConfig r2 = r0.config
            int r2 = r2.pageSize
            r1.setPageSize(r2)
        L5c:
            if (r6 == r5) goto L5f
            goto L60
        L5f:
            r3 = 0
        L60:
            if (r3 == 0) goto L7c
            androidx.paging.PageFetcher$generateNewPagingSource$3 r1 = new androidx.paging.PageFetcher$generateNewPagingSource$3
            r1.<init>(r0)
            r6.registerInvalidatedCallback(r1)
            if (r5 != 0) goto L6d
            goto L75
        L6d:
            androidx.paging.PageFetcher$generateNewPagingSource$4 r1 = new androidx.paging.PageFetcher$generateNewPagingSource$4
            r1.<init>(r0)
            r5.unregisterInvalidatedCallback(r1)
        L75:
            if (r5 != 0) goto L78
            goto L7b
        L78:
            r5.invalidate()
        L7b:
            return r6
        L7c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "An instance of PagingSource was re-used when Pager expected to create a new\ninstance. Ensure that the pagingSourceFactory passed to Pager always returns a\nnew instance of PagingSource."
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcher.generateNewPagingSource(androidx.paging.PagingSource, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcher$generateNewPagingSource$3, reason: invalid class name */
    /* JADX INFO: compiled from: PageFetcher.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass3(Object obj) {
            super(0, obj, PageFetcher.class, "invalidate", "invalidate()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((PageFetcher) this.receiver).invalidate();
        }
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcher$generateNewPagingSource$4, reason: invalid class name */
    /* JADX INFO: compiled from: PageFetcher.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass4(Object obj) {
            super(0, obj, PageFetcher.class, "invalidate", "invalidate()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((PageFetcher) this.receiver).invalidate();
        }
    }

    /* JADX INFO: compiled from: PageFetcher.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0004B)\u0012\u0014\b\u0001\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\tH\u0016J\b\u0010\u0011\u001a\u00020\tH\u0016R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/paging/PageFetcher$PagerUiReceiver;", "Key", "", "Value", "Landroidx/paging/UiReceiver;", "pageFetcherSnapshot", "Landroidx/paging/PageFetcherSnapshot;", "retryEventBus", "Landroidx/paging/ConflatedEventBus;", "", "(Landroidx/paging/PageFetcher;Landroidx/paging/PageFetcherSnapshot;Landroidx/paging/ConflatedEventBus;)V", "getPageFetcherSnapshot$paging_common", "()Landroidx/paging/PageFetcherSnapshot;", "accessHint", "viewportHint", "Landroidx/paging/ViewportHint;", "refresh", "retry", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class PagerUiReceiver<Key, Value> implements UiReceiver {
        private final PageFetcherSnapshot<Key, Value> pageFetcherSnapshot;
        private final ConflatedEventBus<Unit> retryEventBus;
        final /* synthetic */ PageFetcher<Key, Value> this$0;

        public PagerUiReceiver(@VisibleForTesting(otherwise = 2) PageFetcher this$0, PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, ConflatedEventBus<Unit> retryEventBus) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(pageFetcherSnapshot, "pageFetcherSnapshot");
            Intrinsics.checkNotNullParameter(retryEventBus, "retryEventBus");
            this.this$0 = this$0;
            this.pageFetcherSnapshot = pageFetcherSnapshot;
            this.retryEventBus = retryEventBus;
        }

        public final PageFetcherSnapshot<Key, Value> getPageFetcherSnapshot$paging_common() {
            return this.pageFetcherSnapshot;
        }

        @Override // androidx.paging.UiReceiver
        public void accessHint(ViewportHint viewportHint) {
            Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
            this.pageFetcherSnapshot.accessHint(viewportHint);
        }

        @Override // androidx.paging.UiReceiver
        public void retry() {
            this.retryEventBus.send(Unit.INSTANCE);
        }

        @Override // androidx.paging.UiReceiver
        public void refresh() {
            this.this$0.refresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: PageFetcher.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0002B7\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/paging/PageFetcher$GenerationInfo;", "Key", "", "Value", "snapshot", "Landroidx/paging/PageFetcherSnapshot;", "state", "Landroidx/paging/PagingState;", "job", "Lkotlinx/coroutines/Job;", "(Landroidx/paging/PageFetcherSnapshot;Landroidx/paging/PagingState;Lkotlinx/coroutines/Job;)V", "getJob", "()Lkotlinx/coroutines/Job;", "getSnapshot", "()Landroidx/paging/PageFetcherSnapshot;", "getState", "()Landroidx/paging/PagingState;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    static final class GenerationInfo<Key, Value> {
        private final Job job;
        private final PageFetcherSnapshot<Key, Value> snapshot;
        private final PagingState<Key, Value> state;

        public GenerationInfo(PageFetcherSnapshot<Key, Value> snapshot, PagingState<Key, Value> pagingState, Job job) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            Intrinsics.checkNotNullParameter(job, "job");
            this.snapshot = snapshot;
            this.state = pagingState;
            this.job = job;
        }

        public final PageFetcherSnapshot<Key, Value> getSnapshot() {
            return this.snapshot;
        }

        public final PagingState<Key, Value> getState() {
            return this.state;
        }

        public final Job getJob() {
            return this.job;
        }
    }
}
