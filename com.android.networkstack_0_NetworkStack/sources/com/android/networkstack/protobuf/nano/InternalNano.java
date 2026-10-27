package com.android.networkstack.protobuf.nano;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class InternalNano {
    static {
        Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
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
