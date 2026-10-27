package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.amazon.ion.IonTextReader;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import com.amazon.ion.impl._Private_ScalarConversions;
import java.io.IOException;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes3.dex */
abstract class IonReaderTextRawX implements IonTextReader {
    static final int[][] TransitionActions = makeTransitionActionArray();
    static final int[] TransitionActions2 = makeTransition2ActionArray();
    int _annotation_count;
    SymbolToken[] _annotations;
    boolean _container_is_struct;
    boolean _container_prohibits_commas;
    int _container_state_top;
    StringBuilder _current_value_buffer;
    boolean _current_value_buffer_loaded;
    UnifiedSavePointManagerX.SavePoint _current_value_save_point;
    boolean _current_value_save_point_loaded;
    boolean _eof;
    String _field_name;
    boolean _has_next_called;
    int _lob_actual_len;
    byte[] _lob_bytes;
    LOB_STATE _lob_loaded;
    int _lob_token;
    long _lob_value_position;
    boolean _lob_value_set;
    IonType _null_type;
    IonReaderTextRawTokensX _scanner;
    int _state;
    int _value_keyword;
    long _value_start_column;
    long _value_start_line;
    long _value_start_offset;
    IonType _value_type;
    IonType[] _container_state_stack = new IonType[10];
    int _field_name_sid = -1;
    _Private_ScalarConversions.ValueVariant _v = new _Private_ScalarConversions.ValueVariant();
    IonType _nesting_parent = null;

    enum LOB_STATE {
        EMPTY,
        READ,
        FINISHED
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return null;
    }

    private final String get_state_name(int i) {
        switch (i) {
            case 0:
                return "STATE_BEFORE_ANNOTATION_DATAGRAM";
            case 1:
                return "STATE_BEFORE_ANNOTATION_CONTAINED";
            case 2:
                return "STATE_BEFORE_ANNOTATION_SEXP";
            case 3:
                return "STATE_BEFORE_FIELD_NAME";
            case 4:
                return "STATE_BEFORE_VALUE_CONTENT";
            case 5:
                return "STATE_BEFORE_VALUE_CONTENT_SEXP";
            case 6:
                return "STATE_IN_LONG_STRING";
            case 7:
                return "STATE_IN_CLOB_DOUBLE_QUOTED_CONTENT";
            case 8:
                return "STATE_IN_CLOB_TRIPLE_QUOTED_CONTENT";
            case 9:
                return "STATE_IN_BLOB_CONTENT";
            case 10:
                return "STATE_AFTER_VALUE_CONTENTS";
            case 11:
                return "STATE_EOF";
            default:
                return "<invalid state: " + Integer.toString(i) + ">";
        }
    }

    static final int[][] makeTransitionActionArray() {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 12, 27);
        int[] iArr2 = iArr[0];
        iArr2[0] = 14;
        iArr2[1] = 8;
        iArr2[2] = 8;
        iArr2[26] = 8;
        iArr2[3] = 8;
        iArr2[4] = 8;
        iArr2[5] = 8;
        iArr2[6] = 9;
        iArr2[7] = 10;
        iArr2[8] = 8;
        iArr2[12] = 8;
        iArr2[13] = 8;
        iArr2[9] = 2;
        iArr2[10] = 2;
        iArr2[18] = 5;
        iArr2[20] = 3;
        iArr2[22] = 4;
        iArr2[24] = 6;
        for (int i = 0; i < 27; i++) {
            int[] iArr3 = iArr[1];
            int[] iArr4 = iArr[0];
            iArr3[i] = iArr4[i];
            iArr[2][i] = iArr4[i];
            iArr[4][i] = iArr4[i];
            iArr[5][i] = iArr4[i];
        }
        int[] iArr5 = iArr[1];
        iArr5[0] = 0;
        iArr5[19] = 12;
        iArr5[21] = 0;
        iArr5[23] = 12;
        int[] iArr6 = iArr[2];
        iArr6[0] = 0;
        iArr6[11] = 8;
        iArr6[14] = 8;
        iArr6[19] = 12;
        iArr6[21] = 12;
        iArr6[23] = 12;
        int[] iArr7 = iArr[4];
        iArr7[0] = 0;
        iArr7[9] = 8;
        iArr7[10] = 8;
        int[] iArr8 = iArr[5];
        iArr8[0] = 0;
        iArr8[9] = 8;
        iArr8[10] = 8;
        iArr8[11] = 8;
        int[] iArr9 = iArr[3];
        iArr9[0] = 0;
        iArr9[9] = 1;
        iArr9[10] = 1;
        iArr9[12] = 1;
        iArr9[13] = 1;
        iArr9[19] = 12;
        iArr9[21] = 12;
        iArr9[23] = 12;
        int[] iArr10 = iArr[10];
        iArr10[15] = 11;
        iArr10[19] = 12;
        iArr10[21] = 12;
        iArr10[23] = 12;
        iArr[7][21] = 13;
        iArr[8][21] = 13;
        iArr[9][21] = 13;
        for (int i2 = 0; i2 < 27; i2++) {
            iArr[11][i2] = 15;
        }
        return iArr;
    }

    static int[] makeTransition2ActionArray() {
        int[] iArr = new int[324];
        for (int i = 0; i < 12; i++) {
            for (int i2 = 0; i2 < 27; i2++) {
                iArr[(i * 27) + i2] = TransitionActions[i][i2];
            }
        }
        return iArr;
    }

    protected IonReaderTextRawX() {
    }

    protected final void init_once() {
        this._current_value_buffer = new StringBuilder();
        this._annotations = new SymbolToken[5];
    }

    protected final void init(UnifiedInputStreamX unifiedInputStreamX, IonType ionType) {
        init(unifiedInputStreamX, ionType, 1L, 1L);
    }

    protected final void init(UnifiedInputStreamX unifiedInputStreamX, IonType ionType, long j, long j2) {
        this._scanner = new IonReaderTextRawTokensX(unifiedInputStreamX, j, j2);
        this._value_start_line = j;
        this._value_start_column = j2;
        this._current_value_save_point = unifiedInputStreamX.savePointAllocate();
        this._lob_loaded = LOB_STATE.EMPTY;
        set_state(get_state_at_container_start(ionType));
        this._eof = false;
        push_container_state(ionType);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._scanner.close();
    }

    private final void set_state(int i) {
        this._state = i;
    }

    private final int get_state_int() {
        return this._state;
    }

    private final String get_state_name() {
        return get_state_name(get_state_int());
    }

    protected final void clear_current_value_buffer() {
        if (this._current_value_buffer_loaded) {
            this._current_value_buffer.setLength(0);
            this._current_value_buffer_loaded = false;
        }
        if (this._current_value_save_point_loaded) {
            this._current_value_save_point.clear();
            this._current_value_save_point_loaded = false;
        }
    }

    private final void current_value_is_null(IonType ionType) {
        clear_current_value_buffer();
        this._value_type = this._null_type;
        this._v.setValueToNull(ionType);
        this._v.setAuthoritativeType(1);
    }

    private final void current_value_is_bool(boolean z) {
        clear_current_value_buffer();
        this._value_type = IonType.BOOL;
        this._v.setValue(z);
        this._v.setAuthoritativeType(2);
    }

    private final void set_fieldname(SymbolToken symbolToken) {
        this._field_name = symbolToken.getText();
        this._field_name_sid = symbolToken.getSid();
    }

    private final void clear_fieldname() {
        this._field_name = null;
        this._field_name_sid = -1;
    }

    private final void append_annotation(SymbolToken symbolToken) {
        SymbolToken[] symbolTokenArr = this._annotations;
        int length = symbolTokenArr.length;
        if (this._annotation_count >= length) {
            SymbolToken[] symbolTokenArr2 = new SymbolToken[length * 2];
            System.arraycopy(symbolTokenArr, 0, symbolTokenArr2, 0, length);
            this._annotations = symbolTokenArr2;
        }
        SymbolToken[] symbolTokenArr3 = this._annotations;
        int i = this._annotation_count;
        this._annotation_count = i + 1;
        symbolTokenArr3[i] = symbolToken;
    }

    private final void clear_annotation_list() {
        this._annotation_count = 0;
    }

    public boolean hasNext() {
        return has_next_raw_value();
    }

    protected final boolean has_next_raw_value() {
        if (!this._has_next_called && !this._eof) {
            try {
                finish_value(null);
                clear_value();
                parse_to_next_value();
                this._has_next_called = true;
            } catch (IOException e) {
                throw new IonException(e);
            }
        }
        return !this._eof;
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        if (!hasNext()) {
            return null;
        }
        if (this._value_type == null && this._scanner.isUnfinishedToken()) {
            try {
                token_contents_load(this._scanner.getToken());
            } catch (IOException e) {
                throw new IonException(e);
            }
        }
        this._has_next_called = false;
        return this._value_type;
    }

    private final void finish_and_save_value() throws IOException {
        if (this._current_value_save_point_loaded) {
            return;
        }
        this._scanner.save_point_start(this._current_value_save_point);
        finish_value(this._current_value_save_point);
        this._current_value_save_point_loaded = true;
    }

    private final void finish_value(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        IonType ionType;
        int i;
        if (this._scanner.isUnfinishedToken()) {
            if (savePoint != null && (ionType = this._value_type) != null && ((i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) == 1 || i == 2 || i == 3)) {
                savePoint = null;
            }
            this._scanner.finish_token(savePoint);
            set_state(get_state_after_value());
        }
        this._has_next_called = false;
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextRawX$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRUCT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DATAGRAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private final void clear_value() {
        this._value_type = null;
        this._null_type = null;
        if (this._lob_value_set) {
            this._lob_value_set = false;
            this._lob_value_position = 0L;
        }
        LOB_STATE lob_state = LOB_STATE.EMPTY;
        if (!lob_state.equals(this._lob_loaded)) {
            this._lob_actual_len = -1;
            this._lob_bytes = null;
            this._lob_loaded = lob_state;
        }
        clear_current_value_buffer();
        clear_annotation_list();
        clear_fieldname();
        this._v.clear();
        this._value_start_offset = -1L;
    }

    private final void set_container_flags(IonType ionType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i == 1) {
            this._container_is_struct = true;
            this._container_prohibits_commas = false;
            return;
        }
        if (i == 2) {
            this._container_is_struct = false;
            this._container_prohibits_commas = true;
            return;
        }
        if (i == 3) {
            this._container_is_struct = false;
            this._container_prohibits_commas = false;
        } else if (i == 4) {
            this._container_is_struct = false;
            this._container_prohibits_commas = true;
        } else {
            throw new IllegalArgumentException("type must be a container, not a " + ionType.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int get_state_after_value() {
        /*
            r3 = this;
            int[] r0 = com.amazon.ion.impl.IonReaderTextRawX.AnonymousClass1.$SwitchMap$com$amazon$ion$IonType
            com.amazon.ion.IonType r1 = r3.getContainerType()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 1
            r2 = 2
            if (r0 == r1) goto L3e
            if (r0 == r2) goto L40
            r1 = 3
            if (r0 == r1) goto L3e
            r1 = 4
            if (r0 != r1) goto L1a
            r2 = 0
            goto L40
        L1a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "invalid container type encountered during parsing "
            r0.append(r1)
            com.amazon.ion.IonType r1 = r3.getContainerType()
            r0.append(r1)
            com.amazon.ion.impl.IonReaderTextRawTokensX r1 = r3._scanner
            java.lang.String r1 = r1.input_position()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.amazon.ion.IonException r1 = new com.amazon.ion.IonException
            r1.<init>(r0)
            throw r1
        L3e:
            r2 = 10
        L40:
            com.amazon.ion.IonType r0 = r3._nesting_parent
            if (r0 == 0) goto L4c
            int r0 = r3.getDepth()
            if (r0 != 0) goto L4c
            r2 = 11
        L4c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawX.get_state_after_value():int");
    }

    private final int get_state_after_annotation() {
        int i = get_state_int();
        if (i != 0 && i != 1) {
            if (i == 2) {
                return 5;
            }
            if (i == 10) {
                IonType ionType = top_state();
                int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        return 5;
                    }
                    if (i2 != 3 && i2 != 4) {
                        throw new IonException("invalid container type encountered during parsing " + ionType + this._scanner.input_position());
                    }
                }
            } else {
                throw new IonException("invalid state encountered during parsing before the value " + get_state_name() + this._scanner.input_position());
            }
        }
        return 4;
    }

    private final int get_state_after_container() {
        return get_state_after_container(top_state());
    }

    private final int get_state_after_container(int i) {
        IonType ionType = top_state();
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i2 == 1) {
            check_container_close(ionType, 21, i);
        } else if (i2 == 2) {
            check_container_close(ionType, 19, i);
        } else if (i2 == 3) {
            check_container_close(ionType, 23, i);
        } else {
            throw new IonException("invalid container type encountered during parsing " + ionType + this._scanner.input_position());
        }
        return get_state_after_container(ionType);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int get_state_after_container(com.amazon.ion.IonType r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L4
            goto L4a
        L4:
            int[] r1 = com.amazon.ion.impl.IonReaderTextRawX.AnonymousClass1.$SwitchMap$com$amazon$ion$IonType
            int r2 = r5.ordinal()
            r1 = r1[r2]
            r2 = 1
            r3 = 2
            if (r1 == r2) goto L3b
            if (r1 == r3) goto L39
            r2 = 3
            if (r1 == r2) goto L3b
            r2 = 4
            if (r1 != r2) goto L19
            goto L3e
        L19:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "invalid container type encountered during parsing "
            r0.append(r1)
            r0.append(r5)
            com.amazon.ion.impl.IonReaderTextRawTokensX r5 = r4._scanner
            java.lang.String r5 = r5.input_position()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.amazon.ion.IonException r0 = new com.amazon.ion.IonException
            r0.<init>(r5)
            throw r0
        L39:
            r0 = r3
            goto L3e
        L3b:
            r5 = 10
            r0 = r5
        L3e:
            com.amazon.ion.IonType r5 = r4._nesting_parent
            if (r5 == 0) goto L4a
            int r5 = r4.getDepth()
            if (r5 != 0) goto L4a
            r0 = 11
        L4a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawX.get_state_after_container(com.amazon.ion.IonType):int");
    }

    private final void check_container_close(IonType ionType, int i, int i2) {
        if (i2 == i) {
            return;
        }
        throw new IonException(ionType.toString().toLowerCase() + " closed by " + IonTokenConstsX.describeToken(i2) + this._scanner.input_position());
    }

    private final int get_state_at_container_start(IonType ionType) {
        if (ionType == null) {
            return 0;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 1;
        }
        if (i == 4) {
            return 0;
        }
        throw new IonException("invalid container type encountered during parsing " + ionType + this._scanner.input_position());
    }

    private final SymbolToken parseSymbolToken(String str, StringBuilder sb, int i) throws IOException {
        String string;
        int iDecodeSid = -1;
        if (i == 9) {
            int iKeyword = IonTokenConstsX.keyword(sb, 0, sb.length());
            if (iKeyword == 1 || iKeyword == 2 || iKeyword == 3 || iKeyword == 16) {
                parse_error("Cannot use unquoted keyword " + sb.toString() + " as " + str);
            } else if (iKeyword != 17) {
                string = sb.toString();
            }
            string = null;
            iDecodeSid = IonTokenConstsX.decodeSid(sb);
        } else {
            string = sb.toString();
        }
        return new SymbolTokenImpl(string, iDecodeSid);
    }

    protected final void parse_to_next_value() throws IOException {
        int i;
        this._value_start_offset = this._scanner.getStartingOffset();
        this._value_start_line = this._scanner.getLineNumber();
        this._value_start_column = this._scanner.getLineOffset();
        int iNextToken = this._scanner.nextToken();
        boolean z = false;
        while (true) {
            switch (TransitionActions2[(get_state_int() * 27) + iNextToken]) {
                case 0:
                    IonType ionType = this._nesting_parent;
                    if (ionType != null && ((i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) == 1 ? iNextToken == 21 : !(i == 2 ? iNextToken != 19 : i != 3 || iNextToken != 23))) {
                        iPeekNullTypeSymbol = 1;
                    }
                    if (iPeekNullTypeSymbol != 1) {
                        parse_error("invalid syntax [state:" + get_state_name() + " on token:" + IonTokenConstsX.getTokenName(iNextToken) + "]");
                    }
                    set_state(11);
                    this._eof = true;
                    return;
                case 1:
                    if (!is_in_struct_internal()) {
                        throw new IllegalStateException("field names have to be in structs");
                    }
                    finish_and_save_value();
                    set_fieldname(parseSymbolToken("a field name", token_contents_load(iNextToken), iNextToken));
                    clear_current_value_buffer();
                    int iNextToken2 = this._scanner.nextToken();
                    if (iNextToken2 != 16) {
                        parse_error("field name must be followed by a colon, not a " + IonTokenConstsX.getTokenName(iNextToken2));
                    }
                    this._scanner.tokenIsFinished();
                    set_state(1);
                    iNextToken = this._scanner.nextToken();
                    break;
                    break;
                case 2:
                    StringBuilder sb = token_contents_load(iNextToken);
                    boolean zSkip_whitespace = this._scanner.skip_whitespace();
                    if (!this._scanner.skipDoubleColon()) {
                        set_state(get_state_after_annotation());
                    } else {
                        append_annotation(parseSymbolToken("an annotation", sb, iNextToken));
                        clear_current_value_buffer();
                        iNextToken = this._scanner.nextToken();
                        if (iNextToken != 9 && iNextToken != 10) {
                            set_state(get_state_after_annotation());
                        }
                    }
                    z = zSkip_whitespace;
                    break;
                case 3:
                    this._value_type = IonType.STRUCT;
                    set_state(3);
                    return;
                case 4:
                    this._value_type = IonType.LIST;
                    set_state(1);
                    return;
                case 5:
                    this._value_type = IonType.SEXP;
                    set_state(2);
                    return;
                case 6:
                    int iPeekLobStartPunctuation = this._scanner.peekLobStartPunctuation();
                    if (iPeekLobStartPunctuation == 12) {
                        set_state(7);
                        this._lob_token = 12;
                        this._value_type = IonType.CLOB;
                        return;
                    } else if (iPeekLobStartPunctuation == 13) {
                        set_state(8);
                        this._lob_token = 13;
                        this._value_type = IonType.CLOB;
                        return;
                    } else {
                        set_state(9);
                        this._lob_token = 24;
                        this._value_type = IonType.BLOB;
                        return;
                    }
                case 7:
                default:
                    parse_error("unexpected token encountered: " + IonTokenConstsX.getTokenName(iNextToken));
                    break;
                case 8:
                    if (iNextToken == 9) {
                        StringBuilder sb2 = token_contents_load(iNextToken);
                        int iKeyword = IonTokenConstsX.keyword(sb2, 0, sb2.length());
                        this._value_keyword = iKeyword;
                        if (iKeyword == 1) {
                            this._value_type = IonType.BOOL;
                            current_value_is_bool(true);
                        } else if (iKeyword == 2) {
                            this._value_type = IonType.BOOL;
                            current_value_is_bool(false);
                        } else if (iKeyword == 3) {
                            iPeekNullTypeSymbol = z ? 0 : this._scanner.peekNullTypeSymbol();
                            if (iPeekNullTypeSymbol != 0) {
                                switch (iPeekNullTypeSymbol) {
                                    case 3:
                                        this._null_type = IonType.NULL;
                                        break;
                                    case 4:
                                        this._null_type = IonType.BOOL;
                                        break;
                                    case 5:
                                        this._null_type = IonType.INT;
                                        break;
                                    case 6:
                                        this._null_type = IonType.FLOAT;
                                        break;
                                    case 7:
                                        this._null_type = IonType.DECIMAL;
                                        break;
                                    case 8:
                                        this._null_type = IonType.TIMESTAMP;
                                        break;
                                    case 9:
                                        this._null_type = IonType.SYMBOL;
                                        break;
                                    case 10:
                                        this._null_type = IonType.STRING;
                                        break;
                                    case 11:
                                        this._null_type = IonType.BLOB;
                                        break;
                                    case 12:
                                        this._null_type = IonType.CLOB;
                                        break;
                                    case 13:
                                        this._null_type = IonType.LIST;
                                        break;
                                    case 14:
                                        this._null_type = IonType.SEXP;
                                        break;
                                    case 15:
                                        this._null_type = IonType.STRUCT;
                                        break;
                                    default:
                                        parse_error("invalid keyword id (" + iPeekNullTypeSymbol + ") encountered while parsing a null");
                                        break;
                                }
                            } else {
                                this._null_type = IonType.NULL;
                            }
                            current_value_is_null(this._null_type);
                        } else if (iKeyword == 16) {
                            this._value_type = IonType.FLOAT;
                            clear_current_value_buffer();
                            this._v.setValue(Double.NaN);
                            this._v.setAuthoritativeType(7);
                        } else {
                            if (iKeyword == 17) {
                                this._v.setValue(IonTokenConstsX.decodeSid(sb2));
                                this._v.setAuthoritativeType(3);
                            }
                            this._value_type = IonType.SYMBOL;
                        }
                    } else if (iNextToken == 14) {
                        this._value_type = IonType.SYMBOL;
                        clear_current_value_buffer();
                        this._v.setValue(".");
                        this._v.setAuthoritativeType(8);
                    } else {
                        this._value_type = IonTokenConstsX.ion_type_of_scalar(iNextToken);
                    }
                    set_state(get_state_after_value());
                    return;
                case 9:
                    this._value_type = IonType.FLOAT;
                    clear_current_value_buffer();
                    this._v.setValue(Double.POSITIVE_INFINITY);
                    this._v.setAuthoritativeType(7);
                    set_state(get_state_after_value());
                    return;
                case 10:
                    this._value_type = IonType.FLOAT;
                    clear_current_value_buffer();
                    this._v.setValue(Double.NEGATIVE_INFINITY);
                    this._v.setAuthoritativeType(7);
                    set_state(get_state_after_value());
                    return;
                case 11:
                    if (this._container_prohibits_commas) {
                        parse_error("commas aren't used to separate values in " + getContainerType().toString());
                    }
                    set_state(this._container_is_struct ? 3 : 1);
                    this._scanner.tokenIsFinished();
                    this._value_start_offset = this._scanner.getStartingOffset();
                    iNextToken = this._scanner.nextToken();
                    break;
                case 12:
                    set_state(get_state_after_container(iNextToken));
                    this._eof = true;
                    return;
                case 13:
                    set_state(get_state_after_value());
                    return;
                case 14:
                    if (getDepth() != 0) {
                        parse_error("state failure end of datagram encounterd with a non-container stack");
                    }
                    set_state(11);
                    this._eof = true;
                    return;
                case 15:
                    set_state(11);
                    this._eof = true;
                    return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x010b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final java.lang.StringBuilder token_contents_load(int r6) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawX.token_contents_load(int):java.lang.StringBuilder");
    }

    protected void tokenValueIsFinished() {
        this._scanner.tokenIsFinished();
        if (IonType.BLOB.equals(this._value_type) || IonType.CLOB.equals(this._value_type)) {
            set_state(get_state_after_value());
        }
    }

    private final void push_container_state(IonType ionType) {
        IonType[] ionTypeArr = this._container_state_stack;
        int length = ionTypeArr.length;
        if (this._container_state_top >= length) {
            IonType[] ionTypeArr2 = new IonType[length * 2];
            System.arraycopy(ionTypeArr, 0, ionTypeArr2, 0, length);
            this._container_state_stack = ionTypeArr2;
        }
        set_container_flags(ionType);
        IonType[] ionTypeArr3 = this._container_state_stack;
        int i = this._container_state_top;
        this._container_state_top = i + 1;
        ionTypeArr3[i] = ionType;
    }

    private final void pop_container_state() {
        this._container_state_top--;
        set_container_flags(top_state());
        this._eof = false;
        this._has_next_called = false;
        set_state(get_state_after_container());
    }

    private final IonType top_state() {
        return this._container_state_stack[this._container_state_top - 1];
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        return this._value_type;
    }

    private boolean is_in_struct_internal() {
        return IonType.STRUCT.equals(getContainerType());
    }

    public IonType getContainerType() {
        int i = this._container_state_top;
        return i == 0 ? IonType.DATAGRAM : this._container_state_stack[i - 1];
    }

    @Override // com.amazon.ion.IonTextReader, com.amazon.ion.IonReader
    public int getDepth() {
        int i = this._container_state_top;
        if (i <= 0) {
            return i;
        }
        IonType ionType = this._container_state_stack[0];
        IonType ionType2 = this._nesting_parent;
        int i2 = (ionType2 != null ? !ionType2.equals(ionType) : !IonType.DATAGRAM.equals(ionType)) ? i : i - 1;
        if (i2 == i) {
            System.err.println("so here's a case where we didn't subtract 1");
        }
        return i2;
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        if (getDepth() == 0 && is_in_struct_internal()) {
            return null;
        }
        String str = this._field_name;
        if (str != null || this._field_name_sid <= 0) {
            return str;
        }
        throw new UnknownSymbolException(this._field_name_sid);
    }

    final String getRawFieldName() {
        if (getDepth() == 0 && is_in_struct_internal()) {
            return null;
        }
        return this._field_name;
    }

    @Override // com.amazon.ion.IonTextReader, com.amazon.ion.IonReader
    public int getFieldId() {
        if (getDepth() == 0 && is_in_struct_internal()) {
            return -1;
        }
        return this._field_name_sid;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        if (getDepth() == 0 && is_in_struct_internal()) {
            return null;
        }
        String str = this._field_name;
        int fieldId = getFieldId();
        if (str == null && fieldId == -1) {
            return null;
        }
        return new SymbolTokenImpl(str, fieldId);
    }

    @Override // com.amazon.ion.IonReader
    public void stepIn() {
        IonType ionType = this._value_type;
        if (ionType == null || this._eof) {
            throw new IllegalStateException();
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        set_state(get_state_at_container_start(this._value_type));
        push_container_state(this._value_type);
        this._scanner.tokenIsFinished();
        try {
            finish_value(null);
            if (this._v.isNull()) {
                this._eof = true;
                this._has_next_called = true;
            }
            this._value_type = null;
        } catch (IOException e) {
            throw new IonException(e);
        }
    }

    @Override // com.amazon.ion.IonReader
    public void stepOut() {
        if (getDepth() < 1) {
            throw new IllegalStateException("Cannot stepOut any further, already at top level.");
        }
        try {
            finish_value(null);
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[getContainerType().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalStateException("Unexpected value type: " + this._value_type);
                        }
                    } else if (!this._eof) {
                        this._scanner.skip_over_list();
                    }
                } else if (!this._eof) {
                    this._scanner.skip_over_sexp();
                }
            } else if (!this._eof) {
                this._scanner.skip_over_struct();
            }
            pop_container_state();
            this._scanner.tokenIsFinished();
            try {
                finish_value(null);
                clear_value();
            } catch (IOException e) {
                throw new IonException(e);
            }
        } catch (IOException e2) {
            throw new IonException(e2);
        }
    }

    public static class IonReaderTextParsingException extends IonException {
        IonReaderTextParsingException(String str) {
            super(str);
        }

        IonReaderTextParsingException(String str, Exception exc) {
            super(str, exc);
        }
    }

    protected final void parse_error(String str) {
        throw new IonReaderTextParsingException("Syntax error" + this._scanner.input_position() + ": " + str);
    }

    protected final void parse_error(Exception exc) {
        throw new IonReaderTextParsingException("Syntax error at " + this._scanner.input_position() + ": " + exc.getLocalizedMessage(), exc);
    }
}
