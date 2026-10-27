package com.amazon.ion;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes3.dex */
public interface IonDecimal extends IonNumber {
    BigDecimal bigDecimalValue();

    Decimal decimalValue();

    double doubleValue() throws NullValueException;

    @Override // com.amazon.ion.IonNumber, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    void setValue(double d);

    void setValue(float f);

    void setValue(BigDecimal bigDecimal);
}
