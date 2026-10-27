package org.chromium.mojo.system.impl;

import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

/* JADX INFO: loaded from: classes3.dex */
class UntypedHandleImpl extends HandleBase implements UntypedHandle {
    UntypedHandleImpl(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    UntypedHandleImpl(HandleBase handleBase) {
        super(handleBase);
    }

    @Override // org.chromium.mojo.system.Handle
    public UntypedHandle pass() {
        return new UntypedHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.UntypedHandle
    public MessagePipeHandle toMessagePipeHandle() {
        return new MessagePipeHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.UntypedHandle
    public DataPipe.ConsumerHandle toDataPipeConsumerHandle() {
        return new DataPipeConsumerHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.UntypedHandle
    public DataPipe.ProducerHandle toDataPipeProducerHandle() {
        return new DataPipeProducerHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.UntypedHandle
    public SharedBufferHandle toSharedBufferHandle() {
        return new SharedBufferHandleImpl(this);
    }
}
