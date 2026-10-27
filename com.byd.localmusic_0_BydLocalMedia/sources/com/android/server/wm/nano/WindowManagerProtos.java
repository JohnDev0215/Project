package com.android.server.wm.nano;

import com.android.framework.protobuf.nano.CodedInputByteBufferNano;
import com.android.framework.protobuf.nano.CodedOutputByteBufferNano;
import com.android.framework.protobuf.nano.InternalNano;
import com.android.framework.protobuf.nano.InvalidProtocolBufferNanoException;
import com.android.framework.protobuf.nano.MessageNano;
import com.android.framework.protobuf.nano.WireFormatNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface WindowManagerProtos {

    public static final class TaskSnapshotProto extends MessageNano {
        private static volatile TaskSnapshotProto[] _emptyArray;
        public long id;
        public int insetBottom;
        public int insetLeft;
        public int insetRight;
        public int insetTop;
        public boolean isRealSnapshot;
        public boolean isTranslucent;
        public float legacyScale;
        public int orientation;
        public int rotation;
        public int systemUiVisibility;
        public int taskHeight;
        public int taskWidth;
        public String topActivityComponent;
        public int windowingMode;

        public static TaskSnapshotProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TaskSnapshotProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TaskSnapshotProto() {
            clear();
        }

        public TaskSnapshotProto clear() {
            this.orientation = 0;
            this.insetLeft = 0;
            this.insetTop = 0;
            this.insetRight = 0;
            this.insetBottom = 0;
            this.isRealSnapshot = false;
            this.windowingMode = 0;
            this.systemUiVisibility = 0;
            this.isTranslucent = false;
            this.topActivityComponent = "";
            this.legacyScale = 0.0f;
            this.id = 0L;
            this.rotation = 0;
            this.taskWidth = 0;
            this.taskHeight = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.android.framework.protobuf.nano.MessageNano
        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            int i = this.orientation;
            if (i != 0) {
                codedOutputByteBufferNano.writeInt32(1, i);
            }
            int i2 = this.insetLeft;
            if (i2 != 0) {
                codedOutputByteBufferNano.writeInt32(2, i2);
            }
            int i3 = this.insetTop;
            if (i3 != 0) {
                codedOutputByteBufferNano.writeInt32(3, i3);
            }
            int i4 = this.insetRight;
            if (i4 != 0) {
                codedOutputByteBufferNano.writeInt32(4, i4);
            }
            int i5 = this.insetBottom;
            if (i5 != 0) {
                codedOutputByteBufferNano.writeInt32(5, i5);
            }
            boolean z = this.isRealSnapshot;
            if (z) {
                codedOutputByteBufferNano.writeBool(6, z);
            }
            int i6 = this.windowingMode;
            if (i6 != 0) {
                codedOutputByteBufferNano.writeInt32(7, i6);
            }
            int i7 = this.systemUiVisibility;
            if (i7 != 0) {
                codedOutputByteBufferNano.writeInt32(8, i7);
            }
            boolean z2 = this.isTranslucent;
            if (z2) {
                codedOutputByteBufferNano.writeBool(9, z2);
            }
            if (!this.topActivityComponent.equals("")) {
                codedOutputByteBufferNano.writeString(10, this.topActivityComponent);
            }
            if (Float.floatToIntBits(this.legacyScale) != Float.floatToIntBits(0.0f)) {
                codedOutputByteBufferNano.writeFloat(11, this.legacyScale);
            }
            long j = this.id;
            if (j != 0) {
                codedOutputByteBufferNano.writeInt64(12, j);
            }
            int i8 = this.rotation;
            if (i8 != 0) {
                codedOutputByteBufferNano.writeInt32(13, i8);
            }
            int i9 = this.taskWidth;
            if (i9 != 0) {
                codedOutputByteBufferNano.writeInt32(14, i9);
            }
            int i10 = this.taskHeight;
            if (i10 != 0) {
                codedOutputByteBufferNano.writeInt32(15, i10);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        @Override // com.android.framework.protobuf.nano.MessageNano
        protected int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            int i = this.orientation;
            if (i != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
            }
            int i2 = this.insetLeft;
            if (i2 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, i2);
            }
            int i3 = this.insetTop;
            if (i3 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, i3);
            }
            int i4 = this.insetRight;
            if (i4 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(4, i4);
            }
            int i5 = this.insetBottom;
            if (i5 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(5, i5);
            }
            boolean z = this.isRealSnapshot;
            if (z) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(6, z);
            }
            int i6 = this.windowingMode;
            if (i6 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(7, i6);
            }
            int i7 = this.systemUiVisibility;
            if (i7 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(8, i7);
            }
            boolean z2 = this.isTranslucent;
            if (z2) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(9, z2);
            }
            if (!this.topActivityComponent.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(10, this.topActivityComponent);
            }
            if (Float.floatToIntBits(this.legacyScale) != Float.floatToIntBits(0.0f)) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeFloatSize(11, this.legacyScale);
            }
            long j = this.id;
            if (j != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(12, j);
            }
            int i8 = this.rotation;
            if (i8 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(13, i8);
            }
            int i9 = this.taskWidth;
            if (i9 != 0) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(14, i9);
            }
            int i10 = this.taskHeight;
            return i10 != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(15, i10) : iComputeSerializedSize;
        }

        @Override // com.android.framework.protobuf.nano.MessageNano
        public TaskSnapshotProto mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        this.orientation = codedInputByteBufferNano.readInt32();
                        break;
                    case 16:
                        this.insetLeft = codedInputByteBufferNano.readInt32();
                        break;
                    case 24:
                        this.insetTop = codedInputByteBufferNano.readInt32();
                        break;
                    case 32:
                        this.insetRight = codedInputByteBufferNano.readInt32();
                        break;
                    case 40:
                        this.insetBottom = codedInputByteBufferNano.readInt32();
                        break;
                    case 48:
                        this.isRealSnapshot = codedInputByteBufferNano.readBool();
                        break;
                    case 56:
                        this.windowingMode = codedInputByteBufferNano.readInt32();
                        break;
                    case 64:
                        this.systemUiVisibility = codedInputByteBufferNano.readInt32();
                        break;
                    case 72:
                        this.isTranslucent = codedInputByteBufferNano.readBool();
                        break;
                    case 82:
                        this.topActivityComponent = codedInputByteBufferNano.readString();
                        break;
                    case 93:
                        this.legacyScale = codedInputByteBufferNano.readFloat();
                        break;
                    case 96:
                        this.id = codedInputByteBufferNano.readInt64();
                        break;
                    case 104:
                        this.rotation = codedInputByteBufferNano.readInt32();
                        break;
                    case 112:
                        this.taskWidth = codedInputByteBufferNano.readInt32();
                        break;
                    case 120:
                        this.taskHeight = codedInputByteBufferNano.readInt32();
                        break;
                    default:
                        if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                            return this;
                        }
                        break;
                        break;
                }
            }
        }

        public static TaskSnapshotProto parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (TaskSnapshotProto) MessageNano.mergeFrom(new TaskSnapshotProto(), bArr);
        }

        public static TaskSnapshotProto parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new TaskSnapshotProto().mergeFrom(codedInputByteBufferNano);
        }
    }
}
