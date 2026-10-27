package com.amazon.ion.util;

import com.amazon.ion.IonException;
import com.amazon.ion.IonLob;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolToken;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class Equivalence {
    private static final Configuration STRICT_CONFIGURATION = new Configuration(new Builder().withStrict(true));
    private static final Configuration NON_STRICT_CONFIGURATION = new Configuration(new Builder().withStrict(false));

    static final class Configuration {
        private final Double epsilon;
        private final boolean isStrict;

        Configuration(Builder builder) {
            this.isStrict = builder.isStrict;
            this.epsilon = builder.epsilon;
        }
    }

    public static final class Builder {
        private boolean isStrict = true;
        private Double epsilon = null;

        public Builder withStrict(boolean z) {
            this.isStrict = z;
            return this;
        }
    }

    private static int compareAnnotations(SymbolToken[] symbolTokenArr, SymbolToken[] symbolTokenArr2) {
        int length = symbolTokenArr.length;
        int length2 = length - symbolTokenArr2.length;
        if (length2 == 0) {
            for (int i = 0; length2 == 0 && i < length; i++) {
                length2 = compareSymbolTokens(symbolTokenArr[i], symbolTokenArr2[i]);
            }
        }
        return length2;
    }

    private static int compareSymbolTokens(SymbolToken symbolToken, SymbolToken symbolToken2) {
        int sid;
        int sid2;
        String text = symbolToken.getText();
        String text2 = symbolToken2.getText();
        if (text != null && text2 != null) {
            return text.compareTo(text2);
        }
        if (text != null) {
            return 1;
        }
        if (text2 == null && (sid = symbolToken.getSid()) >= (sid2 = symbolToken2.getSid())) {
            return sid > sid2 ? 1 : 0;
        }
        return -1;
    }

    private static final Map<Field, Field> convertToMultiSet(IonStruct ionStruct, Configuration configuration) {
        HashMap map = new HashMap();
        Iterator<IonValue> it = ionStruct.iterator();
        while (it.hasNext()) {
            Field field = new Field(it.next(), configuration);
            Field field2 = (Field) map.put(field, field);
            if (field2 != null) {
                field.occurrences = field2.occurrences;
            }
            Field.access$308(field);
        }
        return map;
    }

    private static int compareStructs(IonStruct ionStruct, IonStruct ionStruct2, Configuration configuration) {
        int size = ionStruct.size() - ionStruct2.size();
        if (size == 0) {
            Map<Field, Field> mapConvertToMultiSet = convertToMultiSet(ionStruct, configuration);
            Iterator<IonValue> it = ionStruct2.iterator();
            while (it.hasNext()) {
                Field field = mapConvertToMultiSet.get(new Field(it.next(), configuration));
                if (field == null || field.occurrences == 0) {
                    return -1;
                }
                Field.access$310(field);
            }
        }
        return size;
    }

    private static int compareSequences(IonSequence ionSequence, IonSequence ionSequence2, Configuration configuration) {
        int size = ionSequence.size() - ionSequence2.size();
        if (size == 0) {
            Iterator<IonValue> it = ionSequence.iterator();
            Iterator<IonValue> it2 = ionSequence2.iterator();
            while (it.hasNext() && (size = ionCompareToImpl(it.next(), it2.next(), configuration)) == 0) {
            }
        }
        return size;
    }

    private static int compareLobContents(IonLob ionLob, IonLob ionLob2) {
        int iByteSize = ionLob.byteSize() - ionLob2.byteSize();
        if (iByteSize == 0) {
            InputStream inputStreamNewInputStream = ionLob.newInputStream();
            inputStreamNewInputStream = ionLob2.newInputStream();
            while (iByteSize == 0) {
                try {
                    try {
                        try {
                            int i = inputStreamNewInputStream.read();
                            int i2 = inputStreamNewInputStream.read();
                            if (i == -1 || i2 == -1) {
                                if (i != -1) {
                                    iByteSize = 1;
                                }
                                if (i2 != -1) {
                                    iByteSize = -1;
                                }
                                inputStreamNewInputStream.close();
                                inputStreamNewInputStream.close();
                            } else {
                                iByteSize = i - i2;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (IOException e) {
                        throw new IonException(e);
                    }
                } finally {
                    inputStreamNewInputStream.close();
                }
            }
            inputStreamNewInputStream.close();
            inputStreamNewInputStream.close();
        }
        return iByteSize;
    }

    static class Field {
        private final Configuration configuration;
        private final String name;
        private int occurrences;
        private final IonValue value;

        static /* synthetic */ int access$308(Field field) {
            int i = field.occurrences;
            field.occurrences = i + 1;
            return i;
        }

        static /* synthetic */ int access$310(Field field) {
            int i = field.occurrences;
            field.occurrences = i - 1;
            return i;
        }

        Field(IonValue ionValue, Configuration configuration) {
            SymbolToken fieldNameSymbol = ionValue.getFieldNameSymbol();
            String text = fieldNameSymbol.getText();
            if (text == null) {
                text = " -- UNKNOWN SYMBOL TEXT -- $" + fieldNameSymbol.getSid();
            }
            this.name = text;
            this.value = ionValue;
            this.configuration = configuration;
            this.occurrences = 0;
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public boolean equals(Object obj) {
            Field field = (Field) obj;
            return this.name.equals(field.name) && Equivalence.ionEqualsImpl(this.value, field.value, this.configuration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean ionEqualsImpl(IonValue ionValue, IonValue ionValue2, Configuration configuration) {
        return ionCompareToImpl(ionValue, ionValue2, configuration) == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int ionCompareToImpl(com.amazon.ion.IonValue r10, com.amazon.ion.IonValue r11, com.amazon.ion.util.Equivalence.Configuration r12) {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.util.Equivalence.ionCompareToImpl(com.amazon.ion.IonValue, com.amazon.ion.IonValue, com.amazon.ion.util.Equivalence$Configuration):int");
    }

    /* JADX INFO: renamed from: com.amazon.ion.util.Equivalence$1, reason: invalid class name */
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
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DATAGRAM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    public static boolean ionEquals(IonValue ionValue, IonValue ionValue2) {
        return ionEqualsImpl(ionValue, ionValue2, STRICT_CONFIGURATION);
    }
}
