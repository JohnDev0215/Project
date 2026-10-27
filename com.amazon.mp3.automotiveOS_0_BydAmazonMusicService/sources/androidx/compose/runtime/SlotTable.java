package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u0007J\u0006\u0010-\u001a\u00020.J\u0015\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0000¢\u0006\u0002\b3JW\u0010/\u001a\u0002002\u0006\u0010)\u001a\u0002042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u000e\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d2\u0006\u0010#\u001a\u00020\u00152\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0000¢\u0006\u0004\b3\u00105J\u000e\u00106\u001a\b\u0012\u0004\u0012\u00020\u001507H\u0002J\u000e\u00108\u001a\b\u0012\u0004\u0012\u00020\u001507H\u0002J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00030:H\u0096\u0002J\u000e\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001507H\u0002J\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001507H\u0002J\u0006\u0010=\u001a\u000202J\u0006\u0010>\u001a\u000204J\u000e\u0010?\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u0007J\u000e\u0010@\u001a\b\u0012\u0004\u0012\u00020\u001507H\u0002J:\u0010A\u001a\u0002HB\"\u0004\b\u0000\u0010B2!\u0010C\u001a\u001d\u0012\u0013\u0012\u001102¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(1\u0012\u0004\u0012\u0002HB0DH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010GJO\u0010H\u001a\u0002002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u000e\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d2\u0006\u0010#\u001a\u00020\u00152\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0000¢\u0006\u0004\bI\u0010JJ\u001d\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e072\u0006\u0010L\u001a\u00020\u0015H\u0000¢\u0006\u0002\bMJ\u0006\u0010N\u001a\u000200J:\u0010O\u001a\u0002HB\"\u0004\b\u0000\u0010B2!\u0010C\u001a\u001d\u0012\u0013\u0012\u001104¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b()\u0012\u0004\u0012\u0002HB0DH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010GJ \u0010P\u001a\u00020\u0015*\u00060Qj\u0002`R2\u0006\u0010S\u001a\u00020\u00152\u0006\u0010T\u001a\u00020\u0015H\u0002R*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d2\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u001e\u0010#\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u001a\u0010%\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010(R\u001e\u0010)\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u001a@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006U"}, d2 = {"Landroidx/compose/runtime/SlotTable;", "Landroidx/compose/runtime/tooling/CompositionData;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "()V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "getAnchors$runtime_release", "()Ljava/util/ArrayList;", "setAnchors$runtime_release", "(Ljava/util/ArrayList;)V", "compositionGroups", "getCompositionGroups", "()Ljava/lang/Iterable;", "<set-?>", "", "groups", "getGroups", "()[I", "", "groupsSize", "getGroupsSize", "()I", "isEmpty", "", "()Z", "readers", "", "", "slots", "getSlots", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "slotsSize", "getSlotsSize", "version", "getVersion$runtime_release", "setVersion$runtime_release", "(I)V", "writer", "getWriter$runtime_release", "anchorIndex", "anchor", "asString", "", "close", "", "reader", "Landroidx/compose/runtime/SlotReader;", "close$runtime_release", "Landroidx/compose/runtime/SlotWriter;", "(Landroidx/compose/runtime/SlotWriter;[II[Ljava/lang/Object;ILjava/util/ArrayList;)V", "dataIndexes", "", "groupSizes", "iterator", "", "keys", "nodes", "openReader", "openWriter", "ownsAnchor", "parentIndexes", "read", "T", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "setTo", "setTo$runtime_release", "([II[Ljava/lang/Object;ILjava/util/ArrayList;)V", "slotsOf", "group", "slotsOf$runtime_release", "verifyWellFormed", "write", "emitGroup", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "index", "level", "runtime_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SlotTable implements CompositionData, Iterable<CompositionGroup>, KMappedMarker {
    private int groupsSize;
    private int readers;
    private int slotsSize;
    private int version;
    private boolean writer;
    private int[] groups = new int[0];
    private Object[] slots = new Object[0];
    private ArrayList<Anchor> anchors = new ArrayList<>();

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    public final int[] getGroups() {
        return this.groups;
    }

    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final Object[] getSlots() {
        return this.slots;
    }

    public final int getSlotsSize() {
        return this.slotsSize;
    }

    /* JADX INFO: renamed from: getWriter$runtime_release, reason: from getter */
    public final boolean getWriter() {
        return this.writer;
    }

    /* JADX INFO: renamed from: getVersion$runtime_release, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    public final void setVersion$runtime_release(int i) {
        this.version = i;
    }

    public final ArrayList<Anchor> getAnchors$runtime_release() {
        return this.anchors;
    }

    public final void setAnchors$runtime_release(ArrayList<Anchor> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.anchors = arrayList;
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return this.groupsSize == 0;
    }

    public final <T> T read(Function1<? super SlotReader, ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SlotReader slotReaderOpenReader = openReader();
        try {
            return block.invoke(slotReaderOpenReader);
        } finally {
            InlineMarker.finallyStart(1);
            slotReaderOpenReader.close();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T write(Function1<? super SlotWriter, ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SlotWriter slotWriterOpenWriter = openWriter();
        try {
            return block.invoke(slotWriterOpenWriter);
        } finally {
            InlineMarker.finallyStart(1);
            slotWriterOpenWriter.close();
            InlineMarker.finallyEnd(1);
        }
    }

    public final SlotReader openReader() {
        if (this.writer) {
            throw new IllegalStateException("Cannot read while a writer is pending".toString());
        }
        this.readers++;
        return new SlotReader(this);
    }

    public final SlotWriter openWriter() {
        if (!this.writer) {
            if (this.readers <= 0) {
                this.writer = true;
                this.version++;
                return new SlotWriter(this);
            }
            ComposerKt.composeRuntimeError("Cannot start a writer when a reader is pending".toString());
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("Cannot start a writer when another writer is pending".toString());
        throw new KotlinNothingValueException();
    }

    public final int anchorIndex(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (!this.writer) {
            if (!anchor.getValid()) {
                throw new IllegalArgumentException("Anchor refers to a group that was removed".toString());
            }
            return anchor.getLocation();
        }
        ComposerKt.composeRuntimeError("Use active SlotWriter to determine anchor location instead".toString());
        throw new KotlinNothingValueException();
    }

    public final boolean ownsAnchor(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (anchor.getValid()) {
            int iSearch = SlotTableKt.search(this.anchors, anchor.getLocation(), this.groupsSize);
            if (iSearch >= 0 && Intrinsics.areEqual(getAnchors$runtime_release().get(iSearch), anchor)) {
                return true;
            }
        }
        return false;
    }

    public final void close$runtime_release(SlotReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        if (!(reader.getTable() == this && this.readers > 0)) {
            throw new IllegalArgumentException("Unexpected reader close()".toString());
        }
        this.readers--;
    }

    public final void close$runtime_release(SlotWriter writer, int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(groups, "groups");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(anchors, "anchors");
        if (!(writer.getTable() == this && this.writer)) {
            throw new IllegalArgumentException("Unexpected writer close()".toString());
        }
        this.writer = false;
        setTo$runtime_release(groups, groupsSize, slots, slotsSize, anchors);
    }

    public final void setTo$runtime_release(int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors) {
        Intrinsics.checkNotNullParameter(groups, "groups");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(anchors, "anchors");
        this.groups = groups;
        this.groupsSize = groupsSize;
        this.slots = slots;
        this.slotsSize = slotsSize;
        this.anchors = anchors;
    }

    public final void verifyWellFormed() {
        int i;
        int i2;
        Ref.IntRef intRef = new Ref.IntRef();
        int i3 = -1;
        if (this.groupsSize > 0) {
            while (true) {
                i = intRef.element;
                i2 = this.groupsSize;
                if (i >= i2) {
                    break;
                } else {
                    verifyWellFormed$validateGroup(intRef, this, -1, i + SlotTableKt.groupSize(this.groups, i));
                }
            }
            if (!(i == i2)) {
                throw new IllegalStateException(("Incomplete group at root " + intRef.element + " expected to be " + getGroupsSize()).toString());
            }
        }
        ArrayList<Anchor> arrayList = this.anchors;
        int size = arrayList.size();
        int i4 = 0;
        while (i4 < size) {
            int i5 = i4 + 1;
            int indexFor = arrayList.get(i4).toIndexFor(this);
            if (!(indexFor >= 0 && indexFor <= getGroupsSize())) {
                throw new IllegalArgumentException("Location out of bound".toString());
            }
            if (!(i3 < indexFor)) {
                throw new IllegalArgumentException("Anchor is out of order".toString());
            }
            i3 = indexFor;
            i4 = i5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v7 */
    private static final int verifyWellFormed$validateGroup(Ref.IntRef intRef, SlotTable slotTable, int i, int i2) {
        int i3 = intRef.element;
        int i4 = i3 + 1;
        intRef.element = i4;
        int iParentAnchor = SlotTableKt.parentAnchor(slotTable.groups, i3);
        if ((iParentAnchor == i) != false) {
            int iGroupSize = SlotTableKt.groupSize(slotTable.groups, i3) + i3;
            if ((iGroupSize <= slotTable.groupsSize) != true) {
                throw new IllegalStateException(Intrinsics.stringPlus("A group extends past the end of the table at ", Integer.valueOf(i3)).toString());
            }
            if ((iGroupSize <= i2) != false) {
                int iDataAnchor = SlotTableKt.dataAnchor(slotTable.groups, i3);
                int iDataAnchor2 = i3 >= slotTable.groupsSize - 1 ? slotTable.slotsSize : SlotTableKt.dataAnchor(slotTable.groups, i4);
                if ((iDataAnchor2 <= slotTable.slots.length) != true) {
                    throw new IllegalStateException(("Slots for " + i3 + " extend past the end of the slot table").toString());
                }
                if ((iDataAnchor <= iDataAnchor2) != false) {
                    if ((SlotTableKt.slotAnchor(slotTable.groups, i3) <= iDataAnchor2) != true) {
                        throw new IllegalStateException(Intrinsics.stringPlus("Slots start out of range at ", Integer.valueOf(i3)).toString());
                    }
                    if ((iDataAnchor2 - iDataAnchor >= ((SlotTableKt.isNode(slotTable.groups, i3) ? 1 : 0) + (SlotTableKt.hasObjectKey(slotTable.groups, i3) ? 1 : 0)) + (SlotTableKt.hasAux(slotTable.groups, i3) ? 1 : 0)) != false) {
                        boolean zIsNode = SlotTableKt.isNode(slotTable.groups, i3);
                        if (((zIsNode && slotTable.slots[SlotTableKt.nodeIndex(slotTable.groups, i3)] == null) ? false : true) == false) {
                            throw new IllegalStateException(Intrinsics.stringPlus("No node recorded for a node group at ", Integer.valueOf(i3)).toString());
                        }
                        int iVerifyWellFormed$validateGroup = 0;
                        while (intRef.element < iGroupSize) {
                            iVerifyWellFormed$validateGroup += verifyWellFormed$validateGroup(intRef, slotTable, i3, iGroupSize);
                        }
                        int iNodeCount = SlotTableKt.nodeCount(slotTable.groups, i3);
                        int iGroupSize2 = SlotTableKt.groupSize(slotTable.groups, i3);
                        if ((iNodeCount == iVerifyWellFormed$validateGroup) == false) {
                            throw new IllegalStateException(("Incorrect node count detected at " + i3 + ", expected " + iNodeCount + ", received " + iVerifyWellFormed$validateGroup).toString());
                        }
                        int i5 = intRef.element - i3;
                        if (iGroupSize2 == i5) {
                            if (zIsNode) {
                                return 1;
                            }
                            return iVerifyWellFormed$validateGroup;
                        }
                        throw new IllegalStateException(("Incorrect slot count detected at " + i3 + ", expected " + iGroupSize2 + ", received " + i5).toString());
                    }
                    throw new IllegalStateException(Intrinsics.stringPlus("Not enough slots added for group ", Integer.valueOf(i3)).toString());
                }
                throw new IllegalStateException(Intrinsics.stringPlus("Invalid data anchor at ", Integer.valueOf(i3)).toString());
            }
            throw new IllegalStateException(Intrinsics.stringPlus("A group extends past its parent group at ", Integer.valueOf(i3)).toString());
        }
        throw new IllegalStateException(("Invalid parent index detected at " + i3 + ", expected parent index to be " + i + " found " + iParentAnchor).toString());
    }

    public final String asString() {
        if (this.writer) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append('\n');
        int groupsSize = getGroupsSize();
        if (groupsSize > 0) {
            int iEmitGroup = 0;
            while (iEmitGroup < groupsSize) {
                iEmitGroup += emitGroup(sb, iEmitGroup, 0);
            }
        } else {
            sb.append("<EMPTY>");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final int emitGroup(StringBuilder sb, int i, int i2) {
        boolean z = false;
        int i3 = 0;
        while (i3 < i2) {
            i3++;
            sb.append(' ');
        }
        sb.append("Group(");
        sb.append(i);
        sb.append(") key=");
        sb.append(SlotTableKt.key(this.groups, i));
        int iGroupSize = SlotTableKt.groupSize(this.groups, i);
        sb.append(", nodes=");
        sb.append(SlotTableKt.nodeCount(this.groups, i));
        sb.append(", size=");
        sb.append(iGroupSize);
        int iEmitGroup$dataIndex = emitGroup$dataIndex(this, i);
        int iEmitGroup = i + 1;
        int iEmitGroup$dataIndex2 = emitGroup$dataIndex(this, iEmitGroup);
        if (iEmitGroup$dataIndex >= 0 && iEmitGroup$dataIndex <= iEmitGroup$dataIndex2) {
            z = true;
        }
        if (z && iEmitGroup$dataIndex2 <= this.slotsSize) {
            if (SlotTableKt.hasObjectKey(this.groups, i)) {
                sb.append(Intrinsics.stringPlus(" objectKey=", this.slots[SlotTableKt.objectKeyIndex(this.groups, i)]));
            }
            if (SlotTableKt.isNode(this.groups, i)) {
                sb.append(Intrinsics.stringPlus(" node=", this.slots[SlotTableKt.nodeIndex(this.groups, i)]));
            }
            if (SlotTableKt.hasAux(this.groups, i)) {
                sb.append(Intrinsics.stringPlus(" aux=", this.slots[SlotTableKt.auxIndex(this.groups, i)]));
            }
            int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, i);
            if (iSlotAnchor < iEmitGroup$dataIndex2) {
                sb.append(", slots=[");
                sb.append(iSlotAnchor);
                sb.append(": ");
                int i4 = iSlotAnchor;
                while (i4 < iEmitGroup$dataIndex2) {
                    int i5 = i4 + 1;
                    if (i4 != iSlotAnchor) {
                        sb.append(", ");
                    }
                    sb.append(String.valueOf(this.slots[i4]));
                    i4 = i5;
                }
                sb.append("]");
            }
        } else {
            sb.append(", *invalid data offsets " + iEmitGroup$dataIndex + '-' + iEmitGroup$dataIndex2 + '*');
        }
        sb.append('\n');
        int i6 = i + iGroupSize;
        while (iEmitGroup < i6) {
            iEmitGroup += emitGroup(sb, iEmitGroup, i2 + 1);
        }
        return iGroupSize;
    }

    private static final int emitGroup$dataIndex(SlotTable slotTable, int i) {
        return i >= slotTable.groupsSize ? slotTable.slotsSize : SlotTableKt.dataAnchor(slotTable.groups, i);
    }

    private final List<Integer> keys() {
        return SlotTableKt.keys(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> nodes() {
        return SlotTableKt.nodeCounts(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> parentIndexes() {
        return SlotTableKt.parentAnchors(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> dataIndexes() {
        return SlotTableKt.dataAnchors(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> groupSizes() {
        return SlotTableKt.groupSizes(this.groups, this.groupsSize * 5);
    }

    public final List<Object> slotsOf$runtime_release(int group) {
        int iDataAnchor = SlotTableKt.dataAnchor(this.groups, group);
        int i = group + 1;
        return ArraysKt___ArraysKt.toList(this.slots).subList(iDataAnchor, i < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, i) : this.slots.length);
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        return new GroupIterator(this, 0, this.groupsSize);
    }
}
