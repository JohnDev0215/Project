package com.android.networkstack.protobuf.nano;

import com.android.networkstack.protobuf.nano.ExtendableMessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ExtendableMessageNano<M extends ExtendableMessageNano<M>> extends MessageNano {
    protected FieldArray unknownFieldData;

    @Override // com.android.networkstack.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        if (this.unknownFieldData == null) {
            return 0;
        }
        int iComputeSerializedSize = 0;
        for (int i = 0; i < this.unknownFieldData.size(); i++) {
            iComputeSerializedSize += this.unknownFieldData.dataAt(i).computeSerializedSize();
        }
        return iComputeSerializedSize;
    }

    @Override // com.android.networkstack.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if (this.unknownFieldData == null) {
            return;
        }
        for (int i = 0; i < this.unknownFieldData.size(); i++) {
            this.unknownFieldData.dataAt(i).writeTo(codedOutputByteBufferNano);
        }
    }

    @Override // com.android.networkstack.protobuf.nano.MessageNano
    /* JADX INFO: renamed from: clone */
    public M mo4clone() throws CloneNotSupportedException {
        M m = (M) super.mo4clone();
        InternalNano.cloneUnknownFieldData(this, m);
        return m;
    }
}
