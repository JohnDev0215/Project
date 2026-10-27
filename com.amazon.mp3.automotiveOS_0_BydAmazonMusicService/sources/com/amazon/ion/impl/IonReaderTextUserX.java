package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonType;
import com.amazon.ion.SeekableReader;
import com.amazon.ion.SpanProvider;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.UnsupportedIonVersionException;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
class IonReaderTextUserX extends IonReaderTextSystemX {
    private static final Pattern ION_VERSION_MARKER_REGEX = Pattern.compile("^\\$ion_[0-9]+_[0-9]+$");
    IonCatalog _catalog;
    private final _Private_LocalSymbolTableFactory _lstFactory;
    private final int _physical_start_offset;
    private SymbolTable[] _symbol_table_stack;
    private int _symbol_table_top;
    SymbolTable _symbols;

    protected IonReaderTextUserX(IonCatalog ionCatalog, _Private_LocalSymbolTableFactory _private_localsymboltablefactory, UnifiedInputStreamX unifiedInputStreamX, int i) {
        super(unifiedInputStreamX);
        this._symbol_table_top = 0;
        this._symbol_table_stack = new SymbolTable[3];
        this._symbols = this._system_symtab;
        this._physical_start_offset = i;
        this._catalog = ionCatalog;
        this._lstFactory = _private_localsymboltablefactory;
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX
    public boolean hasNext() {
        return has_next_user_value();
    }

    private final boolean has_next_user_value() {
        clear_system_value_stack();
        while (!this._has_next_called) {
            has_next_raw_value();
            if (this._value_type != null && !isNullValue() && IonType.DATAGRAM.equals(getContainerType())) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()];
                if (i == 1) {
                    if (this._annotation_count > 0 && "$ion_symbol_table".equals(this._annotations[0].getText())) {
                        SymbolTable symbolTableNewLocalSymtab = this._lstFactory.newLocalSymtab(this._catalog, this, true);
                        this._symbols = symbolTableNewLocalSymtab;
                        push_symbol_table(symbolTableNewLocalSymtab);
                        this._has_next_called = false;
                    }
                } else if (i == 2 && this._annotation_count == 0) {
                    String text = symbolValue().getText();
                    if (!isIonVersionMarker(text)) {
                        continue;
                    } else if ("$ion_1_0".equals(text)) {
                        if (this._value_keyword != 17) {
                            symbol_table_reset();
                            push_symbol_table(this._system_symtab);
                        }
                        this._has_next_called = false;
                    } else {
                        throw new UnsupportedIonVersionException(text);
                    }
                }
            }
        }
        return !this._eof;
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextUserX$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRUCT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static boolean isIonVersionMarker(String str) {
        return str != null && ION_VERSION_MARKER_REGEX.matcher(str).matches();
    }

    private final void symbol_table_reset() {
        next();
        this._symbols = this._system_symtab;
    }

    private void validateSymbolToken(SymbolToken symbolToken) {
        if (symbolToken != null && symbolToken.getText() == null && symbolToken.getSid() > getSymbolTable().getMaxId()) {
            throw new UnknownSymbolException(symbolToken.getSid());
        }
    }

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        SymbolToken[] typeAnnotationSymbols = super.getTypeAnnotationSymbols();
        for (SymbolToken symbolToken : typeAnnotationSymbols) {
            validateSymbolToken(symbolToken);
        }
        return typeAnnotationSymbols;
    }

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public final SymbolToken getFieldNameSymbol() {
        SymbolToken fieldNameSymbol = super.getFieldNameSymbol();
        validateSymbolToken(fieldNameSymbol);
        return fieldNameSymbol;
    }

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.IonReader
    public final SymbolToken symbolValue() {
        SymbolToken symbolTokenSymbolValue = super.symbolValue();
        validateSymbolToken(symbolTokenSymbolValue);
        return symbolTokenSymbolValue;
    }

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return this._symbols;
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

    @Override // com.amazon.ion.impl.IonReaderTextSystemX, com.amazon.ion.impl._Private_ReaderWriter
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

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        AnonymousClass1 anonymousClass1 = null;
        if (cls == SpanProvider.class) {
            return cls.cast(new SpanProviderFacet(this, anonymousClass1));
        }
        if (cls == SeekableReader.class && this._scanner.isBufferedInput()) {
            return cls.cast(new SeekableReaderFacet(this, anonymousClass1));
        }
        return (T) super.asFacet(cls);
    }

    private class SpanProviderFacet implements SpanProvider {
        private SpanProviderFacet() {
        }

        /* synthetic */ SpanProviderFacet(IonReaderTextUserX ionReaderTextUserX, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private final class SeekableReaderFacet extends SpanProviderFacet implements SeekableReader {
        private SeekableReaderFacet() {
            super(IonReaderTextUserX.this, null);
        }

        /* synthetic */ SeekableReaderFacet(IonReaderTextUserX ionReaderTextUserX, AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
