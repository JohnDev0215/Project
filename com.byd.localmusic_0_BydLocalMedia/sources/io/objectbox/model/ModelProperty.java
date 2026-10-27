package io.objectbox.model;

import io.objectbox.flatbuffers.BaseVector;
import io.objectbox.flatbuffers.Constants;
import io.objectbox.flatbuffers.FlatBufferBuilder;
import io.objectbox.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelProperty extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_2_0_8();
    }

    public static ModelProperty getRootAsModelProperty(ByteBuffer byteBuffer) {
        return getRootAsModelProperty(byteBuffer, new ModelProperty());
    }

    public static ModelProperty getRootAsModelProperty(ByteBuffer byteBuffer, ModelProperty modelProperty) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return modelProperty.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ModelProperty __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public IdUid id() {
        return id(new IdUid());
    }

    public IdUid id(IdUid idUid) {
        int i__offset = __offset(4);
        if (i__offset != 0) {
            return idUid.__assign(i__offset + this.bb_pos, this.bb);
        }
        return null;
    }

    public String name() {
        int i__offset = __offset(6);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public ByteBuffer nameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public int type() {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return this.bb.getShort(i__offset + this.bb_pos) & 65535;
        }
        return 0;
    }

    public long flags() {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return ((long) this.bb.getInt(i__offset + this.bb_pos)) & 4294967295L;
        }
        return 0L;
    }

    public IdUid indexId() {
        return indexId(new IdUid());
    }

    public IdUid indexId(IdUid idUid) {
        int i__offset = __offset(12);
        if (i__offset != 0) {
            return idUid.__assign(i__offset + this.bb_pos, this.bb);
        }
        return null;
    }

    public String targetEntity() {
        int i__offset = __offset(14);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer targetEntityAsByteBuffer() {
        return __vector_as_bytebuffer(14, 1);
    }

    public ByteBuffer targetEntityInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 14, 1);
    }

    public String virtualTarget() {
        int i__offset = __offset(16);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer virtualTargetAsByteBuffer() {
        return __vector_as_bytebuffer(16, 1);
    }

    public ByteBuffer virtualTargetInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 1);
    }

    public String nameSecondary() {
        int i__offset = __offset(18);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameSecondaryAsByteBuffer() {
        return __vector_as_bytebuffer(18, 1);
    }

    public ByteBuffer nameSecondaryInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 1);
    }

    public long maxIndexValueLength() {
        int i__offset = __offset(20);
        if (i__offset != 0) {
            return ((long) this.bb.getInt(i__offset + this.bb_pos)) & 4294967295L;
        }
        return 0L;
    }

    public static void startModelProperty(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(9);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(0, i, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addShort(2, (short) i, 0);
    }

    public static void addFlags(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(3, (int) j, 0);
    }

    public static void addIndexId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(4, i, 0);
    }

    public static void addTargetEntity(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addVirtualTarget(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addNameSecondary(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static void addMaxIndexValueLength(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(8, (int) j, 0);
    }

    public static int endModelProperty(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ModelProperty get(int i) {
            return get(new ModelProperty(), i);
        }

        public ModelProperty get(ModelProperty modelProperty, int i) {
            return modelProperty.__assign(ModelProperty.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
