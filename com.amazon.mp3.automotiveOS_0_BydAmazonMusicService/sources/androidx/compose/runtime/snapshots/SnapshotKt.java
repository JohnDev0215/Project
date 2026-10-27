package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.SnapshotThreadLocal;
import androidx.compose.runtime.snapshots.SnapshotIdSet;
import com.facebook.react.bridge.BaseJavaModule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\b\u0010\"\u001a\u00020\bH\u0002\u001a6\u0010\"\u001a\u0002H#\"\u0004\b\u0000\u0010#2!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H#0\u000fH\u0002¢\u0006\u0002\u0010%\u001a'\u0010&\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020'2\u0006\u0010(\u001a\u0002H#2\u0006\u0010)\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010*\u001a\b\u0010+\u001a\u00020\u0007H\u0000\u001aB\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\u0014\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\u0014\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000fH\u0002\u001aB\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\u0014\u00100\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\u0014\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000fH\u0002\u001a\u0018\u00101\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u00072\u0006\u00102\u001a\u000203H\u0001\u001a.\u00104\u001a\u0010\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020'\u0018\u0001052\u0006\u0010+\u001a\u0002062\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u00020\u0010H\u0002\u001a\b\u00109\u001a\u00020:H\u0002\u001a1\u0010;\u001a\u0004\u0018\u0001H#\"\b\b\u0000\u0010#*\u00020'2\u0006\u0010(\u001a\u0002H#2\u0006\u0010<\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010=\u001a\b\u0010>\u001a\u00020:H\u0002\u001a%\u0010?\u001a\u0002H#\"\u0004\b\u0000\u0010#2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0@H\u0081\bø\u0001\u0000¢\u0006\u0002\u0010A\u001a>\u0010B\u001a\u0002H#\"\u0004\b\u0000\u0010#2\u0006\u0010C\u001a\u00020\u00072!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H#0\u000fH\u0002¢\u0006\u0002\u0010D\u001a:\u0010E\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020\u00072!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H#0\u000fH\u0002¢\u0006\u0002\u0010F\u001a\"\u0010G\u001a\u0004\u0018\u00010'2\u0006\u00102\u001a\u0002032\u0006\u0010<\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0002\u001a \u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0002\u001a \u0010H\u001a\u00020I2\u0006\u0010+\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0002\u001a\u0010\u0010L\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u0007H\u0002\u001a\u001c\u0010M\u001a\u00020\u0010*\u00020\u00102\u0006\u0010N\u001a\u00020\u00012\u0006\u0010O\u001a\u00020\u0001H\u0000\u001a+\u0010P\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020'*\u0002H#2\u0006\u00102\u001a\u0002032\u0006\u0010)\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010Q\u001a+\u0010R\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020'*\u0002H#2\u0006\u00102\u001a\u0002032\u0006\u0010)\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010Q\u001aN\u0010S\u001a\u0002HT\"\b\b\u0000\u0010#*\u00020'\"\u0004\b\u0001\u0010T*\u0002H#2\u0006\u00102\u001a\u0002032\u0006\u0010U\u001a\u0002H#2\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002HT0\u000f¢\u0006\u0002\bVH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010W\u001a3\u0010X\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020'*\u0002H#2\u0006\u00102\u001a\u0002032\u0006\u0010)\u001a\u00020\u00072\u0006\u0010U\u001a\u0002H#H\u0000¢\u0006\u0002\u0010Y\u001a!\u0010;\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020'*\u0002H#2\u0006\u00102\u001a\u000203¢\u0006\u0002\u0010Z\u001a)\u0010;\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020'*\u0002H#2\u0006\u00102\u001a\u0002032\u0006\u0010)\u001a\u00020\u0007¢\u0006\u0002\u0010Q\u001aH\u0010[\u001a\u0002HT\"\b\b\u0000\u0010#*\u00020'\"\u0004\b\u0001\u0010T*\u0002H#2!\u0010$\u001a\u001d\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b((\u0012\u0004\u0012\u0002HT0\u000fH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\\\u001aF\u0010]\u001a\u0002HT\"\b\b\u0000\u0010#*\u00020'\"\u0004\b\u0001\u0010T*\u0002H#2\u0006\u00102\u001a\u0002032\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002HT0\u000f¢\u0006\u0002\bVH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010^\u001aN\u0010]\u001a\u0002HT\"\b\b\u0000\u0010#*\u00020'\"\u0004\b\u0001\u0010T*\u0002H#2\u0006\u00102\u001a\u0002032\u0006\u0010)\u001a\u00020\u00072\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002HT0\u000f¢\u0006\u0002\bVH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010_\u001a+\u0010`\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020'*\u0002H#2\u0006\u00102\u001a\u0002032\u0006\u0010)\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010Q\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\",\u0010\u0002\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\".\u0010\t\u001a\"\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nj\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b`\rX\u0082\u0004¢\u0006\u0002\n\u0000\")\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000f0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0015\u001a\u00020\u00068\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001c\u0010\u001c\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0017\u001a\u0004\b\u001e\u0010\u001f\"\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070!X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006a"}, d2 = {"INVALID_SNAPSHOT", "", "applyObservers", "", "Lkotlin/Function2;", "", "", "Landroidx/compose/runtime/snapshots/Snapshot;", "", "currentGlobalSnapshot", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/snapshots/GlobalSnapshot;", "kotlin.jvm.PlatformType", "Landroidx/compose/runtime/AtomicReference;", "emptyLambda", "Lkotlin/Function1;", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "Lkotlin/ParameterName;", "name", "invalid", "globalWriteObservers", "lock", "getLock$annotations", "()V", "getLock", "()Ljava/lang/Object;", "nextSnapshotId", "openSnapshots", "snapshotInitializer", "getSnapshotInitializer$annotations", "getSnapshotInitializer", "()Landroidx/compose/runtime/snapshots/Snapshot;", "threadSnapshot", "Landroidx/compose/runtime/SnapshotThreadLocal;", "advanceGlobalSnapshot", "T", "block", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "current", "Landroidx/compose/runtime/snapshots/StateRecord;", "r", "snapshot", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "currentSnapshot", "mergedReadObserver", "readObserver", "parentObserver", "mergedWriteObserver", "writeObserver", "notifyWrite", "state", "Landroidx/compose/runtime/snapshots/StateObject;", "optimisticMerges", "", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "applyingSnapshot", "invalidSnapshots", "readError", "", "readable", "id", "(Landroidx/compose/runtime/snapshots/StateRecord;ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/StateRecord;", "reportReadonlySnapshotWrite", BaseJavaModule.METHOD_TYPE_SYNC, "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "takeNewGlobalSnapshot", "previousGlobalSnapshot", "(Landroidx/compose/runtime/snapshots/Snapshot;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "takeNewSnapshot", "(Lkotlin/jvm/functions/Function1;)Landroidx/compose/runtime/snapshots/Snapshot;", "used", "valid", "", "data", "candidateSnapshot", "validateOpen", "addRange", "from", "until", "newOverwritableRecord", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "newWritableRecord", "overwritable", "R", "candidate", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/StateRecord;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "overwritableRecord", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;Landroidx/compose/runtime/snapshots/StateRecord;)Landroidx/compose/runtime/snapshots/StateRecord;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;)Landroidx/compose/runtime/snapshots/StateRecord;", "withCurrent", "(Landroidx/compose/runtime/snapshots/StateRecord;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writable", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writableRecord", "runtime_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class SnapshotKt {
    private static final int INVALID_SNAPSHOT = 0;
    private static final List<Function2<Set<? extends Object>, Snapshot, Unit>> applyObservers;
    private static final AtomicReference<GlobalSnapshot> currentGlobalSnapshot;
    private static final List<Function1<Object, Unit>> globalWriteObservers;
    private static int nextSnapshotId;
    private static SnapshotIdSet openSnapshots;
    private static final Snapshot snapshotInitializer;
    private static final Function1<SnapshotIdSet, Unit> emptyLambda = new Function1<SnapshotIdSet, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(SnapshotIdSet it) {
            Intrinsics.checkNotNullParameter(it, "it");
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SnapshotIdSet snapshotIdSet) {
            invoke2(snapshotIdSet);
            return Unit.INSTANCE;
        }
    };
    private static final SnapshotThreadLocal<Snapshot> threadSnapshot = new SnapshotThreadLocal<>();
    private static final Object lock = new Object();

    @PublishedApi
    public static /* synthetic */ void getLock$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getSnapshotInitializer$annotations() {
    }

    public static final Snapshot currentSnapshot() {
        Snapshot snapshot = threadSnapshot.get();
        if (snapshot != null) {
            return snapshot;
        }
        GlobalSnapshot globalSnapshot = currentGlobalSnapshot.get();
        Intrinsics.checkNotNullExpressionValue(globalSnapshot, "currentGlobalSnapshot.get()");
        return globalSnapshot;
    }

    static {
        SnapshotIdSet.Companion companion = SnapshotIdSet.INSTANCE;
        openSnapshots = companion.getEMPTY();
        nextSnapshotId = 1;
        applyObservers = new ArrayList();
        globalWriteObservers = new ArrayList();
        int i = nextSnapshotId;
        nextSnapshotId = i + 1;
        GlobalSnapshot globalSnapshot = new GlobalSnapshot(i, companion.getEMPTY());
        openSnapshots = openSnapshots.set(globalSnapshot.getId());
        AtomicReference<GlobalSnapshot> atomicReference = new AtomicReference<>(globalSnapshot);
        currentGlobalSnapshot = atomicReference;
        GlobalSnapshot globalSnapshot2 = atomicReference.get();
        Intrinsics.checkNotNullExpressionValue(globalSnapshot2, "currentGlobalSnapshot.get()");
        snapshotInitializer = globalSnapshot2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<Object, Unit> mergedReadObserver(final Function1<Object, Unit> function1, final Function1<Object, Unit> function12) {
        if (function1 == null || function12 == null || Intrinsics.areEqual(function1, function12)) {
            return function1 == null ? function12 : function1;
        }
        return new Function1<Object, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.mergedReadObserver.1
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
            public final void invoke2(Object state) {
                Intrinsics.checkNotNullParameter(state, "state");
                function1.invoke(state);
                function12.invoke(state);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<Object, Unit> mergedWriteObserver(final Function1<Object, Unit> function1, final Function1<Object, Unit> function12) {
        if (function1 == null || function12 == null || Intrinsics.areEqual(function1, function12)) {
            return function1 == null ? function12 : function1;
        }
        return new Function1<Object, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.mergedWriteObserver.1
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
            public final void invoke2(Object state) {
                Intrinsics.checkNotNullParameter(state, "state");
                function1.invoke(state);
                function12.invoke(state);
            }
        };
    }

    public static final Object getLock() {
        return lock;
    }

    @PublishedApi
    public static final <T> T sync(Function0<? extends T> block) {
        T tInvoke;
        Intrinsics.checkNotNullParameter(block, "block");
        synchronized (getLock()) {
            try {
                tInvoke = block.invoke();
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return tInvoke;
    }

    public static final <T extends StateRecord, R> R writable(T t, StateObject state, Snapshot snapshot, Function1<? super T, ? extends R> block) {
        R rInvoke;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(block, "block");
        synchronized (getLock()) {
            try {
                rInvoke = block.invoke(writableRecord(t, state, snapshot));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(snapshot, state);
        return rInvoke;
    }

    public static final Snapshot getSnapshotInitializer() {
        return snapshotInitializer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T takeNewGlobalSnapshot(Snapshot snapshot, Function1<? super SnapshotIdSet, ? extends T> function1) {
        T tInvoke = function1.invoke(openSnapshots.clear(snapshot.getId()));
        synchronized (getLock()) {
            int i = nextSnapshotId;
            nextSnapshotId = i + 1;
            openSnapshots = openSnapshots.clear(snapshot.getId());
            currentGlobalSnapshot.set(new GlobalSnapshot(i, openSnapshots));
            openSnapshots = openSnapshots.set(i);
            Unit unit = Unit.INSTANCE;
        }
        return tInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T advanceGlobalSnapshot(Function1<? super SnapshotIdSet, ? extends T> function1) {
        T t;
        List mutableList;
        GlobalSnapshot previousGlobalSnapshot = currentGlobalSnapshot.get();
        synchronized (getLock()) {
            Intrinsics.checkNotNullExpressionValue(previousGlobalSnapshot, "previousGlobalSnapshot");
            t = (T) takeNewGlobalSnapshot(previousGlobalSnapshot, function1);
        }
        Set<StateObject> modified$runtime_release = previousGlobalSnapshot.getModified$runtime_release();
        if (modified$runtime_release != null) {
            synchronized (getLock()) {
                mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) applyObservers);
            }
            int size = mutableList.size();
            for (int i = 0; i < size; i++) {
                ((Function2) mutableList.get(i)).mo4invoke(modified$runtime_release, previousGlobalSnapshot);
            }
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void advanceGlobalSnapshot() {
        advanceGlobalSnapshot(new Function1<SnapshotIdSet, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.advanceGlobalSnapshot.2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SnapshotIdSet it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SnapshotIdSet snapshotIdSet) {
                invoke2(snapshotIdSet);
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends Snapshot> T takeNewSnapshot(final Function1<? super SnapshotIdSet, ? extends T> function1) {
        return (T) advanceGlobalSnapshot(new Function1<SnapshotIdSet, T>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.takeNewSnapshot.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* JADX WARN: Incorrect return type in method signature: (Landroidx/compose/runtime/snapshots/SnapshotIdSet;)TT; */
            @Override // kotlin.jvm.functions.Function1
            public final Snapshot invoke(SnapshotIdSet invalid) {
                Intrinsics.checkNotNullParameter(invalid, "invalid");
                Snapshot snapshot = (Snapshot) function1.invoke(invalid);
                synchronized (SnapshotKt.getLock()) {
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(snapshot.getId());
                    Unit unit = Unit.INSTANCE;
                }
                return snapshot;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateOpen(Snapshot snapshot) {
        if (!openSnapshots.get(snapshot.getId())) {
            throw new IllegalStateException("Snapshot is not open".toString());
        }
    }

    private static final boolean valid(int i, int i2, SnapshotIdSet snapshotIdSet) {
        return (i2 == 0 || i2 > i || snapshotIdSet.get(i2)) ? false : true;
    }

    private static final boolean valid(StateRecord stateRecord, int i, SnapshotIdSet snapshotIdSet) {
        return valid(i, stateRecord.getSnapshotId(), snapshotIdSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends StateRecord> T readable(T t, int i, SnapshotIdSet snapshotIdSet) {
        T t2 = null;
        while (t != null) {
            if (valid(t, i, snapshotIdSet) && (t2 == null || t2.getSnapshotId() < t.getSnapshotId())) {
                t2 = t;
            }
            t = (T) t.getNext();
        }
        if (t2 != null) {
            return t2;
        }
        return null;
    }

    public static final <T extends StateRecord> T readable(T t, StateObject state) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        return (T) readable(t, state, currentSnapshot());
    }

    public static final <T extends StateRecord> T readable(T t, StateObject state, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Function1<Object, Unit> readObserver$runtime_release = snapshot.getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(state);
        }
        T t2 = (T) readable(t, snapshot.getId(), snapshot.getInvalid());
        if (t2 != null) {
            return t2;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void readError() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied".toString());
    }

    private static final StateRecord used(StateObject stateObject, int i, SnapshotIdSet snapshotIdSet) {
        int iLowest = snapshotIdSet.lowest(i);
        StateRecord stateRecord = null;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext()) {
            if (firstStateRecord.getSnapshotId() == 0) {
                return firstStateRecord;
            }
            if (valid(firstStateRecord, iLowest, snapshotIdSet)) {
                if (stateRecord != null) {
                    return firstStateRecord.getSnapshotId() < stateRecord.getSnapshotId() ? firstStateRecord : stateRecord;
                }
                stateRecord = firstStateRecord;
            }
        }
        return null;
    }

    @PublishedApi
    public static final <T extends StateRecord> T writableRecord(T t, StateObject state, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        if (snapshot.getReadOnly()) {
            snapshot.mo34recordModified$runtime_release(state);
        }
        T t2 = (T) readable(t, snapshot.getId(), snapshot.getInvalid());
        if (t2 == null) {
            readError();
            throw new KotlinNothingValueException();
        }
        if (t2.getSnapshotId() == snapshot.getId()) {
            return t2;
        }
        T t3 = (T) newWritableRecord(t2, state, snapshot);
        snapshot.mo34recordModified$runtime_release(state);
        return t3;
    }

    public static final <T extends StateRecord> T overwritableRecord(T t, StateObject state, Snapshot snapshot, T candidate) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        if (snapshot.getReadOnly()) {
            snapshot.mo34recordModified$runtime_release(state);
        }
        int id = snapshot.getId();
        if (candidate.getSnapshotId() == id) {
            return candidate;
        }
        T t2 = (T) newOverwritableRecord(t, state, snapshot);
        t2.setSnapshotId$runtime_release(id);
        snapshot.mo34recordModified$runtime_release(state);
        return t2;
    }

    public static final <T extends StateRecord> T newWritableRecord(T t, StateObject state, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        T t2 = (T) newOverwritableRecord(t, state, snapshot);
        t2.assign(t);
        t2.setSnapshotId$runtime_release(snapshot.getId());
        return t2;
    }

    public static final <T extends StateRecord> T newOverwritableRecord(T t, StateObject state, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        T t2 = (T) used(state, snapshot.getId(), openSnapshots);
        if (t2 == null) {
            t2 = null;
        } else {
            t2.setSnapshotId$runtime_release(Integer.MAX_VALUE);
        }
        if (t2 != null) {
            return t2;
        }
        T t3 = (T) t.create();
        t3.setSnapshotId$runtime_release(Integer.MAX_VALUE);
        t3.setNext$runtime_release(state.getFirstStateRecord());
        state.prependStateRecord(t3);
        return t3;
    }

    @PublishedApi
    public static final void notifyWrite(Snapshot snapshot, StateObject state) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(state, "state");
        Function1<Object, Unit> writeObserver$runtime_release = snapshot.getWriteObserver$runtime_release();
        if (writeObserver$runtime_release == null) {
            return;
        }
        writeObserver$runtime_release.invoke(state);
    }

    public static final <T extends StateRecord, R> R writable(T t, StateObject state, Function1<? super T, ? extends R> block) {
        Snapshot current;
        R rInvoke;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(block, "block");
        getSnapshotInitializer();
        synchronized (getLock()) {
            try {
                current = Snapshot.INSTANCE.getCurrent();
                rInvoke = block.invoke(writableRecord(t, state, current));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(current, state);
        return rInvoke;
    }

    public static final <T extends StateRecord, R> R overwritable(T t, StateObject state, T candidate, Function1<? super T, ? extends R> block) {
        Snapshot current;
        R rInvoke;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(block, "block");
        getSnapshotInitializer();
        synchronized (getLock()) {
            try {
                current = Snapshot.INSTANCE.getCurrent();
                rInvoke = block.invoke(overwritableRecord(t, state, current, candidate));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(current, state);
        return rInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<StateRecord, StateRecord> optimisticMerges(MutableSnapshot mutableSnapshot, MutableSnapshot mutableSnapshot2, SnapshotIdSet snapshotIdSet) {
        StateRecord stateRecord;
        Set<StateObject> modified$runtime_release = mutableSnapshot2.getModified$runtime_release();
        int id = mutableSnapshot.getId();
        if (modified$runtime_release == null) {
            return null;
        }
        SnapshotIdSet snapshotIdSetOr = mutableSnapshot2.getInvalid().set(mutableSnapshot2.getId()).or(mutableSnapshot2.getPreviousIds$runtime_release());
        HashMap map = null;
        for (StateObject stateObject : modified$runtime_release) {
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            StateRecord stateRecord2 = readable(firstStateRecord, id, snapshotIdSet);
            if (stateRecord2 != null && (stateRecord = readable(firstStateRecord, id, snapshotIdSetOr)) != null && !Intrinsics.areEqual(stateRecord2, stateRecord)) {
                StateRecord stateRecord3 = readable(firstStateRecord, mutableSnapshot2.getId(), mutableSnapshot2.getInvalid());
                if (stateRecord3 == null) {
                    readError();
                    throw new KotlinNothingValueException();
                }
                StateRecord stateRecordMergeRecords = stateObject.mergeRecords(stateRecord, stateRecord2, stateRecord3);
                if (stateRecordMergeRecords == null) {
                    return null;
                }
                if (map == null) {
                    map = new HashMap();
                }
                map.put(stateRecord2, stateRecordMergeRecords);
                map = map;
            }
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void reportReadonlySnapshotWrite() {
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot".toString());
    }

    @PublishedApi
    public static final <T extends StateRecord> T current(T r, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(r, "r");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        T t = (T) readable(r, snapshot.getId(), snapshot.getInvalid());
        if (t != null) {
            return t;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    public static final <T extends StateRecord, R> R withCurrent(T t, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(current(t, Snapshot.INSTANCE.getCurrent()));
    }

    public static final SnapshotIdSet addRange(SnapshotIdSet snapshotIdSet, int i, int i2) {
        Intrinsics.checkNotNullParameter(snapshotIdSet, "<this>");
        while (i < i2) {
            snapshotIdSet = snapshotIdSet.set(i);
            i++;
        }
        return snapshotIdSet;
    }
}
