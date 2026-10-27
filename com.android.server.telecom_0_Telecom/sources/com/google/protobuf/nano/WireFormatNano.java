package com.google.protobuf.nano;

/* JADX INFO: loaded from: classes.dex */
public final class WireFormatNano {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    static int makeTag(int i, int i2) {
        return (i << 3) | i2;
    }
}
