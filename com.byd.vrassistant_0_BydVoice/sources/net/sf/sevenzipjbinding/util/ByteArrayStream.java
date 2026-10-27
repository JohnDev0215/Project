package net.sf.sevenzipjbinding.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import net.sf.sevenzipjbinding.IInStream;
import net.sf.sevenzipjbinding.IOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public class ByteArrayStream implements IInStream, IOutStream {
    private static final int INPUT_STREAM_READ_BUFFER_SIZE = 16384;
    private static final int MAX_CHUNK_SIZE = 1048576;
    private final List<byte[]> chunkList;
    private int currentChunkIndex;
    private int currentPosition;
    private int currentPositionInChunk;
    private final int initialSize;
    private final int maxSize;
    private int seekToPosition;
    private int size;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public ByteArrayStream(byte[] bArr, boolean z, int i) {
        this(1024, i);
        setBytes(bArr, z);
    }

    public ByteArrayStream(byte[] bArr, boolean z) {
        this(1024 > bArr.length ? bArr.length : 1024, bArr.length);
        setBytes(bArr, z);
    }

    public ByteArrayStream(int i) {
        this(1024, i);
    }

    public ByteArrayStream(int i, int i2) {
        this.chunkList = new ArrayList();
        this.initialSize = i;
        if (i2 < 0) {
            throw new IllegalStateException("Maximal size of the byte array stream should be >0");
        }
        if (i < 0) {
            throw new IllegalStateException("Initial size of the byte array stream should be >0");
        }
        this.maxSize = i2;
        init();
    }

    private void init() {
        this.chunkList.clear();
        this.currentPosition = 0;
        this.currentPositionInChunk = 0;
        this.currentChunkIndex = -1;
        this.size = 0;
        this.seekToPosition = -1;
    }

    @Override // net.sf.sevenzipjbinding.ISequentialInStream
    public int read(byte[] bArr) throws SevenZipException {
        return read(bArr, 0, bArr.length);
    }

    public synchronized int read(byte[] bArr, int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            if (bArr.length >= i + i2) {
                if (this.seekToPosition > this.size) {
                    return 0;
                }
                performDelayedSeek();
                int i3 = this.currentPosition;
                int i4 = i3 + i2;
                int i5 = this.size;
                if (i4 > i5) {
                    i2 = i5 - i3;
                }
                int i6 = i2;
                while (i6 > 0) {
                    int length = this.chunkList.get(this.currentChunkIndex).length;
                    int i7 = length - this.currentPositionInChunk;
                    if (i6 <= i7) {
                        i7 = i6;
                    }
                    System.arraycopy(this.chunkList.get(this.currentChunkIndex), this.currentPositionInChunk, bArr, i, i7);
                    i += i7;
                    int i8 = this.currentPositionInChunk + i7;
                    this.currentPositionInChunk = i8;
                    i6 -= i7;
                    if (i8 >= length && this.currentChunkIndex < this.chunkList.size() - 1) {
                        int i9 = this.currentChunkIndex + 1;
                        this.currentChunkIndex = i9;
                        this.currentPositionInChunk = 0;
                        int length2 = this.chunkList.get(i9).length;
                    }
                }
                this.currentPosition += i2;
                return i2;
            }
        }
        throw new IllegalStateException("Invalid start position (" + i + ") and length (" + i2 + ")");
    }

    public synchronized boolean isEOF() {
        return getCurrentPosition() >= this.size;
    }

    @Override // net.sf.sevenzipjbinding.ISeekableStream
    public synchronized long seek(long j, int i) throws SevenZipException {
        int i2;
        try {
            if (i != 0) {
                if (i == 1) {
                    i2 = this.seekToPosition;
                    if (i2 == -1) {
                        i2 = this.currentPosition;
                    }
                } else if (i == 2) {
                    i2 = this.size;
                } else {
                    throw new SevenZipException("Seek: unknown origin: " + i);
                }
                j += (long) i2;
            }
            if (j > this.maxSize) {
                throw new RuntimeException("Maximal size of the byte array stream was reached by seek to " + j + ", maximal size is " + this.maxSize + " bytes");
            }
            this.seekToPosition = (int) j;
        } catch (Throwable th) {
            throw th;
        }
        return j;
    }

    public synchronized void rewind() {
        this.seekToPosition = 0;
    }

    @Override // net.sf.sevenzipjbinding.IOutStream
    public synchronized void setSize(long j) {
        setSize(j, false);
    }

    private synchronized void setSize(long j, boolean z) {
        if (j == 0) {
            truncate();
            return;
        }
        if (j > this.maxSize) {
            throw new RuntimeException("Maximal size of the byte array stream was reached by setSize(" + j + "). Maximal size is " + this.maxSize + " bytes");
        }
        int i = this.size;
        if (j > i) {
            if (i == 0) {
                int i2 = (int) j;
                this.chunkList.add(new byte[i2]);
                this.currentChunkIndex = 0;
                this.size = i2;
            }
            int i3 = (int) (j - ((long) this.size));
            int length = 0;
            for (int i4 = 0; i4 < this.chunkList.size(); i4++) {
                length += this.chunkList.get(i4).length;
            }
            int i5 = this.size;
            int i6 = length - i5;
            if (i6 < i3) {
                this.size = i5 + i6;
                int i7 = i3 - i6;
                allocateNextChunk(i7);
                if (z) {
                    this.currentPositionInChunk = i7;
                }
            } else if (z) {
                this.currentPositionInChunk = (this.chunkList.get(r1.size() - 1).length - i6) + i3;
            }
            if (z) {
                this.currentChunkIndex = this.chunkList.size() - 1;
                this.currentPosition = (int) j;
            }
            this.size = (int) j;
        }
        if (j < this.size) {
            int length2 = 0;
            for (int i8 = 0; i8 < this.chunkList.size(); i8++) {
                length2 += this.chunkList.get(i8).length;
                if (length2 >= j) {
                    for (int size = this.chunkList.size() - 1; size > i8; size--) {
                        this.chunkList.remove(size);
                    }
                    int i9 = this.seekToPosition;
                    if (i9 != -1 && i9 > j) {
                        this.seekToPosition = (int) j;
                    } else if (this.currentPosition > j) {
                        int i10 = (int) j;
                        this.currentPosition = i10;
                        this.currentPositionInChunk = (i10 - length2) + this.chunkList.get(i8).length;
                        this.currentChunkIndex = i8;
                    }
                    this.size = (int) j;
                }
            }
        }
    }

    @Override // net.sf.sevenzipjbinding.ISequentialOutStream
    public int write(byte[] bArr) {
        return write(bArr, 0, bArr.length);
    }

    public synchronized int write(byte[] bArr, int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            if (bArr.length >= i + i2) {
                if (i2 == 0) {
                    return 0;
                }
                performDelayedSeek();
                startWriting();
                do {
                    byte[] bArr2 = this.chunkList.get(this.currentChunkIndex);
                    int length = bArr2.length;
                    int i3 = this.currentPositionInChunk;
                    int i4 = length - i3;
                    if (i4 >= i2) {
                        i4 = i2;
                    }
                    System.arraycopy(bArr, i, bArr2, i3, i4);
                    int i5 = this.currentPositionInChunk + i4;
                    this.currentPositionInChunk = i5;
                    this.currentPosition += i4;
                    i += i4;
                    i2 -= i4;
                    if (i5 >= bArr2.length) {
                        this.currentPositionInChunk = 0;
                        int i6 = this.currentChunkIndex + 1;
                        this.currentChunkIndex = i6;
                        if (i6 >= this.chunkList.size()) {
                            allocateNextChunk(-1);
                        }
                    }
                } while (i2 > 0);
                int i7 = this.currentPosition;
                if (i7 > this.size) {
                    this.size = i7;
                }
                endWriting();
                return bArr.length;
            }
        }
        throw new IllegalStateException("Invalid start position (" + i + ") and length (" + i2 + ")");
    }

    public InputStream getDetachedInputStream() {
        throw new IllegalStateException("Not implemented");
    }

    public InputStream getInputStream() {
        throw new IllegalStateException("Not implemented");
    }

    public OutputStream getOutputStream() {
        throw new IllegalStateException("Not implemented");
    }

    public synchronized void writeToOutputStream(OutputStream outputStream, boolean z) throws IOException {
        try {
            int i = 0;
            for (byte[] bArr : this.chunkList) {
                int length = bArr.length + i;
                int i2 = this.size;
                int length2 = length > i2 ? i2 - i : bArr.length;
                outputStream.write(bArr, 0, length2);
                i += length2;
            }
            if (z) {
                try {
                    outputStream.close();
                } catch (Exception unused) {
                }
            }
        } catch (Throwable th) {
            if (z) {
                try {
                    outputStream.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    public synchronized void writeFromInputStream(InputStream inputStream, boolean z) throws IOException {
        performDelayedSeek();
        if (this.size == 0) {
            init();
            int iAvailable = inputStream.available();
            if (iAvailable > this.maxSize) {
                throw new RuntimeException("Maximal size of the byte array stream was reached. (Max size = " + this.maxSize + ")");
            }
            if (iAvailable > this.initialSize) {
                byte[] bArr = new byte[iAvailable];
                int i = inputStream.read(bArr);
                if (i == -1) {
                    return;
                }
                this.size = i;
                this.currentPosition = i;
                this.currentPositionInChunk = i;
                this.currentChunkIndex = 0;
                this.chunkList.add(bArr);
            }
            startWriting();
            while (true) {
                byte[] bArr2 = this.chunkList.get(this.currentChunkIndex);
                int i2 = this.currentPositionInChunk;
                int i3 = inputStream.read(bArr2, i2, bArr2.length - i2);
                if (i3 == -1) {
                    break;
                }
                this.currentPosition += i3;
                int i4 = this.currentPositionInChunk + i3;
                this.currentPositionInChunk = i4;
                if (bArr2.length - i4 == 0) {
                    allocateNextChunk(-1);
                }
            }
            this.size = this.currentPosition;
            endWriting();
        } else {
            byte[] bArr3 = new byte[16384];
            while (true) {
                int i5 = inputStream.read(bArr3);
                if (i5 == -1) {
                    break;
                } else {
                    write(bArr3, 0, i5);
                }
            }
        }
        if (z) {
            inputStream.close();
        }
    }

    public synchronized void truncate() {
        init();
    }

    public synchronized int getSize() {
        return this.size;
    }

    public synchronized int getCurrentPosition() {
        int i = this.seekToPosition;
        if (i != -1) {
            return i;
        }
        return this.currentPosition;
    }

    public synchronized byte[] getBytes() {
        byte[] bArr;
        bArr = new byte[this.size];
        int i = 0;
        for (byte[] bArr2 : this.chunkList) {
            int length = bArr2.length + i;
            int i2 = this.size;
            int length2 = length > i2 ? i2 - i : bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i, length2);
            i += length2;
        }
        return bArr;
    }

    public synchronized void setBytes(byte[] bArr, boolean z) {
        init();
        int length = bArr.length;
        if (z) {
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            bArr = bArr2;
        }
        this.chunkList.add(bArr);
        this.currentChunkIndex = 0;
        this.currentPosition = 0;
        this.currentPositionInChunk = 0;
        this.size = length;
    }

    private void performDelayedSeek() {
        int i = this.seekToPosition;
        if (i == -1) {
            return;
        }
        if (this.currentPosition == i) {
            this.seekToPosition = -1;
            return;
        }
        if (i > this.size) {
            setSize(i, true);
            this.seekToPosition = -1;
            return;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.chunkList.size(); i3++) {
            int length = this.chunkList.get(i3).length;
            i2 += length;
            int i4 = this.seekToPosition;
            if (i2 > i4) {
                this.currentChunkIndex = i3;
                this.currentPositionInChunk = length - (i2 - i4);
                this.currentPosition = i4;
                this.seekToPosition = -1;
                return;
            }
        }
        int size = this.chunkList.size() - 1;
        this.currentChunkIndex = size;
        this.currentPositionInChunk = this.chunkList.get(size).length;
        this.currentPosition = this.size;
        this.seekToPosition = -1;
    }

    private void startWriting() {
        int i = this.currentChunkIndex;
        if (i == -1 || this.currentPositionInChunk >= this.chunkList.get(i).length) {
            allocateNextChunk(-1);
        }
    }

    private void endWriting() {
        if (this.size == this.currentPosition && this.currentPositionInChunk == 0) {
            int i = this.currentChunkIndex;
            if (i == 0) {
                init();
                return;
            }
            List<byte[]> list = this.chunkList;
            this.currentChunkIndex = i - 1;
            list.remove(i);
            this.currentPositionInChunk = this.chunkList.get(this.currentChunkIndex).length;
        }
    }

    private void allocateNextChunk(int i) {
        int i2 = this.currentChunkIndex;
        if (i2 == -1 || (i2 == this.chunkList.size() - 1 && this.chunkList.get(this.currentChunkIndex).length == this.currentPositionInChunk)) {
            this.currentPositionInChunk = 0;
            this.currentChunkIndex++;
        }
        if (this.size >= this.maxSize) {
            throw new RuntimeException("Maximal size of the byte array stream was reached. (Max size = " + this.maxSize + ")");
        }
        int size = this.chunkList.size() - 1;
        int length = size == -1 ? this.initialSize : this.chunkList.get(size).length << 1;
        if (length < 0 || length > 1048576) {
            length = 1048576;
        }
        int i3 = this.size;
        int i4 = i3 + length;
        int i5 = this.maxSize;
        if (i4 > i5) {
            length = i5 - i3;
        }
        if (i == -1 || length >= i) {
            i = length;
        } else if (i3 + i >= i5) {
            throw new RuntimeException("Maximal size of the byte array stream was reached. (Max size = " + this.maxSize + ")");
        }
        this.chunkList.add(new byte[i]);
    }
}
