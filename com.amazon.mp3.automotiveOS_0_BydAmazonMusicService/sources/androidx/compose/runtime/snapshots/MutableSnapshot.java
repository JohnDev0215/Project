package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.internal.StabilityInferred;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0017\u0018\u00002\u00020\u0001BC\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\b\u0010(\u001a\u00020\tH\u0002J\r\u0010)\u001a\u00020\tH\u0000¢\u0006\u0002\b*J'\u0010)\u001a\u0002H+\"\u0004\b\u0000\u0010+2\f\u0010,\u001a\b\u0012\u0004\u0012\u0002H+0-H\u0080\bø\u0001\u0000¢\u0006\u0004\b*\u0010.J\b\u0010/\u001a\u000200H\u0016J\r\u00101\u001a\u00020\tH\u0010¢\u0006\u0002\b2J\b\u00103\u001a\u00020\tH\u0016J\b\u00104\u001a\u00020\rH\u0016J3\u00105\u001a\u0002002\u0006\u00106\u001a\u00020\u00032\u0014\u00107\u001a\u0010\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u000209\u0018\u0001082\u0006\u0010:\u001a\u00020\u0005H\u0000¢\u0006\u0002\b;J\u0015\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\u0001H\u0010¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020\t2\u0006\u0010=\u001a\u00020\u0001H\u0010¢\u0006\u0002\b@J\r\u0010A\u001a\u00020\tH\u0010¢\u0006\u0002\bBJ\u0015\u0010C\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0014H\u0010¢\u0006\u0002\bEJ\u0015\u0010F\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\bGJ\u0015\u0010H\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0005H\u0000¢\u0006\u0002\bIJ8\u0010J\u001a\u00020\u00002\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u00072\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H\u0016J\u001e\u0010K\u001a\u00020\u00012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H\u0016J\r\u0010L\u001a\u00020\tH\u0000¢\u0006\u0002\bMR\u001a\u0010\f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R4\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013@VX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u000fR\u0014\u0010#\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010 \u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006N"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "id", "", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "applied", "", "getApplied$runtime_release", "()Z", "setApplied$runtime_release", "(Z)V", "<set-?>", "", "Landroidx/compose/runtime/snapshots/StateObject;", "modified", "getModified$runtime_release", "()Ljava/util/Set;", "setModified", "(Ljava/util/Set;)V", "previousIds", "getPreviousIds$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setPreviousIds$runtime_release", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "getReadObserver$runtime_release", "()Lkotlin/jvm/functions/Function1;", "readOnly", "getReadOnly", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "snapshots", "getWriteObserver$runtime_release", "abandon", "advance", "advance$runtime_release", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "close", "close$runtime_release", "dispose", "hasPendingChanges", "innerApply", "snapshotId", "optimisticMerges", "", "Landroidx/compose/runtime/snapshots/StateRecord;", "invalidSnapshots", "innerApply$runtime_release", "nestedActivated", "snapshot", "nestedActivated$runtime_release", "nestedDeactivated", "nestedDeactivated$runtime_release", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime_release", "recordModified", "state", "recordModified$runtime_release", "recordPrevious", "recordPrevious$runtime_release", "recordPreviousList", "recordPreviousList$runtime_release", "takeNestedMutableSnapshot", "takeNestedSnapshot", "validateNotApplied", "validateNotApplied$runtime_release", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class MutableSnapshot extends Snapshot {
    public static final int $stable = 8;
    private boolean applied;
    private Set<StateObject> modified;
    private SnapshotIdSet previousIds;
    private final Function1<Object, Unit> readObserver;
    private int snapshots;
    private final Function1<Object, Unit> writeObserver;

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1<Object, Unit> getReadObserver$runtime_release() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1<Object, Unit> getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableSnapshot(int i, SnapshotIdSet invalid, Function1<Object, Unit> function1, Function1<Object, Unit> function12) {
        super(i, invalid, null);
        Intrinsics.checkNotNullParameter(invalid, "invalid");
        this.readObserver = function1;
        this.writeObserver = function12;
        this.previousIds = SnapshotIdSet.INSTANCE.getEMPTY();
        this.snapshots = 1;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        return modified$runtime_release != null && (modified$runtime_release.isEmpty() ^ true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MutableSnapshot takeNestedMutableSnapshot$default(MutableSnapshot mutableSnapshot, Function1 function1, Function1 function12, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takeNestedMutableSnapshot");
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        if ((i & 2) != 0) {
            function12 = null;
        }
        return mutableSnapshot.takeNestedMutableSnapshot(function1, function12);
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
        NestedMutableSnapshot nestedMutableSnapshot;
        validateNotDisposed$runtime_release();
        validateNotApplied$runtime_release();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            SnapshotIdSet invalid = getInvalid();
            setInvalid$runtime_release(invalid.set(i));
            nestedMutableSnapshot = new NestedMutableSnapshot(i, SnapshotKt.addRange(invalid, getId() + 1, i), SnapshotKt.mergedReadObserver(readObserver, getReadObserver$runtime_release()), SnapshotKt.mergedWriteObserver(writeObserver, getWriteObserver$runtime_release()), this);
        }
        int id = getId();
        synchronized (SnapshotKt.getLock()) {
            int i2 = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i2 + 1;
            setId$runtime_release(i2);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
            Unit unit = Unit.INSTANCE;
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
        return nestedMutableSnapshot;
    }

    public SnapshotApplyResult apply() {
        Map<StateRecord, ? extends StateRecord> mapOptimisticMerges;
        Pair pair;
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release != null) {
            Object obj = SnapshotKt.currentGlobalSnapshot.get();
            Intrinsics.checkNotNullExpressionValue(obj, "currentGlobalSnapshot.get()");
            mapOptimisticMerges = SnapshotKt.optimisticMerges((MutableSnapshot) obj, this, SnapshotKt.openSnapshots.clear(((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getId()));
        } else {
            mapOptimisticMerges = null;
        }
        synchronized (SnapshotKt.getLock()) {
            SnapshotKt.validateOpen(this);
            if (modified$runtime_release != null && modified$runtime_release.size() != 0) {
                GlobalSnapshot previousGlobalSnapshot = (GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get();
                SnapshotApplyResult snapshotApplyResultInnerApply$runtime_release = innerApply$runtime_release(SnapshotKt.nextSnapshotId, mapOptimisticMerges, SnapshotKt.openSnapshots.clear(previousGlobalSnapshot.getId()));
                if (!Intrinsics.areEqual(snapshotApplyResultInnerApply$runtime_release, SnapshotApplyResult.Success.INSTANCE)) {
                    return snapshotApplyResultInnerApply$runtime_release;
                }
                close$runtime_release();
                Intrinsics.checkNotNullExpressionValue(previousGlobalSnapshot, "previousGlobalSnapshot");
                SnapshotKt.takeNewGlobalSnapshot(previousGlobalSnapshot, SnapshotKt.emptyLambda);
                Set<StateObject> modified$runtime_release2 = previousGlobalSnapshot.getModified$runtime_release();
                setModified(null);
                previousGlobalSnapshot.setModified(null);
                pair = TuplesKt.to(CollectionsKt___CollectionsKt.toMutableList((Collection) SnapshotKt.applyObservers), modified$runtime_release2);
            } else {
                close$runtime_release();
                GlobalSnapshot previousGlobalSnapshot2 = (GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get();
                Intrinsics.checkNotNullExpressionValue(previousGlobalSnapshot2, "previousGlobalSnapshot");
                SnapshotKt.takeNewGlobalSnapshot(previousGlobalSnapshot2, SnapshotKt.emptyLambda);
                Set<StateObject> modified$runtime_release3 = previousGlobalSnapshot2.getModified$runtime_release();
                pair = (modified$runtime_release3 == null || !(modified$runtime_release3.isEmpty() ^ true)) ? TuplesKt.to(CollectionsKt__CollectionsKt.emptyList(), null) : TuplesKt.to(CollectionsKt___CollectionsKt.toMutableList((Collection) SnapshotKt.applyObservers), modified$runtime_release3);
            }
            List list = (List) pair.component1();
            Set set = (Set) pair.component2();
            this.applied = true;
            if (set != null && (!set.isEmpty())) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ((Function2) list.get(i)).mo4invoke(set, this);
                }
            }
            if (modified$runtime_release != null && (!modified$runtime_release.isEmpty())) {
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    ((Function2) list.get(i2)).mo4invoke(modified$runtime_release, this);
                }
            }
            return SnapshotApplyResult.Success.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        mo33nestedDeactivated$runtime_release(this);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver) {
        NestedReadonlySnapshot nestedReadonlySnapshot;
        validateNotDisposed$runtime_release();
        validateNotApplied$runtime_release();
        int id = getId();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(i, SnapshotKt.addRange(getInvalid(), id + 1, i), readObserver, this);
        }
        int id2 = getId();
        synchronized (SnapshotKt.getLock()) {
            int i2 = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i2 + 1;
            setId$runtime_release(i2);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
            Unit unit = Unit.INSTANCE;
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id2 + 1, getId()));
        return nestedReadonlySnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime_release */
    public void mo32nestedActivated$runtime_release(Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime_release */
    public void mo33nestedDeactivated$runtime_release(Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        int i = this.snapshots;
        if (!(i > 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int i2 = i - 1;
        this.snapshots = i2;
        if (i2 != 0 || this.applied) {
            return;
        }
        abandon();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        if (this.applied || getDisposed()) {
            return;
        }
        advance$runtime_release();
    }

    public final void validateNotApplied$runtime_release() {
        if (!(!this.applied)) {
            throw new IllegalArgumentException("Unsupported operation on a snapshot that has been applied".toString());
        }
    }

    private final void abandon() {
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release != null) {
            validateNotApplied$runtime_release();
            setModified(null);
            int id = getId();
            Iterator<StateObject> it = modified$runtime_release.iterator();
            while (it.hasNext()) {
                for (StateRecord firstStateRecord = it.next().getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext()) {
                    if (firstStateRecord.getSnapshotId() == id || CollectionsKt___CollectionsKt.contains(this.previousIds, Integer.valueOf(firstStateRecord.getSnapshotId()))) {
                        firstStateRecord.setSnapshotId$runtime_release(0);
                    }
                }
            }
        }
        close$runtime_release();
    }

    public final SnapshotApplyResult innerApply$runtime_release(int snapshotId, Map<StateRecord, ? extends StateRecord> optimisticMerges, SnapshotIdSet invalidSnapshots) {
        StateRecord stateRecord;
        Intrinsics.checkNotNullParameter(invalidSnapshots, "invalidSnapshots");
        SnapshotIdSet snapshotIdSetOr = getInvalid().set(getId()).or(this.previousIds);
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        Intrinsics.checkNotNull(modified$runtime_release);
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (StateObject stateObject : modified$runtime_release) {
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            StateRecord stateRecord2 = SnapshotKt.readable(firstStateRecord, snapshotId, invalidSnapshots);
            if (stateRecord2 != null && (stateRecord = SnapshotKt.readable(firstStateRecord, getId(), snapshotIdSetOr)) != null && !Intrinsics.areEqual(stateRecord2, stateRecord)) {
                StateRecord stateRecord3 = SnapshotKt.readable(firstStateRecord, getId(), getInvalid());
                if (stateRecord3 == null) {
                    SnapshotKt.readError();
                    throw new KotlinNothingValueException();
                }
                StateRecord stateRecordMergeRecords = optimisticMerges == null ? null : optimisticMerges.get(stateRecord2);
                if (stateRecordMergeRecords == null) {
                    stateRecordMergeRecords = stateObject.mergeRecords(stateRecord, stateRecord2, stateRecord3);
                }
                if (stateRecordMergeRecords == null) {
                    return new SnapshotApplyResult.Failure(this);
                }
                if (!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord3)) {
                    if (Intrinsics.areEqual(stateRecordMergeRecords, stateRecord2)) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(TuplesKt.to(stateObject, stateRecord2.create()));
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        arrayList2.add(stateObject);
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord) ? TuplesKt.to(stateObject, stateRecordMergeRecords) : TuplesKt.to(stateObject, stateRecord.create()));
                    }
                }
            }
        }
        if (arrayList != null) {
            advance$runtime_release();
            int i = 0;
            int size = arrayList.size();
            while (i < size) {
                int i2 = i + 1;
                Pair pair = (Pair) arrayList.get(i);
                StateObject stateObject2 = (StateObject) pair.component1();
                StateRecord stateRecord4 = (StateRecord) pair.component2();
                stateRecord4.setSnapshotId$runtime_release(getId());
                synchronized (SnapshotKt.getLock()) {
                    stateRecord4.setNext$runtime_release(stateObject2.getFirstStateRecord());
                    stateObject2.prependStateRecord(stateRecord4);
                    Unit unit = Unit.INSTANCE;
                }
                i = i2;
            }
        }
        if (arrayList2 != null) {
            modified$runtime_release.removeAll(arrayList2);
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    public final <T> T advance$runtime_release(Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        recordPrevious$runtime_release(getId());
        T tInvoke = block.invoke();
        int id = getId();
        synchronized (SnapshotKt.getLock()) {
            try {
                int i = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i + 1;
                setId$runtime_release(i);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
        return tInvoke;
    }

    public final void advance$runtime_release() {
        recordPrevious$runtime_release(getId());
        Unit unit = Unit.INSTANCE;
        int id = getId();
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            setId$runtime_release(i);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime_release */
    public void mo34recordModified$runtime_release(StateObject state) {
        Intrinsics.checkNotNullParameter(state, "state");
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release == null) {
            modified$runtime_release = new HashSet<>();
            setModified(modified$runtime_release);
        }
        modified$runtime_release.add(state);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Set<StateObject> getModified$runtime_release() {
        return this.modified;
    }

    public void setModified(Set<StateObject> set) {
        this.modified = set;
    }

    /* JADX INFO: renamed from: getPreviousIds$runtime_release, reason: from getter */
    public final SnapshotIdSet getPreviousIds() {
        return this.previousIds;
    }

    public final void setPreviousIds$runtime_release(SnapshotIdSet snapshotIdSet) {
        Intrinsics.checkNotNullParameter(snapshotIdSet, "<set-?>");
        this.previousIds = snapshotIdSet;
    }

    /* JADX INFO: renamed from: getApplied$runtime_release, reason: from getter */
    public final boolean getApplied() {
        return this.applied;
    }

    public final void setApplied$runtime_release(boolean z) {
        this.applied = z;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void close$runtime_release() {
        synchronized (SnapshotKt.getLock()) {
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId()).andNot(getPreviousIds());
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPrevious$runtime_release(int id) {
        synchronized (SnapshotKt.getLock()) {
            setPreviousIds$runtime_release(getPreviousIds().set(id));
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPreviousList$runtime_release(SnapshotIdSet snapshots) {
        Intrinsics.checkNotNullParameter(snapshots, "snapshots");
        synchronized (SnapshotKt.getLock()) {
            setPreviousIds$runtime_release(getPreviousIds().or(snapshots));
            Unit unit = Unit.INSTANCE;
        }
    }
}
