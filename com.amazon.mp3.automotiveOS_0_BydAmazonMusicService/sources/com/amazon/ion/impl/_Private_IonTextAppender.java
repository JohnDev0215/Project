package com.amazon.ion.impl;

import androidx.core.view.InputDeviceCompat;
import com.amazon.ion.Decimal;
import com.amazon.ion.impl.Base64Encoder;
import com.amazon.ion.util._Private_FastAppendable;
import com.fasterxml.jackson.core.JsonPointer;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import kotlin.text.Typography;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes3.dex */
public final class _Private_IonTextAppender implements Closeable, Flushable {
    static final String[] JSON_ESCAPE_CODES;
    static final String[] LONG_STRING_ESCAPE_CODES;
    public static final boolean[] OPERATOR_CHAR_FLAGS;
    private static final String[] STRING_ESCAPE_CODES;
    static final String[] SYMBOL_ESCAPE_CODES;
    public static final String[] ZERO_PADDING;
    private final char[] _fixedIntBuffer = new char[_Private_IonConstants.MAX_LONG_TEXT_SIZE];
    private final boolean escapeNonAscii;
    private final _Private_FastAppendable myAppendable;
    private static final boolean[] IDENTIFIER_START_CHAR_FLAGS = new boolean[256];
    private static final boolean[] IDENTIFIER_FOLLOW_CHAR_FLAGS = new boolean[256];

    private static boolean is8bitValue(int i) {
        return (i & InputDeviceCompat.SOURCE_ANY) == 0;
    }

    private static boolean isDecimalDigit(int i) {
        return i >= 48 && i <= 57;
    }

    static {
        int i;
        int i2 = 97;
        while (true) {
            if (i2 > 122) {
                break;
            }
            IDENTIFIER_START_CHAR_FLAGS[i2] = true;
            IDENTIFIER_FOLLOW_CHAR_FLAGS[i2] = true;
            i2++;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            IDENTIFIER_START_CHAR_FLAGS[i3] = true;
            IDENTIFIER_FOLLOW_CHAR_FLAGS[i3] = true;
        }
        boolean[] zArr = IDENTIFIER_START_CHAR_FLAGS;
        zArr[95] = true;
        boolean[] zArr2 = IDENTIFIER_FOLLOW_CHAR_FLAGS;
        zArr2[95] = true;
        zArr[36] = true;
        zArr2[36] = true;
        for (int i4 = 48; i4 <= 57; i4++) {
            IDENTIFIER_FOLLOW_CHAR_FLAGS[i4] = true;
        }
        char[] cArr = {Typography.less, Typography.greater, '=', '+', '-', '*', Typography.amp, '^', '%', '~', JsonPointer.SEPARATOR, '?', '.', ';', '!', '|', '@', '`', '#'};
        OPERATOR_CHAR_FLAGS = new boolean[256];
        for (int i5 = 0; i5 < 19; i5++) {
            OPERATOR_CHAR_FLAGS[cArr[i5]] = true;
        }
        ZERO_PADDING = new String[]{"", "0", "00", "000", "0000", "00000", "000000", "0000000"};
        String[] strArr = new String[256];
        STRING_ESCAPE_CODES = strArr;
        strArr[0] = "\\0";
        strArr[7] = "\\a";
        strArr[8] = "\\b";
        strArr[9] = "\\t";
        strArr[10] = "\\n";
        strArr[11] = "\\v";
        strArr[12] = "\\f";
        strArr[13] = "\\r";
        strArr[92] = "\\\\";
        strArr[34] = "\\\"";
        for (i = 1; i < 32; i++) {
            String[] strArr2 = STRING_ESCAPE_CODES;
            if (strArr2[i] == null) {
                String hexString = Integer.toHexString(i);
                strArr2[i] = "\\x" + ZERO_PADDING[2 - hexString.length()] + hexString;
            }
        }
        for (int i6 = WorkQueueKt.MASK; i6 < 256; i6++) {
            STRING_ESCAPE_CODES[i6] = "\\x" + Integer.toHexString(i6);
        }
        LONG_STRING_ESCAPE_CODES = new String[256];
        for (int i7 = 0; i7 < 256; i7++) {
            LONG_STRING_ESCAPE_CODES[i7] = STRING_ESCAPE_CODES[i7];
        }
        String[] strArr3 = LONG_STRING_ESCAPE_CODES;
        strArr3[10] = null;
        strArr3[39] = "\\'";
        strArr3[34] = null;
        SYMBOL_ESCAPE_CODES = new String[256];
        for (int i8 = 0; i8 < 256; i8++) {
            SYMBOL_ESCAPE_CODES[i8] = STRING_ESCAPE_CODES[i8];
        }
        String[] strArr4 = SYMBOL_ESCAPE_CODES;
        strArr4[39] = "\\'";
        strArr4[34] = null;
        String[] strArr5 = new String[256];
        JSON_ESCAPE_CODES = strArr5;
        strArr5[8] = "\\b";
        strArr5[9] = "\\t";
        strArr5[10] = "\\n";
        strArr5[12] = "\\f";
        strArr5[13] = "\\r";
        strArr5[92] = "\\\\";
        strArr5[34] = "\\\"";
        for (int i9 = 0; i9 < 32; i9++) {
            String[] strArr6 = JSON_ESCAPE_CODES;
            if (strArr6[i9] == null) {
                String hexString2 = Integer.toHexString(i9);
                strArr6[i9] = "\\u" + ZERO_PADDING[4 - hexString2.length()] + hexString2;
            }
        }
        for (int i10 = WorkQueueKt.MASK; i10 < 256; i10++) {
            JSON_ESCAPE_CODES[i10] = "\\u00" + Integer.toHexString(i10);
        }
    }

    public static boolean isIdentifierStart(int i) {
        return IDENTIFIER_START_CHAR_FLAGS[i & 255] && is8bitValue(i);
    }

    public static boolean isIdentifierPart(int i) {
        return IDENTIFIER_FOLLOW_CHAR_FLAGS[i & 255] && is8bitValue(i);
    }

    public static boolean isOperatorPart(int i) {
        return OPERATOR_CHAR_FLAGS[i & 255] && is8bitValue(i);
    }

    _Private_IonTextAppender(_Private_FastAppendable _private_fastappendable, boolean z) {
        this.myAppendable = _private_fastappendable;
        this.escapeNonAscii = z;
    }

    public static _Private_IonTextAppender forFastAppendable(_Private_FastAppendable _private_fastappendable, Charset charset) {
        return new _Private_IonTextAppender(_private_fastappendable, charset.equals(_Private_Utils.ASCII_CHARSET));
    }

    public static _Private_IonTextAppender forAppendable(Appendable appendable) {
        return new _Private_IonTextAppender(new AppendableFastAppendable(appendable), false);
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        _Private_FastAppendable _private_fastappendable = this.myAppendable;
        if (_private_fastappendable instanceof Flushable) {
            ((Flushable) _private_fastappendable).flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        _Private_FastAppendable _private_fastappendable = this.myAppendable;
        if (_private_fastappendable instanceof Closeable) {
            ((Closeable) _private_fastappendable).close();
        }
    }

    public void appendAscii(char c) throws IOException {
        this.myAppendable.appendAscii(c);
    }

    public void appendAscii(CharSequence charSequence) throws IOException {
        this.myAppendable.appendAscii(charSequence);
    }

    public void appendAscii(CharSequence charSequence, int i, int i2) throws IOException {
        this.myAppendable.appendAscii(charSequence, i, i2);
    }

    public void appendUtf16(char c) throws IOException {
        this.myAppendable.appendUtf16(c);
    }

    public void appendUtf16Surrogate(char c, char c2) throws IOException {
        this.myAppendable.appendUtf16Surrogate(c, c2);
    }

    public final void printString(CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendAscii("null.string");
            return;
        }
        appendAscii('\"');
        printCodePoints(charSequence, STRING_ESCAPE_CODES);
        appendAscii('\"');
    }

    public final void printLongString(CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendAscii("null.string");
            return;
        }
        appendAscii("'''");
        printCodePoints(charSequence, LONG_STRING_ESCAPE_CODES);
        appendAscii("'''");
    }

    public final void printJsonString(CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendAscii("null");
            return;
        }
        appendAscii('\"');
        printCodePoints(charSequence, JSON_ESCAPE_CODES);
        appendAscii('\"');
    }

    public static boolean isIdentifierKeyword(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        char cCharAt = charSequence.charAt(0);
        if (cCharAt == '$') {
            if (length == 1) {
                return false;
            }
            int i = 1;
            while (i < length) {
                int i2 = i + 1;
                if (!isDecimalDigit(charSequence.charAt(i))) {
                    return false;
                }
                i = i2;
            }
            return true;
        }
        int i3 = 2;
        if (cCharAt != 'f') {
            if (cCharAt == 'n') {
                if (length != 4) {
                    i3 = 1;
                } else if (charSequence.charAt(1) == 'u') {
                    if (charSequence.charAt(2) != 'l') {
                        i3 = 3;
                    } else if (charSequence.charAt(3) != 'l') {
                        i3 = 4;
                    }
                }
                if (length != 3) {
                    return false;
                }
                int i4 = i3 + 1;
                if (charSequence.charAt(i3) != 'a' || charSequence.charAt(i4) != 'n') {
                    return false;
                }
            } else if (cCharAt != 't' || length != 4 || charSequence.charAt(1) != 'r' || charSequence.charAt(2) != 'u' || charSequence.charAt(3) != 'e') {
                return false;
            }
        } else if (length != 5 || charSequence.charAt(1) != 'a' || charSequence.charAt(2) != 'l' || charSequence.charAt(3) != 's' || charSequence.charAt(4) != 'e') {
            return false;
        }
        return true;
    }

    public static boolean symbolNeedsQuoting(CharSequence charSequence, boolean z) {
        int length = charSequence.length();
        if (length != 0 && !isIdentifierKeyword(charSequence)) {
            char cCharAt = charSequence.charAt(0);
            if (!z && isOperatorPart(cCharAt)) {
                for (int i = 0; i < length; i++) {
                    if (!isOperatorPart(charSequence.charAt(i))) {
                        return true;
                    }
                }
                return false;
            }
            if (isIdentifierStart(cCharAt)) {
                for (int i2 = 0; i2 < length; i2++) {
                    char cCharAt2 = charSequence.charAt(i2);
                    if (cCharAt2 == '\'' || cCharAt2 < ' ' || cCharAt2 > '~' || !isIdentifierPart(cCharAt2)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public final void printSymbol(CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendAscii("null.symbol");
        } else {
            if (symbolNeedsQuoting(charSequence, true)) {
                appendAscii('\'');
                printCodePoints(charSequence, SYMBOL_ESCAPE_CODES);
                appendAscii('\'');
                return;
            }
            appendAscii(charSequence);
        }
    }

    public final void printQuotedSymbol(CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendAscii("null.symbol");
            return;
        }
        appendAscii('\'');
        printCodePoints(charSequence, SYMBOL_ESCAPE_CODES);
        appendAscii('\'');
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0024 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void printCodePoints(java.lang.CharSequence r8, java.lang.String[] r9) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl._Private_IonTextAppender.printCodePoints(java.lang.CharSequence, java.lang.String[]):void");
    }

    public void printInt(long j) throws IOException {
        char[] cArr = this._fixedIntBuffer;
        int length = cArr.length;
        if (j == 0) {
            length--;
            cArr[length] = '0';
        } else if (j < 0) {
            while (j != 0) {
                length--;
                this._fixedIntBuffer[length] = (char) (48 - (j % 10));
                j /= 10;
            }
            length--;
            this._fixedIntBuffer[length] = '-';
        } else {
            while (j != 0) {
                length--;
                this._fixedIntBuffer[length] = (char) ((j % 10) + 48);
                j /= 10;
            }
        }
        appendAscii(CharBuffer.wrap(this._fixedIntBuffer), length, this._fixedIntBuffer.length);
    }

    public void printInt(BigInteger bigInteger) throws IOException {
        if (bigInteger == null) {
            appendAscii("null.int");
        } else {
            appendAscii(bigInteger.toString());
        }
    }

    public void printDecimal(_Private_IonTextWriterBuilder _private_iontextwriterbuilder, BigDecimal bigDecimal) throws IOException {
        if (bigDecimal == null) {
            appendAscii("null.decimal");
            return;
        }
        BigInteger bigIntegerUnscaledValue = bigDecimal.unscaledValue();
        if (bigDecimal.signum() < 0) {
            appendAscii('-');
            bigIntegerUnscaledValue = bigIntegerUnscaledValue.negate();
        } else if ((bigDecimal instanceof Decimal) && ((Decimal) bigDecimal).isNegativeZero()) {
            appendAscii('-');
        }
        String string = bigIntegerUnscaledValue.toString();
        int length = string.length();
        int iScale = bigDecimal.scale();
        int i = -iScale;
        if (_private_iontextwriterbuilder._decimal_as_float) {
            appendAscii(string);
            appendAscii('e');
            appendAscii(Integer.toString(i));
            return;
        }
        if (i == 0) {
            appendAscii(string);
            appendAscii('.');
            return;
        }
        if (i >= 0) {
            appendAscii(string);
            appendAscii('d');
            appendAscii(Integer.toString(i));
            return;
        }
        int i2 = (length - 1) - iScale;
        if (i2 >= 0) {
            int i3 = length - iScale;
            appendAscii(string, 0, i3);
            appendAscii('.');
            appendAscii(string, i3, length);
            return;
        }
        if (i2 >= -6) {
            appendAscii("0.");
            appendAscii("00000", 0, iScale - length);
            appendAscii(string);
        } else {
            appendAscii(string);
            appendAscii("d-");
            appendAscii(Integer.toString(iScale));
        }
    }

    public void printFloat(_Private_IonTextWriterBuilder _private_iontextwriterbuilder, double d) throws IOException {
        if (d == 0.0d) {
            if (Double.compare(d, 0.0d) == 0) {
                appendAscii("0e0");
                return;
            } else {
                appendAscii("-0e0");
                return;
            }
        }
        if (Double.isNaN(d)) {
            if (_private_iontextwriterbuilder._float_nan_and_inf_as_null) {
                appendAscii("null");
                return;
            } else {
                appendAscii("nan");
                return;
            }
        }
        if (d == Double.POSITIVE_INFINITY) {
            if (_private_iontextwriterbuilder._float_nan_and_inf_as_null) {
                appendAscii("null");
                return;
            } else {
                appendAscii("+inf");
                return;
            }
        }
        if (d == Double.NEGATIVE_INFINITY) {
            if (_private_iontextwriterbuilder._float_nan_and_inf_as_null) {
                appendAscii("null");
                return;
            } else {
                appendAscii("-inf");
                return;
            }
        }
        String string = Double.toString(d);
        if (string.endsWith(".0")) {
            appendAscii(string, 0, string.length() - 2);
            appendAscii("e0");
        } else {
            appendAscii(string);
            if (string.indexOf(69) == -1) {
                appendAscii("e0");
            }
        }
    }

    public void printBlob(_Private_IonTextWriterBuilder _private_iontextwriterbuilder, byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            appendAscii("null.blob");
            return;
        }
        Base64Encoder.TextStream textStream = new Base64Encoder.TextStream(new ByteArrayInputStream(bArr, i, i2));
        int i3 = _private_iontextwriterbuilder.isPrettyPrintOn() ? 80 : 400;
        char[] cArr = new char[i3];
        CharBuffer charBufferWrap = CharBuffer.wrap(cArr);
        if (_private_iontextwriterbuilder._blob_as_string) {
            appendAscii('\"');
        } else {
            appendAscii("{{");
            if (_private_iontextwriterbuilder.isPrettyPrintOn()) {
                appendAscii(' ');
            }
        }
        while (true) {
            int i4 = textStream.read(cArr, 0, i3);
            if (i4 < 1) {
                break;
            } else {
                appendAscii(charBufferWrap, 0, i4);
            }
        }
        if (_private_iontextwriterbuilder._blob_as_string) {
            appendAscii('\"');
            return;
        }
        if (_private_iontextwriterbuilder.isPrettyPrintOn()) {
            appendAscii(' ');
        }
        appendAscii("}}");
    }

    private void printClobBytes(byte[] bArr, int i, int i2, String[] strArr) throws IOException {
        while (i < i2) {
            char c = (char) (bArr[i] & 255);
            String str = strArr[c];
            if (str != null) {
                appendAscii(str);
            } else {
                appendAscii(c);
            }
            i++;
        }
    }

    public void printClob(_Private_IonTextWriterBuilder _private_iontextwriterbuilder, byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            appendAscii("null.clob");
            return;
        }
        boolean z = _private_iontextwriterbuilder._clob_as_string && _private_iontextwriterbuilder._string_as_json;
        int longStringThreshold = _private_iontextwriterbuilder.getLongStringThreshold();
        boolean z2 = longStringThreshold > 0 && longStringThreshold < bArr.length;
        if (!_private_iontextwriterbuilder._clob_as_string) {
            appendAscii("{{");
            if (_private_iontextwriterbuilder.isPrettyPrintOn()) {
                appendAscii(' ');
            }
        }
        if (z) {
            appendAscii('\"');
            printClobBytes(bArr, i, i2 + i, JSON_ESCAPE_CODES);
            appendAscii('\"');
        } else if (z2) {
            appendAscii("'''");
            printClobBytes(bArr, i, i2 + i, LONG_STRING_ESCAPE_CODES);
            appendAscii("'''");
        } else {
            appendAscii('\"');
            printClobBytes(bArr, i, i2 + i, STRING_ESCAPE_CODES);
            appendAscii('\"');
        }
        if (_private_iontextwriterbuilder._clob_as_string) {
            return;
        }
        if (_private_iontextwriterbuilder.isPrettyPrintOn()) {
            appendAscii(' ');
        }
        appendAscii("}}");
    }
}
