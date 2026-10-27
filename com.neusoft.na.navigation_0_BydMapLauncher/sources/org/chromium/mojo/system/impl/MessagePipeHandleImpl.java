package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: loaded from: classes3.dex */
class MessagePipeHandleImpl extends HandleBase implements MessagePipeHandle {
    MessagePipeHandleImpl(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    MessagePipeHandleImpl(HandleBase handleBase) {
        super(handleBase);
    }

    @Override // org.chromium.mojo.system.Handle
    public MessagePipeHandle pass() {
        return new MessagePipeHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.MessagePipeHandle
    public void writeMessage(ByteBuffer byteBuffer, List<? extends Handle> list, MessagePipeHandle.WriteFlags writeFlags) {
        this.mCore.writeMessage(this, byteBuffer, list, writeFlags);
    }

    @Override // org.chromium.mojo.system.MessagePipeHandle
    public ResultAnd<MessagePipeHandle.ReadMessageResult> readMessage(ByteBuffer byteBuffer, int i, MessagePipeHandle.ReadFlags readFlags) {
        return this.mCore.readMessage(this, byteBuffer, i, readFlags);
    }
}
