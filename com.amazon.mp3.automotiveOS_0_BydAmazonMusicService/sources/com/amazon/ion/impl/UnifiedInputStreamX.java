package com.amazon.ion.impl;

import com.amazon.ion.impl.UnifiedInputBufferX;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: classes3.dex */
abstract class UnifiedInputStreamX implements Closeable {
    static int DEFAULT_PAGE_SIZE = 32768;
    UnifiedInputBufferX _buffer;
    byte[] _bytes;
    char[] _chars;
    boolean _eof;
    boolean _is_byte_data;
    boolean _is_stream;
    int _limit;
    int _max_char_value;
    int _pos;
    Reader _reader;
    UnifiedSavePointManagerX _save_points;
    InputStream _stream;

    private final void verify_matched_unread(int i) {
    }

    UnifiedInputStreamX() {
    }

    public static UnifiedInputStreamX makeStream(byte[] bArr, int i, int i2) {
        return new FromByteArray(bArr, i, i2);
    }

    public static UnifiedInputStreamX makeStream(InputStream inputStream) throws IOException {
        return new FromByteStream(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void init() {
        this._eof = false;
        this._max_char_value = this._buffer.maxValue();
        this._save_points = new UnifiedSavePointManagerX(this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._eof = true;
        this._buffer.clear();
    }

    public final boolean isEOF() {
        return this._eof;
    }

    public long getPosition() {
        UnifiedDataPageX currentPage = this._buffer.getCurrentPage();
        if (currentPage != null) {
            return currentPage.getFilePosition(this._pos);
        }
        return 0L;
    }

    public final UnifiedSavePointManagerX.SavePoint savePointAllocate() {
        return this._save_points.savePointAllocate();
    }

    protected final void save_point_reset_to_prev(UnifiedSavePointManagerX.SavePoint savePoint) {
        int prevIdx = savePoint.getPrevIdx();
        make_page_current(this._buffer.getPage(prevIdx), prevIdx, savePoint.getPrevPos(), savePoint.getPrevLimit());
    }

    protected final void make_page_current(UnifiedDataPageX unifiedDataPageX, int i, int i2, int i3) {
        this._limit = i3;
        this._pos = i2;
        this._eof = false;
        if (is_byte_data()) {
            this._bytes = unifiedDataPageX.getByteBuffer();
        } else {
            this._chars = unifiedDataPageX.getCharBuffer();
        }
        this._buffer.setCurrentPage(i, unifiedDataPageX);
        if (i2 > i3) {
            refill_is_eof();
        }
    }

    private final boolean is_byte_data() {
        return this._is_byte_data;
    }

    public final void unread(int i) {
        if (i == -1) {
            return;
        }
        if (i < 0 || i > this._max_char_value) {
            throw new IllegalArgumentException();
        }
        if (this._eof) {
            this._eof = false;
            if (this._limit == -1) {
                this._limit = this._pos;
            }
        }
        int i2 = this._pos - 1;
        this._pos = i2;
        if (i2 >= 0) {
            UnifiedDataPageX currentPage = this._buffer.getCurrentPage();
            if (this._pos < currentPage.getStartingOffset()) {
                currentPage.inc_unread_count();
                if (is_byte_data()) {
                    this._bytes[this._pos] = (byte) i;
                    return;
                } else {
                    this._chars[this._pos] = (char) i;
                    return;
                }
            }
            verify_matched_unread(i);
            return;
        }
        this._buffer.putCharAt(getPosition(), i);
    }

    public final int read() throws IOException {
        int i = this._pos;
        if (i >= this._limit) {
            return read_helper();
        }
        if (this._is_byte_data) {
            byte[] bArr = this._bytes;
            this._pos = i + 1;
            return bArr[i] & 255;
        }
        char[] cArr = this._chars;
        this._pos = i + 1;
        return cArr[i];
    }

    protected final int read_helper() throws IOException {
        if (this._eof || refill_helper()) {
            return -1;
        }
        if (is_byte_data()) {
            byte[] bArr = this._bytes;
            int i = this._pos;
            this._pos = i + 1;
            return bArr[i] & 255;
        }
        char[] cArr = this._chars;
        int i2 = this._pos;
        this._pos = i2 + 1;
        return cArr[i2];
    }

    private final boolean refill_helper() throws IOException {
        int iRefill = refill();
        this._limit = iRefill;
        if (this._pos < iRefill) {
            return false;
        }
        this._eof = true;
        return true;
    }

    public final void skip(int i) throws IOException {
        int i2;
        int i3 = this._limit;
        int i4 = this._pos;
        if (i3 - i4 < i) {
            i2 = i;
            while (i2 > 0) {
                int i5 = this._limit;
                int i6 = this._pos;
                int i7 = i5 - i6;
                if (i7 > i2) {
                    i7 = i2;
                }
                this._pos = i6 + i7;
                i2 -= i7;
                if (i2 > 0 && refill_helper()) {
                    break;
                }
            }
        } else {
            this._pos = i4 + i;
            i2 = 0;
        }
        if (i2 <= 0) {
            return;
        }
        throw new IOException("unexpected EOF encountered during skip of " + i + " at position " + getPosition());
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (!is_byte_data()) {
            throw new IOException("byte read is not support over character sources");
        }
        int i3 = i2;
        while (i3 > 0 && !isEOF()) {
            int i4 = this._limit;
            int i5 = this._pos;
            int i6 = i4 - i5;
            if (i6 > i3) {
                i6 = i3;
            }
            System.arraycopy(this._bytes, i5, bArr, i, i6);
            int i7 = this._pos + i6;
            this._pos = i7;
            i += i6;
            i3 -= i6;
            if (i3 == 0 || i7 < this._limit || refill_helper()) {
                break;
            }
        }
        return i2 - i3;
    }

    protected int refill() throws IOException {
        int i;
        int endIdx;
        UnifiedDataPageX currentPage = this._buffer.getCurrentPage();
        UnifiedSavePointManagerX.SavePoint savePointSavePointActiveTop = this._save_points.savePointActiveTop();
        if (!can_fill_new_page()) {
            return refill_is_eof();
        }
        if (savePointSavePointActiveTop != null && savePointSavePointActiveTop.getEndIdx() == this._buffer.getCurrentPageIdx()) {
            return refill_is_eof();
        }
        long j = 0;
        if (currentPage == null) {
            i = 0;
        } else {
            long filePosition = currentPage.getFilePosition(this._pos);
            i = filePosition == 0 ? 0 : 10;
            j = filePosition;
        }
        int nextFilledPageIdx = this._buffer.getNextFilledPageIdx();
        if (nextFilledPageIdx < 0) {
            UnifiedDataPageX currentPage2 = this._buffer.getCurrentPage();
            boolean z = currentPage2 == null;
            int currentPageIdx = this._buffer.getCurrentPageIdx();
            if (this._save_points.isSavePointOpen()) {
                currentPageIdx++;
                z = true;
            }
            if (z) {
                currentPage2 = this._buffer.getEmptyPageIdx();
            }
            if (load(currentPage2, i, j) < 1) {
                return refill_is_eof();
            }
            set_current_page(currentPageIdx, currentPage2, i);
        } else {
            if (savePointSavePointActiveTop != null && (endIdx = savePointSavePointActiveTop.getEndIdx()) != -1 && endIdx < nextFilledPageIdx) {
                return refill_is_eof();
            }
            UnifiedDataPageX page = this._buffer.getPage(nextFilledPageIdx);
            set_current_page(nextFilledPageIdx, page, page.getStartingOffset());
            if (savePointSavePointActiveTop != null && savePointSavePointActiveTop.getEndIdx() == nextFilledPageIdx) {
                this._limit = savePointSavePointActiveTop.getEndPos();
            }
        }
        return this._limit;
    }

    private void set_current_page(int i, UnifiedDataPageX unifiedDataPageX, int i2) {
        if (unifiedDataPageX != (i < this._buffer.getPageCount() ? this._buffer.getPage(i) : null)) {
            this._buffer.setPage(i, unifiedDataPageX, true);
        }
        make_page_current(unifiedDataPageX, i, i2, unifiedDataPageX.getBufferLimit());
    }

    private int refill_is_eof() {
        this._eof = true;
        this._limit = -1;
        return -1;
    }

    private final boolean can_fill_new_page() {
        return this._is_stream;
    }

    protected final int load(UnifiedDataPageX unifiedDataPageX, int i, long j) throws IOException {
        if (!can_fill_new_page()) {
            return 0;
        }
        if (is_byte_data()) {
            return unifiedDataPageX.load(this._stream, i, j);
        }
        return unifiedDataPageX.load(this._reader, i, j);
    }

    static class FromByteArray extends UnifiedInputStreamX {
        FromByteArray(byte[] bArr, int i, int i2) {
            this._is_byte_data = true;
            this._is_stream = false;
            UnifiedInputBufferX unifiedInputBufferXMakePageBuffer = UnifiedInputBufferX.makePageBuffer(bArr, i, i2);
            this._buffer = unifiedInputBufferXMakePageBuffer;
            make_page_current(unifiedInputBufferXMakePageBuffer.getCurrentPage(), 0, i, i2 + i);
            init();
        }
    }

    private static class FromByteStream extends UnifiedInputStreamX {
        FromByteStream(InputStream inputStream) throws IOException {
            this._is_byte_data = true;
            this._is_stream = true;
            this._stream = inputStream;
            this._buffer = UnifiedInputBufferX.makePageBuffer(UnifiedInputBufferX.BufferType.BYTES, UnifiedInputStreamX.DEFAULT_PAGE_SIZE);
            init();
            this._limit = refill();
        }

        @Override // com.amazon.ion.impl.UnifiedInputStreamX, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this._stream.close();
        }
    }
}
