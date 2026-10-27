package io.objectbox.model;

import io.objectbox.flatbuffers.BaseVector;
import io.objectbox.flatbuffers.Constants;
import io.objectbox.flatbuffers.FlatBufferBuilder;
import io.objectbox.flatbuffers.Table;
import io.objectbox.model.ModelProperty;
import io.objectbox.model.ModelRelation;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelEntity extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_2_0_8();
    }

    public static ModelEntity getRootAsModelEntity(ByteBuffer byteBuffer) {
        return getRootAsModelEntity(byteBuffer, new ModelEntity());
    }

    public static ModelEntity getRootAsModelEntity(ByteBuffer byteBuffer, ModelEntity modelEntity) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return modelEntity.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ModelEntity __assign(int i, ByteBuffer byteBuffer) {
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

    public ModelProperty properties(int i) {
        return properties(new ModelProperty(), i);
    }

    public ModelProperty properties(ModelProperty modelProperty, int i) {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return modelProperty.__assign(__indirect(__vector(i__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int propertiesLength() {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return __vector_len(i__offset);
        }
        return 0;
    }

    public ModelProperty.Vector propertiesVector() {
        return propertiesVector(new ModelProperty.Vector());
    }

    public ModelProperty.Vector propertiesVector(ModelProperty.Vector vector) {
        int i__offset = __offset(8);
        if (i__offset != 0) {
            return vector.__assign(__vector(i__offset), 4, this.bb);
        }
        return null;
    }

    public IdUid lastPropertyId() {
        return lastPropertyId(new IdUid());
    }

    public IdUid lastPropertyId(IdUid idUid) {
        int i__offset = __offset(10);
        if (i__offset != 0) {
            return idUid.__assign(i__offset + this.bb_pos, this.bb);
        }
        return null;
    }

    public ModelRelation relations(int i) {
        return relations(new ModelRelation(), i);
    }

    public ModelRelation relations(ModelRelation modelRelation, int i) {
        int i__offset = __offset(12);
        if (i__offset != 0) {
            return modelRelation.__assign(__indirect(__vector(i__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int relationsLength() {
        int i__offset = __offset(12);
        if (i__offset != 0) {
            return __vector_len(i__offset);
        }
        return 0;
    }

    public ModelRelation.Vector relationsVector() {
        return relationsVector(new ModelRelation.Vector());
    }

    public ModelRelation.Vector relationsVector(ModelRelation.Vector vector) {
        int i__offset = __offset(12);
        if (i__offset != 0) {
            return vector.__assign(__vector(i__offset), 4, this.bb);
        }
        return null;
    }

    public long flags() {
        int i__offset = __offset(14);
        if (i__offset != 0) {
            return ((long) this.bb.getInt(i__offset + this.bb_pos)) & 4294967295L;
        }
        return 0L;
    }

    public String nameSecondary() {
        int i__offset = __offset(16);
        if (i__offset != 0) {
            return __string(i__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameSecondaryAsByteBuffer() {
        return __vector_as_bytebuffer(16, 1);
    }

    public ByteBuffer nameSecondaryInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 1);
    }

    public static void startModelEntity(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(7);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(0, i, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addProperties(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createPropertiesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startPropertiesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addLastPropertyId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(3, i, 0);
    }

    public static void addRelations(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static int createRelationsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startRelationsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addFlags(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(5, (int) j, 0);
    }

    public static void addNameSecondary(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static int endModelEntity(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ModelEntity get(int i) {
            return get(new ModelEntity(), i);
        }

        public ModelEntity get(ModelEntity modelEntity, int i) {
            return modelEntity.__assign(ModelEntity.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
