package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonType;
import com.amazon.ion.RawValueSpanProvider;
import com.amazon.ion.SeekableReader;
import com.amazon.ion.SpanProvider;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.impl.UnifiedInputStreamX;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class IonReaderBinaryUserX extends IonReaderBinarySystemX {
    IonCatalog _catalog;
    private final _Private_LocalSymbolTableFactory _lstFactory;
    private final int _physical_start_offset;
    private SymbolTable[] _symbol_table_stack;
    private int _symbol_table_top;

    public IonReaderBinaryUserX(IonCatalog ionCatalog, _Private_LocalSymbolTableFactory _private_localsymboltablefactory, UnifiedInputStreamX unifiedInputStreamX, int i) {
        super(unifiedInputStreamX);
        this._symbol_table_top = 0;
        this._symbol_table_stack = new SymbolTable[3];
        this._physical_start_offset = i;
        init_user(ionCatalog);
        this._lstFactory = _private_localsymboltablefactory;
    }

    final void init_user(IonCatalog ionCatalog) {
        this._symbols = SharedSymbolTable.getSystemSymbolTable(1);
        this._catalog = ionCatalog;
    }

    @Override // com.amazon.ion.impl.IonReaderBinaryRawX, com.amazon.ion.IonReader
    public IonType next() {
        if (!hasNext()) {
            return null;
        }
        this._has_next_needed = true;
        return this._value_type;
    }

    @Override // com.amazon.ion.impl.IonReaderBinaryRawX
    public boolean hasNext() {
        if (!this._eof && this._has_next_needed) {
            clear_system_value_stack();
            while (!this._eof && this._has_next_needed) {
                try {
                    has_next_helper_user();
                } catch (IOException e) {
                    error(e);
                }
            }
        }
        return !this._eof;
    }

    private final void has_next_helper_user() throws IOException {
        super.hasNext();
        if (getDepth() != 0 || this._value_is_null) {
            return;
        }
        int i = this._value_tid;
        if (i == 7) {
            if (load_annotations() == 0) {
                load_cached_value(3);
                if (this._v.getInt() == 2) {
                    SymbolTable systemSymbolTable = SharedSymbolTable.getSystemSymbolTable(1);
                    this._symbols = systemSymbolTable;
                    push_symbol_table(systemSymbolTable);
                    this._has_next_needed = true;
                    return;
                }
                return;
            }
            return;
        }
        if (i == 13 && load_annotations() > 0 && this._annotation_ids[0] == 3) {
            SymbolTable symbolTableNewLocalSymtab = this._lstFactory.newLocalSymtab(this._catalog, this, false);
            this._symbols = symbolTableNewLocalSymtab;
            push_symbol_table(symbolTableNewLocalSymtab);
            this._has_next_needed = true;
        }
    }

    private void validateSymbolToken(SymbolToken symbolToken) {
        if (symbolToken != null && symbolToken.getText() == null && symbolToken.getSid() > getSymbolTable().getMaxId()) {
            throw new UnknownSymbolException(symbolToken.getSid());
        }
    }

    @Override // com.amazon.ion.impl.IonReaderBinarySystemX, com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        SymbolToken[] typeAnnotationSymbols = super.getTypeAnnotationSymbols();
        for (SymbolToken symbolToken : typeAnnotationSymbols) {
            validateSymbolToken(symbolToken);
        }
        return typeAnnotationSymbols;
    }

    @Override // com.amazon.ion.impl.IonReaderBinarySystemX, com.amazon.ion.IonReader
    public final SymbolToken getFieldNameSymbol() {
        SymbolToken fieldNameSymbol = super.getFieldNameSymbol();
        validateSymbolToken(fieldNameSymbol);
        return fieldNameSymbol;
    }

    @Override // com.amazon.ion.impl.IonReaderBinarySystemX, com.amazon.ion.IonReader
    public final SymbolToken symbolValue() {
        SymbolToken symbolTokenSymbolValue = super.symbolValue();
        validateSymbolToken(symbolTokenSymbolValue);
        return symbolTokenSymbolValue;
    }

    private void clear_system_value_stack() {
        while (true) {
            int i = this._symbol_table_top;
            if (i <= 0) {
                return;
            }
            int i2 = i - 1;
            this._symbol_table_top = i2;
            this._symbol_table_stack[i2] = null;
        }
    }

    private void push_symbol_table(SymbolTable symbolTable) {
        int i = this._symbol_table_top;
        SymbolTable[] symbolTableArr = this._symbol_table_stack;
        if (i >= symbolTableArr.length) {
            SymbolTable[] symbolTableArr2 = new SymbolTable[symbolTableArr.length * 2];
            System.arraycopy(symbolTableArr, 0, symbolTableArr2, 0, symbolTableArr.length);
            this._symbol_table_stack = symbolTableArr2;
        }
        SymbolTable[] symbolTableArr3 = this._symbol_table_stack;
        int i2 = this._symbol_table_top;
        this._symbol_table_top = i2 + 1;
        symbolTableArr3[i2] = symbolTable;
    }

    @Override // com.amazon.ion.impl.IonReaderBinarySystemX, com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        int i = this._symbol_table_top;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        this._symbol_table_top = i2;
        SymbolTable[] symbolTableArr = this._symbol_table_stack;
        SymbolTable symbolTable = symbolTableArr[i2];
        symbolTableArr[i2] = null;
        return symbolTable;
    }

    @Override // com.amazon.ion.impl.IonReaderBinaryRawX, com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        if (cls == SpanProvider.class) {
            return cls.cast(new SpanProviderFacet());
        }
        UnifiedInputStreamX unifiedInputStreamX = this._input;
        if (unifiedInputStreamX instanceof UnifiedInputStreamX.FromByteArray) {
            if (cls == SeekableReader.class) {
                return cls.cast(new SeekableReaderFacet());
            }
            if (cls == RawValueSpanProvider.class) {
                return cls.cast(new RawValueSpanProviderFacet());
            }
        }
        if (cls == _Private_ByteTransferReader.class && (unifiedInputStreamX instanceof UnifiedInputStreamX.FromByteArray) && getTypeAnnotationSymbols().length == 0 && !isInStruct()) {
            return cls.cast(new ByteTransferReaderFacet());
        }
        return (T) super.asFacet(cls);
    }

    private class SpanProviderFacet implements SpanProvider {
        private SpanProviderFacet() {
        }
    }

    private class RawValueSpanProviderFacet implements RawValueSpanProvider {
        private RawValueSpanProviderFacet() {
        }
    }

    private class SeekableReaderFacet extends SpanProviderFacet implements SeekableReader {
        private SeekableReaderFacet() {
            super();
        }
    }

    private class ByteTransferReaderFacet implements _Private_ByteTransferReader {
        private ByteTransferReaderFacet() {
        }

        @Override // com.amazon.ion.impl._Private_ByteTransferReader
        public void transferCurrentValue(_Private_ByteTransferSink _private_bytetransfersink) throws IOException {
            IonReaderBinaryUserX ionReaderBinaryUserX = IonReaderBinaryUserX.this;
            UnifiedInputStreamX unifiedInputStreamX = ionReaderBinaryUserX._input;
            if (!(unifiedInputStreamX instanceof UnifiedInputStreamX.FromByteArray)) {
                throw new UnsupportedOperationException();
            }
            _private_bytetransfersink.writeBytes(unifiedInputStreamX._bytes, (int) ionReaderBinaryUserX._position_start, (int) ionReaderBinaryUserX._position_len);
        }
    }
}
