package com.google.protobuf.micro;

import com.android.bluetooth.BluetoothStatsLog;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.Reader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class CodedInputStreamMicro {
    private static final int BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 64;
    private static final int DEFAULT_SIZE_LIMIT = 67108864;
    private final byte[] buffer;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private int sizeLimit;
    private int totalBytesRetired;

    public static CodedInputStreamMicro newInstance(InputStream input) {
        return new CodedInputStreamMicro(input);
    }

    public static CodedInputStreamMicro newInstance(byte[] buf) {
        return newInstance(buf, 0, buf.length);
    }

    public static CodedInputStreamMicro newInstance(byte[] buf, int off, int len) {
        return new CodedInputStreamMicro(buf, off, len);
    }

    public int readTag() throws IOException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        int rawVarint32 = readRawVarint32();
        this.lastTag = rawVarint32;
        if (rawVarint32 == 0) {
            throw InvalidProtocolBufferMicroException.invalidTag();
        }
        return rawVarint32;
    }

    public void checkLastTagWas(int value) throws InvalidProtocolBufferMicroException {
        if (this.lastTag != value) {
            throw InvalidProtocolBufferMicroException.invalidEndTag();
        }
    }

    public boolean skipField(int tag) throws IOException {
        int tagWireType = WireFormatMicro.getTagWireType(tag);
        if (tagWireType == 0) {
            readInt32();
            return true;
        }
        if (tagWireType == 1) {
            readRawLittleEndian64();
            return true;
        }
        if (tagWireType == 2) {
            skipRawBytes(readRawVarint32());
            return true;
        }
        if (tagWireType == 3) {
            skipMessage();
            checkLastTagWas(WireFormatMicro.makeTag(WireFormatMicro.getTagFieldNumber(tag), 4));
            return true;
        }
        if (tagWireType == 4) {
            return false;
        }
        if (tagWireType == 5) {
            readRawLittleEndian32();
            return true;
        }
        throw InvalidProtocolBufferMicroException.invalidWireType();
    }

    public void skipMessage() throws IOException {
        int tag;
        do {
            tag = readTag();
            if (tag == 0) {
                return;
            }
        } while (skipField(tag));
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public long readUInt64() throws IOException {
        return readRawVarint64();
    }

    public long readInt64() throws IOException {
        return readRawVarint64();
    }

    public int readInt32() throws IOException {
        return readRawVarint32();
    }

    public long readFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public int readFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public boolean readBool() throws IOException {
        return readRawVarint32() != 0;
    }

    public String readString() throws IOException {
        int size = readRawVarint32();
        if (size <= this.bufferSize - this.bufferPos && size > 0) {
            String result = new String(this.buffer, this.bufferPos, size, "UTF-8");
            this.bufferPos += size;
            return result;
        }
        return new String(readRawBytes(size), "UTF-8");
    }

    public void readGroup(MessageMicro msg, int fieldNumber) throws IOException {
        int i = this.recursionDepth;
        if (i >= this.recursionLimit) {
            throw InvalidProtocolBufferMicroException.recursionLimitExceeded();
        }
        this.recursionDepth = i + 1;
        msg.mergeFrom(this);
        checkLastTagWas(WireFormatMicro.makeTag(fieldNumber, 4));
        this.recursionDepth--;
    }

    public void readMessage(MessageMicro msg) throws IOException {
        int length = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferMicroException.recursionLimitExceeded();
        }
        int oldLimit = pushLimit(length);
        this.recursionDepth++;
        msg.mergeFrom(this);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(oldLimit);
    }

    public ByteStringMicro readBytes() throws IOException {
        int size = readRawVarint32();
        int i = this.bufferSize;
        int i2 = this.bufferPos;
        if (size <= i - i2 && size > 0) {
            ByteStringMicro result = ByteStringMicro.copyFrom(this.buffer, i2, size);
            this.bufferPos += size;
            return result;
        }
        if (size == 0) {
            return ByteStringMicro.EMPTY;
        }
        return ByteStringMicro.copyFrom(readRawBytes(size));
    }

    public int readUInt32() throws IOException {
        return readRawVarint32();
    }

    public int readEnum() throws IOException {
        return readRawVarint32();
    }

    public int readSFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public long readSFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public int readSInt32() throws IOException {
        return decodeZigZag32(readRawVarint32());
    }

    public long readSInt64() throws IOException {
        return decodeZigZag64(readRawVarint64());
    }

    public int readRawVarint32() throws IOException {
        byte tmp = readRawByte();
        if (tmp >= 0) {
            return tmp;
        }
        int result = tmp & Ascii.DEL;
        byte tmp2 = readRawByte();
        if (tmp2 >= 0) {
            return result | (tmp2 << 7);
        }
        int result2 = result | ((tmp2 & Ascii.DEL) << 7);
        byte tmp3 = readRawByte();
        if (tmp3 >= 0) {
            return result2 | (tmp3 << Ascii.SO);
        }
        int result3 = result2 | ((tmp3 & Ascii.DEL) << 14);
        byte tmp4 = readRawByte();
        if (tmp4 >= 0) {
            return result3 | (tmp4 << Ascii.NAK);
        }
        int result4 = result3 | ((tmp4 & Ascii.DEL) << 21);
        byte tmp5 = readRawByte();
        int result5 = result4 | (tmp5 << Ascii.FS);
        if (tmp5 < 0) {
            for (int i = 0; i < 5; i++) {
                if (readRawByte() >= 0) {
                    return result5;
                }
            }
            throw InvalidProtocolBufferMicroException.malformedVarint();
        }
        return result5;
    }

    static int readRawVarint32(InputStream input) throws IOException {
        int result = 0;
        int offset = 0;
        while (offset < 32) {
            int b = input.read();
            if (b == -1) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            result |= (b & BluetoothStatsLog.BLUETOOTH_SCO_CONNECTION_STATE_CHANGED) << offset;
            if ((b & 128) != 0) {
                offset += 7;
            } else {
                return result;
            }
        }
        while (offset < 64) {
            int b2 = input.read();
            if (b2 == -1) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            if ((b2 & 128) != 0) {
                offset += 7;
            } else {
                return result;
            }
        }
        throw InvalidProtocolBufferMicroException.malformedVarint();
    }

    public long readRawVarint64() throws IOException {
        long result = 0;
        for (int shift = 0; shift < 64; shift += 7) {
            byte b = readRawByte();
            result |= ((long) (b & Ascii.DEL)) << shift;
            if ((b & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return result;
            }
        }
        throw InvalidProtocolBufferMicroException.malformedVarint();
    }

    public int readRawLittleEndian32() throws IOException {
        byte b1 = readRawByte();
        byte b2 = readRawByte();
        byte b3 = readRawByte();
        byte b4 = readRawByte();
        return (b1 & UnsignedBytes.MAX_VALUE) | ((b2 & UnsignedBytes.MAX_VALUE) << 8) | ((b3 & UnsignedBytes.MAX_VALUE) << 16) | ((b4 & UnsignedBytes.MAX_VALUE) << 24);
    }

    public long readRawLittleEndian64() throws IOException {
        byte b1 = readRawByte();
        byte b2 = readRawByte();
        byte b3 = readRawByte();
        byte b4 = readRawByte();
        byte b5 = readRawByte();
        byte b6 = readRawByte();
        byte b7 = readRawByte();
        byte b8 = readRawByte();
        return (((long) b1) & 255) | ((((long) b2) & 255) << 8) | ((((long) b3) & 255) << 16) | ((((long) b4) & 255) << 24) | ((((long) b5) & 255) << 32) | ((((long) b6) & 255) << 40) | ((((long) b7) & 255) << 48) | ((255 & ((long) b8)) << 56);
    }

    public static int decodeZigZag32(int n) {
        return (n >>> 1) ^ (-(n & 1));
    }

    public static long decodeZigZag64(long n) {
        return (n >>> 1) ^ (-(1 & n));
    }

    private CodedInputStreamMicro(byte[] buffer, int off, int len) {
        this.currentLimit = Reader.READ_DONE;
        this.recursionLimit = 64;
        this.sizeLimit = 67108864;
        this.buffer = buffer;
        this.bufferSize = off + len;
        this.bufferPos = off;
        this.input = null;
    }

    private CodedInputStreamMicro(InputStream input) {
        this.currentLimit = Reader.READ_DONE;
        this.recursionLimit = 64;
        this.sizeLimit = 67108864;
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.input = input;
    }

    public int setRecursionLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Recursion limit cannot be negative: " + limit);
        }
        int oldLimit = this.recursionLimit;
        this.recursionLimit = limit;
        return oldLimit;
    }

    public int setSizeLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Size limit cannot be negative: " + limit);
        }
        int oldLimit = this.sizeLimit;
        this.sizeLimit = limit;
        return oldLimit;
    }

    public void resetSizeCounter() {
        this.totalBytesRetired = 0;
    }

    public int pushLimit(int byteLimit) throws InvalidProtocolBufferMicroException {
        if (byteLimit < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        int byteLimit2 = byteLimit + this.totalBytesRetired + this.bufferPos;
        int oldLimit = this.currentLimit;
        if (byteLimit2 > oldLimit) {
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        this.currentLimit = byteLimit2;
        recomputeBufferSizeAfterLimit();
        return oldLimit;
    }

    private void recomputeBufferSizeAfterLimit() {
        int i = this.bufferSize + this.bufferSizeAfterLimit;
        this.bufferSize = i;
        int bufferEnd = this.totalBytesRetired + i;
        int i2 = this.currentLimit;
        if (bufferEnd > i2) {
            int i3 = bufferEnd - i2;
            this.bufferSizeAfterLimit = i3;
            this.bufferSize = i - i3;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    public void popLimit(int oldLimit) {
        this.currentLimit = oldLimit;
        recomputeBufferSizeAfterLimit();
    }

    public int getBytesUntilLimit() {
        int i = this.currentLimit;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        int currentAbsolutePosition = this.totalBytesRetired + this.bufferPos;
        return i - currentAbsolutePosition;
    }

    public boolean isAtEnd() throws IOException {
        return this.bufferPos == this.bufferSize && !refillBuffer(false);
    }

    private boolean refillBuffer(boolean mustSucceed) throws IOException {
        int i = this.bufferPos;
        int i2 = this.bufferSize;
        if (i < i2) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        }
        int i3 = this.totalBytesRetired;
        if (i3 + i2 == this.currentLimit) {
            if (mustSucceed) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            return false;
        }
        this.totalBytesRetired = i3 + i2;
        this.bufferPos = 0;
        InputStream inputStream = this.input;
        int i4 = inputStream == null ? -1 : inputStream.read(this.buffer);
        this.bufferSize = i4;
        if (i4 == 0 || i4 < -1) {
            throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.bufferSize + "\nThe InputStream implementation is buggy.");
        }
        if (i4 == -1) {
            this.bufferSize = 0;
            if (mustSucceed) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            return false;
        }
        recomputeBufferSizeAfterLimit();
        int totalBytesRead = this.totalBytesRetired + this.bufferSize + this.bufferSizeAfterLimit;
        if (totalBytesRead > this.sizeLimit || totalBytesRead < 0) {
            throw InvalidProtocolBufferMicroException.sizeLimitExceeded();
        }
        return true;
    }

    public byte readRawByte() throws IOException {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(true);
        }
        byte[] bArr = this.buffer;
        int i = this.bufferPos;
        this.bufferPos = i + 1;
        return bArr[i];
    }

    public byte[] readRawBytes(int size) throws IOException {
        if (size < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        int sizeLeft = this.totalBytesRetired;
        int i = this.bufferPos;
        int i2 = sizeLeft + i + size;
        int i3 = this.currentLimit;
        if (i2 > i3) {
            skipRawBytes((i3 - sizeLeft) - i);
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        int i4 = this.bufferSize;
        if (size <= i4 - i) {
            byte[] bytes = new byte[size];
            System.arraycopy(this.buffer, i, bytes, 0, size);
            this.bufferPos += size;
            return bytes;
        }
        if (size < 4096) {
            byte[] bytes2 = new byte[size];
            int pos = i4 - i;
            System.arraycopy(this.buffer, i, bytes2, 0, pos);
            this.bufferPos = this.bufferSize;
            refillBuffer(true);
            while (true) {
                int i5 = size - pos;
                int i6 = this.bufferSize;
                if (i5 > i6) {
                    System.arraycopy(this.buffer, 0, bytes2, pos, i6);
                    int i7 = this.bufferSize;
                    pos += i7;
                    this.bufferPos = i7;
                    refillBuffer(true);
                } else {
                    System.arraycopy(this.buffer, 0, bytes2, pos, size - pos);
                    this.bufferPos = size - pos;
                    return bytes2;
                }
            }
        } else {
            int originalBufferPos = this.bufferPos;
            int originalBufferSize = this.bufferSize;
            this.totalBytesRetired = sizeLeft + i4;
            this.bufferPos = 0;
            this.bufferSize = 0;
            int sizeLeft2 = size - (originalBufferSize - originalBufferPos);
            Vector chunks = new Vector();
            while (sizeLeft2 > 0) {
                byte[] chunk = new byte[Math.min(sizeLeft2, 4096)];
                int pos2 = 0;
                while (pos2 < chunk.length) {
                    InputStream inputStream = this.input;
                    int n = inputStream == null ? -1 : inputStream.read(chunk, pos2, chunk.length - pos2);
                    if (n == -1) {
                        throw InvalidProtocolBufferMicroException.truncatedMessage();
                    }
                    this.totalBytesRetired += n;
                    pos2 += n;
                }
                sizeLeft2 -= chunk.length;
                chunks.addElement(chunk);
            }
            byte[] bytes3 = new byte[size];
            int pos3 = originalBufferSize - originalBufferPos;
            System.arraycopy(this.buffer, originalBufferPos, bytes3, 0, pos3);
            for (int i8 = 0; i8 < chunks.size(); i8++) {
                byte[] chunk2 = (byte[]) chunks.elementAt(i8);
                System.arraycopy(chunk2, 0, bytes3, pos3, chunk2.length);
                pos3 += chunk2.length;
            }
            return bytes3;
        }
    }

    public void skipRawBytes(int size) throws IOException {
        if (size < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        int i = this.totalBytesRetired;
        int i2 = this.bufferPos;
        int i3 = i + i2 + size;
        int i4 = this.currentLimit;
        if (i3 > i4) {
            skipRawBytes((i4 - i) - i2);
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        int i5 = this.bufferSize;
        if (size <= i5 - i2) {
            this.bufferPos = i2 + size;
            return;
        }
        int pos = i5 - i2;
        this.totalBytesRetired = i + i5;
        this.bufferPos = 0;
        this.bufferSize = 0;
        while (pos < size) {
            InputStream inputStream = this.input;
            int n = inputStream == null ? -1 : (int) inputStream.skip(size - pos);
            if (n <= 0) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            pos += n;
            this.totalBytesRetired += n;
        }
    }
}
