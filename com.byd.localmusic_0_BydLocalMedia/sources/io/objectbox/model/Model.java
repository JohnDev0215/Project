package io.objectbox.model;

import io.objectbox.flatbuffers.BaseVector;
import io.objectbox.flatbuffers.ByteVector;
import io.objectbox.flatbuffers.Constants;
import io.objectbox.flatbuffers.FlatBufferBuilder;
import io.objectbox.flatbuffers.Table;
import io.objectbox.model.ModelEntity;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes5.dex */
public final class Model extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_2_0_8();
    }

    public static Model getRootAsModel(ByteBuffer byteBuffer) {
        return getRootAsModel(byteBuffer, new Model());
    }

    public static Model getRootAsModel(ByteBuffer byteBuffer, Model model) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return model.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Model __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long modelVersion() {
        int i__offset = __offset(4);
        if (i__offset != 0) {
            return ((long) this.bb.getInt(i__offset + this.bb_pos)) & 4294967295L;
        }
        return 0L;
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

    public long version() {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return this.bb.getLong(i__offset + this.bb_pos);
        }
        return 0L;
    }

    public ModelEntity entities(int i) {
        return entities(new ModelEntity(), i);
    }

    public ModelEntity entities(ModelEntity modelEntity, int i) {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return modelEntity.__assign(__indirect(__vector(i__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int entitiesLength() {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return __vector_len(i__offset);
        }
        return 0;
    }

    public ModelEntity.Vector entitiesVector() {
        return entitiesVector(new ModelEntity.Vector());
    }

    public ModelEntity.Vector entitiesVector(ModelEntity.Vector vector) {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return vector.__assign(__vector(i__offset), 4, this.bb);
        }
        return null;
    }

    public IdUid lastEntityId() {
        return lastEntityId(new IdUid());
    }

    public IdUid lastEntityId(IdUid idUid) {
        int i__offset = __offset(12);
        if (i__offset != 0) {
            return idUid.__assign(i__offset + this.bb_pos, this.bb);
        }
        return null;
    }

    public IdUid lastIndexId() {
        return lastIndexId(new IdUid());
    }

    public IdUid lastIndexId(IdUid idUid) {
        int i__offset = __offset(14);
        if (i__offset != 0) {
            return idUid.__assign(i__offset + this.bb_pos, this.bb);
        }
        return null;
    }

    public IdUid lastSequenceId() {
        return lastSequenceId(new IdUid());
    }

    public IdUid lastSequenceId(IdUid idUid) {
        int i__offset = __offset(16);
        if (i__offset != 0) {
            return idUid.__assign(i__offset + this.bb_pos, this.bb);
        }
        return null;
    }

    public IdUid lastRelationId() {
        return lastRelationId(new IdUid());
    }

    public IdUid lastRelationId(IdUid idUid) {
        int i__offset = __offset(18);
        if (i__offset != 0) {
            return idUid.__assign(i__offset + this.bb_pos, this.bb);
        }
        return null;
    }

    public int hash(int i) {
        int i__offset = __offset(20);
        if (i__offset != 0) {
            return this.bb.get(__vector(i__offset) + (i * 1)) & 255;
        }
        return 0;
    }

    public int hashLength() {
        int i__offset = __offset(20);
        if (i__offset != 0) {
            return __vector_len(i__offset);
        }
        return 0;
    }

    public ByteVector hashVector() {
        return hashVector(new ByteVector());
    }

    public ByteVector hashVector(ByteVector byteVector) {
        int i__offset = __offset(20);
        if (i__offset != 0) {
            return byteVector.__assign(__vector(i__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer hashAsByteBuffer() {
        return __vector_as_bytebuffer(20, 1);
    }

    public ByteBuffer hashInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 20, 1);
    }

    public static void startModel(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(9);
    }

    public static void addModelVersion(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(0, (int) j, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addVersion(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(2, j, 0L);
    }

    public static void addEntities(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static int createEntitiesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startEntitiesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addLastEntityId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(4, i, 0);
    }

    public static void addLastIndexId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(5, i, 0);
    }

    public static void addLastSequenceId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(6, i, 0);
    }

    public static void addLastRelationId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(7, i, 0);
    }

    public static void addHash(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static int createHashVector(FlatBufferBuilder flatBufferBuilder, byte[] bArr) {
        return flatBufferBuilder.createByteVector(bArr);
    }

    public static int createHashVector(FlatBufferBuilder flatBufferBuilder, ByteBuffer byteBuffer) {
        return flatBufferBuilder.createByteVector(byteBuffer);
    }

    public static void startHashVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static int endModel(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static void finishModelBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finish(i);
    }

    public static void finishSizePrefixedModelBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finishSizePrefixed(i);
    }

    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Model get(int i) {
            return get(new Model(), i);
        }

        public Model get(Model model, int i) {
            return model.__assign(Model.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
