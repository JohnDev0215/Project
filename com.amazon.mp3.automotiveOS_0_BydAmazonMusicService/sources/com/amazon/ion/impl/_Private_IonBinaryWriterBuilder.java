package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SubstituteSymbolTableException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder;
import com.amazon.ion.system.IonBinaryWriterBuilder;
import com.amazon.ion.system.IonSystemBuilder;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
public class _Private_IonBinaryWriterBuilder extends IonBinaryWriterBuilder {
    private final _Private_IonManagedBinaryWriterBuilder myBinaryWriterBuilder;
    private SymbolTable myInitialSymbolTable;
    private ValueFactory mySymtabValueFactory;

    public _Private_IonBinaryWriterBuilder immutable() {
        return this;
    }

    private _Private_IonBinaryWriterBuilder() {
        this.myBinaryWriterBuilder = _Private_IonManagedBinaryWriterBuilder.create(_Private_IonManagedBinaryWriterBuilder.AllocatorMode.POOLED).withPaddedLengthPreallocation(0);
    }

    private _Private_IonBinaryWriterBuilder(_Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder) {
        super(_private_ionbinarywriterbuilder);
        this.mySymtabValueFactory = _private_ionbinarywriterbuilder.mySymtabValueFactory;
        this.myInitialSymbolTable = _private_ionbinarywriterbuilder.myInitialSymbolTable;
        this.myBinaryWriterBuilder = _private_ionbinarywriterbuilder.myBinaryWriterBuilder.copy();
    }

    public static _Private_IonBinaryWriterBuilder standard() {
        return new Mutable();
    }

    public final _Private_IonBinaryWriterBuilder copy() {
        return new Mutable();
    }

    public ValueFactory getSymtabValueFactory() {
        return this.mySymtabValueFactory;
    }

    public void setSymtabValueFactory(ValueFactory valueFactory) {
        mutationCheck();
        this.mySymtabValueFactory = valueFactory;
    }

    public SymbolTable getInitialSymbolTable() {
        return this.myInitialSymbolTable;
    }

    public void setInitialSymbolTable(SymbolTable symbolTable) {
        mutationCheck();
        if (symbolTable != null) {
            if (symbolTable.isLocalTable()) {
                for (SymbolTable symbolTable2 : ((LocalSymbolTable) symbolTable).getImportedTablesNoCopy()) {
                    if (symbolTable2.isSubstitute()) {
                        throw new SubstituteSymbolTableException("Cannot encode with substitute symbol table: " + symbolTable2.getName());
                    }
                }
            } else if (!symbolTable.isSystemTable()) {
                throw new IllegalArgumentException("symtab must be local or system table");
            }
        }
        this.myInitialSymbolTable = symbolTable;
        this.myBinaryWriterBuilder.withInitialSymbolTable(symbolTable);
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public void setCatalog(IonCatalog ionCatalog) {
        super.setCatalog(ionCatalog);
        this.myBinaryWriterBuilder.withCatalog(ionCatalog);
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public void setStreamCopyOptimized(boolean z) {
        super.setStreamCopyOptimized(z);
        this.myBinaryWriterBuilder.withStreamCopyOptimization(z);
    }

    private _Private_IonBinaryWriterBuilder fillDefaults() {
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderCopy = copy();
        if (_private_ionbinarywriterbuilderCopy.getSymtabValueFactory() == null) {
            _private_ionbinarywriterbuilderCopy.setSymtabValueFactory(IonSystemBuilder.standard().build());
        }
        return _private_ionbinarywriterbuilderCopy.immutable();
    }

    @Override // com.amazon.ion.system.IonWriterBuilder
    public final IonWriter build(OutputStream outputStream) {
        try {
            return fillDefaults().myBinaryWriterBuilder.newWriter(outputStream);
        } catch (IOException e) {
            throw new IonException("I/O Error", e);
        }
    }

    private static final class Mutable extends _Private_IonBinaryWriterBuilder {
        @Override // com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        protected void mutationCheck() {
        }

        private Mutable() {
            super();
        }

        private Mutable(_Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder) {
            super();
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder
        public _Private_IonBinaryWriterBuilder immutable() {
            return new _Private_IonBinaryWriterBuilder();
        }
    }
}
