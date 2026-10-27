package androidx.compose.runtime;

import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.tooling.CompositionData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: Recomposer.kt */
/* JADX INFO: loaded from: classes.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 \u0086\u00012\u00020\u0001:\b\u0086\u0001\u0087\u0001\u0088\u0001\u0089\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020GH\u0002J\u0006\u0010H\u001a\u00020IJ\u0011\u0010J\u001a\u00020DH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0011\u0010L\u001a\u00020DH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0006\u0010M\u001a\u00020DJ\u0006\u0010N\u001a\u00020DJ*\u0010O\u001a\u00020D2\u0006\u0010P\u001a\u00020\u00172\u0011\u0010Q\u001a\r\u0012\u0004\u0012\u00020D0R¢\u0006\u0002\bSH\u0010¢\u0006\u0004\bT\u0010UJ:\u0010V\u001a\u0002HW\"\u0004\b\u0000\u0010W2\u0006\u0010P\u001a\u00020\u00172\u000e\u0010X\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010Y2\f\u0010Z\u001a\b\u0012\u0004\u0012\u0002HW0RH\u0082\b¢\u0006\u0002\u0010[J\u0010\u0010\\\u001a\n\u0012\u0004\u0012\u00020D\u0018\u00010CH\u0002J\u0015\u0010]\u001a\u00020D2\u0006\u0010P\u001a\u00020\u0017H\u0010¢\u0006\u0002\b^J\u0015\u0010_\u001a\u00020D2\u0006\u0010`\u001a\u00020aH\u0010¢\u0006\u0002\bbJ\u0011\u0010c\u001a\u00020DH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ\"\u0010d\u001a\u0004\u0018\u00010\u00172\u0006\u0010P\u001a\u00020\u00172\u000e\u0010X\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010YH\u0002J\u001c\u0010e\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020D0f2\u0006\u0010P\u001a\u00020\u0017H\u0002JO\u0010g\u001a\u00020D2<\u0010Z\u001a8\b\u0001\u0012\u0004\u0012\u00020i\u0012\u0013\u0012\u00110j¢\u0006\f\bk\u0012\b\bl\u0012\u0004\b\b(m\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0n\u0012\u0006\u0012\u0004\u0018\u00010:0h¢\u0006\u0002\boH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010pJ\b\u0010q\u001a\u00020DH\u0002J\u001d\u0010q\u001a\u00020D2\u0012\u0010r\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020D0fH\u0082\bJ\u001b\u0010s\u001a\u00020D2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020v0uH\u0010¢\u0006\u0002\bwJ\u0015\u0010x\u001a\u00020D2\u0006\u0010P\u001a\u00020\u0017H\u0010¢\u0006\u0002\byJ\u0010\u0010z\u001a\u00020D2\u0006\u0010{\u001a\u000205H\u0002J!\u0010|\u001a\u00020D2\u0006\u0010m\u001a\u00020j2\u0006\u0010}\u001a\u00020~H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u007fJ\u0012\u0010\u0080\u0001\u001a\u00020DH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u001b\u0010\u0081\u0001\u001a\u00020D2\u0006\u00100\u001a\u00020\u0003H\u0087@ø\u0001\u0000¢\u0006\u0003\u0010\u0082\u0001J\u0017\u0010\u0083\u0001\u001a\u00020D2\u0006\u0010P\u001a\u00020\u0017H\u0010¢\u0006\u0003\b\u0084\u0001J-\u0010\u0085\u0001\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020D0f2\u0006\u0010P\u001a\u00020\u00172\u000e\u0010X\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010YH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001a8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010\u0002\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0014R\u0014\u0010(\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u0014R\u0011\u0010*\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b+\u0010\u0014R\u0014\u0010,\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\u0014R\u000e\u0010.\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\u00020\u00038PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b1\u0010#R\u0012\u00102\u001a\u000603R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u0014R\u001a\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020:090\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010;\u001a\b\u0012\u0004\u0012\u00020\u00070<8FX\u0087\u0004¢\u0006\f\u0012\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u000e\u0010A\u001a\u00020:X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010B\u001a\n\u0012\u0004\u0012\u00020D\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u008a\u0001"}, d2 = {"Landroidx/compose/runtime/Recomposer;", "Landroidx/compose/runtime/CompositionContext;", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/runtime/Recomposer$State;", "broadcastFrameClock", "Landroidx/compose/runtime/BroadcastFrameClock;", "<set-?>", "", "changeCount", "getChangeCount", "()J", "closeCause", "", "collectingParameterInformation", "", "getCollectingParameterInformation$runtime_release", "()Z", "compositionInvalidations", "", "Landroidx/compose/runtime/ControlledComposition;", "compositionsAwaitingApply", "compoundHashKey", "", "getCompoundHashKey$runtime_release", "()I", "concurrentCompositionsOutstanding", "currentState", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentState", "()Lkotlinx/coroutines/flow/StateFlow;", "getEffectCoroutineContext$runtime_release", "()Lkotlin/coroutines/CoroutineContext;", "effectJob", "Lkotlinx/coroutines/CompletableJob;", "hasConcurrentFrameWorkLocked", "getHasConcurrentFrameWorkLocked", "hasFrameWorkLocked", "getHasFrameWorkLocked", "hasPendingWork", "getHasPendingWork", "hasSchedulingWork", "getHasSchedulingWork", "isClosed", "knownCompositions", "recomposeCoroutineContext", "getRecomposeCoroutineContext$runtime_release", "recomposerInfo", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "runnerJob", "Lkotlinx/coroutines/Job;", "shouldKeepRecomposing", "getShouldKeepRecomposing", "snapshotInvalidations", "", "", "state", "Lkotlinx/coroutines/flow/Flow;", "getState$annotations", "()V", "getState", "()Lkotlinx/coroutines/flow/Flow;", "stateLock", "workContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "applyAndCheck", "snapshot", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "asRecomposerInfo", "Landroidx/compose/runtime/RecomposerInfo;", "awaitIdle", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitWorkAvailable", "cancel", "close", "composeInitial", "composition", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "composing", "T", "modifiedValues", "Landroidx/compose/runtime/collection/IdentityArraySet;", "block", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/collection/IdentityArraySet;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "deriveStateLocked", "invalidate", "invalidate$runtime_release", "invalidateScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "invalidateScope$runtime_release", "join", "performRecompose", "readObserverOf", "Lkotlin/Function1;", "recompositionRunner", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/compose/runtime/MonotonicFrameClock;", "Lkotlin/ParameterName;", "name", "parentFrameClock", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordComposerModificationsLocked", "onEachInvalidComposition", "recordInspectionTable", "table", "", "Landroidx/compose/runtime/tooling/CompositionData;", "recordInspectionTable$runtime_release", "registerComposition", "registerComposition$runtime_release", "registerRunnerJob", "callingJob", "runFrameLoop", "frameSignal", "Landroidx/compose/runtime/ProduceFrameSignal;", "(Landroidx/compose/runtime/MonotonicFrameClock;Landroidx/compose/runtime/ProduceFrameSignal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runRecomposeAndApplyChanges", "runRecomposeConcurrentlyAndApplyChanges", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregisterComposition", "unregisterComposition$runtime_release", "writeObserverOf", "Companion", "HotReloadable", "RecomposerInfoImpl", "State", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Recomposer extends CompositionContext {
    private final MutableStateFlow<State> _state;
    private final BroadcastFrameClock broadcastFrameClock;
    private long changeCount;
    private Throwable closeCause;
    private final List<ControlledComposition> compositionInvalidations;
    private final List<ControlledComposition> compositionsAwaitingApply;
    private int concurrentCompositionsOutstanding;
    private final CoroutineContext effectCoroutineContext;
    private final CompletableJob effectJob;
    private boolean isClosed;
    private final List<ControlledComposition> knownCompositions;
    private final RecomposerInfoImpl recomposerInfo;
    private Job runnerJob;
    private final List<Set<Object>> snapshotInvalidations;
    private final Object stateLock;
    private CancellableContinuation<? super Unit> workContinuation;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final MutableStateFlow<PersistentSet<RecomposerInfoImpl>> _runningRecomposers = StateFlowKt.MutableStateFlow(ExtensionsKt.persistentSetOf());

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/Recomposer$State;", "", "(Ljava/lang/String;I)V", "ShutDown", "ShuttingDown", "Inactive", "InactivePendingWork", "Idle", "PendingWork", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum State {
        ShutDown,
        ShuttingDown,
        Inactive,
        InactivePendingWork,
        Idle,
        PendingWork
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runFrameLoop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer", f = "Recomposer.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {562, 569}, m = "runFrameLoop", n = {"this", "parentFrameClock", "frameSignal", "toRecompose", "toApply", "this", "parentFrameClock", "frameSignal", "toRecompose", "toApply"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C01891 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C01891(Continuation<? super C01891> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Recomposer.this.runFrameLoop(null, null, this);
        }
    }

    @Deprecated(message = "Replaced by currentState as a StateFlow", replaceWith = @ReplaceWith(expression = "currentState", imports = {}))
    public static /* synthetic */ void getState$annotations() {
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getCollectingParameterInformation$runtime_release() {
        return false;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public int getCompoundHashKey$runtime_release() {
        return 1000;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void recordInspectionTable$runtime_release(Set<CompositionData> table) {
        Intrinsics.checkNotNullParameter(table, "table");
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void registerComposition$runtime_release(ControlledComposition composition) {
        Intrinsics.checkNotNullParameter(composition, "composition");
    }

    public Recomposer(CoroutineContext effectCoroutineContext) {
        Intrinsics.checkNotNullParameter(effectCoroutineContext, "effectCoroutineContext");
        BroadcastFrameClock broadcastFrameClock = new BroadcastFrameClock(new Function0<Unit>() { // from class: androidx.compose.runtime.Recomposer$broadcastFrameClock$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CancellableContinuation cancellableContinuationDeriveStateLocked;
                Object obj = this.this$0.stateLock;
                Recomposer recomposer = this.this$0;
                synchronized (obj) {
                    cancellableContinuationDeriveStateLocked = recomposer.deriveStateLocked();
                    if (((Recomposer.State) recomposer._state.getValue()).compareTo(Recomposer.State.ShuttingDown) <= 0) {
                        throw ExceptionsKt.CancellationException("Recomposer shutdown; frame clock awaiter will never resume", recomposer.closeCause);
                    }
                }
                if (cancellableContinuationDeriveStateLocked == null) {
                    return;
                }
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationDeriveStateLocked.resumeWith(Result.m2810constructorimpl(Unit.INSTANCE));
            }
        });
        this.broadcastFrameClock = broadcastFrameClock;
        CompletableJob completableJobJob = JobKt.Job((Job) effectCoroutineContext.get(Job.INSTANCE));
        completableJobJob.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.runtime.Recomposer$effectJob$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Throwable th) {
                CancellableContinuation cancellableContinuation;
                CancellableContinuation cancellableContinuation2;
                CancellationException CancellationException = ExceptionsKt.CancellationException("Recomposer effect job completed", th);
                Object obj = this.this$0.stateLock;
                final Recomposer recomposer = this.this$0;
                synchronized (obj) {
                    Job job = recomposer.runnerJob;
                    cancellableContinuation = null;
                    if (job != null) {
                        recomposer._state.setValue(Recomposer.State.ShuttingDown);
                        if (recomposer.isClosed) {
                            if (recomposer.workContinuation != null) {
                                cancellableContinuation2 = recomposer.workContinuation;
                            }
                            recomposer.workContinuation = null;
                            job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.runtime.Recomposer$effectJob$1$1$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th2) {
                                    invoke2(th2);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable th2) {
                                    Object obj2 = recomposer.stateLock;
                                    Recomposer recomposer2 = recomposer;
                                    Throwable th3 = th;
                                    synchronized (obj2) {
                                        if (th3 != null) {
                                            if (th2 != null) {
                                                if (!(!(th2 instanceof CancellationException))) {
                                                    th2 = null;
                                                }
                                                if (th2 != null) {
                                                    ExceptionsKt__ExceptionsKt.addSuppressed(th3, th2);
                                                }
                                            }
                                        }
                                        th3 = null;
                                        recomposer2.closeCause = th3;
                                        recomposer2._state.setValue(Recomposer.State.ShutDown);
                                        Unit unit = Unit.INSTANCE;
                                    }
                                }
                            });
                            cancellableContinuation = cancellableContinuation2;
                        } else {
                            job.cancel(CancellationException);
                        }
                        cancellableContinuation2 = null;
                        recomposer.workContinuation = null;
                        job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.runtime.Recomposer$effectJob$1$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th2) {
                                invoke2(th2);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable th2) {
                                Object obj2 = recomposer.stateLock;
                                Recomposer recomposer2 = recomposer;
                                Throwable th3 = th;
                                synchronized (obj2) {
                                    if (th3 != null) {
                                        if (th2 != null) {
                                            if (!(!(th2 instanceof CancellationException))) {
                                                th2 = null;
                                            }
                                            if (th2 != null) {
                                                ExceptionsKt__ExceptionsKt.addSuppressed(th3, th2);
                                            }
                                        }
                                    }
                                    th3 = null;
                                    recomposer2.closeCause = th3;
                                    recomposer2._state.setValue(Recomposer.State.ShutDown);
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        });
                        cancellableContinuation = cancellableContinuation2;
                    } else {
                        recomposer.closeCause = CancellationException;
                        recomposer._state.setValue(Recomposer.State.ShutDown);
                        Unit unit = Unit.INSTANCE;
                    }
                }
                if (cancellableContinuation == null) {
                    return;
                }
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m2810constructorimpl(Unit.INSTANCE));
            }
        });
        this.effectJob = completableJobJob;
        this.effectCoroutineContext = effectCoroutineContext.plus(broadcastFrameClock).plus(completableJobJob);
        this.stateLock = new Object();
        this.knownCompositions = new ArrayList();
        this.snapshotInvalidations = new ArrayList();
        this.compositionInvalidations = new ArrayList();
        this.compositionsAwaitingApply = new ArrayList();
        this._state = StateFlowKt.MutableStateFlow(State.Inactive);
        this.recomposerInfo = new RecomposerInfoImpl(this);
    }

    public final long getChangeCount() {
        return this.changeCount;
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getEffectCoroutineContext$runtime_release, reason: from getter */
    public CoroutineContext getEffectCoroutineContext() {
        return this.effectCoroutineContext;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CoroutineContext getRecomposeCoroutineContext$runtime_release() {
        return EmptyCoroutineContext.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CancellableContinuation<Unit> deriveStateLocked() {
        State state;
        if (this._state.getValue().compareTo(State.ShuttingDown) <= 0) {
            this.knownCompositions.clear();
            this.snapshotInvalidations.clear();
            this.compositionInvalidations.clear();
            this.compositionsAwaitingApply.clear();
            CancellableContinuation<? super Unit> cancellableContinuation = this.workContinuation;
            if (cancellableContinuation != null) {
                CancellableContinuation.DefaultImpls.cancel$default(cancellableContinuation, null, 1, null);
            }
            this.workContinuation = null;
            return null;
        }
        if (this.runnerJob == null) {
            this.snapshotInvalidations.clear();
            this.compositionInvalidations.clear();
            state = this.broadcastFrameClock.getHasAwaiters() ? State.InactivePendingWork : State.Inactive;
        } else if ((!this.compositionInvalidations.isEmpty()) || (!this.snapshotInvalidations.isEmpty()) || (!this.compositionsAwaitingApply.isEmpty()) || this.concurrentCompositionsOutstanding > 0 || this.broadcastFrameClock.getHasAwaiters()) {
            state = State.PendingWork;
        } else {
            state = State.Idle;
        }
        this._state.setValue(state);
        if (state != State.PendingWork) {
            return null;
        }
        CancellableContinuation cancellableContinuation2 = this.workContinuation;
        this.workContinuation = null;
        return cancellableContinuation2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getShouldKeepRecomposing() {
        boolean z;
        boolean z2;
        synchronized (this.stateLock) {
            z = !this.isClosed;
        }
        if (z) {
            return true;
        }
        Iterator<Job> it = this.effectJob.getChildren().iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            if (it.next().isActive()) {
                z2 = true;
                break;
            }
        }
        return z2;
    }

    public final Flow<State> getState() {
        return getCurrentState();
    }

    public final StateFlow<State> getCurrentState() {
        return this._state;
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/RecomposerInfo;", "(Landroidx/compose/runtime/Recomposer;)V", "changeCount", "", "getChangeCount", "()J", "hasPendingWork", "", "getHasPendingWork", "()Z", "state", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/compose/runtime/Recomposer$State;", "getState", "()Lkotlinx/coroutines/flow/Flow;", "saveStateAndDisposeForHotReload", "", "Landroidx/compose/runtime/Recomposer$HotReloadable;", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class RecomposerInfoImpl implements RecomposerInfo {
        final /* synthetic */ Recomposer this$0;

        public RecomposerInfoImpl(Recomposer this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public Flow<State> getState() {
            return this.this$0.getCurrentState();
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public boolean getHasPendingWork() {
            return this.this$0.getHasPendingWork();
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public long getChangeCount() {
            return this.this$0.getChangeCount();
        }

        public final List<HotReloadable> saveStateAndDisposeForHotReload() {
            List mutableList;
            Object obj = this.this$0.stateLock;
            Recomposer recomposer = this.this$0;
            synchronized (obj) {
                mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) recomposer.knownCompositions);
            }
            ArrayList arrayList = new ArrayList(mutableList.size());
            int size = mutableList.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                ControlledComposition controlledComposition = (ControlledComposition) mutableList.get(i);
                CompositionImpl compositionImpl = controlledComposition instanceof CompositionImpl ? (CompositionImpl) controlledComposition : null;
                if (compositionImpl != null) {
                    arrayList.add(compositionImpl);
                }
                i = i2;
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int size2 = arrayList.size();
            for (int i3 = 0; i3 < size2; i3++) {
                HotReloadable hotReloadable = new HotReloadable((CompositionImpl) arrayList.get(i3));
                hotReloadable.clearContent();
                arrayList2.add(hotReloadable);
            }
            return arrayList2;
        }
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\u0007J\u0006\u0010\f\u001a\u00020\u0007R\u001b\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/Recomposer$HotReloadable;", "", "composition", "Landroidx/compose/runtime/CompositionImpl;", "(Landroidx/compose/runtime/CompositionImpl;)V", "composable", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/jvm/functions/Function2;", "clearContent", "recompose", "resetContent", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class HotReloadable {
        private Function2<? super Composer, ? super Integer, Unit> composable;
        private final CompositionImpl composition;

        public HotReloadable(CompositionImpl composition) {
            Intrinsics.checkNotNullParameter(composition, "composition");
            this.composition = composition;
            this.composable = composition.getComposable();
        }

        public final void clearContent() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(ComposableSingletons$RecomposerKt.INSTANCE.m9getLambda1$runtime_release());
            }
        }

        public final void resetContent() {
            this.composition.setComposable(this.composable);
        }

        public final void recompose() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(this.composable);
            }
        }
    }

    public final RecomposerInfo asRecomposerInfo() {
        return this.recomposerInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordComposerModificationsLocked() {
        if (!this.snapshotInvalidations.isEmpty()) {
            List<Set<Object>> list = this.snapshotInvalidations;
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                Set<? extends Object> set = list.get(i);
                List<ControlledComposition> list2 = this.knownCompositions;
                int size2 = list2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    list2.get(i3).recordModificationsOf(set);
                }
                i = i2;
            }
            this.snapshotInvalidations.clear();
            if (deriveStateLocked() != null) {
                throw new IllegalStateException("called outside of runRecomposeAndApplyChanges".toString());
            }
        }
    }

    private final void recordComposerModificationsLocked(Function1<? super ControlledComposition, Unit> onEachInvalidComposition) {
        if (!this.snapshotInvalidations.isEmpty()) {
            List list = this.snapshotInvalidations;
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                Set<? extends Object> set = (Set) list.get(i);
                List list2 = this.knownCompositions;
                int size2 = list2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    ((ControlledComposition) list2.get(i3)).recordModificationsOf(set);
                }
                i = i2;
            }
            this.snapshotInvalidations.clear();
        }
        List list3 = this.compositionInvalidations;
        int size3 = list3.size();
        for (int i4 = 0; i4 < size3; i4++) {
            onEachInvalidComposition.invoke(list3.get(i4));
        }
        this.compositionInvalidations.clear();
        if (deriveStateLocked() != null) {
            throw new IllegalStateException("called outside of runRecomposeAndApplyChanges".toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerRunnerJob(Job callingJob) {
        synchronized (this.stateLock) {
            Throwable th = this.closeCause;
            if (th != null) {
                throw th;
            }
            if (this._state.getValue().compareTo(State.ShuttingDown) <= 0) {
                throw new IllegalStateException("Recomposer shut down".toString());
            }
            if (this.runnerJob != null) {
                throw new IllegalStateException("Recomposer already running".toString());
            }
            this.runnerJob = callingJob;
            deriveStateLocked();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Landroidx/compose/runtime/MonotonicFrameClock;", "parentFrameClock", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 1, 1, 1}, l = {407, 425}, m = "invokeSuspend", n = {"parentFrameClock", "toRecompose", "toApply", "parentFrameClock", "toRecompose", "toApply"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    static final class C01912 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        C01912(Continuation<? super C01912> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
            C01912 c01912 = Recomposer.this.new C01912(continuation);
            c01912.L$0 = monotonicFrameClock;
            return c01912.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0086 -> B:11:0x0052). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x009c -> B:11:0x0052). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L40
                if (r1 == r3) goto L2b
                if (r1 != r2) goto L23
                java.lang.Object r1 = r11.L$2
                java.util.List r1 = (java.util.List) r1
                java.lang.Object r4 = r11.L$1
                java.util.List r4 = (java.util.List) r4
                java.lang.Object r5 = r11.L$0
                androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
                kotlin.ResultKt.throwOnFailure(r12)
                r12 = r5
                r5 = r11
                r10 = r4
                r4 = r1
                r1 = r10
                goto L52
            L23:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L2b:
                java.lang.Object r1 = r11.L$2
                java.util.List r1 = (java.util.List) r1
                java.lang.Object r4 = r11.L$1
                java.util.List r4 = (java.util.List) r4
                java.lang.Object r5 = r11.L$0
                androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
                kotlin.ResultKt.throwOnFailure(r12)
                r12 = r5
                r5 = r11
                r10 = r4
                r4 = r1
                r1 = r10
                goto L6b
            L40:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                androidx.compose.runtime.MonotonicFrameClock r12 = (androidx.compose.runtime.MonotonicFrameClock) r12
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.ArrayList r4 = new java.util.ArrayList
                r4.<init>()
                r5 = r11
            L52:
                androidx.compose.runtime.Recomposer r6 = androidx.compose.runtime.Recomposer.this
                boolean r6 = androidx.compose.runtime.Recomposer.access$getShouldKeepRecomposing(r6)
                if (r6 == 0) goto La2
                androidx.compose.runtime.Recomposer r6 = androidx.compose.runtime.Recomposer.this
                r5.L$0 = r12
                r5.L$1 = r1
                r5.L$2 = r4
                r5.label = r3
                java.lang.Object r6 = androidx.compose.runtime.Recomposer.access$awaitWorkAvailable(r6, r5)
                if (r6 != r0) goto L6b
                return r0
            L6b:
                androidx.compose.runtime.Recomposer r6 = androidx.compose.runtime.Recomposer.this
                java.lang.Object r6 = androidx.compose.runtime.Recomposer.access$getStateLock$p(r6)
                androidx.compose.runtime.Recomposer r7 = androidx.compose.runtime.Recomposer.this
                monitor-enter(r6)
                boolean r8 = androidx.compose.runtime.Recomposer.access$getHasFrameWorkLocked(r7)     // Catch: java.lang.Throwable -> L9f
                r9 = 0
                if (r8 != 0) goto L85
                androidx.compose.runtime.Recomposer.access$recordComposerModificationsLocked(r7)     // Catch: java.lang.Throwable -> L9f
                boolean r7 = androidx.compose.runtime.Recomposer.access$getHasFrameWorkLocked(r7)     // Catch: java.lang.Throwable -> L9f
                if (r7 != 0) goto L85
                r9 = r3
            L85:
                monitor-exit(r6)
                if (r9 == 0) goto L89
                goto L52
            L89:
                androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2$2 r6 = new androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2$2
                androidx.compose.runtime.Recomposer r7 = androidx.compose.runtime.Recomposer.this
                r6.<init>()
                r5.L$0 = r12
                r5.L$1 = r1
                r5.L$2 = r4
                r5.label = r2
                java.lang.Object r6 = r12.withFrameNanos(r6, r5)
                if (r6 != r0) goto L52
                return r0
            L9f:
                r12 = move-exception
                monitor-exit(r6)
                throw r12
            La2:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C01912.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object runRecomposeAndApplyChanges(Continuation<? super Unit> continuation) throws Throwable {
        Object objRecompositionRunner = recompositionRunner(new C01912(null), continuation);
        return objRecompositionRunner == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRecompositionRunner : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Landroidx/compose/runtime/MonotonicFrameClock;", "parentFrameClock", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 1}, l = {531, 551, 552}, m = "invokeSuspend", n = {"recomposeCoroutineScope", "frameSignal", "frameLoop", "frameLoop"}, s = {"L$0", "L$1", "L$2", "L$0"})
    static final class C01922 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineContext $recomposeCoroutineContext;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ Recomposer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01922(CoroutineContext coroutineContext, Recomposer recomposer, Continuation<? super C01922> continuation) {
            super(3, continuation);
            this.$recomposeCoroutineContext = coroutineContext;
            this.this$0 = recomposer;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
            C01922 c01922 = new C01922(this.$recomposeCoroutineContext, this.this$0, continuation);
            c01922.L$0 = coroutineScope;
            c01922.L$1 = monotonicFrameClock;
            return c01922.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0098  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x016e  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0191 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x00b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00a9 -> B:25:0x00ab). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 425
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C01922.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @ExperimentalComposeApi
    public final Object runRecomposeConcurrentlyAndApplyChanges(CoroutineContext coroutineContext, Continuation<? super Unit> continuation) throws Throwable {
        Object objRecompositionRunner = recompositionRunner(new C01922(coroutineContext, this, null), continuation);
        return objRecompositionRunner == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRecompositionRunner : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0087 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00a2 -> B:13:0x003f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object runFrameLoop(androidx.compose.runtime.MonotonicFrameClock r8, androidx.compose.runtime.ProduceFrameSignal r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof androidx.compose.runtime.Recomposer.C01891
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.runtime.Recomposer$runFrameLoop$1 r0 = (androidx.compose.runtime.Recomposer.C01891) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.runtime.Recomposer$runFrameLoop$1 r0 = new androidx.compose.runtime.Recomposer$runFrameLoop$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L65
            if (r2 == r4) goto L4d
            if (r2 != r3) goto L45
            java.lang.Object r8 = r0.L$4
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r9 = r0.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r2 = r0.L$2
            androidx.compose.runtime.ProduceFrameSignal r2 = (androidx.compose.runtime.ProduceFrameSignal) r2
            java.lang.Object r5 = r0.L$1
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            java.lang.Object r6 = r0.L$0
            androidx.compose.runtime.Recomposer r6 = (androidx.compose.runtime.Recomposer) r6
            kotlin.ResultKt.throwOnFailure(r10)
        L3f:
            r10 = r9
            r9 = r2
            r2 = r8
            r8 = r5
            r5 = r6
            goto L73
        L45:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4d:
            java.lang.Object r8 = r0.L$4
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r9 = r0.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r2 = r0.L$2
            androidx.compose.runtime.ProduceFrameSignal r2 = (androidx.compose.runtime.ProduceFrameSignal) r2
            java.lang.Object r5 = r0.L$1
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            java.lang.Object r6 = r0.L$0
            androidx.compose.runtime.Recomposer r6 = (androidx.compose.runtime.Recomposer) r6
            kotlin.ResultKt.throwOnFailure(r10)
            goto L8d
        L65:
            kotlin.ResultKt.throwOnFailure(r10)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r5 = r7
        L73:
            java.lang.Object r6 = r5.stateLock
            r0.L$0 = r5
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.L$4 = r2
            r0.label = r4
            java.lang.Object r6 = r9.awaitFrameRequest(r6, r0)
            if (r6 != r1) goto L88
            return r1
        L88:
            r6 = r5
            r5 = r8
            r8 = r2
            r2 = r9
            r9 = r10
        L8d:
            androidx.compose.runtime.Recomposer$runFrameLoop$2 r10 = new androidx.compose.runtime.Recomposer$runFrameLoop$2
            r10.<init>()
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r2
            r0.L$3 = r9
            r0.L$4 = r8
            r0.label = r3
            java.lang.Object r10 = r5.withFrameNanos(r10, r0)
            if (r10 != r1) goto L3f
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.runFrameLoop(androidx.compose.runtime.MonotonicFrameClock, androidx.compose.runtime.ProduceFrameSignal, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasSchedulingWork() {
        boolean z;
        synchronized (this.stateLock) {
            z = true;
            if (!(!this.snapshotInvalidations.isEmpty()) && !(!this.compositionInvalidations.isEmpty())) {
                if (!this.broadcastFrameClock.getHasAwaiters()) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitWorkAvailable(Continuation<? super Unit> continuation) {
        if (!getHasSchedulingWork()) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            synchronized (this.stateLock) {
                if (!getHasSchedulingWork()) {
                    this.workContinuation = cancellableContinuationImpl;
                } else {
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(Unit.INSTANCE));
                }
                Unit unit = Unit.INSTANCE;
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2", f = "Recomposer.kt", i = {0, 0}, l = {681}, m = "invokeSuspend", n = {"callingJob", "unregisterApplyObserver"}, s = {"L$0", "L$1"})
    static final class C01882 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ MonotonicFrameClock $parentFrameClock;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C01882(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, MonotonicFrameClock monotonicFrameClock, Continuation<? super C01882> continuation) {
            super(2, continuation);
            this.$block = function3;
            this.$parentFrameClock = monotonicFrameClock;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01882 c01882 = Recomposer.this.new C01882(this.$block, this.$parentFrameClock, continuation);
            c01882.L$0 = obj;
            return c01882;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01882) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 227
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C01882.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Recomposer.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2$2", f = "Recomposer.kt", i = {}, l = {682}, m = "invokeSuspend", n = {}, s = {})
        static final class C00252 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> $block;
            final /* synthetic */ MonotonicFrameClock $parentFrameClock;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00252(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, MonotonicFrameClock monotonicFrameClock, Continuation<? super C00252> continuation) {
                super(2, continuation);
                this.$block = function3;
                this.$parentFrameClock = monotonicFrameClock;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00252 c00252 = new C00252(this.$block, this.$parentFrameClock, continuation);
                c00252.L$0 = obj;
                return c00252;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00252) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> function3 = this.$block;
                    MonotonicFrameClock monotonicFrameClock = this.$parentFrameClock;
                    this.label = 1;
                    if (function3.invoke(coroutineScope, monotonicFrameClock, this) == coroutine_suspended) {
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object recompositionRunner(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(this.broadcastFrameClock, new C01882(function3, MonotonicFrameClockKt.getMonotonicFrameClock(continuation.getContext()), null), continuation);
        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final void cancel() {
        synchronized (this.stateLock) {
            if (this._state.getValue().compareTo(State.Idle) >= 0) {
                this._state.setValue(State.ShuttingDown);
            }
            Unit unit = Unit.INSTANCE;
        }
        Job.DefaultImpls.cancel$default((Job) this.effectJob, (CancellationException) null, 1, (Object) null);
    }

    public final void close() {
        if (this.effectJob.complete()) {
            synchronized (this.stateLock) {
                this.isClosed = true;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$join$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/runtime/Recomposer$State;", "it", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$join$2", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01872 extends SuspendLambda implements Function2<State, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C01872(Continuation<? super C01872> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01872 c01872 = new C01872(continuation);
            c01872.L$0 = obj;
            return c01872;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(State state, Continuation<? super Boolean> continuation) {
            return ((C01872) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(((State) this.L$0) == State.ShutDown);
        }
    }

    public final Object join(Continuation<? super Unit> continuation) {
        Object objFirst = FlowKt.first(getCurrentState(), new C01872(null), continuation);
        return objFirst == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFirst : Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void composeInitial$runtime_release(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(composition, "composition");
        Intrinsics.checkNotNullParameter(content, "content");
        boolean zIsComposing = composition.isComposing();
        Snapshot.Companion companion = Snapshot.INSTANCE;
        MutableSnapshot mutableSnapshotTakeMutableSnapshot = companion.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, null));
        try {
            Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
            try {
                composition.composeContent(content);
                Unit unit = Unit.INSTANCE;
                if (!zIsComposing) {
                    companion.notifyObjectsInitialized();
                }
                synchronized (this.stateLock) {
                    if (this._state.getValue().compareTo(State.ShuttingDown) > 0 && !this.knownCompositions.contains(composition)) {
                        this.knownCompositions.add(composition);
                    }
                }
                composition.applyChanges();
                if (zIsComposing) {
                    return;
                }
                companion.notifyObjectsInitialized();
            } finally {
                mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } finally {
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.runtime.ControlledComposition performRecompose(final androidx.compose.runtime.ControlledComposition r7, final androidx.compose.runtime.collection.IdentityArraySet<java.lang.Object> r8) {
        /*
            r6 = this;
            boolean r0 = r7.isComposing()
            r1 = 0
            if (r0 != 0) goto L4f
            boolean r0 = r7.getDisposed()
            if (r0 == 0) goto Le
            goto L4f
        Le:
            androidx.compose.runtime.snapshots.Snapshot$Companion r0 = androidx.compose.runtime.snapshots.Snapshot.INSTANCE
            kotlin.jvm.functions.Function1 r2 = r6.readObserverOf(r7)
            kotlin.jvm.functions.Function1 r3 = r6.writeObserverOf(r7, r8)
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r0.takeMutableSnapshot(r2, r3)
            androidx.compose.runtime.snapshots.Snapshot r2 = r0.makeCurrent()     // Catch: java.lang.Throwable -> L4a
            r3 = 1
            r4 = 0
            if (r8 != 0) goto L26
        L24:
            r3 = r4
            goto L2c
        L26:
            boolean r5 = r8.isNotEmpty()     // Catch: java.lang.Throwable -> L45
            if (r5 != r3) goto L24
        L2c:
            if (r3 == 0) goto L36
            androidx.compose.runtime.Recomposer$performRecompose$1$1 r3 = new androidx.compose.runtime.Recomposer$performRecompose$1$1     // Catch: java.lang.Throwable -> L45
            r3.<init>()     // Catch: java.lang.Throwable -> L45
            r7.prepareCompose(r3)     // Catch: java.lang.Throwable -> L45
        L36:
            boolean r8 = r7.recompose()     // Catch: java.lang.Throwable -> L45
            r0.restoreCurrent(r2)     // Catch: java.lang.Throwable -> L4a
            r6.applyAndCheck(r0)
            if (r8 == 0) goto L43
            goto L44
        L43:
            r7 = r1
        L44:
            return r7
        L45:
            r7 = move-exception
            r0.restoreCurrent(r2)     // Catch: java.lang.Throwable -> L4a
            throw r7     // Catch: java.lang.Throwable -> L4a
        L4a:
            r7 = move-exception
            r6.applyAndCheck(r0)
            throw r7
        L4f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.performRecompose(androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.collection.IdentityArraySet):androidx.compose.runtime.ControlledComposition");
    }

    private final Function1<Object, Unit> readObserverOf(final ControlledComposition composition) {
        return new Function1<Object, Unit>() { // from class: androidx.compose.runtime.Recomposer.readObserverOf.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                composition.recordReadOf(value);
            }
        };
    }

    private final Function1<Object, Unit> writeObserverOf(final ControlledComposition composition, final IdentityArraySet<Object> modifiedValues) {
        return new Function1<Object, Unit>() { // from class: androidx.compose.runtime.Recomposer.writeObserverOf.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                composition.recordWriteOf(value);
                IdentityArraySet<Object> identityArraySet = modifiedValues;
                if (identityArraySet == null) {
                    return;
                }
                identityArraySet.add(value);
            }
        };
    }

    private final <T> T composing(ControlledComposition composition, IdentityArraySet<Object> modifiedValues, Function0<? extends T> block) {
        MutableSnapshot mutableSnapshotTakeMutableSnapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
        try {
            Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot.makeCurrent();
            try {
                return block.invoke();
            } finally {
                InlineMarker.finallyStart(1);
                mutableSnapshotTakeMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                InlineMarker.finallyEnd(1);
            }
        } finally {
            InlineMarker.finallyStart(1);
            applyAndCheck(mutableSnapshotTakeMutableSnapshot);
            InlineMarker.finallyEnd(1);
        }
    }

    private final void applyAndCheck(MutableSnapshot snapshot) {
        try {
            if (snapshot.apply() instanceof SnapshotApplyResult.Failure) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.".toString());
            }
        } finally {
            snapshot.dispose();
        }
    }

    public final boolean getHasPendingWork() {
        boolean z;
        synchronized (this.stateLock) {
            z = true;
            if (!(!this.snapshotInvalidations.isEmpty()) && !(!this.compositionInvalidations.isEmpty()) && this.concurrentCompositionsOutstanding <= 0 && !(!this.compositionsAwaitingApply.isEmpty())) {
                if (!this.broadcastFrameClock.getHasAwaiters()) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasFrameWorkLocked() {
        return (this.compositionInvalidations.isEmpty() ^ true) || this.broadcastFrameClock.getHasAwaiters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasConcurrentFrameWorkLocked() {
        return (this.compositionsAwaitingApply.isEmpty() ^ true) || this.broadcastFrameClock.getHasAwaiters();
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$awaitIdle$2, reason: invalid class name */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/runtime/Recomposer$State;", "it", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$awaitIdle$2", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<State, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(State state, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(((State) this.L$0).compareTo(State.Idle) > 0);
        }
    }

    public final Object awaitIdle(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.collect(FlowKt.takeWhile(getCurrentState(), new AnonymousClass2(null)), continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void unregisterComposition$runtime_release(ControlledComposition composition) {
        Intrinsics.checkNotNullParameter(composition, "composition");
        synchronized (this.stateLock) {
            this.knownCompositions.remove(composition);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidate$runtime_release(ControlledComposition composition) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        Intrinsics.checkNotNullParameter(composition, "composition");
        synchronized (this.stateLock) {
            if (this.compositionInvalidations.contains(composition)) {
                cancellableContinuationDeriveStateLocked = null;
            } else {
                this.compositionInvalidations.add(composition);
                cancellableContinuationDeriveStateLocked = deriveStateLocked();
            }
        }
        if (cancellableContinuationDeriveStateLocked == null) {
            return;
        }
        Result.Companion companion = Result.INSTANCE;
        cancellableContinuationDeriveStateLocked.resumeWith(Result.m2810constructorimpl(Unit.INSTANCE));
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidateScope$runtime_release(RecomposeScopeImpl scope) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        Intrinsics.checkNotNullParameter(scope, "scope");
        synchronized (this.stateLock) {
            this.snapshotInvalidations.add(SetsKt__SetsJVMKt.setOf(scope));
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked == null) {
            return;
        }
        Result.Companion companion = Result.INSTANCE;
        cancellableContinuationDeriveStateLocked.resumeWith(Result.m2810constructorimpl(Unit.INSTANCE));
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0006R\u00020\u0007H\u0002J\u0015\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u0013J\u0014\u0010\u0014\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0006R\u00020\u0007H\u0002J\r\u0010\u0015\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u0016R\u001e\u0010\u0003\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0006R\u00020\u00070\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/Recomposer$Companion;", "", "()V", "_runningRecomposers", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/Recomposer;", "runningRecomposers", "Lkotlinx/coroutines/flow/StateFlow;", "", "Landroidx/compose/runtime/RecomposerInfo;", "getRunningRecomposers", "()Lkotlinx/coroutines/flow/StateFlow;", "addRunning", "", "info", "loadStateAndComposeForHotReload", "token", "loadStateAndComposeForHotReload$runtime_release", "removeRunning", "saveStateAndDisposeForHotReload", "saveStateAndDisposeForHotReload$runtime_release", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StateFlow<Set<RecomposerInfo>> getRunningRecomposers() {
            return Recomposer._runningRecomposers;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addRunning(RecomposerInfoImpl info) {
            PersistentSet persistentSet;
            PersistentSet persistentSetAdd;
            do {
                persistentSet = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetAdd = persistentSet.add(info);
                if (persistentSet == persistentSetAdd) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(persistentSet, persistentSetAdd));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void removeRunning(RecomposerInfoImpl info) {
            PersistentSet persistentSet;
            PersistentSet persistentSetRemove;
            do {
                persistentSet = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetRemove = persistentSet.remove(info);
                if (persistentSet == persistentSetRemove) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(persistentSet, persistentSetRemove));
        }

        public final Object saveStateAndDisposeForHotReload$runtime_release() {
            Iterable iterable = (Iterable) Recomposer._runningRecomposers.getValue();
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((RecomposerInfoImpl) it.next()).saveStateAndDisposeForHotReload());
            }
            return arrayList;
        }

        public final void loadStateAndComposeForHotReload$runtime_release(Object token) {
            Intrinsics.checkNotNullParameter(token, "token");
            List list = (List) token;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((HotReloadable) list.get(i)).resetContent();
            }
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((HotReloadable) list.get(i2)).recompose();
            }
        }
    }
}
