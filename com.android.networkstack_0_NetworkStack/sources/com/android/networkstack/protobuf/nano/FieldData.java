package com.android.networkstack.protobuf.nano;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class FieldData implements Cloneable {
    abstract int computeSerializedSize();

    abstract void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException;
}
