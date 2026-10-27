package com.amazon.ion.impl.lite;

import com.amazon.ion.IonType;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl._Private_LocalSymbolTableFactory;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.impl._Private_ValueFactory;

/* JADX INFO: loaded from: classes3.dex */
abstract class ValueFactoryLite implements _Private_ValueFactory {
    private ContainerlessContext _context;
    protected final _Private_LocalSymbolTableFactory _lstFactory = _Private_Utils.newLocalSymbolTableAsStructFactory(this);

    ValueFactoryLite() {
    }

    protected void set_system(IonSystemLite ionSystemLite) {
        this._context = ContainerlessContext.wrap(ionSystemLite);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBlobLite newBlob(byte[] bArr, int i, int i2) {
        IonBlobLite ionBlobLite = new IonBlobLite(this._context, bArr == null);
        ionBlobLite.setBytes(bArr, i, i2);
        return ionBlobLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBoolLite newBool(boolean z) {
        IonBoolLite ionBoolLite = new IonBoolLite(this._context, false);
        ionBoolLite.setValue(z);
        return ionBoolLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonClobLite newClob(byte[] bArr, int i, int i2) {
        IonClobLite ionClobLite = new IonClobLite(this._context, bArr == null);
        ionClobLite.setBytes(bArr, i, i2);
        return ionClobLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonListLite newEmptyList() {
        return new IonListLite(this._context, false);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexpLite newEmptySexp() {
        return new IonSexpLite(this._context, false);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStructLite newEmptyStruct() {
        return new IonStructLite(this._context, false);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloatLite newFloat(double d) {
        IonFloatLite ionFloatLite = new IonFloatLite(this._context, false);
        ionFloatLite.setValue(d);
        return ionFloatLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonIntLite newInt(int i) {
        IonIntLite ionIntLite = new IonIntLite(this._context, false);
        ionIntLite.setValue(i);
        return ionIntLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonIntLite newInt(long j) {
        IonIntLite ionIntLite = new IonIntLite(this._context, false);
        ionIntLite.setValue(j);
        return ionIntLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonIntLite newInt(Number number) {
        IonIntLite ionIntLite = new IonIntLite(this._context, number == null);
        if (number != null) {
            ionIntLite.setValue(number);
        }
        return ionIntLite;
    }

    public IonNullLite newNull() {
        return new IonNullLite(this._context);
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.lite.ValueFactoryLite$1, reason: invalid class name */
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

    @Override // com.amazon.ion.ValueFactory
    public IonValueLite newNull(IonType ionType) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) {
            case 1:
                return newNull();
            case 2:
                return newNullBool();
            case 3:
                return newNullInt();
            case 4:
                return newNullFloat();
            case 5:
                return newNullDecimal();
            case 6:
                return newNullTimestamp();
            case 7:
                return newNullSymbol();
            case 8:
                return newNullString();
            case 9:
                return newNullClob();
            case 10:
                return newNullBlob();
            case 11:
                return newNullList();
            case 12:
                return newNullSexp();
            case 13:
                return newNullStruct();
            default:
                throw new IllegalArgumentException();
        }
    }

    public IonBlobLite newNullBlob() {
        return new IonBlobLite(this._context, true);
    }

    public IonBoolLite newNullBool() {
        return new IonBoolLite(this._context, true);
    }

    public IonClobLite newNullClob() {
        return new IonClobLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimalLite newNullDecimal() {
        return new IonDecimalLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloatLite newNullFloat() {
        return new IonFloatLite(this._context, true);
    }

    public IonIntLite newNullInt() {
        return new IonIntLite(this._context, true);
    }

    public IonListLite newNullList() {
        return new IonListLite(this._context, true);
    }

    public IonSexpLite newNullSexp() {
        return new IonSexpLite(this._context, true);
    }

    public IonStringLite newNullString() {
        return new IonStringLite(this._context, true);
    }

    public IonStructLite newNullStruct() {
        return new IonStructLite(this._context, true);
    }

    public IonSymbolLite newNullSymbol() {
        return new IonSymbolLite(this._context, true);
    }

    public IonTimestampLite newNullTimestamp() {
        return new IonTimestampLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStringLite newString(String str) {
        IonStringLite ionStringLite = new IonStringLite(this._context, str == null);
        if (str != null) {
            ionStringLite.setValue(str);
        }
        return ionStringLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbolLite newSymbol(String str) {
        IonSymbolLite ionSymbolLite = new IonSymbolLite(this._context, str == null);
        if (str != null) {
            ionSymbolLite.setValue(str);
        }
        return ionSymbolLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbolLite newSymbol(SymbolToken symbolToken) {
        return new IonSymbolLite(this._context, symbolToken);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonTimestampLite newTimestamp(Timestamp timestamp) {
        IonTimestampLite ionTimestampLite = new IonTimestampLite(this._context, timestamp == null);
        if (timestamp != null) {
            ionTimestampLite.setValue(timestamp);
        }
        return ionTimestampLite;
    }

    @Override // com.amazon.ion.impl._Private_ValueFactory
    public _Private_LocalSymbolTableFactory getLstFactory() {
        return this._lstFactory;
    }
}
