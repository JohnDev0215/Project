package com.amazon.ion;

import com.amazon.ion.facet.Faceted;
import java.io.Closeable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public interface IonReader extends Closeable, Faceted {
    BigInteger bigIntegerValue();

    boolean booleanValue();

    Decimal decimalValue();

    double doubleValue();

    int getDepth();

    @Deprecated
    int getFieldId();

    String getFieldName();

    SymbolToken getFieldNameSymbol();

    IntegerSize getIntegerSize();

    SymbolTable getSymbolTable();

    IonType getType();

    SymbolToken[] getTypeAnnotationSymbols();

    int intValue();

    boolean isNullValue();

    long longValue();

    byte[] newBytes();

    IonType next();

    void stepIn();

    void stepOut();

    String stringValue();

    SymbolToken symbolValue();

    Timestamp timestampValue();
}
