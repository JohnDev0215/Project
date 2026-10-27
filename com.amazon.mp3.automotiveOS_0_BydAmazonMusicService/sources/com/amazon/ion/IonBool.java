package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public interface IonBool extends IonValue {
    boolean booleanValue() throws NullValueException;

    @Override // com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();
}
