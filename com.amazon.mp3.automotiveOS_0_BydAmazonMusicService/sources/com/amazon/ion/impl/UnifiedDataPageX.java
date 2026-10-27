package com.amazon.ion.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: classes3.dex */
abstract class UnifiedDataPageX {
    protected int _base_offset;
    protected byte[] _bytes;
    protected char[] _characters;
    protected long _file_offset;
    protected int _page_limit;
    protected PageType _page_type;
    protected int _unread_count;

    public enum PageType {
        BYTES,
        CHARS
    }

    public abstract void putValue(int i, int i2);

    private UnifiedDataPageX() {
    }

    public final char[] getCharBuffer() {
        return this._characters;
    }

    public final byte[] getByteBuffer() {
        return this._bytes;
    }

    private final boolean isBytes() {
        return this._page_type == PageType.BYTES;
    }

    int load(Reader reader, int i, long j) throws IOException {
        if (isBytes()) {
            throw new UnsupportedOperationException("byte pages can't load characters");
        }
        char[] cArr = this._characters;
        int i2 = reader.read(cArr, i, cArr.length - i);
        if (i2 > 0) {
            this._page_limit = i + i2;
            this._base_offset = i;
            this._unread_count = 0;
            setFilePosition(j, i);
        }
        return i2;
    }

    int load(InputStream inputStream, int i, long j) throws IOException {
        if (!isBytes()) {
            throw new UnsupportedOperationException("character pages can't load bytes");
        }
        byte[] bArr = this._bytes;
        int i2 = inputStream.read(bArr, i, bArr.length - i);
        if (i2 > 0) {
            this._base_offset = i;
            this._unread_count = 0;
            this._page_limit = i + i2;
            setFilePosition(j, i);
        }
        return i2;
    }

    public int getBufferLimit() {
        return this._page_limit;
    }

    public int getOriginalStartingOffset() {
        return this._base_offset;
    }

    public int getStartingOffset() {
        return this._base_offset - this._unread_count;
    }

    public int getUnreadCount() {
        return this._unread_count;
    }

    public void inc_unread_count() {
        this._unread_count++;
    }

    public final void setFilePosition(long j, int i) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        this._file_offset = j - ((long) i);
    }

    public final long getFilePosition(int i) {
        return this._file_offset + ((long) i);
    }

    public final long getStartingFileOffset() {
        return this._file_offset + ((long) this._base_offset);
    }

    public final boolean containsOffset(long j) {
        long j2 = this._file_offset;
        return ((long) this._base_offset) + j2 <= j && j < j2 + ((long) this._page_limit);
    }

    public final void reset(int i) {
        this._base_offset = i;
        this._page_limit = i;
    }

    public static final class Bytes extends UnifiedDataPageX {
        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getBufferLimit() {
            return super.getBufferLimit();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getOriginalStartingOffset() {
            return super.getOriginalStartingOffset();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getStartingOffset() {
            return super.getStartingOffset();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getUnreadCount() {
            return super.getUnreadCount();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ void inc_unread_count() {
            super.inc_unread_count();
        }

        public Bytes(int i) {
            this(new byte[i], 0, i);
        }

        public Bytes(byte[] bArr, int i, int i2) {
            super();
            this._page_type = PageType.BYTES;
            this._bytes = bArr;
            this._base_offset = i;
            this._page_limit = i + i2;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public void putValue(int i, int i2) {
            this._bytes[this._base_offset] = (byte) i2;
        }
    }

    public static final class Chars extends UnifiedDataPageX {
        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getBufferLimit() {
            return super.getBufferLimit();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getOriginalStartingOffset() {
            return super.getOriginalStartingOffset();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getStartingOffset() {
            return super.getStartingOffset();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ int getUnreadCount() {
            return super.getUnreadCount();
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public /* bridge */ /* synthetic */ void inc_unread_count() {
            super.inc_unread_count();
        }

        public Chars(int i) {
            this(new char[i], 0, i);
        }

        public Chars(char[] cArr, int i, int i2) {
            super();
            this._page_type = PageType.CHARS;
            this._characters = cArr;
            this._base_offset = i;
            this._page_limit = i + i2;
        }

        @Override // com.amazon.ion.impl.UnifiedDataPageX
        public void putValue(int i, int i2) {
            this._characters[i] = (char) i2;
        }
    }
}
