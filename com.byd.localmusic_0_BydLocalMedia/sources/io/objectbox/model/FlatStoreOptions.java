package io.objectbox.model;

import io.objectbox.flatbuffers.BaseVector;
import io.objectbox.flatbuffers.ByteVector;
import io.objectbox.flatbuffers.Constants;
import io.objectbox.flatbuffers.FlatBufferBuilder;
import io.objectbox.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes5.dex */
public final class FlatStoreOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_2_0_8();
    }

    public static FlatStoreOptions getRootAsFlatStoreOptions(ByteBuffer byteBuffer) {
        return getRootAsFlatStoreOptions(byteBuffer, new FlatStoreOptions());
    }

    public static FlatStoreOptions getRootAsFlatStoreOptions(ByteBuffer byteBuffer, FlatStoreOptions flatStoreOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return flatStoreOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public FlatStoreOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String directoryPath() {
        int i__offset = __offset(4);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer directoryPathAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer directoryPathInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public int modelBytes(int i) {
        int i__offset = __offset(6);
        if (i__offset != 0) {
            return this.bb.get(__vector(i__offset) + (i * 1)) & 255;
        }
        return 0;
    }

    public int modelBytesLength() {
        int i__offset = __offset(6);
        if (i__offset != 0) {
            return __vector_len(i__offset);
        }
        return 0;
    }

    public ByteVector modelBytesVector() {
        return modelBytesVector(new ByteVector());
    }

    public ByteVector modelBytesVector(ByteVector byteVector) {
        int i__offset = __offset(6);
        if (i__offset != 0) {
            return byteVector.__assign(__vector(i__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer modelBytesAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public ByteBuffer modelBytesInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public long maxDbSizeInKbyte() {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return this.bb.getLong(i__offset + this.bb_pos);
        }
        return 0L;
    }

    public long fileMode() {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return ((long) this.bb.getInt(i__offset + this.bb_pos)) & 4294967295L;
        }
        return 0L;
    }

    public long maxReaders() {
        int i__offset = __offset(12);
        if (i__offset != 0) {
            return ((long) this.bb.getInt(i__offset + this.bb_pos)) & 4294967295L;
        }
        return 0L;
    }

    public int validateOnOpen() {
        int i__offset = __offset(14);
        if (i__offset != 0) {
            return this.bb.getShort(i__offset + this.bb_pos) & 65535;
        }
        return 0;
    }

    public long validateOnOpenPageLimit() {
        int i__offset = __offset(16);
        if (i__offset != 0) {
            return this.bb.getLong(i__offset + this.bb_pos);
        }
        return 0L;
    }

    public int putPaddingMode() {
        int i__offset = __offset(18);
        if (i__offset != 0) {
            return this.bb.getShort(i__offset + this.bb_pos) & 65535;
        }
        return 0;
    }

    public boolean skipReadSchema() {
        int i__offset = __offset(20);
        return (i__offset == 0 || this.bb.get(i__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean usePreviousCommit() {
        int i__offset = __offset(22);
        return (i__offset == 0 || this.bb.get(i__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean usePreviousCommitOnValidationFailure() {
        int i__offset = __offset(24);
        return (i__offset == 0 || this.bb.get(i__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean readOnly() {
        int i__offset = __offset(26);
        return (i__offset == 0 || this.bb.get(i__offset + this.bb_pos) == 0) ? false : true;
    }

    public long debugFlags() {
        int i__offset = __offset(28);
        if (i__offset != 0) {
            return ((long) this.bb.getInt(i__offset + this.bb_pos)) & 4294967295L;
        }
        return 0L;
    }

    public boolean noReaderThreadLocals() {
        int i__offset = __offset(30);
        return (i__offset == 0 || this.bb.get(i__offset + this.bb_pos) == 0) ? false : true;
    }

    public long maxDataSizeInKbyte() {
        int i__offset = __offset(32);
        if (i__offset != 0) {
            return this.bb.getLong(i__offset + this.bb_pos);
        }
        return 0L;
    }

    public static int createFlatStoreOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2, long j, long j2, long j3, int i3, long j4, int i4, boolean z, boolean z2, boolean z3, boolean z4, long j5, boolean z5, long j6) {
        flatBufferBuilder.startTable(15);
        addMaxDataSizeInKbyte(flatBufferBuilder, j6);
        addValidateOnOpenPageLimit(flatBufferBuilder, j4);
        addMaxDbSizeInKbyte(flatBufferBuilder, j);
        addDebugFlags(flatBufferBuilder, j5);
        addMaxReaders(flatBufferBuilder, j3);
        addFileMode(flatBufferBuilder, j2);
        addModelBytes(flatBufferBuilder, i2);
        addDirectoryPath(flatBufferBuilder, i);
        addPutPaddingMode(flatBufferBuilder, i4);
        addValidateOnOpen(flatBufferBuilder, i3);
        addNoReaderThreadLocals(flatBufferBuilder, z5);
        addReadOnly(flatBufferBuilder, z4);
        addUsePreviousCommitOnValidationFailure(flatBufferBuilder, z3);
        addUsePreviousCommit(flatBufferBuilder, z2);
        addSkipReadSchema(flatBufferBuilder, z);
        return endFlatStoreOptions(flatBufferBuilder);
    }

    public static void startFlatStoreOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(15);
    }

    public static void addDirectoryPath(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addModelBytes(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createModelBytesVector(FlatBufferBuilder flatBufferBuilder, byte[] bArr) {
        return flatBufferBuilder.createByteVector(bArr);
    }

    public static int createModelBytesVector(FlatBufferBuilder flatBufferBuilder, ByteBuffer byteBuffer) {
        return flatBufferBuilder.createByteVector(byteBuffer);
    }

    public static void startModelBytesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static void addMaxDbSizeInKbyte(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(2, j, 0L);
    }

    public static void addFileMode(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(3, (int) j, 0);
    }

    public static void addMaxReaders(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(4, (int) j, 0);
    }

    public static void addValidateOnOpen(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addShort(5, (short) i, 0);
    }

    public static void addValidateOnOpenPageLimit(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(6, j, 0L);
    }

    public static void addPutPaddingMode(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addShort(7, (short) i, 0);
    }

    public static void addSkipReadSchema(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(8, z, false);
    }

    public static void addUsePreviousCommit(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(9, z, false);
    }

    public static void addUsePreviousCommitOnValidationFailure(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(10, z, false);
    }

    public static void addReadOnly(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(11, z, false);
    }

    public static void addDebugFlags(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(12, (int) j, 0);
    }

    public static void addNoReaderThreadLocals(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(13, z, false);
    }

    public static void addMaxDataSizeInKbyte(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(14, j, 0L);
    }

    public static int endFlatStoreOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static void finishFlatStoreOptionsBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finish(i);
    }

    public static void finishSizePrefixedFlatStoreOptionsBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finishSizePrefixed(i);
    }

    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public FlatStoreOptions get(int i) {
            return get(new FlatStoreOptions(), i);
        }

        public FlatStoreOptions get(FlatStoreOptions flatStoreOptions, int i) {
            return flatStoreOptions.__assign(FlatStoreOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
