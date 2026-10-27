package io.objectbox.model;

import io.objectbox.flatbuffers.BaseVector;
import io.objectbox.flatbuffers.FlatBufferBuilder;
import io.objectbox.flatbuffers.Struct;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public final class IdUid extends Struct {
    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public IdUid __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long id() {
        return ((long) this.bb.getInt(this.bb_pos + 0)) & 4294967295L;
    }

    public long uid() {
        return this.bb.getLong(this.bb_pos + 8);
    }

    public static int createIdUid(FlatBufferBuilder flatBufferBuilder, long j, long j2) {
        flatBufferBuilder.prep(8, 16);
        flatBufferBuilder.putLong(j2);
        flatBufferBuilder.pad(4);
        flatBufferBuilder.putInt((int) j);
        return flatBufferBuilder.offset();
    }

    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public IdUid get(int i) {
            return get(new IdUid(), i);
        }

        public IdUid get(IdUid idUid, int i) {
            return idUid.__assign(__element(i), this.bb);
        }
    }
}
