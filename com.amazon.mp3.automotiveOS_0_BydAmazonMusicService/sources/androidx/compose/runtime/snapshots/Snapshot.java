package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.InternalComposeApi;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Set;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010'\u001a\u00020\u001dH\u0010¢\u0006\u0002\b(J\b\u0010)\u001a\u00020\u001dH\u0016J%\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010+2\f\u0010,\u001a\b\u0012\u0004\u0012\u0002H+0-H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010.J\b\u0010/\u001a\u00020\bH&J\n\u00100\u001a\u0004\u0018\u00010\u0000H\u0011J\u0015\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u0000H ¢\u0006\u0002\b3J\u0015\u00104\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u0000H ¢\u0006\u0002\b5J\r\u00106\u001a\u00020\u001dH ¢\u0006\u0002\b7J\u0015\u00108\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u0018H ¢\u0006\u0002\b:J\u0012\u0010;\u001a\u00020\u001d2\b\u00102\u001a\u0004\u0018\u00010\u0000H\u0011J \u0010<\u001a\u00020\u00002\u0016\b\u0002\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH&J\r\u0010=\u001a\u00020\u001dH\u0000¢\u0006\u0002\b>R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003@PX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X \u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX \u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0012\u0010 \u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\nR\u0012\u0010\"\u001a\u00020\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R \u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX \u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u001f\u0082\u0001\u0003@AB\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006C"}, d2 = {"Landroidx/compose/runtime/snapshots/Snapshot;", "", "id", "", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "disposed", "", "getDisposed$runtime_release", "()Z", "setDisposed$runtime_release", "(Z)V", "<set-?>", "getId", "()I", "setId$runtime_release", "(I)V", "getInvalid$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setInvalid$runtime_release", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "modified", "", "Landroidx/compose/runtime/snapshots/StateObject;", "getModified$runtime_release", "()Ljava/util/Set;", "readObserver", "Lkotlin/Function1;", "", "getReadObserver$runtime_release", "()Lkotlin/jvm/functions/Function1;", "readOnly", "getReadOnly", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "writeObserver", "getWriteObserver$runtime_release", "close", "close$runtime_release", "dispose", "enter", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "hasPendingChanges", "makeCurrent", "nestedActivated", "snapshot", "nestedActivated$runtime_release", "nestedDeactivated", "nestedDeactivated$runtime_release", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime_release", "recordModified", "state", "recordModified$runtime_release", "restoreCurrent", "takeNestedSnapshot", "validateNotDisposed", "validateNotDisposed$runtime_release", "Companion", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/ReadonlySnapshot;", "Landroidx/compose/runtime/snapshots/NestedReadonlySnapshot;", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class Snapshot {
    private boolean disposed;
    private int id;
    private SnapshotIdSet invalid;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ Snapshot(int i, SnapshotIdSet snapshotIdSet, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, snapshotIdSet);
    }

    public abstract Set<StateObject> getModified$runtime_release();

    public abstract Function1<Object, Unit> getReadObserver$runtime_release();

    public abstract boolean getReadOnly();

    public abstract Snapshot getRoot();

    public abstract Function1<Object, Unit> getWriteObserver$runtime_release();

    public abstract boolean hasPendingChanges();

    /* JADX INFO: renamed from: nestedActivated$runtime_release */
    public abstract void mo32nestedActivated$runtime_release(Snapshot snapshot);

    /* JADX INFO: renamed from: nestedDeactivated$runtime_release */
    public abstract void mo33nestedDeactivated$runtime_release(Snapshot snapshot);

    public abstract void notifyObjectsInitialized$runtime_release();

    /* JADX INFO: renamed from: recordModified$runtime_release */
    public abstract void mo34recordModified$runtime_release(StateObject state);

    public abstract Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver);

    private Snapshot(int i, SnapshotIdSet snapshotIdSet) {
        this.invalid = snapshotIdSet;
        this.id = i;
    }

    /* JADX INFO: renamed from: getInvalid$runtime_release, reason: from getter */
    public SnapshotIdSet getInvalid() {
        return this.invalid;
    }

    public void setInvalid$runtime_release(SnapshotIdSet snapshotIdSet) {
        Intrinsics.checkNotNullParameter(snapshotIdSet, "<set-?>");
        this.invalid = snapshotIdSet;
    }

    public int getId() {
        return this.id;
    }

    public void setId$runtime_release(int i) {
        this.id = i;
    }

    public void dispose() {
        this.disposed = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Snapshot takeNestedSnapshot$default(Snapshot snapshot, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takeNestedSnapshot");
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        return snapshot.takeNestedSnapshot(function1);
    }

    public final <T> T enter(Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        Snapshot snapshotMakeCurrent = makeCurrent();
        try {
            return block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            restoreCurrent(snapshotMakeCurrent);
            InlineMarker.finallyEnd(1);
        }
    }

    @PublishedApi
    public Snapshot makeCurrent() {
        Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
        SnapshotKt.threadSnapshot.set(this);
        return snapshot;
    }

    @PublishedApi
    public void restoreCurrent(Snapshot snapshot) {
        SnapshotKt.threadSnapshot.set(snapshot);
    }

    /* JADX INFO: renamed from: getDisposed$runtime_release, reason: from getter */
    public final boolean getDisposed() {
        return this.disposed;
    }

    public final void setDisposed$runtime_release(boolean z) {
        this.disposed = z;
    }

    public final void validateNotDisposed$runtime_release() {
        if (!(!this.disposed)) {
            throw new IllegalArgumentException("Cannot use a disposed snapshot".toString());
        }
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\rJO\u0010\u000e\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\n¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0007J&\u0010\u0015\u001a\u00020\u00162\u001e\u0010\u0017\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0019\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\u0018J\u001a\u0010\u001a\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0010J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0001J\u0012\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0001J\u0006\u0010\u001e\u001a\u00020\rJ6\u0010\u001f\u001a\u00020 2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010J\u001e\u0010!\u001a\u00020\u00042\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010J%\u0010\"\u001a\u0002H#\"\u0004\b\u0000\u0010#2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H#0\nH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"}, d2 = {"Landroidx/compose/runtime/snapshots/Snapshot$Companion;", "", "()V", "current", "Landroidx/compose/runtime/snapshots/Snapshot;", "getCurrent", "()Landroidx/compose/runtime/snapshots/Snapshot;", "global", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "notifyObjectsInitialized", "", "observe", "readObserver", "Lkotlin/Function1;", "writeObserver", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "openSnapshotCount", "", "registerApplyObserver", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "observer", "Lkotlin/Function2;", "", "registerGlobalWriteObserver", "removeCurrent", "restoreCurrent", "previous", "sendApplyNotifications", "takeMutableSnapshot", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "takeSnapshot", "withMutableSnapshot", "R", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Snapshot getCurrent() {
            return SnapshotKt.currentSnapshot();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Snapshot takeSnapshot$default(Companion companion, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            return companion.takeSnapshot(function1);
        }

        public final Snapshot takeSnapshot(Function1<Object, Unit> readObserver) {
            return SnapshotKt.currentSnapshot().takeNestedSnapshot(readObserver);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MutableSnapshot takeMutableSnapshot$default(Companion companion, Function1 function1, Function1 function12, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            if ((i & 2) != 0) {
                function12 = null;
            }
            return companion.takeMutableSnapshot(function1, function12);
        }

        public final MutableSnapshot takeMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
            Snapshot snapshotCurrentSnapshot = SnapshotKt.currentSnapshot();
            MutableSnapshot mutableSnapshot = snapshotCurrentSnapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshotCurrentSnapshot : null;
            MutableSnapshot mutableSnapshotTakeNestedMutableSnapshot = mutableSnapshot != null ? mutableSnapshot.takeNestedMutableSnapshot(readObserver, writeObserver) : null;
            if (mutableSnapshotTakeNestedMutableSnapshot != null) {
                return mutableSnapshotTakeNestedMutableSnapshot;
            }
            throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot".toString());
        }

        public final <T> T global(Function0<? extends T> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Snapshot snapshotRemoveCurrent = removeCurrent();
            T tInvoke = block.invoke();
            Snapshot.INSTANCE.restoreCurrent(snapshotRemoveCurrent);
            return tInvoke;
        }

        public final <R> R withMutableSnapshot(Function0<? extends R> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            MutableSnapshot mutableSnapshotTakeMutableSnapshot$default = takeMutableSnapshot$default(this, null, null, 3, null);
            try {
                Snapshot snapshotMakeCurrent = mutableSnapshotTakeMutableSnapshot$default.makeCurrent();
                try {
                    R rInvoke = block.invoke();
                    InlineMarker.finallyStart(1);
                    mutableSnapshotTakeMutableSnapshot$default.restoreCurrent(snapshotMakeCurrent);
                    InlineMarker.finallyEnd(1);
                    mutableSnapshotTakeMutableSnapshot$default.apply().check();
                    return rInvoke;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    mutableSnapshotTakeMutableSnapshot$default.restoreCurrent(snapshotMakeCurrent);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            } finally {
                InlineMarker.finallyStart(1);
                mutableSnapshotTakeMutableSnapshot$default.dispose();
                InlineMarker.finallyEnd(1);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object observe$default(Companion companion, Function1 function1, Function1 function12, Function0 function0, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = null;
            }
            if ((i & 2) != 0) {
                function12 = null;
            }
            return companion.observe(function1, function12, function0);
        }

        public final <T> T observe(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver, Function0<? extends T> block) {
            Snapshot transparentObserverMutableSnapshot;
            Intrinsics.checkNotNullParameter(block, "block");
            if (readObserver != null || writeObserver != null) {
                Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
                if (snapshot == null || (snapshot instanceof MutableSnapshot)) {
                    transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, readObserver, writeObserver);
                } else {
                    if (readObserver == null) {
                        return block.invoke();
                    }
                    transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(readObserver);
                }
                try {
                    Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                    try {
                        return block.invoke();
                    } finally {
                        transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    }
                } finally {
                    transparentObserverMutableSnapshot.dispose();
                }
            }
            return block.invoke();
        }

        public final ObserverHandle registerApplyObserver(final Function2<? super Set<? extends Object>, ? super Snapshot, Unit> observer) {
            Intrinsics.checkNotNullParameter(observer, "observer");
            SnapshotKt.advanceGlobalSnapshot(SnapshotKt.emptyLambda);
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.applyObservers.add(observer);
            }
            return new ObserverHandle() { // from class: androidx.compose.runtime.snapshots.Snapshot$Companion$registerApplyObserver$2
                @Override // androidx.compose.runtime.snapshots.ObserverHandle
                public final void dispose() {
                    Function2<Set<? extends Object>, Snapshot, Unit> function2 = observer;
                    synchronized (SnapshotKt.getLock()) {
                        SnapshotKt.applyObservers.remove(function2);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            };
        }

        public final void notifyObjectsInitialized() {
            SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
        }

        @InternalComposeApi
        public final int openSnapshotCount() {
            return CollectionsKt___CollectionsKt.toList(SnapshotKt.openSnapshots).size();
        }

        @PublishedApi
        public final Snapshot removeCurrent() {
            Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
            if (snapshot != null) {
                SnapshotKt.threadSnapshot.set(null);
            }
            return snapshot;
        }

        @PublishedApi
        public final void restoreCurrent(Snapshot previous) {
            if (previous != null) {
                SnapshotKt.threadSnapshot.set(previous);
            }
        }

        public final ObserverHandle registerGlobalWriteObserver(final Function1<Object, Unit> observer) {
            Intrinsics.checkNotNullParameter(observer, "observer");
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.globalWriteObservers.add(observer);
            }
            SnapshotKt.advanceGlobalSnapshot();
            return new ObserverHandle() { // from class: androidx.compose.runtime.snapshots.Snapshot$Companion$registerGlobalWriteObserver$2
                @Override // androidx.compose.runtime.snapshots.ObserverHandle
                public final void dispose() {
                    Function1<Object, Unit> function1 = observer;
                    synchronized (SnapshotKt.getLock()) {
                        SnapshotKt.globalWriteObservers.remove(function1);
                    }
                    SnapshotKt.advanceGlobalSnapshot();
                }
            };
        }

        public final void sendApplyNotifications() {
            boolean z;
            synchronized (SnapshotKt.getLock()) {
                z = false;
                if (((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getModified$runtime_release() != null) {
                    if (!r1.isEmpty()) {
                        z = true;
                    }
                }
            }
            if (z) {
                SnapshotKt.advanceGlobalSnapshot();
            }
        }
    }

    public void close$runtime_release() {
        synchronized (SnapshotKt.getLock()) {
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId());
            Unit unit = Unit.INSTANCE;
        }
    }
}
