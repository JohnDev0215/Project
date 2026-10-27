package org.chromium.mojo.bindings;

import org.chromium.mojo.system.MessagePipeHandle;

/* JADX INFO: loaded from: classes3.dex */
public interface Router extends MessageReceiverWithResponder, HandleOwner<MessagePipeHandle> {
    void setErrorHandler(ConnectionErrorHandler connectionErrorHandler);

    void setIncomingMessageReceiver(MessageReceiverWithResponder messageReceiverWithResponder);

    void start();
}
