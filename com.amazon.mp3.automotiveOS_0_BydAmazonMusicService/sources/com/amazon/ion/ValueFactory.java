package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public interface ValueFactory {
    IonBlob newBlob(byte[] bArr, int i, int i2);

    IonBool newBool(boolean z);

    IonClob newClob(byte[] bArr, int i, int i2);

    IonList newEmptyList();

    IonSexp newEmptySexp();

    IonStruct newEmptyStruct();

    IonFloat newFloat(double d);

    IonInt newInt(int i);

    IonInt newInt(long j);

    IonInt newInt(Number number);

    IonValue newNull(IonType ionType);

    IonDecimal newNullDecimal();

    IonFloat newNullFloat();

    IonString newString(String str);

    IonSymbol newSymbol(SymbolToken symbolToken);

    IonSymbol newSymbol(String str);

    IonTimestamp newTimestamp(Timestamp timestamp);
}
