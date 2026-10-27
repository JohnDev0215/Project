package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.RunLoop;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("mojo::android")
public class CoreImpl implements Core, AsyncWaiter {
    private static final int FLAG_SIZE = 4;
    private static final int HANDLE_SIZE = 4;
    static final int INVALID_HANDLE = 0;
    private static final int MOJO_READ_DATA_FLAG_DISCARD = 2;
    private final int mByteBufferOffset;
    private final ThreadLocal<BaseRunLoop> mCurrentRunLoop;

    private static boolean isUnrecoverableError(int i) {
        return (i == 0 || i == 1 || i == 4 || i == 9) ? false : true;
    }

    private native AsyncWaiterCancellableImpl nativeAsyncWait(int i, int i2, long j, AsyncWaiter.Callback callback);

    private native ResultAnd<ByteBuffer> nativeBeginReadData(int i, int i2, int i3);

    private native ResultAnd<ByteBuffer> nativeBeginWriteData(int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeCancelAsyncWait(long j, long j2);

    private native int nativeClose(int i);

    private native ResultAnd<IntegerPair> nativeCreateDataPipe(ByteBuffer byteBuffer);

    private native ResultAnd<IntegerPair> nativeCreateMessagePipe(ByteBuffer byteBuffer);

    private native ResultAnd<Integer> nativeCreateSharedBuffer(ByteBuffer byteBuffer, long j);

    private native ResultAnd<Integer> nativeDuplicate(int i, ByteBuffer byteBuffer);

    private native int nativeEndReadData(int i, int i2);

    private native int nativeEndWriteData(int i, int i2);

    private native int nativeGetNativeBufferOffset(ByteBuffer byteBuffer, int i);

    private native long nativeGetTimeTicksNow();

    private native ResultAnd<ByteBuffer> nativeMap(int i, long j, long j2, int i2);

    private native ResultAnd<Integer> nativeReadData(int i, ByteBuffer byteBuffer, int i2, int i3);

    private native ResultAnd<MessagePipeHandle.ReadMessageResult> nativeReadMessage(int i, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i2);

    private native int nativeUnmap(ByteBuffer byteBuffer);

    private native int nativeWait(ByteBuffer byteBuffer, int i, int i2, long j);

    private native int nativeWaitMany(ByteBuffer byteBuffer, long j);

    private native ResultAnd<Integer> nativeWriteData(int i, ByteBuffer byteBuffer, int i2, int i3);

    private native int nativeWriteMessage(int i, ByteBuffer byteBuffer, int i2, ByteBuffer byteBuffer2, int i3);

    @Override // org.chromium.mojo.system.Core
    public AsyncWaiter getDefaultAsyncWaiter() {
        return this;
    }

    private static class LazyHolder {
        private static final Core INSTANCE = new CoreImpl();

        private LazyHolder() {
        }
    }

    public static Core getInstance() {
        return LazyHolder.INSTANCE;
    }

    private CoreImpl() {
        this.mCurrentRunLoop = new ThreadLocal<>();
        this.mByteBufferOffset = nativeGetNativeBufferOffset(ByteBuffer.allocateDirect(8), 8);
    }

    @Override // org.chromium.mojo.system.Core
    public long getTimeTicksNow() {
        return nativeGetTimeTicksNow();
    }

    @Override // org.chromium.mojo.system.Core
    public Core.WaitManyResult waitMany(List<Pair<Handle, Core.HandleSignals>> list, long j) {
        ByteBuffer byteBufferAllocateDirectBuffer = allocateDirectBuffer((list.size() * 16) + 4);
        int i = 0;
        for (Pair<Handle, Core.HandleSignals> pair : list) {
            int i2 = i * 4;
            byteBufferAllocateDirectBuffer.putInt(i2, getMojoHandle(pair.first));
            byteBufferAllocateDirectBuffer.putInt((list.size() * 4) + i2, pair.second.getFlags());
            i++;
        }
        int iNativeWaitMany = nativeWaitMany(byteBufferAllocateDirectBuffer, j);
        Core.WaitManyResult waitManyResult = new Core.WaitManyResult();
        waitManyResult.setMojoResult(filterMojoResultForWait(iNativeWaitMany));
        waitManyResult.setHandleIndex(byteBufferAllocateDirectBuffer.getInt(list.size() * 16));
        if (waitManyResult.getMojoResult() != 3 && waitManyResult.getMojoResult() != 8) {
            Core.HandleSignalsState[] handleSignalsStateArr = new Core.HandleSignalsState[list.size()];
            for (int i3 = 0; i3 < list.size(); i3++) {
                handleSignalsStateArr[i3] = new Core.HandleSignalsState(new Core.HandleSignals(byteBufferAllocateDirectBuffer.getInt((list.size() + i3) * 8)), new Core.HandleSignals(byteBufferAllocateDirectBuffer.getInt(((list.size() + i3) * 8) + 4)));
            }
            waitManyResult.setSignalStates(Arrays.asList(handleSignalsStateArr));
        }
        return waitManyResult;
    }

    @Override // org.chromium.mojo.system.Core
    public Core.WaitResult wait(Handle handle, Core.HandleSignals handleSignals, long j) {
        ByteBuffer byteBufferAllocateDirectBuffer = allocateDirectBuffer(8);
        Core.WaitResult waitResult = new Core.WaitResult();
        waitResult.setMojoResult(filterMojoResultForWait(nativeWait(byteBufferAllocateDirectBuffer, getMojoHandle(handle), handleSignals.getFlags(), j)));
        waitResult.setHandleSignalsState(new Core.HandleSignalsState(new Core.HandleSignals(byteBufferAllocateDirectBuffer.getInt(0)), new Core.HandleSignals(byteBufferAllocateDirectBuffer.getInt(4))));
        return waitResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.chromium.mojo.system.Core
    public Pair<MessagePipeHandle, MessagePipeHandle> createMessagePipe(MessagePipeHandle.CreateOptions createOptions) {
        ByteBuffer byteBufferAllocateDirectBuffer;
        if (createOptions != null) {
            byteBufferAllocateDirectBuffer = allocateDirectBuffer(8);
            byteBufferAllocateDirectBuffer.putInt(0, 8);
            byteBufferAllocateDirectBuffer.putInt(4, createOptions.getFlags().getFlags());
        } else {
            byteBufferAllocateDirectBuffer = null;
        }
        ResultAnd<IntegerPair> resultAndNativeCreateMessagePipe = nativeCreateMessagePipe(byteBufferAllocateDirectBuffer);
        if (resultAndNativeCreateMessagePipe.getMojoResult() != 0) {
            throw new MojoException(resultAndNativeCreateMessagePipe.getMojoResult());
        }
        return Pair.create(new MessagePipeHandleImpl(this, ((Integer) resultAndNativeCreateMessagePipe.getValue().first).intValue()), new MessagePipeHandleImpl(this, ((Integer) resultAndNativeCreateMessagePipe.getValue().second).intValue()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.chromium.mojo.system.Core
    public Pair<DataPipe.ProducerHandle, DataPipe.ConsumerHandle> createDataPipe(DataPipe.CreateOptions createOptions) {
        ByteBuffer byteBufferAllocateDirectBuffer;
        if (createOptions != null) {
            byteBufferAllocateDirectBuffer = allocateDirectBuffer(16);
            byteBufferAllocateDirectBuffer.putInt(0, 16);
            byteBufferAllocateDirectBuffer.putInt(4, createOptions.getFlags().getFlags());
            byteBufferAllocateDirectBuffer.putInt(8, createOptions.getElementNumBytes());
            byteBufferAllocateDirectBuffer.putInt(12, createOptions.getCapacityNumBytes());
        } else {
            byteBufferAllocateDirectBuffer = null;
        }
        ResultAnd<IntegerPair> resultAndNativeCreateDataPipe = nativeCreateDataPipe(byteBufferAllocateDirectBuffer);
        if (resultAndNativeCreateDataPipe.getMojoResult() != 0) {
            throw new MojoException(resultAndNativeCreateDataPipe.getMojoResult());
        }
        return Pair.create(new DataPipeProducerHandleImpl(this, ((Integer) resultAndNativeCreateDataPipe.getValue().first).intValue()), new DataPipeConsumerHandleImpl(this, ((Integer) resultAndNativeCreateDataPipe.getValue().second).intValue()));
    }

    @Override // org.chromium.mojo.system.Core
    public SharedBufferHandle createSharedBuffer(SharedBufferHandle.CreateOptions createOptions, long j) {
        ByteBuffer byteBufferAllocateDirectBuffer;
        if (createOptions != null) {
            byteBufferAllocateDirectBuffer = allocateDirectBuffer(8);
            byteBufferAllocateDirectBuffer.putInt(0, 8);
            byteBufferAllocateDirectBuffer.putInt(4, createOptions.getFlags().getFlags());
        } else {
            byteBufferAllocateDirectBuffer = null;
        }
        ResultAnd<Integer> resultAndNativeCreateSharedBuffer = nativeCreateSharedBuffer(byteBufferAllocateDirectBuffer, j);
        if (resultAndNativeCreateSharedBuffer.getMojoResult() != 0) {
            throw new MojoException(resultAndNativeCreateSharedBuffer.getMojoResult());
        }
        return new SharedBufferHandleImpl(this, resultAndNativeCreateSharedBuffer.getValue().intValue());
    }

    @Override // org.chromium.mojo.system.Core
    public UntypedHandle acquireNativeHandle(int i) {
        return new UntypedHandleImpl(this, i);
    }

    @Override // org.chromium.mojo.system.Core
    public RunLoop createDefaultRunLoop() {
        if (this.mCurrentRunLoop.get() != null) {
            throw new MojoException(9);
        }
        BaseRunLoop baseRunLoop = new BaseRunLoop(this);
        this.mCurrentRunLoop.set(baseRunLoop);
        return baseRunLoop;
    }

    @Override // org.chromium.mojo.system.Core
    public RunLoop getCurrentRunLoop() {
        return this.mCurrentRunLoop.get();
    }

    void clearCurrentRunLoop() {
        this.mCurrentRunLoop.remove();
    }

    @Override // org.chromium.mojo.system.AsyncWaiter
    public AsyncWaiter.Cancellable asyncWait(Handle handle, Core.HandleSignals handleSignals, long j, AsyncWaiter.Callback callback) {
        return nativeAsyncWait(getMojoHandle(handle), handleSignals.getFlags(), j, callback);
    }

    int closeWithResult(int i) {
        return nativeClose(i);
    }

    void close(int i) {
        int iNativeClose = nativeClose(i);
        if (iNativeClose != 0) {
            throw new MojoException(iNativeClose);
        }
    }

    void writeMessage(MessagePipeHandleImpl messagePipeHandleImpl, ByteBuffer byteBuffer, List<? extends Handle> list, MessagePipeHandle.WriteFlags writeFlags) {
        ByteBuffer byteBufferAllocateDirectBuffer;
        if (list == null || list.isEmpty()) {
            byteBufferAllocateDirectBuffer = null;
        } else {
            byteBufferAllocateDirectBuffer = allocateDirectBuffer(list.size() * 4);
            Iterator<? extends Handle> it = list.iterator();
            while (it.hasNext()) {
                byteBufferAllocateDirectBuffer.putInt(getMojoHandle(it.next()));
            }
            byteBufferAllocateDirectBuffer.position(0);
        }
        int iNativeWriteMessage = nativeWriteMessage(messagePipeHandleImpl.getMojoHandle(), byteBuffer, byteBuffer != null ? byteBuffer.limit() : 0, byteBufferAllocateDirectBuffer, writeFlags.getFlags());
        if (iNativeWriteMessage != 0) {
            throw new MojoException(iNativeWriteMessage);
        }
        if (list != null) {
            for (Handle handle : list) {
                if (handle.isValid()) {
                    ((HandleBase) handle).invalidateHandle();
                }
            }
        }
    }

    ResultAnd<MessagePipeHandle.ReadMessageResult> readMessage(MessagePipeHandleImpl messagePipeHandleImpl, ByteBuffer byteBuffer, int i, MessagePipeHandle.ReadFlags readFlags) {
        ByteBuffer byteBufferAllocateDirectBuffer = i > 0 ? allocateDirectBuffer(i * 4) : null;
        ResultAnd<MessagePipeHandle.ReadMessageResult> resultAndNativeReadMessage = nativeReadMessage(messagePipeHandleImpl.getMojoHandle(), byteBuffer, byteBufferAllocateDirectBuffer, readFlags.getFlags());
        if (resultAndNativeReadMessage.getMojoResult() != 0 && resultAndNativeReadMessage.getMojoResult() != 8 && resultAndNativeReadMessage.getMojoResult() != 17) {
            throw new MojoException(resultAndNativeReadMessage.getMojoResult());
        }
        if (resultAndNativeReadMessage.getMojoResult() == 0) {
            MessagePipeHandle.ReadMessageResult value = resultAndNativeReadMessage.getValue();
            if (byteBuffer != null) {
                byteBuffer.position(0);
                byteBuffer.limit(value.getMessageSize());
            }
            ArrayList arrayList = new ArrayList(value.getHandlesCount());
            for (int i2 = 0; i2 < value.getHandlesCount(); i2++) {
                arrayList.add(new UntypedHandleImpl(this, byteBufferAllocateDirectBuffer.getInt(i2 * 4)));
            }
            value.setHandles(arrayList);
        }
        return resultAndNativeReadMessage;
    }

    int discardData(DataPipeConsumerHandleImpl dataPipeConsumerHandleImpl, int i, DataPipe.ReadFlags readFlags) {
        ResultAnd<Integer> resultAndNativeReadData = nativeReadData(dataPipeConsumerHandleImpl.getMojoHandle(), null, i, readFlags.getFlags() | 2);
        if (resultAndNativeReadData.getMojoResult() != 0) {
            throw new MojoException(resultAndNativeReadData.getMojoResult());
        }
        return resultAndNativeReadData.getValue().intValue();
    }

    ResultAnd<Integer> readData(DataPipeConsumerHandleImpl dataPipeConsumerHandleImpl, ByteBuffer byteBuffer, DataPipe.ReadFlags readFlags) {
        ResultAnd<Integer> resultAndNativeReadData = nativeReadData(dataPipeConsumerHandleImpl.getMojoHandle(), byteBuffer, byteBuffer == null ? 0 : byteBuffer.capacity(), readFlags.getFlags());
        if (resultAndNativeReadData.getMojoResult() != 0 && resultAndNativeReadData.getMojoResult() != 17) {
            throw new MojoException(resultAndNativeReadData.getMojoResult());
        }
        if (resultAndNativeReadData.getMojoResult() == 0 && byteBuffer != null) {
            byteBuffer.limit(resultAndNativeReadData.getValue().intValue());
        }
        return resultAndNativeReadData;
    }

    ByteBuffer beginReadData(DataPipeConsumerHandleImpl dataPipeConsumerHandleImpl, int i, DataPipe.ReadFlags readFlags) {
        ResultAnd<ByteBuffer> resultAndNativeBeginReadData = nativeBeginReadData(dataPipeConsumerHandleImpl.getMojoHandle(), i, readFlags.getFlags());
        if (resultAndNativeBeginReadData.getMojoResult() != 0) {
            throw new MojoException(resultAndNativeBeginReadData.getMojoResult());
        }
        return resultAndNativeBeginReadData.getValue().asReadOnlyBuffer();
    }

    void endReadData(DataPipeConsumerHandleImpl dataPipeConsumerHandleImpl, int i) {
        int iNativeEndReadData = nativeEndReadData(dataPipeConsumerHandleImpl.getMojoHandle(), i);
        if (iNativeEndReadData != 0) {
            throw new MojoException(iNativeEndReadData);
        }
    }

    ResultAnd<Integer> writeData(DataPipeProducerHandleImpl dataPipeProducerHandleImpl, ByteBuffer byteBuffer, DataPipe.WriteFlags writeFlags) {
        return nativeWriteData(dataPipeProducerHandleImpl.getMojoHandle(), byteBuffer, byteBuffer.limit(), writeFlags.getFlags());
    }

    ByteBuffer beginWriteData(DataPipeProducerHandleImpl dataPipeProducerHandleImpl, int i, DataPipe.WriteFlags writeFlags) {
        ResultAnd<ByteBuffer> resultAndNativeBeginWriteData = nativeBeginWriteData(dataPipeProducerHandleImpl.getMojoHandle(), i, writeFlags.getFlags());
        if (resultAndNativeBeginWriteData.getMojoResult() != 0) {
            throw new MojoException(resultAndNativeBeginWriteData.getMojoResult());
        }
        return resultAndNativeBeginWriteData.getValue();
    }

    void endWriteData(DataPipeProducerHandleImpl dataPipeProducerHandleImpl, int i) {
        int iNativeEndWriteData = nativeEndWriteData(dataPipeProducerHandleImpl.getMojoHandle(), i);
        if (iNativeEndWriteData != 0) {
            throw new MojoException(iNativeEndWriteData);
        }
    }

    SharedBufferHandle duplicate(SharedBufferHandleImpl sharedBufferHandleImpl, SharedBufferHandle.DuplicateOptions duplicateOptions) {
        ByteBuffer byteBufferAllocateDirectBuffer;
        if (duplicateOptions != null) {
            byteBufferAllocateDirectBuffer = allocateDirectBuffer(8);
            byteBufferAllocateDirectBuffer.putInt(0, 8);
            byteBufferAllocateDirectBuffer.putInt(4, duplicateOptions.getFlags().getFlags());
        } else {
            byteBufferAllocateDirectBuffer = null;
        }
        ResultAnd<Integer> resultAndNativeDuplicate = nativeDuplicate(sharedBufferHandleImpl.getMojoHandle(), byteBufferAllocateDirectBuffer);
        if (resultAndNativeDuplicate.getMojoResult() != 0) {
            throw new MojoException(resultAndNativeDuplicate.getMojoResult());
        }
        return new SharedBufferHandleImpl(this, resultAndNativeDuplicate.getValue().intValue());
    }

    ByteBuffer map(SharedBufferHandleImpl sharedBufferHandleImpl, long j, long j2, SharedBufferHandle.MapFlags mapFlags) {
        ResultAnd<ByteBuffer> resultAndNativeMap = nativeMap(sharedBufferHandleImpl.getMojoHandle(), j, j2, mapFlags.getFlags());
        if (resultAndNativeMap.getMojoResult() != 0) {
            throw new MojoException(resultAndNativeMap.getMojoResult());
        }
        return resultAndNativeMap.getValue();
    }

    void unmap(ByteBuffer byteBuffer) {
        int iNativeUnmap = nativeUnmap(byteBuffer);
        if (iNativeUnmap != 0) {
            throw new MojoException(iNativeUnmap);
        }
    }

    private int getMojoHandle(Handle handle) {
        if (handle.isValid()) {
            return ((HandleBase) handle).getMojoHandle();
        }
        return 0;
    }

    private static int filterMojoResultForWait(int i) {
        if (isUnrecoverableError(i)) {
            throw new MojoException(i);
        }
        return i;
    }

    private ByteBuffer allocateDirectBuffer(int i) {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i + this.mByteBufferOffset);
        int i2 = this.mByteBufferOffset;
        if (i2 != 0) {
            byteBufferAllocateDirect.position(i2);
            byteBufferAllocateDirect = byteBufferAllocateDirect.slice();
        }
        return byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
    }

    private class AsyncWaiterCancellableImpl implements AsyncWaiter.Cancellable {
        private boolean mActive;
        private final long mDataPtr;
        private final long mId;

        private AsyncWaiterCancellableImpl(long j, long j2) {
            this.mActive = true;
            this.mId = j;
            this.mDataPtr = j2;
        }

        @Override // org.chromium.mojo.system.AsyncWaiter.Cancellable
        public void cancel() {
            if (this.mActive) {
                this.mActive = false;
                CoreImpl.this.nativeCancelAsyncWait(this.mId, this.mDataPtr);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isActive() {
            return this.mActive;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void deactivate() {
            this.mActive = false;
        }
    }

    private AsyncWaiterCancellableImpl newAsyncWaiterCancellableImpl(long j, long j2) {
        return new AsyncWaiterCancellableImpl(j, j2);
    }

    private void onAsyncWaitResult(int i, AsyncWaiter.Callback callback, AsyncWaiterCancellableImpl asyncWaiterCancellableImpl) {
        if (asyncWaiterCancellableImpl.isActive()) {
            asyncWaiterCancellableImpl.deactivate();
            if (isUnrecoverableError(i)) {
                callback.onError(new MojoException(i));
            } else {
                callback.onResult(i);
            }
        }
    }

    private static ResultAnd<ByteBuffer> newResultAndBuffer(int i, ByteBuffer byteBuffer) {
        return new ResultAnd<>(i, byteBuffer);
    }

    private static final class IntegerPair extends Pair<Integer, Integer> {
        public IntegerPair(Integer num, Integer num2) {
            super(num, num2);
        }
    }

    private static ResultAnd<MessagePipeHandle.ReadMessageResult> newReadMessageResult(int i, int i2, int i3) {
        MessagePipeHandle.ReadMessageResult readMessageResult = new MessagePipeHandle.ReadMessageResult();
        readMessageResult.setMessageSize(i2);
        readMessageResult.setHandlesCount(i3);
        return new ResultAnd<>(i, readMessageResult);
    }

    private static ResultAnd<Integer> newResultAndInteger(int i, int i2) {
        return new ResultAnd<>(i, Integer.valueOf(i2));
    }

    private static ResultAnd<IntegerPair> newNativeCreationResult(int i, int i2, int i3) {
        return new ResultAnd<>(i, new IntegerPair(Integer.valueOf(i2), Integer.valueOf(i3)));
    }
}
