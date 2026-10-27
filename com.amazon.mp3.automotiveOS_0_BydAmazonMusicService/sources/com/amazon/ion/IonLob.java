package com.amazon.ion;

import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public interface IonLob extends IonValue {
    int byteSize();

    @Override // com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    InputStream newInputStream();
}
