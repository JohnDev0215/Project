package com.amazon.ion.impl;

import androidx.core.view.InputDeviceCompat;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.impl._Private_ScalarConversions;
import com.amazon.music.skyfire.models.TetheredMessageKey;

/* JADX INFO: loaded from: classes3.dex */
final class IonTokenConstsX {
    static final int TN_MAX_NAME_LENGTH;
    static final int[] TypeNameBitIndex;
    private static final String[] escapeCharacterImage;
    private static final int[] escapeCharactersValues;
    public static final int[] hexValue;
    private static final boolean[] invalidTerminatingCharsForInf;
    public static final boolean[] isHexDigit;
    private static final boolean[] isValidExtendedSymbolCharacter;
    private static final boolean[] isValidStartSymbolCharacter;
    private static final boolean[] isValidSymbolCharacter;
    static final int[] typeNameBits;
    static final int[] typeNameKeyWordIds;
    static final String[] typeNameNames;
    public static final char[] BLOB_TERMINATOR = {'}', '}'};
    public static final char[] CLOB_DOUBLE_QUOTED_TERMINATOR = {'\'', '\'', '\''};
    public static final char[] CLOB_TRIPLE_QUOTED_TERMINATOR = {'\"'};
    public static final boolean[] isBase64Character = makeBase64Array();

    public enum EscapeType {
        ESCAPE_DESTINATION_NONE,
        ESCAPE_DESTINATION_STRING,
        ESCAPE_DESTINATION_SYMBOL,
        ESCAPE_DESTINATION_CLOB
    }

    public static final boolean is7bitValue(int i) {
        return (i & (-128)) == 0;
    }

    public static final boolean is8bitValue(int i) {
        return (i & InputDeviceCompat.SOURCE_ANY) == 0;
    }

    public static final boolean isBinaryDigit(int i) {
        return i == 48 || i == 49;
    }

    public static final boolean isDigit(int i) {
        return i >= 48 && i <= 57;
    }

    public static final boolean isWhitespace(int i) {
        return i == 32 || i == 9 || i == 10 || i == 13;
    }

    private static final boolean[] makeIsValidExtendedSymbolCharacterArray() {
        boolean[] zArr = new boolean[256];
        zArr[33] = true;
        zArr[35] = true;
        zArr[37] = true;
        zArr[38] = true;
        zArr[42] = true;
        zArr[43] = true;
        zArr[45] = true;
        zArr[46] = true;
        zArr[47] = true;
        zArr[59] = true;
        zArr[60] = true;
        zArr[61] = true;
        zArr[62] = true;
        zArr[63] = true;
        zArr[64] = true;
        zArr[94] = true;
        zArr[96] = true;
        zArr[124] = true;
        zArr[126] = true;
        return zArr;
    }

    public static final int typeNameLetterIdx(int i) {
        switch (i) {
            case 97:
                return 1;
            case 98:
                return 2;
            case 99:
                return 3;
            case 100:
                return 4;
            case 101:
                return 5;
            case 102:
                return 6;
            case 103:
                return 7;
            case 104:
            case 106:
            case 107:
            case 113:
            case 118:
            case 119:
            default:
                return -1;
            case 105:
                return 8;
            case 108:
                return 9;
            case 109:
                return 10;
            case 110:
                return 11;
            case 111:
                return 12;
            case 112:
                return 13;
            case 114:
                return 14;
            case 115:
                return 15;
            case 116:
                return 16;
            case 117:
                return 17;
            case 120:
                return 18;
            case 121:
                return 19;
        }
    }

    IonTokenConstsX() {
    }

    public static final String getTokenName(int i) {
        switch (i) {
            case -1:
                return "TOKEN_ERROR";
            case 0:
                return "TOKEN_EOF";
            case 1:
                return "TOKEN_UNKNOWN_NUMERIC";
            case 2:
                return "TOKEN_INT";
            case 3:
                return "TOKEN_HEX";
            case 4:
                return "TOKEN_DECIMAL";
            case 5:
                return "TOKEN_FLOAT";
            case 6:
                return "TOKEN_FLOAT_INF";
            case 7:
                return "TOKEN_FLOAT_MINUS_INF";
            case 8:
                return "TOKEN_TIMESTAMP";
            case 9:
                return "TOKEN_SYMBOL_IDENTIFIER";
            case 10:
                return "TOKEN_SYMBOL_QUOTED";
            case 11:
                return "TOKEN_SYMBOL_OPERATOR";
            case 12:
                return "TOKEN_STRING_DOUBLE_QUOTE";
            case 13:
                return "TOKEN_STRING_TRIPLE_QUOTE";
            case 14:
                return "TOKEN_DOT";
            case 15:
                return "TOKEN_COMMA";
            case 16:
                return "TOKEN_COLON";
            case 17:
                return "TOKEN_DOUBLE_COLON";
            case 18:
                return "TOKEN_OPEN_PAREN";
            case 19:
                return "TOKEN_CLOSE_PAREN";
            case 20:
                return "TOKEN_OPEN_BRACE";
            case 21:
                return "TOKEN_CLOSE_BRACE";
            case 22:
                return "TOKEN_OPEN_SQUARE";
            case 23:
                return "TOKEN_CLOSE_SQUARE";
            case 24:
                return "TOKEN_OPEN_DOUBLE_BRACE";
            case 25:
                return "TOKEN_CLOSE_DOUBLE_BRACE";
            default:
                return "<invalid token " + i + ">";
        }
    }

    public static final String describeToken(int i) {
        switch (i) {
            case 18:
                return "(";
            case 19:
                return ")";
            case 20:
                return "{";
            case 21:
                return "}";
            case 22:
                return "[";
            case 23:
                return "]";
            case 24:
                return "{{";
            case 25:
                return "}}";
            default:
                return getTokenName(i);
        }
    }

    public static final IonType ion_type_of_scalar(int i) {
        if (i == 2) {
            return IonType.INT;
        }
        if (i == 3) {
            return IonType.INT;
        }
        if (i == 4) {
            return IonType.DECIMAL;
        }
        if (i == 5) {
            return IonType.FLOAT;
        }
        if (i == 26) {
            return IonType.INT;
        }
        switch (i) {
        }
        return IonType.STRING;
    }

    static {
        int[] iArrMakeHexValueArray = makeHexValueArray();
        hexValue = iArrMakeHexValueArray;
        isHexDigit = makeHexDigitTestArray(iArrMakeHexValueArray);
        escapeCharactersValues = makeEscapeCharacterValuesArray();
        escapeCharacterImage = makeEscapeCharacterImageArray();
        invalidTerminatingCharsForInf = makeInvalidTerminatingCharsForInfArray();
        isValidExtendedSymbolCharacter = makeIsValidExtendedSymbolCharacterArray();
        isValidSymbolCharacter = makeIsValidSymbolCharacterArray();
        isValidStartSymbolCharacter = makeIsValidStartSymbolCharacterArray();
        TN_MAX_NAME_LENGTH = 10;
        typeNameBits = new int[]{1, 2, 4, 8, 16, 32, 48, 128, 256, 512, 1024, 2048, 4096};
        typeNameNames = new String[]{"blob", "bool", "clob", "decimal", "float", "int", "list", "null", "sexp", "string", "struct", "symbol", TetheredMessageKey.timestamp};
        typeNameKeyWordIds = new int[]{11, 4, 12, 7, 6, 5, 13, 3, 14, 10, 15, 9, 8};
        TypeNameBitIndex = makeTypeNameBitIndex();
    }

    private static boolean[] makeBase64Array() {
        boolean[] zArr = new boolean[256];
        for (int i = 48; i <= 57; i++) {
            zArr[i] = true;
        }
        for (int i2 = 97; i2 <= 122; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            zArr[i3] = true;
        }
        zArr[43] = true;
        zArr[47] = true;
        return zArr;
    }

    private static final int[] makeHexValueArray() {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = -1;
        }
        for (int i2 = 48; i2 <= 57; i2++) {
            iArr[i2] = i2 - 48;
        }
        for (int i3 = 97; i3 <= 102; i3++) {
            iArr[i3] = (i3 - 97) + 10;
        }
        for (int i4 = 65; i4 <= 70; i4++) {
            iArr[i4] = (i4 - 65) + 10;
        }
        return iArr;
    }

    private static final boolean[] makeHexDigitTestArray(int[] iArr) {
        boolean[] zArr = new boolean[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            zArr[i] = iArr[i] >= 0;
        }
        return zArr;
    }

    public static final boolean isHexDigit(int i) {
        return isHexDigit[i & 255] && is8bitValue(i);
    }

    public static final int hexDigitValue(int i) {
        if (!isHexDigit(i)) {
            throw new IonException(new IllegalArgumentException("character '" + ((char) i) + "' is not a hex digit"));
        }
        return hexValue[i];
    }

    private static final int[] makeEscapeCharacterValuesArray() {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = -11;
        }
        iArr[48] = 0;
        iArr[97] = 7;
        iArr[98] = 8;
        iArr[116] = 9;
        iArr[110] = 10;
        iArr[102] = 12;
        iArr[114] = 13;
        iArr[118] = 11;
        iArr[34] = 34;
        iArr[39] = 39;
        iArr[63] = 63;
        iArr[92] = 92;
        iArr[47] = 47;
        iArr[10] = -12;
        iArr[13] = -13;
        iArr[120] = -16;
        iArr[117] = -14;
        iArr[85] = -15;
        return iArr;
    }

    public static final String[] makeEscapeCharacterImageArray() {
        String[] strArr = new String[256];
        for (int i = 0; i < 256; i++) {
            strArr[i] = null;
        }
        strArr[48] = "\\0";
        strArr[97] = "\\a";
        strArr[98] = "\\b";
        strArr[116] = "\\t";
        strArr[110] = "\\n";
        strArr[102] = "\\f";
        strArr[114] = "\\r";
        strArr[118] = "\\v";
        strArr[34] = "\\\"";
        strArr[39] = "\\'";
        strArr[63] = "\\?";
        strArr[92] = "\\\\";
        strArr[47] = "\\/";
        return strArr;
    }

    public static final boolean isValidEscapeStart(int i) {
        return escapeCharactersValues[i & 255] != -11 && is8bitValue(i);
    }

    public static final int escapeReplacementCharacter(int i) {
        if (!isValidEscapeStart(i)) {
            throw new IllegalArgumentException("not a valid escape sequence character: " + i);
        }
        return escapeCharactersValues[i];
    }

    private static final boolean[] makeInvalidTerminatingCharsForInfArray() {
        boolean[] zArr = new boolean[256];
        for (int i = 97; i <= 122; i++) {
            zArr[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            zArr[i3] = true;
        }
        zArr[36] = true;
        zArr[95] = true;
        return zArr;
    }

    public static final boolean isValidTerminatingCharForInf(int i) {
        return (is8bitValue(i) && invalidTerminatingCharsForInf[i & 255]) ? false : true;
    }

    public static final boolean isValidExtendedSymbolCharacter(int i) {
        return isValidExtendedSymbolCharacter[i & 255] && is8bitValue(i);
    }

    private static final boolean[] makeIsValidSymbolCharacterArray() {
        boolean[] zArr = new boolean[256];
        for (int i = 97; i <= 122; i++) {
            zArr[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            zArr[i3] = true;
        }
        zArr[36] = true;
        zArr[95] = true;
        return zArr;
    }

    public static final boolean isValidSymbolCharacter(int i) {
        return isValidSymbolCharacter[i & 255] && is8bitValue(i);
    }

    private static final boolean[] makeIsValidStartSymbolCharacterArray() {
        boolean[] zArr = new boolean[256];
        for (int i = 97; i <= 122; i++) {
            zArr[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            zArr[i2] = true;
        }
        zArr[36] = true;
        zArr[95] = true;
        return zArr;
    }

    public static int decodeSid(CharSequence charSequence) {
        String string = charSequence.subSequence(1, charSequence.length()).toString();
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            throw new IonException(String.format("Unable to parse SID %s", string), e);
        }
    }

    public static int keyword(CharSequence charSequence, int i, int i2) {
        char cCharAt = charSequence.charAt(i);
        int i3 = i2 - i;
        if (cCharAt == '$') {
            if (i3 <= 1) {
                return -1;
            }
            for (int i4 = i + 1; i4 < i2; i4++) {
                if (!isDigit(charSequence.charAt(i4))) {
                    return -1;
                }
            }
            return 17;
        }
        if (cCharAt == 'f') {
            if (i3 == 5) {
                int i5 = i + 1;
                if (charSequence.charAt(i5) == 'a' && charSequence.charAt(i + 2) == 'l' && charSequence.charAt(i + 3) == 's' && charSequence.charAt(i + 4) == 'e') {
                    return 2;
                }
                if (charSequence.charAt(i5) == 'l' && charSequence.charAt(i + 2) == 'o' && charSequence.charAt(i + 3) == 'a' && charSequence.charAt(i + 4) == 't') {
                    return 6;
                }
            }
            return -1;
        }
        if (cCharAt == 'i') {
            return (i3 == 3 && charSequence.charAt(i + 1) == 'n' && charSequence.charAt(i + 2) == 't') ? 5 : -1;
        }
        if (cCharAt == 'l') {
            return (i3 == 4 && charSequence.charAt(i + 1) == 'i' && charSequence.charAt(i + 2) == 's' && charSequence.charAt(i + 3) == 't') ? 13 : -1;
        }
        if (cCharAt == 'n') {
            if (i3 == 4) {
                if (charSequence.charAt(i + 1) == 'u' && charSequence.charAt(i + 2) == 'l' && charSequence.charAt(i + 3) == 'l') {
                    return 3;
                }
            } else if (i3 == 3 && charSequence.charAt(i + 1) == 'a' && charSequence.charAt(i + 2) == 'n') {
                return 16;
            }
            return -1;
        }
        if (cCharAt == 's') {
            if (i3 == 4) {
                if (charSequence.charAt(i + 1) == 'e' && charSequence.charAt(i + 2) == 'x' && charSequence.charAt(i + 3) == 'p') {
                    return 14;
                }
            } else if (i3 == 6) {
                int i6 = i + 1;
                if (charSequence.charAt(i6) == 't' && charSequence.charAt(i + 2) == 'r') {
                    int i7 = i + 3;
                    if (charSequence.charAt(i7) == 'i' && charSequence.charAt(i + 4) == 'n' && charSequence.charAt(i + 5) == 'g') {
                        return 10;
                    }
                    return (charSequence.charAt(i7) == 'u' && charSequence.charAt(i + 4) == 'c' && charSequence.charAt(i + 5) == 't') ? 15 : -1;
                }
                if (charSequence.charAt(i6) == 'y' && charSequence.charAt(i + 2) == 'm' && charSequence.charAt(i + 3) == 'b' && charSequence.charAt(i + 4) == 'o' && charSequence.charAt(i + 5) == 'l') {
                    return 9;
                }
            }
            return -1;
        }
        if (cCharAt != 't') {
            switch (cCharAt) {
                case 'b':
                    if (i3 == 4) {
                        int i8 = i + 1;
                        if (charSequence.charAt(i8) == 'o' && charSequence.charAt(i + 2) == 'o' && charSequence.charAt(i + 3) == 'l') {
                            return 4;
                        }
                        if (charSequence.charAt(i8) == 'l' && charSequence.charAt(i + 2) == 'o' && charSequence.charAt(i + 3) == 'b') {
                            return 11;
                        }
                    }
                    return -1;
                case 'c':
                    return (i3 == 4 && charSequence.charAt(i + 1) == 'l' && charSequence.charAt(i + 2) == 'o' && charSequence.charAt(i + 3) == 'b') ? 12 : -1;
                case 'd':
                    return (i3 == 7 && charSequence.charAt(i + 1) == 'e' && charSequence.charAt(i + 2) == 'c' && charSequence.charAt(i + 3) == 'i' && charSequence.charAt(i + 4) == 'm' && charSequence.charAt(i + 5) == 'a' && charSequence.charAt(i + 6) == 'l') ? 7 : -1;
                default:
                    return -1;
            }
        }
        if (i3 == 4) {
            if (charSequence.charAt(i + 1) == 'r' && charSequence.charAt(i + 2) == 'u' && charSequence.charAt(i + 3) == 'e') {
                return 1;
            }
        } else if (i3 == 9 && charSequence.charAt(i + 1) == 'i' && charSequence.charAt(i + 2) == 'm' && charSequence.charAt(i + 3) == 'e' && charSequence.charAt(i + 4) == 's' && charSequence.charAt(i + 5) == 't' && charSequence.charAt(i + 6) == 'a' && charSequence.charAt(i + 7) == 'm' && charSequence.charAt(i + 8) == 'p') {
            return 8;
        }
        return -1;
    }

    static final int[] makeTypeNameBitIndex() {
        int[] iArr = new int[TN_MAX_NAME_LENGTH * 19];
        int i = 0;
        while (true) {
            String[] strArr = typeNameNames;
            if (i >= strArr.length) {
                return iArr;
            }
            String str = strArr[i];
            int i2 = typeNameBits[i];
            for (int i3 = 0; i3 < str.length(); i3++) {
                typename_set_bit(iArr, i3, typeNameLetterIdx(str.charAt(i3)), i2);
            }
            i++;
        }
    }

    private static final void typename_set_bit(int[] iArr, int i, int i2, int i3) {
        int i4 = ((i * 19) + i2) - 1;
        iArr[i4] = iArr[i4] | i3;
    }

    public static final int typeNamePossibilityMask(int i, int i2) {
        return TypeNameBitIndex[((i * 19) + i2) - 1];
    }

    public static final int typeNameKeyWordFromMask(int i, int i2) {
        if (i != 8191) {
            int i3 = 0;
            while (true) {
                int[] iArr = typeNameBits;
                if (i3 >= iArr.length) {
                    break;
                }
                if (iArr[i3] != i) {
                    i3++;
                } else if (typeNameNames[i3].length() == i2) {
                    return typeNameKeyWordIds[i3];
                }
            }
        }
        return -1;
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonTokenConstsX$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType;

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
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr2 = new int[EscapeType.values().length];
            $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType = iArr2;
            try {
                iArr2[EscapeType.ESCAPE_DESTINATION_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType[EscapeType.ESCAPE_DESTINATION_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType[EscapeType.ESCAPE_DESTINATION_SYMBOL.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType[EscapeType.ESCAPE_DESTINATION_CLOB.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public static final String getNullImage(IonType ionType) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) {
            case 1:
                return "null";
            case 2:
                return "null.bool";
            case 3:
                return "null.int";
            case 4:
                return "null.float";
            case 5:
                return "null.decimal";
            case 6:
                return "null.timestamp";
            case 7:
                return "null.symbol";
            case 8:
                return "null.string";
            case 9:
                return "null.blob";
            case 10:
                return "null.clob";
            case 11:
                return "null.sexp";
            case 12:
                return "null.list";
            case 13:
                return "null.struct";
            default:
                throw new IllegalStateException("unexpected type " + ionType);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ca  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0078 -> B:47:0x0089). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x007a -> B:47:0x0089). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x007c -> B:47:0x0089). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x007e -> B:47:0x0089). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x0084 -> B:47:0x0089). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x0088 -> B:47:0x0089). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.amazon.ion.IonType getNullType(java.lang.CharSequence r9) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonTokenConstsX.getNullType(java.lang.CharSequence):com.amazon.ion.IonType");
    }

    private static void invalid_null_image(CharSequence charSequence) {
        throw new _Private_ScalarConversions.CantConvertException("invalid image " + charSequence.toString());
    }
}
