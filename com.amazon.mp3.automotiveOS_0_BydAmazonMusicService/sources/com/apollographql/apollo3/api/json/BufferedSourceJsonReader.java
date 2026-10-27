package com.apollographql.apollo3.api.json;

import com.amazon.music.find.utils.PageUriUtils;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.internal.JsonScope;
import com.apollographql.apollo3.exception.JsonDataException;
import com.apollographql.apollo3.exception.JsonEncodingException;
import java.io.EOFException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: BufferedSourceJsonReader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 K2\u00020\u0001:\u0001KB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0019\u001a\u00020\u0001H\u0016J\b\u0010\u001a\u001a\u00020\u0001H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\b\u0010\u001e\u001a\u00020\nH\u0002J\b\u0010\u001f\u001a\u00020\u0001H\u0016J\b\u0010 \u001a\u00020\u0001H\u0016J\b\u0010!\u001a\u00020\u0010H\u0002J\b\u0010\"\u001a\u00020\fH\u0016J\u0010\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\fH\u0016J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\nH\u0016J\b\u0010*\u001a\u00020\u0014H\u0016J\b\u0010+\u001a\u00020\u0010H\u0016J\u0010\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\fH\u0002J\n\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u000204H\u0002J\n\u00105\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u00106\u001a\u00020\u0010H\u0002J\b\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020\nH\u0002J\b\u0010:\u001a\u00020\nH\u0002J\u0010\u0010;\u001a\u00020\u001c2\u0006\u0010<\u001a\u00020\nH\u0002J\b\u0010=\u001a\u00020%H\u0002J\b\u0010>\u001a\u00020\u001cH\u0016J\u0016\u0010?\u001a\u00020\n2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00100AH\u0016J\u0010\u0010B\u001a\u00020\u001c2\u0006\u00103\u001a\u000204H\u0002J\u0010\u0010C\u001a\u00020\f2\u0006\u0010D\u001a\u00020\u0010H\u0002J\b\u0010E\u001a\u00020\u001cH\u0002J\b\u0010F\u001a\u00020\u001cH\u0002J\b\u0010G\u001a\u00020\u001cH\u0016J\u0010\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u0010H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/apollographql/apollo3/api/json/BufferedSourceJsonReader;", "Lcom/apollographql/apollo3/api/json/JsonReader;", PageUriUtils.SOURCE_QUERY_PARAM, "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "buffer", "Lokio/Buffer;", "indexStack", "", "indexStackSize", "", "lenient", "", "pathIndices", "pathNames", "", "", "[Ljava/lang/String;", "peeked", "peekedLong", "", "peekedNumberLength", "peekedString", "stack", "stackSize", "beginArray", "beginObject", "checkLenient", "", "close", "doPeek", "endArray", "endObject", "getPath", "hasNext", "isLiteral", "c", "", "nextBoolean", "nextDouble", "", "nextInt", "nextLong", "nextName", "nextNonWhitespace", "throwOnEof", "nextNull", "", "nextNumber", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "nextQuotedValue", "runTerminator", "Lokio/ByteString;", "nextString", "nextUnquotedValue", "peek", "Lcom/apollographql/apollo3/api/json/JsonReader$Token;", "peekKeyword", "peekNumber", "push", "newTop", "readEscapeCharacter", "rewind", "selectName", "names", "", "skipQuotedValue", "skipTo", "toFind", "skipToEndOfLine", "skipUnquotedValue", "skipValue", "syntaxError", "Lcom/apollographql/apollo3/exception/JsonEncodingException;", "message", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BufferedSourceJsonReader implements JsonReader {
    private static final ByteString DOUBLE_QUOTE_OR_SLASH;
    private static final ByteString LINEFEED_OR_CARRIAGE_RETURN;
    private static final ByteString SINGLE_QUOTE_OR_SLASH;
    private static final ByteString UNQUOTED_STRING_TERMINALS;
    private final Buffer buffer;
    private final int[] indexStack;
    private int indexStackSize;
    private boolean lenient;
    private final int[] pathIndices;
    private final String[] pathNames;
    private int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private final BufferedSource source;
    private final int[] stack;
    private int stackSize;

    public BufferedSourceJsonReader(BufferedSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.buffer = source.getBuffer();
        int[] iArr = new int[256];
        iArr[0] = 6;
        this.stack = iArr;
        this.stackSize = 1;
        this.pathNames = new String[256];
        this.pathIndices = new int[256];
        int[] iArr2 = new int[256];
        iArr2[0] = 0;
        this.indexStack = iArr2;
        this.indexStackSize = 1;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public JsonReader beginArray() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf == null ? doPeek() : numValueOf.intValue()) == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return this;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPath());
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public JsonReader endArray() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf == null ? doPeek() : numValueOf.intValue()) == 4) {
            int i = this.stackSize - 1;
            this.stackSize = i;
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            return this;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + getPath());
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public JsonReader beginObject() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf == null ? doPeek() : numValueOf.intValue()) == 1) {
            push(3);
            this.peeked = 0;
            int i = this.indexStackSize + 1;
            this.indexStackSize = i;
            this.indexStack[i - 1] = 0;
            return this;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPath());
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public JsonReader endObject() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf == null ? doPeek() : numValueOf.intValue()) == 2) {
            int i = this.stackSize - 1;
            this.stackSize = i;
            this.pathNames[i] = null;
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            this.indexStackSize--;
            return this;
        }
        throw new JsonDataException("Expected END_OBJECT but was " + peek() + " at path " + getPath());
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public boolean hasNext() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iDoPeek = numValueOf == null ? doPeek() : numValueOf.intValue();
        return (iDoPeek == 2 || iDoPeek == 4) ? false : true;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public JsonReader.Token peek() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        switch (numValueOf == null ? doPeek() : numValueOf.intValue()) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
                return JsonReader.Token.NAME;
            case 15:
                return JsonReader.Token.LONG;
            case 16:
                return JsonReader.Token.NUMBER;
            case 17:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    private final int doPeek() throws IOException {
        int[] iArr = this.stack;
        int i = this.stackSize;
        int i2 = iArr[i - 1];
        switch (i2) {
            case 1:
                iArr[i - 1] = 2;
                break;
            case 2:
                int iNextNonWhitespace = nextNonWhitespace(true);
                this.buffer.readByte();
                char c = (char) iNextNonWhitespace;
                if (c == ']') {
                    this.peeked = 4;
                    return 4;
                }
                if (c == ';') {
                    checkLenient();
                } else if (c != ',') {
                    throw syntaxError("Unterminated array");
                }
                break;
                break;
            case 3:
            case 5:
                iArr[i - 1] = 4;
                if (i2 == 5) {
                    int iNextNonWhitespace2 = nextNonWhitespace(true);
                    this.buffer.readByte();
                    char c2 = (char) iNextNonWhitespace2;
                    if (c2 == '}') {
                        this.peeked = 2;
                        return 2;
                    }
                    if (c2 == ';') {
                        checkLenient();
                    } else if (c2 != ',') {
                        throw syntaxError("Unterminated object");
                    }
                }
                char cNextNonWhitespace = (char) nextNonWhitespace(true);
                if (cNextNonWhitespace == '\"') {
                    this.buffer.readByte();
                    this.peeked = 13;
                    return 13;
                }
                if (cNextNonWhitespace == '\'') {
                    this.buffer.readByte();
                    checkLenient();
                    this.peeked = 12;
                    return 12;
                }
                if (cNextNonWhitespace == '}') {
                    if (i2 != 5) {
                        this.buffer.readByte();
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Expected name");
                }
                checkLenient();
                if (isLiteral(cNextNonWhitespace)) {
                    this.peeked = 14;
                    return 14;
                }
                throw syntaxError("Expected name");
            case 4:
                iArr[i - 1] = 5;
                int iNextNonWhitespace3 = nextNonWhitespace(true);
                this.buffer.readByte();
                char c3 = (char) iNextNonWhitespace3;
                if (c3 != ':') {
                    if (c3 == '=') {
                        checkLenient();
                        if (this.source.request(1L) && this.buffer.getByte(0L) == ((byte) 62)) {
                            this.buffer.readByte();
                        }
                    } else {
                        throw syntaxError("Expected ':'");
                    }
                }
                break;
            case 6:
                iArr[i - 1] = 7;
                break;
            case 7:
                if (nextNonWhitespace(false) == -1) {
                    this.peeked = 17;
                    return 17;
                }
                checkLenient();
                break;
                break;
            default:
                if (!(i2 != 8)) {
                    throw new IllegalStateException("JsonReader is closed".toString());
                }
                break;
        }
        char cNextNonWhitespace2 = (char) nextNonWhitespace(true);
        if (cNextNonWhitespace2 == ']') {
            if (i2 == 1) {
                this.buffer.readByte();
                this.peeked = 4;
                return 4;
            }
            if (i2 == 1 || i2 == 2) {
                checkLenient();
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        }
        if (cNextNonWhitespace2 == ';' || cNextNonWhitespace2 == ',') {
            if (i2 == 1 || i2 == 2) {
                checkLenient();
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        }
        if (cNextNonWhitespace2 == '\'') {
            checkLenient();
            this.buffer.readByte();
            this.peeked = 8;
            return 8;
        }
        if (cNextNonWhitespace2 == '\"') {
            this.buffer.readByte();
            this.peeked = 9;
            return 9;
        }
        if (cNextNonWhitespace2 == '[') {
            this.buffer.readByte();
            this.peeked = 3;
            return 3;
        }
        if (cNextNonWhitespace2 == '{') {
            this.buffer.readByte();
            this.peeked = 1;
            return 1;
        }
        int iPeekKeyword = peekKeyword();
        if (iPeekKeyword != 0) {
            return iPeekKeyword;
        }
        int iPeekNumber = peekNumber();
        if (iPeekNumber != 0) {
            return iPeekNumber;
        }
        if (!isLiteral((char) this.buffer.getByte(0L))) {
            throw syntaxError("Expected value");
        }
        checkLenient();
        this.peeked = 10;
        return 10;
    }

    private final int peekKeyword() throws EOFException {
        int i;
        String str;
        String str2;
        byte b = this.buffer.getByte(0L);
        int i2 = 1;
        if (b == ((byte) 116) || b == ((byte) 84)) {
            i = 5;
            str = "true";
            str2 = "TRUE";
        } else {
            if (b == ((byte) 102) || b == ((byte) 70)) {
                i = 6;
                str = "false";
                str2 = "FALSE";
            } else {
                if (!(b == ((byte) 110) || b == ((byte) 78))) {
                    return 0;
                }
                i = 7;
                str = "null";
                str2 = "NULL";
            }
        }
        int length = str.length();
        while (i2 < length) {
            int i3 = i2 + 1;
            long j = i2;
            if (!this.source.request(1 + j)) {
                return 0;
            }
            byte b2 = this.buffer.getByte(j);
            if (b2 != ((byte) str.charAt(i2)) && b2 != ((byte) str2.charAt(i2))) {
                return 0;
            }
            i2 = i3;
        }
        long j2 = length;
        if (this.source.request(1 + j2) && isLiteral((char) this.buffer.getByte(j2))) {
            return 0;
        }
        this.buffer.skip(j2);
        this.peeked = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:?, code lost:
    
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:?, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00ad, code lost:
    
        if (isLiteral(r1) != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00b0, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00b1, code lost:
    
        if (r6 != 2) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00b3, code lost:
    
        if (r10 == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00b9, code lost:
    
        if (r7 != Long.MIN_VALUE) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00bb, code lost:
    
        if (r9 == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00bd, code lost:
    
        if (r9 == false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00c0, code lost:
    
        r7 = -r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00c1, code lost:
    
        r18.peekedLong = r7;
        r18.buffer.skip(r12);
        r18.peeked = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00cd, code lost:
    
        if (r6 == 2) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00d0, code lost:
    
        if (r6 == 4) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00d3, code lost:
    
        if (r6 != 7) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00d5, code lost:
    
        r18.peekedNumberLength = r5;
        r18.peeked = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00db, code lost:
    
        return 16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int peekNumber() throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.BufferedSourceJsonReader.peekNumber():int");
    }

    private final boolean isLiteral(char c) {
        if (!((((c == '/' || c == '\\') || c == ';') || c == '#') || c == '=')) {
            return !(((((((((c == '{' || c == '}') || c == '[') || c == ']') || c == ':') || c == ',') || c == ' ') || c == '\t') || c == '\r') || c == '\n');
        }
        checkLenient();
        return false;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public String nextName() throws IOException {
        String strNextQuotedValue;
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        switch (numValueOf == null ? doPeek() : numValueOf.intValue()) {
            case 12:
                strNextQuotedValue = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
                break;
            case 13:
                strNextQuotedValue = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                break;
            case 14:
                strNextQuotedValue = nextUnquotedValue();
                break;
            default:
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = strNextQuotedValue;
        return strNextQuotedValue;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public String nextString() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        String strValueOf = null;
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iDoPeek = numValueOf == null ? doPeek() : numValueOf.intValue();
        if (iDoPeek == 15) {
            strValueOf = String.valueOf(this.peekedLong);
        } else if (iDoPeek != 16) {
            switch (iDoPeek) {
                case 8:
                    strValueOf = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
                    break;
                case 9:
                    strValueOf = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                    break;
                case 10:
                    strValueOf = nextUnquotedValue();
                    break;
                case 11:
                    String str = this.peekedString;
                    if (str != null) {
                        this.peekedString = null;
                        strValueOf = str;
                    }
                    break;
                default:
                    throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPath());
            }
        } else {
            strValueOf = this.buffer.readUtf8(this.peekedNumberLength);
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return strValueOf;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public boolean nextBoolean() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iDoPeek = numValueOf == null ? doPeek() : numValueOf.intValue();
        if (iDoPeek == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iDoPeek == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        }
        throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + getPath());
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public Void nextNull() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf == null ? doPeek() : numValueOf.intValue()) == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return null;
        }
        throw new JsonDataException("Expected null but was " + peek() + " at path " + getPath());
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public double nextDouble() {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iDoPeek = numValueOf == null ? doPeek() : numValueOf.intValue();
        if (iDoPeek == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (iDoPeek == 16) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else if (iDoPeek == 9) {
            this.peekedString = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (iDoPeek == 8) {
            this.peekedString = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (iDoPeek == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (iDoPeek != 11) {
            throw new JsonDataException("Expected a double but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            Intrinsics.checkNotNull(str);
            double d = Double.parseDouble(str);
            if (!this.lenient && (Double.isNaN(d) || Double.isInfinite(d))) {
                throw new JsonEncodingException("JSON forbids NaN and infinities: " + d + " at path " + getPath());
            }
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return d;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + ((Object) this.peekedString) + " at path " + getPath());
        }
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public long nextLong() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iDoPeek = numValueOf == null ? doPeek() : numValueOf.intValue();
        if (iDoPeek == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (iDoPeek == 16) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else if (iDoPeek == 9 || iDoPeek == 8) {
            String strNextQuotedValue = nextQuotedValue(iDoPeek == 9 ? DOUBLE_QUOTE_OR_SLASH : SINGLE_QUOTE_OR_SLASH);
            this.peekedString = strNextQuotedValue;
            try {
                Intrinsics.checkNotNull(strNextQuotedValue);
                long j = Long.parseLong(strNextQuotedValue);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException unused) {
            }
        } else if (iDoPeek != 11) {
            throw new JsonDataException("Expected a long but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            Intrinsics.checkNotNull(str);
            double d = Double.parseDouble(str);
            long j2 = (long) d;
            if (!(((double) j2) == d)) {
                throw new JsonDataException("Expected a long but was " + ((Object) this.peekedString) + " at path " + getPath());
            }
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr3[i3] = iArr3[i3] + 1;
            return j2;
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected a long but was " + ((Object) this.peekedString) + " at path " + getPath());
        }
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public JsonNumber nextNumber() throws IOException {
        String strNextString = nextString();
        Intrinsics.checkNotNull(strNextString);
        return new JsonNumber(strNextString);
    }

    private final String nextQuotedValue(ByteString runTerminator) throws IOException {
        StringBuilder sb = null;
        while (true) {
            long jIndexOfElement = this.source.indexOfElement(runTerminator);
            if (jIndexOfElement == -1) {
                throw syntaxError("Unterminated string");
            }
            if (this.buffer.getByte(jIndexOfElement) != ((byte) 92)) {
                if (sb == null) {
                    String utf8 = this.buffer.readUtf8(jIndexOfElement);
                    this.buffer.readByte();
                    return utf8;
                }
                sb.append(this.buffer.readUtf8(jIndexOfElement));
                this.buffer.readByte();
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "{\n        builder.append…uilder.toString()\n      }");
                return string;
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.buffer.readUtf8(jIndexOfElement));
            this.buffer.readByte();
            sb.append(readEscapeCharacter());
        }
    }

    private final String nextUnquotedValue() throws IOException {
        long jIndexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        return jIndexOfElement != -1 ? this.buffer.readUtf8(jIndexOfElement) : this.buffer.readUtf8();
    }

    private final void skipQuotedValue(ByteString runTerminator) throws IOException {
        while (true) {
            long jIndexOfElement = this.source.indexOfElement(runTerminator);
            if (jIndexOfElement == -1) {
                throw syntaxError("Unterminated string");
            }
            if (this.buffer.getByte(jIndexOfElement) == ((byte) 92)) {
                this.buffer.skip(jIndexOfElement + 1);
                readEscapeCharacter();
            } else {
                this.buffer.skip(jIndexOfElement + 1);
                return;
            }
        }
    }

    private final void skipUnquotedValue() throws IOException {
        long jIndexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        Buffer buffer = this.buffer;
        if (jIndexOfElement == -1) {
            jIndexOfElement = buffer.getSize();
        }
        buffer.skip(jIndexOfElement);
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public int nextInt() throws IOException {
        Integer numValueOf = Integer.valueOf(this.peeked);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iDoPeek = numValueOf == null ? doPeek() : numValueOf.intValue();
        if (iDoPeek == 15) {
            long j = this.peekedLong;
            int i = (int) j;
            if (j != i) {
                throw new JsonDataException("Expected an int but was " + this.peekedLong + " at path " + getPath());
            }
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        if (iDoPeek == 16) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else if (iDoPeek == 9 || iDoPeek == 8) {
            String strNextQuotedValue = nextQuotedValue(iDoPeek == 9 ? DOUBLE_QUOTE_OR_SLASH : SINGLE_QUOTE_OR_SLASH);
            this.peekedString = strNextQuotedValue;
            try {
                Intrinsics.checkNotNull(strNextQuotedValue);
                int i3 = Integer.parseInt(strNextQuotedValue);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException unused) {
            }
        } else if (iDoPeek != 11) {
            throw new JsonDataException("Expected an int but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            Intrinsics.checkNotNull(str);
            double d = Double.parseDouble(str);
            int i5 = (int) d;
            if (!(((double) i5) == d)) {
                throw new JsonDataException("Expected an int but was " + ((Object) this.peekedString) + " at path " + getPath());
            }
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i6 = this.stackSize - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return i5;
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + ((Object) this.peekedString) + " at path " + getPath());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public void skipValue() throws IOException {
        int i = 0;
        do {
            Integer numValueOf = Integer.valueOf(this.peeked);
            if (numValueOf.intValue() == 0) {
                numValueOf = null;
            }
            switch (numValueOf == null ? doPeek() : numValueOf.intValue()) {
                case 1:
                    push(3);
                    i++;
                    break;
                case 2:
                    this.stackSize--;
                    i--;
                    break;
                case 3:
                    push(1);
                    i++;
                    break;
                case 4:
                    this.stackSize--;
                    i--;
                    break;
                case 8:
                case 12:
                    skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
                    break;
                case 9:
                case 13:
                    skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                    break;
                case 10:
                case 14:
                    skipUnquotedValue();
                    break;
                case 16:
                    this.buffer.skip(this.peekedNumberLength);
                    break;
            }
            this.peeked = 0;
        } while (i != 0);
        int[] iArr = this.pathIndices;
        int i2 = this.stackSize;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.pathNames[i2 - 1] = "null";
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0053, code lost:
    
        skipValue();
     */
    @Override // com.apollographql.apollo3.api.json.JsonReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int selectName(java.util.List<java.lang.String> r7) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = "names"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            boolean r0 = r7.isEmpty()
            r1 = -1
            if (r0 == 0) goto Ld
            return r1
        Ld:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L7e
            java.lang.String r0 = r6.nextName()
            int[] r2 = r6.indexStack
            int r3 = r6.indexStackSize
            int r3 = r3 + (-1)
            r2 = r2[r3]
            java.lang.Object r3 = r7.get(r2)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
            r4 = 0
            if (r3 == 0) goto L47
            int[] r0 = r6.indexStack
            int r1 = r6.indexStackSize
            int r3 = r1 + (-1)
            int r5 = r2 + 1
            r0[r3] = r5
            int r1 = r1 + (-1)
            r0 = r0[r1]
            int r7 = r7.size()
            if (r0 != r7) goto L46
            int[] r7 = r6.indexStack
            int r0 = r6.indexStackSize
            int r0 = r0 + (-1)
            r7[r0] = r4
        L46:
            return r2
        L47:
            r3 = r2
        L48:
            int r3 = r3 + 1
            int r5 = r7.size()
            if (r3 != r5) goto L51
            r3 = r4
        L51:
            if (r3 != r2) goto L57
            r6.skipValue()
            goto Ld
        L57:
            java.lang.Object r5 = r7.get(r3)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r5 == 0) goto L48
            int[] r0 = r6.indexStack
            int r1 = r6.indexStackSize
            int r2 = r1 + (-1)
            int r5 = r3 + 1
            r0[r2] = r5
            int r1 = r1 + (-1)
            r0 = r0[r1]
            int r7 = r7.size()
            if (r0 != r7) goto L7d
            int[] r7 = r6.indexStack
            int r0 = r6.indexStackSize
            int r0 = r0 + (-1)
            r7[r0] = r4
        L7d:
            return r3
        L7e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.BufferedSourceJsonReader.selectName(java.util.List):int");
    }

    private final void push(int newTop) {
        int i = this.stackSize;
        int[] iArr = this.stack;
        if (i == iArr.length) {
            throw new JsonDataException(Intrinsics.stringPlus("Nesting too deep at ", getPath()));
        }
        this.stackSize = i + 1;
        iArr[i] = newTop;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0028, code lost:
    
        r9.buffer.skip(((long) r1) - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
    
        if (r2 != 47) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
    
        if (r9.source.request(2) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
    
        checkLenient();
        r3 = (char) r9.buffer.getByte(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004a, code lost:
    
        if (r3 != '*') goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
    
        r9.buffer.readByte();
        r9.buffer.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005c, code lost:
    
        if (skipTo("*\/") == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005e, code lost:
    
        r9.buffer.readByte();
        r9.buffer.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006f, code lost:
    
        throw syntaxError("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0070, code lost:
    
        if (r3 != '/') goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0072, code lost:
    
        r9.buffer.readByte();
        r9.buffer.readByte();
        skipToEndOfLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0080, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0083, code lost:
    
        if (r2 != 35) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
    
        checkLenient();
        skipToEndOfLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008d, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int nextNonWhitespace(boolean r10) throws java.io.IOException {
        /*
            r9 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            okio.BufferedSource r2 = r9.source
            long r3 = (long) r1
            r5 = 1
            long r7 = r3 + r5
            boolean r2 = r2.request(r7)
            if (r2 == 0) goto L8e
            okio.Buffer r2 = r9.buffer
            int r1 = r1 + 1
            byte r2 = r2.getByte(r3)
            r3 = 10
            if (r2 == r3) goto L2
            r3 = 32
            if (r2 == r3) goto L2
            r3 = 13
            if (r2 == r3) goto L2
            r3 = 9
            if (r2 != r3) goto L28
            goto L2
        L28:
            okio.Buffer r3 = r9.buffer
            long r7 = (long) r1
            long r7 = r7 - r5
            r3.skip(r7)
            r1 = 47
            if (r2 != r1) goto L81
            okio.BufferedSource r3 = r9.source
            r7 = 2
            boolean r3 = r3.request(r7)
            if (r3 != 0) goto L3e
            return r2
        L3e:
            r9.checkLenient()
            okio.Buffer r3 = r9.buffer
            byte r3 = r3.getByte(r5)
            char r3 = (char) r3
            r4 = 42
            if (r3 != r4) goto L70
            okio.Buffer r1 = r9.buffer
            r1.readByte()
            okio.Buffer r1 = r9.buffer
            r1.readByte()
        */
        //  java.lang.String r1 = "*/"
        /*
            boolean r1 = r9.skipTo(r1)
            if (r1 == 0) goto L69
            okio.Buffer r1 = r9.buffer
            r1.readByte()
            okio.Buffer r1 = r9.buffer
            r1.readByte()
            goto L1
        L69:
            java.lang.String r10 = "Unterminated comment"
            com.apollographql.apollo3.exception.JsonEncodingException r10 = r9.syntaxError(r10)
            throw r10
        L70:
            if (r3 != r1) goto L80
            okio.Buffer r1 = r9.buffer
            r1.readByte()
            okio.Buffer r1 = r9.buffer
            r1.readByte()
            r9.skipToEndOfLine()
            goto L1
        L80:
            return r2
        L81:
            r1 = 35
            if (r2 != r1) goto L8d
            r9.checkLenient()
            r9.skipToEndOfLine()
            goto L1
        L8d:
            return r2
        L8e:
            if (r10 != 0) goto L92
            r10 = -1
            return r10
        L92:
            java.io.EOFException r10 = new java.io.EOFException
            java.lang.String r0 = "End of input"
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.BufferedSourceJsonReader.nextNonWhitespace(boolean):int");
    }

    private final void checkLenient() {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private final void skipToEndOfLine() throws IOException {
        long jIndexOfElement = this.source.indexOfElement(LINEFEED_OR_CARRIAGE_RETURN);
        Buffer buffer = this.buffer;
        buffer.skip(jIndexOfElement != -1 ? jIndexOfElement + 1 : buffer.getSize());
    }

    private final boolean skipTo(String toFind) throws EOFException {
        while (true) {
            int i = 0;
            if (!this.source.request(toFind.length())) {
                return false;
            }
            int length = toFind.length();
            while (i < length) {
                int i2 = i + 1;
                if (this.buffer.getByte(i) != ((byte) toFind.charAt(i))) {
                    break;
                }
                i = i2;
            }
            return true;
            this.buffer.readByte();
        }
    }

    private final String getPath() {
        return JsonScope.INSTANCE.getPath(this.stackSize, this.stack, this.pathNames, this.pathIndices);
    }

    private final char readEscapeCharacter() throws EOFException {
        int i;
        if (!this.source.request(1L)) {
            throw syntaxError("Unterminated escape sequence");
        }
        char c = (char) this.buffer.readByte();
        if (c != 'u') {
            if (c == 't') {
                return '\t';
            }
            if (c == 'b') {
                return '\b';
            }
            if (c == 'n') {
                return '\n';
            }
            if (c == 'r') {
                return '\r';
            }
            if (c == 'f') {
                return '\f';
            }
            if ((((((c == '\n' || c == '\'') || c == '\"') || c == '\\') || c == '/') ? 1 : 0) == 0 && !this.lenient) {
                throw syntaxError(Intrinsics.stringPlus("Invalid escape sequence: \\", Character.valueOf(c)));
            }
            return c;
        }
        if (!this.source.request(4L)) {
            throw new EOFException(Intrinsics.stringPlus("Unterminated escape sequence at path ", getPath()));
        }
        char c2 = 0;
        while (i < 4) {
            byte b = this.buffer.getByte(i);
            char c3 = (char) (c2 << 4);
            byte b2 = (byte) 48;
            if (b < b2 || b > ((byte) 57)) {
                byte b3 = (byte) 97;
                if ((b < b3 || b > ((byte) 102)) && (b < (b3 = (byte) 65) || b > ((byte) 70))) {
                    throw syntaxError(Intrinsics.stringPlus("\\u", this.buffer.readUtf8(4L)));
                }
                i = (b - b3) + 10;
            } else {
                i = b - b2;
            }
            c2 = (char) (c3 + i);
            i++;
        }
        this.buffer.skip(4L);
        return c2;
    }

    @Override // com.apollographql.apollo3.api.json.JsonReader
    public void rewind() {
        throw new IllegalStateException("BufferedSourceJsonReader cannot rewind.".toString());
    }

    private final JsonEncodingException syntaxError(String message) {
        return new JsonEncodingException(message + " at path " + getPath());
    }

    static {
        ByteString.Companion companion = ByteString.INSTANCE;
        SINGLE_QUOTE_OR_SLASH = companion.encodeUtf8("'\\");
        DOUBLE_QUOTE_OR_SLASH = companion.encodeUtf8("\"\\");
        UNQUOTED_STRING_TERMINALS = companion.encodeUtf8("{}[]:, \n\t\r/\\;#=");
        LINEFEED_OR_CARRIAGE_RETURN = companion.encodeUtf8("\n\r");
    }
}
