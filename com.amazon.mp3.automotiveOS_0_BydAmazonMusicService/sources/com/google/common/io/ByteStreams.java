package com.google.common.io;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/* JADX INFO: loaded from: classes6.dex */
public final class ByteStreams {
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() { // from class: com.google.common.io.ByteStreams.1
        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int i) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            Preconditions.checkNotNull(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            Preconditions.checkNotNull(bArr);
        }
    };

    static byte[] createBuffer() {
        return new byte[8192];
    }

    private ByteStreams() {
    }

    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(outputStream);
        byte[] bArrCreateBuffer = createBuffer();
        long j = 0;
        while (true) {
            int i = inputStream.read(bArrCreateBuffer);
            if (i == -1) {
                return j;
            }
            outputStream.write(bArrCreateBuffer, 0, i);
            j += (long) i;
        }
    }

    private static byte[] toByteArrayInternal(InputStream inputStream, Queue<byte[]> queue, int i) throws IOException {
        int iSaturatedMultiply = 8192;
        while (i < 2147483639) {
            int iMin = Math.min(iSaturatedMultiply, 2147483639 - i);
            byte[] bArr = new byte[iMin];
            queue.add(bArr);
            int i2 = 0;
            while (i2 < iMin) {
                int i3 = inputStream.read(bArr, i2, iMin - i2);
                if (i3 == -1) {
                    return combineBuffers(queue, i);
                }
                i2 += i3;
                i += i3;
            }
            iSaturatedMultiply = IntMath.saturatedMultiply(iSaturatedMultiply, 2);
        }
        if (inputStream.read() == -1) {
            return combineBuffers(queue, 2147483639);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    private static byte[] combineBuffers(Queue<byte[]> queue, int i) {
        byte[] bArr = new byte[i];
        int i2 = i;
        while (i2 > 0) {
            byte[] bArrRemove = queue.remove();
            int iMin = Math.min(i2, bArrRemove.length);
            System.arraycopy(bArrRemove, 0, bArr, i - i2, iMin);
            i2 -= iMin;
        }
        return bArr;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        return toByteArrayInternal(inputStream, new ArrayDeque(20), 0);
    }

    static byte[] toByteArray(InputStream inputStream, long j) throws IOException {
        Preconditions.checkArgument(j >= 0, "expectedSize (%s) must be non-negative", j);
        if (j > 2147483639) {
            StringBuilder sb = new StringBuilder(62);
            sb.append(j);
            sb.append(" bytes is too large to fit in a byte array");
            throw new OutOfMemoryError(sb.toString());
        }
        int i = (int) j;
        byte[] bArr = new byte[i];
        int i2 = i;
        while (i2 > 0) {
            int i3 = i - i2;
            int i4 = inputStream.read(bArr, i3, i2);
            if (i4 == -1) {
                return Arrays.copyOf(bArr, i3);
            }
            i2 -= i4;
        }
        int i5 = inputStream.read();
        if (i5 == -1) {
            return bArr;
        }
        ArrayDeque arrayDeque = new ArrayDeque(22);
        arrayDeque.add(bArr);
        arrayDeque.add(new byte[]{(byte) i5});
        return toByteArrayInternal(inputStream, arrayDeque, i + 1);
    }

    public static InputStream limit(InputStream inputStream, long j) {
        return new LimitedInputStream(inputStream, j);
    }

    private static final class LimitedInputStream extends FilterInputStream {
        private long left;
        private long mark;

        LimitedInputStream(InputStream inputStream, long j) {
            super(inputStream);
            this.mark = -1L;
            Preconditions.checkNotNull(inputStream);
            Preconditions.checkArgument(j >= 0, "limit must be non-negative");
            this.left = j;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int available() throws IOException {
            return (int) Math.min(((FilterInputStream) this).in.available(), this.left);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void mark(int i) {
            ((FilterInputStream) this).in.mark(i);
            this.mark = this.left;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            if (this.left == 0) {
                return -1;
            }
            int i = ((FilterInputStream) this).in.read();
            if (i != -1) {
                this.left--;
            }
            return i;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            long j = this.left;
            if (j == 0) {
                return -1;
            }
            int i3 = ((FilterInputStream) this).in.read(bArr, i, (int) Math.min(i2, j));
            if (i3 != -1) {
                this.left -= (long) i3;
            }
            return i3;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void reset() throws IOException {
            if (!((FilterInputStream) this).in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1) {
                throw new IOException("Mark not set");
            }
            ((FilterInputStream) this).in.reset();
            this.left = this.mark;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long jSkip = ((FilterInputStream) this).in.skip(Math.min(j, this.left));
            this.left -= jSkip;
            return jSkip;
        }
    }

    public static void readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = read(inputStream, bArr, i, i2);
        if (i3 == i2) {
            return;
        }
        StringBuilder sb = new StringBuilder(81);
        sb.append("reached end of stream after reading ");
        sb.append(i3);
        sb.append(" bytes; ");
        sb.append(i2);
        sb.append(" bytes expected");
        throw new EOFException(sb.toString());
    }

    public static int read(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        int i3 = 0;
        if (i2 < 0) {
            throw new IndexOutOfBoundsException(String.format("len (%s) cannot be negative", Integer.valueOf(i2)));
        }
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i + i3, i2 - i3);
            if (i4 == -1) {
                break;
            }
            i3 += i4;
        }
        return i3;
    }
}
