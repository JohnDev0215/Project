package org.apache.commons.compress.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public class FixedLengthBlockOutputStream extends OutputStream implements WritableByteChannel {
    private final int blockSize;
    private final ByteBuffer buffer;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final WritableByteChannel out;

    public FixedLengthBlockOutputStream(OutputStream outputStream, int i) {
        if (outputStream instanceof FileOutputStream) {
            this.out = ((FileOutputStream) outputStream).getChannel();
            this.buffer = ByteBuffer.allocateDirect(i);
        } else {
            this.out = new BufferAtATimeOutputChannel(outputStream);
            this.buffer = ByteBuffer.allocate(i);
        }
        this.blockSize = i;
    }

    public FixedLengthBlockOutputStream(WritableByteChannel writableByteChannel, int i) {
        this.out = writableByteChannel;
        this.blockSize = i;
        this.buffer = ByteBuffer.allocateDirect(i);
    }

    private void maybeFlush() throws IOException {
        if (this.buffer.hasRemaining()) {
            return;
        }
        writeBlock();
    }

    private void writeBlock() throws IOException {
        this.buffer.flip();
        int iWrite = this.out.write(this.buffer);
        boolean zHasRemaining = this.buffer.hasRemaining();
        int i = this.blockSize;
        if (iWrite != i || zHasRemaining) {
            throw new IOException(String.format("Failed to write %,d bytes atomically. Only wrote  %,d", Integer.valueOf(i), Integer.valueOf(iWrite)));
        }
        this.buffer.clear();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        this.buffer.put((byte) i);
        maybeFlush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        while (i2 > 0) {
            int iMin = Math.min(i2, this.buffer.remaining());
            this.buffer.put(bArr, i, iMin);
            maybeFlush();
            i2 -= iMin;
            i += iMin;
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        int i;
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        int iRemaining = byteBuffer.remaining();
        if (iRemaining < this.buffer.remaining()) {
            this.buffer.put(byteBuffer);
        } else {
            int iLimit = byteBuffer.limit();
            if (this.buffer.position() != 0) {
                int iRemaining2 = this.buffer.remaining();
                byteBuffer.limit(byteBuffer.position() + iRemaining2);
                this.buffer.put(byteBuffer);
                writeBlock();
                i = iRemaining - iRemaining2;
            } else {
                i = iRemaining;
            }
            while (i >= this.blockSize) {
                byteBuffer.limit(byteBuffer.position() + this.blockSize);
                this.out.write(byteBuffer);
                i -= this.blockSize;
            }
            byteBuffer.limit(iLimit);
            this.buffer.put(byteBuffer);
        }
        return iRemaining;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        if (!this.out.isOpen()) {
            this.closed.set(true);
        }
        return !this.closed.get();
    }

    public void flushBlock() throws IOException {
        if (this.buffer.position() != 0) {
            padBlock();
            writeBlock();
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (this.closed.compareAndSet(false, true)) {
            try {
                flushBlock();
            } finally {
                this.out.close();
            }
        }
    }

    private void padBlock() {
        this.buffer.order(ByteOrder.nativeOrder());
        int iRemaining = this.buffer.remaining();
        if (iRemaining > 8) {
            int iPosition = this.buffer.position() & 7;
            if (iPosition != 0) {
                int i = 8 - iPosition;
                for (int i2 = 0; i2 < i; i2++) {
                    this.buffer.put((byte) 0);
                }
                iRemaining -= i;
            }
            while (iRemaining >= 8) {
                this.buffer.putLong(0L);
                iRemaining -= 8;
            }
        }
        while (this.buffer.hasRemaining()) {
            this.buffer.put((byte) 0);
        }
    }

    private static class BufferAtATimeOutputChannel implements WritableByteChannel {
        private final AtomicBoolean closed;
        private final OutputStream out;

        private BufferAtATimeOutputChannel(OutputStream outputStream) {
            this.closed = new AtomicBoolean(false);
            this.out = outputStream;
        }

        @Override // java.nio.channels.WritableByteChannel
        public int write(ByteBuffer byteBuffer) throws IOException {
            if (!isOpen()) {
                throw new ClosedChannelException();
            }
            if (!byteBuffer.hasArray()) {
                throw new IOException("Direct buffer somehow written to BufferAtATimeOutputChannel");
            }
            try {
                int iPosition = byteBuffer.position();
                int iLimit = byteBuffer.limit() - iPosition;
                this.out.write(byteBuffer.array(), byteBuffer.arrayOffset() + iPosition, iLimit);
                byteBuffer.position(byteBuffer.limit());
                return iLimit;
            } catch (IOException e) {
                try {
                    close();
                } catch (IOException unused) {
                }
                throw e;
            }
        }

        @Override // java.nio.channels.Channel
        public boolean isOpen() {
            return !this.closed.get();
        }

        @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed.compareAndSet(false, true)) {
                this.out.close();
            }
        }
    }
}
