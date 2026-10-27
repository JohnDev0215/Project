package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonType;
import com.amazon.ion.NullValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.impl.IonReaderBinaryRawX;
import com.amazon.ion.impl._Private_ScalarConversions;
import java.io.IOException;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
class IonReaderBinarySystemX extends IonReaderBinaryRawX implements _Private_ReaderWriter {
    SymbolTable _symbols;
    static final BigInteger MIN_LONG_VALUE = BigInteger.valueOf(Long.MIN_VALUE);
    static final BigInteger MAX_LONG_VALUE = BigInteger.valueOf(Long.MAX_VALUE);

    @Override // com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        return null;
    }

    IonReaderBinarySystemX(UnifiedInputStreamX unifiedInputStreamX) {
        init_raw(unifiedInputStreamX);
        this._symbols = SharedSymbolTable.getSystemSymbolTable(1);
    }

    @Override // com.amazon.ion.impl.IonReaderBinaryRawX, com.amazon.ion.IonReader
    public final int getFieldId() {
        return this._value_field_id;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        load_annotations();
        int i = this._annotation_count;
        if (i == 0) {
            return SymbolToken.EMPTY_ARRAY;
        }
        SymbolTable symbolTable = getSymbolTable();
        SymbolToken[] symbolTokenArr = new SymbolToken[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this._annotation_ids[i2];
            symbolTokenArr[i2] = new SymbolTokenImpl(symbolTable.findKnownSymbol(i3), i3);
        }
        return symbolTokenArr;
    }

    private void load_once() {
        if (this._v.isEmpty()) {
            try {
                load_scalar_value();
            } catch (IOException e) {
                error(e);
            }
        }
    }

    protected final void prepare_value(int i) {
        load_once();
        if (i == 0 || this._v.hasValueOfType(i)) {
            return;
        }
        if (IonType.SYMBOL.equals(this._value_type)) {
            _Private_ScalarConversions.ValueVariant.isNumericType(i);
        }
        if (!this._v.can_convert(i)) {
            throw new IllegalStateException("can't cast from " + _Private_ScalarConversions.getValueTypeName(this._v.getAuthoritativeType()) + " to " + _Private_ScalarConversions.getValueTypeName(i));
        }
        this._v.cast(this._v.get_conversion_fnid(i));
    }

    protected final void load_cached_value(int i) throws IOException {
        if (this._v.isEmpty()) {
            load_scalar_value();
        }
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderBinarySystemX$1, reason: invalid class name */
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
        }
    }

    private final void load_scalar_value() throws IOException {
        int[] iArr = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType;
        switch (iArr[this._value_type.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                if (this._value_is_null) {
                    this._v.setValueToNull(this._value_type);
                    this._v.setAuthoritativeType(1);
                } else {
                    switch (iArr[this._value_type.ordinal()]) {
                        case 2:
                            this._v.setValue(this._value_is_true);
                            this._v.setAuthoritativeType(2);
                            break;
                        case 3:
                            boolean z = this._value_tid == 3;
                            int i = this._value_len;
                            if (i == 0) {
                                if (z) {
                                    throwIllegalNegativeZeroException();
                                }
                                this._v.setValue(0);
                                this._v.setAuthoritativeType(3);
                            } else if (i <= 8) {
                                long uLong = readULong(i);
                                if (uLong >= 0) {
                                    if (z) {
                                        if (uLong == 0) {
                                            throwIllegalNegativeZeroException();
                                        }
                                        uLong = -uLong;
                                    }
                                    if (uLong < -2147483648L || uLong > 2147483647L) {
                                        this._v.setValue(uLong);
                                        this._v.setAuthoritativeType(4);
                                    } else {
                                        this._v.setValue((int) uLong);
                                        this._v.setAuthoritativeType(3);
                                    }
                                } else {
                                    BigInteger bigIntegerUnsignedLongToBigInteger = IonBinary.unsignedLongToBigInteger(z ? -1 : 1, uLong);
                                    this._v.setValue(bigIntegerUnsignedLongToBigInteger);
                                    if (bigIntegerUnsignedLongToBigInteger.compareTo(MIN_LONG_VALUE) < 0 || bigIntegerUnsignedLongToBigInteger.compareTo(MAX_LONG_VALUE) > 0) {
                                        this._v.setAuthoritativeType(5);
                                    } else {
                                        this._v.addValue(bigIntegerUnsignedLongToBigInteger.longValue());
                                        this._v.setAuthoritativeType(4);
                                    }
                                }
                            } else {
                                this._v.setValue(readBigInteger(i, z));
                                this._v.setAuthoritativeType(5);
                            }
                            break;
                        case 4:
                            int i2 = this._value_len;
                            this._v.setValue(i2 == 0 ? 0.0d : readFloat(i2));
                            this._v.setAuthoritativeType(7);
                            break;
                        case 5:
                            this._v.setValue(readDecimal(this._value_len));
                            this._v.setAuthoritativeType(6);
                            break;
                        case 6:
                            this._v.setValue(readTimestamp(this._value_len));
                            this._v.setAuthoritativeType(10);
                            break;
                        case 7:
                            long uLong2 = readULong(this._value_len);
                            if (uLong2 < 0 || uLong2 > 2147483647L) {
                                throwErrorAt("symbol id [" + uLong2 + "] out of range (1-2147483647)");
                            }
                            this._v.setValue((int) uLong2);
                            this._v.setAuthoritativeType(3);
                            break;
                        case 8:
                            this._v.setValue(readString(this._value_len));
                            this._v.setAuthoritativeType(8);
                            break;
                    }
                    this._state = IonReaderBinaryRawX.State.S_AFTER_VALUE;
                }
                break;
        }
    }

    @Override // com.amazon.ion.impl.IonReaderBinaryRawX, com.amazon.ion.IonReader
    public boolean isNullValue() {
        return this._value_is_null;
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        prepare_value(2);
        return this._v.getBoolean();
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        prepare_value(7);
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
        prepare_value(3);
        return this._v.getInt();
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        checkIsIntApplicableType();
        prepare_value(4);
        return this._v.getLong();
    }

    @Override // com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        checkIsIntApplicableType();
        if (this._value_is_null) {
            return null;
        }
        prepare_value(5);
        return this._v.getBigInteger();
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        if (this._value_is_null) {
            return null;
        }
        prepare_value(6);
        return this._v.getDecimal();
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        if (this._value_is_null) {
            return null;
        }
        prepare_value(10);
        return this._v.getTimestamp();
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        load_once();
        if (this._value_type != IonType.INT || this._v.isNull()) {
            return null;
        }
        return _Private_ScalarConversions.getIntegerSize(this._v.getAuthoritativeType());
    }

    @Override // com.amazon.ion.IonReader
    public String stringValue() {
        if (!IonType.isText(this._value_type)) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        if (this._value_is_null) {
            return null;
        }
        if (this._value_type == IonType.SYMBOL) {
            if (!this._v.hasValueOfType(8)) {
                int symbolId = getSymbolId();
                String strFindKnownSymbol = this._symbols.findKnownSymbol(symbolId);
                if (strFindKnownSymbol == null) {
                    throw new UnknownSymbolException(symbolId);
                }
                this._v.addValue(strFindKnownSymbol);
            }
        } else {
            prepare_value(8);
        }
        return this._v.getString();
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        if (this._value_type != IonType.SYMBOL) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        if (this._value_is_null) {
            return null;
        }
        int symbolId = getSymbolId();
        return new SymbolTokenImpl(this._symbols.findKnownSymbol(symbolId), symbolId);
    }

    int getSymbolId() {
        if (this._value_type != IonType.SYMBOL) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        if (this._value_is_null) {
            throw new NullValueException();
        }
        prepare_value(3);
        return this._v.getInt();
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        int i = this._value_field_id;
        if (i == -1) {
            return null;
        }
        String strFindKnownSymbol = this._symbols.findKnownSymbol(i);
        if (strFindKnownSymbol != null) {
            return strFindKnownSymbol;
        }
        throw new UnknownSymbolException(this._value_field_id);
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        int i = this._value_field_id;
        if (i == -1) {
            return null;
        }
        return new SymbolTokenImpl(this._symbols.findKnownSymbol(i), i);
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return this._symbols;
    }

    private void throwIllegalNegativeZeroException() {
        throw newErrorAt("negative zero is illegal in the binary format");
    }
}
