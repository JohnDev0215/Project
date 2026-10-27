package com.android.providers.telephony.protobuf.nano;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class WireFormatNano {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final byte[] EMPTY_BYTES = new byte[0];

    public static int getTagFieldNumber(int i) {
        return i >>> 3;
    }

    static int getTagWireType(int i) {
        return i & 7;
    }

    static int makeTag(int i, int i2) {
        return (i << 3) | i2;
    }

    public static final int getRepeatedFieldArrayLength(CodedInputByteBufferNano codedInputByteBufferNano, int i) throws IOException {
        int position = codedInputByteBufferNano.getPosition();
        codedInputByteBufferNano.skipField(i);
        int i2 = 1;
        while (codedInputByteBufferNano.readTag() == i) {
            codedInputByteBufferNano.skipField(i);
            i2++;
        }
        codedInputByteBufferNano.rewindToPosition(position);
        return i2;
    }
}
