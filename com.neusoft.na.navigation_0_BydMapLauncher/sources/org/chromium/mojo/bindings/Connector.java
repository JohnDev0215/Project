package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: loaded from: classes3.dex */
public class Connector implements MessageReceiver, HandleOwner<MessagePipeHandle> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final AsyncWaiter mAsyncWaiter;
    private final AsyncWaiterCallback mAsyncWaiterCallback;
    private AsyncWaiter.Cancellable mCancellable;
    private ConnectionErrorHandler mErrorHandler;
    private MessageReceiver mIncomingMessageReceiver;
    private final MessagePipeHandle mMessagePipeHandle;

    public Connector(MessagePipeHandle messagePipeHandle) {
        this(messagePipeHandle, BindingsHelper.getDefaultAsyncWaiterForHandle(messagePipeHandle));
    }

    public Connector(MessagePipeHandle messagePipeHandle, AsyncWaiter asyncWaiter) {
        this.mAsyncWaiterCallback = new AsyncWaiterCallback();
        this.mCancellable = null;
        this.mMessagePipeHandle = messagePipeHandle;
        this.mAsyncWaiter = asyncWaiter;
    }

    public void setIncomingMessageReceiver(MessageReceiver messageReceiver) {
        this.mIncomingMessageReceiver = messageReceiver;
    }

    public void setErrorHandler(ConnectionErrorHandler connectionErrorHandler) {
        this.mErrorHandler = connectionErrorHandler;
    }

    public void start() {
        registerAsyncWaiterForRead();
    }

    @Override // org.chromium.mojo.bindings.MessageReceiver
    public boolean accept(Message message) {
        try {
            this.mMessagePipeHandle.writeMessage(message.getData(), message.getHandles(), MessagePipeHandle.WriteFlags.NONE);
            return true;
        } catch (MojoException e) {
            onError(e);
            return false;
        }
    }

    @Override // org.chromium.mojo.bindings.HandleOwner
    public MessagePipeHandle passHandle() {
        cancelIfActive();
        MessagePipeHandle messagePipeHandlePass = this.mMessagePipeHandle.pass();
        MessageReceiver messageReceiver = this.mIncomingMessageReceiver;
        if (messageReceiver != null) {
            messageReceiver.close();
        }
        return messagePipeHandlePass;
    }

    @Override // org.chromium.mojo.bindings.MessageReceiver, java.io.Closeable, java.lang.AutoCloseable, org.chromium.mojo.bindings.HandleOwner
    public void close() {
        cancelIfActive();
        this.mMessagePipeHandle.close();
        MessageReceiver messageReceiver = this.mIncomingMessageReceiver;
        if (messageReceiver != null) {
            this.mIncomingMessageReceiver = null;
            messageReceiver.close();
        }
    }

    private class AsyncWaiterCallback implements AsyncWaiter.Callback {
        private AsyncWaiterCallback() {
        }

        @Override // org.chromium.mojo.system.AsyncWaiter.Callback
        public void onResult(int i) {
            Connector.this.onAsyncWaiterResult(i);
        }

        @Override // org.chromium.mojo.system.AsyncWaiter.Callback
        public void onError(MojoException mojoException) {
            Connector.this.mCancellable = null;
            Connector.this.onError(mojoException);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAsyncWaiterResult(int i) {
        this.mCancellable = null;
        if (i == 0) {
            readOutstandingMessages();
        } else {
            onError(new MojoException(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(MojoException mojoException) {
        close();
        ConnectionErrorHandler connectionErrorHandler = this.mErrorHandler;
        if (connectionErrorHandler != null) {
            connectionErrorHandler.onConnectionError(mojoException);
        }
    }

    private void registerAsyncWaiterForRead() {
        AsyncWaiter asyncWaiter = this.mAsyncWaiter;
        if (asyncWaiter != null) {
            this.mCancellable = asyncWaiter.asyncWait(this.mMessagePipeHandle, Core.HandleSignals.READABLE, -1L, this.mAsyncWaiterCallback);
        } else {
            onError(new MojoException(3));
        }
    }

    private void readOutstandingMessages() {
        ResultAnd<Boolean> andDispatchMessage;
        do {
            try {
                andDispatchMessage = readAndDispatchMessage(this.mMessagePipeHandle, this.mIncomingMessageReceiver);
            } catch (MojoException e) {
                onError(e);
                return;
            }
        } while (andDispatchMessage.getValue().booleanValue());
        if (andDispatchMessage.getMojoResult() == 17) {
            registerAsyncWaiterForRead();
        } else {
            onError(new MojoException(andDispatchMessage.getMojoResult()));
        }
    }

    private void cancelIfActive() {
        AsyncWaiter.Cancellable cancellable = this.mCancellable;
        if (cancellable != null) {
            cancellable.cancel();
            this.mCancellable = null;
        }
    }

    static ResultAnd<Boolean> readAndDispatchMessage(MessagePipeHandle messagePipeHandle, MessageReceiver messageReceiver) {
        ResultAnd<MessagePipeHandle.ReadMessageResult> message = messagePipeHandle.readMessage(null, 0, MessagePipeHandle.ReadFlags.NONE);
        if (message.getMojoResult() != 8) {
            return new ResultAnd<>(message.getMojoResult(), false);
        }
        MessagePipeHandle.ReadMessageResult value = message.getValue();
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(value.getMessageSize());
        ResultAnd<MessagePipeHandle.ReadMessageResult> message2 = messagePipeHandle.readMessage(byteBufferAllocateDirect, value.getHandlesCount(), MessagePipeHandle.ReadFlags.NONE);
        if (messageReceiver != null && message2.getMojoResult() == 0) {
            return new ResultAnd<>(message2.getMojoResult(), Boolean.valueOf(messageReceiver.accept(new Message(byteBufferAllocateDirect, message2.getValue().getHandles()))));
        }
        return new ResultAnd<>(message2.getMojoResult(), false);
    }
}
