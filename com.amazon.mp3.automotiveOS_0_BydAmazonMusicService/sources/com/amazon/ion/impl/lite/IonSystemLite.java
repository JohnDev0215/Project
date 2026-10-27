package com.amazon.ion.impl.lite;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonLoader;
import com.amazon.ion.IonSystem;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;
import com.amazon.ion.impl._Private_IonReaderBuilder;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;

/* JADX INFO: loaded from: classes3.dex */
final class IonSystemLite extends ValueFactoryLite implements IonSystem {
    private final IonCatalog _catalog;
    private final IonLoader _loader;
    private final SymbolTable _system_symbol_table;
    private final _Private_IonBinaryWriterBuilder myBinaryWriterBuilder;
    private final IonReaderBuilder myReaderBuilder;
    private final IonTextWriterBuilder myTextWriterBuilder;

    public IonSystemLite(IonTextWriterBuilder ionTextWriterBuilder, _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder, IonReaderBuilder ionReaderBuilder) {
        IonCatalog catalog = ionTextWriterBuilder.getCatalog();
        this._catalog = catalog;
        this.myReaderBuilder = ((_Private_IonReaderBuilder) ionReaderBuilder).withLstFactory(this._lstFactory).immutable();
        this._loader = new IonLoaderLite(this, catalog);
        this._system_symbol_table = _private_ionbinarywriterbuilder.getInitialSymbolTable();
        this.myTextWriterBuilder = ionTextWriterBuilder.immutable();
        set_system(this);
        _private_ionbinarywriterbuilder.setSymtabValueFactory(this);
        this.myBinaryWriterBuilder = _private_ionbinarywriterbuilder.immutable();
    }

    IonReaderBuilder getReaderBuilder() {
        return this.myReaderBuilder;
    }

    public IonCatalog getCatalog() {
        return this._catalog;
    }

    @Override // com.amazon.ion.IonSystem
    public final SymbolTable getSystemSymbolTable() {
        return this._system_symbol_table;
    }
}
