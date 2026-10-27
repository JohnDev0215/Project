package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.Pair;

/* JADX INFO: loaded from: classes3.dex */
public class Encoder {
    private static final int INITIAL_BUFFER_SIZE = 1024;
    private int mBaseOffset;
    private final EncoderState mEncoderState;

    public void encode(AssociatedInterfaceNotSupported associatedInterfaceNotSupported, int i, boolean z) {
    }

    public void encode(AssociatedInterfaceRequestNotSupported associatedInterfaceRequestNotSupported, int i, boolean z) {
    }

    public void encode(AssociatedInterfaceNotSupported[] associatedInterfaceNotSupportedArr, int i, int i2, int i3) {
    }

    public void encode(AssociatedInterfaceRequestNotSupported[] associatedInterfaceRequestNotSupportedArr, int i, int i2, int i3) {
    }

    private static class EncoderState {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public ByteBuffer byteBuffer;
        public final Core core;
        public int dataEnd;
        public final List<Handle> handles;

        private EncoderState(Core core, int i) {
            this.handles = new ArrayList();
            this.core = core;
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i <= 0 ? 1024 : i);
            this.byteBuffer = byteBufferAllocateDirect;
            byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
            this.dataEnd = 0;
        }

        public void claimMemory(int i) {
            this.dataEnd += i;
            growIfNeeded();
        }

        private void growIfNeeded() {
            if (this.byteBuffer.capacity() >= this.dataEnd) {
                return;
            }
            int iCapacity = this.byteBuffer.capacity();
            do {
                iCapacity *= 2;
            } while (iCapacity < this.dataEnd);
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iCapacity);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            this.byteBuffer.position(0);
            ByteBuffer byteBuffer = this.byteBuffer;
            byteBuffer.limit(byteBuffer.capacity());
            byteBufferAllocateDirect.put(this.byteBuffer);
            this.byteBuffer = byteBufferAllocateDirect;
        }
    }

    public Message getMessage() {
        this.mEncoderState.byteBuffer.position(0);
        this.mEncoderState.byteBuffer.limit(this.mEncoderState.dataEnd);
        return new Message(this.mEncoderState.byteBuffer, this.mEncoderState.handles);
    }

    public Encoder(Core core, int i) {
        this(new EncoderState(core, i));
    }

    private Encoder(EncoderState encoderState) {
        this.mEncoderState = encoderState;
        this.mBaseOffset = encoderState.dataEnd;
    }

    public Encoder getEncoderAtDataOffset(DataHeader dataHeader) {
        Encoder encoder = new Encoder(this.mEncoderState);
        encoder.encode(dataHeader);
        return encoder;
    }

    public void encode(DataHeader dataHeader) {
        this.mEncoderState.claimMemory(BindingsHelper.align(dataHeader.size));
        encode(dataHeader.size, 0);
        encode(dataHeader.elementsOrVersion, 4);
    }

    public void encode(byte b, int i) {
        this.mEncoderState.byteBuffer.put(this.mBaseOffset + i, b);
    }

    public void encode(boolean z, int i, int i2) {
        if (z) {
            this.mEncoderState.byteBuffer.put(this.mBaseOffset + i, (byte) (this.mEncoderState.byteBuffer.get(this.mBaseOffset + i) | ((byte) (1 << i2))));
        }
    }

    public void encode(short s, int i) {
        this.mEncoderState.byteBuffer.putShort(this.mBaseOffset + i, s);
    }

    public void encode(int i, int i2) {
        this.mEncoderState.byteBuffer.putInt(this.mBaseOffset + i2, i);
    }

    public void encode(float f, int i) {
        this.mEncoderState.byteBuffer.putFloat(this.mBaseOffset + i, f);
    }

    public void encode(long j, int i) {
        this.mEncoderState.byteBuffer.putLong(this.mBaseOffset + i, j);
    }

    public void encode(double d, int i) {
        this.mEncoderState.byteBuffer.putDouble(this.mBaseOffset + i, d);
    }

    public void encode(Struct struct, int i, boolean z) {
        if (struct == null) {
            encodeNullPointer(i, z);
        } else {
            encodePointerToNextUnclaimedData(i);
            struct.encode(this);
        }
    }

    public void encode(Union union, int i, boolean z) {
        if (union == null && !z) {
            throw new SerializationException("Trying to encode a null pointer for a non-nullable type.");
        }
        if (union == null) {
            encode(0L, i);
            encode(0L, i + 8);
        } else {
            union.encode(this, i);
        }
    }

    public void encode(String str, int i, boolean z) {
        if (str == null) {
            encodeNullPointer(i, z);
        } else {
            encode(str.getBytes(Charset.forName("utf8")), i, z ? 1 : 0, -1);
        }
    }

    public void encode(Handle handle, int i, boolean z) {
        if (handle == null || !handle.isValid()) {
            encodeInvalidHandle(i, z);
        } else {
            encode(this.mEncoderState.handles.size(), i);
            this.mEncoderState.handles.add(handle);
        }
    }

    public <T extends Interface> void encode(T t, int i, boolean z, Interface.Manager<T, ?> manager) {
        if (t == null) {
            encodeInvalidHandle(i, z);
            encode(0, i + 4);
            return;
        }
        if (this.mEncoderState.core == null) {
            throw new UnsupportedOperationException("The encoder has been created without a Core. It can't encode an interface.");
        }
        if (t instanceof Interface.Proxy) {
            Interface.Proxy.Handler proxyHandler = ((Interface.Proxy) t).getProxyHandler();
            encode(proxyHandler.passHandle(), i, z);
            encode(proxyHandler.getVersion(), i + 4);
        } else {
            Pair<MessagePipeHandle, MessagePipeHandle> pairCreateMessagePipe = this.mEncoderState.core.createMessagePipe(null);
            manager.bind(t, pairCreateMessagePipe.first);
            encode(pairCreateMessagePipe.second, i, z);
            encode(manager.getVersion(), i + 4);
        }
    }

    public <I extends Interface> void encode(InterfaceRequest<I> interfaceRequest, int i, boolean z) {
        if (interfaceRequest == null) {
            encodeInvalidHandle(i, z);
        } else {
            if (this.mEncoderState.core == null) {
                throw new UnsupportedOperationException("The encoder has been created without a Core. It can't encode an interface.");
            }
            encode(interfaceRequest.passHandle(), i, z);
        }
    }

    public Encoder encodePointerArray(int i, int i2, int i3) {
        return encoderForArray(8, i, i2, i3);
    }

    public Encoder encodeUnionArray(int i, int i2, int i3) {
        return encoderForArray(16, i, i2, i3);
    }

    public void encode(boolean[] zArr, int i, int i2, int i3) {
        if (zArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
            return;
        }
        if (i3 != -1 && i3 != zArr.length) {
            throw new SerializationException("Trying to encode a fixed array of incorrect length.");
        }
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int i4 = 0; i4 < length; i4++) {
            for (int i5 = 0; i5 < 8; i5++) {
                int i6 = (i4 * 8) + i5;
                if (i6 < zArr.length && zArr[i6]) {
                    bArr[i4] = (byte) (bArr[i4] | ((byte) (1 << i5)));
                }
            }
        }
        encodeByteArray(bArr, zArr.length, i);
    }

    public void encode(byte[] bArr, int i, int i2, int i3) {
        if (bArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
        } else {
            if (i3 != -1 && i3 != bArr.length) {
                throw new SerializationException("Trying to encode a fixed array of incorrect length.");
            }
            encodeByteArray(bArr, bArr.length, i);
        }
    }

    public void encode(short[] sArr, int i, int i2, int i3) {
        if (sArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
        } else {
            encoderForArray(2, sArr.length, i, i3).append(sArr);
        }
    }

    public void encode(int[] iArr, int i, int i2, int i3) {
        if (iArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
        } else {
            encoderForArray(4, iArr.length, i, i3).append(iArr);
        }
    }

    public void encode(float[] fArr, int i, int i2, int i3) {
        if (fArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
        } else {
            encoderForArray(4, fArr.length, i, i3).append(fArr);
        }
    }

    public void encode(long[] jArr, int i, int i2, int i3) {
        if (jArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
        } else {
            encoderForArray(8, jArr.length, i, i3).append(jArr);
        }
    }

    public void encode(double[] dArr, int i, int i2, int i3) {
        if (dArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
        } else {
            encoderForArray(8, dArr.length, i, i3).append(dArr);
        }
    }

    public void encode(Handle[] handleArr, int i, int i2, int i3) {
        if (handleArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
            return;
        }
        Encoder encoderEncoderForArray = encoderForArray(4, handleArr.length, i, i3);
        for (int i4 = 0; i4 < handleArr.length; i4++) {
            encoderEncoderForArray.encode(handleArr[i4], (i4 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
    }

    public <T extends Interface> void encode(T[] tArr, int i, int i2, int i3, Interface.Manager<T, ?> manager) {
        if (tArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
            return;
        }
        Encoder encoderEncoderForArray = encoderForArray(8, tArr.length, i, i3);
        for (int i4 = 0; i4 < tArr.length; i4++) {
            encoderEncoderForArray.encode(tArr[i4], (i4 * 8) + 8, BindingsHelper.isElementNullable(i2), manager);
        }
    }

    public Encoder encoderForMap(int i) {
        encodePointerToNextUnclaimedData(i);
        return getEncoderAtDataOffset(BindingsHelper.MAP_STRUCT_HEADER);
    }

    public Encoder encoderForUnionPointer(int i) {
        encodePointerToNextUnclaimedData(i);
        Encoder encoder = new Encoder(this.mEncoderState);
        encoder.mEncoderState.claimMemory(16);
        return encoder;
    }

    public <I extends Interface> void encode(InterfaceRequest<I>[] interfaceRequestArr, int i, int i2, int i3) {
        if (interfaceRequestArr == null) {
            encodeNullPointer(i, BindingsHelper.isArrayNullable(i2));
            return;
        }
        Encoder encoderEncoderForArray = encoderForArray(4, interfaceRequestArr.length, i, i3);
        for (int i4 = 0; i4 < interfaceRequestArr.length; i4++) {
            encoderEncoderForArray.encode(interfaceRequestArr[i4], (i4 * 4) + 8, BindingsHelper.isElementNullable(i2));
        }
    }

    public void encodeNullPointer(int i, boolean z) {
        if (!z) {
            throw new SerializationException("Trying to encode a null pointer for a non-nullable type.");
        }
        this.mEncoderState.byteBuffer.putLong(this.mBaseOffset + i, 0L);
    }

    public void encodeInvalidHandle(int i, boolean z) {
        if (!z) {
            throw new SerializationException("Trying to encode an invalid handle for a non-nullable type.");
        }
        this.mEncoderState.byteBuffer.putInt(this.mBaseOffset + i, -1);
    }

    void claimMemory(int i) {
        this.mEncoderState.claimMemory(BindingsHelper.align(i));
    }

    private void encodePointerToNextUnclaimedData(int i) {
        encode(((long) this.mEncoderState.dataEnd) - ((long) (this.mBaseOffset + i)), i);
    }

    private Encoder encoderForArray(int i, int i2, int i3, int i4) {
        if (i4 != -1 && i4 != i2) {
            throw new SerializationException("Trying to encode a fixed array of incorrect length.");
        }
        return encoderForArrayByTotalSize(i * i2, i2, i3);
    }

    private Encoder encoderForArrayByTotalSize(int i, int i2, int i3) {
        encodePointerToNextUnclaimedData(i3);
        return getEncoderAtDataOffset(new DataHeader(i + 8, i2));
    }

    private void encodeByteArray(byte[] bArr, int i, int i2) {
        encoderForArrayByTotalSize(bArr.length, i, i2).append(bArr);
    }

    private void append(byte[] bArr) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.put(bArr);
    }

    private void append(short[] sArr) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asShortBuffer().put(sArr);
    }

    private void append(int[] iArr) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asIntBuffer().put(iArr);
    }

    private void append(float[] fArr) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asFloatBuffer().put(fArr);
    }

    private void append(double[] dArr) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asDoubleBuffer().put(dArr);
    }

    private void append(long[] jArr) {
        this.mEncoderState.byteBuffer.position(this.mBaseOffset + 8);
        this.mEncoderState.byteBuffer.asLongBuffer().put(jArr);
    }
}
