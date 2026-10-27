package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.system.IonWriterBuilderBase;

/* JADX INFO: loaded from: classes3.dex */
abstract class IonWriterBuilderBase<T extends IonWriterBuilderBase> extends IonWriterBuilder {
    private IonCatalog myCatalog;
    private SymbolTable[] myImports;

    protected IonWriterBuilderBase() {
    }

    protected IonWriterBuilderBase(IonWriterBuilderBase ionWriterBuilderBase) {
        this.myCatalog = ionWriterBuilderBase.myCatalog;
        this.myImports = ionWriterBuilderBase.myImports;
    }

    protected void mutationCheck() {
        throw new UnsupportedOperationException("This builder is immutable");
    }

    public IonCatalog getCatalog() {
        return this.myCatalog;
    }

    public void setCatalog(IonCatalog ionCatalog) {
        mutationCheck();
        this.myCatalog = ionCatalog;
    }

    private static SymbolTable[] safeCopy(SymbolTable[] symbolTableArr) {
        return (symbolTableArr == null || symbolTableArr.length == 0) ? symbolTableArr : (SymbolTable[]) symbolTableArr.clone();
    }

    public SymbolTable[] getImports() {
        return safeCopy(this.myImports);
    }
}
