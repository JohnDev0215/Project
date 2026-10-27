package com.android.providers.telephony.protobuf.nano;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class CodedInputByteBufferNano {
    private final byte[] buffer;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int bufferStart;
    private int lastTag;
    private int recursionDepth;
    private int currentLimit = Integer.MAX_VALUE;
    private int recursionLimit = 64;

    public static CodedInputByteBufferNano newInstance(byte[] bArr, int i, int i2) {
        return new CodedInputByteBufferNano(bArr, i, i2);
    }

    public int readTag() throws IOException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        int rawVarint32 = readRawVarint32();
        this.lastTag = rawVarint32;
        if (rawVarint32 != 0) {
            return rawVarint32;
        }
        throw InvalidProtocolBufferNanoException.invalidTag();
    }

    public void checkLastTagWas(int i) throws InvalidProtocolBufferNanoException {
        if (this.lastTag != i) {
            throw InvalidProtocolBufferNanoException.invalidEndTag();
        }
    }

    public boolean skipField(int i) throws IOException {
        int tagWireType = WireFormatNano.getTagWireType(i);
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
            checkLastTagWas(WireFormatNano.makeTag(WireFormatNano.getTagFieldNumber(i), 4));
            return true;
        }
        if (tagWireType == 4) {
            return false;
        }
        if (tagWireType == 5) {
            readRawLittleEndian32();
            return true;
        }
        throw InvalidProtocolBufferNanoException.invalidWireType();
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

    public int readInt32() throws IOException {
        return readRawVarint32();
    }

    public String readString() throws IOException {
        int rawVarint32 = readRawVarint32();
        if (rawVarint32 <= this.bufferSize - this.bufferPos && rawVarint32 > 0) {
            String str = new String(this.buffer, this.bufferPos, rawVarint32, InternalNano.UTF_8);
            this.bufferPos += rawVarint32;
            return str;
        }
        return new String(readRawBytes(rawVarint32), InternalNano.UTF_8);
    }

    public void readMessage(MessageNano messageNano) throws IOException {
        int rawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferNanoException.recursionLimitExceeded();
        }
        int iPushLimit = pushLimit(rawVarint32);
        this.recursionDepth++;
        messageNano.mergeFrom(this);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(iPushLimit);
    }

    public int readRawVarint32() throws IOException {
        int i;
        byte rawByte = readRawByte();
        if (rawByte >= 0) {
            return rawByte;
        }
        int i2 = rawByte & 127;
        byte rawByte2 = readRawByte();
        if (rawByte2 >= 0) {
            i = rawByte2 << 7;
        } else {
            i2 |= (rawByte2 & 127) << 7;
            byte rawByte3 = readRawByte();
            if (rawByte3 >= 0) {
                i = rawByte3 << 14;
            } else {
                i2 |= (rawByte3 & 127) << 14;
                byte rawByte4 = readRawByte();
                if (rawByte4 < 0) {
                    int i3 = i2 | ((rawByte4 & 127) << 21);
                    byte rawByte5 = readRawByte();
                    int i4 = i3 | (rawByte5 << 28);
                    if (rawByte5 >= 0) {
                        return i4;
                    }
                    for (int i5 = 0; i5 < 5; i5++) {
                        if (readRawByte() >= 0) {
                            return i4;
                        }
                    }
                    throw InvalidProtocolBufferNanoException.malformedVarint();
                }
                i = rawByte4 << 21;
            }
        }
        return i | i2;
    }

    public int readRawLittleEndian32() throws IOException {
        byte rawByte = readRawByte();
        byte rawByte2 = readRawByte();
        byte rawByte3 = readRawByte();
        return ((readRawByte() & 255) << 24) | (rawByte & 255) | ((rawByte2 & 255) << 8) | ((rawByte3 & 255) << 16);
    }

    public long readRawLittleEndian64() throws IOException {
        byte rawByte = readRawByte();
        return ((((long) readRawByte()) & 255) << 8) | (((long) rawByte) & 255) | ((((long) readRawByte()) & 255) << 16) | ((((long) readRawByte()) & 255) << 24) | ((((long) readRawByte()) & 255) << 32) | ((((long) readRawByte()) & 255) << 40) | ((((long) readRawByte()) & 255) << 48) | ((((long) readRawByte()) & 255) << 56);
    }

    private CodedInputByteBufferNano(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.bufferStart = i;
        this.bufferSize = i2 + i;
        this.bufferPos = i;
    }

    public int pushLimit(int i) throws InvalidProtocolBufferNanoException {
        if (i < 0) {
            throw InvalidProtocolBufferNanoException.negativeSize();
        }
        int i2 = i + this.bufferPos;
        int i3 = this.currentLimit;
        if (i2 > i3) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        this.currentLimit = i2;
        recomputeBufferSizeAfterLimit();
        return i3;
    }

    private void recomputeBufferSizeAfterLimit() {
        int i = this.bufferSize + this.bufferSizeAfterLimit;
        this.bufferSize = i;
        int i2 = this.currentLimit;
        if (i > i2) {
            int i3 = i - i2;
            this.bufferSizeAfterLimit = i3;
            this.bufferSize = i - i3;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    public void popLimit(int i) {
        this.currentLimit = i;
        recomputeBufferSizeAfterLimit();
    }

    public boolean isAtEnd() {
        return this.bufferPos == this.bufferSize;
    }

    public int getPosition() {
        return this.bufferPos - this.bufferStart;
    }

    public byte[] getData(int i, int i2) {
        if (i2 == 0) {
            return WireFormatNano.EMPTY_BYTES;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.bufferStart + i, bArr, 0, i2);
        return bArr;
    }

    public void rewindToPosition(int i) {
        int i2 = this.bufferPos;
        int i3 = this.bufferStart;
        if (i > i2 - i3) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.bufferPos - this.bufferStart));
        }
        if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        }
        this.bufferPos = i3 + i;
    }

    public byte readRawByte() throws IOException {
        int i = this.bufferPos;
        if (i == this.bufferSize) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i + 1;
        return bArr[i];
    }

    public byte[] readRawBytes(int i) throws IOException {
        if (i < 0) {
            throw InvalidProtocolBufferNanoException.negativeSize();
        }
        int i2 = this.bufferPos;
        int i3 = i2 + i;
        int i4 = this.currentLimit;
        if (i3 > i4) {
            skipRawBytes(i4 - i2);
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        if (i <= this.bufferSize - i2) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, i2, bArr, 0, i);
            this.bufferPos += i;
            return bArr;
        }
        throw InvalidProtocolBufferNanoException.truncatedMessage();
    }

    public void skipRawBytes(int i) throws IOException {
        if (i < 0) {
            throw InvalidProtocolBufferNanoException.negativeSize();
        }
        int i2 = this.bufferPos;
        int i3 = i2 + i;
        int i4 = this.currentLimit;
        if (i3 > i4) {
            skipRawBytes(i4 - i2);
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        if (i <= this.bufferSize - i2) {
            this.bufferPos = i2 + i;
            return;
        }
        throw InvalidProtocolBufferNanoException.truncatedMessage();
    }
}
