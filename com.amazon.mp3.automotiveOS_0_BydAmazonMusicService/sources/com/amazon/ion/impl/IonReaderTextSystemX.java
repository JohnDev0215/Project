package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.impl.IonReaderTextRawTokensX;
import com.amazon.ion.impl.IonReaderTextRawX;
import com.amazon.ion.impl._Private_ScalarConversions;
import java.io.IOException;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
class IonReaderTextSystemX extends IonReaderTextRawX implements _Private_ReaderWriter {
    private static int UNSIGNED_BYTE_MAX_VALUE = 255;
    SymbolTable _system_symtab = _Private_Utils.systemSymtab(1);

    @Override // com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        return null;
    }

    protected IonReaderTextSystemX(UnifiedInputStreamX unifiedInputStreamX) {
        init_once();
        init(unifiedInputStreamX, IonType.DATAGRAM);
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        load_once();
        if (this._value_type != IonType.INT || this._v.isNull()) {
            return null;
        }
        return _Private_ScalarConversions.getIntegerSize(this._v.getAuthoritativeType());
    }

    private void load_once() {
        if (this._v.isEmpty()) {
            try {
                load_scalar_value();
            } catch (IOException e) {
                throw new IonException(e);
            }
        }
    }

    private final void load_or_cast_cached_value(int i) {
        load_once();
        if (i == 0 || this._v.hasValueOfType(i)) {
            return;
        }
        cast_cached_value(i);
    }

    enum Radix {
        DECIMAL { // from class: com.amazon.ion.impl.IonReaderTextSystemX.Radix.1
            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            boolean isInt(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_INT_IMAGE, Radix.MAX_INT_IMAGE);
            }

            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            boolean isLong(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_LONG_IMAGE, Radix.MAX_LONG_IMAGE);
            }
        },
        HEX { // from class: com.amazon.ion.impl.IonReaderTextSystemX.Radix.2
            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            boolean isInt(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_HEX_INT_IMAGE, Radix.MAX_HEX_INT_IMAGE);
            }

            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            boolean isLong(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_HEX_LONG_IMAGE, Radix.MAX_HEX_LONG_IMAGE);
            }
        },
        BINARY { // from class: com.amazon.ion.impl.IonReaderTextSystemX.Radix.3
            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            boolean isInt(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_BINARY_INT_IMAGE, Radix.MAX_BINARY_INT_IMAGE);
            }

            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            boolean isLong(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_BINARY_LONG_IMAGE, Radix.MAX_BINARY_LONG_IMAGE);
            }
        };

        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final char[] MAX_INT_IMAGE = Integer.toString(Integer.MAX_VALUE).toCharArray();
        private static final char[] MIN_INT_IMAGE = Integer.toString(Integer.MIN_VALUE).toCharArray();
        private static final char[] MAX_LONG_IMAGE = Long.toString(Long.MAX_VALUE).toCharArray();
        private static final char[] MIN_LONG_IMAGE = Long.toString(Long.MIN_VALUE).toCharArray();
        private static final char[] MAX_BINARY_INT_IMAGE = Integer.toBinaryString(Integer.MAX_VALUE).toCharArray();
        private static final char[] MIN_BINARY_INT_IMAGE = ("-" + Integer.toBinaryString(Integer.MIN_VALUE)).toCharArray();
        private static final char[] MAX_BINARY_LONG_IMAGE = Long.toBinaryString(Long.MAX_VALUE).toCharArray();
        private static final char[] MIN_BINARY_LONG_IMAGE = ("-" + Long.toBinaryString(Long.MIN_VALUE)).toCharArray();
        private static final char[] MAX_HEX_INT_IMAGE = Integer.toHexString(Integer.MAX_VALUE).toCharArray();
        private static final char[] MIN_HEX_INT_IMAGE = ("-" + Integer.toHexString(Integer.MIN_VALUE)).toCharArray();
        private static final char[] MAX_HEX_LONG_IMAGE = Long.toHexString(Long.MAX_VALUE).toCharArray();
        private static final char[] MIN_HEX_LONG_IMAGE = ("-" + Long.toHexString(Long.MIN_VALUE)).toCharArray();

        abstract boolean isInt(String str, int i);

        abstract boolean isLong(String str, int i);

        /* synthetic */ Radix(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean valueWithinBounds(String str, int i, char[] cArr, char[] cArr2) {
            if (!(str.charAt(0) == '-')) {
                cArr = cArr2;
            }
            int length = cArr.length;
            return i < length || (i == length && magnitudeLessThanOrEqualTo(str, i, cArr));
        }

        private static boolean magnitudeLessThanOrEqualTo(String str, int i, char[] cArr) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (str.charAt(i2) > cArr[i2]) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextSystemX$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BOOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private final void load_scalar_value() throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()]) {
            case 1:
                this._v.setValueToNull(this._null_type);
                this._v.setAuthoritativeType(1);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                StringBuilder sb = token_contents_load(this._scanner.getToken());
                int token = this._scanner.getToken();
                if (this._value_type == IonType.DECIMAL) {
                    for (int i = 0; i < sb.length(); i++) {
                        char cCharAt = sb.charAt(i);
                        if (cCharAt == 'd' || cCharAt == 'D') {
                            sb.setCharAt(i, 'e');
                        }
                    }
                } else if (token == 3 || token == 26) {
                    int i2 = sb.charAt(0) == '-' ? 1 : 0;
                    char c = token == 3 ? 'x' : 'b';
                    if (sb.length() <= (i2 != 0 ? 3 : 2) || Character.toLowerCase(sb.charAt(i2 + 1)) != c) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Invalid ");
                        sb2.append(c == 'x' ? "hexadecimal" : "binary");
                        sb2.append(" int value.");
                        parse_error(sb2.toString());
                    }
                    sb.deleteCharAt(i2);
                    sb.deleteCharAt(i2);
                }
                int length = sb.length();
                String string = sb.toString();
                clear_current_value_buffer();
                if (token == 1) {
                    int i3 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()];
                    if (i3 == 3) {
                        Radix radix = Radix.DECIMAL;
                        if (radix.isInt(string, length)) {
                            this._v.setValue(Integer.parseInt(string));
                        } else if (radix.isLong(string, length)) {
                            this._v.setValue(Long.parseLong(string));
                        } else {
                            this._v.setValue(new BigInteger(string));
                        }
                    } else if (i3 == 4) {
                        try {
                            this._v.setValue(Double.parseDouble(string));
                        } catch (NumberFormatException e) {
                            parse_error(e);
                            return;
                        }
                    } else if (i3 == 5) {
                        try {
                            this._v.setValue(Decimal.valueOf(string));
                        } catch (NumberFormatException e2) {
                            parse_error(e2);
                            return;
                        }
                    } else if (i3 == 6) {
                        this._v.setValue(Timestamp.valueOf(string));
                    } else {
                        parse_error("unexpected prefectched value type " + getType().toString() + " encountered handling an unquoted symbol");
                    }
                    break;
                } else if (token == 2) {
                    Radix radix2 = Radix.DECIMAL;
                    if (radix2.isInt(string, length)) {
                        this._v.setValue(Integer.parseInt(string));
                    } else if (radix2.isLong(string, length)) {
                        this._v.setValue(Long.parseLong(string));
                    } else {
                        this._v.setValue(new BigInteger(string));
                    }
                    break;
                } else if (token == 3) {
                    Radix radix3 = Radix.HEX;
                    if (radix3.isInt(string, length)) {
                        this._v.setValue(Integer.parseInt(string, 16));
                    } else if (radix3.isLong(string, length)) {
                        this._v.setValue(Long.parseLong(string, 16));
                    } else {
                        this._v.setValue(new BigInteger(string, 16));
                    }
                    break;
                } else if (token == 4) {
                    try {
                        this._v.setValue(Decimal.valueOf(string));
                    } catch (NumberFormatException e3) {
                        parse_error(e3);
                        return;
                    }
                    break;
                } else if (token == 5) {
                    try {
                        this._v.setValue(Double.parseDouble(string));
                    } catch (NumberFormatException e4) {
                        parse_error(e4);
                        return;
                    }
                    break;
                } else if (token == 26) {
                    Radix radix4 = Radix.BINARY;
                    if (radix4.isInt(string, length)) {
                        this._v.setValue(Integer.parseInt(string, 2));
                    } else if (radix4.isLong(string, length)) {
                        this._v.setValue(Long.parseLong(string, 2));
                    } else {
                        this._v.setValue(new BigInteger(string, 2));
                    }
                    break;
                } else {
                    switch (token) {
                        case 8:
                            Timestamp timestampValueOf = null;
                            try {
                                timestampValueOf = Timestamp.valueOf(string);
                            } catch (IllegalArgumentException e5) {
                                parse_error(e5);
                            }
                            this._v.setValue(timestampValueOf);
                            break;
                        case 9:
                            if (isNullValue()) {
                                this._v.setValueToNull(this._null_type);
                            } else {
                                int i4 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[getType().ordinal()];
                                if (i4 == 2) {
                                    int i5 = this._value_keyword;
                                    if (i5 == 1) {
                                        this._v.setValue(true);
                                    } else if (i5 == 2) {
                                        this._v.setValue(false);
                                    } else {
                                        parse_error("unexpected keyword " + string + " identified as a BOOL");
                                    }
                                } else if (i4 != 4) {
                                    if (i4 == 7) {
                                        this._v.setValue(string);
                                    } else {
                                        parse_error("unexpected prefectched value type " + getType().toString() + " encountered handling an unquoted symbol");
                                    }
                                } else if (this._value_keyword == 16) {
                                    this._v.setValue(Double.NaN);
                                } else {
                                    parse_error("unexpected keyword " + string + " identified as a FLOAT");
                                }
                            }
                            break;
                        case 10:
                        case 11:
                        case 12:
                            this._v.setValue(string);
                            break;
                        case 13:
                            this._v.setValue(string);
                            break;
                        default:
                            parse_error("scalar token " + IonTokenConstsX.getTokenName(this._scanner.getToken()) + "isn't a recognized type");
                            break;
                    }
                }
                break;
        }
    }

    private final void cast_cached_value(int i) {
        if (this._v.isNull()) {
            return;
        }
        if (!IonType.SYMBOL.equals(this._value_type)) {
            if (!this._v.can_convert(i)) {
                throw new _Private_ScalarConversions.CantConvertException("can't cast from " + _Private_ScalarConversions.getValueTypeName(this._v.getAuthoritativeType()) + " to " + _Private_ScalarConversions.getValueTypeName(i));
            }
            this._v.cast(this._v.get_conversion_fnid(i));
            return;
        }
        if (i == 3) {
            this._v.addValue(getSymbolTable().findSymbol(this._v.getString()));
        } else if (i == 8) {
            this._v.addValue(getSymbolTable().findKnownSymbol(this._v.getInt()));
        } else {
            throw new _Private_ScalarConversions.CantConvertException("can't cast symbol from " + _Private_ScalarConversions.getValueTypeName(this._v.getAuthoritativeType()) + " to " + _Private_ScalarConversions.getValueTypeName(i));
        }
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        int i = this._annotation_count;
        if (i == 0) {
            return SymbolToken.EMPTY_ARRAY;
        }
        resolveAnnotationSymbols(i);
        SymbolToken[] symbolTokenArr = new SymbolToken[i];
        System.arraycopy(this._annotations, 0, symbolTokenArr, 0, i);
        return symbolTokenArr;
    }

    private void resolveAnnotationSymbols(int i) {
        SymbolTable symbolTable = getSymbolTable();
        for (int i2 = 0; i2 < i; i2++) {
            SymbolToken symbolToken = this._annotations[i2];
            SymbolToken symbolTokenLocalize = _Private_Utils.localize(symbolTable, symbolToken);
            if (symbolTokenLocalize != symbolToken) {
                this._annotations[i2] = symbolTokenLocalize;
            }
        }
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        return this._v.isNull();
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        load_or_cast_cached_value(2);
        return this._v.getBoolean();
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        load_or_cast_cached_value(7);
        return this._v.getDouble();
    }

    private void checkIsIntApplicableType() {
        IonType ionType = this._value_type;
        if (ionType == IonType.INT || ionType == IonType.DECIMAL || ionType == IonType.FLOAT) {
            return;
        }
        throw new IllegalStateException("Unexpected value type: " + this._value_type);
    }

    @Override // com.amazon.ion.IonReader
    public int intValue() {
        checkIsIntApplicableType();
        load_or_cast_cached_value(3);
        return this._v.getInt();
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        checkIsIntApplicableType();
        load_or_cast_cached_value(4);
        return this._v.getLong();
    }

    @Override // com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        checkIsIntApplicableType();
        load_or_cast_cached_value(5);
        if (this._v.isNull()) {
            return null;
        }
        return this._v.getBigInteger();
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        load_or_cast_cached_value(6);
        if (this._v.isNull()) {
            return null;
        }
        return this._v.getDecimal();
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        load_or_cast_cached_value(10);
        if (this._v.isNull()) {
            return null;
        }
        return this._v.getTimestamp();
    }

    @Override // com.amazon.ion.IonReader
    public final String stringValue() {
        if (!IonType.isText(this._value_type)) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        if (this._v.isNull()) {
            return null;
        }
        load_or_cast_cached_value(8);
        String string = this._v.getString();
        if (string != null) {
            return string;
        }
        throw new UnknownSymbolException(this._v.getInt());
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        SymbolTable symbolTable = super.getSymbolTable();
        return symbolTable == null ? this._system_symtab : symbolTable;
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonTextReader, com.amazon.ion.IonReader
    public final int getFieldId() {
        String rawFieldName;
        int fieldId = super.getFieldId();
        return (fieldId != -1 || (rawFieldName = getRawFieldName()) == null) ? fieldId : getSymbolTable().findSymbol(rawFieldName);
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public final String getFieldName() {
        int fieldId;
        String rawFieldName = getRawFieldName();
        if (rawFieldName == null && (fieldId = getFieldId()) != -1 && (rawFieldName = getSymbolTable().findKnownSymbol(fieldId)) == null) {
            throw new UnknownSymbolException(fieldId);
        }
        return rawFieldName;
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        SymbolToken fieldNameSymbol = super.getFieldNameSymbol();
        return fieldNameSymbol != null ? _Private_Utils.localize(getSymbolTable(), fieldNameSymbol) : fieldNameSymbol;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        if (this._value_type != IonType.SYMBOL) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        if (this._v.isNull()) {
            return null;
        }
        load_or_cast_cached_value(8);
        if (!this._v.hasValueOfType(3)) {
            cast_cached_value(3);
        }
        return new SymbolTokenImpl(this._v.getString(), this._v.getInt());
    }

    private final void load_lob_length_overflow_error(long j) {
        throw new IonException("Size overflow: " + this._value_type.toString() + " size (" + Long.toString(j) + ") exceeds int ");
    }

    private final long load_lob_save_point() throws IOException {
        if (this._lob_loaded == IonReaderTextRawX.LOB_STATE.EMPTY) {
            this._scanner.save_point_start(this._current_value_save_point);
            this._scanner.skip_over_lob(this._lob_token, this._current_value_save_point);
            this._current_value_save_point_loaded = true;
            tokenValueIsFinished();
            this._lob_loaded = IonReaderTextRawX.LOB_STATE.READ;
        }
        return this._current_value_save_point.length();
    }

    private int load_lob_contents() throws IOException {
        if (this._lob_loaded == IonReaderTextRawX.LOB_STATE.EMPTY) {
            load_lob_save_point();
        }
        if (this._lob_loaded == IonReaderTextRawX.LOB_STATE.READ) {
            long length = this._current_value_save_point.length();
            if (length < 0 || length > 2147483647L) {
                load_lob_length_overflow_error(length);
            }
            int i = (int) length;
            this._lob_bytes = new byte[i];
            try {
                this._scanner.save_point_activate(this._current_value_save_point);
                this._lob_actual_len = readBytes(this._lob_bytes, 0, i);
                this._scanner.save_point_deactivate(this._current_value_save_point);
                this._lob_loaded = IonReaderTextRawX.LOB_STATE.FINISHED;
            } catch (IOException e) {
                throw new IonException(e);
            }
        }
        return this._lob_actual_len;
    }

    private void ensureLob(String str) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()];
        if (i == 9 || i == 10) {
            return;
        }
        throw new IllegalStateException(str + " is only valid if the reader is on a lob value, not a " + this._value_type + " value");
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        ensureLob("newBytes");
        try {
            int iLoad_lob_contents = load_lob_contents();
            byte[] bArr = new byte[iLoad_lob_contents];
            System.arraycopy(this._lob_bytes, 0, bArr, 0, iLoad_lob_contents);
            return bArr;
        } catch (IOException e) {
            throw new IonException(e);
        }
    }

    private int readBytes(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6 = this._lob_token;
        if (i6 == 12) {
            i3 = i;
            i4 = -1;
            while (true) {
                int i7 = i2 - 1;
                if (i2 <= 0) {
                    break;
                }
                i4 = this._scanner.read_double_quoted_char(true);
                if (i4 >= 0) {
                    bArr[i3] = (byte) i4;
                    i3++;
                } else if (i4 != -7 && i4 != -8 && i4 != -9) {
                    break;
                }
                i2 = i7;
            }
        } else if (i6 == 13) {
            i3 = i;
            i4 = -1;
            while (true) {
                int i8 = i2 - 1;
                if (i2 <= 0) {
                    break;
                }
                i4 = this._scanner.read_triple_quoted_char(true);
                if (i4 >= 0) {
                    i5 = i3 + 1;
                    bArr[i3] = (byte) i4;
                } else if (i4 != -7 && i4 != -8 && i4 != -9 && i4 != -3) {
                    if (i4 != -4 && i4 != -5 && i4 != -6) {
                        break;
                    }
                    i5 = i3 + 1;
                    bArr[i3] = 10;
                } else {
                    i2 = i8;
                }
                i3 = i5;
                i2 = i8;
            }
        } else {
            if (i6 != 24) {
                throw new IonReaderTextRawTokensX.IonReaderTextTokenException("invalid type [" + this._value_type.toString() + "] for lob handling");
            }
            i3 = i;
            i4 = -1;
            while (true) {
                int i9 = i2 - 1;
                if (i2 <= 0 || (i4 = this._scanner.read_base64_byte()) < 0) {
                    break;
                }
                bArr[i3] = (byte) i4;
                i3++;
                i2 = i9;
            }
        }
        if (i4 == -1) {
            this._scanner.tokenIsFinished();
        }
        int i10 = i3 - i;
        this._lob_value_position += (long) i10;
        return i10;
    }
}
