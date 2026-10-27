package W1;

import A7.k;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class b extends InputStream implements DataInput {
    public final DataInputStream l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9672m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ByteOrder f9673n;
    public byte[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f9674p;

    public b(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), ByteOrder.BIG_ENDIAN);
        this.f9674p = bArr.length;
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.l.available();
    }

    public final void b(int i7) throws IOException {
        int i10 = 0;
        while (i10 < i7) {
            DataInputStream dataInputStream = this.l;
            int i11 = i7 - i10;
            int iSkip = (int) dataInputStream.skip(i11);
            if (iSkip <= 0) {
                if (this.o == null) {
                    this.o = new byte[8192];
                }
                iSkip = dataInputStream.read(this.o, 0, Math.min(8192, i11));
                if (iSkip == -1) {
                    throw new EOFException(k.g("Reached EOF while skipping ", i7, " bytes."));
                }
            }
            i10 += iSkip;
        }
        this.f9672m += i10;
    }

    @Override // java.io.InputStream
    public final void mark(int i7) {
        throw new UnsupportedOperationException("Mark is currently unsupported");
    }

    @Override // java.io.InputStream
    public final int read() {
        this.f9672m++;
        return this.l.read();
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() {
        this.f9672m++;
        return this.l.readBoolean();
    }

    @Override // java.io.DataInput
    public final byte readByte() throws IOException {
        this.f9672m++;
        int i7 = this.l.read();
        if (i7 >= 0) {
            return (byte) i7;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final char readChar() {
        this.f9672m += 2;
        return this.l.readChar();
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
    public final void readFully(byte[] bArr, int i7, int i10) throws IOException {
        this.f9672m += i10;
        this.l.readFully(bArr, i7, i10);
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        this.f9672m += 4;
        DataInputStream dataInputStream = this.l;
        int i7 = dataInputStream.read();
        int i10 = dataInputStream.read();
        int i11 = dataInputStream.read();
        int i12 = dataInputStream.read();
        if ((i7 | i10 | i11 | i12) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.f9673n;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            return (i12 << 24) + (i11 << 16) + (i10 << 8) + i7;
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return (i7 << 24) + (i10 << 16) + (i11 << 8) + i12;
        }
        throw new IOException("Invalid byte order: " + this.f9673n);
    }

    @Override // java.io.DataInput
    public final String readLine() {
        Log.d("ExifInterface", "Currently unsupported");
        return null;
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        this.f9672m += 8;
        DataInputStream dataInputStream = this.l;
        int i7 = dataInputStream.read();
        int i10 = dataInputStream.read();
        int i11 = dataInputStream.read();
        int i12 = dataInputStream.read();
        int i13 = dataInputStream.read();
        int i14 = dataInputStream.read();
        int i15 = dataInputStream.read();
        int i16 = dataInputStream.read();
        if ((i7 | i10 | i11 | i12 | i13 | i14 | i15 | i16) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.f9673n;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            return (((long) i16) << 56) + (((long) i15) << 48) + (((long) i14) << 40) + (((long) i13) << 32) + (((long) i12) << 24) + (((long) i11) << 16) + (((long) i10) << 8) + ((long) i7);
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return (((long) i7) << 56) + (((long) i10) << 48) + (((long) i11) << 40) + (((long) i12) << 32) + (((long) i13) << 24) + (((long) i14) << 16) + (((long) i15) << 8) + ((long) i16);
        }
        throw new IOException("Invalid byte order: " + this.f9673n);
    }

    @Override // java.io.DataInput
    public final short readShort() throws IOException {
        int i7;
        this.f9672m += 2;
        DataInputStream dataInputStream = this.l;
        int i10 = dataInputStream.read();
        int i11 = dataInputStream.read();
        if ((i10 | i11) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.f9673n;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            i7 = (i11 << 8) + i10;
        } else {
            if (byteOrder != ByteOrder.BIG_ENDIAN) {
                throw new IOException("Invalid byte order: " + this.f9673n);
            }
            i7 = (i10 << 8) + i11;
        }
        return (short) i7;
    }

    @Override // java.io.DataInput
    public final String readUTF() {
        this.f9672m += 2;
        return this.l.readUTF();
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() {
        this.f9672m++;
        return this.l.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        this.f9672m += 2;
        DataInputStream dataInputStream = this.l;
        int i7 = dataInputStream.read();
        int i10 = dataInputStream.read();
        if ((i7 | i10) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.f9673n;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            return (i10 << 8) + i7;
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return (i7 << 8) + i10;
        }
        throw new IOException("Invalid byte order: " + this.f9673n);
    }

    @Override // java.io.InputStream
    public final void reset() {
        throw new UnsupportedOperationException("Reset is currently unsupported");
    }

    @Override // java.io.DataInput
    public final int skipBytes(int i7) {
        throw new UnsupportedOperationException("skipBytes is currently unsupported");
    }

    public b(InputStream inputStream) {
        this(inputStream, ByteOrder.BIG_ENDIAN);
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) throws IOException {
        int i11 = this.l.read(bArr, i7, i10);
        this.f9672m += i11;
        return i11;
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) throws IOException {
        this.f9672m += bArr.length;
        this.l.readFully(bArr);
    }

    public b(InputStream inputStream, ByteOrder byteOrder) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.l = dataInputStream;
        dataInputStream.mark(0);
        this.f9672m = 0;
        this.f9673n = byteOrder;
        this.f9674p = inputStream instanceof b ? ((b) inputStream).f9674p : -1;
    }
}
