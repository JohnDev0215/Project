package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import com.amazon.ion.impl._Private_ScalarConversions;
import com.amazon.ion.impl.bin.utf8.ByteBufferPool;
import com.amazon.ion.impl.bin.utf8.PoolableByteBuffer;
import com.amazon.ion.impl.bin.utf8.Utf8StringDecoder;
import com.amazon.ion.impl.bin.utf8.Utf8StringDecoderPool;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.nio.ByteBuffer;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes3.dex */
abstract class IonReaderBinaryRawX implements IonReader {
    private static final int BINARY_VERSION_MARKER_LEN;
    private static final int BINARY_VERSION_MARKER_TID;
    int _annotation_count;
    int[] _annotation_ids;
    UnifiedSavePointManagerX.SavePoint _annotations;
    long[] _container_stack;
    int _container_top;
    boolean _eof;
    boolean _has_next_needed;
    UnifiedInputStreamX _input;
    boolean _is_in_struct;
    int _local_remaining;
    int _parent_tid;
    long _position_len;
    long _position_start;
    State _state;
    boolean _struct_is_ordered;
    _Private_ScalarConversions.ValueVariant _v;
    int _value_field_id;
    boolean _value_is_null;
    boolean _value_is_true;
    int _value_len;
    boolean _value_lob_is_ready;
    int _value_lob_remaining;
    long _value_start;
    int _value_tid;
    IonType _value_type;
    private final Utf8StringDecoder utf8Decoder = (Utf8StringDecoder) Utf8StringDecoderPool.getInstance().getOrCreate();
    private final PoolableByteBuffer pooledUtf8InputBuffer = (PoolableByteBuffer) ByteBufferPool.getInstance().getOrCreate();

    protected enum State {
        S_INVALID,
        S_BEFORE_FIELD,
        S_BEFORE_TID,
        S_BEFORE_VALUE,
        S_AFTER_VALUE,
        S_EOF
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    @Deprecated
    public abstract /* synthetic */ int getFieldId();

    static {
        byte[] bArr = _Private_IonConstants.BINARY_VERSION_MARKER_1_0;
        BINARY_VERSION_MARKER_TID = _Private_IonConstants.getTypeCode(bArr[0] & 255);
        BINARY_VERSION_MARKER_LEN = _Private_IonConstants.getLowNibble(bArr[0] & 255);
    }

    protected IonReaderBinaryRawX() {
    }

    protected final void init_raw(UnifiedInputStreamX unifiedInputStreamX) {
        this._input = unifiedInputStreamX;
        this._container_stack = new long[12];
        this._annotations = unifiedInputStreamX.savePointAllocate();
        this._v = new _Private_ScalarConversions.ValueVariant();
        this._annotation_ids = new int[10];
        re_init_raw();
        this._position_start = -1L;
    }

    final void re_init_raw() {
        this._local_remaining = Integer.MIN_VALUE;
        this._parent_tid = 16;
        this._value_field_id = -1;
        this._state = State.S_BEFORE_TID;
        this._has_next_needed = true;
        this._eof = false;
        this._value_type = null;
        this._value_is_null = false;
        this._value_is_true = false;
        this._value_len = 0;
        this._value_start = 0L;
        this._value_lob_remaining = 0;
        this._value_lob_is_ready = false;
        this._annotation_count = 0;
        this._is_in_struct = false;
        this._struct_is_ordered = false;
        this._parent_tid = 0;
        this._container_top = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._input.close();
        this.utf8Decoder.close();
        this.pooledUtf8InputBuffer.close();
    }

    private final void push(int i, long j, int i2) {
        long[] jArr = this._container_stack;
        int length = jArr.length;
        if (this._container_top + 2 >= length) {
            long[] jArr2 = new long[length * 2];
            System.arraycopy(jArr, 0, jArr2, 0, length);
            this._container_stack = jArr2;
        }
        long[] jArr3 = this._container_stack;
        int i3 = this._container_top;
        jArr3[i3 + 0] = j;
        jArr3[i3 + 1] = (((long) i2) << 32) | (((long) i) & (-1));
        this._container_top = i3 + 2;
    }

    private final long get_top_position() {
        return this._container_stack[(this._container_top - 2) + 0];
    }

    private final int get_top_type() {
        int i = (int) (this._container_stack[(this._container_top - 2) + 1] & (-1));
        if (i < 0 || i > 16) {
            throwErrorAt("invalid type id in parent stack");
        }
        return i;
    }

    private final int get_top_local_remaining() {
        return (int) ((this._container_stack[(this._container_top - 2) + 1] >> 32) & (-1));
    }

    private final void pop() {
        this._container_top -= 2;
    }

    public boolean hasNext() {
        if (!this._eof && this._has_next_needed) {
            try {
                has_next_helper_raw();
            } catch (IOException e) {
                error(e);
            }
        }
        return !this._eof;
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        if (this._eof) {
            return null;
        }
        if (this._has_next_needed) {
            try {
                has_next_helper_raw();
            } catch (IOException e) {
                error(e);
            }
        }
        this._has_next_needed = true;
        return this._value_type;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x005f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0058 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void has_next_helper_raw() throws java.io.IOException {
        /*
            r15 = this;
            r15.clear_value()
        L3:
            int r0 = r15._value_tid
            r1 = 0
            r2 = -1
            if (r0 != r2) goto Lba
            boolean r0 = r15._eof
            if (r0 != 0) goto Lba
            int[] r0 = com.amazon.ion.impl.IonReaderBinaryRawX.AnonymousClass1.$SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State
            com.amazon.ion.impl.IonReaderBinaryRawX$State r3 = r15._state
            int r3 = r3.ordinal()
            r0 = r0[r3]
            r3 = 2
            r4 = 1
            if (r0 == r4) goto L41
            if (r0 == r3) goto L4c
            r1 = 3
            if (r0 == r1) goto L2c
            r1 = 4
            if (r0 == r1) goto L31
            r1 = 5
            if (r0 == r1) goto L3
            java.lang.String r0 = "internal error: raw binary reader in invalid state!"
            r15.error(r0)
            goto L3
        L2c:
            int r0 = r15._value_len
            r15.skip(r0)
        L31:
            boolean r0 = r15.isInStruct()
            if (r0 == 0) goto L3c
            com.amazon.ion.impl.IonReaderBinaryRawX$State r0 = com.amazon.ion.impl.IonReaderBinaryRawX.State.S_BEFORE_FIELD
            r15._state = r0
            goto L3
        L3c:
            com.amazon.ion.impl.IonReaderBinaryRawX$State r0 = com.amazon.ion.impl.IonReaderBinaryRawX.State.S_BEFORE_TID
            r15._state = r0
            goto L3
        L41:
            int r0 = r15.read_field_id()
            r15._value_field_id = r0
            if (r0 != r2) goto L4c
            r15._eof = r4
            goto L3
        L4c:
            com.amazon.ion.impl.IonReaderBinaryRawX$State r0 = com.amazon.ion.impl.IonReaderBinaryRawX.State.S_BEFORE_VALUE
            r15._state = r0
            int r0 = r15.read_type_id()
            r15._value_tid = r0
            if (r0 != r2) goto L5f
            com.amazon.ion.impl.IonReaderBinaryRawX$State r0 = com.amazon.ion.impl.IonReaderBinaryRawX.State.S_EOF
            r15._state = r0
            r15._eof = r4
            goto L3
        L5f:
            r2 = 99
            if (r0 != r2) goto L6c
            int r0 = r15._value_len
            r15.skip(r0)
            r15.clear_value()
            goto L3
        L6c:
            r2 = 14
            if (r0 != r2) goto Lb2
            int r0 = r15._value_len
            int r2 = com.amazon.ion.impl.IonReaderBinaryRawX.BINARY_VERSION_MARKER_LEN
            if (r0 != r2) goto L7e
            r15.load_version_marker()
            com.amazon.ion.IonType r0 = com.amazon.ion.IonType.SYMBOL
            r15._value_type = r0
            goto L3
        L7e:
            long r5 = r15._position_start
            long r7 = r15._position_len
            com.amazon.ion.IonType r0 = r15.load_annotation_start_with_value_type()
            r15._value_type = r0
            long r9 = r5 + r7
            long r11 = r15._position_start
            long r13 = r15._position_len
            long r11 = r11 + r13
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 != 0) goto L99
            r15._position_start = r5
            r15._position_len = r7
            goto L3
        L99:
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.Long r2 = java.lang.Long.valueOf(r9)
            r0[r1] = r2
            java.lang.Long r1 = java.lang.Long.valueOf(r11)
            r0[r4] = r1
            java.lang.String r1 = "Wrapper length mismatch: wrapper %s wrapped value %s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            com.amazon.ion.IonException r0 = r15.newErrorAt(r0)
            throw r0
        Lb2:
            com.amazon.ion.IonType r0 = r15.get_iontype_from_tid(r0)
            r15._value_type = r0
            goto L3
        Lba:
            r15._has_next_needed = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderBinaryRawX.has_next_helper_raw():void");
    }

    private final void load_version_marker() throws IOException {
        int i = 1;
        while (true) {
            byte[] bArr = _Private_IonConstants.BINARY_VERSION_MARKER_1_0;
            if (i < bArr.length) {
                if (read() != (bArr[i] & 255)) {
                    throwErrorAt("invalid binary image");
                }
                i++;
            } else {
                this._value_tid = 7;
                this._value_len = 0;
                this._value_start = 0L;
                this._v.setValue(2);
                this._v.setAuthoritativeType(3);
                this._value_is_null = false;
                this._value_lob_is_ready = false;
                this._annotations.clear();
                this._value_field_id = -1;
                this._state = State.S_AFTER_VALUE;
                return;
            }
        }
    }

    private final IonType load_annotation_start_with_value_type() throws IOException {
        int varUInt = readVarUInt();
        this._annotations.start(getPosition(), 0L);
        skip(varUInt);
        this._annotations.markEnd();
        int i = read_type_id();
        this._value_tid = i;
        if (i == 99) {
            throwErrorAt("NOP padding is not allowed within annotation wrappers.");
        }
        if (this._value_tid == -1) {
            throwErrorAt("unexpected EOF encountered where a type descriptor byte was expected");
        }
        if (this._value_tid == 14) {
            throwErrorAt("An annotation wrapper may not contain another annotation wrapper.");
        }
        return get_iontype_from_tid(this._value_tid);
    }

    protected final int load_annotations() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[this._state.ordinal()];
        if (i == 3 || i == 4) {
            if (this._annotations.isDefined()) {
                int i2 = this._local_remaining;
                this._input._save_points.savePointPushActive(this._annotations, getPosition(), 0L);
                this._local_remaining = Integer.MIN_VALUE;
                this._annotation_count = 0;
                do {
                    try {
                        int varUIntOrEOF = readVarUIntOrEOF();
                        if (varUIntOrEOF == -1) {
                            break;
                        }
                        load_annotation_append(varUIntOrEOF);
                    } catch (IOException e) {
                        error(e);
                    }
                } while (!isEOF());
                this._input._save_points.savePointPopActive(this._annotations);
                this._local_remaining = i2;
                this._annotations.clear();
            }
            return this._annotation_count;
        }
        throw new IllegalStateException("annotations require the value to be ready");
    }

    private final void load_annotation_append(int i) {
        int[] iArr = this._annotation_ids;
        int length = iArr.length;
        if (this._annotation_count >= length) {
            int[] iArr2 = new int[length * 2];
            System.arraycopy(iArr, 0, iArr2, 0, length);
            this._annotation_ids = iArr2;
        }
        int[] iArr3 = this._annotation_ids;
        int i2 = this._annotation_count;
        this._annotation_count = i2 + 1;
        iArr3[i2] = i;
    }

    private final void clear_value() {
        this._value_type = null;
        this._value_tid = -1;
        this._value_is_null = false;
        this._value_lob_is_ready = false;
        this._annotations.clear();
        this._v.clear();
        this._annotation_count = 0;
        this._value_field_id = -1;
    }

    private final int read_field_id() throws IOException {
        return readVarUIntOrEOF();
    }

    private final int read_type_id() throws IOException {
        long position = this._input.getPosition();
        long position2 = 1 + position;
        int i = read();
        if (i < 0) {
            return -1;
        }
        int typeCode = _Private_IonConstants.getTypeCode(i);
        int lowNibble = _Private_IonConstants.getLowNibble(i);
        if (typeCode == 0 && lowNibble != 15) {
            if (lowNibble == 14) {
                lowNibble = readVarUInt();
            }
            this._state = this._is_in_struct ? State.S_BEFORE_FIELD : State.S_BEFORE_TID;
            typeCode = 99;
        } else if (lowNibble == 14) {
            lowNibble = readVarUInt();
            position2 = this._input.getPosition();
        } else {
            if (typeCode == 0 || lowNibble == 15) {
                this._value_is_null = true;
                this._state = State.S_AFTER_VALUE;
            } else if (typeCode == 1) {
                if (lowNibble == 0) {
                    this._value_is_true = false;
                } else if (lowNibble == 1) {
                    this._value_is_true = true;
                } else {
                    throwErrorAt("invalid length nibble in boolean value: " + lowNibble);
                }
                this._state = State.S_AFTER_VALUE;
            } else if (typeCode == 13) {
                boolean z = lowNibble == 1;
                this._struct_is_ordered = z;
                if (z) {
                    lowNibble = readVarUInt();
                    if (lowNibble == 0) {
                        throwErrorAt("Structs flagged as having ordered keys must contain at least one key/value pair.");
                    }
                    position2 = this._input.getPosition();
                }
            }
            lowNibble = 0;
        }
        this._value_tid = typeCode;
        this._value_len = lowNibble;
        this._value_start = position2;
        this._position_len = ((long) lowNibble) + (position2 - position);
        this._position_start = position;
        return typeCode;
    }

    private final IonType get_iontype_from_tid(int i) {
        switch (i) {
            case 0:
                return IonType.NULL;
            case 1:
                return IonType.BOOL;
            case 2:
            case 3:
                return IonType.INT;
            case 4:
                return IonType.FLOAT;
            case 5:
                return IonType.DECIMAL;
            case 6:
                return IonType.TIMESTAMP;
            case 7:
                return IonType.SYMBOL;
            case 8:
                return IonType.STRING;
            case 9:
                return IonType.CLOB;
            case 10:
                return IonType.BLOB;
            case 11:
                return IonType.LIST;
            case 12:
                return IonType.SEXP;
            case 13:
                return IonType.STRUCT;
            case 14:
                return null;
            default:
                throw newErrorAt("unrecognized value type encountered: " + i);
        }
    }

    @Override // com.amazon.ion.IonReader
    public void stepIn() {
        IonType ionType = this._value_type;
        if (ionType == null || this._eof) {
            throw new IllegalStateException();
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalStateException();
        }
        if (this._value_is_null) {
            State state = this._state;
            State state2 = State.S_AFTER_VALUE;
        } else {
            State state3 = this._state;
            State state4 = State.S_BEFORE_VALUE;
        }
        long position = getPosition();
        int i2 = this._value_len;
        long j = position + ((long) i2);
        int i3 = this._local_remaining;
        if (i3 != Integer.MIN_VALUE && (i3 = i3 - i2) < 0) {
            i3 = 0;
        }
        push(this._parent_tid, j, i3);
        int i4 = this._value_tid;
        boolean z = i4 == 13;
        this._is_in_struct = z;
        this._local_remaining = this._value_len;
        this._state = z ? State.S_BEFORE_FIELD : State.S_BEFORE_TID;
        this._parent_tid = i4;
        clear_value();
        this._has_next_needed = true;
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderBinaryRawX$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRUCT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[State.values().length];
            $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State = iArr2;
            try {
                iArr2[State.S_BEFORE_FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[State.S_BEFORE_TID.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[State.S_BEFORE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[State.S_AFTER_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[State.S_EOF.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    @Override // com.amazon.ion.IonReader
    public void stepOut() {
        if (getDepth() < 1) {
            throw new IllegalStateException("Cannot stepOut any further, already at top level.");
        }
        long j = get_top_position();
        int i = get_top_local_remaining();
        int i2 = get_top_type();
        pop();
        this._eof = false;
        this._parent_tid = i2;
        if (i2 == 13) {
            this._is_in_struct = true;
            this._state = State.S_BEFORE_FIELD;
        } else {
            this._is_in_struct = false;
            this._state = State.S_BEFORE_TID;
        }
        this._has_next_needed = true;
        clear_value();
        long position = getPosition();
        if (j > position) {
            long j2 = j - position;
            while (true) {
                long j3 = 2147483646;
                if (j2 <= j3) {
                    break;
                }
                try {
                    skip(2147483646);
                    j2 -= j3;
                } catch (IOException e) {
                    error(e);
                }
                error(e);
            }
            if (j2 > 0) {
                skip((int) j2);
            }
        } else if (j < position) {
            error("invalid position during stepOut, current position " + position + " next value at " + j);
        }
        this._local_remaining = i;
    }

    public int byteSize() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()];
        if (i != 4 && i != 5) {
            throw new IllegalStateException("only valid for LOB values");
        }
        if (!this._value_lob_is_ready) {
            this._value_lob_remaining = this._value_is_null ? 0 : this._value_len;
            this._value_lob_is_ready = true;
        }
        return this._value_lob_remaining;
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        int iByteSize = byteSize();
        if (this._value_is_null) {
            return null;
        }
        byte[] bArr = new byte[iByteSize];
        getBytes(bArr, 0, iByteSize);
        return bArr;
    }

    public int getBytes(byte[] bArr, int i, int i2) {
        int iByteSize = byteSize();
        if (iByteSize <= i2) {
            i2 = iByteSize;
        }
        return readBytes(bArr, i, i2);
    }

    public int readBytes(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 < 1) {
            return 0;
        }
        try {
            i3 = read(bArr, i, i2);
            this._value_lob_remaining -= i3;
        } catch (IOException e) {
            error(e);
            i3 = -1;
        }
        int i4 = this._value_lob_remaining;
        if (i4 == 0) {
            this._state = State.S_AFTER_VALUE;
        } else {
            this._value_len = i4;
        }
        return i3;
    }

    @Override // com.amazon.ion.IonReader
    public int getDepth() {
        return this._container_top / 2;
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        return this._value_type;
    }

    public boolean isInStruct() {
        return this._is_in_struct;
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        return this._value_is_null;
    }

    private final int read() throws IOException {
        int i = this._local_remaining;
        if (i != Integer.MIN_VALUE) {
            if (i < 1) {
                return -1;
            }
            this._local_remaining = i - 1;
        }
        return this._input.read();
    }

    private final int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null || i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i3 = this._local_remaining;
        if (i3 == Integer.MIN_VALUE) {
            return this._input.read(bArr, i, i2);
        }
        if (i2 > i3) {
            if (i3 < 1) {
                throwUnexpectedEOFException();
            }
            i2 = this._local_remaining;
        }
        int i4 = this._input.read(bArr, i, i2);
        this._local_remaining -= i4;
        return i4;
    }

    public void readAll(byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int i3 = read(bArr, i, i2);
            if (i3 <= 0) {
                throwUnexpectedEOFException();
            }
            i2 -= i3;
            i += i3;
        }
    }

    private final boolean isEOF() {
        int i = this._local_remaining;
        if (i > 0) {
            return false;
        }
        if (i == Integer.MIN_VALUE) {
            return this._input.isEOF();
        }
        return true;
    }

    private final long getPosition() {
        return this._input.getPosition();
    }

    private final void skip(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        int i2 = this._local_remaining;
        if (i2 == Integer.MIN_VALUE) {
            this._input.skip(i);
            return;
        }
        if (i > i2) {
            if (i2 < 1) {
                throwUnexpectedEOFException();
            }
            i = this._local_remaining;
        }
        this._input.skip(i);
        this._local_remaining -= i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final long readULong(int r6) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            r2 = 8
            switch(r6) {
                case 0: goto L6f;
                case 1: goto L63;
                case 2: goto L57;
                case 3: goto L4b;
                case 4: goto L3f;
                case 5: goto L33;
                case 6: goto L27;
                case 7: goto L1b;
                case 8: goto L10;
                default: goto L7;
            }
        L7:
            com.amazon.ion.IonException r6 = new com.amazon.ion.IonException
            java.lang.String r0 = "value too large for Java long"
            r6.<init>(r0)
            throw r6
        L10:
            int r6 = r5.read()
            if (r6 >= 0) goto L19
            r5.throwUnexpectedEOFException()
        L19:
            long r3 = (long) r6
            long r0 = r0 | r3
        L1b:
            int r6 = r5.read()
            if (r6 >= 0) goto L24
            r5.throwUnexpectedEOFException()
        L24:
            long r0 = r0 << r2
            long r3 = (long) r6
            long r0 = r0 | r3
        L27:
            int r6 = r5.read()
            if (r6 >= 0) goto L30
            r5.throwUnexpectedEOFException()
        L30:
            long r0 = r0 << r2
            long r3 = (long) r6
            long r0 = r0 | r3
        L33:
            int r6 = r5.read()
            if (r6 >= 0) goto L3c
            r5.throwUnexpectedEOFException()
        L3c:
            long r0 = r0 << r2
            long r3 = (long) r6
            long r0 = r0 | r3
        L3f:
            int r6 = r5.read()
            if (r6 >= 0) goto L48
            r5.throwUnexpectedEOFException()
        L48:
            long r0 = r0 << r2
            long r3 = (long) r6
            long r0 = r0 | r3
        L4b:
            int r6 = r5.read()
            if (r6 >= 0) goto L54
            r5.throwUnexpectedEOFException()
        L54:
            long r0 = r0 << r2
            long r3 = (long) r6
            long r0 = r0 | r3
        L57:
            int r6 = r5.read()
            if (r6 >= 0) goto L60
            r5.throwUnexpectedEOFException()
        L60:
            long r0 = r0 << r2
            long r3 = (long) r6
            long r0 = r0 | r3
        L63:
            int r6 = r5.read()
            if (r6 >= 0) goto L6c
            r5.throwUnexpectedEOFException()
        L6c:
            long r0 = r0 << r2
            long r2 = (long) r6
            long r0 = r0 | r2
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderBinaryRawX.readULong(int):long");
    }

    protected final BigInteger readBigInteger(int i, boolean z) throws IOException {
        if (i > 0) {
            byte[] bArr = new byte[i];
            readAll(bArr, 0, i);
            return new BigInteger(z ? -1 : 1, bArr);
        }
        return BigInteger.ZERO;
    }

    protected final int readVarInt() throws IOException {
        return readVarInt(read());
    }

    protected final Integer readVarInteger() throws IOException {
        int i = read();
        if (i == 192) {
            return null;
        }
        return Integer.valueOf(readVarInt(i));
    }

    private int readVarInt(int i) throws IOException {
        long j;
        boolean z = false;
        while (true) {
            if (i < 0) {
                throwUnexpectedEOFException();
            }
            if ((i & 64) != 0) {
                z = true;
            }
            j = i & 63;
            if ((i & 128) != 0) {
                break;
            }
            int i2 = read();
            if (i2 < 0) {
                throwUnexpectedEOFException();
            }
            j = (j << 7) | ((long) (i2 & WorkQueueKt.MASK));
            if ((i2 & 128) != 0) {
                break;
            }
            int i3 = read();
            if (i3 < 0) {
                throwUnexpectedEOFException();
            }
            j = (j << 7) | ((long) (i3 & WorkQueueKt.MASK));
            if ((i3 & 128) != 0) {
                break;
            }
            int i4 = read();
            if (i4 < 0) {
                throwUnexpectedEOFException();
            }
            j = (j << 7) | ((long) (i4 & WorkQueueKt.MASK));
            if ((i4 & 128) != 0) {
                break;
            }
            i = read();
            if (i < 0) {
                throwUnexpectedEOFException();
            }
            j = (j << 7) | ((long) (i & WorkQueueKt.MASK));
            if ((i & 128) != 0) {
                break;
            }
            throwVarIntOverflowException();
        }
        if (z) {
            j = -j;
        }
        int i5 = (int) j;
        if (j != i5) {
            throwVarIntOverflowException();
        }
        return i5;
    }

    protected final int readVarUIntOrEOF() throws IOException {
        long j = 0;
        while (true) {
            int i = read();
            if (i >= 0) {
                j = (j << 7) | ((long) (i & WorkQueueKt.MASK));
                if ((i & 128) != 0) {
                    break;
                }
                int i2 = read();
                if (i2 < 0) {
                    throwUnexpectedEOFException();
                }
                j = (j << 7) | ((long) (i2 & WorkQueueKt.MASK));
                if ((i2 & 128) != 0) {
                    break;
                }
                int i3 = read();
                if (i3 < 0) {
                    throwUnexpectedEOFException();
                }
                j = (j << 7) | ((long) (i3 & WorkQueueKt.MASK));
                if ((i3 & 128) != 0) {
                    break;
                }
                int i4 = read();
                if (i4 < 0) {
                    throwUnexpectedEOFException();
                }
                j = (j << 7) | ((long) (i4 & WorkQueueKt.MASK));
                if ((i4 & 128) != 0) {
                    break;
                }
                int i5 = read();
                if (i5 < 0) {
                    throwUnexpectedEOFException();
                }
                j = (j << 7) | ((long) (i5 & WorkQueueKt.MASK));
                if ((i5 & 128) != 0) {
                    break;
                }
                throwVarIntOverflowException();
            } else {
                return -1;
            }
        }
        int i6 = (int) j;
        if (j != i6) {
            throwVarIntOverflowException();
        }
        return i6;
    }

    protected final int readVarUInt() throws IOException {
        int varUIntOrEOF = readVarUIntOrEOF();
        if (varUIntOrEOF == -1) {
            throwUnexpectedEOFException();
        }
        return varUIntOrEOF;
    }

    protected final double readFloat(int i) throws IOException {
        if (i == 0) {
            return 0.0d;
        }
        if (i != 4 && i != 8) {
            throw new IOException("Length of float read must be 0, 4, or 8");
        }
        long uLong = readULong(i);
        if (i == 4) {
            return Float.intBitsToFloat((int) (uLong & 4294967295L));
        }
        return Double.longBitsToDouble(uLong);
    }

    protected final Decimal readDecimal(int i) throws IOException {
        BigInteger bigInteger;
        Decimal decimalValueOf;
        MathContext mathContext = MathContext.UNLIMITED;
        int i2 = 0;
        if (i == 0) {
            return Decimal.valueOf(0, mathContext);
        }
        int i3 = this._local_remaining;
        int i4 = i3 != Integer.MIN_VALUE ? i3 - i : Integer.MIN_VALUE;
        this._local_remaining = i;
        int varInt = readVarInt();
        int i5 = this._local_remaining;
        if (i5 > 0) {
            byte[] bArr = new byte[i5];
            readAll(bArr, 0, i5);
            byte b = bArr[0];
            if (b < 0) {
                bArr[0] = (byte) (b & 127);
                i2 = -1;
            } else {
                i2 = 1;
            }
            bigInteger = new BigInteger(i2, bArr);
        } else {
            bigInteger = BigInteger.ZERO;
        }
        int i6 = -varInt;
        if (bigInteger.signum() == 0 && i2 == -1) {
            decimalValueOf = Decimal.negativeZero(i6, mathContext);
        } else {
            decimalValueOf = Decimal.valueOf(bigInteger, i6, mathContext);
        }
        this._local_remaining = i4;
        return decimalValueOf;
    }

    protected final Timestamp readTimestamp(int i) throws IOException {
        Decimal decimal;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Timestamp.Precision precision;
        Decimal decimal2 = null;
        if (i < 1) {
            return null;
        }
        int i7 = this._local_remaining;
        int i8 = i7 != Integer.MIN_VALUE ? i7 - i : Integer.MIN_VALUE;
        this._local_remaining = i;
        Integer varInteger = readVarInteger();
        int varUInt = readVarUInt();
        Timestamp.Precision precision2 = Timestamp.Precision.YEAR;
        int varUInt2 = 0;
        if (this._local_remaining > 0) {
            int varUInt3 = readVarUInt();
            Timestamp.Precision precision3 = Timestamp.Precision.MONTH;
            if (this._local_remaining > 0) {
                int varUInt4 = readVarUInt();
                Timestamp.Precision precision4 = Timestamp.Precision.DAY;
                if (this._local_remaining > 0) {
                    int varUInt5 = readVarUInt();
                    int varUInt6 = readVarUInt();
                    Timestamp.Precision precision5 = Timestamp.Precision.MINUTE;
                    if (this._local_remaining > 0) {
                        varUInt2 = readVarUInt();
                        precision5 = Timestamp.Precision.SECOND;
                        int i9 = this._local_remaining;
                        if (i9 > 0) {
                            decimal2 = readDecimal(i9);
                            if (decimal2.compareTo(BigDecimal.ZERO) < 0 || decimal2.compareTo(BigDecimal.ONE) >= 0) {
                                throwErrorAt("The fractional seconds value in a timestamp must be greater than or equal to zero and less than one.");
                            }
                        }
                    }
                    decimal = decimal2;
                    i6 = varUInt2;
                    i5 = varUInt6;
                    precision = precision5;
                    i3 = varUInt4;
                    i4 = varUInt5;
                } else {
                    decimal = null;
                    i3 = varUInt4;
                    i4 = 0;
                    i5 = 0;
                    i6 = 0;
                    precision = precision4;
                }
                i2 = varUInt3;
            } else {
                i2 = varUInt3;
                decimal = null;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                precision = precision3;
            }
        } else {
            decimal = null;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            precision = precision2;
        }
        this._local_remaining = i8;
        try {
            return Timestamp.createFromUtcFields(precision, varUInt, i2, i3, i4, i5, i6, decimal, varInteger);
        } catch (IllegalArgumentException e) {
            throw newErrorAt("Invalid timestamp encoding: " + e.getMessage());
        }
    }

    protected final String readString(int i) throws IOException {
        ByteBuffer buffer = this.pooledUtf8InputBuffer.getBuffer();
        if (i <= buffer.capacity()) {
            return readStringWithReusableBuffer(i, buffer);
        }
        this.utf8Decoder.prepareDecode(i);
        int i2 = this._local_remaining;
        int i3 = i2 != Integer.MIN_VALUE ? i2 - i : Integer.MIN_VALUE;
        this._local_remaining = i;
        int i4 = 0;
        int iRemaining = 0;
        while (i4 < i) {
            int i5 = read(buffer.array(), iRemaining, Math.min(i - i4, buffer.array().length - iRemaining));
            if (i5 <= 0) {
                throwUnexpectedEOFException();
            }
            i4 += i5;
            buffer.position(0);
            buffer.limit(iRemaining + i5);
            this.utf8Decoder.partialDecode(buffer, i4 >= i);
            iRemaining = buffer.remaining();
            if (iRemaining > 0) {
                System.arraycopy(buffer.array(), buffer.position(), buffer.array(), 0, iRemaining);
            }
        }
        this._local_remaining = i3;
        return this.utf8Decoder.finishDecode();
    }

    private String readStringWithReusableBuffer(int i, ByteBuffer byteBuffer) throws IOException {
        int i2 = this._local_remaining;
        int i3 = i2 != Integer.MIN_VALUE ? i2 - i : Integer.MIN_VALUE;
        this._local_remaining = i;
        readAll(byteBuffer.array(), 0, i);
        this._local_remaining = i3;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        return this.utf8Decoder.decode(byteBuffer, i);
    }

    private final void throwUnexpectedEOFException() throws IOException {
        throwErrorAt("unexpected EOF in value");
    }

    private final void throwVarIntOverflowException() throws IOException {
        throwErrorAt("int in stream is too long for a Java int 32");
    }

    protected IonException newErrorAt(String str) {
        return new IonException(str + " at position " + getPosition());
    }

    protected void throwErrorAt(String str) {
        throw newErrorAt(str);
    }

    protected void error(String str) {
        throw new IonException(str);
    }

    protected void error(Exception exc) {
        throw new IonException(exc);
    }
}
