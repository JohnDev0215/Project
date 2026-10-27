package com.amazon.ion.impl;

import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;

/* JADX INFO: loaded from: classes3.dex */
public interface _Private_IonValue extends IonValue {

    public interface SymbolTableProvider {
        SymbolTable getSymbolTable();
    }

    int findTypeAnnotation(String str);

    int getElementId();

    @Override // com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    SymbolToken getFieldNameSymbol(SymbolTableProvider symbolTableProvider);

    SymbolToken[] getTypeAnnotationSymbols(SymbolTableProvider symbolTableProvider);
}
