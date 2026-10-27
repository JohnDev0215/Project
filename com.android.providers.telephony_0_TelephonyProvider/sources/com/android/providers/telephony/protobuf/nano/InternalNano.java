package com.android.providers.telephony.protobuf.nano;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class InternalNano {
    public static final Object LAZY_INIT_LOCK;
    static final Charset UTF_8 = Charset.forName("UTF-8");

    static {
        Charset.forName("ISO-8859-1");
        LAZY_INIT_LOCK = new Object();
    }

    public static void cloneUnknownFieldData(ExtendableMessageNano extendableMessageNano, ExtendableMessageNano extendableMessageNano2) {
        FieldArray fieldArray = extendableMessageNano.unknownFieldData;
        if (fieldArray != null) {
            extendableMessageNano2.unknownFieldData = fieldArray.m1clone();
        }
    }
}
