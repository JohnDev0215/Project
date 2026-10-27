package org.chromium.mojo.bindings;

import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.MessagePipeHandle;

/* JADX INFO: loaded from: classes3.dex */
public class InterfaceRequest<P extends Interface> implements HandleOwner<MessagePipeHandle> {
    private final MessagePipeHandle mHandle;

    InterfaceRequest(MessagePipeHandle messagePipeHandle) {
        this.mHandle = messagePipeHandle;
    }

    @Override // org.chromium.mojo.bindings.HandleOwner
    public MessagePipeHandle passHandle() {
        return this.mHandle.pass();
    }

    @Override // org.chromium.mojo.bindings.HandleOwner, java.lang.AutoCloseable
    public void close() {
        this.mHandle.close();
    }

    public static InterfaceRequest asInterfaceRequestUnsafe(MessagePipeHandle messagePipeHandle) {
        return new InterfaceRequest(messagePipeHandle);
    }
}
