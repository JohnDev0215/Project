package com.amazon.ion.impl;

import androidx.core.provider.FontsContractCompat;
import androidx.core.view.MotionEventCompat;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.UnexpectedEofException;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import com.amazon.ion.util.IonTextUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class IonReaderTextRawTokensX {
    private int _base64_prefetch_count;
    private int _base64_prefetch_stack;
    private long _line_count;
    private long _line_count_cached;
    private long _line_offset_cached;
    private long _line_starting_position;
    private UnifiedInputStreamX _stream;
    private boolean _unfinished_token;
    private static final Appendable NULL_APPENDABLE = new Appendable() { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.1
        @Override // java.lang.Appendable
        public Appendable append(char c) throws IOException {
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence) throws IOException {
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
            return this;
        }
    };
    static final int[] BASE64_CHAR_TO_BIN = Base64Encoder.Base64EncodingCharToInt;
    static final int BASE64_TERMINATOR_CHAR = Base64Encoder.Base64EncodingTerminator;
    private int _token = -1;
    private boolean _line_count_has_cached = false;

    private enum CommentStrategy {
        IGNORE { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy.1
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy
            boolean onComment(IonReaderTextRawTokensX ionReaderTextRawTokensX) throws IOException {
                int i = ionReaderTextRawTokensX.read_char();
                if (i == 42) {
                    ionReaderTextRawTokensX.skip_block_comment();
                    return true;
                }
                if (i != 47) {
                    ionReaderTextRawTokensX.unread_char(i);
                    return false;
                }
                ionReaderTextRawTokensX.skip_single_line_comment();
                return true;
            }
        },
        ERROR { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy.2
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy
            boolean onComment(IonReaderTextRawTokensX ionReaderTextRawTokensX) throws IOException {
                int i = ionReaderTextRawTokensX.read_char();
                if (i != 47 && i != 42) {
                    ionReaderTextRawTokensX.unread_char(i);
                    return false;
                }
                ionReaderTextRawTokensX.error("Illegal comment");
                return false;
            }
        },
        BREAK { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy.3
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy
            boolean onComment(IonReaderTextRawTokensX ionReaderTextRawTokensX) throws IOException {
                return false;
            }
        };

        abstract boolean onComment(IonReaderTextRawTokensX ionReaderTextRawTokensX) throws IOException;
    }

    private enum NumericState {
        START,
        UNDERSCORE,
        DIGIT
    }

    private enum Radix {
        BINARY { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.Radix.1
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            boolean isPrefix(int i) {
                return i == 98 || i == 66;
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            char normalizeDigit(char c) {
                return c;
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            boolean isValidDigit(int i) {
                return IonTokenConstsX.isBinaryDigit(i);
            }
        },
        DECIMAL { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.Radix.2
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            boolean isPrefix(int i) {
                return false;
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            char normalizeDigit(char c) {
                return c;
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            boolean isValidDigit(int i) {
                return IonTokenConstsX.isDigit(i);
            }
        },
        HEX { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.Radix.3
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            boolean isPrefix(int i) {
                return i == 120 || i == 88;
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            boolean isValidDigit(int i) {
                return IonTokenConstsX.isHexDigit(i);
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            char normalizeDigit(char c) {
                return Character.toLowerCase(c);
            }
        };

        static final /* synthetic */ boolean $assertionsDisabled = false;

        void assertPrefix(int i) {
        }

        abstract boolean isPrefix(int i);

        abstract boolean isValidDigit(int i);

        abstract char normalizeDigit(char c);
    }

    private static final int decode_base64_byte1(int i, int i2, int i3, int i4) {
        return ((i << 2) & 252) | ((i2 >> 4) & 3);
    }

    private static final int decode_base64_byte2(int i, int i2, int i3, int i4) {
        return (((i2 << 4) & 240) | ((i3 >> 2) & 15)) & 255;
    }

    private static final int decode_base64_byte3(int i, int i2, int i3, int i4) {
        return (((i3 & 3) << 6) | (i4 & 63)) & 255;
    }

    private static final int decode_base64_length(int i, int i2, int i3, int i4) {
        if (i4 != 128) {
            return 3;
        }
        return i3 != 128 ? 2 : 1;
    }

    public IonReaderTextRawTokensX(UnifiedInputStreamX unifiedInputStreamX, long j, long j2) {
        this._stream = unifiedInputStreamX;
        this._line_count = j;
        this._line_starting_position = unifiedInputStreamX.getPosition() - j2;
    }

    public void close() throws IOException {
        this._stream.close();
    }

    public int getToken() {
        return this._token;
    }

    public long getLineNumber() {
        return this._line_count;
    }

    public long getLineOffset() {
        return this._stream.getPosition() - this._line_starting_position;
    }

    public final boolean isBufferedInput() {
        return !this._stream._is_stream;
    }

    protected String input_position() {
        return " at line " + getLineNumber() + " offset " + getLineOffset();
    }

    public final boolean isUnfinishedToken() {
        return this._unfinished_token;
    }

    public final void tokenIsFinished() {
        this._unfinished_token = false;
        this._base64_prefetch_count = 0;
    }

    protected final int read_char() throws IOException {
        int i = this._stream.read();
        return (i == 13 || i == 10) ? line_count(i) : i;
    }

    protected final int read_string_char(ProhibitedCharacters prohibitedCharacters) throws IOException {
        int i = this._stream.read();
        if (prohibitedCharacters.includes(i)) {
            error("invalid character [" + IonTextUtils.printCodePointAsString(i) + "]");
        }
        return (i == 13 || i == 10 || i == 92) ? line_count(i) : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unread_char(int i) {
        if (i >= 0) {
            this._stream.unread(i);
            return;
        }
        if (i != -1) {
            switch (i) {
                case -9:
                    this._stream.unread(10);
                    this._stream.unread(13);
                    this._stream.unread(92);
                    break;
                case -8:
                    this._stream.unread(13);
                    this._stream.unread(92);
                    break;
                case -7:
                    this._stream.unread(10);
                    this._stream.unread(92);
                    break;
                case -6:
                    line_count_unread(i);
                    this._stream.unread(10);
                    this._stream.unread(13);
                    break;
                case -5:
                    line_count_unread(i);
                    this._stream.unread(13);
                    break;
                case FontsContractCompat.FontRequestCallback.FAIL_REASON_SECURITY_VIOLATION /* -4 */:
                    line_count_unread(i);
                    this._stream.unread(10);
                    break;
            }
            return;
        }
        this._stream.unread(-1);
    }

    private final int line_count_unread(int i) {
        if (this._line_count_has_cached) {
            this._line_count = this._line_count_cached;
            this._line_starting_position = this._line_offset_cached;
            this._line_count_has_cached = false;
        }
        return i;
    }

    private final int line_count(int i) throws IOException {
        int i2;
        if (i == 10) {
            i2 = -4;
        } else if (i == 13) {
            int i3 = this._stream.read();
            if (i3 == 10) {
                i2 = -6;
            } else {
                unread_char(i3);
                i2 = -5;
            }
        } else if (i == 92) {
            int i4 = this._stream.read();
            if (i4 == 10) {
                i2 = -7;
            } else if (i4 == 13) {
                int i5 = this._stream.read();
                if (i5 != 10) {
                    unread_char(i5);
                    i2 = -8;
                } else {
                    i2 = -9;
                }
            } else {
                unread_char(i4);
                return i;
            }
        } else {
            throw new IllegalStateException();
        }
        long j = this._line_count;
        this._line_count_cached = j;
        this._line_offset_cached = this._line_starting_position;
        this._line_count_has_cached = true;
        this._line_count = j + 1;
        this._line_starting_position = this._stream.getPosition() - 1;
        return i2;
    }

    public final boolean skipDoubleColon() throws IOException {
        int iSkip_over_whitespace = skip_over_whitespace();
        if (iSkip_over_whitespace != 58) {
            unread_char(iSkip_over_whitespace);
            return false;
        }
        int i = read_char();
        if (i == 58) {
            return true;
        }
        unread_char(i);
        unread_char(58);
        return false;
    }

    public final int peekNullTypeSymbol() throws IOException {
        int i = read_char();
        int i2 = 0;
        if (i != 46) {
            unread_char(i);
            return 0;
        }
        int[] iArr = new int[IonTokenConstsX.TN_MAX_NAME_LENGTH + 1];
        int iTypeNamePossibilityMask = 8191;
        while (true) {
            if (i2 >= IonTokenConstsX.TN_MAX_NAME_LENGTH + 1) {
                break;
            }
            i = read_char();
            int i3 = i2 + 1;
            iArr[i2] = i;
            int iTypeNameLetterIdx = IonTokenConstsX.typeNameLetterIdx(i);
            if (iTypeNameLetterIdx < 1) {
                if (!IonTokenConstsX.isValidTerminatingCharForInf(i)) {
                    return peekNullTypeSymbolUndo(iArr, i3);
                }
                i2 = i3;
            } else {
                iTypeNamePossibilityMask &= IonTokenConstsX.typeNamePossibilityMask(i3 - 1, iTypeNameLetterIdx);
                if (iTypeNamePossibilityMask == 0) {
                    return peekNullTypeSymbolUndo(iArr, i3);
                }
                i2 = i3;
            }
        }
        int iTypeNameKeyWordFromMask = IonTokenConstsX.typeNameKeyWordFromMask(iTypeNamePossibilityMask, i2 - 1);
        if (iTypeNameKeyWordFromMask == -1) {
            peekNullTypeSymbolUndo(iArr, i2);
        } else {
            unread_char(i);
        }
        return iTypeNameKeyWordFromMask;
    }

    private final int peekNullTypeSymbolUndo(int[] iArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            str = str + ((char) iArr[i2]);
        }
        error("invalid type name on a typed null value");
        return -1;
    }

    public final int peekLobStartPunctuation() throws IOException {
        int iSkip_over_lob_whitespace = skip_over_lob_whitespace();
        if (iSkip_over_lob_whitespace == 34) {
            return 12;
        }
        if (iSkip_over_lob_whitespace != 39) {
            unread_char(iSkip_over_lob_whitespace);
            return -1;
        }
        int i = read_char();
        if (i != 39) {
            unread_char(i);
            unread_char(39);
            return -1;
        }
        int i2 = read_char();
        if (i2 == 39) {
            return 13;
        }
        unread_char(i2);
        unread_char(39);
        unread_char(39);
        return -1;
    }

    protected final void skip_clob_close_punctuation() throws IOException {
        int iSkip_over_clob_whitespace = skip_over_clob_whitespace();
        if (iSkip_over_clob_whitespace == 125) {
            int i = read_char();
            if (i == 125) {
                return;
            }
            unread_char(i);
            iSkip_over_clob_whitespace = 125;
        }
        unread_char(iSkip_over_clob_whitespace);
        error("invalid closing puctuation for CLOB");
    }

    protected final void finish_token(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        if (this._unfinished_token) {
            unread_char(skip_to_end(savePoint));
            this._unfinished_token = false;
        }
    }

    private final int skip_to_end(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int iSkip_over_number;
        int i = this._token;
        if (i == 1) {
            iSkip_over_number = skip_over_number(savePoint);
        } else if (i == 2) {
            iSkip_over_number = skip_over_int(savePoint);
        } else if (i == 3) {
            iSkip_over_number = skipOverRadix(savePoint, Radix.HEX);
        } else if (i == 4) {
            iSkip_over_number = skip_over_decimal(savePoint);
        } else if (i == 5) {
            iSkip_over_number = skip_over_float(savePoint);
        } else if (i == 18) {
            skip_over_sexp();
            iSkip_over_number = read_char();
        } else if (i == 20) {
            skip_over_struct();
            iSkip_over_number = read_char();
        } else if (i == 22) {
            skip_over_list();
            iSkip_over_number = read_char();
        } else if (i == 24) {
            skip_over_blob(savePoint);
            iSkip_over_number = read_char();
        } else if (i == 26) {
            iSkip_over_number = skipOverRadix(savePoint, Radix.BINARY);
        } else {
            switch (i) {
                case 8:
                    iSkip_over_number = skip_over_timestamp(savePoint);
                    break;
                case 9:
                    iSkip_over_number = skip_over_symbol_identifier(savePoint);
                    break;
                case 10:
                    iSkip_over_number = skip_single_quoted_string(savePoint);
                    break;
                case 11:
                    iSkip_over_number = skip_over_symbol_operator(savePoint);
                    break;
                case 12:
                    skip_double_quoted_string_helper();
                    iSkip_over_number = skip_over_whitespace();
                    break;
                case 13:
                    skip_triple_quoted_string(savePoint);
                    iSkip_over_number = skip_over_whitespace();
                    break;
                default:
                    iSkip_over_number = -1;
                    error("token " + IonTokenConstsX.getTokenName(this._token) + " unexpectedly encounterd as \"unfinished\"");
                    break;
            }
        }
        if (IonTokenConstsX.isWhitespace(iSkip_over_number)) {
            iSkip_over_number = skip_over_whitespace();
        }
        this._unfinished_token = false;
        return iSkip_over_number;
    }

    public final long getStartingOffset() throws IOException {
        int iSkip_over_whitespace;
        if (this._unfinished_token) {
            iSkip_over_whitespace = skip_to_end(null);
        } else {
            iSkip_over_whitespace = skip_over_whitespace();
        }
        unread_char(iSkip_over_whitespace);
        return this._stream.getPosition();
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int nextToken() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 492
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.nextToken():int");
    }

    private final int next_token_finish(int i, boolean z) {
        this._token = i;
        this._unfinished_token = z;
        return i;
    }

    private final int skip_over_whitespace() throws IOException {
        return skip_over_whitespace(CommentStrategy.IGNORE);
    }

    private final int skip_over_whitespace(CommentStrategy commentStrategy) throws IOException {
        skip_whitespace(commentStrategy);
        return read_char();
    }

    private final int skip_over_lob_whitespace() throws IOException {
        return skip_over_blob_whitespace();
    }

    private final int skip_over_blob_whitespace() throws IOException {
        return skip_over_whitespace(CommentStrategy.BREAK);
    }

    private final int skip_over_clob_whitespace() throws IOException {
        return skip_over_whitespace(CommentStrategy.ERROR);
    }

    protected final boolean skip_whitespace() throws IOException {
        return skip_whitespace(CommentStrategy.IGNORE);
    }

    private final boolean skip_whitespace(CommentStrategy commentStrategy) throws IOException {
        int i;
        boolean z = false;
        while (true) {
            i = read_char();
            if (i != 9 && i != 32) {
                if (i != 47) {
                    switch (i) {
                    }
                } else if (!commentStrategy.onComment(this)) {
                }
            }
            z = true;
        }
        unread_char(i);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void skip_single_line_comment() throws IOException {
        while (true) {
            int i = read_char();
            if (i != -1) {
                switch (i) {
                    case -9:
                    case -8:
                    case -7:
                    case -6:
                    case -5:
                    case FontsContractCompat.FontRequestCallback.FAIL_REASON_SECURITY_VIOLATION /* -4 */:
                        return;
                }
            }
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void skip_block_comment() throws IOException {
        int i;
        while (true) {
            int i2 = read_char();
            if (i2 == -1) {
                bad_token_start(i2);
            } else if (i2 != 42) {
                continue;
            } else {
                do {
                    i = read_char();
                    if (i == 47) {
                        return;
                    }
                } while (i == 42);
            }
        }
    }

    private final boolean is_2_single_quotes_helper() throws IOException {
        int i = read_char();
        if (i != 39) {
            unread_char(i);
            return false;
        }
        int i2 = read_char();
        if (i2 == 39) {
            return true;
        }
        unread_char(i2);
        unread_char(39);
        return false;
    }

    private final boolean peek_inf_helper(int i) throws IOException {
        if (i != 43 && i != 45) {
            return false;
        }
        int i2 = read_char();
        if (i2 == 105) {
            int i3 = read_char();
            if (i3 == 110) {
                int i4 = read_char();
                if (i4 == 102) {
                    int i5 = read_char();
                    if (is_value_terminating_character(i5)) {
                        unread_char(i5);
                        return true;
                    }
                    unread_char(i5);
                    i4 = 102;
                }
                unread_char(i4);
                i3 = 110;
            }
            unread_char(i3);
            i2 = 105;
        }
        unread_char(i2);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int scan_for_numeric_type(int r8) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 6
            int[] r0 = new int[r0]
            boolean r1 = com.amazon.ion.impl.IonTokenConstsX.isDigit(r8)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L1c
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
            r1[r2] = r4
            java.lang.String r4 = "Expected digit, got U+%04X"
            java.lang.String r1 = java.lang.String.format(r4, r1)
            r7.error(r1)
        L1c:
            int r1 = r7.read_char()
            r0[r2] = r1
            r2 = 48
            r4 = 2
            r5 = 3
            r6 = 4
            if (r8 != r2) goto L5e
            r8 = 46
            if (r1 == r8) goto L5e
            r8 = 66
            if (r1 == r8) goto L5b
            r8 = 88
            if (r1 == r8) goto L59
            r8 = 98
            if (r1 == r8) goto L5b
            r8 = 120(0x78, float:1.68E-43)
            if (r1 == r8) goto L59
            r8 = 68
            if (r1 == r8) goto L57
            r8 = 69
            if (r1 == r8) goto L55
            r8 = 100
            if (r1 == r8) goto L57
            r8 = 101(0x65, float:1.42E-43)
            if (r1 == r8) goto L55
            boolean r8 = r7.is_value_terminating_character(r1)
            if (r8 == 0) goto L5e
            r8 = r4
            goto L5f
        L55:
            r8 = 5
            goto L5f
        L57:
            r8 = r6
            goto L5f
        L59:
            r8 = r5
            goto L5f
        L5b:
            r8 = 26
            goto L5f
        L5e:
            r8 = r3
        L5f:
            if (r8 != r3) goto L94
            boolean r1 = com.amazon.ion.impl.IonTokenConstsX.isDigit(r1)
            if (r1 == 0) goto L94
            int r1 = r7.read_char()
            r0[r3] = r1
            boolean r1 = com.amazon.ion.impl.IonTokenConstsX.isDigit(r1)
            if (r1 == 0) goto L93
            int r1 = r7.read_char()
            r0[r4] = r1
            boolean r1 = com.amazon.ion.impl.IonTokenConstsX.isDigit(r1)
            if (r1 == 0) goto L91
            int r1 = r7.read_char()
            r0[r5] = r1
            r2 = 45
            if (r1 == r2) goto L8d
            r2 = 84
            if (r1 != r2) goto L8f
        L8d:
            r8 = 8
        L8f:
            r3 = r6
            goto L94
        L91:
            r3 = r5
            goto L94
        L93:
            r3 = r4
        L94:
            int r3 = r3 + (-1)
            r1 = r0[r3]
            r7.unread_char(r1)
            if (r3 > 0) goto L94
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.scan_for_numeric_type(int):int");
    }

    private final boolean is_value_terminating_character(int i) throws IOException {
        if (i == 47) {
            int i2 = read_char();
            unread_char(i2);
            return i2 == 47 || i2 == 42;
        }
        switch (i) {
            case -9:
            case -8:
            case -7:
            case -6:
            case -5:
            case FontsContractCompat.FontRequestCallback.FAIL_REASON_SECURITY_VIOLATION /* -4 */:
                return true;
            default:
                return IonTextUtils.isNumericStop(i);
        }
    }

    private final int scan_negative_for_numeric_type(int i) throws IOException {
        int i2 = read_char();
        int iScan_for_numeric_type = scan_for_numeric_type(i2);
        if (iScan_for_numeric_type == 8) {
            bad_token(i2);
        }
        unread_char(i2);
        return iScan_for_numeric_type;
    }

    protected void load_raw_characters(StringBuilder sb) throws IOException {
        read_char();
        while (true) {
            int iLowSurrogate = read_char();
            if (iLowSurrogate != -9 && iLowSurrogate != -8 && iLowSurrogate != -7) {
                if (iLowSurrogate == -1) {
                    return;
                }
                if (!IonTokenConstsX.is7bitValue(iLowSurrogate)) {
                    iLowSurrogate = read_large_char_sequence(iLowSurrogate);
                }
                if (IonUTF8.needsSurrogateEncoding(iLowSurrogate)) {
                    sb.append(IonUTF8.highSurrogate(iLowSurrogate));
                    iLowSurrogate = IonUTF8.lowSurrogate(iLowSurrogate);
                }
                sb.append((char) iLowSurrogate);
            }
        }
    }

    protected void skip_over_struct() throws IOException {
        skip_over_container(125);
    }

    protected void skip_over_list() throws IOException {
        skip_over_container(93);
    }

    protected void skip_over_sexp() throws IOException {
        skip_over_container(41);
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0000, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void skip_over_container(int r6) throws java.io.IOException {
        /*
            r5 = this;
        L0:
            int r0 = r5.skip_over_whitespace()
            r1 = -1
            if (r0 == r1) goto L74
            r1 = 34
            if (r0 == r1) goto L70
            r2 = 91
            r3 = 93
            if (r0 == r2) goto L6c
            if (r0 == r3) goto L77
            r2 = 123(0x7b, float:1.72E-43)
            r3 = 0
            r4 = 125(0x7d, float:1.75E-43)
            if (r0 == r2) goto L38
            if (r0 == r4) goto L77
            switch(r0) {
                case 39: goto L26;
                case 40: goto L20;
                case 41: goto L77;
                default: goto L1f;
            }
        L1f:
            goto L0
        L20:
            r0 = 41
            r5.skip_over_container(r0)
            goto L0
        L26:
            boolean r0 = r5.is_2_single_quotes_helper()
            if (r0 == 0) goto L30
            r5.skip_triple_quoted_string(r3)
            goto L0
        L30:
            int r0 = r5.skip_single_quoted_string(r3)
            r5.unread_char(r0)
            goto L0
        L38:
            int r0 = r5.read_char()
            if (r0 != r2) goto L62
            int r0 = r5.skip_over_lob_whitespace()
            if (r0 != r1) goto L47
            r0 = 12
            goto L5e
        L47:
            r1 = 39
            if (r0 != r1) goto L59
            boolean r0 = r5.is_2_single_quotes_helper()
            if (r0 != 0) goto L56
            java.lang.String r0 = "invalid single quote in lob content"
            r5.error(r0)
        L56:
            r0 = 13
            goto L5e
        L59:
            r5.unread_char(r0)
            r0 = 24
        L5e:
            r5.skip_over_lob(r0, r3)
            goto L0
        L62:
            if (r0 != r4) goto L65
            goto L0
        L65:
            r5.unread_char(r0)
            r5.skip_over_container(r4)
            goto L0
        L6c:
            r5.skip_over_container(r3)
            goto L0
        L70:
            r5.skip_double_quoted_string_helper()
            goto L0
        L74:
            r5.unexpected_eof()
        L77:
            if (r0 != r6) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.skip_over_container(int):void");
    }

    private int skip_over_number(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int i = read_char();
        if (i == 45) {
            i = read_char();
        }
        int iSkip_over_digits = skip_over_digits(i);
        if (iSkip_over_digits == 46) {
            iSkip_over_digits = skip_over_digits(read_char());
        }
        if (iSkip_over_digits == 100 || iSkip_over_digits == 68 || iSkip_over_digits == 101 || iSkip_over_digits == 69) {
            int i2 = read_char();
            if (i2 == 45 || i2 == 43) {
                i2 = read_char();
            }
            iSkip_over_digits = skip_over_digits(i2);
        }
        if (!is_value_terminating_character(iSkip_over_digits)) {
            bad_token(iSkip_over_digits);
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return iSkip_over_digits;
    }

    private int skip_over_int(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int i = read_char();
        if (i == 45) {
            i = read_char();
        }
        int iSkip_over_digits = skip_over_digits(i);
        if (!is_value_terminating_character(iSkip_over_digits)) {
            bad_token(iSkip_over_digits);
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return iSkip_over_digits;
    }

    private int skip_over_digits(int i) throws IOException {
        while (IonTokenConstsX.isDigit(i)) {
            i = read_char();
        }
        return i;
    }

    private int skipOverRadix(UnifiedSavePointManagerX.SavePoint savePoint, Radix radix) throws IOException {
        if (read_char() == 45) {
            read_char();
        }
        radix.assertPrefix(read_char());
        int numeric = readNumeric(NULL_APPENDABLE, radix);
        if (!is_value_terminating_character(numeric)) {
            bad_token(numeric);
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return numeric;
    }

    private int skip_over_decimal(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        return skip_over_number(savePoint);
    }

    private int skip_over_float(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        return skip_over_number(savePoint);
    }

    private int skip_over_timestamp(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int iSkip_timestamp_past_digits = skip_timestamp_past_digits(4);
        if (iSkip_timestamp_past_digits == 84) {
            if (savePoint != null) {
                savePoint.markEnd(0);
            }
            return skip_over_whitespace();
        }
        if (iSkip_timestamp_past_digits != 45) {
            error("invalid timestamp encountered");
        }
        int iSkip_timestamp_past_digits2 = skip_timestamp_past_digits(2);
        if (iSkip_timestamp_past_digits2 == 84) {
            if (savePoint != null) {
                savePoint.markEnd(0);
            }
            return skip_over_whitespace();
        }
        skip_timestamp_validate(iSkip_timestamp_past_digits2, 45);
        int iSkip_timestamp_past_digits3 = skip_timestamp_past_digits(2);
        if (iSkip_timestamp_past_digits3 != 84) {
            return skip_timestamp_finish(iSkip_timestamp_past_digits3, savePoint);
        }
        int i = read_char();
        if (!IonTokenConstsX.isDigit(i)) {
            return skip_timestamp_finish(skip_optional_timestamp_offset(i), savePoint);
        }
        int iSkip_timestamp_past_digits4 = skip_timestamp_past_digits(1);
        if (iSkip_timestamp_past_digits4 != 58) {
            bad_token(iSkip_timestamp_past_digits4);
        }
        int iSkip_timestamp_past_digits5 = skip_timestamp_past_digits(2);
        if (iSkip_timestamp_past_digits5 != 58) {
            return skip_timestamp_offset_or_z(iSkip_timestamp_past_digits5, savePoint);
        }
        int iSkip_timestamp_past_digits6 = skip_timestamp_past_digits(2);
        if (iSkip_timestamp_past_digits6 != 46) {
            return skip_timestamp_offset_or_z(iSkip_timestamp_past_digits6, savePoint);
        }
        int iSkip_over_digits = read_char();
        if (IonTokenConstsX.isDigit(iSkip_over_digits)) {
            iSkip_over_digits = skip_over_digits(iSkip_over_digits);
        }
        return skip_timestamp_offset_or_z(iSkip_over_digits, savePoint);
    }

    private int skip_timestamp_finish(int i, UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        if (!is_value_terminating_character(i)) {
            bad_token(i);
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return i;
    }

    private int skip_optional_timestamp_offset(int i) throws IOException {
        if (i != 45 && i != 43) {
            return i;
        }
        int iSkip_timestamp_past_digits = skip_timestamp_past_digits(2);
        if (iSkip_timestamp_past_digits != 58) {
            bad_token(iSkip_timestamp_past_digits);
        }
        return skip_timestamp_past_digits(2);
    }

    private int skip_timestamp_offset_or_z(int i, UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        if (i == 45 || i == 43) {
            int iSkip_timestamp_past_digits = skip_timestamp_past_digits(2);
            if (iSkip_timestamp_past_digits != 58) {
                bad_token(iSkip_timestamp_past_digits);
            }
            i = skip_timestamp_past_digits(2);
        } else if (i == 90 || i == 122) {
            i = read_char();
        } else {
            bad_token(i);
        }
        return skip_timestamp_finish(i, savePoint);
    }

    private final void skip_timestamp_validate(int i, int i2) {
        if (i != i2) {
            error("invalid character '" + ((char) i) + "' encountered in timestamp (when '" + ((char) i2) + "' was expected");
        }
    }

    private final int skip_timestamp_past_digits(int i) throws IOException {
        return skip_timestamp_past_digits(i, i);
    }

    private final int skip_timestamp_past_digits(int i, int i2) throws IOException {
        while (i > 0) {
            int i3 = read_char();
            if (!IonTokenConstsX.isDigit(i3)) {
                error("invalid character '" + ((char) i3) + "' encountered in timestamp");
            }
            i--;
            i2--;
        }
        while (i2 > 0) {
            int i4 = read_char();
            if (!IonTokenConstsX.isDigit(i4)) {
                return i4;
            }
            i2--;
        }
        return read_char();
    }

    protected IonType load_number(StringBuilder sb) throws IOException {
        int i;
        int i2 = read_char();
        boolean z = i2 == 45 || i2 == 43;
        if (z) {
            sb.append((char) i2);
            i2 = read_char();
        }
        if (!IonTokenConstsX.isDigit(i2)) {
            bad_token(i2);
        }
        boolean z2 = i2 == 48;
        if (z2) {
            int i3 = read_char();
            Radix radix = Radix.HEX;
            if (radix.isPrefix(i3)) {
                sb.append((char) i2);
                return load_finish_number(sb, loadRadixValue(sb, z, i3, radix), 3);
            }
            Radix radix2 = Radix.BINARY;
            if (radix2.isPrefix(i3)) {
                sb.append((char) i2);
                return load_finish_number(sb, loadRadixValue(sb, z, i3, radix2), 26);
            }
            unread_char(i3);
        }
        int iLoad_digits = load_digits(sb, i2);
        int i4 = 4;
        if (iLoad_digits == 45 || iLoad_digits == 84) {
            if (z) {
                error("Numeric value followed by invalid character: " + ((Object) sb) + ((char) iLoad_digits));
            }
            if (sb.length() != 4) {
                error("Numeric value followed by invalid character: " + ((Object) sb) + ((char) iLoad_digits));
            }
            return load_timestamp(sb, iLoad_digits);
        }
        if (z2) {
            int length = sb.length();
            if (z) {
                length--;
            }
            if (length != 1) {
                error("Invalid leading zero in number: " + ((Object) sb));
            }
        }
        if (iLoad_digits == 46) {
            sb.append((char) iLoad_digits);
            iLoad_digits = load_digits(sb, read_char());
            i = 4;
        } else {
            i = 2;
        }
        if (iLoad_digits == 101 || iLoad_digits == 69) {
            i4 = 5;
            sb.append((char) iLoad_digits);
            iLoad_digits = load_exponent(sb);
        } else if (iLoad_digits == 100 || iLoad_digits == 68) {
            sb.append((char) iLoad_digits);
            iLoad_digits = load_exponent(sb);
        } else {
            i4 = i;
        }
        return load_finish_number(sb, iLoad_digits, i4);
    }

    private final IonType load_finish_number(CharSequence charSequence, int i, int i2) throws IOException {
        if (!is_value_terminating_character(i)) {
            error("Numeric value followed by invalid character: " + ((Object) charSequence) + ((char) i));
        }
        unread_char(i);
        return IonTokenConstsX.ion_type_of_scalar(i2);
    }

    private final int load_exponent(StringBuilder sb) throws IOException {
        int i = read_char();
        if (i == 45 || i == 43) {
            sb.append((char) i);
            i = read_char();
        }
        int iLoad_digits = load_digits(sb, i);
        if (iLoad_digits != 46) {
            return iLoad_digits;
        }
        sb.append((char) iLoad_digits);
        return load_digits(sb, read_char());
    }

    private final int load_digits(StringBuilder sb, int i) throws IOException {
        if (!IonTokenConstsX.isDigit(i)) {
            return i;
        }
        sb.append((char) i);
        return readNumeric(sb, Radix.DECIMAL, NumericState.DIGIT);
    }

    private final void load_fixed_digits(StringBuilder sb, int i) throws IOException {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        while (i > 4) {
                            int i2 = read_char();
                            if (!IonTokenConstsX.isDigit(i2)) {
                                bad_token(i2);
                            }
                            sb.append((char) i2);
                            i--;
                        }
                    }
                    int i3 = read_char();
                    if (!IonTokenConstsX.isDigit(i3)) {
                        bad_token(i3);
                    }
                    sb.append((char) i3);
                }
                int i4 = read_char();
                if (!IonTokenConstsX.isDigit(i4)) {
                    bad_token(i4);
                }
                sb.append((char) i4);
            }
            int i5 = read_char();
            if (!IonTokenConstsX.isDigit(i5)) {
                bad_token(i5);
            }
            sb.append((char) i5);
        }
        int i6 = read_char();
        if (!IonTokenConstsX.isDigit(i6)) {
            bad_token(i6);
        }
        sb.append((char) i6);
    }

    private final IonType load_timestamp(StringBuilder sb, int i) throws IOException {
        sb.append((char) i);
        if (i == 84) {
            return load_finish_number(sb, read_char(), 8);
        }
        load_fixed_digits(sb, 2);
        int i2 = read_char();
        if (i2 == 84) {
            sb.append((char) i2);
            return load_finish_number(sb, read_char(), 8);
        }
        if (i2 != 45) {
            bad_token(i2);
        }
        sb.append((char) i2);
        load_fixed_digits(sb, 2);
        int i3 = read_char();
        if (i3 != 84) {
            return load_finish_number(sb, i3, 8);
        }
        sb.append((char) i3);
        int i4 = read_char();
        if (!IonTokenConstsX.isDigit(i4)) {
            return load_finish_number(sb, i4, 8);
        }
        sb.append((char) i4);
        load_fixed_digits(sb, 1);
        int i5 = read_char();
        if (i5 != 58) {
            bad_token(i5);
        }
        sb.append((char) i5);
        load_fixed_digits(sb, 2);
        int iLoad_digits = read_char();
        if (iLoad_digits == 58) {
            sb.append((char) iLoad_digits);
            load_fixed_digits(sb, 2);
            iLoad_digits = read_char();
            if (iLoad_digits == 46) {
                sb.append((char) iLoad_digits);
                int i6 = read_char();
                if (!IonTokenConstsX.isDigit(i6)) {
                    expected_but_found("at least one digit after timestamp's decimal point", i6);
                }
                iLoad_digits = load_digits(sb, i6);
            }
        }
        if (iLoad_digits == 122 || iLoad_digits == 90) {
            sb.append((char) iLoad_digits);
            iLoad_digits = read_char();
        } else if (iLoad_digits == 43 || iLoad_digits == 45) {
            sb.append((char) iLoad_digits);
            load_fixed_digits(sb, 2);
            int i7 = read_char();
            if (i7 != 58) {
                bad_token(i7);
            }
            sb.append((char) i7);
            load_fixed_digits(sb, 2);
            iLoad_digits = read_char();
        } else {
            bad_token(iLoad_digits);
        }
        return load_finish_number(sb, iLoad_digits, 8);
    }

    private final int loadRadixValue(StringBuilder sb, boolean z, int i, Radix radix) throws IOException {
        radix.assertPrefix(i);
        sb.append((char) i);
        return readNumeric(sb, radix);
    }

    private final int skip_over_symbol_identifier(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int i = read_char();
        while (IonTokenConstsX.isValidSymbolCharacter(i)) {
            i = read_char();
        }
        if (savePoint != null) {
            savePoint.markEnd(0);
        }
        return i;
    }

    protected void load_symbol_identifier(StringBuilder sb) throws IOException {
        int i = read_char();
        while (IonTokenConstsX.isValidSymbolCharacter(i)) {
            sb.append((char) i);
            i = read_char();
        }
        unread_char(i);
    }

    private int skip_over_symbol_operator(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int i = read_char();
        if (peek_inf_helper(i)) {
            i = read_char();
        } else {
            while (IonTokenConstsX.isValidExtendedSymbolCharacter(i)) {
                i = read_char();
            }
        }
        if (savePoint != null) {
            savePoint.markEnd(0);
        }
        return i;
    }

    protected void load_symbol_operator(StringBuilder sb) throws IOException {
        int i = read_char();
        if ((i == 43 || i == 45) && peek_inf_helper(i)) {
            sb.append((char) i);
            sb.append("inf");
        } else {
            while (IonTokenConstsX.isValidExtendedSymbolCharacter(i)) {
                sb.append((char) i);
                i = read_char();
            }
            unread_char(i);
        }
    }

    private final int skip_single_quoted_string(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        while (true) {
            int i = read_string_char(ProhibitedCharacters.NONE);
            if (i == -1) {
                unexpected_eof();
                break;
            }
            if (i == 39) {
                break;
            }
            if (i == 92) {
                read_char();
            }
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return read_char();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int load_single_quoted_string(java.lang.StringBuilder r4, boolean r5) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
        L1:
            com.amazon.ion.impl.IonReaderTextRawTokensX$ProhibitedCharacters r1 = com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.NONE
            int r1 = r3.read_string_char(r1)
            r2 = -1
            if (r1 == r2) goto L58
            r2 = 39
            if (r1 == r2) goto L58
            r2 = 92
            if (r1 == r2) goto L25
            switch(r1) {
                case -9: goto L1;
                case -8: goto L1;
                case -7: goto L1;
                case -6: goto L22;
                case -5: goto L22;
                case -4: goto L22;
                default: goto L15;
            }
        L15:
            if (r5 != 0) goto L2d
            boolean r2 = com.amazon.ion.impl.IonTokenConstsX.is7bitValue(r1)
            if (r2 != 0) goto L2d
            int r1 = r3.read_large_char_sequence(r1)
            goto L2d
        L22:
            r3.bad_token(r1)
        L25:
            int r1 = r3.read_char()
            int r1 = r3.read_escaped_char_content_helper(r1, r5)
        L2d:
            if (r5 != 0) goto L4a
            boolean r0 = r3.check_for_low_surrogate(r1, r0)
            boolean r2 = com.amazon.ion.impl.IonUTF8.needsSurrogateEncoding(r1)
            if (r2 == 0) goto L45
            char r2 = com.amazon.ion.impl.IonUTF8.highSurrogate(r1)
            r4.append(r2)
            char r1 = com.amazon.ion.impl.IonUTF8.lowSurrogate(r1)
            goto L53
        L45:
            boolean r0 = com.amazon.ion.impl.IonUTF8.isHighSurrogate(r1)
            goto L53
        L4a:
            boolean r2 = com.amazon.ion.impl.IonTokenConstsX.is8bitValue(r1)
            if (r2 == 0) goto L53
            r3.bad_token(r1)
        L53:
            char r1 = (char) r1
            r4.append(r1)
            goto L1
        L58:
            if (r5 != 0) goto L5d
            r3.check_for_low_surrogate(r1, r0)
        L5d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.load_single_quoted_string(java.lang.StringBuilder, boolean):int");
    }

    private void skip_double_quoted_string(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        skip_double_quoted_string_helper();
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0022, code lost:
    
        bad_token(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0025, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void skip_double_quoted_string_helper() throws java.io.IOException {
        /*
            r2 = this;
        L0:
            com.amazon.ion.impl.IonReaderTextRawTokensX$ProhibitedCharacters r0 = com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.NONE
            int r0 = r2.read_string_char(r0)
            r1 = -6
            if (r0 == r1) goto L22
            r1 = -5
            if (r0 == r1) goto L22
            r1 = -4
            if (r0 == r1) goto L22
            r1 = -1
            if (r0 == r1) goto L1f
            r1 = 34
            if (r0 == r1) goto L25
            r1 = 92
            if (r0 == r1) goto L1b
            goto L0
        L1b:
            r2.read_char()
            goto L0
        L1f:
            r2.unexpected_eof()
        L22:
            r2.bad_token(r0)
        L25:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.skip_double_quoted_string_helper():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int load_double_quoted_string(java.lang.StringBuilder r4, boolean r5) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
        L1:
            com.amazon.ion.impl.IonReaderTextRawTokensX$ProhibitedCharacters r1 = com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.SHORT_CHAR
            int r1 = r3.read_string_char(r1)
            r2 = -1
            if (r1 == r2) goto L4a
            r2 = 34
            if (r1 == r2) goto L4a
            r2 = 92
            if (r1 == r2) goto L25
            switch(r1) {
                case -9: goto L1;
                case -8: goto L1;
                case -7: goto L1;
                case -6: goto L22;
                case -5: goto L22;
                case -4: goto L22;
                default: goto L15;
            }
        L15:
            if (r5 != 0) goto L29
            boolean r2 = com.amazon.ion.impl.IonTokenConstsX.is7bitValue(r1)
            if (r2 != 0) goto L29
            int r1 = r3.read_large_char_sequence(r1)
            goto L29
        L22:
            r3.bad_token(r1)
        L25:
            int r1 = r3.read_char_escaped(r1, r5)
        L29:
            if (r5 != 0) goto L45
            boolean r0 = r3.check_for_low_surrogate(r1, r0)
            boolean r2 = com.amazon.ion.impl.IonUTF8.needsSurrogateEncoding(r1)
            if (r2 == 0) goto L41
            char r2 = com.amazon.ion.impl.IonUTF8.highSurrogate(r1)
            r4.append(r2)
            char r1 = com.amazon.ion.impl.IonUTF8.lowSurrogate(r1)
            goto L45
        L41:
            boolean r0 = com.amazon.ion.impl.IonUTF8.isHighSurrogate(r1)
        L45:
            char r1 = (char) r1
            r4.append(r1)
            goto L1
        L4a:
            if (r5 != 0) goto L4f
            r3.check_for_low_surrogate(r1, r0)
        L4f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.load_double_quoted_string(java.lang.StringBuilder, boolean):int");
    }

    private boolean check_for_low_surrogate(int i, boolean z) throws IonException {
        if (IonUTF8.isLowSurrogate(i)) {
            if (z) {
                return false;
            }
            error("unexpected low surrogate " + IonTextUtils.printCodePointAsString(i));
        } else if (z) {
            expected_but_found("a low surrogate", i);
        }
        return false;
    }

    protected int read_double_quoted_char(boolean z) throws IOException {
        int i = read_char();
        if (z && i > 127) {
            throw new IonReaderTextTokenException("non ASCII character in clob: " + i);
        }
        if (i == -1) {
            return i;
        }
        if (i == 34) {
            unread_char(i);
            return -2;
        }
        if (i != 92) {
            return (z || IonTokenConstsX.is7bitValue(i)) ? i : read_large_char_sequence(i);
        }
        return read_char_escaped(i, z);
    }

    private void skip_triple_quoted_string(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        skip_triple_quoted_string(savePoint, CommentStrategy.IGNORE);
    }

    private void skip_triple_quoted_clob_string(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        skip_triple_quoted_string(savePoint, CommentStrategy.ERROR);
    }

    private void skip_triple_quoted_string(UnifiedSavePointManagerX.SavePoint savePoint, CommentStrategy commentStrategy) throws IOException {
        int iSkip_over_whitespace;
        while (true) {
            int i = read_char();
            if (i == -1) {
                unexpected_eof();
            } else if (i != 39) {
                if (i == 92) {
                    read_char();
                }
            }
            if (read_char() == 39) {
                int i2 = read_char();
                if (savePoint != null) {
                    savePoint.markEnd(-3);
                }
                if (i2 == 39 && ((iSkip_over_whitespace = skip_over_whitespace(commentStrategy)) != 39 || !is_2_single_quotes_helper())) {
                    break;
                }
            } else {
                continue;
            }
        }
        unread_char(iSkip_over_whitespace);
    }

    protected int load_triple_quoted_string(StringBuilder sb, boolean z) throws IOException {
        boolean zCheck_for_low_surrogate = false;
        while (true) {
            int iLowSurrogate = read_triple_quoted_char(z);
            switch (iLowSurrogate) {
                case -6:
                case -5:
                case FontsContractCompat.FontRequestCallback.FAIL_REASON_SECURITY_VIOLATION /* -4 */:
                    iLowSurrogate = 10;
                    break;
                case -3:
                    if (z) {
                        continue;
                    } else {
                        zCheck_for_low_surrogate = check_for_low_surrogate(iLowSurrogate, zCheck_for_low_surrogate);
                    }
                    break;
                case -2:
                case -1:
                    if (!z) {
                        check_for_low_surrogate(iLowSurrogate, zCheck_for_low_surrogate);
                    }
                    return iLowSurrogate;
            }
            if (!z) {
                zCheck_for_low_surrogate = check_for_low_surrogate(iLowSurrogate, zCheck_for_low_surrogate);
                if (IonUTF8.needsSurrogateEncoding(iLowSurrogate)) {
                    sb.append(IonUTF8.highSurrogate(iLowSurrogate));
                    iLowSurrogate = IonUTF8.lowSurrogate(iLowSurrogate);
                } else {
                    zCheck_for_low_surrogate = IonUTF8.isHighSurrogate(iLowSurrogate);
                }
            }
            sb.append((char) iLowSurrogate);
        }
    }

    protected int read_triple_quoted_char(boolean z) throws IOException {
        int i = read_string_char(ProhibitedCharacters.LONG_CHAR);
        if (z && i > 127) {
            throw new IonReaderTextTokenException("non ASCII character in clob: " + i);
        }
        if (i == -1) {
            return i;
        }
        if (i != 39) {
            if (i == 92) {
                return read_char_escaped(i, z);
            }
            switch (i) {
                case -9:
                case -8:
                case -7:
                case -6:
                case -5:
                case FontsContractCompat.FontRequestCallback.FAIL_REASON_SECURITY_VIOLATION /* -4 */:
                    return i;
                default:
                    return (z || IonTokenConstsX.is7bitValue(i)) ? i : read_large_char_sequence(i);
            }
        }
        if (!is_2_single_quotes_helper()) {
            return i;
        }
        int iSkip_over_whitespace = skip_over_whitespace();
        if (iSkip_over_whitespace == 39 && is_2_single_quotes_helper()) {
            return -3;
        }
        unread_char(iSkip_over_whitespace);
        return -2;
    }

    protected void skip_over_lob(int i, UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        if (i == 12) {
            skip_double_quoted_string(savePoint);
            skip_clob_close_punctuation();
            return;
        }
        if (i == 13) {
            skip_triple_quoted_clob_string(savePoint);
            skip_clob_close_punctuation();
        } else {
            if (i == 24) {
                skip_over_blob(savePoint);
                return;
            }
            error("unexpected token " + IonTokenConstsX.getTokenName(i) + " encountered for lob content");
        }
    }

    private final int read_char_escaped(int i, boolean z) throws IOException {
        while (true) {
            if (i == -9 || i == -8 || i == -7) {
                i = read_string_char(ProhibitedCharacters.NONE);
            } else if (i == 92) {
                int i2 = read_char();
                if (i2 < 0) {
                    unexpected_eof();
                }
                i = read_escaped_char_content_helper(i2, z);
                if (i == -7 || i == -8 || i == -9) {
                    i = read_string_char(ProhibitedCharacters.NONE);
                } else if (i == -11) {
                    bad_escape_sequence();
                }
            } else if (!z && !IonTokenConstsX.is7bitValue(i)) {
                i = read_large_char_sequence(i);
            }
        }
        if (i != -1 && z && !IonTokenConstsX.is8bitValue(i)) {
            error("invalid character [" + IonTextUtils.printCodePointAsString(i) + "] in CLOB");
        }
        return i;
    }

    private final int read_large_char_sequence(int i) throws IOException {
        if (this._stream._is_byte_data) {
            return read_ut8_sequence(i);
        }
        if (!_Private_IonConstants.isHighSurrogate(i)) {
            return i;
        }
        int i2 = read_char();
        if (_Private_IonConstants.isLowSurrogate(i2)) {
            return _Private_IonConstants.makeUnicodeScalar(i, i2);
        }
        unread_char(i2);
        return i;
    }

    private final int read_ut8_sequence(int i) throws IOException {
        int uTF8LengthFromFirstByte = IonUTF8.getUTF8LengthFromFirstByte(i);
        if (uTF8LengthFromFirstByte == 1) {
            return i;
        }
        if (uTF8LengthFromFirstByte == 2) {
            return IonUTF8.twoByteScalar(i, read_char());
        }
        if (uTF8LengthFromFirstByte == 3) {
            return IonUTF8.threeByteScalar(i, read_char(), read_char());
        }
        if (uTF8LengthFromFirstByte == 4) {
            return IonUTF8.fourByteScalar(i, read_char(), read_char(), read_char());
        }
        error("invalid UTF8 starting byte");
        return i;
    }

    private void skip_over_blob(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int iSkip_over_blob_whitespace = skip_over_blob_whitespace();
        while (true) {
            if (iSkip_over_blob_whitespace == -1 || iSkip_over_blob_whitespace == 125) {
                break;
            } else {
                iSkip_over_blob_whitespace = skip_over_blob_whitespace();
            }
        }
        if (savePoint != null) {
            savePoint.markEnd(iSkip_over_blob_whitespace != 125 ? 0 : -1);
        }
        if (iSkip_over_blob_whitespace != 125) {
            unexpected_eof();
        }
        int i = read_char();
        if (i < 0) {
            unexpected_eof();
        }
        if (i != 125) {
            error("improperly closed BLOB, " + IonTextUtils.printCodePointAsString(i) + " encountered when '}' was expected");
        }
        if (savePoint != null) {
            savePoint.markEnd();
        }
    }

    private final int read_escaped_char_content_helper(int i, boolean z) throws IOException {
        if (i < 0) {
            if (i == -6) {
                return -9;
            }
            if (i == -5) {
                return -8;
            }
            if (i == -4) {
                return -7;
            }
            bad_escape_sequence(i);
        }
        if (!IonTokenConstsX.isValidEscapeStart(i)) {
            bad_escape_sequence(i);
        }
        int iEscapeReplacementCharacter = IonTokenConstsX.escapeReplacementCharacter(i);
        if (iEscapeReplacementCharacter == -11) {
            return iEscapeReplacementCharacter;
        }
        switch (iEscapeReplacementCharacter) {
            case -16:
                return read_hex_escape_sequence_value(2);
            case -15:
                if (z) {
                    bad_escape_sequence(iEscapeReplacementCharacter);
                }
                return read_hex_escape_sequence_value(8);
            case -14:
                if (z) {
                    bad_escape_sequence(iEscapeReplacementCharacter);
                }
                return read_hex_escape_sequence_value(4);
            default:
                return iEscapeReplacementCharacter;
        }
    }

    private final int read_hex_escape_sequence_value(int i) throws IOException {
        int i2 = 0;
        while (i > 0) {
            i--;
            int i3 = read_char();
            if (i3 < 0) {
                unexpected_eof();
            }
            int iHexDigitValue = IonTokenConstsX.hexDigitValue(i3);
            if (iHexDigitValue < 0) {
                return -1;
            }
            i2 = (i2 << 4) + iHexDigitValue;
        }
        if (i > 0) {
            error("invalid hex digit [" + IonTextUtils.printCodePointAsString(i2) + "] in escape sequence");
        }
        return i2;
    }

    public final int read_base64_byte() throws IOException {
        int i = this._base64_prefetch_count;
        if (i < 1) {
            return read_base64_byte_helper();
        }
        int i2 = this._base64_prefetch_stack;
        int i3 = i2 & 255;
        this._base64_prefetch_stack = i2 >> 8;
        this._base64_prefetch_count = i - 1;
        return i3;
    }

    private final int read_base64_byte_helper() throws IOException {
        int iSkip_over_blob_whitespace = skip_over_blob_whitespace();
        if (iSkip_over_blob_whitespace == -1 || iSkip_over_blob_whitespace == 125) {
            return -1;
        }
        int i = read_base64_getchar_helper(iSkip_over_blob_whitespace);
        int i2 = read_base64_getchar_helper();
        int i3 = read_base64_getchar_helper();
        int i4 = read_base64_getchar_helper();
        int iDecode_base64_length = decode_base64_length(i, i2, i3, i4);
        this._base64_prefetch_stack = 0;
        this._base64_prefetch_count = iDecode_base64_length - 1;
        if (iDecode_base64_length != 1) {
            if (iDecode_base64_length != 2) {
                if (iDecode_base64_length != 3) {
                    throw new IonReaderTextTokenException("invalid binhex sequence encountered at offset" + input_position());
                }
                this._base64_prefetch_stack = (decode_base64_byte3(i, i2, i3, i4) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
            }
            this._base64_prefetch_stack = (decode_base64_byte2(i, i2, i3, i4) & 255) | this._base64_prefetch_stack;
        }
        return decode_base64_byte1(i, i2, i3, i4);
    }

    private final int read_base64_getchar_helper(int i) throws IOException {
        if (i == -1 || i == 125) {
            return -1;
        }
        if (i == BASE64_TERMINATOR_CHAR) {
            error("invalid base64 image - excess terminator characters ['=']");
        }
        return read_base64_getchar_helper2(i);
    }

    private final int read_base64_getchar_helper() throws IOException {
        int iSkip_over_blob_whitespace = skip_over_blob_whitespace();
        if (iSkip_over_blob_whitespace == -1 || iSkip_over_blob_whitespace == 125) {
            error("invalid base64 image - too short");
        }
        return read_base64_getchar_helper2(iSkip_over_blob_whitespace);
    }

    private final int read_base64_getchar_helper2(int i) throws IOException {
        if (i == BASE64_TERMINATOR_CHAR) {
            return 128;
        }
        int i2 = BASE64_CHAR_TO_BIN[i & 255];
        if (i2 != -1 && IonTokenConstsX.is8bitValue(i)) {
            return i2;
        }
        throw new IonReaderTextTokenException("invalid character " + Character.toString((char) i) + " encountered in base64 value at " + input_position());
    }

    protected void save_point_start(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        savePoint.start(this._line_count, this._line_starting_position);
    }

    protected void save_point_activate(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        this._stream._save_points.savePointPushActive(savePoint, this._line_count, this._line_starting_position);
        this._line_count = savePoint.getStartLineNumber();
        this._line_starting_position = savePoint.getStartLineStart();
    }

    protected void save_point_deactivate(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        this._stream._save_points.savePointPopActive(savePoint);
        this._line_count = savePoint.getPrevLineNumber();
        this._line_starting_position = savePoint.getPrevLineStart();
    }

    protected final void error(String str) {
        throw new IonReaderTextTokenException(str + input_position());
    }

    protected final void unexpected_eof() {
        throw new UnexpectedEofException("unexpected EOF encountered " + input_position());
    }

    protected final void bad_escape_sequence() {
        throw new IonReaderTextTokenException("bad escape character encountered " + input_position());
    }

    protected final void bad_escape_sequence(int i) {
        throw new IonReaderTextTokenException("bad escape character '" + IonTextUtils.printCodePointAsString(i) + "' encountered " + input_position());
    }

    protected final void bad_token_start(int i) {
        throw new IonReaderTextTokenException("bad character [" + i + ", " + IonTextUtils.printCodePointAsString(i) + "] encountered where a token was supposed to start " + input_position());
    }

    protected final void bad_token(int i) {
        throw new IonReaderTextTokenException("a bad character " + IonTextUtils.printCodePointAsString(i) + " was encountered " + input_position());
    }

    protected final void expected_but_found(String str, int i) {
        throw new IonReaderTextTokenException("Expected " + str + " but found " + IonTextUtils.printCodePointAsString(i) + input_position());
    }

    public static class IonReaderTextTokenException extends IonException {
        IonReaderTextTokenException(String str) {
            super(str);
        }
    }

    private enum ProhibitedCharacters {
        SHORT_CHAR { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.1
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters
            boolean includes(int i) {
                return ProhibitedCharacters.isControlCharacter(i) && !ProhibitedCharacters.isWhitespace(i);
            }
        },
        LONG_CHAR { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.2
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters
            boolean includes(int i) {
                return (!ProhibitedCharacters.isControlCharacter(i) || ProhibitedCharacters.isWhitespace(i) || ProhibitedCharacters.isNewline(i)) ? false : true;
            }
        },
        NONE { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.3
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters
            boolean includes(int i) {
                return false;
            }
        };

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isControlCharacter(int i) {
            return i <= 31 && i >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isNewline(int i) {
            return i == 10 || i == 13;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isWhitespace(int i) {
            return i == 9 || i == 11 || i == 12 || i == 32;
        }

        abstract boolean includes(int i);
    }

    private int readNumeric(Appendable appendable, Radix radix) throws IOException {
        return readNumeric(appendable, radix, NumericState.START);
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextRawTokensX$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState;

        static {
            int[] iArr = new int[NumericState.values().length];
            $SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState = iArr;
            try {
                iArr[NumericState.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState[NumericState.DIGIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState[NumericState.UNDERSCORE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private int readNumeric(Appendable appendable, Radix radix, NumericState numericState) throws IOException {
        while (true) {
            int i = read_char();
            int i2 = AnonymousClass2.$SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState[numericState.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        continue;
                    } else if (radix.isValidDigit(i)) {
                        appendable.append(radix.normalizeDigit((char) i));
                        numericState = NumericState.DIGIT;
                    } else {
                        unread_char(i);
                        return 95;
                    }
                } else if (radix.isValidDigit(i)) {
                    appendable.append(radix.normalizeDigit((char) i));
                    numericState = NumericState.DIGIT;
                } else {
                    if (i != 95) {
                        return i;
                    }
                    numericState = NumericState.UNDERSCORE;
                }
            } else {
                if (!radix.isValidDigit(i)) {
                    return i;
                }
                appendable.append(radix.normalizeDigit((char) i));
                numericState = NumericState.DIGIT;
            }
        }
    }
}
