package com.google.protobuf.nano;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class InternalNano {
    public static final Object LAZY_INIT_LOCK;

    static {
        Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        LAZY_INIT_LOCK = new Object();
    }

    public static void cloneUnknownFieldData(ExtendableMessageNano extendableMessageNano, ExtendableMessageNano extendableMessageNano2) {
        FieldArray fieldArray = extendableMessageNano.unknownFieldData;
        if (fieldArray == null) {
            return;
        }
        fieldArray.clone();
        throw null;
    }
}
