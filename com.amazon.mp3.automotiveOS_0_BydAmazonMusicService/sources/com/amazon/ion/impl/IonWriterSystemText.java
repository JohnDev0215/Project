package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.ion.util.IonTextUtils;
import com.amazon.ion.util._Private_FastAppendable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
class IonWriterSystemText extends IonWriterSystem {
    private boolean _closed;
    private boolean _following_long_string;
    boolean _in_struct;
    private boolean _is_writing_ivm;
    private final int _long_string_threshold;
    private final _Private_IonTextWriterBuilder _options;
    private final _Private_IonTextAppender _output;
    boolean _pending_separator;
    CharSequence _separator_character;
    int[] _stack_parent_type;
    boolean[] _stack_pending_comma;
    int _top;

    protected IonWriterSystemText(SymbolTable symbolTable, _Private_IonTextWriterBuilder _private_iontextwriterbuilder, _Private_FastAppendable _private_fastappendable) {
        super(symbolTable, _private_iontextwriterbuilder.getInitialIvmHandling(), _private_iontextwriterbuilder.getIvmMinimizing());
        this._stack_parent_type = new int[10];
        this._stack_pending_comma = new boolean[10];
        this._output = _Private_IonTextAppender.forFastAppendable(_private_fastappendable, _private_iontextwriterbuilder.getCharset());
        this._options = _private_iontextwriterbuilder;
        this._separator_character = _private_iontextwriterbuilder.topLevelSeparator();
        int longStringThreshold = _private_iontextwriterbuilder.getLongStringThreshold();
        this._long_string_threshold = longStringThreshold < 1 ? Integer.MAX_VALUE : longStringThreshold;
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase
    public int getDepth() {
        return this._top;
    }

    @Override // com.amazon.ion.IonWriter
    public boolean isInStruct() {
        return this._in_struct;
    }

    protected IonType getContainer() {
        int i = this._top;
        if (i < 1) {
            return IonType.DATAGRAM;
        }
        int i2 = this._stack_parent_type[i - 1];
        if (i2 == 16) {
            return IonType.DATAGRAM;
        }
        switch (i2) {
            case 11:
                return IonType.LIST;
            case 12:
                return IonType.SEXP;
            case 13:
                return IonType.STRUCT;
            default:
                throw new IonException("unexpected container in parent stack: " + this._stack_parent_type[this._top - 1]);
        }
    }

    void push(int i) {
        if (this._top + 1 == this._stack_parent_type.length) {
            growStack();
        }
        int[] iArr = this._stack_parent_type;
        int i2 = this._top;
        iArr[i2] = i;
        this._stack_pending_comma[i2] = this._pending_separator;
        switch (i) {
            case 11:
            case 13:
                this._separator_character = ",";
                break;
            case 12:
                this._separator_character = " ";
                break;
            default:
                this._separator_character = this._options.lineSeparator();
                break;
        }
        this._top++;
    }

    void growStack() {
        int[] iArr = this._stack_parent_type;
        int length = iArr.length;
        int i = length * 2;
        int[] iArr2 = new int[i];
        boolean[] zArr = new boolean[i];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        System.arraycopy(this._stack_pending_comma, 0, zArr, 0, length);
        this._stack_parent_type = iArr2;
        this._stack_pending_comma = zArr;
    }

    int pop() {
        int i = this._top - 1;
        this._top = i;
        int[] iArr = this._stack_parent_type;
        int i2 = iArr[i];
        int i3 = i > 0 ? iArr[i - 1] : -1;
        if (i3 == -1) {
            this._in_struct = false;
            this._separator_character = this._options.topLevelSeparator();
        } else {
            switch (i3) {
                case 11:
                    this._in_struct = false;
                    this._separator_character = ",";
                    break;
                case 12:
                    this._in_struct = false;
                    this._separator_character = " ";
                    break;
                case 13:
                    this._in_struct = true;
                    this._separator_character = ",";
                    break;
                default:
                    this._separator_character = this._options.lineSeparator();
                    break;
            }
        }
        return i2;
    }

    int topType() {
        return this._stack_parent_type[this._top - 1];
    }

    boolean topPendingComma() {
        int i = this._top;
        if (i == 0) {
            return false;
        }
        return this._stack_pending_comma[i - 1];
    }

    private boolean containerIsSexp() {
        return this._top != 0 && topType() == 12;
    }

    void printLeadingWhiteSpace() throws IOException {
        for (int i = 0; i < this._top; i++) {
            this._output.appendAscii(' ');
            this._output.appendAscii(' ');
        }
    }

    void closeCollection(char c) throws IOException {
        if (this._options.isPrettyPrintOn()) {
            this._output.appendAscii(this._options.lineSeparator());
            printLeadingWhiteSpace();
        }
        this._output.appendAscii(c);
    }

    private void writeSidLiteral(int i) throws IOException {
        boolean z = this._options._symbol_as_string;
        if (z) {
            this._output.appendAscii('\"');
        }
        this._output.appendAscii(Typography.dollar);
        this._output.printInt(i);
        if (z) {
            this._output.appendAscii('\"');
        }
    }

    private void writeSymbolToken(String str) throws IOException {
        _Private_IonTextWriterBuilder _private_iontextwriterbuilder = this._options;
        if (_private_iontextwriterbuilder._symbol_as_string) {
            if (_private_iontextwriterbuilder._string_as_json) {
                this._output.printJsonString(str);
                return;
            } else {
                this._output.printString(str);
                return;
            }
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.symbolVariant(str).ordinal()];
        if (i == 1) {
            this._output.appendAscii(str);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
        } else if (containerIsSexp()) {
            this._output.appendAscii(str);
            return;
        }
        this._output.printQuotedSymbol(str);
    }

    void writeFieldNameToken(SymbolToken symbolToken) throws IOException {
        String text = symbolToken.getText();
        if (text == null) {
            writeSidLiteral(symbolToken.getSid());
        } else {
            writeSymbolToken(text);
        }
    }

    void writeAnnotations(SymbolToken[] symbolTokenArr) throws IOException {
        for (SymbolToken symbolToken : symbolTokenArr) {
            writeAnnotationToken(symbolToken);
            this._output.appendAscii("::");
        }
    }

    void writeAnnotationToken(SymbolToken symbolToken) throws IOException {
        String text = symbolToken.getText();
        if (text == null) {
            this._output.appendAscii(Typography.dollar);
            this._output.appendAscii(Integer.toString(symbolToken.getSid()));
        } else {
            this._output.printSymbol(text);
        }
    }

    boolean writeSeparator(boolean z) throws IOException {
        if (this._options.isPrettyPrintOn()) {
            if (this._pending_separator && !IonTextUtils.isAllWhitespace(this._separator_character)) {
                this._output.appendAscii(this._separator_character);
                z = false;
            }
            this._output.appendAscii(this._options.lineSeparator());
            printLeadingWhiteSpace();
            return z;
        }
        if (!this._pending_separator) {
            return z;
        }
        this._output.appendAscii(this._separator_character);
        if (IonTextUtils.isAllWhitespace(this._separator_character)) {
            return z;
        }
        return false;
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    void startValue() throws IOException {
        super.startValue();
        boolean zWriteSeparator = writeSeparator(this._following_long_string);
        boolean z = false;
        if (this._in_struct) {
            writeFieldNameToken(assumeFieldNameSymbol());
            this._output.appendAscii(':');
            clearFieldName();
            zWriteSeparator = false;
        }
        if (hasAnnotations() && !this._is_writing_ivm) {
            if (this._options._skip_annotations) {
                z = zWriteSeparator;
            } else {
                writeAnnotations(getTypeAnnotationSymbols());
            }
            clearAnnotations();
            zWriteSeparator = z;
        }
        this._following_long_string = zWriteSeparator;
    }

    void closeValue() throws IOException {
        super.endValue();
        this._pending_separator = true;
        this._following_long_string = false;
        if (getDepth() == 0) {
            try {
                flush();
            } catch (IOException e) {
                throw new IonException(e);
            }
        }
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    void writeIonVersionMarkerAsIs(SymbolTable symbolTable) throws IOException {
        this._is_writing_ivm = true;
        writeSymbolAsIs(symbolTable.getIonVersionId());
        this._is_writing_ivm = false;
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    void writeLocalSymtab(SymbolTable symbolTable) throws IOException {
        SymbolTable[] importedTables = symbolTable.getImportedTables();
        IonTextWriterBuilder.LstMinimizing lstMinimizing = this._options.getLstMinimizing();
        if (lstMinimizing == null) {
            symbolTable.writeTo(this);
        } else if (lstMinimizing == IonTextWriterBuilder.LstMinimizing.LOCALS && importedTables.length > 0) {
            SymbolTableReader symbolTableReader = new SymbolTableReader(symbolTable);
            symbolTableReader.next();
            setTypeAnnotationSymbols(symbolTableReader.getTypeAnnotationSymbols());
            stepIn(IonType.STRUCT);
            symbolTableReader.stepIn();
            while (symbolTableReader.next() != null) {
                if (!"symbols".equals(symbolTableReader.getFieldName())) {
                    writeValue(symbolTableReader);
                }
            }
            stepOut();
        } else {
            writeIonVersionMarker(symbolTable.getSystemSymbolTable());
        }
        super.writeLocalSymtab(symbolTable);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    @Override // com.amazon.ion.IonWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void stepIn(com.amazon.ion.IonType r4) throws java.io.IOException {
        /*
            r3 = this;
            r3.startValue()
            int[] r0 = com.amazon.ion.impl.IonWriterSystemText.AnonymousClass1.$SwitchMap$com$amazon$ion$IonType
            int r4 = r4.ordinal()
            r4 = r0[r4]
            r0 = 1
            r1 = 0
            if (r4 == r0) goto L22
            r2 = 2
            if (r4 == r2) goto L2f
            r2 = 3
            if (r4 != r2) goto L1c
            r4 = 13
            r3._in_struct = r0
            r0 = 123(0x7b, float:1.72E-43)
            goto L35
        L1c:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r4.<init>()
            throw r4
        L22:
            com.amazon.ion.impl._Private_IonTextWriterBuilder r4 = r3._options
            boolean r4 = r4._sexp_as_list
            if (r4 != 0) goto L2f
            r4 = 12
            r3._in_struct = r1
            r0 = 40
            goto L35
        L2f:
            r4 = 11
            r3._in_struct = r1
            r0 = 91
        L35:
            r3.push(r4)
            com.amazon.ion.impl._Private_IonTextAppender r4 = r3._output
            r4.appendAscii(r0)
            r3._pending_separator = r1
            r3._following_long_string = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonWriterSystemText.stepIn(com.amazon.ion.IonType):void");
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonWriterSystemText$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.SEXP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BOOL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr2 = new int[IonTextUtils.SymbolVariant.values().length];
            $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant = iArr2;
            try {
                iArr2[IonTextUtils.SymbolVariant.IDENTIFIER.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.SymbolVariant.OPERATOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.SymbolVariant.QUOTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        char c;
        if (this._top < 1) {
            throw new IllegalStateException("Cannot stepOut any further, already at top level.");
        }
        this._pending_separator = topPendingComma();
        switch (pop()) {
            case 11:
                c = ']';
                break;
            case 12:
                c = ')';
                break;
            case 13:
                c = '}';
                break;
            default:
                throw new IllegalStateException();
        }
        closeCollection(c);
        closeValue();
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeNull() throws IOException {
        startValue();
        this._output.appendAscii("null");
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        startValue();
        String str = "null";
        if (!this._options._untyped_nulls) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) {
                case 1:
                    str = "null.sexp";
                    break;
                case 2:
                    str = "null.list";
                    break;
                case 3:
                    str = "null.struct";
                    break;
                case 4:
                    break;
                case 5:
                    str = "null.bool";
                    break;
                case 6:
                    str = "null.int";
                    break;
                case 7:
                    str = "null.float";
                    break;
                case 8:
                    str = "null.decimal";
                    break;
                case 9:
                    str = "null.timestamp";
                    break;
                case 10:
                    str = "null.symbol";
                    break;
                case 11:
                    str = "null.string";
                    break;
                case 12:
                    str = "null.blob";
                    break;
                case 13:
                    str = "null.clob";
                    break;
                default:
                    throw new IllegalStateException("unexpected type " + ionType);
            }
        }
        this._output.appendAscii(str);
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        startValue();
        this._output.appendAscii(z ? "true" : "false");
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        startValue();
        this._output.printInt(j);
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        if (bigInteger == null) {
            writeNull(IonType.INT);
            return;
        }
        startValue();
        this._output.printInt(bigInteger);
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        startValue();
        this._output.printFloat(this._options, d);
        closeValue();
    }

    @Override // com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        if (bigDecimal == null) {
            writeNull(IonType.DECIMAL);
            return;
        }
        startValue();
        this._output.printDecimal(this._options, bigDecimal);
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        if (timestamp == null) {
            writeNull(IonType.TIMESTAMP);
            return;
        }
        startValue();
        _Private_IonTextWriterBuilder _private_iontextwriterbuilder = this._options;
        if (_private_iontextwriterbuilder._timestamp_as_millis) {
            this._output.appendAscii(Long.toString(timestamp.getMillis()));
        } else if (_private_iontextwriterbuilder._timestamp_as_string) {
            this._output.appendAscii('\"');
            this._output.appendAscii(timestamp.toString());
            this._output.appendAscii('\"');
        } else {
            this._output.appendAscii(timestamp.toString());
        }
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        startValue();
        if (str != null && !this._following_long_string && this._long_string_threshold < str.length()) {
            this._output.printLongString(str);
            closeValue();
            this._following_long_string = true;
        } else {
            if (this._options._string_as_json) {
                this._output.printJsonString(str);
            } else {
                this._output.printString(str);
            }
            closeValue();
        }
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    void writeSymbolAsIs(int i) throws IOException {
        String strFindKnownSymbol = getSymbolTable().findKnownSymbol(i);
        if (strFindKnownSymbol != null) {
            writeSymbolAsIs(strFindKnownSymbol);
            return;
        }
        startValue();
        writeSidLiteral(i);
        closeValue();
    }

    @Override // com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(String str) throws IOException {
        if (str == null) {
            writeNull(IonType.SYMBOL);
            return;
        }
        startValue();
        writeSymbolToken(str);
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            writeNull(IonType.BLOB);
            return;
        }
        startValue();
        this._output.printBlob(this._options, bArr, i, i2);
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            writeNull(IonType.CLOB);
            return;
        }
        startValue();
        this._output.printClob(this._options, bArr, i, i2);
        closeValue();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Flushable
    public void flush() throws IOException {
        if (this._closed) {
            return;
        }
        this._output.flush();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this._closed) {
            return;
        }
        try {
            if (getDepth() == 0) {
                finish();
            }
        } finally {
            this._closed = true;
            this._output.close();
        }
    }
}
