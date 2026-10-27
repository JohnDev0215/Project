package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionGroup;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0010H\u0096\u0002J\t\u0010\u0011\u001a\u00020\u0002H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/runtime/GroupIterator;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "table", "Landroidx/compose/runtime/SlotTable;", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "", "end", "(Landroidx/compose/runtime/SlotTable;II)V", "getEnd", "()I", "index", "getTable", "()Landroidx/compose/runtime/SlotTable;", "version", "hasNext", "", "next", "validateRead", "", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
final class GroupIterator implements Iterator<CompositionGroup>, KMappedMarker {
    private final int end;
    private int index;
    private final SlotTable table;
    private final int version;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public GroupIterator(SlotTable table, int i, int i2) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
        this.end = i2;
        this.index = i;
        this.version = table.getVersion();
        if (table.getWriter()) {
            throw new ConcurrentModificationException();
        }
    }

    public final SlotTable getTable() {
        return this.table;
    }

    public final int getEnd() {
        return this.end;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.end;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public CompositionGroup next() {
        validateRead();
        int i = this.index;
        this.index = SlotTableKt.groupSize(this.table.getGroups(), i) + i;
        return new AnonymousClass1(i);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.GroupIterator$next$1, reason: invalid class name */
    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016H\u0096\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001c\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0005R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"androidx/compose/runtime/GroupIterator$next$1", "Landroidx/compose/runtime/tooling/CompositionGroup;", "", "compositionGroups", "getCompositionGroups", "()Ljava/lang/Iterable;", "data", "", "getData", "isEmpty", "", "()Z", "key", "getKey", "()Ljava/lang/Object;", "node", "getNode", "sourceInfo", "", "getSourceInfo", "()Ljava/lang/String;", "iterator", "", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements CompositionGroup, Iterable<CompositionGroup>, KMappedMarker {
        final /* synthetic */ int $group;

        @Override // androidx.compose.runtime.tooling.CompositionData
        public Iterable<CompositionGroup> getCompositionGroups() {
            return this;
        }

        AnonymousClass1(int i) {
            this.$group = i;
        }

        @Override // androidx.compose.runtime.tooling.CompositionData
        public boolean isEmpty() {
            return SlotTableKt.groupSize(GroupIterator.this.getTable().getGroups(), this.$group) == 0;
        }

        @Override // androidx.compose.runtime.tooling.CompositionGroup
        public Object getKey() {
            if (SlotTableKt.hasObjectKey(GroupIterator.this.getTable().getGroups(), this.$group)) {
                Object obj = GroupIterator.this.getTable().getSlots()[SlotTableKt.objectKeyIndex(GroupIterator.this.getTable().getGroups(), this.$group)];
                Intrinsics.checkNotNull(obj);
                return obj;
            }
            return Integer.valueOf(SlotTableKt.key(GroupIterator.this.getTable().getGroups(), this.$group));
        }

        @Override // androidx.compose.runtime.tooling.CompositionGroup
        public String getSourceInfo() {
            if (!SlotTableKt.hasAux(GroupIterator.this.getTable().getGroups(), this.$group)) {
                return null;
            }
            Object obj = GroupIterator.this.getTable().getSlots()[SlotTableKt.auxIndex(GroupIterator.this.getTable().getGroups(), this.$group)];
            if (obj instanceof String) {
                return (String) obj;
            }
            return null;
        }

        @Override // androidx.compose.runtime.tooling.CompositionGroup
        public Object getNode() {
            if (SlotTableKt.isNode(GroupIterator.this.getTable().getGroups(), this.$group)) {
                return GroupIterator.this.getTable().getSlots()[SlotTableKt.nodeIndex(GroupIterator.this.getTable().getGroups(), this.$group)];
            }
            return null;
        }

        @Override // androidx.compose.runtime.tooling.CompositionGroup
        public Iterable<Object> getData() {
            return new GroupIterator$next$1$data$1(SlotTableKt.dataAnchor(GroupIterator.this.getTable().getGroups(), this.$group), this.$group + 1 < GroupIterator.this.getTable().getGroupsSize() ? SlotTableKt.dataAnchor(GroupIterator.this.getTable().getGroups(), this.$group + 1) : GroupIterator.this.getTable().getSlotsSize(), GroupIterator.this);
        }

        @Override // java.lang.Iterable
        public Iterator<CompositionGroup> iterator() {
            GroupIterator.this.validateRead();
            SlotTable table = GroupIterator.this.getTable();
            int i = this.$group;
            return new GroupIterator(table, i + 1, i + SlotTableKt.groupSize(GroupIterator.this.getTable().getGroups(), this.$group));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void validateRead() {
        if (this.table.getVersion() != this.version) {
            throw new ConcurrentModificationException();
        }
    }
}
