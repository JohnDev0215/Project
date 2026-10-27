package p;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class bl0 extends InputStream implements DataInput {
    public static final ByteOrder m = ByteOrder.LITTLE_ENDIAN;
    public static final ByteOrder n = ByteOrder.BIG_ENDIAN;
    public final DataInputStream c;
    public ByteOrder e;
    public int k;
    public byte[] l;

    public bl0(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), ByteOrder.BIG_ENDIAN);
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.c.available();
    }

    public final void b(int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            DataInputStream dataInputStream = this.c;
            int i3 = i - i2;
            int iSkip = (int) dataInputStream.skip(i3);
            if (iSkip <= 0) {
                if (this.l == null) {
                    this.l = new byte[8192];
                }
                iSkip = dataInputStream.read(this.l, 0, Math.min(8192, i3));
                if (iSkip == -1) {
                    throw new EOFException(u4.f("Reached EOF while skipping ", i, " bytes."));
                }
            }
            i2 += iSkip;
        }
        this.k += i2;
    }

    @Override // java.io.InputStream
    public final void mark(int i) {
        throw new UnsupportedOperationException("Mark is currently unsupported");
    }

    @Override // java.io.InputStream
    public final int read() {
        this.k++;
        return this.c.read();
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() {
        this.k++;
        return this.c.readBoolean();
    }

    @Override // java.io.DataInput
    public final byte readByte() throws IOException {
        this.k++;
        int i = this.c.read();
        if (i >= 0) {
            return (byte) i;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final char readChar() {
        this.k += 2;
        return this.c.readChar();
    }

    @Override // java.io.DataInput
    public final double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public final float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr, int i, int i2) throws IOException {
        this.k += i2;
        this.c.readFully(bArr, i, i2);
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        this.k += 4;
        DataInputStream dataInputStream = this.c;
        int i = dataInputStream.read();
        int i2 = dataInputStream.read();
        int i3 = dataInputStream.read();
        int i4 = dataInputStream.read();
        if ((i | i2 | i3 | i4) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.e;
        if (byteOrder == m) {
            return (i4 << 24) + (i3 << 16) + (i2 << 8) + i;
        }
        if (byteOrder == n) {
            return (i << 24) + (i2 << 16) + (i3 << 8) + i4;
        }
        throw new IOException("Invalid byte order: " + this.e);
    }

    @Override // java.io.DataInput
    public final String readLine() {
        return null;
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        this.k += 8;
        DataInputStream dataInputStream = this.c;
        int i = dataInputStream.read();
        int i2 = dataInputStream.read();
        int i3 = dataInputStream.read();
        int i4 = dataInputStream.read();
        int i5 = dataInputStream.read();
        int i6 = dataInputStream.read();
        int i7 = dataInputStream.read();
        int i8 = dataInputStream.read();
        if ((i | i2 | i3 | i4 | i5 | i6 | i7 | i8) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.e;
        if (byteOrder == m) {
            return (((long) i8) << 56) + (((long) i7) << 48) + (((long) i6) << 40) + (((long) i5) << 32) + (((long) i4) << 24) + (((long) i3) << 16) + (((long) i2) << 8) + ((long) i);
        }
        if (byteOrder == n) {
            return (((long) i) << 56) + (((long) i2) << 48) + (((long) i3) << 40) + (((long) i4) << 32) + (((long) i5) << 24) + (((long) i6) << 16) + (((long) i7) << 8) + ((long) i8);
        }
        throw new IOException("Invalid byte order: " + this.e);
    }

    @Override // java.io.DataInput
    public final short readShort() throws IOException {
        this.k += 2;
        DataInputStream dataInputStream = this.c;
        int i = dataInputStream.read();
        int i2 = dataInputStream.read();
        if ((i | i2) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.e;
        if (byteOrder == m) {
            return (short) ((i2 << 8) + i);
        }
        if (byteOrder == n) {
            return (short) ((i << 8) + i2);
        }
        throw new IOException("Invalid byte order: " + this.e);
    }

    @Override // java.io.DataInput
    public final String readUTF() {
        this.k += 2;
        return this.c.readUTF();
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() {
        this.k++;
        return this.c.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        this.k += 2;
        DataInputStream dataInputStream = this.c;
        int i = dataInputStream.read();
        int i2 = dataInputStream.read();
        if ((i | i2) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.e;
        if (byteOrder == m) {
            return (i2 << 8) + i;
        }
        if (byteOrder == n) {
            return (i << 8) + i2;
        }
        throw new IOException("Invalid byte order: " + this.e);
    }

    @Override // java.io.InputStream
    public final void reset() {
        throw new UnsupportedOperationException("Reset is currently unsupported");
    }

    @Override // java.io.DataInput
    public final int skipBytes(int i) {
        throw new UnsupportedOperationException("skipBytes is currently unsupported");
    }

    public bl0(InputStream inputStream) {
        this(inputStream, ByteOrder.BIG_ENDIAN);
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.c.read(bArr, i, i2);
        this.k += i3;
        return i3;
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) throws IOException {
        this.k += bArr.length;
        this.c.readFully(bArr);
    }

    public bl0(InputStream inputStream, ByteOrder byteOrder) {
        this.e = ByteOrder.BIG_ENDIAN;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.c = dataInputStream;
        dataInputStream.mark(0);
        this.k = 0;
        this.e = byteOrder;
    }
}
