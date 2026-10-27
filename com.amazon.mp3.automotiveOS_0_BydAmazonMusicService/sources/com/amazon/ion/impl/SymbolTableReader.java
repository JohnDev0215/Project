package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
final class SymbolTableReader implements IonReader {
    private SymbolTable _current_import;
    int _current_state = 0;
    int _flags;
    private Iterator<SymbolTable> _import_iterator;
    private SymbolTable[] _imported_tables;
    long _int_value;
    Iterator<String> _local_symbols;
    private final int _maxId;
    String _string_value;
    private final SymbolTable _symbol_table;
    private static final SymbolToken ION_SYMBOL_TABLE_SYM = _Private_Utils.newSymbolToken("$ion_symbol_table", 3);
    private static final SymbolToken ION_SHARED_SYMBOL_TABLE_SYM = _Private_Utils.newSymbolToken("$ion_shared_symbol_table", 9);

    private enum Op {
        NEXT,
        STEPOUT
    }

    private final int stateFollowingLocalSymbols() {
        return 20;
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return null;
    }

    private static final String get_state_name(int i) {
        switch (i) {
            case 0:
                return "S_BOF";
            case 1:
                return "S_STRUCT";
            case 2:
                return "S_IN_STRUCT";
            case 3:
                return "S_NAME";
            case 4:
                return "S_VERSION";
            case 5:
                return "S_MAX_ID";
            case 6:
                return "S_IMPORT_LIST";
            case 7:
                return "S_IN_IMPORTS";
            case 8:
                return "S_IMPORT_STRUCT";
            case 9:
                return "S_IN_IMPORT_STRUCT";
            case 10:
                return "S_IMPORT_NAME";
            case 11:
                return "S_IMPORT_VERSION";
            case 12:
                return "S_IMPORT_MAX_ID";
            case 13:
                return "S_IMPORT_STRUCT_CLOSE";
            case 14:
                return "S_IMPORT_LIST_CLOSE";
            case 15:
                return "S_AFTER_IMPORT_LIST";
            case 16:
                return "S_SYMBOL_LIST";
            case 17:
                return "S_IN_SYMBOLS";
            case 18:
                return "S_SYMBOL";
            case 19:
                return "S_SYMBOL_LIST_CLOSE";
            case 20:
                return "S_STRUCT_CLOSE";
            case 21:
                return "S_EOF";
            default:
                return "<Unrecognized state: " + i + ">";
        }
    }

    static final IonType stateType(int i) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return IonType.STRUCT;
            case 2:
                return null;
            case 3:
                return IonType.STRING;
            case 4:
                return IonType.INT;
            case 5:
                return IonType.INT;
            case 6:
                return IonType.LIST;
            case 7:
                return null;
            case 8:
                return IonType.STRUCT;
            case 9:
                return null;
            case 10:
                return IonType.STRING;
            case 11:
                return IonType.INT;
            case 12:
                return IonType.INT;
            case 13:
            case 14:
            case 15:
                return null;
            case 16:
                return IonType.LIST;
            case 17:
                return null;
            case 18:
                return IonType.STRING;
            default:
                throwUnrecognizedState(i);
            case 19:
            case 20:
            case 21:
                return null;
        }
    }

    static final int stateDepth(int i) {
        switch (i) {
            case 0:
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
            case 8:
                return 2;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return 3;
            case 14:
                return 2;
            case 15:
            case 16:
                return 1;
            case 17:
            case 18:
            case 19:
                return 2;
            case 20:
                return 1;
            case 21:
                return 0;
            default:
                throwUnrecognizedState(i);
                return -1;
        }
    }

    public SymbolTableReader(SymbolTable symbolTable) {
        int maxId;
        this._symbol_table = symbolTable;
        synchronized (symbolTable) {
            maxId = symbolTable.getMaxId();
            this._maxId = maxId;
            this._local_symbols = symbolTable.iterateDeclaredSymbolNames();
        }
        if (!symbolTable.isLocalTable()) {
            set_flag(1, true);
            set_flag(2, true);
        }
        SymbolTable[] importedTables = symbolTable.getImportedTables();
        this._imported_tables = importedTables;
        if (importedTables != null && importedTables.length != 0) {
            set_flag(8, true);
        }
        if (symbolTable.getImportedMaxId() < maxId) {
            set_flag(16, true);
        }
    }

    private final void set_flag(int i, boolean z) {
        if (z) {
            this._flags = i | this._flags;
        } else {
            this._flags = (~i) & this._flags;
        }
    }

    private final boolean test_flag(int i) {
        return (i & this._flags) != 0;
    }

    final boolean hasName() {
        return test_flag(1);
    }

    final boolean hasMaxId() {
        return test_flag(4);
    }

    final boolean hasImports() {
        return test_flag(8);
    }

    final boolean hasLocalSymbols() {
        return test_flag(16);
    }

    private final boolean has_next_helper() {
        int i = this._current_state;
        switch (i) {
            case 0:
                return true;
            case 2:
                if (stateFirstInStruct() != 20) {
                    return true;
                }
            case 1:
                return false;
            case 3:
                return true;
            case 4:
                return hasMaxId() || stateFollowingMaxId() != 20;
            case 5:
                return stateFollowingMaxId() != 20;
            case 6:
                return hasLocalSymbols();
            case 7:
            case 8:
                return this._import_iterator.hasNext();
            case 9:
            case 10:
            case 11:
                return true;
            case 15:
                if (hasLocalSymbols()) {
                    return true;
                }
            case 12:
            case 13:
            case 14:
                return false;
            case 17:
            case 18:
                if (this._local_symbols.hasNext()) {
                    return true;
                }
            case 16:
                return false;
            default:
                throwUnrecognizedState(i);
            case 19:
            case 20:
            case 21:
                return false;
        }
    }

    private static final void throwUnrecognizedState(int i) {
        throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + get_state_name(i));
    }

    private final int stateFirstInStruct() {
        if (hasName()) {
            return 3;
        }
        if (hasMaxId()) {
            return 5;
        }
        if (hasImports()) {
            return 6;
        }
        return hasLocalSymbols() ? 16 : 20;
    }

    private final int stateFollowingMaxId() {
        if (hasImports()) {
            return 6;
        }
        return hasLocalSymbols() ? 16 : 20;
    }

    private final int nextImport() {
        if (this._import_iterator.hasNext()) {
            this._current_import = this._import_iterator.next();
            return 8;
        }
        this._current_import = null;
        return 14;
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.SymbolTableReader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$SymbolTableReader$Op;

        static {
            int[] iArr = new int[Op.values().length];
            $SwitchMap$com$amazon$ion$impl$SymbolTableReader$Op = iArr;
            try {
                iArr[Op.NEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$SymbolTableReader$Op[Op.STEPOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private final int stateFollowingImportList(Op op) {
        if (!hasLocalSymbols()) {
            return 20;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$impl$SymbolTableReader$Op[op.ordinal()];
        if (i != 1) {
            return i != 2 ? -1 : 15;
        }
        return 16;
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        if (!has_next_helper()) {
            return null;
        }
        int i = this._current_state;
        int iStateFirstInStruct = 19;
        switch (i) {
            case 0:
                iStateFirstInStruct = 1;
                break;
            case 1:
            case 21:
                iStateFirstInStruct = 21;
                break;
            case 2:
                iStateFirstInStruct = stateFirstInStruct();
                loadStateData(iStateFirstInStruct);
                break;
            case 3:
                iStateFirstInStruct = 4;
                loadStateData(4);
                break;
            case 4:
                if (hasMaxId()) {
                    iStateFirstInStruct = 5;
                    loadStateData(5);
                } else {
                    iStateFirstInStruct = stateFollowingMaxId();
                }
                break;
            case 5:
                iStateFirstInStruct = stateFollowingMaxId();
                break;
            case 6:
                iStateFirstInStruct = stateFollowingImportList(Op.NEXT);
                break;
            case 7:
            case 8:
                iStateFirstInStruct = nextImport();
                break;
            case 9:
                iStateFirstInStruct = 10;
                loadStateData(10);
                break;
            case 10:
                iStateFirstInStruct = 11;
                loadStateData(11);
                break;
            case 11:
                iStateFirstInStruct = 12;
                loadStateData(12);
                break;
            case 12:
            case 13:
                iStateFirstInStruct = 13;
                break;
            case 14:
                iStateFirstInStruct = 14;
                break;
            case 15:
                iStateFirstInStruct = 16;
                break;
            case 16:
                iStateFirstInStruct = stateFollowingLocalSymbols();
                break;
            case 17:
            case 18:
                if (this._local_symbols.hasNext()) {
                    this._string_value = this._local_symbols.next();
                    iStateFirstInStruct = 18;
                }
                break;
            case 19:
                break;
            case 20:
                iStateFirstInStruct = 20;
                break;
            default:
                throwUnrecognizedState(i);
                iStateFirstInStruct = -1;
                break;
        }
        this._current_state = iStateFirstInStruct;
        return stateType(iStateFirstInStruct);
    }

    private final void loadStateData(int i) {
        if (i == 3) {
            this._string_value = this._symbol_table.getName();
            return;
        }
        if (i == 4) {
            this._int_value = this._symbol_table.getVersion();
            return;
        }
        if (i == 5) {
            this._int_value = this._maxId;
            return;
        }
        if (i == 6 || i == 16) {
            return;
        }
        switch (i) {
            case 10:
                this._string_value = this._current_import.getName();
                return;
            case 11:
                this._string_value = null;
                this._int_value = this._current_import.getVersion();
                return;
            case 12:
                this._int_value = this._current_import.getMaxId();
                return;
            default:
                throw new IonException("UnifiedSymbolTableReader in state " + get_state_name(i) + " has no state to load.");
        }
    }

    @Override // com.amazon.ion.IonReader
    public void stepIn() {
        int i;
        int i2 = this._current_state;
        if (i2 == 1) {
            i = 2;
        } else if (i2 == 6) {
            this._import_iterator = Arrays.asList(this._imported_tables).iterator();
            i = 7;
        } else if (i2 == 8) {
            i = 9;
        } else {
            if (i2 != 16) {
                throw new IllegalStateException("current value is not a container");
            }
            i = 17;
        }
        this._current_state = i;
    }

    @Override // com.amazon.ion.IonReader
    public void stepOut() {
        int iStateFollowingImportList;
        switch (this._current_state) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 15:
            case 16:
            case 20:
                iStateFollowingImportList = 21;
                break;
            case 7:
            case 8:
            case 14:
                this._current_import = null;
                this._import_iterator = null;
                iStateFollowingImportList = stateFollowingImportList(Op.STEPOUT);
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                iStateFollowingImportList = !this._import_iterator.hasNext() ? 14 : 8;
                break;
            case 17:
            case 18:
            case 19:
                this._string_value = null;
                this._local_symbols = null;
                iStateFollowingImportList = stateFollowingLocalSymbols();
                break;
            default:
                throw new IllegalStateException("current value is not in a container");
        }
        this._current_state = iStateFollowingImportList;
    }

    @Override // com.amazon.ion.IonReader
    public int getDepth() {
        return stateDepth(this._current_state);
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        return stateType(this._current_state);
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        SymbolToken symbolToken;
        if (this._current_state == 1) {
            if (this._symbol_table.isLocalTable() || this._symbol_table.isSystemTable()) {
                symbolToken = ION_SYMBOL_TABLE_SYM;
            } else {
                symbolToken = ION_SHARED_SYMBOL_TABLE_SYM;
            }
            return new SymbolToken[]{symbolToken};
        }
        return SymbolToken.EMPTY_ARRAY;
    }

    @Override // com.amazon.ion.IonReader
    public int getFieldId() {
        switch (this._current_state) {
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return -1;
            case 3:
            case 10:
                return 4;
            case 4:
            case 11:
                return 5;
            case 5:
            case 12:
                return 8;
            case 6:
                return 6;
            case 16:
                return 7;
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        switch (this._current_state) {
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return null;
            case 3:
            case 10:
                return "name";
            case 4:
            case 11:
                return "version";
            case 5:
            case 12:
                return "max_id";
            case 6:
                return "imports";
            case 16:
                return "symbols";
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        switch (this._current_state) {
            case 1:
            case 2:
            case 7:
            case 8:
            case 9:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return null;
            case 3:
            case 10:
                return new SymbolTokenImpl("name", 4);
            case 4:
            case 11:
                return new SymbolTokenImpl("version", 5);
            case 5:
            case 12:
                return new SymbolTokenImpl("max_id", 8);
            case 6:
                return new SymbolTokenImpl("imports", 6);
            case 16:
                return new SymbolTokenImpl("symbols", 7);
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        switch (this._current_state) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return false;
            default:
                throw new IonException("Internal error: UnifiedSymbolTableReader is in an unrecognized state: " + this._current_state);
        }
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        throw new IllegalStateException("only valid if the value is a boolean");
    }

    @Override // com.amazon.ion.IonReader
    public int intValue() {
        return (int) this._int_value;
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        return this._int_value;
    }

    @Override // com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        return new BigInteger(Long.toString(this._int_value));
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        throw new IllegalStateException("only valid if the value is a double");
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        throw new IllegalStateException("only valid if the value is a decimal");
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        throw new IllegalStateException("only valid if the value is a timestamp");
    }

    @Override // com.amazon.ion.IonReader
    public String stringValue() {
        return this._string_value;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        throw new IllegalStateException("newBytes() is only valid if the reader is on a lob value, not a " + stateType(this._current_state) + " value");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._current_state = 21;
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        if (stateType(this._current_state) != IonType.INT) {
            return null;
        }
        return IntegerSize.INT;
    }
}
