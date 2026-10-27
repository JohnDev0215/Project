package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public interface IonTimestamp extends IonValue {
    @Override // com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    long getMillis() throws NullValueException;

    void setLocalOffset(int i) throws NullValueException;

    Timestamp timestampValue();
}
