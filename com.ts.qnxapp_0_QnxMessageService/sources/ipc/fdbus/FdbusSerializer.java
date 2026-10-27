package ipc.fdbus;

/* JADX INFO: loaded from: classes.dex */
public class FdbusSerializer {
    private static final int EXPANSION_BLOCK_SIZE = 2000;
    private int mBufferPos = 0;
    private byte[] mBuffer = new byte[2000];

    private void writeString(String string) {
        byte[] bytes;
        try {
            bytes = string.getBytes("UTF-8");
        } catch (Exception e) {
            bytes = new byte[0];
        }
        int size = bytes.length;
        writeRawLittleEndian16(size + 1);
        writeRawBytes(bytes);
        writeRaw8(0);
    }

    private void writeRawBytes(byte[] value, int length) {
        expandBuffer(length);
        System.arraycopy(value, 0, this.mBuffer, this.mBufferPos, length);
        this.mBufferPos += length;
    }

    private void writeRawBytes(byte[] value) {
        writeRawBytes(value, value.length);
    }

    private void writeRaw8(int value) {
        expandBuffer(1);
        byte[] bArr = this.mBuffer;
        int i = this.mBufferPos;
        this.mBufferPos = i + 1;
        bArr[i] = (byte) (value & 255);
    }

    private void writeRawLittleEndian16(int value) {
        expandBuffer(2);
        byte[] bArr = this.mBuffer;
        int i = this.mBufferPos;
        int i2 = i + 1;
        this.mBufferPos = i2;
        bArr[i] = (byte) (value & 255);
        this.mBufferPos = i2 + 1;
        bArr[i2] = (byte) ((value >> 8) & 255);
    }

    private void writeRawLittleEndian32(int value) {
        expandBuffer(4);
        byte[] bArr = this.mBuffer;
        int i = this.mBufferPos;
        int i2 = i + 1;
        this.mBufferPos = i2;
        bArr[i] = (byte) (value & 255);
        int i3 = i2 + 1;
        this.mBufferPos = i3;
        bArr[i2] = (byte) ((value >> 8) & 255);
        int i4 = i3 + 1;
        this.mBufferPos = i4;
        bArr[i3] = (byte) ((value >> 16) & 255);
        this.mBufferPos = i4 + 1;
        bArr[i4] = (byte) ((value >> 24) & 255);
    }

    private void writeRawLittleEndian64(long value) {
        expandBuffer(8);
        byte[] bArr = this.mBuffer;
        int i = this.mBufferPos;
        int i2 = i + 1;
        this.mBufferPos = i2;
        bArr[i] = (byte) (value & 255);
        int i3 = i2 + 1;
        this.mBufferPos = i3;
        bArr[i2] = (byte) ((value >> 8) & 255);
        int i4 = i3 + 1;
        this.mBufferPos = i4;
        bArr[i3] = (byte) ((value >> 16) & 255);
        int i5 = i4 + 1;
        this.mBufferPos = i5;
        bArr[i4] = (byte) ((value >> 24) & 255);
        int i6 = i5 + 1;
        this.mBufferPos = i6;
        bArr[i5] = (byte) ((value >> 32) & 255);
        int i7 = i6 + 1;
        this.mBufferPos = i7;
        bArr[i6] = (byte) ((value >> 40) & 255);
        int i8 = i7 + 1;
        this.mBufferPos = i8;
        bArr[i7] = (byte) ((value >> 48) & 255);
        this.mBufferPos = i8 + 1;
        bArr[i8] = (byte) ((value >> 56) & 255);
    }

    private void expandBuffer(int expected) {
        int i = this.mBufferPos;
        int i2 = i + expected;
        byte[] bArr = this.mBuffer;
        if (i2 <= bArr.length) {
            return;
        }
        int new_size = i + expected + 2000;
        byte[] new_buf = new byte[new_size];
        System.arraycopy(bArr, 0, new_buf, 0, bArr.length);
        this.mBuffer = new_buf;
    }

    public int getPosition() {
        return this.mBufferPos;
    }

    public byte[] getBuffer() {
        return this.mBuffer;
    }

    public byte[] export() {
        int i = this.mBufferPos;
        byte[] b = new byte[i];
        System.arraycopy(this.mBuffer, 0, b, 0, i);
        return b;
    }

    public void in8(int value) {
        writeRaw8(value);
    }

    public void in8(int[] value) {
        writeRawLittleEndian16(value.length);
        for (int i : value) {
            writeRaw8(i);
        }
    }

    public void in16(int value) {
        writeRawLittleEndian16(value);
    }

    public void in16(int[] value) {
        writeRawLittleEndian16(value.length);
        for (int i : value) {
            writeRawLittleEndian16(i);
        }
    }

    public void in32(int value) {
        writeRawLittleEndian32(value);
    }

    public void in32(int[] value) {
        writeRawLittleEndian16(value.length);
        for (int i : value) {
            writeRawLittleEndian32(i);
        }
    }

    public void in64(long value) {
        writeRawLittleEndian64(value);
    }

    public void in64(long[] value) {
        writeRawLittleEndian16(value.length);
        for (long j : value) {
            writeRawLittleEndian64(j);
        }
    }

    public void inBool(boolean z) {
        writeRaw8(z ? 1 : 0);
    }

    public void inBool(boolean[] zArr) {
        writeRawLittleEndian16(zArr.length);
        for (boolean z : zArr) {
            writeRaw8(z ? 1 : 0);
        }
    }

    public void inS(String value) {
        writeString(value);
    }

    public void inS(String[] value) {
        writeRawLittleEndian16(value.length);
        for (String str : value) {
            writeString(str);
        }
    }

    public void in(byte[] value, int length) {
        writeRawLittleEndian32(length);
        writeRawBytes(value, length);
    }

    public void in(byte[] value) {
        in(value, value.length);
    }

    public void in(byte[][] value) {
        writeRawLittleEndian16(value.length);
        for (byte[] bArr : value) {
            in(bArr);
        }
    }

    public void in(FdbusParcelable parcelable) {
        parcelable.serialize(this);
    }

    public void in(FdbusParcelable[] parcelables) {
        writeRawLittleEndian16(parcelables.length);
        for (FdbusParcelable fdbusParcelable : parcelables) {
            fdbusParcelable.serialize(this);
        }
    }
}
