package com.bugsnag.android.repackaged.dslplatform.json;

import androidx.annotation.Nullable;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Formatter;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes5.dex */
public final class JsonReader<TContext> {
    private static final boolean[] WHITESPACE;
    private static final EOFException eof;
    private static final Charset utf8 = Charset.forName(ParserUtil.UTF_8);
    protected byte[] buffer;
    private int bufferLenWithExtraSpace;
    protected char[] chars;
    public final TContext context;
    private int currentIndex;
    private long currentPosition;
    protected final int doubleLengthLimit;
    protected final DoublePrecision doublePrecision;
    private final StringBuilder error;
    private final Formatter errorFormatter;
    protected final ErrorInfo errorInfo;
    private final StringCache keyCache;
    private byte last;
    private int lastNameLen;
    private int length;
    protected final int maxNumberDigits;
    private final int maxStringBuffer;
    private int nameEnd;
    private final byte[] originalBuffer;
    private final int originalBufferLenWithExtraSpace;
    private int readLimit;
    private InputStream stream;
    private final char[] tmp;
    private int tokenStart;
    private final TypeLookup typeLookup;
    protected final UnknownNumberParsing unknownNumbers;
    private final StringCache valuesCache;

    public enum ErrorInfo {
        WITH_STACK_TRACE,
        DESCRIPTION_AND_POSITION,
        DESCRIPTION_ONLY,
        MINIMAL
    }

    public interface ReadJsonObject<T extends JsonObject> {
        @Nullable
        T deserialize(JsonReader jsonReader) throws IOException;
    }

    public interface ReadObject<T> {
        @Nullable
        T read(JsonReader jsonReader) throws IOException;
    }

    public enum UnknownNumberParsing {
        LONG_AND_BIGDECIMAL,
        LONG_AND_DOUBLE,
        BIGDECIMAL,
        DOUBLE
    }

    static {
        boolean[] zArr = new boolean[256];
        WHITESPACE = zArr;
        zArr[137] = true;
        zArr[138] = true;
        zArr[139] = true;
        zArr[140] = true;
        zArr[141] = true;
        zArr[160] = true;
        zArr[32] = true;
        zArr[97] = true;
        zArr[98] = true;
        zArr[99] = true;
        eof = new EmptyEOFException();
    }

    public enum DoublePrecision {
        EXACT(0),
        HIGH(1),
        DEFAULT(3),
        LOW(4);

        final int level;

        DoublePrecision(int i) {
            this.level = i;
        }
    }

    private JsonReader(char[] cArr, byte[] bArr, int i, @Nullable TContext tcontext, @Nullable StringCache stringCache, @Nullable StringCache stringCache2, @Nullable TypeLookup typeLookup, ErrorInfo errorInfo, DoublePrecision doublePrecision, UnknownNumberParsing unknownNumberParsing, int i2, int i3) {
        this.currentIndex = 0;
        this.currentPosition = 0L;
        this.last = (byte) 32;
        StringBuilder sb = new StringBuilder(0);
        this.error = sb;
        this.errorFormatter = new Formatter(sb);
        this.tmp = cArr;
        this.buffer = bArr;
        this.length = i;
        int length = bArr.length - 38;
        this.bufferLenWithExtraSpace = length;
        this.context = tcontext;
        this.chars = cArr;
        this.keyCache = stringCache;
        this.valuesCache = stringCache2;
        this.typeLookup = typeLookup;
        this.errorInfo = errorInfo;
        this.doublePrecision = doublePrecision;
        this.unknownNumbers = unknownNumberParsing;
        this.maxNumberDigits = i2;
        this.maxStringBuffer = i3;
        this.doubleLengthLimit = doublePrecision.level + 15;
        this.originalBuffer = bArr;
        this.originalBufferLenWithExtraSpace = length;
    }

    JsonReader(byte[] bArr, int i, @Nullable TContext tcontext, char[] cArr, @Nullable StringCache stringCache, @Nullable StringCache stringCache2, @Nullable TypeLookup typeLookup, ErrorInfo errorInfo, DoublePrecision doublePrecision, UnknownNumberParsing unknownNumberParsing, int i2, int i3) {
        this(cArr, bArr, i, tcontext, stringCache, stringCache2, typeLookup, errorInfo, doublePrecision, unknownNumberParsing, i2, i3);
        if (cArr == null) {
            throw new IllegalArgumentException("tmp buffer provided as null.");
        }
        if (i > bArr.length) {
            throw new IllegalArgumentException("length can't be longer than buffer.length");
        }
        if (i < bArr.length) {
            bArr[i] = 0;
        }
    }

    final void reset() {
        this.buffer = this.originalBuffer;
        this.bufferLenWithExtraSpace = this.originalBufferLenWithExtraSpace;
        this.currentIndex = 0;
        this.length = 0;
        this.readLimit = 0;
        this.stream = null;
    }

    public final JsonReader<TContext> process(@Nullable InputStream inputStream) throws IOException {
        this.currentPosition = 0L;
        this.currentIndex = 0;
        this.stream = inputStream;
        if (inputStream != null) {
            int i = this.length;
            int i2 = this.bufferLenWithExtraSpace;
            if (i >= i2) {
                i = i2;
            }
            this.readLimit = i;
            int fully = readFully(this.buffer, inputStream, 0);
            int i3 = this.bufferLenWithExtraSpace;
            if (fully < i3) {
                i3 = fully;
            }
            this.readLimit = i3;
            this.length = fully;
        }
        return this;
    }

    public final int length() {
        return this.length;
    }

    public String toString() {
        return new String(this.buffer, 0, this.length, utf8);
    }

    private static int readFully(byte[] bArr, InputStream inputStream, int i) throws IOException {
        int i2;
        while (i < bArr.length && (i2 = inputStream.read(bArr, i, bArr.length - i)) != -1) {
            i += i2;
        }
        return i;
    }

    private static class EmptyEOFException extends EOFException {
        private EmptyEOFException() {
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    boolean withStackTrace() {
        return this.errorInfo == ErrorInfo.WITH_STACK_TRACE;
    }

    public final byte read() throws IOException {
        if (this.stream != null && this.currentIndex > this.readLimit) {
            prepareNextBlock();
        }
        int i = this.currentIndex;
        if (i >= this.length) {
            throw ParsingException.create("Unexpected end of JSON input", eof, withStackTrace());
        }
        byte[] bArr = this.buffer;
        this.currentIndex = i + 1;
        byte b = bArr[i];
        this.last = b;
        return b;
    }

    private int prepareNextBlock() throws IOException {
        int i = this.length;
        int i2 = this.currentIndex;
        int i3 = i - i2;
        byte[] bArr = this.buffer;
        System.arraycopy(bArr, i2, bArr, 0, i3);
        int fully = readFully(this.buffer, this.stream, i3);
        long j = this.currentPosition;
        int i4 = this.currentIndex;
        this.currentPosition = j + ((long) i4);
        if (fully == i3) {
            int i5 = this.length - i4;
            this.readLimit = i5;
            this.length = i5;
            this.currentIndex = 0;
        } else {
            int i6 = this.bufferLenWithExtraSpace;
            if (fully < i6) {
                i6 = fully;
            }
            this.readLimit = i6;
            this.length = fully;
            this.currentIndex = 0;
        }
        return fully;
    }

    final boolean isEndOfStream() throws IOException {
        return this.stream == null ? this.length == this.currentIndex : this.length == this.currentIndex && prepareNextBlock() == 0;
    }

    public final byte last() {
        return this.last;
    }

    private void positionDescription(int i, StringBuilder sb) {
        sb.append("at position: ");
        sb.append(positionInStream(i));
        int i2 = this.currentIndex;
        if (i2 > i) {
            try {
                int iMin = Math.min(i2 - i, 20);
                String str = new String(this.buffer, (this.currentIndex - i) - iMin, iMin, utf8);
                sb.append(", following: `");
                sb.append(str);
                sb.append('`');
            } catch (Exception unused) {
            }
        }
        int i3 = this.currentIndex;
        int i4 = i3 - i;
        int i5 = this.readLimit;
        if (i4 < i5) {
            try {
                String str2 = new String(this.buffer, this.currentIndex - i, Math.min((i5 - i3) + i, 20), utf8);
                sb.append(", before: `");
                sb.append(str2);
                sb.append('`');
            } catch (Exception unused2) {
            }
        }
    }

    public final ParsingException newParseError(String str) {
        return newParseError(str, 0);
    }

    public final ParsingException newParseError(String str, int i) {
        if (this.errorInfo == ErrorInfo.MINIMAL) {
            return ParsingException.create(str, false);
        }
        this.error.setLength(0);
        this.error.append(str);
        this.error.append(". Found ");
        this.error.append((char) this.last);
        if (this.errorInfo == ErrorInfo.DESCRIPTION_ONLY) {
            return ParsingException.create(this.error.toString(), false);
        }
        this.error.append(" ");
        positionDescription(i, this.error);
        return ParsingException.create(this.error.toString(), withStackTrace());
    }

    public final ParsingException newParseErrorAt(String str, int i) {
        ErrorInfo errorInfo = this.errorInfo;
        if (errorInfo == ErrorInfo.MINIMAL || errorInfo == ErrorInfo.DESCRIPTION_ONLY) {
            return ParsingException.create(str, false);
        }
        this.error.setLength(0);
        this.error.append(str);
        this.error.append(" ");
        positionDescription(i, this.error);
        return ParsingException.create(this.error.toString(), withStackTrace());
    }

    public final ParsingException newParseErrorAt(String str, int i, Exception exc) {
        if (exc == null) {
            throw new IllegalArgumentException("cause can't be null");
        }
        if (this.errorInfo == ErrorInfo.MINIMAL) {
            return ParsingException.create(str, exc, false);
        }
        this.error.setLength(0);
        String message = exc.getMessage();
        if (message != null && message.length() > 0) {
            this.error.append(message);
            if (!message.endsWith(".")) {
                this.error.append(".");
            }
            this.error.append(" ");
        }
        this.error.append(str);
        if (this.errorInfo == ErrorInfo.DESCRIPTION_ONLY) {
            return ParsingException.create(this.error.toString(), exc, false);
        }
        this.error.append(" ");
        positionDescription(i, this.error);
        return ParsingException.create(this.error.toString(), withStackTrace());
    }

    public final ParsingException newParseErrorFormat(String str, int i, String str2, Object... objArr) {
        if (this.errorInfo == ErrorInfo.MINIMAL) {
            return ParsingException.create(str, false);
        }
        this.error.setLength(0);
        this.errorFormatter.format(str2, objArr);
        if (this.errorInfo == ErrorInfo.DESCRIPTION_ONLY) {
            return ParsingException.create(this.error.toString(), false);
        }
        this.error.append(" ");
        positionDescription(i, this.error);
        return ParsingException.create(this.error.toString(), withStackTrace());
    }

    public final ParsingException newParseErrorWith(String str, @Nullable Object obj) {
        return newParseErrorWith(str, 0, "", str, obj, "");
    }

    public final ParsingException newParseErrorWith(String str, int i, String str2, String str3, @Nullable Object obj, String str4) {
        if (this.errorInfo == ErrorInfo.MINIMAL) {
            return ParsingException.create(str, false);
        }
        this.error.setLength(0);
        this.error.append(str2);
        this.error.append(str3);
        if (obj != null) {
            this.error.append(": '");
            this.error.append(obj.toString());
            this.error.append("'");
        }
        this.error.append(str4);
        if (this.errorInfo == ErrorInfo.DESCRIPTION_ONLY) {
            return ParsingException.create(this.error.toString(), false);
        }
        this.error.append(" ");
        positionDescription(i, this.error);
        return ParsingException.create(this.error.toString(), withStackTrace());
    }

    public final int getTokenStart() {
        return this.tokenStart;
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final int scanNumber() {
        int i = this.currentIndex;
        this.tokenStart = i - 1;
        byte b = this.last;
        int i2 = 1;
        while (i < this.length) {
            int i3 = i + 1;
            b = this.buffer[i];
            if (b == 44 || b == 125 || b == 93) {
                break;
            }
            i2++;
            i = i3;
        }
        this.currentIndex += i2 - 1;
        this.last = b;
        return this.tokenStart;
    }

    final char[] prepareBuffer(int i, int i2) throws ParsingException {
        char[] cArr;
        if (i2 > this.maxNumberDigits) {
            throw newParseErrorWith("Too many digits detected in number", i2, "", "Too many digits detected in number", Integer.valueOf(i2), "");
        }
        while (true) {
            cArr = this.chars;
            if (cArr.length >= i2) {
                break;
            }
            this.chars = Arrays.copyOf(cArr, cArr.length * 2);
        }
        byte[] bArr = this.buffer;
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = (char) bArr[i + i3];
        }
        return cArr;
    }

    final boolean allWhitespace(int i, int i2) {
        byte[] bArr = this.buffer;
        while (i < i2) {
            if (!WHITESPACE[bArr[i] + ByteCompanionObject.MIN_VALUE]) {
                return false;
            }
            i++;
        }
        return true;
    }

    public final String readSimpleString() throws ParsingException {
        char[] cArr;
        if (this.last != 34) {
            throw newParseError("Expecting '\"' for string start");
        }
        int i = this.currentIndex;
        int i2 = 0;
        while (true) {
            try {
                cArr = this.tmp;
                if (i2 >= cArr.length) {
                    break;
                }
                int i3 = i + 1;
                byte b = this.buffer[i];
                if (b == 34) {
                    i = i3;
                    break;
                }
                int i4 = i2 + 1;
                cArr[i2] = (char) b;
                i2 = i4;
                i = i3;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw newParseErrorAt("JSON string was not closed with a double quote", 0);
            }
        }
        if (i > this.length) {
            throw newParseErrorAt("JSON string was not closed with a double quote", 0);
        }
        this.currentIndex = i;
        return new String(cArr, 0, i2);
    }

    public final char[] readSimpleQuote() throws ParsingException {
        char[] cArr;
        if (this.last != 34) {
            throw newParseError("Expecting '\"' for string start");
        }
        int i = this.currentIndex;
        this.tokenStart = i;
        int i2 = 0;
        while (true) {
            try {
                cArr = this.tmp;
                if (i2 >= cArr.length) {
                    break;
                }
                int i3 = i + 1;
                byte b = this.buffer[i];
                if (b == 34) {
                    i = i3;
                    break;
                }
                cArr[i2] = (char) b;
                i2++;
                i = i3;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw newParseErrorAt("JSON string was not closed with a double quote", 0);
            }
        }
        if (i > this.length) {
            throw newParseErrorAt("JSON string was not closed with a double quote", 0);
        }
        this.currentIndex = i;
        return cArr;
    }

    public final String readString() throws IOException {
        int string = parseString();
        StringCache stringCache = this.valuesCache;
        return stringCache == null ? new String(this.chars, 0, string) : stringCache.get(this.chars, string);
    }

    public final StringBuilder appendString(StringBuilder sb) throws IOException {
        sb.append(this.chars, 0, parseString());
        return sb;
    }

    public final StringBuffer appendString(StringBuffer stringBuffer) throws IOException {
        stringBuffer.append(this.chars, 0, parseString());
        return stringBuffer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01ca, code lost:
    
        throw newParseErrorWith("Maximum string buffer limit exceeded", java.lang.Integer.valueOf(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01cb, code lost:
    
        r4[r6] = (char) r0;
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01d9, code lost:
    
        throw newParseErrorAt("JSON string was not closed with a double quote", 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0035, code lost:
    
        if (r5 != r4.length) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0037, code lost:
    
        r1 = r15.chars;
        r4 = r1.length * 2;
        r5 = r15.maxStringBuffer;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003e, code lost:
    
        if (r4 > r5) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0040, code lost:
    
        r4 = java.util.Arrays.copyOf(r1, r4);
        r15.chars = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004f, code lost:
    
        throw newParseErrorWith("Maximum string buffer limit exceeded", java.lang.Integer.valueOf(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0050, code lost:
    
        r1 = r4.length;
        r6 = r6 - 1;
        r15.currentIndex = r6;
        r6 = r6 - r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0059, code lost:
    
        if (isEndOfStream() != false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
    
        r0 = read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005f, code lost:
    
        if (r0 != 34) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0061, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0068, code lost:
    
        if (r0 != 92) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006c, code lost:
    
        if (r6 < (r1 - 6)) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006e, code lost:
    
        r0 = r15.chars;
        r1 = r0.length * 2;
        r4 = r15.maxStringBuffer;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0075, code lost:
    
        if (r1 > r4) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0077, code lost:
    
        r4 = java.util.Arrays.copyOf(r0, r1);
        r15.chars = r4;
        r1 = r4.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0087, code lost:
    
        throw newParseErrorWith("Maximum string buffer limit exceeded", java.lang.Integer.valueOf(r4));
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0088, code lost:
    
        r0 = r15.buffer;
        r10 = r15.currentIndex;
        r11 = r10 + 1;
        r15.currentIndex = r11;
        r10 = r0[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0092, code lost:
    
        if (r10 == 34) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0096, code lost:
    
        if (r10 == 47) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0098, code lost:
    
        if (r10 == 92) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009c, code lost:
    
        if (r10 == 98) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a0, code lost:
    
        if (r10 == 102) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a4, code lost:
    
        if (r10 == 110) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00a8, code lost:
    
        if (r10 == 114) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ac, code lost:
    
        if (r10 == 116) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b0, code lost:
    
        if (r10 != 117) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00b2, code lost:
    
        r15.currentIndex = r11 + 1;
        r0 = hexToInt(r0[r11]) << 12;
        r7 = r15.buffer;
        r9 = r15.currentIndex;
        r15.currentIndex = r9 + 1;
        r0 = r0 + (hexToInt(r7[r9]) << 8);
        r5 = r15.buffer;
        r7 = r15.currentIndex;
        r15.currentIndex = r7 + 1;
        r0 = r0 + (hexToInt(r5[r7]) << 4);
        r5 = r15.buffer;
        r7 = r15.currentIndex;
        r15.currentIndex = r7 + 1;
        r5 = hexToInt(r5[r7]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ed, code lost:
    
        r0 = r0 + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00fa, code lost:
    
        throw newParseErrorWith("Invalid escape combination detected", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00fb, code lost:
    
        r0 = 9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ff, code lost:
    
        r0 = 13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0103, code lost:
    
        r0 = 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0107, code lost:
    
        r0 = 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x010a, code lost:
    
        r0 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x010d, code lost:
    
        r0 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0112, code lost:
    
        if ((r0 & 128) == 0) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0116, code lost:
    
        if (r6 < (r1 - 4)) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0118, code lost:
    
        r1 = r15.chars;
        r4 = r1.length * 2;
        r5 = r15.maxStringBuffer;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x011f, code lost:
    
        if (r4 > r5) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0121, code lost:
    
        r1 = java.util.Arrays.copyOf(r1, r4);
        r15.chars = r1;
        r4 = r1;
        r1 = r1.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0134, code lost:
    
        throw newParseErrorWith("Maximum string buffer limit exceeded", java.lang.Integer.valueOf(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0135, code lost:
    
        r5 = r15.buffer;
        r7 = r15.currentIndex;
        r10 = r7 + 1;
        r15.currentIndex = r10;
        r7 = r5[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0143, code lost:
    
        if ((r0 & 224) != 192) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0145, code lost:
    
        r0 = (r0 & 31) << 6;
        r5 = r7 & 63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x014c, code lost:
    
        r11 = r10 + 1;
        r15.currentIndex = r11;
        r10 = r5[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0156, code lost:
    
        if ((r0 & 240) != 224) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0158, code lost:
    
        r0 = ((r0 & 15) << 12) + ((r7 & 63) << 6);
        r5 = r10 & 63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0163, code lost:
    
        r15.currentIndex = r11 + 1;
        r5 = r5[r11];
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x016f, code lost:
    
        if ((r0 & 248) != 240) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0171, code lost:
    
        r0 = ((((r0 & 7) << 18) + ((r7 & 63) << 12)) + ((r10 & 63) << 6)) + (r5 & 63);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0183, code lost:
    
        if (r0 < 65536) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0187, code lost:
    
        if (r0 >= 1114112) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0189, code lost:
    
        r0 = r0 - 65536;
        r5 = r6 + 1;
        r4[r6] = (char) ((r0 >>> 10) + com.fasterxml.jackson.core.base.GeneratorBase.SURR1_FIRST);
        r6 = r5 + 1;
        r4[r5] = (char) ((r0 & 1023) + com.fasterxml.jackson.core.base.GeneratorBase.SURR2_FIRST);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01a6, code lost:
    
        throw newParseErrorAt("Invalid unicode character detected", 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ab, code lost:
    
        throw newParseErrorAt("Invalid unicode character detected", 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01ac, code lost:
    
        if (r6 < r1) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01ae, code lost:
    
        r1 = r15.chars;
        r4 = r1.length * 2;
        r5 = r15.maxStringBuffer;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01b5, code lost:
    
        if (r4 > r5) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01b7, code lost:
    
        r1 = java.util.Arrays.copyOf(r1, r4);
        r15.chars = r1;
        r4 = r1;
        r1 = r1.length;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int parseString() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 488
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.repackaged.dslplatform.json.JsonReader.parseString():int");
    }

    private int hexToInt(byte b) throws ParsingException {
        if (b >= 48 && b <= 57) {
            return b - 48;
        }
        if (b >= 65 && b <= 70) {
            return b - 55;
        }
        if (b < 97 || b > 102) {
            throw newParseErrorWith("Could not parse unicode escape, expected a hexadecimal digit", Byte.valueOf(b));
        }
        return b - 87;
    }

    private boolean wasWhiteSpace() {
        byte b = this.last;
        if (b != -96 && b != 32) {
            switch (b) {
                case -31:
                    int i = this.currentIndex;
                    if (i + 1 < this.length) {
                        byte[] bArr = this.buffer;
                        if (bArr[i] == -102 && bArr[i + 1] == -128) {
                            this.currentIndex = i + 2;
                            this.last = (byte) 32;
                            return true;
                        }
                    }
                    return false;
                case -30:
                    int i2 = this.currentIndex;
                    if (i2 + 1 >= this.length) {
                        return false;
                    }
                    byte[] bArr2 = this.buffer;
                    byte b2 = bArr2[i2];
                    byte b3 = bArr2[i2 + 1];
                    if (b2 == -127 && b3 == -97) {
                        this.currentIndex = i2 + 2;
                        this.last = (byte) 32;
                        return true;
                    }
                    if (b2 != -128) {
                        return false;
                    }
                    if (b3 != -88 && b3 != -87 && b3 != -81) {
                        switch (b3) {
                            case -128:
                            case -127:
                            case -126:
                            case -125:
                            case -124:
                            case -123:
                            case -122:
                            case -121:
                            case -120:
                            case -119:
                            case -118:
                                break;
                            default:
                                return false;
                        }
                    }
                    this.currentIndex = i2 + 2;
                    this.last = (byte) 32;
                    return true;
                case -29:
                    int i3 = this.currentIndex;
                    if (i3 + 1 < this.length) {
                        byte[] bArr3 = this.buffer;
                        if (bArr3[i3] == -128 && bArr3[i3 + 1] == -128) {
                            this.currentIndex = i3 + 2;
                            this.last = (byte) 32;
                            return true;
                        }
                    }
                    return false;
                default:
                    switch (b) {
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                            break;
                        default:
                            return false;
                    }
                    break;
            }
        }
        return true;
    }

    public final byte getNextToken() throws IOException {
        read();
        if (WHITESPACE[this.last + ByteCompanionObject.MIN_VALUE]) {
            while (wasWhiteSpace()) {
                read();
            }
        }
        return this.last;
    }

    public final long positionInStream(int i) {
        return (this.currentPosition + ((long) this.currentIndex)) - ((long) i);
    }

    public final int getLastHash() {
        long j = -2128831035;
        if (this.stream != null && this.nameEnd == -1) {
            for (int i = 0; i < this.lastNameLen; i++) {
                j = (j ^ ((long) ((byte) this.chars[i]))) * 16777619;
            }
        } else {
            int i2 = this.nameEnd - 1;
            for (int i3 = this.tokenStart; i3 < i2; i3++) {
                j = (j ^ ((long) this.buffer[i3])) * 16777619;
            }
        }
        return (int) j;
    }

    public final byte[] readBase64() throws IOException {
        if (this.stream != null && Base64.findEnd(this.buffer, this.currentIndex) == this.buffer.length) {
            int string = parseString();
            byte[] bArr = new byte[string];
            for (int i = 0; i < string; i++) {
                bArr[i] = (byte) this.chars[i];
            }
            return Base64.decodeFast(bArr, 0, string);
        }
        if (this.last != 34) {
            throw newParseError("Expecting '\"' for base64 start");
        }
        int i2 = this.currentIndex;
        int iFindEnd = Base64.findEnd(this.buffer, i2);
        byte[] bArr2 = this.buffer;
        int i3 = iFindEnd + 1;
        this.currentIndex = i3;
        byte b = bArr2[iFindEnd];
        this.last = b;
        if (b != 34) {
            throw newParseError("Expecting '\"' for base64 end");
        }
        return Base64.decodeFast(bArr2, i2, i3 - 1);
    }

    public final String readKey() throws IOException {
        int string = parseString();
        StringCache stringCache = this.keyCache;
        String str = stringCache != null ? stringCache.get(this.chars, string) : new String(this.chars, 0, string);
        if (getNextToken() != 58) {
            throw newParseError("Expecting ':' after attribute name");
        }
        getNextToken();
        return str;
    }

    public final boolean wasNull() throws ParsingException {
        if (this.last != 110) {
            return false;
        }
        int i = this.currentIndex;
        if (i + 2 < this.length) {
            byte[] bArr = this.buffer;
            if (bArr[i] == 117 && bArr[i + 1] == 108 && bArr[i + 2] == 108) {
                this.currentIndex = i + 3;
                this.last = (byte) 108;
                return true;
            }
        }
        throw newParseErrorAt("Invalid null constant found", 0);
    }

    public final boolean wasTrue() throws ParsingException {
        if (this.last != 116) {
            return false;
        }
        int i = this.currentIndex;
        if (i + 2 < this.length) {
            byte[] bArr = this.buffer;
            if (bArr[i] == 114 && bArr[i + 1] == 117 && bArr[i + 2] == 101) {
                this.currentIndex = i + 3;
                this.last = (byte) 101;
                return true;
            }
        }
        throw newParseErrorAt("Invalid true constant found", 0);
    }

    public final boolean wasFalse() throws ParsingException {
        if (this.last != 102) {
            return false;
        }
        int i = this.currentIndex;
        if (i + 3 < this.length) {
            byte[] bArr = this.buffer;
            if (bArr[i] == 97 && bArr[i + 1] == 108 && bArr[i + 2] == 115 && bArr[i + 3] == 101) {
                this.currentIndex = i + 4;
                this.last = (byte) 101;
                return true;
            }
        }
        throw newParseErrorAt("Invalid false constant found", 0);
    }

    public final void checkArrayEnd() throws IOException {
        if (this.last != 93) {
            if (this.currentIndex < this.length) {
                throw newParseError("Expecting ']' as array end");
            }
            throw newParseErrorAt("Unexpected end of JSON in collection", 0, eof);
        }
    }

    public final <T, S extends T> ArrayList<T> deserializeNullableCollection(ReadObject<S> readObject) throws IOException {
        ArrayList<T> arrayList = new ArrayList<>(4);
        deserializeNullableCollection(readObject, arrayList);
        return arrayList;
    }

    public final <T, S extends T> void deserializeNullableCollection(ReadObject<S> readObject, Collection<T> collection) throws IOException {
        if (wasNull()) {
            collection.add(null);
        } else {
            collection.add(readObject.read(this));
        }
        while (getNextToken() == 44) {
            getNextToken();
            if (wasNull()) {
                collection.add(null);
            } else {
                collection.add(readObject.read(this));
            }
        }
        checkArrayEnd();
    }

    public final <T extends JsonObject> ArrayList<T> deserializeNullableCollection(ReadJsonObject<T> readJsonObject) throws IOException {
        ArrayList<T> arrayList = new ArrayList<>(4);
        deserializeNullableCollection(readJsonObject, arrayList);
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends JsonObject> void deserializeNullableCollection(ReadJsonObject<T> readJsonObject, Collection<T> collection) throws IOException {
        if (this.last == 123) {
            getNextToken();
            collection.add(readJsonObject.deserialize(this));
        } else if (wasNull()) {
            collection.add(null);
        } else {
            throw newParseError("Expecting '{' as collection start");
        }
        while (getNextToken() == 44) {
            if (getNextToken() == 123) {
                getNextToken();
                collection.add(readJsonObject.deserialize(this));
            } else if (wasNull()) {
                collection.add(null);
            } else {
                throw newParseError("Expecting '{' as object start within a collection");
            }
        }
        checkArrayEnd();
    }
}
