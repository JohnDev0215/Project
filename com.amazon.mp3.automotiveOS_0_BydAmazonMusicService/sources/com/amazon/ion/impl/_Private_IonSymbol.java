package com.amazon.ion.impl;

import com.amazon.ion.IonSymbol;
import com.amazon.ion.NullValueException;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.impl._Private_IonValue;

/* JADX INFO: loaded from: classes3.dex */
public interface _Private_IonSymbol extends IonSymbol {
    @Override // com.amazon.ion.IonSymbol, com.amazon.ion.IonText, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    @Override // com.amazon.ion.IonSymbol
    @Deprecated
    /* synthetic */ int getSymbolId() throws NullValueException;

    SymbolToken symbolValue(_Private_IonValue.SymbolTableProvider symbolTableProvider);
}
