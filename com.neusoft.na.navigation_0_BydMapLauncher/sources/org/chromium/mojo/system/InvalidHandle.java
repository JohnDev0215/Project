package org.chromium.mojo.system;

import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;

/* JADX INFO: loaded from: classes3.dex */
public class InvalidHandle implements UntypedHandle, MessagePipeHandle, DataPipe.ConsumerHandle, DataPipe.ProducerHandle, SharedBufferHandle {
    public static final InvalidHandle INSTANCE = new InvalidHandle();

    @Override // org.chromium.mojo.system.Handle, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // org.chromium.mojo.system.Handle
    public Core getCore() {
        return null;
    }

    @Override // org.chromium.mojo.system.Handle
    public boolean isValid() {
        return false;
    }

    @Override // org.chromium.mojo.system.UntypedHandle, org.chromium.mojo.system.Handle
    public InvalidHandle pass() {
        return this;
    }

    @Override // org.chromium.mojo.system.Handle
    public int releaseNativeHandle() {
        return 0;
    }

    @Override // org.chromium.mojo.system.UntypedHandle
    public DataPipe.ConsumerHandle toDataPipeConsumerHandle() {
        return this;
    }

    @Override // org.chromium.mojo.system.UntypedHandle
    public DataPipe.ProducerHandle toDataPipeProducerHandle() {
        return this;
    }

    @Override // org.chromium.mojo.system.UntypedHandle
    public MessagePipeHandle toMessagePipeHandle() {
        return this;
    }

    @Override // org.chromium.mojo.system.UntypedHandle
    public SharedBufferHandle toSharedBufferHandle() {
        return this;
    }

    @Override // org.chromium.mojo.system.Handle
    public UntypedHandle toUntypedHandle() {
        return this;
    }

    private InvalidHandle() {
    }

    @Override // org.chromium.mojo.system.Handle
    public Core.WaitResult wait(Core.HandleSignals handleSignals, long j) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.SharedBufferHandle
    public SharedBufferHandle duplicate(SharedBufferHandle.DuplicateOptions duplicateOptions) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.SharedBufferHandle
    public ByteBuffer map(long j, long j2, SharedBufferHandle.MapFlags mapFlags) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.SharedBufferHandle
    public void unmap(ByteBuffer byteBuffer) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.DataPipe.ProducerHandle
    public ResultAnd<Integer> writeData(ByteBuffer byteBuffer, DataPipe.WriteFlags writeFlags) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.DataPipe.ProducerHandle
    public ByteBuffer beginWriteData(int i, DataPipe.WriteFlags writeFlags) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.DataPipe.ProducerHandle
    public void endWriteData(int i) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.DataPipe.ConsumerHandle
    public int discardData(int i, DataPipe.ReadFlags readFlags) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.DataPipe.ConsumerHandle
    public ResultAnd<Integer> readData(ByteBuffer byteBuffer, DataPipe.ReadFlags readFlags) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.DataPipe.ConsumerHandle
    public ByteBuffer beginReadData(int i, DataPipe.ReadFlags readFlags) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.DataPipe.ConsumerHandle
    public void endReadData(int i) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.MessagePipeHandle
    public void writeMessage(ByteBuffer byteBuffer, List<? extends Handle> list, MessagePipeHandle.WriteFlags writeFlags) {
        throw new MojoException(3);
    }

    @Override // org.chromium.mojo.system.MessagePipeHandle
    public ResultAnd<MessagePipeHandle.ReadMessageResult> readMessage(ByteBuffer byteBuffer, int i, MessagePipeHandle.ReadFlags readFlags) {
        throw new MojoException(3);
    }
}
