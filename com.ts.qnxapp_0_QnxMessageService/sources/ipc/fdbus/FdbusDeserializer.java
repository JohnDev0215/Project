package ipc.fdbus;

/* JADX INFO: loaded from: classes.dex */
public class FdbusDeserializer {
    private final byte[] mBuffer;
    private int mBufferPos = 0;
    private boolean mError = false;

    public FdbusDeserializer(byte[] buffer) {
        this.mBuffer = buffer;
    }

    private double readDouble() {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    private float readFloat() {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    private String readString() {
        int size = readRawLittleEndian16();
        int i = this.mBufferPos;
        int i2 = i + size;
        byte[] bArr = this.mBuffer;
        if (i2 <= bArr.length && size > 0 && bArr[(i + size) - 1] == 0) {
            String result = new String(this.mBuffer, this.mBufferPos, size - 1);
            this.mBufferPos += size;
            return result;
        }
        String result2 = new String();
        this.mError = true;
        return result2;
    }

    private void readRawBytes(byte[] value) {
        int i = this.mBufferPos;
        int length = value.length + i;
        byte[] bArr = this.mBuffer;
        if (length > bArr.length) {
            this.mError = true;
        } else {
            System.arraycopy(bArr, i, value, 0, value.length);
            this.mBufferPos += value.length;
        }
    }

    private int readRaw8() {
        int i = this.mBufferPos;
        int i2 = i + 1;
        byte[] bArr = this.mBuffer;
        if (i2 > bArr.length) {
            this.mError = true;
            return 0;
        }
        this.mBufferPos = i + 1;
        return bArr[i];
    }

    private int readRawLittleEndian16() {
        int i = this.mBufferPos;
        int i2 = i + 2;
        byte[] bArr = this.mBuffer;
        if (i2 > bArr.length) {
            this.mError = true;
            return 0;
        }
        int i3 = i + 1;
        this.mBufferPos = i3;
        byte b1 = bArr[i];
        this.mBufferPos = i3 + 1;
        byte b2 = bArr[i3];
        return (b1 & 255) | ((b2 & 255) << 8);
    }

    private int readRawLittleEndian32() {
        int i = this.mBufferPos;
        int i2 = i + 4;
        byte[] bArr = this.mBuffer;
        if (i2 > bArr.length) {
            this.mError = true;
            return 0;
        }
        int i3 = i + 1;
        this.mBufferPos = i3;
        byte b1 = bArr[i];
        int i4 = i3 + 1;
        this.mBufferPos = i4;
        byte b2 = bArr[i3];
        int i5 = i4 + 1;
        this.mBufferPos = i5;
        byte b3 = bArr[i4];
        this.mBufferPos = i5 + 1;
        byte b4 = bArr[i5];
        return (b1 & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16) | ((b4 & 255) << 24);
    }

    private long readRawLittleEndian64() {
        int i = this.mBufferPos;
        int i2 = i + 8;
        byte[] bArr = this.mBuffer;
        if (i2 > bArr.length) {
            this.mError = true;
            return 0L;
        }
        int i3 = i + 1;
        this.mBufferPos = i3;
        byte b1 = bArr[i];
        int i4 = i3 + 1;
        this.mBufferPos = i4;
        byte b2 = bArr[i3];
        int i5 = i4 + 1;
        this.mBufferPos = i5;
        byte b3 = bArr[i4];
        int i6 = i5 + 1;
        this.mBufferPos = i6;
        byte b4 = bArr[i5];
        int i7 = i6 + 1;
        this.mBufferPos = i7;
        byte b5 = bArr[i6];
        int i8 = i7 + 1;
        this.mBufferPos = i8;
        byte b6 = bArr[i7];
        int i9 = i8 + 1;
        this.mBufferPos = i9;
        byte b7 = bArr[i8];
        this.mBufferPos = i9 + 1;
        byte b8 = bArr[i9];
        return (((long) b1) & 255) | ((((long) b2) & 255) << 8) | ((((long) b3) & 255) << 16) | ((((long) b4) & 255) << 24) | ((((long) b5) & 255) << 32) | ((((long) b6) & 255) << 40) | ((((long) b7) & 255) << 48) | ((255 & ((long) b8)) << 56);
    }

    private <T> T createParcelable(Class<T> clz) {
        try {
            T p = clz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            return p;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean isAtEnd() {
        return this.mBufferPos == this.mBuffer.length;
    }

    public int getPosition() {
        return this.mBufferPos;
    }

    public byte[] getBuffer() {
        return this.mBuffer;
    }

    public int out8() {
        return readRaw8();
    }

    public int[] out8A() {
        int len = readRawLittleEndian16();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int data = readRaw8();
            if (this.mError) {
                break;
            }
            arr[i] = data;
        }
        return arr;
    }

    public int out16() {
        return readRawLittleEndian16();
    }

    public int[] out16A() {
        int len = readRawLittleEndian16();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int data = readRawLittleEndian16();
            if (this.mError) {
                break;
            }
            arr[i] = data;
        }
        return arr;
    }

    public int out32() {
        return readRawLittleEndian32();
    }

    public int[] out32A() {
        int len = readRawLittleEndian16();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int data = readRawLittleEndian32();
            if (this.mError) {
                break;
            }
            arr[i] = data;
        }
        return arr;
    }

    public long out64() {
        return readRawLittleEndian64();
    }

    public long[] out64A() {
        int len = readRawLittleEndian16();
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) {
            long data = readRawLittleEndian64();
            if (this.mError) {
                break;
            }
            arr[i] = data;
        }
        return arr;
    }

    public boolean outBool() {
        return readRaw8() != 0;
    }

    public boolean[] outBoolA() {
        int len = readRawLittleEndian16();
        boolean[] arr = new boolean[len];
        for (int i = 0; i < len; i++) {
            boolean data = readRaw8() != 0;
            if (this.mError) {
                break;
            }
            arr[i] = data;
        }
        return arr;
    }

    public String outS() {
        return readString();
    }

    public String[] outSA() {
        int len = readRawLittleEndian16();
        String[] arr = new String[len];
        for (int i = 0; i < len; i++) {
            String data = readString();
            if (this.mError) {
                break;
            }
            arr[i] = data;
        }
        return arr;
    }

    public byte[] outBA() {
        if (this.mError) {
            return new byte[0];
        }
        int len = readRawLittleEndian32();
        byte[] value = null;
        try {
            value = new byte[len];
        } catch (Exception e) {
        }
        if (value == null) {
            byte[] value2 = new byte[0];
            return value2;
        }
        readRawBytes(value);
        return value;
    }

    public byte[][] outBAA() {
        if (this.mError) {
            return new byte[0][];
        }
        int len = readRawLittleEndian16();
        byte[][] value = null;
        try {
            value = new byte[len][];
        } catch (Exception e) {
        }
        if (value == null) {
            byte[][] value2 = new byte[0][];
            return value2;
        }
        for (int i = 0; i < len; i++) {
            value[i] = outBA();
        }
        return value;
    }

    public <T> FdbusParcelable out(Class<T> type) {
        FdbusParcelable parcelable = (FdbusParcelable) createParcelable(type);
        if (parcelable != null) {
            parcelable.deserialize(this);
        }
        return parcelable;
    }

    public int arrayLength() {
        return readRawLittleEndian16();
    }

    public <T> T[] out(T[] tArr, Class<T> type) {
        Object objCreateParcelable;
        for (int i = 0; i < tArr.length && (objCreateParcelable = createParcelable(type)) != null; i++) {
            FdbusParcelable p = (FdbusParcelable) objCreateParcelable;
            p.deserialize(this);
            tArr[i] = objCreateParcelable;
        }
        return tArr;
    }
}
