package com.android.providers.telephony.protobuf.nano;

import com.android.providers.telephony.protobuf.nano.ExtendableMessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class Extension<M extends ExtendableMessageNano<M>, T> {
    protected final Class<T> clazz;

    abstract int computeSerializedSize(Object obj);

    abstract void writeTo(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException;
}
