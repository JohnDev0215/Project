package com.sun.xml.bind;

import com.android.multidex.ClassPathElement;
import com.blankj.utilcode.constant.TimeConstants;
import com.google.common.base.Ascii;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.WeakHashMap;
import javax.xml.bind.DatatypeConverterInterface;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.pool.TypePool;
import okio.Utf8;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class DatatypeConverterImpl implements DatatypeConverterInterface {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte PADDING = 127;

    @Deprecated
    public static final DatatypeConverterInterface theInstance = new DatatypeConverterImpl();
    private static final byte[] decodeMap = initDecodeMap();
    private static final char[] encodeMap = initEncodeMap();
    private static final Map<ClassLoader, DatatypeFactory> DF_CACHE = Collections.synchronizedMap(new WeakHashMap());

    @Deprecated
    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    public static String _printBoolean(boolean z) {
        return z ? "true" : "false";
    }

    @Deprecated
    private static int hexToBin(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        char c2 = 'A';
        if ('A' > c || c > 'F') {
            c2 = 'a';
            if ('a' > c || c > 'f') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    private static boolean isDigitOrPeriodOrSign(char c) {
        return ('0' <= c && c <= '9') || c == '+' || c == '-' || c == '.';
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String parseAnySimpleType(String str) {
        return str;
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String parseString(String str) {
        return str;
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printAnySimpleType(String str) {
        return str;
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printBoolean(boolean z) {
        return z ? "true" : "false";
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printString(String str) {
        return str;
    }

    protected DatatypeConverterImpl() {
    }

    public static BigInteger _parseInteger(CharSequence charSequence) {
        return new BigInteger(removeOptionalPlus(WhiteSpaceProcessor.trim(charSequence)).toString());
    }

    public static String _printInteger(BigInteger bigInteger) {
        return bigInteger.toString();
    }

    public static int _parseInt(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 1;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = charSequence.charAt(i3);
            if (!WhiteSpaceProcessor.isWhiteSpace(cCharAt)) {
                if ('0' <= cCharAt && cCharAt <= '9') {
                    i2 = (i2 * 10) + (cCharAt - '0');
                } else if (cCharAt == '-') {
                    i = -1;
                } else if (cCharAt != '+') {
                    throw new NumberFormatException("Not a number: " + ((Object) charSequence));
                }
            }
        }
        return i2 * i;
    }

    public static long _parseLong(CharSequence charSequence) {
        return Long.parseLong(removeOptionalPlus(WhiteSpaceProcessor.trim(charSequence)).toString());
    }

    public static short _parseShort(CharSequence charSequence) {
        return (short) _parseInt(charSequence);
    }

    public static String _printShort(short s) {
        return String.valueOf((int) s);
    }

    public static BigDecimal _parseDecimal(CharSequence charSequence) {
        CharSequence charSequenceTrim = WhiteSpaceProcessor.trim(charSequence);
        if (charSequenceTrim.length() <= 0) {
            return null;
        }
        return new BigDecimal(charSequenceTrim.toString());
    }

    public static float _parseFloat(CharSequence charSequence) {
        String string = WhiteSpaceProcessor.trim(charSequence).toString();
        if (string.equals("NaN")) {
            return Float.NaN;
        }
        if (string.equals("INF")) {
            return Float.POSITIVE_INFINITY;
        }
        if (string.equals("-INF")) {
            return Float.NEGATIVE_INFINITY;
        }
        if (string.length() == 0 || !isDigitOrPeriodOrSign(string.charAt(0)) || !isDigitOrPeriodOrSign(string.charAt(string.length() - 1))) {
            throw new NumberFormatException();
        }
        return Float.parseFloat(string);
    }

    public static String _printFloat(float f) {
        return Float.isNaN(f) ? "NaN" : f == Float.POSITIVE_INFINITY ? "INF" : f == Float.NEGATIVE_INFINITY ? "-INF" : String.valueOf(f);
    }

    public static double _parseDouble(CharSequence charSequence) {
        String string = WhiteSpaceProcessor.trim(charSequence).toString();
        if (string.equals("NaN")) {
            return Double.NaN;
        }
        if (string.equals("INF")) {
            return Double.POSITIVE_INFINITY;
        }
        if (string.equals("-INF")) {
            return Double.NEGATIVE_INFINITY;
        }
        if (string.length() == 0 || !isDigitOrPeriodOrSign(string.charAt(0)) || !isDigitOrPeriodOrSign(string.charAt(string.length() - 1))) {
            throw new NumberFormatException(string);
        }
        return Double.parseDouble(string);
    }

    public static Boolean _parseBoolean(CharSequence charSequence) {
        int i;
        char cCharAt;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (charSequence == null) {
            return null;
        }
        int length = charSequence.length();
        if (charSequence.length() <= 0) {
            return null;
        }
        boolean z = false;
        int i7 = 0;
        while (true) {
            i = i7 + 1;
            cCharAt = charSequence.charAt(i7);
            if (!WhiteSpaceProcessor.isWhiteSpace(cCharAt) || i >= length) {
                break;
            }
            i7 = i;
        }
        if (cCharAt != '0') {
            if (cCharAt == '1') {
                z = true;
            } else if (cCharAt == 'f') {
                int i8 = 0;
                while (true) {
                    i3 = i + 1;
                    i4 = i8 + 1;
                    if ("alse".charAt(i8) != charSequence.charAt(i) || i3 >= length || i4 >= 4) {
                        break;
                    }
                    i = i3;
                    i8 = i4;
                }
                if (i4 != 4) {
                    return false;
                }
                i = i3;
            } else if (cCharAt == 't') {
                int i9 = 0;
                while (true) {
                    i5 = i + 1;
                    i6 = i9 + 1;
                    if ("rue".charAt(i9) != charSequence.charAt(i) || i5 >= length || i6 >= 3) {
                        break;
                    }
                    i = i5;
                    i9 = i6;
                }
                if (i6 != 3) {
                    return false;
                }
                i = i5;
                z = true;
            }
        }
        if (i < length) {
            while (true) {
                i2 = i + 1;
                if (!WhiteSpaceProcessor.isWhiteSpace(charSequence.charAt(i)) || i2 >= length) {
                    break;
                }
                i = i2;
            }
            i = i2;
        }
        if (i == length) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    public static byte _parseByte(CharSequence charSequence) {
        return (byte) _parseInt(charSequence);
    }

    public static String _printByte(byte b) {
        return String.valueOf((int) b);
    }

    public static QName _parseQName(CharSequence charSequence, NamespaceContext namespaceContext) {
        String string;
        String namespaceURI;
        String str;
        int length = charSequence.length();
        int i = 0;
        while (i < length && WhiteSpaceProcessor.isWhiteSpace(charSequence.charAt(i))) {
            i++;
        }
        while (length > i && WhiteSpaceProcessor.isWhiteSpace(charSequence.charAt(length - 1))) {
            length--;
        }
        if (length == i) {
            throw new IllegalArgumentException("input is empty");
        }
        int i2 = i + 1;
        while (i2 < length && charSequence.charAt(i2) != ':') {
            i2++;
        }
        if (i2 == length) {
            str = "";
            namespaceURI = namespaceContext.getNamespaceURI("");
            string = charSequence.subSequence(i, length).toString();
        } else {
            String string2 = charSequence.subSequence(i, i2).toString();
            string = charSequence.subSequence(i2 + 1, length).toString();
            namespaceURI = namespaceContext.getNamespaceURI(string2);
            if (namespaceURI == null || namespaceURI.length() == 0) {
                throw new IllegalArgumentException("prefix " + string2 + " is not bound to a namespace");
            }
            str = string2;
        }
        return new QName(namespaceURI, string, str);
    }

    public static GregorianCalendar _parseDateTime(CharSequence charSequence) {
        return getDatatypeFactory().newXMLGregorianCalendar(WhiteSpaceProcessor.trim(charSequence).toString()).toGregorianCalendar();
    }

    public static String _printDateTime(Calendar calendar) {
        return CalendarFormatter.doFormat("%Y-%M-%DT%h:%m:%s%z", calendar);
    }

    public static String _printDate(Calendar calendar) {
        return CalendarFormatter.doFormat("%Y-%M-%D%z", calendar);
    }

    public static String _printInt(int i) {
        return String.valueOf(i);
    }

    public static String _printLong(long j) {
        return String.valueOf(j);
    }

    public static String _printDecimal(BigDecimal bigDecimal) {
        return bigDecimal.toPlainString();
    }

    public static String _printDouble(double d) {
        return Double.isNaN(d) ? "NaN" : d == Double.POSITIVE_INFINITY ? "INF" : d == Double.NEGATIVE_INFINITY ? "-INF" : String.valueOf(d);
    }

    public static String _printQName(QName qName, NamespaceContext namespaceContext) {
        String prefix = namespaceContext.getPrefix(qName.getNamespaceURI());
        String localPart = qName.getLocalPart();
        return (prefix == null || prefix.length() == 0) ? localPart : prefix + ':' + localPart;
    }

    private static byte[] initDecodeMap() {
        byte[] bArr = new byte[128];
        for (int i = 0; i < 128; i++) {
            bArr[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            bArr[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            bArr[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            bArr[i4] = (byte) ((i4 - 48) + 52);
        }
        bArr[43] = 62;
        bArr[47] = Utf8.REPLACEMENT_BYTE;
        bArr[61] = 127;
        return bArr;
    }

    private static int guessLength(String str) {
        int length = str.length();
        int i = length - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            byte b = decodeMap[str.charAt(i)];
            if (b == 127) {
                i--;
            } else if (b == -1) {
                return (str.length() / 4) * 3;
            }
        }
        int i2 = length - (i + 1);
        if (i2 > 2) {
            return (str.length() / 4) * 3;
        }
        return ((str.length() / 4) * 3) - i2;
    }

    public static byte[] _parseBase64Binary(String str) {
        int iGuessLength = guessLength(str);
        byte[] bArr = new byte[iGuessLength];
        int length = str.length();
        byte[] bArr2 = new byte[4];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            byte b = decodeMap[str.charAt(i3)];
            if (b != -1) {
                bArr2[i2] = b;
                i2++;
            }
            if (i2 == 4) {
                int i4 = i + 1;
                bArr[i] = (byte) ((bArr2[0] << 2) | (bArr2[1] >> 4));
                byte b2 = bArr2[2];
                if (b2 != 127) {
                    bArr[i4] = (byte) ((b2 >> 2) | (bArr2[1] << 4));
                    i4++;
                }
                byte b3 = bArr2[3];
                if (b3 != 127) {
                    bArr[i4] = (byte) (b3 | (bArr2[2] << 6));
                    i = i4 + 1;
                } else {
                    i = i4;
                }
                i2 = 0;
            }
        }
        if (iGuessLength == i) {
            return bArr;
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr, 0, bArr3, 0, i);
        return bArr3;
    }

    private static char[] initEncodeMap() {
        int i;
        int i2;
        char[] cArr = new char[64];
        int i3 = 0;
        while (true) {
            i = 26;
            if (i3 >= 26) {
                break;
            }
            cArr[i3] = (char) (i3 + 65);
            i3++;
        }
        while (true) {
            if (i >= 52) {
                break;
            }
            cArr[i] = (char) ((i - 26) + 97);
            i++;
        }
        for (i2 = 52; i2 < 62; i2++) {
            cArr[i2] = (char) ((i2 - 52) + 48);
        }
        cArr[62] = SignatureVisitor.EXTENDS;
        cArr[63] = ClassPathElement.SEPARATOR_CHAR;
        return cArr;
    }

    public static char encode(int i) {
        return encodeMap[i & 63];
    }

    public static byte encodeByte(int i) {
        return (byte) encodeMap[i & 63];
    }

    public static String _printBase64Binary(byte[] bArr) {
        return _printBase64Binary(bArr, 0, bArr.length);
    }

    public static String _printBase64Binary(byte[] bArr, int i, int i2) {
        char[] cArr = new char[((i2 + 2) / 3) * 4];
        _printBase64Binary(bArr, i, i2, cArr, 0);
        return new String(cArr);
    }

    public static int _printBase64Binary(byte[] bArr, int i, int i2, char[] cArr, int i3) {
        while (i2 >= 3) {
            int i4 = i3 + 1;
            cArr[i3] = encode(bArr[i] >> 2);
            int i5 = i4 + 1;
            int i6 = i + 1;
            cArr[i4] = encode(((bArr[i] & 3) << 4) | ((bArr[i6] >> 4) & 15));
            int i7 = i5 + 1;
            int i8 = i + 2;
            cArr[i5] = encode((3 & (bArr[i8] >> 6)) | ((bArr[i6] & Ascii.SI) << 2));
            i3 = i7 + 1;
            cArr[i7] = encode(bArr[i8] & Utf8.REPLACEMENT_BYTE);
            i2 -= 3;
            i += 3;
        }
        if (i2 == 1) {
            int i9 = i3 + 1;
            cArr[i3] = encode(bArr[i] >> 2);
            int i10 = i9 + 1;
            cArr[i9] = encode((bArr[i] & 3) << 4);
            int i11 = i10 + 1;
            cArr[i10] = SignatureVisitor.INSTANCEOF;
            i3 = i11 + 1;
            cArr[i11] = SignatureVisitor.INSTANCEOF;
        }
        if (i2 != 2) {
            return i3;
        }
        int i12 = i3 + 1;
        cArr[i3] = encode(bArr[i] >> 2);
        int i13 = i12 + 1;
        int i14 = (3 & bArr[i]) << 4;
        int i15 = i + 1;
        cArr[i12] = encode(i14 | ((bArr[i15] >> 4) & 15));
        int i16 = i13 + 1;
        cArr[i13] = encode((bArr[i15] & Ascii.SI) << 2);
        int i17 = i16 + 1;
        cArr[i16] = SignatureVisitor.INSTANCEOF;
        return i17;
    }

    public static void _printBase64Binary(byte[] bArr, int i, int i2, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        char[] cArr = new char[4];
        while (i2 >= 3) {
            cArr[0] = encode(bArr[i] >> 2);
            int i3 = i + 1;
            cArr[1] = encode(((bArr[i] & 3) << 4) | ((bArr[i3] >> 4) & 15));
            int i4 = i + 2;
            cArr[2] = encode(((bArr[i3] & Ascii.SI) << 2) | ((bArr[i4] >> 6) & 3));
            cArr[3] = encode(bArr[i4] & Utf8.REPLACEMENT_BYTE);
            xMLStreamWriter.writeCharacters(cArr, 0, 4);
            i2 -= 3;
            i += 3;
        }
        if (i2 == 1) {
            cArr[0] = encode(bArr[i] >> 2);
            cArr[1] = encode((bArr[i] & 3) << 4);
            cArr[2] = SignatureVisitor.INSTANCEOF;
            cArr[3] = SignatureVisitor.INSTANCEOF;
            xMLStreamWriter.writeCharacters(cArr, 0, 4);
        }
        if (i2 == 2) {
            cArr[0] = encode(bArr[i] >> 2);
            int i5 = (bArr[i] & 3) << 4;
            int i6 = i + 1;
            cArr[1] = encode(i5 | ((bArr[i6] >> 4) & 15));
            cArr[2] = encode((bArr[i6] & Ascii.SI) << 2);
            cArr[3] = SignatureVisitor.INSTANCEOF;
            xMLStreamWriter.writeCharacters(cArr, 0, 4);
        }
    }

    public static int _printBase64Binary(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        while (i2 >= 3) {
            int i4 = i3 + 1;
            bArr2[i3] = encodeByte(bArr[i] >> 2);
            int i5 = i4 + 1;
            int i6 = i + 1;
            bArr2[i4] = encodeByte(((bArr[i] & 3) << 4) | ((bArr[i6] >> 4) & 15));
            int i7 = i5 + 1;
            int i8 = i + 2;
            bArr2[i5] = encodeByte((3 & (bArr[i8] >> 6)) | ((bArr[i6] & Ascii.SI) << 2));
            i3 = i7 + 1;
            bArr2[i7] = encodeByte(bArr[i8] & Utf8.REPLACEMENT_BYTE);
            i2 -= 3;
            i += 3;
        }
        if (i2 == 1) {
            int i9 = i3 + 1;
            bArr2[i3] = encodeByte(bArr[i] >> 2);
            int i10 = i9 + 1;
            bArr2[i9] = encodeByte((bArr[i] & 3) << 4);
            int i11 = i10 + 1;
            bArr2[i10] = 61;
            i3 = i11 + 1;
            bArr2[i11] = 61;
        }
        if (i2 != 2) {
            return i3;
        }
        int i12 = i3 + 1;
        bArr2[i3] = encodeByte(bArr[i] >> 2);
        int i13 = i12 + 1;
        int i14 = (3 & bArr[i]) << 4;
        int i15 = i + 1;
        bArr2[i12] = encodeByte(i14 | ((bArr[i15] >> 4) & 15));
        int i16 = i13 + 1;
        bArr2[i13] = encodeByte((bArr[i15] & Ascii.SI) << 2);
        int i17 = i16 + 1;
        bArr2[i16] = 61;
        return i17;
    }

    private static CharSequence removeOptionalPlus(CharSequence charSequence) {
        int length = charSequence.length();
        if (length <= 1 || charSequence.charAt(0) != '+') {
            return charSequence;
        }
        CharSequence charSequenceSubSequence = charSequence.subSequence(1, length);
        char cCharAt = charSequenceSubSequence.charAt(0);
        if (('0' > cCharAt || cCharAt > '9') && '.' != cCharAt) {
            throw new NumberFormatException();
        }
        return charSequenceSubSequence;
    }

    public static DatatypeFactory getDatatypeFactory() {
        ClassLoader classLoader = (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() { // from class: com.sun.xml.bind.DatatypeConverterImpl.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public ClassLoader run() {
                return Thread.currentThread().getContextClassLoader();
            }
        });
        Map<ClassLoader, DatatypeFactory> map = DF_CACHE;
        DatatypeFactory datatypeFactoryNewInstance = map.get(classLoader);
        if (datatypeFactoryNewInstance == null) {
            synchronized (DatatypeConverterImpl.class) {
                datatypeFactoryNewInstance = map.get(classLoader);
                if (datatypeFactoryNewInstance == null) {
                    try {
                        datatypeFactoryNewInstance = DatatypeFactory.newInstance();
                        map.put(classLoader, datatypeFactoryNewInstance);
                    } catch (DatatypeConfigurationException e) {
                        throw new Error(Messages.FAILED_TO_INITIALE_DATATYPE_FACTORY.format(new Object[0]), e);
                    }
                }
            }
        }
        return datatypeFactoryNewInstance;
    }

    private static final class CalendarFormatter {
        private CalendarFormatter() {
        }

        public static String doFormat(String str, Calendar calendar) throws IllegalArgumentException {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                char cCharAt = str.charAt(i);
                if (cCharAt != '%') {
                    sb.append(cCharAt);
                    i = i2;
                } else {
                    i = i2 + 1;
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 == 'D') {
                        formatDays(calendar, sb);
                    } else if (cCharAt2 == 'M') {
                        formatMonth(calendar, sb);
                    } else if (cCharAt2 == 'Y') {
                        formatYear(calendar, sb);
                    } else if (cCharAt2 == 'h') {
                        formatHours(calendar, sb);
                    } else if (cCharAt2 == 'm') {
                        formatMinutes(calendar, sb);
                    } else if (cCharAt2 == 's') {
                        formatSeconds(calendar, sb);
                    } else if (cCharAt2 == 'z') {
                        formatTimeZone(calendar, sb);
                    } else {
                        throw new InternalError();
                    }
                }
            }
            return sb.toString();
        }

        private static void formatYear(Calendar calendar, StringBuilder sb) {
            String string;
            int i = calendar.get(1);
            if (i <= 0) {
                string = Integer.toString(1 - i);
            } else {
                string = Integer.toString(i);
            }
            while (string.length() < 4) {
                string = "0" + string;
            }
            if (i <= 0) {
                string = "-" + string;
            }
            sb.append(string);
        }

        private static void formatMonth(Calendar calendar, StringBuilder sb) {
            formatTwoDigits(calendar.get(2) + 1, sb);
        }

        private static void formatDays(Calendar calendar, StringBuilder sb) {
            formatTwoDigits(calendar.get(5), sb);
        }

        private static void formatHours(Calendar calendar, StringBuilder sb) {
            formatTwoDigits(calendar.get(11), sb);
        }

        private static void formatMinutes(Calendar calendar, StringBuilder sb) {
            formatTwoDigits(calendar.get(12), sb);
        }

        private static void formatSeconds(Calendar calendar, StringBuilder sb) {
            int i;
            formatTwoDigits(calendar.get(13), sb);
            if (!calendar.isSet(14) || (i = calendar.get(14)) == 0) {
                return;
            }
            String string = Integer.toString(i);
            while (string.length() < 3) {
                string = "0" + string;
            }
            sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
            sb.append(string);
        }

        private static void formatTimeZone(Calendar calendar, StringBuilder sb) {
            TimeZone timeZone = calendar.getTimeZone();
            if (timeZone == null) {
                return;
            }
            int offset = timeZone.getOffset(calendar.getTime().getTime());
            if (offset == 0) {
                sb.append('Z');
                return;
            }
            if (offset >= 0) {
                sb.append(SignatureVisitor.EXTENDS);
            } else {
                sb.append(SignatureVisitor.SUPER);
                offset *= -1;
            }
            int i = offset / TimeConstants.MIN;
            formatTwoDigits(i / 60, sb);
            sb.append(':');
            formatTwoDigits(i % 60, sb);
        }

        private static void formatTwoDigits(int i, StringBuilder sb) {
            if (i < 10) {
                sb.append('0');
            }
            sb.append(i);
        }
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public BigInteger parseInteger(String str) {
        return _parseInteger(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printInteger(BigInteger bigInteger) {
        return _printInteger(bigInteger);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public int parseInt(String str) {
        return _parseInt(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public long parseLong(String str) {
        return _parseLong(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public short parseShort(String str) {
        return _parseShort(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printShort(short s) {
        return _printShort(s);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public BigDecimal parseDecimal(String str) {
        return _parseDecimal(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public float parseFloat(String str) {
        return _parseFloat(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printFloat(float f) {
        return _printFloat(f);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public double parseDouble(String str) {
        return _parseDouble(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public boolean parseBoolean(String str) {
        Boolean bool_parseBoolean = _parseBoolean(str);
        if (bool_parseBoolean == null) {
            return false;
        }
        return bool_parseBoolean.booleanValue();
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public byte parseByte(String str) {
        return _parseByte(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printByte(byte b) {
        return _printByte(b);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public QName parseQName(String str, NamespaceContext namespaceContext) {
        return _parseQName(str, namespaceContext);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public Calendar parseDateTime(String str) {
        return _parseDateTime(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printDateTime(Calendar calendar) {
        return _printDateTime(calendar);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public byte[] parseBase64Binary(String str) {
        return _parseBase64Binary(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public byte[] parseHexBinary(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("hexBinary needs to be even-length: " + str);
        }
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            int iHexToBin = hexToBin(str.charAt(i));
            int iHexToBin2 = hexToBin(str.charAt(i + 1));
            if (iHexToBin == -1 || iHexToBin2 == -1) {
                throw new IllegalArgumentException("contains illegal character for hexBinary: " + str);
            }
            bArr[i / 2] = (byte) ((iHexToBin * 16) + iHexToBin2);
        }
        return bArr;
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printHexBinary(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            char[] cArr = hexCode;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & Ascii.SI]);
        }
        return sb.toString();
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public long parseUnsignedInt(String str) {
        return _parseLong(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printUnsignedInt(long j) {
        return _printLong(j);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public int parseUnsignedShort(String str) {
        return _parseInt(str);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public Calendar parseTime(String str) {
        return getDatatypeFactory().newXMLGregorianCalendar(str).toGregorianCalendar();
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printTime(Calendar calendar) {
        return CalendarFormatter.doFormat("%h:%m:%s%z", calendar);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public Calendar parseDate(String str) {
        return getDatatypeFactory().newXMLGregorianCalendar(str).toGregorianCalendar();
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printDate(Calendar calendar) {
        return _printDate(calendar);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printInt(int i) {
        return _printInt(i);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printLong(long j) {
        return _printLong(j);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printDecimal(BigDecimal bigDecimal) {
        return _printDecimal(bigDecimal);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printDouble(double d) {
        return _printDouble(d);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printQName(QName qName, NamespaceContext namespaceContext) {
        return _printQName(qName, namespaceContext);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printBase64Binary(byte[] bArr) {
        return _printBase64Binary(bArr);
    }

    @Override // javax.xml.bind.DatatypeConverterInterface
    @Deprecated
    public String printUnsignedShort(int i) {
        return String.valueOf(i);
    }
}
