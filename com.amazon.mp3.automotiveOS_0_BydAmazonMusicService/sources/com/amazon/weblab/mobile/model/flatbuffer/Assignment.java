package com.amazon.weblab.mobile.model.flatbuffer;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public final class Assignment extends Table {
    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
    }

    public Assignment __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String name() {
        int i__offset = __offset(4);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public String treatment() {
        int i__offset = __offset(6);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public String version() {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public long cacheInsertionSecs() {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return this.bb.getLong(i__offset + this.bb_pos);
        }
        return 0L;
    }

    public long expirationSecs() {
        int i__offset = __offset(12);
        if (i__offset != 0) {
            return this.bb.getLong(i__offset + this.bb_pos);
        }
        return 0L;
    }

    public boolean canTrigger() {
        int i__offset = __offset(14);
        return (i__offset == 0 || this.bb.get(i__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean isLocked() {
        int i__offset = __offset(18);
        return (i__offset == 0 || this.bb.get(i__offset + this.bb_pos) == 0) ? false : true;
    }

    public static void startAssignment(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(8);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addTreatment(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addVersion(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addCacheInsertionSecs(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(3, j, 0L);
    }

    public static void addCanTrigger(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(5, z, false);
    }

    public static void addSampleTrigger(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(6, z, false);
    }

    public static void addIsLocked(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(7, z, false);
    }

    public static int endAssignment(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
