package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.system.IonWriterBuilder;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
abstract class IonWriterSystem extends _Private_IonWriterBase {
    private int _annotation_count;
    private boolean _anything_written;
    final SymbolTable _default_system_symbol_table;
    private String _field_name;
    private IonType _field_name_type;
    private IonWriterBuilder.InitialIvmHandling _initial_ivm_handling;
    private final IonWriterBuilder.IvmMinimizing _ivm_minimizing;
    private boolean _previous_value_was_ivm;
    private SymbolTable _symbol_table;
    private int _field_name_sid = -1;
    private SymbolToken[] _annotations = new SymbolToken[4];

    abstract void writeIonVersionMarkerAsIs(SymbolTable symbolTable) throws IOException;

    abstract void writeSymbolAsIs(int i) throws IOException;

    abstract void writeSymbolAsIs(String str) throws IOException;

    IonWriterSystem(SymbolTable symbolTable, IonWriterBuilder.InitialIvmHandling initialIvmHandling, IonWriterBuilder.IvmMinimizing ivmMinimizing) {
        symbolTable.getClass();
        this._default_system_symbol_table = symbolTable;
        this._symbol_table = symbolTable;
        this._initial_ivm_handling = initialIvmHandling;
        this._ivm_minimizing = ivmMinimizing;
    }

    final SymbolTable getDefaultSystemSymtab() {
        return this._default_system_symbol_table;
    }

    @Override // com.amazon.ion.IonWriter
    public final SymbolTable getSymbolTable() {
        return this._symbol_table;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final void setSymbolTable(SymbolTable symbolTable) throws IOException {
        if (symbolTable == null || _Private_Utils.symtabIsSharedNotSystem(symbolTable)) {
            throw new IllegalArgumentException("symbol table must be local or system to be set, or reset");
        }
        if (getDepth() > 0) {
            throw new IllegalStateException("the symbol table cannot be set, or reset, while a container is open");
        }
        this._symbol_table = symbolTable;
    }

    boolean shouldWriteIvm() {
        IonWriterBuilder.InitialIvmHandling initialIvmHandling = this._initial_ivm_handling;
        if (initialIvmHandling == IonWriterBuilder.InitialIvmHandling.ENSURE) {
            return true;
        }
        if (initialIvmHandling == IonWriterBuilder.InitialIvmHandling.SUPPRESS) {
            return false;
        }
        IonWriterBuilder.IvmMinimizing ivmMinimizing = this._ivm_minimizing;
        if (ivmMinimizing == IonWriterBuilder.IvmMinimizing.ADJACENT) {
            return !this._previous_value_was_ivm;
        }
        if (ivmMinimizing == IonWriterBuilder.IvmMinimizing.DISTANT) {
            return !this._anything_written;
        }
        return true;
    }

    final void writeIonVersionMarker(SymbolTable symbolTable) throws IOException {
        if (getDepth() != 0) {
            throw new IllegalStateException("Ion Version Markers are only valid at the top level of a data stream");
        }
        if (!"$ion_1_0".equals(symbolTable.getIonVersionId())) {
            throw new UnsupportedOperationException("This library only supports Ion 1.0");
        }
        if (shouldWriteIvm()) {
            this._initial_ivm_handling = null;
            writeIonVersionMarkerAsIs(symbolTable);
            this._previous_value_was_ivm = true;
        }
        this._symbol_table = symbolTable;
    }

    public final void writeIonVersionMarker() throws IOException {
        writeIonVersionMarker(this._default_system_symbol_table);
    }

    void writeLocalSymtab(SymbolTable symbolTable) throws IOException {
        this._symbol_table = symbolTable;
    }

    SymbolTable inject_local_symbol_table() throws IOException {
        return LocalSymbolTable.DEFAULT_LST_FACTORY.newLocalSymtab(this._symbol_table, new SymbolTable[0]);
    }

    final int add_symbol(String str) throws IOException {
        if (this._symbol_table.isSystemTable()) {
            int iFindSymbol = this._symbol_table.findSymbol(str);
            if (iFindSymbol != -1) {
                return iFindSymbol;
            }
            this._symbol_table = inject_local_symbol_table();
        }
        return this._symbol_table.intern(str).getSid();
    }

    void startValue() throws IOException {
        if (this._initial_ivm_handling == IonWriterBuilder.InitialIvmHandling.ENSURE) {
            writeIonVersionMarker(this._default_system_symbol_table);
        }
    }

    void endValue() {
        this._initial_ivm_handling = null;
        this._previous_value_was_ivm = false;
        this._anything_written = true;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    final void writeSymbol(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("symbol IDs are >= 0.");
        }
        if (i == 2 && getDepth() == 0 && this._annotation_count == 0) {
            writeIonVersionMarker();
        } else {
            writeSymbolAsIs(i);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public final void writeSymbol(String str) throws IOException {
        if ("$ion_1_0".equals(str) && getDepth() == 0 && this._annotation_count == 0) {
            writeIonVersionMarker();
        } else {
            writeSymbolAsIs(str);
        }
    }

    public void finish() throws IOException {
        if (getDepth() != 0) {
            throw new IllegalStateException("IonWriter.finish() can only be called at top-level.");
        }
        flush();
        this._previous_value_was_ivm = false;
        this._initial_ivm_handling = IonWriterBuilder.InitialIvmHandling.ENSURE;
        this._symbol_table = this._default_system_symbol_table;
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonWriterSystem$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public final boolean isFieldNameSet() {
        IonType ionType = this._field_name_type;
        if (ionType == null) {
            return false;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        return i != 1 ? i == 2 && this._field_name_sid >= 0 : this._field_name != null;
    }

    final void clearFieldName() {
        this._field_name_type = null;
        this._field_name = null;
        this._field_name_sid = -1;
    }

    @Override // com.amazon.ion.IonWriter
    public final void setFieldNameSymbol(SymbolToken symbolToken) {
        if (!isInStruct()) {
            throw new IllegalStateException();
        }
        String text = symbolToken.getText();
        if (text != null) {
            this._field_name_type = IonType.STRING;
            this._field_name = text;
            this._field_name_sid = -1;
            return;
        }
        int sid = symbolToken.getSid();
        if (sid < 0) {
            throw new IllegalArgumentException();
        }
        validateSymbolId(sid);
        this._field_name_type = IonType.INT;
        this._field_name_sid = sid;
        this._field_name = null;
    }

    final int getFieldId() {
        IonType ionType = this._field_name_type;
        if (ionType == null) {
            throw new IllegalStateException("the field has not be set");
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return this._field_name_sid;
            }
            throw new IllegalStateException("the field has not be set");
        }
        try {
            return add_symbol(this._field_name);
        } catch (IOException e) {
            throw new IonException(e);
        }
    }

    final SymbolToken assumeFieldNameSymbol() {
        if (this._field_name_type == null) {
            throw new IllegalStateException("IonWriter.setFieldName() must be called before writing a value into a struct.");
        }
        return new SymbolTokenImpl(this._field_name, this._field_name_sid);
    }

    final void ensureAnnotationCapacity(int i) {
        SymbolToken[] symbolTokenArr = this._annotations;
        int length = symbolTokenArr == null ? 0 : symbolTokenArr.length;
        if (i < length) {
            return;
        }
        int length2 = symbolTokenArr == null ? 10 : symbolTokenArr.length * 2;
        if (i <= length2) {
            i = length2;
        }
        SymbolToken[] symbolTokenArr2 = new SymbolToken[i];
        if (length > 0) {
            System.arraycopy(symbolTokenArr, 0, symbolTokenArr2, 0, length);
        }
        this._annotations = symbolTokenArr2;
    }

    final boolean hasAnnotations() {
        return this._annotation_count != 0;
    }

    final void clearAnnotations() {
        this._annotation_count = 0;
    }

    final int findAnnotation(String str) {
        if (this._annotation_count <= 0) {
            return -1;
        }
        for (int i = 0; i < this._annotation_count; i++) {
            if (str.equals(this._annotations[i].getText())) {
                return i;
            }
        }
        return -1;
    }

    final SymbolToken[] getTypeAnnotationSymbols() {
        int i = this._annotation_count;
        if (i == 0) {
            return SymbolToken.EMPTY_ARRAY;
        }
        SymbolToken[] symbolTokenArr = new SymbolToken[i];
        System.arraycopy(this._annotations, 0, symbolTokenArr, 0, i);
        return symbolTokenArr;
    }

    @Override // com.amazon.ion.IonWriter
    public final void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr) {
        if (symbolTokenArr == null || symbolTokenArr.length == 0) {
            this._annotation_count = 0;
            return;
        }
        int length = symbolTokenArr.length;
        ensureAnnotationCapacity(length);
        SymbolTable symbolTable = getSymbolTable();
        for (int i = 0; i < length; i++) {
            SymbolToken symbolToken = symbolTokenArr[i];
            if (symbolToken.getText() == null) {
                validateSymbolId(symbolToken.getSid());
            }
            this._annotations[i] = _Private_Utils.localize(symbolTable, symbolToken);
        }
        this._annotation_count = length;
    }
}
