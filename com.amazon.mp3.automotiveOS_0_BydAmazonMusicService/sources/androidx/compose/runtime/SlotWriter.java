package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0017\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b0\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\nJ\u0010\u00102\u001a\u00020\u00072\b\b\u0002\u00103\u001a\u00020\nJ\u000e\u00104\u001a\u00020\n2\u0006\u00102\u001a\u00020\u0007J\u0006\u00105\u001a\u000200J\u0006\u00106\u001a\u000200J \u00107\u001a\u00020\n2\u0006\u00102\u001a\u00020\n2\u0006\u00108\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u00109\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002J\u0010\u0010:\u001a\u00020\n2\u0006\u00109\u001a\u00020\nH\u0002J(\u0010;\u001a\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u0010<\u001a\u00020\n2\u0006\u00108\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010=\u001a\u00020\nJ\u0006\u0010>\u001a\u000200J\u000e\u0010?\u001a\u0002002\u0006\u00102\u001a\u00020\u0007J\u000e\u0010?\u001a\u0002002\u0006\u00103\u001a\u00020\nJ \u0010@\u001a\u0002002\u0006\u0010\"\u001a\u00020\n2\u0006\u0010=\u001a\u00020\n2\u0006\u0010A\u001a\u00020\nH\u0002J\u0010\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\nJ\u0010\u0010C\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002J\u000e\u0010D\u001a\u00020\n2\u0006\u00103\u001a\u00020\nJ\u0010\u0010E\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\nJ\u000e\u0010F\u001a\u00020\n2\u0006\u00103\u001a\u00020\nJ\u000e\u0010G\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010HJ\u0006\u0010I\u001a\u00020JJ\u0010\u0010K\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u0010\u0010M\u001a\u0002002\u0006\u0010$\u001a\u00020\nH\u0002J\u0018\u0010N\u001a\u0002002\u0006\u0010$\u001a\u00020\n2\u0006\u0010O\u001a\u00020\nH\u0002J\u000e\u0010P\u001a\b\u0012\u0004\u0012\u00020\n0QH\u0002J \u0010R\u001a\u0002002\u0006\u0010S\u001a\u00020\n2\u0006\u0010T\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0002J\u001c\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00070Q2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00103\u001a\u00020\nJ\u000e\u0010V\u001a\u0002002\u0006\u0010W\u001a\u00020\nJ\u0010\u0010X\u001a\u0002002\u0006\u00103\u001a\u00020\nH\u0002J\u0018\u0010Y\u001a\u0002002\u0006\u00103\u001a\u00020\n2\u0006\u0010O\u001a\u00020\nH\u0002J\u0010\u0010Z\u001a\u0004\u0018\u00010\u00012\u0006\u00102\u001a\u00020\u0007J\u0010\u0010Z\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\nJ\u000e\u0010\"\u001a\u00020\n2\u0006\u00102\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020\n2\u0006\u00103\u001a\u00020\nJ\u0010\u0010[\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002J\u0018\u0010\\\u001a\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u0010<\u001a\u00020\nH\u0002J\u0018\u0010]\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0002J\u0006\u0010^\u001a\u00020\u000eJ\u0018\u0010_\u001a\u00020\u000e2\u0006\u0010`\u001a\u00020\n2\u0006\u0010a\u001a\u00020\nH\u0002J \u0010b\u001a\u0002002\u0006\u0010`\u001a\u00020\n2\u0006\u0010a\u001a\u00020\n2\u0006\u0010O\u001a\u00020\nH\u0002J\b\u0010c\u001a\u00020\nH\u0002J\b\u0010d\u001a\u000200H\u0002J\u000e\u0010e\u001a\u0002002\u0006\u00102\u001a\u00020\u0007J\u0010\u0010f\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u001a\u0010f\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\n2\b\u0010L\u001a\u0004\u0018\u00010\u0001J\b\u0010g\u001a\u0004\u0018\u00010\u0001J\u0006\u0010h\u001a\u00020\nJ\u0006\u0010i\u001a\u000200J\u0018\u0010j\u001a\u0002002\u0006\u0010k\u001a\u00020\n2\b\u0010l\u001a\u0004\u0018\u00010\u0001J\"\u0010j\u001a\u0002002\u0006\u0010k\u001a\u00020\n2\b\u0010m\u001a\u0004\u0018\u00010\u00012\b\u0010l\u001a\u0004\u0018\u00010\u0001J\u0006\u0010n\u001a\u000200J\u000e\u0010n\u001a\u0002002\u0006\u0010k\u001a\u00020\nJ\u0018\u0010n\u001a\u0002002\u0006\u0010k\u001a\u00020\n2\b\u0010o\u001a\u0004\u0018\u00010\u0001J,\u0010n\u001a\u0002002\u0006\u0010k\u001a\u00020\n2\b\u0010m\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001f\u001a\u00020\u000e2\b\u0010l\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010p\u001a\u0002002\b\u0010k\u001a\u0004\u0018\u00010\u0001J\u001a\u0010p\u001a\u0002002\b\u0010k\u001a\u0004\u0018\u00010\u00012\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\b\u0010q\u001a\u00020JH\u0016J\u0012\u0010r\u001a\u0004\u0018\u00010\u00012\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u0018\u0010s\u001a\u0002002\u0006\u0010t\u001a\u00020\n2\u0006\u0010u\u001a\u00020\nH\u0002J\u0010\u0010v\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u0018\u0010w\u001a\u0002002\u0006\u00102\u001a\u00020\u00072\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u0010\u0010w\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\u001a\u0010x\u001a\u0002002\u0006\u00103\u001a\u00020\n2\b\u0010L\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010y\u001a\u0002002\b\u0010L\u001a\u0004\u0018\u00010\u0001J\r\u0010z\u001a\u000200H\u0000¢\u0006\u0002\b{J\r\u0010|\u001a\u000200H\u0000¢\u0006\u0002\b}J\u0014\u0010~\u001a\u00020\n*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\nH\u0002J\u0014\u00109\u001a\u00020\n*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\nH\u0002J\u0013\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\n0Q*\u00020\u001cH\u0002J\u001b\u0010\u0081\u0001\u001a\u000200*\b0\u0082\u0001j\u0003`\u0083\u00012\u0006\u00103\u001a\u00020\nH\u0002J\u0015\u0010\u0084\u0001\u001a\u00020\n*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\nH\u0002J\u0014\u0010\"\u001a\u00020\n*\u00020\u001c2\u0006\u00103\u001a\u00020\nH\u0002J\u0015\u0010\u0085\u0001\u001a\u00020\n*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\nH\u0002J\u001d\u0010\u0086\u0001\u001a\u000200*\u00020\u001c2\u0006\u0010\u007f\u001a\u00020\n2\u0006\u00109\u001a\u00020\nH\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0011R\u0011\u0010\u001f\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0011R\u000e\u0010 \u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\fR\u0014\u0010$\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\fR\u0018\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010'X\u0082\u000e¢\u0006\u0004\n\u0002\u0010(R\u000e\u0010)\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u0006\u0087\u0001"}, d2 = {"Landroidx/compose/runtime/SlotWriter;", "", "table", "Landroidx/compose/runtime/SlotTable;", "(Landroidx/compose/runtime/SlotTable;)V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "capacity", "", "getCapacity", "()I", "<set-?>", "", "closed", "getClosed", "()Z", "currentGroup", "getCurrentGroup", "currentGroupEnd", "currentSlot", "currentSlotEnd", "endStack", "Landroidx/compose/runtime/IntStack;", "groupGapLen", "groupGapStart", "groups", "", "insertCount", "isGroupEnd", "isNode", "nodeCount", "nodeCountStack", "parent", "getParent", "size", "getSize$runtime_release", "slots", "", "[Ljava/lang/Object;", "slotsGapLen", "slotsGapOwner", "slotsGapStart", "startStack", "getTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "advanceBy", "", "amount", "anchor", "index", "anchorIndex", "beginInsert", "close", "dataAnchorToDataIndex", "gapLen", "dataIndex", "dataIndexToDataAddress", "dataIndexToDataAnchor", "gapStart", "endGroup", "endInsert", "ensureStarted", "fixParentAnchorsFor", "firstChild", "groupAux", "groupIndexToAddress", "groupKey", "groupObjectKey", "groupSize", "groupSlots", "", "groupsAsString", "", "insertAux", "value", "insertGroups", "insertSlots", "group", "keys", "", "moveAnchors", "originalLocation", "newLocation", "moveFrom", "moveGroup", "offset", "moveGroupGapTo", "moveSlotGapTo", "node", "parentAnchorToIndex", "parentIndexToAnchor", "removeAnchors", "removeGroup", "removeGroups", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "len", "removeSlots", "restoreCurrentGroupEnd", "saveCurrentGroupEnd", "seek", "set", "skip", "skipGroup", "skipToGroupEnd", "startData", "key", "aux", "objectKey", "startGroup", "dataKey", "startNode", "toString", "update", "updateAnchors", "previousGapStart", "newGapStart", "updateAux", "updateNode", "updateNodeOfGroup", "updateParentNode", "verifyDataAnchors", "verifyDataAnchors$runtime_release", "verifyParentAnchors", "verifyParentAnchors$runtime_release", "auxIndex", "address", "dataIndexes", "groupAsString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "nodeIndex", "slotIndex", "updateDataIndex", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SlotWriter {
    private ArrayList<Anchor> anchors;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private final IntStack endStack;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private final IntStack nodeCountStack;
    private int parent;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private final IntStack startStack;
    private final SlotTable table;

    private final int dataAnchorToDataIndex(int anchor, int gapLen, int capacity) {
        return anchor < 0 ? (capacity - gapLen) + anchor + 1 : anchor;
    }

    private final int dataIndexToDataAnchor(int index, int gapStart, int gapLen, int capacity) {
        return index > gapStart ? -(((capacity - gapLen) - index) + 1) : index;
    }

    public SlotWriter(SlotTable table) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
        this.groups = table.getGroups();
        this.slots = table.getSlots();
        this.anchors = table.getAnchors$runtime_release();
        this.groupGapStart = table.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - table.getGroupsSize();
        this.currentGroupEnd = table.getGroupsSize();
        this.slotsGapStart = table.getSlotsSize();
        this.slotsGapLen = this.slots.length - table.getSlotsSize();
        this.slotsGapOwner = table.getGroupsSize();
        this.startStack = new IntStack();
        this.endStack = new IntStack();
        this.nodeCountStack = new IntStack();
        this.parent = -1;
    }

    /* JADX INFO: renamed from: getTable$runtime_release, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final boolean isGroupEnd() {
        return this.currentGroup == this.currentGroupEnd;
    }

    public final boolean isNode() {
        int i = this.currentGroup;
        return i < this.currentGroupEnd && SlotTableKt.isNode(this.groups, groupIndexToAddress(i));
    }

    public final int groupKey(int index) {
        return SlotTableKt.key(this.groups, groupIndexToAddress(index));
    }

    public final Object groupObjectKey(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        if (SlotTableKt.hasObjectKey(this.groups, iGroupIndexToAddress)) {
            return this.slots[SlotTableKt.objectKeyIndex(this.groups, iGroupIndexToAddress)];
        }
        return null;
    }

    public final int groupSize(int index) {
        return SlotTableKt.groupSize(this.groups, groupIndexToAddress(index));
    }

    public final Object groupAux(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        return SlotTableKt.hasAux(this.groups, iGroupIndexToAddress) ? this.slots[auxIndex(this.groups, iGroupIndexToAddress)] : Composer.INSTANCE.getEmpty();
    }

    public final Object node(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress)) {
            return this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))];
        }
        return null;
    }

    public final Object node(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        return node(anchor.toIndexFor(this));
    }

    public final int getParent() {
        return this.parent;
    }

    public final int parent(int index) {
        return parent(this.groups, index);
    }

    public final int parent(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (anchor.getValid()) {
            return parent(this.groups, anchorIndex(anchor));
        }
        return -1;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final void close() {
        this.closed = true;
        moveGroupGapTo(getSize$runtime_release());
        moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
        this.table.close$runtime_release(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors);
    }

    public final Object update(Object value) {
        Object objSkip = skip();
        set(value);
        return objSkip;
    }

    public final void updateAux(Object value) {
        int iGroupIndexToAddress = groupIndexToAddress(this.currentGroup);
        if (SlotTableKt.hasAux(this.groups, iGroupIndexToAddress)) {
            this.slots[dataIndexToDataAddress(auxIndex(this.groups, iGroupIndexToAddress))] = value;
        } else {
            ComposerKt.composeRuntimeError("Updating the data of a group that was not created with a data slot".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final void insertAux(Object value) {
        if (this.insertCount >= 0) {
            int i = this.parent;
            int iGroupIndexToAddress = groupIndexToAddress(i);
            if (!SlotTableKt.hasAux(this.groups, iGroupIndexToAddress)) {
                insertSlots(1, i);
                int iAuxIndex = auxIndex(this.groups, iGroupIndexToAddress);
                int iDataIndexToDataAddress = dataIndexToDataAddress(iAuxIndex);
                int i2 = this.currentSlot;
                if (i2 > iAuxIndex) {
                    int i3 = i2 - iAuxIndex;
                    if (!(i3 < 3)) {
                        throw new IllegalStateException("Moving more than two slot not supported".toString());
                    }
                    if (i3 > 1) {
                        Object[] objArr = this.slots;
                        objArr[iDataIndexToDataAddress + 2] = objArr[iDataIndexToDataAddress + 1];
                    }
                    Object[] objArr2 = this.slots;
                    objArr2[iDataIndexToDataAddress + 1] = objArr2[iDataIndexToDataAddress];
                }
                SlotTableKt.addAux(this.groups, iGroupIndexToAddress);
                this.slots[iDataIndexToDataAddress] = value;
                this.currentSlot++;
                return;
            }
            ComposerKt.composeRuntimeError("Group already has auxiliary data".toString());
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("Cannot insert auxiliary data when not inserting".toString());
        throw new KotlinNothingValueException();
    }

    public final void updateNode(Object value) {
        updateNodeOfGroup(this.currentGroup, value);
    }

    public final void updateNode(Anchor anchor, Object value) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        updateNodeOfGroup(anchor.toIndexFor(this), value);
    }

    public final void updateParentNode(Object value) {
        updateNodeOfGroup(this.parent, value);
    }

    public final void set(Object value) {
        int i = this.currentSlot;
        if (i <= this.currentSlotEnd) {
            this.slots[dataIndexToDataAddress(i - 1)] = value;
        } else {
            ComposerKt.composeRuntimeError("Writing to an invalid slot".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final Object set(int index, Object value) {
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int i = iSlotIndex + index;
        if (!(i >= iSlotIndex && i < dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1)))) {
            ComposerKt.composeRuntimeError(("Write to an invalid slot index " + index + " for group " + getCurrentGroup()).toString());
            throw new KotlinNothingValueException();
        }
        int iDataIndexToDataAddress = dataIndexToDataAddress(i);
        Object[] objArr = this.slots;
        Object obj = objArr[iDataIndexToDataAddress];
        objArr[iDataIndexToDataAddress] = value;
        return obj;
    }

    public final Object skip() {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        return objArr[dataIndexToDataAddress(i)];
    }

    public final void advanceBy(int amount) {
        if (!(amount >= 0)) {
            throw new IllegalArgumentException("Cannot seek backwards".toString());
        }
        if (!(this.insertCount <= 0)) {
            throw new IllegalStateException("Cannot call seek() while inserting".toString());
        }
        int i = this.currentGroup + amount;
        if (!(i >= this.parent && i <= this.currentGroupEnd)) {
            ComposerKt.composeRuntimeError(("Cannot seek outside the current group (" + getParent() + '-' + this.currentGroupEnd + ')').toString());
            throw new KotlinNothingValueException();
        }
        this.currentGroup = i;
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i));
        this.currentSlot = iDataIndex;
        this.currentSlotEnd = iDataIndex;
    }

    public final void seek(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        advanceBy(anchor.toIndexFor(this) - this.currentGroup);
    }

    public final void skipToGroupEnd() {
        int i = this.currentGroupEnd;
        this.currentGroup = i;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(i));
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            saveCurrentGroupEnd();
        }
    }

    public final void endInsert() {
        int i = this.insertCount;
        if (!(i > 0)) {
            throw new IllegalStateException("Unbalanced begin/end insert".toString());
        }
        int i2 = i - 1;
        this.insertCount = i2;
        if (i2 == 0) {
            if (this.nodeCountStack.getTos() == this.startStack.getTos()) {
                restoreCurrentGroupEnd();
            } else {
                ComposerKt.composeRuntimeError("startGroup/endGroup mismatch while inserting".toString());
                throw new KotlinNothingValueException();
            }
        }
    }

    public final void startGroup() {
        if (!(this.insertCount == 0)) {
            throw new IllegalArgumentException("Key must be supplied when inserting".toString());
        }
        Composer.Companion companion = Composer.INSTANCE;
        startGroup(0, companion.getEmpty(), false, companion.getEmpty());
    }

    public final void startGroup(int key) {
        Composer.Companion companion = Composer.INSTANCE;
        startGroup(key, companion.getEmpty(), false, companion.getEmpty());
    }

    public final void startGroup(int key, Object dataKey) {
        startGroup(key, dataKey, false, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(Object key) {
        startGroup(125, key, true, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(Object key, Object node) {
        startGroup(125, key, true, node);
    }

    public final void startData(int key, Object objectKey, Object aux) {
        startGroup(key, objectKey, false, aux);
    }

    public final void startData(int key, Object aux) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, aux);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void startGroup(int key, Object objectKey, boolean isNode, Object aux) {
        int iGroupSize;
        byte b = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        if (b != false) {
            insertGroups(1);
            int i = this.currentGroup;
            int iGroupIndexToAddress = groupIndexToAddress(i);
            Composer.Companion companion = Composer.INSTANCE;
            int i2 = objectKey != companion.getEmpty() ? 1 : 0;
            int i3 = (isNode || aux == companion.getEmpty()) ? 0 : 1;
            SlotTableKt.initGroup(this.groups, iGroupIndexToAddress, key, isNode, i2, i3, this.parent, this.currentSlot);
            this.currentSlotEnd = this.currentSlot;
            int i4 = (isNode ? 1 : 0) + i2 + i3;
            if (i4 > 0) {
                insertSlots(i4, i);
                Object[] objArr = this.slots;
                int i5 = this.currentSlot;
                if (isNode) {
                    objArr[i5] = aux;
                    i5++;
                }
                if (i2 != 0) {
                    objArr[i5] = objectKey;
                    i5++;
                }
                if (i3 != 0) {
                    objArr[i5] = aux;
                    i5++;
                }
                this.currentSlot = i5;
            }
            this.nodeCount = 0;
            iGroupSize = i + 1;
            this.parent = i;
            this.currentGroup = iGroupSize;
        } else {
            this.startStack.push(this.parent);
            saveCurrentGroupEnd();
            int i6 = this.currentGroup;
            int iGroupIndexToAddress2 = groupIndexToAddress(i6);
            if (!Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty())) {
                if (isNode) {
                    updateNode(aux);
                } else {
                    updateAux(aux);
                }
            }
            this.currentSlot = slotIndex(this.groups, iGroupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            this.nodeCount = SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress2);
            this.parent = i6;
            this.currentGroup = i6 + 1;
            iGroupSize = i6 + SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2);
        }
        this.currentGroupEnd = iGroupSize;
    }

    public final int endGroup() {
        boolean z = this.insertCount > 0;
        int i = this.currentGroup;
        int i2 = this.currentGroupEnd;
        int i3 = this.parent;
        int iGroupIndexToAddress = groupIndexToAddress(i3);
        int i4 = this.nodeCount;
        int i5 = i - i3;
        boolean zIsNode = SlotTableKt.isNode(this.groups, iGroupIndexToAddress);
        if (z) {
            SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress, i5);
            SlotTableKt.updateNodeCount(this.groups, iGroupIndexToAddress, i4);
            this.nodeCount = this.nodeCountStack.pop() + (zIsNode ? 1 : i4);
            this.parent = parent(this.groups, i3);
        } else {
            if ((i != i2 ? 0 : 1) != 0) {
                int iGroupSize = SlotTableKt.groupSize(this.groups, iGroupIndexToAddress);
                int iNodeCount = SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress);
                SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress, i5);
                SlotTableKt.updateNodeCount(this.groups, iGroupIndexToAddress, i4);
                int iPop = this.startStack.pop();
                restoreCurrentGroupEnd();
                this.parent = iPop;
                int iParent = parent(this.groups, i3);
                int iPop2 = this.nodeCountStack.pop();
                this.nodeCount = iPop2;
                if (iParent == iPop) {
                    this.nodeCount = iPop2 + (zIsNode ? 0 : i4 - iNodeCount);
                } else {
                    int i6 = i5 - iGroupSize;
                    int i7 = zIsNode ? 0 : i4 - iNodeCount;
                    if (i6 != 0 || i7 != 0) {
                        while (iParent != 0 && iParent != iPop && (i7 != 0 || i6 != 0)) {
                            int iGroupIndexToAddress2 = groupIndexToAddress(iParent);
                            if (i6 != 0) {
                                SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress2, SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2) + i6);
                            }
                            if (i7 != 0) {
                                int[] iArr = this.groups;
                                SlotTableKt.updateNodeCount(iArr, iGroupIndexToAddress2, SlotTableKt.nodeCount(iArr, iGroupIndexToAddress2) + i7);
                            }
                            if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress2)) {
                                i7 = 0;
                            }
                            iParent = parent(this.groups, iParent);
                        }
                    }
                    this.nodeCount += i7;
                }
            } else {
                throw new IllegalArgumentException("Expected to be at the end of a group".toString());
            }
        }
        return i4;
    }

    public final void ensureStarted(int index) {
        if (!(this.insertCount <= 0)) {
            throw new IllegalArgumentException("Cannot call ensureStarted() while inserting".toString());
        }
        int i = this.parent;
        if (i != index) {
            if (!(index >= i && index < this.currentGroupEnd)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Started group must be a subgroup of the group at ", Integer.valueOf(i)).toString());
            }
            int i2 = this.currentGroup;
            int i3 = this.currentSlot;
            int i4 = this.currentSlotEnd;
            this.currentGroup = index;
            startGroup();
            this.currentGroup = i2;
            this.currentSlot = i3;
            this.currentSlotEnd = i4;
        }
    }

    public final void ensureStarted(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        ensureStarted(anchor.toIndexFor(this));
    }

    public final int skipGroup() {
        int iGroupIndexToAddress = groupIndexToAddress(this.currentGroup);
        int iGroupSize = this.currentGroup + SlotTableKt.groupSize(this.groups, iGroupIndexToAddress);
        this.currentGroup = iGroupSize;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(iGroupSize));
        if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress)) {
            return 1;
        }
        return SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress);
    }

    public final boolean removeGroup() {
        if (!(this.insertCount == 0)) {
            throw new IllegalArgumentException("Cannot remove group while inserting".toString());
        }
        int i = this.currentGroup;
        int i2 = this.currentSlot;
        int iSkipGroup = skipGroup();
        boolean zRemoveGroups = removeGroups(i, this.currentGroup - i);
        removeSlots(i2, this.currentSlot - i2, i - 1);
        this.currentGroup = i;
        this.currentSlot = i2;
        this.nodeCount -= iSkipGroup;
        return zRemoveGroups;
    }

    public final Iterator<Object> groupSlots() {
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int[] iArr = this.groups;
        int i = this.currentGroup;
        return new AnonymousClass1(iDataIndex, dataIndex(iArr, groupIndexToAddress(i + groupSize(i))), this);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SlotWriter$groupSlots$1, reason: invalid class name */
    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\t\u0010\t\u001a\u00020\nH\u0096\u0002J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"androidx/compose/runtime/SlotWriter$groupSlots$1", "", "", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "hasNext", "", "next", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<Object>, KMappedMarker {
        final /* synthetic */ int $end;
        final /* synthetic */ int $start;
        private int current;
        final /* synthetic */ SlotWriter this$0;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1(int i, int i2, SlotWriter slotWriter) {
            this.$start = i;
            this.$end = i2;
            this.this$0 = slotWriter;
            this.current = i;
        }

        public final int getCurrent() {
            return this.current;
        }

        public final void setCurrent(int i) {
            this.current = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current < this.$end;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                return null;
            }
            Object[] objArr = this.this$0.slots;
            SlotWriter slotWriter = this.this$0;
            int i = this.current;
            this.current = i + 1;
            return objArr[slotWriter.dataIndexToDataAddress(i)];
        }
    }

    public final void moveGroup(int offset) {
        if (!(this.insertCount == 0)) {
            throw new IllegalArgumentException("Cannot move a group while inserting".toString());
        }
        if (!(offset >= 0)) {
            throw new IllegalArgumentException("Parameter offset is out of bounds".toString());
        }
        if (offset == 0) {
            return;
        }
        int i = this.currentGroup;
        int i2 = this.parent;
        int i3 = this.currentGroupEnd;
        int iGroupSize = i;
        for (int i4 = offset; i4 > 0; i4--) {
            iGroupSize += SlotTableKt.groupSize(this.groups, groupIndexToAddress(iGroupSize));
            if (!(iGroupSize <= i3)) {
                throw new IllegalArgumentException("Parameter offset is out of bounds".toString());
            }
        }
        int iGroupSize2 = SlotTableKt.groupSize(this.groups, groupIndexToAddress(iGroupSize));
        int i5 = this.currentSlot;
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(iGroupSize));
        int i6 = iGroupSize + iGroupSize2;
        int iDataIndex2 = dataIndex(this.groups, groupIndexToAddress(i6));
        int i7 = iDataIndex2 - iDataIndex;
        insertSlots(i7, Math.max(this.currentGroup - 1, 0));
        insertGroups(iGroupSize2);
        int[] iArr = this.groups;
        int iGroupIndexToAddress = groupIndexToAddress(i6) * 5;
        ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, groupIndexToAddress(i) * 5, iGroupIndexToAddress, (iGroupSize2 * 5) + iGroupIndexToAddress);
        if (i7 > 0) {
            Object[] objArr = this.slots;
            ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i5, dataIndexToDataAddress(iDataIndex + i7), dataIndexToDataAddress(iDataIndex2 + i7));
        }
        int i8 = iDataIndex + i7;
        int i9 = i8 - i5;
        int i10 = this.slotsGapStart;
        int i11 = this.slotsGapLen;
        int length = this.slots.length;
        int i12 = this.slotsGapOwner;
        int i13 = i + iGroupSize2;
        int i14 = i;
        while (i14 < i13) {
            int i15 = i14 + 1;
            int iGroupIndexToAddress2 = groupIndexToAddress(i14);
            int i16 = i9;
            updateDataIndex(iArr, iGroupIndexToAddress2, dataIndexToDataAnchor(dataIndex(iArr, iGroupIndexToAddress2) - i9, i12 < iGroupIndexToAddress2 ? 0 : i10, i11, length));
            i9 = i16;
            i14 = i15;
        }
        moveAnchors(i6, i, iGroupSize2);
        if (!removeGroups(i6, iGroupSize2)) {
            fixParentAnchorsFor(i2, this.currentGroupEnd, i);
            if (i7 > 0) {
                removeSlots(i8, i7, i6 - 1);
                return;
            }
            return;
        }
        ComposerKt.composeRuntimeError("Unexpectedly removed anchors".toString());
        throw new KotlinNothingValueException();
    }

    public final List<Anchor> moveFrom(SlotTable table, int index) {
        List<Anchor> listEmptyList;
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(table, "table");
        if (!(this.insertCount > 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (index != 0 || this.currentGroup != 0 || this.table.getGroupsSize() != 0) {
            SlotWriter slotWriterOpenWriter = table.openWriter();
            try {
                int iGroupSize = slotWriterOpenWriter.groupSize(index);
                int i3 = index + iGroupSize;
                int iDataIndex = slotWriterOpenWriter.dataIndex(index);
                int iDataIndex2 = slotWriterOpenWriter.dataIndex(i3);
                int i4 = iDataIndex2 - iDataIndex;
                insertGroups(iGroupSize);
                insertSlots(i4, getCurrentGroup());
                int[] iArr = this.groups;
                int currentGroup = getCurrentGroup();
                ArraysKt___ArraysJvmKt.copyInto(slotWriterOpenWriter.groups, iArr, currentGroup * 5, index * 5, i3 * 5);
                Object[] objArr = this.slots;
                int i5 = this.currentSlot;
                ArraysKt___ArraysJvmKt.copyInto(slotWriterOpenWriter.slots, objArr, i5, iDataIndex, iDataIndex2);
                SlotTableKt.updateParentAnchor(iArr, currentGroup, getParent());
                int i6 = currentGroup - index;
                int i7 = iGroupSize + currentGroup;
                int iDataIndex3 = i5 - dataIndex(iArr, currentGroup);
                int i8 = this.slotsGapOwner;
                int i9 = this.slotsGapLen;
                int length = objArr.length;
                int i10 = currentGroup;
                while (i10 < i7) {
                    int i11 = i10 + 1;
                    if (i10 != currentGroup) {
                        SlotTableKt.updateParentAnchor(iArr, i10, SlotTableKt.parentAnchor(iArr, i10) + i6);
                    }
                    int iDataIndex4 = dataIndex(iArr, i10) + iDataIndex3;
                    if (i8 < i10) {
                        i = iDataIndex3;
                        i2 = 0;
                    } else {
                        i = iDataIndex3;
                        i2 = this.slotsGapStart;
                    }
                    SlotTableKt.updateDataAnchor(iArr, i10, dataIndexToDataAnchor(iDataIndex4, i2, i9, length));
                    if (i10 == i8) {
                        i8++;
                    }
                    i10 = i11;
                    iDataIndex3 = i;
                }
                this.slotsGapOwner = i8;
                int iLocationOf = SlotTableKt.locationOf(table.getAnchors$runtime_release(), index, table.getGroupsSize());
                int iLocationOf2 = SlotTableKt.locationOf(table.getAnchors$runtime_release(), i3, table.getGroupsSize());
                if (iLocationOf < iLocationOf2) {
                    ArrayList<Anchor> anchors$runtime_release = table.getAnchors$runtime_release();
                    ArrayList arrayList = new ArrayList(iLocationOf2 - iLocationOf);
                    int i12 = iLocationOf;
                    while (i12 < iLocationOf2) {
                        int i13 = i12 + 1;
                        Anchor anchor = anchors$runtime_release.get(i12);
                        Intrinsics.checkNotNullExpressionValue(anchor, "sourceAnchors[anchorIndex]");
                        Anchor anchor2 = anchor;
                        anchor2.setLocation$runtime_release(anchor2.getLocation() + i6);
                        arrayList.add(anchor2);
                        i12 = i13;
                    }
                    getTable().getAnchors$runtime_release().addAll(SlotTableKt.locationOf(this.anchors, getCurrentGroup(), getSize$runtime_release()), arrayList);
                    anchors$runtime_release.subList(iLocationOf, iLocationOf2).clear();
                    listEmptyList = arrayList;
                } else {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
                int iParent = slotWriterOpenWriter.parent(index);
                if (iParent >= 0) {
                    slotWriterOpenWriter.startGroup();
                    slotWriterOpenWriter.advanceBy(iParent - slotWriterOpenWriter.getCurrentGroup());
                    slotWriterOpenWriter.startGroup();
                }
                slotWriterOpenWriter.advanceBy(index - slotWriterOpenWriter.getCurrentGroup());
                boolean zRemoveGroup = slotWriterOpenWriter.removeGroup();
                if (iParent >= 0) {
                    slotWriterOpenWriter.skipToGroupEnd();
                    slotWriterOpenWriter.endGroup();
                    slotWriterOpenWriter.skipToGroupEnd();
                    slotWriterOpenWriter.endGroup();
                }
                if (!zRemoveGroup) {
                    this.nodeCount += SlotTableKt.isNode(iArr, currentGroup) ? 1 : SlotTableKt.nodeCount(iArr, currentGroup);
                    this.currentGroup = i7;
                    this.currentSlot = i5 + i4;
                    return listEmptyList;
                }
                ComposerKt.composeRuntimeError("Unexpectedly removed anchors".toString());
                throw new KotlinNothingValueException();
            } finally {
                slotWriterOpenWriter.close();
            }
        }
        int[] iArr2 = this.groups;
        Object[] objArr2 = this.slots;
        ArrayList<Anchor> arrayList2 = this.anchors;
        int[] groups = table.getGroups();
        int groupsSize = table.getGroupsSize();
        Object[] slots = table.getSlots();
        int slotsSize = table.getSlotsSize();
        this.groups = groups;
        this.slots = slots;
        this.anchors = table.getAnchors$runtime_release();
        this.groupGapStart = groupsSize;
        this.groupGapLen = (groups.length / 5) - groupsSize;
        this.slotsGapStart = slotsSize;
        this.slotsGapLen = slots.length - slotsSize;
        this.slotsGapOwner = groupsSize;
        table.setTo$runtime_release(iArr2, 0, objArr2, 0, arrayList2);
        return this.anchors;
    }

    public static /* synthetic */ Anchor anchor$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.currentGroup;
        }
        return slotWriter.anchor(i);
    }

    public final Anchor anchor(int index) {
        ArrayList<Anchor> arrayList = this.anchors;
        int iSearch = SlotTableKt.search(arrayList, index, getSize$runtime_release());
        if (iSearch < 0) {
            if (index > this.groupGapStart) {
                index = -(getSize$runtime_release() - index);
            }
            Anchor anchor = new Anchor(index);
            arrayList.add(-(iSearch + 1), anchor);
            return anchor;
        }
        Anchor anchor2 = arrayList.get(iSearch);
        Intrinsics.checkNotNullExpressionValue(anchor2, "get(location)");
        return anchor2;
    }

    public final int anchorIndex(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        int location = anchor.getLocation();
        return location < 0 ? location + getSize$runtime_release() : location;
    }

    public String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    private final void saveCurrentGroupEnd() {
        this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
    }

    private final int restoreCurrentGroupEnd() {
        int capacity = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.currentGroupEnd = capacity;
        return capacity;
    }

    private final void fixParentAnchorsFor(int parent, int endGroup, int firstChild) {
        int iParentIndexToAnchor = parentIndexToAnchor(parent, this.groupGapStart);
        while (firstChild < endGroup) {
            SlotTableKt.updateParentAnchor(this.groups, groupIndexToAddress(firstChild), iParentIndexToAnchor);
            int iGroupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(firstChild)) + firstChild;
            fixParentAnchorsFor(firstChild, iGroupSize, firstChild + 1);
            firstChild = iGroupSize;
        }
    }

    private final void moveGroupGapTo(int index) {
        int i = this.groupGapLen;
        int i2 = this.groupGapStart;
        if (i2 != index) {
            if (!this.anchors.isEmpty()) {
                updateAnchors(i2, index);
            }
            if (i > 0) {
                int[] iArr = this.groups;
                int i3 = index * 5;
                int i4 = i * 5;
                int i5 = i2 * 5;
                if (index < i2) {
                    ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i4 + i3, i3, i5);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i5, i5 + i4, i3 + i4);
                }
            }
            if (index < i2) {
                i2 = index + i;
            }
            int capacity = getCapacity();
            ComposerKt.runtimeCheck(i2 < capacity);
            while (i2 < capacity) {
                int iParentAnchor = SlotTableKt.parentAnchor(this.groups, i2);
                int iParentIndexToAnchor = parentIndexToAnchor(parentAnchorToIndex(iParentAnchor), index);
                if (iParentIndexToAnchor != iParentAnchor) {
                    SlotTableKt.updateParentAnchor(this.groups, i2, iParentIndexToAnchor);
                }
                i2++;
                if (i2 == index) {
                    i2 += i;
                }
            }
        }
        this.groupGapStart = index;
    }

    private final void moveSlotGapTo(int index, int group) {
        int i = this.slotsGapLen;
        int i2 = this.slotsGapStart;
        int i3 = this.slotsGapOwner;
        if (i2 != index) {
            Object[] objArr = this.slots;
            if (index < i2) {
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, index + i, index, i2);
            } else {
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i2, i2 + i, index + i);
            }
            ArraysKt___ArraysJvmKt.fill(objArr, (Object) null, index, index + i);
        }
        int iMin = Math.min(group + 1, getSize$runtime_release());
        if (i3 != iMin) {
            int length = this.slots.length - i;
            if (iMin < i3) {
                int iGroupIndexToAddress = groupIndexToAddress(iMin);
                int iGroupIndexToAddress2 = groupIndexToAddress(i3);
                int i4 = this.groupGapStart;
                while (iGroupIndexToAddress < iGroupIndexToAddress2) {
                    int iDataAnchor = SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress);
                    if (iDataAnchor >= 0) {
                        SlotTableKt.updateDataAnchor(this.groups, iGroupIndexToAddress, -((length - iDataAnchor) + 1));
                        iGroupIndexToAddress++;
                        if (iGroupIndexToAddress == i4) {
                            iGroupIndexToAddress += this.groupGapLen;
                        }
                    } else {
                        ComposerKt.composeRuntimeError("Unexpected anchor value, expected a positive anchor".toString());
                        throw new KotlinNothingValueException();
                    }
                }
            } else {
                int iGroupIndexToAddress3 = groupIndexToAddress(i3);
                int iGroupIndexToAddress4 = groupIndexToAddress(iMin);
                while (iGroupIndexToAddress3 < iGroupIndexToAddress4) {
                    int iDataAnchor2 = SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress3);
                    if (iDataAnchor2 < 0) {
                        SlotTableKt.updateDataAnchor(this.groups, iGroupIndexToAddress3, iDataAnchor2 + length + 1);
                        iGroupIndexToAddress3++;
                        if (iGroupIndexToAddress3 == this.groupGapStart) {
                            iGroupIndexToAddress3 += this.groupGapLen;
                        }
                    } else {
                        ComposerKt.composeRuntimeError("Unexpected anchor value, expected a negative anchor".toString());
                        throw new KotlinNothingValueException();
                    }
                }
            }
            this.slotsGapOwner = iMin;
        }
        this.slotsGapStart = index;
    }

    private final void insertGroups(int size) {
        if (size > 0) {
            int i = this.currentGroup;
            moveGroupGapTo(i);
            int i2 = this.groupGapStart;
            int i3 = this.groupGapLen;
            int[] iArr = this.groups;
            int length = iArr.length / 5;
            int i4 = length - i3;
            if (i3 < size) {
                int iMax = Math.max(Math.max(length * 2, i4 + size), 32);
                int[] iArr2 = new int[iMax * 5];
                int i5 = iMax - i4;
                ArraysKt___ArraysJvmKt.copyInto(iArr, iArr2, 0, 0, i2 * 5);
                ArraysKt___ArraysJvmKt.copyInto(iArr, iArr2, (i2 + i5) * 5, (i3 + i2) * 5, length * 5);
                this.groups = iArr2;
                i3 = i5;
            }
            int i6 = this.currentGroupEnd;
            if (i6 >= i2) {
                this.currentGroupEnd = i6 + size;
            }
            int i7 = i2 + size;
            this.groupGapStart = i7;
            this.groupGapLen = i3 - size;
            int iDataIndexToDataAnchor = dataIndexToDataAnchor(i4 > 0 ? dataIndex(i + size) : 0, this.slotsGapOwner >= i2 ? this.slotsGapStart : 0, this.slotsGapLen, this.slots.length);
            for (int i8 = i2; i8 < i7; i8++) {
                SlotTableKt.updateDataAnchor(this.groups, i8, iDataIndexToDataAnchor);
            }
            int i9 = this.slotsGapOwner;
            if (i9 >= i2) {
                this.slotsGapOwner = i9 + size;
            }
        }
    }

    private final void insertSlots(int size, int group) {
        if (size > 0) {
            moveSlotGapTo(this.currentSlot, group);
            int i = this.slotsGapStart;
            int i2 = this.slotsGapLen;
            if (i2 < size) {
                Object[] objArr = this.slots;
                int length = objArr.length;
                int i3 = length - i2;
                int iMax = Math.max(Math.max(length * 2, i3 + size), 32);
                Object[] objArr2 = new Object[iMax];
                for (int i4 = 0; i4 < iMax; i4++) {
                    objArr2[i4] = null;
                }
                int i5 = iMax - i3;
                int i6 = i2 + i;
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr2, 0, 0, i);
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr2, i + i5, i6, length);
                this.slots = objArr2;
                i2 = i5;
            }
            int i7 = this.currentSlotEnd;
            if (i7 >= i) {
                this.currentSlotEnd = i7 + size;
            }
            this.slotsGapStart = i + size;
            this.slotsGapLen = i2 - size;
        }
    }

    private final boolean removeGroups(int start, int len) {
        if (len > 0) {
            ArrayList<Anchor> arrayList = this.anchors;
            moveGroupGapTo(start);
            zRemoveAnchors = arrayList.isEmpty() ^ true ? removeAnchors(start, len) : false;
            this.groupGapStart = start;
            this.groupGapLen += len;
            int i = this.slotsGapOwner;
            if (i > start) {
                this.slotsGapOwner = i - len;
            }
            int i2 = this.currentGroupEnd;
            if (i2 >= start) {
                this.currentGroupEnd = i2 - len;
            }
        }
        return zRemoveAnchors;
    }

    private final void removeSlots(int start, int len, int group) {
        if (len > 0) {
            int i = this.slotsGapLen;
            int i2 = start + len;
            moveSlotGapTo(i2, group);
            this.slotsGapStart = start;
            this.slotsGapLen = i + len;
            ArraysKt___ArraysJvmKt.fill(this.slots, (Object) null, start, i2);
            int i3 = this.currentSlotEnd;
            if (i3 >= start) {
                this.currentSlotEnd = i3 - len;
            }
        }
    }

    private final void updateNodeOfGroup(int index, Object value) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        int[] iArr = this.groups;
        if (!(iGroupIndexToAddress < iArr.length && SlotTableKt.isNode(iArr, iGroupIndexToAddress))) {
            ComposerKt.composeRuntimeError(("Updating the node of a group at " + index + " that was not created with as a node group").toString());
            throw new KotlinNothingValueException();
        }
        this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))] = value;
    }

    private final void updateAnchors(int previousGapStart, int newGapStart) {
        int i;
        int capacity = getCapacity() - this.groupGapLen;
        if (previousGapStart < newGapStart) {
            for (int iLocationOf = SlotTableKt.locationOf(this.anchors, previousGapStart, capacity); iLocationOf < this.anchors.size(); iLocationOf++) {
                Anchor anchor = this.anchors.get(iLocationOf);
                Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
                Anchor anchor2 = anchor;
                int location = anchor2.getLocation();
                if (location >= 0 || (i = location + capacity) >= newGapStart) {
                    return;
                }
                anchor2.setLocation$runtime_release(i);
            }
            return;
        }
        for (int iLocationOf2 = SlotTableKt.locationOf(this.anchors, newGapStart, capacity); iLocationOf2 < this.anchors.size(); iLocationOf2++) {
            Anchor anchor3 = this.anchors.get(iLocationOf2);
            Intrinsics.checkNotNullExpressionValue(anchor3, "anchors[index]");
            Anchor anchor4 = anchor3;
            int location2 = anchor4.getLocation();
            if (location2 < 0) {
                return;
            }
            anchor4.setLocation$runtime_release(-(capacity - location2));
        }
    }

    private final boolean removeAnchors(int gapStart, int size) {
        int i = size + gapStart;
        int iLocationOf = SlotTableKt.locationOf(this.anchors, i, getCapacity() - this.groupGapLen);
        if (iLocationOf >= this.anchors.size()) {
            iLocationOf--;
        }
        int i2 = iLocationOf + 1;
        int i3 = 0;
        while (iLocationOf >= 0) {
            Anchor anchor = this.anchors.get(iLocationOf);
            Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
            Anchor anchor2 = anchor;
            int iAnchorIndex = anchorIndex(anchor2);
            if (iAnchorIndex < gapStart) {
                break;
            }
            if (iAnchorIndex < i) {
                anchor2.setLocation$runtime_release(Integer.MIN_VALUE);
                if (i3 == 0) {
                    i3 = iLocationOf + 1;
                }
                i2 = iLocationOf;
            }
            iLocationOf--;
        }
        boolean z = i2 < i3;
        if (z) {
            this.anchors.subList(i2, i3).clear();
        }
        return z;
    }

    private final void moveAnchors(int originalLocation, int newLocation, int size) {
        int i = size + originalLocation;
        int size$runtime_release = getSize$runtime_release();
        int iLocationOf = SlotTableKt.locationOf(this.anchors, originalLocation, size$runtime_release);
        ArrayList arrayList = new ArrayList();
        if (iLocationOf >= 0) {
            while (iLocationOf < this.anchors.size()) {
                Anchor anchor = this.anchors.get(iLocationOf);
                Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
                Anchor anchor2 = anchor;
                int iAnchorIndex = anchorIndex(anchor2);
                if (iAnchorIndex < originalLocation || iAnchorIndex >= i) {
                    break;
                }
                arrayList.add(anchor2);
                this.anchors.remove(iLocationOf);
            }
        }
        int i2 = newLocation - originalLocation;
        int i3 = 0;
        int size2 = arrayList.size();
        while (i3 < size2) {
            int i4 = i3 + 1;
            Anchor anchor3 = (Anchor) arrayList.get(i3);
            int iAnchorIndex2 = anchorIndex(anchor3) + i2;
            if (iAnchorIndex2 >= this.groupGapStart) {
                anchor3.setLocation$runtime_release(-(size$runtime_release - iAnchorIndex2));
            } else {
                anchor3.setLocation$runtime_release(iAnchorIndex2);
            }
            this.anchors.add(SlotTableKt.locationOf(this.anchors, iAnchorIndex2, size$runtime_release), anchor3);
            i3 = i4;
        }
    }

    public final String groupsAsString() {
        StringBuilder sb = new StringBuilder();
        int size$runtime_release = getSize$runtime_release();
        for (int i = 0; i < size$runtime_release; i++) {
            groupAsString(sb, i);
            sb.append('\n');
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final void groupAsString(StringBuilder sb, int i) {
        int iGroupIndexToAddress = groupIndexToAddress(i);
        sb.append("Group(");
        if (i < 10) {
            sb.append(' ');
        }
        if (i < 100) {
            sb.append(' ');
        }
        if (i < 1000) {
            sb.append(' ');
        }
        sb.append(i);
        sb.append('#');
        sb.append(SlotTableKt.groupSize(this.groups, iGroupIndexToAddress));
        sb.append('^');
        sb.append(parentAnchorToIndex(SlotTableKt.parentAnchor(this.groups, iGroupIndexToAddress)));
        sb.append(": key=");
        sb.append(SlotTableKt.key(this.groups, iGroupIndexToAddress));
        sb.append(", nodes=");
        sb.append(SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress));
        sb.append(", dataAnchor=");
        sb.append(SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress));
        sb.append(", parentAnchor=");
        sb.append(SlotTableKt.parentAnchor(this.groups, iGroupIndexToAddress));
        sb.append(")");
    }

    public final void verifyDataAnchors$runtime_release() {
        int i = this.slotsGapOwner;
        int length = this.slots.length - this.slotsGapLen;
        int size$runtime_release = getSize$runtime_release();
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (i2 < size$runtime_release) {
            int i4 = i2 + 1;
            int iGroupIndexToAddress = groupIndexToAddress(i2);
            int iDataAnchor = SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress);
            int iDataIndex = dataIndex(this.groups, iGroupIndexToAddress);
            if (!(iDataIndex >= i3)) {
                throw new IllegalStateException(("Data index out of order at " + i2 + ", previous = " + i3 + ", current = " + iDataIndex).toString());
            }
            if (!(iDataIndex <= length)) {
                throw new IllegalStateException(("Data index, " + iDataIndex + ", out of bound at " + i2).toString());
            }
            if (iDataAnchor < 0 && !z) {
                if (!(i == i2)) {
                    throw new IllegalStateException(("Expected the slot gap owner to be " + i + " found gap at " + i2).toString());
                }
                z = true;
            }
            i2 = i4;
            i3 = iDataIndex;
        }
    }

    public final void verifyParentAnchors$runtime_release() {
        int i = this.groupGapStart;
        int i2 = this.groupGapLen;
        int capacity = getCapacity();
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                int i4 = i2 + i;
                while (i4 < capacity) {
                    int i5 = i4 + 1;
                    int iParentAnchor = SlotTableKt.parentAnchor(this.groups, i4);
                    if (parentAnchorToIndex(iParentAnchor) < i) {
                        if (!(iParentAnchor > -2)) {
                            throw new IllegalStateException(Intrinsics.stringPlus("Expected a start relative anchor at ", Integer.valueOf(i4)).toString());
                        }
                    } else {
                        if (!(iParentAnchor <= -2)) {
                            throw new IllegalStateException(Intrinsics.stringPlus("Expected an end relative anchor at ", Integer.valueOf(i4)).toString());
                        }
                    }
                    i4 = i5;
                }
                return;
            }
            int i6 = i3 + 1;
            if (!(SlotTableKt.parentAnchor(this.groups, i3) > -2)) {
                throw new IllegalStateException(Intrinsics.stringPlus("Expected a start relative anchor at ", Integer.valueOf(i3)).toString());
            }
            i3 = i6;
        }
    }

    public final int getSize$runtime_release() {
        return getCapacity() - this.groupGapLen;
    }

    private final int getCapacity() {
        return this.groups.length / 5;
    }

    private final int groupIndexToAddress(int index) {
        return index < this.groupGapStart ? index : index + this.groupGapLen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int dataIndex) {
        return dataIndex < this.slotsGapStart ? dataIndex : dataIndex + this.slotsGapLen;
    }

    private final int parent(int[] iArr, int i) {
        return parentAnchorToIndex(SlotTableKt.parentAnchor(iArr, groupIndexToAddress(i)));
    }

    private final int dataIndex(int index) {
        return dataIndex(this.groups, groupIndexToAddress(index));
    }

    private final int dataIndex(int[] iArr, int i) {
        return i >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.dataAnchor(iArr, i), this.slotsGapLen, this.slots.length);
    }

    private final int slotIndex(int[] iArr, int i) {
        return i >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.slotAnchor(iArr, i), this.slotsGapLen, this.slots.length);
    }

    private final void updateDataIndex(int[] iArr, int i, int i2) {
        SlotTableKt.updateDataAnchor(iArr, i, dataIndexToDataAnchor(i2, this.slotsGapStart, this.slotsGapLen, this.slots.length));
    }

    private final int nodeIndex(int[] iArr, int i) {
        return dataIndex(iArr, i);
    }

    private final int auxIndex(int[] iArr, int i) {
        return dataIndex(iArr, i) + SlotTableKt.countOneBits(SlotTableKt.groupInfo(iArr, i) >> 29);
    }

    private final List<Integer> dataIndexes(int[] iArr) {
        List listDataAnchors$default = SlotTableKt.dataAnchors$default(this.groups, 0, 1, null);
        List listPlus = CollectionsKt___CollectionsKt.plus((Collection) CollectionsKt___CollectionsKt.slice(listDataAnchors$default, RangesKt___RangesKt.until(0, this.groupGapStart)), (Iterable) CollectionsKt___CollectionsKt.slice(listDataAnchors$default, RangesKt___RangesKt.until(this.groupGapStart + this.groupGapLen, iArr.length / 5)));
        ArrayList arrayList = new ArrayList(listPlus.size());
        int size = listPlus.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(Integer.valueOf(dataAnchorToDataIndex(((Number) listPlus.get(i)).intValue(), this.slotsGapLen, this.slots.length)));
        }
        return arrayList;
    }

    private final List<Integer> keys() {
        List listKeys$default = SlotTableKt.keys$default(this.groups, 0, 1, null);
        ArrayList arrayList = new ArrayList(listKeys$default.size());
        int size = listKeys$default.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            Object obj = listKeys$default.get(i);
            ((Number) obj).intValue();
            int i3 = this.groupGapStart;
            if (i < i3 || i >= i3 + this.groupGapLen) {
                arrayList.add(obj);
            }
            i = i2;
        }
        return arrayList;
    }

    private final int parentIndexToAnchor(int index, int gapStart) {
        return index < gapStart ? index : -((getSize$runtime_release() - index) + 2);
    }

    private final int parentAnchorToIndex(int index) {
        return index > -2 ? index : getSize$runtime_release() + index + 2;
    }
}
