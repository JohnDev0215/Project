package com.amazon.ion;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public interface IonInt extends IonValue {
    BigInteger bigIntegerValue();

    @Override // com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    IntegerSize getIntegerSize();

    int intValue() throws NullValueException;

    long longValue() throws NullValueException;

    void setValue(int i);
}
