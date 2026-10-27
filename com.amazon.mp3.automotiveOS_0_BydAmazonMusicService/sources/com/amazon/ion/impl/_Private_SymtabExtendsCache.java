package com.amazon.ion.impl;

import com.amazon.ion.SymbolTable;

/* JADX INFO: loaded from: classes3.dex */
public final class _Private_SymtabExtendsCache {
    private SymbolTable myReaderSymtab;
    private int myReaderSymtabMaxId;
    private boolean myResult;
    private SymbolTable myWriterSymtab;
    private int myWriterSymtabMaxId;

    public boolean symtabsCompat(SymbolTable symbolTable, SymbolTable symbolTable2) {
        if (this.myWriterSymtab == symbolTable && this.myReaderSymtab == symbolTable2 && this.myWriterSymtabMaxId == symbolTable.getMaxId() && this.myReaderSymtabMaxId == symbolTable2.getMaxId()) {
            return this.myResult;
        }
        this.myResult = _Private_Utils.symtabExtends(symbolTable, symbolTable2);
        this.myWriterSymtab = symbolTable;
        this.myReaderSymtab = symbolTable2;
        this.myWriterSymtabMaxId = symbolTable.getMaxId();
        this.myReaderSymtabMaxId = symbolTable2.getMaxId();
        return this.myResult;
    }
}
