package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.LocalSymbolTableAsStruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
class IonWriterUser extends _Private_IonWriterBase implements _Private_IonWriter {
    private final IonCatalog _catalog;
    IonWriterSystem _current_writer;
    private IonStruct _symbol_table_value;
    private final ValueFactory _symtab_value_factory;
    final IonWriterSystem _system_writer;

    IonWriterUser(IonCatalog ionCatalog, ValueFactory valueFactory, IonWriterSystem ionWriterSystem) {
        this._symtab_value_factory = valueFactory;
        this._catalog = ionCatalog;
        this._system_writer = ionWriterSystem;
        this._current_writer = ionWriterSystem;
    }

    IonWriterUser(IonCatalog ionCatalog, ValueFactory valueFactory, IonWriterSystem ionWriterSystem, SymbolTable symbolTable) {
        this(ionCatalog, valueFactory, ionWriterSystem);
        SymbolTable defaultSystemSymtab = ionWriterSystem.getDefaultSystemSymtab();
        if (symbolTable.isLocalTable() || symbolTable != defaultSystemSymtab) {
            try {
                setSymbolTable(symbolTable);
            } catch (IOException e) {
                throw new IonException(e);
            }
        }
    }

    int findAnnotation(String str) {
        return this._current_writer.findAnnotation(str);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public int getDepth() {
        return this._current_writer.getDepth();
    }

    @Override // com.amazon.ion.IonWriter
    public boolean isInStruct() {
        return this._current_writer.isInStruct();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Flushable
    public void flush() throws IOException {
        this._current_writer.flush();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            try {
                if (getDepth() == 0) {
                    finish();
                }
            } finally {
                this._current_writer.close();
            }
        } finally {
            this._system_writer.close();
        }
    }

    public final void finish() throws IOException {
        if (symbol_table_being_collected()) {
            throw new IllegalStateException("IonWriter.finish() can only be called at top-level.");
        }
        this._system_writer.finish();
    }

    SymbolTable activeSystemSymbolTable() {
        return getSymbolTable().getSystemSymbolTable();
    }

    private boolean symbol_table_being_collected() {
        return this._current_writer != this._system_writer;
    }

    private void open_local_symbol_table_copy() {
        this._symbol_table_value = this._symtab_value_factory.newEmptyStruct();
        SymbolToken[] typeAnnotationSymbols = this._system_writer.getTypeAnnotationSymbols();
        this._system_writer.clearAnnotations();
        this._symbol_table_value.setTypeAnnotationSymbols(typeAnnotationSymbols);
        this._current_writer = new IonWriterSystemTree(activeSystemSymbolTable(), this._catalog, this._symbol_table_value, null);
    }

    private void close_local_symbol_table_copy() throws IOException {
        SymbolTable symbolTableNewLocalSymtab = ((LocalSymbolTableAsStruct.Factory) ((_Private_ValueFactory) this._symtab_value_factory).getLstFactory()).newLocalSymtab(this._catalog, this._symbol_table_value);
        this._symbol_table_value = null;
        this._current_writer = this._system_writer;
        setSymbolTable(symbolTableNewLocalSymtab);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final void setSymbolTable(SymbolTable symbolTable) throws IOException {
        if (symbolTable == null || _Private_Utils.symtabIsSharedNotSystem(symbolTable)) {
            throw new IllegalArgumentException("symbol table must be local or system to be set, or reset");
        }
        if (getDepth() > 0) {
            throw new IllegalStateException("the symbol table cannot be set, or reset, while a container is open");
        }
        if (symbolTable.isSystemTable()) {
            writeIonVersionMarker(symbolTable);
        } else {
            this._system_writer.writeLocalSymtab(symbolTable);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public final SymbolTable getSymbolTable() {
        return this._system_writer.getSymbolTable();
    }

    @Override // com.amazon.ion.IonWriter
    public final void setFieldNameSymbol(SymbolToken symbolToken) {
        this._current_writer.setFieldNameSymbol(symbolToken);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final boolean isFieldNameSet() {
        return this._current_writer.isFieldNameSet();
    }

    @Override // com.amazon.ion.IonWriter
    public void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr) {
        this._current_writer.setTypeAnnotationSymbols(symbolTokenArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void stepIn(IonType ionType) throws IOException {
        if (ionType == IonType.STRUCT && this._current_writer.getDepth() == 0 && findAnnotation("$ion_symbol_table") == 0) {
            open_local_symbol_table_copy();
        } else {
            this._current_writer.stepIn(ionType);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        if (symbol_table_being_collected() && this._current_writer.getDepth() == 1) {
            close_local_symbol_table_copy();
        } else {
            this._current_writer.stepOut();
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        this._current_writer.writeBlob(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        this._current_writer.writeBool(z);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        this._current_writer.writeClob(bArr, i, i2);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        this._current_writer.writeDecimal(bigDecimal);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        this._current_writer.writeFloat(d);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        this._current_writer.writeInt(j);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        this._current_writer.writeInt(bigInteger);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        this._current_writer.writeNull(ionType);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        this._current_writer.writeString(str);
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    final void writeSymbol(int i) throws IOException {
        this._current_writer.writeSymbol(i);
    }

    @Override // com.amazon.ion.IonWriter
    public final void writeSymbol(String str) throws IOException {
        this._current_writer.writeSymbol(str);
    }

    final void writeIonVersionMarker(SymbolTable symbolTable) throws IOException {
        this._current_writer.writeIonVersionMarker(symbolTable);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        this._current_writer.writeTimestamp(timestamp);
    }
}
