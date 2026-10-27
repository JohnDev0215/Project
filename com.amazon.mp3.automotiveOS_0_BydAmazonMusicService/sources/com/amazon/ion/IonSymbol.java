package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public interface IonSymbol extends IonText {
    @Override // com.amazon.ion.IonText, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    @Deprecated
    int getSymbolId() throws NullValueException;

    @Override // com.amazon.ion.IonText
    String stringValue() throws UnknownSymbolException;

    SymbolToken symbolValue();
}
