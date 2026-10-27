package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import androidx.paging.PageFetcherSnapshotState;
import androidx.paging.PagingSource;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: compiled from: PageFetcherSnapshot.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u0083\u0001\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0011\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\u0010\u0014J\u000e\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020\u000bJ\u001d\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010/J\u0011\u00100\u001a\u00020\u000bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010/J!\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0082@ø\u0001\u0000¢\u0006\u0002\u00106J%\u00107\u001a\b\u0012\u0004\u0012\u00028\u0000082\u0006\u00102\u001a\u0002032\b\u00109\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u0010:J\b\u0010;\u001a\u00020\u000bH\u0002J#\u0010<\u001a\u00020\u000b2\u0006\u00102\u001a\u0002032\b\u0010+\u001a\u0004\u0018\u00010,H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010=J#\u0010>\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020?0\n2\u0006\u00102\u001a\u000203H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010@J7\u0010A\u001a\u0004\u0018\u00018\u0000*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010B2\u0006\u00102\u001a\u0002032\u0006\u0010C\u001a\u00020?2\u0006\u0010D\u001a\u00020?H\u0002¢\u0006\u0002\u0010EJ1\u0010F\u001a\u00020\u000b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010B2\u0006\u00102\u001a\u0002032\u0006\u0010G\u001a\u00020HH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010IJ)\u0010J\u001a\u00020\u000b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010B2\u0006\u00102\u001a\u000203H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\f\u0010L\u001a\u00020\u000b*\u00020MH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001c0\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001c\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Landroidx/paging/PageFetcherSnapshot;", "Key", "", "Value", "initialKey", "pagingSource", "Landroidx/paging/PagingSource;", "config", "Landroidx/paging/PagingConfig;", "retryFlow", "Lkotlinx/coroutines/flow/Flow;", "", "triggerRemoteRefresh", "", "remoteMediatorConnection", "Landroidx/paging/RemoteMediatorConnection;", "previousPagingState", "Landroidx/paging/PagingState;", "invalidate", "Lkotlin/Function0;", "(Ljava/lang/Object;Landroidx/paging/PagingSource;Landroidx/paging/PagingConfig;Lkotlinx/coroutines/flow/Flow;ZLandroidx/paging/RemoteMediatorConnection;Landroidx/paging/PagingState;Lkotlin/jvm/functions/Function0;)V", "hintHandler", "Landroidx/paging/HintHandler;", "getInitialKey$paging_common", "()Ljava/lang/Object;", "Ljava/lang/Object;", "pageEventCh", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/paging/PageEvent;", "pageEventChCollected", "Ljava/util/concurrent/atomic/AtomicBoolean;", "pageEventChannelFlowJob", "Lkotlinx/coroutines/CompletableJob;", "pageEventFlow", "getPageEventFlow", "()Lkotlinx/coroutines/flow/Flow;", "getPagingSource$paging_common", "()Landroidx/paging/PagingSource;", "getRemoteMediatorConnection", "()Landroidx/paging/RemoteMediatorConnection;", "stateHolder", "Landroidx/paging/PageFetcherSnapshotState$Holder;", "accessHint", "viewportHint", "Landroidx/paging/ViewportHint;", "close", "currentPagingState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doInitialLoad", "doLoad", "loadType", "Landroidx/paging/LoadType;", "generationalHint", "Landroidx/paging/GenerationalViewportHint;", "(Landroidx/paging/LoadType;Landroidx/paging/GenerationalViewportHint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadParams", "Landroidx/paging/PagingSource$LoadParams;", "key", "(Landroidx/paging/LoadType;Ljava/lang/Object;)Landroidx/paging/PagingSource$LoadParams;", "onInvalidLoad", "retryLoadError", "(Landroidx/paging/LoadType;Landroidx/paging/ViewportHint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectAsGenerationalViewportHints", "", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/LoadType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nextLoadKeyOrNull", "Landroidx/paging/PageFetcherSnapshotState;", "generationId", "presentedItemsBeyondAnchor", "(Landroidx/paging/PageFetcherSnapshotState;Landroidx/paging/LoadType;II)Ljava/lang/Object;", "setError", "error", "Landroidx/paging/LoadState$Error;", "(Landroidx/paging/PageFetcherSnapshotState;Landroidx/paging/LoadType;Landroidx/paging/LoadState$Error;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setLoading", "(Landroidx/paging/PageFetcherSnapshotState;Landroidx/paging/LoadType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startConsumingHints", "Lkotlinx/coroutines/CoroutineScope;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PageFetcherSnapshot<Key, Value> {
    private final PagingConfig config;
    private final HintHandler hintHandler;
    private final Key initialKey;
    private final Function0<Unit> invalidate;
    private final Channel<PageEvent<Value>> pageEventCh;
    private final AtomicBoolean pageEventChCollected;
    private final CompletableJob pageEventChannelFlowJob;
    private final Flow<PageEvent<Value>> pageEventFlow;
    private final PagingSource<Key, Value> pagingSource;
    private final PagingState<Key, Value> previousPagingState;
    private final RemoteMediatorConnection<Key, Value> remoteMediatorConnection;
    private final Flow<Unit> retryFlow;
    private final PageFetcherSnapshotState.Holder<Key, Value> stateHolder;
    private final boolean triggerRemoteRefresh;

    /* JADX INFO: compiled from: PageFetcherSnapshot.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.APPEND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcherSnapshot$currentPagingState$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PageFetcherSnapshot.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot", f = "PageFetcherSnapshot.kt", i = {0, 0, 0}, l = {608}, m = "currentPagingState", n = {"this", "this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1", "L$2"})
    static final class C02291 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02291(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super C02291> continuation) {
            super(continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.currentPagingState(this);
        }
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcherSnapshot$doInitialLoad$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PageFetcherSnapshot.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot", f = "PageFetcherSnapshot.kt", i = {0, 0, 0, 1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8}, l = {608, 280, 283, 619, 630, 317, 641, 652, 341}, m = "doInitialLoad", n = {"this", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "$this$withLock_u24default$iv$iv", "this", "this", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "result", "$this$withLock_u24default$iv$iv", "this", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0"})
    static final class C02301 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02301(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super C02301> continuation) {
            super(continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.doInitialLoad(this);
        }
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcherSnapshot$doLoad$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PageFetcherSnapshot.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot", f = "PageFetcherSnapshot.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, l = {609, 620, 398, 406, 631, 642, 448, 653, 470, 496, 664}, m = "doLoad", n = {"this", "loadType", "generationalHint", "itemsLoaded", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "loadType", "generationalHint", "$this$withLock_u24default$iv$iv", "state", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "dropType", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "$this$withLock_u24default$iv$iv", "state", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "this_$iv", "$this$withLock_u24default$iv$iv", "endsPrepend", "endsAppend"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "I$1"})
    static final class C02311 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02311(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super C02311> continuation) {
            super(continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.doLoad(null, null, this);
        }
    }

    public PageFetcherSnapshot(Key key, PagingSource<Key, Value> pagingSource, PagingConfig config, Flow<Unit> retryFlow, boolean z, RemoteMediatorConnection<Key, Value> remoteMediatorConnection, PagingState<Key, Value> pagingState, Function0<Unit> invalidate) {
        Intrinsics.checkNotNullParameter(pagingSource, "pagingSource");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(retryFlow, "retryFlow");
        Intrinsics.checkNotNullParameter(invalidate, "invalidate");
        this.initialKey = key;
        this.pagingSource = pagingSource;
        this.config = config;
        this.retryFlow = retryFlow;
        this.triggerRemoteRefresh = z;
        this.remoteMediatorConnection = remoteMediatorConnection;
        this.previousPagingState = pagingState;
        this.invalidate = invalidate;
        if (!(config.jumpThreshold == Integer.MIN_VALUE || pagingSource.getJumpingSupported())) {
            throw new IllegalArgumentException("PagingConfig.jumpThreshold was set, but the associated PagingSource has not marked support for jumps by overriding PagingSource.jumpingSupported to true.".toString());
        }
        this.hintHandler = new HintHandler();
        this.pageEventChCollected = new AtomicBoolean(false);
        this.pageEventCh = ChannelKt.Channel$default(-2, null, null, 6, null);
        this.stateHolder = new PageFetcherSnapshotState.Holder<>(config);
        CompletableJob completableJobJob$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        this.pageEventChannelFlowJob = completableJobJob$default;
        this.pageEventFlow = FlowKt.onStart(CancelableChannelFlowKt.cancelableChannelFlow(completableJobJob$default, new PageFetcherSnapshot$pageEventFlow$1(this, null)), new PageFetcherSnapshot$pageEventFlow$2(this, null));
    }

    public final Key getInitialKey$paging_common() {
        return this.initialKey;
    }

    public final PagingSource<Key, Value> getPagingSource$paging_common() {
        return this.pagingSource;
    }

    public final RemoteMediatorConnection<Key, Value> getRemoteMediatorConnection() {
        return this.remoteMediatorConnection;
    }

    public /* synthetic */ PageFetcherSnapshot(Object obj, PagingSource pagingSource, PagingConfig pagingConfig, Flow flow, boolean z, RemoteMediatorConnection remoteMediatorConnection, PagingState pagingState, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, pagingSource, pagingConfig, flow, (i & 16) != 0 ? false : z, (i & 32) != 0 ? null : remoteMediatorConnection, (i & 64) != 0 ? null : pagingState, (i & 128) != 0 ? new Function0<Unit>() { // from class: androidx.paging.PageFetcherSnapshot.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        } : function0);
    }

    public final Flow<PageEvent<Value>> getPageEventFlow() {
        return this.pageEventFlow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object collectAsGenerationalViewportHints(Flow<Integer> flow, final LoadType loadType, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.conflate(FlowExtKt.simpleRunningReduce(FlowExtKt.simpleTransformLatest(flow, new PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1(null, this, loadType)), new AnonymousClass3(loadType, null))).collect(new FlowCollector<GenerationalViewportHint>() { // from class: androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$collect$1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public Object emit(GenerationalViewportHint generationalViewportHint, Continuation<? super Unit> continuation2) throws Throwable {
                Object objDoLoad = this.this$0.doLoad(loadType, generationalViewportHint, continuation2);
                return objDoLoad == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDoLoad : Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object retryLoadError(LoadType loadType, ViewportHint viewportHint, Continuation<? super Unit> continuation) throws Throwable {
        if (WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()] == 1) {
            Object objDoInitialLoad = doInitialLoad(continuation);
            return objDoInitialLoad == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDoInitialLoad : Unit.INSTANCE;
        }
        if (!(viewportHint != null)) {
            throw new IllegalStateException("Cannot retry APPEND / PREPEND load on PagingSource without ViewportHint".toString());
        }
        this.hintHandler.forceSetHint(loadType, viewportHint);
        return Unit.INSTANCE;
    }

    public final void accessHint(ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        this.hintHandler.processHint(viewportHint);
    }

    public final void close() {
        Job.DefaultImpls.cancel$default((Job) this.pageEventChannelFlowJob, (CancellationException) null, 1, (Object) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object currentPagingState(kotlin.coroutines.Continuation<? super androidx.paging.PagingState<Key, Value>> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.paging.PageFetcherSnapshot.C02291
            if (r0 == 0) goto L13
            r0 = r6
            androidx.paging.PageFetcherSnapshot$currentPagingState$1 r0 = (androidx.paging.PageFetcherSnapshot.C02291) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.PageFetcherSnapshot$currentPagingState$1 r0 = new androidx.paging.PageFetcherSnapshot$currentPagingState$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L3e
            if (r2 != r4) goto L36
            java.lang.Object r1 = r0.L$2
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r2 = (androidx.paging.PageFetcherSnapshotState.Holder) r2
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcherSnapshot r0 = (androidx.paging.PageFetcherSnapshot) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L58
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r2 = r5.stateHolder
            kotlinx.coroutines.sync.Mutex r6 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r2)
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r6
            r0.label = r4
            java.lang.Object r0 = r6.lock(r3, r0)
            if (r0 != r1) goto L56
            return r1
        L56:
            r0 = r5
            r1 = r6
        L58:
            androidx.paging.PageFetcherSnapshotState r6 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r2)     // Catch: java.lang.Throwable -> L6a
            androidx.paging.HintHandler r0 = r0.hintHandler     // Catch: java.lang.Throwable -> L6a
            androidx.paging.ViewportHint$Access r0 = r0.getLastAccessHint()     // Catch: java.lang.Throwable -> L6a
            androidx.paging.PagingState r6 = r6.currentPagingState$paging_common(r0)     // Catch: java.lang.Throwable -> L6a
            r1.unlock(r3)
            return r6
        L6a:
            r6 = move-exception
            r1.unlock(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.currentPagingState(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startConsumingHints(CoroutineScope coroutineScope) {
        if (this.config.jumpThreshold != Integer.MIN_VALUE) {
            Iterator it = CollectionsKt__CollectionsKt.listOf((Object[]) new LoadType[]{LoadType.APPEND, LoadType.PREPEND}).iterator();
            while (it.hasNext()) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new PageFetcherSnapshot$startConsumingHints$1$1(this, (LoadType) it.next(), null), 3, null);
            }
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C02323(this, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcherSnapshot$startConsumingHints$2, reason: invalid class name */
    /* JADX INFO: compiled from: PageFetcherSnapshot.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u00020\u0003H\u008a@"}, d2 = {"", "Key", "Value", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$startConsumingHints$2", f = "PageFetcherSnapshot.kt", i = {0, 0}, l = {608, 229}, m = "invokeSuspend", n = {"this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            PageFetcherSnapshot<Key, Value> pageFetcherSnapshot;
            PageFetcherSnapshotState.Holder holder;
            Mutex mutex;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    pageFetcherSnapshot = this.this$0;
                    holder = ((PageFetcherSnapshot) pageFetcherSnapshot).stateHolder;
                    Mutex mutex2 = holder.lock;
                    this.L$0 = holder;
                    this.L$1 = mutex2;
                    this.L$2 = pageFetcherSnapshot;
                    this.label = 1;
                    if (mutex2.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex = mutex2;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    pageFetcherSnapshot = (PageFetcherSnapshot) this.L$2;
                    mutex = (Mutex) this.L$1;
                    holder = (PageFetcherSnapshotState.Holder) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Flow<Integer> flowConsumePrependGenerationIdAsFlow = holder.state.consumePrependGenerationIdAsFlow();
                mutex.unlock(null);
                LoadType loadType = LoadType.PREPEND;
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 2;
                if (pageFetcherSnapshot.collectAsGenerationalViewportHints(flowConsumePrependGenerationIdAsFlow, loadType, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                mutex.unlock(null);
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcherSnapshot$startConsumingHints$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PageFetcherSnapshot.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u00020\u0003H\u008a@"}, d2 = {"", "Key", "Value", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$startConsumingHints$3", f = "PageFetcherSnapshot.kt", i = {0, 0}, l = {608, 234}, m = "invokeSuspend", n = {"this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1"})
    static final class C02323 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02323(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super C02323> continuation) {
            super(2, continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02323(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02323) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            PageFetcherSnapshot<Key, Value> pageFetcherSnapshot;
            PageFetcherSnapshotState.Holder holder;
            Mutex mutex;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    pageFetcherSnapshot = this.this$0;
                    holder = ((PageFetcherSnapshot) pageFetcherSnapshot).stateHolder;
                    Mutex mutex2 = holder.lock;
                    this.L$0 = holder;
                    this.L$1 = mutex2;
                    this.L$2 = pageFetcherSnapshot;
                    this.label = 1;
                    if (mutex2.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex = mutex2;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    pageFetcherSnapshot = (PageFetcherSnapshot) this.L$2;
                    mutex = (Mutex) this.L$1;
                    holder = (PageFetcherSnapshotState.Holder) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Flow<Integer> flowConsumeAppendGenerationIdAsFlow = holder.state.consumeAppendGenerationIdAsFlow();
                mutex.unlock(null);
                LoadType loadType = LoadType.APPEND;
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 2;
                if (pageFetcherSnapshot.collectAsGenerationalViewportHints(flowConsumeAppendGenerationIdAsFlow, loadType, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                mutex.unlock(null);
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$3, reason: invalid class name */
    /* JADX INFO: compiled from: PageFetcherSnapshot.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"", "Key", "Value", "Landroidx/paging/GenerationalViewportHint;", "previous", "next", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$3", f = "PageFetcherSnapshot.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function3<GenerationalViewportHint, GenerationalViewportHint, Continuation<? super GenerationalViewportHint>, Object> {
        final /* synthetic */ LoadType $loadType;
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(LoadType loadType, Continuation<? super AnonymousClass3> continuation) {
            super(3, continuation);
            this.$loadType = loadType;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(GenerationalViewportHint generationalViewportHint, GenerationalViewportHint generationalViewportHint2, Continuation<? super GenerationalViewportHint> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$loadType, continuation);
            anonymousClass3.L$0 = generationalViewportHint;
            anonymousClass3.L$1 = generationalViewportHint2;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            GenerationalViewportHint generationalViewportHint = (GenerationalViewportHint) this.L$0;
            GenerationalViewportHint generationalViewportHint2 = (GenerationalViewportHint) this.L$1;
            return PageFetcherSnapshotKt.shouldPrioritizeOver(generationalViewportHint2, generationalViewportHint, this.$loadType) ? generationalViewportHint2 : generationalViewportHint;
        }
    }

    private final PagingSource.LoadParams<Key> loadParams(LoadType loadType, Key key) {
        return PagingSource.LoadParams.INSTANCE.create(loadType, key, loadType == LoadType.REFRESH ? this.config.initialLoadSize : this.config.pageSize, this.config.enablePlaceholders);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x027c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0161 A[Catch: all -> 0x0237, TryCatch #5 {all -> 0x0237, blocks: (B:51:0x013d, B:53:0x0161, B:54:0x016e, B:56:0x0177), top: B:127:0x013d }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0177 A[Catch: all -> 0x0237, TRY_LEAVE, TryCatch #5 {all -> 0x0237, blocks: (B:51:0x013d, B:53:0x0161, B:54:0x016e, B:56:0x0177), top: B:127:0x013d }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01c2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x023c  */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [androidx.paging.PageFetcherSnapshot] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r2v30, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r2v42 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r2v46 */
    /* JADX WARN: Type inference failed for: r2v9, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v15, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v11, types: [androidx.paging.PageFetcherSnapshot] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doInitialLoad(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 692
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.doInitialLoad(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x0351, code lost:
    
        r0 = r8;
        r8 = r12;
        r12 = r14;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:206:0x05f8, B:209:0x0603], limit reached: 272 */
    /* JADX WARN: Path cross not found for [B:212:0x0608, B:215:0x0612], limit reached: 272 */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x04c8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0528 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0546 A[Catch: all -> 0x068b, TRY_LEAVE, TryCatch #8 {all -> 0x068b, blocks: (B:174:0x0534, B:177:0x0546, B:181:0x0564), top: B:271:0x0534 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0595 A[Catch: all -> 0x00bc, TryCatch #9 {all -> 0x00bc, blocks: (B:186:0x057e, B:188:0x0595, B:190:0x05a1, B:192:0x05a9, B:194:0x05b6, B:193:0x05b0, B:195:0x05b9, B:199:0x05db, B:203:0x05ee, B:185:0x0576, B:14:0x0086, B:17:0x00b7), top: B:263:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x05a9 A[Catch: all -> 0x00bc, TryCatch #9 {all -> 0x00bc, blocks: (B:186:0x057e, B:188:0x0595, B:190:0x05a1, B:192:0x05a9, B:194:0x05b6, B:193:0x05b0, B:195:0x05b9, B:199:0x05db, B:203:0x05ee, B:185:0x0576, B:14:0x0086, B:17:0x00b7), top: B:263:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x05b0 A[Catch: all -> 0x00bc, TryCatch #9 {all -> 0x00bc, blocks: (B:186:0x057e, B:188:0x0595, B:190:0x05a1, B:192:0x05a9, B:194:0x05b6, B:193:0x05b0, B:195:0x05b9, B:199:0x05db, B:203:0x05ee, B:185:0x0576, B:14:0x0086, B:17:0x00b7), top: B:263:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x05e3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x05e4  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x065f  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x067e  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x069e A[Catch: all -> 0x06a4, TRY_ENTER, TryCatch #0 {all -> 0x06a4, blocks: (B:47:0x0222, B:72:0x02d3, B:54:0x0239, B:56:0x024a, B:57:0x0256, B:59:0x0260, B:63:0x027e, B:65:0x0297, B:68:0x02b5, B:249:0x069e, B:250:0x06a3), top: B:256:0x0222 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0325 A[Catch: all -> 0x0696, TRY_LEAVE, TryCatch #3 {all -> 0x0696, blocks: (B:77:0x030a, B:80:0x0325), top: B:261:0x030a }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0387  */
    /* JADX WARN: Type inference failed for: r0v70 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v23, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v25, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v34 */
    /* JADX WARN: Type inference failed for: r10v35 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v32 */
    /* JADX WARN: Type inference failed for: r12v33 */
    /* JADX WARN: Type inference failed for: r12v34 */
    /* JADX WARN: Type inference failed for: r12v42, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v44, types: [androidx.paging.PageFetcherSnapshot] */
    /* JADX WARN: Type inference failed for: r12v47 */
    /* JADX WARN: Type inference failed for: r12v50 */
    /* JADX WARN: Type inference failed for: r12v51 */
    /* JADX WARN: Type inference failed for: r14v22, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v43 */
    /* JADX WARN: Type inference failed for: r15v17 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v7 */
    /* JADX WARN: Type inference failed for: r1v19, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15, types: [T] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v21, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v50 */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v6, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r5v0, types: [int] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r5v40, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r5v44, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v46 */
    /* JADX WARN: Type inference failed for: r5v49 */
    /* JADX WARN: Type inference failed for: r5v51 */
    /* JADX WARN: Type inference failed for: r5v83 */
    /* JADX WARN: Type inference failed for: r5v84 */
    /* JADX WARN: Type inference failed for: r5v85 */
    /* JADX WARN: Type inference failed for: r5v86 */
    /* JADX WARN: Type inference failed for: r5v87 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /* JADX WARN: Type inference failed for: r9v44, types: [androidx.paging.PageFetcherSnapshot] */
    /* JADX WARN: Type inference failed for: r9v51, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v54 */
    /* JADX WARN: Type inference failed for: r9v55, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v58 */
    /* JADX WARN: Type inference failed for: r9v62 */
    /* JADX WARN: Type inference failed for: r9v63 */
    /* JADX WARN: Type inference failed for: r9v64 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:223:0x0645 -> B:267:0x064b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object doLoad(androidx.paging.LoadType r18, androidx.paging.GenerationalViewportHint r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1744
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.doLoad(androidx.paging.LoadType, androidx.paging.GenerationalViewportHint, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object setLoading(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, Continuation<? super Unit> continuation) {
        LoadState loadState = pageFetcherSnapshotState.getSourceLoadStates().get(loadType);
        LoadState.Loading loading = LoadState.Loading.INSTANCE;
        if (!Intrinsics.areEqual(loadState, loading)) {
            pageFetcherSnapshotState.getSourceLoadStates().set(loadType, loading);
            Object objSend = this.pageEventCh.send(new PageEvent.LoadStateUpdate(pageFetcherSnapshotState.getSourceLoadStates().snapshot(), null), continuation);
            return objSend == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object setError(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, LoadState.Error error, Continuation<? super Unit> continuation) {
        if (!Intrinsics.areEqual(pageFetcherSnapshotState.getSourceLoadStates().get(loadType), error)) {
            pageFetcherSnapshotState.getSourceLoadStates().set(loadType, error);
            Object objSend = this.pageEventCh.send(new PageEvent.LoadStateUpdate(pageFetcherSnapshotState.getSourceLoadStates().snapshot(), null), continuation);
            return objSend == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    private final Key nextLoadKeyOrNull(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, int i, int i2) {
        if (i != pageFetcherSnapshotState.generationId$paging_common(loadType) || (pageFetcherSnapshotState.getSourceLoadStates().get(loadType) instanceof LoadState.Error) || i2 >= this.config.prefetchDistance) {
            return null;
        }
        if (loadType == LoadType.PREPEND) {
            return (Key) ((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.first((List) pageFetcherSnapshotState.getPages$paging_common())).getPrevKey();
        }
        return (Key) ((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.last((List) pageFetcherSnapshotState.getPages$paging_common())).getNextKey();
    }

    private final void onInvalidLoad() {
        close();
        this.pagingSource.invalidate();
    }
}
