package com.amazon.ion.impl;

import com.amazon.ion.IonDatagram;
import com.amazon.ion.SymbolTable;

/* JADX INFO: loaded from: classes3.dex */
public interface _Private_IonDatagram extends _Private_IonValue, IonDatagram {
    void appendTrailingSymbolTable(SymbolTable symbolTable);

    @Override // com.amazon.ion.impl._Private_IonValue
    /* synthetic */ int getElementId();

    @Override // com.amazon.ion.impl._Private_IonValue, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();
}
