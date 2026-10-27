package com.amazon.weblab.mobile.model.flatbuffer;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes5.dex */
public final class Assignments extends Table {
    public static Assignments getRootAsAssignments(ByteBuffer byteBuffer) {
        return getRootAsAssignments(byteBuffer, new Assignments());
    }

    public static Assignments getRootAsAssignments(ByteBuffer byteBuffer, Assignments assignments) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return assignments.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
    }

    public Assignments __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String appVersion() {
        int i__offset = __offset(4);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public String sessionId() {
        int i__offset = __offset(6);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public String marketplaceId() {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public Assignment treatmentAssignments(int i) {
        return treatmentAssignments(new Assignment(), i);
    }

    public Assignment treatmentAssignments(Assignment assignment, int i) {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return assignment.__assign(__indirect(__vector(i__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int treatmentAssignmentsLength() {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return __vector_len(i__offset);
        }
        return 0;
    }

    public static int createAssignments(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4) {
        flatBufferBuilder.startObject(4);
        addTreatmentAssignments(flatBufferBuilder, i4);
        addMarketplaceId(flatBufferBuilder, i3);
        addSessionId(flatBufferBuilder, i2);
        addAppVersion(flatBufferBuilder, i);
        return endAssignments(flatBufferBuilder);
    }

    public static void addAppVersion(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addSessionId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addMarketplaceId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addTreatmentAssignments(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static int createTreatmentAssignmentsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int endAssignments(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
