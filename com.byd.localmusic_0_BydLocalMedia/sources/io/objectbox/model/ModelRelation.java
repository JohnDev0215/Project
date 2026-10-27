package io.objectbox.model;

import io.objectbox.flatbuffers.BaseVector;
import io.objectbox.flatbuffers.Constants;
import io.objectbox.flatbuffers.FlatBufferBuilder;
import io.objectbox.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelRelation extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_2_0_8();
    }

    public static ModelRelation getRootAsModelRelation(ByteBuffer byteBuffer) {
        return getRootAsModelRelation(byteBuffer, new ModelRelation());
    }

    public static ModelRelation getRootAsModelRelation(ByteBuffer byteBuffer, ModelRelation modelRelation) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return modelRelation.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ModelRelation __assign(int i, ByteBuffer byteBuffer) {
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

    public IdUid targetEntityId() {
        return targetEntityId(new IdUid());
    }

    public IdUid targetEntityId(IdUid idUid) {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return idUid.__assign(i__offset + this.bb_pos, this.bb);
        }
        return null;
    }

    public static void startModelRelation(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(0, i, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addTargetEntityId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(2, i, 0);
    }

    public static int endModelRelation(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ModelRelation get(int i) {
            return get(new ModelRelation(), i);
        }

        public ModelRelation get(ModelRelation modelRelation, int i) {
            return modelRelation.__assign(ModelRelation.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
