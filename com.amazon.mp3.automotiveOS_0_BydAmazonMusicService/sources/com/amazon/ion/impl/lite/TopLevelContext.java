package com.amazon.ion.impl.lite;

import com.amazon.ion.SymbolTable;

/* JADX INFO: loaded from: classes3.dex */
final class TopLevelContext implements IonContext {
    private final IonDatagramLite _datagram;
    private final SymbolTable _symbols;

    private TopLevelContext(SymbolTable symbolTable, IonDatagramLite ionDatagramLite) {
        this._symbols = symbolTable;
        this._datagram = ionDatagramLite;
    }

    static TopLevelContext wrap(SymbolTable symbolTable, IonDatagramLite ionDatagramLite) {
        return new TopLevelContext(symbolTable, ionDatagramLite);
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonDatagramLite getContextContainer() {
        return this._datagram;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public SymbolTable getContextSymbolTable() {
        return this._symbols;
    }

    @Override // com.amazon.ion.impl.lite.IonContext
    public IonSystemLite getSystem() {
        return this._datagram.getSystem();
    }
}
