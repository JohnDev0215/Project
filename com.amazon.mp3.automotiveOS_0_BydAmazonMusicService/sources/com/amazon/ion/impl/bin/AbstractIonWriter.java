package com.amazon.ion.impl.bin;

import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.impl._Private_ByteTransferReader;
import com.amazon.ion.impl._Private_ByteTransferSink;
import com.amazon.ion.impl._Private_IonWriter;
import com.amazon.ion.impl._Private_SymtabExtendsCache;
import com.amazon.ion.impl._Private_Utils;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractIonWriter implements _Private_IonWriter, _Private_ByteTransferSink {
    private final _Private_SymtabExtendsCache symtabExtendsCache;

    enum WriteValueOptimization {
        NONE,
        COPY_OPTIMIZED
    }

    @Override // com.amazon.ion.impl._Private_IonWriter
    public abstract /* synthetic */ int getDepth();

    AbstractIonWriter(WriteValueOptimization writeValueOptimization) {
        this.symtabExtendsCache = writeValueOptimization == WriteValueOptimization.COPY_OPTIMIZED ? new _Private_SymtabExtendsCache() : null;
    }

    public final void writeValue(IonReader ionReader) throws IOException {
        _Private_ByteTransferReader _private_bytetransferreader;
        IonType type = ionReader.getType();
        if (isStreamCopyOptimized() && (_private_bytetransferreader = (_Private_ByteTransferReader) ionReader.asFacet(_Private_ByteTransferReader.class)) != null && (_Private_Utils.isNonSymbolScalar(type) || this.symtabExtendsCache.symtabsCompat(getSymbolTable(), ionReader.getSymbolTable()))) {
            _private_bytetransferreader.transferCurrentValue(this);
        } else {
            writeValueRecursive(ionReader);
        }
    }

    public final void writeValueRecursive(IonReader ionReader) throws IOException {
        IonType type = ionReader.getType();
        SymbolToken fieldNameSymbol = ionReader.getFieldNameSymbol();
        if (fieldNameSymbol != null && !isFieldNameSet() && isInStruct()) {
            setFieldNameSymbol(fieldNameSymbol);
        }
        SymbolToken[] typeAnnotationSymbols = ionReader.getTypeAnnotationSymbols();
        if (typeAnnotationSymbols.length > 0) {
            setTypeAnnotationSymbols(typeAnnotationSymbols);
        }
        if (ionReader.isNullValue()) {
            writeNull(type);
            return;
        }
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[type.ordinal()]) {
            case 1:
                writeBool(ionReader.booleanValue());
                return;
            case 2:
                int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IntegerSize[ionReader.getIntegerSize().ordinal()];
                if (i == 1) {
                    writeInt(ionReader.intValue());
                    return;
                } else if (i == 2) {
                    writeInt(ionReader.longValue());
                    return;
                } else {
                    if (i == 3) {
                        writeInt(ionReader.bigIntegerValue());
                        return;
                    }
                    throw new IllegalStateException();
                }
            case 3:
                writeFloat(ionReader.doubleValue());
                return;
            case 4:
                writeDecimal(ionReader.decimalValue());
                return;
            case 5:
                writeTimestamp(ionReader.timestampValue());
                return;
            case 6:
                writeSymbolToken(ionReader.symbolValue());
                return;
            case 7:
                writeString(ionReader.stringValue());
                return;
            case 8:
                writeClob(ionReader.newBytes());
                return;
            case 9:
                writeBlob(ionReader.newBytes());
                return;
            case 10:
            case 11:
            case 12:
                ionReader.stepIn();
                stepIn(type);
                while (ionReader.next() != null) {
                    writeValue(ionReader);
                }
                stepOut();
                ionReader.stepOut();
                return;
            default:
                throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.bin.AbstractIonWriter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IntegerSize;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[IntegerSize.values().length];
            $SwitchMap$com$amazon$ion$IntegerSize = iArr2;
            try {
                iArr2[IntegerSize.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$ion$IntegerSize[IntegerSize.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$ion$IntegerSize[IntegerSize.BIG_INTEGER.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    @Override // com.amazon.ion.IonWriter
    public final void writeValues(IonReader ionReader) throws IOException {
        if (ionReader.getType() != null) {
            writeValue(ionReader);
        }
        while (ionReader.next() != null) {
            writeValue(ionReader);
        }
    }

    public final boolean isStreamCopyOptimized() {
        return this.symtabExtendsCache != null;
    }
}
