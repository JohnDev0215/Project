package com.android.networkstack.protobuf.nano;

/* JADX INFO: loaded from: classes.dex */
public final class WireFormatNano {
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];

    static int makeTag(int i, int i2) {
        return (i << 3) | i2;
    }
}
