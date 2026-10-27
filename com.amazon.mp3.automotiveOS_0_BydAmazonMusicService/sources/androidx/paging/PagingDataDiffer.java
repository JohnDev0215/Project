package androidx.paging;

import androidx.annotation.IntRange;
import androidx.annotation.RestrictTo;
import androidx.paging.PagePresenter;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: PagingDataDiffer.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u008d\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000*\u0001 \b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010'\u001a\u00020\n2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n0)J\u0014\u0010*\u001a\u00020\n2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0\u001cJ\u001f\u0010+\u001a\u00020\n2\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010.J\u001f\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000101H\u0000¢\u0006\u0002\b3J\u001a\u00104\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u00105\u001a\u00020\u0010H\u0086\u0002¢\u0006\u0002\u00106J\u0017\u00107\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u00105\u001a\u00020\u0010¢\u0006\u0002\u00106J\b\u00108\u001a\u00020\u0012H\u0016JE\u00109\u001a\u0004\u0018\u00010\u00102\f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00000;2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\n0\u001cH¦@ø\u0001\u0000¢\u0006\u0002\u0010>J\u0006\u0010?\u001a\u00020\nJ\u001a\u0010@\u001a\u00020\n2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n0)J\u0014\u0010A\u001a\u00020\n2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0\u001cJ\u0006\u0010B\u001a\u00020\nJ\f\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000DR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00148F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006E"}, d2 = {"Landroidx/paging/PagingDataDiffer;", "T", "", "differCallback", "Landroidx/paging/DifferCallback;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Landroidx/paging/DifferCallback;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_onPagesUpdatedFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "collectFromRunner", "Landroidx/paging/SingleRunner;", "combinedLoadStatesCollection", "Landroidx/paging/MutableCombinedLoadStateCollection;", "lastAccessedIndex", "", "lastAccessedIndexUnfulfilled", "", "loadStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/CombinedLoadStates;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "onPagesUpdatedFlow", "getOnPagesUpdatedFlow", "onPagesUpdatedListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function0;", "presenter", "Landroidx/paging/PagePresenter;", "processPageEventCallback", "androidx/paging/PagingDataDiffer$processPageEventCallback$1", "Landroidx/paging/PagingDataDiffer$processPageEventCallback$1;", "receiver", "Landroidx/paging/UiReceiver;", "size", "getSize", "()I", "addLoadStateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "addOnPagesUpdatedListener", "collectFrom", "pagingData", "Landroidx/paging/PagingData;", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchLoadStates", PageUriUtils.SOURCE_QUERY_PARAM, "Landroidx/paging/LoadStates;", "mediator", "dispatchLoadStates$paging_common", "get", "index", "(I)Ljava/lang/Object;", "peek", "postEvents", "presentNewList", "previousList", "Landroidx/paging/NullPaddedList;", "newList", "onListPresentable", "(Landroidx/paging/NullPaddedList;Landroidx/paging/NullPaddedList;ILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refresh", "removeLoadStateListener", "removeOnPagesUpdatedListener", "retry", "snapshot", "Landroidx/paging/ItemSnapshotList;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class PagingDataDiffer<T> {
    private final MutableSharedFlow<Unit> _onPagesUpdatedFlow;
    private final SingleRunner collectFromRunner;
    private final MutableCombinedLoadStateCollection combinedLoadStatesCollection;
    private final DifferCallback differCallback;
    private volatile int lastAccessedIndex;
    private volatile boolean lastAccessedIndexUnfulfilled;
    private final Flow<CombinedLoadStates> loadStateFlow;
    private final CoroutineDispatcher mainDispatcher;
    private final CopyOnWriteArrayList<Function0<Unit>> onPagesUpdatedListeners;
    private PagePresenter<T> presenter;
    private final PagingDataDiffer$processPageEventCallback$1 processPageEventCallback;
    private UiReceiver receiver;

    public boolean postEvents() {
        return false;
    }

    public abstract Object presentNewList(NullPaddedList<T> nullPaddedList, NullPaddedList<T> nullPaddedList2, int i, Function0<Unit> function0, Continuation<? super Integer> continuation);

    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.paging.PagingDataDiffer$processPageEventCallback$1] */
    public PagingDataDiffer(DifferCallback differCallback, CoroutineDispatcher mainDispatcher) {
        Intrinsics.checkNotNullParameter(differCallback, "differCallback");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
        this.differCallback = differCallback;
        this.mainDispatcher = mainDispatcher;
        this.presenter = PagePresenter.INSTANCE.initial$paging_common();
        MutableCombinedLoadStateCollection mutableCombinedLoadStateCollection = new MutableCombinedLoadStateCollection();
        this.combinedLoadStatesCollection = mutableCombinedLoadStateCollection;
        this.onPagesUpdatedListeners = new CopyOnWriteArrayList<>();
        this.collectFromRunner = new SingleRunner(false, 1, null);
        this.processPageEventCallback = new PagePresenter.ProcessPageEventCallback(this) { // from class: androidx.paging.PagingDataDiffer$processPageEventCallback$1
            final /* synthetic */ PagingDataDiffer<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onChanged(int position, int count) {
                ((PagingDataDiffer) this.this$0).differCallback.onChanged(position, count);
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onInserted(int position, int count) {
                ((PagingDataDiffer) this.this$0).differCallback.onInserted(position, count);
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onRemoved(int position, int count) {
                ((PagingDataDiffer) this.this$0).differCallback.onRemoved(position, count);
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onStateUpdate(LoadStates source, LoadStates mediator) {
                Intrinsics.checkNotNullParameter(source, "source");
                this.this$0.dispatchLoadStates$paging_common(source, mediator);
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onStateUpdate(LoadType loadType, boolean fromMediator, LoadState loadState) {
                Intrinsics.checkNotNullParameter(loadType, "loadType");
                Intrinsics.checkNotNullParameter(loadState, "loadState");
                if (Intrinsics.areEqual(((PagingDataDiffer) this.this$0).combinedLoadStatesCollection.get(loadType, fromMediator), loadState)) {
                    return;
                }
                ((PagingDataDiffer) this.this$0).combinedLoadStatesCollection.set(loadType, fromMediator, loadState);
            }
        };
        this.loadStateFlow = mutableCombinedLoadStateCollection.getFlow();
        this._onPagesUpdatedFlow = SharedFlowKt.MutableSharedFlow(0, 64, BufferOverflow.DROP_OLDEST);
        addOnPagesUpdatedListener(new Function0<Unit>(this) { // from class: androidx.paging.PagingDataDiffer.1
            final /* synthetic */ PagingDataDiffer<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ((PagingDataDiffer) this.this$0)._onPagesUpdatedFlow.tryEmit(Unit.INSTANCE);
            }
        });
    }

    public /* synthetic */ PagingDataDiffer(DifferCallback differCallback, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(differCallback, (i & 2) != 0 ? Dispatchers.getMain() : coroutineDispatcher);
    }

    public final void dispatchLoadStates$paging_common(LoadStates source, LoadStates mediator) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (Intrinsics.areEqual(this.combinedLoadStatesCollection.getSource(), source) && Intrinsics.areEqual(this.combinedLoadStatesCollection.getMediator(), mediator)) {
            return;
        }
        this.combinedLoadStatesCollection.set(source, mediator);
    }

    /* JADX INFO: renamed from: androidx.paging.PagingDataDiffer$collectFrom$2, reason: invalid class name */
    /* JADX INFO: compiled from: PagingDataDiffer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000H\u008a@"}, d2 = {"", "T", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PagingDataDiffer$collectFrom$2", f = "PagingDataDiffer.kt", i = {}, l = {467}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ PagingData<T> $pagingData;
        int label;
        final /* synthetic */ PagingDataDiffer<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PagingDataDiffer<T> pagingDataDiffer, PagingData<T> pagingData, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.this$0 = pagingDataDiffer;
            this.$pagingData = pagingData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$pagingData, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ((PagingDataDiffer) this.this$0).receiver = this.$pagingData.getReceiver();
                Flow<PageEvent<T>> flow$paging_common = this.$pagingData.getFlow$paging_common();
                final PagingDataDiffer<T> pagingDataDiffer = this.this$0;
                FlowCollector<PageEvent<T>> flowCollector = new FlowCollector<PageEvent<T>>() { // from class: androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(PageEvent<T> pageEvent, Continuation<? super Unit> continuation) throws Throwable {
                        Object objWithContext = BuildersKt.withContext(pagingDataDiffer.mainDispatcher, new PagingDataDiffer$collectFrom$2$1$1(pageEvent, pagingDataDiffer, null), continuation);
                        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flow$paging_common.collect(flowCollector, this) == coroutine_suspended) {
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

    public final Object collectFrom(PagingData<T> pagingData, Continuation<? super Unit> continuation) {
        Object objRunInIsolation$default = SingleRunner.runInIsolation$default(this.collectFromRunner, 0, new AnonymousClass2(this, pagingData, null), continuation, 1, null);
        return objRunInIsolation$default == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRunInIsolation$default : Unit.INSTANCE;
    }

    public final T get(@IntRange(from = 0) int index) {
        this.lastAccessedIndexUnfulfilled = true;
        this.lastAccessedIndex = index;
        UiReceiver uiReceiver = this.receiver;
        if (uiReceiver != null) {
            uiReceiver.accessHint(this.presenter.accessHintForPresenterIndex(index));
        }
        return this.presenter.get(index);
    }

    public final T peek(@IntRange(from = 0) int index) {
        return this.presenter.get(index);
    }

    public final ItemSnapshotList<T> snapshot() {
        return this.presenter.snapshot();
    }

    public final void retry() {
        UiReceiver uiReceiver = this.receiver;
        if (uiReceiver == null) {
            return;
        }
        uiReceiver.retry();
    }

    public final void refresh() {
        UiReceiver uiReceiver = this.receiver;
        if (uiReceiver == null) {
            return;
        }
        uiReceiver.refresh();
    }

    public final int getSize() {
        return this.presenter.getSize();
    }

    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.loadStateFlow;
    }

    public final Flow<Unit> getOnPagesUpdatedFlow() {
        return FlowKt.asSharedFlow(this._onPagesUpdatedFlow);
    }

    public final void addOnPagesUpdatedListener(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onPagesUpdatedListeners.add(listener);
    }

    public final void removeOnPagesUpdatedListener(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onPagesUpdatedListeners.remove(listener);
    }

    public final void addLoadStateListener(Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.combinedLoadStatesCollection.addListener(listener);
    }

    public final void removeLoadStateListener(Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.combinedLoadStatesCollection.removeListener(listener);
    }
}
