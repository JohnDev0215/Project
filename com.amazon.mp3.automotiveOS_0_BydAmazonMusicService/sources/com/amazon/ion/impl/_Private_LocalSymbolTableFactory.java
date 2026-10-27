package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonReader;
import com.amazon.ion.SymbolTable;

/* JADX INFO: loaded from: classes3.dex */
public interface _Private_LocalSymbolTableFactory {
    SymbolTable newLocalSymtab(IonCatalog ionCatalog, IonReader ionReader, boolean z);

    SymbolTable newLocalSymtab(SymbolTable symbolTable, SymbolTable... symbolTableArr);
}
