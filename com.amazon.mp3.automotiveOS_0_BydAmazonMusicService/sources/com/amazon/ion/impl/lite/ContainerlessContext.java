package com.amazon.ion.impl.lite;

import com.amazon.ion.SymbolTable;

/* JADX INFO: loaded from: classes3.dex */
class ContainerlessContext implements IonContext {
    private final SymbolTable _symbols;
    private final IonSystemLite _system;

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonContainerLite getContextContainer() {
        return null;
    }

    public static ContainerlessContext wrap(IonSystemLite ionSystemLite) {
        return new ContainerlessContext(ionSystemLite, null);
    }

    private ContainerlessContext(IonSystemLite ionSystemLite, SymbolTable symbolTable) {
        this._system = ionSystemLite;
        this._symbols = symbolTable;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonSystemLite getSystem() {
        return this._system;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public SymbolTable getContextSymbolTable() {
        return this._symbols;
    }
}
