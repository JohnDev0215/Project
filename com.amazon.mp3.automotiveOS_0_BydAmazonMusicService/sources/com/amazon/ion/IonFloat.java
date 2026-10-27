package com.amazon.ion;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes3.dex */
public interface IonFloat extends IonValue {
    BigDecimal bigDecimalValue() throws NullValueException;

    double doubleValue() throws NullValueException;

    @Override // com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    void setValue(double d);

    void setValue(float f);
}
