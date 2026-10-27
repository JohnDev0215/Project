package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.lz77support.AbstractLZ77CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;

/* JADX INFO: loaded from: classes4.dex */
public class SnappyCompressorInputStream extends AbstractLZ77CompressorInputStream {
    public static final int DEFAULT_BLOCK_SIZE = 32768;
    private static final int TAG_MASK = 3;
    private boolean endReached;
    private final int size;
    private State state;
    private int uncompressedBytesRemaining;

    private enum State {
        NO_BLOCK,
        IN_LITERAL,
        IN_BACK_REFERENCE
    }

    public SnappyCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, 32768);
    }

    public SnappyCompressorInputStream(InputStream inputStream, int i) throws IOException {
        super(inputStream, i);
        this.state = State.NO_BLOCK;
        int size = (int) readSize();
        this.size = size;
        this.uncompressedBytesRemaining = size;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.endReached) {
            return -1;
        }
        int i3 = AnonymousClass1.$SwitchMap$org$apache$commons$compress$compressors$snappy$SnappyCompressorInputStream$State[this.state.ordinal()];
        if (i3 == 1) {
            fill();
            return read(bArr, i, i2);
        }
        if (i3 == 2) {
            int literal = readLiteral(bArr, i, i2);
            if (!hasMoreDataInBlock()) {
                this.state = State.NO_BLOCK;
            }
            return literal > 0 ? literal : read(bArr, i, i2);
        }
        if (i3 == 3) {
            int backReference = readBackReference(bArr, i, i2);
            if (!hasMoreDataInBlock()) {
                this.state = State.NO_BLOCK;
            }
            return backReference > 0 ? backReference : read(bArr, i, i2);
        }
        throw new IOException("Unknown stream state " + this.state);
    }

    /* JADX INFO: renamed from: org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$compress$compressors$snappy$SnappyCompressorInputStream$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$org$apache$commons$compress$compressors$snappy$SnappyCompressorInputStream$State = iArr;
            try {
                iArr[State.NO_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$compressors$snappy$SnappyCompressorInputStream$State[State.IN_LITERAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$compressors$snappy$SnappyCompressorInputStream$State[State.IN_BACK_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void fill() throws IOException {
        if (this.uncompressedBytesRemaining == 0) {
            this.endReached = true;
            return;
        }
        int oneByte = readOneByte();
        if (oneByte == -1) {
            throw new IOException("Premature end of stream reading block start");
        }
        int i = oneByte & 3;
        if (i == 0) {
            int literalLength = readLiteralLength(oneByte);
            if (literalLength < 0) {
                throw new IOException("Illegal block with a negative literal size found");
            }
            this.uncompressedBytesRemaining -= literalLength;
            startLiteral(literalLength);
            this.state = State.IN_LITERAL;
            return;
        }
        if (i == 1) {
            int i2 = ((oneByte >> 2) & 7) + 4;
            if (i2 < 0) {
                throw new IOException("Illegal block with a negative match length found");
            }
            this.uncompressedBytesRemaining -= i2;
            int i3 = (oneByte & 224) << 3;
            int oneByte2 = readOneByte();
            if (oneByte2 == -1) {
                throw new IOException("Premature end of stream reading back-reference length");
            }
            try {
                startBackReference(i3 | oneByte2, i2);
                this.state = State.IN_BACK_REFERENCE;
                return;
            } catch (IllegalArgumentException e) {
                throw new IOException("Illegal block with bad offset found", e);
            }
        }
        if (i == 2) {
            int i4 = (oneByte >> 2) + 1;
            if (i4 < 0) {
                throw new IOException("Illegal block with a negative match length found");
            }
            this.uncompressedBytesRemaining -= i4;
            try {
                startBackReference((int) ByteUtils.fromLittleEndian(this.supplier, 2), i4);
                this.state = State.IN_BACK_REFERENCE;
                return;
            } catch (IllegalArgumentException e2) {
                throw new IOException("Illegal block with bad offset found", e2);
            }
        }
        if (i != 3) {
            return;
        }
        int i5 = (oneByte >> 2) + 1;
        if (i5 < 0) {
            throw new IOException("Illegal block with a negative match length found");
        }
        this.uncompressedBytesRemaining -= i5;
        try {
            startBackReference(((int) ByteUtils.fromLittleEndian(this.supplier, 4)) & Integer.MAX_VALUE, i5);
            this.state = State.IN_BACK_REFERENCE;
        } catch (IllegalArgumentException e3) {
            throw new IOException("Illegal block with bad offset found", e3);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int readLiteralLength(int i) throws IOException {
        long jFromLittleEndian;
        int oneByte = i >> 2;
        switch (oneByte) {
            case 60:
                oneByte = readOneByte();
                if (oneByte == -1) {
                    throw new IOException("Premature end of stream reading literal length");
                }
                return oneByte + 1;
            case 61:
                jFromLittleEndian = ByteUtils.fromLittleEndian(this.supplier, 2);
                oneByte = (int) jFromLittleEndian;
                return oneByte + 1;
            case 62:
                jFromLittleEndian = ByteUtils.fromLittleEndian(this.supplier, 3);
                oneByte = (int) jFromLittleEndian;
                return oneByte + 1;
            case 63:
                jFromLittleEndian = ByteUtils.fromLittleEndian(this.supplier, 4);
                oneByte = (int) jFromLittleEndian;
                return oneByte + 1;
            default:
                return oneByte + 1;
        }
    }

    private long readSize() throws IOException {
        int i = 0;
        long j = 0;
        while (true) {
            int oneByte = readOneByte();
            if (oneByte == -1) {
                throw new IOException("Premature end of stream reading size");
            }
            int i2 = i + 1;
            j |= (long) ((oneByte & 127) << (i * 7));
            if ((oneByte & 128) == 0) {
                return j;
            }
            i = i2;
        }
    }

    @Override // org.apache.commons.compress.compressors.lz77support.AbstractLZ77CompressorInputStream
    public int getSize() {
        return this.size;
    }
}
