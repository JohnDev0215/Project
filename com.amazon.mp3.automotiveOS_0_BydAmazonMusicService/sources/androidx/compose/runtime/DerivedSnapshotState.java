package androidx.compose.runtime;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateRecord;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: androidx.compose.runtime.DerivedSnapshotState, reason: from toString */
/* JADX INFO: compiled from: DerivedState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001#B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J2\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010\"\u001a\u00020\u001fH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\u0004\u0018\u00018\u00008G¢\u0006\f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\tR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\t¨\u0006$"}, d2 = {"Landroidx/compose/runtime/DerivedSnapshotState;", "T", "Landroidx/compose/runtime/snapshots/StateObject;", "Landroidx/compose/runtime/DerivedState;", "calculation", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "currentValue", "getCurrentValue", "()Ljava/lang/Object;", "debuggerDisplayValue", "getDebuggerDisplayValue$annotations", "()V", "getDebuggerDisplayValue", "dependencies", "", "getDependencies", "()Ljava/util/Set;", "first", "Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;", "firstStateRecord", "Landroidx/compose/runtime/snapshots/StateRecord;", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "value", "getValue", "currentRecord", "readable", "snapshot", "Landroidx/compose/runtime/snapshots/Snapshot;", "displayValue", "", "prependStateRecord", "", "toString", "ResultRecord", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
final class DerivedState<T> implements StateObject, androidx.compose.runtime.DerivedState<T> {
    private final Function0<T> calculation;
    private ResultRecord<T> first;

    public static /* synthetic */ void getDebuggerDisplayValue$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DerivedState(Function0<? extends T> calculation) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        this.calculation = calculation;
        this.first = new ResultRecord<>();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        return StateObject.DefaultImpls.mergeRecords(this, stateRecord, stateRecord2, stateRecord3);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.DerivedSnapshotState$ResultRecord */
    /* JADX INFO: compiled from: DerivedState.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\b\u0010\u001b\u001a\u00020\u0002H\u0016J\u001a\u0010\u001c\u001a\u00020\u001d2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!J\u001a\u0010\"\u001a\u00020\u00132\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!R.\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0004\u0018\u00018\u0001X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;", "T", "Landroidx/compose/runtime/snapshots/StateRecord;", "()V", "dependencies", "Ljava/util/HashSet;", "Landroidx/compose/runtime/snapshots/StateObject;", "Lkotlin/collections/HashSet;", "getDependencies", "()Ljava/util/HashSet;", "setDependencies", "(Ljava/util/HashSet;)V", "result", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "resultHash", "", "getResultHash", "()I", "setResultHash", "(I)V", "assign", "", "value", "create", "isValid", "", "derivedState", "Landroidx/compose/runtime/DerivedState;", "snapshot", "Landroidx/compose/runtime/snapshots/Snapshot;", "readableHash", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class ResultRecord<T> extends StateRecord {
        private HashSet<StateObject> dependencies;
        private T result;
        private int resultHash;

        public final HashSet<StateObject> getDependencies() {
            return this.dependencies;
        }

        public final void setDependencies(HashSet<StateObject> hashSet) {
            this.dependencies = hashSet;
        }

        public final T getResult() {
            return this.result;
        }

        public final void setResult(T t) {
            this.result = t;
        }

        public final int getResultHash() {
            return this.resultHash;
        }

        public final void setResultHash(int i) {
            this.resultHash = i;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord value) {
            Intrinsics.checkNotNullParameter(value, "value");
            ResultRecord resultRecord = (ResultRecord) value;
            this.dependencies = resultRecord.dependencies;
            this.result = resultRecord.result;
            this.resultHash = resultRecord.resultHash;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new ResultRecord();
        }

        public final boolean isValid(androidx.compose.runtime.DerivedState<?> derivedState, Snapshot snapshot) {
            Intrinsics.checkNotNullParameter(derivedState, "derivedState");
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            return this.result != null && this.resultHash == readableHash(derivedState, snapshot);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final int readableHash(androidx.compose.runtime.DerivedState<?> derivedState, Snapshot snapshot) {
            HashSet<StateObject> dependencies;
            Intrinsics.checkNotNullParameter(derivedState, "derivedState");
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            synchronized (SnapshotKt.getLock()) {
                dependencies = getDependencies();
            }
            int iIdentityHashCode = 7;
            if (dependencies != null) {
                PersistentList persistentListPersistentListOf = (PersistentList) SnapshotStateKt__DerivedStateKt.derivedStateObservers.get();
                if (persistentListPersistentListOf == null) {
                    persistentListPersistentListOf = ExtensionsKt.persistentListOf();
                }
                int size = persistentListPersistentListOf.size();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    ((Function1) ((Pair) persistentListPersistentListOf.get(i2)).component1()).invoke(derivedState);
                }
                try {
                    for (StateObject stateObject : dependencies) {
                        StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                        Intrinsics.checkNotNullExpressionValue(stateObject, "stateObject");
                        StateRecord stateRecord = SnapshotKt.readable(firstStateRecord, stateObject, snapshot);
                        iIdentityHashCode = (((iIdentityHashCode * 31) + ActualJvm_jvmKt.identityHashCode(stateRecord)) * 31) + stateRecord.getSnapshotId();
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                    int size2 = persistentListPersistentListOf.size();
                    while (i < size2) {
                        ((Function1) ((Pair) persistentListPersistentListOf.get(i)).component2()).invoke(derivedState);
                        i++;
                    }
                }
            }
            return iIdentityHashCode;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ResultRecord<T> currentRecord(ResultRecord<T> readable, Snapshot snapshot, Function0<? extends T> calculation) {
        Snapshot.Companion companion;
        ResultRecord<T> resultRecord;
        if (readable.isValid(this, snapshot)) {
            return readable;
        }
        Boolean bool = (Boolean) SnapshotStateKt__DerivedStateKt.isCalculationBlockRunning.get();
        int i = 0;
        boolean zBooleanValue = bool == null ? false : bool.booleanValue();
        final HashSet hashSet = new HashSet();
        PersistentList persistentListPersistentListOf = (PersistentList) SnapshotStateKt__DerivedStateKt.derivedStateObservers.get();
        if (persistentListPersistentListOf == null) {
            persistentListPersistentListOf = ExtensionsKt.persistentListOf();
        }
        int size = persistentListPersistentListOf.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Function1) ((Pair) persistentListPersistentListOf.get(i2)).component1()).invoke(this);
        }
        if (!zBooleanValue) {
            try {
                SnapshotStateKt__DerivedStateKt.isCalculationBlockRunning.set(Boolean.TRUE);
            } finally {
                int size2 = persistentListPersistentListOf.size();
                while (i < size2) {
                    ((Function1) ((Pair) persistentListPersistentListOf.get(i)).component2()).invoke(this);
                    i++;
                }
            }
        }
        Object objObserve = Snapshot.INSTANCE.observe(new Function1<Object, Unit>(this) { // from class: androidx.compose.runtime.DerivedSnapshotState$currentRecord$result$1$result$1
            final /* synthetic */ DerivedState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it == this.this$0) {
                    throw new IllegalStateException("A derived state calculation cannot read itself".toString());
                }
                if (it instanceof StateObject) {
                    hashSet.add(it);
                }
            }
        }, null, calculation);
        if (!zBooleanValue) {
            SnapshotStateKt__DerivedStateKt.isCalculationBlockRunning.set(Boolean.FALSE);
        }
        synchronized (SnapshotKt.getLock()) {
            companion = Snapshot.INSTANCE;
            Snapshot current = companion.getCurrent();
            resultRecord = (ResultRecord<T>) ((ResultRecord) SnapshotKt.newWritableRecord(this.first, this, current));
            resultRecord.setDependencies(hashSet);
            resultRecord.setResultHash(resultRecord.readableHash(this, current));
            resultRecord.setResult(objObserve);
        }
        if (!zBooleanValue) {
            companion.notifyObjectsInitialized();
        }
        return resultRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.first;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.first = (ResultRecord) value;
    }

    @Override // androidx.compose.runtime.State
    public T getValue() {
        Function1<Object, Unit> readObserver$runtime_release = Snapshot.INSTANCE.getCurrent().getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(this);
        }
        return getCurrentValue();
    }

    @Override // androidx.compose.runtime.DerivedState
    public T getCurrentValue() {
        ResultRecord<T> resultRecord = this.first;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        return currentRecord((ResultRecord) SnapshotKt.current(resultRecord, companion.getCurrent()), companion.getCurrent(), this.calculation).getResult();
    }

    @Override // androidx.compose.runtime.DerivedState
    public Set<StateObject> getDependencies() {
        ResultRecord<T> resultRecord = this.first;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        HashSet<StateObject> dependencies = currentRecord((ResultRecord) SnapshotKt.current(resultRecord, companion.getCurrent()), companion.getCurrent(), this.calculation).getDependencies();
        return dependencies == null ? SetsKt__SetsKt.emptySet() : dependencies;
    }

    public String toString() {
        return "DerivedState(value=" + displayValue() + ")@" + hashCode();
    }

    @JvmName(name = "getDebuggerDisplayValue")
    public final T getDebuggerDisplayValue() {
        ResultRecord<T> resultRecord = this.first;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        ResultRecord resultRecord2 = (ResultRecord) SnapshotKt.current(resultRecord, companion.getCurrent());
        if (resultRecord2.isValid(this, companion.getCurrent())) {
            return (T) resultRecord2.getResult();
        }
        return null;
    }

    private final String displayValue() {
        ResultRecord<T> resultRecord = this.first;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        ResultRecord resultRecord2 = (ResultRecord) SnapshotKt.current(resultRecord, companion.getCurrent());
        return resultRecord2.isValid(this, companion.getCurrent()) ? String.valueOf(resultRecord2.getResult()) : "<Not calculated>";
    }
}
