package com.google.protobuf;

import com.google.protobuf.Utf8;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes7.dex */
public abstract class CodedOutputStream extends ByteOutput {
    private boolean serializationDeterministic;
    CodedOutputStreamWriter wrapper;
    private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.hasUnsafeArrayOperations();

    public static int computeBoolSizeNoTag(final boolean unused) {
        return 1;
    }

    public static int computeDoubleSizeNoTag(final double unused) {
        return 8;
    }

    public static int computeFixed32SizeNoTag(final int unused) {
        return 4;
    }

    public static int computeFixed64SizeNoTag(final long unused) {
        return 8;
    }

    public static int computeFloatSizeNoTag(final float unused) {
        return 4;
    }

    static int computePreferredBufferSize(int dataLength) {
        if (dataLength > 4096) {
            return 4096;
        }
        return dataLength;
    }

    public static int computeSFixed32SizeNoTag(final int unused) {
        return 4;
    }

    public static int computeSFixed64SizeNoTag(final long unused) {
        return 8;
    }

    public static int computeUInt32SizeNoTag(final int value) {
        if ((value & (-128)) == 0) {
            return 1;
        }
        if ((value & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & value) == 0) {
            return 3;
        }
        return (value & (-268435456)) == 0 ? 4 : 5;
    }

    public static int computeUInt64SizeNoTag(long value) {
        int i;
        if (((-128) & value) == 0) {
            return 1;
        }
        if (value < 0) {
            return 10;
        }
        if (((-34359738368L) & value) != 0) {
            i = 6;
            value >>>= 28;
        } else {
            i = 2;
        }
        if (((-2097152) & value) != 0) {
            i += 2;
            value >>>= 14;
        }
        return (value & (-16384)) != 0 ? i + 1 : i;
    }

    public static int encodeZigZag32(final int n) {
        return (n >> 31) ^ (n << 1);
    }

    public static long encodeZigZag64(final long n) {
        return (n >> 63) ^ (n << 1);
    }

    public abstract void flush() throws IOException;

    public abstract int getTotalBytesWritten();

    public abstract int spaceLeft();

    public abstract void write(byte value) throws IOException;

    public abstract void writeBool(int fieldNumber, boolean value) throws IOException;

    abstract void writeByteArrayNoTag(final byte[] value, final int offset, final int length) throws IOException;

    public abstract void writeBytes(int fieldNumber, ByteString value) throws IOException;

    public abstract void writeBytesNoTag(final ByteString value) throws IOException;

    public abstract void writeFixed32(int fieldNumber, int value) throws IOException;

    public abstract void writeFixed32NoTag(int value) throws IOException;

    public abstract void writeFixed64(int fieldNumber, long value) throws IOException;

    public abstract void writeFixed64NoTag(long value) throws IOException;

    public abstract void writeInt32(int fieldNumber, int value) throws IOException;

    public abstract void writeInt32NoTag(final int value) throws IOException;

    @Override // com.google.protobuf.ByteOutput
    public abstract void writeLazy(byte[] value, int offset, int length) throws IOException;

    public abstract void writeMessage(final int fieldNumber, final MessageLite value) throws IOException;

    abstract void writeMessage(final int fieldNumber, final MessageLite value, Schema schema) throws IOException;

    public abstract void writeMessageNoTag(final MessageLite value) throws IOException;

    public abstract void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException;

    public abstract void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException;

    public abstract void writeString(int fieldNumber, String value) throws IOException;

    public abstract void writeStringNoTag(String value) throws IOException;

    public abstract void writeTag(int fieldNumber, int wireType) throws IOException;

    public abstract void writeUInt32(int fieldNumber, int value) throws IOException;

    public abstract void writeUInt32NoTag(int value) throws IOException;

    public abstract void writeUInt64(int fieldNumber, long value) throws IOException;

    public abstract void writeUInt64NoTag(long value) throws IOException;

    public static CodedOutputStream newInstance(final OutputStream output, final int bufferSize) {
        return new OutputStreamEncoder(output, bufferSize);
    }

    public static CodedOutputStream newInstance(final byte[] flatArray) {
        return newInstance(flatArray, 0, flatArray.length);
    }

    public static CodedOutputStream newInstance(final byte[] flatArray, final int offset, final int length) {
        return new ArrayEncoder(flatArray, offset, length);
    }

    boolean isSerializationDeterministic() {
        return this.serializationDeterministic;
    }

    private CodedOutputStream() {
    }

    public final void writeSInt32(final int fieldNumber, final int value) throws IOException {
        writeUInt32(fieldNumber, encodeZigZag32(value));
    }

    public final void writeSFixed32(final int fieldNumber, final int value) throws IOException {
        writeFixed32(fieldNumber, value);
    }

    public final void writeInt64(final int fieldNumber, final long value) throws IOException {
        writeUInt64(fieldNumber, value);
    }

    public final void writeSInt64(final int fieldNumber, final long value) throws IOException {
        writeUInt64(fieldNumber, encodeZigZag64(value));
    }

    public final void writeSFixed64(final int fieldNumber, final long value) throws IOException {
        writeFixed64(fieldNumber, value);
    }

    public final void writeFloat(final int fieldNumber, final float value) throws IOException {
        writeFixed32(fieldNumber, Float.floatToRawIntBits(value));
    }

    public final void writeDouble(final int fieldNumber, final double value) throws IOException {
        writeFixed64(fieldNumber, Double.doubleToRawLongBits(value));
    }

    public final void writeEnum(final int fieldNumber, final int value) throws IOException {
        writeInt32(fieldNumber, value);
    }

    public final void writeSInt32NoTag(final int value) throws IOException {
        writeUInt32NoTag(encodeZigZag32(value));
    }

    public final void writeSFixed32NoTag(final int value) throws IOException {
        writeFixed32NoTag(value);
    }

    public final void writeInt64NoTag(final long value) throws IOException {
        writeUInt64NoTag(value);
    }

    public final void writeSInt64NoTag(final long value) throws IOException {
        writeUInt64NoTag(encodeZigZag64(value));
    }

    public final void writeSFixed64NoTag(final long value) throws IOException {
        writeFixed64NoTag(value);
    }

    public final void writeFloatNoTag(final float value) throws IOException {
        writeFixed32NoTag(Float.floatToRawIntBits(value));
    }

    public final void writeDoubleNoTag(final double value) throws IOException {
        writeFixed64NoTag(Double.doubleToRawLongBits(value));
    }

    public final void writeBoolNoTag(boolean z) throws IOException {
        write(z ? (byte) 1 : (byte) 0);
    }

    public final void writeEnumNoTag(final int value) throws IOException {
        writeInt32NoTag(value);
    }

    public final void writeByteArrayNoTag(final byte[] value) throws IOException {
        writeByteArrayNoTag(value, 0, value.length);
    }

    public static int computeInt32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeInt32SizeNoTag(value);
    }

    public static int computeUInt32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeUInt32SizeNoTag(value);
    }

    public static int computeSInt32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeSInt32SizeNoTag(value);
    }

    public static int computeFixed32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeFixed32SizeNoTag(value);
    }

    public static int computeSFixed32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeSFixed32SizeNoTag(value);
    }

    public static int computeInt64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeInt64SizeNoTag(value);
    }

    public static int computeUInt64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeUInt64SizeNoTag(value);
    }

    public static int computeSInt64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeSInt64SizeNoTag(value);
    }

    public static int computeFixed64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeFixed64SizeNoTag(value);
    }

    public static int computeSFixed64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeSFixed64SizeNoTag(value);
    }

    public static int computeFloatSize(final int fieldNumber, final float value) {
        return computeTagSize(fieldNumber) + computeFloatSizeNoTag(value);
    }

    public static int computeDoubleSize(final int fieldNumber, final double value) {
        return computeTagSize(fieldNumber) + computeDoubleSizeNoTag(value);
    }

    public static int computeBoolSize(final int fieldNumber, final boolean value) {
        return computeTagSize(fieldNumber) + computeBoolSizeNoTag(value);
    }

    public static int computeEnumSize(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeEnumSizeNoTag(value);
    }

    public static int computeStringSize(final int fieldNumber, final String value) {
        return computeTagSize(fieldNumber) + computeStringSizeNoTag(value);
    }

    public static int computeBytesSize(final int fieldNumber, final ByteString value) {
        return computeTagSize(fieldNumber) + computeBytesSizeNoTag(value);
    }

    public static int computeLazyFieldSize(final int fieldNumber, final LazyFieldLite value) {
        return computeTagSize(fieldNumber) + computeLazyFieldSizeNoTag(value);
    }

    public static int computeMessageSize(final int fieldNumber, final MessageLite value) {
        return computeTagSize(fieldNumber) + computeMessageSizeNoTag(value);
    }

    static int computeMessageSize(final int fieldNumber, final MessageLite value, final Schema schema) {
        return computeTagSize(fieldNumber) + computeMessageSizeNoTag(value, schema);
    }

    public static int computeMessageSetExtensionSize(final int fieldNumber, final MessageLite value) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, fieldNumber) + computeMessageSize(3, value);
    }

    public static int computeRawMessageSetExtensionSize(final int fieldNumber, final ByteString value) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, fieldNumber) + computeBytesSize(3, value);
    }

    public static int computeLazyFieldMessageSetExtensionSize(final int fieldNumber, final LazyFieldLite value) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, fieldNumber) + computeLazyFieldSize(3, value);
    }

    public static int computeTagSize(final int fieldNumber) {
        return computeUInt32SizeNoTag(WireFormat.makeTag(fieldNumber, 0));
    }

    public static int computeInt32SizeNoTag(final int value) {
        if (value >= 0) {
            return computeUInt32SizeNoTag(value);
        }
        return 10;
    }

    public static int computeSInt32SizeNoTag(final int value) {
        return computeUInt32SizeNoTag(encodeZigZag32(value));
    }

    public static int computeInt64SizeNoTag(final long value) {
        return computeUInt64SizeNoTag(value);
    }

    public static int computeSInt64SizeNoTag(final long value) {
        return computeUInt64SizeNoTag(encodeZigZag64(value));
    }

    public static int computeEnumSizeNoTag(final int value) {
        return computeInt32SizeNoTag(value);
    }

    public static int computeStringSizeNoTag(final String value) {
        int length;
        try {
            length = Utf8.encodedLength(value);
        } catch (Utf8.UnpairedSurrogateException unused) {
            length = value.getBytes(Internal.UTF_8).length;
        }
        return computeLengthDelimitedFieldSize(length);
    }

    public static int computeLazyFieldSizeNoTag(final LazyFieldLite value) {
        return computeLengthDelimitedFieldSize(value.getSerializedSize());
    }

    public static int computeBytesSizeNoTag(final ByteString value) {
        return computeLengthDelimitedFieldSize(value.size());
    }

    public static int computeByteArraySizeNoTag(final byte[] value) {
        return computeLengthDelimitedFieldSize(value.length);
    }

    public static int computeMessageSizeNoTag(final MessageLite value) {
        return computeLengthDelimitedFieldSize(value.getSerializedSize());
    }

    static int computeMessageSizeNoTag(final MessageLite value, final Schema schema) {
        return computeLengthDelimitedFieldSize(((AbstractMessageLite) value).getSerializedSize(schema));
    }

    static int computeLengthDelimitedFieldSize(int fieldLength) {
        return computeUInt32SizeNoTag(fieldLength) + fieldLength;
    }

    public final void checkNoSpaceLeft() {
        if (spaceLeft() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public static class OutOfSpaceException extends IOException {
        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        OutOfSpaceException(Throwable cause) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", cause);
        }

        OutOfSpaceException(String explanationMessage, Throwable cause) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + explanationMessage, cause);
        }
    }

    final void inefficientWriteStringNoTag(String value, Utf8.UnpairedSurrogateException cause) throws IOException {
        logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) cause);
        byte[] bytes = value.getBytes(Internal.UTF_8);
        try {
            writeUInt32NoTag(bytes.length);
            writeLazy(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
        }
    }

    @Deprecated
    public final void writeGroup(final int fieldNumber, final MessageLite value) throws IOException {
        writeTag(fieldNumber, 3);
        writeGroupNoTag(value);
        writeTag(fieldNumber, 4);
    }

    @Deprecated
    final void writeGroup(final int fieldNumber, final MessageLite value, Schema schema) throws IOException {
        writeTag(fieldNumber, 3);
        writeGroupNoTag(value, schema);
        writeTag(fieldNumber, 4);
    }

    @Deprecated
    public final void writeGroupNoTag(final MessageLite value) throws IOException {
        value.writeTo(this);
    }

    @Deprecated
    final void writeGroupNoTag(final MessageLite value, Schema schema) throws IOException {
        schema.writeTo(value, this.wrapper);
    }

    @Deprecated
    public static int computeGroupSize(final int fieldNumber, final MessageLite value) {
        return (computeTagSize(fieldNumber) * 2) + value.getSerializedSize();
    }

    @Deprecated
    static int computeGroupSize(final int fieldNumber, final MessageLite value, Schema schema) {
        return (computeTagSize(fieldNumber) * 2) + computeGroupSizeNoTag(value, schema);
    }

    @Deprecated
    public static int computeGroupSizeNoTag(final MessageLite value) {
        return value.getSerializedSize();
    }

    @Deprecated
    static int computeGroupSizeNoTag(final MessageLite value, Schema schema) {
        return ((AbstractMessageLite) value).getSerializedSize(schema);
    }

    private static class ArrayEncoder extends CodedOutputStream {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        @Override // com.google.protobuf.CodedOutputStream
        public void flush() {
        }

        ArrayEncoder(byte[] buffer, int offset, int length) {
            super();
            if (buffer == null) {
                throw new NullPointerException("buffer");
            }
            int i = offset + length;
            if ((offset | length | (buffer.length - i)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(buffer.length), Integer.valueOf(offset), Integer.valueOf(length)));
            }
            this.buffer = buffer;
            this.offset = offset;
            this.position = offset;
            this.limit = i;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeTag(final int fieldNumber, final int wireType) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 0);
            writeInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 0);
            writeUInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 5);
            writeFixed32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64(final int fieldNumber, final long value) throws IOException {
            writeTag(fieldNumber, 0);
            writeUInt64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64(final int fieldNumber, final long value) throws IOException {
            writeTag(fieldNumber, 1);
            writeFixed64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBool(int i, boolean z) throws IOException {
            writeTag(i, 0);
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeString(final int fieldNumber, final String value) throws IOException {
            writeTag(fieldNumber, 2);
            writeStringNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(fieldNumber, 2);
            writeBytesNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytesNoTag(final ByteString value) throws IOException {
            writeUInt32NoTag(value.size());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
            writeUInt32NoTag(length);
            write(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(fieldNumber, 2);
            writeMessageNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        final void writeMessage(final int fieldNumber, final MessageLite value, Schema schema) throws IOException {
            writeTag(fieldNumber, 2);
            writeUInt32NoTag(((AbstractMessageLite) value).getSerializedSize(schema));
            schema.writeTo(value, this.wrapper);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeMessage(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeBytes(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessageNoTag(final MessageLite value) throws IOException {
            writeUInt32NoTag(value.getSerializedSize());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void write(byte value) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = value;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32NoTag(int value) throws IOException {
            if (value >= 0) {
                writeUInt32NoTag(value);
            } else {
                writeUInt64NoTag(value);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32NoTag(int value) throws IOException {
            while ((value & (-128)) != 0) {
                try {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((value & WorkQueueKt.MASK) | 128);
                    value >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr2 = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr2[i2] = (byte) value;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32NoTag(int value) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                int i2 = i + 1;
                bArr[i] = (byte) (value & 255);
                int i3 = i2 + 1;
                bArr[i2] = (byte) ((value >> 8) & 255);
                int i4 = i3 + 1;
                bArr[i3] = (byte) ((value >> 16) & 255);
                this.position = i4 + 1;
                bArr[i4] = (byte) ((value >> 24) & 255);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64NoTag(long value) throws IOException {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS && spaceLeft() >= 10) {
                while ((value & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    UnsafeUtil.putByte(bArr, i, (byte) ((((int) value) & WorkQueueKt.MASK) | 128));
                    value >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                UnsafeUtil.putByte(bArr2, i2, (byte) value);
                return;
            }
            while ((value & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr3[i3] = (byte) ((((int) value) & WorkQueueKt.MASK) | 128);
                    value >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr4[i4] = (byte) value;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64NoTag(long value) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                int i2 = i + 1;
                bArr[i] = (byte) (((int) value) & 255);
                int i3 = i2 + 1;
                bArr[i2] = (byte) (((int) (value >> 8)) & 255);
                int i4 = i3 + 1;
                bArr[i3] = (byte) (((int) (value >> 16)) & 255);
                int i5 = i4 + 1;
                bArr[i4] = (byte) (((int) (value >> 24)) & 255);
                int i6 = i5 + 1;
                bArr[i5] = (byte) (((int) (value >> 32)) & 255);
                int i7 = i6 + 1;
                bArr[i6] = (byte) (((int) (value >> 40)) & 255);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (((int) (value >> 48)) & 255);
                this.position = i8 + 1;
                bArr[i8] = (byte) (((int) (value >> 56)) & 255);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        public final void write(byte[] value, int offset, int length) throws IOException {
            try {
                System.arraycopy(value, offset, this.buffer, this.position, length);
                this.position += length;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(length)), e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public final void writeLazy(byte[] value, int offset, int length) throws IOException {
            write(value, offset, length);
        }

        public final void write(ByteBuffer value) throws IOException {
            int iRemaining = value.remaining();
            try {
                value.get(this.buffer, this.position, iRemaining);
                this.position += iRemaining;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(iRemaining)), e);
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public final void writeLazy(ByteBuffer value) throws IOException {
            write(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeStringNoTag(String value) throws IOException {
            int i = this.position;
            try {
                int iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(value.length() * 3);
                int iComputeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(value.length());
                if (iComputeUInt32SizeNoTag2 == iComputeUInt32SizeNoTag) {
                    int i2 = i + iComputeUInt32SizeNoTag2;
                    this.position = i2;
                    int iEncode = Utf8.encode(value, this.buffer, i2, spaceLeft());
                    this.position = i;
                    writeUInt32NoTag((iEncode - i) - iComputeUInt32SizeNoTag2);
                    this.position = iEncode;
                } else {
                    writeUInt32NoTag(Utf8.encodedLength(value));
                    this.position = Utf8.encode(value, this.buffer, this.position, spaceLeft());
                }
            } catch (Utf8.UnpairedSurrogateException e) {
                this.position = i;
                inefficientWriteStringNoTag(value, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int spaceLeft() {
            return this.limit - this.position;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int getTotalBytesWritten() {
            return this.position - this.offset;
        }
    }

    private static abstract class AbstractBufferedEncoder extends CodedOutputStream {
        final byte[] buffer;
        final int limit;
        int position;
        int totalBytesWritten;

        AbstractBufferedEncoder(int bufferSize) {
            super();
            if (bufferSize < 0) {
                throw new IllegalArgumentException("bufferSize must be >= 0");
            }
            byte[] bArr = new byte[Math.max(bufferSize, 20)];
            this.buffer = bArr;
            this.limit = bArr.length;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int spaceLeft() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int getTotalBytesWritten() {
            return this.totalBytesWritten;
        }

        final void buffer(byte value) {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = value;
            this.totalBytesWritten++;
        }

        final void bufferTag(final int fieldNumber, final int wireType) {
            bufferUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
        }

        final void bufferInt32NoTag(final int value) {
            if (value >= 0) {
                bufferUInt32NoTag(value);
            } else {
                bufferUInt64NoTag(value);
            }
        }

        final void bufferUInt32NoTag(int value) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j = this.position;
                while ((value & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    UnsafeUtil.putByte(bArr, i, (byte) ((value & WorkQueueKt.MASK) | 128));
                    value >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                UnsafeUtil.putByte(bArr2, i2, (byte) value);
                this.totalBytesWritten += (int) (((long) this.position) - j);
                return;
            }
            while ((value & (-128)) != 0) {
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) ((value & WorkQueueKt.MASK) | 128);
                this.totalBytesWritten++;
                value >>>= 7;
            }
            byte[] bArr4 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr4[i4] = (byte) value;
            this.totalBytesWritten++;
        }

        final void bufferUInt64NoTag(long value) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j = this.position;
                while ((value & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    UnsafeUtil.putByte(bArr, i, (byte) ((((int) value) & WorkQueueKt.MASK) | 128));
                    value >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                UnsafeUtil.putByte(bArr2, i2, (byte) value);
                this.totalBytesWritten += (int) (((long) this.position) - j);
                return;
            }
            while ((value & (-128)) != 0) {
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) ((((int) value) & WorkQueueKt.MASK) | 128);
                this.totalBytesWritten++;
                value >>>= 7;
            }
            byte[] bArr4 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr4[i4] = (byte) value;
            this.totalBytesWritten++;
        }

        final void bufferFixed32NoTag(int value) {
            byte[] bArr = this.buffer;
            int i = this.position;
            int i2 = i + 1;
            bArr[i] = (byte) (value & 255);
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((value >> 8) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((value >> 16) & 255);
            this.position = i4 + 1;
            bArr[i4] = (byte) ((value >> 24) & 255);
            this.totalBytesWritten += 4;
        }

        final void bufferFixed64NoTag(long value) {
            byte[] bArr = this.buffer;
            int i = this.position;
            int i2 = i + 1;
            bArr[i] = (byte) (value & 255);
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((value >> 8) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((value >> 16) & 255);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (255 & (value >> 24));
            int i6 = i5 + 1;
            bArr[i5] = (byte) (((int) (value >> 32)) & 255);
            int i7 = i6 + 1;
            bArr[i6] = (byte) (((int) (value >> 40)) & 255);
            int i8 = i7 + 1;
            bArr[i7] = (byte) (((int) (value >> 48)) & 255);
            this.position = i8 + 1;
            bArr[i8] = (byte) (((int) (value >> 56)) & 255);
            this.totalBytesWritten += 8;
        }
    }

    private static final class OutputStreamEncoder extends AbstractBufferedEncoder {
        private final OutputStream out;

        OutputStreamEncoder(OutputStream out, int bufferSize) {
            super(bufferSize);
            if (out == null) {
                throw new NullPointerException("out");
            }
            this.out = out;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeTag(final int fieldNumber, final int wireType) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeInt32(final int fieldNumber, final int value) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(fieldNumber, 0);
            bufferInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt32(final int fieldNumber, final int value) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(fieldNumber, 0);
            bufferUInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed32(final int fieldNumber, final int value) throws IOException {
            flushIfNotAvailable(14);
            bufferTag(fieldNumber, 5);
            bufferFixed32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt64(final int fieldNumber, final long value) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(fieldNumber, 0);
            bufferUInt64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed64(final int fieldNumber, final long value) throws IOException {
            flushIfNotAvailable(18);
            bufferTag(fieldNumber, 1);
            bufferFixed64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBool(int i, boolean z) throws IOException {
            flushIfNotAvailable(11);
            bufferTag(i, 0);
            buffer(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeString(final int fieldNumber, final String value) throws IOException {
            writeTag(fieldNumber, 2);
            writeStringNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(fieldNumber, 2);
            writeBytesNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBytesNoTag(final ByteString value) throws IOException {
            writeUInt32NoTag(value.size());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
            writeUInt32NoTag(length);
            write(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(fieldNumber, 2);
            writeMessageNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        void writeMessage(final int fieldNumber, final MessageLite value, Schema schema) throws IOException {
            writeTag(fieldNumber, 2);
            writeMessageNoTag(value, schema);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeMessage(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeBytes(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessageNoTag(final MessageLite value) throws IOException {
            writeUInt32NoTag(value.getSerializedSize());
            value.writeTo(this);
        }

        void writeMessageNoTag(final MessageLite value, Schema schema) throws IOException {
            writeUInt32NoTag(((AbstractMessageLite) value).getSerializedSize(schema));
            schema.writeTo(value, this.wrapper);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void write(byte value) throws IOException {
            if (this.position == this.limit) {
                doFlush();
            }
            buffer(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeInt32NoTag(int value) throws IOException {
            if (value >= 0) {
                writeUInt32NoTag(value);
            } else {
                writeUInt64NoTag(value);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int value) throws IOException {
            flushIfNotAvailable(5);
            bufferUInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed32NoTag(final int value) throws IOException {
            flushIfNotAvailable(4);
            bufferFixed32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt64NoTag(long value) throws IOException {
            flushIfNotAvailable(10);
            bufferUInt64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed64NoTag(final long value) throws IOException {
            flushIfNotAvailable(8);
            bufferFixed64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeStringNoTag(String value) throws IOException {
            int iEncodedLength;
            try {
                int length = value.length() * 3;
                int iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(length);
                int i = iComputeUInt32SizeNoTag + length;
                int i2 = this.limit;
                if (i > i2) {
                    byte[] bArr = new byte[length];
                    int iEncode = Utf8.encode(value, bArr, 0, length);
                    writeUInt32NoTag(iEncode);
                    writeLazy(bArr, 0, iEncode);
                    return;
                }
                if (i > i2 - this.position) {
                    doFlush();
                }
                int iComputeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(value.length());
                int i3 = this.position;
                try {
                    if (iComputeUInt32SizeNoTag2 == iComputeUInt32SizeNoTag) {
                        int i4 = i3 + iComputeUInt32SizeNoTag2;
                        this.position = i4;
                        int iEncode2 = Utf8.encode(value, this.buffer, i4, this.limit - i4);
                        this.position = i3;
                        iEncodedLength = (iEncode2 - i3) - iComputeUInt32SizeNoTag2;
                        bufferUInt32NoTag(iEncodedLength);
                        this.position = iEncode2;
                    } else {
                        iEncodedLength = Utf8.encodedLength(value);
                        bufferUInt32NoTag(iEncodedLength);
                        this.position = Utf8.encode(value, this.buffer, this.position, iEncodedLength);
                    }
                    this.totalBytesWritten += iEncodedLength;
                } catch (Utf8.UnpairedSurrogateException e) {
                    this.totalBytesWritten -= this.position - i3;
                    this.position = i3;
                    throw e;
                } catch (ArrayIndexOutOfBoundsException e2) {
                    throw new OutOfSpaceException(e2);
                }
            } catch (Utf8.UnpairedSurrogateException e3) {
                inefficientWriteStringNoTag(value, e3);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void flush() throws IOException {
            if (this.position > 0) {
                doFlush();
            }
        }

        public void write(byte[] value, int offset, int length) throws IOException {
            int i = this.limit;
            int i2 = this.position;
            if (i - i2 >= length) {
                System.arraycopy(value, offset, this.buffer, i2, length);
                this.position += length;
                this.totalBytesWritten += length;
                return;
            }
            int i3 = i - i2;
            System.arraycopy(value, offset, this.buffer, i2, i3);
            int i4 = offset + i3;
            int i5 = length - i3;
            this.position = this.limit;
            this.totalBytesWritten += i3;
            doFlush();
            if (i5 <= this.limit) {
                System.arraycopy(value, i4, this.buffer, 0, i5);
                this.position = i5;
            } else {
                this.out.write(value, i4, i5);
            }
            this.totalBytesWritten += i5;
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void writeLazy(byte[] value, int offset, int length) throws IOException {
            write(value, offset, length);
        }

        public void write(ByteBuffer value) throws IOException {
            int iRemaining = value.remaining();
            int i = this.limit;
            int i2 = this.position;
            if (i - i2 >= iRemaining) {
                value.get(this.buffer, i2, iRemaining);
                this.position += iRemaining;
                this.totalBytesWritten += iRemaining;
                return;
            }
            int i3 = i - i2;
            value.get(this.buffer, i2, i3);
            int i4 = iRemaining - i3;
            this.position = this.limit;
            this.totalBytesWritten += i3;
            doFlush();
            while (true) {
                int i5 = this.limit;
                if (i4 > i5) {
                    value.get(this.buffer, 0, i5);
                    this.out.write(this.buffer, 0, this.limit);
                    int i6 = this.limit;
                    i4 -= i6;
                    this.totalBytesWritten += i6;
                } else {
                    value.get(this.buffer, 0, i4);
                    this.position = i4;
                    this.totalBytesWritten += i4;
                    return;
                }
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer value) throws IOException {
            write(value);
        }

        private void flushIfNotAvailable(int requiredSize) throws IOException {
            if (this.limit - this.position < requiredSize) {
                doFlush();
            }
        }

        private void doFlush() throws IOException {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }
    }
}
