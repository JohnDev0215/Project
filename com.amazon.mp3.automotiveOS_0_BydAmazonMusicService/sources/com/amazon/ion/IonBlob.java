package com.amazon.ion;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface IonBlob extends IonLob {
    @Override // com.amazon.ion.IonLob, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    void printBase64(Appendable appendable) throws NullValueException, IOException;
}
