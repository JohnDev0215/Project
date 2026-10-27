package org.chromium.mojo.bindings;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

/* JADX INFO: loaded from: classes3.dex */
public class Decoder {
    private final int mBaseOffset;
    private final Message mMessage;
    private final Validator mValidator;

    public AssociatedInterfaceRequestNotSupported readAssociatedInterfaceRequestNotSupported(int i, boolean z) {
        return null;
    }

    public AssociatedInterfaceRequestNotSupported[] readAssociatedInterfaceRequestNotSupporteds(int i, int i2, int i3) {
        return null;
    }

    public AssociatedInterfaceNotSupported readAssociatedServiceInterfaceNotSupported(int i, boolean z) {
        return null;
    }

    public AssociatedInterfaceNotSupported[] readAssociatedServiceInterfaceNotSupporteds(int i, int i2, int i3) {
        return null;
    }

    static final class Validator {
        private final long mMaxMemory;
        private int mMinNextClaimedHandle = 0;
        private long mMinNextMemory = 0;
        private final long mNumberOfHandles;

        Validator(long j, int i) {
            this.mMaxMemory = j;
            this.mNumberOfHandles = i;
        }

        public void claimHandle(int i) {
            if (i < this.mMinNextClaimedHandle) {
                throw new DeserializationException("Trying to access handle out of order.");
            }
            if (i >= this.mNumberOfHandles) {
                throw new DeserializationException("Trying to access non present handle.");
            }
            this.mMinNextClaimedHandle = i + 1;
        }

        public void claimMemory(long j, long j2) {
            if (j % 8 != 0) {
                throw new DeserializationException("Incorrect starting alignment: " + j + ".");
            }
            if (j < this.mMinNextMemory) {
                throw new DeserializationException("Trying to access memory out of order.");
            }
            if (j2 < j) {
                throw new DeserializationException("Incorrect memory range.");
            }
            if (j2 > this.mMaxMemory) {
                throw new DeserializationException("Trying to access out of range memory.");
            }
            this.mMinNextMemory = BindingsHelper.align(j2);
        }
    }

    public Decoder(Message message) {
        this(message, new Validator(message.getData().limit(), message.getHandles().size()), 0);
    }

    private Decoder(Message message, Validator validator, int i) {
        this.mMessage = message;
        message.getData().order(ByteOrder.LITTLE_ENDIAN);
        this.mBaseOffset = i;
        this.mValidator = validator;
    }

    public DataHeader readDataHeader() {
        this.mValidator.claimMemory(this.mBaseOffset, r1 + 8);
        DataHeader dataHeaderAtOffset = readDataHeaderAtOffset(0, false);
        Validator validator = this.mValidator;
        int i = this.mBaseOffset;
        validator.claimMemory(i + 8, i + dataHeaderAtOffset.size);
        return dataHeaderAtOffset;
    }

    public DataHeader readDataHeaderForUnion(int i) {
        DataHeader dataHeaderAtOffset = readDataHeaderAtOffset(i, true);
        if (dataHeaderAtOffset.size == 0) {
            if (dataHeaderAtOffset.elementsOrVersion != 0) {
                throw new DeserializationException("Unexpected version tag for a null union. Expecting 0, found: " + dataHeaderAtOffset.elementsOrVersion);
            }
        } else if (dataHeaderAtOffset.size != 16) {
            throw new DeserializationException("Unexpected size of an union. The size must be 0 for a null union, or 16 for a non-null union.");
        }
        return dataHeaderAtOffset;
    }

    public Decoder decoderForSerializedUnion() {
        this.mValidator.claimMemory(0L, 16L);
        return this;
    }

    private DataHeader readDataHeaderAtOffset(int i, boolean z) {
        int i2 = readInt(i + 0);
        int i3 = readInt(i + 4);
        if (i2 < 0) {
            throw new DeserializationException("Negative size. Unsigned integers are not valid for java.");
        }
        if (i3 < 0 && (!z || i3 != -1)) {
            throw new DeserializationException("Negative elements or version. Unsigned integers are not valid for java.");
        }
        return new DataHeader(i2, i3);
    }

    public DataHeader readAndValidateDataHeader(DataHeader[] dataHeaderArr) {
        DataHeader dataHeader;
        DataHeader dataHeader2 = readDataHeader();
        int length = dataHeaderArr.length - 1;
        if (dataHeader2.elementsOrVersion <= dataHeaderArr[length].elementsOrVersion) {
            while (true) {
                if (length < 0) {
                    dataHeader = null;
                    break;
                }
                dataHeader = dataHeaderArr[length];
                if (dataHeader2.elementsOrVersion >= dataHeader.elementsOrVersion) {
                    break;
                }
                length--;
            }
            if (dataHeader == null || dataHeader.size != dataHeader2.size) {
                throw new DeserializationException("Header doesn't correspond to any known version.");
            }
        } else if (dataHeader2.size < dataHeaderArr[length].size) {
            throw new DeserializationException("Message newer than the last known version cannot be shorter than required by the last known version.");
        }
        return dataHeader2;
    }

    public DataHeader readDataHeaderForPointerArray(int i) {
        return readDataHeaderForArray(8L, i);
    }

    public DataHeader readDataHeaderForUnionArray(int i) {
        return readDataHeaderForArray(16L, i);
    }

    public void readDataHeaderForMap() {
        DataHeader dataHeader = readDataHeader();
        if (dataHeader.size != BindingsHelper.MAP_STRUCT_HEADER.size) {
            throw new DeserializationException("Incorrect header for map. The size is incorrect.");
        }
        if (dataHeader.elementsOrVersion != BindingsHelper.MAP_STRUCT_HEADER.elementsOrVersion) {
            throw new DeserializationException("Incorrect header for map. The version is incorrect.");
        }
    }

    public byte readByte(int i) {
        validateBufferSize(i, 1);
        return this.mMessage.getData().get(this.mBaseOffset + i);
    }

    public boolean readBoolean(int i, int i2) {
        validateBufferSize(i, 1);
        return (readByte(i) & (1 << i2)) != 0;
    }

    public short readShort(int i) {
        validateBufferSize(i, 2);
        return this.mMessage.getData().getShort(this.mBaseOffset + i);
    }

    public int readInt(int i) {
        validateBufferSize(i, 4);
        return this.mMessage.getData().getInt(this.mBaseOffset + i);
    }

    public float readFloat(int i) {
        validateBufferSize(i, 4);
        return this.mMessage.getData().getFloat(this.mBaseOffset + i);
    }

    public long readLong(int i) {
        validateBufferSize(i, 8);
        return this.mMessage.getData().getLong(this.mBaseOffset + i);
    }

    public double readDouble(int i) {
        validateBufferSize(i, 8);
        return this.mMessage.getData().getDouble(this.mBaseOffset + i);
    }

    public Decoder readPointer(int i, boolean z) {
        int i2 = this.mBaseOffset + i;
        long j = readLong(i);
        if (j != 0) {
            return getDecoderAtPosition((int) (((long) i2) + j));
        }
        if (z) {
            return null;
        }
        throw new DeserializationException("Trying to decode null pointer for a non-nullable type.");
    }

    public boolean[] readBooleans(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        DataHeader dataHeaderForBooleanArray = pointer.readDataHeaderForBooleanArray(i3);
        int i4 = (dataHeaderForBooleanArray.elementsOrVersion + 7) / 8;
        byte[] bArr = new byte[i4];
        pointer.mMessage.getData().position(pointer.mBaseOffset + 8);
        pointer.mMessage.getData().get(bArr);
        int i5 = dataHeaderForBooleanArray.elementsOrVersion;
        boolean[] zArr = new boolean[i5];
        for (int i6 = 0; i6 < i4; i6++) {
            for (int i7 = 0; i7 < 8; i7++) {
                int i8 = (i6 * 8) + i7;
                if (i8 < i5) {
                    zArr[i8] = (bArr[i6] & (1 << i7)) != 0;
                }
            }
        }
        return zArr;
    }

    public byte[] readBytes(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        byte[] bArr = new byte[pointer.readDataHeaderForArray(1L, i3).elementsOrVersion];
        pointer.mMessage.getData().position(pointer.mBaseOffset + 8);
        pointer.mMessage.getData().get(bArr);
        return bArr;
    }

    public short[] readShorts(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        short[] sArr = new short[pointer.readDataHeaderForArray(2L, i3).elementsOrVersion];
        pointer.mMessage.getData().position(pointer.mBaseOffset + 8);
        pointer.mMessage.getData().asShortBuffer().get(sArr);
        return sArr;
    }

    public int[] readInts(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        int[] iArr = new int[pointer.readDataHeaderForArray(4L, i3).elementsOrVersion];
        pointer.mMessage.getData().position(pointer.mBaseOffset + 8);
        pointer.mMessage.getData().asIntBuffer().get(iArr);
        return iArr;
    }

    public float[] readFloats(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        float[] fArr = new float[pointer.readDataHeaderForArray(4L, i3).elementsOrVersion];
        pointer.mMessage.getData().position(pointer.mBaseOffset + 8);
        pointer.mMessage.getData().asFloatBuffer().get(fArr);
        return fArr;
    }

    public long[] readLongs(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        long[] jArr = new long[pointer.readDataHeaderForArray(8L, i3).elementsOrVersion];
        pointer.mMessage.getData().position(pointer.mBaseOffset + 8);
        pointer.mMessage.getData().asLongBuffer().get(jArr);
        return jArr;
    }

    public double[] readDoubles(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        double[] dArr = new double[pointer.readDataHeaderForArray(8L, i3).elementsOrVersion];
        pointer.mMessage.getData().position(pointer.mBaseOffset + 8);
        pointer.mMessage.getData().asDoubleBuffer().get(dArr);
        return dArr;
    }

    public Handle readHandle(int i, boolean z) {
        int i2 = readInt(i);
        if (i2 != -1) {
            this.mValidator.claimHandle(i2);
            return this.mMessage.getHandles().get(i2);
        }
        if (!z) {
            throw new DeserializationException("Trying to decode an invalid handle for a non-nullable type.");
        }
        return InvalidHandle.INSTANCE;
    }

    public UntypedHandle readUntypedHandle(int i, boolean z) {
        return readHandle(i, z).toUntypedHandle();
    }

    public DataPipe.ConsumerHandle readConsumerHandle(int i, boolean z) {
        return readUntypedHandle(i, z).toDataPipeConsumerHandle();
    }

    public DataPipe.ProducerHandle readProducerHandle(int i, boolean z) {
        return readUntypedHandle(i, z).toDataPipeProducerHandle();
    }

    public MessagePipeHandle readMessagePipeHandle(int i, boolean z) {
        return readUntypedHandle(i, z).toMessagePipeHandle();
    }

    public SharedBufferHandle readSharedBufferHandle(int i, boolean z) {
        return readUntypedHandle(i, z).toSharedBufferHandle();
    }

    public <P extends Interface.Proxy> P readServiceInterface(int i, boolean z, Interface.Manager<?, P> manager) {
        MessagePipeHandle messagePipeHandle = readMessagePipeHandle(i, z);
        if (messagePipeHandle.isValid()) {
            return (P) manager.attachProxy(messagePipeHandle, readInt(i + 4));
        }
        return null;
    }

    public <I extends Interface> InterfaceRequest<I> readInterfaceRequest(int i, boolean z) {
        MessagePipeHandle messagePipeHandle = readMessagePipeHandle(i, z);
        if (messagePipeHandle == null) {
            return null;
        }
        return new InterfaceRequest<>(messagePipeHandle);
    }

    public String readString(int i, boolean z) {
        byte[] bytes = readBytes(i, z ? 1 : 0, -1);
        if (bytes == null) {
            return null;
        }
        return new String(bytes, Charset.forName("utf8"));
    }

    public Handle[] readHandles(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        int i4 = pointer.readDataHeaderForArray(4L, i3).elementsOrVersion;
        Handle[] handleArr = new Handle[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            handleArr[i5] = pointer.readHandle((i5 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
        return handleArr;
    }

    public UntypedHandle[] readUntypedHandles(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        int i4 = pointer.readDataHeaderForArray(4L, i3).elementsOrVersion;
        UntypedHandle[] untypedHandleArr = new UntypedHandle[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            untypedHandleArr[i5] = pointer.readUntypedHandle((i5 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
        return untypedHandleArr;
    }

    public DataPipe.ConsumerHandle[] readConsumerHandles(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        int i4 = pointer.readDataHeaderForArray(4L, i3).elementsOrVersion;
        DataPipe.ConsumerHandle[] consumerHandleArr = new DataPipe.ConsumerHandle[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            consumerHandleArr[i5] = pointer.readConsumerHandle((i5 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
        return consumerHandleArr;
    }

    public DataPipe.ProducerHandle[] readProducerHandles(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        int i4 = pointer.readDataHeaderForArray(4L, i3).elementsOrVersion;
        DataPipe.ProducerHandle[] producerHandleArr = new DataPipe.ProducerHandle[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            producerHandleArr[i5] = pointer.readProducerHandle((i5 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
        return producerHandleArr;
    }

    public MessagePipeHandle[] readMessagePipeHandles(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        int i4 = pointer.readDataHeaderForArray(4L, i3).elementsOrVersion;
        MessagePipeHandle[] messagePipeHandleArr = new MessagePipeHandle[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            messagePipeHandleArr[i5] = pointer.readMessagePipeHandle((i5 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
        return messagePipeHandleArr;
    }

    public SharedBufferHandle[] readSharedBufferHandles(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        int i4 = pointer.readDataHeaderForArray(4L, i3).elementsOrVersion;
        SharedBufferHandle[] sharedBufferHandleArr = new SharedBufferHandle[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            sharedBufferHandleArr[i5] = pointer.readSharedBufferHandle((i5 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
        return sharedBufferHandleArr;
    }

    public <S extends Interface, P extends Interface.Proxy> S[] readServiceInterfaces(int i, int i2, int i3, Interface.Manager<S, P> manager) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        S[] sArr = (S[]) manager.buildArray(pointer.readDataHeaderForArray(8L, i3).elementsOrVersion);
        for (int i4 = 0; i4 < sArr.length; i4++) {
            sArr[i4] = pointer.readServiceInterface((i4 * 8) + 8, BindingsHelper.isElementNullable(i2), manager);
        }
        return sArr;
    }

    public <I extends Interface> InterfaceRequest<I>[] readInterfaceRequests(int i, int i2, int i3) {
        Decoder pointer = readPointer(i, BindingsHelper.isArrayNullable(i2));
        if (pointer == null) {
            return null;
        }
        int i4 = pointer.readDataHeaderForArray(4L, i3).elementsOrVersion;
        InterfaceRequest<I>[] interfaceRequestArr = new InterfaceRequest[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            interfaceRequestArr[i5] = pointer.readInterfaceRequest((i5 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
        return interfaceRequestArr;
    }

    private Decoder getDecoderAtPosition(int i) {
        return new Decoder(this.mMessage, this.mValidator, i);
    }

    private DataHeader readDataHeaderForBooleanArray(int i) {
        DataHeader dataHeader = readDataHeader();
        if (dataHeader.size < ((dataHeader.elementsOrVersion + 7) / 8) + 8) {
            throw new DeserializationException("Array header is incorrect.");
        }
        if (i == -1 || dataHeader.elementsOrVersion == i) {
            return dataHeader;
        }
        throw new DeserializationException("Incorrect array length. Expected: " + i + ", but got: " + dataHeader.elementsOrVersion + ".");
    }

    private DataHeader readDataHeaderForArray(long j, int i) {
        DataHeader dataHeader = readDataHeader();
        if (dataHeader.size < (j * ((long) dataHeader.elementsOrVersion)) + 8) {
            throw new DeserializationException("Array header is incorrect.");
        }
        if (i == -1 || dataHeader.elementsOrVersion == i) {
            return dataHeader;
        }
        throw new DeserializationException("Incorrect array length. Expected: " + i + ", but got: " + dataHeader.elementsOrVersion + ".");
    }

    private void validateBufferSize(int i, int i2) {
        if (this.mMessage.getData().limit() < i + i2) {
            throw new DeserializationException("Buffer is smaller than expected.");
        }
    }
}
